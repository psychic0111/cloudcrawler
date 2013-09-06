import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
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
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HttpFetcher {
	private static MultiThreadedHttpConnectionManager connectionManager = new MultiThreadedHttpConnectionManager();
	private static HttpClient client;
	/** 下载超时时间跟连接超时时间 */
	private static int TIMEOUT = 20000;
	/** 发送数据缓存以及接收数据缓存 */
	private static final int BUFFER_SIZE = 8 * 1024;
	/** 总的下载线程数 */
	private static int MAX_THREADS_TOTAL = 10;
	/** 单站点最大下载线程数 */
	private static int MAX_THREADS_PER_HOST = 5;
	/** User-Agent */
	private static String userAgent = "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:15.0) Gecko/20100101 Firefox/15.0.1";
	private static long FILE_MAX_SIZE = 1024 * 1024 * 1;
	private int count = 0;
	
	/**
	 * 请求一个URL
	 * @param url
	 * @return
	 */
	public String fetcher(String url) {
		Content contentObject = response(url);
		String content = "";
		try {
			content = new String(contentObject.getContent(), findcharset(contentObject, url));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		content = content.replace("\u00A0", " ");
		return content;
	}
	
	/**
	 * 获取客户端
	 * @param url
	 * @return
	 */
	public synchronized HttpClient getClient(String url) {
		if (client == null) {
			client = new HttpClient();
			configureClient(client);
		}
		return client;
	}

	/**
	 * 替换斜线
	 * 
	 * @param surl
	 * @return
	 */
	private static String changeSlash(String surl) {
		String[] terms = surl.split("\\?");
		String newUrl = null;
		if (terms.length > 1) {
			newUrl = terms[0].replace("\\", "/") + "?" + terms[1];
		} else {
			newUrl = surl.replace("\\", "/");
		}
		return newUrl;
	}

	/**
	 * 对URL进行格式化
	 * 
	 * @param surl
	 * @return
	 */
	public static String changeUrl(String surl) {
		surl = changeSlash(surl);
		URL url;
		try {
			surl = surl.replace("/../", "/");
			url = new URL(surl);
			String newUrl = null;
			StringBuilder sbInfo = new StringBuilder();
			String query = url.getQuery();
			if (query != null && query.trim().length() > 0) {
				String[] parameters = query.split("&");
				if (parameters != null && parameters.length > 0) {
					for (String param : parameters) {
						if (param != null && param.trim().length() > 0) {
							if (sbInfo.length() > 0) {
								sbInfo.append("&");
							}
							String[] terms = param.split("=");
							if (terms.length == 2) {
								sbInfo.append(terms[0]).append("=");
								if (terms[1].contains("%")) {
									sbInfo.append(terms[1]);
								} else {
									try {
										sbInfo.append(URLEncoder.encode(terms[1], "GB2312"));
									} catch (UnsupportedEncodingException e) {
										sbInfo.append(terms[1]);
									}
								}
							} else {
								sbInfo.append(param);
							}
						}
					}
				}
			}
			String[] urlTerms = surl.split("\\?");
			StringBuilder sbUrl = new StringBuilder();
			for (int i = 0; i < urlTerms[0].length(); i++) {
				char c = urlTerms[0].charAt(i);
				if (!(c > 0 && c < 255) || c == ' ') {
					try {
						sbUrl.append(URLEncoder.encode(String.valueOf(c), "GB2312"));
					} catch (UnsupportedEncodingException e) {
						sbUrl.append(c);
					}
				} else if (c == '!' || c == '"' || c == '#' || c == '$' || c == '%' || c == '&' || c == '\'' || c == '(' || c == ')' || c == '+' || c == ',' || c == ';' || c == '<' || c == '=' || c == '>' || c == '?' || c == '@' || c == '[' || c == ']'
						|| c == '^' || c == '`' || c == '{' || c == '|' || c == '}' || c == '~') {
					try {
						sbUrl.append(URLEncoder.encode(String.valueOf(c), "GB2312"));
					} catch (UnsupportedEncodingException e) {
						sbUrl.append(c);
					}
				} else {
					sbUrl.append(c);
				}
			}
			newUrl = sbUrl.toString();
			if (sbInfo.length() > 0) {
				newUrl = newUrl + "?" + sbInfo.toString();
			}
			return newUrl;
		} catch (MalformedURLException e1) {
			return surl;
		}
	}
	
	/**
	 * 找到编码
	 * @param content
	 * @param url
	 * @return
	 */
	private String findcharset(Content content, String url) {
		try {
			String scontent = new String(content.getContent(), "ISO-8859-1");
			Document doc = Jsoup.parse(scontent);
			Elements metas = doc.select("meta");
			for (int i = 0; i < metas.size(); i++) {
				Element ele = metas.get(i);
				String contentVal = ele.attr("content");
				if (contentVal == null || contentVal.trim().length() == 0) {
					continue;
				} else {
					String cl = contentVal.toLowerCase();
					if (cl.contains("charset")) {
						if (cl.contains("gb2312")) {
							return "gb2312";
						} else if (cl.contains("gbk")) {
							return "gbk";
						} else if (cl.contains("utf-8")) {
							return "utf-8";
						} else if (cl.contains("gb18030")) {
							return "gb18030";
						} else if (cl.contains("iso-8859-1")) {
							return "iso-8859-1";
						} else if (cl.contains("big5")) {
							return "big5";
						} else {
							return "gb2312";
						}
					}
				}
			}

			for (int i = 0; i < metas.size(); i++) {
				Element ele = metas.get(i);
				String contentVal = ele.attr("charset");
				if (contentVal == null || contentVal.trim().length() == 0) {
					continue;
				} else {
					String cl = contentVal.toLowerCase();
					if (cl.contains("gb2312")) {
						return "gb2312";
					} else if (cl.contains("gbk")) {
						return "gbk";
					} else if (cl.contains("utf-8")) {
						return "utf-8";
					} else if (cl.contains("gb18030")) {
						return "gb18030";
					} else if (cl.contains("iso-8859-1")) {
						return "iso-8859-1";
					} else if (cl.contains("big5")) {
						return "big5";
					} else {
						return "gb2312";
					}
				}
			}
		} catch (UnsupportedEncodingException e) {
		}
		return "gb2312";
	}

	/**
	 * 请求，获取数据
	 * 
	 * @param url
	 * @return
	 */
	private Content response(String url) {
		count++;
		URL urlObject;
		try {
			urlObject = new URL(url);
			GetMethod get = new GetMethod(url);
			get.setFollowRedirects(false);
			get.setRequestHeader("User-Agent", userAgent);
			get.setRequestHeader("Referer", url);
			HttpClient client = getClient(url);
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
//				List<String> contentTypes = headerMap.get("content-type");
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
					e.printStackTrace();
					if (code == 200) {
						throw new IOException(e.toString());
					}
				}
				get.releaseConnection();
			} catch (HttpException e) {
				e.printStackTrace();
				get.releaseConnection();
			} catch (IOException e) {
				e.printStackTrace();
				get.releaseConnection();
			} finally {
				get.releaseConnection();
			}

			try {
				if (code == 200) { // 下载成功
					return c;
				} else if (code >= 300 && code < 400) { // 相应发生跳转，请求新资源
					String location = c.getHeader("Location");
					if (location == null) {
						location = c.getHeader("location");
					}
					if (location == null) {
						location = "";
					}
					urlObject = new URL(urlObject, location);
					if (count > 3) {
						return null;
					} else {
						return response(urlObject.toString());
					}
				} else if (code == 401) {
					return null;
				} else if (code == 404 || code == 410) {
					return null;
				} else {
					return null;
				}
			} catch (Throwable e) {
				e.printStackTrace();
				return null;
			}
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
			return null;
		}
	}

	/**
	 * 参数配置
	 * 
	 * @param client
	 */
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
