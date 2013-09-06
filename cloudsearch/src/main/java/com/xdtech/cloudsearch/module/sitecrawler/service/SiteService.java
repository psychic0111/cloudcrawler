package com.xdtech.cloudsearch.module.sitecrawler.service;

import java.util.ArrayList;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import java.util.List;

import org.springframework.stereotype.Service;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.xdtech.cloudsearch.module.base.page.PageResult;
import com.xdtech.cloudsearch.module.base.service.BaseService;
import com.xdtech.cloudsearch.module.sitecrawler.bean.Site;
import com.xdtech.cloudsearch.module.sitecrawler.bean.SiteCategory;
import com.xdtech.cloudsearch.module.sitecrawler.bean.Template;
import com.xdtech.cloudsearch.module.sitecrawler.bean.TemplateCategory;

/**
 * 站点-Service
 * 
 * @author WangWei
 * 2013-06-13
 */
@Service(value="siteService")
public class SiteService  extends BaseService {
	/**查询所有站点
     * @author WangWei
     * @return
     */
	public List<Site> findAll(){
		String queryString = "from Site ";
		List<Site> list = find(queryString);
		return list;
	}
	/**检查站点是否存在
	 * 
	 * @param Site
     * @author WangWei
     * @return
     */
	public String checkSite(Site site){
		StringBuffer queryString =new StringBuffer("select count(*) from Site s where 1=1 ");
		List<Object> list =new ArrayList<Object>();
		if(site.getId()!=null&&!site.getId().equals("")){
			queryString.append(" and s.id<>?  ");
			list.add(site.getId());
		}
		if(site.getName()!=null&&!site.getName().equals("")){
			queryString.append("and s.name=?  ");
			list.add(site.getName());
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
	 * @param Site
	 * @param pageResult
	 * @return
	 */
	public PageResult findByPage(Site site,PageResult pageResult){
		String queryString  = "from Site where 1 = 1";
		if(null!=site){
			if(null!=site.getName()&&!"".equals(site.getName())){
				queryString =queryString +" and name like '%"+site.getName()+"%'";
			}
			if(null!=site.getSiteCategoryId()&&!"".equals(site.getSiteCategoryId())){
				queryString =queryString +" and siteCategoryId = '"+site.getSiteCategoryId()+"'";
			}
		}
		List<Site> list=null;
		queryString = queryString +" order by operateTime desc ";
		try {
			list = findByPage(queryString, pageResult.getPageNo(), pageResult.getPageSize());
		} catch (Exception e) {
			e.printStackTrace();
		}
		pageResult.setRows(list);
		pageResult.setTotal(getCount(queryString));
		return pageResult;
	}
	/**
	 * 所有-站点数据备份，包括站点，模板，模板类型的备份
	 * @return
	 */
	public List<String> backup(){
		String queryString = "select s.id from Site s";
		List<String> list = find(queryString);
		return backup(list.toArray(new String[list.size()]));
	}
	/**
	 * 选中-站点数据备份，包括站点，模板，模板类型的备份
	 * @param siteIds
	 * @return
	 */
	public List<String> backup(String[] siteIds){
		List<String> xmlData = new ArrayList<String>();
		XStream xStream = new XStream();
		List<Site> sites = null;
		if(siteIds == null){
			String queryString = " from Site ";
			sites = find(queryString);
		}else{
			StringBuffer sb = new StringBuffer("");
			for(String s:siteIds){
				if(null != s && !s.trim().equals("")){
					if(!"".equals(sb.toString())){
						sb.append(",");
					}
					sb.append("'").append(s).append("'");
				}
			}
			sites = backupSite(sb.toString());
		}
		if(null != sites && sites.size() > 0){
			xmlData.add(xStream.toXML(sites));
		}
		List<Template> templates = null;
		if(null != sites && sites.size() > 0){
			templates = backupTemplate(sites);
			if(null != templates && templates.size() > 0){
				xmlData.add(xStream.toXML(templates));
			}
		}
		if(null != templates && templates.size() > 0){
			List<TemplateCategory> templateCategorys = backupTemplateCategory(templates);
			if(null != templateCategorys && templateCategorys.size() > 0){
				xmlData.add(xStream.toXML(templateCategorys));
			}
		}
		if(null != sites && sites.size() > 0){
			List<SiteCategory> siteCategorys = backSiteCategory(sites);
			if(siteCategorys!=null && siteCategorys.size()>0){
				xmlData.add(xStream.toXML(siteCategorys));
			}
		}
		return xmlData;
	}
	/**
	 * 根据站点多个ID查询站点
	 * @param siteIds
	 */
	public List<Site> backupSite(String siteIds){
		String queryString = " from Site where id in ("+siteIds+")";
		List<Site> sites = find(queryString);
		return sites;
	}
	/**
	 * 备份站点分类
	 * @return
	 */
	public List<SiteCategory> backSiteCategory(){
		
		return null;
	}
	/**
	 * 备份站点模板
	 * @param siteIds
	 */
	public List<Template> backupTemplate(List<Site> sites){
		StringBuffer sb = new StringBuffer("");
		String templateIds = "";
		for(Site site:sites){
			templateIds = site.getTemplateIds();
			if(templateIds == null){
				continue;
			}
			String[] tids = templateIds.split(",");
			for(String id:tids){
				if(null != id && !id.trim().equals("")){
					if(!"".equals(sb.toString())){
						sb.append(",");
					}
					sb.append("'").append(id).append("'");
				}
			}
		}
		List<Template> templates = null;
		if(!"".equals(sb.toString())){
			String queryString = " from Template where id in ("+sb.toString()+")";
			templates = find(queryString);
		}
		return templates;
	}
	/**
	 * 备份站点分类
	 * @param sites
	 * @return
	 */
	public List<SiteCategory> backSiteCategory(List<Site> sites){
		StringBuffer sb = new StringBuffer("");
		for(Site site :sites){
			if(!"".equals(sb.toString())){
				sb.append(",");
			}
			sb.append("'").append(site.getSiteCategoryId()).append("'");
		}
		List<SiteCategory> siteCategoryList = null;
		if(!"".equals(sb.toString())){
			String queryString = "from SiteCategory where id in("+sb.toString()+")";
			siteCategoryList = find(queryString);
		}
		return siteCategoryList;
	}
	/**
	 * 查询模板站点模板类型
	 * @param siteIds
	 */
	public List<TemplateCategory> backupTemplateCategory(List<Template> templates){
		List<TemplateCategory> templateCategorys = null;
		StringBuffer sb = new StringBuffer("");
		for(Template template:templates){
			if(null != template.getCategoryId() && !template.getCategoryId().trim().equals("")){
				if(!"".equals(sb.toString())){
					sb.append(",");
				}
				sb.append("'").append(template.getCategoryId()).append("'");
			}
		}
		if(!"".equals(sb.toString())){
			String queryString = " from TemplateCategory where (id in ("+sb.toString()+")) or (id in ( select tc.parentId from TemplateCategory tc where (id in ("+sb.toString()+"))))";
			templateCategorys = find(queryString);
		}
		return templateCategorys;
	}
	/**
	 * 拼接删除条件
	 * @param dels
	 * @return
	 */
	public String getDelString(List<String> dels){
		StringBuffer sb = new StringBuffer("");
		for(String s:dels){
			if(!"".equals(s)){
				if(!"".equals(sb.toString())){
					sb.append(",");
				}
				sb.append("'").append(s).append("'");
			}
		}
		return sb.toString();
	}
	/**
	 * 数据恢复
	 * 
	 * @param xmlData
	 */
	public void restore(List<String> xmlData) throws Exception {
		List<Site> sites = null;
		List<SiteCategory> siteCategorys = null;
		List<Template> templates = null;
		List<TemplateCategory> templateCategorys = null;
		XStream xstream = new XStream(new DomDriver());
		// 从XML中解析出站点数据
		if (xmlData != null && xmlData.size() > 0) {
			xstream.processAnnotations(Site.class);
			sites = (List<Site>) xstream.fromXML(xmlData.get(0));
		}
		// 从XML中解析出采集模板数据
		if (xmlData != null && xmlData.size() > 1) {
			xstream.processAnnotations(Template.class);
			templates = (List<Template>) xstream.fromXML(xmlData.get(1));
		}
		// 从XML中解析出采集模板类型数据
		if (xmlData != null && xmlData.size() > 2) {
			xstream.processAnnotations(TemplateCategory.class);
			templateCategorys = (List<TemplateCategory>) xstream.fromXML(xmlData.get(2));
		}
		// 从XML中解析出站点分类数据
		if (xmlData != null && xmlData.size() > 0) {
			xstream.processAnnotations(SiteCategory.class);
			siteCategorys = (List<SiteCategory>) xstream.fromXML(xmlData.get(3));
		}
		//恢复模板分类
		Map<String,String> tconids = resotreTemplateCategory(templateCategorys);
		//恢复模板
		Map<String,String> tonids = resotreTemplate(templates, tconids);
		//恢复站点分类
		Map<String,String> cateIds = resotreSiteCategory(siteCategorys);
		//恢复站点
		resotreSite(sites, cateIds, tonids);
	}
	/**
	 * 数据恢复
	 * 
	 * @param xmlData
	 */
	public void restoreDeleteExists(List<String> xmlData) throws Exception {
		List<Site> sites = null;
		List<Template> templates = null;
		List<TemplateCategory> templateCategorys = null;
		XStream xstream = new XStream(new DomDriver());
		// 从XML中解析出站点数据
		if (xmlData != null && xmlData.size() > 0) {
			xstream.processAnnotations(Site.class);
			sites = (List<Site>) xstream.fromXML(xmlData.get(0));
		}
		// 从XML中解析出采集模板数据
		if (xmlData != null && xmlData.size() > 1) {
			xstream.processAnnotations(Template.class);
			templates = (List<Template>) xstream.fromXML(xmlData.get(1));
		}
		// 从XML中解析出采集模板类型数据
		if (xmlData != null && xmlData.size() > 2) {
			xstream.processAnnotations(TemplateCategory.class);
			templateCategorys = (List<TemplateCategory>) xstream.fromXML(xmlData.get(2));
		}
		//找到根目录
		TemplateCategory root = null;
		String roorSql = "from TemplateCategory where parentId = '0'";
		List<TemplateCategory> roots = this.getDao().findList(roorSql);
		if(null != roots && roots.size() > 0){
			root = roots.get(0);
		}
		//删除数据库中存在的模板分类
		
		Map<String,String> tconids = new HashMap<String, String>();
		StringBuffer sbtemplatecategoryids = new StringBuffer("");
		StringBuffer sbtemplatecategorynames = new StringBuffer("");
		int lenght = templateCategorys.size();
		for(int i = 0;i<lenght;i++){
			TemplateCategory tc = templateCategorys.get(i);
			//根据跟目录，分析一级分类的parentId
			if(tc.getCategoryLevel() == 1){
				tc.setParentId(root.getId());
			}
			templateCategorys.set(i, tc);
			if(!"".equals(sbtemplatecategoryids.toString())){
				sbtemplatecategoryids.append(",");
			}
			if(!"".equals(sbtemplatecategorynames.toString())){
				sbtemplatecategorynames.append(",");
			}
			sbtemplatecategoryids.append("'").append(tc.getId()).append("'");
			sbtemplatecategorynames.append("'").append(tc.getName()).append("'");
		}
		//根据一级分类sbtemplatecategorynames，分析一级分类的parentId
		String seltcsql ="select tc.id from TemplateCategory tc where (id in ("+sbtemplatecategoryids.toString()+")) or (name in ("+sbtemplatecategorynames.toString()+"))";
		List<String> deltcids = this.find(seltcsql);
		String allDeleteTCSql = getDelString(deltcids);
		if(!"".equals(allDeleteTCSql)){
			String dtcsql = " delete from TemplateCategory tc where tc.id in ("+allDeleteTCSql+")";
			this.getDao().getTemplate().bulkUpdate(dtcsql);
		}
		//排序，保证一级分类先添加，在添加二级分类，然后更新parentId
		Collections.sort(templateCategorys,new Comparator(){
			@Override
			public int compare(Object o1, Object o2) {
				// TODO Auto-generated method stub
				TemplateCategory t1 = (TemplateCategory)o1;
				TemplateCategory t2 = (TemplateCategory)o2;
				return t1.getCategoryLevel().compareTo(t2.getCategoryLevel());
			}
			
		});
		for(TemplateCategory tc:templateCategorys){
			String oldId = tc.getId();
			tc.setId(null);
			if(tc.getCategoryLevel() == 2){
				tc.setParentId(tconids.get(tc.getParentId()));
			}
			this.getDao().save(tc);
			//如果是一级分类，需要更新数据库中二级分类的parentID
			if(tc.getCategoryLevel() == 1){
				String updatesql = " update TemplateCategory set parentId = '"+tc.getId()+"' where parentId ='"+oldId+"'" ;
				this.getDao().getTemplate().bulkUpdate(updatesql);
			}
			tconids.put(oldId, tc.getId());
		}
		//删除数据库中存在的模板
		Map<String,String> tonids = new HashMap<String, String>();
		StringBuffer sbtemplateids = new StringBuffer("");
		StringBuffer sbtemplatenames = new StringBuffer("");
		lenght = templates.size();
		for(int i = 0;i<lenght;i++){
			Template template = templates.get(i);
			if(!"".equals(sbtemplateids.toString())){
				sbtemplateids.append(",");
			}
			if(!"".equals(sbtemplatenames.toString())){
				sbtemplatenames.append(",");
			}
			sbtemplateids.append("'").append(template.getId()).append("'");
			sbtemplatenames.append("'").append(template.getName()).append("'");
		}
		String seltemplatesql ="select t.id from Template t where (id in ("+sbtemplateids.toString()+")) or (name in ("+sbtemplatenames.toString()+"))";
		List<String> deltids = this.find(seltemplatesql);
		String allDeleteTSql = getDelString(deltids);
		if(!"".equals(allDeleteTSql)){
			String dtemplatesql = " delete from Template t where t.id in ("+allDeleteTSql+")";
			this.getDao().getTemplate().bulkUpdate(dtemplatesql);
		}
		for(Template t:templates){
			String oldId = t.getId();
			//更新模板所属的分类
			t.setCategoryId(tconids.get(t.getCategoryId()));
			t.setId(null);
			this.getDao().save(t);
			tonids.put(oldId, t.getId());
		}
		//删除数据库中存在的站点
		StringBuffer sbsiteids = new StringBuffer("");
		StringBuffer sbsitenames = new StringBuffer("");
		lenght = sites.size();
		for(int i = 0;i<lenght;i++){
			Site site = sites.get(i);
			if(!"".equals(sbsiteids.toString())){
				sbsiteids.append(",");
			}
			if(!"".equals(sbsitenames.toString())){
				sbsitenames.append(",");
			}
			sbsiteids.append("'").append(site.getId()).append("'");
			sbsitenames.append("'").append(site.getName()).append("'");
		}
		String selsitesql ="select s.id from Site s where (id in ("+sbsiteids.toString()+")) or (name in ("+sbsitenames.toString()+"))";
		List<String> delids = this.find(selsitesql);
		String allDeleteSql = getDelString(delids);
		if(!"".equals(allDeleteSql)){
			String dsitesql = " delete from Site temps where temps.id in ("+getDelString(delids)+")";
			this.getDao().getTemplate().bulkUpdate(dsitesql);
		}
		for(Site site:sites){
			String templateids = site.getTemplateIds();
			String[] tids = templateids.split(",");
			StringBuffer sb = new StringBuffer();
			for(String s :tids){
				if(!"".equals(sb.toString())){
					sb.append(",");
				}
				sb.append(s);
			}
			//更新站点的模板ID
			site.setTemplateIds(sb.toString());
			site.setId(null);
			this.getDao().save(site);
		}
	}
	/**
	 * 导入模板分类
	 * @param templateCategorys
	 */
	public Map<String,String> resotreTemplateCategory(List<TemplateCategory> templateCategorys){
		
		//根据名称站点备份文件中在数据库中存在的模板分类
		Map<String,String> tconids = new HashMap<String, String>();
		StringBuffer sbtemplatecategorynames = new StringBuffer("");
		int lenght = templateCategorys.size();
		for(int i = 0;i<lenght;i++){
			TemplateCategory tc = templateCategorys.get(i);
			if(!"".equals(sbtemplatecategorynames.toString())){
				sbtemplatecategorynames.append(",");
			}
			sbtemplatecategorynames.append("'").append(tc.getName()).append("'");
		}
		String seltcsql ="select tc.name from TemplateCategory tc where  name in ("+sbtemplatecategorynames.toString()+")";
		//找到重复的ID
		List<String> deltcids = this.find(seltcsql);
		//排序，保证一级分类先添加，在添加二级分类，然后更新parentId
		Collections.sort(templateCategorys,new Comparator(){
			@Override
			public int compare(Object o1, Object o2) {
				// TODO Auto-generated method stub
				TemplateCategory t1 = (TemplateCategory)o1;
				TemplateCategory t2 = (TemplateCategory)o2;
				return t1.getCategoryLevel().compareTo(t2.getCategoryLevel());
			}
			
		});
		//将不重复的数据添加的数据库中
		for(int i = 0;i<lenght;i++){
			TemplateCategory tc = templateCategorys.get(i);
			String oldId = tc.getId();
			if(tc.getCategoryLevel() == 2){
				tc.setParentId(tconids.get(tc.getParentId()));
			}
			if(!deltcids.contains(tc.getName())){
				System.out.println("old id:"+tc.getId()+"\t"+tc.getName());
				tc.setId(null);
				this.getDao().save(tc);
			}else{
				this.getDao().update(tc);
			}
			tconids.put(oldId, tc.getId());  //导入的ID与新ID
		}
		return tconids;
	}
	
	
	/**
	 * 导入模板
	 * @param templates
	 */
	public Map<String,String> resotreTemplate(List<Template> templates,Map<String,String> tconids){
		//根据名称模板备份文件中在数据库中存在的模板
		Map<String,String> tonids = new HashMap<String, String>();
		StringBuffer sbtemplatenames = new StringBuffer("");
		int lenght = templates.size();
		for(int i = 0;i<lenght;i++){
			Template template = templates.get(i);
			if(!"".equals(sbtemplatenames.toString())){
				sbtemplatenames.append(",");
			}
			sbtemplatenames.append("'").append(template.getName()).append("'");
		}
		String seltemplatesql ="select t.name from Template t where name in ("+sbtemplatenames.toString()+")";
		//找到重复的ID
		List<String> deltids = this.find(seltemplatesql);
		for(Template t:templates){
			String oldId = t.getId();
			t.setCategoryId(tconids.get(t.getCategoryId()));
			if(!deltids.contains(t.getName())){
				//更新模板所属的分类
				t.setId(null);
				this.getDao().save(t);
			}else{
				this.getDao().update(t);
			}
			tonids.put(oldId, t.getId());
		}
		return tonids;
	}
	/**
	 * 导入站点
	 * @param templates
	 */
	public void resotreSite(List<Site> sites,Map<String,String> cateIds, Map<String,String> tonids){
		//删除数据库中存在的站点
		StringBuffer sbsitenames = new StringBuffer("");
		int lenght = sites.size();
		for(int i = 0;i<lenght;i++){
			Site site = sites.get(i);
			if(!"".equals(sbsitenames.toString())){
				sbsitenames.append(",");
			}
			sbsitenames.append("'").append(site.getName()).append("'");
		}
		String selsitesql ="select s.name from Site s where name in ("+sbsitenames.toString()+")";
		List<String> delids = this.find(selsitesql);
		for(Site site:sites){
			String templateids = site.getTemplateIds();
			StringBuffer sb = new StringBuffer();
			if(templateids != null){
				String[] tids = templateids.split(",");
				for(String s :tids){
					if(!"".equals(sb.toString())){
						sb.append(",");
					}
					sb.append(tonids.get(s));
				}
			}
			//站点分类ID
			String cateId = site.getSiteCategoryId();
			site.setSiteCategoryId(cateIds.get(cateId));
			//更新站点的模板ID
			site.setTemplateIds(sb.toString());
			
			//插入站点
			if(!delids.contains(site.getName())){
				site.setId(null);
				this.getDao().save(site);
			}else{
				this.getDao().update(site);
			}
		}
	}
	
	/**
	 * 导入站点分类
	 * @param templates
	 */
	public Map<String,String> resotreSiteCategory(List<SiteCategory> siteCategorys){
		Map<String,String> tonids = new HashMap<String, String>();
		StringBuffer sbsitenames = new StringBuffer("");
		int lenght = siteCategorys.size();
		for(int i = 0;i<lenght;i++){
			SiteCategory siteCategory = siteCategorys.get(i);
			if(!"".equals(sbsitenames.toString())){
				sbsitenames.append(",");
			}
			sbsitenames.append("'").append(siteCategory.getName()).append("'");
		}
		String selsitesql ="select s.name from SiteCategory s where name in ("+sbsitenames.toString()+")";
		List<String> delids = this.find(selsitesql);
		for(SiteCategory siteCategory:siteCategorys){
			String oid = siteCategory.getId();
			if(!delids.contains(siteCategory.getName())){
				this.getDao().save(siteCategory);
			}
			tonids.put(oid, siteCategory.getId());
		}
		return tonids;
	}
	
}
