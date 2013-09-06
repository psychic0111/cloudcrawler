package com.xdtech.cloudsearch.module.sitecrawler.action;

import java.io.BufferedReader;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.tools.ant.filters.StringInputStream;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;
import org.apache.tools.zip.ZipOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.xdtech.cloudsearch.module.base.action.BaseAction;
import com.xdtech.cloudsearch.module.base.page.PageResult;
import com.xdtech.cloudsearch.module.sitecrawler.bean.ParserRsult;
import com.xdtech.cloudsearch.module.sitecrawler.bean.Region;
import com.xdtech.cloudsearch.module.sitecrawler.bean.Site;
import com.xdtech.cloudsearch.module.sitecrawler.bean.SiteCategory;
import com.xdtech.cloudsearch.module.sitecrawler.bean.Template;
import com.xdtech.cloudsearch.module.sitecrawler.bean.TemplateCategory;
import com.xdtech.cloudsearch.module.sitecrawler.service.PluginService;
import com.xdtech.cloudsearch.module.sitecrawler.service.RegionService;
import com.xdtech.cloudsearch.module.sitecrawler.service.SiteCategoryService;
import com.xdtech.cloudsearch.module.sitecrawler.service.SiteProxyService;
import com.xdtech.cloudsearch.module.sitecrawler.service.SiteService;
import com.xdtech.cloudsearch.module.sitecrawler.service.TemplateCategoryService;
import com.xdtech.cloudsearch.module.sitecrawler.service.TemplateService;
import com.xdtech.cloudsearch.util.AppConf;
import com.xdtech.cloudsearch.util.GenURL;
import com.xdtech.cloudsearch.util.ValidateObject;
import com.xdtech.cloudsearch.webservice.xdservice.CrawlerConfigManager;
/**
 * 站点-Action
 * 
 * @author WangWei
 * 2013-06-13
 */
@Controller
@RequestMapping("/site")
public class SiteAction extends BaseAction{
	@Autowired(required = true)
	private SiteService siteService;
	@Autowired(required = true)
	private SiteCategoryService siteCategoryService;
	@Autowired(required = true)
	private TemplateCategoryService templateCategoryService;
	@Autowired(required = true)
	private RegionService regionService;
	@Autowired(required = true)
	private SiteProxyService siteProxyService;
	@Autowired(required = true)
	private PluginService pluginService;
	@Autowired(required = true)
	private TemplateService templateService;
	
	
	/**
	 * 站点管理列表页
	 * @param site
	 * @return 
	 * @author WangWei
	 */
	@RequestMapping("/site.do")
	public  ModelAndView index(Site site,@ModelAttribute PageResult pageReuslt) {
		if (pageReuslt == null) {
			pageReuslt = new PageResult();
		}
		ModelMap mmap = new ModelMap();
		mmap.put("siteCategory", siteCategoryService.getDao().getTemplate().find(" from SiteCategory "));
		PageResult pageResult = siteService.findByPage(site, pageReuslt);// userService.findAll();
		mmap.put("list", pageResult.getRows());
		mmap.put("pageResult", pageResult);
		ModelAndView mv = new ModelAndView("/module/sitecrawler/site/index.jsp");
		mv.addAllObjects(mmap);
		return mv;
	}
	/**
	 * 站点管理列表页
	 * @param site
	 * @return 
	 * @author WangWei
	 */
	@RequestMapping("/siteList.do")
	public  ModelAndView list(Site site,@ModelAttribute PageResult pageReuslt) {
		ModelMap mmap = new ModelMap();
		if (pageReuslt == null) {
			pageReuslt = new PageResult();
		}
		mmap.put("siteCategory", siteCategoryService.getDao().getTemplate().find(" from SiteCategory "));
		PageResult pageResult = siteService.findByPage(site, pageReuslt);// userService.findAll();
		mmap.put("list", pageResult.getRows());
		mmap.put("pageResult", pageResult);
		ModelAndView mv = new ModelAndView("/module/sitecrawler/site/list.jsp");
		mv.addAllObjects(mmap);
		return mv;
	}
	/**
	 * 跳转到站点添加页面
	 * @return
	 * @author WangWei
	 */
	@RequestMapping("/add.do")
	public ModelAndView add(HttpServletRequest request) {
		Site site = new Site();
		ModelMap mmap = new ModelMap();
		site.setWebType(0);
		site.setStatue(0);
		site.setLevel("1");
		site.setCrawlerDeep(3);
		site.setIspart(0);
		mmap.put("site", site);
		mmap.put("siteCategory", siteCategoryService.getDao().getTemplate().find(" from SiteCategory "));
		List<TemplateCategory> allTemplateCategorys = new ArrayList<TemplateCategory>();
		List<TemplateCategory> fristCategory = templateCategoryService.getAllFristLevel();
		for(TemplateCategory tc:fristCategory){
			allTemplateCategorys.add(tc);
			tc.setChildList(templateCategoryService.getAllSecondLevel(tc.getId()));
		}
		mmap.put("fristCategory",allTemplateCategorys);
		mmap.put("areaTypes",AppConf.get().get("system.area.type").split("\\|"));
		mmap.put("mediaTypes",AppConf.get().get("system.media.type").split("\\|"));
		mmap.put("htmlCodes",AppConf.get().get("system.site.htmlcode").split("\\|"));
		List<Region> countrys = regionService.findCountry();
		mmap.put("countrys", countrys);
		if(null != countrys && countrys.size() > 0){
			mmap.put("provinces", regionService.findChildrenByParentId(countrys.get(0).getId()));
		}
		mmap.put("proxys", siteProxyService.findAll());
		mmap.put("plugins", pluginService.findAll());
		System.out.println(request.getContextPath()+File.separator+"site/doAdd.do");
		mmap.put("action", request.getContextPath()+"/site/doAdd.do");
		ModelAndView mv = new ModelAndView("/module/sitecrawler/site/add.jsp");
		mv.addAllObjects(mmap);
		return mv;
	}
	
	/**
	 * 站点添加
	 * @return
	 * @author WangWei
	 */
	@RequestMapping("/doAdd.do")
	public @ResponseBody String doAdd(Site site,Template template) {
		String[] names = site.getName().split(",");
		String[] ids = site.getTemplateIds().split(",");
		if(null == ids || ids.length ==0){
			template.setId(null);
		}else if(ids.length ==1){
			template.setId(ids[0].equals("")?null:ids[0]);
		}
		if(names.length==2){
			template.setName(names[1]);
		}
		System.out.println("长度："+template.getCategoryId().split(",").length);
		if(template.getCategoryId().split(",").length == 1){
			template.setCategoryId(template.getCategoryId().split(",")[0]);
		}else if(template.getCategoryId().split(",").length == 2){
			template.setCategoryId(template.getCategoryId().split(",")[1]);
		}
		template.setOperateTime(new Date());
		template.setOperateUser(currentUser.getUsername());
		if(!"".equals(template.getUrlRule())&&template.getUrlRule()!=null){
			templateService.saveOrUpdate(template);
		}
		
		if("".equals(site.getId())){
			site.setId(null);
		}else {
			site.setId(site.getId().split(",")[0]);
		}
		site.setName(names[0]);
		site.setIspart(0);
		if(null != site.getTemplateIds() && !"".equals(site.getTemplateIds())){
			site.setTemplateIds(site.getTemplateIds()+","+template.getId());
		}else{
			site.setTemplateIds(template.getId());
		}
		site.setOperateTime(new Date());
		site.setOperateUser(currentUser.getUsername());
		site.setSiteCategoryName(((SiteCategory)siteCategoryService.getDao().findById(SiteCategory.class, site.getSiteCategoryId())).getName());
		siteService.saveOrUpdate(site);
		CrawlerConfigManager.addOrEditSiteSyn(site);
		
		return "true";
	}
	
	/**
	 *查看站点是否存在
	 * @return
	 * @author WangWei
	 */
	@RequestMapping("/checkSite")
	public @ResponseBody String checkSite(Site site) {
		return siteService.checkSite(site);
	}
	/**
	 * 站点删除
	 * @return
	 * @author WangWei
	 */
	@RequestMapping("/del.do")
	public @ResponseBody String delete(String[] id) {
		CrawlerConfigManager.deleteSitesSyn(Arrays.asList(id));
		siteService.deleteAll(Site.class,id );
		return "true";
	}
	/**
	 * 跳转到站点修改页面
	 * @return
	 * @author WangWei
	 */
	@RequestMapping("/edit.do")
	public ModelAndView edit(String id,HttpServletRequest request) {
		ModelMap mmap = new ModelMap();
		mmap.put("siteCategory", siteCategoryService.getDao().getTemplate().find(" from SiteCategory "));
		List<TemplateCategory> allTemplateCategorys = new ArrayList<TemplateCategory>();
		List<TemplateCategory> fristCategory = templateCategoryService.getAllFristLevel();
		for(TemplateCategory tc:fristCategory){
			allTemplateCategorys.add(tc);
			tc.setChildList(templateCategoryService.getAllSecondLevel(tc.getId()));
		}
		mmap.put("fristCategory",allTemplateCategorys);
		mmap.put("areaTypes",AppConf.get().get("system.area.type").split("\\|"));
		mmap.put("mediaTypes",AppConf.get().get("system.media.type").split("\\|"));
		mmap.put("htmlCodes",AppConf.get().get("system.site.htmlcode").split("\\|"));
		List<Region> countrys = regionService.findCountry();
		mmap.put("countrys", countrys);
		if(null != countrys && countrys.size() > 0){
			mmap.put("provinces", regionService.findChildrenByParentId(countrys.get(0).getId()));
		}
		mmap.put("proxys", siteProxyService.findAll());
		mmap.put("plugins", pluginService.findAll());
		Site site = siteService.findById(Site.class, id);
		if(site.getCityId()!=""){
			List<Region> citys = regionService.find(" from Region where id = '"+site.getCityId()+"'");
			mmap.put("citys",citys );
		}
		if(site.getAreaId()!=""){
			List<Region> areas = regionService.find(" from Region where id = '"+site.getAreaId()+"'");
			mmap.put("areas", areas);
		}
		if(!"".equals(site.getTemplateIds())){
			site.setTemplates(templateService.findAllByIds(site.getTemplateIds()));
		}
		mmap.put("site", site);
		mmap.put("action", request.getContextPath()+"/site/editDo.do");
		ModelAndView mv = new ModelAndView("/module/sitecrawler/site/add.jsp");
		mv.addAllObjects(mmap);
		return mv;
	}
    
	/**
	 *站点修改
	 * @return
	 * @author WangWei
	 */
	@RequestMapping("/editDo")
	public @ResponseBody String editDo(Site site) {
		site.setId(site.getId().split(",")[0]);
		String[] names = site.getName().split(",");
		site.setName(names[0]);
		site.setOperateTime(new Date());
		site.setOperateUser(currentUser.getUsername());
		site.setSiteCategoryName(((SiteCategory)siteCategoryService.getDao().findById(SiteCategory.class, site.getSiteCategoryId())).getName());
		siteService.update(site);
		CrawlerConfigManager.addOrEditSiteSyn(site);
		return "true";
	}
	/**
	 * 模板添加(站点)
	 * @return
	 * @author WangWei
	 */
	@RequestMapping("/doAddForSite.do")
	public @ResponseBody void doAddForSite(HttpServletResponse response,Template template) {
		template.setOperateTime(new Date());
		template.setOperateUser(currentUser.getUsername());
		String categoryId = "";
		if(template.getCategoryId().length() > 32){
			categoryId = template.getCategoryId().split(",")[1];
		}
		template.setCategoryId(categoryId);
		templateService.save(template);
		JSONObject jo = JSONObject.fromObject(template);
		try {
			response.reset();
			response.setContentType("text/json; charset=UTF-8"); 
			PrintWriter out = response.getWriter();
			out.print(jo.toString());
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 根据父节点，找到子节点
	 * @param response 以json格式返回数据
	 * @param parentId 父ID
	 */
	@RequestMapping("/getRegionByParentId")
	public void getRegionByParentId(HttpServletResponse response,String parentId){
		List<Region> tcs = regionService.findChildrenByParentId(parentId);
		JSONArray jsonArray = JSONArray.fromObject(tcs);
		try {
			response.setContentType("text/json; charset=UTF-8"); 
			response.getWriter().print(jsonArray.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 站点启用
	 * @return
	 * @author WangWei
	 */
	@RequestMapping("/use.do")
	public @ResponseBody String use(String id[]) {
		siteService.updateAll(Site.class,id,"statue",1 );
		StringBuffer sb = new StringBuffer("");
		for(String sid:id){
			if(!sb.toString().equals("")){
				sb.append(",");
			}
			sb.append("'").append(sid).append("'");
		}
		List<Site> sites = siteService.find("from Site where statue = 1 and id in ("+sb.toString()+")");
		CrawlerConfigManager.addOrEditSiteSyn(sites);
		return "true";
	}
	
	/**
	 * 站点启用 根据查询结果
	 * @return
	 * @author chenlong
	 */
	@RequestMapping("/changeState.do")
	public @ResponseBody String changeState(Site site) {
		ModelMap mmap = new ModelMap();
		PageResult pageResult = new PageResult();
		pageResult.setPageSize(Integer.MAX_VALUE);
		PageResult result = siteService.findByPage(site, pageResult);// userService.findAll();
		List siteList = result.getRows();
		if(ValidateObject.hasValueInCollection(siteList)){
			String[] id = new String[siteList.size()];
			for(int i=0;i<siteList.size();i++){
				Site s = (Site) siteList.get(i);
				id[i] = s.getId();
			}
			if(site.getStatue() == 0){
				return use(id);
			}else{
				return noUse(id);
			}
		}
		
		return "true";
	}
	
	/**
	 * 站点禁用
	 * @return
	 * @author WangWei
	 */
	@RequestMapping("/noUse.do")
	public @ResponseBody String noUse(String id[]) {
		siteService.updateAll(Site.class,id,"statue",0 );
		CrawlerConfigManager.deleteSitesSyn(Arrays.asList(id));
		return "true";
	}
	
	
	/**
	 * 备份站点
	 */
	@RequestMapping("/backup.do")
	public void backupSiteDate(HttpServletRequest request,HttpServletResponse response,String[] id) {
		List<String> xmlData = null;
		if(id!=null&&id.length>0){
			xmlData = siteService.backup(id);
		}else{
			xmlData = siteService.backup(null);
		}
		response.setHeader("Content-Disposition", "attachment;fileName=site.zip");
		response.setContentType("application/octet-stream");
		ZipOutputStream zos = null;
		StringInputStream ins = new StringInputStream(xmlData.get(0), "UTF-8");
		ServletOutputStream outputStream = null;
		try {
			outputStream = response.getOutputStream();
			zos = new ZipOutputStream(outputStream);
			byte[] buffer = new byte[1024];
			int len = -1;
			// 将站点的备份数据写入zip包包中
			zos.putNextEntry(new ZipEntry("site_back.xml"));
			while ((len = ins.read(buffer)) != -1) {
				zos.write(buffer, 0, len);
			}
			
			if (xmlData.size() > 3) {
				len = -1;
				ins = new StringInputStream(xmlData.get(3), "UTF-8");
				zos.putNextEntry(new ZipEntry("siteCategory_back.xml"));
				while ((len = ins.read(buffer)) != -1) {
					zos.write(buffer, 0, len);
				}
			}
			
			// 如果有相关的采集模板数据,则将采集模板数据写入压缩包中
			if (xmlData.size() > 1) {
				len = -1;
				ins = new StringInputStream(xmlData.get(1), "UTF-8");
				zos.putNextEntry(new ZipEntry("site_fetchtemplate_back.xml"));
				while ((len = ins.read(buffer)) != -1) {
					zos.write(buffer, 0, len);
				}
			}
			//如果有相关的采集模板类型数据，则将采集模板类型数据写入到压缩包中
			if (xmlData.size() > 2) {
				len = -1;
				ins = new StringInputStream(xmlData.get(2), "UTF-8");
				zos.putNextEntry(new ZipEntry("site_fetchtemplate_category_back.xml"));
				while ((len = ins.read(buffer)) != -1) {
					zos.write(buffer, 0, len);
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				ins.close();
				zos.close();
				outputStream.flush();
				outputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 导出查询出的站点
	 * @param site
	 * @return 
	 * @author WangWei
	 */
	@RequestMapping("/backupQuery.do")
	public void backupQuery(HttpServletRequest request,HttpServletResponse response, Site site) {
		PageResult pageResult = new PageResult();
		pageResult.setPageSize(Integer.MAX_VALUE);
		PageResult result = siteService.findByPage(site, pageResult);// userService.findAll();
		List siteList = result.getRows();
		if(ValidateObject.hasValueInCollection(siteList)){
			String[] id = new String[siteList.size()];
			for(int i=0;i<siteList.size();i++){
				Site s = (Site) siteList.get(i);
				id[i] = s.getId();
			}
			backupSiteDate(request, response, id);
		}
	}
	
	/**
	 * 跳转到站点导入页面
	 * @return
	 * @author WangWei
	 */
	@RequestMapping("/toRestore.do")
	public String toRestore() {
		return "/module/sitecrawler/site/resotre.jsp";
	}
	/**
	 * 站点数据恢复
	 * @return 
	 */
	@RequestMapping(value="/restore.do", method=RequestMethod.POST) 
	public ModelAndView restore(HttpServletRequest request,@RequestParam MultipartFile sitesZip)throws Exception {
		String resultInfo = new String("alertMsg.info");
		File file = new File(request.getRealPath("/")+sitesZip.getName()+".zip");
		sitesZip.transferTo(file);
		if (sitesZip != null) {
			List<String> xmlData = new ArrayList<String>();
			try {
				ZipFile zipFile = new ZipFile(file);
				ZipEntry sitesEntry = zipFile.getEntry("site_back.xml");
				ZipEntry fetchTemplatesData = zipFile.getEntry("site_fetchtemplate_back.xml");
				ZipEntry templateCategoryData = zipFile.getEntry("site_fetchtemplate_category_back.xml");
				ZipEntry siteCategoryData = zipFile.getEntry("siteCategory_back.xml");
				if (sitesEntry == null) {
					resultInfo="文件格式错误,压缩包中未找到sie_back.xml";
				} else {
					BufferedReader br = new BufferedReader(new InputStreamReader(zipFile.getInputStream(sitesEntry), "UTF-8"));
					String line = "";
					StringBuilder sb = new StringBuilder();
					while ((line = br.readLine()) != null) {
						sb.append(line);
					}
					xmlData.add(sb.toString());
					br.close();
				}
				if (fetchTemplatesData != null) {
					BufferedReader br = new BufferedReader(new InputStreamReader(zipFile.getInputStream(fetchTemplatesData), "UTF-8"));
					String line = "";
					StringBuilder sb = new StringBuilder();
					while ((line = br.readLine()) != null) {
						sb.append(line);
					}
					xmlData.add(sb.toString());
					br.close();
				}
				if (templateCategoryData != null) {
					BufferedReader br = new BufferedReader(new InputStreamReader(zipFile.getInputStream(templateCategoryData), "UTF-8"));
					String line = "";
					StringBuilder sb = new StringBuilder();
					while ((line = br.readLine()) != null) {
						sb.append(line);
					}
					xmlData.add(sb.toString());
					br.close();
				}
				if(siteCategoryData!=null){
					BufferedReader br = new BufferedReader(new InputStreamReader(zipFile.getInputStream(siteCategoryData), "UTF-8"));
					String line = "";
					StringBuilder sb = new StringBuilder();
					while ((line = br.readLine()) != null) {
						sb.append(line);
					}
					xmlData.add(sb.toString());
					br.close();
				}
				siteService.restore(xmlData);
				resultInfo = "数据恢复成功!";
				zipFile.close();
			} catch (Exception e) {
				resultInfo = "数据恢复失败!请确认数据文件是否正确";
				e.printStackTrace();
			}
		}
		if(file.isFile()){
			file.delete();
		}
		ModelMap mmap = new ModelMap();
		mmap.put("resultInfo", resultInfo);
		ModelAndView mv = new ModelAndView("/module/sitecrawler/site/restoreResult.jsp");
		mv.addAllObjects(mmap);
		return mv;
	}
	/**
	 * 测试配置
	 */
	@RequestMapping("/test")
	public void test(HttpServletResponse response,String rule){
		List<String> urls = new ArrayList<String>();
		GenURL genURL = new GenURL();
		if (rule != null) {
			String[] urlregs = rule.split("\n");
			for (String urlreg : urlregs) {
				String[] results = null;
				try {
					results = genURL.parse(urlreg);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				if (results != null) {
					for (String tempUrl : results) {
						if (tempUrl != null && !urls.contains(tempUrl)) {// 这里的去重判断并不能完全去重。这不是一个bug，在地址库那里会再做一次去重。
							urls.add(tempUrl);
						}
					}
				}
			}
		}
		JSONArray jsonArray = JSONArray.fromObject(urls);
		try {
			response.setContentType("text/json; charset=UTF-8");
			System.out.println(jsonArray.toString());
			response.getWriter().print(jsonArray.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
