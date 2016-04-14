package com.fd.b2c.manager.modules.actions.shipping;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.b2c.manager.modules.actions.SecureAction;
import com.fd.fashion.seller.bean.ShippingDetailBean;
import com.fd.fashion.seller.bean.ShippingInfoBean;
import com.fd.fashion.seller.bean.ShippingOptionBean;
import com.fd.fashion.seller.manager.IShippingManager;
import com.fd.util.JSONUtil;
import com.fd.util.WebPropUtil;

public class AjaxSaveShipping extends SecureAction {
	@Override
	public void doPerform(RunData data, Context context) throws Exception {
		super.doPerform(data, context);
		
		ShippingInfoBean shippingInfo = new ShippingInfoBean();
		String[] shippingCompanies = data.getParameters().getStrings("shippingCompany");
		List<ShippingDetailBean> details = new ArrayList<ShippingDetailBean>();
		for (String comp : shippingCompanies) {
			String shippingType = data.getParameters().get(comp);
			if (StringUtils.isEmpty(shippingType)) {
				continue;
			}
			ShippingDetailBean detail = new ShippingDetailBean();
			detail.setShippingCompany(comp);
			if (comp.startsWith("custCompany_")) {
				// 自定义增加的物流方式
				detail.setShippingCompany(data.getParameters().getString(comp+"_name"));
				detail.setTransportDays(data.getParameters().getString(comp+"_transportDays"));
			}
			detail.setDetailId(data.getParameters().getLong(comp+"_detailId"));
			
			detail.setShippingType(shippingType);
			if (shippingType.equals("off")) {
				detail.setDiscountRate(data.getParameters().getInt(comp+"_off"));
			} else if (shippingType.equals("custom")) {
				List<ShippingOptionBean> options = new ArrayList<ShippingOptionBean>();
				int i=1;
				while (true) {
					String shippingCountry = data.getParameters().getString(comp + "_shippingCountry" + i);
					if (StringUtils.isEmpty(shippingCountry)) {
						break;
					}
					ShippingOptionBean option = new ShippingOptionBean();
					option.setShippingCountry(shippingCountry);
					String custShippingType = data.getParameters().getString(comp + "_shippingType" + i);
					option.setShippingType(custShippingType);
					if (custShippingType.equals("off")) {
						option.setDiscountRate(data.getParameters().getInt(comp + "_discountRate" + i));
					} else if (custShippingType.equals("custom")) {
						option.setWeightPrice(data.getParameters().getDouble(comp + "_weightPrice" + i));
						option.setReWeightPrice(data.getParameters().getDouble(comp + "_reWeightPrice" + i));
					}
					option.setTransportDays(data.getParameters().getString(comp + "_transportDays" + i));
					i++;
					options.add(option);
				}
				detail.setOptions(options);
			}
			details.add(detail);
		}
		shippingInfo.setDetails(details);
		shippingInfo.setName(data.getParameters().getString("name"));
		shippingInfo.setCreateDate(new Date());
		shippingInfo.setModifyTime(new Date());
		WebPropUtil propUtil = new WebPropUtil();
		long sellerId = Long.valueOf(propUtil.get("seller.id"));
		shippingInfo.setSellerId(sellerId);
		
		long shippingInfoId = data.getParameters().getLong("shippingInfoId");
		
		IShippingManager shippingManager = (IShippingManager) getBean("shippingManager");
		if (shippingInfoId > 0) {
			shippingInfo.setShippingInfoId(shippingInfoId);
			shippingManager.updateShippingInfo(shippingInfo);
		} else {
			shippingManager.addShippingInfo(shippingInfo);
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("shippingInfo", shippingInfo);
		map.put("shippingDetails", shippingInfo.getDetails());
		map.put("status", 1);
		context.put("contentdata", JSONUtil.obj2JSON(map));
	}
}
