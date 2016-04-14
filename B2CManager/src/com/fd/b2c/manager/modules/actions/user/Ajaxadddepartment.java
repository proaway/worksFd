package com.fd.b2c.manager.modules.actions.user;

import java.util.Date;
import java.util.HashMap;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.b2c.manager.modules.actions.SecureAction;
import com.fd.constants.SessionConstants;
import com.fd.fashion.seller.bean.DepartmentBean;
import com.fd.fashion.seller.bean.UsersBean;
import com.fd.fashion.seller.manager.ISellerManager;
import com.fd.session.FdSession;
import com.fd.session.FdSessionFactory;
import com.fd.util.JSONUtil;
import com.fd.util.ParametersUtil;

public class Ajaxadddepartment extends SecureAction {
	@Override
	public void doPerform(RunData data, Context context) throws Exception {
		super.doPerform(data, context);

		DepartmentBean dep = new DepartmentBean();
		ParametersUtil.initParameters(data, dep);

		FdSession session = FdSessionFactory.getFdSession(data);
		UsersBean user = (UsersBean) session.getAttribute(SessionConstants.KEY_LOGIN_USER);
		Date now  = new Date();
		dep.setCreateTime(now);
		if (user != null) {
			dep.setCreator(user.getUserId());
		}
		
		ISellerManager sellerManager = (ISellerManager) getBean("sellerManager");
		dep = sellerManager.addDepartment(dep);
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("status", dep != null);
		map.put("dep", dep);
		context.put("contentdata", JSONUtil.obj2JSON(map));
	}
}
