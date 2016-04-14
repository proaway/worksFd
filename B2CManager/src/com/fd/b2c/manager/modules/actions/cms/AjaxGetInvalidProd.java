package com.fd.b2c.manager.modules.actions.cms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.b2c.manager.modules.actions.SecureAction;
import com.fd.fashion.product.bean.ProductBean;
import com.fd.fashion.product.constants.ProductConstants;
import com.fd.fashion.product.manager.IProductManager;
import com.fd.util.JSONUtil;

/**
 * @CreateDate: 2013-5-21 下午03:17:49
 * @author Longli
 * @Description: 获取无效block
 * 
 */
public class AjaxGetInvalidProd extends SecureAction {
	@Override
	public void doPerform(RunData data, Context context) throws Exception {
		super.doPerform(data, context);
		
		String [] locations = data.getParameters().getStrings("locations");
		if (locations == null) {
			return;
		}
		IProductManager productManager = (IProductManager) getBean("productManager");
		List<HashMap<String, String>> resList = new ArrayList<HashMap<String, String>>();
		for (String str : locations) {
			String ids[] = str.split("_");
			long productId = Long.valueOf(ids[1]);
			ProductBean prod = productManager.getProductBean(productId);
			if (prod == null || prod.getProductStatus() != ProductConstants.ONSALE) { // 产品没找到或产品非上架状态
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("location", ids[0]);
				map.put("productId", ids[1]);
				resList.add(map);
			}
		}
		context.put("contentdata", JSONUtil.list2JSON(resList));
	}
}
