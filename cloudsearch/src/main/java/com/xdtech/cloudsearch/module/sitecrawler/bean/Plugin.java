package com.xdtech.cloudsearch.module.sitecrawler.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 插件
 * 
 * @author WangWei
 * 2013-06-05
 */
@Entity
@Table(name = "xd_crawler_plugin")
public class Plugin {
	/**
	 * 主键
	 */
	private String id;
	/**
	 * 插件名称
	 */
	private String pluginName;
	/**
	 * 插件类型 值为java或javascript
	 */
	private String pluginType;
	/**
	 * 插件实现类
	 */
	private String implementClass;
	/**
	 * 插件代码
	 */
	private String pluginCode;
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
	public String getPluginName() {
		return pluginName;
	}
	public void setPluginName(String pluginName) {
		this.pluginName = pluginName;
	}
	@Column(length = 20)
	public String getPluginType() {
		return pluginType;
	}
	public void setPluginType(String pluginType) {
		this.pluginType = pluginType;
	}
	@Column(length = 200)
	public String getImplementClass() {
		return implementClass;
	}
	public void setImplementClass(String implementClass) {
		this.implementClass = implementClass;
	}
	@Column(columnDefinition="text")
	public String getPluginCode() {
		return pluginCode;
	}
	public void setPluginCode(String pluginCode) {
		this.pluginCode = pluginCode;
	}

}
