package com.fd.b2c.manager.modules.actions.category;

import java.net.URLDecoder;
import java.util.List;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.b2c.manager.modules.actions.SecureAction;
import com.fd.fashion.dict.bean.CategoryBean;
import com.fd.fashion.dict.manager.IDictManager;
import com.fd.util.JSONUtil;

public class AjaxGetCategoryCondition extends SecureAction {

	public void doPerform(RunData data, Context context) throws Exception {
		super.doPerform(data, context);
		
		String condition = data.getParameters().getString("condition");
		condition = URLDecoder.decode(condition, "utf-8");
		if("".equals(condition)){
			context.put("contentdata", "");
		}else{
			condition = condition.trim();
			IDictManager dictManager = (IDictManager) getBean("dictManager");
			List<CategoryBean> categories = null;
			categories = dictManager.searchCategories(condition);
			if(null != categories && categories.size()>0){
				categories = dictManager.changeCategoryNameString(categories);
			}
			context.put("contentdata", JSONUtil.list2JSON(categories));
		}
	}

}
