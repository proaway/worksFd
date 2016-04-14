package com.fd.fashion.modules.actions.order;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.constants.SessionConstants;
import com.fd.fashion.buyer.bean.BuyerAddressBookBean;
import com.fd.fashion.buyer.bean.BuyerBean;
import com.fd.fashion.buyer.manager.IBuyerManager;
import com.fd.fashion.dict.bean.RegionBean;
import com.fd.fashion.dict.manager.IDictManager;
import com.fd.fashion.modules.actions.BaseAction;
import com.fd.fashion.order.bean.OrderBean;
import com.fd.fashion.order.manager.IOrderStatusManager;
import com.fd.payment.bean.AlipayNotifyBean;
import com.fd.payment.bean.PaypalSettingBean;
import com.fd.payment.manager.IPaymentManager;
import com.fd.payment.util.PaypalApiUtil;
import com.fd.payment.vo.PaypalInfoVo;
import com.fd.session.FdSession;
import com.fd.session.FdSessionFactory;
import com.fd.util.IPAddrUtil;
import com.fd.util.StringUtil;
import com.paypal.sdk.core.nvp.NVPDecoder;

public class AlipayReturn extends BaseAction {
	
	public void doPerform(RunData data, Context context) throws Exception {
		data.setCharSet("UTF-8");
		IOrderStatusManager orderStatusManager = (IOrderStatusManager) getBean("orderStatusManager");
		
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
		
		String tradeStatus = notify.getTradeStatus();
		
		long orderId = Long.valueOf(notify.getOutTradeNo());
		OrderBean order = orderStatusManager.getOrder(orderId);
		
		if ("TRADE_FINISHED".equals(tradeStatus) || "TRADE_SUCCESS".equals(tradeStatus)) {
			if ("OA001".equals(order.getOrderStatus())) {
				orderStatusManager.oa001ToOa002(order, notify, "customer");
				order.setOrderStatus("OA002");
			}
			if ("OA002".equals(order.getOrderStatus())) {
				orderStatusManager.oa002ToOs001(order, "customer", "");
			}
		}
		
		setTemplate(data, "order,PaymentSuccessful.html");
	}
	

}
