package com.xdtech.platform.crawler.process;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.UUID;

/**
 * 写文本操作
 * 
 * @author zhangjianbing@msn.com
 * 
 */
public class TextWriter extends ContentWriter {
	public File write() {
		File file = new File(folder, UUID.randomUUID().toString().replace("-", "") + FileSystemWriter.SUFFIX);
//		System.out.println(file);
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
			bw.write(text);
			bw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bw != null) {
				try {
					bw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return file;
	}
}
