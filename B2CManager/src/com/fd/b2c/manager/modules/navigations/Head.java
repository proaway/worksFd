package com.fd.b2c.manager.modules.navigations;

import java.util.ArrayList;
import java.util.List;

import org.apache.turbine.modules.navigations.VelocityNavigation;
import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.constants.SessionConstants;
import com.fd.fashion.seller.bean.ModulesBean;
import com.fd.fashion.seller.bean.UsersBean;
import com.fd.fashion.seller.manager.ISellerManager;
import com.fd.session.FdSession;
import com.fd.session.FdSessionFactory;
import com.fd.util.AppContextUtil;

/**
 * @CreateDate:  2013-3-13 下午08:17:27 
 * @author:  ZhouRongyu
 * @Description:  TODO
 * 
 * @version:  V1.0
 */
public class Head extends VelocityNavigation {

	@SuppressWarnings("unchecked")
	protected void doBuildTemplate(RunData data, Context context)
			throws Exception {
		data.setCharSet("UTF-8");
		FdSession session = FdSessionFactory.getFdSession(data);
		UsersBean user = (UsersBean) session.getAttribute(SessionConstants.KEY_LOGIN_USER);
		context.put("user", user);
		List<ModulesBean> modules = (List<ModulesBean>) session.getAttribute(SessionConstants.KEY_USER_MODULES);
		
		ISellerManager sellerManager = (ISellerManager)AppContextUtil.getBean("sellerManager");
		List<ModulesBean> roots = sellerManager.getSubModules(-1);
		List<ModulesBean> roleModules = new ArrayList<ModulesBean>();

		if (user.getRoleId() == 1) { // 超级管理员
			roleModules = roots;
			
		} else {
			for (ModulesBean m : roots) {
				for (ModulesBean r : modules) {
					if (m.getModulesId().longValue() == r.getModulesId().longValue()) {
						roleModules.add(m);
					}
				}
			}
		}
		for (ModulesBean m : roleModules) {
			List<ModulesBean> roleSubs = getSubModules(modules, sellerManager, m, user);
			if (roleSubs != null && roleSubs.size()>0) {
				for (ModulesBean mm : roleSubs) {
					mm.setSubModules(getSubModules(modules, sellerManager, mm, user));
				}
			}
			m.setSubModules(roleSubs);
		}
		context.put("rootModules", roleModules);
	}

	/**
	 * @param modules
	 * @param sellerManager
	 * @param m
	 * @return
	 * @throws Exception
	 */
	private List<ModulesBean> getSubModules(List<ModulesBean> modules,
			ISellerManager sellerManager, ModulesBean m, UsersBean user) throws Exception {
		List<ModulesBean> subs = sellerManager.getSubModules(m.getModulesId());
		List<ModulesBean> roleSubs = new ArrayList<ModulesBean>();
		if (user.getPriority() == 1) {
			roleSubs = subs;
		} else {
			for (ModulesBean s : subs) {
				for (ModulesBean r : modules) {
					if (s.getModulesId().longValue() == r.getModulesId().longValue()) {
						roleSubs.add(s);
					}
				}
			}
		}
		return roleSubs;
	}
	
}
