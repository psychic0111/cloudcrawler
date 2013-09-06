package com.xdtech.project.modules.webdb.service;

import java.io.File;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

import com.sleepycat.bind.EntryBinding;
import com.sleepycat.bind.serial.SerialBinding;
import com.sleepycat.bind.serial.StoredClassCatalog;
import com.sleepycat.je.Database;
import com.sleepycat.je.DatabaseConfig;
import com.sleepycat.je.DatabaseEntry;
import com.sleepycat.je.DatabaseException;
import com.sleepycat.je.Environment;
import com.sleepycat.je.EnvironmentConfig;
import com.sleepycat.je.LockMode;
import com.sleepycat.je.OperationStatus;
import com.xdtech.project.util.ConfigParam;

public class BerkeleyDB {

	public static final Logger LOG = Logger.getLogger(BerkeleyDB.class
			.getName());
	/** 数据库名称 */
	private String dbname;
	/** 数据库 */
	private Database myDb = null;
	private Database classCatalogDb;
	private Environment dbEnv = null;
	private StoredClassCatalog classCatalog;
	private EntryBinding dataBinding;
	private boolean isClosed = false;
	/** 从上次同步之后已经添加的新数据的数目 */
	private int addCount = 0;

	public BerkeleyDB(String name) {
		init(name);
		this.dbname = name;
	}

	private void init(String dbname) {
		LOG.info("初始化DB[" + dbname + "]......");
		File file = new File(ConfigParam.BerkeleyDBFolderPath);
		file = new File(file, dbname);
		if (!file.exists()) {
			file.mkdirs();
		}
		EnvironmentConfig envConf = new EnvironmentConfig();
		envConf.setAllowCreate(true);
		envConf.setTransactional(false);
		envConf.setCacheSize(30 * 1024 * 1024);
		envConf.setConfigParam("je.log.fileMax", "1000000000");
		dbEnv = new Environment(file, envConf);

		DatabaseConfig dbConf = new DatabaseConfig();
		dbConf.setAllowCreate(true);
		dbConf.setTransactional(false);
		dbConf.setSortedDuplicates(false);
		dbConf.setDeferredWrite(false);

		String database = "data";
		myDb = dbEnv.openDatabase(null, database, dbConf);

		classCatalogDb = dbEnv.openDatabase(null, "ClassCatalogDB", dbConf);
		classCatalog = new StoredClassCatalog(classCatalogDb);
		dataBinding = new SerialBinding(classCatalog, Properties.class);
	}

	/**
	 * 清空数据库
	 */
	public void clear() {
		synchronized (myDb) {
			DatabaseConfig dbConf = myDb.getConfig();
			myDb.close();
			String database = "data";
			dbEnv.truncateDatabase(null, database, true);
			dbEnv.sync();
			myDb = dbEnv.openDatabase(null, database, dbConf);
		}
	}

	/**
	 * 关闭数据库的方法
	 * 
	 * @throws IOException
	 */
	public void close() {
		LOG.info("关闭DB[" + this.getDbname() + "]......");
		if (myDb != null) {
			myDb.close();
		}
		if (classCatalogDb != null) {
			classCatalogDb.close();
		}
		if (dbEnv != null) {
			dbEnv.cleanLog();
			dbEnv.close();
		}
		isClosed = true;
	}

	/**
	 * 进行缓存与硬盘数据的同步
	 */
	public synchronized void syncDb() {
		synchronized (myDb) {
			try {
				if (dbEnv != null) {
					dbEnv.sync();
				}
			} catch (DatabaseException e) {
				LOG.info("数据已经同步完成，错误消息：" + e.getMessage());
			}
		}
	}

	/**
	 * 删除数据
	 * 
	 * @param key
	 *            数据的键
	 */
	public void delete(String key) {
		DatabaseEntry theKey = new DatabaseEntry((key.getBytes()));
		synchronized (myDb) {
			myDb.delete(null, theKey);
			syncDb();
		}
	}

	/**
	 * 添加新数据或者修改旧数据
	 * 
	 * @param key
	 * @param object
	 * @throws IOException
	 * @throws DatabaseException
	 */
	public synchronized void saveRecord(String key, Properties object) {
		if (key == null) {
			return;
		}
		if (object == null) {
			object = new Properties();
		}
		if (classCatalog == null || dataBinding == null) {
			classCatalog = new StoredClassCatalog(myDb);
			dataBinding = new SerialBinding(classCatalog, Properties.class);
		}
		DatabaseEntry theKey = new DatabaseEntry();
		DatabaseEntry theData = new DatabaseEntry();

		dataBinding.objectToEntry(object, theData);
		theKey.setData(key.getBytes());
		myDb.put(null, theKey, theData);
		addCount++;
		if (addCount >= 1000) {
			addCount = 0;
			syncDb();
		}
	}

	/**
	 * 根据主键获取数据
	 * 
	 * @param key
	 * @return
	 * @throws IOException
	 */
	public Properties getRecord(String key) {
		if (key == null) {
			return null;
		}
		DatabaseEntry theKey = new DatabaseEntry((key.getBytes()));
		DatabaseEntry theData = new DatabaseEntry();
		Properties properties = null;
		if (myDb.get(null, theKey, theData, LockMode.DEFAULT) == OperationStatus.SUCCESS) {
			properties = (Properties) dataBinding.entryToObject(theData);
		}
		return properties;
	}

	/**
	 * 获取数据库的名称
	 * 
	 * @return
	 */
	public String getDbname() {
		return dbname;
	}

	/**
	 * 测试方法
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		BerkeleyDB db = new BerkeleyDB("z");
		db.clear();
		db.close();
	}

}
