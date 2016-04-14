package com.fd.fashion.modules.screens;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpSession;

import org.apache.turbine.modules.screens.VelocitySecureScreen;
import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.constants.SessionConstants;
import com.fd.fashion.buyer.bean.BuyerBean;
import com.fd.session.FdSession;
import com.fd.session.FdSessionFactory;
import com.fd.util.AppContextUtil;
import com.fd.util.DictUtil;
import com.fd.util.RewriteUtil;
import com.fd.util.StringUtil;
import com.fd.util.WebPropUtil;

public class SecureScreen extends VelocitySecureScreen {

	public SecureScreen() {

	}
	public Object getBean(String name) {
		return AppContextUtil.getBean(name);
	}

	public void doBuildTemplate(RunData data, Context context) throws Exception {
		data.setCharSet("UTF-8");
		data.setTemplateEncoding("UTF-8");
		data.setContentType("text/html;charset=UTF-8");
		data.getRequest().setCharacterEncoding("UTF-8");
		data.getResponse().setCharacterEncoding("UTF-8");
		data.getResponse().setContentType("text/html;charset=UTF-8");
		data.getParameters().setCharacterEncoding("UTF-8");
		data.getParameters().setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
		context.put("DictUtil", new DictUtil());
		context.put("StringUtil", new StringUtil());
		context.put("WebPropUtil", new WebPropUtil());
		context.put("RewriteUtil", new RewriteUtil());
	}

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