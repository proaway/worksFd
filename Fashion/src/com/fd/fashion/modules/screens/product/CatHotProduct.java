/**
 * CatHotProduct.java
 * @author:  ZhouRongyu
 */
package com.fd.fashion.modules.screens.product;

import java.util.ArrayList;
import java.util.List;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.fashion.modules.screens.BaseScreen;
import com.fd.fashion.product.bean.ProductBean;
import com.fd.fashion.product.manager.IProductManager;
import com.fd.fashion.product.vo.ProductVo;
import com.fd.util.AppContextUtil;
import com.fd.util.RewriteUtil;

/**
 * @CreateDate:  2013-3-15 上午10:32:00 
 * @author:  ZhouRongyu
 * @Description:  TODO
 * 
 * @version:  V1.0
 */
public class CatHotProduct extends BaseScreen{
	public void doBuildTemplate(RunData data, Context context) throws Exception {
		super.doBuildTemplate(data, context);
		data.setLayoutTemplate("/NoLayout.html");
		
		String catalogId = data.getParameters().getString("catId");
		long productId = data.getParameters().getLong("productId");
		IProductManager productManager = (IProductManager)AppContextUtil.getBean("productManager");
		//获取热销产品的数量
		//int amount = 6;)
		//List<ProductBean> products = productManager.getCatHotProducts(catalogId, amount);
		ProductBean pb = new ProductBean();
		pb.setCustomCatId(catalogId);
		pb.setProductId(productId);
		List<ProductVo> pvs = productManager.getHotProducts(pb);
		List<ProductVo> hotProducts = new ArrayList<ProductVo>();
		for(int i=0;i<pvs.size();i++){
			ProductVo pv = pvs.get(i);
			if(productId != pv.getProduct().getProductId()){
				if(hotProducts.size() <= 5){
					hotProducts.add(pv);
				}
			}
		}
		if(pvs.size()>0){
			context.put("hotproduct", hotProducts);
		}
	}
}
