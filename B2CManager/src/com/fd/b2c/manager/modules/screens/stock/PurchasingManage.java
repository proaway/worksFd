package com.fd.b2c.manager.modules.screens.stock;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;
import com.fd.b2c.manager.modules.screens.SecureScreen;

/**
 * @createTime 2013-6-18 下午3:13:47
 * @author ErWei
 * @description 采购管理
 * @version V1.0
 */
public class PurchasingManage extends SecureScreen {
	public void doBuildTemplate(RunData data, Context context) throws Exception {
		super.doBuildTemplate(data, context);
		data.setLayoutTemplate("/Layout.html");

	}
}
