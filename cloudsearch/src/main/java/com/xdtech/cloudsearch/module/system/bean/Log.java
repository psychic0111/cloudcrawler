package com.xdtech.cloudsearch.module.system.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


/**
 * 系统日志表表结构
 * 
 * @author yuhao
 */
@Entity
@Table(name = "cs_syslog")
public class Log implements Serializable{

	private static final long serialVersionUID = -7149550908972457783L;
	
	/** 主键 */
    private Integer id;
    
    /** 类型*/
    private String action;
    
    /** 操作说明*/
    private String description;
    
    /** 操作指令*/
    private String commond;
    
    /** 操作人ID*/
    private String actionUser;
    
    /** 客户端IP*/
    private String ip;
    
    /** 操作时间*/
    private Date actiontime;
    /** 起始时间    */
	private Date  startTime;
	/** 结束时间  */
	private Date endTime;
    
    @Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(length = 50)
	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	@Column(length = 100)
	public String getDescription() {
		return description;
	}
    
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Column(length = 50)
	public String getCommond() {
		return commond;
	}

	public void setCommond(String commond) {
		this.commond = commond;
	}
	
	public String getActionUser() {
		return actionUser;
	}

	public void setActionUser(String actionUser) {
		this.actionUser = actionUser;
	}

	@Column(length = 50)
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Date getActiontime() {
		return actiontime;
	}

	public void setActiontime(Date actiontime) {
		this.actiontime = actiontime;
	}
	@Transient
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	@Transient
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	} 
    
}
