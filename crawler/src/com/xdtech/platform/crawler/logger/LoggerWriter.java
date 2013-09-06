package com.xdtech.platform.crawler.logger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LoggerWriter {
	private static File infoFile;
	private static File errorFile;
	static {
		URL url = Thread.currentThread().getContextClassLoader().getResource(".");
		if (url != null) {
			try {
				infoFile = new File(url.toURI());
				infoFile = new File(infoFile, "info.txt");
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
			try {
				errorFile = new File(url.toURI());
				errorFile = new File(errorFile, "error.txt");
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
		}
	}

	public static void writeErro(String message) {
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(errorFile, true)));
			bw.write(getDateString() + "\t" + message);
			bw.newLine();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void writeInfo(String message) {
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(infoFile, true)));
			bw.write(getDateString() + "\t" + message);
			bw.newLine();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private static String getDateString() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(new Date());
	}
}