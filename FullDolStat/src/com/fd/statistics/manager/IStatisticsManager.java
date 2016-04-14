/**
 * IStatisticsService.java
 * @author:  Zhou Rongyu
 */
package com.fd.statistics.manager;

import com.fd.statistics.bean.SearchClickBean;
import com.fd.statistics.bean.VisitAdclickBean;
import com.fd.statistics.bean.VisitAdwordBean;
import com.fd.statistics.bean.VisitLoginBean;
import com.fd.statistics.bean.VisitOpenBean;
import com.fd.statistics.bean.VisitOrderBean;
import com.fd.statistics.bean.VisitProdshowBean;
import com.fd.statistics.bean.VisitProductBean;
import com.fd.statistics.bean.VisitRegisterBean;
import com.fd.statistics.bean.VisitSearchBean;
import com.fd.statistics.bean.VisitSessionBean;

/**
 * @CreateDate:  2013-5-30 下午4:03:52 
 * @author:  Zhou Rongyu
 * @Description:  TODO
 * 
 * @version:  V1.0
 */
public interface IStatisticsManager {
	/**
	 * 判断是否爬虫
	 * 
	 * @param visitSession
	 * @return
	 * @throws Exception
	 */
	public boolean ignore(String userAgent, String ipAddress) throws Exception;
	
	/**
	 * 新增访问
	 * 
	 * @param visitSession
	 * @return
	 * @throws Exception
	 */
	public VisitSessionBean saveVisitSession(VisitSessionBean visitSession) throws Exception;
	
	/**
	 * 获取访问登陆记录
	 * 
	 * @param visitSession
	 * @return
	 * @throws Exception
	 */
	public VisitSessionBean getVisitSession(VisitSessionBean visitSession) throws Exception;
	
	/**
	 * 更新访问登陆记录
	 * 
	 * @param visitSession
	 * @return
	 * @throws Exception
	 */
	public int updateVisitSession(VisitSessionBean visitSession) throws Exception;
	
	/**
	 * 新增一个访问打开
	 * 
	 * @param visitOpen
	 * @return
	 * @throws Exception
	 */
	public VisitOpenBean addVisitOpen(VisitOpenBean visitOpen) throws Exception;
	
	/**
	 * 新增一个访问登录
	 * 
	 * @param visitLogin
	 * @return
	 * @throws Exception
	 */
	public VisitLoginBean addVisitLogin(VisitLoginBean visitLogin) throws Exception;
	
	/**
	 * 增加一个访问注册
	 * 
	 * @param visitRegister
	 * @return
	 * @throws Exception
	 */
	public VisitRegisterBean addVisitRegister(VisitRegisterBean visitRegister) throws Exception;
	
	/**
	 * 新增一个访问产品
	 * 
	 * @param visitProduct
	 * @return
	 * @throws Exception
	 */
	public VisitProductBean saveVisitProduct(VisitProductBean visitProduct) throws Exception;
	
	/**
	 * 新增一个访问产品
	 * 
	 * @param visitOrder
	 * @return
	 * @throws Exception
	 */
	public VisitOrderBean addVisitOrder(VisitOrderBean visitOrder) throws Exception;
	
	/**
	 * 新增一个访问广告点击
	 * 
	 * @param visitAdClick
	 * @return
	 * @throws Exception
	 */
	public VisitAdclickBean addVisitAdClick(VisitAdclickBean visitAdClick) throws Exception;
	
/*	*//**
	 * 新增一个Affiliate访问
	 * 
	 * @param affiliate
	 * @return
	 * @throws Exception
	 *//*
	public VisitAffiliate addVisitAffiliate(VisitAffiliate affiliate) throws Exception;*/
	
	/**
	 * 新增一个搜索信息
	 * 
	 * @param search
	 * @return
	 * @throws Exception
	 */
	public VisitSearchBean addVisitSearch(VisitSearchBean search) throws Exception;
	
	/**
	 * 保存点击记录
	 * 
	 * @param click
	 * @return
	 * @throws Exception
	 */
	public SearchClickBean saveSearchClick(SearchClickBean click) throws Exception;
	
	/**
	 * 新增一个Adword信息
	 * 
	 * @param adword
	 * @return
	 * @throws Exception
	 */
	public VisitAdwordBean addVisitAdword(VisitAdwordBean adword) throws Exception;
	
	/**
	 * 新增一个产品曝光信息
	 * 
	 * @param adword
	 * @return
	 * @throws Exception
	 */
	public VisitProdshowBean addVisitProdshow(VisitProdshowBean productshow) throws Exception;
}
