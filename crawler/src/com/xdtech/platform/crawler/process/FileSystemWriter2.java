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

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.xdtech.platform.crawler.ControlStatus;
import com.xdtech.platform.crawler.parser.Outlink;
import com.xdtech.platform.crawler.protocol.FetchEntry;
import com.xdtech.platform.crawler.protocol.ProtocolStatus;
import com.xdtech.platform.util.AppConf;
import com.xdtech.platform.util.URLType;

public class FileSystemWriter2 {
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
							if (files.length > 0) {
								xmlWriter.close(useFolder);
								createFolder();
							}
						}
					}
				}
			}
		});
		// thread.start();
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
	public static void write1(String url, Map<String, List<String>> headers, String text, Outlink[] outlinks, FetchEntry fle, ProtocolStatus status, InputStream is) {
		File folder = null;
		try {
			if (text != null || is != null) {
				synchronized (useFolder) {
					if (useFolder.list().length >= 799) {
						createFolder();
					}
					folder = new File(useFolder.getAbsolutePath());
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
							info = findType(stype, url);
							type = info.isText() ? "text" : "media";
							ftype = info.getFileType();
						}
					}
				}
				append(file, type, ftype, fethcStateCode, priority, md5encode, tag, url, fileName, outlinks, headers);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void append(File datafile, String type, String ftype, String fethcStateCode, String priority, String md5encode, String tag, String url, String fileName, Outlink[] outlinks, Map<String, List<String>> headers) {
		File xmlFile = new File(datafile.getParentFile(), datafile.getName() + ".dat");
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
	}

	/**
	 * 根据头信息猜测文件类型
	 * 
	 * @param contentType
	 * @param url
	 * @return
	 */
	public static ContentTypeInfo findType(String contentType, String url) {
		boolean text = false;
		String result = "";
		String urlFile = "";
		try {
			URL uri = new URL(url.split("\\?")[0]);
			urlFile = uri.getFile();
			if (urlFile != null && urlFile.length() > 0 && urlFile.contains(".")) {
				String tt[] = urlFile.split("\\.");
				if (tt.length > 1) {
					if (tt[1] != null) {
						String tempType = tt[1].toLowerCase();
						if (!map.containsKey(tempType)) {
							result = tt[1];
						}
					}
				}
			}
		} catch (MalformedURLException e) {
		}
		if (contentType != null && contentType.trim().length() > 0) {
			contentType = contentType.trim().toLowerCase();
			if (contentType.startsWith("text")) {
				text = true;
				if (contentType.contains("htm")) {
					result = "html";
				} else if (contentType.contains("plain")) {
					result = "txt";
				} else if (contentType.contains("css")) {
					result = "css";
				}
			} else if (contentType.startsWith("application")) {
				if (contentType.contains("dsssl") || contentType.contains("script") || contentType.contains("json")) {
					text = true;
					if (contentType.contains("script") || contentType.contains("json")) {
						result = "js";
					} else {
						result = "xsl";
					}
				} else if (contentType.contains("msword")) {
					text = false;
					if (result != null && result.length() > 0) {
					} else {
						result = "doc";
					}
				} else if (contentType.contains("excel")) {
					text = false;
					if (result != null && result.length() > 0) {
					} else {
						result = "xls";
					}
				} else if (contentType.contains("pdf")) {
					text = false;
					if (result != null && result.length() > 0) {
					} else {
						result = "pdf";
					}
				} else if (contentType.contains("powerpoint")) {
					text = false;
					if (result != null && result.length() > 0) {
					} else {
						result = "ppt";
					}
				}
			} else if (contentType.startsWith("image")) {
				text = false;
				if (contentType.contains("gif")) {
					result = "gif";
				} else if (contentType.contains("png")) {
					result = "png";
				} else if (contentType.contains("jpeg")) {
					result = "jpg";
				} else if (contentType.contains("tiff")) {
					result = "tif";
				} else if (contentType.contains("bmp") || contentType.contains("bitmap")) {
					result = "bmp";
				} else {
					result = "jpg";
				}
			}
		}

		ContentTypeInfo info = new ContentTypeInfo();
		info.setFileType(result);
		info.setText(text);
		return info;
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
		private synchronized void append(File datafile, String type, String ftype, String fethcStateCode, String priority, String md5encode, String tag, String url, String fileName, Outlink[] outlinks, Map<String, List<String>> headers) {
			File xmlFile = new File(datafile.getParentFile(), datafile.getName() + ".dat");
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
