package com.fd.fashion.modules.actions.order;

import java.util.HashMap;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.fashion.modules.actions.SecureAction;
import com.fd.fashion.order.bean.OrderAddressBean;
import com.fd.fashion.order.bean.OrderBean;
import com.fd.fashion.order.manager.IOrderManager;
import com.fd.fashion.order.manager.IOrderStatusManager;
import com.fd.fashion.order.util.OrderUtil;
import com.fd.payment.bean.AlipaySettingBean;
import com.fd.payment.bean.WesternUnionBean;
import com.fd.payment.manager.IPaymentManager;
import com.fd.payment.util.AlipayUtil;
import com.fd.util.CullNumUtil;
import com.fd.util.ParametersUtil;

/**
 * @CreateDate: 2013-4-25 下午08:46:47
 * @author Longli
 * @Description: Alipay提交Action
 * 
 */
public class PayByAlipay extends SecureAction {
	@Override
	public void doPerform(RunData data, Context context) throws Exception {
		super.doPerform(data, context);
		
		long orderId = data.getParameters().getLong("orderId");
		IOrderManager orderManager = (IOrderManager) getBean("orderManager");
		OrderBean order = orderManager.getOrder(orderId);

		int paymentType = data.getParameters().getInt("paymentType", 4);
		order.setPaymentType(paymentType);
		String default_bank = "boc-visa";
		if (paymentType == 5) {
			default_bank = "boc-master";
		}
		OrderBean o = new OrderBean();
		o.setOrderId(orderId);
		o.setPaymentType(paymentType);
		orderManager.updateOrder(o);
		
		double total = OrderUtil.calOrderTotal(order);
		
		IPaymentManager paymentManager = (IPaymentManager) getBean("paymentManager");
		AlipaySettingBean setting = paymentManager.getAlipaySetting();
		
		AlipayUtil alipay = new AlipayUtil(setting.getPartner(), setting.getAliKey());
		
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("service", "alipay.trade.direct.forcard.pay");
		params.put("partner", setting.getPartner());
		params.put("_input_charset", AlipayUtil.input_charset);
		params.put("notify_url", AlipayUtil.notify_url);
		params.put("return_url", AlipayUtil.return_url);
		params.put("out_trade_no", String.valueOf(orderId));
		params.put("subject", String.valueOf(orderId));
		params.put("default_bank", default_bank);
		//params.put("extend_param", extend_param.toString());
		params.put("seller_logon_id", setting.getSellerLogonId());
		params.put("total_fee", CullNumUtil.cullNumTwo(total));
		params.put("currency", AlipayUtil.currency);
		
		String contentdata = alipay.buildRequest(params, "post");
		context.put("contentdata", contentdata);
	}
}
