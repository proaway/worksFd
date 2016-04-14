package com.fd.b2c.manager.modules.actions.shipping;

import java.util.HashMap;
import java.util.Map;

import org.apache.ecs.xhtml.map;
import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.b2c.manager.modules.actions.SecureAction;
import com.fd.fashion.seller.manager.IShippingManager;
import com.fd.util.JSONUtil;

public class AjaxDelShipping extends SecureAction {
	@Override
	public void doPerform(RunData data, Context context) throws Exception {
		super.doPerform(data, context);
		long [] shippingInfoIds = data.getParameters().getLongs("shippingId");
		Map<String, Object> map = new HashMap<String, Object>();
		if (shippingInfoIds == null) {
			map.put("status", 0);
		} else {
			IShippingManager shippingManager = (IShippingManager) getBean("shippingManager");
			int i = shippingManager.deleteShippingInfos(shippingInfoIds);
			if (i>0) {
				map.put("status", 1);
			} else {
				map.put("status", 0);
			}
		}
		context.put("contentdata", JSONUtil.obj2JSON(map));
	}
}
