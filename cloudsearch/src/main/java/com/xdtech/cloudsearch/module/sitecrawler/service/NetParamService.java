package com.xdtech.cloudsearch.module.sitecrawler.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.xdtech.cloudsearch.module.base.service.BaseService;
/**
 * 网络参数配置service
 * @author LZF
 *
 */
@Service(value="netParamService")
@Transactional(rollbackFor=Exception.class)
public class NetParamService extends BaseService{
	
}
