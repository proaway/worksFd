package com.fd.fashion.modules.actions.order;

import org.apache.commons.lang.StringUtils;
import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.fashion.modules.actions.SecureAction;
import com.fd.fashion.order.bean.OrderBean;
import com.fd.fashion.order.manager.IOrderManager;
import com.fd.fashion.order.manager.IOrderStatusManager;
import com.fd.fashion.order.util.OrderUtil;
import com.fd.payment.bean.PaypalSettingBean;
import com.fd.payment.bean.PaypalipnBean;
import com.fd.payment.manager.IPaymentManager;
import com.fd.payment.util.PaypalApiUtil;
import com.fd.session.FdSession;
import com.fd.session.FdSessionFactory;
import com.fd.util.StringUtil;
import com.paypal.sdk.core.nvp.NVPDecoder;
import com.paypal.sdk.core.nvp.NVPEncoder;

public class PayByPaypal extends SecureAction {
	@Override
	public void doPerform(RunData data, Context context) throws Exception {
		super.doPerform(data, context);
		FdSession session = FdSessionFactory.getFdSession(data);
		
		int paymentType = 1;
		long orderId = data.getParameters().getLong("orderId");
		IOrderManager orderManager = (IOrderManager) getBean("orderManager");
		OrderBean order = orderManager.getOrder(orderId);
		IOrderStatusManager orderStatusManager = (IOrderStatusManager) getBean("orderStatusManager");
		
		IPaymentManager paymentManager = (IPaymentManager) getBean("paymentManager");
		PaypalSettingBean setting = paymentManager.getPaypalSetting();

		String token = (String) session.getAttribute("TOKEN");
		String payerId = (String)session.getAttribute("PAYERID");
		if (StringUtils.isEmpty(payerId)) {
			payerId = data.getParameters().getString("PayerID");
			session.setAttribute("PAYERID", payerId);
		}
		String notifyUrl = PaypalApiUtil.getProperty("paypalBackUrl");
		StringBuffer redirectUrl = new StringBuffer(PaypalApiUtil.getProperty("postUrl"));
		
		PaypalApiUtil paypalApiUtil = new PaypalApiUtil(setting.getApiUserName(), 
				setting.getApiPassword(), setting.getApiSignature(), 
				setting.getApiEnvironment(), setting.getAccount());
		
		if (StringUtils.isNotEmpty(token) && StringUtils.isNotEmpty(payerId)) {
			// get expresscheckout detail
			NVPDecoder dc = new NVPDecoder();
			paypalApiUtil.getExpressCheckoutDetails(token, dc);
			String accountMail = dc.get("EMAIL");
			String accountContactName = dc.get("PAYMENTREQUEST_0_SHIPTONAME");
			if (StringUtils.isEmpty(accountContactName)) {
				accountContactName = dc.get("SHIPTONAME");
			}
			String accountCountry = dc.get("PAYMENTREQUEST_0_SHIPTOCOUNTRYCODE");
			if (StringUtils.isEmpty(accountCountry)) {
				accountCountry = dc.get("COUNTRYCODE");
			}
			// ExpressCheckout
			NVPEncoder encoder = new NVPEncoder();
			encoder.add("TOKEN", token);
			encoder.add("PAYERID", payerId);
			
			// Sale方式：不超过10个订单可以用Sale方式
			double total = OrderUtil.calOrderTotal(order);
			encoder.add("PAYMENTREQUEST_0_PAYMENTACTION", "Sale");
			encoder.add("PAYMENTREQUEST_0_AMT", StringUtil.formatCurrency(total));
			encoder.add("PAYMENTREQUEST_0_SELLERPAYPALACCOUNTID", setting.getAccount());
			
			encoder.add("PAYMENTREQUEST_0_PAYMENTREQUESTID", order.getOrderId()+"");
			encoder.add("PAYMENTREQUEST_0_CUSTOM", order.getOrderId()+"");
			encoder.add("PAYMENTREQUEST_0_INVNUM", order.getOrderId()+"");
			encoder.add("PAYMENTREQUEST_0_CURRENCYCODE", "USD");
			encoder.add("PAYMENTREQUEST_0_NOTIFYURL", notifyUrl);
			
			NVPDecoder decoder = new NVPDecoder();
			// do express checkout
			String ack = paypalApiUtil.doExpressCheckout(encoder, decoder);
			session.removeAttribute("TOKEN");
			session.removeAttribute("PAYERID");
			if(ack.toLowerCase().startsWith("success")){
				String txnId = decoder.get("PAYMENTINFO_0_TRANSACTIONID");
				order.setTxnId(txnId); // 交易号
				order.setPaymentType(paymentType); // 付款方式
				PaypalipnBean ipn = new PaypalipnBean();
				ipn.setPayerEmail(accountMail);
				ipn.setAddressName(accountContactName);
				ipn.setAddressCountryCode(accountCountry);
				
				// 判断风控
				String riskInfo = OrderUtil.isRisk(order, ipn);
				boolean isRisk = StringUtils.isNotEmpty(riskInfo);
				if (isRisk) {
					orderStatusManager.setOr101(order, "", riskInfo);
				}
				
				try {
					// 修改订单支付状态为待确认
					orderStatusManager.oa001ToOa002(order, "");
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				try {
					// 修改订单支付状态为付款成功
					orderStatusManager.oa002ToOs001(order, "", "");
				} catch (Exception e) {
					e.printStackTrace();
				}
				setTemplate(data, "order,PaymentSuccessful.html");
				return;
			} else {
				data.getResponse().sendRedirect(PaypalApiUtil.getProperty("payECCancelUrl") + "?orderId=" + orderId);
				return;
			}
		} else {
			String handle = "US";
			// set express checkout
			//设置快速支付提交参数
			NVPEncoder encoder = new NVPEncoder();
			encoder.add("LOCALECODE", handle);
			encoder.add("PAYMENTACTION", "Authorization");
			encoder.add("HDRIMG", PaypalApiUtil.getProperty("logoUrl"));
			encoder.add("RETURNURL", PaypalApiUtil.getProperty("payECReturnUrl") + "?orderId=" + orderId);
			encoder.add("CANCELURL", PaypalApiUtil.getProperty("payECCancelUrl") + "?orderId=" + orderId);
			double total = OrderUtil.calOrderTotal(order);
			encoder.add("PAYMENTREQUEST_0_AMT", StringUtil.formatCurrency(total));
			
			encoder.add("PAYMENTREQUEST_0_PAYMENTREQUESTID", order.getOrderId()+"");
			encoder.add("PAYMENTREQUEST_0_CUSTOM", order.getOrderId()+"");
			encoder.add("PAYMENTREQUEST_0_INVNUM", order.getOrderId()+"");
			encoder.add("PAYMENTREQUEST_0_CURRENCYCODE", "USD");
			
			encoder.add("PAYMENTREQUEST_0_ITEMAMT",StringUtil.formatCurrency(total));
			encoder.add("L_PAYMENTREQUEST_0_NAME0", order.getOrderId()+"");
			encoder.add("L_PAYMENTREQUEST_0_AMT0",StringUtil.formatCurrency(total));
			
			//返回参数
			NVPDecoder decoder = new NVPDecoder();
			String ack = paypalApiUtil.setExpressCheckout(encoder, decoder);
			if (StringUtil.isEmpty(ack)) {
				data.getResponse().sendRedirect(data.getRequest().getContextPath()+"/template/order,PayOrder.vm?orderId=" + orderId);
				return;
			}
			if (ack.toLowerCase().startsWith("success")) {
				//根据返回参数跳转页面
				token = decoder.get("TOKEN");
				//把token值写到session中
				session.setAttribute("TOKEN", token);
				redirectUrl.append("?cmd=_express-checkout&token=");
				redirectUrl.append(token);
				data.getResponse().sendRedirect(redirectUrl.toString());
				return;
			} else {
				data.getResponse().sendRedirect(data.getRequest().getContextPath()+"/template/order,PayOrder.vm?orderId=" + orderId);
				return;
			}
		}
	}
}
