package com.xdtech.cloudsearch.module.sitecrawler.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 插件参数
 * 
 * @author WangWei
 * 2013-06-05
 */
@Entity
@Table(name = "xd_crawler_pluginParam")
public class PluginParam {
	/**
	 * 主键
	 */
	private String id;
	/**
	 * 插件主键
	 */
	private String pluginId;
	/**
	 * 参数名称
	 */
	private String paramName;
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
	@Column(length = 32)
	public String getPluginId() {
		return pluginId;
	}
	public void setPluginId(String pluginId) {
		this.pluginId = pluginId;
	}
	@Column(length = 20)
	public String getParamName() {
		return paramName;
	}
	public void setParamName(String paramName) {
		this.paramName = paramName;
	}
	@Column(length = 200)
	public String getParamValue() {
		return paramValue;
	}
	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}
	/**
	 * 参数值
	 */
	private String paramValue;
	

}
