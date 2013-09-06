package com.xdtech.cloudsearch.module.system.bean;

import java.util.List;
import java.util.Map;
/**
 * 日志审计
 * @author wangwenxiang
 *
 */
public class LogAudit {
	 String clazz;   //类
	 String description;  //描述
	 String operate;  //操作项
	 Map<String,String> perporty; //属性
	public String getClazz() {
		return clazz;
	}
	public void setClazz(String clazz) {
		this.clazz = clazz;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getOperate() {
		return operate;
	}
	public void setOperate(String operate) {
		this.operate = operate;
	}
	public Map<String, String> getPerporty() {
		return perporty;
	}
	public void setPerporty(Map<String, String> perporty) {
		this.perporty = perporty;
	}
	 
}
