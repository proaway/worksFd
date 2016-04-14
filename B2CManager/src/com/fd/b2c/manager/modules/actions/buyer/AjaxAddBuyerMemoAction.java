package com.fd.b2c.manager.modules.actions.buyer;

import java.net.URLDecoder;
import java.util.Date;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.b2c.manager.modules.actions.SecureAction;
import com.fd.fashion.buyer.bean.BuyerMemoBean;
import com.fd.fashion.buyer.manager.IBuyerManager;
import com.fd.fashion.seller.bean.UsersBean;
import com.fd.session.FdSession;
import com.fd.session.FdSessionFactory;
import com.fd.util.JSONUtil;

public class AjaxAddBuyerMemoAction extends SecureAction {
	public void doPerform(RunData data, Context context) throws Exception {
		super.doPerform(data, context);
		//后台操作人员信息
		FdSession session = FdSessionFactory.getFdSession(data);
		UsersBean user = (UsersBean)session.getAttribute("KEY_LOGIN_USER");
		
		long buyerId = data.getParameters().getLongObject("buyerId");
		String message = data.getParameters().getString("message","");
		message = URLDecoder.decode(message, "utf-8");
		BuyerMemoBean buyerMemo = new BuyerMemoBean();
		buyerMemo.setBuyerId(buyerId);
		buyerMemo.setCreateDate(new Date());
		buyerMemo.setMemo(message);
		buyerMemo.setOperator(user.getLoginName());
		
		IBuyerManager buyerManager = (IBuyerManager)getBean("buyerManager");
		buyerMemo = buyerManager.insertBuyerMemo(buyerMemo);
		if(buyerMemo!=null){
			context.put("contentdata", JSONUtil.obj2JSON(buyerMemo));
		}
	}
}
