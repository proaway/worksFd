/**
 * ProductUploadAction.java
 * @author:  ZhouRongyu
 */
package com.fd.b2c.manager.modules.actions.product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.b2c.manager.modules.actions.SecureAction;
import com.fd.fashion.dict.bean.CategoryBean;
import com.fd.fashion.dict.manager.IDictManager;
import com.fd.fashion.product.bean.ImageBean;
import com.fd.fashion.product.bean.ProductBean;
import com.fd.fashion.product.manager.IProductManager;
import com.fd.fashion.product.vo.PriceVo;
import com.fd.fashion.seller.bean.CustomCategoryBean;
import com.fd.fashion.seller.manager.ISellerManager;
import com.fd.util.JSONUtil;

/**
 * @CreateDate:  2013-3-26 下午8:12:26 
 * @author:  ZhouRongyu
 * @Description:  TODO
 * 
 * @version:  V1.0
 */
public class AjaxBatchSubmitProduct extends SecureAction {
	@SuppressWarnings("unused")
	public void doPerform(RunData data, Context context) throws Exception {
		
		long productIds[] = data.getParameters().getLongs("productId");
		
		IProductManager productManager = (IProductManager) getBean("productManager");
		IDictManager dictManager = (IDictManager) getBean("dictManager");
		ISellerManager sellerManager = (ISellerManager) getBean("sellerManager");
		List<String> skus = new ArrayList<String>();
		for(int i=0;i<productIds.length;i++){
			long id = productIds[i];
			boolean flag = true;
			ProductBean pb = productManager.getProductBean(id);
			
			String catId = pb.getCatId();
        	String customCatId = pb.getCustomCatId();
        	CategoryBean cb = dictManager.getCategory(catId);
        	CustomCategoryBean ccb = sellerManager.getCustomCategory(customCatId);
        	if(cb == null || ccb == null){
        		 flag = false;
        	}
        	List<ImageBean> pib = productManager.getImageBeans(id);
        	if(null == pib || pib.size() == 0 ){
        		 flag = false;
        	}
			try{
	        	PriceVo priceBean = productManager.getPriceVo(id);
	        	double price = priceBean.getPrice();
	        	
	        	String sku = pb.getSku();
	        	String productName = pb.getProductName();
	        	//String price = productrow[4];
	        	String description = pb.getDescription();
	        	String specifications = pb.getSpecifications();
	        	//int stock = pb.getStock();
	        	double weight = pb.getWeight();
	        	double length = pb.getLength();
	        	double width = pb.getWidth();
	        	double height = pb.getHeight();
	        	String packageInformation = pb.getPackageInformation();
	        	
			}catch(Exception e){
				 flag = false;
			}
			HashMap<String, Object> map = new HashMap<String, Object>();
			if(flag == false){
				skus.add(pb.getSku());
			}else{
				pb.setProductStatus(1);
				pb.setImageUrl(pib.get(0).getImageUrl());
				productManager.updateProductBean(pb);
			}

			map.put("skus", JSONArray.fromObject(skus));
			context.put("contentdata", JSONUtil.obj2JSON(map));
		}
		

	}
}
