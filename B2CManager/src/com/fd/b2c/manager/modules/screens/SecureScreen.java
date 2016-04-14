package com.fd.b2c.manager.modules.screens;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.turbine.modules.screens.VelocitySecureScreen;
import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.constants.SessionConstants;
import com.fd.fashion.seller.bean.ModulesBean;
import com.fd.fashion.seller.bean.UsersBean;
import com.fd.fashion.seller.manager.ISellerManager;
import com.fd.session.CacheManager;
import com.fd.session.FdSession;
import com.fd.session.FdSessionFactory;
import com.fd.util.AppContextUtil;
import com.fd.util.DictUtil;
import com.fd.util.JSONUtil;
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
		//data.getParameters().setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
		context.put("DictUtil", new DictUtil());
		context.put("StringUtil", new StringUtil());
		context.put("WebPropUtil", new WebPropUtil());
		context.put("JSONUtil", new JSONUtil());
		context.put("RewriteUtil", new RewriteUtil());
	}

	@SuppressWarnings("unchecked")
	protected boolean isAuthorized(RunData data) throws Exception {
		String url = data.getRequest().getRequestURI();
		url = URLDecoder.decode(url, "utf-8");
		
		FdSession session = FdSessionFactory.getFdSession(data);
		UsersBean user = (UsersBean) session.getAttribute(SessionConstants.KEY_LOGIN_USER);
		if (user == null) {
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
		} else if (user.getRoleId() > 1) {
			String name = url.substring(url.lastIndexOf("/")+1);
			List<ModulesBean> modules = (List<ModulesBean>) session.getAttribute(SessionConstants.KEY_USER_MODULES);
			HashMap<String, ModulesBean> allModules = (HashMap<String, ModulesBean>) CacheManager.getData(SessionConstants.KEY_ALL_MODULES);
			if (allModules == null) {
				ISellerManager sellerManager = (ISellerManager) AppContextUtil.getBean("sellerManager");
				List<ModulesBean> list = sellerManager.getAllModules();
				allModules = new HashMap<String, ModulesBean>();
				for (ModulesBean m : list) {
					allModules.put(m.getUrl(), m);
				}
				CacheManager.setData(SessionConstants.KEY_ALL_MODULES, allModules, 360);
			}
			if (allModules.get(name) == null) {
				// 权限控制需求列表中没有的url默认有访问权限
				return true;
			}
			if (modules != null) {
				for (ModulesBean m : modules) {
					if (name.equalsIgnoreCase(m.getUrl())) {
						return true;
					}
				}
			}
			setTemplate(data, "NoAllow.html");
		}
		return true;
	}

}