package com.xdtech.project.webservicecrawler;

import java.util.List;



import javax.jws.WebService;

import com.xdtech.project.webservicecrawler.bean.URLInfoCrawler;
import com.xdtech.project.webservicecrawler.bean.WebDBCrawlerResult;
import com.xdtech.project.webservicecrawler.bean.WebDbCrawler;


/**
 * WBDBservice
 * 
 * @author zhangjianbing@msn.com
 * 
 */
@WebService(targetNamespace = "http://www.xd-tech.com")
public interface WebDBCrawlerServiceInterface {

	/**
	 * 添加数据，会对数据进行去重并组成新的数据结构
	 * 
	 * @param urls
	 *            待添加的数据
	 * @throws Exception
	 */
	public void addUrl(URLInfoCrawler[] urls) throws Exception;

	/**
	 * 清空指定的地址库
	 *            地址库名称
	 * @throws Exception
	 */
	public void clearDB() throws Exception;

	/**
	 * 根据检索条件检索数据
	 * @param query
	 *            条件，条件中应该没有where
	 * @param order
	 *            排序，如 id desc
	 * @param start
	 *            开始位置
	 * @param pageNum
	 *            页面尺寸
	 * @return
	 * @throws Exception
	 */
	public WebDBCrawlerResult findWebDbByQuery(String query, String order, int start, int pageNum) throws Exception;

	/**
	 * 删除某一条数据
	 * @param id
	 *            数据ID
	 * @throws Exception
	 */
	public void delete(String id) throws Exception;

	/**
	 * 更新状态接口
	 * 
	 * @param id
	 *            数据主键
	 * @param type
	 *            状态
	 * @throws Exception
	 */
	public void update(String id, int type) throws Exception;

	/**
	 * 从夫多张表中获取数据，数据是以入库时间升序排序的
	 * @param query
	 *            检索语句
	 * @param pageNum
	 *            数量
	 * @return
	 * @throws Exception
	 */
	public List<WebDbCrawler> findWebDbByQueryFromTable(String query, int pageNum) throws Exception;

	/**
	 * 添加数据或者吧数据更新为未采集状态
	 * 
	 * @param urls
	 */
	public void addOrUpdateURLs(URLInfoCrawler[] urls);

	/**
	 * 根据URL的MD5修改数据
	 * 
	 * @param urlMd5
	 * @param type
	 */
	public void updateByMD5(String[] urlMd5, int type);
}
