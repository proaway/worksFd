/**
 * ProductUploadAction.java
 * @author:  ZhouRongyu
 */
package com.fd.b2c.manager.modules.actions.product;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.b2c.manager.modules.actions.SecureAction;
import com.fd.fashion.order.manager.IOrderManager;
import com.fd.fashion.product.bean.ImageBean;
import com.fd.fashion.product.bean.PriceBean;
import com.fd.fashion.product.bean.ProductAttrBean;
import com.fd.fashion.product.bean.ProductBean;
import com.fd.fashion.product.bean.ProductConfImgBean;
import com.fd.fashion.product.bean.ProductConfigBean;
import com.fd.fashion.product.bean.ProductImageBean;
import com.fd.fashion.product.bean.ProductStandardBean;
import com.fd.fashion.product.manager.IProductManager;
import com.fd.fashion.product.manager.IProductUploadManager;
import com.fd.fashion.product.vo.ProductUploadVo;
import com.fd.util.AppContextUtil;
import com.fd.util.ImageUtil;
import com.fd.util.ParametersUtil;

/**
 * @CreateDate:  2013-3-26 下午8:12:26 
 * @author:  ZhouRongyu
 * @Description:  TODO
 * 
 * @version:  V1.0
 */
public class ProductUploadAction extends SecureAction {
	public void doPerform(RunData data, Context context) throws Exception {
		
		IProductManager productManager = (IProductManager)AppContextUtil.getBean("productManager");
		IProductUploadManager uploadProductManager = (IProductUploadManager)AppContextUtil.getBean("productUploadManager");
		
		ProductUploadVo product = new ProductUploadVo();
		
		//产品基础信息
		ProductBean pb = new ProductBean();
		ParametersUtil.initParameters(data, pb);
		boolean saleIgnoreStock = data.getParameters().getBoolean("saleIgnoreStock", false);
		pb.setSaleIgnoreStock(saleIgnoreStock);
		
		Date now = new Date();
		if (pb.getProductId() == null) {
			pb.setCreateTime(now);
		}
		pb.setUpdateTime(now);
		pb.setEditTime(now);
		
//		String shippingType = data.getParameters().getString("shippingType");
//		if("0".equals(shippingType)){
//			pb.setShippingTemplateId(Long.valueOf(0));
//		}else{
//			shippingType = data.getParameters().get("shippingSelect");
//		}
		String rd_mode = data.getParameters().getString("rd_mode");
		if("1".equals(rd_mode)){
			int quantity = data.getParameters().getInt("quantity");
			pb.setQuantity(quantity);
			pb.setIspackage(1);
		}else{
			pb.setQuantity(1);
		}
		product.setProductBean(pb);
		
		//图片信息
		List<ProductImageBean> images = new ArrayList<ProductImageBean>();
		String[] imgs = data.getParameters().getStrings("p_img");
		if(imgs != null && imgs.length > 0){
			for(int i=0;i<imgs.length;i++){
				String imageId = imgs[i];
				ProductImageBean pib = new ProductImageBean();
				pib.setImageId(Long.valueOf(imageId));
				pib.setIndexId(i);
				images.add(pib);
				
				// 查找图片，切图
				ImageBean image = new ImageBean();
				image.setImageId(Long.valueOf(imageId));
				image = productManager.getImageBean(image);
				ImageUtil.cutProductImage(image.getImageUrl());
			}
		}
		product.setImages(images);
		
		//产品Attr属性
		int attrCount = data.getParameters().getInt("attrCount");
		List<ProductAttrBean> attrs = new ArrayList<ProductAttrBean>();
		for(int m = 1;m<=attrCount;m++){
			String titleAttrId = data.getParameters().getString("attrTitle"+m);
			String[] attrValueIds = data.getParameters().getStrings("attrId"+m);
			if(attrValueIds != null && attrValueIds.length >0){
				for(int k = 0;k<attrValueIds.length;k++){
					String valueAttrId = attrValueIds[k];
					if(StringUtils.isNotEmpty(valueAttrId)){
						ProductAttrBean pab = new ProductAttrBean();
						pab.setTitleAttrId(Long.valueOf(titleAttrId));
						pab.setValueAttrId(Long.valueOf(valueAttrId));
						pab.setProductAttrId(null);
						attrs.add(pab);
					}
				}
			}else{
				String attrValue = data.getParameters().getString("attrValue"+m);
				if (StringUtils.isNotEmpty(attrValue)) {
					ProductAttrBean pab = new ProductAttrBean();
					pab.setTitleAttrId(Long.valueOf(titleAttrId));
					pab.setValue(attrValue);
					attrs.add(pab);
				}
			}
		}
		product.setAttrs(attrs);

		//产品规格属性
		String[] mainConfig = data.getParameters().getStrings("mainConfig");
		String[] subConfig = data.getParameters().getStrings("subConfig");
		//添加ProductConfig表主属性
		List<ProductConfigBean> mainProductConfigs = new ArrayList<ProductConfigBean>();
		if(mainConfig != null && mainConfig.length>0){
			for(int x=0;x<mainConfig.length;x++){
				String titleAttrId = data.getParameters().getString("mainConfigTitle");
				ProductConfigBean pcb = new ProductConfigBean();
				pcb.setTitleAttrId(Long.valueOf(titleAttrId));
				String valueAttrId = mainConfig[x];
				pcb.setValueAttrId(Long.valueOf(valueAttrId));
				
				//规格对应图片
				String[] configImgs = data.getParameters().getStrings("configImg_"+valueAttrId);
				List<ProductConfImgBean> configImages = new ArrayList<ProductConfImgBean>();
				if(configImgs != null && configImgs.length > 0){
					for(int c = 0;c < configImgs.length; c++){
						ProductConfImgBean pcib = new ProductConfImgBean();
						String imgId = configImgs[c];
						if(imgId != null){
							pcib.setImageId(Long.valueOf(imgId));
						}
						pcib.setIndexId(c);
						configImages.add(pcib);
					}
				}
				pcb.setImages(configImages);
				
				String attrName = data.getParameters().getString("attrName_"+valueAttrId);
				if(attrName != null){
					pcb.setAttrName(attrName);
				}
				mainProductConfigs.add(pcb);
			}
		}
		product.setMainConfigs(mainProductConfigs);
		
		//添加ProductConfig表从属性
		List<ProductConfigBean> subProductConfigs = new ArrayList<ProductConfigBean>();
		if(subConfig != null && subConfig.length > 0){
			for(int y=0;y<subConfig.length;y++){
				String titleAttrId = data.getParameters().getString("subConfigTitle");
				ProductConfigBean subPcb = new ProductConfigBean();
				subPcb.setTitleAttrId(Long.valueOf(titleAttrId));
				String valueAttrId = subConfig[y];
				subPcb.setValueAttrId(Long.valueOf(valueAttrId));
				subProductConfigs.add(subPcb);
			}
		}
		product.setSubConfigs(subProductConfigs);
		
		//产品ProductStandard表
		List<ProductStandardBean> standards = new ArrayList<ProductStandardBean>();
		if(mainProductConfigs != null && mainProductConfigs.size() > 0 ){
			for(int xx=0;xx<mainProductConfigs.size();xx++){
				ProductConfigBean mainPcb = mainProductConfigs.get(xx);
				Long valueAttrId = mainPcb.getValueAttrId();
				//主从属性都具备
				if( subProductConfigs != null && subProductConfigs.size() > 0){
					for(int yy=0;yy<subProductConfigs.size();yy++){
						ProductConfigBean subPcb = subProductConfigs.get(yy);
						Long subValueAttrId = subPcb.getValueAttrId();
						String price = data.getParameters().getString("price_"+valueAttrId+"_"+subValueAttrId);
						int stock = data.getParameters().getInt("stockNum_"+valueAttrId+"_"+subValueAttrId);
						String sku = pb.getSku() + "-" + data.getParameters().getString("sku_"+valueAttrId+"_"+subValueAttrId);
						String discount = data.getParameters().getString("discount_"+valueAttrId+"_"+subValueAttrId);
						String wholesale = data.getParameters().getString("wholesale_"+valueAttrId+"_"+subValueAttrId);
						ProductStandardBean psb = new ProductStandardBean();
						if (StringUtils.isNotEmpty(price)) {
							psb.setPrice(Double.valueOf(price));
						}
						psb.setSku(sku);
						psb.setStock(stock);
						if("1".equals(discount)){
							psb.setDiscount(true);
						}
						if("1".equals(wholesale)){
							psb.setWholesale(true);
						}
						standards.add(psb);
					}
				}else{
					//只有主属性
					String price = data.getParameters().getString("price_"+valueAttrId+"_");
					int stock = data.getParameters().getInt("stockNum_"+valueAttrId+"_");
					String sku = pb.getSku() + "-" + data.getParameters().getString("sku_"+valueAttrId+"_");
					String discount = data.getParameters().getString("discount_"+valueAttrId+"_");
					String wholesale = data.getParameters().getString("wholesale_"+valueAttrId+"_");
					ProductStandardBean psb = new ProductStandardBean();
					psb.setPrice(Double.valueOf(price));
					psb.setSku(sku);
					psb.setStock(stock);
					if("1".equals(discount)){
						psb.setDiscount(true);
					}
					if("1".equals(wholesale)){
						psb.setWholesale(true);
					}
					standards.add(psb);
				}
			}
		}else if(subProductConfigs != null && subProductConfigs.size() > 0){
			for(int yy=0;yy<subProductConfigs.size();yy++){
				ProductConfigBean subPcb = mainProductConfigs.get(yy);
				Long subValueAttrId = subPcb.getValueAttrId();
				String price = data.getParameters().getString("price__"+subValueAttrId);
				int stock = data.getParameters().getInt("stockNum__"+subValueAttrId);
				String sku = data.getParameters().getString("sku__"+subValueAttrId);
				String discount = data.getParameters().getString("discount__"+subValueAttrId);
				String wholesale = data.getParameters().getString("wholesale__"+subValueAttrId);
				ProductStandardBean psb = new ProductStandardBean();
				psb.setPrice(Double.valueOf(price));
				psb.setSku(sku);
				psb.setStock(stock);
				if("1".equals(discount)){
					psb.setDiscount(true);
				}
				if("1".equals(wholesale)){
					psb.setWholesale(true);
				}
				standards.add(psb);
			}
		}
		product.setStandards(standards);
		
		//产品批发折扣价格
		PriceBean priceBean = new PriceBean();
		String ifDiscount = data.getParameters().getString("ifDiscount");
		Double pPrice = data.getParameters().getDoubleObject("price");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if(ifDiscount != null && "1".endsWith(ifDiscount)){
			String discountStartDate = data.getParameters().getString("discountStartDate");
			String discountEndDate = data.getParameters().getString("discountEndDate");
			String discount = data.getParameters().getString("discount");
			priceBean.setDiscount(Double.valueOf(discount));
			priceBean.setDiscountStartDate(sdf.parse(discountStartDate));
			priceBean.setDiscountEndDate(sdf.parse(discountEndDate));
		}
		String ifWholesale = data.getParameters().getString("ifWholesale"); 
		if(ifWholesale != null && "1".endsWith(ifWholesale)){
			int wholesaleCount = data.getParameters().getInt("wholesaleCount"); 
			String wholesaleDiscount = data.getParameters().getString("wholesaleDiscount"); 
			priceBean.setWholesaleCount(wholesaleCount);
			priceBean.setWholesaleDiscount(Double.valueOf(wholesaleDiscount));
		}

		if (standards.size()>0) {
			double p = 0;
			for (ProductStandardBean standard : standards) {
				if (standard.getPrice() != null) {
					if (p == 0 || p > standard.getPrice()) {
						p = standard.getPrice();
					}
				}
			}
			pPrice = p;
		}
		
		priceBean.setPrice(pPrice);
		priceBean.setProductId(pb.getProductId());
		product.setPrice(priceBean);
		// 提交保存
		long productId = data.getParameters().getLong("productId", 0);
		if (productId > 0) { // 更新
			// 判断订单产品，生成快照
			IOrderManager orderManager = (IOrderManager) getBean("orderManager");
			orderManager.makeOrderProdSnapshot(productId);
			// 更新产品
			ProductBean p = productManager.getProductBean(productId);
			uploadProductManager.updateProductInfo(product, p);
		} else { // 新增
			uploadProductManager.addProductInfo(product);
		}
		
		context.put("product", product);
		this.setTemplate(data, "product,Product_upload_success.html");
		//data.getResponse().sendRedirect("product,Product_upload_success.html");
		
		//图片规格属性
		
		/*//库存信息
		GiftsBean gift = new GiftsBean();
		//ParametersUtil.initParameters(data, gift);
		String startTime = data.getParameters().getString("promotion_startTime");
		String endTime = data.getParameters().getString("promotion_endTime");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		gift.setStartTime(sdf.parse(startTime));
		gift.setEndTime(sdf.parse(endTime));
		String[] giftProductIds = data.getParameters().getStrings("giftProductId");
		String[] groupProdIds = data.getParameters().getStrings("groupProdId");
		if(giftProductIds.length > 0 && giftProductIds.length < 2){
			gift.setActivitieProductId1(Long.valueOf(giftProductIds[0]));
		}else if(giftProductIds.length == 2){
			gift.setActivitieProductId2(Long.valueOf(giftProductIds[1]));
		}else if(giftProductIds.length == 3){
			gift.setActivitieProductId3(Long.valueOf(giftProductIds[2]));
		}else if(giftProductIds.length == 4){
			gift.setActivitieProductId4(Long.valueOf(giftProductIds[3]));
		}else if(giftProductIds.length == 5){
			gift.setActivitieProductId5(Long.valueOf(giftProductIds[4]));
		}*/
	}
}
