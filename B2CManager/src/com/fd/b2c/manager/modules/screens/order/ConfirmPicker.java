package com.fd.b2c.manager.modules.screens.order;

import java.util.List;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.b2c.manager.modules.screens.SecureScreen;
import com.fd.fashion.seller.bean.RolesBean;
import com.fd.fashion.seller.bean.UsersBean;
import com.fd.fashion.seller.manager.ISellerManager;
import com.fd.fashion.seller.vo.UserSearchVo;

public class ConfirmPicker extends SecureScreen {
	
	public void doBuildTemplate(RunData data, Context context) throws Exception {
		super.doBuildTemplate(data, context);
		
		String orderIds = data.getParameters().getString("orderIds");
		context.put("orderIds", orderIds);
		
		String flag = data.getParameters().getString("flag");
		if(flag==null || flag.equals("")){
			
		}else{
			context.put("flag", flag);
		}
		
		ISellerManager sellerManager = (ISellerManager) getBean("sellerManager");
		RolesBean role = new RolesBean();
		role.setRoleName("拣货人");
		List<RolesBean> list = sellerManager.getRoles(role);
		if(list!=null && list.size()>0){
			role = list.get(0);
		}
		if(role!=null && role.getRoleId()!=null && role.getRoleId()>0){
			//该角色存在
			UserSearchVo user = new UserSearchVo();
			user.setRoleId(role.getRoleId());
			List<UsersBean> users = sellerManager.getUsers(user, null);
			if(users!=null && users.size()>0){
				context.put("users", users);
			}
		}
	}

}
