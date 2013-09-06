package com.xdtech.platform.crawler.parser;

import java.io.UnsupportedEncodingException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.xdtech.platform.crawler.dbcach.TwoLevelUrlManager;
import com.xdtech.platform.crawler.protocol.Content;
import com.xdtech.platform.crawler.protocol.FetchEntry;
import com.xdtech.platform.crawler.ws.webdb.UrlInfo;
import com.xdtech.platform.util.URLType;

public class HtmlParser implements Parser {

	public Parse getParse(String tableName,Content c, FetchEntry fle) {
		Parse parse = null;
		try {
			parse = new Parse();
			String content = new String(c.getContent(), findcharset(c, fle));
			content = content.replace("\u00A0", " ");
			parse.setText(content);
			ParseData data = parse(content, fle, parse);
			parse.setData(data);
			Outlink[] outlinks = data.getOutlinks();
			if (outlinks != null && outlinks.length > 0) {
				List<UrlInfo> list = new ArrayList<UrlInfo>();
				for (Outlink outlink : outlinks) {
					String url = outlink.getUrl();
					UrlInfo info = new UrlInfo();
					info.setUrl(url);
					info.setLevel(outlink.getLevel());
					info.setSiteId(fle.getSiteId());
					info.setUrlType(outlink.getType());
					list.add(info);
				}
				try {
					//如果是测试的话，生成的连接就不提交到地址库
					if(!tableName.equals("test")){
						TwoLevelUrlManager.addWebDb(tableName,list);
					}
				} catch (Exception e) {
				}
			}
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
	private static String findcharset(Content content, FetchEntry fle) {
		String url = fle.getUrl();
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
		if (url != null
				&& (url.contains("http://sjb.zz") || url.contains("http://search.zz") || url.contains("http://mail.zz") || url.contains("http://bookshop.zz") || url.contains("http://tsll.zz") || url.contains("http://bzk.zz") || url.contains("http://jsw.zh"))) {
			return "UTF-8";
		}
		return "gb2312";
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
		List<Outlink> outlinkList = new ArrayList<Outlink>();
		Document doc = Jsoup.parse(content);

		boolean isparser = true;
		Elements totleeles = doc.select("title");
		if (totleeles != null && !totleeles.isEmpty()) {
			Element title = totleeles.get(0);
			if (title != null) {
				String titleVal = title.html();
				titleVal = titleVal == null ? "" : titleVal;
				isparser = !titleVal.contains("{$title}");
			}
		}
		if (!isparser) {
			data.setOutlinks(new Outlink[0]);
			return data;
		}

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
		 * 用于显示的URL
		 */
		List<Outlink> showurl = findUrlSameLevel(doc, fle);
		outlinkList.addAll(showurl);
		Collection<String> allUrls = findAllUrl(doc, fle);
		if (allUrls != null && !allUrls.isEmpty()) {
			Outlink link = null;
			for (String url : allUrls) {
				if (url != null) {
					if (!showurl.contains(url)) {
						link = new Outlink();
						link.setLevel(fle.getLevel() + 1);
						link.setFromUrl(fle.getUrl());
						link.setUrl(url);
						link.setType(-1);
						outlinkList.add(link);
					}
				}
			}
		}
		data.setOutlinks(outlinkList.toArray(new Outlink[outlinkList.size()]));
		return data;
	}

	/**
	 * 特殊处理的页面
	 * 
	 * @param outlinkList
	 * @param doc
	 * @param fle
	 */
	private static void gdnews(List<Outlink> outlinkList, Document doc, FetchEntry fle) {
		String surl = fle.getUrl();
		if (surl.contains("http://news.zz/news/gd.asp?endid=") || surl.equals("http://news.zz/html/news/gd.html") || surl.equals("http://www.zz/news/gd.html")) {
			Elements links = doc.select("a[href]");
			for (int i = 0; i < links.size(); i++) {
				Element aElement = links.get(i);
				String href = aElement.attr("href");
				String url = null;
				if (href.startsWith("javascript:opengd(")) {
					String[] terms = href.split("',");
					url = terms[0].replace("javascript:opengd('", "");
					String u = "http://www.zz";
					if (url.startsWith("/")) {
						u = u + url;
					} else {
						u = u + "/" + url;
					}
					Outlink link = new Outlink();
					link.setLevel(fle.getLevel() + 1);
					link.setFromUrl(fle.getOldUrl());
					link.setType(URLType.HTML);
					link.setUrl(u);
					outlinkList.add(link);
				}
			}
		}
	}

	/**
	 * 找到相同等级的URL，主要包括css、js、img、flash,frameset,iframe
	 * 
	 * @return
	 */
	private static List<Outlink> findUrlSameLevel(Document doc, FetchEntry fle) {
		Set<String> links = ParserUtil.findUrlFromLink(doc, fle);
		Set<String> scripts = ParserUtil.findUrlFromScript(doc, fle);
		Set<String> embed = ParserUtil.findUrlFromEmbed(doc, fle);
		Set<String> styles = ParserUtil.findUrlFromStyle(doc, fle);//
		Set<String> images = ParserUtil.findUrlFromImg(doc, fle);//
		Set<String> bgImages = ParserUtil.findUrlFromBackGround(doc, fle);// 查找background中的标签
		Set<String> eleStyleUrls = ParserUtil.findUrlFromElementStyle(doc, fle);// 查找标签属性中的图片路径
		Set<String> frameUrls = ParserUtil.findUrlFromElementFrame(doc, fle);// 从frame中还有iframe中招链接
		Set<String> rep = new HashSet<String>();
		List<Outlink> result = new ArrayList<Outlink>();
		fix(links, rep, fle, result, URLType.LINK);
		fix(scripts, rep, fle, result, URLType.SCRIPT);
		fix(embed, rep, fle, result, URLType.EMBED);
		fix(styles, rep, fle, result, URLType.IMAGE);
		fix(images, rep, fle, result, URLType.IMAGE);
		fix(bgImages, rep, fle, result, URLType.IMAGE);
		fix(eleStyleUrls, rep, fle, result, URLType.IMAGE);
		fix(frameUrls, rep, fle, result, URLType.LINK);
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

	/**
	 * 获取所有URL
	 * 
	 * @param doc
	 * @param fle
	 * @return
	 */
	private static Collection<String> findAllUrl(Document doc, FetchEntry fle) {
		Collection<String> allUrls = ParserUtil.findAllUrl(doc, fle);
		return allUrls;
	}
}
