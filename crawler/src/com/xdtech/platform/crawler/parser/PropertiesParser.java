package com.xdtech.platform.crawler.parser;

import java.io.IOException;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.util.CycleDetectionStrategy;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.basistech.readability.AutoContentHelp;
import com.xdtech.platform.crawler.control.NConfigManager;
import com.xdtech.platform.crawler.control.bean.FetchTemplate;
import com.xdtech.platform.crawler.control.bean.Site;
import com.xdtech.platform.crawler.control.nbean.NSite;
import com.xdtech.platform.crawler.logger.XDLogger;
import com.xdtech.platform.crawler.protocol.FetchEntry;
import com.xdtech.platform.util.AppConf;

/**
 * 将网页内容进行结构化解析
 * 
 * @author zhangjianbing@msn.com
 */
public class PropertiesParser {
	private static Map<String, Pattern> patternMap = new HashMap<String, Pattern>();
	/** 用于发布时间抽取的正则表达式与日期解析器的映射 */
	private static Map<String, SimpleDateFormat> dateFormatMap = new HashMap<String, SimpleDateFormat>();
	/** 用于抽取时间的正则 */
	private static List<String> formatRegs = new ArrayList<String>();
	/** 时间解析器 */
	private static DateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
	static {
		String dateFormat = AppConf.get().get("crawl.date.format");
		String[] formats = dateFormat.split(";");
		if (formats != null) {
			for (String format : formats) {
				if (format.trim().length() > 0) {
					String[] terms = format.split("#");
					if (terms.length == 2) {
						String reg = null;
						if (terms[0].trim().length() > 0) {
							reg = terms[0].trim();
						}
						String dateformatString = null;
						if (terms[1].trim().length() > 0) {
							dateformatString = terms[1].trim();
						}
						if (reg != null && dateformatString != null) {
							SimpleDateFormat sdf = new SimpleDateFormat(dateformatString);
							dateFormatMap.put(reg, sdf);
							formatRegs.add(reg);
						}
					}
				}
			}
		}
	}

	/**
	 * 属性解析
	 * 
	 * @param content
	 * @param fle
	 * @param doc
	 * @return
	 */
	public static Properties parse(String content, FetchEntry fle, Document doc) {
		String siteId = fle.getSiteId();// 站点ID
		List<FetchTemplate> fetchTemplateList = NConfigManager.getInstance().findFetchTemplateBeanBySiteId(siteId);
		return parse(content, fle, doc, fetchTemplateList);
	}

	/**
	 * 解析关联规则
	 * 
	 * @param content
	 * @param fle
	 * @param doc
	 * @return
	 */
	public static List<Properties> parseRelationRule(String content, FetchEntry fle, Document doc) {
		List<Properties> result = null;
		String url = fle.getUrl();// 当前页面的URL
		String siteId = fle.getSiteId();// 站点ID
		List<FetchTemplate> fetchTemplateList = NConfigManager.getInstance().findFetchTemplateBeanBySiteId(siteId);
		List<FetchTemplate> templateList = new ArrayList<FetchTemplate>();
		if (fetchTemplateList != null) {
			for (FetchTemplate fetchTemplate : fetchTemplateList) {
				if (urlMatcher(url, fetchTemplate)) {
					templateList.add(fetchTemplate);
				}
			}
		}
		result = parseRelationRule(content, fle, doc, templateList);
		return result == null ? new ArrayList<Properties>() : result;
	}

	/**
	 * 解析关联规则
	 * 
	 * @param content
	 * @param fle
	 * @param doc
	 * @param templateList
	 * @return
	 */
	public static List<Properties> parseRelationRule(String content, FetchEntry fle, Document doc, List<FetchTemplate> templateList) {
		List<Properties> result = new ArrayList<Properties>();
		if (templateList.isEmpty()) {
			result = new ArrayList<Properties>();
		} else {
			for (FetchTemplate fetchTemplate : templateList) {
				result = parseRelationRule(content, fle, doc, fetchTemplate);
				if (result != null && !result.isEmpty()) {
					break;
				}
			}
		}
		return result;
	}

	/**
	 * 解析关联规则
	 * 
	 * @param content
	 * @param fle
	 * @param doc
	 * @param fetchTemplate
	 * @return
	 */
	public static List<Properties> parseRelationRule(String content, FetchEntry fle, Document doc, FetchTemplate fetchTemplate) {
		List<Properties> result = new ArrayList<Properties>();
		String relationRule = fetchTemplate.getRelationRule();
		List<String> blocks = explainRuleMore(content, fle, doc, relationRule, false);// 找到各个符合关联规则的块
		for (String block : blocks) {
			Properties properties = parseRelationBlock(block, fle, doc, fetchTemplate);// 解析一个一个的块
			result.add(properties);
		}
		return result;
	}

	/**
	 * 根据块规则解析关联块。系统中暂时没有引入块规则的概念，该方法留待以后扩充。
	 * 
	 * @param block
	 * @param fle
	 * @param doc
	 * @param fetchTemplate
	 * @return
	 */
	public static Properties parseRelationBlock(String block, FetchEntry fle, Document doc, FetchTemplate fetchTemplate) {
		Properties properties = new Properties();
		if (block != null && block.length() > 0) {
			Document blockDoc = Jsoup.parseBodyFragment(block);
			block = blockDoc.body().text();
		}
		properties.setProperty("blockContent", block);
		return properties;
	}

	/**
	 * 找到分页链接
	 * 
	 * @param content
	 * @param fle
	 * @param doc
	 * @return
	 */
	public static Outlink[] findPageUrl(String content, FetchEntry fle, Document doc,Integer type) {
		String siteId = fle.getSiteId();// 站点ID
		List<FetchTemplate> fetchTemplateList = NConfigManager.getInstance().findFetchTemplateBeanBySiteId(siteId);
		fetchTemplateList = fetchTemplateList == null ? new ArrayList<FetchTemplate>() : fetchTemplateList;
		return findPageUrl(content, fle, doc, fetchTemplateList,type);
	}

	/**
	 * 找到分页部分
	 * 
	 * @param content
	 * @param fle
	 * @param doc
	 * @param fetchTemplateList
	 * @param type type=1 获取分页的url type=2获取相管连接的url
	 * @return
	 */
	public static Outlink[] findPageUrl(String content, FetchEntry fle, Document doc, List<FetchTemplate> fetchTemplateList,Integer type) {
		List<Outlink> urlResult = new ArrayList<Outlink>();
		fetchTemplateList = fetchTemplateList == null ? new ArrayList<FetchTemplate>() : fetchTemplateList;
		for (FetchTemplate fetchTemplate : fetchTemplateList) {
			String pageRule = null;
			if(type == 1){
				pageRule = fetchTemplate.getPageRules();
			}else{
				pageRule = fetchTemplate.getRelationRule();
			}
			if (pageRule != null && pageRule.trim().length() > 0) {
				String result = explainRule(content, fle, doc, pageRule, false);
				if (result != null && result.length() > 0) {
					Document pageDoc = Jsoup.parseBodyFragment(result);
					Elements eles = pageDoc.select("a");
					if (eles != null && eles.size() > 0) {
						for (Element ele : eles) {
							String href = ele.attr("href");
							if (href != null && href.trim().length() > 0) {
								try {
									URL hrefUrl = new URL(new URL(fle.getUrl()), href);
									String pageUrl = hrefUrl.toString();
									Outlink outlink = new Outlink();
									outlink.setFromUrl(fle.getUrl());
									outlink.setLevel(fle.getLevel());
									outlink.setUrl(pageUrl);
									urlResult.add(outlink);
								} catch (MalformedURLException e) {
								}
							}
						}
					}
				}
			}
		}
		return urlResult.toArray(new Outlink[urlResult.size()]);
	}

	/**
	 * 对内容进行解析以及赋值
	 * 
	 * @param content
	 * @param fle
	 * @param doc
	 * @param fetchTemplateList
	 * @return
	 */
	public static Properties parse(String content, FetchEntry fle, Document doc, List<FetchTemplate> fetchTemplateList) {
		Properties properties = new Properties();
		String siteId = fle.getSiteId();// 站点ID
		String url = fle.getUrl();// 当前页面的URL
		String title = "";// 标题
		String author = "";// 作者
		String pubtime = "";// 发布时间
		String srcSite = "";// 来源
		String plCount = "";// 评论数
		String viewCount = "";// 浏览数
		String icontent = "";// 正文内容
		String siteName = "";// 站点名称
		String catagroyName = "";// 分类名称
		String contryName = "";// 国家
		String provinceName = "";// 省
		String cityName = "";// 市
		String countyName = "";// 县
		String domain = "";// 域
		String refurl = fle.getRefurl();// 上级页面的URL
		String metatype = "";// 媒体类型
		String areaType = "";// 区域类型
		String ctype = "";//
		List<FetchTemplate> templateList = new ArrayList<FetchTemplate>();
		if (fetchTemplateList != null) {
			for (FetchTemplate fetchTemplate : fetchTemplateList) {
				if (urlMatcher(url, fetchTemplate)) {
					templateList.add(fetchTemplate);
				}
			}
		}
		if (templateList.isEmpty()) {
			properties.setProperty("xdservermessage", "URL地址不匹配");
		}
		title = findTitle(content, fle, doc, templateList);// 标题
		//添加标题替换规则
		for (FetchTemplate fetchTemplate : templateList) {
			if (title != null && title.trim().length() > 0 && null != fetchTemplate.getTitleFilterChars()) {
				title = title.replace(fetchTemplate.getTitleFilterChars(), "");
				break;
			}
			
		}
		//判断爬虫是否存储快照
		String snap = "";
		if(NConfigManager.getCrawler().getSaveSnap() == 1){
			snap = content;
		}
		icontent = findContent(content, fle, doc, templateList);// 内容
		pubtime = findPubTime(content, fle, doc, templateList);// 发布时间
		author = findAuthor(content, fle, doc, templateList);// 作者
		srcSite = findSrcSite(content, fle, doc, templateList);// 站点来源
		plCount = findPlCount(content, fle, doc, templateList);// 评论数
		viewCount = findViewCount(content, fle, doc, templateList);// 浏览数

		NSite site = NConfigManager.getInstance().getSiteById(siteId);// 根据ID获取站点配置
		if (site != null) {
			siteName = site.getName();// 站点名称
			catagroyName = site.getSiteCategoryId();// 站点分类
			contryName = site.getContryId();// 国家
			provinceName = site.getProvinceId();// 省
			cityName = site.getCityId();// 市
			countyName = site.getAreaId();// 县
			metatype = site.getMediaType();
			areaType = site.getAreaType();// 区域类型
			if (0 == site.getWebType()) {
				ctype = "新闻";
			} else if (1 == site.getWebType()) {
				ctype = "论坛";
			} else if (2 == site.getWebType()) {
				ctype = "博客";
			} else {
				ctype = "其他";//
			}
		}
		try {
			URL urlObject = new URL(url);
			domain = urlObject.getHost();
		} catch (MalformedURLException e) {
		}
		//如果是站点采集，则搜索引擎编码为空；如果是全网监控，站点ID为空，
		if(null != fle.getSearchEngine() && !"".equals(fle.getSearchEngine())){
			properties.setProperty(DataAttribute.searchEngine, fle.getSearchEngine());
		}else{
			properties.setProperty(DataAttribute.siteid, siteId == null ? "" : siteId);
		}
		fle.setTitle(title == null ? "" : title);
		properties.setProperty(DataAttribute.title, title == null ? "" : title);
		properties.setProperty(DataAttribute.mainContent, icontent == null ? "" : icontent);
		properties.setProperty(DataAttribute.author, author == null ? "" : author);
		properties.setProperty(DataAttribute.issueDate, pubtime == null ? "" : pubtime);
		properties.setProperty(DataAttribute.copyfrom, srcSite == null ? "" : srcSite);
		properties.setProperty(DataAttribute.commentcount, plCount == null ? "" : plCount);
		properties.setProperty(DataAttribute.hitcount, viewCount == null ? "" : viewCount);
		properties.setProperty(DataAttribute.siteName, siteName == null ? "" : siteName);
		properties.setProperty(DataAttribute.sitecategory, catagroyName == null ? "" : catagroyName);
		properties.setProperty(DataAttribute.country, contryName == null ? "" : contryName);
		properties.setProperty(DataAttribute.province, provinceName == null ? "" : provinceName);
		properties.setProperty(DataAttribute.city, cityName == null ? "" : cityName);
		properties.setProperty(DataAttribute.area, countyName == null ? "" : countyName);
		properties.setProperty(DataAttribute.snapshot, snap == null ? "" : snap);
		properties.setProperty(DataAttribute.domain, domain == null ? "" : domain);
		properties.setProperty(DataAttribute.referer, refurl == null ? "" : refurl);
		properties.setProperty(DataAttribute.crawlDate, sdf.format(new Date()));// 采集时间
		properties.setProperty(DataAttribute.metaType, metatype == null ? "网页" : metatype);// 媒体类型
		properties.setProperty(DataAttribute.mediaType, ctype == null ? "" : ctype);// 表现类型
		properties.setProperty(DataAttribute.sourceurl, fle.getOldUrl());
		properties.setProperty(DataAttribute.mediaAreaType, areaType == null ? "" : areaType);// 区域属性
		properties.setProperty(DataAttribute.ctype, ctype == null ? "" : ctype);// 站点类型
		properties.setProperty(DataAttribute.URLMD5, fle.getMd5() == null ? "" : fle.getMd5());//URL的MD5
		properties.setProperty(DataAttribute.xdaddtime,new Date().toLocaleString());
		return properties;
	}

	/**
	 * 找到文章的发布时间
	 * 
	 * @param content
	 * @param fle
	 * @param doc
	 * @param templateList
	 * @return
	 */
	private static String findPubTime(String content, FetchEntry fle, Document doc, List<FetchTemplate> templateList) {
		String result = "";
		for (FetchTemplate fetchTemplate : templateList) {
			result = findPubTime(content, fle, doc, fetchTemplate);
			if (result != null && result.trim().length() > 0) {
				break;
			}
		}
		if (result == null || result.trim().length() == 0) {
			result = sdf.format(new Date());
		}
		return result;
	}

	/**
	 * 解析评论数
	 * 
	 * @param content
	 * @param fle
	 * @param doc
	 * @param templateList
	 * @return
	 */
	private static String findViewCount(String content, FetchEntry fle, Document doc, List<FetchTemplate> templateList) {
		String result = "";
		for (FetchTemplate fetchTemplate : templateList) {
			result = findViewCount(content, fle, doc, fetchTemplate);
			if (result != null && result.trim().length() > 0) {
				break;
			}
		}
		return result = result == null ? "" : result;
	}

	/**
	 * 解析评论数
	 * 
	 * @param content
	 * @param fle
	 * @param doc
	 * @param fetchTemplate
	 * @return
	 */
	private static String findViewCount(String content, FetchEntry fle, Document doc, FetchTemplate fetchTemplate) {
		String rule = fetchTemplate.getViews();
		String result = explainRule(content, fle, doc, rule, true);
		if (result != null && result.length() > 0) {
			try {
				result = Integer.parseInt(result) + "";
			} catch (Exception e) {
			}
		}
		return result;
	}

	/**
	 * 解析评论数
	 * 
	 * @param content
	 * @param fle
	 * @param doc
	 * @param templateList
	 * @return
	 */
	private static String findPlCount(String content, FetchEntry fle, Document doc, List<FetchTemplate> templateList) {
		String result = "";
		for (FetchTemplate fetchTemplate : templateList) {
			result = findPlCount(content, fle, doc, fetchTemplate);
			if (result != null && result.trim().length() > 0) {
				break;
			}
		}
		return result = result == null ? "" : result;
	}

	/**
	 * 解析评论数
	 * 
	 * @param content
	 * @param fle
	 * @param doc
	 * @param fetchTemplate
	 * @return
	 */
	private static String findPlCount(String content, FetchEntry fle, Document doc, FetchTemplate fetchTemplate) {
		String rule = fetchTemplate.getComments();
		String result = explainRule(content, fle, doc, rule, true);
		if (result != null && result.length() > 0) {
			try {
				result = Integer.parseInt(result) + "";
			} catch (Exception e) {
			}
		}
		return result;
	}

	/**
	 * 寻找来源
	 * 
	 * @param content
	 * @param fle
	 * @param doc
	 * @param templateList
	 * @return
	 */
	private static String findSrcSite(String content, FetchEntry fle, Document doc, List<FetchTemplate> templateList) {
		String result = "";
		for (FetchTemplate fetchTemplate : templateList) {
			result = findSrcSite(content, fle, doc, fetchTemplate);
			if (result != null && result.trim().length() > 0) {
				break;
			}
		}
		return result = result == null ? "" : result;
	}

	/**
	 * 寻找来源
	 * 
	 * @param content
	 * @param fle
	 * @param doc
	 * @param fetchTemplate
	 * @return
	 */
	private static String findSrcSite(String content, FetchEntry fle, Document doc, FetchTemplate fetchTemplate) {
		String rule = fetchTemplate.getSrcSites();
		String result = explainRule(content, fle, doc, rule, true);
		return result;
	}

	/**
	 * 匹配作者
	 * 
	 * @param content
	 * @param fle
	 * @param doc
	 * @param templateList
	 * @return
	 */
	private static String findAuthor(String content, FetchEntry fle, Document doc, List<FetchTemplate> templateList) {
		String result = "";
		for (FetchTemplate fetchTemplate : templateList) {
			result = findAuthor(content, fle, doc, fetchTemplate);
			if (result != null && result.trim().length() > 0) {
				break;
			}
		}
		return result = result == null ? "" : result;
	}

	/**
	 * 匹配作者
	 * 
	 * @param content
	 * @param fle
	 * @param doc
	 * @param fetchTemplate
	 * @return
	 */
	private static String findAuthor(String content, FetchEntry fle, Document doc, FetchTemplate fetchTemplate) {
		String rule = fetchTemplate.getAuthor();
		String result = explainRule(content, fle, doc, rule, true);
		return result;
	}

	/**
	 * 找到文章的发布时间
	 * 
	 * @param content
	 * @param fle
	 * @param doc
	 * @param fetchTemplate
	 * @return
	 */
	private static String findPubTime(String content, FetchEntry fle, Document doc, FetchTemplate fetchTemplate) {
		String rule = fetchTemplate.getDatatime();
		String result = explainRule(content, fle, doc, rule);
		if (result != null) {
			for (String format : formatRegs) {
				Pattern pattern = Pattern.compile(format, Pattern.CASE_INSENSITIVE);
				Matcher matcher = pattern.matcher(result);
				if (matcher.find()) {
					result = matcher.group(0);
					SimpleDateFormat df = dateFormatMap.get(format);
					if (df != null) {
						try {
							result = sdf.format(df.parse(result));
						} catch (Exception e) {
						}
					}
					break;
				}
			}
		}
		return result;
	}

	/**
	 * 根据一个模板找到内容，如果根据模板内容没有找到，则自动发现内容。内容中的A标签需要去掉连接。
	 * 
	 * @param content
	 * @param fle
	 * @param doc
	 * @param fetchTemplate
	 * @return
	 */
	private static String findContent(String content, FetchEntry fle, Document doc, List<FetchTemplate> templateList) {
		String result = "";
		/*
		 * 模板中提取
		 */
		for (FetchTemplate fetchTemplate : templateList) {
			result = findContent(content, fle, doc, fetchTemplate);
			if (result != null && result.trim().length() > 0) {
				break;
			}
		}
		/*
		 * 正文自动获取算法自动获取正文
		 */
		if (result == null || result.trim().length() == 0) {
			try {
				String url = fle.getUrl();
				result = AutoContentHelp.getAutoContent(url, content);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		/*
		 * 都没有找到正文，则把整个网页当做正文
		 */
		if (result == null || result.trim().length() == 0) {
			Elements bodys = doc.select("body");
			if (bodys != null && !bodys.isEmpty()) {
				Element body = bodys.get(0);
				Elements styles = body.select("style");
				if (styles != null) {
					for (Element ele : styles) {
						ele.remove();
					}
				}
				Elements scripts = body.select("script");
				if (scripts != null) {
					for (Element ele : scripts) {
						ele.remove();
					}
				}
				Elements inputs = body.select("input");
				if (inputs != null) {
					for (Element ele : inputs) {
						if (!"image".equalsIgnoreCase(ele.attr("type"))) {
							ele.remove();
						}
					}
				}
				Elements textareas = body.select("textarea");
				if (textareas != null) {
					for (Element ele : textareas) {
						ele.remove();
					}
				}
				result = body.html();
			}
		}
		if (result != null && result.length() > 0) {
			Document contentDoc = Jsoup.parseBodyFragment(result);
			Elements eles = contentDoc.select("a");
			if (eles != null) {
				for (Element ele : eles) {
					ele.removeAttr("href");
				}
			}
			result = contentDoc.body().html();
		}
		return result;
	}

	/**
	 * 找到标题，如果标题中有html标签，则把标签去掉
	 * 
	 * @param content
	 * @param fle
	 * @param doc
	 * @param fetchTemplate
	 * @return
	 */
	private static String findTitle(String content, FetchEntry fle, Document doc, List<FetchTemplate> templateList) {
		String title = "";
		for (FetchTemplate fetchTemplate : templateList) {
			title = findTitle(content, fle, doc, fetchTemplate);
			if (title != null && title.trim().length() > 0) {
				break;
			}
		}
		if (title == null || title.trim().length() == 0) {
			title = explainRule(content, fle, doc, "title");
			if (templateList != null && templateList.size() > 0) {
				FetchTemplate fetchTemplate = templateList.get(0);
				if (fetchTemplate != null && fetchTemplate.getTitleFilterChars() != null && fetchTemplate.getTitleFilterChars().trim().length() > 0) {
					title = title.replace(fetchTemplate.getTitleFilterChars().trim(), "");
				}
			}
		}
		if (title != null && title.contains("<") && title.contains(">")) {
			Document titleDoc = Jsoup.parseBodyFragment(title);
			title = titleDoc.text();
		}
		return title;
	}

	/**
	 * 根据一个模板找到标题
	 * 
	 * @param content
	 * @param fle
	 * @param doc
	 * @param fetchTemplate
	 * @return
	 */
	private static String findTitle(String content, FetchEntry fle, Document doc, FetchTemplate fetchTemplate) {
		String rule = fetchTemplate.getTitleRules();
		String title = explainRule(content, fle, doc, rule);
		if (title != null && fetchTemplate.getTitleFilterChars() != null && fetchTemplate.getTitleFilterChars().trim().length() > 0) {
			title = title.replace(fetchTemplate.getTitleFilterChars().trim(), "");
		}
		return title;
	}

	/**
	 * 根据一个模板找到内容
	 * 
	 * @param content
	 * @param fle
	 * @param doc
	 * @param fetchTemplate
	 * @return
	 */
	private static String findContent(String content, FetchEntry fle, Document doc, FetchTemplate fetchTemplate) {
		String rule = fetchTemplate.getContentRules();
		String icontent = explainRule(content, fle, doc, rule);
		return icontent;
	}

	/**
	 * 通用的规则解释器
	 * 
	 * @param content
	 * @param fle
	 * @param doc
	 * @param rule
	 * @return
	 */
	private static String explainRule(String content, FetchEntry fle, Document doc, String rule) {
		String result = null;
		result = explainRule(content, fle, doc, rule, false);
		return result;
	}

	/**
	 * 通用的规则解释器
	 * 
	 * @param content
	 * @param fle
	 * @param doc
	 * @param rule
	 * @param text
	 * @return
	 */
	private static String explainRule(String content, FetchEntry fle, Document doc, String rule, boolean text) {
		String result = null;
		if (rule != null && rule.trim().length() > 0) {
			if (rule.startsWith("~")) {
				Pattern pattern = Pattern.compile(rule.substring(1), Pattern.CASE_INSENSITIVE);
				Matcher matcher = pattern.matcher(content);
				if (matcher.find()) {
					result = matcher.group(1);
					if (text && result != null && result.length() > 0) {
						try {
							Document segDoc = Jsoup.parseBodyFragment(result);
							result = segDoc.body().text();
						} catch (Exception e) {
						}
					}
				}
			} else {
				Elements eles = doc.select(rule);
				if (eles != null && eles.size() > 0) {
					Element ele = eles.get(0);
					if (ele != null) {
						if (text) {
							result = ele.text();
						} else {
							result = ele.html();
						}
					}
				}
			}
		}
		return result;
	}

	/**
	 * 根据规则找到所有符合条件的元素
	 * 
	 * @param content
	 * @param fle
	 * @param doc
	 * @param rule
	 * @param text
	 * @return
	 */
	private static List<String> explainRuleMore(String content, FetchEntry fle, Document doc, String rule, boolean text) {
		List<String> result = new ArrayList<String>();
		if (rule != null && rule.trim().length() > 0) {
			rule = rule.trim();
			if (rule.startsWith("~")) {
				Pattern pattern = Pattern.compile(rule.substring(1), Pattern.CASE_INSENSITIVE);
				Matcher matcher = pattern.matcher(content);
				while (matcher.find()) {
					String one = matcher.group(1);
					if (text && one != null && one.length() > 0) {
						try {
							Document segDoc = Jsoup.parseBodyFragment(one);
							one = segDoc.body().text();
						} catch (Exception e) {
						}
					}
					result.add(one);

				}
			} else {
				Elements eles = doc.select(rule);
				if (eles != null && eles.size() > 0) {
					for (Element ele : eles) {
						if (ele != null) {
							String one = "";
							if (text) {
								one = ele.text();
							} else {
								one = ele.html();
							}
							result.add(one);
						}
					}
				}
			}
		}
		return result;
	}

	/**
	 * 判断URL是否与模板的URL匹配
	 * 
	 * @param url
	 * @param fetchTemplate
	 * @return
	 */
	private static boolean urlMatcher(String url, FetchTemplate fetchTemplate) {
		String urlRule = fetchTemplate.getUrlRule();
		if (urlRule != null) {
			if (urlRule.startsWith("~")) {
				String regex = urlRule.substring(1);
				Pattern pattern = patternMap.get(regex);
				if (pattern == null) {
					pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
					patternMap.put(regex, pattern);
				}
				Matcher matcher = pattern.matcher(url);
				return matcher.find();
			} else {
				return url.toLowerCase().contains(urlRule.toLowerCase());
			}
		} else {
			return false;
		}
	}

	/**
	 * 测试url规则
	 */
	private static void testUrlRule() {
		String url = "http://www.xj.xinhuanet.com/2013-02/25/c_114788503.htm";
		String urlRule = "~www.xj.xinhuanet.com/[\\d]{4}-[\\d]{2}/[\\d]{2}/c_[\\d]{1,}.htm";
		FetchTemplate template = new FetchTemplate();
		template.setUrlRule(urlRule);
		boolean isOk = urlMatcher(url, template);
		System.out.println(isOk);

	}

	private static void testtTitleRule() throws HttpException, IOException {
		String url = "http://www.xj.xinhuanet.com/2013-02/25/c_114788503.htm";
		HttpClient client = new HttpClient();
		HttpMethod method = new GetMethod(url);
		int code = client.executeMethod(method);
		if (code == 200) {
			String content = new String(method.getResponseBody(), "utf-8");
			Document doc = Jsoup.parse(content);
			FetchTemplate fetchTemplate = new FetchTemplate();
			fetchTemplate.setTitleRules("asdads");
			fetchTemplate.setTitleFilterChars("新疆");
			List<FetchTemplate> list = new ArrayList<FetchTemplate>();
			list.add(fetchTemplate);
			FetchEntry fle = new FetchEntry();
			String title = findTitle(content, fle, doc, list);
			System.out.println(title);
		}
	}

	private static void testtContentRule() throws HttpException, IOException {
		String url = "http://news.iqilu.com/china/gedi/2013/0225/1455507.shtml";
		HttpClient client = new HttpClient();
		HttpMethod method = new GetMethod(url);
		int code = client.executeMethod(method);
		if (code == 200) {
			String content = new String(method.getResponseBody(), "utf-8");
			Document doc = Jsoup.parse(content);
			FetchTemplate fetchTemplate = new FetchTemplate();
			fetchTemplate.setTitleRules("asdads");
			fetchTemplate.setTitleFilterChars("新疆");
			fetchTemplate.setContentRules("#context");
			List<FetchTemplate> list = new ArrayList<FetchTemplate>();
			list.add(fetchTemplate);
			FetchEntry fle = new FetchEntry();
			String icontent = findContent(content, fle, doc, list);
			System.out.println(icontent);
		}
	}

	public static void main(String[] args) throws Exception {
		testtTitleRule();
		testtContentRule();
	}
}
