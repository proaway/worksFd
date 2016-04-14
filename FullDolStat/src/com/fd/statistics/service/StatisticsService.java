package com.fd.statistics.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.fd.dao.IBaseDao;
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

@Component
public class StatisticsService implements IStatisticsService {
	@Autowired
	@Qualifier("dao")
	private IBaseDao dao;

	/**
	 * 增加搜索结果点击统计
	 * 
	 * @param searchClick
	 * @return
	 * @throws Exception
	 */
	public SearchClickBean insertSearchClickBean(SearchClickBean searchClick)
			throws Exception {
		dao.insertObj("insertSearchClickBean", searchClick);
		return searchClick;
	}

	/**
	 * 修改搜索结果点击统计
	 * 
	 * @param searchClick
	 * @return
	 * @throws Exception
	 */
	public int updateSearchClickBean(SearchClickBean searchClick)
			throws Exception {
		return dao.updateObj("updateSearchClickBean", searchClick);
	}

	/**
	 * 获取搜索结果点击统计
	 * 
	 * @param searchClick
	 * @return
	 * @throws Exception
	 */
	public SearchClickBean getSearchClickBean(SearchClickBean searchClick)
			throws Exception {
		return (SearchClickBean) dao.getAsObject("getSearchClickBean",
				searchClick);
	}

	/**
	 * 获取搜索结果点击统计
	 * 
	 * @param searchClick
	 * @return
	 * @throws Exception
	 */
	public List<SearchClickBean> getSearchClickBeans(SearchClickBean searchClick)
			throws Exception {
		return dao.getAsList("getSearchClickBean", searchClick);
	}

	/**
	 * 获取搜索结果点击统计
	 * 
	 * @param searchClick
	 * @return
	 * @throws Exception
	 */
	public List<SearchClickBean> getSearchClickBeans(
			SearchClickBean searchClick, PageInfo pageInfo) throws Exception {
		Integer count = (Integer) dao.getAsObject("getSearchClickBeanCount",
				searchClick);
		pageInfo.setRecordCount(count == null ? 0 : count);
		if (count != null && count > 0) {
			return dao.getAsList("getSearchClickBean", searchClick, pageInfo);
		}
		return null;
	}

	/**
	 * 删除搜索结果点击统计
	 * 
	 * @param searchClick
	 * @return
	 * @throws Exception
	 */
	public int deleteSearchClickBean(SearchClickBean searchClick)
			throws Exception {
		return dao.deleteObj("deleteSearchClickBean", searchClick);
	}

	/**
	 * 增加广告点击统计
	 * 
	 * @param visiadclick
	 * @return
	 * @throws Exception
	 */
	public VisitAdclickBean insertVisitAdclickBean(VisitAdclickBean visiadclick)
			throws Exception {
		dao.insertObj("insertVisitAdclickBean", visiadclick);
		return visiadclick;
	}

	/**
	 * 修改广告点击统计
	 * 
	 * @param visiadclick
	 * @return
	 * @throws Exception
	 */
	public int updateVisitAdclickBean(VisitAdclickBean visiadclick)
			throws Exception {
		return dao.updateObj("updateVisitAdclickBean", visiadclick);
	}

	/**
	 * 获取广告点击统计
	 * 
	 * @param visiadclick
	 * @return
	 * @throws Exception
	 */
	public VisitAdclickBean getVisitAdclickBean(VisitAdclickBean visiadclick)
			throws Exception {
		return (VisitAdclickBean) dao.getAsObject("getVisitAdclickBean",
				visiadclick);
	}

	/**
	 * 获取广告点击统计
	 * 
	 * @param visiadclick
	 * @return
	 * @throws Exception
	 */
	public List<VisitAdclickBean> getVisitAdclickBeans(
			VisitAdclickBean visiadclick) throws Exception {
		return dao.getAsList("getVisitAdclickBean", visiadclick);
	}

	/**
	 * 获取广告点击统计
	 * 
	 * @param visiadclick
	 * @return
	 * @throws Exception
	 */
	public List<VisitAdclickBean> getVisitAdclickBeans(
			VisitAdclickBean visiadclick, PageInfo pageInfo) throws Exception {
		Integer count = (Integer) dao.getAsObject("getVisitAdclickBeanCount",
				visiadclick);
		pageInfo.setRecordCount(count == null ? 0 : count);
		if (count != null && count > 0) {
			return dao.getAsList("getVisitAdclickBean", visiadclick, pageInfo);
		}
		return null;
	}

	/**
	 * 删除广告点击统计
	 * 
	 * @param visiadclick
	 * @return
	 * @throws Exception
	 */
	public int deleteVisitAdclickBean(VisitAdclickBean visiadclick)
			throws Exception {
		return dao.deleteObj("deleteVisitAdclickBean", visiadclick);
	}

	/**
	 * 增加google 统计代码链接访问统计
	 * 
	 * @param visiadword
	 * @return
	 * @throws Exception
	 */
	public VisitAdwordBean insertVisitAdwordBean(VisitAdwordBean visiadword)
			throws Exception {
		dao.insertObj("insertVisitAdwordBean", visiadword);
		return visiadword;
	}

	/**
	 * 修改google 统计代码链接访问统计
	 * 
	 * @param visiadword
	 * @return
	 * @throws Exception
	 */
	public int updateVisitAdwordBean(VisitAdwordBean visiadword)
			throws Exception {
		return dao.updateObj("updateVisitAdwordBean", visiadword);
	}

	/**
	 * 获取google 统计代码链接访问统计
	 * 
	 * @param visiadword
	 * @return
	 * @throws Exception
	 */
	public VisitAdwordBean getVisitAdwordBean(VisitAdwordBean visiadword)
			throws Exception {
		return (VisitAdwordBean) dao.getAsObject("getVisitAdwordBean",
				visiadword);
	}

	/**
	 * 获取google 统计代码链接访问统计
	 * 
	 * @param visiadword
	 * @return
	 * @throws Exception
	 */
	public List<VisitAdwordBean> getVisitAdwordBeans(VisitAdwordBean visiadword)
			throws Exception {
		return dao.getAsList("getVisitAdwordBean", visiadword);
	}

	/**
	 * 获取google 统计代码链接访问统计
	 * 
	 * @param visiadword
	 * @return
	 * @throws Exception
	 */
	public List<VisitAdwordBean> getVisitAdwordBeans(
			VisitAdwordBean visiadword, PageInfo pageInfo) throws Exception {
		Integer count = (Integer) dao.getAsObject("getVisitAdwordBeanCount",
				visiadword);
		pageInfo.setRecordCount(count == null ? 0 : count);
		if (count != null && count > 0) {
			return dao.getAsList("getVisitAdwordBean", visiadword, pageInfo);
		}
		return null;
	}

	/**
	 * 删除google 统计代码链接访问统计
	 * 
	 * @param visiadword
	 * @return
	 * @throws Exception
	 */
	public int deleteVisitAdwordBean(VisitAdwordBean visiadword)
			throws Exception {
		return dao.deleteObj("deleteVisitAdwordBean", visiadword);
	}

	/**
	 * 增加每日访问统计
	 * 
	 * @param visidailystat
	 * @return
	 * @throws Exception
	 */
	public VisitDailystatBean insertVisitDailystatBean(
			VisitDailystatBean visidailystat) throws Exception {
		dao.insertObj("insertVisitDailystatBean", visidailystat);
		return visidailystat;
	}

	/**
	 * 修改每日访问统计
	 * 
	 * @param visidailystat
	 * @return
	 * @throws Exception
	 */
	public int updateVisitDailystatBean(VisitDailystatBean visidailystat)
			throws Exception {
		return dao.updateObj("updateVisitDailystatBean", visidailystat);
	}

	/**
	 * 获取每日访问统计
	 * 
	 * @param visidailystat
	 * @return
	 * @throws Exception
	 */
	public VisitDailystatBean getVisitDailystatBean(
			VisitDailystatBean visidailystat) throws Exception {
		return (VisitDailystatBean) dao.getAsObject("getVisitDailystatBean",
				visidailystat);
	}

	/**
	 * 获取每日访问统计
	 * 
	 * @param visidailystat
	 * @return
	 * @throws Exception
	 */
	public List<VisitDailystatBean> getVisitDailystatBeans(
			VisitDailystatBean visidailystat) throws Exception {
		return dao.getAsList("getVisitDailystatBean", visidailystat);
	}

	/**
	 * 获取每日访问统计
	 * 
	 * @param visidailystat
	 * @return
	 * @throws Exception
	 */
	public List<VisitDailystatBean> getVisitDailystatBeans(
			VisitDailystatBean visidailystat, PageInfo pageInfo)
			throws Exception {
		Integer count = (Integer) dao.getAsObject("getVisitDailystatBeanCount",
				visidailystat);
		pageInfo.setRecordCount(count == null ? 0 : count);
		if (count != null && count > 0) {
			return dao.getAsList("getVisitDailystatBean", visidailystat,
					pageInfo);
		}
		return null;
	}

	/**
	 * 删除每日访问统计
	 * 
	 * @param visidailystat
	 * @return
	 * @throws Exception
	 */
	public int deleteVisitDailystatBean(VisitDailystatBean visidailystat)
			throws Exception {
		return dao.deleteObj("deleteVisitDailystatBean", visidailystat);
	}

	/**
	 * 增加访问登录
	 * 
	 * @param visilogin
	 * @return
	 * @throws Exception
	 */
	public VisitLoginBean insertVisitLoginBean(VisitLoginBean visilogin)
			throws Exception {
		dao.insertObj("insertVisitLoginBean", visilogin);
		return visilogin;
	}

	/**
	 * 修改访问登录
	 * 
	 * @param visilogin
	 * @return
	 * @throws Exception
	 */
	public int updateVisitLoginBean(VisitLoginBean visilogin) throws Exception {
		return dao.updateObj("updateVisitLoginBean", visilogin);
	}

	/**
	 * 获取访问登录
	 * 
	 * @param visilogin
	 * @return
	 * @throws Exception
	 */
	public VisitLoginBean getVisitLoginBean(VisitLoginBean visilogin)
			throws Exception {
		return (VisitLoginBean) dao.getAsObject("getVisitLoginBean", visilogin);
	}

	/**
	 * 获取访问登录
	 * 
	 * @param visilogin
	 * @return
	 * @throws Exception
	 */
	public List<VisitLoginBean> getVisitLoginBeans(VisitLoginBean visilogin)
			throws Exception {
		return dao.getAsList("getVisitLoginBean", visilogin);
	}

	/**
	 * 获取访问登录
	 * 
	 * @param visilogin
	 * @return
	 * @throws Exception
	 */
	public List<VisitLoginBean> getVisitLoginBeans(VisitLoginBean visilogin,
			PageInfo pageInfo) throws Exception {
		Integer count = (Integer) dao.getAsObject("getVisitLoginBeanCount",
				visilogin);
		pageInfo.setRecordCount(count == null ? 0 : count);
		if (count != null && count > 0) {
			return dao.getAsList("getVisitLoginBean", visilogin, pageInfo);
		}
		return null;
	}

	/**
	 * 删除访问登录
	 * 
	 * @param visilogin
	 * @return
	 * @throws Exception
	 */
	public int deleteVisitLoginBean(VisitLoginBean visilogin) throws Exception {
		return dao.deleteObj("deleteVisitLoginBean", visilogin);
	}

	/**
	 * 增加访问统计
	 * 
	 * @param visiopen
	 * @return
	 * @throws Exception
	 */
	public VisitOpenBean insertVisitOpenBean(VisitOpenBean visiopen)
			throws Exception {
		dao.insertObj("insertVisitOpenBean", visiopen);
		return visiopen;
	}

	/**
	 * 修改访问统计
	 * 
	 * @param visiopen
	 * @return
	 * @throws Exception
	 */
	public int updateVisitOpenBean(VisitOpenBean visiopen) throws Exception {
		return dao.updateObj("updateVisitOpenBean", visiopen);
	}

	/**
	 * 获取访问统计
	 * 
	 * @param visiopen
	 * @return
	 * @throws Exception
	 */
	public VisitOpenBean getVisitOpenBean(VisitOpenBean visiopen)
			throws Exception {
		return (VisitOpenBean) dao.getAsObject("getVisitOpenBean", visiopen);
	}

	/**
	 * 获取访问统计
	 * 
	 * @param visiopen
	 * @return
	 * @throws Exception
	 */
	public List<VisitOpenBean> getVisitOpenBeans(VisitOpenBean visiopen)
			throws Exception {
		return dao.getAsList("getVisitOpenBean", visiopen);
	}

	/**
	 * 获取访问统计
	 * 
	 * @param visiopen
	 * @return
	 * @throws Exception
	 */
	public List<VisitOpenBean> getVisitOpenBeans(VisitOpenBean visiopen,
			PageInfo pageInfo) throws Exception {
		Integer count = (Integer) dao.getAsObject("getVisitOpenBeanCount",
				visiopen);
		pageInfo.setRecordCount(count == null ? 0 : count);
		if (count != null && count > 0) {
			return dao.getAsList("getVisitOpenBean", visiopen, pageInfo);
		}
		return null;
	}

	/**
	 * 删除访问统计
	 * 
	 * @param visiopen
	 * @return
	 * @throws Exception
	 */
	public int deleteVisitOpenBean(VisitOpenBean visiopen) throws Exception {
		return dao.deleteObj("deleteVisitOpenBean", visiopen);
	}

	/**
	 * 增加订单统计
	 * 
	 * @param visiorder
	 * @return
	 * @throws Exception
	 */
	public VisitOrderBean insertVisitOrderBean(VisitOrderBean visiorder)
			throws Exception {
		dao.insertObj("insertVisitOrderBean", visiorder);
		return visiorder;
	}

	/**
	 * 修改订单统计
	 * 
	 * @param visiorder
	 * @return
	 * @throws Exception
	 */
	public int updateVisitOrderBean(VisitOrderBean visiorder) throws Exception {
		return dao.updateObj("updateVisitOrderBean", visiorder);
	}

	/**
	 * 获取订单统计
	 * 
	 * @param visiorder
	 * @return
	 * @throws Exception
	 */
	public VisitOrderBean getVisitOrderBean(VisitOrderBean visiorder)
			throws Exception {
		return (VisitOrderBean) dao.getAsObject("getVisitOrderBean", visiorder);
	}

	/**
	 * 获取订单统计
	 * 
	 * @param visiorder
	 * @return
	 * @throws Exception
	 */
	public List<VisitOrderBean> getVisitOrderBeans(VisitOrderBean visiorder)
			throws Exception {
		return dao.getAsList("getVisitOrderBean", visiorder);
	}

	/**
	 * 获取订单统计
	 * 
	 * @param visiorder
	 * @return
	 * @throws Exception
	 */
	public List<VisitOrderBean> getVisitOrderBeans(VisitOrderBean visiorder,
			PageInfo pageInfo) throws Exception {
		Integer count = (Integer) dao.getAsObject("getVisitOrderBeanCount",
				visiorder);
		pageInfo.setRecordCount(count == null ? 0 : count);
		if (count != null && count > 0) {
			return dao.getAsList("getVisitOrderBean", visiorder, pageInfo);
		}
		return null;
	}

	/**
	 * 删除订单统计
	 * 
	 * @param visiorder
	 * @return
	 * @throws Exception
	 */
	public int deleteVisitOrderBean(VisitOrderBean visiorder) throws Exception {
		return dao.deleteObj("deleteVisitOrderBean", visiorder);
	}

	/**
	 * 增加外链访问
	 * 
	 * @param visioutside
	 * @return
	 * @throws Exception
	 */
	public VisitOutsideBean insertVisitOutsideBean(VisitOutsideBean visioutside)
			throws Exception {
		dao.insertObj("insertVisitOutsideBean", visioutside);
		return visioutside;
	}

	/**
	 * 修改外链访问
	 * 
	 * @param visioutside
	 * @return
	 * @throws Exception
	 */
	public int updateVisitOutsideBean(VisitOutsideBean visioutside)
			throws Exception {
		return dao.updateObj("updateVisitOutsideBean", visioutside);
	}

	/**
	 * 获取外链访问
	 * 
	 * @param visioutside
	 * @return
	 * @throws Exception
	 */
	public VisitOutsideBean getVisitOutsideBean(VisitOutsideBean visioutside)
			throws Exception {
		return (VisitOutsideBean) dao.getAsObject("getVisitOutsideBean",
				visioutside);
	}

	/**
	 * 获取外链访问
	 * 
	 * @param visioutside
	 * @return
	 * @throws Exception
	 */
	public List<VisitOutsideBean> getVisitOutsideBeans(
			VisitOutsideBean visioutside) throws Exception {
		return dao.getAsList("getVisitOutsideBean", visioutside);
	}

	/**
	 * 获取外链访问
	 * 
	 * @param visioutside
	 * @return
	 * @throws Exception
	 */
	public List<VisitOutsideBean> getVisitOutsideBeans(
			VisitOutsideBean visioutside, PageInfo pageInfo) throws Exception {
		Integer count = (Integer) dao.getAsObject("getVisitOutsideBeanCount",
				visioutside);
		pageInfo.setRecordCount(count == null ? 0 : count);
		if (count != null && count > 0) {
			return dao.getAsList("getVisitOutsideBean", visioutside, pageInfo);
		}
		return null;
	}

	/**
	 * 删除外链访问
	 * 
	 * @param visioutside
	 * @return
	 * @throws Exception
	 */
	public int deleteVisitOutsideBean(VisitOutsideBean visioutside)
			throws Exception {
		return dao.deleteObj("deleteVisitOutsideBean", visioutside);
	}

	/**
	 * 增加产品曝光展示
	 * 
	 * @param visiprodshow
	 * @return
	 * @throws Exception
	 */
	public VisitProdshowBean insertVisitProdshowBean(
			VisitProdshowBean visiprodshow) throws Exception {
		dao.insertObj("insertVisitProdshowBean", visiprodshow);
		return visiprodshow;
	}

	/**
	 * 修改产品曝光展示
	 * 
	 * @param visiprodshow
	 * @return
	 * @throws Exception
	 */
	public int updateVisitProdshowBean(VisitProdshowBean visiprodshow)
			throws Exception {
		return dao.updateObj("updateVisitProdshowBean", visiprodshow);
	}

	/**
	 * 获取产品曝光展示
	 * 
	 * @param visiprodshow
	 * @return
	 * @throws Exception
	 */
	public VisitProdshowBean getVisitProdshowBean(VisitProdshowBean visiprodshow)
			throws Exception {
		return (VisitProdshowBean) dao.getAsObject("getVisitProdshowBean",
				visiprodshow);
	}

	/**
	 * 获取产品曝光展示
	 * 
	 * @param visiprodshow
	 * @return
	 * @throws Exception
	 */
	public List<VisitProdshowBean> getVisitProdshowBeans(
			VisitProdshowBean visiprodshow) throws Exception {
		return dao.getAsList("getVisitProdshowBean", visiprodshow);
	}

	/**
	 * 获取产品曝光展示
	 * 
	 * @param visiprodshow
	 * @return
	 * @throws Exception
	 */
	public List<VisitProdshowBean> getVisitProdshowBeans(
			VisitProdshowBean visiprodshow, PageInfo pageInfo) throws Exception {
		Integer count = (Integer) dao.getAsObject("getVisitProdshowBeanCount",
				visiprodshow);
		pageInfo.setRecordCount(count == null ? 0 : count);
		if (count != null && count > 0) {
			return dao
					.getAsList("getVisitProdshowBean", visiprodshow, pageInfo);
		}
		return null;
	}

	/**
	 * 删除产品曝光展示
	 * 
	 * @param visiprodshow
	 * @return
	 * @throws Exception
	 */
	public int deleteVisitProdshowBean(VisitProdshowBean visiprodshow)
			throws Exception {
		return dao.deleteObj("deleteVisitProdshowBean", visiprodshow);
	}

	/**
	 * 增加产品访问统计
	 * 
	 * @param visiproduct
	 * @return
	 * @throws Exception
	 */
	public VisitProductBean insertVisitProductBean(VisitProductBean visiproduct)
			throws Exception {
		dao.insertObj("insertVisitProductBean", visiproduct);
		return visiproduct;
	}

	/**
	 * 修改产品访问统计
	 * 
	 * @param visiproduct
	 * @return
	 * @throws Exception
	 */
	public int updateVisitProductBean(VisitProductBean visiproduct)
			throws Exception {
		return dao.updateObj("updateVisitProductBean", visiproduct);
	}

	/**
	 * 获取产品访问统计
	 * 
	 * @param visiproduct
	 * @return
	 * @throws Exception
	 */
	public VisitProductBean getVisitProductBean(VisitProductBean visiproduct)
			throws Exception {
		return (VisitProductBean) dao.getAsObject("getVisitProductBean",
				visiproduct);
	}

	/**
	 * 获取产品访问统计
	 * 
	 * @param visiproduct
	 * @return
	 * @throws Exception
	 */
	public List<VisitProductBean> getVisitProductBeans(
			VisitProductBean visiproduct) throws Exception {
		return dao.getAsList("getVisitProductBean", visiproduct);
	}

	/**
	 * 获取产品访问统计
	 * 
	 * @param visiproduct
	 * @return
	 * @throws Exception
	 */
	public List<VisitProductBean> getVisitProductBeans(
			VisitProductBean visiproduct, PageInfo pageInfo) throws Exception {
		Integer count = (Integer) dao.getAsObject("getVisitProductBeanCount",
				visiproduct);
		pageInfo.setRecordCount(count == null ? 0 : count);
		if (count != null && count > 0) {
			return dao.getAsList("getVisitProductBean", visiproduct, pageInfo);
		}
		return null;
	}

	/**
	 * 删除产品访问统计
	 * 
	 * @param visiproduct
	 * @return
	 * @throws Exception
	 */
	public int deleteVisitProductBean(VisitProductBean visiproduct)
			throws Exception {
		return dao.deleteObj("deleteVisitProductBean", visiproduct);
	}

	/**
	 * 增加访问注册
	 * 
	 * @param visiregister
	 * @return
	 * @throws Exception
	 */
	public VisitRegisterBean insertVisitRegisterBean(
			VisitRegisterBean visiregister) throws Exception {
		dao.insertObj("insertVisitRegisterBean", visiregister);
		return visiregister;
	}

	/**
	 * 修改访问注册
	 * 
	 * @param visiregister
	 * @return
	 * @throws Exception
	 */
	public int updateVisitRegisterBean(VisitRegisterBean visiregister)
			throws Exception {
		return dao.updateObj("updateVisitRegisterBean", visiregister);
	}

	/**
	 * 获取访问注册
	 * 
	 * @param visiregister
	 * @return
	 * @throws Exception
	 */
	public VisitRegisterBean getVisitRegisterBean(VisitRegisterBean visiregister)
			throws Exception {
		return (VisitRegisterBean) dao.getAsObject("getVisitRegisterBean",
				visiregister);
	}

	/**
	 * 获取访问注册
	 * 
	 * @param visiregister
	 * @return
	 * @throws Exception
	 */
	public List<VisitRegisterBean> getVisitRegisterBeans(
			VisitRegisterBean visiregister) throws Exception {
		return dao.getAsList("getVisitRegisterBean", visiregister);
	}

	/**
	 * 获取访问注册
	 * 
	 * @param visiregister
	 * @return
	 * @throws Exception
	 */
	public List<VisitRegisterBean> getVisitRegisterBeans(
			VisitRegisterBean visiregister, PageInfo pageInfo) throws Exception {
		Integer count = (Integer) dao.getAsObject("getVisitRegisterBeanCount",
				visiregister);
		pageInfo.setRecordCount(count == null ? 0 : count);
		if (count != null && count > 0) {
			return dao
					.getAsList("getVisitRegisterBean", visiregister, pageInfo);
		}
		return null;
	}

	/**
	 * 删除访问注册
	 * 
	 * @param visiregister
	 * @return
	 * @throws Exception
	 */
	public int deleteVisitRegisterBean(VisitRegisterBean visiregister)
			throws Exception {
		return dao.deleteObj("deleteVisitRegisterBean", visiregister);
	}

	/**
	 * 增加搜索统计
	 * 
	 * @param visisearch
	 * @return
	 * @throws Exception
	 */
	public VisitSearchBean insertVisitSearchBean(VisitSearchBean visisearch)
			throws Exception {
		dao.insertObj("insertVisitSearchBean", visisearch);
		return visisearch;
	}

	/**
	 * 修改搜索统计
	 * 
	 * @param visisearch
	 * @return
	 * @throws Exception
	 */
	public int updateVisitSearchBean(VisitSearchBean visisearch)
			throws Exception {
		return dao.updateObj("updateVisitSearchBean", visisearch);
	}

	/**
	 * 获取搜索统计
	 * 
	 * @param visisearch
	 * @return
	 * @throws Exception
	 */
	public VisitSearchBean getVisitSearchBean(VisitSearchBean visisearch)
			throws Exception {
		return (VisitSearchBean) dao.getAsObject("getVisitSearchBean",
				visisearch);
	}

	/**
	 * 获取搜索统计
	 * 
	 * @param visisearch
	 * @return
	 * @throws Exception
	 */
	public List<VisitSearchBean> getVisitSearchBeans(VisitSearchBean visisearch)
			throws Exception {
		return dao.getAsList("getVisitSearchBean", visisearch);
	}

	/**
	 * 获取搜索统计
	 * 
	 * @param visisearch
	 * @return
	 * @throws Exception
	 */
	public List<VisitSearchBean> getVisitSearchBeans(
			VisitSearchBean visisearch, PageInfo pageInfo) throws Exception {
		Integer count = (Integer) dao.getAsObject("getVisitSearchBeanCount",
				visisearch);
		pageInfo.setRecordCount(count == null ? 0 : count);
		if (count != null && count > 0) {
			return dao.getAsList("getVisitSearchBean", visisearch, pageInfo);
		}
		return null;
	}

	/**
	 * 删除搜索统计
	 * 
	 * @param visisearch
	 * @return
	 * @throws Exception
	 */
	public int deleteVisitSearchBean(VisitSearchBean visisearch)
			throws Exception {
		return dao.deleteObj("deleteVisitSearchBean", visisearch);
	}

	/**
	 * 增加访问统计 - 会话
	 * 
	 * @param visisession
	 * @return
	 * @throws Exception
	 */
	public VisitSessionBean insertVisitSessionBean(VisitSessionBean visisession)
			throws Exception {
		dao.insertObj("insertVisitSessionBean", visisession);
		return visisession;
	}

	/**
	 * 修改访问统计 - 会话
	 * 
	 * @param visisession
	 * @return
	 * @throws Exception
	 */
	public int updateVisitSessionBean(VisitSessionBean visisession)
			throws Exception {
		return dao.updateObj("updateVisitSessionBean", visisession);
	}

	/**
	 * 获取访问统计 - 会话
	 * 
	 * @param visisession
	 * @return
	 * @throws Exception
	 */
	public VisitSessionBean getVisitSessionBean(VisitSessionBean visisession)
			throws Exception {
		return (VisitSessionBean) dao.getAsObject("getVisitSessionBean",
				visisession);
	}

	/**
	 * 获取访问统计 - 会话
	 * 
	 * @param visisession
	 * @return
	 * @throws Exception
	 */
	public List<VisitSessionBean> getVisitSessionBeans(
			VisitSessionBean visisession) throws Exception {
		return dao.getAsList("getVisitSessionBean", visisession);
	}

	/**
	 * 获取访问统计 - 会话
	 * 
	 * @param visisession
	 * @return
	 * @throws Exception
	 */
	public List<VisitSessionBean> getVisitSessionBeans(
			VisitSessionBean visisession, PageInfo pageInfo) throws Exception {
		Integer count = (Integer) dao.getAsObject("getVisitSessionBeanCount",
				visisession);
		pageInfo.setRecordCount(count == null ? 0 : count);
		if (count != null && count > 0) {
			return dao.getAsList("getVisitSessionBean", visisession, pageInfo);
		}
		return null;
	}

	/**
	 * 删除访问统计 - 会话
	 * 
	 * @param visisession
	 * @return
	 * @throws Exception
	 */
	public int deleteVisitSessionBean(VisitSessionBean visisession)
			throws Exception {
		return dao.deleteObj("deleteVisitSessionBean", visisession);
	}

	/**
	 * 获取IP地址库
	 * 
	 * @param ipaddress
	 * @return
	 * @throws Exception
	 */
	public IpaddressBean getIpaddressBean(IpaddressBean ipaddress)
			throws Exception {
		return (IpaddressBean) dao.getAsObject("getIpaddressBean", ipaddress);
	}

	/**
	 * 获取IP地址库
	 * 
	 * @param ipaddress
	 * @return
	 * @throws Exception
	 */
	public List<IpaddressBean> getIpaddressBeans(IpaddressBean ipaddress)
			throws Exception {
		return dao.getAsList("getIpaddressBean", ipaddress);
	}
	
    public Long getVisitId(VisitSessionBean visitSession) throws Exception {
    	return (Long) dao.getAsObject("getVisitId", visitSession);
    }
}