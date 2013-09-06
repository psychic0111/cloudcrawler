package com.xdtech.cloudsearch.module.sitecrawler.action;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xdtech.cloudsearch.module.base.action.BaseAction;
import com.xdtech.cloudsearch.module.base.page.PageResult;
import com.xdtech.cloudsearch.module.sitecrawler.bean.Crawler;
import com.xdtech.cloudsearch.module.sitecrawler.bean.DataBase;
import com.xdtech.cloudsearch.module.sitecrawler.bean.TableProperties;
import com.xdtech.cloudsearch.module.sitecrawler.service.CrawlerService;
import com.xdtech.cloudsearch.module.sitecrawler.service.DataBaseService;
import com.xdtech.cloudsearch.webservice.xdservice.CrawlerConfigManager;
import com.xdtech.cloudsearch.ws.client.CrawlerClient;
import com.xdtech.cloudsearch.ws.sitecrawler.CrawlerServiceInterface;

/**
 * 爬虫-Action
 * 
 * @author WangWei
 * 2013-06-20
 */
@Controller
@RequestMapping("/crawler")
public class CrawlerAction  extends BaseAction{
	@Autowired(required = true)
	private CrawlerService crawlerService;
	@Autowired(required = true)
	private DataBaseService dataBaseService;
	private String isCrawlerList = "isCrawlerList";
	
	/**
	 * 爬虫列表页
	 * @param Crawler
	 * @return 
	 * @author WangWei
	 */
	@RequestMapping("/crawler.do")
	public  ModelAndView index(Crawler crawler,@ModelAttribute PageResult pageReuslt) {
		if (pageReuslt == null) {
			pageReuslt = new PageResult();
		}
		ModelMap mmap = new ModelMap();
		PageResult pageResult = crawlerService.findByPage(crawler, pageReuslt);// userService.findAll();
		List<Crawler> crawlers = pageResult.getRows();
		mmap.put("list", crawlers);
		int i = 0;
		for(Crawler c:crawlers){
			i = i + c.getStatus();
		}
		if(i == 0){
			mmap.put("operate", 1);
		}else{
			mmap.put("operate", 0);
		}
		mmap.put("pageResult", pageResult);
		mmap.put("isCrawlerList", isCrawlerList);
		ModelAndView mv = new ModelAndView("/module/sitecrawler/crawler/index.jsp");
		mv.addAllObjects(mmap);
		return mv;
	}
	/**
	 * 爬虫列表页
	 * @param Crawler
	 * @return 
	 * @author WangWei
	 */
	@RequestMapping("/crawlerList.do")
	public  ModelAndView list(Crawler crawler,@ModelAttribute PageResult pageReuslt) {
		ModelMap mmap = new ModelMap();
		PageResult pageResult = crawlerService.findByPage(crawler, pageReuslt);// userService.findAll();
		List<Crawler> crawlers = pageResult.getRows();
		mmap.put("list", crawlers);
		int i = 0;
		for(Crawler c:crawlers){
			i = i + c.getStatus();
			System.out.println("i:"+i);
		}
		System.out.println("共有多少个启动："+i);
		if(i == 0){
			mmap.put("operate", 1);
		}else{
			mmap.put("operate", 0);
		}
		
		mmap.put("pageResult", pageResult);
		mmap.put("isCrawlerList", isCrawlerList);
		ModelAndView mv = new ModelAndView("/module/sitecrawler/crawler/list.jsp");
		mv.addAllObjects(mmap);
		return mv;
	}
	/**
	 * 跳转到爬虫添加页面
	 * @return
	 * @author WangWei
	 */
	@RequestMapping("/add.do")
	public ModelAndView add(HttpServletRequest request) {
		ModelMap mmap = new ModelMap();
		Crawler crawler = new Crawler();
		crawler.setCrawlerThread(20);
		crawler.setDownloadThread(10);
		crawler.setSiteThread(5);
		crawler.setSaveSnap(0);
		crawler.setStatus(1);
		crawler.setDownImage(0);
		crawler.setImageSize(20);
		crawler.setCrawlerStatus(1);
		crawler.setCrawlerType("网页爬虫");
		crawler.setImagePath(request.getContextPath());
		crawler.setImagePath(File.separator+"downloadimage");
		crawler.setDataPath(File.separator+"downloadfile");
		List<DataBase> dataBases = dataBaseService.getDao().findList(" from DataBase order by operateTime desc");
		DataBase dataBase = null;
		if(null == dataBases || dataBases.size() == 0){
			dataBase = new DataBase();
			dataBase.setDbtype("mysql");
			dataBase.setDbport(3306);
			dataBase.setDbuser("root");
		}else{
			dataBase = dataBases.get(0);
		}
		mmap.put("crawler", crawler);
		mmap.put("dataBase", dataBase);
		mmap.put("action", request.getContextPath()+"/crawler/doAdd.do");
		ModelAndView mv = new ModelAndView("/module/sitecrawler/crawler/add.jsp");
		mv.addAllObjects(mmap);
		return mv;
	}
	
	/**
	 * 爬虫添加
	 * @return
	 * @author WangWei
	 */
	@RequestMapping("/doAdd.do")
	public @ResponseBody String doAdd(Crawler crawler,DataBase database) {
		crawler.setOperateTime(new Date());
		crawler.setOperateUser(currentUser.getUsername());
		dataBaseService.save(database);
		crawler.setDatabaseId(database.getId());
		crawler.setIsDeath(1);
		crawler.setAutoConnects(0);
		crawlerService.save(crawler);
		CrawlerConfigManager.refreshInit();
		return "true";
	}
	
	/**
	 *查看爬虫是否存在
	 * @return
	 * @author WangWei
	 */
	@RequestMapping("/checkCrawler")
	public @ResponseBody String checkCrawler(Crawler crawler) {
		return crawlerService.checkCrawler(crawler);
	}

	/**
	 *查看爬虫代码是否存在
	 * @return
	 * @author WangWei
	 */
	@RequestMapping("/checkCode")
	public @ResponseBody String checkCode(Crawler crawler) {
		return crawlerService.checkCode(crawler);
	}
	/**
	 * 爬虫删除
	 * @return
	 * @author WangWei
	 */
	@RequestMapping("/del.do")
	public @ResponseBody String delete(String id[]) {
		crawlerService.deleteAll(Crawler.class,id );
		CrawlerConfigManager.refreshInit();
		return "true";
	}
	/**
	 * 跳转到爬虫修改页面
	 * @return
	 * @author WangWei
	 */
	@RequestMapping("/edit.do")
	public ModelAndView edit(String id,HttpServletRequest request){
		ModelMap mmap = new ModelMap(); 
		Crawler crawler = crawlerService.findById(Crawler.class, id);
		DataBase dataBase = dataBaseService.findById(DataBase.class, crawler.getDatabaseId());
		mmap.put("crawler", crawler);
		mmap.put("dataBase", dataBase);
		mmap.put("action", request.getContextPath()+File.separator+"crawler"+File.separator+"editDo.do");
		ModelAndView mv = new ModelAndView("/module/sitecrawler/crawler/add.jsp");
		mv.addAllObjects(mmap);
		return mv;
	}
    
	/**
	 *爬虫修改
	 * @return
	 * @author WangWei
	 */
	@RequestMapping("/editDo")
	public @ResponseBody String editDo(Crawler crawler,DataBase database) {
		crawler.setOperateTime(new Date());
		crawler.setOperateUser(currentUser.getUsername());
		crawlerService.update(crawler);
		database.setId(crawler.getDatabaseId());
		dataBaseService.update(database);
		CrawlerConfigManager.refreshInit();
		return "true";
	}
	/**
	 * 开始爬虫
	 * @return
	 */
	@RequestMapping("/start.do")
	public ModelAndView start(Crawler crawler,@ModelAttribute PageResult pageReuslt){
		try {
			if(crawler!=null&&null!=crawler.getCode()&&!crawler.getCode().equals("")){
				crawler = crawlerService.getCrawlerByCode(crawler.getCode());
				CrawlerClient.getCrawlerServiceInterface(crawler).start();
				crawler.setCrawlerStatus(2);
				crawlerService.update(crawler);
			}else{
				List<Crawler>  crawlers = crawlerService.getAllCrawlerUsedAndNoStop();
				for(Crawler c :crawlers){
					CrawlerClient.getCrawlerServiceInterface(c).start();
					c.setCrawlerStatus(2);
					crawlerService.update(c);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		crawler = new Crawler();
		return list(crawler, pageReuslt);
	}
	/**
	 * 停止爬虫
	 * @return
	 */
	@RequestMapping("/end.do")
	public ModelAndView end(Crawler crawler,@ModelAttribute PageResult pageReuslt){
		try {
			List<Crawler>  crawlers = crawlerService.getRunningCrawlers();
			if(crawler!=null&&null!=crawler.getCode()&&!crawler.getCode().equals("")){
				crawler = crawlerService.getCrawlerByCode(crawler.getCode());
				CrawlerClient.getCrawlerServiceInterface(crawler).stop();
				crawler.setCrawlerStatus(1);
				crawlerService.update(crawler);
			}else{
				for(Crawler c :crawlers){
					CrawlerClient.getCrawlerServiceInterface(c).stop();
					c.setCrawlerStatus(1);
					crawlerService.update(c);
				}
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		crawler = new Crawler();
		return list(crawler, pageReuslt);
	}
	/**
	 * 测试连接是否成功
	 * @return
	 */
	@RequestMapping("/testConnection.do")
	public @ResponseBody String testConnection(DataBase dataBase){
		String clazz = dataBase.getDbtype();
		String url = dataBase.getDburl();
		String user = dataBase.getDbuser();
		String password = dataBase.getDbpassword();
		Connection conn = null;
		String result = "连接测试失败！请检查用户名和密码是否正确！";
		try {
			Class.forName(clazz);
			conn = DriverManager.getConnection(url, user, password);
			if(null != conn){
				result = "连接测试成功！";
			}
		} catch (Exception e) {
		}finally{
			try {
				if(null != conn){
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	/**
	 * 获取数据库中的表名称
	 * @return
	 */
	@RequestMapping("/getTableNames.do")
	public void getTableNames(HttpServletResponse response,DataBase dataBase){
		String clazz = dataBase.getDbtype();
		String url = dataBase.getDburl();
		String user = dataBase.getDbuser();
		String password = dataBase.getDbpassword();
		Connection conn = null;
		String result = "1";
		ResultSet rs = null;
		try {
			Class.forName(clazz);
			conn = DriverManager.getConnection(url, user, password);
			if(null != conn){
				DatabaseMetaData meta = conn.getMetaData();   
				rs = meta.getTables(null, null, null,   
						new String[] { "TABLE" });
				List<TableProperties> pts = new ArrayList<TableProperties>();
				while (rs.next()) { 
					String tableCode = rs.getString(3);
					TableProperties tp = new TableProperties();
					tp.setTableCode(tableCode);
					tp.setTableName(tableCode);
					pts.add(tp);
				}
				JSONArray jsonArray = JSONArray.fromObject(pts);
				result = jsonArray.toString();
			}
			
		} catch (Exception e) {
		}finally{
			try {
				if(null != rs){
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if(null != conn){
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		try {
			response.setContentType("text/json; charset=UTF-8"); 
			response.getWriter().print(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 自动刷新爬虫状态
	 * 
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/ajaxList.do")
	public @ResponseBody String ajaxList() {
		List<Crawler>  crawlers = crawlerService.getRunningCrawlers();
		@SuppressWarnings("unused")
		CrawlerServiceInterface serverCrawler = null;
		String string = "";
		if (crawlers != null && !crawlers.isEmpty()) {
			for (Crawler cra : crawlers) {
				try {
					String runstatus = CrawlerClient.getCrawlerServiceInterface(cra).findRunStatus();
					if (string.length() > 0 && !"".equals(string)) {
						string += ",";
					}
					string += cra.getId().toString() + ":" + runstatus;
				} catch (Exception e) {
				}
			}
		}
		return string;
	}
	
	/**
	 * 自动刷新爬虫状态
	 * 
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/ajaxRuningCrawlers.do")
	public @ResponseBody String ajaxRuningCrawlers(Crawler crawler) {
		PageResult pageResult = new PageResult();
		PageResult result = crawlerService.findCrawlersByExample(crawler, pageResult);
		List crawlers = result.getRows();
		String string = "";
		if (crawlers != null && !crawlers.isEmpty()) {
			for (int i = 0; i < crawlers.size(); i++) {
				Crawler cra = (Crawler) crawlers.get(i);
				try {
					//String runstatus = CrawlerClient.getCrawlerServiceInterface(cra).findRunStatus();
					string += cra.getName() + ",";
				} catch (Exception e) {
				}
			}
			if(string.length() > 0){
				string = string.substring(0,string.length() - 1);
			}
		}
		return string;
	}
}
