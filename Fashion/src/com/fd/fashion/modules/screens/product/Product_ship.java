/**
 * Product_ship.java
 * @author:  ZhouRongyu
 */
package com.fd.fashion.modules.screens.product;

import java.util.ArrayList;
import java.util.List;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.fashion.modules.screens.BaseScreen;
import com.fd.fashion.product.bean.ProductBean;
import com.fd.fashion.product.manager.IProductManager;
import com.fd.fashion.seller.vo.ResultRateVo;
import com.fd.fashion.seller.vo.ShippingRateVo;
import com.fd.util.AppContextUtil;
import com.fd.util.CullNumUtil;

/**
 * @CreateDate:  2013-3-15 下午08:25:30 
 * @author:  ZhouRongyu
 * @Description:  TODO
 * 
 * @version:  V1.0
 */
public class Product_ship extends BaseScreen{
	public void doBuildTemplate(RunData data, Context context) throws Exception {
		super.doBuildTemplate(data, context);
		data.setLayoutTemplate("/NoLayout.html");
		
		String country = data.getParameters().getString("regionId");
		Long productId = data.getParameters().getLong("productId");
		int amount = data.getParameters().getInt("amount");
		IProductManager productManager = (IProductManager)AppContextUtil.getBean("productManager");
		ProductBean product = new ProductBean();
		product = productManager.getProductBean(productId);
		//产品详细页运费显示
		List<ResultRateVo> resultRateList = getShippingCost(product, amount,country, productManager);
		context.put("resultRateList", resultRateList);
		CullNumUtil cnu = new CullNumUtil();
		context.put("cnu", cnu);
	}
	public List<ResultRateVo>  getShippingCost(ProductBean pif,Integer amount, String country, IProductManager productManager) throws Exception{
		float length = 0;
		float width = 0;
		float heigth = 0;
		float heavy = 0;
		
		if(pif.getLength()!=null){
			try{
				length = pif.getLength().floatValue();
			}catch(Exception e){
				length = 0;
			}			
		}
		
		if(pif.getWidth()!=null){
			try{
				width = pif.getWidth().floatValue();
			}catch(Exception e){
				width = 0;
			}			
		}
		if(pif.getHeight()!=null){
			try{
				heigth = pif.getHeight().floatValue();
			}catch(Exception e){
				heigth = 0;
			}			
		}
		
		if(pif.getWeight()!=null){
			try{
				heavy = pif.getWeight().floatValue();
			}catch(Exception e){
				heavy = 0;
			}			
		}
		
		ShippingRateVo shippingRateVo = new ShippingRateVo();
		shippingRateVo.setWidth(width);
		shippingRateVo.setHeight(heigth);
		shippingRateVo.setHeavy(heavy);
		shippingRateVo.setLength(length);
		shippingRateVo.setAmount(amount);
		shippingRateVo.setCountry(country);
		shippingRateVo.setShippingTemplateId(pif.getShippingTemplateId());		
		shippingRateVo.setProductId(pif.getProductId());
		//获得结果
		List<ResultRateVo> resultRateVoList = new ArrayList<ResultRateVo>();	
	
		resultRateVoList = productManager.getShippingCalculateResult(shippingRateVo);		
		return resultRateVoList;
	
	}
}
