package com.xdtech.platform.crawler.process;

import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xdtech.platform.crawler.parser.Outlink;
import com.xdtech.platform.crawler.protocol.FetchEntry;

/**
 * 写内容的接口
 * 
 * @author zhangjianbing@msn.com
 * 
 */
public abstract class ContentWriter {
	protected String url;
	protected Map<String, List<String>> headers;
	protected String text;
	protected Outlink[] outlinks;
	protected FetchEntry fle;
	protected File folder;
	protected InputStream is;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Map<String, List<String>> getHeaders() {
		return headers;
	}

	public void setHeaders(Map<String, List<String>> headers) {
		if (headers != null) {
			Map<String, List<String>> newHeader = new HashMap<String, List<String>>(headers.size());
			newHeader.putAll(headers);
			this.headers = newHeader;
		} else {
			this.headers = headers;
		}
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Outlink[] getOutlinks() {
		return outlinks;
	}

	public void setOutlinks(Outlink[] outlinks) {
		if (outlinks != null) {
			this.outlinks = new Outlink[outlinks.length];
			int i = 0;
			for (Outlink outlink : outlinks) {
				outlinks[i] = outlink.shallowClone();
			}
		} else {
			this.outlinks = outlinks;
		}
	}

	public FetchEntry getFle() {
		return fle;
	}

	public void setFle(FetchEntry fle) {
		this.fle = fle.shallowClone();
	}

	public File getFolder() {
		return folder;
	}

	public void setFolder(File folder) {
		this.folder = new File(folder.getAbsolutePath());
	}

	public InputStream getIs() {
		return is;
	}

	public void setIs(InputStream is) {
		this.is = is;
	}

	/**
	 * 写文件操作
	 * 
	 * @return
	 */
	public abstract File write();
}
