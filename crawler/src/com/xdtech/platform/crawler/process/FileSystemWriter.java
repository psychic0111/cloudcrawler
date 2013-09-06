package com.xdtech.platform.crawler.process;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.xdtech.platform.crawler.ControlStatus;
import com.xdtech.platform.crawler.parser.Outlink;
import com.xdtech.platform.crawler.protocol.FetchEntry;
import com.xdtech.platform.crawler.protocol.HtmlFetcher;
import com.xdtech.platform.crawler.protocol.ProtocolStatus;
import com.xdtech.platform.util.AppConf;
import com.xdtech.platform.util.CrawlerUtil;
import com.xdtech.platform.util.URLType;

/**
 * 写文件类
 * 
 * @author zhangjianbing@msn.com
 * 
 */
public class FileSystemWriter {
	/** 日志 */
	private static Logger logger = Logger.getLogger(FileSystemWriter.class);
	/** 根目录 */
	private static String rootFolder = AppConf.get().get("writer.base.folder");
	/** 年月日 */
	private static DateFormat ymd = new SimpleDateFormat("yyyyMMdd");
	/** 当前使用的目录 */
	private static File useFolder = null;
	/** 当前使用的文件夹最后一次使用时间 */
	private static long useFolderUseTime = 0;
	/** 当前文件夹中文件数量 */
	private static int userFolderFileCount = 0;
	/** 单个文件夹下最多允许的文件个数 */
	private final static int MAXFILECOUNT = 500;
	/** 数据文件的后缀 */
	public final static String SUFFIX = ".content";
	/***/
	private static Map<String, Set<String>> table = new Hashtable<String, Set<String>>();
	/***/
	private static Map<String, String> map = new HashMap<String, String>();
	/** XML写工具 */
	private static XMLWriter xmlWriter = new XMLWriter();
	static {
		map.put("com", "");
		map.put("cn", "");
		map.put("zz", "");
		map.put("org", "");
		map.put("edu", "");
		map.put("net", "");
		map.put("cc", "");
		map.put("hk", "");
		map.put("tv", "");
		File file = new File(rootFolder);
		if (!file.exists()) {
			file.mkdirs();
		}
		logger.info("文件根目录：" + file.getAbsolutePath());
		createFolder();
		Thread thread = new Thread(new Runnable() {
			public void run() {
				while (true) {
					if (ControlStatus.shutdown) {
						break;
					}
					try {
						Thread.sleep(20000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					long cur = System.currentTimeMillis();
					if (cur - useFolderUseTime > 1 * 60 * 1000) {
						// 超过15分钟没有使用则关闭该文件夹
						synchronized (useFolder) {
							File[] files = useFolder.listFiles();
							if (files != null && files.length > 0) {
								xmlWriter.close(useFolder);
								createFolder();
							}
						}
					}
				}
			}
		});
		thread.start();
	}

	/**
	 * 创建文件夹,修改当前操作的文件夹
	 */
	private synchronized static void createFolder() {
		File file = new File(rootFolder);
		Date date = new Date();
		String dayFolder = ymd.format(date);
		file = new File(file, dayFolder);
		if (!file.exists()) {
			file.mkdirs();
		}
		String hFolder = System.currentTimeMillis() + "";
		file = new File(file, hFolder);
		if (!file.exists()) {
			file.mkdirs();
		}
		useFolder = file;
		useFolderUseTime = System.currentTimeMillis();
		logger.info("当前使用目录：" + useFolder.getAbsolutePath());
		userFolderFileCount = 0;
	}

	/**
	 * 写文本文件
	 * 
	 * @param url
	 * @param headers
	 * @param text
	 * @param outlinks
	 */
	public static void write(String url, Map<String, List<String>> headers, String text, Outlink[] outlinks, FetchEntry fle, ProtocolStatus status, InputStream is) {
		String threadId = String.valueOf(Thread.currentThread().getId());
		File folder = null;
		try {
			if (text != null || is != null) {
				synchronized (useFolder) {
					synchronized (table) {
						folder = new File(useFolder.getAbsolutePath());
						String path = folder.getAbsolutePath();
						Set<String> useThread = table.get(path);
						if (useThread == null) {
							useThread = new HashSet<String>();
							table.put(path, useThread);
						}
						useThread.add(threadId);
						userFolderFileCount++;
						if (userFolderFileCount >= MAXFILECOUNT) {
							createFolder();
						}
					}
				}
				ContentWriter writer = null;
				if (is == null) {
					writer = new TextWriter();
				} else {
					writer = new StreamWriter();
				}
				writer.setIs(is);
				writer.setFle(fle);
				writer.setHeaders(headers);
				writer.setOutlinks(outlinks);
				writer.setText(text);
				writer.setUrl(url);
				writer.setFolder(folder);
				File file = writer.write();
				synchronized (table) {
					Set<String> container = table.get(folder.getAbsolutePath());
					if (container != null) {
						container.remove(threadId);
					}
				}

				List<String> contentTypes = headers.get("content-type");
				String type = "text";
				String ftype = "";
				String fethcStateCode = (status.getCode() == ProtocolStatus.SUCCESS) ? "200" : "404";
				String priority = fle.getLevel() + "";
				String md5encode = fle.getMd5();
				String tag = URLType.toString(fle.getUrlType());
				String fileName = file.getName();
				if (contentTypes != null) {
					ContentTypeInfo info = null;
					for (String stype : contentTypes) {
						if (stype != null) {
							info = findType(stype, url, fle.getUrlType());
							type = info.isText() ? "text" : "media";
							ftype = info.getFileType();
						}
					}
				}
				xmlWriter.append(file.getParentFile(), type, ftype, fethcStateCode, priority, md5encode, tag, url, fileName, outlinks, headers);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 根据头信息猜测文件类型
	 * 
	 * @param contentType
	 * @param url
	 * @return
	 */
	public static ContentTypeInfo findType(String contentType, String url, int urlType) {
		return CrawlerUtil.findType(contentType, url, urlType);
	}

	/**
	 * XML的writer
	 * 
	 * @author zhangjianbing@msn.com
	 * 
	 */
	private static class XMLWriter {
		private static String start = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><urls>";
		private static String end = "</urls>";
		/** xml文件锁 */
		private static final Object lock = new Object();

		/**
		 * 追加
		 * 
		 * @param folder
		 *            文件夹
		 * @param type
		 *            资源类型
		 * @param ftype
		 *            资源对应的文件类型
		 * @param fethcStateCode
		 *            HTTP状态码
		 * @param priority
		 *            等级
		 * @param md5encode
		 *            MD5
		 * @param tag
		 *            来源标签
		 * @param url
		 *            url
		 * @param fileName
		 *            文件名称
		 * @param outlinks
		 *            外部链接
		 * @param headers
		 *            请求头信息
		 */
		private synchronized void append(File folder, String type, String ftype, String fethcStateCode, String priority, String md5encode, String tag, String url, String fileName, Outlink[] outlinks, Map<String, List<String>> headers) {
			synchronized (lock) {
				synchronized (table) {
					if (folder.equals(useFolder)) {
						useFolderUseTime = System.currentTimeMillis();
					}
					File xmlFile = new File(folder, "xml.dat");
					Document doc = DocumentHelper.createDocument();
					Element root = doc.addElement("urls");
					Element urlElement = root.addElement("url");
					urlElement.addAttribute("type", type);
					urlElement.addAttribute("ftype", ftype);
					urlElement.addAttribute("fethcStateCode", fethcStateCode);
					urlElement.addAttribute("priority", priority);
					urlElement.addAttribute("md5encode", md5encode);
					urlElement.addAttribute("tag", tag);
					Element urlValue = urlElement.addElement("urlValue");
					urlValue.addCDATA(url);
					Element file = urlElement.addElement("file");
					file.addCDATA(fileName);
					Element headerElement = urlElement.addElement("httpHeader");
					headerElement.addCDATA((headers != null && !headers.isEmpty()) ? headers.toString() : "");
					Element assicatedURLs = urlElement.addElement("assicatedURLs");
					if (outlinks != null) {
						String utype = "";// 0:資源文件 1:外部鏈接
						for (Outlink outlink : outlinks) {
							int outlinkType = outlink.getType();
							switch (outlinkType) {
							case 1:
							case 2:
							case 3:
							case 4:
								utype = "0";
								break;
							default:
								utype = "1";
								break;
							}
							Element assicatedUrl = assicatedURLs.addElement("assicatedUrl");
							assicatedUrl.addAttribute("type", utype);
							assicatedUrl.addCDATA(outlink.getUrl());
						}
					}
					String xmlContent = urlElement.asXML();
					BufferedWriter bw = null;
					try {
						bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(xmlFile, true), "UTF-8"));
						bw.write(xmlContent);
						bw.newLine();
						bw.close();
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					} finally {
						try {
							bw.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}

					String path = folder.getAbsolutePath();
					Set<String> container = table.get(path);
					if (container == null) {
						System.out.println(path);
					}
					if (container.isEmpty()) {
						String[] files = folder.list(new FilenameFilter() {
							public boolean accept(File dir, String name) {
								if (name.endsWith(SUFFIX)) {
									return true;
								}
								return false;
							}
						});
						if (files.length >= MAXFILECOUNT) {
							close(xmlFile.getParentFile());
							table.remove(path);
						}
					}
				}
			}
		}

		private synchronized void close(File useFolder) {
			synchronized (lock) {
				File xmlFile = new File(useFolder, "xml.dat");
				if (xmlFile != null && xmlFile.exists()) {
					BufferedReader br = null;
					File xml = new File(xmlFile.getParent(), "xml.rec");
					BufferedWriter bw = null;
					try {
						bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(xml, true), "UTF-8"));
						br = new BufferedReader(new InputStreamReader(new FileInputStream(xmlFile), "UTF-8"));
						bw.write(start);
						String line = null;
						while ((line = br.readLine()) != null) {
							bw.write(line);
							bw.newLine();
						}
						bw.write(end);
						bw.close();
						br.close();
						String xmlFileName = (xml.getParentFile().listFiles().length - 2) + "";
						File newFile = new File(xmlFile.getParentFile(), xmlFileName + ".xml");
						xml.renameTo(newFile);
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					} finally {
						if (bw != null) {
							try {
								bw.close();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
						if (br != null) {
							try {
								br.close();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
		}
	}

}
