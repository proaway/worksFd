package com.fd.fashion.product.vo;

import java.util.Date;

import com.fd.fashion.product.bean.PriceBean;
import com.fd.fashion.product.bean.ProductStandardBean;
import com.fd.util.CullNumUtil;

/**
 * @CreateDate: 2013-3-21 下午12:26:58
 * @author Longli
 * @Description: 产品规格VO，封装产品规格表示层数据
 * 
 */
public class ProductStandardVo {
	/** 产品价格Bean */
	private PriceBean priceBean;
	/** 产品规格Bean */
	private ProductStandardBean productStandardBean;
	/** 显示原价格 */
	private double price;
	/** 显示价格 */
	private double discountPrice;
	/** 佣金 */
	private double commission = 1.05;
	/** 批发起始数量 */
	private Integer wholesaleCount;
	/** 批发价格 */
	private double wholesalePrice;
	/** 节省金额 */
	private double save;
	
	/**
	 * 设置显示的价格
	 */
	private void setPrice(PriceBean priceBean, ProductStandardBean productStandard) {
		if (priceBean == null || productStandard == null) {
			return;
		}
		if (productStandardBean.getPrice() == null) {
			return;
		}
		price = productStandardBean.getPrice() * commission;
		discountPrice = price;
		wholesalePrice = price;
		if(productStandardBean.getDiscount()!=null && productStandardBean.getDiscount() && priceBean.getDiscount() != null && priceBean.getDiscount() > 0) {
			Date startDate = priceBean.getDiscountStartDate();
			Date endDate = priceBean.getDiscountEndDate();
			Date now = new Date();
			if (now.after(startDate) && now.before(endDate)) {
				discountPrice = price * (1 - priceBean.getDiscount()/100);
			} else {
				priceBean.setDiscount(0.00);
			}
		}
		price = CullNumUtil.cullNum(price);
		discountPrice = CullNumUtil.cullNum(discountPrice);
		wholesaleCount = priceBean.getWholesaleCount();
		if (productStandardBean.getWholesale()!=null && productStandardBean.getWholesale() && wholesaleCount != null && wholesaleCount>1) {
			wholesalePrice = discountPrice * (1-priceBean.getWholesaleDiscount()/100);
			wholesalePrice = CullNumUtil.cullNum(wholesalePrice);
		}
		save = CullNumUtil.cullNum(price - discountPrice);
	}
	
	/**
	 * @return the productStandard
	 */
	public ProductStandardBean getProductStandardBean() {
		return productStandardBean;
	}
	/**
	 * @param productStandard the productStandard to set
	 */
	public void setProductStandardBean(ProductStandardBean productStandard) {
		if (productStandard == null) {
			return;
		}
		this.productStandardBean = productStandard;
		if (priceBean == null && productStandardBean.getPrice() != null) {
			price = productStandardBean.getPrice() * commission;
			discountPrice = price;
			wholesalePrice = price;
			save = 0;
		} else {
			setPrice(priceBean, productStandardBean);
		}
	}
	/**
	 * @return the commission
	 */
	public double getCommission() {
		return commission;
	}
	/**
	 * @param commission the commission to set
	 */
	public void setCommission(double commission) {
		this.commission = commission;
	}
	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}
	/**
	 * @return the discountPrice
	 */
	public double getDiscountPrice() {
		return discountPrice;
	}
	/**
	 * @return the wholesaleCount
	 */
	public Integer getWholesaleCount() {
		return wholesaleCount;
	}
	/**
	 * @return the wholesalePrice
	 */
	public double getWholesalePrice() {
		return wholesalePrice;
	}
	/**
	 * @return the save
	 */
	public double getSave() {
		return save;
	}
	/**
	 * @return the priceBean
	 */
	public PriceBean getPriceBean() {
		return priceBean;
	}
	/**
	 * @param priceBean the priceBean to set
	 */
	public void setPriceBean(PriceBean priceBean) {
		if (priceBean == null) {
			return;
		}
		this.priceBean = priceBean;
		if (productStandardBean == null) {
			price = priceBean.getPrice() * commission;
			discountPrice = price;
			if(priceBean.getDiscount() != null && priceBean.getDiscount() > 0) {
				Date startDate = priceBean.getDiscountStartDate();
				Date endDate = priceBean.getDiscountEndDate();
				Date now = new Date();
				if (now.after(startDate) && now.before(endDate)) {
					discountPrice = price * (1 - priceBean.getDiscount()/100);
				} else {
					priceBean.setDiscount(0.00);
				}
			}
			price = CullNumUtil.cullNum(price);
			discountPrice = CullNumUtil.cullNum(discountPrice);
			if (wholesaleCount != null && wholesaleCount>1) {
				wholesalePrice = discountPrice * (1-priceBean.getWholesaleDiscount()/100);
				wholesalePrice = CullNumUtil.cullNum(wholesalePrice);
			}
			save = CullNumUtil.cullNum(price - discountPrice);
		} else {
			setPrice(priceBean, productStandardBean);
		}
	}
}
