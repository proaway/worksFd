package com.fd.fashion.modules.actions.buyer;

import java.util.HashMap;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.constants.SessionConstants;
import com.fd.fashion.buyer.bean.BuyerBean;
import com.fd.fashion.buyer.manager.IBuyerManager;
import com.fd.fashion.modules.actions.SecureAction;
import com.fd.session.FdSession;
import com.fd.session.FdSessionFactory;

public class SetDefaultAddressAction extends SecureAction {

	@SuppressWarnings("unchecked")
	public void doPerform(RunData data, Context context) throws Exception {
		data.setCharSet("UTF-8");
		FdSession session = FdSessionFactory.getFdSession(data);
		
		BuyerBean buyer = (BuyerBean) session.getAttribute(SessionConstants.KEY_LOGIN_BUYER);
		
		IBuyerManager buyerManager = (IBuyerManager) getBean("buyerManager");
		Integer addressId = data.getParameters().getIntObject("addressId");
		try {
			buyerManager.setDefaultAddressBook(buyer.getBuyerId(), addressId);
			context.put("contentdata", 1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
