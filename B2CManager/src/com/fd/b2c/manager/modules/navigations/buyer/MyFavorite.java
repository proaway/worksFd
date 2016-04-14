package com.fd.b2c.manager.modules.navigations.buyer;

import java.util.ArrayList;
import java.util.List;

import org.apache.turbine.modules.navigations.VelocityNavigation;
import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.fashion.dict.bean.CategoryBean;
import com.fd.fashion.dict.manager.IDictManager;
import com.fd.fashion.order.bean.ShoppingCartBean;
import com.fd.fashion.product.bean.ProductBean;
import com.fd.fashion.product.manager.IProductManager;
import com.fd.fashion.product.vo.ProductVo;
import com.fd.util.AppContextUtil;
import com.fd.util.PageInfo;

public class MyFavorite extends VelocityNavigation {

	protected void doBuildTemplate(RunData data, Context context)
			throws Exception {
		data.setCharSet("UTF-8");
		
		Long buyerId = (Long)context.get("buyerId");
		
		/**分页信息*/
		PageInfo pageInfo = new PageInfo();
		/*int nowPage = data.getParameters().getInt("pageIndex", 1);
		pageInfo.setPageIndex(nowPage);*/
		pageInfo.setPageIndex(1);
		pageInfo.setPageSize(10);
		context.put("pageInfo", pageInfo);
		IDictManager dictManager = (IDictManager)AppContextUtil.getBean("dictManager");
		IProductManager productManager = (IProductManager)AppContextUtil.getBean("productManager");
		List<ProductVo> favList = productManager.getBuyerFavorite(buyerId,pageInfo);
		if(favList!=null && favList.size()>0){
			for(ProductVo p : favList){
				CategoryBean cat = new CategoryBean();
				cat = dictManager.getCategory(p.getProduct().getCatId());
				List<CategoryBean> list = new ArrayList<CategoryBean>();
				list.add(cat);
				list = dictManager.changeCategoryNameString(list);
				cat = list.get(0);
				p.setCategory(cat);
			}
			context.put("favoriteList", favList);
			context.put("favoriteCount", pageInfo.getRecordCount());
		}
		
		
	}

}
