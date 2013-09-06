package com.xdtech.cloudsearch.module.generate;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import com.thoughtworks.xstream.io.json.JsonWriter.Format;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;
public class GenerateBean {
	//模板文件路径-javebean
	private static String navspathBean="template/Keyword.ftl";
	//模板文件路径-service
	private static String navspathService="template/KeywordService.ftl";
	//模板文件路径-action
	private static String navspathAction="template/KeywordAction.ftl";
	//模板文件路径-添加
	private static String navspath="template/add.ftl";
	//模板文件路径-列表
	private static String navspathList="template/list.ftl";
	//模板文件路径-入口页面
	private static String navspathIndex="template/index.ftl";
	//生成文件保存路径
	private static String successpath="C:\\Users\\Administrator\\Desktop\\outputfile\\";
	//生成的javabean名称
	private static String beanName = "CrawlerLog";
	//生成的javabean功能描述
	private static String beanNamedep = "日志管理";
	//对应数据库中的表
	private static String tableName = "xd_crawler_log";
	private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	//创建时间
	private static String createDate = format.format(new Date());
	//创建人
	private static String createUser = "WangWei";
	//包名
	private static String beanpackage = "com.xdtech.cloudsearch.module.webcrawler";
	public static void main(String[] args)throws Exception {
		List<BeanProperties> beanProperties = getClumns();
		//生成javabean
		generateBean(beanProperties);
		//生成service
		generateService();
		//生成action
		generateAction();
		//生成添加页面
		generateAddJsp(beanProperties);
		//生成类别页面
		generateListJsp(beanProperties);
		//生成入口页面
		generateIndexJsp(beanProperties);
	}
	/**
	 * 生成 javabean
	 * @param beanProperties
	 */
	public static void generateBean(List<BeanProperties> beanProperties){
		ClassLoader cl = GenerateBean.class.getClassLoader();
		try{
			OutputStreamWriter out = null;
			File templateFile = new File(cl.getResource(navspathBean).toString().replace("file:/", ""));
			Configuration cfg = prepareConfiguration(templateFile.getParent());
			Template template =  cfg.getTemplate(templateFile.getName(), "utf-8");
			Map root = new HashMap();
			root.put("ClassName", beanName);
			root.put("beanProperties", beanProperties);
			root.put("beanNamedep", beanNamedep);
			root.put("tableName", tableName);
			root.put("createUser", createUser);
			root.put("createDate", createDate);
			root.put("beanpackage", beanpackage);
			String fileOut = successpath + beanName+".java";
			File file = new File(fileOut);
			if(!file.getParentFile().exists()){
				file.getParentFile().mkdirs();
			}
			out = new OutputStreamWriter(new FileOutputStream(new File(fileOut)), "utf-8");
			try {
				template.process(root, out);
			} catch (TemplateException e) {
				e.printStackTrace();
			}
			out.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * 生成 Service
	 */
	public static void generateService(){
		ClassLoader cl = GenerateBean.class.getClassLoader();
		try{
			OutputStreamWriter out = null;
			File templateFile = new File(cl.getResource(navspathService).toString().replace("file:/", ""));
			Configuration cfg = prepareConfiguration(templateFile.getParent());
			Template template =  cfg.getTemplate(templateFile.getName(), "utf-8");
			Map root = new HashMap();
			root.put("ClassName", beanName);
			root.put("fristSmallClassName", beanName.toLowerCase());
			root.put("beanNamedep", beanNamedep);
			root.put("tableName", tableName);
			root.put("createUser", createUser);
			root.put("createDate", createDate);
			root.put("beanpackage", beanpackage);
			String fileOut = successpath + beanName+"Service.java";
			File file = new File(fileOut);
			if(!file.getParentFile().exists()){
				file.getParentFile().mkdirs();
			}
			out = new OutputStreamWriter(new FileOutputStream(new File(fileOut)), "utf-8");
			try {
				template.process(root, out);
			} catch (TemplateException e) {
				e.printStackTrace();
			}
			out.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * 生成 Action
	 */
	public static void generateAction(){
		ClassLoader cl = GenerateBean.class.getClassLoader();
		try{
			OutputStreamWriter out = null;
			File templateFile = new File(cl.getResource(navspathAction).toString().replace("file:/", ""));
			Configuration cfg = prepareConfiguration(templateFile.getParent());
			Template template =  cfg.getTemplate(templateFile.getName(), "utf-8");
			Map root = new HashMap();
			root.put("ClassName", beanName);
			root.put("fristSmallClassName", beanName.toLowerCase());
			root.put("beanNamedep", beanNamedep);
			root.put("tableName", tableName);
			root.put("createUser", createUser);
			root.put("createDate", createDate);
			root.put("beanpackage", beanpackage);
			String fileOut = successpath + beanName+"Action.java";
			File file = new File(fileOut);
			if(!file.getParentFile().exists()){
				file.getParentFile().mkdirs();
			}
			out = new OutputStreamWriter(new FileOutputStream(new File(fileOut)), "utf-8");
			try {
				template.process(root, out);
			} catch (TemplateException e) {
				e.printStackTrace();
			}
			out.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * 生成添加页面
	 * @param beanProperties
	 * @throws IOException
	 */
	private static void generateAddJsp(List<BeanProperties> beanProperties) throws IOException {
		ClassLoader cl = GenerateBean.class.getClassLoader();
		try{
			OutputStreamWriter out = null;
			File templateFile = new File(cl.getResource(navspath).toString().replace("file:/", ""));
			Configuration cfg = prepareConfiguration(templateFile.getParent());
			Template template =  cfg.getTemplate(templateFile.getName(), "utf-8");
			Map root = new HashMap();
			root.put("ClassName", beanName);
			root.put("fristSmallClassName", beanName.toLowerCase());
			root.put("beanProperties", beanProperties);
			root.put("beanNamedep", beanNamedep);
			root.put("tableName", tableName);
			root.put("createUser", createUser);
			root.put("createDate", createDate);
			root.put("beanpackage", beanpackage);
			String fileOut = successpath + "add.jsp";
			File file = new File(fileOut);
			if(!file.getParentFile().exists()){
				file.getParentFile().mkdirs();
			}
			out = new OutputStreamWriter(new FileOutputStream(new File(fileOut)), "utf-8");
			try {
				template.process(root, out);
			} catch (TemplateException e) {
				e.printStackTrace();
			}
			out.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * 生成列表页面
	 * @param beanProperties
	 * @throws IOException
	 */
	private static void generateListJsp(List<BeanProperties> beanProperties) throws IOException {
		ClassLoader cl = GenerateBean.class.getClassLoader();
		try{
			OutputStreamWriter out = null;
			File templateFile = new File(cl.getResource(navspathList).toString().replace("file:/", ""));
			Configuration cfg = prepareConfiguration(templateFile.getParent());
			Template template =  cfg.getTemplate(templateFile.getName(), "utf-8");
			Map root = new HashMap();
			root.put("ClassName", beanName);
			root.put("fristSmallClassName", beanName.toLowerCase());
			root.put("beanProperties", beanProperties);
			root.put("beanNamedep", beanNamedep);
			root.put("tableName", tableName);
			root.put("createUser", createUser);
			root.put("createDate", createDate);
			root.put("beanpackage", beanpackage);
			String fileOut = successpath + "list.jsp";
			File file = new File(fileOut);
			if(!file.getParentFile().exists()){
				file.getParentFile().mkdirs();
			}
			out = new OutputStreamWriter(new FileOutputStream(new File(fileOut)), "utf-8");
			try {
				template.process(root, out);
			} catch (TemplateException e) {
				e.printStackTrace();
			}
			out.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * 生成入口页面
	 * @param beanProperties
	 * @throws IOException
	 */
	private static void generateIndexJsp(List<BeanProperties> beanProperties) throws IOException {
		ClassLoader cl = GenerateBean.class.getClassLoader();
		try{
			OutputStreamWriter out = null;
			File templateFile = new File(cl.getResource(navspathIndex).toString().replace("file:/", ""));
			Configuration cfg = prepareConfiguration(templateFile.getParent());
			Template template =  cfg.getTemplate(templateFile.getName(), "utf-8");
			Map root = new HashMap();
			root.put("ClassName", beanName);
			root.put("fristSmallClassName", beanName.toLowerCase());
			root.put("beanProperties", beanProperties);
			root.put("beanNamedep", beanNamedep);
			root.put("tableName", tableName);
			root.put("createUser", createUser);
			root.put("createDate", createDate);
			root.put("beanpackage", beanpackage);
			String fileOut = successpath + "index.jsp";
			File file = new File(fileOut);
			if(!file.getParentFile().exists()){
				file.getParentFile().mkdirs();
			}
			out = new OutputStreamWriter(new FileOutputStream(new File(fileOut)), "utf-8");
			try {
				template.process(root, out);
			} catch (TemplateException e) {
				e.printStackTrace();
			}
			out.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * 准备Configuration
	 * 
	 * @return
	 * @throws IOException
	 */
	private static Configuration prepareConfiguration(String inPath) throws IOException {
		Configuration cfg = new Configuration();
		String url = inPath;
		// 设置模板文件夹
		cfg.setNumberFormat("#");//设置数据输出格式
		cfg.setDirectoryForTemplateLoading(new File(url));
		cfg.setObjectWrapper(new DefaultObjectWrapper());
		cfg.setDefaultEncoding("utf-8");
		cfg.setEncoding(Locale.CHINA, "utf-8");
		cfg.setOutputEncoding("utf-8");
		return cfg;
	}
	/**
	 * 根据配置文件，获取数据库连接
	 * @return
	 */
	public static Connection getConnection(){
		//驱动程序名
		String driver = "";
		// URL指向要访问的数据库名scutcs
		String url = "";
		// MySQL配置时的用户名
		String user = "";
		// Java连接MySQL配置时的密码
		String password = "";
		try{
			String dbconfig = "dbconfig.properties";
			ClassLoader cl = GenerateBean.class.getClassLoader();
			InputStream in = cl.getSystemResourceAsStream(dbconfig);
			Properties properties = new Properties();
			properties.load(in);
			driver = properties.getProperty("driver");
			url = properties.getProperty("url");
			user = properties.getProperty("user");
			password = properties.getProperty("password");
		}catch(Exception e){
			e.printStackTrace();
		}
		Connection conn = null;
		try {
			// 加载驱动程序
			Class.forName(driver);
			// 连续数据库
			conn = DriverManager.getConnection(url, user, password);
		}catch(Exception e){
			e.printStackTrace();
		}
		return conn;
	}
	public static List<BeanProperties> getClumns(){
		List<BeanProperties> beanProperties = new ArrayList<BeanProperties>();
		try{
			String sql = "select * from "+tableName;
			Statement st = getConnection().createStatement();
			ResultSet rs = st.executeQuery(sql);
			ResultSetMetaData rsmd = rs.getMetaData();
			int colcount = rsmd.getColumnCount();
			//取得全部列数
			for(int i=1;i<=colcount;i++){  
				BeanProperties bean = new BeanProperties();
				String colname = rsmd.getColumnName(i);
				String coltype = rsmd.getColumnTypeName(i);
				Integer collength = rsmd.getColumnDisplaySize(i);
				bean.setPropertiesName(colname);
				bean.setPropertiesType(coltype);
				bean.setPropertiesLength(collength);
				String otherName = colname.substring(0,1).toUpperCase()+colname.substring(1,colname.length());
				bean.setFirstGigPropertiesName(otherName);
				beanProperties.add(bean);
				System.out.println(colname+"\t"+otherName+"\t"+coltype+"\t"+collength);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return beanProperties;
	}
}
