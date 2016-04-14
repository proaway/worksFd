package com.fd.b2c.manager.modules.actions.category;

import java.util.List;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.b2c.manager.modules.actions.SecureAction;
import com.fd.fashion.dict.manager.IDictManager;
import com.fd.fashion.dict.vo.CatAttrNode;
import com.fd.fashion.dict.vo.CatConfNode;

public class AjaxGetCatAttr extends SecureAction {
	@Override
	public void doPerform(RunData data, Context context) throws Exception {
		super.doPerform(data, context);
		
		String catId = data.getParameters().getString("catId");

		IDictManager dictManager = (IDictManager) getBean("dictManager");
		List<CatAttrNode> attrNodes = dictManager.getCatAttributeNodes(catId);
		context.put("attrNodes", attrNodes);
		List<CatConfNode> configNodes = dictManager.getCatConfigNodes(catId);
		context.put("configNodes", configNodes);
		
		// 佣金
		context.put("commission", 1.05);
		
		setTemplate(data, "category,catattrs.html");
	}
}
