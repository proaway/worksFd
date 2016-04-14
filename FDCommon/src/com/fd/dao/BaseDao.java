package com.fd.dao;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.List;

import org.springframework.orm.ibatis.SqlMapClientTemplate;

import com.fd.util.PageInfo;
import com.ibatis.sqlmap.client.SqlMapClient;

/**
 * @author 龙力
 * 
 */
@SuppressWarnings("rawtypes")
public class BaseDao extends SqlMapClientTemplate implements IBaseDao {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(BaseDao.class);

	/**
	 * @return
	 */
	public SqlMapClient getSqlClient() {
		return getSqlMapClient();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.madeinchina.holesell.dao.Dao#delete(java.lang.String,
	 *      java.lang.Object)
	 */
	public int deleteObj(String sqlName, Object obj) throws Exception {
		try {
			return getSqlMapClient().delete(sqlName, obj);
		} catch (Exception e) {
			ToStringBuilder.setDefaultStyle(ToStringStyle.MULTI_LINE_STYLE);
			logger.error(sqlName + " : " + ToStringBuilder.reflectionToString(obj));
			throw e;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.madeinchina.holesell.dao.Dao#getAsList(java.lang.String,
	 *      java.lang.Object)
	 */
	public List getAsList(String sqlName, Object obj) throws Exception {
		try {
			return getSqlMapClient().queryForList(sqlName, obj);
		} catch (Exception e) {
			ToStringBuilder.setDefaultStyle(ToStringStyle.MULTI_LINE_STYLE);
			logger.error(sqlName + " : " + ToStringBuilder.reflectionToString(obj));
			throw e;
		}
	}
	
	/**
	 * 获取列表
	 * 
	 * @param sqlName ibatis配置的sql名称
	 * @param obj 参数对象
	 * @return 返回列表
	 * @throws Exception
	 */
	public List getAsList(String sqlName, Object obj, PageInfo pageInfo) throws Exception {
		try {
			int begin = pageInfo.getPageSize() * (pageInfo.getPageIndex()-1);
			return getSqlMapClient().queryForList(sqlName, obj, begin, pageInfo.getPageSize());
		} catch (Exception e) {
			ToStringBuilder.setDefaultStyle(ToStringStyle.MULTI_LINE_STYLE);
			logger.error(sqlName + " : " + ToStringBuilder.reflectionToString(obj));
			throw e;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.madeinchina.holesell.dao.Dao#getAsObject(java.lang.String,
	 *      java.lang.Object)
	 */
	public Object getAsObject(String sqlName, Object obj) throws Exception {
		try {
			return getSqlMapClient().queryForObject(sqlName, obj);
		} catch (Exception e) {
			ToStringBuilder.setDefaultStyle(ToStringStyle.MULTI_LINE_STYLE);
			logger.error(sqlName + " : " + ToStringBuilder.reflectionToString(obj));
			throw e;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.madeinchina.holesell.dao.Dao#insert(java.lang.String,
	 *      java.lang.Object)
	 */
	public Object insertObj(String sqlName, Object obj) throws Exception {
		try {
			return getSqlMapClient().insert(sqlName, obj);
		} catch (Exception e) {
			ToStringBuilder.setDefaultStyle(ToStringStyle.MULTI_LINE_STYLE);
			logger.error(sqlName + " : " + ToStringBuilder.reflectionToString(obj));
			throw e;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.madeinchina.holesell.dao.Dao#update(java.lang.String,
	 *      java.lang.Object)
	 */
	public int updateObj(String sqlName, Object obj) throws Exception {
		try {
			return getSqlMapClient().update(sqlName, obj);
		} catch (Exception e) {
			ToStringBuilder.setDefaultStyle(ToStringStyle.MULTI_LINE_STYLE);
			logger.error(sqlName + " : " + ToStringBuilder.reflectionToString(obj));
			throw e;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.madeinchina.holesell.dao.Dao#getAsList(java.lang.String)
	 */
	public List getAsList(String sqlName) throws Exception {
		try {
			return getSqlMapClient().queryForList(sqlName);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	/**
	 * 获取列表
	 * 
	 * @param sqlName ibatis配置的sql名称
	 * @return 返回列表
	 * @throws Exception
	 */
	public List getAsList(String sqlName, PageInfo pageInfo) throws Exception {
		try {
			int begin = pageInfo.getPageSize() * (pageInfo.getPageIndex()-1);
			return getSqlMapClient().queryForList(sqlName, begin, pageInfo.getPageSize());
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.madeinchina.holesell.dao.Dao#getAsObject(java.lang.String)
	 */
	public Object getAsObject(String sqlName) throws Exception {
		try {
			return getSqlMapClient().queryForObject(sqlName);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.mic.dao.IBaseDao#insertBatch(java.lang.String, java.util.List)
	 */
	public int insertBatch(String sqlName, List list) throws Exception {
		try {
			SqlMapClient client = getSqlClient();
			// client.startBatch();
			int result = 0;
			for (int i = 0; i < list.size(); i++) {
				Object o = client.insert(sqlName, list.get(i));
				if (o != null) {
					result++;
				}
			}
			// client.executeBatch();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.mic.dao.IBaseDao#deleteBatch(java.lang.String, java.util.List)
	 */
	public int deleteBatch(String sqlName, List list) throws Exception {
		try {
			int result = 0;
			SqlMapClient client = getSqlClient();
			// client.startBatch();
			for (int i = 0; i < list.size(); i++) {
				int k = client.delete(sqlName, list.get(i));
				result += k;
			}
			// client.executeBatch();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.mic.dao.IBaseDao#updateBatch(java.lang.String, java.util.List)
	 */
	public int updateBatch(String sqlName, List list) throws Exception {
		try {
			int result = 0;
			// getSqlMapClient().startBatch();
			for (int i = 0; i < list.size(); i++) {
				int k = getSqlMapClient().update(sqlName, list.get(i));
				result += k;
			}
			// result = getSqlMapClient().executeBatch();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * 获取SqlMapClient对象
	 * 
	 * @return
	 */
	public String getGUID() throws Exception {
		Object obj = getSqlMapClient().queryForObject("getGUID");
		if (obj == null) {
			return null;
		}
		String guid = (String) obj;
		return guid;
	}

	/**
	 *  执行更新的SQL语句
	 * @throws SQLException 
	 */
	public void updateSql(String sql) throws SQLException {
		if(sql == null){
			return;
		}
		getSqlMapClient().update(sql);
	}
}
