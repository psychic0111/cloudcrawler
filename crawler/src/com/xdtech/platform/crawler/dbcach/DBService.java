package com.xdtech.platform.crawler.dbcach;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.apache.log4j.Logger;
import org.springframework.web.context.WebApplicationContext;

import com.xdtech.platform.util.SpringUtil;


public class DBService {
	private final static Logger LOGGER = Logger.getLogger(DBService.class);
	/**
	 * 获取数据库连接
	 * 
	 * @return
	 */
	public static Connection getConnection() {
		WebApplicationContext wac = SpringUtil.getWebApplicationContext();
		javax.sql.DataSource datasource = (javax.sql.DataSource) wac.getBean("dataSource");
		Connection conn = null;
		try {
			conn = datasource.getConnection();
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return conn;
	}
	/**
	 * 批量添加日志
	 * @param crawlerLogs
	 */
	public static void insertLog(CrawlerLog crawlerLog){
		PreparedStatement ps=null;
		Connection conn = getConnection();
		String sql ="insert into xd_crawler_log (id,siteId,siteName,url,status,crawlerTime,count,crawlerCode) values (?,?,?,?,?,?,?,?)";
		try{
			ps=conn.prepareStatement(sql);
			ps.setString(1, crawlerLog.getId());
			ps.setString(2, crawlerLog.getSiteId());
			ps.setString(3, crawlerLog.getSiteName());
			ps.setString(4, crawlerLog.getUrl());
			ps.setInt(5, crawlerLog.getStatus()==null?0:crawlerLog.getStatus());
			ps.setTimestamp(6, new Timestamp(crawlerLog.getCrawlerTime().getTime()));
			ps.setInt(7, crawlerLog.getCount()==null?0:crawlerLog.getCount());
			ps.setString(8, crawlerLog.getCrawlerCode());
			ps.execute();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}
}
