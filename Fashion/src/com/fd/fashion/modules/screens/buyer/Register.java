package com.fd.fashion.modules.screens.buyer;

import java.util.List;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.fashion.dict.bean.RegionBean;
import com.fd.fashion.dict.manager.IDictManager;
import com.fd.fashion.modules.screens.BaseScreen;

/**
 * @CreateDate: 2013-3-14 下午12:13:57
 * @author Longli
 * @Description: 注册页面 buyer,Register.html
 * 
 */
public class Register extends BaseScreen {
	public void doBuildTemplate(RunData data, Context context) throws Exception {
		super.doBuildTemplate(data, context);
		data.setLayoutTemplate("/Layout.html");
		
		IDictManager dictManager = (IDictManager) getBean("dictManager");
		List<RegionBean> countries = dictManager.getCountries();
		context.put("countries", countries);
	}
}
