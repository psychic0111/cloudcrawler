package com.xdtech.cloudsearch.module.system.action;

import java.io.InputStream;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 系统导航管理
 * @author Chang Fei
 *
 */
@RequestMapping("/nav")
@Controller
public class NavAction {
	
	private static String navspath="nav.xml";
	/**导航上下文*/
	private static Document navcontext;
	
	static{
		loadNavContext();
	}
	
	/**
	 * 加载导航上下文
	 */
	private static void loadNavContext() {
		ClassLoader cl = NavAction.class.getClassLoader();
		InputStream in =cl.getResourceAsStream(navspath);
		SAXReader reader = new SAXReader();
		try {
			navcontext=reader.read(in);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 根据ID导航
	 * @param id
	 * @return
	 */
	@RequestMapping("/navTo.do")
	public @ResponseBody() String getNav(String id){
		loadNavContext();
		String xml=navcontext.elementByID(id).asXML();
		xml="<?xml version='1.0' encoding='UTF-8'?>"+xml;
		return xml;
	}
}
