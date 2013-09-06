package com.xdtech.cloudsearch.module.sitecrawler.service;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.namespace.QName;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xdtech.cloudsearch.module.base.page.PageResult;
import com.xdtech.cloudsearch.module.base.service.BaseService;
import com.xdtech.cloudsearch.module.sitecrawler.bean.BlockResult;
import com.xdtech.cloudsearch.module.sitecrawler.bean.Crawler;
import com.xdtech.cloudsearch.module.sitecrawler.bean.ParserRsult;
import com.xdtech.cloudsearch.module.sitecrawler.bean.Site;
import com.xdtech.cloudsearch.module.sitecrawler.bean.Template;
import com.xdtech.cloudsearch.module.sitecrawler.bean.TemplateCategory;
import com.xdtech.cloudsearch.util.ValidateObject;
import com.xdtech.cloudsearch.ws.sitecrawler.CrawlerServiceInterface;
import com.xdtech.cloudsearch.ws.sitecrawler.CrawlerServiceService;
import com.xdtech.cloudsearch.ws.sitecrawler.FetchTemplate;
import com.xdtech.cloudsearch.ws.sitecrawler.ParseResult;
import com.xdtech.cloudsearch.ws.sitecrawler.WebServiceProperties;

/**
 * 模板-Service
 * 
 * @author WangWei
 * 2013-06-07
 */
@Service(value="templateService")
public class TemplateService extends BaseService{
	@Autowired(required = true)
	private CrawlerService crawlerService;
	@Autowired(required = true)
	private SiteService siteService;
	/**查询所有模板
     * @author WangWei
     * @return
     */
	public List<Template> findAll(){
		String queryString = "from Template ";
		List<Template> list = find(queryString);
		return list;
	}
	/**
	 * 根据模板主键，获取模板
	 * WangWei
	 * @param idstr
	 * @return
	 */
	public List<Template> findAllByIds(String idstr){
		String queryString = "from Template where 1 = 1";
		if(null != idstr){
			String[] ids = idstr.split(",");
			StringBuffer sb = new StringBuffer("");
			for(String s:ids){
				if(!"".equals(sb.toString())){
					sb.append(",");
				}
				sb.append("'").append(s).append("'");
			}
			queryString = queryString + " and id in ("+sb.toString()+")";
			List<Template> list = find(queryString);
			return list;
		}
		return null;
	}
	/**检查模板是否存在
	 * 
	 * @param Template
     * @author WangWei
     * @return
     */
	public String checkTemplate(Template template){
		StringBuffer queryString =new StringBuffer("select count(*) from Template t where 1=1 ");
		List<Object> list =new ArrayList<Object>();
		if(template.getId()!=null&&!template.getId().equals("")){
			queryString.append(" and t.id<>?  ");
			list.add(template.getId());
		}
		if(template.getName()!=null&&!template.getName().equals("")){
			queryString.append("and t.name=?  ");
			list.add(template.getName());
		}
		List<Object> userlist = getDao().findList(queryString.toString(),list.toArray());
		Long c= (Long) userlist.get(0);
		if(c>0){
			return "false";
		}
		return "true";
	}
	/**
	 * 分页
	 * @param Template
	 * @param pageResult
	 * @return
	 */
	public PageResult findByPage(Template template,PageResult pageResult){
		String queryString  = "from Template where 1 = 1";
		if(null!=template){
			if(null!=template.getName()&&!"".equals(template.getName())){
				queryString =queryString +" and name like '%"+template.getName()+"%'";
			}
			if(null!=template.getCategoryId()&&!"".equals(template.getCategoryId())&&!",".equals(template.getCategoryId())){
				StringBuffer sb = new StringBuffer("");
				if(template.getCategoryId().length() > 32){
					String[] categoryIds = template.getCategoryId().split(",");
					for(String s:categoryIds){
						if(!"".equals(sb.toString())){
							sb.append(",");
						}
						sb.append("'"+s.trim()+"'");
					}
				}else{
					sb.append("'"+template.getCategoryId()+"'");
				}
				queryString =queryString +" and categoryId in ("+sb.toString()+")";
			}
		}
		List<Template> list=null;
		queryString = queryString +" order by operateTime desc ";
		try {
			list = findByPage(queryString, pageResult.getPageNo(), pageResult.getPageSize());
		} catch (Exception e) {
			e.printStackTrace();
		}
		initCategoryNames(list);
		pageResult.setRows(list);
		pageResult.setTotal(getCount(queryString));
		setTemplateSites(list);
		return pageResult;
	}
	/**
	 * 获取站点个数
	 * @param list
	 */
	public void setTemplateSites(List<Template> list){
		List<Site> sites = getSites();
		Integer length = list.size();
		for(int i=0;i<length;i++){
			Template t = list.get(i);
			setTemplateSites(t, sites);
		}
	}
	/**
	 * 分页
	 * @param Template
	 * @param pageResult
	 * @return
	 */
	public PageResult findByPageC(Template template,PageResult pageResult){
		String queryString  = "from Template where 1 = 1";
		if(null!=template){
			if(null!=template.getName()&&!"".equals(template.getName())){
				queryString =queryString +" and name like '%"+template.getName()+"%'";
			}
			//判断是否是根目录，如果不是跟目录在进行分类检索
			if(template.getIsRoot() != 1){
				if(null!=template.getCategoryId()&&!"".equals(template.getCategoryId())&&!",".equals(template.getCategoryId())){
					StringBuffer sb = new StringBuffer("");
					if(template.getCategoryId().length() > 33){
						String[] categoryIds = template.getCategoryId().split(",");
						for(String s:categoryIds){
							if(!"".equals(sb.toString())){
								sb.append(",");
							}
							sb.append("'"+s.trim()+"'");
						}
						//属性检索条件
						queryString =queryString +" and categoryId in ("+sb.toString()+")";
					}else{
						//分类树检索条件
						sb.append("'"+template.getCategoryId().replace(",", "")+"'");
						queryString =queryString +" and ((categoryId in (select tc.id from TemplateCategory tc where tc.parentId in ("+sb.toString()+"))) or (categoryId = '"+template.getCategoryId().replace(",", "")+"'))";
					}
				}
			}
		}
		List<Template> list=null;
		queryString = queryString +" order by operateTime desc ";
		try {
			list = findByPage(queryString, pageResult.getPageNo(), pageResult.getPageSize());
		} catch (Exception e) {
			e.printStackTrace();
		}
		initCategoryNames(list);
		pageResult.setRows(list);
		pageResult.setTotal(getCount(queryString));
		setTemplateSites(list);
		return pageResult;
	}
	/**
	 * 根据模板的类型ID,给模板的名称赋值
	 * @param list
	 */
	public void initCategoryNames(List<Template> list){
		//将模板分类和名称进行一一对应
		List<TemplateCategory> tcs = getDao().findAll(TemplateCategory.class);
		Map<String,String> map = new HashMap<String, String>();
		for(TemplateCategory tc:tcs){
			map.put(tc.getId(), tc.getName());
		}
		int length = list.size();
		for(int i = 0;i<length;i++){
			Template t = list.get(i);
			t.setCategoryName(map.get(t.getCategoryId()));
			list.set(i, t);
		}
	}
	/**根据模板类型获取模板
     * @author WangWei
     * @return
     */
	public List<Template> findTemplateByTemplateCategoryId(String tcid){
		String queryString = "from Template where (categoryId in (select tc.id from TemplateCategory tc where tc.parentId = '"+tcid+"')) or (categoryId = '"+tcid+"') ";
		queryString = queryString +")";
		List<Template> list = find(queryString);
		return list;
	}
	/**
	 * @param url
	 * @param template
	 */
	public ParserRsult test(String url, Template template) {
		String tableName = "test";
		URL serviceUrl;
		ParserRsult parserRsult = new ParserRsult();
		List<Crawler>  crawlers = crawlerService.getRunningCrawlers();
		if(!ValidateObject.hasValueInCollection(crawlers)){
			parserRsult.setMessage("没找到可用的爬虫，请先创建爬虫或检查现有爬虫状态！");
			return parserRsult;
		}
		CrawlerServiceService service = null;
		CrawlerServiceInterface serverCrawler = null;
		Crawler c = crawlers.get(0);
		try {
			serviceUrl = new URL(c.getCrawlerAddress()+"/service/crawler?wsdl");
			service = new CrawlerServiceService(serviceUrl, new QName("http://www.xd-tech.com", "CrawlerServiceService"));
			serverCrawler = service.getCrawlerServicePort();
			FetchTemplate ft = template.fix(template);
			ParseResult parseResult = serverCrawler.parser(tableName,url, ft);
			
			WebServiceProperties base = parseResult.getBase();
			String[] keys = base.getKeys() != null ? base.getKeys().toArray(new String[0]) : new String[] {};
			String[] values = base.getValues() != null ? base.getValues().toArray(new String[0]) : new String[] {};
			Map<String, String> map = new HashMap<String, String>();
			for (int i = 0; i < keys.length && i < values.length; i++) {
				map.put(keys[i], values[i]);
			}
			parserRsult = new ParserRsult();
			parserRsult.setAuthor(map.get("author"));
			parserRsult.setCommentCount(map.get("commentcount"));
			parserRsult.setContent(map.get("mainContent"));
			parserRsult.setPubtime(map.get("issueDate"));
			parserRsult.setTitle(map.get("title"));
			parserRsult.setViewCount(map.get("hitcount"));
			parserRsult.setSource(map.get("copyfrom"));
			parserRsult.setMessage(map.get("xdservermessage"));
			String pageurls = map.get("xdpageurls");
			Set<String> pageUrlList = new java.util.HashSet<String>();
			if (pageurls != null && pageurls.trim().length() > 0) {
				String[] urls = pageurls.split("\n");
				if (urls != null && urls.length > 0) {
					for (String pageurl : urls) {
						if (pageurl != null) {
							pageUrlList.add(pageurl.trim());
						}
					}
				}
			}
			parserRsult.setFenyeUrls(pageUrlList.toArray(new String[pageUrlList.size()]));
			//关联规则
			String retiveurls = map.get("xdretiveurls");
			Set<String> retiveUrlList = new java.util.HashSet<String>();
			if (retiveurls != null && retiveurls.trim().length() > 0) {
				String[] urls = retiveurls.split("\n");
				if (urls != null && urls.length > 0) {
					for (String pageurl : urls) {
						if (pageurl != null) {
							retiveUrlList.add(pageurl.trim());
						}
					}
				}
			}
			parserRsult.setRetiveUrls(retiveUrlList.toArray(new String[retiveUrlList.size()]));
			/*
			 * 块规则（关联规则）
			 */
			List<WebServiceProperties> blocks = parseResult.getBlocks();
			List<BlockResult> blockResultList = new ArrayList<BlockResult>();
			if (blocks != null && !blocks.isEmpty()) {
				for (WebServiceProperties block : blocks) {
					BlockResult blockResult = new BlockResult();
					blockResult.setKeys(block.getKeys());
					blockResult.setValues(block.getValues());
					blockResultList.add(blockResult);
				}
			}
			parserRsult.setBlockResultList(blockResultList);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return parserRsult;
	}
	/**
	 * 获取所有的站点信息
	 * @return
	 */
	public List<Site> getSites(){
		List<Site> sites = siteService.findAll();
		return sites;
	}
	
	public void setTemplateSites(Template t,List<Site> sites){
		Integer count = new Integer(0);
		for(Site s:sites){
			if(s != null && s.getTemplateIds() != null && s.getTemplateIds().indexOf(t.getId()) > -1){
				count ++;
			}
		}
		t.setSites(count);
	}
}
