package com.fd.b2c.manager.modules.actions.buyer;

import java.net.URLDecoder;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.b2c.manager.modules.actions.SecureAction;
import com.fd.fashion.buyer.bean.BuyerBean;
import com.fd.fashion.buyer.manager.IBuyerManager;
import com.fd.util.DESadeUtil;
import com.fd.util.JSONUtil;

public class AjaxChangePasswordAction  extends SecureAction {
	public void doPerform(RunData data, Context context) throws Exception {
		super.doPerform(data, context);
		long buyerId = data.getParameters().getLongObject("buyerId");
		String password = data.getParameters().getString("password","");
		password = URLDecoder.decode(password, "utf-8");
		String isEmail = data.getParameters().getString("isSendEmail");
		/**isEmail==1则表示发送邮件，isEmail==2则表示直接强制修改密码*/
		if(isEmail.equals("1")){
			//发送修改密码的邮件
		}else if(isEmail.equals("2")){
			//强制修改密码
			IBuyerManager buyerManager = (IBuyerManager)getBean("buyerManager");
			BuyerBean buyer = new BuyerBean();
			buyer.setBuyerId(buyerId);
			//加密操作
			buyer.setPassword(DESadeUtil.encryptMode(password));
			Integer count = buyerManager.updateBuyer(buyer);
			if(count!=null && count>0){
				context.put("contentdata", JSONUtil.obj2JSON(buyer));
			}
		}
		
	}

}
