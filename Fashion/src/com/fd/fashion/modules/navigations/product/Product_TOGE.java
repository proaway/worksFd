package com.fd.fashion.modules.navigations.product;

import org.apache.turbine.modules.navigations.VelocityNavigation;
import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.fashion.product.bean.GroupsBean;
import com.fd.fashion.product.manager.IProductManager;
import com.fd.fashion.product.vo.GroupVo;
import com.fd.util.AppContextUtil;

/**
 * @CreateDate:  2013-3-13 下午08:17:27 
 * @author:  ZhouRongyu
 * @Description:  产品详细页组合销售模块
 * 
 * @version:  V1.0
 */
public class Product_TOGE extends VelocityNavigation {
	
	protected void doBuildTemplate(RunData data, Context context) throws Exception {
		long productId = data.getParameters().getLong("productId", 0);
		
		IProductManager productManager = (IProductManager) AppContextUtil.getBean("productManager");
		GroupVo productGroup = productManager.getProductGroup(productId);
		
		context.put("productGroup", productGroup);
	}

}
