package com.fd.fashion.modules.actions.buyer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.constants.SessionConstants;
import com.fd.fashion.dict.bean.AttributeBean;
import com.fd.fashion.modules.actions.BaseAction;
import com.fd.fashion.order.bean.OrderProductBean;
import com.fd.fashion.order.manager.IOrderManager;
import com.fd.fashion.product.bean.ImageBean;
import com.fd.fashion.product.manager.IProductManager;
import com.fd.fashion.product.vo.CartProductVo;
import com.fd.fashion.product.vo.ProductVo;
import com.fd.session.FdSession;
import com.fd.session.FdSessionFactory;
import com.fd.util.RewriteUtil;

/**
 * @author zhangling(将某个订单中所有的产品重新加入到购物车)
 *
 */
public class AjaxOrderToCartAction extends BaseAction {
	@SuppressWarnings("unchecked")
	public void doPerform(RunData data, Context context) throws Exception {
		FdSession session = FdSessionFactory.getFdSession(data);
		//需要获得的属性
		/*productId:productId,
		 mainConfigId:mainConfigId,
		 mainId:mainId,
		 mainTitle:mainTitle,
		 subConfigId:subConfigId,
		 subId:subId,
		 subTitle:subTitle,
		 quantity:quantity,
		 sku:sku,
		 price:price,
		 productName:productName,
		 imageUrl:imageUrl,
		 weight:weight,
		 height:height,
		 width:width,
		 length:length,
		 maxStockDays:maxStockDays*/
		List<CartProductVo> cartProducts = (List<CartProductVo>) session
		.getAttribute(SessionConstants.KEY_CART_PRODUCT);
		IOrderManager orderManager = (IOrderManager)getBean("orderManager");
		Long orderId = data.getParameters().getLongObject("orderId");
		if(orderId==null || orderId<0){
			return ;
		}
		List<OrderProductBean> orderProducts = orderManager.getOrderProductInfo(orderId);
		List<CartProductVo> addList = new ArrayList<CartProductVo>();
		if(orderProducts!=null && orderProducts.size()>0){
			IProductManager productManager = (IProductManager)getBean("productManager");
			for(OrderProductBean orderPro :orderProducts){
				ProductVo p = productManager.getProductVo(orderPro.getProductId());
				CartProductVo cartVo = new CartProductVo();
				if(p!=null){
					cartVo.setProductId(p.getProduct().getProductId());
					cartVo.setQuantity(p.getProduct().getQuantity());
					cartVo.setSku(p.getProduct().getSku());
					cartVo.setPrice(orderPro.getItemPrice());
					cartVo.setProductName(p.getProduct().getProductName().replaceAll("\"","&quot;").replaceAll("'", "&prime;"));
					cartVo.setWeight(p.getProduct().getWeight());
					cartVo.setHeight(p.getProduct().getHeight());
					cartVo.setWidth(p.getProduct().getWidth());
					cartVo.setLength(p.getProduct().getLength());
					cartVo.setMaxStockDays(p.getProduct().getStockDays());
				}
				ImageBean image = productManager.getFirstProdImageBean(orderPro.getProductId());
				orderPro.setImageUrl(RewriteUtil.getImageUrl(image.getImageUrl(), "m"));
				cartVo.setImageUrl(RewriteUtil.getImageUrl(image.getImageUrl(), "m"));
				if(orderPro.getStandardId()!=null){
					HashMap<String,AttributeBean> configs = productManager.getProductConfigs(orderPro.getStandardId());
					orderPro.setConfig(configs);
					if(configs.get("mainValue")!=null){
						cartVo.setMainConfigId(configs.get("mainValue").getAttrId());
						cartVo.setMainId(configs.get("mainValue").getValue());
						cartVo.setMainTitle(configs.get("mainTitle").getValue());
					}
					if(configs.get("subValue")!=null){
						cartVo.setSubConfigId(configs.get("subValue").getAttrId());
						cartVo.setSubId(configs.get("subValue").getValue());
						cartVo.setSubTitle(configs.get("subTitle").getValue());
					}
				}
				addList.add(cartVo);
			}
			if (addList != null && addList.size() > 0) {
				for (CartProductVo cartVo : addList) {
					if(cartProducts != null && cartProducts.size() > 0){
						boolean add = true;
						for(int i=0;i<cartProducts.size();i++){
							CartProductVo cartProd = cartProducts.get(i);
							long pid = cartProd.getProductId();
							long mid = 0;
							long sid =0;
							if(cartProd.getMainConfigId()!=null && cartProd.getMainConfigId()>0){
								 mid = cartProd.getMainConfigId()==null?0:cartProd.getMainConfigId();
							}
							if(cartProd.getSubConfigId()!=null && cartProd.getSubConfigId()>0){
								sid = cartProd.getSubConfigId()==null?0:cartProd.getSubConfigId();
							}
							if(pid==cartVo.getProductId()){ // 跳过已添加产品
								if(cartProd.getMainConfigId()!=null && cartProd.getMainConfigId()>0){
									if(mid==cartVo.getMainConfigId()){
										if(cartProd.getSubConfigId()!=null && cartProd.getSubConfigId()>0){
											if(sid==cartVo.getSubConfigId()){
												add = false;
											}
										}
										add = false;
									}
								}
								add = false;
								break;
							}
						}
						if (add) {
							cartProducts.add(cartVo);
						}
					}else{
						cartProducts = new ArrayList<CartProductVo>();
						cartProducts.add(cartVo);
					}
				}
			}
		}
		session.setAttribute(SessionConstants.KEY_CART_PRODUCT, cartProducts);
		context.put("contentdata", cartProducts.size());
	}

}
