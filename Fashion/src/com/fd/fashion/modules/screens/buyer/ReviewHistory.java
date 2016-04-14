package com.fd.fashion.modules.screens.buyer;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.fashion.modules.screens.SecureScreen;
import com.fd.fashion.product.manager.IProductManager;
import com.fd.fashion.product.vo.ProductVo;
import com.fd.session.FdSession;
import com.fd.session.FdSessionFactory;
import com.fd.util.AppContextUtil;
import com.fd.util.StringUtil;

public class ReviewHistory extends SecureScreen {
	public void doBuildTemplate(RunData data, Context context) throws Exception {
		super.doBuildTemplate(data, context);
		data.setLayoutTemplate("/BuyerAccountLayout.html");
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
				context.put("recentHistorys", recentHistorys);
			}
		}
	}

}
