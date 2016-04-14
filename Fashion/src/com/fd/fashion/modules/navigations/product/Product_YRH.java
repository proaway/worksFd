package com.fd.fashion.modules.navigations.product;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.turbine.modules.navigations.VelocityNavigation;
import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.fashion.product.manager.IProductManager;
import com.fd.fashion.product.vo.ProductVo;
import com.fd.util.AppContextUtil;
import com.fd.util.StringUtil;

/**
 * @CreateDate:  2013-3-13 下午08:17:27 
 * @author:  ZhouRongyu
 * @Description:  产品详细页最近浏览产品模块，读取cookie中最近浏览的产品，并返回图片价格名称等信息用于显示
 * 
 * @version:  V1.0
 */
public class Product_YRH extends VelocityNavigation{
	
	protected void doBuildTemplate(RunData data, Context context) throws Exception {
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
						if (productIds.size() == 6) {
							break;
						}
					}
				}
				IProductManager productManager = (IProductManager) AppContextUtil.getBean("productManager");
				List<ProductVo> recentHistorys = productManager.getRecentHistoryProducts(productIds);
				context.put("recentHistorys", recentHistorys);
			}
		}
	}

}
