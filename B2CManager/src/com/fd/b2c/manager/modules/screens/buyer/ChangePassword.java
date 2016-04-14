package com.fd.b2c.manager.modules.screens.buyer;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.b2c.manager.modules.screens.SecureScreen;

/**
 * @author zhangling
 *修改密码
 *
 */
public class ChangePassword  extends SecureScreen {
	@Override
	public void doBuildTemplate(RunData data, Context context) throws Exception {
		super.doBuildTemplate(data, context);
		
		long buyerId = data.getParameters().getLongObject("buyerId");
		String email = data.getParameters().getString("email");
		String userId = data.getParameters().getString("userId");
		context.put("buyerId", buyerId);
		context.put("email", email);
		context.put("userId", userId);
		
	}
}
