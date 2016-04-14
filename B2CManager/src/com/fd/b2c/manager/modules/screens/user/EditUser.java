package com.fd.b2c.manager.modules.screens.user;

import java.util.List;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.b2c.manager.modules.screens.SecureScreen;
import com.fd.fashion.dict.bean.CategoryBean;
import com.fd.fashion.dict.manager.IDictManager;
import com.fd.fashion.seller.bean.DepartmentBean;
import com.fd.fashion.seller.bean.RolesBean;
import com.fd.fashion.seller.bean.UsersBean;
import com.fd.fashion.seller.manager.ISellerManager;

public class EditUser extends SecureScreen {
	@Override
	public void doBuildTemplate(RunData data, Context context) throws Exception {
		super.doBuildTemplate(data, context);
		data.setLayoutTemplate("/PopLayout.html");
		
		long userId = data.getParameters().getLong("userId", 0);
		
		ISellerManager sellerManager = (ISellerManager) getBean("sellerManager");
		if (userId > 0) {
			UsersBean user = sellerManager.getUser(userId);
			context.put("user", user);
		}
		
		List<DepartmentBean> departments = sellerManager.getDepartments();
		context.put("departments", departments);
		
		List<RolesBean> roles = sellerManager.getRoles(null);
		context.put("roles", roles);
		
		IDictManager dictManager = (IDictManager) getBean("dictManager");
		List<CategoryBean> industries = dictManager.getChildrenCategories(null);
		context.put("industries", industries);
	}
}
