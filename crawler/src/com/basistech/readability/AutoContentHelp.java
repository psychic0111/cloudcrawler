package com.basistech.readability;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;

public class AutoContentHelp {
	
	
	public static String getAutoContent(String url,String html){
		String preContent = null;
		try {
			Readability readability = new Readability();
			readability.processDocument1(html, url);
			preContent = readability.getArticleText();
		} catch (PageReadException e) {
			e.printStackTrace();
		}
		return preContent;
	}
	public static void main(String[] args) {
		String url = "http://news.sina.com.cn/c/2013-08-07/030027881742.shtml";
		String encode = "gb2312";
		HttpClient client = new HttpClient();
		HttpMethod method = new GetMethod(url);
		
		try {
			int code = client.executeMethod(method);
			if (code == 200) {
				String content = new String(method.getResponseBody(), encode);
				System.out.println(content);
				System.out.println("*************************");
				System.out.println("*************************");
				long start = System.currentTimeMillis();
				String value = getAutoContent(url, content);
				System.out.println(value);
				long end = System.currentTimeMillis();
				System.out.println("解析这个文档共花费了："+(end-start)+"毫秒！");
			}
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			method.releaseConnection();
		}
		

	}
}
