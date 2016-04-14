package com.fd.b2c.manager.modules.actions.user;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.turbine.util.RunData;

import com.fd.b2c.manager.modules.actions.BaseAction;
import com.fd.constants.SessionConstants;
import com.fd.fashion.seller.bean.ModulesBean;
import com.fd.fashion.seller.bean.UsersBean;
import com.fd.fashion.seller.manager.ISellerManager;
import com.fd.session.FdSession;
import com.fd.session.FdSessionFactory;

public class LoginAction extends BaseAction {
	
	@Override
	public void doPerform(RunData data,
			org.apache.velocity.context.Context context) throws Exception {
		super.doPerform(data, context);
		String loginName = data.getParameters().getString("loginName");
		String psw = data.getParameters().getString("psw");
		String securityCode = data.getParameters().getString("securityCode");
		String returnUrl = data.getParameters().getString("returnUrl");
		
		FdSession session = FdSessionFactory.getFdSession(data);
		String sessionSecurityCode = (String) session.getAttribute(SessionConstants.SECURITYCODE);
		if (securityCode.equalsIgnoreCase(sessionSecurityCode)) {
			ISellerManager sellerManager = (ISellerManager) getBean("sellerManager");
			UsersBean user = sellerManager.login(loginName, psw);
			if (user != null) {
				if(user.getVaild()==0){
					context.put("loginErrMsg", "此用户被禁用，请联系管理人员启用后再使用");
				}else{
					List<ModulesBean> modules = sellerManager.getRoleModules(user.getRoleId());
					if (modules != null) {
						session.setAttribute(SessionConstants.KEY_USER_MODULES, modules);
					}
					session.setAttribute(SessionConstants.KEY_LOGIN_USER, user);
					if (StringUtils.isEmpty(returnUrl)) {
						returnUrl = data.getRequest().getContextPath()
									+ "/template/Index.html";
					}
					data.getResponse().sendRedirect(returnUrl);
					return;
				}
			}
			context.put("loginErrMsg", "用户名密码错误");
		} else {
			context.put("loginErrMsg", "校验码错误");
		}
		context.put("loginName", loginName);
		setTemplate(data, "user,Login.html");
		
	}
}
