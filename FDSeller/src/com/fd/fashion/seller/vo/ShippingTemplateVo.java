package com.fd.fashion.seller.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import sun.text.resources.FormatData;

import com.fd.fashion.seller.bean.ShippingInfoBean;

/**运费模板及运输方式详细
 * @author zhangling
 *
 */
public class ShippingTemplateVo {
	
	//运费模板基本属性
	private ShippingInfoBean shippingInfoBean;
	
	//运费模板描述
	private String description;
	
	//运费模板详情(运输方式等属性)
	private List<ShippingTemplateDetailVo> tempDetails;

	public ShippingInfoBean getShippingInfoBean() {
		return shippingInfoBean;
	}

	public void setShippingInfoBean(ShippingInfoBean shippingInfoBean) {
		this.shippingInfoBean = shippingInfoBean;
	}

	public List<ShippingTemplateDetailVo> getTempDetails() {
		return tempDetails;
	}

	public void setTempDetails(List<ShippingTemplateDetailVo> tempDetails) {
		this.tempDetails = tempDetails;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String discription) {
		this.description = discription;
	}
}
