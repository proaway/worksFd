/**
 * EditAddress.java
 * @author:  ZhouRongyu
 */
package com.fd.fashion.modules.screens.order;


import java.util.List;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.fashion.buyer.bean.BuyerAddressBookBean;
import com.fd.fashion.buyer.manager.IBuyerManager;
import com.fd.fashion.dict.bean.RegionBean;
import com.fd.fashion.dict.manager.IDictManager;
import com.fd.fashion.modules.screens.BaseScreen;
import com.fd.fashion.modules.screens.SecureScreen;
import com.fd.util.AppContextUtil;
import com.fd.util.StringUtil;

/**
 * @CreateDate:  2013-4-2 下午8:26:45 
 * @author:  ZhouRongyu
 * @Description:  TODO
 * 
 * @version:  V1.0
 */
public class EditAddress extends BaseScreen {

	public void doBuildTemplate(RunData data, Context context) throws Exception {
		super.doBuildTemplate(data, context);
		data.setCharSet("utf-8");
		data.setLayoutTemplate("/NoLayout.html");

		IDictManager dictManager = (IDictManager) AppContextUtil.getBean("dictManager");
		List<RegionBean> countriesList = dictManager.getCountries();
		context.put("countriesList", countriesList);
		
		long addressId = data.getParameters().getLong("addressId");
		if (addressId > 0) {
			IBuyerManager buyerManager = (IBuyerManager) this.getBean("buyerManager");
			BuyerAddressBookBean book = buyerManager.getBuyerAddressBook(addressId);
			context.put("book", book);
		}
	}
}
