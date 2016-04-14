package com.fd.fashion.modules.actions.buyer;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.constants.SessionConstants;
import com.fd.fashion.buyer.bean.BuyerBean;
import com.fd.fashion.buyer.manager.IBuyerManager;
import com.fd.fashion.modules.actions.SecureAction;
import com.fd.session.FdSession;
import com.fd.session.FdSessionFactory;

/**
 * @author zhangling
 *
 */
public class AjaxDeleteAddressAction extends SecureAction {

	@SuppressWarnings("unchecked")
	public void doPerform(RunData data, Context context) throws Exception {
		data.setCharSet("UTF-8");
		Integer addressId = data.getParameters().getIntObject("addressBookId");
		if(addressId!=null && addressId>0){
			IBuyerManager buyerManager = (IBuyerManager)getBean("buyerManager");
			Integer count = buyerManager.deleteAddressBook(addressId);
			if(count!=null&&count>0){
				context.put("contentdata", 1);
			}
		}
	}

}
