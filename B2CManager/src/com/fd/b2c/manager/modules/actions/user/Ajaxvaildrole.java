package com.fd.b2c.manager.modules.actions.user;

import java.util.HashMap;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.b2c.manager.modules.actions.SecureAction;
import com.fd.fashion.seller.manager.ISellerManager;
import com.fd.util.JSONUtil;

public class Ajaxvaildrole extends SecureAction {
	
	@Override
	public void doPerform(RunData data, Context context) throws Exception {
		super.doPerform(data, context);

		long[] roleIds = data.getParameters().getLongs("roleIds");
		int vaild = data.getParameters().getInt("vaild", -1);

		boolean success = false;
		if (vaild == 0 || vaild == 1) {
			ISellerManager sellerManager = (ISellerManager) getBean("sellerManager");
			int i = sellerManager.vaildRoles(roleIds, vaild);
			success = i>0;
		}
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("status", success);
		context.put("contentdata", JSONUtil.obj2JSON(map));
	}
}
