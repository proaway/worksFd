package com.fd.dao;

import java.sql.SQLException;
import java.util.List;

import com.fd.util.PageInfo;
import com.ibatis.sqlmap.client.SqlMapClient;

/**
 * @author 龙力
 *
 */
@SuppressWarnings({"rawtypes" })
public interface IBaseDao {
	/**
	 * 执行插入操作
	 * 
	 * @param sqlName ibatis配置的sql名称
	 * @param obj 参数对象
	 * @throws Exception
	 */
	public Object insertObj(String sqlName, Object obj) throws Exception;
	
	/**
	 * 执行更新操作
	 * 
	 * @param sqlName  ibatis配置的sql名称
	 * @param obj 参数对象
	 * @throws Exception
	 */
	public int updateObj(String sqlName, Object obj) throws Exception;
	
	/**
	 * 执行删除操作
	 * 
	 * @param sqlName ibatis配置的sql名称
	 * @param obj 参数对象
	 * @throws Exception
	 */
	public int deleteObj(String sqlName, Object obj) throws Exception;
	
	/**
	 * 获取列表
	 * 
	 * @param sqlName ibatis配置的sql名称
	 * @param obj 参数对象
	 * @return 返回列表
	 * @throws Exception
	 */
	public List getAsList(String sqlName, Object obj) throws Exception;
	
	/**
	 * 获取列表
	 * 
	 * @param sqlName ibatis配置的sql名称
	 * @param obj 参数对象
	 * @return 返回列表
	 * @throws Exception
	 */
	public List getAsList(String sqlName, Object obj, PageInfo pageInfo) throws Exception;
	
	/**
	 * 获取对象
	 * 
	 * @param sqlName ibatis配置的sql名称
	 * @param obj 参数对象
	 * @return 返回对象
	 * @throws Exception
	 */
	public Object getAsObject(String sqlName, Object obj) throws Exception;
	
	/**
	 * 获取列表
	 * 
	 * @param sqlName ibatis配置的sql名称
	 * @return 返回列表
	 * @throws Exception
	 */
	public List getAsList(String sqlName) throws Exception;
	
	/**
	 * 获取列表
	 * 
	 * @param sqlName ibatis配置的sql名称
	 * @return 返回列表
	 * @throws Exception
	 */
	public List getAsList(String sqlName, PageInfo pageInfo) throws Exception;
	
	/**
	 * 获取对象
	 * 
	 * @param sqlName ibatis配置的sql名称
	 * @return 返回对象
	 * @throws Exception
	 */
	public Object getAsObject(String sqlName) throws Exception;
	
	/**
	 * 批量插入
	 * 
	 * @param sqlName  ibatis配置的sql名称
	 * @param list 参数对象列表
	 * @return 批量执行的行�?
	 * @throws Exception
	 */
	public int insertBatch(String sqlName, List list) throws Exception;
	
	/**
	 * 批量删除
	 * 
	 * @param sqlName  ibatis配置的sql名称
	 * @param list 参数对象列表
	 * @return 批量执行的行�?
	 * @throws Exception
	 */
	public int deleteBatch(String sqlName, List list) throws Exception;
	
	/**
	 * 批量修改
	 * 
	 * @param sqlName  ibatis配置的sql名称
	 * @param list 参数对象列表
	 * @return 批量执行的行�?
	 * @throws Exception
	 */
	public int updateBatch(String sqlName, List list) throws Exception;
	
	/**
	 * 获取SqlMapClient对象
	 * 
	 * @return
	 */
	public SqlMapClient getSqlClient();
	
	/**
	 * 获得GUID
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getGUID() throws Exception;

	/**
	 * 执行更新sql
	 * @param string
	 * @throws SQLException 
	 */
	public void updateSql(String sql) throws SQLException;
}
