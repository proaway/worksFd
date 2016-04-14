package com.fd.statistics.modules.actions.statistics;

import java.util.Date;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.session.FdSession;
import com.fd.session.FdSessionFactory;
import com.fd.statistics.bean.VisitProductBean;
import com.fd.statistics.bean.VisitSessionBean;
import com.fd.statistics.manager.IStatisticsManager;
import com.fd.statistics.modules.actions.BaseAction;
import com.fd.statistics.util.StatisticsUtil;
import com.fd.util.IPAddrUtil;
import com.fd.util.RegexUtil;



/**
 * @author jzq 产品列表页 2009-5-7
 */
public class ProductStatAction extends BaseAction {
	RegexUtil regex = new RegexUtil();

	public void doPerform(RunData data, Context context) throws Exception {
		try {
			data.setCharSet("UTF-8");
			IStatisticsManager statisticsManager = (IStatisticsManager) this.getBean(
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
			// 订单
			int cart = data.getParameters().getInt("cart", 0);
			String productIds = data.getParameters().getString("productIds");
			String ids[] = productIds.split(",");
			for (String productId : ids) {
				VisitProductBean vp = new VisitProductBean();
				vp.setVisitId(visit.getVisitId());
				vp.setProductId(Long.valueOf(productId));
				vp.setCart(cart);

				vp.setClickTime(now);
				statisticsManager.saveVisitProduct(vp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			context.put("data", "");
		}
	}
}
