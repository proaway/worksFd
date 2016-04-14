package com.fd.fashion.modules.actions.buyer;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.constants.SessionConstants;
import com.fd.fashion.modules.actions.BaseAction;
import com.fd.session.FdSession;
import com.fd.session.FdSessionFactory;

/**
 * @CreateDate: 2013-3-14 下午12:38:55
 * @author Longli
 * @Description: 买家注册是校验买家是否登录的方法，使用ajax调用，返回json格式数据，同时返回买家购物车产品数量
 * 
 */
public class Logout extends BaseAction {
	@Override
	public void doPerform(RunData data, Context context) throws Exception {
		super.doPerform(data, context);
		FdSession session = FdSessionFactory.getFdSession(data);
		
		session.removeAttribute(SessionConstants.KEY_LOGIN_BUYER);
		session.removeAttribute(SessionConstants.KEY_CART_PRODUCT);

		setTemplate(data, "buyer,Login.html");
	}
}
