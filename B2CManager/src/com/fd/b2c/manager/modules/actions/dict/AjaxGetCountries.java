package com.fd.b2c.manager.modules.actions.dict;

import java.util.List;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.b2c.manager.modules.actions.SecureAction;
import com.fd.fashion.dict.bean.RegionBean;
import com.fd.fashion.dict.manager.IDictManager;
import com.fd.util.JSONUtil;

/**
 * @CreateDate: 2013-3-29 下午01:42:40
 * @author Longli
 * @Description: ajax获取国家列表
 * 
 */
public class AjaxGetCountries extends SecureAction {
	@Override
	public void doPerform(RunData data, Context context) throws Exception {
		super.doPerform(data, context);
		IDictManager dictManager = (IDictManager) getBean("dictManager");
		List<RegionBean> countries = dictManager.getCountries();
		context.put("contentdata", JSONUtil.list2JSON(countries));
	}
}
