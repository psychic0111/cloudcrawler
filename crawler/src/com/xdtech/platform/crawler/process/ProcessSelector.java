package com.xdtech.platform.crawler.process;

import com.xdtech.platform.crawler.parser.Parse;
import com.xdtech.platform.crawler.protocol.FetchEntry;
import com.xdtech.platform.crawler.protocol.ProtocolOutput;
import com.xdtech.platform.util.AppConf;

public class ProcessSelector {
	public static Process getProcess(String oldURL, FetchEntry fle, ProtocolOutput output, Parse parse) {
		int appType = AppConf.get().getInt("crawler.app.type", 0);
		if (appType == 0) {
			return DataBaseProcessor.getInstance();
		} else {
			return FileSystemProcessor.getInstance();
		}
	}
}
