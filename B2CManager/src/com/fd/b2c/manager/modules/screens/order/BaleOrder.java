package com.fd.b2c.manager.modules.screens.order;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.b2c.manager.modules.screens.SecureScreen;

/**
 * @author zhangling
 * 
 * 打包
 *
 */
public class BaleOrder extends SecureScreen{
	public void doBuildTemplate(RunData data, Context context) throws Exception {
		super.doBuildTemplate(data, context);
		data.setLayoutTemplate("/Layout.html");
		
	}

}
