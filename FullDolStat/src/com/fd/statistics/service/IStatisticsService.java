package com.fd.statistics.service;

import java.util.List;

import com.fd.statistics.bean.IpaddressBean;
import com.fd.statistics.bean.SearchClickBean;
import com.fd.statistics.bean.VisitAdclickBean;
import com.fd.statistics.bean.VisitAdwordBean;
import com.fd.statistics.bean.VisitDailystatBean;
import com.fd.statistics.bean.VisitLoginBean;
import com.fd.statistics.bean.VisitOpenBean;
import com.fd.statistics.bean.VisitOrderBean;
import com.fd.statistics.bean.VisitOutsideBean;
import com.fd.statistics.bean.VisitProdshowBean;
import com.fd.statistics.bean.VisitProductBean;
import com.fd.statistics.bean.VisitRegisterBean;
import com.fd.statistics.bean.VisitSearchBean;
import com.fd.statistics.bean.VisitSessionBean;
import com.fd.util.PageInfo;

public interface IStatisticsService {
	/**
	 * 增加搜索结果点击统计
	 * 
	 * @param searchClick
	 * @return
	 * @throws Exception
	 */
	public SearchClickBean insertSearchClickBean(SearchClickBean searchClick)
			throws Exception;

	/**
	 * 修改搜索结果点击统计
	 * 
	 * @param searchClick
	 * @return
	 * @throws Exception
	 */
	public int updateSearchClickBean(SearchClickBean searchClick)
			throws Exception;

	/**
	 * 获取搜索结果点击统计
	 * 
	 * @param searchClick
	 * @return
	 * @throws Exception
	 */
	public SearchClickBean getSearchClickBean(SearchClickBean searchClick)
			throws Exception;

	/**
	 * 获取搜索结果点击统计
	 * 
	 * @param searchClick
	 * @return
	 * @throws Exception
	 */
	public List<SearchClickBean> getSearchClickBeans(SearchClickBean searchClick)
			throws Exception;

	/**
	 * 获取搜索结果点击统计
	 * 
	 * @param searchClick
	 * @return
	 * @throws Exception
	 */
	public List<SearchClickBean> getSearchClickBeans(
			SearchClickBean searchClick, PageInfo pageInfo) throws Exception;

	/**
	 * 删除搜索结果点击统计
	 * 
	 * @param searchClick
	 * @return
	 * @throws Exception
	 */
	public int deleteSearchClickBean(SearchClickBean searchClick)
			throws Exception;

	/**
	 * 增加广告点击统计
	 * 
	 * @param visiadclick
	 * @return
	 * @throws Exception
	 */
	public VisitAdclickBean insertVisitAdclickBean(VisitAdclickBean visiadclick)
			throws Exception;

	/**
	 * 修改广告点击统计
	 * 
	 * @param visiadclick
	 * @return
	 * @throws Exception
	 */
	public int updateVisitAdclickBean(VisitAdclickBean visiadclick)
			throws Exception;

	/**
	 * 获取广告点击统计
	 * 
	 * @param visiadclick
	 * @return
	 * @throws Exception
	 */
	public VisitAdclickBean getVisitAdclickBean(VisitAdclickBean visiadclick)
			throws Exception;

	/**
	 * 获取广告点击统计
	 * 
	 * @param visiadclick
	 * @return
	 * @throws Exception
	 */
	public List<VisitAdclickBean> getVisitAdclickBeans(
			VisitAdclickBean visiadclick) throws Exception;

	/**
	 * 获取广告点击统计
	 * 
	 * @param visiadclick
	 * @return
	 * @throws Exception
	 */
	public List<VisitAdclickBean> getVisitAdclickBeans(
			VisitAdclickBean visiadclick, PageInfo pageInfo) throws Exception;

	/**
	 * 删除广告点击统计
	 * 
	 * @param visiadclick
	 * @return
	 * @throws Exception
	 */
	public int deleteVisitAdclickBean(VisitAdclickBean visiadclick)
			throws Exception;

	/**
	 * 增加google 统计代码链接访问统计
	 * 
	 * @param visiadword
	 * @return
	 * @throws Exception
	 */
	public VisitAdwordBean insertVisitAdwordBean(VisitAdwordBean visiadword)
			throws Exception;

	/**
	 * 修改google 统计代码链接访问统计
	 * 
	 * @param visiadword
	 * @return
	 * @throws Exception
	 */
	public int updateVisitAdwordBean(VisitAdwordBean visiadword)
			throws Exception;

	/**
	 * 获取google 统计代码链接访问统计
	 * 
	 * @param visiadword
	 * @return
	 * @throws Exception
	 */
	public VisitAdwordBean getVisitAdwordBean(VisitAdwordBean visiadword)
			throws Exception;

	/**
	 * 获取google 统计代码链接访问统计
	 * 
	 * @param visiadword
	 * @return
	 * @throws Exception
	 */
	public List<VisitAdwordBean> getVisitAdwordBeans(VisitAdwordBean visiadword)
			throws Exception;

	/**
	 * 获取google 统计代码链接访问统计
	 * 
	 * @param visiadword
	 * @return
	 * @throws Exception
	 */
	public List<VisitAdwordBean> getVisitAdwordBeans(
			VisitAdwordBean visiadword, PageInfo pageInfo) throws Exception;

	/**
	 * 删除google 统计代码链接访问统计
	 * 
	 * @param visiadword
	 * @return
	 * @throws Exception
	 */
	public int deleteVisitAdwordBean(VisitAdwordBean visiadword)
			throws Exception;

	/**
	 * 增加每日访问统计
	 * 
	 * @param visidailystat
	 * @return
	 * @throws Exception
	 */
	public VisitDailystatBean insertVisitDailystatBean(
			VisitDailystatBean visidailystat) throws Exception;

	/**
	 * 修改每日访问统计
	 * 
	 * @param visidailystat
	 * @return
	 * @throws Exception
	 */
	public int updateVisitDailystatBean(VisitDailystatBean visidailystat)
			throws Exception;

	/**
	 * 获取每日访问统计
	 * 
	 * @param visidailystat
	 * @return
	 * @throws Exception
	 */
	public VisitDailystatBean getVisitDailystatBean(
			VisitDailystatBean visidailystat) throws Exception;

	/**
	 * 获取每日访问统计
	 * 
	 * @param visidailystat
	 * @return
	 * @throws Exception
	 */
	public List<VisitDailystatBean> getVisitDailystatBeans(
			VisitDailystatBean visidailystat) throws Exception;

	/**
	 * 获取每日访问统计
	 * 
	 * @param visidailystat
	 * @return
	 * @throws Exception
	 */
	public List<VisitDailystatBean> getVisitDailystatBeans(
			VisitDailystatBean visidailystat, PageInfo pageInfo)
			throws Exception;

	/**
	 * 删除每日访问统计
	 * 
	 * @param visidailystat
	 * @return
	 * @throws Exception
	 */
	public int deleteVisitDailystatBean(VisitDailystatBean visidailystat)
			throws Exception;

	/**
	 * 增加访问登录
	 * 
	 * @param visilogin
	 * @return
	 * @throws Exception
	 */
	public VisitLoginBean insertVisitLoginBean(VisitLoginBean visilogin)
			throws Exception;

	/**
	 * 修改访问登录
	 * 
	 * @param visilogin
	 * @return
	 * @throws Exception
	 */
	public int updateVisitLoginBean(VisitLoginBean visilogin) throws Exception;

	/**
	 * 获取访问登录
	 * 
	 * @param visilogin
	 * @return
	 * @throws Exception
	 */
	public VisitLoginBean getVisitLoginBean(VisitLoginBean visilogin)
			throws Exception;

	/**
	 * 获取访问登录
	 * 
	 * @param visilogin
	 * @return
	 * @throws Exception
	 */
	public List<VisitLoginBean> getVisitLoginBeans(VisitLoginBean visilogin)
			throws Exception;

	/**
	 * 获取访问登录
	 * 
	 * @param visilogin
	 * @return
	 * @throws Exception
	 */
	public List<VisitLoginBean> getVisitLoginBeans(VisitLoginBean visilogin,
			PageInfo pageInfo) throws Exception;

	/**
	 * 删除访问登录
	 * 
	 * @param visilogin
	 * @return
	 * @throws Exception
	 */
	public int deleteVisitLoginBean(VisitLoginBean visilogin) throws Exception;

	/**
	 * 增加访问统计
	 * 
	 * @param visiopen
	 * @return
	 * @throws Exception
	 */
	public VisitOpenBean insertVisitOpenBean(VisitOpenBean visiopen)
			throws Exception;

	/**
	 * 修改访问统计
	 * 
	 * @param visiopen
	 * @return
	 * @throws Exception
	 */
	public int updateVisitOpenBean(VisitOpenBean visiopen) throws Exception;

	/**
	 * 获取访问统计
	 * 
	 * @param visiopen
	 * @return
	 * @throws Exception
	 */
	public VisitOpenBean getVisitOpenBean(VisitOpenBean visiopen)
			throws Exception;

	/**
	 * 获取访问统计
	 * 
	 * @param visiopen
	 * @return
	 * @throws Exception
	 */
	public List<VisitOpenBean> getVisitOpenBeans(VisitOpenBean visiopen)
			throws Exception;

	/**
	 * 获取访问统计
	 * 
	 * @param visiopen
	 * @return
	 * @throws Exception
	 */
	public List<VisitOpenBean> getVisitOpenBeans(VisitOpenBean visiopen,
			PageInfo pageInfo) throws Exception;

	/**
	 * 删除访问统计
	 * 
	 * @param visiopen
	 * @return
	 * @throws Exception
	 */
	public int deleteVisitOpenBean(VisitOpenBean visiopen) throws Exception;

	/**
	 * 增加订单统计
	 * 
	 * @param visiorder
	 * @return
	 * @throws Exception
	 */
	public VisitOrderBean insertVisitOrderBean(VisitOrderBean visiorder)
			throws Exception;

	/**
	 * 修改订单统计
	 * 
	 * @param visiorder
	 * @return
	 * @throws Exception
	 */
	public int updateVisitOrderBean(VisitOrderBean visiorder) throws Exception;

	/**
	 * 获取订单统计
	 * 
	 * @param visiorder
	 * @return
	 * @throws Exception
	 */
	public VisitOrderBean getVisitOrderBean(VisitOrderBean visiorder)
			throws Exception;

	/**
	 * 获取订单统计
	 * 
	 * @param visiorder
	 * @return
	 * @throws Exception
	 */
	public List<VisitOrderBean> getVisitOrderBeans(VisitOrderBean visiorder)
			throws Exception;

	/**
	 * 获取订单统计
	 * 
	 * @param visiorder
	 * @return
	 * @throws Exception
	 */
	public List<VisitOrderBean> getVisitOrderBeans(VisitOrderBean visiorder,
			PageInfo pageInfo) throws Exception;

	/**
	 * 删除订单统计
	 * 
	 * @param visiorder
	 * @return
	 * @throws Exception
	 */
	public int deleteVisitOrderBean(VisitOrderBean visiorder) throws Exception;

	/**
	 * 增加外链访问
	 * 
	 * @param visioutside
	 * @return
	 * @throws Exception
	 */
	public VisitOutsideBean insertVisitOutsideBean(VisitOutsideBean visioutside)
			throws Exception;

	/**
	 * 修改外链访问
	 * 
	 * @param visioutside
	 * @return
	 * @throws Exception
	 */
	public int updateVisitOutsideBean(VisitOutsideBean visioutside)
			throws Exception;

	/**
	 * 获取外链访问
	 * 
	 * @param visioutside
	 * @return
	 * @throws Exception
	 */
	public VisitOutsideBean getVisitOutsideBean(VisitOutsideBean visioutside)
			throws Exception;

	/**
	 * 获取外链访问
	 * 
	 * @param visioutside
	 * @return
	 * @throws Exception
	 */
	public List<VisitOutsideBean> getVisitOutsideBeans(
			VisitOutsideBean visioutside) throws Exception;

	/**
	 * 获取外链访问
	 * 
	 * @param visioutside
	 * @return
	 * @throws Exception
	 */
	public List<VisitOutsideBean> getVisitOutsideBeans(
			VisitOutsideBean visioutside, PageInfo pageInfo) throws Exception;

	/**
	 * 删除外链访问
	 * 
	 * @param visioutside
	 * @return
	 * @throws Exception
	 */
	public int deleteVisitOutsideBean(VisitOutsideBean visioutside)
			throws Exception;

	/**
	 * 增加产品曝光展示
	 * 
	 * @param visiprodshow
	 * @return
	 * @throws Exception
	 */
	public VisitProdshowBean insertVisitProdshowBean(
			VisitProdshowBean visiprodshow) throws Exception;

	/**
	 * 修改产品曝光展示
	 * 
	 * @param visiprodshow
	 * @return
	 * @throws Exception
	 */
	public int updateVisitProdshowBean(VisitProdshowBean visiprodshow)
			throws Exception;

	/**
	 * 获取产品曝光展示
	 * 
	 * @param visiprodshow
	 * @return
	 * @throws Exception
	 */
	public VisitProdshowBean getVisitProdshowBean(VisitProdshowBean visiprodshow)
			throws Exception;

	/**
	 * 获取产品曝光展示
	 * 
	 * @param visiprodshow
	 * @return
	 * @throws Exception
	 */
	public List<VisitProdshowBean> getVisitProdshowBeans(
			VisitProdshowBean visiprodshow) throws Exception;

	/**
	 * 获取产品曝光展示
	 * 
	 * @param visiprodshow
	 * @return
	 * @throws Exception
	 */
	public List<VisitProdshowBean> getVisitProdshowBeans(
			VisitProdshowBean visiprodshow, PageInfo pageInfo) throws Exception;

	/**
	 * 删除产品曝光展示
	 * 
	 * @param visiprodshow
	 * @return
	 * @throws Exception
	 */
	public int deleteVisitProdshowBean(VisitProdshowBean visiprodshow)
			throws Exception;

	/**
	 * 增加产品访问统计
	 * 
	 * @param visiproduct
	 * @return
	 * @throws Exception
	 */
	public VisitProductBean insertVisitProductBean(VisitProductBean visiproduct)
			throws Exception;

	/**
	 * 修改产品访问统计
	 * 
	 * @param visiproduct
	 * @return
	 * @throws Exception
	 */
	public int updateVisitProductBean(VisitProductBean visiproduct)
			throws Exception;

	/**
	 * 获取产品访问统计
	 * 
	 * @param visiproduct
	 * @return
	 * @throws Exception
	 */
	public VisitProductBean getVisitProductBean(VisitProductBean visiproduct)
			throws Exception;

	/**
	 * 获取产品访问统计
	 * 
	 * @param visiproduct
	 * @return
	 * @throws Exception
	 */
	public List<VisitProductBean> getVisitProductBeans(
			VisitProductBean visiproduct) throws Exception;

	/**
	 * 获取产品访问统计
	 * 
	 * @param visiproduct
	 * @return
	 * @throws Exception
	 */
	public List<VisitProductBean> getVisitProductBeans(
			VisitProductBean visiproduct, PageInfo pageInfo) throws Exception;

	/**
	 * 删除产品访问统计
	 * 
	 * @param visiproduct
	 * @return
	 * @throws Exception
	 */
	public int deleteVisitProductBean(VisitProductBean visiproduct)
			throws Exception;

	/**
	 * 增加访问注册
	 * 
	 * @param visiregister
	 * @return
	 * @throws Exception
	 */
	public VisitRegisterBean insertVisitRegisterBean(
			VisitRegisterBean visiregister) throws Exception;

	/**
	 * 修改访问注册
	 * 
	 * @param visiregister
	 * @return
	 * @throws Exception
	 */
	public int updateVisitRegisterBean(VisitRegisterBean visiregister)
			throws Exception;

	/**
	 * 获取访问注册
	 * 
	 * @param visiregister
	 * @return
	 * @throws Exception
	 */
	public VisitRegisterBean getVisitRegisterBean(VisitRegisterBean visiregister)
			throws Exception;

	/**
	 * 获取访问注册
	 * 
	 * @param visiregister
	 * @return
	 * @throws Exception
	 */
	public List<VisitRegisterBean> getVisitRegisterBeans(
			VisitRegisterBean visiregister) throws Exception;

	/**
	 * 获取访问注册
	 * 
	 * @param visiregister
	 * @return
	 * @throws Exception
	 */
	public List<VisitRegisterBean> getVisitRegisterBeans(
			VisitRegisterBean visiregister, PageInfo pageInfo) throws Exception;

	/**
	 * 删除访问注册
	 * 
	 * @param visiregister
	 * @return
	 * @throws Exception
	 */
	public int deleteVisitRegisterBean(VisitRegisterBean visiregister)
			throws Exception;

	/**
	 * 增加搜索统计
	 * 
	 * @param visisearch
	 * @return
	 * @throws Exception
	 */
	public VisitSearchBean insertVisitSearchBean(VisitSearchBean visisearch)
			throws Exception;

	/**
	 * 修改搜索统计
	 * 
	 * @param visisearch
	 * @return
	 * @throws Exception
	 */
	public int updateVisitSearchBean(VisitSearchBean visisearch)
			throws Exception;

	/**
	 * 获取搜索统计
	 * 
	 * @param visisearch
	 * @return
	 * @throws Exception
	 */
	public VisitSearchBean getVisitSearchBean(VisitSearchBean visisearch)
			throws Exception;

	/**
	 * 获取搜索统计
	 * 
	 * @param visisearch
	 * @return
	 * @throws Exception
	 */
	public List<VisitSearchBean> getVisitSearchBeans(VisitSearchBean visisearch)
			throws Exception;

	/**
	 * 获取搜索统计
	 * 
	 * @param visisearch
	 * @return
	 * @throws Exception
	 */
	public List<VisitSearchBean> getVisitSearchBeans(
			VisitSearchBean visisearch, PageInfo pageInfo) throws Exception;

	/**
	 * 删除搜索统计
	 * 
	 * @param visisearch
	 * @return
	 * @throws Exception
	 */
	public int deleteVisitSearchBean(VisitSearchBean visisearch)
			throws Exception;

	/**
	 * 增加访问统计 - 会话
	 * 
	 * @param visisession
	 * @return
	 * @throws Exception
	 */
	public VisitSessionBean insertVisitSessionBean(VisitSessionBean visisession)
			throws Exception;

	/**
	 * 修改访问统计 - 会话
	 * 
	 * @param visisession
	 * @return
	 * @throws Exception
	 */
	public int updateVisitSessionBean(VisitSessionBean visisession)
			throws Exception;

	/**
	 * 获取访问统计 - 会话
	 * 
	 * @param visisession
	 * @return
	 * @throws Exception
	 */
	public VisitSessionBean getVisitSessionBean(VisitSessionBean visisession)
			throws Exception;

	/**
	 * 获取访问统计 - 会话
	 * 
	 * @param visisession
	 * @return
	 * @throws Exception
	 */
	public List<VisitSessionBean> getVisitSessionBeans(
			VisitSessionBean visisession) throws Exception;

	/**
	 * 获取访问统计 - 会话
	 * 
	 * @param visisession
	 * @return
	 * @throws Exception
	 */
	public List<VisitSessionBean> getVisitSessionBeans(
			VisitSessionBean visisession, PageInfo pageInfo) throws Exception;

	/**
	 * 删除访问统计 - 会话
	 * 
	 * @param visisession
	 * @return
	 * @throws Exception
	 */
	public int deleteVisitSessionBean(VisitSessionBean visisession)
			throws Exception;

	/**
	 * 获取IP地址库
	 * 
	 * @param ipaddress
	 * @return
	 * @throws Exception
	 */
	public IpaddressBean getIpaddressBean(IpaddressBean ipaddress)
			throws Exception;

	/**
	 * 获取IP地址库
	 * 
	 * @param ipaddress
	 * @return
	 * @throws Exception
	 */
	public List<IpaddressBean> getIpaddressBeans(IpaddressBean ipaddress)
			throws Exception;
	
	
    public Long getVisitId(VisitSessionBean visitSession) throws Exception;
    
}