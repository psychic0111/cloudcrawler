package com.xdtech.project.webservicecrawler;

import java.util.ArrayList;


import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.jws.WebService;

import com.xdtech.project.modules.webdb.service.BerkeleyDBTool;
import com.xdtech.project.modules.webdb.service.DBCrawlerService;
import com.xdtech.project.webservicecrawler.bean.URLInfoCrawler;
import com.xdtech.project.webservicecrawler.bean.WebDBCrawlerResult;
import com.xdtech.project.webservicecrawler.bean.WebDbCrawler;




@WebService(endpointInterface = "com.xdtech.project.webservicecrawler.WebDBCrawlerServiceInterface", targetNamespace = "http://www.xd-tech.com")
public class WebDBCrawlerService implements WebDBCrawlerServiceInterface {

	public void addOrUpdateURLs(URLInfoCrawler[] urls) {

	}

	public void addUrl(URLInfoCrawler[] urls) throws Exception {
		if (urls != null) {
			List<WebDbCrawler> dataList = new ArrayList<WebDbCrawler>(urls.length);
			Map<String, String> tempMap = new HashMap<String, String>();
			for (URLInfoCrawler info : urls) {
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
					WebDbCrawler db = new WebDbCrawler();
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
					//添加搜索引擎属性
					db.setSearchEngine(info.getSearchEngine());
					db.setAuthor(info.getAuthor());
					db.setTimeLong(info.getTimeLong());
					db.setTitle(info.getTitle());
					db.setSource(info.getSource());
					db.setPublishTime(info.getPublishTime());
					db.setImagePath(info.getImagePath());
					dataList.add(db);
				}
			}
			DBCrawlerService service = new DBCrawlerService();
			service.addData(dataList);
		}
	}

	public void clearDB() throws Exception {
		DBCrawlerService service = new DBCrawlerService();
		service.clear();
	}

	public void delete(String id) throws Exception {
		DBCrawlerService service = new DBCrawlerService();
		String tbname = service.getTableName();
		String sql = "delete from " + tbname + " where id='" + id + "'";
		try {
			List<WebDbCrawler> dataList = service.select("select * from " + tbname + " where id='" + id + "'");
			if (dataList != null && !dataList.isEmpty()) {
				WebDbCrawler webdb = dataList.get(0);
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

	public WebDBCrawlerResult findWebDbByQuery(String query, String order, int start,
			int pageNum) throws Exception {
		WebDBCrawlerResult result = new WebDBCrawlerResult();
		DBCrawlerService service = new DBCrawlerService();
		String tableName = service.getTableName();
		result.setTotal(0);
		List<WebDbCrawler> dataList = new ArrayList<WebDbCrawler>();
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

		
		System.out.println(sbSQL.toString());
		List<WebDbCrawler> webdbList = service.select(sbSQL.toString());
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

	public List<WebDbCrawler> findWebDbByQueryFromTable(String query, int pageNum)
			throws Exception {
		DBCrawlerService service = new DBCrawlerService();
		String tableName = service.getTableName();
		StringBuilder sbSQL = new StringBuilder();
		sbSQL.append("SELECT * FROM ");
		sbSQL.append(tableName);
		if (query != null && query.trim().length() > 0) {
			sbSQL.append(" WHERE ");
			sbSQL.append(query);
		}
		sbSQL.append(" limit 0,");
		sbSQL.append(pageNum);
		List<WebDbCrawler> result = service.selectFromTable(sbSQL.toString(), tableName, service.getConnection());
		if (result != null) {
			Collections.sort(result, new Comparator<WebDbCrawler>() {
				public int compare(WebDbCrawler o1, WebDbCrawler o2) {
					return o1.getIntime().compareTo(o2.getIntime());
				}
			});
		}
		if (result.size() > pageNum) {
			result = result.subList(0, pageNum - 1);
		}
		return result;
	}

	public void update(String id, int type) throws Exception {
		DBCrawlerService service = new DBCrawlerService();
		try {
			service.update(id,type);
		} catch (Exception e) {
			throw e;
		}
	}

	public void updateByMD5(String[] urlMd5s, int type) {
		if (urlMd5s != null && urlMd5s.length > 0) {
			DBCrawlerService service = new DBCrawlerService();
			String tableName = service.getTableName();
			if (tableName != null) {
				service.update(urlMd5s, type);
			}
		}
	}

}
