package com.xdtech.project.webservice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.jws.WebService;

import com.xdtech.project.modules.webdb.bean.WebDB;
import com.xdtech.project.modules.webdb.service.BerkeleyDBTool;
import com.xdtech.project.modules.webdb.service.DBService;
import com.xdtech.project.webservice.bean.URLInfo;
import com.xdtech.project.webservice.bean.WebDBResult;
import com.xdtech.project.webservice.bean.WebDb;

@WebService(endpointInterface = "com.xdtech.project.webservice.WebDBServiceInterface", targetNamespace = "http://www.xd-tech.com")
public class WebDBService implements WebDBServiceInterface {
	/**
	 * 获取地址库中所有配置的表
	 * 
	 * @return
	 */
	public String[] findTables() {
		DBService service = new DBService();
		return service.findTables();
	}

	/**
	 * 添加数据，会对数据进行去重并组成新的数据结构
	 */
	public void addUrl(String tableName,URLInfo[] urls) throws Exception {
		if (urls != null) {
			List<WebDB> dataList = new ArrayList<WebDB>(urls.length);
			Map<String, String> tempMap = new HashMap<String, String>();
			for (URLInfo info : urls) {
				String url = info.getUrl();
				if (url == null || url.trim().length() == 0) {
					continue;
				}
				url = url.trim();
				info.setUrl(url);
				String md5 = info.md5String();
				if (md5 != null && md5.length() > 0) {
					if (tempMap.containsKey(md5)) {
						continue;
					} else {
						tempMap.put(md5, "");
					}
					WebDB db = new WebDB();
					db.setId(UUID.randomUUID().toString().replace("-", ""));
					db.setUrl(info.getUrl());
					db.setStatus(0);
					db.setFcount(0);
					db.setIntime(new Date());
					db.setFtime(null);
					db.setSiteid(info.getSiteId());
					db.setNftime(new Date());
					db.setMd5(md5);
					db.setUrllevel(info.getLevel());
					db.setUrlType(info.getUrlType());
					db.setRefurl(info.getRefurl());
					dataList.add(db);
				}
			}
			DBService service = new DBService();
			service.addData(tableName,dataList);
		}
	}

	/**
	 * 清空某张表中的所有数据
	 * 
	 * @param name
	 * @throws Exception
	 */
	public void clearDB(String name) throws Exception {
		DBService service = new DBService();
		service.clear(name);
	}

	/**
	 * 
	 * @param tableName
	 * @param query
	 * @param order
	 * @param groupby
	 * @param start
	 * @param pageNum
	 * @return
	 * @throws Exception
	 */
	public WebDBResult findWebDbByQuery(String tableName, String query, String order, int start, int pageNum) throws Exception {
		WebDBResult result = new WebDBResult();
		result.setTotal(0);
		List<WebDb> dataList = new ArrayList<WebDb>();
		result.setDataList(dataList);
		StringBuilder sbSQL = new StringBuilder();
		sbSQL.append("SELECT * FROM (SELECT ROW_NUMBER() OVER(");
		if (order == null || order.trim().length() == 0) {
			order = "ORDER BY INTIME DESC";
		}
		sbSQL.append(order);
		sbSQL.append(")AS RowNumber,* FROM ");
		sbSQL.append(tableName);
		if (query != null && query.trim().length() > 0) {
			sbSQL.append(" WHERE ");
			sbSQL.append(query);
		}
		sbSQL.append(") T WHERE (RowNumber BETWEEN ");
		sbSQL.append(start);
		sbSQL.append(" AND ");
		sbSQL.append(start + pageNum);
		sbSQL.append(")");

		DBService service = new DBService();
		System.out.println(sbSQL.toString());
		List<WebDb> webdbList = service.select(sbSQL.toString(), tableName);
		if (webdbList != null) {
			dataList.addAll(webdbList);
		}
		String countSQL = "select count(*) from " + tableName;
		if (query != null && query.trim().length() > 0) {
			countSQL = countSQL + " where " + query;
		}
		int count = service.selectCount(countSQL);
		result.setTotal(count);
		return result;
	}

	/**
	 * 从多个表中获取数据并按照入庫時間升序排序
	 * 
	 * @param tableName
	 * @param query
	 * @param order
	 * @param start
	 * @param pageNum
	 * @return
	 * @throws Exception
	 */
	public List<WebDb> findWebDbByQueryFromTable(String[] tableName, String query, int pageNum) throws Exception {
		List<String> sqlArray = new ArrayList<String>();
		for (int i = 0; i < tableName.length; i++) {
			StringBuilder sbSQL = new StringBuilder();
			sbSQL.append("SELECT * FROM ");
			sbSQL.append(tableName[i]);
			if (query != null && query.trim().length() > 0) {
				sbSQL.append(" WHERE ");
				sbSQL.append(query);
			}
			sbSQL.append(" limit 0,");
			sbSQL.append(pageNum);
			sqlArray.add(sbSQL.toString());
		}
		DBService service = new DBService();
		List<WebDb> result = service.selectFromTables(sqlArray.toArray(new String[sqlArray.size()]), tableName);
		if (result != null) {
			Collections.sort(result, new Comparator<WebDb>() {
				public int compare(WebDb o1, WebDb o2) {
					return o1.getIntime().compareTo(o2.getIntime());
				}
			});
		}
		if (result.size() > pageNum) {
			result = result.subList(0, pageNum - 1);
		}
		return result;
	}

	/**
	 * 根据条件删除数据
	 * 
	 * @param tbname
	 *            表名称
	 * @param query
	 *            条件
	 * @throws Exception
	 */
	public void delete(String tbname, String id) throws Exception {
		String sql = "delete from " + tbname + " where id='" + id + "'";
		DBService service = new DBService();
		try {
			List<WebDb> dataList = service.select("select * from " + tbname + " where id='" + id + "'", tbname);
			if (dataList != null && !dataList.isEmpty()) {
				WebDb webdb = dataList.get(0);
				if (webdb != null) {
					String md5 = webdb.getMd5();
					BerkeleyDBTool.delete(md5, tbname);
				}
			}
			service.delete(sql);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 更新数据
	 * 
	 * @param id
	 *            主键
	 * @param tableName
	 *            表名称
	 * @param type
	 *            0：失败 1：成功 2：失败次数未达到上限
	 * @param fcount
	 *            抓取次数
	 */
	public void update(String id, String tableName, int type) throws Exception {
		DBService service = new DBService();
		try {
			service.update(id, tableName, type);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 更新或者添加数据
	 */
	public void addOrUpdateURLs(String tableName,URLInfo[] urls) {
		if (urls != null) {
			List<WebDB> dataList = new ArrayList<WebDB>(urls.length);
			Map<String, String> tempMap = new HashMap<String, String>();
			for (URLInfo info : urls) {
				String url = info.getUrl();
				if (url == null || url.trim().length() == 0) {
					continue;
				}
				url = url.trim();
				info.setUrl(url);
				String md5 = info.md5String();
				if (md5 != null && md5.length() > 0) {
					if (tempMap.containsKey(md5)) {
						continue;
					} else {
						tempMap.put(md5, "");
					}
					WebDB db = new WebDB();
					db.setId(UUID.randomUUID().toString().replace("-", ""));
					db.setUrl(info.getUrl());
					db.setStatus(0);
					db.setFcount(0);
					db.setIntime(new Date());
					db.setFtime(null);
					db.setSiteid(info.getSiteId());
					db.setNftime(new Date());
					db.setMd5(md5);
					db.setUrllevel(info.getLevel());
					db.setUrlType(info.getUrlType());
					db.setRefurl(info.getRefurl());
					dataList.add(db);
				}
			}
			DBService service = new DBService();
			try {
				service.insertOrUpdateData(dataList);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * 根据URL的MD5修改数据状态
	 */
	public void updateByMD5(String[] urlMd5s, int type) {
		if (urlMd5s != null && urlMd5s.length > 0) {
			Map<Character, Set<String>> tableMap = new HashMap<Character, Set<String>>();
			for (String md5 : urlMd5s) {
				if (md5 != null && md5.length() > 0) {
					Set<String> set = tableMap.get(md5.charAt(0));
					set = set == null ? new HashSet<String>() : set;
					set.add(md5);
				}
			}

			for (Map.Entry<Character, Set<String>> entry : tableMap.entrySet()) {
				Character c = entry.getKey();
				Set<String> values = entry.getValue();
				String tableName = DBService.selectDbname(c, 1);
				if (tableName != null && values != null && !values.isEmpty()) {
					DBService service = new DBService();
					service.update(values.toArray(new String[values.size()]), type, tableName);
				}
			}
		}

	}
}
