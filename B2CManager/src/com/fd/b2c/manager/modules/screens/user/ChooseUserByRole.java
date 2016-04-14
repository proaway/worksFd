package com.fd.b2c.manager.modules.screens.user;

import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.b2c.manager.modules.screens.SecureScreen;
import com.fd.fashion.dict.bean.CategoryBean;
import com.fd.fashion.dict.manager.IDictManager;
import com.fd.fashion.seller.bean.DepartmentBean;
import com.fd.fashion.seller.bean.RolesBean;
import com.fd.fashion.seller.bean.UsersBean;
import com.fd.fashion.seller.manager.ISellerManager;

/**
 * @author zhangling
 *
 */
public class ChooseUserByRole extends SecureScreen {
	@Override
	public void doBuildTemplate(RunData data, Context context) throws Exception {
		super.doBuildTemplate(data, context);
		
		UsersBean cond = new UsersBean();
		Long roleId = data.getParameters().getLongObject("roleId");
		context.put("roleId", roleId);
		/**注入查询条件*/
		cond.setRoleId(roleId);
		
		ISellerManager sellerManager = (ISellerManager) getBean("sellerManager");
		List<UsersBean> users  =sellerManager.getUsers(cond);
		Map<Long, DepartmentBean> departments = sellerManager.getDepartmentsMap();
		Map<Long, RolesBean> roles = sellerManager.getRolesMap();
		context.put("departments", departments);
		context.put("roles", roles);
		context.put("users", users);

	}


}
