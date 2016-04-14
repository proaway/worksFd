package com.fd.b2c.manager.modules.actions.category;

import java.util.List;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.b2c.manager.modules.actions.SecureAction;
import com.fd.fashion.dict.manager.IDictManager;
import com.fd.fashion.dict.vo.CatAttrNode;
import com.fd.fashion.dict.vo.CatConfNode;
import com.fd.fashion.seller.bean.CustomCategoryBean;
import com.fd.fashion.seller.manager.ISellerManager;

public class AjaxGetNewCatId extends SecureAction {
	@Override
	public void doPerform(RunData data, Context context) throws Exception {
		super.doPerform(data, context);
		
		String catId = data.getParameters().getString("catId");

		ISellerManager sellerManager = (ISellerManager) getBean("sellerManager");
		List<CustomCategoryBean> cats = sellerManager.getCustomChildrenCats(catId);
		String newCatId = catId + "001";
		if (cats != null && cats.size()>0) {
			int last = 1;
			for (CustomCategoryBean cat : cats) {
				int id = Integer.valueOf(cat.getCatId().substring(cat.getCatId().length()-3));
				if (last<id) {
					break;
				}
				last++;
			}
			String cid = last+"";
			while (cid.length()<3) {
				cid = "0" + cid;
			}
			newCatId = catId + cid;
		}
		
		context.put("contentdata", newCatId);
	}
}
