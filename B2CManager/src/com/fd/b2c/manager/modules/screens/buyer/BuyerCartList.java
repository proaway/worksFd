package com.fd.b2c.manager.modules.screens.buyer;

import java.util.ArrayList;
import java.util.List;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.b2c.manager.modules.screens.SecureScreen;
import com.fd.fashion.dict.bean.CategoryBean;
import com.fd.fashion.dict.manager.IDictManager;
import com.fd.fashion.order.bean.ShoppingCartBean;
import com.fd.fashion.order.manager.IOrderManager;
import com.fd.fashion.product.bean.ProductBean;
import com.fd.fashion.product.manager.IProductManager;
import com.fd.util.AppContextUtil;
import com.fd.util.PageInfo;

public class BuyerCartList extends SecureScreen{
	public void doBuildTemplate(RunData data, Context context) throws Exception {
		super.doBuildTemplate(data, context);
		data.setLayoutTemplate("/Layout.html");
		
		Long buyerId = data.getParameters().getLongObject("buyerId");
		context.put("buyerId", buyerId);
		
		/**分页信息*/
		PageInfo pageInfo = new PageInfo();
		int nowPage = data.getParameters().getInt("pageIndex", 1);
		pageInfo.setPageIndex(nowPage);
		pageInfo.setPageSize(10);
		context.put("pageInfo", pageInfo);
		
		IOrderManager orderManager = (IOrderManager)AppContextUtil.getBean("orderManager");
		IProductManager productManager = (IProductManager)AppContextUtil.getBean("productManager");
		IDictManager dictManager = (IDictManager)AppContextUtil.getBean("dictManager");
		ShoppingCartBean shoppingCart = new ShoppingCartBean();
		shoppingCart.setBuyerId(buyerId);
		List<ShoppingCartBean> cartLists= orderManager.getShoppingCartBeans(shoppingCart,pageInfo);
		if(cartLists!=null && cartLists.size()>0){
			for(ShoppingCartBean sc : cartLists){
				ProductBean p = new ProductBean();
				p = productManager.getProductBean(sc.getProductId());
				CategoryBean cat = new CategoryBean();
				cat = dictManager.getCategory(p.getCatId());
				List<CategoryBean> list = new ArrayList<CategoryBean>();
				list.add(cat);
				list = dictManager.changeCategoryNameString(list);
				cat = list.get(0);
				sc.setCatName(cat.getCatNameCn());
			}
			context.put("cartList", cartLists);
		}
	}

}
