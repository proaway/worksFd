package com.fd.b2c.manager.modules.screens.user;

import java.util.List;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.b2c.manager.modules.screens.SecureScreen;
import com.fd.fashion.seller.bean.RolesBean;
import com.fd.fashion.seller.bean.UsersBean;
import com.fd.fashion.seller.manager.ISellerManager;
import com.fd.session.FdSession;
import com.fd.session.FdSessionFactory;
import com.fd.util.PageInfo;
import com.fd.util.ParametersUtil;

public class Roles extends SecureScreen {
	@Override
	public void doBuildTemplate(RunData data, Context context) throws Exception {
		super.doBuildTemplate(data, context);
		data.setLayoutTemplate("/Layout.html");
		
		FdSession session = FdSessionFactory.getFdSession(data);
		UsersBean user = (UsersBean)session.getAttribute("KEY_LOGIN_USER");
		context.put("userBean", user);
		
		RolesBean cond = new RolesBean();
		ParametersUtil.initParameters(data, cond);
		context.put("cond", cond);
		
		/**分页*/
		PageInfo pageInfo = new PageInfo();
		String pageIndex = data.getParameters().getString("pageIndex","1");
		pageInfo.setPageIndex(Integer.parseInt(pageIndex));
		pageInfo.setPageSize(10);
		
		ISellerManager sellerManager = (ISellerManager) getBean("sellerManager");
		List<RolesBean> roles = sellerManager.getRoles(cond,pageInfo);
		
		context.put("roles", roles);
		context.put("pageInfo", pageInfo);
	}
}
