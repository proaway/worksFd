package com.fd.b2c.manager.modules.actions.category;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.b2c.manager.modules.actions.SecureAction;
import com.fd.fashion.dict.manager.IDictManager;
import com.fd.fashion.dict.vo.CatAttrNode;
import com.fd.fashion.dict.vo.CatConfNode;
import com.fd.fashion.seller.manager.ISellerManager;
import com.fd.util.JSONUtil;

public class AjaxImportSysCategories extends SecureAction {
	@Override
	public void doPerform(RunData data, Context context) throws Exception {
		super.doPerform(data, context);
		
		Map<String, Object> map = new HashMap<String, Object>();
		ISellerManager sellerManager = (ISellerManager) getBean("sellerManager");
		try {
			sellerManager.importSysCategories("longli");
			map.put("status", 1);
		} catch (Exception e) {
			map.put("status", 0);
		}
		context.put("contentdata", JSONUtil.obj2JSON(map));
	}
}
