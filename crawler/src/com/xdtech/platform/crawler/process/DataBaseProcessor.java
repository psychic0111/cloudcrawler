package com.xdtech.platform.crawler.process;

import java.lang.reflect.Field;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import com.xdtech.platform.crawler.control.NConfigManager;
import com.xdtech.platform.crawler.control.nbean.NDataBase;
import com.xdtech.platform.crawler.control.nbean.NEngine;
import com.xdtech.platform.crawler.parser.Data;
import com.xdtech.platform.crawler.parser.Parse;
import com.xdtech.platform.crawler.protocol.FetchEntry;
import com.xdtech.platform.crawler.protocol.ProtocolOutput;
import com.xdtech.platform.util.AppConf;

/**
 * 将数据加入到数据库中
 * 
 * @author zhangjianbing@msn.com
 */
public class DataBaseProcessor implements Process {
	/** 单例 */
	private static DataBaseProcessor instance = new DataBaseProcessor();
	/** 解析完成的数据 */
	private final List<Data> dataList = new ArrayList<Data>();
	/** 获取Data的数据结构 */
	private static Method[] methods = Data.class.getDeclaredMethods();
	private static Map<String, String> fieldTypeMap = getFieldTypeMap();
	private static ThreadGroup threadGroup = new ThreadGroup("databaseinsert");

	/**
	 * 私有构造方法
	 */
	private DataBaseProcessor() {
		startTask();
	}

	/**
	 * 获取实例
	 * 
	 * @return
	 */
	public static DataBaseProcessor getInstance() {
		return instance;
	}

	/**
	 * 處理
	 */
	public void process(String tableName,String oldURL, FetchEntry fle, ProtocolOutput output, Parse parse) {
		if (fle.getLevel() > 0) {
			Properties properties = parse.getData().getMetadata();
			Data data = new Data();
			for (String fieldName : fieldTypeMap.keySet()) {
				Method method = getSetMethod(fieldName);
				String value = properties.getProperty(fieldName);
				if (value != null && !"null".equalsIgnoreCase((String) value)) {
					setValue(data, method, value);
				}
			}
			if(tableName.equals("webdb_webcrawler")){
				com.xdtech.platform.webcrawler.protocol.FetchEntry fetchEntry = (com.xdtech.platform.webcrawler.protocol.FetchEntry)fle;
				NEngine nengine = NConfigManager.getNEngine(fetchEntry.getSearchEngine());
				data.setAuthor(fetchEntry.getAuthor());
				data.setSearchEngine(fetchEntry.getSearchEngine());
				if(null != fetchEntry.getPublishTime()){
					data.setIssueDate(fetchEntry.getPublishTime().toLocaleString());
				}
				data.setCtype(setCType(nengine.getContentType()));
				data.setMediaType(setCType(nengine.getContentType()));
				data.setMetaType(nengine.getMediaType());
			}
			data.setIntime(new Date());
			data.setProcess(0);
			dataList.add(data);
		}
	}

	public String setCType(String contentType){
		if(contentType.equals("0")){
			return "新闻";
		}else if(contentType.equals("1")){
			return "论坛";
		}else if(contentType.equals("2")){
			return "博客";
		}else if(contentType.equals("3")){
			return "其他";
		}
		return "";
	}
	public void startTask() {
		Thread thread = new Thread(threadGroup, new Runnable() {
			public void run() {
				while (true) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					boolean saveSnapshot = AppConf.get().getBoolean("system.save.snapshot", false);
					boolean traceCralwer = AppConf.get().getBoolean("crawler.type.trace", false);
					try {
						List<Data> datas = null;
						synchronized (dataList) {
							datas = new ArrayList<Data>(dataList);
							dataList.clear();
						}
						if (datas != null && !datas.isEmpty()) {
							Connection conn = getConnection();
							try {
								conn.setAutoCommit(true);
								// 判断是否追踪爬虫
								if (!traceCralwer) {
									saveData(conn, datas, saveSnapshot);
								} else {
									saveTraceData(conn, datas);
								}
							} catch (SQLException e) {
								e.printStackTrace();
							} finally{
								if (conn != null) {
									try {
										conn.close();
									} catch (SQLException e) {
										e.printStackTrace();
									}
								}
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}

		}, "xDDatabaseInsertThread");
		thread.start();
	}

	/**
	 * 获取数据库连接
	 * @return
	 */
	public static Connection getConnection(){
		NDataBase dataBase = NConfigManager.getNDataBase();
		String clazz = null;
		String url = null;
		String user = null;
		String password = null;
		if(null != dataBase){
			clazz = dataBase.getDbtype();
			url = dataBase.getDburl();
			user = dataBase.getDbuser();
			password = dataBase.getDbpassword();
		}else{
			clazz = AppConf.get().get("data.database.driver.class", "com.microsoft.sqlserver.jdbc.SQLServerDriver");
			url = AppConf.get().get("data.database.url");
			user = AppConf.get().get("data.database.username");
			password = AppConf.get().get("data.database.password");
		}
		Connection conn = null;
		try {
			Class.forName(clazz);
			DriverManager.setLoginTimeout(10);
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return conn;
	}
	/**
	 * 保存追踪数据
	 * 
	 * @param conn
	 * @param datas
	 * @throws SQLException
	 */
	private void saveTraceData(Connection conn, List<Data> datas) throws SQLException {
		String sql = "insert into xd_traceurls(traceurlid,url,urlmd5,commentcount,replaycount,crawlerDate) values (?,?,?,?,?,?)";
		PreparedStatement pst = conn.prepareStatement(sql);
		java.sql.Timestamp now = new java.sql.Timestamp(System.currentTimeMillis());

		for (Data data : datas) {
			String uuid = UUID.randomUUID().toString().replace("-", "");
			pst.setString(1, uuid);
			pst.setString(2, data.getSourceurl());
			pst.setString(3, data.getUrlmd5());
			pst.setInt(4, changeToInt(data.getCommentcount()));
			pst.setInt(5, changeToInt(data.getReplaycount()));
			pst.setTimestamp(6, now);
			try {
				pst.execute();
			} catch (Exception r) {
				r.printStackTrace();
			}
		}
		pst.close();
	}

	/**
	 * 转为int
	 * 
	 * @param str
	 * @return
	 */
	private int changeToInt(String str) {
		try {
			return Integer.parseInt(str);
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * 保存所有抓取数据
	 * 
	 * @param conn
	 * @param datas
	 * @param saveSnapshot
	 * @throws SQLException
	 */
	private void saveData(Connection conn, List<Data> datas, boolean saveSnapshot) throws SQLException {
		String sql = "insert into xd_data(data_id,ctitle,issue_date,crawl_date,ctype,meta_type,author,hitcount,commentCount,replayCount,sourceurl,sitecategory,siteid,site_name,domain,media_type,media_area_type,search_engine,country,province,city,area,bussiness,positive,negative,pn,similarityNo,pname,updatetime,referer,weiboid,userid,useraccount,nickname,toolname,receiveraccount,receiveruserid,receivernickname,receiverid,copyfrom,summary,keywords,main_content,snapshot,urlmd5,xdaddtime) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement pst = conn.prepareStatement(sql);
		for (Data data : datas) {
			String uuid = UUID.randomUUID().toString().replace("-", "");
			pst.setString(1, uuid);
			pst.setString(2, data.getTitle());
			pst.setString(3, data.getIssueDate());
			pst.setString(4, data.getCrawlDate());
			pst.setString(5, data.getCtype());
			pst.setString(6, data.getMetaType());
			pst.setString(7, data.getAuthor());
			pst.setString(8, data.getHitcount());
			pst.setString(9, data.getCommentcount());
			pst.setString(10, data.getReplaycount());
			pst.setString(11, data.getSourceurl());
			pst.setString(12, data.getSitecategory());
			pst.setString(13, data.getSiteid());
			pst.setString(14, data.getSiteName());
			pst.setString(15, data.getDomain());
			pst.setString(16, data.getMediaType());
			pst.setString(17, data.getMediaAreaType());
			pst.setString(18, data.getSearchEngine());
			pst.setString(19, data.getCountry());
			pst.setString(20, data.getProvince());
			pst.setString(21, data.getCity());
			pst.setString(22, data.getArea());
			pst.setString(23, data.getBussiness());
			pst.setString(24, null);
			pst.setString(25, null);
			pst.setString(26, data.getPn());
			pst.setString(27, null);
			pst.setString(28, data.getPname());
			if (data.getUpdatetime() == null) {
				pst.setDate(29, null);
			} else {
				java.sql.Date updatetime = new java.sql.Date(data.getUpdatetime().getTime());
				pst.setDate(29, updatetime);
			}
			pst.setString(30, data.getReferer());
			pst.setString(31, data.getWeiboid());
			pst.setString(32, data.getUserid());
			pst.setString(33, data.getUseraccount());
			pst.setString(34, data.getNickname());
			pst.setString(35, data.getToolname());
			pst.setString(36, data.getReceiveraccount());
			pst.setString(37, data.getReceiveruserid());
			pst.setString(38, data.getReceivernickname());
			pst.setString(39, data.getReceiverid());
			pst.setString(40, data.getCopyfrom());
			pst.setString(41, data.getSummary());
			pst.setString(42, data.getKeywords());
			pst.setString(43, data.getMainContent());
			if (saveSnapshot) {
				pst.setString(44, data.getSnapshot());
			} else {
				pst.setString(44, "");
			}
			pst.setString(45, data.getUrlmd5());
			pst.setString(46, data.getXdaddtime());
			try {
				if(NConfigManager.getInstance().checkCrawlerSites(data.getSiteid())||NConfigManager.getInstance().checkCrawlerSites(data.getSearchEngine())){
					pst.execute();
				}
			} catch (Exception r) {
				r.printStackTrace();
			}
		}
		pst.close();
	}

	/**
	 * 获取一个字段的Set方法
	 * 
	 * @param fieldName
	 * @return
	 */
	public static Method getSetMethod(String fieldName) {
		try {
			String methodName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
			for (Method method : methods) {
				if (method.getName().equals(methodName)) {
					return method;
				}
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 为data对象赋值
	 * 
	 * @param data
	 * @param method
	 * @param value
	 */
	public static void setValue(Data data, Method method, Object value) {
		if (method != null) {
			try {
				method.invoke(data, value);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 返回声明变量以及变量类型
	 * 
	 * @return
	 */
	public static Map<String, String> getFieldTypeMap() {
		Map<String, String> fieldTypeMap = new HashMap<String, String>();
		try {
			Field[] fields = Data.class.getDeclaredFields();
			for (Field field : fields) {
				fieldTypeMap.put(field.getName(), field.getType().getName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fieldTypeMap;
	}

	public static void main(String[] args) {
		getFieldTypeMap();
	}
}
