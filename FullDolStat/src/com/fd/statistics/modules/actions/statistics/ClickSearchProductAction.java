package com.fd.statistics.modules.actions.statistics;

import java.util.Date;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.session.FdSession;
import com.fd.session.FdSessionFactory;
import com.fd.statistics.bean.SearchClickBean;
import com.fd.statistics.bean.VisitAdclickBean;
import com.fd.statistics.bean.VisitSessionBean;
import com.fd.statistics.manager.IStatisticsManager;
import com.fd.statistics.modules.actions.BaseAction;
import com.fd.statistics.util.StatisticsUtil;
import com.fd.util.IPAddrUtil;

/**
 * @author jzq 产品列表页 2009-5-7
 */
public class ClickSearchProductAction extends BaseAction {

	public void doPerform(RunData data, Context context) throws Exception {
		try {
			data.setCharSet("UTF-8");
			IStatisticsManager statisticsManager = (IStatisticsManager) getBean(
					"statisticsManager");
			String userAgent = data.getRequest().getHeader("user-agent");
			String ipAddress = IPAddrUtil.getIpAddr(data.getRequest());
			if (statisticsManager.ignore(userAgent, ipAddress)) {
				// 排除掉爬虫和国内
				return;
			}
			String referer = data.getRequest().getHeader("Referer");
			long searchResultId = data.getParameters().getLong("sid", 0);
			Long productId = data.getParameters().getLong("productId");
			int position = data.getParameters().getInt("p");
			//int p = StringUtil.getAsInt(String.valueOf(position), 0);
			String url = data.getParameters().getString("url");
			Date now = new Date();
			FdSession session = FdSessionFactory.getFdSession(data);
			VisitSessionBean visit = StatisticsUtil.getVisitSession(data, url, now,
					statisticsManager, session);
/*			if (StringUtils.isNotEmpty(itemCode)) {
				VisitProductBean visitProduct = new VisitProductBean();
				visitProduct.setCart(0);
				visitProduct.setClickTime(now);
				visitProduct.setItemCode(itemCode);
				visitProduct.setPosition(position);
				visitProduct.setVisitId(visit.getVisitId());
				visitProduct.setUrl(referer);
				statisticsManager.saveVisitProduct(visitProduct);
			}*/
			if (position > 0 && searchResultId > 0) {
				SearchClickBean click = new SearchClickBean();
				click.setProductId(productId);
				click.setPosition(position);
				click.setResultId(searchResultId);
/*				ISearchResultManager searchResultManager = (ISearchResultManager) getBean(
						"searchResultManager", data);
				searchResultManager.saveSearchClick(click);*/
				statisticsManager.saveSearchClick(click);
			} else if (position == 0) {
				VisitAdclickBean visitAdClick = new VisitAdclickBean();
				visitAdClick.setClickTime(now);
				visitAdClick.setPosition(position);
				visitAdClick.setUrl(referer);
				visitAdClick.setVisitId(visit.getVisitId());
				statisticsManager.addVisitAdClick(visitAdClick);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			context.put("data", "");
		}
	}
}
