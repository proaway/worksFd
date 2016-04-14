package com.fd.fashion.order.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.fd.dao.IBaseDao;
import com.fd.fashion.buyer.bean.BuyerBean;
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
import com.fd.fashion.order.bean.RiskSettingBean;
import com.fd.fashion.order.bean.ShippingCompanyBean;
import com.fd.fashion.order.bean.ShoppingCartBean;
import com.fd.fashion.order.vo.OrderScanVo;
import com.fd.fashion.order.vo.OrderVo;
import com.fd.fashion.order.vo.SearchOrderVo;
import com.fd.payment.bean.PaymentTypeBean;
import com.fd.util.PageInfo;

@Component
@SuppressWarnings("unchecked")
public class OrderService implements IOrderService {
	@Autowired
	@Qualifier("dao")
	private IBaseDao dao;

	/**
	 * 增加订单地址
	 * 
	 * @param orderAddress
	 * @return
	 * @throws Exception
	 */
	public OrderAddressBean insertOrderAddressBean(OrderAddressBean orderAddress)
			throws Exception {
		dao.insertObj("insertOrderAddressBean", orderAddress);
		return orderAddress;
	}

	/**
	 * 修改订单地址
	 * 
	 * @param orderAddress
	 * @return
	 * @throws Exception
	 */
	public int updateOrderAddressBean(OrderAddressBean orderAddress)
			throws Exception {
		return dao.updateObj("updateOrderAddressBean", orderAddress);
	}

	/**
	 * 获取订单地址
	 * 
	 * @param orderAddress
	 * @return
	 * @throws Exception
	 */
	public OrderAddressBean getOrderAddressBean(OrderAddressBean orderAddress)
			throws Exception {
		return (OrderAddressBean) dao.getAsObject("getOrderAddressBean",
				orderAddress);
	}

	/**
	 * 获取订单地址
	 * 
	 * @param orderAddress
	 * @return
	 * @throws Exception
	 */
	public List<OrderAddressBean> getOrderAddressBeans(
			OrderAddressBean orderAddress) throws Exception {
		return dao.getAsList("getOrderAddressBean", orderAddress);
	}

	/**
	 * 获取订单地址
	 * 
	 * @param orderAddress
	 * @return
	 * @throws Exception
	 */
	public List<OrderAddressBean> getOrderAddressBeans(
			OrderAddressBean orderAddress, PageInfo pageInfo) throws Exception {
		Integer count = (Integer) dao.getAsObject("getOrderAddressBeanCount",
				orderAddress);
		pageInfo.setRecordCount(count == null ? 0 : count);
		if (count != null && count > 0) {
			return dao.getAsList("getOrderAddressBean", orderAddress, pageInfo);
		}
		return null;
	}

	/**
	 * 删除订单地址
	 * 
	 * @param orderAddress
	 * @return
	 * @throws Exception
	 */
	public int deleteOrderAddressBean(OrderAddressBean orderAddress)
			throws Exception {
		return dao.deleteObj("deleteOrderAddressBean", orderAddress);
	}

	/**
	 * 增加订单支付情况信息
	 * 
	 * @param orderPaymentinfo
	 * @return
	 * @throws Exception
	 */
	public OrderPaymentinfoBean insertOrderPaymentinfoBean(
			OrderPaymentinfoBean orderPaymentinfo) throws Exception {
		dao.insertObj("insertOrderPaymentinfoBean", orderPaymentinfo);
		return orderPaymentinfo;
	}

	/**
	 * 修改订单支付情况信息
	 * 
	 * @param orderPaymentinfo
	 * @return
	 * @throws Exception
	 */
	public int updateOrderPaymentinfoBean(OrderPaymentinfoBean orderPaymentinfo)
			throws Exception {
		return dao.updateObj("updateOrderPaymentinfoBean", orderPaymentinfo);
	}

	/**
	 * 获取订单支付情况信息
	 * 
	 * @param orderPaymentinfo
	 * @return
	 * @throws Exception
	 */
	public OrderPaymentinfoBean getOrderPaymentinfoBean(
			OrderPaymentinfoBean orderPaymentinfo) throws Exception {
		return (OrderPaymentinfoBean) dao.getAsObject(
				"getOrderPaymentinfoBean", orderPaymentinfo);
	}

	/**
	 * 获取订单支付情况信息
	 * 
	 * @param orderPaymentinfo
	 * @return
	 * @throws Exception
	 */
	public List<OrderPaymentinfoBean> getOrderPaymentinfoBeans(
			OrderPaymentinfoBean orderPaymentinfo) throws Exception {
		return dao.getAsList("getOrderPaymentinfoBean", orderPaymentinfo);
	}

	/**
	 * 获取订单支付情况信息
	 * 
	 * @param orderPaymentinfo
	 * @return
	 * @throws Exception
	 */
	public List<OrderPaymentinfoBean> getOrderPaymentinfoBeans(
			OrderPaymentinfoBean orderPaymentinfo, PageInfo pageInfo)
			throws Exception {
		Integer count = (Integer) dao.getAsObject(
				"getOrderPaymentinfoBeanCount", orderPaymentinfo);
		pageInfo.setRecordCount(count == null ? 0 : count);
		if (count != null && count > 0) {
			return dao.getAsList("getOrderPaymentinfoBean", orderPaymentinfo,
					pageInfo);
		}
		return null;
	}

	/**
	 * 删除订单支付情况信息
	 * 
	 * @param orderPaymentinfo
	 * @return
	 * @throws Exception
	 */
	public int deleteOrderPaymentinfoBean(OrderPaymentinfoBean orderPaymentinfo)
			throws Exception {
		return dao.deleteObj("deleteOrderPaymentinfoBean", orderPaymentinfo);
	}

	/**
	 * 增加订单产品
	 * 
	 * @param orderProduct
	 * @return
	 * @throws Exception
	 */
	public OrderProductBean insertOrderProductBean(OrderProductBean orderProduct)
			throws Exception {
		dao.insertObj("insertOrderProductBean", orderProduct);
		return orderProduct;
	}

	/**
	 * 修改订单产品
	 * 
	 * @param orderProduct
	 * @return
	 * @throws Exception
	 */
	public int updateOrderProductBean(OrderProductBean orderProduct)
			throws Exception {
		return dao.updateObj("updateOrderProductBean", orderProduct);
	}

	/**
	 * 获取订单产品
	 * 
	 * @param orderProduct
	 * @return
	 * @throws Exception
	 */
	public OrderProductBean getOrderProductBean(OrderProductBean orderProduct)
			throws Exception {
		return (OrderProductBean) dao.getAsObject("getOrderProductBean",
				orderProduct);
	}

	/**
	 * 获取订单产品
	 * 
	 * @param orderProduct
	 * @return
	 * @throws Exception
	 */
	public List<OrderProductBean> getOrderProductBeans(
			OrderProductBean orderProduct) throws Exception {
		return dao.getAsList("getOrderProductBean", orderProduct);
	}

	/**
	 * 获取订单产品
	 * 
	 * @param orderProduct
	 * @return
	 * @throws Exception
	 */
	public List<OrderProductBean> getOrderProductBeans(
			OrderProductBean orderProduct, PageInfo pageInfo) throws Exception {
		Integer count = (Integer) dao.getAsObject("getOrderProductBeanCount",
				orderProduct);
		pageInfo.setRecordCount(count == null ? 0 : count);
		if (count != null && count > 0) {
			return dao.getAsList("getOrderProductBean", orderProduct, pageInfo);
		}
		return null;
	}

	/**
	 * 删除订单产品
	 * 
	 * @param orderProduct
	 * @return
	 * @throws Exception
	 */
	public int deleteOrderProductBean(OrderProductBean orderProduct)
			throws Exception {
		return dao.deleteObj("deleteOrderProductBean", orderProduct);
	}

	/**
	 * 增加coupon
	 * 
	 * @param tCoupon
	 * @return
	 * @throws Exception
	 */
	public CouponBean insertCouponBean(CouponBean tCoupon) throws Exception {
		dao.insertObj("insertCouponBean", tCoupon);
		return tCoupon;
	}

	/**
	 * 修改coupon
	 * 
	 * @param tCoupon
	 * @return
	 * @throws Exception
	 */
	public int updateCouponBean(CouponBean tCoupon) throws Exception {
		return dao.updateObj("updateCouponBean", tCoupon);
	}

	/**
	 * 获取coupon
	 * 
	 * @param tCoupon
	 * @return
	 * @throws Exception
	 */
	public List<CouponBean> getCouponBeans(CouponBean tCoupon) throws Exception {
		return dao.getAsList("getCouponBean", tCoupon);
	}

	/**
	 * 获取coupon
	 * 
	 * @param tCoupon
	 * @return
	 * @throws Exception
	 */
	public List<CouponBean> getCouponBeans(CouponBean tCoupon, PageInfo pageInfo)
			throws Exception {
		tCoupon.setPageInfo(pageInfo);
		Integer count = (Integer) dao
				.getAsObject("getCouponBeanCount", tCoupon);
		pageInfo.setRecordCount(count == null ? 0 : count);
		if (count != null && count > 0) {
			List<CouponBean> couponList = dao.getAsList("getCouponBean",
					tCoupon);
			return couponList;
		}
		return null;
	}

	/**
	 * 删除coupon
	 * 
	 * @param tCoupon
	 * @return
	 * @throws Exception
	 */
	public int deleteCouponBean(CouponBean tCoupon) throws Exception {
		return dao.deleteObj("deleteCouponBean", tCoupon);
	}

	/**
	 * 增加风控设置
	 * 
	 * @param riskSetting
	 * @return
	 * @throws Exception
	 */
	public RiskSettingBean insertRiskSettingBean(RiskSettingBean riskSetting)
			throws Exception {
		dao.insertObj("insertRiskSettingBean", riskSetting);
		return riskSetting;
	}

	/**
	 * 修改风控设置
	 * 
	 * @param riskSetting
	 * @return
	 * @throws Exception
	 */
	public int updateRiskSettingBean(RiskSettingBean riskSetting)
			throws Exception {
		return dao.updateObj("updateRiskSettingBean", riskSetting);
	}

	/**
	 * 获取风控设置
	 * 
	 * @param riskSetting
	 * @return
	 * @throws Exception
	 */
	public List<RiskSettingBean> getRiskSettingBeans(RiskSettingBean riskSetting)
			throws Exception {
		return dao.getAsList("getRiskSettingBean", riskSetting);
	}

	/**
	 * 获取风控设置
	 * 
	 * @param riskSetting
	 * @return
	 * @throws Exception
	 */
	public List<RiskSettingBean> getRiskSettingBeans(
			RiskSettingBean riskSetting, PageInfo pageInfo) throws Exception {
		Integer count = (Integer) dao.getAsObject("getRiskSettingBeanCount",
				riskSetting);
		pageInfo.setRecordCount(count == null ? 0 : count);
		if (count != null && count > 0) {
			return dao.getAsList("getRiskSettingBean", riskSetting, pageInfo);
		}
		return null;
	}

	/**
	 * 删除风控设置
	 * 
	 * @param riskSetting
	 * @return
	 * @throws Exception
	 */
	public int deleteRiskSettingBean(RiskSettingBean riskSetting)
			throws Exception {
		return dao.deleteObj("deleteRiskSettingBean", riskSetting);
	}

	/**
	 * 增加订单货运
	 * 
	 * @param orderShipping
	 * @return
	 * @throws Exception
	 */
	public OrderShippingBean insertOrderShippingBean(
			OrderShippingBean orderShipping) throws Exception {
		dao.insertObj("insertOrderShippingBean", orderShipping);
		return orderShipping;
	}

	/**
	 * 修改订单货运
	 * 
	 * @param orderShipping
	 * @return
	 * @throws Exception
	 */
	public int updateOrderShippingBean(OrderShippingBean orderShipping)
			throws Exception {
		return dao.updateObj("updateOrderShippingBean", orderShipping);
	}

	/**
	 * 获取订单货运
	 * 
	 * @param orderShipping
	 * @return
	 * @throws Exception
	 */
	public List<OrderShippingBean> getOrderShippingBean(
			OrderShippingBean orderShipping) throws Exception {
		return dao.getAsList("getOrderShippingBean", orderShipping);
	}

	/**
	 * 获取订单货运
	 * 
	 * @param orderShipping
	 * @return
	 * @throws Exception
	 */
	public List<OrderShippingBean> getOrderShippingBeans(
			OrderShippingBean orderShipping) throws Exception {
		return dao.getAsList("getOrderShippingBean", orderShipping);
	}

	/**
	 * 获取订单货运
	 * 
	 * @param orderShipping
	 * @return
	 * @throws Exception
	 */
	public List<OrderShippingBean> getOrderShippingBeans(
			OrderShippingBean orderShipping, PageInfo pageInfo)
			throws Exception {
		Integer count = (Integer) dao.getAsObject("getOrderShippingBeanCount",
				orderShipping);
		pageInfo.setRecordCount(count == null ? 0 : count);
		if (count != null && count > 0) {
			return dao.getAsList("getOrderShippingBean", orderShipping,
					pageInfo);
		}
		return null;
	}

	/**
	 * 删除订单货运
	 * 
	 * @param orderShipping
	 * @return
	 * @throws Exception
	 */
	public int deleteOrderShippingBean(OrderShippingBean orderShipping)
			throws Exception {
		return dao.deleteObj("deleteOrderShippingBean", orderShipping);
	}

	/**
	 * 增加订单状态信息
	 * 
	 * @param orderstatusInfo
	 * @return
	 * @throws Exception
	 */
	public OrderstatusInfoBean insertOrderstatusInfoBean(
			OrderstatusInfoBean orderstatusInfo) throws Exception {
		dao.insertObj("insertOrderstatusInfoBean", orderstatusInfo);
		return orderstatusInfo;
	}

	/**
	 * 修改订单状态信息
	 * 
	 * @param orderstatusInfo
	 * @return
	 * @throws Exception
	 */
	public int updateOrderstatusInfoBean(OrderstatusInfoBean orderstatusInfo)
			throws Exception {
		return dao.updateObj("updateOrderstatusInfoBean", orderstatusInfo);
	}

	/**
	 * 获取订单状态信息
	 * 
	 * @param orderstatusInfo
	 * @return
	 * @throws Exception
	 */
	public OrderstatusInfoBean getOrderstatusInfoBean(
			OrderstatusInfoBean orderstatusInfo) throws Exception {
		return (OrderstatusInfoBean) dao.getAsObject("getOrderstatusInfoBean",
				orderstatusInfo);
	}

	/**
	 * 获取订单状态信息
	 * 
	 * @param orderstatusInfo
	 * @return
	 * @throws Exception
	 */
	public List<OrderstatusInfoBean> getOrderstatusInfoBeans(
			OrderstatusInfoBean orderstatusInfo) throws Exception {
		return dao.getAsList("getOrderstatusInfoBean", orderstatusInfo);
	}

	/**
	 * 获取订单状态信息
	 * 
	 * @param orderstatusInfo
	 * @return
	 * @throws Exception
	 */
	public List<OrderstatusInfoBean> getOrderstatusInfoBeans(
			OrderstatusInfoBean orderstatusInfo, PageInfo pageInfo)
			throws Exception {
		Integer count = (Integer) dao.getAsObject(
				"getOrderstatusInfoBeanCount", orderstatusInfo);
		pageInfo.setRecordCount(count == null ? 0 : count);
		if (count != null && count > 0) {
			return dao.getAsList("getOrderstatusInfoBean", orderstatusInfo,
					pageInfo);
		}
		return null;
	}

	/**
	 * 删除订单状态信息
	 * 
	 * @param orderstatusInfo
	 * @return
	 * @throws Exception
	 */
	public int deleteOrderstatusInfoBean(OrderstatusInfoBean orderstatusInfo)
			throws Exception {
		return dao.deleteObj("deleteOrderstatusInfoBean", orderstatusInfo);
	}

	/**
	 * 增加订单
	 * 
	 * @param order
	 * @return
	 * @throws Exception
	 */
	public OrderBean insertOrderBean(OrderBean order) throws Exception {
		dao.insertObj("insertOrderBean", order);
		return order;
	}

	/**
	 * 修改订单
	 * 
	 * @param order
	 * @return
	 * @throws Exception
	 */
	public int updateOrderBean(OrderBean order) throws Exception {
		return dao.updateObj("updateOrderBean", order);
	}

	/**
	 * 获取订单
	 * 
	 * @param order
	 * @return
	 * @throws Exception
	 */
	public OrderBean getOrderBean(OrderBean order) throws Exception {
		return (OrderBean) dao.getAsObject("getOrderBean", order);
	}

	/**
	 * 获取订单
	 * 
	 * @param order
	 * @return
	 * @throws Exception
	 */
	public List<OrderBean> getOrderBeans(OrderBean order) throws Exception {
		return dao.getAsList("getOrderBean", order);
	}

	/**
	 * 获取订单
	 * 
	 * @param order
	 * @return
	 * @throws Exception
	 */
	public List<OrderBean> getOrderBeans(OrderBean order, PageInfo pageInfo)
			throws Exception {
		Integer count = (Integer) dao.getAsObject("getOrderBeanCount", order);
		pageInfo.setRecordCount(count == null ? 0 : count);
		if (count != null && count > 0) {
			return dao.getAsList("getOrderBean", order, pageInfo);
		}
		return null;
	}

	/**
	 * 删除订单
	 * 
	 * @param order
	 * @return
	 * @throws Exception
	 */
	public int deleteOrderBean(OrderBean order) throws Exception {
		return dao.deleteObj("deleteOrderBean", order);
	}

	/**
	 * 增加订单金额历史
	 * 
	 * @param orderAmount
	 * @return
	 * @throws Exception
	 */
	public OrderAmountBean insertOrderAmountBean(OrderAmountBean orderAmount)
			throws Exception {
		dao.insertObj("insertOrderAmountBean", orderAmount);
		return orderAmount;
	}

	/**
	 * 修改订单金额历史
	 * 
	 * @param orderAmount
	 * @return
	 * @throws Exception
	 */
	public int updateOrderAmountBean(OrderAmountBean orderAmount)
			throws Exception {
		return dao.updateObj("updateOrderAmountBean", orderAmount);
	}

	/**
	 * 获取订单金额历史
	 * 
	 * @param orderAmount
	 * @return
	 * @throws Exception
	 */
	public OrderAmountBean getOrderAmountBean(OrderAmountBean orderAmount)
			throws Exception {
		return (OrderAmountBean) dao.getAsObject("getOrderAmountBean",
				orderAmount);
	}

	/**
	 * 获取订单金额历史
	 * 
	 * @param orderAmount
	 * @return
	 * @throws Exception
	 */
	public List<OrderAmountBean> getOrderAmountBeans(OrderAmountBean orderAmount)
			throws Exception {
		return dao.getAsList("getOrderAmountBean", orderAmount);
	}

	/**
	 * 获取订单金额历史
	 * 
	 * @param orderAmount
	 * @return
	 * @throws Exception
	 */
	public List<OrderAmountBean> getOrderAmountBeans(
			OrderAmountBean orderAmount, PageInfo pageInfo) throws Exception {
		Integer count = (Integer) dao.getAsObject("getOrderAmountBeanCount",
				orderAmount);
		pageInfo.setRecordCount(count == null ? 0 : count);
		if (count != null && count > 0) {
			return dao.getAsList("getOrderAmountBean", orderAmount, pageInfo);
		}
		return null;
	}

	/**
	 * 删除订单金额历史
	 * 
	 * @param orderAmount
	 * @return
	 * @throws Exception
	 */
	public int deleteOrderAmountBean(OrderAmountBean orderAmount)
			throws Exception {
		return dao.deleteObj("deleteOrderAmountBean", orderAmount);
	}

	/**
	 * 获取订单管理列表
	 * 
	 * @param order
	 * @return
	 * @throws Exception
	 */
	public SearchOrderVo getOrderList(SearchOrderVo searchOrder)
			throws Exception {
		SearchOrderVo search = (SearchOrderVo) dao.getAsObject(
				"getOrderListCount", searchOrder);

		if (search != null) {
			int count = 0;
			if (search.getOrderCount() != null) {
				count = search.getOrderCount();
			}
			double orderPrice = 0;
			if (search.getOrderPriceTotal() != null) {
				orderPrice = search.getOrderPriceTotal();
			}
			searchOrder.setOrderCount(count);
			searchOrder.setOrderPriceTotal(orderPrice);
			searchOrder.getPageInfo().setRecordCount(count);
			if (count > 0) {
				List<OrderVo> list = dao.getAsList("getOrderList", searchOrder);
				searchOrder.setOrders(list);
			}
			return searchOrder;
		} else
			return null;
	}

	/**
	 * 获取所有符合条件的订单列表
	 * 
	 * @param searchOrder
	 * @return
	 * @throws Exception
	 */
	public SearchOrderVo getAllOrderList(SearchOrderVo searchOrder)
			throws Exception {
		List<OrderVo> listOrder = dao.getAsList("getOrderList", searchOrder);
		searchOrder.setOrders(listOrder);
		return searchOrder;
	}

	/**
	 * 获取订单状态统计
	 * 
	 * @param order
	 * @return
	 * @throws Exception
	 */
	public SearchOrderVo getOrderStatusCount(SearchOrderVo searchOrder)
			throws Exception {
		return (SearchOrderVo) dao.getAsObject("getOrderCountByStatus",
				searchOrder);
	}

	/**
	 * 获取所有的货运方式
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<ShippingCompanyBean> getShippingCompanys() throws Exception {
		return dao.getAsList("getShippingCompany");
	}

	/**
	 * 获取买家订单历史记录
	 * 
	 * @param order
	 * @return
	 * @throws Exception
	 */
	public List<OrderVo> getBuyerOrderList(HashMap<String, Long> hm)
			throws Exception {
		return dao.getAsList("getBuyerOrderHis", hm);
	}

	/**
	 * 获取最后一个订单状态
	 * 
	 * @param orderId
	 * @return
	 * @throws Exception
	 */
	public OrderstatusInfoBean getLastOrderStatusinfo(long orderId)
			throws Exception {
		return (OrderstatusInfoBean) dao.getAsObject("getLastOrderStatusinfo",
				orderId);
	}

	/**
	 * 增加购物车
	 * 
	 * @param shoppingCart
	 * @return
	 * @throws Exception
	 */
	public ShoppingCartBean insertShoppingCartBean(ShoppingCartBean shoppingCart)
			throws Exception {
		dao.insertObj("insertShoppingCartBean", shoppingCart);
		return shoppingCart;
	}

	/**
	 * 修改购物车
	 * 
	 * @param shoppingCart
	 * @return
	 * @throws Exception
	 */
	public int updateShoppingCartBean(ShoppingCartBean shoppingCart)
			throws Exception {
		return dao.updateObj("updateShoppingCartBean", shoppingCart);
	}

	/**
	 * 获取购物车
	 * 
	 * @param shoppingCart
	 * @return
	 * @throws Exception
	 */
	public ShoppingCartBean getShoppingCartBean(ShoppingCartBean shoppingCart)
			throws Exception {
		return (ShoppingCartBean) dao.getAsObject("getShoppingCartBean",
				shoppingCart);
	}

	/**
	 * 获取购物车
	 * 
	 * @param shoppingCart
	 * @return
	 * @throws Exception
	 */
	public List<ShoppingCartBean> getShoppingCartBeans(
			ShoppingCartBean shoppingCart) throws Exception {
		return dao.getAsList("getShoppingCartBean", shoppingCart);
	}

	/**
	 * 获取购物车
	 * 
	 * @param shoppingCart
	 * @return
	 * @throws Exception
	 */
	public List<ShoppingCartBean> getShoppingCartBeans(
			ShoppingCartBean shoppingCart, PageInfo pageInfo) throws Exception {
		if (pageInfo == null) {
			return dao.getAsList("getShoppingCartBean", shoppingCart);
		} else {
			Integer count = (Integer) dao.getAsObject(
					"getShoppingCartBeanCount", shoppingCart);
			pageInfo.setRecordCount(count == null ? 0 : count);
			if (count != null && count > 0) {
				return dao.getAsList("getShoppingCartBean", shoppingCart,
						pageInfo);
			}
			return null;
		}
	}

	/**
	 * 删除购物车
	 * 
	 * @param shoppingCart
	 * @return
	 * @throws Exception
	 */
	public int deleteShoppingCartBean(ShoppingCartBean shoppingCart)
			throws Exception {
		return dao.deleteObj("deleteShoppingCartBean", shoppingCart);
	}

	/**
	 * 获取买家订单历史记录
	 * 
	 * @param order
	 * @return
	 * @throws Exception
	 */
	public List<OrderVo> getBuyerOrderListory(BuyerBean buyer, PageInfo pageInfo)
			throws Exception {
		Integer count = (Integer) dao.getAsObject("getBuyerOrderHistoryCount",
				buyer);
		pageInfo.setRecordCount(count == null ? 0 : count);
		if (count != null && count > 0) {
			buyer.setPageInfo(pageInfo);
			return dao.getAsList("getBuyerOrderHistory", buyer);
		}
		return null;

	}

	/**
	 * 获取某个买家所有的支付方式
	 * 
	 * @param buyerId
	 * @return
	 * @throws Exception
	 */
	public List<PaymentTypeBean> getBuyerPaymentType(long buyerId)
			throws Exception {
		return dao.getAsList("getBuyerPaymentType", buyerId);
	}

	/**
	 * 获取所有的支付方式
	 * 
	 * @param buyerId
	 * @return
	 * @throws Exception
	 */
	public List<PaymentTypeBean> getBuyerPaymentType() throws Exception {
		return dao.getAsList("getPaymentTypeBean");
	}

	/**
	 * 获取产品订单数
	 * 
	 * @param productId
	 * @return
	 * @throws Exception
	 */
	public int getProductOrderCount(long productId) throws Exception {
		OrderProductBean orderProduct = new OrderProductBean();
		orderProduct.setProductId(productId);
		Integer count = (Integer) dao.getAsObject("getOrderProductBeanCount",
				orderProduct);
		return count == null ? 0 : count;
	}

	/**
	 * 通过产品ID获取该产品最近被购买时间
	 * 
	 * @param productId
	 * @return
	 * @throws Exception
	 */
	public OrderBean getOrderRecentBuyTimeByProductId(HashMap<String, Long> ids)
			throws Exception {
		List<OrderBean> list = dao.getAsList("getProductRecentyBuyTime", ids);
		if (list != null) {
			return list.get(0);
		}
		return null;
	}

	/**
	 * 增加问题订单池
	 * 
	 * @param problemOrder
	 * @return
	 * @throws Exception
	 */
	public ProblemOrderBean insertProblemOrderBean(ProblemOrderBean problemOrder)
			throws Exception {
		dao.insertObj("insertProblemOrderBean", problemOrder);
		return problemOrder;
	}

	/**
	 * 修改问题订单池
	 * 
	 * @param problemOrder
	 * @return
	 * @throws Exception
	 */
	public int updateProblemOrderBean(ProblemOrderBean problemOrder)
			throws Exception {
		return dao.updateObj("updateProblemOrderBean", problemOrder);
	}

	/**
	 * 获取问题订单池
	 * 
	 * @param problemOrder
	 * @return
	 * @throws Exception
	 */
	public ProblemOrderBean getProblemOrderBean(ProblemOrderBean problemOrder)
			throws Exception {
		return (ProblemOrderBean) dao.getAsObject("getProblemOrderBean",
				problemOrder);
	}

	/**
	 * 获取问题订单池
	 * 
	 * @param problemOrder
	 * @return
	 * @throws Exception
	 */
	public List<ProblemOrderBean> getProblemOrderBeans(
			ProblemOrderBean problemOrder) throws Exception {
		return dao.getAsList("getProblemOrderBean", problemOrder);
	}

	/**
	 * 获取问题订单池
	 * 
	 * @param problemOrder
	 * @return
	 * @throws Exception
	 */
	public List<ProblemOrderBean> getProblemOrderBeans(
			ProblemOrderBean problemOrder, PageInfo pageInfo) throws Exception {
		Integer count = (Integer) dao.getAsObject("getProblemOrderBeanCount",
				problemOrder);
		pageInfo.setRecordCount(count == null ? 0 : count);
		if (count != null && count > 0) {
			return dao.getAsList("getProblemOrderBean", problemOrder, pageInfo);
		}
		return null;
	}

	/**
	 * 删除问题订单池
	 * 
	 * @param problemOrder
	 * @return
	 * @throws Exception
	 */
	public int deleteProblemOrderBean(ProblemOrderBean problemOrder)
			throws Exception {
		return dao.deleteObj("deleteProblemOrderBean", problemOrder);
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
		dao.insertObj("insertPickingInfoBean", pickingInfo);
		return pickingInfo;
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
		return dao.updateObj("updatePickingInfoBean", pickingInfo);
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
		return (PickingInfoBean) dao.getAsObject("getPickingInfoBean",
				pickingInfo);
	}

	/**
	 * 获取拣货信息
	 * 
	 * @param pickingInfo
	 * @return
	 * @throws Exception
	 */
	public List<PickingInfoBean> getPickingInfoBeans(PickingInfoBean pickingInfo)
			throws Exception {
		return dao.getAsList("getPickingInfoBean", pickingInfo);
	}

	/**
	 * 获取拣货信息
	 * 
	 * @param pickingInfo
	 * @return
	 * @throws Exception
	 */
	public List<PickingInfoBean> getPickingInfoBeans(
			PickingInfoBean pickingInfo, PageInfo pageInfo) throws Exception {
		Integer count = (Integer) dao.getAsObject("getPickingInfoBeanCount",
				pickingInfo);
		pageInfo.setRecordCount(count == null ? 0 : count);
		if (count != null && count > 0) {
			return dao.getAsList("getPickingInfoBean", pickingInfo, pageInfo);
		}
		return null;
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
		return dao.deleteObj("deletePickingInfoBean", pickingInfo);
	}

	/**
	 * 增加拣货
	 * 
	 * @param picking
	 * @return
	 * @throws Exception
	 */
	public PickingBean insertPickingBean(PickingBean picking) throws Exception {
		dao.insertObj("insertPickingBean", picking);
		return picking;
	}

	/**
	 * 修改拣货
	 * 
	 * @param picking
	 * @return
	 * @throws Exception
	 */
	public int updatePickingBean(PickingBean picking) throws Exception {
		return dao.updateObj("updatePickingBean", picking);
	}

	/**
	 * 获取拣货
	 * 
	 * @param picking
	 * @return
	 * @throws Exception
	 */
	public PickingBean getPickingBean(PickingBean picking) throws Exception {
		return (PickingBean) dao.getAsObject("getPickingBean", picking);
	}

	/**
	 * 获取拣货
	 * 
	 * @param picking
	 * @return
	 * @throws Exception
	 */
	public List<PickingBean> getPickingBeans(PickingBean picking)
			throws Exception {
		return dao.getAsList("getPickingBean", picking);
	}

	/**
	 * 获取拣货
	 * 
	 * @param picking
	 * @return
	 * @throws Exception
	 */
	public List<PickingBean> getPickingBeans(PickingBean picking,
			PageInfo pageInfo) throws Exception {
		Integer count = (Integer) dao.getAsObject("getPickingBeanCount",
				picking);
		pageInfo.setRecordCount(count == null ? 0 : count);
		if (count != null && count > 0) {
			return dao.getAsList("getPickingBean", picking, pageInfo);
		}
		return null;
	}

	/**
	 * 删除拣货
	 * 
	 * @param picking
	 * @return
	 * @throws Exception
	 */
	public int deletePickingBean(PickingBean picking) throws Exception {
		return dao.deleteObj("deletePickingBean", picking);
	}
	
	/**获取所有符合条件的打印订单
	 * @param orderScanVo
	 * @return
	 * @throws Exception
	 */
	public List<OrderScanVo> getPrintOrderList(OrderScanVo orderScanVo,PageInfo pageInfo) throws Exception{
		Integer count = (Integer) dao.getAsObject("getPrintOrderListCount",
				orderScanVo);
		pageInfo.setRecordCount(count == null ? 0 : count);
		if (count != null && count > 0) {
			orderScanVo.setPageInfo(pageInfo);
			return dao.getAsList("getPrintOrderList", orderScanVo);
		}
		return null;
	}
	
	/**
	 * 获取需要快照的订单产品
	 * 
	 * @param productId
	 * @return
	 * @throws Exception
	 */
	public List<OrderProductBean> getNeedsSnapshotProd(long productId) throws Exception {
		return dao.getAsList("getNeedsSnapshotProd", productId);
	}
}