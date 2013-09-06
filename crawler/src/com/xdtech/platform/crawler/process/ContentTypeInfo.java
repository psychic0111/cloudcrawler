package com.xdtech.platform.crawler.process;

public class ContentTypeInfo {
	private boolean text;
	private String fileType;

	public boolean isText() {
		return text;
	}

	public void setText(boolean text) {
		this.text = text;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
}
