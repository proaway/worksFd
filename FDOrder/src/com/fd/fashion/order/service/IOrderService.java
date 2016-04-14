package com.fd.fashion.order.service;

import java.util.HashMap;
import java.util.List;

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

public interface IOrderService {
	/**
	 * 增加订单地址
	 * 
	 * @param orderAddress
	 * @return
	 * @throws Exception
	 */
	public OrderAddressBean insertOrderAddressBean(OrderAddressBean orderAddress)
			throws Exception;

	/**
	 * 修改订单地址
	 * 
	 * @param orderAddress
	 * @return
	 * @throws Exception
	 */
	public int updateOrderAddressBean(OrderAddressBean orderAddress)
			throws Exception;

	/**
	 * 获取订单地址
	 * 
	 * @param orderAddress
	 * @return
	 * @throws Exception
	 */
	public OrderAddressBean getOrderAddressBean(OrderAddressBean orderAddress)
			throws Exception;

	/**
	 * 获取订单地址
	 * 
	 * @param orderAddress
	 * @return
	 * @throws Exception
	 */
	public List<OrderAddressBean> getOrderAddressBeans(
			OrderAddressBean orderAddress) throws Exception;

	/**
	 * 获取订单地址
	 * 
	 * @param orderAddress
	 * @return
	 * @throws Exception
	 */
	public List<OrderAddressBean> getOrderAddressBeans(
			OrderAddressBean orderAddress, PageInfo pageInfo) throws Exception;

	/**
	 * 删除订单地址
	 * 
	 * @param orderAddress
	 * @return
	 * @throws Exception
	 */
	public int deleteOrderAddressBean(OrderAddressBean orderAddress)
			throws Exception;

	/**
	 * 增加订单支付情况信息
	 * 
	 * @param orderPaymentinfo
	 * @return
	 * @throws Exception
	 */
	public OrderPaymentinfoBean insertOrderPaymentinfoBean(
			OrderPaymentinfoBean orderPaymentinfo) throws Exception;

	/**
	 * 修改订单支付情况信息
	 * 
	 * @param orderPaymentinfo
	 * @return
	 * @throws Exception
	 */
	public int updateOrderPaymentinfoBean(OrderPaymentinfoBean orderPaymentinfo)
			throws Exception;

	/**
	 * 获取订单支付情况信息
	 * 
	 * @param orderPaymentinfo
	 * @return
	 * @throws Exception
	 */
	public OrderPaymentinfoBean getOrderPaymentinfoBean(
			OrderPaymentinfoBean orderPaymentinfo) throws Exception;

	/**
	 * 获取订单支付情况信息
	 * 
	 * @param orderPaymentinfo
	 * @return
	 * @throws Exception
	 */
	public List<OrderPaymentinfoBean> getOrderPaymentinfoBeans(
			OrderPaymentinfoBean orderPaymentinfo) throws Exception;

	/**
	 * 获取订单支付情况信息
	 * 
	 * @param orderPaymentinfo
	 * @return
	 * @throws Exception
	 */
	public List<OrderPaymentinfoBean> getOrderPaymentinfoBeans(
			OrderPaymentinfoBean orderPaymentinfo, PageInfo pageInfo)
			throws Exception;

	/**
	 * 删除订单支付情况信息
	 * 
	 * @param orderPaymentinfo
	 * @return
	 * @throws Exception
	 */
	public int deleteOrderPaymentinfoBean(OrderPaymentinfoBean orderPaymentinfo)
			throws Exception;

	/**
	 * 增加订单产品
	 * 
	 * @param orderProduct
	 * @return
	 * @throws Exception
	 */
	public OrderProductBean insertOrderProductBean(OrderProductBean orderProduct)
			throws Exception;

	/**
	 * 修改订单产品
	 * 
	 * @param orderProduct
	 * @return
	 * @throws Exception
	 */
	public int updateOrderProductBean(OrderProductBean orderProduct)
			throws Exception;

	/**
	 * 获取订单产品
	 * 
	 * @param orderProduct
	 * @return
	 * @throws Exception
	 */
	public OrderProductBean getOrderProductBean(OrderProductBean orderProduct)
			throws Exception;

	/**
	 * 获取订单产品
	 * 
	 * @param orderProduct
	 * @return
	 * @throws Exception
	 */
	public List<OrderProductBean> getOrderProductBeans(
			OrderProductBean orderProduct) throws Exception;

	/**
	 * 获取订单产品
	 * 
	 * @param orderProduct
	 * @return
	 * @throws Exception
	 */
	public List<OrderProductBean> getOrderProductBeans(
			OrderProductBean orderProduct, PageInfo pageInfo) throws Exception;

	/**
	 * 删除订单产品
	 * 
	 * @param orderProduct
	 * @return
	 * @throws Exception
	 */
	public int deleteOrderProductBean(OrderProductBean orderProduct)
			throws Exception;

	/**
	 * 增加coupon
	 * 
	 * @param tCoupon
	 * @return
	 * @throws Exception
	 */
	public CouponBean insertCouponBean(CouponBean tCoupon) throws Exception;

	/**
	 * 修改coupon
	 * 
	 * @param tCoupon
	 * @return
	 * @throws Exception
	 */
	public int updateCouponBean(CouponBean tCoupon) throws Exception;

	/**
	 * 获取coupon
	 * 
	 * @param tCoupon
	 * @return
	 * @throws Exception
	 */
	public List<CouponBean> getCouponBeans(CouponBean tCoupon) throws Exception;

	/**
	 * 获取coupon
	 * 
	 * @param tCoupon
	 * @return
	 * @throws Exception
	 */
	public List<CouponBean> getCouponBeans(CouponBean tCoupon, PageInfo pageInfo)
			throws Exception;

	/**
	 * 删除coupon
	 * 
	 * @param tCoupon
	 * @return
	 * @throws Exception
	 */
	public int deleteCouponBean(CouponBean tCoupon) throws Exception;

	/**
	 * 增加风控设置
	 * 
	 * @param riskSetting
	 * @return
	 * @throws Exception
	 */
	public RiskSettingBean insertRiskSettingBean(RiskSettingBean riskSetting)
			throws Exception;

	/**
	 * 修改风控设置
	 * 
	 * @param riskSetting
	 * @return
	 * @throws Exception
	 */
	public int updateRiskSettingBean(RiskSettingBean riskSetting)
			throws Exception;

	/**
	 * 获取风控设置
	 * 
	 * @param riskSetting
	 * @return
	 * @throws Exception
	 */
	public List<RiskSettingBean> getRiskSettingBeans(RiskSettingBean riskSetting)
			throws Exception;

	/**
	 * 获取风控设置
	 * 
	 * @param riskSetting
	 * @return
	 * @throws Exception
	 */
	public List<RiskSettingBean> getRiskSettingBeans(
			RiskSettingBean riskSetting, PageInfo pageInfo) throws Exception;

	/**
	 * 删除风控设置
	 * 
	 * @param riskSetting
	 * @return
	 * @throws Exception
	 */
	public int deleteRiskSettingBean(RiskSettingBean riskSetting)
			throws Exception;

	/**
	 * 增加订单货运
	 * 
	 * @param orderShipping
	 * @return
	 * @throws Exception
	 */
	public OrderShippingBean insertOrderShippingBean(
			OrderShippingBean orderShipping) throws Exception;

	/**
	 * 修改订单货运
	 * 
	 * @param orderShipping
	 * @return
	 * @throws Exception
	 */
	public int updateOrderShippingBean(OrderShippingBean orderShipping)
			throws Exception;

	/**
	 * 获取订单货运
	 * 
	 * @param orderShipping
	 * @return
	 * @throws Exception
	 */
	public List<OrderShippingBean> getOrderShippingBean(
			OrderShippingBean orderShipping) throws Exception;

	/**
	 * 获取订单货运
	 * 
	 * @param orderShipping
	 * @return
	 * @throws Exception
	 */
	public List<OrderShippingBean> getOrderShippingBeans(
			OrderShippingBean orderShipping) throws Exception;

	/**
	 * 获取订单货运
	 * 
	 * @param orderShipping
	 * @return
	 * @throws Exception
	 */
	public List<OrderShippingBean> getOrderShippingBeans(
			OrderShippingBean orderShipping, PageInfo pageInfo)
			throws Exception;

	/**
	 * 删除订单货运
	 * 
	 * @param orderShipping
	 * @return
	 * @throws Exception
	 */
	public int deleteOrderShippingBean(OrderShippingBean orderShipping)
			throws Exception;

	/**
	 * 增加订单状态信息
	 * 
	 * @param orderstatusInfo
	 * @return
	 * @throws Exception
	 */
	public OrderstatusInfoBean insertOrderstatusInfoBean(
			OrderstatusInfoBean orderstatusInfo) throws Exception;

	/**
	 * 修改订单状态信息
	 * 
	 * @param orderstatusInfo
	 * @return
	 * @throws Exception
	 */
	public int updateOrderstatusInfoBean(OrderstatusInfoBean orderstatusInfo)
			throws Exception;

	/**
	 * 获取订单状态信息
	 * 
	 * @param orderstatusInfo
	 * @return
	 * @throws Exception
	 */
	public OrderstatusInfoBean getOrderstatusInfoBean(
			OrderstatusInfoBean orderstatusInfo) throws Exception;

	/**
	 * 获取订单状态信息
	 * 
	 * @param orderstatusInfo
	 * @return
	 * @throws Exception
	 */
	public List<OrderstatusInfoBean> getOrderstatusInfoBeans(
			OrderstatusInfoBean orderstatusInfo) throws Exception;

	/**
	 * 获取订单状态信息
	 * 
	 * @param orderstatusInfo
	 * @return
	 * @throws Exception
	 */
	public List<OrderstatusInfoBean> getOrderstatusInfoBeans(
			OrderstatusInfoBean orderstatusInfo, PageInfo pageInfo)
			throws Exception;

	/**
	 * 删除订单状态信息
	 * 
	 * @param orderstatusInfo
	 * @return
	 * @throws Exception
	 */
	public int deleteOrderstatusInfoBean(OrderstatusInfoBean orderstatusInfo)
			throws Exception;

	/**
	 * 增加订单
	 * 
	 * @param order
	 * @return
	 * @throws Exception
	 */
	public OrderBean insertOrderBean(OrderBean order) throws Exception;

	/**
	 * 修改订单
	 * 
	 * @param order
	 * @return
	 * @throws Exception
	 */
	public int updateOrderBean(OrderBean order) throws Exception;

	/**
	 * 获取订单
	 * 
	 * @param order
	 * @return
	 * @throws Exception
	 */
	public OrderBean getOrderBean(OrderBean order) throws Exception;

	/**
	 * 获取订单
	 * 
	 * @param order
	 * @return
	 * @throws Exception
	 */
	public List<OrderBean> getOrderBeans(OrderBean order) throws Exception;

	/**
	 * 获取订单
	 * 
	 * @param order
	 * @return
	 * @throws Exception
	 */
	public List<OrderBean> getOrderBeans(OrderBean order, PageInfo pageInfo)
			throws Exception;

	/**
	 * 删除订单
	 * 
	 * @param order
	 * @return
	 * @throws Exception
	 */
	public int deleteOrderBean(OrderBean order) throws Exception;

	/**
	 * 增加订单金额历史
	 * 
	 * @param orderAmount
	 * @return
	 * @throws Exception
	 */
	public OrderAmountBean insertOrderAmountBean(OrderAmountBean orderAmount)
			throws Exception;

	/**
	 * 修改订单金额历史
	 * 
	 * @param orderAmount
	 * @return
	 * @throws Exception
	 */
	public int updateOrderAmountBean(OrderAmountBean orderAmount)
			throws Exception;

	/**
	 * 获取订单金额历史
	 * 
	 * @param orderAmount
	 * @return
	 * @throws Exception
	 */
	public OrderAmountBean getOrderAmountBean(OrderAmountBean orderAmount)
			throws Exception;

	/**
	 * 获取订单金额历史
	 * 
	 * @param orderAmount
	 * @return
	 * @throws Exception
	 */
	public List<OrderAmountBean> getOrderAmountBeans(OrderAmountBean orderAmount)
			throws Exception;

	/**
	 * 获取订单金额历史
	 * 
	 * @param orderAmount
	 * @return
	 * @throws Exception
	 */
	public List<OrderAmountBean> getOrderAmountBeans(
			OrderAmountBean orderAmount, PageInfo pageInfo) throws Exception;

	/**
	 * 删除订单金额历史
	 * 
	 * @param orderAmount
	 * @return
	 * @throws Exception
	 */
	public int deleteOrderAmountBean(OrderAmountBean orderAmount)
			throws Exception;

	/**
	 * 获取订单管理列表
	 * 
	 * @param order
	 * @return
	 * @throws Exception
	 */
	public SearchOrderVo getOrderList(SearchOrderVo searchOrder)
			throws Exception;

	/**
	 * 获取所有符合条件的订单列表
	 * 
	 * @param searchOrder
	 * @return
	 * @throws Exception
	 */
	public SearchOrderVo getAllOrderList(SearchOrderVo searchOrder)
			throws Exception;

	/**
	 * 获取订单状态统计
	 * 
	 * @param order
	 * @return
	 * @throws Exception
	 */
	public SearchOrderVo getOrderStatusCount(SearchOrderVo searchOrder)
			throws Exception;

	/**
	 * 获取所有的货运方式
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<ShippingCompanyBean> getShippingCompanys() throws Exception;

	/**
	 * 获取买家订单历史记录
	 * 
	 * @param order
	 * @return
	 * @throws Exception
	 */
	public List<OrderVo> getBuyerOrderList(HashMap<String, Long> hm)
			throws Exception;

	/**
	 * 获取最后一个订单状态
	 * 
	 * @param orderId
	 * @return
	 * @throws Exception
	 */
	public OrderstatusInfoBean getLastOrderStatusinfo(long orderId)
			throws Exception;

	/**
	 * 增加购物车
	 * 
	 * @param shoppingCart
	 * @return
	 * @throws Exception
	 */
	public ShoppingCartBean insertShoppingCartBean(ShoppingCartBean shoppingCart)
			throws Exception;

	/**
	 * 修改购物车
	 * 
	 * @param shoppingCart
	 * @return
	 * @throws Exception
	 */
	public int updateShoppingCartBean(ShoppingCartBean shoppingCart)
			throws Exception;

	/**
	 * 获取购物车
	 * 
	 * @param shoppingCart
	 * @return
	 * @throws Exception
	 */
	public ShoppingCartBean getShoppingCartBean(ShoppingCartBean shoppingCart)
			throws Exception;

	/**
	 * 获取购物车
	 * 
	 * @param shoppingCart
	 * @return
	 * @throws Exception
	 */
	public List<ShoppingCartBean> getShoppingCartBeans(
			ShoppingCartBean shoppingCart) throws Exception;

	/**
	 * 获取购物车
	 * 
	 * @param shoppingCart
	 * @return
	 * @throws Exception
	 */
	public List<ShoppingCartBean> getShoppingCartBeans(
			ShoppingCartBean shoppingCart, PageInfo pageInfo) throws Exception;

	/**
	 * 删除购物车
	 * 
	 * @param shoppingCart
	 * @return
	 * @throws Exception
	 */
	public int deleteShoppingCartBean(ShoppingCartBean shoppingCart)
			throws Exception;

	/**
	 * 获取买家订单历史记录
	 * 
	 * @param order
	 * @return
	 * @throws Exception
	 */
	public List<OrderVo> getBuyerOrderListory(BuyerBean buyer, PageInfo pageInfo)
			throws Exception;

	/**
	 * 获取某个买家所有的支付方式
	 * 
	 * @param buyerId
	 * @return
	 * @throws Exception
	 */
	public List<PaymentTypeBean> getBuyerPaymentType(long buyerId)
			throws Exception;

	/**
	 * 获取所有的支付方式
	 * 
	 * @param buyerId
	 * @return
	 * @throws Exception
	 */
	public List<PaymentTypeBean> getBuyerPaymentType() throws Exception;

	/**
	 * 获取产品订单数
	 * 
	 * @param productId
	 * @return
	 * @throws Exception
	 */
	public int getProductOrderCount(long productId) throws Exception;

	/**
	 * 通过产品ID获取该产品最近被购买时间
	 * 
	 * @param productId
	 * @return
	 * @throws Exception
	 */
	public OrderBean getOrderRecentBuyTimeByProductId(HashMap<String, Long> ids)
			throws Exception;

	/**
	 * 增加问题订单池
	 * 
	 * @param problemOrder
	 * @return
	 * @throws Exception
	 */
	public ProblemOrderBean insertProblemOrderBean(ProblemOrderBean problemOrder)
			throws Exception;

	/**
	 * 修改问题订单池
	 * 
	 * @param problemOrder
	 * @return
	 * @throws Exception
	 */
	public int updateProblemOrderBean(ProblemOrderBean problemOrder)
			throws Exception;

	/**
	 * 获取问题订单池
	 * 
	 * @param problemOrder
	 * @return
	 * @throws Exception
	 */
	public ProblemOrderBean getProblemOrderBean(ProblemOrderBean problemOrder)
			throws Exception;

	/**
	 * 获取问题订单池
	 * 
	 * @param problemOrder
	 * @return
	 * @throws Exception
	 */
	public List<ProblemOrderBean> getProblemOrderBeans(
			ProblemOrderBean problemOrder) throws Exception;

	/**
	 * 获取问题订单池
	 * 
	 * @param problemOrder
	 * @return
	 * @throws Exception
	 */
	public List<ProblemOrderBean> getProblemOrderBeans(
			ProblemOrderBean problemOrder, PageInfo pageInfo) throws Exception;

	/**
	 * 删除问题订单池
	 * 
	 * @param problemOrder
	 * @return
	 * @throws Exception
	 */
	public int deleteProblemOrderBean(ProblemOrderBean problemOrder)
			throws Exception;

	/**
	 * 增加拣货信息
	 * 
	 * @param pickingInfo
	 * @return
	 * @throws Exception
	 */
	public PickingInfoBean insertPickingInfoBean(PickingInfoBean pickingInfo)
			throws Exception;

	/**
	 * 修改拣货信息
	 * 
	 * @param pickingInfo
	 * @return
	 * @throws Exception
	 */
	public int updatePickingInfoBean(PickingInfoBean pickingInfo)
			throws Exception;

	/**
	 * 获取拣货信息
	 * 
	 * @param pickingInfo
	 * @return
	 * @throws Exception
	 */
	public PickingInfoBean getPickingInfoBean(PickingInfoBean pickingInfo)
			throws Exception;

	/**
	 * 获取拣货信息
	 * 
	 * @param pickingInfo
	 * @return
	 * @throws Exception
	 */
	public List<PickingInfoBean> getPickingInfoBeans(PickingInfoBean pickingInfo)
			throws Exception;

	/**
	 * 获取拣货信息
	 * 
	 * @param pickingInfo
	 * @return
	 * @throws Exception
	 */
	public List<PickingInfoBean> getPickingInfoBeans(
			PickingInfoBean pickingInfo, PageInfo pageInfo) throws Exception;

	/**
	 * 删除拣货信息
	 * 
	 * @param pickingInfo
	 * @return
	 * @throws Exception
	 */
	public int deletePickingInfoBean(PickingInfoBean pickingInfo)
			throws Exception;

	/**
	 * 增加拣货
	 * 
	 * @param picking
	 * @return
	 * @throws Exception
	 */
	public PickingBean insertPickingBean(PickingBean picking) throws Exception;

	/**
	 * 修改拣货
	 * 
	 * @param picking
	 * @return
	 * @throws Exception
	 */
	public int updatePickingBean(PickingBean picking) throws Exception;

	/**
	 * 获取拣货
	 * 
	 * @param picking
	 * @return
	 * @throws Exception
	 */
	public PickingBean getPickingBean(PickingBean picking) throws Exception;

	/**
	 * 获取拣货
	 * 
	 * @param picking
	 * @return
	 * @throws Exception
	 */
	public List<PickingBean> getPickingBeans(PickingBean picking)
			throws Exception;

	/**
	 * 获取拣货
	 * 
	 * @param picking
	 * @return
	 * @throws Exception
	 */
	public List<PickingBean> getPickingBeans(PickingBean picking,
			PageInfo pageInfo) throws Exception;

	/**
	 * 删除拣货
	 * 
	 * @param picking
	 * @return
	 * @throws Exception
	 */
	public int deletePickingBean(PickingBean picking) throws Exception;
	
	/**获取所有符合条件的打印订单
	 * @param orderScanVo
	 * @return
	 * @throws Exception
	 */
	public List<OrderScanVo> getPrintOrderList(OrderScanVo orderScanVo,PageInfo pageInfo) throws Exception;
	
	/**
	 * 获取需要快照的订单产品
	 * 
	 * @param productId
	 * @return
	 * @throws Exception
	 */
	public List<OrderProductBean> getNeedsSnapshotProd(long productId) throws Exception;
}