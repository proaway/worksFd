package com.fd.fashion.modules.actions.order;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.fashion.modules.actions.BaseAction;
import com.fd.fashion.order.bean.OrderBean;
import com.fd.fashion.order.manager.IOrderManager;
import com.fd.fashion.order.manager.IOrderStatusManager;
import com.fd.payment.bean.AlipayNotifyBean;
import com.fd.payment.bean.AlipaySettingBean;
import com.fd.payment.manager.IPaymentManager;
import com.fd.payment.util.AlipayUtil;
import com.fd.util.StringUtil;

/**
 * @CreateDate: 2013-4-9 下午09:11:08
 * @author Longli
 * @Description: 接收Paypal IPN 的Action 方法
 * 
 */
public class AlipayNotifyCheck extends BaseAction {
	@Override
	public void doPerform(RunData data, Context context) throws Exception {
		super.doPerform(data, context);
		IOrderStatusManager orderStatusManager = (IOrderStatusManager) getBean("orderStatusManager");
		
		IPaymentManager paymentManager = (IPaymentManager) getBean("paymentManager");
		
		AlipayNotifyBean notify = new AlipayNotifyBean();
		
		notify.setNotifyTime(data.getParameters().getString("notify_time"));
		notify.setNotifyType(data.getParameters().getString("notify_type"));
		notify.setNotifyId(data.getParameters().getString("notify_id"));
		notify.setSignType(data.getParameters().getString("sign_type"));
		notify.setSign(data.getParameters().getString("sign"));
		notify.setOutTradeNo(data.getParameters().getString("out_trade_no"));
		notify.setSubject(data.getParameters().getString("subject"));
		notify.setPaymentType(data.getParameters().getString("payment_type"));
		notify.setTradeNo(data.getParameters().getString("trade_no"));
		notify.setTradeStatus(data.getParameters().getString("trade_status"));
		notify.setGmtCreate(data.getParameters().getString("gmt_create"));
		notify.setGmtPayment(data.getParameters().getString("gmt_payment"));
		notify.setGmtClose(data.getParameters().getString("gmt_close"));
		notify.setRefundStatus(data.getParameters().getString("refund_status"));
		notify.setGmtRefund(data.getParameters().getString("gmt_refund"));
		notify.setSellerEmail(data.getParameters().getString("seller_email"));
		notify.setBuyerEmail(data.getParameters().getString("buyer_email"));
		notify.setSellerId(data.getParameters().getString("seller_id"));
		notify.setBuyerId(data.getParameters().getString("buyer_id"));
		notify.setPrice(data.getParameters().getString("price"));
		notify.setTotalFee(data.getParameters().getString("total_fee"));
		notify.setQuantity(data.getParameters().getString("quantity"));
		notify.setBody(data.getParameters().getString("body"));
		notify.setDiscount(data.getParameters().getString("discount"));
		notify.setIsTotalFeeAdjust(data.getParameters().getString("is_total_fee_adjust"));
		notify.setUseCoupon(data.getParameters().getString("use_coupon"));
		notify.setErrorCode(data.getParameters().getString("error_code"));
		notify.setBankSeqNo(data.getParameters().getString("bank_seq_no"));
		notify.setExtraCommonParam(data.getParameters().getString("extra_common_param"));
		notify.setCurrency(data.getParameters().getString("currency"));
		notify.setForexTotalFee(data.getParameters().getString("forex_total_fee"));
		
		AlipaySettingBean setting = paymentManager.getAlipaySetting();
		
		AlipayUtil alipay = new AlipayUtil(setting.getPartner(), setting.getAliKey());

		// 验证Notify是不是Alipay发出
		String verify =  alipay.verifyResponse(notify.getNotifyId());
		
		notify.setVerified(verify);
		context.put("contentdata", "success");
		
		AlipayNotifyBean n = paymentManager.getAlipayNotify(notify.getNotifyId());
		if (n != null) { // 判断notifyID是否曾经收到过，防止重复处理数据
			return;
		}

		// 写入Notify数据
		paymentManager.addAlipayNotify(notify);
		
		boolean verified = "true".equalsIgnoreCase(verify);
		
		if (verified && !StringUtil.isEmpty(notify.getOutTradeNo())) {
			long orderId = Long.valueOf(notify.getOutTradeNo());
			OrderBean order = orderStatusManager.getOrder(orderId);
			try {
				String status = notify.getTradeStatus();
				if ("TRADE_SUCCESS".equals(status)) {	
					// 成功
					if ("OA001".equals(order.getOrderStatus())) {
						orderStatusManager.oa001ToOa002(order, notify, "customer");
						order.setOrderStatus("OA002");
					}
					if ("OA002".equals(order.getOrderStatus())) {
						orderStatusManager.oa002ToOs001(order, "customer", "");
					}
				} else if ("TRADE_PENDING".equals(status)) { 
					// 等待卖家收款
					if ("OA001".equals(order.getOrderStatus())) {
						orderStatusManager.oa001ToOa002(order, notify, "customer");
						order.setOrderStatus("OA002");
					}
				} else if ("WAIT_BUYER_PAY".equals(status)) { 
					// 交易创建，等待买家付款
					
				} else if ("TRADE_CLOSED".equals(status)) { 
					// 在指定时间段内未支付时关闭的交易、在交易完成全额退款成功时关闭的交易
					
				} else if ("TRADE_FINISHED".equals(status)) { 
					// 交易成功且不可退款
					if ("OA001".equals(order.getOrderStatus())) {
						orderStatusManager.oa001ToOa002(order, notify, "customer");
						order.setOrderStatus("OA002");
					}
					if ("OA002".equals(order.getOrderStatus())) {
						orderStatusManager.oa002ToOs001(order, "customer", "");
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
	}
}
