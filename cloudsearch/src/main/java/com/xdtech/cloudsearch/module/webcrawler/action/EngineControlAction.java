package com.xdtech.cloudsearch.module.webcrawler.action;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xdtech.cloudsearch.module.base.action.BaseAction;
import com.xdtech.cloudsearch.module.sitecrawler.bean.Crawler;
import com.xdtech.cloudsearch.module.sitecrawler.service.CrawlerService;
import com.xdtech.cloudsearch.module.webcrawler.bean.EngineControl;
import com.xdtech.cloudsearch.module.webcrawler.service.EngineService;
import com.xdtech.cloudsearch.webservice.xdservice.DataCache;
import com.xdtech.cloudsearch.ws.webcrawler.CrawlerServiceImplService;
import com.xdtech.cloudsearch.ws.webcrawler.ICrawlerService;
/**
 * 全网采集action
 * @author LZF
 *
 */
@Controller
@RequestMapping("/engineControl")
public class EngineControlAction extends BaseAction{
	@Autowired(required = true)
	private EngineService engineService;
	@Autowired(required = true)
	private CrawlerService crawlerService;
	/**
	 * 进入搜索引擎管理页面
	 * @return
	 */
	@RequestMapping("/engineControl.do")
	public ModelAndView index (){
		EngineControl engineControl = new EngineControl();
		engineControl.setEngines(engineService.findAll(1));
		ModelMap mmap = new ModelMap();
		mmap.put("engineControl", engineControl);
		ModelAndView mv = new ModelAndView("/module/webcrawler/enginecontrol/index.jsp");
		mv.addAllObjects(mmap);
		return mv;
	}
	/**
	 * 开始搜索引擎
	 * @return
	 */
	@RequestMapping("/start.do")
	public ModelAndView start(){
		EngineControl engineControl = new EngineControl();
		engineControl.setEngines(engineService.findAll(1));
		engineControl.setStart(true);
		
		Crawler crawler = DataCache.getWebCrawler();
		ModelAndView mv = new ModelAndView("/module/webcrawler/enginecontrol/engineControl.jsp");
		if(null != crawler){
			URL serviceUrl;
			try {
				serviceUrl = new URL(crawler.getCrawlerAddress()+"/service/webcrawler?wsdl");
				CrawlerServiceImplService service = new CrawlerServiceImplService(serviceUrl, new QName("http://www.xd-tech.com", "CrawlerServiceImplService"));
				ICrawlerService serverCrawler = service.getCrawlerServiceImplPort();
				serverCrawler.start();
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
			ModelMap mmap = new ModelMap();
			mmap.put("engineControl", engineControl);
			mv.addAllObjects(mmap);
		}
		return mv;
	}
	/**
	 * 停止搜索引擎
	 * @return
	 */
	@RequestMapping("/end.do")
	public ModelAndView end(){
		EngineControl engineControl = new EngineControl();
		engineControl.setEngines(engineService.findAll(1));
		engineControl.setStart(false);
		Crawler crawler =  DataCache.getWebCrawler();
		ModelAndView mv = new ModelAndView("/module/webcrawler/enginecontrol/engineControl.jsp");
		if(null != crawler){
			URL serviceUrl;
			try {
				serviceUrl = new URL(crawler.getCrawlerAddress()+"/service/webcrawler?wsdl");
				CrawlerServiceImplService service = new CrawlerServiceImplService(serviceUrl, new QName("http://www.xd-tech.com", "CrawlerServiceImplService"));
				ICrawlerService serverCrawler = service.getCrawlerServiceImplPort();
				serverCrawler.stop();
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
			ModelMap mmap = new ModelMap();
			mmap.put("engineControl", engineControl);
			mv.addAllObjects(mmap);
		}
		return mv;
	}
	/**
	 * 自动刷新全网监控状态
	 * 
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/ajaxList.do")
	public @ResponseBody String ajaxList() {
		Crawler crawler = DataCache.getWebCrawler();
		String string = "-1";
		if(null != crawler){
			URL serviceUrl;
			try {
				serviceUrl = new URL(crawler.getCrawlerAddress()+"/service/webcrawler?wsdl");
				CrawlerServiceImplService service = new CrawlerServiceImplService(serviceUrl, new QName("http://www.xd-tech.com", "CrawlerServiceImplService"));
				ICrawlerService serverCrawler = service.getCrawlerServiceImplPort();
				string = serverCrawler.findRunStatus();
				System.out.println("全网监控状态为："+string);
			} catch (Exception e) {
				//e.printStackTrace();
			}
		}else{
			string = "-1";
		}
		return string;
	}
}
