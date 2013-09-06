package com.xdtech.cloudsearch.module.sitecrawler.service;

import java.util.List;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xdtech.cloudsearch.module.base.service.BaseService;
import com.xdtech.cloudsearch.module.sitecrawler.bean.Plugin;

/**
 * 插件-Service
 * 
 * @author WangWei
 * 2013-06-05
 */
@Service(value="pluginService")
@Transactional(rollbackFor=Exception.class)
public class PluginService  extends BaseService {
	/**查询所有插件
     * @author WangWei
     * @return
     */
	public List<Plugin> findAll(){
		String queryString = "from Plugin ";
		List<Plugin> list = find(queryString);
		return list;
	}
}
