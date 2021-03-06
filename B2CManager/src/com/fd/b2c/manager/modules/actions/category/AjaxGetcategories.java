package com.fd.b2c.manager.modules.actions.category;

import java.util.List;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.b2c.manager.modules.actions.SecureAction;
import com.fd.fashion.dict.bean.CategoryBean;
import com.fd.fashion.dict.manager.IDictManager;
import com.fd.util.JSONUtil;

/**
 * @CreateDate: 2013-3-21 下午12:00:07
 * @author Longli
 * @Description: Ajax方式获取所有下级类目
 * 
 */
public class AjaxGetcategories extends SecureAction {
	@Override
	public void doPerform(RunData data, Context context) throws Exception {
		super.doPerform(data, context);
		String catId = data.getParameters().getString("catId");
		
		IDictManager dictManager = (IDictManager) getBean("dictManager");
		List<CategoryBean> categories = dictManager.getChildrenCategories(catId);
		
		context.put("contentdata", JSONUtil.list2JSON(categories));
	}
}
