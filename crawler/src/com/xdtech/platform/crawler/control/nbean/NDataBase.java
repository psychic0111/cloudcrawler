package com.xdtech.platform.crawler.control.nbean;

import java.lang.reflect.Field;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import com.xdtech.platform.crawler.ws.ndispatcher.DataBase;

import org.hibernate.annotations.GenericGenerator;

import com.xdtech.platform.crawler.ws.ndispatcher.Site;

/**
 * 爬虫数据库配置
 * 
 * @author WangWei
 * 2013-06-20
 */
public class NDataBase {
	/**
	 * 主键
	 */
	private String id;
	/**
	 * 数据库类型
	 */
	private String dbtype;
	/**
	 * 数据库连接url
	 */
	private String dburl;
	/**
	 * 数据库ip
	 */
	private String dbip;
	/**
	 * 数据库端口
	 */
	private Integer dbport; 
	/**
	 * 数据库用户名
	 */
	private String dbuser;
	/**
	 * 数据库密码
	 */
	private String dbpassword;
	/**
	 * 数据库名称
	 */
	private String dbname;
	/**
	 * 数据表名称
	 */
	private String tableName;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDbtype() {
		return dbtype;
	}
	public void setDbtype(String dbtype) {
		this.dbtype = dbtype;
	}
	public String getDburl() {
		return dburl;
	}
	public void setDburl(String dburl) {
		this.dburl = dburl;
	}
	public String getDbip() {
		return dbip;
	}
	public void setDbip(String dbip) {
		this.dbip = dbip;
	}
	public Integer getDbport() {
		return dbport;
	}
	public void setDbport(Integer dbport) {
		this.dbport = dbport;
	}
	public String getDbuser() {
		return dbuser;
	}
	public void setDbuser(String dbuser) {
		this.dbuser = dbuser;
	}
	public String getDbpassword() {
		return dbpassword;
	}
	public void setDbpassword(String dbpassword) {
		this.dbpassword = dbpassword;
	}
	public String getDbname() {
		return dbname;
	}
	public void setDbname(String dbname) {
		this.dbname = dbname;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	/**
	 * 给数据库连接属性赋值
	 * 
	 * @param dataBase
	 */
	public void fix(DataBase dataBase) {
		if (dataBase != null) {
			Field[] beanFields = DataBase.class.getDeclaredFields();
			Field[] siteFields = NDataBase.class.getDeclaredFields();
			for (Field siteField : siteFields) {
				for (Field beanField : beanFields) {
					if (beanField.getName().equals(siteField.getName())) {
						beanField.setAccessible(true);
						try {
							siteField.set(this, beanField.get(dataBase));
						} catch (IllegalArgumentException e) {
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
	}
}
