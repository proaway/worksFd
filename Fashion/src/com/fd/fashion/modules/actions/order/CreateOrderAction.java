/**
 * CreateOrderAction.java
 * @author:  ZhouRongyu
 */
package com.fd.fashion.modules.actions.order;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.constants.SessionConstants;
import com.fd.fashion.buyer.bean.BuyerAddressBookBean;
import com.fd.fashion.buyer.bean.BuyerBean;
import com.fd.fashion.buyer.manager.IBuyerManager;
import com.fd.fashion.dict.bean.RegionBean;
import com.fd.fashion.dict.manager.IDictManager;
import com.fd.fashion.modules.actions.SecureAction;
import com.fd.fashion.order.bean.OrderAddressBean;
import com.fd.fashion.order.bean.OrderBean;
import com.fd.fashion.order.manager.IOrderManager;
import com.fd.fashion.product.vo.CartProductVo;
import com.fd.session.FdSession;
import com.fd.session.FdSessionFactory;
import com.fd.util.IPAddrUtil;
import com.fd.util.SecurityUtil;


/**
 * @CreateDate:  2013-4-8 下午12:10:25 
 * @author:  ZhouRongyu
 * @Description:  TODO
 * 
 * @version:  V1.0
 */
public class CreateOrderAction extends SecureAction {

	@SuppressWarnings("unchecked")
	public void doPerform(RunData data, Context context) throws Exception {
		data.setCharSet("UTF-8");
		FdSession session = FdSessionFactory.getFdSession(data);
		
		BuyerBean buyer = (BuyerBean) session.getAttribute(SessionConstants.KEY_LOGIN_BUYER);
		String addressBookId = data.getParameters().getString("addressBookId");
		String couponCode = data.getParameters().getString("couponCode");
		if (StringUtils.isNotEmpty(couponCode)) {
			couponCode = couponCode.trim();
		}
		String shippingCompany = data.getParameters().getString("shippingMethod");
		String [] pids = data.getParameters().getStrings("orderProductId");
		Double couponAmount = data.getParameters().getDouble("couponAmount",0.00);
		Double totalPrice = data.getParameters().getDouble("totalPrice");
		Double shippingTotal = data.getParameters().getDouble("shippingTotal");
		Double itemTotal = data.getParameters().getDouble("itemTotal");
		String couponProduct = data.getParameters().getString("couponProduct");
		IBuyerManager buyerManager = (IBuyerManager) this.getBean("buyerManager");
		BuyerAddressBookBean babb = buyerManager.getBuyerAddressBook(Long.valueOf(addressBookId));
		
		OrderAddressBean orb = new OrderAddressBean();
		PropertyUtils.copyProperties(orb,babb);
		System.out.println(orb.getCity());
		orb.setOrderAddressId(Long.valueOf(addressBookId));
		IDictManager dictManager = (IDictManager) getBean("dictManager");
		RegionBean country = dictManager.getRegionByRegionId(orb.getCountry());
		
		
		IOrderManager orderManager = (IOrderManager) this.getBean("orderManager");
		//orderManager.addOrderAddress(orb);
		String ipAddress = IPAddrUtil.getIpAddr(data.getRequest());
		OrderBean order = new OrderBean();
		//order.setSellerId(sellerId);
		order.setBuyerId(buyer.getBuyerId());
		order.setUserId(buyer.getUserId());
		order.setCouponCode(couponCode);
		order.setCouponAmount(couponAmount);
		order.setOrderStatus("OA001");
		order.setShippingType(shippingCompany);
		order.setTotal(totalPrice);
		order.setCreateDate(new Date());
		order.setShippingTotal(shippingTotal);
		order.setItemTotal(itemTotal);
		order.setIpAddress(ipAddress);
		order.setShippingCountry(country.getRegionName());
		
		List<CartProductVo> cartProducts = (List<CartProductVo>)session.getAttribute(SessionConstants.KEY_CART_PRODUCT);
		List<CartProductVo> cProducts = new ArrayList<CartProductVo>();
		String[] couponProducts = couponProduct.split(",");
		int maxStockDays = 0;
		for(int j=0;j<pids.length;j++){
			String pid = pids[j];
			for(int i=0;i<cartProducts.size();i++){
				CartProductVo cartProductVo = cartProducts.get(i);
				String productId = String.valueOf(cartProductVo.getProductId());
				if(null != cartProductVo.getMaxStockDays()){
					int tmp = cartProductVo.getMaxStockDays();
					if(maxStockDays < tmp){
						maxStockDays = tmp;
					}
				}

				//计算order订单产品对应coupon价格
				for(int m=0;m<couponProducts.length;m++){
					if(productId.equals(couponProducts[m])){
						cartProductVo.setIsCouponProduct(1);
					}
				}
				
				if(pid.equals(productId)){
					//计算规格对应价格
					cProducts.add(cartProductVo);
				}
			}
		}
		order.setMaxStockDays(maxStockDays);
		
		int flag = orderManager.addOrder(order,orb,cProducts);
		if(flag == 1) {
			String productIds = "";
			for (CartProductVo cp : cProducts) {
				productIds += cp.getProductId()+",";
				orderManager.deleteShoppingCart(cp, buyer.getBuyerId());
				cartProducts.remove(cp);
			}
			JSONArray orderJsonArray = new JSONArray();
			JSONObject orderJsonObj = new JSONObject();
			orderJsonObj.put("orderId", order.getOrderId());
			orderJsonObj.put("productIds", productIds);
			orderJsonArray.add(orderJsonObj);
			String orderInfo = SecurityUtil.encryptMode(orderJsonArray.toString());
			
			session.setAttribute(SessionConstants.KEY_CART_PRODUCT, cartProducts);
			String url = "/fashion/template/order,PayOrder.html?orderId="+order.getOrderId()+"&productIds="+productIds+"&orderInfo="+orderInfo;
			context.put("payUrl", url);

			this.setTemplate(data, "order,Payment.html");
		}
		//order.setIpAddress(IPAddrUtil.getIpAddr(data.getRequest()));
		//order.setIsAgrDeliver("1"); // 默认不同意转单
/*		order.setTotal(total);
		order.setCreatDate(new Date());
		order.setOrderAddressId(orderAddressId);*/
		
	}

}
