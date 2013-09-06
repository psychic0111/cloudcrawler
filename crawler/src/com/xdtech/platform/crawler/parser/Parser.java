package com.xdtech.platform.crawler.parser;

import com.xdtech.platform.crawler.protocol.Content;
import com.xdtech.platform.crawler.protocol.FetchEntry;

/**
 * 解析接口
 * 
 * @author Administrator
 * 
 */
public interface Parser {
	Parse getParse(String tableName,Content c, FetchEntry fle);
}
