/**
 * ProductBatchUpload.java
 * @author:  ZhouRongyu
 */
package com.fd.b2c.manager.modules.screens.product;

import java.util.List;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.b2c.manager.modules.screens.SecureScreen;
import com.fd.fashion.product.bean.ImageBean;
import com.fd.fashion.product.bean.ProductBean;
import com.fd.fashion.product.manager.IProductManager;
import com.fd.fashion.product.vo.ProductVo;
import com.fd.fashion.product.vo.SearchProductVo;
import com.fd.util.PageInfo;
import com.fd.util.ParametersUtil;

/**
 * @CreateDate:  2013-4-26 下午12:11:31 
 * @author:  ZhouRongyu
 * @Description:  TODO
 * 
 * @version:  V1.0
 */
public class ProductBatchUpload extends SecureScreen{
	public void doBuildTemplate(RunData data, Context context) throws Exception {
		super.doBuildTemplate(data, context);
		data.setLayoutTemplate("/Layout.html");
		//IProductManager productManager = (IProductManager)AppContextUtil.getBean("productManager");
		
		data.getRequest().setCharacterEncoding("UTF-8");
	    ProductBean product = new ProductBean();
	    product.setProductStatus(3);
		IProductManager productManager = (IProductManager)this.getBean("productManager");
		
		//**********设置PageInfo分页信息
		int pageSize = 20;		
		String pageIndexStr = data.getParameters().get("pageIndex");
		int pageIndex = 1;
		try{
		       pageIndex = Integer.parseInt(pageIndexStr);
		}catch(Exception e){
		       pageIndex = 1;
		}	       
		PageInfo pageInfo = new PageInfo();
		pageInfo.setPageIndex(pageIndex);
		pageInfo.setPageSize(pageSize);
				
		SearchProductVo searchProductVo = new SearchProductVo();
		ParametersUtil.initParameters(data, searchProductVo);
		searchProductVo.setProductStatus(3);
		searchProductVo = productManager.getProductByKey(searchProductVo, pageInfo);
		
		List<ProductVo> products = searchProductVo.getProductList();
		for(int i=0;i<products.size();i++){
			ProductVo pv = products.get(i);
			List<ImageBean> images = productManager.getImageBeans(pv.getProduct().getProductId());
			pv.setImages(images);
		}
		
		context.put("searchProductVo", searchProductVo);
		context.put("pageInfo", pageInfo);
	}

}
