package com.fd.b2c.manager.modules.actions.category;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.b2c.manager.modules.actions.SecureAction;
import com.fd.fashion.dict.manager.IDictManager;
import com.fd.fashion.dict.vo.CatAttrNode;
import com.fd.fashion.dict.vo.CatConfNode;
import com.fd.fashion.seller.bean.CustomCategoryBean;
import com.fd.fashion.seller.manager.ISellerManager;
import com.fd.util.JSONUtil;
import com.fd.util.ParametersUtil;

public class AjaxAddCategory extends SecureAction {
	@Override
	public void doPerform(RunData data, Context context) throws Exception {
		super.doPerform(data, context);
		int act = data.getParameters().getInt("act");
		
		CustomCategoryBean cat = new CustomCategoryBean();
		ParametersUtil.initParameters(data, cat);
		if (act == 0) {
			cat.setCreator("longli");
			cat.setCreateTime(new Date());
		}
		cat.setOperator("longli");
		cat.setUpdateTime(new Date());

		ISellerManager sellerManager = (ISellerManager) getBean("sellerManager");
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			if (act == 0) {
				sellerManager.addCustomCategory(cat);
			} else {
				sellerManager.updateCustomCategory(cat);
			}
			map.put("status", 1);
			map.put("cat", cat);
		} catch (Exception e) {
			map.put("status", 0);
		}
		context.put("contentdata", JSONUtil.obj2JSON(map));
	}
}
