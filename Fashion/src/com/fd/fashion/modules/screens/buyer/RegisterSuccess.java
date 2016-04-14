package com.fd.fashion.modules.screens.buyer;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.fashion.modules.screens.BaseScreen;

/**
 * @CreateDate: 2013-3-14 下午12:13:57
 * @author Longli
 * @Description: 注册成功页面 buyer,RegisterSuccess.html
 * 
 */
public class RegisterSuccess extends BaseScreen {
	public void doBuildTemplate(RunData data, Context context) throws Exception {
		super.doBuildTemplate(data, context);
		data.setLayoutTemplate("/Layout.html");
		String buyer = data.getParameters().getString("regUser");
		context.put("buyer", buyer);
	}
}
