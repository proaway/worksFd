package com.fd.b2c.manager.modules.actions.user;

import javax.servlet.http.HttpSession;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.b2c.manager.modules.actions.SecureAction;
import com.fd.constants.SessionConstants;
import com.fd.fashion.seller.bean.UsersBean;
import com.fd.fashion.seller.manager.ISellerManager;
import com.fd.session.FdSession;
import com.fd.session.FdSessionFactory;

public class RepasswordAction extends SecureAction {
	
	@Override
	public void doPerform(RunData data, Context context) throws Exception {
		super.doPerform(data, context);
		
		String psw = data.getParameters().getString("psw");
		String pswrepeat = data.getParameters().getString("pswrepeat");
		String oldpsw = data.getParameters().getString("oldpsw");
		
		if (pswrepeat.equals(psw)) {
			FdSession session = FdSessionFactory.getFdSession(data);
			UsersBean user = (UsersBean) session.getAttribute(SessionConstants.KEY_LOGIN_USER);
			String loginName = user.getLoginName();
			ISellerManager sellerManager = (ISellerManager) getBean("sellerManager");
			if (sellerManager.repassword(loginName, psw, oldpsw)) {
				context.put("repasswordMsg", "修改成功");
			} else {
				context.put("repasswordMsg", "修改失败");
			}
		} else {
			context.put("repasswordMsg", "重复密码错误");
		}
		setTemplate(data, "user,Repassword.html");
	}
}
