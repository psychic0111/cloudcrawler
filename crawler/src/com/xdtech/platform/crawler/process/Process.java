package com.xdtech.platform.crawler.process;

import com.xdtech.platform.crawler.parser.Parse;
import com.xdtech.platform.crawler.protocol.FetchEntry;
import com.xdtech.platform.crawler.protocol.ProtocolOutput;

public interface Process {
	public void process(String tableName,String oldURL, FetchEntry fle, ProtocolOutput output, Parse parse);
}
