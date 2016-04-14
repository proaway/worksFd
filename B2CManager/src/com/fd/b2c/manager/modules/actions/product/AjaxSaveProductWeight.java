package com.fd.b2c.manager.modules.actions.product;

import java.util.HashMap;
import java.util.List;

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
public class AjaxSaveProductWeight extends SecureAction {
	@Override
	public void doPerform(RunData data, Context context) throws Exception {
		super.doPerform(data, context);
		long productIds[] = data.getParameters().getLongs("productId");
		double addWeight = data.getParameters().getDouble("addWeight");
		int editFlag = data.getParameters().getInt("editFlag");
		
		IProductManager productManager = (IProductManager) getBean("productManager");
		List<HashMap<String, Object>> weightMaps = productManager.updateProductWeight(productIds, addWeight, editFlag);
		HashMap<String, Object> map = new HashMap<String, Object>();
		if (weightMaps != null && weightMaps.size()>0) {
			map.put("status", 1);
			map.put("weightMaps", weightMaps);
		} else {
			map.put("status", 0);
		}
		context.put("contentdata", JSONUtil.obj2JSON(map));
	}
}
