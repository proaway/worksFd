/**
 * UpdateAddressBookAction.java
 * @author:  ZhouRongyu
 */
package com.fd.fashion.modules.actions.order;

import java.util.HashMap;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.constants.SessionConstants;
import com.fd.fashion.buyer.bean.BuyerAddressBookBean;
import com.fd.fashion.buyer.bean.BuyerBean;
import com.fd.fashion.buyer.manager.IBuyerManager;
import com.fd.fashion.modules.actions.SecureAction;
import com.fd.session.FdSession;
import com.fd.session.FdSessionFactory;
import com.fd.util.JSONUtil;
import com.fd.util.StringUtil;

/**
 * @CreateDate:  2013-4-2 下午5:04:35 
 * @author:  ZhouRongyu
 * @Description:  TODO
 * 
 * @version:  V1.0
 */
public class UpdateAddressBookAction  extends SecureAction {
	public void doPerform(RunData data, Context context) throws Exception {
		FdSession session = FdSessionFactory.getFdSession(data);
		
		BuyerBean buyer = (BuyerBean) session.getAttribute(SessionConstants.KEY_LOGIN_BUYER);
		
		String firstName = data.getParameters().getString("firstName");
		String lastName = data.getParameters().getString("lastName");
		String addressLine1 = data.getParameters().getString("addressLine1");
		String addressLine2 = data.getParameters().getString("addressLine2");
		String city = data.getParameters().getString("city");
		String country = data.getParameters().getString("country");
		String province = data.getParameters().getString("province");
		String postalCode = data.getParameters().getString("postalCode");
		String phone = data.getParameters().getString("phone");
		
		BuyerAddressBookBean book = new BuyerAddressBookBean();
		book.setFirstName(firstName);
		book.setLastName(lastName);
		book.setAddressLine1(addressLine1);
		book.setAddressLine2(addressLine2);
		book.setCity(city);
		book.setCountry(country);
		book.setProvince(province);
		book.setPostalCode(postalCode);
		book.setPhone(phone);
		book.setBuyerId(buyer.getBuyerId());

		HashMap<String, Object> map = new HashMap<String, Object>();
		IBuyerManager buyerManager = (IBuyerManager) this.getBean("buyerManager");
		String addressId = data.getParameters().getString("addressId");
		try {
			if (StringUtil.isEmpty(addressId)) {
				buyerManager.addBuyerAddressBook(book);
				map.put("status", 1);
				map.put("book", book);
			} else {
				book.setAddressId(Long.valueOf(addressId));
				int i = buyerManager.updateBuyerAddressBook(book);
				if (i>0) {
					map.put("status", 1);
					map.put("book", book);
				} else {
					map.put("status", 0);
				}
			}
		} catch (Exception e) {
			map.put("status", 0);
		}
		context.put("contentdata", JSONUtil.obj2JSON(map));
	}

}
