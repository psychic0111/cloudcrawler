package com.xdtech.cloudsearch.module.sitecrawler.action;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xdtech.cloudsearch.module.sitecrawler.service.PluginService;

/**
 * 插件-Action
 * 
 * @author WangWei
 * 2013-06-05
 */
@Controller
@RequestMapping("/plugin")
public class PluginAction {
	@Autowired(required = true)
	private PluginService pluginService;
}
