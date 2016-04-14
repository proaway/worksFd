/**
 * EditActivity.java
 * @author:  Zhou Rongyu
 */
package com.fd.b2c.manager.modules.screens.marketing;

import java.util.ArrayList;
import java.util.List;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.b2c.manager.modules.screens.SecureScreen;
import com.fd.fashion.product.bean.ActivityBean;
import com.fd.fashion.product.bean.GiftsBean;
import com.fd.fashion.product.bean.GroupsBean;
import com.fd.fashion.product.bean.ImageBean;
import com.fd.fashion.product.manager.IProductManager;
import com.fd.fashion.product.vo.ProductVo;

/**
 * @CreateDate:  2013-5-23 下午7:54:37 
 * @author:  Zhou Rongyu
 * @Description:  TODO
 * 
 * @version:  V1.0
 */
public class ViewActivity extends SecureScreen {
	@Override
	public void doBuildTemplate(RunData data, Context context) throws Exception {
		super.doBuildTemplate(data, context);
		data.setLayoutTemplate("/NoLayout.html");
		String activityId = data.getParameters().getString("activityId");
		IProductManager productManager = (IProductManager) getBean("productManager");
		ActivityBean ab = new ActivityBean();
		ab.setActivityId(activityId);
		List<ActivityBean>  activityList = productManager.getActivityList(ab, null);
		ab =  activityList.get(0);
		context.put("activity",ab);
		ProductVo product = productManager.getProductVo(ab.getProductId());
		if (product != null) {
			ImageBean image = productManager.getFirstProdImageBean(product.getProduct().getProductId());
			product.setFirstImage(image);
		}
		context.put("product", product);
		if(ab.getType() == 0){
			GroupsBean group = productManager.getGroupsBean(ab.getActivityId());
			List<ProductVo> groupProduct = new ArrayList<ProductVo>();
			if(group.getGroupProductId1()!= null){
				ProductVo productVo = productManager.getProductVo(group.getGroupProductId1());
				if (productVo != null) {
					ImageBean image = productManager.getFirstProdImageBean(productVo.getProduct().getProductId());
					productVo.setFirstImage(image);
				}
				groupProduct.add(productVo);
			}
			if(group.getGroupProductId2()!= null){
				ProductVo productVo = productManager.getProductVo(group.getGroupProductId2());
				if (productVo != null) {
					ImageBean image = productManager.getFirstProdImageBean(productVo.getProduct().getProductId());
					productVo.setFirstImage(image);
				}
				groupProduct.add(productVo);
			}
			if(group.getGroupProductId3()!= null){
				ProductVo productVo = productManager.getProductVo(group.getGroupProductId3());
				if (productVo != null) {
					ImageBean image = productManager.getFirstProdImageBean(productVo.getProduct().getProductId());
					productVo.setFirstImage(image);
				}
				groupProduct.add(productVo);
			}
			if(group.getGroupProductId4()!= null){
				ProductVo productVo = productManager.getProductVo(group.getGroupProductId4());
				if (productVo != null) {
					ImageBean image = productManager.getFirstProdImageBean(productVo.getProduct().getProductId());
					productVo.setFirstImage(image);
				}
				groupProduct.add(productVo);
			}
			context.put("group", group);
			context.put("groupProductList", groupProduct);
			
		}else if(ab.getType() == 1){
			List<GiftsBean> gifts = productManager.getGiftsList(ab.getActivityId());
			for(int i=0;i<gifts.size();i++){
				GiftsBean gb = gifts.get(i);
				Long productId = gb.getProductId();
				ProductVo productVo = productManager.getProductVo(productId);
				if (productVo != null) {
					ImageBean image = productManager.getFirstProdImageBean(productVo.getProduct().getProductId());
					productVo.setFirstImage(image);
				}
				gb.setProductVo(productVo);
			}
			context.put("gifts",gifts);
		}
	}

}
