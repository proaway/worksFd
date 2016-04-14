package com.fd.statistics.modules.actions.statistics;

import java.util.Date;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.session.FdSession;
import com.fd.session.FdSessionFactory;
import com.fd.statistics.bean.VisitSearchBean;
import com.fd.statistics.bean.VisitSessionBean;
import com.fd.statistics.manager.IStatisticsManager;
import com.fd.statistics.modules.actions.BaseAction;
import com.fd.statistics.util.StatisticsUtil;
import com.fd.util.Constants;
import com.fd.util.IPAddrUtil;
import com.fd.util.RegexUtil;

/**
 * @author 
 */
public class SaveSearchAction extends BaseAction {
	RegexUtil regex = new RegexUtil();

	public void doPerform(RunData data, Context context) throws Exception {
			data.setCharSet("UTF-8");
			IStatisticsManager statisticsManager = (IStatisticsManager) getBean(
					"statisticsManager");
			String userAgent = data.getRequest().getHeader("user-agent");
			String ipAddress = IPAddrUtil.getIpAddr(data.getRequest());
			if (statisticsManager.ignore(userAgent, ipAddress)) {
				// 排除掉爬虫和国内
				return;
			}
			String url = data.getParameters().getString("url");
			Date now = new Date();
			FdSession session = FdSessionFactory.getFdSession(data);
			VisitSessionBean visit = StatisticsUtil.getVisitSession(data, url, now,
					statisticsManager, session);
			// 搜索关键词
			String keywords = data.getParameters().getString("keywords");
/*			boolean freeShipping = data.getParameters().getBoolean(
					"freeShipping", false);*/
			String category = data.getParameters().get("category"); // 分类
/*			String itemCode = data.getParameters().get("itemCode");
			String withoutKeywords = data.getParameters()
					.get("withoutKeywords");
			String sellerName = data.getParameters().get("sellerName");
			boolean onlyDiscount = data.getParameters().getBoolean(
					"onlyDiscount", false);
			boolean onlyWholesale = data.getParameters().getBoolean(
					"onlyWholesale", false);*/
			
			int pageSize = data.getParameters().getInt("pageSize", Constants.PAGE_SIZE);
			int pageIndex = data.getParameters().getInt("pageIndex", 1);
			int resultCount = data.getParameters().getInt("resultCount");
			
			VisitSearchBean visitSearch = new VisitSearchBean();
			visitSearch.setVisitId(visit.getVisitId());
			visitSearch.setCategory(category);
			visitSearch.setKeyword(keywords);
			visitSearch.setPageIndex(pageIndex);
			visitSearch.setPageSize(pageSize);
			visitSearch.setResultCount(resultCount);
			
			visitSearch = statisticsManager.addVisitSearch(visitSearch);
			context.put("contentdata", "resultId = '" + visitSearch.getResultId() + "';");

	}
}
