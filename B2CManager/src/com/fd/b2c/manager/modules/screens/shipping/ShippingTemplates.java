package com.fd.b2c.manager.modules.screens.shipping;

import java.util.List;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.b2c.manager.modules.screens.SecureScreen;
import com.fd.fashion.seller.manager.ISellerManager;
import com.fd.fashion.seller.manager.IShippingManager;
import com.fd.fashion.seller.vo.ShippingTemplateVo;

/**
 * @CreateDate: 2013-3-28 下午12:12:51
 * @author Longli
 * @Description: 物流模版管理页面
 * 
 */
public class ShippingTemplates extends SecureScreen {
	@Override
	public void doBuildTemplate(RunData data, Context context) throws Exception {
		super.doBuildTemplate(data, context);
		data.setLayoutTemplate("/Layout.html");
		IShippingManager shippingManager = (IShippingManager) getBean("shippingManager");
		List<ShippingTemplateVo> shippings = shippingManager.getShippingInfos();
		context.put("shippings", shippings);
		
	}
}
