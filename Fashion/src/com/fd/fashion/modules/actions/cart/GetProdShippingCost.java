package com.fd.fashion.modules.actions.cart;

import java.util.List;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.fashion.modules.actions.BaseAction;
import com.fd.fashion.seller.manager.IShippingManager;
import com.fd.fashion.seller.vo.ShippingDetailVo;
import com.fd.util.JSONUtil;

/**
 * @CreateDate: 2013-4-15 下午02:40:09
 * @author Longli
 * @Description: 获取购物车的运费信息
 * 
 */
public class GetProdShippingCost extends BaseAction {
	@SuppressWarnings("unchecked")
	@Override
	public void doPerform(RunData data, Context context) throws Exception {
		super.doPerform(data, context);
		String country = data.getParameters().getString("country");
		int quantity = data.getParameters().getInt("quantity");
		int length = data.getParameters().getInt("length") * quantity;
		int width = data.getParameters().getInt("width") * quantity;
		int height = data.getParameters().getInt("height") * quantity;
		double weight = data.getParameters().getDouble("weight") * quantity;

		IShippingManager shippingManager = (IShippingManager) getBean("shippingManager");
		List<ShippingDetailVo> shipppingDetails = shippingManager.getShippingDetails(weight, length, width, height, country);
		context.put("contentdata", JSONUtil.list2JSON(shipppingDetails));
	}
}
