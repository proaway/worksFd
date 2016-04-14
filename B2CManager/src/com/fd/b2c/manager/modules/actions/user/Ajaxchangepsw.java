package com.fd.b2c.manager.modules.actions.user;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.b2c.manager.modules.actions.SecureAction;
import com.fd.fashion.seller.bean.UsersBean;
import com.fd.fashion.seller.manager.ISellerManager;
import com.fd.util.ParametersUtil;

public class Ajaxchangepsw extends SecureAction {
	
	@Override
	public void doPerform(RunData data, Context context) throws Exception {
		super.doPerform(data, context);

		UsersBean user = new UsersBean();
		ParametersUtil.initParameters(data, user);

		ISellerManager sellerManager = (ISellerManager) getBean("sellerManager");
		int i = sellerManager.updateUser(user);
		context.put("contentdata", i);
	}
}
