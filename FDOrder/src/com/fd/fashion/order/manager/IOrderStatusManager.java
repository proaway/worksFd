package com.fd.fashion.order.manager;

import com.fd.fashion.order.bean.OrderBean;
import com.fd.fashion.order.bean.OrderShippingBean;
import com.fd.fashion.order.bean.OrderstatusInfoBean;
import com.fd.fashion.order.vo.OrderVo;
import com.fd.payment.bean.AlipayNotifyBean;
import com.fd.payment.bean.PaypalipnBean;
import com.fd.payment.bean.WesternUnionBean;

/**
 * @CreateDate: 2013-4-9 下午06:52:05
 * @author Longli
 * @Description: 封装订单状态流转方法接口
 * 
 */
public interface IOrderStatusManager extends IOrderManager {
	/**
	 * 付款待确认
	 * 
	 * @param order
	 * @return
	 * @throws Exception
	 */
	public boolean oa001ToOa002(OrderBean order, String operator) throws Exception;
	
	/**
	 * Paypal付款待确认
	 * 
	 * @param order
	 * @return
	 * @throws Exception
	 */
	public boolean oa001ToOa002(OrderBean order, PaypalipnBean ipn, String operator) throws Exception;
	
	/**
	 * Alipay付款待确认
	 * 
	 * @param order
	 * @return
	 * @throws Exception
	 */
	public boolean oa001ToOa002(OrderBean order, AlipayNotifyBean notify, String operator) throws Exception;
	
	/**
	 * 西联付款待确认
	 * 
	 * @param order
	 * @return
	 * @throws Exception
	 */
	public boolean oa001ToOa002(OrderBean order, WesternUnionBean westernUnion, String operator) throws Exception;
	
	/**
	 * 付款失败
	 * 
	 * @param order
	 * @return
	 * @throws Exception
	 */
	public boolean oa002ToOa003(OrderBean order, String operator) throws Exception;
	
	/**
	 * Paypal自动付款确认
	 * 
	 * @param order
	 * @return
	 * @throws Exception
	 */
	public boolean oa002ToOs001(OrderBean order, PaypalipnBean ipn) throws Exception;
	
	/**
	 * Alipay自动付款确认
	 * 
	 * @param order
	 * @return
	 * @throws Exception
	 */
	public boolean oa002ToOs001(OrderBean order, AlipayNotifyBean notify) throws Exception;
	
	/**
	 * 付款确认
	 * 
	 * @param order
	 * @return
	 * @throws Exception
	 */
	public boolean oa002ToOs001(OrderBean order, String operator,String message) throws Exception;
	
	/**
	 * 拆单
	 * 
	 * @param order
	 * @return
	 * @throws Exception
	 */
	public boolean os001ToOs006(OrderBean order, String operator) throws Exception;
	
	/**
	 * 打单
	 * 
	 * @param order
	 * @return
	 * @throws Exception
	 */
	public boolean os001ToOs002(OrderBean order, String operator) throws Exception;
	
	/**
	 * 拣货
	 * 
	 * @param order
	 * @return
	 * @throws Exception
	 */
	public boolean os002ToOs003(OrderBean order, String operator) throws Exception;
	
	/**
	 * 包装，出库
	 * 
	 * @param order
	 * @return
	 * @throws Exception
	 */
	public boolean os003ToOs004(OrderBean order, String operator) throws Exception;
	
	/**
	 * 发货，待确认
	 * 
	 * @param order
	 * @return
	 * @throws Exception
	 */
	public boolean os004ToOs005(OrderBean order, OrderShippingBean osb, String operator) throws Exception;
	
	/**
	 * 订单完成
	 * 
	 * @param order
	 * @return
	 * @throws Exception
	 */
	public OrderVo os005ToOc101(OrderBean order, String operator) throws Exception;
	
	/**
	 * 冻结订单
	 * 
	 * @param order
	 * @return
	 * @throws Exception
	 */
	public boolean freezeOrder(OrderBean order, String operator, String memo) throws Exception;
	
	/**
	 * 订单取消冻结
	 * 
	 * @param order
	 * @return
	 * @throws Exception
	 */
	public boolean cancelFreeze(OrderBean order, String operator, String memo) throws Exception;
	
	/**
	 * Paypal纠纷
	 * 
	 * @param order
	 * @return
	 * @throws Exception
	 */
	public boolean setOd001(OrderBean order, String operator, String memo) throws Exception;
	
	/**
	 * Paypal纠纷处理
	 * 
	 * @param order
	 * @param operator
	 * @param cancelOrder
	 * @param refundAmount
	 * @param memo
	 * @return
	 * @throws Exception
	 */
	public boolean od001ToOd101(OrderBean order, String operator, boolean cancelOrder, double refundAmount, String memo) throws Exception;
	
	/**
	 * Paypal调查
	 * 
	 * @param order
	 * @return
	 * @throws Exception
	 */
	public boolean setOd002(OrderBean order, String operator, String memo) throws Exception;
	
	/**
	 * Paypal调查处理
	 * 
	 * @param order
	 * @return
	 * @throws Exception
	 */
	public boolean od002ToOd102(OrderBean order, String operator, String memo) throws Exception;
	
	/**
	 * ChargeBack退单
	 * 
	 * @param order
	 * @return
	 * @throws Exception
	 */
	public boolean setOd003(OrderBean order, String operator, String memo) throws Exception;
	
	/**
	 * ChargeBack退单处理
	 * 
	 * @param order
	 * @return
	 * @throws Exception
	 */
	public boolean od003ToOd103(OrderBean order, String operator, String memo) throws Exception;
	
	/**
	 * 进入风控状态
	 * 
	 * @param order
	 * @param operator
	 * @param memo
	 * @return
	 * @throws Exception
	 */
	public boolean setOr101(OrderBean order, String operator, String memo) throws Exception;
	
	/**
	 * 通过风控
	 * 
	 * @param order
	 * @return
	 * @throws Exception
	 */
	public boolean or101ToOr102(OrderBean order, String operator, String memo) throws Exception;
	
	/**
	 * 未通过风控
	 * 
	 * @param order
	 * @return
	 * @throws Exception
	 */
	public boolean or101ToOr103(OrderBean order, String operator, String memo) throws Exception;
	
	/**
	 * 进入退款状态
	 * 
	 * @param order
	 * @return
	 * @throws Exception
	 */
	public boolean setOr201(OrderBean order, String operator) throws Exception;
	
	/**
	 * 退款处理
	 * 
	 * @param order
	 * @param refundStatus
	 * @return
	 * @throws Exception
	 */
	public boolean or201ToOr2XX(OrderBean order, String refundStatus,Double refundNum, String operator,String remark) throws Exception;
	
	/**
	 * 退款处理
	 * 
	 * @param order
	 * @return
	 * @throws Exception
	 */
	public boolean or2XXToOr230(OrderBean order,Double refundNum, String operator,String remark) throws Exception;
	
	/**添加备注
	 * @param orderId
	 * @param statusCode
	 * @param memo
	 * @param operator
	 * @param visible
	 * @return
	 * @throws Exception
	 */
	public OrderstatusInfoBean insertOrderStatusInfo(OrderstatusInfoBean orderStatusInfo) throws Exception;
	
	/**
	 * 订单发货
	 * 
	 * @param order
	 * @return
	 * @throws Exception
	 */
	public void shipment(OrderShippingBean osb) throws Exception;
	
	/**买家前台Refund功能
	 * @param order
	 * @param operator
	 * @return
	 * @throws Exception
	 */
	public OrderVo statusToOR201(OrderBean order,String operator) throws Exception;
	
	/**买家前台取消订单功能
	 * @param order
	 * @param operator
	 * @return
	 * @throws Exception
	 */
	public OrderVo cancelOrder(OrderBean order,String operator) throws Exception;
	
}
