package com.xdtech.platform.crawler.process;

import java.util.List;
import java.util.Map;

import com.xdtech.platform.crawler.parser.Outlink;
import com.xdtech.platform.crawler.parser.Parse;
import com.xdtech.platform.crawler.protocol.FetchEntry;
import com.xdtech.platform.crawler.protocol.ProtocolOutput;

public class FileSystemProcessor implements Process {
	private static FileSystemProcessor instance = new FileSystemProcessor();

	private FileSystemProcessor() {
	}

	public static FileSystemProcessor getInstance() {
		return instance;
	}

	public void process(String tableName,String oldURL, FetchEntry fle, ProtocolOutput output, Parse parse) {
		Map<String, List<String>> headers = output.getContent().getHeaders();
		Outlink[] outlinks = parse.getData().getOutlinks();
		FileSystemWriter.write(oldURL, headers, parse.getText(), outlinks, fle, output.getStatus(), null);
	}

}
