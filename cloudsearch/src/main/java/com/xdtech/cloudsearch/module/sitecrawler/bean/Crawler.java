package com.xdtech.cloudsearch.module.sitecrawler.bean;

import java.util.ArrayList;
import java.util.Date;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.xdtech.cloudsearch.module.task.bean.Task;

/**
 * 爬虫
 * 
 * @author WangWei
 * 2013-06-20
 */
@Entity
@Table(name = "xd_crawler_crawler")
public class Crawler {
	/**
	 * 主键
	 */
	private String id;
	/**
	 * 爬虫名称
	 */
	private String name;
	/**
	 * 爬虫编号
	 */
	private String code;
	/**
	 * 采集线程
	 */
	private Integer crawlerThread;
	/**
	 * 下载线程
	 */
	private Integer downloadThread;
	/**
	 * 单个站点下载线程
	 */
	private Integer siteThread;
	/**
	 * 是否保存快照 1 是 0 否
	 */
	private Integer saveSnap;
	/**
	 * 是否追踪爬虫
	 */
	private Integer traceCrawler;
	/**
	 * 是否下载图片
	 */
	private Integer downImage;
	/**
	 * 图片存储目录
	 */
	private String imagePath;
	/**
	 * 数据保存目录
	 */
	private String dataPath;
	/**
	 * 使用状态 1 已启用 0 未启用
	 */
	private Integer status;
	/**
	 * 爬虫类型
	 */
	private String crawlerType;
	/**
	 * 数据存储方式 1 数据库存储 2 文件存储
	 */
	private Integer saveMode;
	/**
	 * 数据库信息
	 */
	private String databaseId;
	/**
	 * 操作人
	 */
	private String operateUser;
	/**
	 * 操作时间
	 */
	private Date operateTime;
	/**
	 * 爬行状态 1 已停止 2 运行中 3 停止中
	 */
	private Integer crawlerStatus;
	/**
	 * 下载图片最小限制
	 */
	private Integer imageSize;
	
	/**
	 * 该爬虫要爬取的站点
	 */
	private List<Task> tasks = new ArrayList<Task>();
	/**
	 * 爬虫访问地址
	 */
	private String crawlerAddress;
	/**
	 * 爬虫是否死掉 1 爬虫正常运行 0 爬虫死掉
	 */
	private Integer isDeath;
	/**
	 * 自动连接次数
	 */
	private Integer autoConnects;
	/**
	 * 数据库连接信息
	 */
	private DataBase dataBase;
	
	
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(columnDefinition="varchar(50)")
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Integer getCrawlerThread() {
		return crawlerThread;
	}
	public void setCrawlerThread(Integer crawlerThread) {
		this.crawlerThread = crawlerThread;
	}
	public Integer getDownloadThread() {
		return downloadThread;
	}
	public void setDownloadThread(Integer downloadThread) {
		this.downloadThread = downloadThread;
	}
	public Integer getSiteThread() {
		return siteThread;
	}
	public void setSiteThread(Integer siteThread) {
		this.siteThread = siteThread;
	}
	public Integer getSaveSnap() {
		return saveSnap;
	}
	public void setSaveSnap(Integer saveSnap) {
		this.saveSnap = saveSnap;
	}
	public Integer getTraceCrawler() {
		return traceCrawler;
	}
	public void setTraceCrawler(Integer traceCrawler) {
		this.traceCrawler = traceCrawler;
	}
	public Integer getDownImage() {
		return downImage;
	}
	public void setDownImage(Integer downImage) {
		this.downImage = downImage;
	}
	@Column(columnDefinition="varchar(100)")
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	@Column(columnDefinition="varchar(100)")
	public String getDataPath() {
		return dataPath;
	}
	public void setDataPath(String dataPath) {
		this.dataPath = dataPath;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	@Column(columnDefinition="varchar(50)")
	public String getCrawlerType() {
		return crawlerType;
	}
	public void setCrawlerType(String crawlerType) {
		this.crawlerType = crawlerType;
	}
	public Integer getSaveMode() {
		return saveMode;
	}
	public void setSaveMode(Integer saveMode) {
		this.saveMode = saveMode;
	}
	public String getDatabaseId() {
		return databaseId;
	}
	public void setDatabaseId(String databaseId) {
		this.databaseId = databaseId;
	}
	@Column(columnDefinition="varchar(32)")
	public String getOperateUser() {
		return operateUser;
	}
	public void setOperateUser(String operateUser) {
		this.operateUser = operateUser;
	}
	public Date getOperateTime() {
		return operateTime;
	}
	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}
	public Integer getCrawlerStatus() {
		return crawlerStatus;
	}
	public void setCrawlerStatus(Integer crawlerStatus) {
		this.crawlerStatus = crawlerStatus;
	}
	public Integer getImageSize() {
		return imageSize;
	}
	public void setImageSize(Integer imageSize) {
		this.imageSize = imageSize;
	}
	@Transient
	public List<Task> getTasks() {
		return tasks;
	}
	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}
	@Column(columnDefinition="varchar(500)")
	public String getCrawlerAddress() {
		return crawlerAddress;
	}
	public void setCrawlerAddress(String crawlerAddress) {
		this.crawlerAddress = crawlerAddress;
	}
	public Integer getIsDeath() {
		return isDeath;
	}
	public void setIsDeath(Integer isDeath) {
		this.isDeath = isDeath;
	}
	public Integer getAutoConnects() {
		return autoConnects;
	}
	public void setAutoConnects(Integer autoConnects) {
		this.autoConnects = autoConnects;
	}
	@Transient
	public DataBase getDataBase() {
		return dataBase;
	}
	public void setDataBase(DataBase dataBase) {
		this.dataBase = dataBase;
	}
}
