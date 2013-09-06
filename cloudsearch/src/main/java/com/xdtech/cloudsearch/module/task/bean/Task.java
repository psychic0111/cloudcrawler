package com.xdtech.cloudsearch.module.task.bean;

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

import com.xdtech.cloudsearch.module.sitecrawler.bean.Site;

/**
 * 任务表
 * 
 * @author LZF
 * 
 */
@Entity
@Table(name = "xd_crawler_task")
public class Task implements Cloneable {
	/**
	 * 主键
	 */
	private String id;
	/**
	 * 任务名称
	 */
	private String taskName;
	/**
	 * 开始时间-小时
	 */
	private Integer startHour;
	/**
	 * 开始时间-分钟
	 */
	private Integer startMinute;
	/**
	 * 结束时间-小时
	 */
	private Integer endHour;
	/**
	 * 结束时间-分钟
	 */
	private Integer endMinute;
	/**
	 * 执行频率
	 */
	private Integer click;
	/**
	 * 执行频率方式 1 按照小时计算 2 按照分钟计算
	 */
	private Integer clickType;
	/**
	 * 分类IDS
	 */
	private String siteCategories;
	/**
	 * 站点IDS
	 */
	private String sites;
	/**
	 * 是否启用
	 */
	private Integer isUse;
	/**
	 * 操作时间
	 */
	private Date operateTime;
	/**
	 * 操作人
	 */
	private String operateUser;
	/**
	 * 是否采集所有的站点
	 */
	private Integer isAllSites;

	/**
	 * 该任务下需要执行采集的站点
	 */
	private List<Site> siteList = new ArrayList<Site>();
	/**
	 * 结束年
	 */
	private Integer endYear;
	/**
	 * 任务的执行类型，1 按周执行，2 按天执行
	 */
	private int execType;

	/**
	 * 如果是按周执行，则要记录是周几执行 1 周一 2 周二 3 周三 4 周四 5 周五 6 周六 7 周日
	 */
	private String weekDays;

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

	@Column(length = 50)
	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public Integer getClick() {
		return click;
	}

	public void setClick(Integer click) {
		this.click = click;
	}

	@Column(length = 300)
	public String getSiteCategories() {
		return siteCategories;
	}

	public void setSiteCategories(String siteCategories) {
		this.siteCategories = siteCategories;
	}

	@Column(length = 300)
	public String getSites() {
		return sites;
	}

	public void setSites(String sites) {
		this.sites = sites;
	}

	@Column(length = 10)
	public Integer getIsUse() {
		return isUse;
	}

	public void setIsUse(Integer isUse) {
		this.isUse = isUse;
	}

	public Date getOperateTime() {
		return operateTime;
	}

	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}

	@Column(length = 32)
	public String getOperateUser() {
		return operateUser;
	}

	public void setOperateUser(String operateUser) {
		this.operateUser = operateUser;
	}

	@Column(length = 10)
	public Integer getIsAllSites() {
		return isAllSites;
	}

	public void setIsAllSites(Integer isAllSites) {
		this.isAllSites = isAllSites;
	}

	@Transient
	public List<Site> getSiteList() {
		return siteList;
	}

	public void setSiteList(List<Site> siteList) {
		this.siteList = siteList;
	}

	public Integer getStartHour() {
		return startHour;
	}

	public void setStartHour(Integer startHour) {
		this.startHour = startHour;
	}

	public Integer getEndYear() {
		return endYear;
	}

	public void setEndYear(Integer endYear) {
		this.endYear = endYear;
	}

	public Integer getStartMinute() {
		return startMinute;
	}

	public void setStartMinute(Integer startMinute) {
		this.startMinute = startMinute;
	}

	public Integer getEndHour() {
		return endHour;
	}

	public void setEndHour(Integer endHour) {
		this.endHour = endHour;
	}

	public Integer getEndMinute() {
		return endMinute;
	}

	public void setEndMinute(Integer endMinute) {
		this.endMinute = endMinute;
	}

	public Integer getClickType() {
		return clickType;
	}

	public void setClickType(Integer clickType) {
		this.clickType = clickType;
	}
	public int getExecType() {
		return execType;
	}

	public void setExecType(int execType) {
		this.execType = execType;
	}

	public String getWeekDays() {
		return weekDays;
	}

	public void setWeekDays(String weekDays) {
		this.weekDays = weekDays;
	}
	public Task simpleCloneNewObj() {
		Task task = new Task();
		task.setClick(click);
		task.setClickType(clickType);
		task.setEndHour(endHour);
		task.setEndMinute(endMinute);
		task.setEndYear(endYear);
		task.setId(id);
		task.setIsAllSites(isAllSites);
		task.setIsUse(isUse);
		task.setOperateTime(operateTime);
		task.setOperateUser(operateUser);
		task.setSiteCategories(siteCategories);
		task.setSiteList(new ArrayList<Site>());
		task.setSites(sites);
		task.setStartHour(startHour);
		task.setStartMinute(startMinute);
		task.setTaskName(taskName);
		task.setExecType(execType);
		task.setWeekDays(weekDays);
		return task;
	}

}
