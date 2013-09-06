package com.xdtech.cloudsearch.module.sitecrawler.action;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xdtech.cloudsearch.module.sitecrawler.service.PluginParamService;

/**
 * 插件参数-Action
 * 
 * @author WangWei
 * 2013-06-05
 */
@Controller
@RequestMapping("/pluginParam")
public class PluginParamAction {
	@Autowired(required = true)
	private PluginParamService pluginParamService;
}
