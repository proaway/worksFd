package com.fd.b2c.manager.modules.actions.user;

import java.util.HashMap;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.b2c.manager.modules.actions.SecureAction;
import com.fd.fashion.seller.bean.UsersBean;
import com.fd.fashion.seller.manager.ISellerManager;
import com.fd.util.JSONUtil;

public class AjaxVaildUserAction extends SecureAction {
	
	@Override
	public void doPerform(RunData data, Context context) throws Exception {
		super.doPerform(data, context);
		
		ISellerManager sellerManager = (ISellerManager) getBean("sellerManager");
		String flag = data.getParameters().getString("actionFlag");
		Long userId = data.getParameters().getLongObject("userId");
		UsersBean user = new UsersBean();
		user.setUserId(userId);
		if(flag.equals("1")){
			//执行禁用操作
			user.setVaild(0);
		}else if(flag.equals("2")){
			//执行启用操作
			user.setVaild(1);
		}
		HashMap<String, Object> map = new HashMap<String, Object>();
		Integer count = sellerManager.updateUser(user);
		if(count>0){
			map.put("status", 1);
		}
		context.put("contentdata", JSONUtil.obj2JSON(map));
	}

}
