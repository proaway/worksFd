package com.fd.b2c.manager.modules.screens.buyer;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.b2c.manager.modules.screens.SecureScreen;
import com.fd.fashion.dict.bean.CategoryBean;
import com.fd.fashion.dict.manager.IDictManager;
import com.fd.fashion.order.bean.OrderBean;
import com.fd.fashion.order.manager.IOrderManager;
import com.fd.fashion.product.bean.ProductBean;
import com.fd.fashion.product.manager.IProductManager;
import com.fd.fashion.product.vo.ProductVo;
import com.fd.util.AppContextUtil;
import com.fd.util.PageInfo;

public class BuyerProductList  extends SecureScreen{
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
		
		IDictManager dictManager = (IDictManager)AppContextUtil.getBean("dictManager");
		IProductManager productManager = (IProductManager)AppContextUtil.getBean("productManager");
		IOrderManager orderManager = (IOrderManager)AppContextUtil.getBean("orderManager");
		List<ProductVo> listPro = productManager.getBuyerBuyProducts(buyerId, pageInfo);
		for(ProductVo p : listPro){
			CategoryBean cat = new CategoryBean();
			cat = dictManager.getCategory(p.getProduct().getCatId());
			List<CategoryBean> list = new ArrayList<CategoryBean>();
			list.add(cat);
			list = dictManager.changeCategoryNameString(list);
			cat = list.get(0);
			p.setCategory(cat);
			HashMap<String,Object> map = orderManager.getOrderRecentBuyTimeByProductId(p.getProduct().getProductId(), buyerId);
			if(map!=null){
				ProductBean pro = p.getProduct();
				if(map.get("createDate")!=null){
					pro.setCreateTime((Date)map.get("createDate"));
				}
				if(map.get("quantity")!=null){
					pro.setQuantity((Integer)map.get("quantity"));
				}
				
				p.setProduct(pro);
			}
		}
		context.put("productList", listPro);
		
	}
}
