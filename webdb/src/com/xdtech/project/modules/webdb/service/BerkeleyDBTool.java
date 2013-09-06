package com.xdtech.project.modules.webdb.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * bdb数据库工具類
 * 
 * @author zhangjianbing@msn.com
 * 
 */
public class BerkeleyDBTool {
	/** 数据库映射 */
	private static Map<String, BerkeleyDB> dbMap = new HashMap<String, BerkeleyDB>();

	/**
	 * 初始化所有数据库
	 * 
	 * @throws IOException
	 */
	public static void init() throws IOException {
		DBService service = new DBService();
		String[] dbnames = service.findTables();
		if (dbnames != null) {
			for (String dbname : dbnames) {
				BerkeleyDB db = new BerkeleyDB(String.valueOf(dbname));
				dbMap.put(dbname, db);
				dbMap.put(dbname.toUpperCase(), db);
			}
		}
	}

	/**
	 * 保存数据
	 * 
	 * @param key
	 *            主键
	 * @param properties
	 * @throws Exception
	 */
	public static String saveData(String key, Properties properties, int lel) throws Exception {
		String dbname = DBService.selectDbname(key.charAt(0), lel);
		BerkeleyDB db = dbMap.get(dbname);
		if (db == null) {
			throw new Exception("数据库没有找到");
		} else {
			db.saveRecord(key, properties);
		}
		return dbname;
	}
	
	public static String update(String key, Properties properties, int lel) throws Exception {
		return saveData(key, properties, lel);
	}

	/**
	 * 同步指定的数据库
	 * 
	 * @param dbnames
	 *            数据库的名称
	 */
	public synchronized static void syncDb(String dbname) {
		BerkeleyDB db = dbMap.get(dbname);
		if (db != null) {
			db.syncDb();
		}
	}

	/**
	 * 同步所有数据库的缓存
	 */
	public synchronized static void syncAllDb() {
		if (dbMap != null && dbMap.isEmpty()) {
			for (Map.Entry<String, BerkeleyDB> entry : dbMap.entrySet()) {
				syncDb(entry.getKey());
			}
		}
	}

	/**
	 * 查看是否包含这个key
	 * 
	 * @param key
	 * @return
	 */
	public static boolean contain(String key, int lel) {
		BerkeleyDB db = null;
		if (key != null && key.length() > 0) {
			String dbname = DBService.selectDbname(key.charAt(0), lel);
			db = dbMap.get(dbname);
		}
		return db == null ? false : (db.getRecord(key) != null ? true : false);
	}

	/**
	 * 获取数据
	 * 
	 * @param key
	 * @param lel
	 * @return
	 */
	public static Properties getProperty(String key, int lel) {
		BerkeleyDB db = null;
		if (key != null && key.length() > 0) {
			String dbname = DBService.selectDbname(key.charAt(0), lel);
			db = dbMap.get(dbname);
		}
		if (db != null) {
			return db.getRecord(key);
		} else {
			return null;
		}
	}

	/**
	 * 清空指定库中的数据
	 * 
	 * @param dbname
	 */
	public static void clear(String dbname) {
		BerkeleyDB db = null;
		if (dbname != null && dbname.length() > 0) {
			db = dbMap.get(dbname);
			if (db != null) {
				db.clear();
			}
		}
	}

	/**
	 * 关闭所有数据库
	 */
	public static void closeDB() {
		if (!dbMap.isEmpty()) {
			for (Map.Entry<String, BerkeleyDB> entry : dbMap.entrySet()) {
				BerkeleyDB db = entry.getValue();
				db.close();
			}
		}
	}

	/**
	 * 删除某一条数据
	 * 
	 * @param key
	 * @param dbname
	 */
	public static void delete(String key, String dbname) {
		if (key != null) {
			BerkeleyDB db = null;
			if (dbname != null && dbname.length() > 0) {
				db = dbMap.get(dbname);
				if (db != null) {
					db.delete(key);
				}
			}
		}
	}
}
