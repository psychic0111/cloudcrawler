package com.xdtech.cloudsearch.module.sitecrawler.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import java.util.List;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xdtech.cloudsearch.module.base.page.PageResult;
import com.xdtech.cloudsearch.module.base.service.BaseService;
import com.xdtech.cloudsearch.module.sitecrawler.bean.Plugin;
import com.xdtech.cloudsearch.module.sitecrawler.bean.Site;
import com.xdtech.cloudsearch.module.sitecrawler.bean.Template;
import com.xdtech.cloudsearch.module.sitecrawler.bean.TemplateCategory;

/**
 * 模板分类-Service
 * 
 * @author WangWei
 * 2013-06-07
 */
@Service(value="templateCategoryService")
public class TemplateCategoryService extends BaseService{
	@Autowired(required = true)
	private TemplateService templateService;
	/**检查模板分类是否存在
	 * 
	 * @param region
     * @author WangWei
     * @return
     */
	public String checkTemplateCategory(TemplateCategory templateCategory){
		StringBuffer queryString =new StringBuffer("select count(*) from TemplateCategory tc where 1 = 1 ");
		List<Object> list =new ArrayList<Object>();
		if(templateCategory.getId()!=null&&!templateCategory.getId().equals("")){
			queryString.append(" and tc.id<>?  ");
			list.add(templateCategory.getId());
		}
		if(templateCategory.getName()!=null&&!templateCategory.getName().equals("")){
			queryString.append("and tc.name=?  ");
			list.add(templateCategory.getName());
		}
		if(templateCategory.getParentId()!=null&&!templateCategory.getParentId().equals("")){
			queryString.append("and tc.parentId=?  ");
			list.add(templateCategory.getParentId());
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
	 * @param TemplateCategory
	 * @author WangWei
	 * @param pageResult
	 * @return
	 */
	public PageResult findByPage(TemplateCategory templateCategory,PageResult pageResult){
		String queryString  = "from TemplateCategory where 1 = 1";
		if(null!=templateCategory){
			if(null!=templateCategory.getName()&&!"".equals(templateCategory.getName())){
				queryString =queryString +" and name like '%"+templateCategory.getName()+"%'";
			}
			if(null!=templateCategory.getParentId()&&!"".equals(templateCategory.getParentId())){
				queryString =queryString +" and parentId = '"+templateCategory.getParentId()+"'";
			}
		}
		List<TemplateCategory> list=null;
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
	 * 分页
	 * @param TemplateCategory
	 * @author WangWei
	 * @param pageResult
	 * @return
	 */
	public PageResult getAll(TemplateCategory templateCategory,PageResult pageResult){
		pageResult.setPageSize(10000);
		String queryString  = "from TemplateCategory where 1 = 1";
		if(null!=templateCategory){
			if(null!=templateCategory.getName()&&!"".equals(templateCategory.getName())){
				queryString =queryString +" and name like '%"+templateCategory.getName()+"%'";
			}
			if(null!=templateCategory.getParentId()&&!"".equals(templateCategory.getParentId())){
				queryString =queryString +" and parentId = '"+templateCategory.getParentId()+"'";
			}
		}
		List<TemplateCategory> list=null;
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
	 * 分页
	 * @param TemplateCategory
	 * @author WangWei
	 * @param pageResult
	 * @return
	 */
	public PageResult findByPageC(TemplateCategory templateCategory,PageResult pageResult){
		String queryString  = "from TemplateCategory where 1 = 1";
		if(null!=templateCategory){
			if(null!=templateCategory.getId()&&!"".equals(templateCategory.getId())){
				queryString =queryString +" and parentId = '"+templateCategory.getId()+"'";
			}
		}
		List<TemplateCategory> list=null;
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
	 * 分页
	 * @param TemplateCategory
	 * @author WangWei
	 * @param pageResult
	 * @return
	 */
	public PageResult findByPageTree(TemplateCategory TemplateCategory,PageResult pageResult){
		String queryString  = "from TemplateCategory where 1 = 1";
		if(null!=TemplateCategory){
			if(null!=TemplateCategory.getName()&&!"".equals(TemplateCategory.getName())){
				queryString =queryString +" and name like '%"+TemplateCategory.getName()+"%'";
			}
			if(null!=TemplateCategory.getParentId()&&!"".equals(TemplateCategory.getParentId())){
				queryString =queryString +" and parentId = '"+TemplateCategory.getParentId().replace("_1", "").replace("_0", "")+"'";
			}
		}
		List<TemplateCategory> list=null;
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
	
	
	/**获取所有的一级分类
     * @author WangWei
     * 2013-06-08
     * @return
     */
	public List<TemplateCategory> getAllFristLevel(){
		String queryString = "from TemplateCategory where categoryLevel = 1 and parentId != '0'";
		List<TemplateCategory> list = find(queryString);
		return list;
	}
	/**
	 * 获取所有的二级分类
	 * @param parentId 父ID
	 * @return
	 */
	public List<TemplateCategory> getAllSecondLevel(String parentId){
		String queryString = "from TemplateCategory where parentId = '"+parentId+"'";
		List<TemplateCategory> list = find(queryString);
		return list;
	}
	/**根据父类查找
     * @author WangWei
     * @return
     */
	public List<TemplateCategory> findChildrenByParentId(String parentId){
		String queryString = "from TemplateCategory where parentId = '"+parentId+"'";
		List<TemplateCategory> list = find(queryString);
		return list;
	}
	/**
	 * 返回一个节点的xml
	 * 
	 * @param node
	 * @return
	 */
	public String nodeXml(TemplateCategory node,String selectedId,Map<String, Integer> categoryAndNubers) {
		StringBuilder strb = new StringBuilder();
		Integer number = categoryAndNubers.get(node.getId());
		if(null == number){
			number = 0;
		}
		String hasChildren = "0";
		if(null != node.getChildList() && node.getChildList().size() > 0){
			hasChildren = "1";
		}
		String xml = "<item text=\"" + node.getName() +"(number"+node.getId()+")"+ "\" id=\"" + node.getId() +"_"+hasChildren+ "\" ";
		strb.append(xml);
		if (selectedId.equals(node.getId())) {
			strb.append(" open =\"1\"  select=\"1\" call=\"1\" ");
		}
		strb.append(" > ");
		int length = node.getChildList() == null? 0 : node.getChildList().size();
		for (int i = 0; i < length; i++) {
			String categoryId = node.getChildList().get(i).getId();
			Integer childNumber =  categoryAndNubers.get(categoryId)==null?0:categoryAndNubers.get(categoryId);
			strb.append(nodeXml(node.getChildList().get(i),selectedId,categoryAndNubers));
			number = number + childNumber;
			if(node.getCategoryLevel() == 0){
				Integer total = new Integer(0);
				for(Entry<String, Integer> entry:categoryAndNubers.entrySet()){
					total = total + (categoryAndNubers.get(entry.getKey())==null?0:entry.getValue());
				}
				number = total;
			}
		}
		Integer start = strb.indexOf("number"+node.getId());
		strb.replace(start, start+38,number.toString());
		strb.append("</item>");
		return strb.toString();
	}
	/**
	 * 获取所有的模板
	 * @return
	 */
	public Map<String, Template> getAllTemplateMap(){
		List<Template> templates = templateService.findAll();
		Map<String, Template> tempatesMap = new HashMap<String, Template>();
		for(Template t:templates){
			tempatesMap.put(t.getId(), t);
		}
		return tempatesMap;
	}
	/**
	 * 根据模板分类进行统计
	 * @return
	 */
	public Map<String, Integer> getNumberAndCategoryId(){
		String queryString = " select count(categoryId), categoryId from Template group by categoryId ";
		List templates = templateService.getDao().getTemplate().find(queryString);
		Map<String, Integer> categoryAndNubers = new HashMap<String, Integer>();
		for(Object t:templates){
			Object[] objs = (Object[])t;
			categoryAndNubers.put(objs[1].toString(), Integer.valueOf(objs[0].toString()));
		}
		return categoryAndNubers;
	}
	/**
	 * 将所有的分类和Id进行一一对应的映射
	 * @return
	 */
	public Map<String,TemplateCategory> getTemplateCategoryMap(){
		Map<String,TemplateCategory> map = new HashMap<String, TemplateCategory>();
		String queryString = " from TemplateCategory ";
		List<TemplateCategory> list = find(queryString);
		for(TemplateCategory tc:list){
			map.put(tc.getId(), tc);
		}
		return map;
	}
	/**
	 * 初始化子节点
	 * @param root
	 * @param map
	 */
	public void initRootChild(TemplateCategory root,Map<String,TemplateCategory> map){
		List<TemplateCategory> childrens = new ArrayList<TemplateCategory>();
		for(Entry<String, TemplateCategory> entry:map.entrySet()){
			TemplateCategory tc = entry.getValue();
			if(root.getId().equals(tc.getParentId())){
				childrens.add(tc);
			}
		}
		root.setChildList(childrens);
		int length = root.getChildList().size();
		for(int i = 0;i<length;i++){
			TemplateCategory tc = root.getChildList().get(i);
			List<TemplateCategory> secondChildrens = new ArrayList<TemplateCategory>();
			for(Entry<String, TemplateCategory> e:map.entrySet()){
				TemplateCategory stc = e.getValue();
				if(tc.getId().equals(stc.getParentId())){
					secondChildrens.add(stc);
				}
			}
			tc.setChildList(secondChildrens);
			root.getChildList().set(i, tc);
		}
	}

	/**
	 * 返回一个节点的xml
	 * 
	 * @param node
	 * @return
	 */
	public String nodeXmlCategory(TemplateCategory node,String selectedId,Map<String, Integer> categoryAndNubers) {
		StringBuilder strb = new StringBuilder();
		String hasChildren = "0";
		if(null != node.getChildList() && node.getChildList().size() > 0){
			hasChildren = "1";
		}
		String xml = "<item text=\"" + node.getName() + "\" id=\"" + node.getId() +"_"+hasChildren+ "\" ";
		strb.append(xml);
		if (selectedId.equals(node.getId())) {
			strb.append(" open =\"1\"  select=\"1\" call=\"1\" ");
		}
		strb.append(" > ");
		int length = node.getChildList() == null? 0 : node.getChildList().size();
		for (int i = 0; i < length; i++) {
			strb.append(nodeXmlCategory2(node.getChildList().get(i),selectedId,categoryAndNubers));
		}
		strb.append("</item>");
		return strb.toString();
	}
	public String nodeXmlCategory2(TemplateCategory node,String selectedId,Map<String, Integer> categoryAndNubers) {
		StringBuilder strb = new StringBuilder();
		String hasChildren = "0";
		if(null != node.getChildList() && node.getChildList().size() > 0){
			hasChildren = "1";
		}
		String xml = "<item text=\"" + node.getName() + "\" id=\"" + node.getId() +"_"+hasChildren+ "\" ";
		strb.append(xml);
		if (selectedId.equals(node.getId())) {
			strb.append(" open =\"1\"  select=\"1\" call=\"1\" ");
		}
		strb.append(" > ");
		strb.append("</item>");
		return strb.toString();
	}
}
