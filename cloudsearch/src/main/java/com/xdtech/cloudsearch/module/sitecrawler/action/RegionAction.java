package com.xdtech.cloudsearch.module.sitecrawler.action;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xdtech.cloudsearch.module.base.action.BaseAction;
import com.xdtech.cloudsearch.module.base.page.PageResult;
import com.xdtech.cloudsearch.module.sitecrawler.bean.Region;
import com.xdtech.cloudsearch.module.sitecrawler.service.RegionService;
import com.xdtech.cloudsearch.module.webcrawler.bean.Keyword;
import com.xdtech.cloudsearch.webservice.xdservice.DataCache;

/**
 * 地区-Action
 * 
 * @author WangWei
 * 2013-06-06
 */
@Controller
@RequestMapping("/region")
public class RegionAction  extends BaseAction{
	@Autowired(required = true)
	private RegionService regionService;
	
	/**
	 * 地区管理列表页
	 * @param region
	 * @return 
	 * @author WangWei
	 */
	@RequestMapping("/region.do")
	public  ModelAndView index(Region region,@ModelAttribute PageResult pageReuslt) {
		if (pageReuslt == null) {
			pageReuslt = new PageResult();
		}
		ModelMap mmap = new ModelMap();
		PageResult pageResult = regionService.findByPage(region, pageReuslt);// userService.findAll();
		mmap.put("list", pageResult.getRows());
		mmap.put("pageResult", pageResult);
		ModelAndView mv = new ModelAndView("/module/sitecrawler/region/index.jsp");
		mv.addAllObjects(mmap);
		return mv;
	}
	/**
	 * 地区管理列表页
	 * @param region
	 * @return 
	 * @author WangWei
	 */
	@RequestMapping("/regionList.do")
	public  ModelAndView list(Region region,@ModelAttribute PageResult pageReuslt) {
		ModelMap mmap = new ModelMap();
		PageResult pageResult = regionService.findByPage(region, pageReuslt);// userService.findAll();
		mmap.put("list", pageResult.getRows());
		mmap.put("pageResult", pageResult);
		ModelAndView mv = new ModelAndView("/module/sitecrawler/region/regionList.jsp");
		mv.addAllObjects(mmap);
		return mv;
	}
	/**
	 * 地区管理列表页
	 * @param region
	 * @return 
	 * @author WangWei
	 */
	@RequestMapping("/regionContentList.do")
	public  ModelAndView contentList(Region region,@ModelAttribute PageResult pageReuslt) {
		ModelMap mmap = new ModelMap();
		PageResult pageResult = regionService.findByPageC(region, pageReuslt);// userService.findAll();
		mmap.put("list", pageResult.getRows());
		mmap.put("pageResult", pageResult);
		ModelAndView mv = new ModelAndView("/module/sitecrawler/region/list.jsp");
		mv.addAllObjects(mmap);
		return mv;
	}
	/**
	 * 地区管理列表页
	 * @param region
	 * @return 
	 * @author WangWei
	 */
	@RequestMapping("/regionListTree.do")
	public  ModelAndView listTree(Region region,@ModelAttribute PageResult pageReuslt) {
		ModelMap mmap = new ModelMap();
		PageResult pageResult = regionService.findByPageTree(region, pageReuslt);// userService.findAll();
		mmap.put("list", pageResult.getRows());
		mmap.put("pageResult", pageResult);
		ModelAndView mv = new ModelAndView("/module/sitecrawler/region/list.jsp");
		mv.addAllObjects(mmap);
		return mv;
	}
	/**
	 * 跳转到地区添加页面
	 * @return
	 * @author WangWei
	 */
	@RequestMapping("/add.do")
	public String add() {
		return "/module/sitecrawler/region/add.jsp";
	}
	
	/**
	 * 地区添加
	 * @return
	 * @author WangWei
	 */
	@RequestMapping("/doAdd.do")
	public @ResponseBody String doAdd(Region region) {
		region.setParentId(region.getParentId().replace("_1", "").replace("_0", ""));
		region.setDeleted(1);
		regionService.save(region);
		DataCache.initRegion();
		return "true";
	}
	
	/**
	 *查看地区是否存在
	 * @return
	 * @author WangWei
	 */
	@RequestMapping("/checkRegion")
	public @ResponseBody String checkRegion(Region region) {
		return regionService.checkRegion(region);
	}
	/**
	 * 地区删除
	 * @return
	 * @author WangWei
	 */
	@RequestMapping("/del.do")
	public @ResponseBody String delete(String id[]) {
		regionService.updateAll(Region.class,id,"deleted",0 );
		DataCache.initRegion();
		return "true";
	}
	/**
	 * 跳转到地区修改页面
	 * @return
	 * @author WangWei
	 */
	@RequestMapping("/edit.do")
	public ModelAndView edit(String id) {
		Region region = regionService.findById(Region.class, id);
		return new ModelAndView("/module/sitecrawler/region/edit.jsp", "region", region);
	}
    
	/**
	 *地区修改
	 * @return
	 * @author WangWei
	 */
	@RequestMapping("/editDo")
	public @ResponseBody String editDo(Region region) {
		region.setParentId(region.getParentId().replace("_1", "").replace("_0", ""));
		regionService.update(region);
		DataCache.initRegion();
		return "true";
	}
	/**
	 * 菜单树形结构展示.提供xml
	 * 
	 * @return
	 */
	@RequestMapping("/treeXml")
	public void treeXml(HttpServletResponse response,Region region,@ModelAttribute PageResult pageReuslt,String selectedId) {
		try {
			StringBuilder strb = new StringBuilder();
			strb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
			strb.append("<tree id=\"0\" radio=\"1\">");
			// 查找根节点根节点
			region.setParentId("0");
			List<Region> regions = regionService.findCountry();
			// 找到所有的一级节点,pid =1
			for(Region r:regions){
				strb.append(nodeXml(r,selectedId));
			}
			strb.append("</tree>");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/xml");
			System.out.println(strb.toString());
			response.getWriter().println(strb.toString());
			response.getWriter().close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 返回一个节点的xml
	 * 
	 * @param node
	 * @return
	 */
	private String nodeXml(Region node,String selectedId) {
		StringBuilder strb = new StringBuilder();
		//if(regionService.checkRegionExistsChildren(node.getId())){
			String hasChildren = "0";
			List<Region> regions = null;
			if(node.getId().length() < 6){
				regions = regionService.findChildrenByParentId(node.getId());
			}
			node.setChildList(regions);
			if(null != node.getChildList() && node.getChildList().size() > 0){
				hasChildren = "1";
			}
			String xml = "<item text=\"" + node.getAreaName() + "\" id=\"" + node.getId() +"_"+hasChildren+ "\" ";
			strb.append(xml);
			if (selectedId.equals(node.getId())) {
				strb.append(" open =\"1\"  select=\"1\" call=\"1\" ");
			}
			strb.append(" > ");
			int length = node.getChildList() == null || node.getChildList().size() <= 0 ? 0 : node.getChildList().size();
			for (int i = 0; i < length; i++) {
				strb.append(nodeXml(node.getChildList().get(i),selectedId));
			}
			strb.append("</item>");
		//}
		return strb.toString();
	}
}
