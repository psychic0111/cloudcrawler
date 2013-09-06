package com.xdtech.project.webservice;

import java.util.List;

import javax.jws.WebService;

import com.xdtech.project.webservice.bean.URLInfo;
import com.xdtech.project.webservice.bean.WebDBResult;
import com.xdtech.project.webservice.bean.WebDb;

/**
 * WBDBservice
 * 
 * @author zhangjianbing@msn.com
 * 
 */
@WebService(targetNamespace = "http://www.xd-tech.com")
public interface WebDBServiceInterface {
	/**
	 * 查看地址库中所有的地址表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String[] findTables() throws Exception;

	/**
	 * 添加数据，会对数据进行去重并组成新的数据结构
	 * 
	 * @param urls
	 *            待添加的数据
	 * @throws Exception
	 */
	public void addUrl(String tableName,URLInfo[] urls) throws Exception;

	/**
	 * 清空指定的地址库
	 * 
	 * @param name
	 *            地址库名称
	 * @throws Exception
	 */
	public void clearDB(String name) throws Exception;

	/**
	 * 根据检索条件检索数据
	 * 
	 * @param tableName
	 *            表名称
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
	public WebDBResult findWebDbByQuery(String tableName, String query, String order, int start, int pageNum) throws Exception;

	/**
	 * 删除某一条数据
	 * 
	 * @param tbname
	 *            数据表
	 * @param id
	 *            数据ID
	 * @throws Exception
	 */
	public void delete(String tbname, String id) throws Exception;

	/**
	 * 更新状态接口
	 * 
	 * @param id
	 *            数据主键
	 * @param tableName
	 *            表名称
	 * @param type
	 *            状态
	 * @throws Exception
	 */
	public void update(String id, String tableName, int type) throws Exception;

	/**
	 * 从夫多张表中获取数据，数据是以入库时间升序排序的
	 * 
	 * @param tableName
	 *            地址表
	 * @param query
	 *            检索语句
	 * @param pageNum
	 *            数量
	 * @return
	 * @throws Exception
	 */
	public List<WebDb> findWebDbByQueryFromTable(String[] tableName, String query, int pageNum) throws Exception;

	/**
	 * 添加数据或者吧数据更新为未采集状态
	 * 
	 * @param urls
	 */
	public void addOrUpdateURLs(String tableName,URLInfo[] urls);

	/**
	 * 根据URL的MD5修改数据
	 * 
	 * @param urlMd5
	 * @param type
	 */
	public void updateByMD5(String[] urlMd5, int type);
}
