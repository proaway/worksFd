package com.fd.fashion.modules.actions;

import org.apache.turbine.modules.actions.VelocitySecureAction;
import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.constants.SessionConstants;
import com.fd.fashion.buyer.bean.BuyerBean;
import com.fd.session.FdSession;
import com.fd.session.FdSessionFactory;
import com.fd.util.AppContextUtil;

public class SecureAction extends VelocitySecureAction {
	public SecureAction()
	{
	
	}

	public Object getBean(String name)
	{
		return AppContextUtil.getBean(name);
	}

	public void doPerform(RunData data, Context context) throws Exception {
	}

	/**
	 * @param 	data
	 * @return	boolean		是否能继续访问请求Action
	 * @throws Exception
	 */
	protected boolean isAuthorized(RunData data) throws Exception {
		FdSession session = FdSessionFactory.getFdSession(data);
		BuyerBean buyer = (BuyerBean) session.getAttribute(SessionConstants.KEY_LOGIN_BUYER);
		if (buyer == null) {
			data.getResponse().sendRedirect(data.getRequest().getContextPath()
					+ "/template/buyer,Login.html");
			return false;
		}
		return true;
	}


	
}
