package com.fd.fashion.modules.navigations.product;

import java.util.List;

import org.apache.turbine.modules.navigations.VelocityNavigation;
import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.fashion.product.bean.ImageBean;
import com.fd.fashion.product.manager.IProductManager;
import com.fd.fashion.product.vo.ProductVo;
import com.fd.util.AppContextUtil;
import com.fd.util.RewriteUtil;

/**
 * @CreateDate:  2013-3-13 下午08:17:27 
 * @author:  ZhouRongyu
 * @Description:  产品详细页图片模块
 * 
 * @version:  V1.0
 */
public class Product_TP extends VelocityNavigation{
	
	protected void doBuildTemplate(RunData data, Context context) throws Exception {
		data.setCharSet("UTF-8");
		
		ProductVo p = (ProductVo)context.get("productVo");
		if(null== p){
			return;
		}
		IProductManager productManager = (IProductManager)AppContextUtil.getBean("productManager");
		List<ImageBean> imgList = productManager.getImageBeans(p.getProduct().getProductId());
		if(imgList!=null && imgList.size()>0){
			context.put("imgList", imgList);
		}
		String url = RewriteUtil.getProductUrl(p.getProduct().getProductName(), p.getProduct().getProductId());
		context.put("newProductUrl", url);
	}
}
