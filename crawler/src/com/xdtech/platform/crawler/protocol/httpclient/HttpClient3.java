package com.xdtech.platform.crawler.protocol.httpclient;

import java.io.ByteArrayOutputStream;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;

import com.xdtech.platform.crawler.CrawlerStatus;
import com.xdtech.platform.crawler.control.NConfigManager;
import com.xdtech.platform.crawler.control.bean.ProxyConfig;
import com.xdtech.platform.crawler.control.bean.Site;
import com.xdtech.platform.crawler.control.nbean.NSite;
import com.xdtech.platform.crawler.logger.LoggerWriter;
import com.xdtech.platform.crawler.process.ContentTypeInfo;
import com.xdtech.platform.crawler.process.FileSystemWriter;
import com.xdtech.platform.crawler.protocol.Content;
import com.xdtech.platform.crawler.protocol.FetchEntry;
import com.xdtech.platform.crawler.protocol.Protocol;
import com.xdtech.platform.crawler.protocol.ProtocolOutput;
import com.xdtech.platform.crawler.protocol.ProtocolStatus;
import com.xdtech.platform.crawler.ws.ndispatcher.SiteProxy;
import com.xdtech.platform.util.AppConf;
import com.xdtech.platform.util.CrawlerUtil;

public class HttpClient3 implements Protocol {
	private static MultiThreadedHttpConnectionManager connectionManager = new MultiThreadedHttpConnectionManager();
	private static HttpClient client;
	/** 下载超时时间跟连接超时时间 */
	private static int TIMEOUT = AppConf.get().getInt("crawler.html.timeout", 60000);
	/** 发送数据缓存以及接收数据缓存 */
	private static final int BUFFER_SIZE = 8 * 1024;
	/** 总的下载线程数 */
	private static int MAX_THREADS_TOTAL = 40;
	/** 单站点最大下载线程数 */
	private static int MAX_THREADS_PER_HOST = 5;
	/** User-Agent */
	private static String userAgent = AppConf.get().get("crawler.userAgent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:15.0) Gecko/20100101 Firefox/15.0.1");
	private static long FILE_MAX_SIZE = 1024 * 1024 * 1;
	/** 代理主键跟客户端的映射 */
	private static Map<String, HttpClient> proxyClinetMap = new HashMap<String, HttpClient>();

	public HttpClient3() {
		if (NConfigManager.getInstance().getConf() != null) {
			MAX_THREADS_TOTAL = NConfigManager.getInstance().getConf().getCrawlerThread() + NConfigManager.getInstance().getConf().getDownloadThread();
			MAX_THREADS_PER_HOST = NConfigManager.getInstance().getConf().getSiteThread();
		} else {
			MAX_THREADS_TOTAL = 40;
			MAX_THREADS_PER_HOST = 5;
		}
	}

	public static synchronized HttpClient getClient(FetchEntry fle) {
		NSite site = null;
		if (fle.getSiteId() != null) {
			site = NConfigManager.getInstance().getSiteById(fle.getSiteId());
		}
		SiteProxy proxyConfig = null;
		if (site != null && site.getProxyId() != null) {
			proxyConfig = NConfigManager.getSiteProxy(site.getId());
		}
		if (proxyConfig != null && proxyConfig.getId() != null) {
			HttpClient client = proxyClinetMap.get(proxyConfig.getId());
			if (client == null) {
				client = new HttpClient(connectionManager);
				configureClient(client);
				client.getHostConfiguration().setProxy(proxyConfig.getProxyIp(), proxyConfig.getProxyPort());
				proxyClinetMap.put(proxyConfig.getId(), client);
			}
			return client;
		} else {
			if (client != null) {
				return client;
			}
			configureClient();
		}
		return client;
	}

	/**
	 * 用于开发测试的方法，不要调用
	 * 
	 * @param fle
	 * @return
	 */
	@Deprecated
	private static synchronized HttpClient getClient1(FetchEntry fle) {
		HttpClient client = new HttpClient();
		configureClient(client);
		client.getHostConfiguration().setProxy("127.0.0.1", 9666);
		return client;
	}

	/**
	 * 请求数据
	 */
	public ProtocolOutput getProtocolOutput(FetchEntry fle) {
		return response(fle);
	}

	private ProtocolOutput response(FetchEntry fle) {
		String url = fle.getUrl();
		URL urlObject;
		Integer count = -1;
		Date crawlerTime = new Date();
		try {
			urlObject = new URL(url);
			GetMethod get = new GetMethod(url);
			get.setFollowRedirects(false);
			get.setRequestHeader("User-Agent", userAgent);
			get.setRequestHeader("Referer", url);
			// get.setRequestHeader("Cookie", "");暂不做处理
			
			HttpClient client = getClient(fle);
			// client = getClient1(fle);
			int code = 0;
			Content c = null;
			try {
				code = client.executeMethod(get);
				Header[] headers = get.getResponseHeaders();
				Map<String, List<String>> headerMap = new HashMap<String, List<String>>();
				if (headers != null) {
					for (Header header : headers) {
						String key = header.getName();
						String value = header.getValue();
						if (key != null && value != null) {
							List<String> values = headerMap.get(key.toLowerCase());
							if (values == null) {
								values = new ArrayList<String>();
								headerMap.put(key.toLowerCase(), values);
							}
							values.add(value);
						}
					}
				}

				List<String> contentTypes = headerMap.get("content-type");
				String contentType = null;
				if (contentTypes != null && !contentTypes.isEmpty()) {
					contentType = contentTypes.get(0);
				}
				ContentTypeInfo typeInfo = CrawlerUtil.findType(contentType, url, fle.getUrlType());
				if (!typeInfo.isText()) {
					if (code == 200) {
						InputStream in = get.getResponseBodyAsStream();
						count = 1;
						crawlerTime = new Date();
						return download(in, fle.getOldUrl(), headerMap, fle,count,"",crawlerTime);
					} else {
						c = new Content();
						c.setUrl(url);
						c.setHeaders(headerMap);
					}
				} else {
					try {
						InputStream in = get.getResponseBodyAsStream();
						if (in != null) {
							byte[] buffer = new byte[BUFFER_SIZE];
							int bufferFilled = 0;
							int totalRead = 0;
							boolean giveUp = false;
							ByteArrayOutputStream out = new ByteArrayOutputStream();
							while (in != null && (bufferFilled = in.read(buffer, 0, buffer.length)) != -1) {
								totalRead += bufferFilled;
								if (FILE_MAX_SIZE <= totalRead) {
									giveUp = true;
									break;
								}
								out.write(buffer, 0, bufferFilled);
							}
							buffer = null;
							byte[] bcontent = null;
							if (!giveUp) {
								bcontent = out.toByteArray();
							} else {
								bcontent = new byte[0];
							}
							if (in != null) {
								in.close();
							}
							c = new Content();
							c.setUrl(url);
							c.setHeaders(headerMap);
							c.setContent(bcontent);
						} else {
							code = 404;
						}
					} catch (Exception e) {
						System.out.println(url);
						LoggerWriter.writeErro(url + ":" + e.getMessage());
						e.printStackTrace();
						if (code == 200) {
							throw new IOException(e.toString());
						}
					}
				}
				get.releaseConnection();
			} catch (HttpException e) {
				CrawlerStatus.failCount++;
				System.out.println(url);
				e.printStackTrace();
				LoggerWriter.writeErro(url + ":" + e.getMessage());
				get.releaseConnection();
			} catch (IOException e) {
				CrawlerStatus.failCount++;
				System.out.println(url);
				e.printStackTrace();
				LoggerWriter.writeErro(url + ":" + e.getMessage());
				get.releaseConnection();
			} finally {
				get.releaseConnection();
			}
			
			try {
				count = CrawlerStatus.failCount;
				crawlerTime = new Date();
				if (code == 200) { // 下载成功
					return new ProtocolOutput(c,count,"",crawlerTime);
				} else if (code >= 300 && code < 400) { // 相应发生跳转，请求新资源
					String location = c.getHeader("Location");
					if (location == null) {
						location = c.getHeader("location");
					}
					if (location == null) {
						location = "";
					}
					urlObject = new URL(urlObject, location);
					ProtocolStatus protocolStatus = new ProtocolStatus(ProtocolStatus.MOVED, urlObject.toString());
					protocolStatus.setNewUrl(urlObject.toString());
					return new ProtocolOutput(c, protocolStatus,count,"",crawlerTime);
				} else if (code == 401) {
					// 需要身份验证，暂时不做这个处理
					return new ProtocolOutput(c, new ProtocolStatus(ProtocolStatus.ACCESS_DENIED, "Authentication required: " + url),count,"",crawlerTime);
				} else if (code == 404 || code == 410) {
					// 资源没有了
					return new ProtocolOutput(c, new ProtocolStatus(ProtocolStatus.NOTFOUND, url),count,"",crawlerTime);
				} else {
					// 发生异常，异常信息在c中
					return new ProtocolOutput(c, new ProtocolStatus(ProtocolStatus.EXCEPTION, "Http code=" + code + ", url=" + url),count,"",crawlerTime);
				}
			} catch (Throwable e) {
				CrawlerStatus.failCount++;
				System.out.println(url);
				e.printStackTrace();
				LoggerWriter.writeErro(url + ":" + e.getMessage());
				return new ProtocolOutput(null, new ProtocolStatus(0, e.getMessage()),count,"",crawlerTime);
			}
		} catch (MalformedURLException e1) {
			System.out.println(url);
			CrawlerStatus.failCount++;
			e1.printStackTrace();
			LoggerWriter.writeErro(url + ":" + e1.getMessage());
			return new ProtocolOutput(null, new ProtocolStatus(0, e1.getMessage()),count,"",crawlerTime);
		}
	}

	/**
	 * 下载非文本文件
	 * 
	 * @param in
	 * @param url
	 * @param headerMap
	 * @param fle
	 * @return
	 */
	private ProtocolOutput download(InputStream in, String url, Map<String, List<String>> headerMap, FetchEntry fle,Integer count,String title,Date crawlerTime) {
		if (in != null) {
			try {
				FileSystemWriter.write(url, headerMap, null, null, fle, ProtocolStatus.STATUS_SUCCESS, in);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (in != null) {
					try {
						in.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			ProtocolStatus protocolStatus = ProtocolStatus.STATUS_SUCCESS;
			Content c = new Content();
			c.setUrl(url);
			c.setHeaders(headerMap);
			c.setContent(new byte[0]);
			ProtocolOutput out = new ProtocolOutput(c, protocolStatus,count,"",crawlerTime);
			out.setMedia(true);
			return out;
		} else {
			ProtocolOutput out = new ProtocolOutput(null, ProtocolStatus.STATUS_NOTFOUND,count,"",crawlerTime);
			out.setMedia(true);
			return out;
		}
	}

	public static void configureClient() {
		client = new HttpClient(connectionManager);
		configureClient(client);
		System.out.println("*****************************");
		System.out.println("MAX_THREADS_TOTAL:"+MAX_THREADS_TOTAL);
		System.out.println("MAX_THREADS_PER_HOST:"+MAX_THREADS_PER_HOST);
		System.out.println("******************************");
	}

	private static void configureClient(HttpClient client) {
		org.apache.commons.httpclient.protocol.Protocol dummyhttps = new org.apache.commons.httpclient.protocol.Protocol("https", new DummySSLProtocolSocketFactory(), 443);
		org.apache.commons.httpclient.protocol.Protocol.registerProtocol("https", dummyhttps);

		HttpConnectionManagerParams params = connectionManager.getParams();
		params.setConnectionTimeout(TIMEOUT);
		params.setSoTimeout(TIMEOUT);
		params.setSendBufferSize(BUFFER_SIZE);
		params.setReceiveBufferSize(BUFFER_SIZE);
		params.setMaxTotalConnections(MAX_THREADS_TOTAL);
		if (MAX_THREADS_TOTAL > MAX_THREADS_PER_HOST) {
			params.setDefaultMaxConnectionsPerHost(MAX_THREADS_PER_HOST);
		} else {
			params.setDefaultMaxConnectionsPerHost(MAX_THREADS_TOTAL);
		}
		client.getParams().setCookiePolicy(CookiePolicy.BROWSER_COMPATIBILITY);
	}
}
