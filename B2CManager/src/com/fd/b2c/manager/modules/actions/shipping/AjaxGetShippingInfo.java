package com.fd.b2c.manager.modules.actions.shipping;

import java.util.List;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.b2c.manager.modules.actions.SecureAction;
import com.fd.fashion.seller.bean.ShippingInfoBean;
import com.fd.fashion.seller.manager.IShippingManager;
import com.fd.util.JSONUtil;

public class AjaxGetShippingInfo extends SecureAction {
	@Override
	public void doPerform(RunData data, Context context) throws Exception {
		super.doPerform(data, context);
		IShippingManager shippingManager = (IShippingManager) getBean("shippingManager");
		List<ShippingInfoBean> shippingInfos = shippingManager.getShippingInfoBeans();
		
		context.put("contentdata", JSONUtil.list2JSON(shippingInfos));
	}
}
