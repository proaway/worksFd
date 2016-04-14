package com.fd.fashion.modules.actions.order;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.fashion.modules.actions.BaseAction;
import com.fd.payment.bean.PaypalSettingBean;
import com.fd.payment.manager.IPaymentManager;
import com.fd.payment.util.PaypalApiUtil;
import com.fd.session.FdSession;
import com.fd.session.FdSessionFactory;
import com.fd.util.StringUtil;
import com.paypal.sdk.core.nvp.NVPDecoder;
import com.paypal.sdk.core.nvp.NVPEncoder;

/**
 * @CreateDate: 2013-4-10 上午11:01:02
 * @author Longli
 * @Description: Paypal快速支付
 * 
 */
public class PaypalExpressCheckout extends BaseAction {
	@Override
	public void doPerform(RunData data, Context context) throws Exception {
		super.doPerform(data, context);
		FdSession session = FdSessionFactory.getFdSession(data);
		
		IPaymentManager paymentManager = (IPaymentManager) getBean("paymentManager");
		PaypalSettingBean setting = paymentManager.getPaypalSetting();

		PaypalApiUtil paypalApiUtil = new PaypalApiUtil(setting.getApiUserName(), 
				setting.getApiPassword(), setting.getApiSignature(), 
				setting.getApiEnvironment(), setting.getAccount());
		
		String amount = data.getParameters().getString("amount");
		
		String handle = "US";
		//设置快速支付提交参数
		NVPEncoder encoder = new NVPEncoder();
		encoder.add("RETURNURL", PaypalApiUtil.getProperty("ECReturnUrl"));
		encoder.add("CANCELURL", PaypalApiUtil.getProperty("ECCancelUrl"));
		encoder.add("PAYMENTACTION", "Authorization");
		encoder.add("LOCALECODE", handle);
		encoder.add("HDRIMG", PaypalApiUtil.getProperty("logoUrl"));
		encoder.add("PAYMENTREQUEST_0_AMT", amount);
		//返回参数
		NVPDecoder decoder = new NVPDecoder();
		String ack = paypalApiUtil.setExpressCheckout(encoder, decoder);
		if (StringUtil.isEmpty(ack)) {
			data.getResponse().sendRedirect(PaypalApiUtil.getProperty("ECCancelUrl"));
			return;
		}
		if (ack.toLowerCase().startsWith("success")) {
			//根据返回参数跳转页面
			String token = decoder.get("TOKEN");
			//把token值写到session中
			session.setAttribute("TOKEN", token);
			StringBuffer redirectUrl = new StringBuffer(PaypalApiUtil.getProperty("postUrl"));
			redirectUrl.append("?cmd=_express-checkout&token=");
			redirectUrl.append(token);
			data.getResponse().sendRedirect(redirectUrl.toString());
			return;
		} else {
			data.getResponse().sendRedirect(PaypalApiUtil.getProperty("ECCancelUrl"));
			return;
		}
	}
}
