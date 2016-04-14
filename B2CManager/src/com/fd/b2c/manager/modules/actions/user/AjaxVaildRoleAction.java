package com.fd.b2c.manager.modules.actions.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.b2c.manager.modules.actions.SecureAction;
import com.fd.fashion.seller.bean.UsersBean;
import com.fd.fashion.seller.manager.ISellerManager;
import com.fd.session.FdSession;
import com.fd.session.FdSessionFactory;
import com.fd.util.JSONUtil;

/**
 * @author zhangling
 *启用或禁用角色
 *启用角色则把所选择的用户启用
 *禁用角色则把所有为该角色的用户禁用
 */
public class AjaxVaildRoleAction extends SecureAction {
	
	@Override
	public void doPerform(RunData data, Context context) throws Exception {
		super.doPerform(data, context);
		
		ISellerManager sellerManager = (ISellerManager) getBean("sellerManager");
		String flag = data.getParameters().getString("actionFlag");
		Long roleId = data.getParameters().getLongObject("roleId");
	    long[] roleIds = {0};
	    roleIds[0] = roleId;
	    HashMap<String, Object> map = new HashMap<String, Object>();
	    FdSession session = FdSessionFactory.getFdSession(data);
		UsersBean userBean = (UsersBean)session.getAttribute("KEY_LOGIN_USER");
		if(flag.equals("0")){
			//执行禁用操作
			int i = sellerManager.vaildRoles(roleIds, 0);
			if(i>0){
				UsersBean user = new UsersBean();
				user.setUpdateTime(new Date());
				user.setOperator(userBean.getUserId());
				user.setVaild(0);
				user.setRoleId(roleId);
				List<UsersBean> list = new ArrayList<UsersBean>();
				list.add(user);
				sellerManager.updateUsers(list);
				map.put("status", 1);
			}
		}else if(flag.equals("1")){
			//执行启用操作
			int i = sellerManager.vaildRoles(roleIds, 1); 
			if(i>0){
				map.put("status", 1);
			}
		}else if(flag.equals("3")){
			//启用用户
			String id = data.getParameters().getString("userIds","");
			if("".equals(id)){
				map.put("status", 1);
			}else{
				String[] ids = id.split(",");
				List<UsersBean> users  = new ArrayList<UsersBean>();
				for (int j = 0; j < ids.length; j++) {
					UsersBean ub = new UsersBean();
					ub.setUpdateTime(new Date());
					ub.setOperator(userBean.getUserId());
					ub.setVaild(1);
					ub.setUserId(Long.valueOf(ids[j]));
					users.add(ub);
				}
				int userCount = sellerManager.updateUsers(users);
				if(userCount>0){
					map.put("status", userCount);
				}
			}
		}
		
		context.put("contentdata", JSONUtil.obj2JSON(map));
	}

}
