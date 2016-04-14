/**
 * CouponManager.java
 * @author:  ZhouRongyu
 */
package com.fd.fashion.order.manager;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.fd.fashion.buyer.bean.BuyerBean;
import com.fd.fashion.buyer.service.IBuyerService;
import com.fd.fashion.dict.bean.RegionBean;
import com.fd.fashion.dict.service.IDictService;
import com.fd.fashion.finance.service.IFinancialService;
import com.fd.fashion.order.bean.CouponBean;
import com.fd.fashion.order.bean.OrderAddressBean;
import com.fd.fashion.order.bean.OrderAmountBean;
import com.fd.fashion.order.bean.OrderBean;
import com.fd.fashion.order.bean.OrderPaymentinfoBean;
import com.fd.fashion.order.bean.OrderProductBean;
import com.fd.fashion.order.bean.OrderShippingBean;
import com.fd.fashion.order.bean.OrderstatusInfoBean;
import com.fd.fashion.order.bean.PickingBean;
import com.fd.fashion.order.bean.PickingInfoBean;
import com.fd.fashion.order.bean.ProblemOrderBean;
import com.fd.fashion.order.bean.ShippingCompanyBean;
import com.fd.fashion.order.bean.ShoppingCartBean;
import com.fd.fashion.order.service.IOrderService;
import com.fd.fashion.order.util.OrderUtil;
import com.fd.fashion.order.vo.OrderProductVo;
import com.fd.fashion.order.vo.OrderScanVo;
import com.fd.fashion.order.vo.OrderVo;
import com.fd.fashion.order.vo.PaymentInfoVo;
import com.fd.fashion.order.vo.SearchOrderVo;
import com.fd.fashion.product.bean.PriceBean;
import com.fd.fashion.product.bean.ProductBean;
import com.fd.fashion.product.bean.ProductStandardBean;
import com.fd.fashion.product.constants.ProductConstants;
import com.fd.fashion.product.service.IProductService;
import com.fd.fashion.product.vo.CartProductVo;
import com.fd.fashion.product.vo.PriceVo;
import com.fd.fashion.product.vo.ProductStandardVo;
import com.fd.payment.bean.PaymentTypeBean;
import com.fd.payment.bean.PaypalipnBean;
import com.fd.payment.service.IPaymentService;
import com.fd.util.CullNumUtil;
import com.fd.util.DictUtil;
import com.fd.util.FileUtil;
import com.fd.util.PageInfo;
import com.fd.util.StringUtil;
import com.fd.util.WebPropUtil;

/**
 * @CreateDate:  2013-4-8 下午12:23:51 
 * @author:  ZhouRongyu
 * @Description:  TODO
 * 
 * @version:  V1.0
 */
@Transactional(rollbackFor = Exception.class)
@Component
public class OrderManager implements IOrderManager {
	@Autowired
	@Qualifier("orderService")
	IOrderService orderService;
	
	@Autowired
	@Qualifier("dictService")
	IDictService dictService;
	
	@Autowired
	@Qualifier("productService")
	IProductService productService;

	@Autowired
	@Qualifier("paymentService")
	IPaymentService paymentService;
	
	@Autowired
	@Qualifier("buyerService")
	IBuyerService buyerService;
	
	@Autowired
	@Qualifier("financialService")
	IFinancialService financialService;


	/**
	 * 使用orderId获取Order
	 * 
	 * @param orderId
	 * @return
	 * @throws Exception
	 */
	public OrderBean getOrder(long orderId) throws Exception {
		OrderBean order = new OrderBean();
		order.setOrderId(orderId);
		order = orderService.getOrderBean(order);
		return order;
	}
	
	/**
	 * 增加订单历史状态
	 * 
	 * @return
	 * @throws Exception
	 */
	OrderstatusInfoBean addOrderstatusInfo(long orderId, String statusCode, String memo, String operator, boolean visible) throws Exception {
		OrderstatusInfoBean status = new OrderstatusInfoBean();
		status.setCreateTime(new Date());
		if (visible) {
			status.setStatus(1);
		} else {
			status.setStatus(0);
		}
		status.setOrderStatus(statusCode);
		//OrderStatusBean orderStatus = dictService.getOrderStatusBean(statusCode);
		status.setMemo(memo);
		status.setOrderId(orderId);
		status.setOperator(operator);
		orderService.insertOrderstatusInfoBean(status);
		return status;
	}
	
	/**
	 * 增加订单金额记录
	 * 
	 * @param orderId
	 * @param amount
	 * @param amountType
	 * @param operator
	 * @param memo
	 * @throws Exception
	 */
	OrderAmountBean addOrderAmount(long orderId, double amount, int amountType, String operator, String memo) throws Exception {
		OrderAmountBean amountBean = new OrderAmountBean();
		amountBean.setOrderId(orderId);
		amountBean.setAmountType(amountType);
		amountBean.setCreateDate(new Date());
		amountBean.setOperator(operator);
		amountBean.setMemo(memo);
		amountBean.setAmount(amount);
		orderService.insertOrderAmountBean(amountBean);
		return amountBean;
	}
	
	/**
	 * 添加订单地址
	 * 
	 * @param OrderAddress
	 * @return
	 * @throws Exception
	 */
	public OrderAddressBean addOrderAddress(OrderAddressBean orderAddress) throws Exception{
		return orderService.insertOrderAddressBean(orderAddress);
	}
	
	/**
	 * 添加订单
	 * 
	 * @param OrderBean
	 * @return
	 * @throws Exception
	 */
	public int addOrder(OrderBean order,OrderAddressBean orderAddress,List<CartProductVo> cProducts) throws Exception{
		Double totalPrice = 0.00;
		Double itemTotalPrice = 0.00;
		Double totalCouponProductPrice = 0.00;
		List<OrderProductVo> orderProducts = new ArrayList<OrderProductVo>();
		for(int i=0;i<cProducts.size();i++){
			CartProductVo cartProduct = cProducts.get(i);
			//查询product基本信息
			ProductBean pb = new ProductBean();
			pb.setProductId(cartProduct.getProductId());
			pb = productService.getProductBean(pb);
			
			//查询产品价格信息
			PriceVo priceVo = new PriceVo();
			PriceBean price = new PriceBean();
			price.setProductId(cartProduct.getProductId());
			price = productService.getPriceBean(price);
			priceVo.setPriceBean(price);
			OrderProductBean opb = new OrderProductBean();
			//根据规格查询成本价
			Double itemPrice = 0.00, cost=0d;
			if(cartProduct.getMainConfigId() != null || cartProduct.getSubConfigId() != null){
				ProductStandardBean productStandard = new ProductStandardBean();
				productStandard.setProductId(pb.getProductId());
				productStandard.setMainConfigId(cartProduct.getMainConfigId());
				productStandard.setSubConfigId(cartProduct.getSubConfigId());
				productStandard = productService.getProductStandardBean(productStandard);
				ProductStandardVo standardVo = new ProductStandardVo();
				standardVo.setProductStandardBean(productStandard);
				standardVo.setPriceBean(price);
				itemPrice = standardVo.getDiscountPrice();
				if (price == null) {
					price = new PriceBean();
				}
				opb.setStandardId(productStandard.getStandardId());
				cost = productStandard.getPrice();
			}else{
				itemPrice = priceVo.getDiscountPrice();
				cost = price.getPrice();
			}
			
			Double itemTotal = itemPrice * cartProduct.getQuantity();
			itemTotalPrice += itemTotal;
			if(cartProduct.getIsCouponProduct() == 1){
				totalCouponProductPrice += itemTotal;
			}
			//拼装订单产品
			//PropertyUtils.copyProperties(opb, pb);
			opb.setCommission(priceVo.getCommission());
			opb.setItemTotal(CullNumUtil.cullNum(itemTotal,2));
			opb.setDiscount(priceVo.getPriceBean().getDiscount());
			opb.setItemPrice(CullNumUtil.cullNum(itemPrice,2));
			Double itemCost = cost * cartProduct.getQuantity();
			opb.setItemCost(CullNumUtil.cullNum(itemCost, 2));
			opb.setOrderId(order.getOrderId());
			opb.setSku(cartProduct.getSku());
			opb.setProductId(pb.getProductId());
			opb.setQuantity(cartProduct.getQuantity());
			OrderProductVo orderProduct = new OrderProductVo();
			orderProduct.setOrderProduct(opb);
			orderProduct.setUseCoupon(cartProduct.getIsCouponProduct() == 1);
			orderProducts.add(orderProduct);
		}
		totalPrice = itemTotalPrice + order.getShippingTotal();
		
		OrderAddressBean oab = orderService.insertOrderAddressBean(orderAddress);

		if(order.getCouponCode() != null){
			CouponBean coupon = new CouponBean();
			coupon.setCouponCode(order.getCouponCode());
			coupon.setNormalState(2);
			orderService.updateCouponBean(coupon);
		}
		
		if(oab != null){
			order.setOrderAddressId(oab.getOrderAddressId());
		}
		//long dateTime = System.nanoTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String str = sdf.format(new Date());
		String dateTime = String.valueOf(System.nanoTime());
		int start = dateTime.length() - 5;
		int end = dateTime.length();
		long orderId = Long.valueOf(str +dateTime.substring(start, end));
		order.setOrderId(orderId);
		order.setTotal(CullNumUtil.cullNum(totalPrice));
		order.setItemTotal(CullNumUtil.cullNum(itemTotalPrice));
		
		order = orderService.insertOrderBean(order);
		addOrderstatusInfo(order.getOrderId(),order.getOrderStatus(),"","",true);
		for (OrderProductVo orderProduct : orderProducts) {
			if(orderProduct.isUseCoupon()){
				Double couponAmount = orderProduct.getOrderProduct().getItemTotal() * order.getCouponAmount() / totalCouponProductPrice;
				orderProduct.getOrderProduct().setCouponAmount(CullNumUtil.cullNum(couponAmount));
			}
			orderProduct.getOrderProduct().setOrderId(orderId);
			orderService.insertOrderProductBean(orderProduct.getOrderProduct());
		}
		
		if(null != order.getOrderId()){
			return 1;
		}else{
			return 0;
		}
	}
	
	/**
	 * 查询订单列表
	 * flag 当flag==1时表示后台显示数据，查询状态显示中文，反之而显示英文
	 * @param OrderBean
	 * @return
	 * @throws Exception
	 */
	public SearchOrderVo getSearchOrder(SearchOrderVo searchOrder, PageInfo pageInfo,String flag) throws Exception{
		SearchOrderVo sov = new SearchOrderVo();
		searchOrder.setPageInfo(pageInfo);
		searchOrder = orderService.getOrderList(searchOrder);
		if(null != searchOrder.getOrders() && searchOrder.getOrders().size()>0){
			Map<Integer, PaymentTypeBean> paymentsMap = getPaymentTypeMap();
			List<OrderVo> list = searchOrder.getOrders();
			for(int i = 0;i < list.size();i++){
				OrderVo order = list.get(i);
				String orderStatusCN = "";
				if(flag.equals("1")){
					orderStatusCN = dictService.getOrderStatusBean(order.getOrderStatus()).getStatusCn();
				}else{
					//获取当前状态英文名称
					if(order.getFreeze()!=null && order.getFreeze().equals("OF001")){
						orderStatusCN = dictService.getOrderStatusBean("OF001").getStatusEn();
					}else if(order.getRiskStatus()!=null && order.getRiskStatus().equals("OR101")){
						orderStatusCN = dictService.getOrderStatusBean("OR101").getStatusEn();
					}else if(order.getDisputeStatus()!=null){
						orderStatusCN = dictService.getOrderStatusBean(order.getDisputeStatus()).getStatusEn();
					}else if(order.getRefundStatus()!=null){
						orderStatusCN = dictService.getOrderStatusBean(order.getRefundStatus()).getStatusEn();
					}else{
						orderStatusCN = dictService.getOrderStatusBean(order.getOrderStatus()).getStatusEn();
					}
				}
				order.setOrderStatusCN(orderStatusCN);
				if(StringUtils.isNotEmpty(order.getPaymentType())) {
					order.setPaymentType(paymentsMap.get(Integer.valueOf(order.getPaymentType())).getName());
				}
				if (order.getAdjustAmount() != null) {
					order.setTotal(order.getTotal() + order.getAdjustAmount());
				}
				if (order.getCouponAmount() != null) {
					order.setTotal(order.getTotal() - order.getCouponAmount());
				}
			}
		}
		sov = orderService.getOrderStatusCount(searchOrder);
		if(sov!=null){
			searchOrder.setWaitDeliveryCount(null==sov.getWaitDeliveryCount() ? 0 : sov.getWaitDeliveryCount());
			searchOrder.setWaitPaymentcCount(sov.getWaitPaymentcCount()==null ? 0 : sov.getWaitPaymentcCount());
			searchOrder.setIsStockCount(sov.getIsStockCount()==null ? 0 : sov.getIsStockCount());
			searchOrder.setIssueCount(sov.getIssueCount()==null ? 0 : sov.getIssueCount());
		}
		return searchOrder;
	}

	/**
	 * @return
	 * @throws Exception
	 */
	public Map<Integer, PaymentTypeBean> getPaymentTypeMap() throws Exception {
		List<PaymentTypeBean> payments = paymentService.getPaymentTypeBeans(new PaymentTypeBean());
		Map<Integer, PaymentTypeBean> paymentsMap = new HashMap<Integer, PaymentTypeBean>();
		for (PaymentTypeBean paymentTypeBean : payments) {
			paymentsMap.put(paymentTypeBean.getPaymentType(), paymentTypeBean);
		}
		return paymentsMap;
	}
	
	/**根据查询条件获取所有满足条件的订单列表，不分页
	 * @param searchOrder
	 * @return
	 * @throws Exception
	 */
	public SearchOrderVo getSearchOrderList(SearchOrderVo searchOrder) throws Exception{
		searchOrder = orderService.getAllOrderList(searchOrder);
		if(null != searchOrder.getOrders() && searchOrder.getOrders().size()>0){
			List<OrderVo> list = searchOrder.getOrders();
			Map<Integer, PaymentTypeBean> paymentsMap = getPaymentTypeMap();
			for(int i = 0;i < list.size();i++){
				OrderVo order = list.get(i);
				String orderStatusCN = dictService.getOrderStatusBean(order.getOrderStatus()).getStatusCn();
				order.setOrderStatusCN(orderStatusCN);
				if(StringUtils.isNotEmpty(order.getPaymentType())) {
					order.setPaymentType(paymentsMap.get(Integer.valueOf(order.getPaymentType())).getName());
				}
				order.setShippingType(DictUtil.getShippingCompany(order.getShippingType()));
			}
		}
		return searchOrder;
	}
	
	/**获取所有的货运方式
	 * @return
	 * @throws Exception
	 */
	public List<ShippingCompanyBean> getShippingCompanys() throws Exception{
		return orderService.getShippingCompanys();
	}
	
	/** 获取单个订单信息
	 * @return
	 * @throws Exception
	 */
	public OrderBean getOrder(Long orderId)throws Exception{
		//订单信息
		OrderBean ob = new OrderBean();
		ob.setOrderId(orderId);
		ob = orderService.getOrderBean(ob);
		
/*		//订单历史状态
		OrderstatusInfoBean osib = new OrderstatusInfoBean();
		osib.setOrderId(orderId);
		osib = orderService.getOrderstatusInfoBean(osib);
		
		//订单产品
		OrderProductBean orderProduct = new OrderProductBean();
		orderProduct.setOrderId(orderId);
		List<OrderProductBean> products = orderService.getOrderProductBeans(orderProduct);
		
		//订单物流信息
		OrderShippingBean osb = new OrderShippingBean();
		osb.setOrderId(orderId);
		osb = orderService.getOrderShippingBean(osb);
		
		//订单支付信息
		OrderPaymentinfoBean opib = new OrderPaymentinfoBean();
		opib.setOrderId(orderId);
		opib = orderService.getOrderPaymentinfoBean(opib);*/
		return ob;
	}
	
	/** 获取单个订单历史状态
	 * @return
	 * @throws Exception
	 */
	public List<OrderstatusInfoBean> getOrderStatusInfo(Long orderId,String flag)throws Exception{
		//订单历史状态(flag==1查询中文,并且显示全部历史状态;反之，则显示可见状态)
		OrderstatusInfoBean osib = new OrderstatusInfoBean();
		osib.setOrderId(orderId);
		if(!(flag.equals("1"))){
			osib.setStatus(1);
		}
		List<OrderstatusInfoBean> osibList  = orderService.getOrderstatusInfoBeans(osib);
		if(osibList!=null && osibList.size()>0){
			for(OrderstatusInfoBean ordSta:osibList){
				String orderStatusCn="";
				if(flag.equals("1")){
					orderStatusCn = dictService.getOrderStatusBean(ordSta.getOrderStatus()).getStatusCn();
				}else{
					orderStatusCn = dictService.getOrderStatusBean(ordSta.getOrderStatus()).getStatusEn();
				}
				ordSta.setOrderStatusCn(orderStatusCn);
			}
		}
		return osibList;
	}
	
	
	/** 获取单个订单产品
	 * @return
	 * @throws Exception
	 */
	public List<OrderProductBean> getOrderProductInfo(Long orderId)throws Exception{
		OrderProductBean orderProduct = new OrderProductBean();
		orderProduct.setOrderId(orderId);
		List<OrderProductBean> products = orderService.getOrderProductBeans(orderProduct);

		return products;
	}
	
	/** 订单物流信息
	 * @return
	 * @throws Exception
	 */
	public List<OrderShippingBean> getOrderShippingInfo(Long orderId)throws Exception{
		//订单历史状态
		OrderShippingBean osb = new OrderShippingBean();
		osb.setOrderId(orderId);
		List<OrderShippingBean> orderShippingList = orderService.getOrderShippingBean(osb);
		return orderShippingList;
	}
	
	/** 获取单个订单支付信息
	 * @return
	 * @throws Exception
	 */
	public OrderPaymentinfoBean getOrderPaymentinfo(Long orderId)throws Exception{
		//订单支付信息
		OrderPaymentinfoBean opib = new OrderPaymentinfoBean();
		opib.setOrderId(orderId);
		opib = orderService.getOrderPaymentinfoBean(opib);
		return opib;
	}
	
	/** 获取单个订单支付信息Vo
	 * @return
	 * @throws Exception
	 */
	public PaymentInfoVo getPaymentinfoVo(Long orderId)throws Exception {
		OrderPaymentinfoBean bean = getOrderPaymentinfo(orderId);
		if (bean == null) {
			return null;
		}
		PaymentInfoVo info = new PaymentInfoVo();
		info.setPaymentInfoBean(bean);
		info.setPayTime(StringUtil.getDateTimeString(bean.getPayTime()));
		
		PaymentTypeBean payType = new PaymentTypeBean();
		payType.setPaymentType(bean.getPaymentType());
		payType = paymentService.getPaymentTypeBean(payType);
		info.setPaymentType(payType.getName());
		
		if (bean.getPaymentType() == 1) { // Paypal
			PaypalipnBean ipn = new PaypalipnBean();
			ipn.setId(bean.getPayMethodId());
			ipn = paymentService.getPaypalipnBean(ipn);
			if (ipn != null) {
				info.setBillAddress(ipn.getAddressStreet() + ", " + ipn.getAddressCity() + ", " + ipn.getAddressState() + ", " + ipn.getAddressCountry());
			}
			info.setBillEmail(ipn.getPayerEmail());
			info.setPaypalVerified(ipn.getPayerStatus());
		}
		return info;
	}
	
	/** 获取单个订单货运地址
	 * @return
	 * @throws Exception
	 */
	public OrderAddressBean getOrderAddressInfo(Long orderAddressId)throws Exception{
		OrderAddressBean oab = new OrderAddressBean();
		oab.setOrderAddressId(orderAddressId);
		oab = orderService.getOrderAddressBean(oab);
		return oab;
	}
	
	/** 获取买家历史订单(如果num<0则查询所有的历史订单)
	 * @return
	 * @throws Exception
	 */
	public List<OrderVo> getBuyerOrderList(Long buyerId,int num) throws Exception{
		HashMap<String,Long> hm = new HashMap<String,Long>();
		hm.put("buyerId", buyerId);
		hm.put("num", Long.valueOf(num));
		List<OrderVo> osibList = orderService.getBuyerOrderList(hm);
		for (OrderVo o : osibList) {
			OrderAddressBean addressBean = getOrderAddressInfo(o.getOrderAddressId());
			o.setAddressBean(addressBean);
		}
		return osibList;
	}
	
	/**
	 * 调整订单金额
	 * 
	 * @return 
	 * @throws Exception
	 */
	public int adjustOrderAmount(long orderId, double amount, String operator) throws Exception{
		OrderBean order = new OrderBean(); 
		order.setOrderId(orderId);
		order = orderService.getOrderBean(order);
		
		OrderstatusInfoBean status = orderService.getLastOrderStatusinfo(orderId);

		double total = order.getTotal();
		Double oldAmount = order.getAdjustAmount();
		if (oldAmount != null) {
			total += oldAmount;
			order.setAdjustAmount(amount + oldAmount);
		} else {
			order.setAdjustAmount(amount);
		}
		total += amount;

		String memo = "The total amount of order is adjusted to "+ total +" dollars by adding ("+ amount + ") dollars" ;
		
		int flag = orderService.updateOrderBean(order);
		if(flag == 1){
			this.addOrderstatusInfo(order.getOrderId(), status.getOrderStatus(), memo, "SYSTEM", true);
			this.addOrderAmount(order.getOrderId(), amount, OrderUtil.AMOUNT_TYPE_ADJUST, operator, memo);
		}
		return flag;
	}
	
	/**
	 * 查询订单所有物流跟踪号
	 * @return 
	 * @throws Exception
	 */
	public List<OrderShippingBean> getOrderShipping(Long orderId) throws Exception{
		OrderShippingBean orderShipping = new OrderShippingBean();
		orderShipping.setOrderId(orderId);
		List<OrderShippingBean> orderShippings = orderService.getOrderShippingBeans(orderShipping);
		return orderShippings;
	}
	
	/**
	 * 购物车产品入库
	 * 
	 * @param cart
	 * @return
	 * @throws Exception
	 */
	public ShoppingCartBean addShoppingCart(CartProductVo cartProd, long buyerId) throws Exception {
		ShoppingCartBean cartBean = new ShoppingCartBean();
		cartBean.setBuyerId(buyerId);
		cartBean.setProductId(cartProd.getProductId());
		cartBean.setStandardId(cartProd.getStandardId());
		cartBean = orderService.getShoppingCartBean(cartBean);
		if (cartBean == null) {
			cartBean = new ShoppingCartBean();
			PropertyUtils.copyProperties(cartBean, cartProd);
			cartBean.setBuyerId(buyerId);
			cartBean.setCreateDate(new Date());
			return orderService.insertShoppingCartBean(cartBean);
		}
		return cartBean;
	}
	
	/**
	 * 查询购物车
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<CartProductVo> getShoppingCart(long buyerId) throws Exception {
		ShoppingCartBean cartBean = new ShoppingCartBean();
		cartBean.setBuyerId(buyerId);
		List<ShoppingCartBean> list = orderService.getShoppingCartBeans(cartBean);
		if (list != null && list.size()>0) {
			List<CartProductVo> carts = new ArrayList<CartProductVo>();
			for (ShoppingCartBean shoppingCartBean : list) {
				CartProductVo cartProd = new CartProductVo();
				PropertyUtils.copyProperties(cartProd, shoppingCartBean);
				// 产品信息
				ProductBean prod = new ProductBean();
				prod.setProductId(cartProd.getProductId());
				prod = productService.getProductBean(prod);
				if (prod.getProductStatus() != ProductConstants.ONSALE) {
					cartProd.setOffSale(true);
				}
				if (prod.getEditTime().after(shoppingCartBean.getCreateDate())) {
					cartProd.setChanged(true);
				}
				
				carts.add(cartProd);
			}
			return carts;
		}
		return null;
	}
	
	/**
	 * 购物车产品更新入库
	 * 
	 * @param cart
	 * @return
	 * @throws Exception
	 */
	public ShoppingCartBean updateShoppingCart(CartProductVo cartProd, long buyerId) throws Exception {
		ShoppingCartBean cartBean = new ShoppingCartBean();
		cartBean.setBuyerId(buyerId);
		cartBean.setProductId(cartProd.getProductId());
		cartBean.setStandardId(cartProd.getStandardId());
		cartBean = orderService.getShoppingCartBean(cartBean);
		if (cartBean != null) {
			PropertyUtils.copyProperties(cartBean, cartProd);
			cartBean.setBuyerId(buyerId);
			cartBean.setCreateDate(new Date());
			orderService.updateShoppingCartBean(cartBean);
			return cartBean;
		} else {
			return addShoppingCart(cartProd, buyerId);
		}
	}
	
	/**
	 * 购物车产品删除
	 * 
	 * @param cart
	 * @return
	 * @throws Exception
	 */
	public int deleteShoppingCart(CartProductVo cartProd, long buyerId) throws Exception {
		ShoppingCartBean cartBean = new ShoppingCartBean();
		cartBean.setBuyerId(buyerId);
		cartBean.setProductId(cartProd.getProductId());
		cartBean.setStandardId(cartProd.getStandardId());
		return orderService.deleteShoppingCartBean(cartBean);
	}
	
	/** 获取买家历史订单(查询所有的历史订单)
	 * @return
	 * @throws Exception
	 */
	public List<OrderVo> getBuyerOrderListory(Long buyerId,PageInfo pageInfo) throws Exception{
		BuyerBean buyer = new BuyerBean();
		buyer.setBuyerId(buyerId);
		List<OrderVo> osibList = orderService.getBuyerOrderListory(buyer, pageInfo);
		if(osibList!=null){
			for(OrderVo order:osibList){
				if(order!=null && order.getPaymentInfoId()!=null && order.getPaymentInfoId()>0){
					PaymentInfoVo paymentInfo = getPaymentinfoVo(order.getOrderId());
					order.setPaymentDate(paymentInfo.getPaymentInfoBean().getPayTime());
					order.setPaymentType(paymentInfo.getPaymentType());
				}
			}
			
		}
		return osibList;
	}
	
	/**
	 * 获取购物车
	 * 
	 * @param shoppingCart
	 * @return
	 * @throws Exception
	 */
	public List<ShoppingCartBean> getShoppingCartBeans(ShoppingCartBean shoppingCart,PageInfo pageInfo) throws Exception{
		return orderService.getShoppingCartBeans(shoppingCart,pageInfo);
	}
	
	/**
	 * 更新订单
	 * 
	 * @param o
	 * @return
	 * @throws Exception
	 */
	public int updateOrder(OrderBean o) throws Exception {
		int i = orderService.updateOrderBean(o);
		if (i<=0) {
			throw new Exception("更新订单状态出错");
		}
		return i;
	}
	
	/**获取(某个买家)所有的支付方式
	 * @param buyerId
	 * @return
	 * @throws Exception
	 */
	public List<PaymentTypeBean> getBuyerPaymentType(long buyerId) throws Exception{
		if(buyerId>0){
			return orderService.getBuyerPaymentType(buyerId);
		}else{
			return orderService.getBuyerPaymentType();
		}
	}
	
	/**
	 * 通过买家ID获取该买家所有的订单
	 * 
	 * @param buyerId
	 * @return
	 * @throws Exception
	 */
	public List<OrderBean> getOrderBeans(long buyerId) throws Exception {
		OrderBean order = new OrderBean();
		order.setBuyerId(buyerId);
		return orderService.getOrderBeans(order);
	}
	
	/**引用字典库
	 * @return
	 * @throws Exception
	 */
	public Map<String,PaymentTypeBean> getPaymentTypeList() throws Exception{
		List<PaymentTypeBean> listPay = getBuyerPaymentType(0);
		Map<String,PaymentTypeBean> map = new HashMap<String,PaymentTypeBean>();
		for(PaymentTypeBean pt :listPay){
			map.put(String.valueOf(pt.getPaymentType()), pt);
		}
		return map;
	}
	
	/**
	 * 获取产品订单数
	 * 
	 * @param productId
	 * @return
	 * @throws Exception
	 */
	public int getProductOrderCount(long productId) throws Exception {
		return orderService.getProductOrderCount(productId);
	}
	
	/**通过产品ID获取该产品最近被购买时间
	 * @param productId
	 * @return
	 * @throws Exception
	 */
	public HashMap<String,Object> getOrderRecentBuyTimeByProductId(long productId,long buyerId) throws Exception{
		HashMap<String,Long> ids = new HashMap<String,Long>();
		ids.put("buyerId", buyerId);
		ids.put("productId", productId);
		HashMap<String,Object> map = new HashMap<String,Object>();
		OrderBean order  = new OrderBean();
		order = orderService.getOrderRecentBuyTimeByProductId(ids);
		if(order!=null){
			map.put("createDate", order.getCreateDate());
			OrderProductBean orderProd = new OrderProductBean();
			orderProd.setProductId(productId);
			orderProd.setOrderId(order.getOrderId());
			orderProd = orderService.getOrderProductBean(orderProd);
			if(orderProd!=null){
				map.put("quantity", orderProd.getQuantity());
			}
		}
		return map;
	}
	
	/**
	 * 增加问题订单池
	 * 
	 * @param problemOrder
	 * @return
	 * @throws Exception
	 */
	public ProblemOrderBean insertProblemOrderBean(ProblemOrderBean problemOrder) throws Exception{
		return orderService.insertProblemOrderBean(problemOrder);
	}
	
	/**获取所有符合条件的打印订单
	 * @param orderScanVo
	 * @return
	 * @throws Exception
	 */
	public List<OrderScanVo> getPrintOrderList(OrderScanVo orderScanVo,PageInfo pageInfo) throws Exception{
		List<OrderScanVo> printList = orderService.getPrintOrderList(orderScanVo, pageInfo);
		if(printList!=null && printList.size()>0){
			Map<String,PaymentTypeBean> map = getPaymentTypeList();
			for(OrderScanVo ov :printList){
				String orderStatusName=  dictService.getOrderStatusBean(ov.getOrderStatus()).getStatusCn();
				ov.setOrderStatusName(orderStatusName);
				if(ov.getPaymentType()!=null && ov.getPaymentType()>0){
					ov.setPaymentTypeName(map.get(String.valueOf(ov.getPaymentType())).getDescription());
				}
				RegionBean region = new RegionBean();
				region.setShortName(ov.getShippingCountry());
				region = dictService.getRegionBean(region);
				if(region!=null){
					ov.setCountryName(region.getRegionNameCn()+"("+region.getRegionName()+")");
				}
				List<OrderProductBean> orderProducts = getOrderProductInfo(ov.getOrderNo());
				if(orderProducts!=null && orderProducts.size()>0){
					double totalWeight = 0;
					int totalCount = 0;
					for (OrderProductBean orderPro : orderProducts) {
						totalCount = totalCount + orderPro.getQuantity();
						ProductBean p = new ProductBean();
						p.setProductId(orderPro.getProductId());
						p = productService.getProductBean(p);
						if(p!=null && p.getWeight()!=null){
							totalWeight = totalWeight+orderPro.getQuantity()*p.getWeight();
						}
					}
					ov.setTotalCount(totalCount);
					ov.setTotalWeight(totalWeight);
				}
			}
			return printList;
		}
		return null;
	}
	
	/**
	 * 增加拣货
	 * 
	 * @param picking
	 * @return
	 * @throws Exception
	 */
	public PickingBean insertPickingBean(PickingBean picking) throws Exception {
		return orderService.insertPickingBean(picking);
	}
	
	/**
	 * 获取拣货
	 * 
	 * @param picking
	 * @return
	 * @throws Exception
	 */
	public List<PickingBean> getPickingBeans(PickingBean picking,
			PageInfo pageInfo) throws Exception{
		return orderService.getPickingBeans(picking, pageInfo);
	}
	
	/**查询打印订单信息(订单拣货人的信息)
	 * @param picking
	 * @return
	 * @throws Exception
	 */
	public PickingBean getPickingBean(PickingBean picking)throws Exception{
		return orderService.getPickingBean(picking);
	}
	
	/**更新拣货信息
	 * @param picking
	 * @return
	 * @throws Exception
	 */
	public int updatePickingBean(PickingBean picking) throws Exception{
		return orderService.updatePickingBean(picking);
	}
	
	/**
	 * 增加拣货信息
	 * 
	 * @param pickingInfo
	 * @return
	 * @throws Exception
	 */
	public PickingInfoBean insertPickingInfoBean(PickingInfoBean pickingInfo)
			throws Exception {
		return orderService.insertPickingInfoBean(pickingInfo);
	}

	/**
	 * 修改拣货信息
	 * 
	 * @param pickingInfo
	 * @return
	 * @throws Exception
	 */
	public int updatePickingInfoBean(PickingInfoBean pickingInfo)
			throws Exception {
		return orderService.updatePickingInfoBean(pickingInfo);
	}

	/**
	 * 获取拣货信息
	 * 
	 * @param pickingInfo
	 * @return
	 * @throws Exception
	 */
	public PickingInfoBean getPickingInfoBean(PickingInfoBean pickingInfo)
			throws Exception {
		return orderService.getPickingInfoBean(pickingInfo);
	}
	
	/**
	 * 删除拣货信息
	 * 
	 * @param pickingInfo
	 * @return
	 * @throws Exception
	 */
	public int deletePickingInfoBean(PickingInfoBean pickingInfo)
			throws Exception {
		return orderService.deletePickingInfoBean(pickingInfo);
	}
	
	/**
	 * 获取拣货信息列表
	 * 
	 * @param orderId
	 * @return
	 * @throws Exception
	 */
	public List<PickingInfoBean> getPickingInfoBeans(Long orderId)
			throws Exception {
		PickingInfoBean pif = new PickingInfoBean();
		pif.setOrderId(orderId);
		return orderService.getPickingInfoBeans(pif);
	}
	
	/**
	 * 生成订单产品快照
	 * 
	 * @param productId
	 * @return
	 * @throws Exception
	 */
	public int makeOrderProdSnapshot(long productId) throws Exception {
		List<OrderProductBean> orderProds = orderService.getNeedsSnapshotProd(productId);
		int i = 0;
		if (orderProds != null && orderProds.size()>0) {
			// 生成快照
			WebPropUtil wpu = new WebPropUtil();
			HttpClient client = new HttpClient();
			GetMethod method = new GetMethod(wpu.get("product.snapshot.url")+"?snapshot=1&productId=" + productId);
			int stat = client.executeMethod(method);
			if (stat != 200) {
				throw new Exception("生成快照失败:" + stat);
			}
			String content = method.getResponseBodyAsString();

			String snapshot = wpu.get("cms.root");
	
			//创建文件夹
			snapshot += "/prod_snapshot/";
			File saveDirFile = new File(snapshot);
			if (!saveDirFile.exists()) {
				saveDirFile.mkdirs();
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			String ymd = sdf.format(new Date());
			snapshot += ymd ;
			long dateTime = System.nanoTime();
			snapshot += "/" + String.valueOf(dateTime) + ".html";
			
			FileUtil.writeFile(content, snapshot);
				
			for (OrderProductBean op : orderProds) {
				op.setSnapshot(snapshot);
				i += orderService.updateOrderProductBean(op);
			}
		}
		return i;
	}
}
