package com.xdtech.platform.util;

import java.io.File;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;

import com.xdtech.platform.crawler.process.ContentTypeInfo;

public class CrawlerUtil {
	/***/
	private static Map<String, String> map = new HashMap<String, String>();
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
				} else if (c == '!' || c == '"' || c == '#' || c == '$' || c == '%' || c == '&' || c == '\'' || c == '(' || c == ')' || c == '+' || c == ',' || c == ';' || c == '<' || c == '=' || c == '>' || c == '?' || c == '@' || c == '[' || c == ']' || c == '^' || c == '`' || c == '{' || c == '|' || c == '}' || c == '~') {
//					try {
//						sbUrl.append(URLEncoder.encode(String.valueOf(c), "GB2312"));
//					} catch (UnsupportedEncodingException e) {
//						sbUrl.append(c);
//					}
					sbUrl.append(c);
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
	 * 根据头信息猜测文件类型
	 * 
	 * @param contentType
	 * @param url
	 * @return
	 */
	public static ContentTypeInfo findType(String contentType, String url, int urlType) {
		boolean text = false;
		String result = "";
		if (urlType == URLType.SCRIPT) {
			ContentTypeInfo info = new ContentTypeInfo();
			info.setText(false);
			info.setFileType("js");
			return info;
		}

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
			if (contentType.startsWith("text") && !contentType.contains("script") && urlType != URLType.IMAGE) {
				text = true;
				if (contentType.contains("htm")) {
					result = "html";
				} else if (contentType.contains("plain")) {
					result = "txt";
				} else if (contentType.contains("css")) {
					result = "css";
				}
			} else if (contentType.startsWith("text") && contentType.contains("script")) {
				text = false;
				result = "js";
			} else if (contentType.startsWith("application")) {
				if (contentType.contains("dsssl")) {
					text = true;
					result = "xsl";
				} else if (contentType.contains("script") || contentType.contains("json")) {
					text = false;
					result = "js";
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
			} else if (contentType.startsWith("image") || urlType == URLType.IMAGE) {
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

	public static void main(String[] args) throws HttpException, IOException {
		HttpClient client = new HttpClient();
		String url = "http://dv.zz/getsmallpic.aspx?path=flv\\junying\\wywh图片\\五族之家.jpg";
		url = changeUrl(url);
		System.out.println(url);
		HttpMethod method = new GetMethod(url);
		int code = client.executeMethod(method);
		System.out.println(code);
		Header header = method.getResponseHeader("content-type");
		byte[] content = method.getResponseBody();
		FileOutputStream fos = new FileOutputStream(new File("c:\\AAA.JPG"));
		fos.write(content);
		method.releaseConnection();
		ContentTypeInfo info = findType(header.getValue(), "http://img.zz/ZZMAIN/NAV_small_950.JS", URLType.IMAGE);
		System.out.println(info.getFileType());
		System.out.println(info.isText());
	}
}
