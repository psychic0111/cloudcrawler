package com.xdtech.platform.crawler.parser;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.xdtech.platform.crawler.protocol.FetchEntry;

import cz.vutbr.web.css.CSSException;
import cz.vutbr.web.css.CSSFactory;
import cz.vutbr.web.css.Declaration;
import cz.vutbr.web.css.RuleSet;
import cz.vutbr.web.css.StyleSheet;
import cz.vutbr.web.css.Term;
import cz.vutbr.web.css.TermURI;

/**
 * 解析帮助类
 * 
 * @author zhangjianbing@msn.com
 */
public class ParserUtil {
	/**
	 * 从指定的快中发现连接
	 * 
	 * @param doc
	 * @param fle
	 * @param rules
	 * @return
	 */
	public static Set<String> findUrlFromA(Document doc, FetchEntry fle, String[] rules) {
		Elements aTags = doc.select("a[href]");
		Set<String> result = new HashSet<String>();
		if (aTags != null && aTags.size() > 0) {
			String baseUrl = fle.getBaseUrl() == null || fle.getBaseUrl().trim().length() == 0 ? fle.getUrl() : fle.getBaseUrl();
			for (Element link : aTags) {
				String href = link.attr("href");
				if (href != null && href.trim().length() > 0 && !"#".equals(href)) {
					try {
						URL url = new URL(new URL(baseUrl), href);
						if (!baseUrl.equals(url.toString())) {
							result.add(url.toString());
						}
					} catch (MalformedURLException e) {
					}
				}
			}
		}
		return result;
	}
	/**
	 * 从指定的快中发现连接
	 * 
	 * @param doc
	 * @param fle
	 * @param rules
	 * @return
	 */
	public static Set<String> findUrlFromAFirst(Document doc, FetchEntry fle, String[] rules) {
		Elements aTags = doc.select("a[href]");
		Set<String> result = new HashSet<String>();
		if (aTags != null && aTags.size() > 0) {
			String baseUrl = fle.getBaseUrl() == null || fle.getBaseUrl().trim().length() == 0 ? fle.getUrl() : fle.getBaseUrl();
			Element link = aTags.get(0);
			String href = link.attr("href");
			if (href != null && href.trim().length() > 0 && !"#".equals(href)) {
				try {
					URL url = new URL(new URL(baseUrl), href);
					if (!baseUrl.equals(url.toString())) {
						result.add(url.toString());
					}
				} catch (MalformedURLException e) {
				}
			}
		}
		return result;
	}
	/**
	 * 获取link标签的URL
	 * 
	 * @param doc
	 * @param fle
	 * @param el
	 * @return
	 */
	public static Set<String> findUrlFromLink(Document doc, FetchEntry fle) {
		Set<String> result = new HashSet<String>();
		Elements css = doc.select("link[href]");// css
		Set<String> cssUrls = findUrlsFromElements(css, "href", fle);
		/*
		 * 将结果汇总去重
		 */
		if (cssUrls != null && !cssUrls.isEmpty()) {
			result.addAll(cssUrls);
		}
		return result;
	}

	/**
	 * 获取script标签的URL
	 * 
	 * @param doc
	 * @param fle
	 * @param el
	 * @return
	 */
	public static Set<String> findUrlFromScript(Document doc, FetchEntry fle) {
		Set<String> result = new HashSet<String>();
		Elements scripts = doc.select("script[src]");// script
		Set<String> scriptsUrls = findUrlsFromElements(scripts, "src", fle);
		if (scriptsUrls != null && !scriptsUrls.isEmpty()) {
			result.addAll(scriptsUrls);
		}
		return result;
	}

	/**
	 * 获取嵌入式插件的URL
	 * 
	 * @param doc
	 * @param fle
	 * @param el
	 * @return
	 */
	public static Set<String> findUrlFromEmbed(Document doc, FetchEntry fle) {
		Set<String> result = new HashSet<String>();
		Elements embeds = doc.select("embed[src]");// flash
		Set<String> embedsUrls = findUrlsFromElements(embeds, "src", fle);
		if (embedsUrls != null && !embedsUrls.isEmpty()) {
			result.addAll(embedsUrls);
		}
		return result;
	}

	/**
	 * 从页面的style块中获取URL
	 * 
	 * @param doc
	 * @param fle
	 * @param el
	 * @return
	 */
	public static Set<String> findUrlFromStyle(Document doc, FetchEntry fle) {
		Set<String> result = new HashSet<String>();
		Elements styles = doc.select("style");// 嵌入在页面的CSS
		Set<String> embedcssUrl = findEmbedStyle(styles, fle);
		if (embedcssUrl != null && !embedcssUrl.isEmpty()) {
			result.addAll(embedcssUrl);
		}
		return result;
	}

	/**
	 * 从页面的img标签中获取URL
	 * 
	 * @param doc
	 * @param fle
	 * @param el
	 * @return
	 */
	public static Set<String> findUrlFromImg(Document doc, FetchEntry fle) {
		Set<String> result = new HashSet<String>();
		Elements imgs = doc.select("img[src]");// 图片
		Elements inputImgs = doc.select("input[type=image]");// input
		Set<String> imgsUrl = findUrlsFromElements(imgs, "src", fle);
		Set<String> inputImgsUrl = findUrlsFromElements(inputImgs, "src", fle);
		if (imgsUrl != null && !imgsUrl.isEmpty()) {
			result.addAll(imgsUrl);
		}
		if (inputImgsUrl != null && !inputImgsUrl.isEmpty()) {
			result.addAll(inputImgsUrl);
		}
		return result;
	}

	/**
	 * 从元素的background中获取图片
	 * 
	 * @param doc
	 * @param fle
	 * @return
	 */
	public static Set<String> findUrlFromBackGround(Document doc, FetchEntry fle) {
		Set<String> result = new HashSet<String>();
		Elements imgs = doc.select("[background]");// 图片
		Set<String> imgsUrl = findUrlsFromElements(imgs, "background", fle);
		if (imgsUrl != null && !imgsUrl.isEmpty()) {
			result.addAll(imgsUrl);
		}
		return result;
	}

	/**
	 * 从内嵌style中获取图片
	 * 
	 * @param doc
	 * @param fle
	 * @return
	 */
	public static Set<String> findUrlFromElementStyle(Document doc, FetchEntry fle) {
		Set<String> result = new HashSet<String>();
		Elements styles = doc.select("[style]");// 图片
		if (styles != null && styles.size() > 0) {
			String baseUrl = (fle.getBaseUrl() != null && fle.getBaseUrl().trim().length() > 0) ? fle.getBaseUrl() : fle.getUrl();
			for (int i = 0; i < styles.size(); i++) {
				Element ele = styles.get(i);
				String style = ele.attr("style");
				if (style != null && style.toLowerCase().contains("url")) {
					style = ".xdtech{" + style + "}";
					Set<String> urls = findUrlFromStyle(style, baseUrl);
					if (urls != null && !urls.isEmpty()) {
						result.addAll(urls);
					}
				}
			}
		}
		return result;
	}

	/**
	 * 从内嵌style中获取图片
	 * 
	 * @param doc
	 * @param fle
	 * @return
	 */
	public static Set<String> findUrlFromElementFrame(Document doc, FetchEntry fle) {
		String baseUrl = (fle.getBaseUrl() != null && fle.getBaseUrl().trim().length() > 0) ? fle.getBaseUrl() : fle.getUrl();
		Set<String> result = new HashSet<String>();
		Elements iframe = doc.select("iframe");// iframe
		if (iframe != null && iframe.size() > 0) {
			for (int i = 0; i < iframe.size(); i++) {
				Element ele = iframe.get(i);
				String src = ele.attr("src");
				if (src != null && src.trim().length() > 0) {
					try {
						URL url = new URL(new URL(baseUrl), src);
						result.add(url.toString());
					} catch (MalformedURLException e) {
					}
				}
			}
		}

		Elements frame = doc.select("frame");// iframe
		if (frame != null && frame.size() > 0) {
			for (int i = 0; i < frame.size(); i++) {
				Element ele = frame.get(i);
				String src = ele.attr("src");
				if (src != null && src.trim().length() > 0) {
					try {
						URL url = new URL(new URL(baseUrl), src);
						result.add(url.toString());
					} catch (MalformedURLException e) {
					}
				}
			}
		}
		return result;
	}

	/**
	 * 找到与页面显示相关的URL，主要包括css、js、img、flash
	 * 
	 * @return
	 */
	public static Set<String> findUrlFromShow(Document doc, FetchEntry fle) {
		Set<String> result = new HashSet<String>();
		result.addAll(findUrlFromLink(doc, fle));
		result.addAll(findUrlFromScript(doc, fle));
		result.addAll(findUrlFromEmbed(doc, fle));
		result.addAll(findUrlFromStyle(doc, fle));
		result.addAll(findUrlFromImg(doc, fle));
		return result;
	}

	/**
	 * 从网页中的css中找到所有连接
	 * 
	 * @param styles
	 * @param fle
	 * @return
	 */
	private static Set<String> findEmbedStyle(Elements styles, FetchEntry fle) {
		Set<String> result = new HashSet<String>();
		if (styles != null) {
			String baseUrl = fle.getBaseUrl() == null || fle.getBaseUrl().trim().length() == 0 ? fle.getUrl() : fle.getBaseUrl();
			for (int i = 0; i < styles.size(); i++) {
				Element ele = styles.get(i);
				if (ele != null) {
					Set<String> styleurls = findUrlFromStyle(ele.html(), baseUrl);
					if (styleurls != null && !styleurls.isEmpty()) {
						for (String url : styleurls) {
							if (url != null && url.trim().length() > 0) {
								result.add(url);
							}
						}
					}
				}
			}
		}
		return result;
	}

	/**
	 * 从css代码中找到外部链接
	 * 
	 * @param style
	 *            CSS代码
	 * @return
	 */
	public static Set<String> findUrlFromStyle(String style, String baseUrl) {
		Set<String> urls = new HashSet<String>();
		try {
			StyleSheet ss = CSSFactory.parse(style);
			int count = ss.size();
			for (int i = 0; i < count; i++) {
				Object obj1 = ss.get(i);
				if (obj1 instanceof RuleSet) {
					RuleSet rs = (RuleSet) ss.get(i);
					List<Declaration> list = rs.asList();
					for (Declaration d : list) {
						if ("background".equalsIgnoreCase(d.getProperty()) || "background-image".equalsIgnoreCase(d.getProperty())) {
							List<Term<?>> terms = d.asList();
							for (Term<?> t : terms) {
								if (t != null && t instanceof TermURI) {
									Object obj = t.getValue();
									if (obj != null && obj instanceof String) {
										String imgurl = t.getValue().toString();
										try {
											URL url = new URL(new URL(baseUrl), imgurl);
											urls.add(url.toString());
										} catch (Exception e) {
											e.printStackTrace();
										}
									}
								}
							}
						}
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (CSSException e) {
			e.printStackTrace();
		}
		return urls;
	}

	/**
	 * 找到元素结合中的所有URL,如果没有发现URL则返回空
	 * 
	 * @param links
	 * @param attr
	 * @param baseUrl
	 * @return
	 */
	private static Set<String> findUrlsFromElements(Elements links, String attr, FetchEntry fle) {
		Set<String> result = null;
		if (links != null && links.size() > 0) {
			result = new HashSet<String>();
			String baseUrl = fle.getBaseUrl() == null || fle.getBaseUrl().trim().length() == 0 ? fle.getUrl() : fle.getBaseUrl();
			for (Element link : links) {
				String href = link.attr(attr);
				if (href != null && href.trim().length() > 0) {
					try {
						URL url = new URL(new URL(baseUrl), href);
						if (!baseUrl.equals(url.toString())) {
							result.add(url.toString());
						}
					} catch (MalformedURLException e) {
					}
				}
			}
		}
		return result;
	}

	/**
	 * 获取页面中的所有连接
	 * 
	 * @param doc
	 * @param fle
	 * @return
	 */
	public static Collection<String> findAllUrl(Document doc, FetchEntry fle) {
		Set<String> result = new HashSet<String>();
		String base = fle.getBaseUrl() == null || fle.getBaseUrl().trim().length() == 0 ? fle.getUrl() : fle.getBaseUrl();
		Set<String> pageUrls = findUrlFromDocument(doc, base);// 获取所有非css代码中的URL
		if (pageUrls != null && !pageUrls.isEmpty()) {
			result.addAll(pageUrls);
		}
		Elements styles = doc.select("style");
		if (styles != null && styles.size() > 0) {
			Set<String> styleUrls = findEmbedStyle(styles, fle);
			if (styleUrls != null && !styleUrls.isEmpty()) {
				result.addAll(styleUrls);
			}
		}
		List<String> finalResult = new ArrayList<String>();
		if (!result.isEmpty()) {
			for (String term : result) {
				if (isOk(term, fle)) {
					finalResult.add(term);
				}
			}
		}
		return finalResult;
	}

	/**
	 * 判断URL是否过滤,该方法只判断了包含的规则.产品化的时候需要判断禁止的规则以及正则规则
	 * 
	 * @param url
	 * @return
	 */
	private static boolean isOk(String url, FetchEntry fle) {
		return true;
	}

	public static Set<String> findUrlFromDocument(Document doc, String baseUrl) {
		Elements links = doc.select("a[href]");
		Elements media = doc.select("[src]");
		Elements imports = doc.select("link[href]");
		Set<String> urls = new HashSet<String>();
		;
		if (links != null) {
			for (Element link : links) {
				String href = link.attr("href");
				if (href != null && href.trim().length() > 0) {
					try {
						URL url = new URL(new URL(baseUrl), href);
						if (!baseUrl.equals(url.toString())) {
							urls.add(url.toString());
						}
					} catch (MalformedURLException e) {
					}
				}
			}
		}
		if (media != null) {
			for (Element link : media) {
				String src = link.attr("src");
				if (src != null && src.trim().length() > 0) {
					try {
						URL url = new URL(new URL(baseUrl), src);
						if (!baseUrl.equals(url.toString())) {
							urls.add(url.toString());
						}
					} catch (MalformedURLException e) {
					}
				}
			}
		}
		if (imports != null) {
			for (Element link : imports) {
				String href = link.attr("href");
				if (href != null && href.trim().length() > 0) {
					try {
						URL url = new URL(new URL(baseUrl), href);
						if (!baseUrl.equals(url.toString())) {
							urls.add(url.toString());
						}
					} catch (MalformedURLException e) {
					}
				}
			}
		}
		return urls;
	}

	public static void main(String[] args) {
		String url = "http://news.baidu.com/";
		FetchEntry fle = new FetchEntry();
		fle.setUrl(url);
		HttpClient client = new HttpClient();
		HttpMethod method = new GetMethod(url);
		try {
			int code = client.executeMethod(method);
			byte[] content = method.getResponseBody();
			String html = new String(content, "GB2312");
			Document doc = Jsoup.parse(html);
			Set<String> urls = findUrlFromElementFrame(doc, fle);
			System.out.println(urls);
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}