package com.xdtech.project.modules.webdb.service;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;

import org.apache.log4j.Logger;
import org.springframework.web.context.WebApplicationContext;

import com.xdtech.project.modules.webdb.bean.WebDB;
import com.xdtech.project.modules.webdb.exception.NoConnException;
import com.xdtech.project.util.ConfigParam;
import com.xdtech.project.util.SpringUtil;
import com.xdtech.project.webservice.bean.WebDb;
import com.xdtech.project.webservicecrawler.bean.WebDbCrawler;

/**
 * 与数据库操作相关的业务逻辑
 * 
 * @author zhangjianbing@msn.com
 * 
 */
public class DBCrawlerService extends Thread{
	
	@Override
	public void run() {
		while(true){
			try {
				System.out.println("正在保存数据");
				Thread.sleep(1000 * 60 * 1);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			try{
				if(webDbCrawlers.size() > 0){
					Connection conn = getConnection();
					List<WebDbCrawler> dataList = new ArrayList<WebDbCrawler>();
					synchronized (webDbCrawlers) {
						dataList.addAll(webDbCrawlers);
						webDbCrawlers.clear();
					}
					insertData(dataList, conn);
					BerkeleyDBTool.syncDb(tableName);
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	private static List<WebDbCrawler> webDbCrawlers = new ArrayList<WebDbCrawler>();
	private static int count = 0;
	public static int getCount(){
		return count;
	}
	private final static Logger LOGGER = Logger.getLogger(DBCrawlerService.class);
	private final static String tableName = "webdb_webcrawler";
	/**
	 * 获取表名称
	 * @return
	 */
	public String getTableName(){
		return tableName;
	}
	/**
	 * 获取数据库连接
	 * 
	 * @return
	 */
	public Connection getConnection() {
		WebApplicationContext wac = SpringUtil.getWebApplicationContext();
		javax.sql.DataSource datasource = (javax.sql.DataSource)  wac.getBean("dataSource");
		Connection conn = null;
		try {
			conn = datasource.getConnection();
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		}
		count ++;
		return conn;
	}
	public int selectCount(String sql) throws Exception {
		int result = 0;
		Connection conn = null;
		try {
			conn = getConnection();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()) {
				result = rs.getInt(1);
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw e;
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return result;
	}
	/**
	 * 更新数据
	 * @param md5s
	 * @param type
	 * @param tableName
	 */
	public void update(String[] md5s, int type) {
		if (md5s != null && md5s.length > 0) {
			StringBuilder sbSQL = new StringBuilder();
			sbSQL.append("update ").append(tableName).append(" set status=").append(type).append(" where xdmd5 in('");
			StringBuilder md5Builder = new StringBuilder();
			int i = 0;
			for (String md5 : md5s) {
				i++;
				if (md5Builder.length() > 0) {
					md5Builder.append("','");
				}
				md5Builder.append(md5);
				if (i % 50 == 0) {
					String sql = sbSQL.toString() + md5Builder.toString() + "')";
					try {
						update(sql,type);
						md5Builder.setLength(0);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			if (md5Builder.length() > 0) {
				String sql = sbSQL.toString() + md5Builder.toString() + "')";
				try {
					update(sql,type);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	/**
	 * 将数据添加到地址库
	 * @param dataList
	 * @throws Exception
	 */
	public void addData(List<WebDbCrawler> dataList) throws Exception {
		try{
			synchronized (webDbCrawlers) {
				webDbCrawlers.addAll(dataList);
			}
		}catch(Exception e){
		}
	}
	/**
	 * 插入数据库数据。
	 * 
	 * @param dataList
	 * @param conn
	 * @throws SQLException
	 * @throws Exception
	 */
	private void insertData(List<WebDbCrawler> dataList, Connection conn) throws SQLException {
		if (dataList != null && !dataList.isEmpty()) {
			String sql = "insert into " + tableName + "(id,url,status,fcount,intime,ftime,siteid,nftime,urllevel,xdmd5,urltype,refurl,searchEngine,publishTime,title,author,source,timeLong,imagePath) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement pst = null;
			try {
				conn.setAutoCommit(false);
				pst = conn.prepareStatement(sql);
				for (WebDbCrawler data : dataList) {
					if (data.getUrl().getBytes().length >= 2000) {
						continue;
					}
					pst.setString(1, data.getId());
					pst.setString(2, data.getUrl());
					pst.setInt(3, 0);
					pst.setInt(4, 0);
					Date intime = data.getIntime();
					if (intime != null) {
						pst.setTimestamp(5, new java.sql.Timestamp(System.currentTimeMillis()));
					} else {
						pst.setTimestamp(5, null);
					}
					pst.setTimestamp(6, null);
					pst.setString(7, data.getSiteid());
					pst.setTimestamp(8, new java.sql.Timestamp(System.currentTimeMillis()));
					pst.setInt(9, data.getUrllevel());
					pst.setString(10, data.getMd5());
					pst.setInt(11, data.getUrlType());
					pst.setString(12, data.getRefurl());
					
					pst.setString(13, data.getSearchEngine());
					pst.setTimestamp(14, (null == data.getPublishTime()) ? null:(new java.sql.Timestamp(data.getPublishTime().getTime())));
					pst.setString(15, data.getTitle());
					pst.setString(16, data.getAuthor());
					pst.setString(17, data.getSource());
					pst.setString(18, data.getTimeLong());
					pst.setString(19, data.getImagePath());
					pst.execute();
					Set<String> bdbnames = new HashSet<String>();
					bdbnames.add(tableName);
				}
				pst.close();
				conn.commit();
			} catch (SQLException e) {
				e.printStackTrace();
				try {
					conn.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				LOGGER.error(e.getMessage(), e);
				throw e;
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
				e.printStackTrace();
			} finally {
				if (pst != null) {
					try {
						pst.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				try {
					conn.setAutoCommit(true);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				try {
					if(null != conn){
						conn.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	public List<WebDbCrawler> update(String id, int type) throws Exception {
		List<WebDbCrawler> result = new ArrayList<WebDbCrawler>();
		Connection conn = null;
		try {
			String selectSQL = "select * from " + tableName + " where id = '" + id + "'";
			conn = getConnection();
			Statement st = null;
			ResultSet rs = null;
			PreparedStatement pst = null;
			try {
				st = conn.createStatement();
				rs = st.executeQuery(selectSQL);
				WebDbCrawler webDb = null;
				while (rs.next()) {
					webDb = new WebDbCrawler();
					fixwebBDB(webDb, rs, tableName);
					result.add(webDb);
				}
				rs.close();
				st.close();
				if (!result.isEmpty()) {
					webDb = result.remove(0);
					String status = "0";
					if (type == 1) {
						status = "1";
					} else if (webDb.getFcount() > 6) {
						status = "3";
					} else {
						status = "2";
					}
					if (webDb.getStatus() != 1) {
						String updatesql = "update " + tableName + " set status=" + status + ",fcount=fcount+1,ftime=? where id='" + id + "'";
						pst = conn.prepareStatement(updatesql);
						pst.setTimestamp(1, new java.sql.Timestamp(System.currentTimeMillis()));
						pst.execute();
						pst.close();
					}
				}
			} catch (SQLException e) {
				LOGGER.error(e.getMessage(), e);
				e.printStackTrace();
			}
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pst != null) {
				pst.close();
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return result;
	}
	/**
	 * 根据条件删除数据
	 * 
	 * @param tbname
	 * @param query
	 * @throws Exception
	 */
	public void delete(String sql) throws Exception {
		Connection conn = null;
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			Statement st = null;
			try {
				st = conn.createStatement();
				st.execute(sql);
				if (st != null) {
					st.close();
				}
				conn.commit();
			} catch (SQLException e) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				if (st != null) {
					st.close();
				}
				throw e;
			}
			conn.setAutoCommit(true);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw e;
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}
	/**
	 * 清空表中的所有数据
	 */
	public void clear(){
		try {
			clear(tableName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 清空表中的所有数据
	 * 
	 * @param dbname
	 * @throws Exception
	 */
	public void clear(String dbname) throws Exception {
		String sql = "truncate table " + dbname;
		Connection conn = null;
		try {
			conn = getConnection();
			if (conn != null) {
				Statement st = null;
				try {
					st = conn.createStatement();
					st.execute(sql);
					if (st != null) {
						try {
							st.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
				} catch (SQLException e) {
					if (st != null) {
						try {
							st.close();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					}
					e.printStackTrace();
					throw e;
				}

				try {
					BerkeleyDBTool.clear(dbname);
				} catch (Exception e) {
					e.printStackTrace();
					throw e;
				}

			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw e;
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

	/**
	 * 检索
	 * 
	 * @param sql
	 * @param tableName
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	public List<WebDbCrawler> selectFromTable(String sql, String tableName, Connection conn) throws Exception {
		LOGGER.info(sql);
		List<WebDbCrawler> result = new ArrayList<WebDbCrawler>();
		try {
			Statement st = null;
			ResultSet rs = null;
			try {
				st = conn.createStatement();
				rs = st.executeQuery(sql);
				WebDbCrawler webDb = null;
				while (rs.next()) {
					webDb = new WebDbCrawler();
					fixwebBDB(webDb, rs, tableName);
					result.add(webDb);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw e;
		}
		return result;
	}
	public List<WebDbCrawler> select(String sql) throws Exception {
		List<WebDbCrawler> result = new ArrayList<WebDbCrawler>();
		Connection conn = null;
		try {
			conn = getConnection();
			result.addAll(selectFromTable(sql, tableName, conn));
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw e;
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return result;
	}
	private void fixwebBDB(WebDbCrawler webDb, ResultSet rs, String tableName) throws SQLException {
		String id = rs.getString("id");
		String url = rs.getString("url");
		int status = rs.getInt("status");
		int fcount = rs.getInt("fcount");
		Timestamp intime = rs.getTimestamp("intime");
		Timestamp ftime = rs.getTimestamp("ftime");
		String siteid = rs.getString("siteid");
		Timestamp nftime = rs.getTimestamp("nftime");
		int urllevel = rs.getInt("urllevel");
		String xdmd5 = rs.getString("xdmd5");
		int urlType = rs.getInt("urltype");
		String refurl = rs.getString("refurl");
		String searchEngine = rs.getString("searchEngine");
		Timestamp publishTime = rs.getTimestamp("publishTime");
		String title = rs.getString("title");
		String author = rs.getString("author");
		String source = rs.getString("source");
		String timeLong = rs.getString("timeLong");
		String imagePath = rs.getString("imagePath");
		
		webDb.setDbname(tableName);
		webDb.setFcount(fcount);
		webDb.setFtime(ftime);
		webDb.setId(id);
		webDb.setIntime(intime);
		webDb.setNftime(nftime);
		webDb.setSiteid(siteid);
		webDb.setStatus(status);
		webDb.setUrl(url);
		webDb.setUrllevel(urllevel);
		webDb.setMd5(xdmd5);
		webDb.setUrlType(urlType);
		webDb.setRefurl(refurl);
		webDb.setSearchEngine(searchEngine);
		webDb.setPublishTime(publishTime);
		webDb.setTitle(title);
		webDb.setAuthor(author);
		webDb.setSource(source);
		webDb.setImagePath(imagePath);
		webDb.setTimeLong(timeLong);
	}
}
