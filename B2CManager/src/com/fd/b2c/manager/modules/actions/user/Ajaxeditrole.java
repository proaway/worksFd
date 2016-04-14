package com.fd.b2c.manager.modules.actions.user;

import java.util.Date;
import java.util.HashMap;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.b2c.manager.modules.actions.SecureAction;
import com.fd.constants.SessionConstants;
import com.fd.fashion.seller.bean.RolesBean;
import com.fd.fashion.seller.bean.UsersBean;
import com.fd.fashion.seller.manager.ISellerManager;
import com.fd.session.FdSession;
import com.fd.session.FdSessionFactory;
import com.fd.util.JSONUtil;
import com.fd.util.StringUtil;

public class Ajaxeditrole extends SecureAction {
	
	@Override
	public void doPerform(RunData data, Context context) throws Exception {
		super.doPerform(data, context);
		
		Long roleId = data.getParameters().getLongObject("roleId");
		String roleName = data.getParameters().getString("roleName");
		roleName = StringUtil.htmlEncode(roleName);
		String roleDesc = data.getParameters().getString("roleDesc");
		roleDesc = StringUtil.htmlEncode(roleDesc);
		String ids = data.getParameters().getString("modulesIds","");
		String status = data.getParameters().getString("statuts","1");
		RolesBean role = new RolesBean();
		if(roleId!=null){
			role.setRoleId(roleId);
		}
		role.setRoleName(roleName);
		role.setRoleDesc(roleDesc);
		role.setStatus(Integer.parseInt(status));
		if(!ids.equals("")){
			String[] modulesIds = ids.split(",");
			int i=0;
			Long[] mids = new Long[modulesIds.length];
			for(String str :modulesIds){
				if(str!=null && !str.equals("")){
					mids[i] = Long.valueOf(str);
					i++;
				}
			}
			role.setModulesIds(mids);
		}
		

		FdSession session = FdSessionFactory.getFdSession(data);
		UsersBean user = (UsersBean) session.getAttribute(SessionConstants.KEY_LOGIN_USER);
		Date now = new Date();
		boolean success = false;
		ISellerManager sellerManager = (ISellerManager) getBean("sellerManager");
		if(role.getRoleId() != null && role.getRoleId().longValue()>0) {
			// update
			try {
				if (user != null) {
					role.setLastUpdateBy(user.getUserId());
				}
				role.setLastUpdateTime(now);
				int i = sellerManager.updateRoles(role);
				success = i>0;
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			// add
			try {
				if (user != null) {
					role.setLastUpdateBy(user.getUserId());
					role.setCreateBy(user.getUserId());
				}
				role.setStatus(1);
				role.setCreateTime(now);
				role.setLastUpdateTime(now);
				role = sellerManager.addRoles(role);
				success = role != null;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("status", success);
		if (success) {
			map.put("role", role);
		}
		context.put("contentdata", JSONUtil.obj2JSON(map));
	}
}
