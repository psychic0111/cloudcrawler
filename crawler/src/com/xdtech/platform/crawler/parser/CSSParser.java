package com.xdtech.platform.crawler.parser;

import java.io.UnsupportedEncodingException;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import com.xdtech.platform.crawler.dbcach.TwoLevelUrlManager;
import com.xdtech.platform.crawler.protocol.Content;
import com.xdtech.platform.crawler.protocol.FetchEntry;
import com.xdtech.platform.crawler.ws.webdb.UrlInfo;
import com.xdtech.platform.util.URLType;

public class CSSParser implements Parser {

	public Parse getParse(String tableName,Content c, FetchEntry fle) {
		Parse parse = null;
		try {
			parse = new Parse();
			String content = new String(c.getContent(), findcharset(c, fle));
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
					TwoLevelUrlManager.addWebDb(tableName,list);
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
		Set<String> urls = ParserUtil.findUrlFromStyle(content, fle.getUrl());
		Outlink outlink = null;
		for (String url : urls) {
			outlink = new Outlink();
			outlink.setFromUrl(fle.getUrl());
			outlink.setLevel(fle.getLevel());
			outlink.setType(URLType.IMAGE);
			outlink.setUrl(url);
			outlinkList.add(outlink);
		}
		data.setOutlinks(outlinkList.toArray(new Outlink[outlinkList.size()]));
		return data;
	}

	public static void main(String[] args) {
		String content = "#top100header {FLOAT: left; BACKGROUND-IMAGE: url(http://img.zz/flash/images/00_y15.gif); WIDTH: 196px; CURSOR: pointer; LINE-HEIGHT: 25px; HEIGHT: 25px}";
		Set<String> urls = ParserUtil.findUrlFromStyle(content, "http://img.zz/flash/css/fontnew.css");
		System.out.println(urls);
	}
}
