package com.xdtech.cloudsearch.module.sitecrawler.action;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xdtech.cloudsearch.module.base.action.BaseAction;
import com.xdtech.cloudsearch.module.base.page.PageResult;
import com.xdtech.cloudsearch.module.sitecrawler.bean.NetParam;
import com.xdtech.cloudsearch.module.sitecrawler.bean.Site;
import com.xdtech.cloudsearch.module.sitecrawler.service.NetParamService;
/**
 * 网络参数设置Action
 * @author LZF
 *
 */
@Controller
@RequestMapping("/netParam")
public class NetParamAction extends BaseAction{
	@Autowired(required = true)
	private NetParamService netParamService;
	/**
	 * 获得网络参数设置数据进index页面
	 * @return
	 */
	@RequestMapping("/netParam.do")
	public ModelAndView index (NetParam netParam,@ModelAttribute PageResult pageReuslt){
		if (pageReuslt == null) {
			pageReuslt = new PageResult();
		}
		ModelMap mmap = new ModelMap();
		List<NetParam> netParams = netParamService.getDao().getTemplate().find(" from NetParam ");
		if(null != netParams && netParams.size() > 0){
			netParam = netParams.get(0);
		}else{
			netParam = new NetParam();
			netParam.setConnetCount(5);
			netParam.setTimeoutSecond(20);
			netParam.setWaiCount(5);
			netParam.setReferCount(5);
		}
		mmap.put("netParam", netParam);
		ModelAndView mv = new ModelAndView("/module/sitecrawler/netParam/index.jsp");
		mv.addAllObjects(mmap);
		return mv;
	}
	/**
	 * 修改网络参数
	 * @param site
	 * @return
	 */
	@RequestMapping("/editParamDo")
	public @ResponseBody String editDo(NetParam netParam) {
		if(netParam.getId().equals("")){
			netParam.setId(null);
		}
		netParamService.saveOrUpdate(netParam);
		return "true";
	}
	/**
	 * 获得网络参数设置数据进index页面
	 * @return
	 */
	@RequestMapping("/netParamSuccess.do")
	public ModelAndView indexSuccess (NetParam netParam,@ModelAttribute PageResult pageReuslt){
		if (pageReuslt == null) {
			pageReuslt = new PageResult();
		}
		ModelMap mmap = new ModelMap();
		mmap.put("netParam", netParamService.getDao().getTemplate().find(" from NetParam ").get(0));
		ModelAndView mv = new ModelAndView("/module/sitecrawler/netParam/edit.jsp");
		mv.addAllObjects(mmap);
		return mv;
	}
}
