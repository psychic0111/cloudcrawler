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

/**
 * 与数据库操作相关的业务逻辑
 * 
 * @author zhangjianbing@msn.com
 * 
 */
public class DBService {
	private final static Logger LOGGER = Logger.getLogger(DBService.class);
	private String tableName = "webdb_webcrawler";
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
		return conn;
	}

	/**
	 * 查看地址库中的所有表
	 * 
	 * @return
	 */
	public String[] findTables() {
		Set<String> result = new TreeSet<String>();
		Connection conn = null;
		try {
			conn = getConnection();
			if (conn != null) {
				DatabaseMetaData dmd = conn.getMetaData();
				ResultSet rs = dmd.getTables(null, null, null, new String[] { "TABLE" });
				while (rs.next()) {
					String tablesName = rs.getString(3);
					if (tablesName != null && tablesName.toUpperCase().startsWith(ConfigParam.WEBDB_)) {
						result.add(tablesName);
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage(), e);
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result.toArray(new String[result.size()]);
	}

	/**
	 * 添加数据或者将地址修改成未采集状态
	 * 
	 * @param dataList
	 * @throws Exception
	 */
	public void insertOrUpdateData(List<WebDB> dataList) throws Exception {
		if (dataList != null && !dataList.isEmpty()) {
			Map<Character, List<WebDB>> dataMap = new HashMap<Character, List<WebDB>>();
			List<WebDB> firstLvlDatas = new ArrayList<WebDB>();
			for (WebDB data : dataList) {
				String md5 = data.getMd5();
				if (data.getUrllevel() == 0) {
					firstLvlDatas.add(data);// 一级地址加入一级地址库中
					continue;
				}
				if (md5 != null && md5.length() > 0) {
					char c = md5.charAt(0);
					List<WebDB> list = dataMap.get(c);
					if (list == null) {
						list = new ArrayList<WebDB>();
						dataMap.put(c, list);
					}
					list.add(data);
				}
			}

			Connection conn = getConnection();
			if (conn != null) {
				try {
					Set<String> bdbnames = new HashSet<String>();
					if (!firstLvlDatas.isEmpty()) {
						insertOrUpdateDataZero(firstLvlDatas, conn, selectDbname(' ', 0), bdbnames);// 插入一级地址
					}
					if (!dataMap.isEmpty()) {
						// 插入非一级地址
						for (Map.Entry<Character, List<WebDB>> entry : dataMap.entrySet()) {
							List<WebDB> list = entry.getValue();
							insertOrUpdateDataZero(list, conn, selectDbname(entry.getKey(), 1), bdbnames);
						}
					}
					if (bdbnames != null) {
						for (String bdbname : bdbnames) {
							BerkeleyDBTool.syncDb(bdbname);
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
			} else {
				NoConnException e1 = new NoConnException();
				LOGGER.error(e1.getMessage(), e1);
				throw e1;
			}
		}
	}

	/**
	 * 将数据添加到地址库
	 * 
	 * @param dataList
	 * @throws Exception
	 */
	public void addData(String tableName,List<WebDB> dataList) throws Exception {
		System.out.println(dataList.size());
		Connection conn = getConnection();
		if(tableName != null && this.tableName.equals(tableName)){
			insertData(dataList, conn, tableName, null);
			BerkeleyDBTool.syncDb(tableName);
		}else{
			if (dataList != null && !dataList.isEmpty()) {
				Map<Character, List<WebDB>> dataMap = new HashMap<Character, List<WebDB>>();
				List<WebDB> firstLvlDatas = new ArrayList<WebDB>();
				for (WebDB data : dataList) {
					String md5 = data.getMd5();
					if (data.getUrllevel() == 0) {
						firstLvlDatas.add(data);// 一级地址加入一级地址库中
						continue;
					}
					if (md5 != null && md5.length() > 0) {
						char c = md5.charAt(0);
						List<WebDB> list = dataMap.get(c);
						if (list == null) {
							list = new ArrayList<WebDB>();
							dataMap.put(c, list);
						}
						list.add(data);
					}
				}
				if (conn != null) {
					try {
						Set<String> bdbnames = new HashSet<String>();
						if (!firstLvlDatas.isEmpty()) {
							insertData(firstLvlDatas, conn, selectDbname(' ', 0), bdbnames);// 插入一级地址
						}
						if (!dataMap.isEmpty()) {
							// 插入非一级地址
							for (Map.Entry<Character, List<WebDB>> entry : dataMap.entrySet()) {
								List<WebDB> list = entry.getValue();
								insertData(list, conn, selectDbname(entry.getKey(), 1), bdbnames);
							}
						}
						if (bdbnames != null) {
							for (String bdbname : bdbnames) {
								BerkeleyDBTool.syncDb(bdbname);
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
				} else {
					NoConnException e1 = new NoConnException();
					LOGGER.error(e1.getMessage(), e1);
					throw e1;
				}
			}
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
	private void insertData(List<WebDB> dataList, Connection conn, String tableName, Set<String> bdbnames) throws SQLException {
		if (dataList != null && !dataList.isEmpty()) {
			String sql = "insert into " + tableName + "(id,url,status,fcount,intime,ftime,siteid,nftime,urllevel,xdmd5,urltype,refurl) values(?,?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement pst = null;
			try {
				conn.setAutoCommit(false);
				pst = conn.prepareStatement(sql);
				Map<String, Properties> pMap = new HashMap<String, Properties>();
				for (WebDB data : dataList) {
					if(tableName != null && this.tableName.equals(tableName)){
						if (data.getUrl().getBytes().length >= 1000) {
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
						pst.execute();
						String dbname = BerkeleyDBTool.saveData(data.getMd5(), data.toProperties(tableName), ConfigParam.WEBDB_L1.equals(tableName) ? 0 : 1);
						if(null != bdbnames){
							bdbnames.add(dbname);
						}
					}else{
						if (!BerkeleyDBTool.contain(data.getMd5(), data.getUrllevel())) {
							if (data.getUrl().getBytes().length >= 1000) {
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
							pst.execute();
							String dbname = BerkeleyDBTool.saveData(data.getMd5(), data.toProperties(tableName), ConfigParam.WEBDB_L1.equals(tableName) ? 0 : 1);
							if(null != bdbnames){
								bdbnames.add(dbname);
							}
						} else {
							Properties p = BerkeleyDBTool.getProperty(data.getMd5(), data.getUrllevel());
							if (p != null) {
								String level = p.getProperty("urllevel");
								int ilevel = -100;
								if (level != null) {
									try {
										ilevel = Integer.parseInt(level);
									} catch (Exception e) {
									}
								}
								if (data.getUrllevel() < ilevel) {
									String oldTableName = p.getProperty("tablename");
									String id = p.getProperty("id");
									p.setProperty("urllevel", data.getUrllevel() + "");
									if (oldTableName != null && oldTableName.trim().length() > 0 && id != null && id.trim().length() > 0) {
										String sqlupdate = "update " + oldTableName + " set urllevel=" + data.getUrllevel() + " where id='" + id + "'";
										pMap.put(sqlupdate, p);
									}
								}
							}
						}
					}
					
				}
				pst.close();
				conn.commit();
				updateLevel(pMap, conn);
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
			}
		}
	}

	/**
	 * 插入数据或者修改数据采集状态为未采集
	 * 
	 * @param dataList
	 * @param conn
	 * @param tableName
	 * @param bdbnames
	 * @throws SQLException
	 */
	private void insertOrUpdateDataZero(List<WebDB> dataList, Connection conn, String tableName, Set<String> bdbnames) throws SQLException {
		if (dataList != null && !dataList.isEmpty()) {
			List<WebDB> addList = new ArrayList<WebDB>();
			List<WebDB> updateList = new ArrayList<WebDB>();
			Set<String> keys = new HashSet<String>();
			for (WebDB webDB : dataList) {
				String md5 = webDB.getMd5();
				if (!keys.contains(md5)) {
					keys.add(md5);
					Properties properties = BerkeleyDBTool.getProperty(md5, webDB.getUrllevel());
					if (properties == null) {
						addList.add(webDB);
					} else {
						updateList.add(webDB);
					}
				}
			}
			// 插入新数据
			if (!addList.isEmpty()) {
				insertData(addList, conn, tableName, bdbnames);
			}
			// 更新已有数据为未采集
			if (!updateList.isEmpty()) {
				updateData(updateList, conn, tableName, bdbnames);
			}
		}
	}

	private void updateData(List<WebDB> dataList, Connection conn, String tableName, Set<String> bdbnames) throws SQLException {
		if (dataList != null) {
			String updateSql = "update " + tableName + " set status=0,fcount=0,ftime=null where xdmd5 = ?";
			PreparedStatement pst = conn.prepareStatement(updateSql);
			for (WebDB webDB : dataList) {
				try {
					pst.setString(1, webDB.getMd5());
					pst.executeUpdate();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (pst != null) {
				pst.close();
			}
		}
	}

	public void updateLevel(Map<String, Properties> pMap, Connection conn) {
		if (pMap != null && !pMap.isEmpty()) {
			for (Map.Entry<String, Properties> entry : pMap.entrySet()) {
				try {
					String sql = entry.getKey();
					Properties p = entry.getValue();
					Statement st = conn.createStatement();
					LOGGER.info(sql);
					st.executeUpdate(sql);
					st.close();
					conn.commit();
					int n = 1;
					try {
						n = Integer.parseInt(p.getProperty("urllevel"));
					} catch (Exception e) {
					}
					BerkeleyDBTool.update(p.getProperty("key"), p, n);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
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

	public List<WebDb> select(String sql, String tableName) throws Exception {
		List<WebDb> result = new ArrayList<WebDb>();
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

	public List<WebDb> selectFromTables(String[] sql, String[] table) throws Exception {
		List<WebDb> result = new ArrayList<WebDb>();
		Connection conn = null;
		try {
			conn = getConnection();
			for (int i = 0; i < sql.length; i++) {
				result.addAll(selectFromTable(sql[i], table[i], conn));
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
	 * 检索
	 * 
	 * @param sql
	 * @param tableName
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	public List<WebDb> selectFromTable(String sql, String tableName, Connection conn) throws Exception {
		LOGGER.info(sql);
		List<WebDb> result = new ArrayList<WebDb>();
		try {
			Statement st = null;
			ResultSet rs = null;
			try {
				st = conn.createStatement();
				rs = st.executeQuery(sql);
				WebDb webDb = null;
				while (rs.next()) {
					webDb = new WebDb();
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

	public List<WebDb> update(String id, String tableName, int type) throws Exception {
		List<WebDb> result = new ArrayList<WebDb>();
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
				WebDb webDb = null;
				while (rs.next()) {
					webDb = new WebDb();
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

	private void fixwebBDB(WebDb webDb, ResultSet rs, String tableName) throws SQLException {
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
	 * 根据MD5获取数据库名称。一级地址直接返回ConfigParam.WEBDB_L1
	 * 
	 * @param md5
	 * @return
	 */
	public static String selectDbname(char c, int lel) {
		if (lel == 0) {
			return ConfigParam.WEBDB_L1.toUpperCase();
		} else {
			return (ConfigParam.WEBDB_ + c).toUpperCase();
		}
	}

	public static void recordErorrUrl(String url) {

	}
	
	/**
	 * 更新数据
	 * @param md5s
	 * @param type
	 * @param tableName
	 */
	public void update(String[] md5s, int type, String tableName) {
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
					update(sql);
					md5Builder.setLength(0);
				}
			}
			if (md5Builder.length() > 0) {
				String sql = sbSQL.toString() + md5Builder.toString() + "')";
				update(sql);
			}
		}
	}
	
	/**
	 * 执行更新sql
	 * @param sql
	 */
	public void update(String sql) {
		Connection conn = null;
		Statement st = null;
		try {
			conn = getConnection();
			st = conn.createStatement();
			st.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (st != null) {
				try {
					st.close();
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
