package com.fd.statistics.modules.actions.statistics;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.session.FdSession;
import com.fd.session.FdSessionFactory;
import com.fd.statistics.bean.VisitAdwordBean;
import com.fd.statistics.bean.VisitLoginBean;
import com.fd.statistics.bean.VisitOpenBean;
import com.fd.statistics.bean.VisitProdshowBean;
import com.fd.statistics.bean.VisitRegisterBean;
import com.fd.statistics.bean.VisitSessionBean;
import com.fd.statistics.manager.IStatisticsManager;
import com.fd.statistics.modules.actions.BaseAction;
import com.fd.statistics.util.StatisticsUtil;
import com.fd.util.IPAddrUtil;
import com.fd.util.RegexUtil;
import com.fd.util.StringUtil;

/**
 * @author jzq 产品列表页 2009-5-7
 */
public class VisitStatAction extends BaseAction {
	RegexUtil regex = new RegexUtil();

	@SuppressWarnings("unused")
	public void doPerform(RunData data, Context context) throws Exception {
		try {

			IStatisticsManager statisticsManager = (IStatisticsManager) this
					.getBean("statisticsManager");
			String userAgent = data.getRequest().getHeader("user-agent");
			String ipAddress = IPAddrUtil.getIpAddr(data.getRequest());
			FdSession session = FdSessionFactory.getFdSession(data);
			VisitSessionBean visit2 = (VisitSessionBean) session.getAttribute(StatisticsUtil.VISIT_SESSION_NAME);
			Boolean ignore = (Boolean) session.getAttribute("KEY_LOGIN_BUYER");
			String referer = data.getParameters().getString("referer");
			String url = data.getParameters().getString("url");
			if (StringUtils.isEmpty(url)) {
				return;
			}
			//登陆
			String buyer = data.getParameters().getString("userId");

			VisitSessionBean tmp = null;
			if(visit2 != null){
				tmp = new VisitSessionBean();
				tmp.setVisitId(visit2.getVisitId());
				tmp.setBuyer(buyer);
				tmp = statisticsManager.getVisitSession(tmp);
			}
			
			if(buyer != null && tmp == null){
				saveVisitLogin(statisticsManager,buyer,visit2);
			}
			//注册
			String regBuyer = data.getParameters().getString("regUser");
			if(regBuyer != null && visit2 != null){
				saveVisitRegister(statisticsManager,regBuyer,visit2);
			}
			
			
			String visitFrom = StringUtil.getRequestParameters(
					data.getRequest(), "f");

			Date now = new Date();
			VisitSessionBean visit = StatisticsUtil.getVisitSession(data, url, now,
					 statisticsManager, session);

			try {
				// 保存打开页面
				String productIds = data.getParameters().getString("productIds");
				saveOpen(statisticsManager, referer, url, visitFrom, now, visit, productIds);
			} catch (Exception e) {
				e.printStackTrace();
			}
/*			try {
				// Affiliate信息
				saveAffiliate(statisticsManager, url, auid, now, visit, data);
			} catch (Exception e) {
				e.printStackTrace();
			}*/
			try {
				// adword信息
				saveAdword(statisticsManager, data, now, visit);
			} catch (Exception e) {
				e.printStackTrace();
			}
/*			try {
				// 搜索无结果页
				if (url.contains("searchNoResult.shtml")) {
					saveSearchNoResult(statisticsManager, data, now, visit);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}*/
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			context.put("data", "");
		}

	}

	private void saveAdword(IStatisticsManager statisticsManager, RunData data, Date now, VisitSessionBean visit) throws Exception {
		String referer = data.getParameters().getString("referer");
		String url = data.getParameters().getString("url");
		// 广告系列来源
		String utmSource = StringUtil.getRequestParameters(data.getRequest(), "utm_source");
		if (StringUtils.isEmpty(utmSource)) {
			return;
		}
		// 广告系列媒介
		String utmMedium = StringUtil.getRequestParameters(data.getRequest(), "utm_medium");
		// 广告系列字词
		String utmTerm = StringUtil.getRequestParameters(data.getRequest(), "utm_term");
		// 广告系列内容
		String utmContent = StringUtil.getRequestParameters(data.getRequest(), "utm_content");
		// 广告系列名称
		String utmCampaign = StringUtil.getRequestParameters(data.getRequest(), "utm_campaign");
		VisitAdwordBean adword = new VisitAdwordBean();
		adword.setUtmCampaign(utmCampaign);
		adword.setUtmContent(utmContent);
		adword.setUtmMedium(utmMedium);
		adword.setUtmSource(utmSource);
		adword.setUtmTerm(utmTerm);
		adword.setVisitId(visit.getVisitId());
		adword.setLandingPage(url);
		adword.setClickTime(now);
		adword.setReferer(referer);
		String srcKey = StringUtil.getRequestParameters(data.getRequest(), "q");
		adword.setSrckey(srcKey);
		statisticsManager.addVisitAdword(adword);
	}

	/**
	 * @param statisticsManager
	 * @param url
	 * @param auid
	 * @param now
	 * @param visit
	 * @throws Exception
	 *//*
	private void saveAffiliate(IStatisticsManager statisticsManager,
			String url, String auid, Date now, VisitSessionBean visit, RunData data)
			throws Exception {
		VisitAffiliate affiliate = null;
		if (StringUtils.isNotEmpty(auid)) {
			String type = StringUtil.getRequestParameters(data.getRequest(), "af_type");
			String format = StringUtil.getRequestParameters(data.getRequest(), "af_format");
			String term = StringUtil.getRequestParameters(data.getRequest(), "af_term");
			term = SecurityUtil.decryptMode(term);
			affiliate = new VisitAffiliate();
			affiliate.setClickTime(now);	
			affiliate.setVisitId(visit.getVisitId());
			affiliate.setUrl(url);
			affiliate.setAuid(auid);
			affiliate.setType(StringUtil.getAsInt(type, 3));
			affiliate.setFormat(StringUtil.getAsInt(format, 0));
			affiliate.setTerm(term);
			statisticsManager.addVisitAffiliate(affiliate);
		}
	}
*/
	
/*	private void saveSearchNoResult(IStatisticsManager statisticsManager, RunData data, Date now, VisitSessionBean visit) throws Exception {
		String keyWords = StringUtil.getRequestParameters(data.getRequest(), "keywords");
		String freeShipping = StringUtil.getRequestParameters(data.getRequest(), "freeShipping");
		String category = StringUtil.getRequestParameters(data.getRequest(), "category"); // 分类
		String sellerName = StringUtil.getRequestParameters(data.getRequest(), "sellerName");
		String pageSize = StringUtil.getRequestParameters(data.getRequest(), "pageSize");
		String pageIndex = StringUtil.getRequestParameters(data.getRequest(), "pageIndex");
		String onlyWholesale = StringUtil.getRequestParameters(data.getRequest(), "onlyWholesale");
		String onlyDiscount = StringUtil.getRequestParameters(data.getRequest(), "showDiscount");
		int resultCount = 0;
		SearchResultVo searchResult = new SearchResultVo();
		searchResult.setVisitId(visit.getVisitId());
		searchResult.setCategory(category);
		searchResult.setKeyword(keyWords);
		searchResult.setSeller(sellerName);
		searchResult.setPageIndex(StringUtil.getAsInt(pageIndex, 1));
		searchResult.setPageSize(StringUtil.getAsInt(pageSize, Constants.PAGE_SIZE));
		searchResult.setOnlyFreeshipping(StringUtil.getAsBoolean(freeShipping));
		searchResult.setOnlyWholesale(StringUtil.getAsBoolean(onlyWholesale));
		searchResult.setOnlyDiscount(StringUtil.getAsBoolean(onlyDiscount));
		searchResult.setResultCount(resultCount);
		searchResult.setSearchTime(now);
		searchResult.setSessionId(FdSessionFactory.getSessionID(data.getRequest(), data.getResponse()));
		ISearchResultManager searchResultManager = (ISearchResultManager) getBean("searchResultManager", data);
		searchResultManager.saveSearchResult(searchResult);
	}*/
	
	/**
	 * @param statisticsManager
	 * @param referer
	 * @param url
	 * @param visitFrom
	 * @param now
	 * @param visit
	 * @throws Exception
	 */
	private void saveOpen(IStatisticsManager statisticsManager, String referer,
			String url, String visitFrom, Date now, VisitSessionBean visit, String productIds)
			throws Exception {
		VisitOpenBean visitOpen = new VisitOpenBean();
		visitOpen.setOpenUrl(url);
		visitOpen.setOpenFrom(visitFrom);
		visitOpen.setReferer(referer);
		visitOpen.setVisitId(visit.getVisitId());
		visitOpen = statisticsManager.addVisitOpen(visitOpen);
		if(visitOpen != null && productIds != null){
			String[] products = productIds.split(",");
		    List<String> apacheList = new ArrayList<String>();  
		    CollectionUtils.addAll(apacheList, products);  
			List<String> tmp = removeDuplicate(apacheList);
			
			for(int i=0;i<tmp.size();i++){
				Long id = Long.valueOf(tmp.get(i));
				VisitProdshowBean vpb = new VisitProdshowBean();
				vpb.setOpenId(visitOpen.getOpenId());
				vpb.setProductId(id);
				statisticsManager.addVisitProdshow(vpb);
			}
		}
		
	}
	
	private void saveVisitLogin(IStatisticsManager statisticsManager, String buyer, VisitSessionBean visit){
		VisitLoginBean visitLogin = new VisitLoginBean();
		visitLogin.setBuyer(buyer);
		visitLogin.setLoginTime(new Date());
		visitLogin.setVisitId(visit.getVisitId());
		try {
			statisticsManager.addVisitLogin(visitLogin);
			visit.setBuyer(buyer);
			statisticsManager.updateVisitSession(visit);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	
	private void saveVisitRegister(IStatisticsManager statisticsManager, String buyer, VisitSessionBean visit){
/*		VisitLoginBean visitLogin = new VisitLoginBean();
		visitLogin.setBuyer(buyer);
		visitLogin.setLoginTime(new Date());
		visitLogin.setVisitId(visit.getVisitId());*/
		VisitRegisterBean visitRegister = new VisitRegisterBean();
		visitRegister.setBuyer(buyer);
		visitRegister.setVisitId(visit.getVisitId());
		visitRegister.setRegisterTime(new Date());
		try {
			statisticsManager.addVisitRegister(visitRegister);
			/*visit.setBuyer(buyer);
			statisticsManager.updateVisitSession(visit);*/
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static List<String>  removeDuplicate(List<String> list) {
	      HashSet<String> h = new HashSet<String>(list);
	      list.clear();
	      list.addAll(h);
	      return list;
	} 

}
