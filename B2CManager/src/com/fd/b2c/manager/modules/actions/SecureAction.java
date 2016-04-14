package com.fd.b2c.manager.modules.actions;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.turbine.modules.actions.VelocitySecureAction;
import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.constants.SessionConstants;
import com.fd.fashion.seller.bean.ModulesBean;
import com.fd.fashion.seller.bean.UsersBean;
import com.fd.session.FdSession;
import com.fd.session.FdSessionFactory;
import com.fd.util.AppContextUtil;
import com.fd.util.DictUtil;
import com.fd.util.JSONUtil;
import com.fd.util.StringUtil;
import com.fd.util.WebPropUtil;

public class SecureAction extends VelocitySecureAction {
	public SecureAction()
	{
	
	}

	public Object getBean(String name)
	{
		return AppContextUtil.getBean(name);
	}

	public void doPerform(RunData data, Context context) throws Exception {
		data.setCharSet("UTF-8");
		data.setTemplateEncoding("UTF-8");
		data.setContentType("text/html;charset=UTF-8");
		data.getRequest().setCharacterEncoding("UTF-8");
		data.getResponse().setCharacterEncoding("UTF-8");
		data.getResponse().setContentType("text/html;charset=UTF-8");
		data.getParameters().setCharacterEncoding("UTF-8");
		context.put("DictUtil", new DictUtil());
		context.put("StringUtil", new StringUtil());
		context.put("WebPropUtil", new WebPropUtil());
		context.put("JSONUtil", new JSONUtil());
	}

	/**
	 * @param 	data
	 * @return	boolean		是否能继续访问请求Action
	 * @throws Exception
	 */
	protected boolean isAuthorized(RunData data) throws Exception {
		FdSession session = FdSessionFactory.getFdSession(data);
		UsersBean user = (UsersBean) session.getAttribute(SessionConstants.KEY_LOGIN_USER);
		if (user == null) {
			String url = data.getRequest().getRequestURI();
			if (url.contains("login.vm")) {
				data.getResponse().sendRedirect(
						data.getRequest().getContextPath()
								+ "/template/user,Login.html");
			} else {
				String queryStr = data.getRequest().getQueryString();
				if (StringUtils.isNotEmpty(queryStr)) {
					url = url + "?" + queryStr;
				}
				data.getResponse().sendRedirect(
						data.getRequest().getContextPath()
								+ "/template/user,Login.html?returnUrl="
								+ url);
			}
			return false;
		} 
		return true;
	}
}
