package com.xdtech.cloudsearch.module.sitecrawler.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 爬虫数据库配置
 * 
 * @author WangWei
 * 2013-06-20
 */
@Entity
@Table(name = "xd_crawler_database")
public class DataBase {
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
	 * 操作时间，用于找到最新创建的数据库连接
	 */
	private Date operateTime;
	/**
	 * 数据表名称
	 */
	private String tableName;
	@Id
	@Column(length = 32)
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Column(columnDefinition="varchar(50)")
	public String getDbtype() {
		return dbtype;
	}
	public void setDbtype(String dbtype) {
		this.dbtype = dbtype;
	}
	@Column(columnDefinition="varchar(500)")
	public String getDburl() {
		return dburl;
	}
	public void setDburl(String dburl) {
		this.dburl = dburl;
	}
	@Column(columnDefinition="varchar(20)")
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
	@Column(columnDefinition="varchar(50)")
	public String getDbuser() {
		return dbuser;
	}
	public void setDbuser(String dbuser) {
		this.dbuser = dbuser;
	}
	@Column(columnDefinition="varchar(100)")
	public String getDbpassword() {
		return dbpassword;
	}
	public void setDbpassword(String dbpassword) {
		this.dbpassword = dbpassword;
	}
	@Column(columnDefinition="varchar(50)")
	public String getDbname() {
		return dbname;
	}
	public void setDbname(String dbname) {
		this.dbname = dbname;
	}
	public Date getOperateTime() {
		return operateTime;
	}
	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}
	@Column(columnDefinition="varchar(50)")
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
}
