package com.fd.fashion.modules.screens.buyer;

import org.apache.commons.lang.StringUtils;
import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.constants.SessionConstants;
import com.fd.fashion.buyer.bean.BuyerBean;
import com.fd.fashion.buyer.manager.IBuyerManager;
import com.fd.fashion.modules.screens.SecureScreen;
import com.fd.session.FdSession;
import com.fd.session.FdSessionFactory;
import com.fd.util.DESadeUtil;

public class ChangePassword extends SecureScreen {
	public void doBuildTemplate(RunData data, Context context) throws Exception {
		super.doBuildTemplate(data, context);
		data.setLayoutTemplate("/BuyerAccountLayout.html");
		FdSession session = FdSessionFactory.getFdSession(data);

		String loginError = (String) session
				.getAttribute(SessionConstants.KEY_LOGIN_ERROR);
		int loginErrorCount = 0;
		if (StringUtils.isNotEmpty(loginError)) {
			loginErrorCount = Integer.valueOf(loginError);
		}
		context.put("loginErrorCount", loginErrorCount);
		
		BuyerBean buyer = (BuyerBean) session
				.getAttribute(SessionConstants.KEY_LOGIN_BUYER);
		String actionFlag = data.getParameters().getString("actionFlag","");
		if (actionFlag.equals("changePassword")) {
			String originPassword = data.getParameters()
					.getString("originPass");
			String newPassword = data.getParameters().getString("newPass");
			IBuyerManager buyerManager = (IBuyerManager) getBean("buyerManager");
			if (DESadeUtil.decryptMode(buyer.getPassword()).equals(
					originPassword)) {
				BuyerBean buyerBean = new BuyerBean();
				buyerBean.setBuyerId(buyer.getBuyerId());
				buyerBean.setPassword(DESadeUtil.encryptMode(newPassword));
				Integer count = buyerManager.updateBuyer(buyerBean);
				if (count != null && count > 0) {
					buyer.setPassword(buyer.getPassword());
					session.setAttribute(SessionConstants.KEY_LOGIN_BUYER,
							buyer);
					context.put("message", "change password success");
				} else {
					context.put("msg", "change password failure");
				}
			} else {
				context.put("msg", "your existing password is wrong");
			}
		}
	}
}
