package com.fd.b2c.manager.modules.actions.product;

import java.util.HashMap;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.b2c.manager.modules.actions.SecureAction;
import com.fd.fashion.product.manager.IProductManager;
import com.fd.util.JSONUtil;

/**
 * @CreateDate: 2013-3-26 上午09:58:46
 * @author Longli
 * @Description: 获取赠品产品信息
 * 
 */
public class AjaxSaveProductName extends SecureAction {
	@Override
	public void doPerform(RunData data, Context context) throws Exception {
		super.doPerform(data, context);
		long productIds[] = data.getParameters().getLongs("productId");
		String nameBefore = data.getParameters().getString("nameBefore");
		String nameAfter = data.getParameters().getString("nameAfter");
		String replaceReg = data.getParameters().getString("replaceReg");
		String replaceStr = data.getParameters().getString("replaceStr");
		
		IProductManager productManager = (IProductManager) getBean("productManager");
		int i = productManager.updateProductsName(productIds, nameBefore, nameAfter, replaceReg, replaceStr);
		HashMap<String, Object> map = new HashMap<String, Object>();
		if (i>0) {
			map.put("status", 1);
		} else {
			map.put("status", 0);
		}
		context.put("contentdata", JSONUtil.obj2JSON(map));
	}
}
