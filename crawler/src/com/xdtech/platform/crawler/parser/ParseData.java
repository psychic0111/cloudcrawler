package com.xdtech.platform.crawler.parser;

import java.util.List;
import java.util.Properties;

/**
 * 解析后的元数据以及有当前页面提取的相关链接
 * 
 * @author zhangjianbing@msn.com
 */
public class ParseData {
	/** 文本链接 */
	private Outlink[] outlinks;
	/** 元数据 */
	private Properties metadata;
	/** 内部链接，如分頁链接等 */
	private Outlink[] internallinks;
	/**页面解析到的块*/
	private List<Properties> blocks;

	public Properties getMetadata() {
		return metadata;
	}

	public void setMetadata(Properties metadata) {
		this.metadata = metadata;
	}

	public Outlink[] getOutlinks() {
		return outlinks;
	}

	public void setOutlinks(Outlink[] outlinks) {
		this.outlinks = outlinks;
	}

	public Outlink[] getInternallinks() {
		return internallinks;
	}

	public void setInternallinks(Outlink[] internallinks) {
		this.internallinks = internallinks;
	}

	public List<Properties> getBlocks() {
		return blocks;
	}

	public void setBlocks(List<Properties> blocks) {
		this.blocks = blocks;
	}
}
