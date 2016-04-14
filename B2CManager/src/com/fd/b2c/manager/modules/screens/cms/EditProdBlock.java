package com.fd.b2c.manager.modules.screens.cms;

import java.util.List;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.b2c.manager.modules.screens.SecureScreen;
import com.fd.fashion.cms.bean.CmsBlockBean;
import com.fd.fashion.product.bean.ImageBean;
import com.fd.fashion.product.manager.IProductManager;
import com.fd.fashion.product.vo.PriceVo;
import com.fd.fashion.product.vo.ProductVo;
import com.fd.fashion.seller.bean.CustomCategoryBean;
import com.fd.fashion.seller.manager.ISellerManager;

/**
 * @CreateDate: 2013-5-22 下午01:46:13
 * @author Longli
 * @Description: 编辑产品cms栏目
 * 
 */
public class EditProdBlock extends SecureScreen {
	@Override
	public void doBuildTemplate(RunData data, Context context) throws Exception {
		super.doBuildTemplate(data, context);
		
		CmsBlockBean block = (CmsBlockBean) context.get("block");
		long productId = data.getParameters().getLong("productId");
		if (productId > 0) {
			
		} else if (block != null && block.getInfoId() != null) {
			productId = block.getInfoId();
		}
		if (productId > 0) {
			IProductManager productManager = (IProductManager) getBean("productManager");
			ProductVo product = productManager.getProductVo(productId);
			context.put("product", product);
			
			ISellerManager sellerManager = (ISellerManager) getBean("sellerManager");
			List<CustomCategoryBean> cats = sellerManager.getParentsCats(product.getProduct().getCustomCatId());
			String c = "";
			for (CustomCategoryBean cat : cats) {
				c += cat.getCatNameCn() + " >> ";
			}
			c = c.substring(0, c.length() - 3);
			context.put("cats", c);

			ImageBean image = productManager.getFirstProdImageBean(productId);
			product.setFirstImage(image);
			
			PriceVo price = productManager.getPriceVo(productId);
			context.put("price", price);
		}
	}	
}
