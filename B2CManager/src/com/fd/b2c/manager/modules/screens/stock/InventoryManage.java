package com.fd.b2c.manager.modules.screens.stock;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;
import com.fd.b2c.manager.modules.screens.SecureScreen;

/**
 * @createTime 2013-6-18 下午6:39:54
 * @author ErWei
 * @description 库存管理
 * @version V1.0
 */
public class InventoryManage extends SecureScreen {
	public void doBuildTemplate(RunData data, Context context) throws Exception {
		super.doBuildTemplate(data, context);
		data.setLayoutTemplate("/Layout.html");

	}
}
