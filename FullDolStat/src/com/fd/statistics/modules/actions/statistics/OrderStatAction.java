package com.fd.statistics.modules.actions.statistics;

import java.util.Date;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.session.FdSession;
import com.fd.session.FdSessionFactory;
import com.fd.statistics.bean.VisitOrderBean;
import com.fd.statistics.bean.VisitProductBean;
import com.fd.statistics.bean.VisitSessionBean;
import com.fd.statistics.manager.IStatisticsManager;
import com.fd.statistics.modules.actions.BaseAction;
import com.fd.statistics.util.StatisticsUtil;
import com.fd.util.RegexUtil;
import com.fd.util.SecurityUtil;


/**
 * @author jzq 产品列表页 2009-5-7
 */
public class OrderStatAction extends BaseAction {
	RegexUtil regex = new RegexUtil();

	public void doPerform(RunData data, Context context) throws Exception {
		data.setCharSet("UTF-8");
		try {
			IStatisticsManager statisticsManager = (IStatisticsManager) getBean(
					"statisticsManager");
			String url = data.getParameters().getString("url");
			Date now = new Date();
			FdSession session = FdSessionFactory.getFdSession(data);
			VisitSessionBean visit = StatisticsUtil.getVisitSession(data, url, now,
					statisticsManager, session);
			// 订单
			String ordersinfo = data.getParameters().getString("ordersinfo");
			ordersinfo = SecurityUtil.decryptMode(ordersinfo);
			JSONArray ordersArray = JSONArray.fromObject(ordersinfo);
			for (Object obj : ordersArray) {
				JSONObject orderObj = (JSONObject) obj;
				Long orderId = orderObj.getLong("orderId");
				String productIds = orderObj.getString("productIds");
				String ids[] = productIds.split(",");
				VisitOrderBean vo = new VisitOrderBean();
				vo.setVisitId(visit.getVisitId());
				vo.setOrderId(orderId);
				statisticsManager.addVisitOrder(vo);
				for (String id : ids) {
					VisitProductBean vp = new VisitProductBean();
					vp.setVisitId(visit.getVisitId());
					vp.setProductId(Long.valueOf(id));
					vp.setOrderNo(orderId);
					vp.setCart(4);
					statisticsManager.saveVisitProduct(vp);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			context.put("data", "");
		}
	}
}
