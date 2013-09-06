package com.xdtech.project.webservice.bean;

import com.xdtech.project.util.MD5Util;

public class URLInfo {
	private String url;
	private int level;
	private String siteId;
	private int urlType;
	private String refurl;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getSiteId() {
		return siteId;
	}

	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}

	public int getUrlType() {
		return urlType;
	}

	public void setUrlType(int urlType) {
		this.urlType = urlType;
	}

	public String md5String() {
		if (url != null) {
			return MD5Util.MD5(url);
		}
		return null;
	}

	public String getRefurl() {
		return refurl;
	}

	public void setRefurl(String refurl) {
		this.refurl = refurl;
	}
}
