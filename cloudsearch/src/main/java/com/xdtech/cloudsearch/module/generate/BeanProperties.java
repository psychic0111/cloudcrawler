package com.xdtech.cloudsearch.module.generate;
/**
 * javabean的属性
 * @author Administrator
 *
 */
public class BeanProperties {
	/**
	 * 属性名称
	 */
	private String propertiesName;
	/**
	 * 属性类型
	 */
	private String propertiesType;
	/**
	 * 属性长度
	 */
	private Integer propertiesLength;
	/**
	 * 将属性名称首字母大写
	 */
	private String FirstGigPropertiesName;
	public String getPropertiesName() {
		return propertiesName;
	}
	public void setPropertiesName(String propertiesName) {
		this.propertiesName = propertiesName;
	}
	public String getPropertiesType() {
		return propertiesType;
	}
	public void setPropertiesType(String propertiesType) {
		this.propertiesType = propertiesType;
	}
	public Integer getPropertiesLength() {
		return propertiesLength;
	}
	public void setPropertiesLength(Integer propertiesLength) {
		this.propertiesLength = propertiesLength;
	}
	public String getFirstGigPropertiesName() {
		return FirstGigPropertiesName;
	}
	public void setFirstGigPropertiesName(String firstGigPropertiesName) {
		FirstGigPropertiesName = firstGigPropertiesName;
	}

}
