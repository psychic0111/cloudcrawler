package com.xdtech.platform.crawler.process;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

import com.xdtech.platform.crawler.CrawlerStatus;
import com.xdtech.platform.util.AppConf;

public class StreamWriter extends ContentWriter {
	/**
	 * 媒体文件最大下载限制
	 */
	private static final long maxSize = 10 * 1024 * 1024;

	public File write() {
		File file = new File(folder, UUID.randomUUID().toString().replace("-", "") + FileSystemWriter.SUFFIX);
		byte[] buff = new byte[1024];
		int length = 0;
		int total = 0;
		BufferedOutputStream bos = null;
		try {
			bos = new BufferedOutputStream(new FileOutputStream(file));
			while ((length = is.read(buff)) > 0) {
				CrawlerStatus.addLength(length);
				bos.write(buff, 0, length);
				total = total + length;
				if (total > maxSize) {
					break;
				}
			}
			bos.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bos != null) {
				try {
					bos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return file;
	}

}
