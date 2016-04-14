package com.fd.fashion.modules.actions.order;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.fashion.modules.actions.BaseAction;
import com.fd.fashion.order.bean.OrderBean;
import com.fd.fashion.order.manager.IOrderManager;
import com.fd.fashion.order.manager.IOrderStatusManager;
import com.fd.payment.bean.PaypalipnBean;
import com.fd.payment.manager.IPaymentManager;
import com.fd.payment.util.PaypalApiUtil;
import com.fd.util.StringUtil;

/**
 * @CreateDate: 2013-4-9 下午09:11:08
 * @author Longli
 * @Description: 接收Paypal IPN 的Action 方法
 * 
 */
public class PaypalIPNCheck extends BaseAction {
	@Override
	public void doPerform(RunData data, Context context) throws Exception {
		super.doPerform(data, context);
		IOrderManager orderManager = (IOrderManager) getBean("orderManager");
		
		IOrderStatusManager orderStatusManager = (IOrderStatusManager) getBean("orderStatusManager");
		
		IPaymentManager paymentManager = (IPaymentManager) getBean("paymentManager");
		
		HttpServletRequest request = data.getRequest();
		
		Enumeration en = request.getParameterNames(); 
		//cmd参数：
		String str = "cmd=_notify-validate";
		while (en.hasMoreElements()) {
			String paramName = (String) en.nextElement();
			String paramValue = request.getParameter(paramName);
			str = str + "&" + paramName + "="
					+ URLEncoder.encode(paramValue, "iso-8859-1");
		}
		// System.out.println("对paypal确认请求paypalStr:" + str);
		// 回调paypal，进行ipn确认
		URL u = new URL(PaypalApiUtil.getProperty("paypalIPNUrl"));
		URLConnection uc = u.openConnection();
		uc.setDoOutput(true);
		uc.setRequestProperty("Content-Type",
				"application/x-www-form-urlencoded");
		PrintWriter pw = new PrintWriter(uc.getOutputStream());
		pw.println(str);
		pw.close();

		BufferedReader in = new BufferedReader(new InputStreamReader(uc
				.getInputStream()));
		String res = in.readLine();
		
		in.close();
		PaypalipnBean ipn = new PaypalipnBean();
		
		//订单号
		String item_name = request.getParameter("item_name");

		ipn.setCmd(request.getParameter("cmd"));
		String custom = request.getParameter("custom");
		ipn.setCustom(custom);
		ipn.setTxnId(request.getParameter("txn_id"));
		String caseType = request.getParameter("case_type");	//纠纷类型
		if(caseType!=null){
			caseType = caseType.trim();
		}
		ipn.setCaseType(caseType);	
		ipn.setPayerEmail(request.getParameter("payer_email"));
		ipn.setCharset(request.getParameter("charset"));
		ipn.setBusiness(request.getParameter("business"));
		ipn.setNotifyVersion(request.getParameter("notify_version"));
		ipn.setTxnType(request.getParameter("txn_type"));
		ipn.setVerifySign(request.getParameter("verify_sign"));
		ipn.setPaymentDate(request.getParameter("payment_date"));
		ipn.setReasonCode(request.getParameter("reason_code"));
		ipn.setPayerId(request.getParameter("payer_id"));
		ipn.setReceiverEmail(request.getParameter("receiver_email"));
		ipn.setReceiverId(request.getParameter("receiver_id"));
		ipn.setCaseId(request.getParameter("case_id"));
		ipn.setParentTxnId(request.getParameter("parent_txn_id"));
		
		ipn.setAddressCity(request.getParameter("address_city"));
		ipn.setAddressCountry(request.getParameter("address_country"));
		ipn.setAddressCountryCode(request.getParameter("address_country_code"));
		ipn.setAddressName(request.getParameter("address_name"));
		ipn.setAddressState(request.getParameter("address_state"));
		ipn.setAddressStatus(request.getParameter("address_status"));
		ipn.setAddressStreet(request.getParameter("address_street"));
		ipn.setAddressZip(request.getParameter("address_zip"));
		ipn.setFirstName(request.getParameter("first_name"));
		ipn.setHandlingAmount(request.getParameter("handling_amount"));
		ipn.setItemName(request.getParameter("item_name"));
		ipn.setItemNumber(request.getParameter("item_number"));
		ipn.setLastName(request.getParameter("last_name"));
		ipn.setMcCurrency(request.getParameter("mc_currency"));
		ipn.setMcFee(request.getParameter("mc_fee"));
		
		//IPN传回的总金额
		ipn.setMcGross(request.getParameter("mc_gross"));
		ipn.setPayerStatus(request.getParameter("payer_status"));
		ipn.setPaymentDate(request.getParameter("payment_date"));
		ipn.setPaymentFee(request.getParameter("payment_fee"));
		ipn.setPaymentGross(request.getParameter("payment_gross"));
		
		//支付状态
		ipn.setPaymentStatus(request.getParameter("payment_status"));
		ipn.setPaymentType(request.getParameter("payment_type"));
		ipn.setPendingReason(request.getParameter("pending_reason"));
		ipn.setProtectionEligibility(request
				.getParameter("protection_eligibility"));
		ipn.setQuantity(request.getParameter("quantity"));
		ipn.setResidenceCountry(request
				.getParameter("residence_country"));
		ipn.setShipping(request.getParameter("shipping"));
		ipn.setTax(request.getParameter("tax"));
		ipn.setTestIpn(request.getParameter("test_ipn"));
		ipn.setTransactionSubject(request
				.getParameter("transaction_subject"));
		ipn.setInvoice(request.getParameter("invoice"));
		ipn.setIpnDate(new Date());
		if ("VERIFIED".equals(res) || "INVALID".equals(res)) {
			ipn.setVerifyIpn(res);
		} else {
			ipn.setVerifyIpn("VerifyFailed");
			System.out.println("******VerifyIPN Failed******" + res);
		}
		
		// 写入IPN数据
		paymentManager.addPaypalIPN(ipn);
		
		// 判断是否需要验证IPN是不是Paypal发出，主要是为了方便测试
		String strVerifyIPN = PaypalApiUtil.getProperty("verifyIPN");
		boolean verifyIPN = "VERIFIED".equals(res);
		if ("false".equals(strVerifyIPN)) {
			verifyIPN = true;
		}
		
		//res = "VERIFIED"; 说明是Paypal发出的IPN 
		//res = "INVALID"; 说明不是Paypal发出的IPN 
		if (verifyIPN && !StringUtil.isEmpty(custom)) {
			String orderNos = ipn.getInvoice();
			if (StringUtil.isEmpty(orderNos)) {
				orderNos = custom;
			}
			Long orderId = Long.valueOf(orderNos);
			OrderBean order = orderManager.getOrder(orderId);
			try {
				if (("complaint".equals(caseType))
						|| ("Reversed".equals(ipn.getPaymentStatus()) 
							&& "buyer_complaint".equals(ipn.getReasonCode()))) {	
					//事件类型为补偿申请
					// Reversed --> buyer_complaint 未经授权的补偿申请
					String txnId = request.getParameter("txn_id");
				} else if("chargeback".equals(caseType)
						|| ("Reversed".equals(ipn.getPaymentStatus()) 
								&& "chargeback".equals(ipn.getReasonCode()))){	
					//事件类型为退单
				} else if("dispute".equals(caseType)){	
					//争议，将关联的订单自动改为“Paypal纠纷”状态
				} else if ("Completed".equals(ipn.getPaymentStatus())) { 
					// 成功
					if ("OA001".equals(order.getOrderStatus())) {
						orderStatusManager.oa001ToOa002(order, ipn, "");
					}
					orderStatusManager.oa002ToOs001(order, ipn);
				} else if ("Reversed".equals(ipn.getPaymentStatus())
						&& ("other".equals(ipn.getReasonCode()) 
							|| "unauthorized_claim".equals(ipn.getReasonCode()))) { 
					// Reversed --> Paypal调查
				} else if ("Refunded".equals(ipn.getPaymentStatus())) { 
					// Refunded --> paypal 退款
				} else if ("Pending".equals(ipn.getPaymentStatus())) { 
					// Pending
					if ("OA001".equals(order.getOrderStatus())) {
						orderStatusManager.oa001ToOa002(order, ipn, "");
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
	}
}
