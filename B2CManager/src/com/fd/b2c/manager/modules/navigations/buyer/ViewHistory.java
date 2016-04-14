package com.fd.b2c.manager.modules.navigations.buyer;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.turbine.modules.navigations.VelocityNavigation;
import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.fashion.dict.bean.CategoryBean;
import com.fd.fashion.dict.manager.IDictManager;
import com.fd.fashion.product.manager.IProductManager;
import com.fd.fashion.product.vo.ProductVo;
import com.fd.util.AppContextUtil;
import com.fd.util.PageInfo;
import com.fd.util.StringUtil;

public class ViewHistory extends VelocityNavigation {

	protected void doBuildTemplate(RunData data, Context context)
			throws Exception {
		data.setCharSet("UTF-8");
		String historyProductIds = data.getCookies().get("ProductHistory");
		if(historyProductIds != null){
			historyProductIds = URLDecoder.decode(historyProductIds, "utf-8");
			if (StringUtils.isNotEmpty(historyProductIds)) {
				String ids[] = historyProductIds.split(",");
				List<Long> productIds = new ArrayList<Long>();
				for (String id : ids) {
					if (StringUtils.isNotEmpty(id)) {
						productIds.add(StringUtil.getAsLong(id, 0));
					}
				}
				IProductManager productManager = (IProductManager) AppContextUtil.getBean("productManager");
				List<ProductVo> recentHistorys = productManager.getRecentHistoryProducts(productIds);
				if(recentHistorys!=null && recentHistorys.size()>0){
					for(ProductVo p : recentHistorys){
						IDictManager dictManager = (IDictManager)AppContextUtil.getBean("dictManager");
						CategoryBean cat = new CategoryBean();
						cat = dictManager.getCategory(p.getProduct().getCatId());
						List<CategoryBean> list = new ArrayList<CategoryBean>();
						list.add(cat);
						list = dictManager.changeCategoryNameString(list);
						cat = list.get(0);
						p.setCategory(cat);
					}
					context.put("recentHistorys", recentHistorys);
				}
				
			}
		}
	}

}
