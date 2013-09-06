package com.xdtech.platform.util;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class RegularSelectHelper {
	/**
	 * 
	 * @param content html的内容
	 * @param doc 要转换的html文档
	 * @param rule 规则
	 * @param iscontent 取值，为true时，取文本数据，为false时，取html文本
	 * @return
	 */
	public static Set<String> getValues(String content, Document doc, String rule,boolean iscontent) {
		Set<String> values = null;
		if (null != rule && !"".equals(rule)) {
			values = new HashSet<String>();
			//解决当配置为多个正则表达式和选择器时，通过回车键进行分割
			String[] titleRules = rule.split("\n");
			for(String testRule:titleRules){
				testRule = testRule.trim();
				if (testRule.startsWith("~")) {
					// 正则
					values = getValuesByReg(content, rule);
				} else {
					// 选择器
					values = getValuesBySelect(doc, rule,iscontent);
				}
				if(null!=values&&!values.isEmpty()){
					break;
				}
			}
		}
		return values;
	}
	/**
	 * 
	 * @param content html的内容
	 * @param doc 要转换的html文档
	 * @param rule 规则
	 * @param iscontent 取值，为true时，取文本数据，为false时，取html文本
	 * @return
	 */
	public static String getValue(String content, Document doc, String rule,boolean iscontent) {
		String value = "";
		if (null != rule && !"".equals(rule)) {
			//解决当配置为多个正则表达式和选择器时，通过回车键进行分割
			String[] titleRules = rule.split("\n");
			for(String testRule:titleRules){
				testRule = testRule.trim();
				if (testRule.startsWith("~")) {
					// 正则
					value = getValueByReg(content, testRule);
				} else {
					// 选择器
					value = getValueBySelect(doc, testRule,iscontent);
				}
				if(!"".equals(value)){
					break;
				}
			}
		}
		return value;
	}
	/**
	 * 根据选择器进行抽取数据
	 * @param doc 要转换的html文档
	 * @param rule 规则
	 * @param iscontent 取值，为true时，取文本数据，为false时，取html文本
	 * @return
	 */
	public static String getValueBySelect(Document doc, String titleRule,boolean iscontent) {
		String value = "";
		Elements eles = doc.select(titleRule);
		if(!eles.isEmpty()){
			Element e = eles.get(0);
			value = getValueFromDoc(e,iscontent);
		}
		return value;
	}
	/**
	 * 通过正则表达式获取数据，返回一个String的值
	 * @param content html的文档内容
	 * @param reg 正则表达式
	 * @return
	 */
	public static String getValueByReg(String content, String reg) {
		String value = "";
		if (null != content && !"".equals(content) && null != reg && !"".equals(reg)) {
			Pattern p = Pattern.compile(reg.substring(1), Pattern.CASE_INSENSITIVE);
			Matcher m = p.matcher(content);
			if (m.find()) {
				value = m.group(1);
			}
		}
		return value;
	}
	/**
	 * 验证内容是否符合正则表达式或者内容是否包含字符串
	 * @param content html的文档内容
	 * @param reg 正则表达式
	 * @return
	 */
	public static boolean findValueByRegOrInclude(String content, String reg) {
		boolean value = false;
		if(reg.startsWith("~")){
			if (null != content && !"".equals(content) && null != reg && !"".equals(reg)) {
				Pattern p = Pattern.compile(reg.substring(1), Pattern.CASE_INSENSITIVE);
				Matcher m = p.matcher(content);
				value = m.find();
			}
		}else{
			if(content.indexOf(reg) > -1){
				value = true;
			}
		}
		return value;
	}

	/**
	 * 通过正则表达式获取数据，返回一个集合
	 * @param content html的文档内容
	 * @param reg 正则表达式
	 * @return
	 */
	public static Set<String> getValuesByReg(String content, String reg) {
		String value = "";
		Set<String> urls = new HashSet<String>();
		Pattern p = Pattern.compile(reg.substring(1), Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(content);
		while (m.find()) {
			value = m.group(1);
			urls.add(value);
		}
		return urls;
	}
	/**
	 * 
	 * @param doc 要转换的html文档
	 * @param rule 规则
	 * @param iscontent 取值，为true时，取文本数据，为false时，取html文本
	 * @return
	 */
	public static Set<String> getValuesBySelect(Document doc, String rule,boolean iscontent) {
		Set<String> values = null;
		Elements eles = doc.select(rule);
		if(!eles.isEmpty()){
			values = new HashSet<String>();
			for (Element e : eles) {
				String value = getValueFromDoc(e,iscontent);
				values.add(value);
			}
		}
		return values;
	}
	/**
	 * 根据文档元素和取值方式进行取值
	 * @param e 文档节点
	 * @param iscontent 取值，为true时，取文本数据，为false时，取html文本
	 * @return
	 */
	public static String getValueFromDoc(Element e,boolean iscontent){
		String value = "";
		if(e.tagName().equals("a")){
			if(iscontent){
				value = e.html();
			}else{
				value = e.attr("href");
			}
		}else{
			if(iscontent){
				value = e.text();
			}else{
				value = e.html();
			}
		}
		return value;
	}
	/**
	 * 判断该内容是否符后面的正则
	 * @param content 要判断的内容
	 * @param reg 要符合的正则
	 * @return
	 */
	public static boolean getValueFromReg(String content, String reg){
		Pattern p = Pattern.compile(reg.substring(1), Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(content);
		return m.find();
	}

}
