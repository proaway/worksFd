package com.fd.b2c.manager.modules.screens.stock;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;
import com.fd.b2c.manager.modules.screens.SecureScreen;

/**
 * @createTime 2013-6-18 上午10:39:54
 * @author ErWei
 * @description 采购历史
 * @version V1.0
 */
public class PurchaseHistory extends SecureScreen {
	public void doBuildTemplate(RunData data, Context context) throws Exception {
		super.doBuildTemplate(data, context);
		data.setLayoutTemplate("/Layout.html");

	}
}
