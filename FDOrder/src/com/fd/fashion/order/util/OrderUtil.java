package com.fd.fashion.order.util;

import java.util.ArrayList;
import java.util.List;

import com.fd.fashion.order.bean.OrderBean;
import com.fd.fashion.order.vo.OrderGroup;
import com.fd.payment.util.PaypalApiUtil;
import com.fd.util.StringUtil;
import com.paypal.sdk.core.nvp.NVPDecoder;
import com.paypal.sdk.core.nvp.NVPEncoder;

public class OrderUtil {
	/** 订单金额类型 1-付款 */
	public static int AMOUNT_TYPE_PAY = 1;
	/** 订单金额类型 2-金额调整 */
	public static int AMOUNT_TYPE_ADJUST = 2;
	/** 订单金额类型 3-退款 */
	public static int AMOUNT_TYPE_REFUND = 3;
	
	/**
	 * 计算订单实际金额
	 * 
	 * @param order
	 * @return
	 */
	public static double calOrderTotal(OrderBean order) {
		double total = order.getTotal();
		// Coupon折扣
		if (order.getCouponAmount() != null) {
			total = total - order.getCouponAmount();
		}
		// 金额调整
		if (order.getAdjustAmount() != null) {
			total = total + order.getAdjustAmount();
		}
		return total;
	}
	
	/**
	 * 判断订单是否风险
	 * 
	 * @param orders
	 * @param payObj 支付方式对应对象参数，例如Paypal支付，是PaypalipnBean......
	 * @return
	 */
	public static String isRisk(OrderBean orders, Object payObj) {
		return null;
	}


	/**
	 * @param orderList
	 * @return 计算总金额
	 */
	public static Double getTotalAmount(List<OrderBean> orderList) {
		Double totalAmount = 0D;
		for (OrderBean order : orderList) {
			double orderTotal = calOrderTotal(order);
			totalAmount += orderTotal;
		}
		return totalAmount;
	}
	
	/**
	 * 将订单分组，每十个订单一组，用于Paypal支付
	 * 
	 * @param orderList
	 * @return
	 * @throws Exception
	 */
	public static List<OrderGroup> groupOrder(List<OrderBean> orderList) throws Exception {
		List<OrderGroup> groups = new ArrayList<OrderGroup>();
		int size = orderList.size();
		// 订单小于等于10个，每组一个订单；订单大于10个，每组10个
		if (size <= 10) {
			for (OrderBean o : orderList) {
				OrderGroup group = new OrderGroup();
				group.addOrder(o);
				groups.add(group);
			}
		} else {
			for (int i=0; i<size; i+=10) {
				OrderGroup group = new OrderGroup();
				for(int j=0; i+j<size && j<10; j++) {
					OrderBean order = orderList.get(i+j);
					group.addOrder(order);
				}
				groups.add(group);
			}
		}
		return groups;
	}

	/**
	 * Paypal分组支付情况下，可能会需要Capture，这个是按分组来Capture订单
	 * 
	 * @param paypalApiUtil
	 * @param decoder
	 * @param capturedOrders
	 * @param i
	 * @param group
	 */
	public static List<OrderBean> captureGroup(PaypalApiUtil paypalApiUtil, String authId, OrderGroup group) {
		List<OrderBean> capturedOrders = new ArrayList<OrderBean>();
		for (OrderBean order : group.orders) {
			NVPEncoder ec = new NVPEncoder();
			ec.add("AUTHORIZATIONID", authId);
			NVPDecoder capteureDc = new NVPDecoder();
			double orderTotal = OrderUtil.calOrderTotal(order);
			String completeCodeType = "NotComplete";
			ec.add("COMPLETETYPE", completeCodeType);
			ec.add("INVNUM", order.getOrderId()+"");
			ec.add("AMT", StringUtil.formatCurrency(orderTotal));

			String captureAck = paypalApiUtil.doCapture(ec, capteureDc);
			// 成功
			boolean captured = captureAck.toLowerCase().startsWith("success");
			// 或已经Capture完成
			captured = captured
					|| (captureAck.toLowerCase().startsWith("failure") && capteureDc.get(
							"L_SHORTMESSAGE0").equalsIgnoreCase(
							"Authorization completed."));
			if (captured) {
				order.setTxnId(capteureDc.get("TRANSACTIONID"));
				capturedOrders.add(order);
			}
		}
		return capturedOrders;
	}
}
