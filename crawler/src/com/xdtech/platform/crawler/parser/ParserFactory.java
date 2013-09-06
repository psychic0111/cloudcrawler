package com.xdtech.platform.crawler.parser;

import com.xdtech.platform.crawler.protocol.FetchEntry;
import com.xdtech.platform.crawler.protocol.ProtocolOutput;
import com.xdtech.platform.util.AppConf;
import com.xdtech.platform.util.URLType;

public class ParserFactory {
	public static Parser getParser(ProtocolOutput c, FetchEntry fle) {
		int appType = AppConf.get().getInt("crawler.app.type", 0);
		if (appType == 1) {
			if (fle.getUrlType() == URLType.LINK) {
				return new CSSParser();
			} else {
				return new HtmlParser();
			}
		} else {
			return new PageParser();
		}
	}
}
