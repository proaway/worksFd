package com.fd.b2c.manager.modules.actions.user;

import java.util.Date;
import java.util.HashMap;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.b2c.manager.modules.actions.SecureAction;
import com.fd.constants.SessionConstants;
import com.fd.fashion.seller.bean.UsersBean;
import com.fd.fashion.seller.manager.ISellerManager;
import com.fd.session.FdSession;
import com.fd.session.FdSessionFactory;
import com.fd.util.JSONUtil;
import com.fd.util.ParametersUtil;

public class Ajaxedituser extends SecureAction {
	
	@Override
	public void doPerform(RunData data, Context context) throws Exception {
		super.doPerform(data, context);

		UsersBean user = new UsersBean();
		ParametersUtil.initParameters(data, user);

		FdSession session = FdSessionFactory.getFdSession(data);
		UsersBean sessionUser = (UsersBean) session.getAttribute(SessionConstants.KEY_LOGIN_USER);
		Date now = new Date();
		boolean success = false;
		ISellerManager sellerManager = (ISellerManager) getBean("sellerManager");
		if(user.getUserId() != null && user.getUserId().longValue()>0) {
			// update
			try {
				if (sessionUser != null) {
					user.setOperator(sessionUser.getUserId());
				}
				user.setUpdateTime(now);
				int i = sellerManager.updateUser(user);
				success = i>0;
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			// add
			try {
				if (sessionUser != null) {
					user.setOperator(sessionUser.getUserId());
					user.setCreator(sessionUser.getUserId());
				}
				user.setVaild(1);
				user.setCreateTime(now);
				user.setUpdateTime(now);
				user = sellerManager.addUser(user);
				success = user != null;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("status", success);
		if (success) {
			map.put("user", user);
		}
		context.put("contentdata", JSONUtil.obj2JSON(map));
	}
}
