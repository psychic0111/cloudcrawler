package com.xdtech.platform.crawler.parser;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PushbackInputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.mozilla.universalchardet.UniversalDetector;

import com.xdtech.platform.crawler.protocol.Content;
import com.xdtech.platform.crawler.protocol.FetchEntry;
import com.xdtech.platform.util.URLType;

public class PageParser implements Parser {
	public Parse getParse(String tableName,Content c, FetchEntry fle) {
		Parse parse = null;
		try {
			parse = new Parse();
			String charSet = findcharset(c, fle);
			String content = new String(c.getContent(), charSet);
			content = content.replace("\u00A0", " ");
			parse.setText(content);
			ParseData data = parse(content, fle, parse);
			parse.setData(data);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return parse;
	}

	/**
	 * 获取编码
	 * 
	 * @param content
	 * @return
	 */
	public static String findcharset(Content content, FetchEntry fle) {
		String encode = findCharsetFromDoc(content, fle);// 在html中找编码
		if (encode != null && encode.trim().length() > 0) {
			return encode;
		}
		encode = findCharsetFromHeader(content, fle);// 从响应头信息中找编码
		if (encode != null && encode.trim().length() > 0) {
			return encode;
		}
		if (encode == null) {
			encode = findCharsetFromContent(content, fle);// 从字节流中判断编码
			if (encode != null && encode.trim().length() > 0) {
				return encode;
			}
		}
		encode = encode == null ? "gb2312" : encode;
		return encode;
	}

	/**
	 * 从响应头信息中找编码
	 * 
	 * @param content
	 * @param fle
	 * @return
	 */
	private static String findCharsetFromHeader(Content content, FetchEntry fle) {
		String encode = null;
		Map<String, List<String>> headers = null;
		try{
			headers = content.getHeaders();
		}catch(Exception e){
		}
		if (headers != null) {
			List<String> vals = headers.get("Content-Type".toLowerCase());
			if (vals != null && !vals.isEmpty()) {
				for (String val : vals) {
					if (val != null) {
						val = val.toLowerCase();
						if (val.indexOf("charset") > -1) {
							val = val.replace("[ ]{1,}", "");
							val = val.replace("\"", "");
							String[] terms = val.split(";");
							for (String term : terms) {
								int index = term.indexOf("charset");
								if (index > -1 && term.length() > 8) {
									encode = term.substring(index + 8);
									break;
								}
							}
							break;
						}
					}
				}
			}
		}
		return encode;
	}

	/**
	 * 从字节码中招编码
	 * 
	 * @param content
	 * @param fle
	 * @return
	 */
	private static String findCharsetFromContent(Content content, FetchEntry fle) {
		byte[] contentByte = null;
		String encode = null;
		try{
			contentByte = content.getContent();
			PushbackInputStream bis = new PushbackInputStream(new ByteArrayInputStream(contentByte));
			encode = get_charset(bis);
		}catch(Exception e){
		}
		return encode;
	}

	/**
	 * 根据字节流判断编码
	 * 
	 * @param bis
	 * @return
	 */
	private static String get_charset(PushbackInputStream bis) {
		String encoding = null;
		java.io.ByteArrayOutputStream bso = new java.io.ByteArrayOutputStream();
		try {
			byte[] buff = new byte[4096];
			int len = 0;
			UniversalDetector det = new UniversalDetector(null);
			int total = 0;
			while ((len = bis.read(buff)) > 0 && total < 20480) {
				bso.write(buff, 0, len);
				det.handleData(buff, 0, len);
				total += len;
			}
			det.dataEnd();
			encoding = det.getDetectedCharset();
			bis.unread(bso.toByteArray());
			bso.close();
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
		}
		return encoding;
	}

	/**
	 * 从html文档中找编码
	 * 
	 * @param content
	 * @param fle
	 * @return
	 */
	private static String findCharsetFromDoc(Content content, FetchEntry fle) {
		String encode = null;
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
							encode = "gb2312";
							break;
						} else if (cl.contains("gbk")) {
							encode = "gbk";
							break;
						} else if (cl.contains("utf-8")) {
							encode = "utf-8";
							break;
						} else if (cl.contains("gb18030")) {
							encode = "gb18030";
							break;
						} else if (cl.contains("iso-8859-1")) {
							encode = "iso-8859-1";
							break;
						} else if (cl.contains("big5")) {
							encode = "big5";
							break;
						} else {
							contentVal = contentVal.replace("[ ]{1,}", "");
							contentVal = contentVal.replace("\"", "");
							String[] terms = contentVal.split(";");
							boolean finded = false;
							for (String term : terms) {
								int index = term.indexOf("charset");
								if (index > -1 && term.length() > 8) {
									encode = term.substring(index + 8);
									finded = true;
									break;
								}
							}
							if (finded) {
								break;
							}
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
						encode = "gb2312";
						break;
					} else if (cl.contains("gbk")) {
						encode = "gbk";
						break;
					} else if (cl.contains("utf-8")) {
						encode = "utf-8";
						break;
					} else if (cl.contains("gb18030")) {
						encode = "gb18030";
						break;
					} else if (cl.contains("iso-8859-1")) {
						encode = "iso-8859-1";
						break;
					} else if (cl.contains("big5")) {
						encode = "big5";
						break;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return encode;
	}

	/**
	 * 获取解析后的对象
	 * 
	 * @param content
	 * @param fle
	 * @param parse
	 * @return
	 */
	private static ParseData parse(String content, FetchEntry fle, Parse parse) {
		ParseData data = new ParseData();
		data.setMetadata(new Properties());
		Document doc = Jsoup.parse(content);
		Properties properties = PropertiesParser.parse(content, fle, doc);
		data.setMetadata(properties);
		if (fle.getLevel() != 0) {
			//获取分页连接
			Outlink[] pageLinks = PropertiesParser.findPageUrl(content, fle, doc,1);
			data.setInternallinks(pageLinks);
			
			//获取关联连
			Outlink[] nativeLinks = PropertiesParser.findPageUrl(content, fle, doc,2);
			data.setOutlinks(nativeLinks);
		}
		List<Properties> blocks = PropertiesParser.parseRelationRule(content, fle, doc);
		data.setBlocks(blocks);
		/*
		 * 获取页面的base属性
		 */
		Elements baseEles = doc.select("base");
		if (baseEles != null && baseEles.size() > 0) {
			String baseUrl = null;
			for (Element ele : baseEles) {
				String href = ele.attr("href");
				if (href != null && href.trim().length() > 0) {
					baseUrl = href;
					break;
				}
			}
			fle.setBaseUrl(baseUrl);
		}

		/*
		 * 获取外向连接
		 */
		Map<String, Outlink> levelAddMap = new HashMap<String, Outlink>();
		List<Outlink> outlinks = findUrl(doc, fle);
		if (outlinks != null && !outlinks.isEmpty()) {
			for (Outlink outlink : outlinks) {
				outlink.setLevel(outlink.getLevel() < 0 ? 0 : outlink.getLevel() + 1);// 深度加1
				levelAddMap.put(outlink.getUrl(), outlink);
			}
		}

		/*
		 * 获取frame连接
		 */
		List<Outlink> frameUrls = findUrlFram(doc, fle);
		if (frameUrls != null) {
			for (Outlink outlink : frameUrls) {
				outlink.setLevel(outlink.getLevel() < 0 ? 0 : outlink.getLevel());
				Outlink has = levelAddMap.get(outlink.getUrl());
				if (has != null) {
					outlinks.remove(has);
				}
			}
		}
		List<Outlink> outlinkList = new ArrayList<Outlink>();
		outlinkList.addAll(frameUrls);
		outlinkList.addAll(outlinks);
		data.setOutlinks(outlinkList.toArray(new Outlink[outlinkList.size()]));
		
		return data;
	}

	/**
	 * 找到外部连接
	 * 
	 * @return
	 */
	private static List<Outlink> findUrl(Document doc, FetchEntry fle) {
		Set<String> links = ParserUtil.findUrlFromA(doc, fle, null);
		Set<String> rep = new HashSet<String>();
		List<Outlink> result = new ArrayList<Outlink>();
		fix(links, rep, fle, result, URLType.LINK);
		return result;
	}

	/**
	 * 发现frame中的URL
	 * 
	 * @param doc
	 * @param fle
	 * @return
	 */
	private static List<Outlink> findUrlFram(Document doc, FetchEntry fle) {
		Set<String> links = ParserUtil.findUrlFromElementFrame(doc, fle);// 从frame中还有iframe中招链接
		Set<String> rep = new HashSet<String>();
		List<Outlink> result = new ArrayList<Outlink>();
		fix(links, rep, fle, result, URLType.LINK);
		return result;
	}

	/**
	 * 组装outlink
	 * 
	 * @param urls
	 * @param rep
	 * @param fle
	 * @param result
	 * @param urlType
	 */
	private static void fix(Set<String> urls, Set<String> rep, FetchEntry fle, List<Outlink> result, int urlType) {
		if (urls != null && !urls.isEmpty()) {
			Outlink outlink = null;
			for (String url : urls) {
				if (url != null && !rep.contains(url)) {
					outlink = new Outlink();
					outlink.setFromUrl(fle.getUrl());
					outlink.setLevel(fle.getLevel());
					outlink.setType(urlType);
					outlink.setUrl(url);
					result.add(outlink);
					rep.add(url);
				}
			}
		}
	}

}
