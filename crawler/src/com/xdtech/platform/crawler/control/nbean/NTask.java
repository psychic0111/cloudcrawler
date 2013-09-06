package com.xdtech.platform.crawler.control.nbean;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.xdtech.platform.crawler.ws.ndispatcher.Task;


public class NTask {
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
	 * 结束年
	 */
	private Integer endYear;
	/**
	 * 最后一次执行任务的时间
	 */
	private Date urlGenerateDate; 
	
	/**
	 * 任务的执行类型，1 按周执行，2 按天执行
	 */
	private int execType;

	/**
	 * 如果是按周执行，则要记录是周几执行 1 周一 2 周二 3 周三 4 周四 5 周五 6 周六 7 周日
	 */
	private String weekDays;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public Integer getStartHour() {
		return startHour;
	}
	public void setStartHour(Integer startHour) {
		this.startHour = startHour;
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
	public Integer getClick() {
		return click;
	}
	public void setClick(Integer click) {
		this.click = click;
	}
	public Integer getClickType() {
		return clickType;
	}
	public void setClickType(Integer clickType) {
		this.clickType = clickType;
	}
	public String getSiteCategories() {
		return siteCategories;
	}
	public void setSiteCategories(String siteCategories) {
		this.siteCategories = siteCategories;
	}
	public String getSites() {
		return sites;
	}
	public void setSites(String sites) {
		this.sites = sites;
	}
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
	public String getOperateUser() {
		return operateUser;
	}
	public void setOperateUser(String operateUser) {
		this.operateUser = operateUser;
	}
	public Integer getIsAllSites() {
		return isAllSites;
	}
	public void setIsAllSites(Integer isAllSites) {
		this.isAllSites = isAllSites;
	}
	public Integer getEndYear() {
		return endYear;
	}
	public void setEndYear(Integer endYear) {
		this.endYear = endYear;
	}
	public Date getUrlGenerateDate() {
		return urlGenerateDate;
	}
	public void setUrlGenerateDate(Date urlGenerateDate) {
		this.urlGenerateDate = urlGenerateDate;
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
	/**
	 * 站点赋值
	 * 
	 * @param site
	 */
	public void fix(Task taskBean) {
		if (taskBean != null) {
			Field[] beanFields = Task.class.getDeclaredFields();
			Field[] siteFields = NTask.class.getDeclaredFields();
			for (Field siteField : siteFields) {
				for (Field beanField : beanFields) {
					if (beanField.getName().equals(siteField.getName())) {
						beanField.setAccessible(true);
						try {
							if("operateTime".equals(beanField.getName())){
								SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
								String date = beanField.get(taskBean).toString().replace("T", " ").substring(0,19);
								try {
									siteField.set(this, format.parse(date));
								} catch (ParseException e) {
									siteField.set(this,new Date());
								}
							}else{
								siteField.set(this, beanField.get(taskBean));
							}
						} catch (IllegalArgumentException e) {
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							e.printStackTrace();
						}
					}
				}
			}
			this.setUrlGenerateDate(new Date());
		}
	}
	
}
