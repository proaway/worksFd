package com.fd.statistics.manager;


import java.util.Date;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.fd.fashion.dict.service.IDictService;
import com.fd.statistics.bean.IpaddressBean;
import com.fd.statistics.bean.SearchClickBean;
import com.fd.statistics.bean.VisitAdclickBean;
import com.fd.statistics.bean.VisitAdwordBean;
import com.fd.statistics.bean.VisitLoginBean;
import com.fd.statistics.bean.VisitOpenBean;
import com.fd.statistics.bean.VisitOrderBean;
import com.fd.statistics.bean.VisitOutsideBean;
import com.fd.statistics.bean.VisitProdshowBean;
import com.fd.statistics.bean.VisitProductBean;
import com.fd.statistics.bean.VisitRegisterBean;
import com.fd.statistics.bean.VisitSearchBean;
import com.fd.statistics.bean.VisitSessionBean;
import com.fd.statistics.service.IStatisticsService;
import com.fd.util.IPAddrUtil;
import com.fd.util.RegexUtil;

@Transactional(rollbackFor=Exception.class)
@Component
public class StatisticsManager implements IStatisticsManager {
    @Autowired      
    @Qualifier("statisticsService") 
	private IStatisticsService statisticsService;

	@Autowired
	@Qualifier("dictService")
	IDictService dictService;


	/**
	 * 判断是否爬虫
	 * 
	 * @param visitSession
	 * @return
	 * @throws Exception
	 */
	public boolean ignore(String userAgent, String ipAddress) throws Exception {
		IpaddressBean IpAddress = new IpaddressBean();
		// 判断是否爬虫
		if (StringUtils.isNotEmpty(userAgent)) {
			for (String spiderFlag : IpAddress.spiderFlags) {
				if (userAgent.toLowerCase().contains(spiderFlag.toLowerCase())) {
					return true;
				}
			}
		}
		// 判断是否国内
		if (StringUtils.isNotEmpty(ipAddress)) {
			Long ipLong = IPAddrUtil.ip2Long(ipAddress);
			IpAddress.setIpstart(ipLong);
			IpaddressBean ipAdd = statisticsService.getIpaddressBean(IpAddress);
			if (ipAdd != null) {
				if ("cn".equals(ipAdd.getCountry()))
					return true;
				if (1 == ipAdd.getIsspider())
					return true;
			}
		}
		return false;
	}

	/**
	 * 新增访问
	 * 
	 * @param visitSession
	 * @return
	 * @throws Exception
	 */
	public synchronized VisitSessionBean saveVisitSession(VisitSessionBean visisession) throws Exception {
		Long visitId = statisticsService.getVisitId(visisession);
		if (visitId != null && visitId.longValue()>0) {
			visisession.setVisitId(visitId);
			return visisession;
		}
		
/*		// 判断是否国内
		String ipAddress = visisession.getIp();
		Long ipLong = IPAddrUtil.ip2Long(ipAddress);
		visisession.setIpAddress(ipLong);
 		IpAddress ipAdd = dictService.getIpAddress(ipLong);
 		visisession.setIsCn(0);
 		visisession.setIsSpider(0);
		if (ipAdd != null) {
			if ("cn".equals(ipAdd.getCountry()))
				visitSession.setIsCn(1);
			if (1 == ipAdd.getIsSpider().intValue())
				visitSession.setIsSpider(1);
		}*/
		// 判断是否国内
		if (StringUtils.isNotEmpty(visisession.getIp())) {
			Long ipLong = IPAddrUtil.ip2Long(visisession.getIp());
			
			visisession.setIpAddress(ipLong);
	 		IpaddressBean IpAddress = new IpaddressBean();
	 		//dictService.getIpAddress(ipLong);
	 		IpAddress.setIpstart(ipLong);
			IpaddressBean ipAdd = statisticsService.getIpaddressBean(IpAddress);
			
			if (ipAdd != null) {
				visisession.setCountry(ipAdd.getCountry());
/*				if (1 == ipAdd.getIsSpider().intValue())
					visisession.sets;*/
			}
		}
		return statisticsService.insertVisitSessionBean(visisession);
	}

	/**
	 * 获取访问登陆记录
	 * 
	 * @param visitSession
	 * @return
	 * @throws Exception
	 */
	public VisitSessionBean getVisitSession(VisitSessionBean visitSession) throws Exception{
		return statisticsService.getVisitSessionBean(visitSession);
	}
	
	/**
	 * 更新访问登陆记录
	 * 
	 * @param visitSession
	 * @return
	 * @throws Exception
	 */
	public int updateVisitSession(VisitSessionBean visitSession) throws Exception{
		return statisticsService.updateVisitSessionBean(visitSession);
	}
	
	/**
	 * 新增一个访问统计
	 * 
	 * @param visitOpen
	 * @return
	 * @throws Exception
	 */
	public VisitOpenBean addVisitOpen(VisitOpenBean visitOpen) throws Exception {
		VisitOpenBean vo = statisticsService.getVisitOpenBean(visitOpen);
		if (vo == null) {
			Date now = new Date();
			visitOpen.setOpenTime(now);
			vo = statisticsService.insertVisitOpenBean(visitOpen);
			String referer = visitOpen.getReferer();
			if (StringUtils.isNotEmpty(referer)) {
				// 检查是否外链跳转过来，如果是，保存信息到冗余表
				RegexUtil regex = new RegexUtil();
				if(!regex.isMatch(referer, "^https?://[^\\.]+?\\.fashion\\.com")) {
					String domain = regex.getMatchedStr(referer, "^https?://([^/]+)", 1);
					VisitOutsideBean visioutside = new VisitOutsideBean();
					PropertyUtils.copyProperties(visioutside, visitOpen);
					visioutside.setDomain(domain);
					statisticsService.insertVisitOutsideBean(visioutside);
				}
			}
			return vo;
		}else{
			return null;
		}
	}
	
	/**
	 * 新增一个访问登录
	 * 
	 * @param visitLogin
	 * @return
	 * @throws Exception
	 */
	public VisitLoginBean addVisitLogin(VisitLoginBean visitLogin) throws Exception {
		return statisticsService.insertVisitLoginBean(visitLogin);
	}
	
	/**
	 * 增加一个访问注册
	 * 
	 * @param visitRegister
	 * @return
	 * @throws Exception
	 */
	public VisitRegisterBean addVisitRegister(VisitRegisterBean visitRegister) throws Exception {
		return statisticsService.insertVisitRegisterBean(visitRegister);
	}
	
	/**
	 * 新增一个访问产品
	 * 
	 * @param visitProduct
	 * @return
	 * @throws Exception
	 */
	public VisitProductBean saveVisitProduct(VisitProductBean visitProduct) throws Exception {
		VisitProductBean tmp = new VisitProductBean();
		tmp.setProductId(visitProduct.getProductId());
		tmp.setVisitId(visitProduct.getVisitId());
		VisitProductBean vp = statisticsService.getVisitProductBean(tmp);
		if (vp == null) {
			return statisticsService.insertVisitProductBean(visitProduct);
		}
		if (visitProduct.getCart() == null && vp.getCart() != null) {
			visitProduct.setCart(vp.getCart());
		}
		if (visitProduct.getCart() != null && vp.getCart() != null
				&& visitProduct.getCart() < vp.getCart()) {
			visitProduct.setCart(vp.getCart());
		}
		if (null != visitProduct.getOrderNo()
				&& null != vp.getOrderNo()) {
			visitProduct.setOrderNo(vp.getOrderNo());
		}
		if (null != vp.getPosition() && null != vp.getPosition()) {
			visitProduct.setPosition(vp.getPosition());
			visitProduct.setUrl(vp.getUrl());
		}
		statisticsService.updateVisitProductBean(visitProduct);
		return visitProduct;
	}
	
	/**
	 * 新增一个访问产品
	 * 
	 * @param visitOrder
	 * @return
	 * @throws Exception
	 */
	public VisitOrderBean addVisitOrder(VisitOrderBean visitOrder) throws Exception {
		VisitOrderBean vo = statisticsService.getVisitOrderBean(visitOrder);
		if (vo == null) {
			return statisticsService.insertVisitOrderBean(visitOrder);
		}
		return vo;
	}
	
	/**
	 * 新增一个访问广告点击
	 * 
	 * @param visitAdClick
	 * @return
	 * @throws Exception
	 */
	public VisitAdclickBean addVisitAdClick(VisitAdclickBean visiadclick) throws Exception {
		VisitAdclickBean vac = statisticsService.getVisitAdclickBean(visiadclick);
		if (vac == null) {
			return statisticsService.insertVisitAdclickBean(visiadclick);
		}
		return null;
	}
	
/*	*//**
	 * 新增一个Affiliate访问
	 * 
	 * @param affiliate
	 * @return
	 * @throws Exception
	 *//*
	public VisitAffiliate addVisitAffiliate(VisitAffiliate affiliate) throws Exception {
		VisitAffiliate va = statisticsService.getVisitAffiliate(affiliate);
		if (va == null) {
			return statisticsService.addVisitAffiliate(affiliate);
		}
		return null;
	}
	*/
	/**
	 * 新增一个搜索信息
	 * 
	 * @param search
	 * @return
	 * @throws Exception
	 */
	public VisitSearchBean addVisitSearch(VisitSearchBean visisearch) throws Exception {
		VisitSearchBean vsk = statisticsService.getVisitSearchBean(visisearch);
		if (vsk == null) {
			Date now = new Date();
			visisearch.setSearchTime(now);
			return statisticsService.insertVisitSearchBean(visisearch);
		}
		return vsk;
	}
	
	/**
	 * 保存点击记录
	 * 
	 * @param click
	 * @return
	 * @throws Exception
	 */
	public SearchClickBean saveSearchClick(SearchClickBean click) throws Exception{
		SearchClickBean tmp = statisticsService.getSearchClickBean(click);
		if (tmp  == null) {
			statisticsService.insertSearchClickBean(click);
		}

		return click;
	}
	
	/**
	 * 新增一个Adword信息
	 * 
	 * @param adword
	 * @return
	 * @throws Exception
	 */
	public VisitAdwordBean addVisitAdword(VisitAdwordBean visiadword) throws Exception {
		VisitAdwordBean ad = statisticsService.getVisitAdwordBean(visiadword);
		if (ad == null) {
			return statisticsService.insertVisitAdwordBean(visiadword);
		}
		return null;
	}
	
	/**
	 * 新增一个产品曝光信息
	 * 
	 * @param adword
	 * @return
	 * @throws Exception
	 */
	public VisitProdshowBean addVisitProdshow(VisitProdshowBean productshow) throws Exception{
		VisitProdshowBean tmp = statisticsService.getVisitProdshowBean(productshow);
		if(tmp == null){
			return statisticsService.insertVisitProdshowBean(productshow);
		}
		return tmp;
	}

}
