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
public class AjaxSavePackingUnit extends SecureAction {
	@Override
	public void doPerform(RunData data, Context context) throws Exception {
		super.doPerform(data, context);
		long productIds[] = data.getParameters().getLongs("productId");
		int ispackage = data.getParameters().getInt("ispackage");
		int quantity = data.getParameters().getInt("quantity");
		String packing = data.getParameters().getString("ispackage");
		
		IProductManager productManager = (IProductManager) getBean("productManager");
		int i = productManager.updateProductPacking(productIds, ispackage, packing, quantity);
		HashMap<String, Object> map = new HashMap<String, Object>();
		if (i>0) {
			map.put("status", 1);
		} else {
			map.put("status", 0);
		}
		context.put("contentdata", JSONUtil.obj2JSON(map));
	}
}
