package com.fd.fashion.seller.vo;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.fd.fashion.dict.bean.ShippingCostBean;
import com.fd.fashion.seller.bean.ShippingDetailBean;
import com.fd.fashion.seller.bean.ShippingOptionBean;
import com.fd.util.CullNumUtil;
import com.fd.util.DictUtil;

/**
 * @CreateDate: 2013-4-15 上午11:27:38
 * @author Longli
 * @Description: 物流信息和价格表，对于指定的产品，计算包装重量，算出物流运费价格。
 * 
 */
public class ShippingDetailVo {
	/** 物流模版明细 */
	private ShippingDetailBean detail;
	
	/** 物流自定义明细 */
	private ShippingOptionBean option;
	
	/** 算出的最终运费，如果物流不到达指定国家，返回-1 */
	private double shippingAmount;

	/** 物流天数 */
	private String transportDays;
	
	/** 物流费率 */
	private ShippingCostBean shippingCost;
	
	/** 产品重量，单位 kg */
	private double weight;
	
	/** 产品体积，单位用 cm */
	private long volume;
	
	/** 物流方式全名称 */
	private String shippingCompany;
	
	/**
	 * 构造物流运费Vo，并计算出运费
	 * 
	 * @param weight 重量，单位 kg，当运费不是免运费时，这是必须参数
	 * @param volume 体积，单位 cm，当运费方式是DHL、TNT、FEDEX、UPS时，重量使用实际重量和体积重量中更大的。
	 * @param country 国家代码，必须参数
	 * @param detail 运费模版明细，必须参数
	 * @param options 自定义运费明细，可选参数
	 * @param shippingCosts 物流费率列表，可选，当物流方式是折扣方式时，这是必须参数
	 * @throws Exception
	 */
	public ShippingDetailVo(double weight, long volume, String country, ShippingDetailBean detail, List<ShippingOptionBean> options, List<ShippingCostBean> shippingCosts) throws Exception {
		if (detail == null) {
			throw new Exception("缺少运费明细，请选择合理的运费设置");
		}
		if (StringUtils.isEmpty(country)) {
			throw new Exception("缺少国家参数");
		}
		this.detail=detail;
		this.shippingCompany = DictUtil.getShippingCompany(detail.getShippingCompany());
		this.weight=weight;
		this.volume=volume;

		// 运费
		if ("freeshipping".equals(detail.getShippingType())) { // 免运费
			shippingAmount = 0;
		} else if ("custom".equals(detail.getShippingType())) { // 自定义运费(指定国家)
			if (option == null) {
				setOption(country, options);
			}
			if (option == null) { // 物流方式不到达指定国家，返回运费为-1
				shippingAmount = -1;
				return;
			}
			if ("off".equals(option.getShippingType())) { // 自定义时选折扣
				if (shippingCosts == null) {
					throw new Exception("缺少物流费率");
				}
				if (shippingCost == null) {
					setShippingCost(country, detail, shippingCosts);
				}
				shippingAmount = calShippingAmount(detail.getShippingCompany(), shippingCost.getWeightPrice(), shippingCost.getReWeightPrice());
				shippingAmount = shippingAmount * (1 - option.getDiscountRate() / 100.0);
			} else if ("custom".equals(option.getShippingType())) { // 自定义起重续重价格
				shippingAmount = calShippingAmount(detail.getShippingCompany(), option.getWeightPrice(), option.getReWeightPrice());
			} else if ("freeshipping".equals(option.getShippingType())) { // 自定义时选免运费
				shippingAmount = 0;
			}
		} else if ("off".equals(detail.getShippingType())) { // 折扣运费
			if (shippingCosts == null) {
				throw new Exception("缺少物流费率");
			}
			if (shippingCost == null) {
				setShippingCost(country, detail, shippingCosts);
			}
			shippingAmount = calShippingAmount(detail.getShippingCompany(), shippingCost.getWeightPrice(), shippingCost.getReWeightPrice());
			shippingAmount = shippingAmount * (1 - detail.getDiscountRate() / 100.0);
		}
		shippingAmount = CullNumUtil.cullNum(shippingAmount);
		
		
		// 物流天数 
		if ("custom".equals(detail.getShippingType()) && StringUtils.isNotEmpty(option.getTransportDays())) { // 自定义
			this.transportDays = option.getTransportDays();
		} else  {
			if (shippingCosts == null) {
				throw new Exception("缺少物流费率");
			}
			if (shippingCost == null) {
				setShippingCost(country, detail, shippingCosts);
			}
			if (StringUtils.isNotEmpty(detail.getTransportDays())) {
				this.transportDays = detail.getTransportDays();
			} else {
				this.transportDays = shippingCost.getTransportDays();
			}
		}
	}
	
	/**
	 * 根据选择的运费方式，计算出运费
	 * 
	 * @param shippingCompany
	 * @param weightPrice
	 * @param reWeightPrice
	 * @return
	 */
	private double calShippingAmount(String shippingCompany, double weightPrice, double reWeightPrice) {
		double weight = this.weight;
		if ("DHL".equals(shippingCompany) || "UPS".equals(shippingCompany) || "FEDEX".equals(shippingCompany) || "TNT".equals(shippingCompany)) {
			double w = volume / 5000; // 体积重量
			weight = weight > w ? weight : w;
		}
		if (weight < 0.5) {
			return weightPrice;
		}
		double amount = weightPrice;
		double w = weight - 0.5;
		while (w > 0.5) {
			amount += reWeightPrice;
			w -= 0.5;
		}
		return amount;
	}

	/**
	 * @param country
	 * @param options
	 */
	private void setOption(String country, List<ShippingOptionBean> options) {
		for (ShippingOptionBean option : options) {
			if (option.getShippingCountry().contains(country + ",")) {
				this.option = option;
				break;
			}
		}
	}

	/**
	 * @param country
	 * @param detail
	 * @param shippingCosts
	 */
	private void setShippingCost(String country, ShippingDetailBean detail,
			List<ShippingCostBean> shippingCosts) {
		for (ShippingCostBean cost : shippingCosts) {
			if(cost.getCountry().equals(country) && cost.getShippingCompany().equalsIgnoreCase(detail.getShippingCompany())) {
				shippingCost = cost;
				break;
			}
		}
	}

	/**
	 * @return the option
	 */
	public ShippingOptionBean getOption() {
		return option;
	}

	/**
	 * @param option the option to set
	 */
	public void setOptions(ShippingOptionBean option) {
		this.option = option;
	}

	/**
	 * @return the weight
	 */
	public double getWeight() {
		return weight;
	}

	/**
	 * @param weight the weight to set
	 */
	public void setWeight(double weight) {
		this.weight = weight;
	}

	/**
	 * @return the detailBean
	 */
	public ShippingDetailBean getDetail() {
		return detail;
	}

	/**
	 * @param detailBean the detailBean to set
	 */
	public void setDetail(ShippingDetailBean detail) {
		this.detail = detail;
	}

	/** 算出的最终运费，如果物流不到达指定国家，返回-1 */
	public double getShippingAmount() {
		return shippingAmount;
	}

	/**
	 * @return the transportDays
	 */
	public String getTransportDays() {
		return transportDays;
	}

	/**
	 * @return the shippingCost
	 */
	public ShippingCostBean getShippingCost() {
		return shippingCost;
	}

	/**
	 * @return the shippingCompany
	 */
	public String getShippingCompany() {
		return shippingCompany;
	}
}
