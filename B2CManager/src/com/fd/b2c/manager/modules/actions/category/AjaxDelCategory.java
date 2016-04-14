package com.fd.b2c.manager.modules.actions.category;

import java.util.HashMap;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.b2c.manager.modules.actions.SecureAction;
import com.fd.fashion.seller.manager.ISellerManager;
import com.fd.util.JSONUtil;

public class AjaxDelCategory extends SecureAction {
	@Override
	public void doPerform(RunData data, Context context) throws Exception {
		super.doPerform(data, context);
		String catId = data.getParameters().getString("catId");

		ISellerManager sellerManager = (ISellerManager) getBean("sellerManager");
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			sellerManager.deleteCustomCategory(catId);
			map.put("status", 1);
		} catch (Exception e) {
			map.put("status", 0);
		}
		context.put("contentdata", JSONUtil.obj2JSON(map));
	}
}
