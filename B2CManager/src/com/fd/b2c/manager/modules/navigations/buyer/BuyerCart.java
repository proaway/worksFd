package com.fd.b2c.manager.modules.navigations.buyer;

import java.util.ArrayList;
import java.util.List;

import org.apache.turbine.modules.navigations.VelocityNavigation;
import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.fashion.dict.bean.CategoryBean;
import com.fd.fashion.dict.manager.IDictManager;
import com.fd.fashion.order.bean.ShoppingCartBean;
import com.fd.fashion.order.manager.IOrderManager;
import com.fd.fashion.product.bean.ProductBean;
import com.fd.fashion.product.manager.IProductManager;
import com.fd.session.FdSession;
import com.fd.session.FdSessionFactory;
import com.fd.util.AppContextUtil;
import com.fd.util.PageInfo;

public class BuyerCart extends VelocityNavigation {

	protected void doBuildTemplate(RunData data, Context context)
			throws Exception {
		data.setCharSet("UTF-8");
		FdSession session = FdSessionFactory.getFdSession(data);
		Long buyerId = (Long)context.get("buyerId");
		IOrderManager orderManager = (IOrderManager)AppContextUtil.getBean("orderManager");
		IProductManager productManager = (IProductManager)AppContextUtil.getBean("productManager");
		IDictManager dictManager = (IDictManager)AppContextUtil.getBean("dictManager");
		ShoppingCartBean shoppingCart = new ShoppingCartBean();
		shoppingCart.setBuyerId(buyerId);
		
		/**分页信息*/
		PageInfo pageInfo = new PageInfo();
		int nowPage = data.getParameters().getInt("pageIndex", 1);
		pageInfo.setPageIndex(nowPage);
		pageInfo.setPageSize(5);
		context.put("pageInfo", pageInfo);
		
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
			context.put("cartCount", pageInfo.getRecordCount());
		}
	}

}
