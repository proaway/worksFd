/**
 * ICouponManager.java
 * @author:  ZhouRongyu
 */
package com.fd.fashion.order.manager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fd.fashion.order.bean.OrderAddressBean;
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
import com.fd.fashion.order.vo.OrderScanVo;
import com.fd.fashion.order.vo.OrderVo;
import com.fd.fashion.order.vo.PaymentInfoVo;
import com.fd.fashion.order.vo.SearchOrderVo;
import com.fd.fashion.product.vo.CartProductVo;
import com.fd.payment.bean.PaymentTypeBean;
import com.fd.util.PageInfo;

/**
 * @CreateDate:  2013-4-8 下午12:23:19 
 * @author:  ZhouRongyu
 * @Description:  TODO
 * 
 * @version:  V1.0
 */
public interface IOrderManager {
	/**
	 * 使用orderId获取Order
	 * 
	 * @param orderId
	 * @return
	 * @throws Exception
	 */
	public OrderBean getOrder(long orderId) throws Exception ;
	
	/**
	 * 添加订单地址
	 * 
	 * @param OrderAddress
	 * @return
	 * @throws Exception
	 */
	public OrderAddressBean addOrderAddress(OrderAddressBean orderAddressBean) throws Exception ;
	
	/**
	 * 添加订单
	 * 
	 * @param OrderBean
	 * @return
	 * @throws Exception
	 */
	public int addOrder(OrderBean order,OrderAddressBean orderAddress,List<CartProductVo> cProducts) throws Exception ;
	
	/**
	 * 查询订单列表
	 * 
	 * @param OrderBean
	 * @return
	 * @throws Exception
	 */
	public SearchOrderVo getSearchOrder(SearchOrderVo searchOrder, PageInfo pageInfo,String flag) throws Exception;
	
	/**根据查询条件获取所有满足条件的订单列表，不分页
	 * @param searchOrder
	 * @return
	 * @throws Exception
	 */
	public SearchOrderVo getSearchOrderList(SearchOrderVo searchOrder) throws Exception;
	
	/**获取所有的货运方式
	 * @return
	 * @throws Exception
	 */
	public List<ShippingCompanyBean> getShippingCompanys() throws Exception;
	
	/** 获取单个订单信息
	 * @return
	 * @throws Exception
	 */
	public OrderBean getOrder(Long orderId)throws Exception;
	
	/** 获取单个订单历史状态
	 * @return
	 * @throws Exception
	 */
	public List<OrderstatusInfoBean> getOrderStatusInfo(Long orderId,String flag)throws Exception;
	
	/** 获取单个订单历史状态
	 * @return
	 * @throws Exception
	 */
	public List<OrderProductBean> getOrderProductInfo(Long orderId)throws Exception;
	
	/** 订单物流信息
	 * @return
	 * @throws Exception
	 */
	public List<OrderShippingBean> getOrderShippingInfo(Long orderId)throws Exception;
	
	/** 获取单个订单支付信息
	 * @return
	 * @throws Exception
	 */
	public OrderPaymentinfoBean getOrderPaymentinfo(Long orderId)throws Exception;
	
	/** 获取单个订单支付信息Vo
	 * @return
	 * @throws Exception
	 */
	public PaymentInfoVo getPaymentinfoVo(Long orderId)throws Exception;
	
	/** 获取单个订单货运地址
	 * @return
	 * @throws Exception
	 */
	public OrderAddressBean getOrderAddressInfo(Long orderAddressId)throws Exception;
	
	/** 获取买家历史订单
	 * @return
	 * @throws Exception
	 */
	public List<OrderVo> getBuyerOrderList(Long buyerId,int num) throws Exception;
	
	/**
	 * 调整订单金额
	 * 
	 * @return 
	 * @throws Exception
	 */
	public int adjustOrderAmount(long orderId, double amount, String operator) throws Exception;
	
	/**
	 * 查询订单所有物流跟踪号
	 * @return 
	 * @throws Exception
	 */
	public List<OrderShippingBean> getOrderShipping(Long orderId) throws Exception;
	
	/**
	 * 购物车产品入库
	 * 
	 * @param cart
	 * @return
	 * @throws Exception
	 */
	public ShoppingCartBean addShoppingCart(CartProductVo cartProd, long buyerId) throws Exception;
	
	/**
	 * 查询购物车
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<CartProductVo> getShoppingCart(long buyerId) throws Exception;
	
	/**
	 * 购物车产品更新入库
	 * 
	 * @param cart
	 * @return
	 * @throws Exception
	 */
	public ShoppingCartBean updateShoppingCart(CartProductVo cartProd, long buyerId) throws Exception;
	
	/**
	 * 购物车产品删除
	 * 
	 * @param cart
	 * @return
	 * @throws Exception
	 */
	public int deleteShoppingCart(CartProductVo cartProd, long buyerId) throws Exception;
	
	/** 获取买家历史订单(查询所有的历史订单)
	 * @return
	 * @throws Exception
	 */
	public List<OrderVo> getBuyerOrderListory(Long buyerId,PageInfo pageInfo) throws Exception;
	
	/**
	 * 获取购物车
	 * 
	 * @param shoppingCart
	 * @return
	 * @throws Exception
	 */
	public List<ShoppingCartBean> getShoppingCartBeans(ShoppingCartBean shoppingCart,PageInfo pageInfo) throws Exception;
	
	/**
	 * 更新订单
	 * 
	 * @param o
	 * @return
	 * @throws Exception
	 */
	public int updateOrder(OrderBean o) throws Exception;
	
	/**获取某个买家所有的支付方式
	 * @param buyerId
	 * @return
	 * @throws Exception
	 */
	public List<PaymentTypeBean> getBuyerPaymentType(long buyerId) throws Exception;
	
	/**
	 * 通过买家ID获取该买家所有的订单
	 * 
	 * @param buyerId
	 * @return
	 * @throws Exception
	 */
	public List<OrderBean> getOrderBeans(long buyerId) throws Exception ;
	
	/**引用字典库
	 * @return
	 * @throws Exception
	 */
	public Map<String,PaymentTypeBean> getPaymentTypeList() throws Exception;
	
	/**
	 * 获取产品订单数
	 * 
	 * @param productId
	 * @return
	 * @throws Exception
	 */
	public int getProductOrderCount(long productId) throws Exception;
	
	/**通过产品ID获取该产品最近被购买时间
	 * @param productId
	 * @return
	 * @throws Exception
	 */
	public HashMap<String,Object> getOrderRecentBuyTimeByProductId(long productId,long buyerId) throws Exception;
	
	/**
	 * 增加问题订单池
	 * 
	 * @param problemOrder
	 * @return
	 * @throws Exception
	 */
	public ProblemOrderBean insertProblemOrderBean(ProblemOrderBean problemOrder) throws Exception;
	
	/**获取所有符合条件的打印订单
	 * @param orderScanVo
	 * @return
	 * @throws Exception
	 */
	public List<OrderScanVo> getPrintOrderList(OrderScanVo orderScanVo,PageInfo pageInfo) throws Exception;
	
	/**
	 * 增加拣货
	 * 
	 * @param picking
	 * @return
	 * @throws Exception
	 */
	public PickingBean insertPickingBean(PickingBean picking) throws Exception ;
	
	/**
	 * 获取拣货
	 * 
	 * @param picking
	 * @return
	 * @throws Exception
	 */
	public List<PickingBean> getPickingBeans(PickingBean picking,
			PageInfo pageInfo) throws Exception;
	
	/**查询打印订单信息(订单拣货人的信息)
	 * @param picking
	 * @return
	 * @throws Exception
	 */
	public PickingBean getPickingBean(PickingBean picking)throws Exception;
	
	/**更新拣货信息
	 * @param picking
	 * @return
	 * @throws Exception
	 */
	public int updatePickingBean(PickingBean picking) throws Exception;
	
	/**
	 * 增加拣货信息
	 * 
	 * @param pickingInfo
	 * @return
	 * @throws Exception
	 */
	public PickingInfoBean insertPickingInfoBean(PickingInfoBean pickingInfo)
			throws Exception ;

	/**
	 * 修改拣货信息
	 * 
	 * @param pickingInfo
	 * @return
	 * @throws Exception
	 */
	public int updatePickingInfoBean(PickingInfoBean pickingInfo)
			throws Exception ;

	/**
	 * 获取拣货信息
	 * 
	 * @param pickingInfo
	 * @return
	 * @throws Exception
	 */
	public PickingInfoBean getPickingInfoBean(PickingInfoBean pickingInfo)
			throws Exception ;
	
	/**
	 * 删除拣货信息
	 * 
	 * @param pickingInfo
	 * @return
	 * @throws Exception
	 */
	public int deletePickingInfoBean(PickingInfoBean pickingInfo)
			throws Exception ;
	
	/**
	 * 获取拣货信息列表
	 * 
	 * @param pickingInfo
	 * @return
	 * @throws Exception
	 */
	public List<PickingInfoBean> getPickingInfoBeans(Long orderId) throws Exception;
	
	/**
	 * 生成订单产品快照
	 * 
	 * @param productId
	 * @return
	 * @throws Exception
	 */
	public int makeOrderProdSnapshot(long productId) throws Exception;
}
