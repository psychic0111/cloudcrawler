package com.xdtech.cloudsearch.web.listener;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.event.PostDeleteEvent;
import org.hibernate.event.PostDeleteEventListener;
import org.hibernate.event.PostInsertEvent;
import org.hibernate.event.PostInsertEventListener;
import org.hibernate.event.PostUpdateEvent;
import org.hibernate.event.PostUpdateEventListener;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import com.xdtech.cloudsearch.module.system.bean.Log;
import com.xdtech.cloudsearch.module.system.bean.LogAudit;


public class LogAuditListener implements PostInsertEventListener,PostUpdateEventListener, PostDeleteEventListener {    
	private static String INSERT="添加";
	private static String UPDATE="修改";
	private static String DELETE="删除";
	private static Map<String,LogAudit> logAuditMap=new HashMap<String, LogAudit>();
	/**
	 * 添加操作   日志
	 */
	public void onPostInsert(PostInsertEvent event) {   
		Class<?> clazz = event.getEntity().getClass();
		try {
			Log log=null;
			if (logAuditMap.size() > 0) {
				LogAudit logAudit=logAuditMap.get(clazz.getSimpleName());
				if (logAudit != null) {
			    	log=newLog(logAudit,INSERT);
			    	String content = INSERT + logAudit.getDescription();//操作说明
			    	Object obj = "";  //操作人
			    	try {
			    		Method m = clazz.getMethod("getOperateUser", null);  //获取操作人
						obj = m.invoke(event.getEntity(), null);
					} catch (Exception e) {
						try {
							Method m = clazz.getMethod("getLastUpdateUser", null);  //获取操作人
							obj = m.invoke(event.getEntity(), null);
						}catch(Exception e1){
							try {
								Method m = clazz.getMethod("getUsername", null);  //获取操作人
								obj = m.invoke(event.getEntity(), null);
							}catch(Exception e2){
							}
						}
					}
					Object[] newState = event.getState();
					String[] fields = event.getPersister().getPropertyNames();
					if (newState != null && fields != null && newState.length == fields.length ) {
						for (int i = 0; i < fields.length; i++) {
							if(logAudit.getPerporty().containsKey(fields[i])){
								content += "\""+logAudit.getPerporty().get(fields[i])+ "\"  "+newState[i];
							}
						}
					}
					log.setDescription(content);
					log.setActionUser(obj!=null?obj.toString():null);
					saveOperate(event.getSession(),log);
			    }
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}      
	/**
	 * 修改操作  日志   
	 */
	public void onPostUpdate(PostUpdateEvent event) {  
		Class<?> clazz = event.getEntity().getClass();
		try {
			Log log=null;
			if (logAuditMap.size() > 0) {
				LogAudit logAudit=logAuditMap.get(clazz.getSimpleName());
				if (logAudit != null) {
						log =newLog(logAudit,UPDATE);
						String content = UPDATE + logAudit.getDescription(); //操作说明
						Object obj = "";  //操作人
				    	try {
				    		Method m = clazz.getMethod("getOperateUser", null);  //获取操作人
							obj = m.invoke(event.getEntity(), null);
						} catch (Exception e) {
							try {
								Method m = clazz.getMethod("getLastUpdateUser", null);  //获取操作人
								obj = m.invoke(event.getEntity(), null);
							}catch(Exception e1){
								try {
									Method m = clazz.getMethod("getUsername", null);  //获取操作人
									obj = m.invoke(event.getEntity(), null);
								}catch(Exception e2){
								}
							}
						}
						log.setActionUser(obj != null ? obj.toString() : null);
						Object[] oldState = event.getOldState();  //获取旧值
						Object[] newState = event.getState();		//获取更改后的值
						String[] fields = event.getPersister().getPropertyNames();  //获取对象属性
						if("User".equals(clazz.getSimpleName())){ //当对象是用用户时      将用户 单独拿出来    判断用户登录和修改密码
							if(oldState != null && newState != null && fields != null && oldState.length == newState.length && oldState.length == fields.length){
								boolean ref=false; //是否为登录操作
								for (int i = 0; i < fields.length; i++) {
									if(fields[i]=="lastlogintime" && oldState[i]!=newState[i]){ //登录操作  最后一次登录时间被修改
										ref=false;break ;
									}else if(fields[i]=="password" && oldState[i]!=newState[i]){ //登录操作  最后一次登录时间被修改
										content = "修改密码";
										ref=true;
									}else if(logAudit.getPerporty().containsKey(fields[i])){
										content += "{将  \"" + logAudit.getPerporty().get(fields[i]) + "\"  改为    \"" + String.valueOf(newState[i]) + "\"}";
										ref=true;
									}
								}
								if(ref){
									log.setDescription(content);
									saveOperate(event.getSession(), log);
								}
							}
						}else if (oldState != null && newState != null && fields != null && oldState.length == newState.length && oldState.length == fields.length ) {
							for (int i = 0; i < fields.length; i++) {
								if(logAudit.getPerporty().containsKey(fields[i])){
									content += "{将  \"" + logAudit.getPerporty().get(fields[i]) + "\"  改为    \"" + String.valueOf(newState[i]) + "\"}";
								}
							}
							log.setDescription(content);
							saveOperate(event.getSession(), log);
						}
					}
				}
		} catch (Exception e) {
			e.printStackTrace();
		}    
	}      
	/**
	 * 删除操作     日志           
	 */
	public void onPostDelete(PostDeleteEvent event) {      
		Class<?> clazz = event.getEntity().getClass();
		try {
			Log log=null;
			if (logAuditMap.size() > 0) {
				LogAudit logAudit=logAuditMap.get(clazz.getSimpleName());
				if (logAudit != null) {
				    	log=newLog(logAudit,DELETE);
				    	log.setDescription(DELETE+logAudit.getDescription());
				    	Object obj = "";  //操作人
				    	try {
				    		Method m = clazz.getMethod("getOperateUser", null);  //获取操作人
							obj = m.invoke(event.getEntity(), null);
						} catch (Exception e) {
							try {
								Method m = clazz.getMethod("getLastUpdateUser", null);  //获取操作人
								obj = m.invoke(event.getEntity(), null);
							}catch(Exception e1){
								try {
									Method m = clazz.getMethod("getUsername", null);  //获取操作人
									obj = m.invoke(event.getEntity(), null);
								}catch(Exception e2){
								}
							}
						}
						log.setActionUser(obj!=null?obj.toString():null);
						saveOperate(event.getSession(),log);
				    }
				} 
		} catch (Exception e) {
			e.printStackTrace();
		}  
	}
	
	
	public Log newLog(LogAudit audit,String operation){
		Log log=new Log();
    	log.setAction(operation);  
    	log.setCommond(audit.getOperate());
    	log.setActiontime(new Date());
		return log;
	}
	/**
	 * 生成日志
	 * @param session
	 * @param entry
	 */
	private void saveOperate(Session session, Log entry) {
        Session tempSession = session.getSessionFactory().openSession();
        Transaction tx = tempSession.beginTransaction();
        try {
            tx.begin();
            tempSession.save(entry);
            tempSession.flush();
            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
        }
        tempSession.close();
    }
	/**
	 * 读取需做日志记录的XML配置文件
	 * @return
	 */
	
	
	static{	
		long start = System.currentTimeMillis();
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = dbf.newDocumentBuilder();
			Document doc = builder.parse(LogAudit.class.getClassLoader().getResourceAsStream("auditLog.xml")); // 获取到xml文件
			Element root = doc.getDocumentElement(); // 获取根元素
			NodeList logAudits = root.getElementsByTagName("entity");
			LogAudit log =null;
			for (int i = 0; i < logAudits.getLength(); i++) {
				Element ss = (Element) logAudits.item(i);
				log= new LogAudit();
				log.setClazz(ss.getAttribute("clazz"));  			//实体类
				log.setDescription(ss.getAttribute("description"));  //操作说明
				log.setOperate(ss.getAttribute("operate"));   		//操作项
				NodeList propertys = ss.getElementsByTagName("property");//属性
				Map<String,String> proMap=new HashMap<String,String>();
				for(int j = 0; j < propertys.getLength(); j++){
					Element e = (Element) propertys.item(j);
					proMap.put(e.getAttribute("name"), e.getTextContent());
				}
				log.setPerporty(proMap);
				logAuditMap.put(ss.getAttribute("clazz"), log);
			}
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
