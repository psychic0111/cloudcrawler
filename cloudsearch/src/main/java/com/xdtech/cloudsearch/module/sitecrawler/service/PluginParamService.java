package com.xdtech.cloudsearch.module.sitecrawler.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xdtech.cloudsearch.module.base.service.BaseService;

/**
 * 插件-Service
 * 
 * @author WangWei
 * 2013-06-05
 */
@Service(value="pluginParamService")
@Transactional(rollbackFor=Exception.class)
public class PluginParamService  extends BaseService {

}
