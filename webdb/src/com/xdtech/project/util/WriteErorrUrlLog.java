package com.xdtech.project.util;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

public class WriteErorrUrlLog {
	private static File logFile = null;
	
	static {
		synchronized (WriteErorrUrlLog.class) {
			URL url = Thread.currentThread().getContextClassLoader().getResource(".");
			try {
				logFile = new File(url.toURI());
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
		}
	}
	
	
}
