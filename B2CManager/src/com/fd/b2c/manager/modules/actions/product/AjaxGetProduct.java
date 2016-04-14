package com.fd.b2c.manager.modules.actions.product;

import java.util.List;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.b2c.manager.modules.actions.SecureAction;
import com.fd.fashion.product.bean.ActivityBean;
import com.fd.fashion.product.bean.ImageBean;
import com.fd.fashion.product.bean.ProductBean;
import com.fd.fashion.product.manager.IProductManager;
import com.fd.fashion.product.vo.ProductVo;
import com.fd.util.AppContextUtil;
import com.fd.util.JSONUtil;
import com.fd.util.ParametersUtil;

/**
 * @CreateDate: 2013-5-22 下午02:01:27
 * @author Longli
 * @Description: Ajax获取产品方法，根据传入参数获取ProductBean对象，没有则返回""
 * 
 */
public class AjaxGetProduct extends SecureAction {
	@Override
	public void doPerform(RunData data, Context context) throws Exception {
		super.doPerform(data, context);
		
		ProductBean product = new ProductBean();
		ParametersUtil.initParameters(data, product);
		IProductManager productManager = (IProductManager)AppContextUtil.getBean("productManager");
		product = productManager.getProductBean(product);
		if (product != null) {
			int type = data.getParameters().getInt("type");
			ActivityBean activity = new ActivityBean();
			activity.setProductId(product.getProductId());
			activity.setType(type);
			List<ActivityBean> activityList = productManager.getActivityList(activity, null);
			int productType = data.getParameters().getInt("productType");
			if(activityList != null && activityList.size() != 0 && productType == 0){
				activity = activityList.get(0);
				context.put("contentdata", 1);
			}else{
				ProductVo productVo = new ProductVo();
				productVo.setProduct(product);
				ImageBean image = productManager.getFirstProdImageBean(product.getProductId());
				productVo.setFirstImage(image);
				
				context.put("contentdata", JSONUtil.obj2JSON(productVo));
			}
		}else{
			context.put("contentdata", 0);
		}
		

		
	}
}
