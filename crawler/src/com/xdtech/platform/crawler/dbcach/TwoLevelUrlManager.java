package com.xdtech.platform.crawler.dbcach;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.namespace.QName;

import com.xdtech.platform.crawler.ControlStatus;
import com.xdtech.platform.crawler.protocol.FetchEntry;
import com.xdtech.platform.crawler.ws.webdb.Exception_Exception;
import com.xdtech.platform.crawler.ws.webdb.UrlInfo;
import com.xdtech.platform.crawler.ws.webdb.WebDBServiceInterface;
import com.xdtech.platform.crawler.ws.webdb.WebDBServiceService;
import com.xdtech.platform.crawler.ws.webdb.WebDb;
import com.xdtech.platform.util.AppConf;


public class TwoLevelUrlManager {
	/** 一级地址缓存 */
	/** 非一級URL缓存 */
	private static FetchEntryContainer urls1level = new FetchEntryContainer();
	/** URLloader线程组 */
	private static ThreadGroup urlManagerThreadGroup = new ThreadGroup("XDURLManagerThread");
	/** 爬虫要采集的地址表 */
	private static String[] webdbs = new String[] {};
	/** 要加载的URL的等級 */
	private static int[] urlLevels = new int[] {};
	/**
	 * 添加一级地址到地址库管理中
	 * @param entrys
	 */
	public static void addOneLevelUrl(List<FetchEntry> entrys){
		urls1level.addAll(entrys);
	}
	/**
	 * 获取一个一级地址
	 * @return
	 */
	public static FetchEntry getNext(){
		return urls1level.removeNext();
	}
	/**
	 * 获取一级地址库的数量
	 * @return
	 */
	public static Integer getSize(){
		return urls1level.urls0level.size();
	}
	/**
	 * 抓取完成之后移除数据
	 * 
	 * @param dataId
	 * @param level
	 */
	public static void remove(String dataId, int level) {
		if (urls1level != null) {
			urls1level.remove(dataId);
		}
	}
	public static WebDBServiceInterface servicePort = null;
	public static void update(FetchEntry fle, int type) {
		if(null == servicePort){
			try {
				URL url;
				url = new URL(AppConf.get().get("webdb.webservice.url"));
				WebDBServiceService service = new WebDBServiceService(url, new QName("http://www.xd-tech.com", "WebDBServiceService"));
				servicePort = service.getWebDBServicePort();
			} catch (MalformedURLException e1) {
				e1.printStackTrace();
			}
		}
		try {
			servicePort.update(fle.getDataId(), fle.getWebdb(), type);
		} catch (Exception_Exception e) {
			e.printStackTrace();
		}
			
	}

	/**
	 * 向地址库中添加新地址
	 * 
	 * @param list
	 */
	public static void addWebDb(String tableName,List<UrlInfo> list) {
		if (list != null && list.size() > 0) {
			if(null == servicePort){
				URL url;
				try {
					url = new URL(AppConf.get().get("webdb.webservice.url"));
					WebDBServiceService service = new WebDBServiceService(url, new QName("http://www.xd-tech.com", "WebDBServiceService"));
					servicePort = service.getWebDBServicePort();
				} catch (MalformedURLException e1) {
					e1.printStackTrace();
				}
			}
			try {
				servicePort.addUrl(tableName,list);
			} catch (Exception_Exception e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 保存URL的容器，这个类所生成的对象的所有对外方法应该都是线程安全的
	 * 
	 * @author zhangjianbing@msn.com
	 */
	private static class FetchEntryContainer {
		/**
		 * 待抓取的URL
		 */
		private List<FetchEntry> urls0level = new ArrayList<FetchEntry>();
		/**
		 * 正在抓取的URL的ID值
		 */
		private List<String> fetchingUrlsId = new ArrayList<String>();
		/**
		 * 信息指纹
		 */
		private Map<String, String> finger = new HashMap<String, String>();
		/**
		 * 常量
		 */
		private static final String KONG = "".intern();

		/**
		 * 添加
		 * 
		 * @param entry
		 */
		private void add(FetchEntry entry) {
			synchronized (this) {
				if(!fetchingUrlsId.contains(entry.getDataId())){
					if (!finger.containsKey(entry.getDataId())) {
						finger.put(entry.getDataId(), KONG);
						urls0level.add(entry);
					}
				}
			}
		}

		/**
		 * 批量添加
		 * 
		 * @param entrys
		 */
		public void addAll(List<FetchEntry> entrys) {
			synchronized (this) {
				if (entrys != null) {
					for (FetchEntry entry : entrys) {
						add(entry);
					}
				}
			}
		}

		/**
		 * 获取数据
		 * 
		 * @return
		 */
		public FetchEntry removeNext() {
			synchronized (this) {
				FetchEntry result = null;
				if (!urls0level.isEmpty()) {
					result = urls0level.remove(0);
					fetchingUrlsId.add(result.getDataId());
				}
				return result;
			}
		}

		public void remove(String id) {
			synchronized (this) {
				if (id != null) {
					fetchingUrlsId.remove(id);
					finger.remove(id);
				}
			}
		}

		/**
		 * 判断该容器是否为空
		 * 
		 * @return
		 */
		public boolean isEmpty() {
			synchronized (this) {
				return urls0level.isEmpty();
			}
		}
	}

}
