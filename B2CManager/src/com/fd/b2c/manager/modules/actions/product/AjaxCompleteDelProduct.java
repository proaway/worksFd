package com.fd.b2c.manager.modules.actions.product;

import java.util.HashMap;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.b2c.manager.modules.actions.SecureAction;
import com.fd.fashion.product.manager.IProductManager;
import com.fd.fashion.product.manager.IProductUploadManager;
import com.fd.util.JSONUtil;

/**
 * @CreateDate: 2013-3-26 上午09:58:46
 * @author Longli
 * @Description: 彻底删除产品
 * 
 */
public class AjaxCompleteDelProduct extends SecureAction {
	@Override
	public void doPerform(RunData data, Context context) throws Exception {
		super.doPerform(data, context);
		long productIds[] = data.getParameters().getLongs("productId");
		
		IProductUploadManager productUploadManager = (IProductUploadManager) getBean("productUploadManager");
		int i = 0;
		if (productIds != null) {
			for (long productId : productIds) {
				i += productUploadManager.deleteProductInfo(productId);
			}
		}
		HashMap<String, Object> map = new HashMap<String, Object>();
		if (i>0) {
			map.put("status", i);
		} else {
			map.put("status", 0);
		}
		context.put("contentdata", JSONUtil.obj2JSON(map));
	}
}
