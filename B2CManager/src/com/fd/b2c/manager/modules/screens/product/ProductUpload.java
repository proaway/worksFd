/**
 * ProductUpload.java
 * @author:  ZhouRongyu
 */
package com.fd.b2c.manager.modules.screens.product;

import java.io.File;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.b2c.manager.modules.screens.SecureScreen;
import com.fd.fashion.dict.bean.PackageUnitBean;
import com.fd.fashion.dict.manager.IDictManager;
import com.fd.fashion.product.bean.ImageBean;
import com.fd.fashion.product.bean.ProductAttrBean;
import com.fd.fashion.product.manager.IProductManager;
import com.fd.fashion.product.vo.PriceVo;
import com.fd.fashion.product.vo.ProductConfigVo;
import com.fd.fashion.product.vo.ProductConfigsVo;
import com.fd.fashion.product.vo.ProductStandardVo;
import com.fd.fashion.product.vo.ProductVo;
import com.fd.fashion.seller.bean.SizeTemplateBean;
import com.fd.util.AppContextUtil;
import com.fd.util.FileUtil;
import com.fd.util.JSONUtil;
import com.fd.util.RewriteUtil;
import com.fd.util.StringUtil;

/**
 * @CreateDate:  2013-3-21 上午11:30:40 
 * @author:  ZhouRongyu
 * @Description:  TODO
 * 
 * @version:  V1.0
 */
public class ProductUpload extends SecureScreen{
	public void doBuildTemplate(RunData data, Context context) throws Exception {
		super.doBuildTemplate(data, context);
		data.setLayoutTemplate("/ProductUploadLayout.html");
		
		boolean addSame = data.getParameters().getBoolean("addSame", false);

//		WebPropUtil propUtil = new WebPropUtil();
		//long sellerId = Long.valueOf(propUtil.get("seller.id"));
		//获取包装单位
		IDictManager dictManager = (IDictManager)AppContextUtil.getBean("dictManager");
		List<PackageUnitBean> packageUnits = dictManager.getPackageUnitBeans(null);
		if(packageUnits != null){
			context.put("packageUnits", packageUnits);
		}
		
		//获取尺码模板
		IProductManager productManager = (IProductManager)AppContextUtil.getBean("productManager");
		List<SizeTemplateBean> sizeTemplates = productManager.getSizeTemplateBeans(null);
		if(sizeTemplates != null){
			context.put("sizeTemplates", sizeTemplates);
		}
		
		//获取用户自定义运费模板
//		IShippingManager shippingManager = (IShippingManager) getBean("shippingManager");
//		List<ShippingInfoBean> shippingInfos = shippingManager.getShippingInfoBeans();
//		if(shippingInfos != null){
//			context.put("shippingInfos",shippingInfos);
//		}
		
		long productId = data.getParameters().getLong("productId", 0);
		if (productId > 0) {
			ProductVo product = productManager.getProductVo(productId);
			PriceVo price = productManager.getPriceVo(productId);
			product.setPriceVo(price);
			ProductConfigsVo configs = productManager.getProductConfigsVo(productId, product.getProduct().getCatId());
			if (configs != null) {
				if (configs.getMainShow() != null && configs.getMainShow().getConfigs() != null) {
					for (ProductConfigVo config : configs.getMainShow().getConfigs()) {
						long productConfigId = config.getProductConfigBean().getProductConfigId();
						List<ImageBean> images = productManager.getConfigImgs(productConfigId);
						if (images != null) {
							for (ImageBean image : images) {
								image.setImageUrl(RewriteUtil.getImageUrl(image.getImageUrl(), "s"));
							}
						}
						config.setImages(images);
					}
				}
				if (configs.getStandards() != null) {
					for (ProductStandardVo standard : configs.getStandards()) {
						String subSku = standard.getProductStandardBean().getSku();
						if (StringUtils.isNotEmpty(subSku)) {
							subSku = subSku.replace(product.getProduct().getSku()+"-", "");
							standard.getProductStandardBean().setSku(subSku);
						}
					}
				}
				context.put("productConfig", JSONUtil.obj2JSON(configs));
			}
			List<ProductAttrBean> productAttrs = productManager.getProductAttrs(productId);
			context.put("productAttrs", JSONUtil.list2JSON(productAttrs));
			List<ImageBean> images = productManager.getImageBeans(productId);
			if (images != null) {
				for (ImageBean image : images) {
					image.setImageUrl(RewriteUtil.getImageUrl(image.getImageUrl(), "m"));
				}
				product.setImages(images);
			}
			String spec = product.getProduct().getSpecifications();
			if (new File(spec).exists()) {
				spec = FileUtil.readFile(spec);
				context.put("spec", spec);
			}
			if (addSame) {
				product.getProduct().setProductId(null);
			}
			context.put("product", product);
			context.put("saleIgnoreStock", product.getProduct().getSaleIgnoreStock());
		} else {
			context.put("saleIgnoreStock", true);
		}
		
		StringUtil su = new StringUtil();
		context.put("su", su);
	}
}
