package com.xdtech.platform.crawler.logs;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.xdtech.platform.crawler.dbcach.CrawlerLog;
import com.xdtech.platform.util.SpringUtil;

public class CrawlerLogs extends Thread{
	
	private final static String path = SpringUtil.getPath() +File.separator + "logs";
	
	private final static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd--HH-mm-ss");
	private final static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static File infoFile = new File(path + File.separator + format.format(new Date())+".txt");
	private static List<CrawlerLog> crawlerLogs = new ArrayList<CrawlerLog>();
	/**
	 * 将爬虫日志添加到集合中
	 * @param log
	 */
	public synchronized static void addCrawlerLog(CrawlerLog log){
		crawlerLogs.add(log);
	}
	/**
	 * 获取一个日志文件
	 */
	public synchronized static CrawlerLog nextCrawlerLog(){
		if(crawlerLogs.size() > 0){
			return crawlerLogs.remove(0);
		}
		return null;
	}
	/**
	 * 创建日志文件
	 */
	public static void createNewFile(){
		File file = new File(path);
		if(!file.isDirectory()){
			file.mkdirs();
		}
	}
	/**
	 * 获取一个新的文件
	 */
	public static void getNewFile(){
		infoFile = new File(path + File.separator + format.format(new Date())+".txt");
	}
	@Override
	public void run() {
		//是否要创建文件夹
		createNewFile();
		CrawlerLog log = null;
		while(true){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			log = nextCrawlerLog();
			String fileSize = showFileSite(infoFile);
			if(fileSize.startsWith("2") && fileSize.endsWith("M")){
				getNewFile();
			}
			if(null != log){
				StringBuffer sb = new StringBuffer();
				sb.append(getDateString());
				sb.append("\t");
				sb.append(log.getId());
				sb.append("\t");
				sb.append(log.getCrawlerCode());
				sb.append("\t");
				sb.append(log.getSiteId());
				sb.append("\t");
				sb.append(log.getSiteName());
				sb.append("\t");
				sb.append(log.getUrl());
				sb.append("\t");
				sb.append(log.getStatus());
				sb.append("\t");
				sb.append(sdf.format(log.getCrawlerTime()));
				sb.append("\n");
				//将文件中写入日志
				writeInfo(sb.toString());
			}
			
		}
		
	}
	/**
	 * 将文件中写入日志
	 * @param log
	 */
	public static void writeInfo(String content) {
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(infoFile, true)));
			bw.write(content);
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
	/**
	 * 小时文件的大小
	 * @param file
	 * @return
	 */
	public static String showFileSite(File file){
		try {
			long size = getFileSizes(file);
			return formetFileSize(size);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "没有找到文件！";
	}
	/**
	 * 获取文件大小
	 * @param fileS
	 * @return
	 */
	public static String formetFileSize(long fileS) {//转换文件大小
        DecimalFormat df = new DecimalFormat("#.00");
        String fileSizeString = "";
        if (fileS < 1024) {
            fileSizeString = df.format((double) fileS) + "B";
        } else if (fileS < 1048576) {
            fileSizeString = df.format((double) fileS / 1024) + "K";
        } else if (fileS < 1073741824) {
            fileSizeString = df.format((double) fileS / 1048576) + "M";
        } else {
            fileSizeString = df.format((double) fileS / 1073741824) + "G";
        }
        return fileSizeString;
    }

	/**
	 * 获取文件大小
	 * @param f
	 * @return
	 * @throws Exception
	 */
	public static long getFileSizes(File f) throws Exception{//取得文件大小
        long s=0;
        if (f.exists()) {
            FileInputStream fis = null;
            fis = new FileInputStream(f);
           s= fis.available();
        } else {
            f.createNewFile();
            System.out.println("文件不存在");
        }
        return s;
    }

	private static String getDateString() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(new Date());
	}

}
