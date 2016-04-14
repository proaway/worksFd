/**
 * ConfirmOrder.java
 * @author:  ZhouRongyu
 */
package com.fd.fashion.modules.screens.order;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.constants.SessionConstants;
import com.fd.fashion.buyer.bean.BuyerBean;
import com.fd.fashion.dict.bean.RegionBean;
import com.fd.fashion.dict.manager.IDictManager;
import com.fd.fashion.modules.screens.SecureScreen;
import com.fd.fashion.order.bean.OrderBean;
import com.fd.fashion.order.manager.IOrderManager;
import com.fd.payment.bean.PaymentTypeBean;
import com.fd.payment.bean.WesternSettingBean;
import com.fd.payment.manager.IPaymentManager;
import com.fd.session.FdSession;
import com.fd.session.FdSessionFactory;
import com.fd.util.SecurityUtil;
import com.fd.util.StringUtil;

/**
 * @CreateDate:  2013-4-2 下午4:24:31 
 * @author:  ZhouRongyu
 * @Description:  TODO
 * 
 * @version:  V1.0
 */
public class PayOrder extends SecureScreen{
	@SuppressWarnings("unchecked")
	public void doBuildTemplate(RunData data, Context context) throws Exception {
		super.doBuildTemplate(data, context);
		data.setLayoutTemplate("/OrderLayout.html");
		// 设置页面不缓存
		data.getResponse().setHeader("Cache-Control", "no-store");
		data.getResponse().setHeader("Pragma", "no-cache");
		data.getResponse().setDateHeader("Expires", 0);
		
		String ordersinfo = data.getParameters().getString("orderInfo");
		context.put("ordersinfo", ordersinfo);
		
		ordersinfo = SecurityUtil.decryptMode(ordersinfo);
		JSONArray ordersArray = JSONArray.fromObject(ordersinfo);
		JSONObject orderObj = (JSONObject) ordersArray.get(0);
		Long orderId = orderObj.getLong("orderId");
		String productIds = orderObj.getString("productIds");

		context.put("productIds", productIds);
		
		FdSession session = FdSessionFactory.getFdSession(data);
		BuyerBean buyer = (BuyerBean) session.getAttribute(SessionConstants.KEY_LOGIN_BUYER);
		
		IOrderManager orderManager = (IOrderManager) getBean("orderManager");
		OrderBean order = orderManager.getOrder(orderId);
		if (order.getBuyerId().longValue() != buyer.getBuyerId().longValue()) { // 不是登录买家的订单
			return;
		}
		
		if (order.getOrderStatus().equals("OA001") || order.getOrderStatus().equals("OA003")) { // 订单不是待付款状态
			context.put("order", order);
			IPaymentManager paymentManager = (IPaymentManager) getBean("paymentManager");
			List<PaymentTypeBean> paymentTypes = paymentManager.getEnabledPayments();
			for (PaymentTypeBean paymentTypeBean : paymentTypes) {
				if (paymentTypeBean.getPaymentType() == 2) { // 西联
					WesternSettingBean westernSetting = paymentManager.getWesternSetting();
					context.put("westernSetting", westernSetting);
				}
			}
			context.put("paymentTypes", paymentTypes);
		}
		
		double couponAmount = order.getCouponAmount() == null ? 0 : order.getCouponAmount();
		double adjustAmount = order.getAdjustAmount() == null ? 0 : order.getAdjustAmount();
		
		double total = order.getTotal() - couponAmount + adjustAmount;
		context.put("total", total);
		
		IDictManager dictManager = (IDictManager) getBean("dictManager");
		List<RegionBean> countries = dictManager.getCountries();
		context.put("countries", countries);
		context.put("cartStep", 3);
	}

}
