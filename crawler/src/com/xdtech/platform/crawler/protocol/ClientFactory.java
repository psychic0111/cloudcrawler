package com.xdtech.platform.crawler.protocol;

import com.xdtech.platform.crawler.protocol.httpclient.HttpClient3;

public class ClientFactory {
	public static Protocol findAProtocol(String url) {
		return new HttpClient3();
	}
}
