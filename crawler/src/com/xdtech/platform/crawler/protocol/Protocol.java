package com.xdtech.platform.crawler.protocol;


/**
 * 抓取接口
 * 
 * @author zhangjianbing@msn.com
 * 
 */
public interface Protocol {
	/**
	 * 根据抓取对象获取数据
	 * 
	 * @param fle
	 * @return
	 */
	ProtocolOutput getProtocolOutput(FetchEntry fle);
}
