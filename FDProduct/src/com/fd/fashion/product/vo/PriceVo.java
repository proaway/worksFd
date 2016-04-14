package com.fd.fashion.product.vo;

import java.util.Date;

import com.fd.fashion.product.bean.PriceBean;
import com.fd.util.CullNumUtil;

/**
 * @CreateDate: 2013-3-16 下午04:01:25
 * @author Longli
 * @Description: 价格的显示层VO，封装计算好了原价，折扣价，批发价等等
 * 
 */
public class PriceVo {
	/** 产品ID */
	private Long productId;
	/** 价格Bean */
	private PriceBean priceBean;
	/** 显示价格 */
	private double discountPrice;
	/** 显示原价 */
	private double price;
	/** 佣金 */
	private double commission = 1.05;
	/** 批发起始数量 */
	private Integer wholesaleCount;
	/** 批发价格 */
	private double wholesalePrice;
	/** 节省金额 */
	private double save;
	
	public double getPrice() {
		return price;
	}
	
	public double getDiscountPrice() {
		return discountPrice;
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
		this.priceBean = priceBean;
		if (priceBean == null || priceBean.getPrice() == null) {
			return;
		}
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
	}

	/**
	 * @return the productId
	 */
	public Long getProductId() {
		return productId;
	}

	/**
	 * @param productId the productId to set
	 */
	public void setProductId(Long productId) {
		this.productId = productId;
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
	 * @return the save
	 */
	public double getSave() {
		return save;
	}

	/**
	 * @return the wholesaleCount
	 */
	public Integer getWholesaleCount() {
		return wholesaleCount;
	}

	/**
	 * @param wholesaleCount the wholesaleCount to set
	 */
	public void setWholesaleCount(Integer wholesaleCount) {
		this.wholesaleCount = wholesaleCount;
	}

	/**
	 * @return the wholesalePrice
	 */
	public double getWholesalePrice() {
		return wholesalePrice;
	}

	/**
	 * @param wholesalePrice the wholesalePrice to set
	 */
	public void setWholesalePrice(double wholesalePrice) {
		this.wholesalePrice = wholesalePrice;
	}

	/**
	 * @param discountPrice the discountPrice to set
	 */
	public void setDiscountPrice(double discountPrice) {
		this.discountPrice = discountPrice;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * @param save the save to set
	 */
	public void setSave(double save) {
		this.save = save;
	}
}
