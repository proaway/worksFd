package com.fd.fashion.product.vo;

import java.util.ArrayList;
import java.util.List;

import com.fd.fashion.product.bean.PriceBean;
import com.fd.fashion.product.bean.ProductStandardBean;

/**
 * @CreateDate: 2013-3-15 下午11:53:44
 * @author Longli
 * @Description: 封装一个产品的规格、主配置属性、从配置属性
 * 
 */
public class ProductConfigsVo {
	/** 产品Id  */
	private Long productId;
	/** 产品价格 */
	private PriceVo price;
	/** 所有规格 */
	private List<ProductStandardVo> standards;
	/** 主配置属性 */
	private ProductConfigShow mainShow;
	/** 从配置属性 */
	private ProductConfigShow subShow;
	/** 总库存 */
	private int stockNum;
	
	/**
	 * 设置库存清零
	 */
	public void emptyStock() {
		if (standards != null && standards.size()>0) {
			for (ProductStandardVo standard : standards) {
				if (standard.getProductStandardBean().getStock() != null) {
					standard.getProductStandardBean().setStock(0);
				}
			}
		}
		stockNum = 0;
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
	 * @return the standards
	 */
	public List<ProductStandardVo> getStandards() {
		return standards;
	}

	/**
	 * @param standards the standards to set
	 */
	public void setStandards(List<ProductStandardBean> standards) {
		if (standards != null && standards.size()>0) {
			this.standards = new ArrayList<ProductStandardVo>();
			for (ProductStandardBean standard : standards) {
				if (standard.getStock() != null) {
					stockNum += standard.getStock();
				}
				ProductStandardVo sd = new ProductStandardVo();
				sd.setProductStandardBean(standard);
				this.standards.add(sd);
			}
		}
	}

	/**
	 * @return the mainShow
	 */
	public ProductConfigShow getMainShow() {
		return mainShow;
	}

	/**
	 * @param mainShow the mainShow to set
	 */
	public void setMainShow(ProductConfigShow mainShow) {
		this.mainShow = mainShow;
	}

	/**
	 * @return the subShow
	 */
	public ProductConfigShow getSubShow() {
		return subShow;
	}

	/**
	 * @param subShow the subShow to set
	 */
	public void setSubShow(ProductConfigShow subShow) {
		this.subShow = subShow;
	}

	/**
	 * @return the price
	 */
	public PriceVo getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(PriceVo price) {
		this.price = price;
		if (standards != null) {
			double minPrice = 0;
			ProductStandardVo minStandard = null;
			for (ProductStandardVo standard : standards) {
				standard.setPriceBean(price.getPriceBean());
				if (minPrice ==0 || standard.getPrice() < minPrice) {
					minPrice = standard.getPrice();
					minStandard = standard;
				}
			}
			if (minStandard != null) {
				price.setDiscountPrice(minStandard.getDiscountPrice());
				price.setPrice(minStandard.getPrice());
				price.setSave(minStandard.getSave());
			}
		}
	}

	/**
	 * @return the stockNum
	 */
	public int getStockNum() {
		return stockNum;
	}
}
