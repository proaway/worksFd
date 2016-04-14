/**
 * ProductVo.java
 * @author:  ZhouRongyu
 */
package com.fd.fashion.product.vo;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.fd.fashion.dict.bean.CategoryBean;
import com.fd.fashion.product.bean.ImageBean;
import com.fd.fashion.product.bean.PriceBean;
import com.fd.fashion.product.bean.ProductBean;
import com.fd.fashion.seller.bean.CustomCategoryBean;
import com.fd.util.DateValidUtil;
import com.fd.util.RewriteUtil;

/**
 * @CreateDate:  2013-3-18 下午7:12:38 
 * @author:  ZhouRongyu
 * @Description:  TODO
 * 
 * @version:  V1.0
 */
public class ProductVo {
	/** 产品属性 **/
	private ProductBean product;
	/** 产品分类属性 **/
	private CategoryBean category;
	/** 价格对象 */
	private PriceVo priceVo;
	/** 配置属性封装对象 */
	private ProductConfigsVo productConfigs;
	/** 剩余有效期天数 */
	private int expiredDays;
	/** 产品首图 */
	private ImageBean firstImage;
	/** 产品首图原图地址 */
	private String srcImageUrl;
	/** 产品首图大图地址 */
	private String bigImageUrl;
	/** 产品首图中图地址 */
	private String midImageUrl;
	/** 产品首图小图地址 */
	private String smallImageUrl;
	/** 产品URL */
	private String productUrl;
	/** 产品完整分类链 */
	private List<CustomCategoryBean> cats;
	/** 产品图片 */
	private List<ImageBean> images;
	/** 显示用的产品名称 */
	private String productName;
	
	/** 搜索产品位置 */
	private int position;
	
	
	
	/**
	 * @return the product
	 */
	public ProductBean getProduct() {
		return product;
	}
	/**
	 * @param product the product to set
	 */
	public void setProduct(ProductBean product) {
		this.product = product;
		if (product != null && product.getExpiredDay() != null) {
			long expiredDay = product.getExpiredDay();
			if (expiredDay > 0) {
				DateValidUtil dv = new DateValidUtil();
				long day = dv.getDaysToNow(product.getUpdateTime());
				long dayNum = expiredDay-day;
				expiredDays = (int)dayNum;
			}
			
			this.productUrl = RewriteUtil.getProductUrl(product.getProductName(), product.getProductId());
		}
	}
	/**
	 * @return the category
	 */
	public CategoryBean getCategory() {
		return category;
	}
	/**
	 * @param category the category to set
	 */
	public void setCategory(CategoryBean category) {
		this.category = category;
	}
	/**
	 * @return the priceVo
	 */
	public PriceVo getPriceVo() {
		return priceVo;
	}
	/**
	 * @param priceVo the priceVo to set
	 */
	public void setPriceVo(PriceVo priceVo) {
		this.priceVo = priceVo;
	}
	/**
	 * @param priceVo the priceVo to set
	 */
	public void setPriceVo(PriceBean priceBean) {
		if (priceBean != null) {
			priceVo = new PriceVo();
			priceVo.setPriceBean(priceBean);
		}
	}
	/**
	 * @return the expiredDays
	 */
	public int getExpiredDays() {
		return expiredDays;
	}
	/**
	 * @param expiredDays the expiredDays to set
	 */
	public void setExpiredDays(int expiredDays) {
		this.expiredDays = expiredDays;
	}
	/**
	 * @return the productConfigs
	 */
	public ProductConfigsVo getProductConfigs() {
		return productConfigs;
	}
	/**
	 * @param productConfigs the productConfigs to set
	 */
	public void setProductConfigs(ProductConfigsVo productConfigs) {
		this.productConfigs = productConfigs;
	}
	/**
	 * @return the srcImageUrl
	 */
	public String getSrcImageUrl() {
		return srcImageUrl;
	}
	/**
	 * @return the bigImageUrl
	 */
	public String getBigImageUrl() {
		return bigImageUrl;
	}
	/**
	 * @return the midImageUrl
	 */
	public String getMidImageUrl() {
		return midImageUrl;
	}
	/**
	 * @return the smallImageUrl
	 */
	public String getSmallImageUrl() {
		return smallImageUrl;
	}
	/**
	 * @return the firstImage
	 */
	public ImageBean getFirstImage() {
		return firstImage;
	}
	/**
	 * @param firstImage the firstImage to set
	 */
	public void setFirstImage(ImageBean firstImage) {
		this.firstImage = firstImage;
		if (firstImage != null && StringUtils.isNotEmpty(firstImage.getImageUrl())) {
			smallImageUrl = RewriteUtil.getImageUrl(firstImage.getImageUrl(), "s");
			midImageUrl = RewriteUtil.getImageUrl(firstImage.getImageUrl(), "m");
			bigImageUrl = RewriteUtil.getImageUrl(firstImage.getImageUrl(), "b");
			srcImageUrl = RewriteUtil.getImageUrl(firstImage.getImageUrl());
		}
	}
	
	public static void main(String[] args) {
		System.out.println(new Date().getTime());
	}
	/**
	 * @return the productUrl
	 */
	public String getProductUrl() {
		return productUrl;
	}
	/**
	 * @return the cats
	 */
	public List<CustomCategoryBean> getCats() {
		return cats;
	}
	/**
	 * @param cats the cats to set
	 */
	public void setCats(List<CustomCategoryBean> cats) {
		this.cats = cats;
	}
	/**
	 * @return the images
	 */
	public List<ImageBean> getImages() {
		return images;
	}
	/**
	 * @param images the images to set
	 */
	public void setImages(List<ImageBean> images) {
		this.images = images;
	}
	/**
	 * @return the productName
	 */
	public String getProductName() {
		return productName;
	}
	/**
	 * @param productName the productName to set
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}
	/**
	 * @return the position
	 */
	public int getPosition() {
		return position;
	}
	/**
	 * @param position the position to set
	 */
	public void setPosition(int position) {
		this.position = position;
	}
}
