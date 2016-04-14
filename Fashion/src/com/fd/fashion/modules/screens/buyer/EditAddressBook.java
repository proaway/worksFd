package com.fd.fashion.modules.screens.buyer;

import java.util.List;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.fashion.buyer.bean.BuyerAddressBookBean;
import com.fd.fashion.buyer.manager.IBuyerManager;
import com.fd.fashion.dict.bean.RegionBean;
import com.fd.fashion.dict.manager.IDictManager;
import com.fd.fashion.modules.screens.BaseScreen;
import com.fd.util.AppContextUtil;

public class EditAddressBook extends BaseScreen {

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
