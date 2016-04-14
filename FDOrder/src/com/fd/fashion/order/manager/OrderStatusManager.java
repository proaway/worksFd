package com.fd.fashion.order.manager;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.fd.fashion.dict.bean.OrderStatusBean;
import com.fd.fashion.finance.bean.FinancialBean;
import com.fd.fashion.finance.vo.FinancialVo;
import com.fd.fashion.order.bean.OrderBean;
import com.fd.fashion.order.bean.OrderPaymentinfoBean;
import com.fd.fashion.order.bean.OrderProductBean;
import com.fd.fashion.order.bean.OrderShippingBean;
import com.fd.fashion.order.bean.OrderstatusInfoBean;
import com.fd.fashion.order.util.OrderUtil;
import com.fd.fashion.order.vo.OrderVo;
import com.fd.fashion.product.bean.ProductBean;
import com.fd.payment.bean.AlipayNotifyBean;
import com.fd.payment.bean.PaymentTypeBean;
import com.fd.payment.bean.PaypalipnBean;
import com.fd.payment.bean.WesternUnionBean;
import com.fd.util.CommissionUtil;
import com.fd.util.CullNumUtil;


/**
 * @CreateDate: 2013-4-9 下午06:52:38
 * @author Longli
 * @Description: 封装订单状态流转方法接口实现类
 * 
 */
@Transactional(rollbackFor = Exception.class)
@Component
public class OrderStatusManager extends OrderManager implements IOrderStatusManager {
	/**
	 * 付款待确认
	 * 
	 * @param order
	 * @return
	 * @throws Exception
	 */
	public boolean oa001ToOa002(OrderBean order, String operator) throws Exception {
		// TODO 付款待确认
		String statusCode = "OA002";
		OrderBean o = new OrderBean();
		o.setOrderId(order.getOrderId());
		o.setOrderStatus(statusCode);
		o.addOldOrderStatus("OA001");
		if (StringUtils.isNotEmpty(order.getTxnId())) {
			o.setTxnId(order.getTxnId());
		}
		o.setPaymentType(order.getPaymentType());

		addOrderstatusInfo(o.getOrderId(), statusCode, "", operator, true);
		return updateOrder(o)>0;
	}
	
	/**
	 * Paypal付款待确认
	 * 
	 * @param order
	 * @return
	 * @throws Exception
	 */
	public boolean oa001ToOa002(OrderBean order, PaypalipnBean ipn, String operator) throws Exception {
		OrderPaymentinfoBean paymentInfo = new OrderPaymentinfoBean();
		paymentInfo.setPayMethodId(ipn.getId());
		paymentInfo.setPaymentType(order.getPaymentType());
		paymentInfo.setOrderId(order.getOrderId());
		paymentInfo.setPayTime(new Date());
		paymentInfo.setTxnId(ipn.getTxnId());
		orderService.insertOrderPaymentinfoBean(paymentInfo);
		return oa001ToOa002(order, operator);
	}
	
	/**
	 * Alipay付款待确认
	 * 
	 * @param order
	 * @return
	 * @throws Exception
	 */
	public boolean oa001ToOa002(OrderBean order, AlipayNotifyBean notify, String operator) throws Exception {
		if (!order.getOrderStatus().equals("OA001")) {
			return false;
		}
		OrderPaymentinfoBean paymentInfo = new OrderPaymentinfoBean();
		paymentInfo.setPayMethodId(notify.getId());
		paymentInfo.setPaymentType(order.getPaymentType());
		paymentInfo.setOrderId(order.getOrderId());
		paymentInfo.setPayTime(new Date());
		paymentInfo.setTxnId(notify.getTradeNo());
		orderService.insertOrderPaymentinfoBean(paymentInfo);
		return oa001ToOa002(order, operator);
	}
	
	/**
	 * 西联付款待确认
	 * 
	 * @param order
	 * @return
	 * @throws Exception
	 */
	public boolean oa001ToOa002(OrderBean order, WesternUnionBean westernUnion, String operator) throws Exception {
		paymentService.insertWesternUnionBean(westernUnion);
		OrderPaymentinfoBean paymentInfo = new OrderPaymentinfoBean();
		paymentInfo.setPayMethodId(westernUnion.getId());
		paymentInfo.setPaymentType(order.getPaymentType());
		paymentInfo.setOrderId(order.getOrderId());
		paymentInfo.setPayTime(new Date());
		paymentInfo.setTxnId(westernUnion.getMtcnCode());
		orderService.insertOrderPaymentinfoBean(paymentInfo);
		return oa001ToOa002(order, operator);
	}
	
	/**
	 * 付款失败
	 * 
	 * @param order
	 * @return
	 * @throws Exception
	 */
	public boolean oa002ToOa003(OrderBean order, String operator) throws Exception {
		// TODO 付款失败
		String statusCode = "OA003";
		OrderBean o = new OrderBean();
		o.setOrderId(order.getOrderId());
		o.setOrderStatus(statusCode);
		o.addOldOrderStatus("OA002");
		if (StringUtils.isNotEmpty(order.getTxnId())) {
			o.setTxnId(order.getTxnId());
		}

		addOrderstatusInfo(o.getOrderId(), statusCode, "", operator, true);
		return updateOrder(o)>0;
	}
	
	/**
	 * Paypal自动付款确认
	 * 
	 * @param order
	 * @return
	 * @throws Exception
	 */
	public boolean oa002ToOs001(OrderBean order, PaypalipnBean ipn) throws Exception {
		putPaymentInfo(order, ipn.getId(), ipn.getTxnId());
		if ("OA002".equals(order.getOrderStatus())) {
			return oa002ToOs001(order, "", "");
		}
		return true;
	}
	
	/**
	 * Alipay自动付款确认
	 * 
	 * @param order
	 * @return
	 * @throws Exception
	 */
	public boolean oa002ToOs001(OrderBean order, AlipayNotifyBean notify) throws Exception {
		putPaymentInfo(order, notify.getId(), notify.getTradeNo());
		if ("OA002".equals(order.getOrderStatus())) {
			return oa002ToOs001(order, "", "");
		}
		return true;
	}
	
	private void putPaymentInfo(OrderBean order, long payMethodId, String txnId) throws Exception {
		OrderPaymentinfoBean paymentInfo = new OrderPaymentinfoBean();
		paymentInfo.setOrderId(order.getOrderId());
		paymentInfo = orderService.getOrderPaymentinfoBean(paymentInfo);
		if (paymentInfo == null) {
			paymentInfo = new OrderPaymentinfoBean();
			paymentInfo.setPayMethodId(payMethodId);
			paymentInfo.setPaymentType(order.getPaymentType());
			paymentInfo.setOrderId(order.getOrderId());
			paymentInfo.setPayTime(new Date());
			paymentInfo.setTxnId(txnId);
			orderService.insertOrderPaymentinfoBean(paymentInfo);
		} else {
			paymentInfo.setPayMethodId(payMethodId);
			paymentInfo.setTxnId(txnId);
			orderService.updateOrderPaymentinfoBean(paymentInfo);
		}
	}
	
	/**
	 * 付款确认
	 * 
	 * @param order
	 * @return
	 * @throws Exception
	 */
	public boolean oa002ToOs001(OrderBean order, String operator,String message) throws Exception {
		// TODO 付款确认
		String statusCode = "OS001";
		String orderStatusEn = dictService.getOrderStatusBean("OS001").getStatusEn();
		OrderBean o = new OrderBean();
		o.setOrderId(order.getOrderId());
		o.setOrderStatus(statusCode);
		o.addOldOrderStatus("OA002");
		OrderstatusInfoBean status = addOrderstatusInfo(o.getOrderId(), statusCode, orderStatusEn, operator, true);
		addOrderAmount(order.getOrderId(), OrderUtil.calOrderTotal(order), OrderUtil.AMOUNT_TYPE_PAY, operator, status.getMemo());
		
		if(updateOrder(o)>0){
			
			//更新产品库存
			List<OrderProductBean> orderProducts = this.getOrderProductInfo(order.getOrderId());
			for(int i=0;i<orderProducts.size();i++){
				OrderProductBean opb = orderProducts.get(i);
				Long productId = opb.getProductId();
				ProductBean pb = new ProductBean();
				pb.setProductId(productId);
				pb = productService.getProductBean(pb);
				int stock = pb.getStock() - opb.getQuantity();
				pb.setStock(stock);
				productService.updateProductBean(pb);
			}
			
			//财务记录
			FinancialBean financial = new FinancialBean();
			
			financial.setAmount(order.getTotal());
			financial.setAmountType("1");
			//financial.setBalance(balance);
			financial.setCreateTime(new Date());
			financial.setLinkeId(order.getOrderId());
			Map<Integer, PaymentTypeBean> ptMap = this.getPaymentTypeMap();
			financial.setPaymentType(ptMap.get(order.getPaymentType()).getName());
			financial.setFinancialStatus("已支付");
			financialService.insertFinancialBean(financial);
			
			FinancialVo fv = financialService.getFinancialSum();
			Double balance = CullNumUtil.cullNum(fv.getIncomeTotal() + fv.getExpendTotal());
			//Double balance = financialSum + financial.getAmount();
			financial.setBalance(balance);
			financialService.updateFinancialBean(financial);
			
			//佣金记录
			Double total = CullNumUtil.cullNum(CommissionUtil.calCommission(1.05, order.getTotal()));
			Double commission = order.getTotal() - total;
			FinancialBean financial2 = new FinancialBean();
			financial2.setLinkeId(order.getOrderId());
			financial2.setAmount(-commission);
			financial2.setAmountType("5");
			financial2.setCreateTime(new Date());
			financial2.setFinancialStatus("完成");
			financialService.insertFinancialBean(financial2);
			
			FinancialVo fv2 = financialService.getFinancialSum();
			Double balance2 = CullNumUtil.cullNum(fv2.getIncomeTotal() + fv2.getExpendTotal());
			financial2.setBalance(balance2);
			financialService.updateFinancialBean(financial2);
			return true;
		}else{
			return false;
		}
		
	}
	
	/**
	 * 拆单
	 * 
	 * @param order
	 * @return
	 * @throws Exception
	 */
	public boolean os001ToOs006(OrderBean order, String operator) throws Exception {
		// TODO 拆单
		return false;
	}
	
	/**
	 * 打单
	 * 
	 * @param order
	 * @return
	 * @throws Exception
	 */
	public boolean os001ToOs002(OrderBean order, String operator) throws Exception {
		// TODO 打单
		String statusCode = "OS002";
		OrderBean o = new OrderBean();
		o.setOrderId(order.getOrderId());
		o.setOrderStatus(statusCode);
		
		addOrderstatusInfo(o.getOrderId(), statusCode, "", operator, true);
		return updateOrder(o)>0;
	}
	
	/**
	 * 拣货
	 * 
	 * @param order
	 * @return
	 * @throws Exception
	 */
	public boolean os002ToOs003(OrderBean order, String operator) throws Exception {
		// TODO 拣货
		String statusCode = "OS003";
		OrderBean o = new OrderBean();
		o.setOrderId(order.getOrderId());
		o.setOrderStatus(statusCode);
		addOrderstatusInfo(o.getOrderId(), statusCode, "", operator, true);
		return updateOrder(o)>0;
	}
	
	/**
	 * 包装，出库
	 * 
	 * @param order
	 * @return
	 * @throws Exception
	 */
	public boolean os003ToOs004(OrderBean order, String operator) throws Exception {
		// TODO 包装，出库
		String statusCode = "OS004";
		OrderBean o = new OrderBean();
		o.setOrderId(order.getOrderId());
		o.setOrderStatus(statusCode);

		addOrderstatusInfo(o.getOrderId(), statusCode, "", operator, true);
		return updateOrder(o)>0;
	}
	
	/**
	 * 发货，待确认
	 * 
	 * @param order
	 * @return
	 * @throws Exception
	 */
	public boolean os004ToOs005(OrderBean order,OrderShippingBean osb, String operator) throws Exception {
		// TODO 发货，待确认
		String statusCode = "OS005";
		OrderBean o = new OrderBean();
		o.setOrderId(order.getOrderId());
		o.setOrderStatus(statusCode);
		String memo = "ShippingType:"+ osb.getShippingType() + ", TrackingNo is " + osb.getTrackingNo();
		addOrderstatusInfo(o.getOrderId(), statusCode, memo, operator, true);
		return updateOrder(o)>0;
	}
	
	/**
	 * 订单完成
	 * 
	 * @param order
	 * @return
	 * @throws Exception
	 */
	public OrderVo os005ToOc101(OrderBean order, String operator) throws Exception {
		// TODO 订单完成
		String statusCode = "OC101";
		OrderBean o = new OrderBean();
		o.setOrderId(order.getOrderId());
		o.setOrderStatus(statusCode);
		String orderStatusEn = dictService.getOrderStatusBean("OC101").getStatusEn();
		addOrderstatusInfo(o.getOrderId(), statusCode, "", operator, true);
		boolean flag = updateOrder(o)>0;
		if(flag==true){
			OrderVo orderVo = new OrderVo();
			orderVo.setOrderId(o.getOrderId());
			orderVo.setOrderStatus(statusCode);
			orderVo.setOrderStatusCN(orderStatusEn);
			return orderVo;
		}
		return null;
	}
	
	/**
	 * 冻结订单
	 * 
	 * @param order
	 * @return
	 * @throws Exception
	 */
	public boolean freezeOrder(OrderBean order, String operator, String memo) throws Exception {
		// TODO 冻结订单
		String statusCode = "OF001";
		OrderBean o = new OrderBean();
		o.setOrderId(order.getOrderId());
		o.setFreeze(statusCode);
		int i = orderService.updateOrderBean(o);
		if (i>0) {
			addOrderstatusInfo(o.getOrderId(), statusCode, "", operator, false);
			return true;
		}
		return updateOrder(o)>0;
	}
	
	/**
	 * 订单取消冻结
	 * 
	 * @param order
	 * @return
	 * @throws Exception
	 */
	public boolean cancelFreeze(OrderBean order, String operator, String memo) throws Exception {
		// TODO 订单取消冻结
		//String statusCode = "OF002";
		String statusCode = order.getOrderStatus();
		OrderBean o = new OrderBean();
		o.setOrderId(order.getOrderId());
		o.setFreeze("");
		int i = orderService.updateOrderBean(o);
		if (i>0) {
			addOrderstatusInfo(o.getOrderId(), statusCode, "", operator, false);
			return true;
		}
		return false;
	}
	
	/**
	 * Paypal纠纷
	 * 
	 * @param order
	 * @return
	 * @throws Exception
	 */
	public boolean setOd001(OrderBean order, String operator, String memo) throws Exception {
		// TODO Paypal纠纷
		String statusCode = "OD001";
		OrderBean o = new OrderBean();
		o.setOrderId(order.getOrderId());
		o.setDisputeStatus(statusCode);
		int i = orderService.updateOrderBean(o);
		if (i>0) {
			addOrderstatusInfo(o.getOrderId(), statusCode, memo, operator, true);
			return true;
		}
		return false;
	}
	
	/**
	 * Paypal纠纷处理
	 * 
	 * @param order 订单
	 * @param operator 操作人
	 * @param cancelOrder 是否取消订单，true-是，false-否
	 * @param refundAmount 退款金额，cancelOrder=true
	 * @param memo
	 * @return
	 * @throws Exception
	 */
	public boolean od001ToOd101(OrderBean order, String operator, boolean cancelOrder, double refundAmount, String memo) throws Exception {
		// TODO Paypal纠纷处理
		String statusCode = "OD101";
		OrderBean o = new OrderBean();
		o.setOrderId(order.getOrderId());
		o.setDisputeStatus(statusCode);
		
		int i = orderService.updateOrderBean(o);
		if (i>0) { // 处理纠纷状态
			addOrderstatusInfo(o.getOrderId(), statusCode, memo, operator, true);
		}
		if (cancelOrder) { // 取消订单
			o.setOrderStatus("OC202");
			i = orderService.updateOrderBean(o);
			addOrderstatusInfo(o.getOrderId(), "OC202", "", operator, true);
			// TODO 计算订单余额，全额退款
			
		} else { // 继续订单
			
		}
		return i>0;
	}
	
	/**
	 * Paypal调查
	 * 
	 * @param order
	 * @return
	 * @throws Exception
	 */
	public boolean setOd002(OrderBean order, String operator, String memo) throws Exception {
		// TODO Paypal调查
		return false;
	}
	
	/**
	 * Paypal调查处理
	 * 
	 * @param order
	 * @return
	 * @throws Exception
	 */
	public boolean od002ToOd102(OrderBean order, String operator, String memo) throws Exception {
		// TODO Paypal调查处理
		return false;
	}
	
	/**
	 * ChargeBack退单
	 * 
	 * @param order
	 * @return
	 * @throws Exception
	 */
	public boolean setOd003(OrderBean order, String operator, String memo) throws Exception {
		// TODO ChargeBack退单
		return false;
	}
	
	/**
	 * ChargeBack退单处理
	 * 
	 * @param order
	 * @return
	 * @throws Exception
	 */
	public boolean od003ToOd103(OrderBean order, String operator, String memo) throws Exception {
		// TODO ChargeBack退单
		return false;
	}
	
	/**
	 * 进入风控状态
	 * 
	 * @param order
	 * @return
	 * @throws Exception
	 */
	public boolean setOr101(OrderBean order, String operator, String memo) throws Exception {
		// TODO 进入风控状态
		String statusCode = "OR101";
		OrderBean o = new OrderBean();
		o.setOrderId(order.getOrderId());
		o.setRiskStatus(statusCode);
		int i = orderService.updateOrderBean(o);
		if (i>0) {
			addOrderstatusInfo(o.getOrderId(), statusCode, memo, operator, true);
			return true;
		}
		return false;
	}
	
	/**
	 * 通过风控
	 * 
	 * @param order
	 * @return
	 * @throws Exception
	 */
	public boolean or101ToOr102(OrderBean order, String operator, String memo) throws Exception {
		// TODO 通过风控
		String statusCode = "OR102";
		OrderBean o = new OrderBean();
		o.setOrderId(order.getOrderId());
		o.setRiskStatus(statusCode);
		int i = orderService.updateOrderBean(o);
		if (i>0) {
			addOrderstatusInfo(o.getOrderId(), statusCode, memo, operator, true);
		}
		// TODO 判断支付方式，以及是否需要确认付款（比如信用卡Capture）
		int captureFlag = 0;
		if (captureFlag == 1) { // Paypal 支付，需要手动确认
			// TODO 
		}
		
		return i>0;
	}
	
	/**
	 * 未通过风控
	 * 
	 * @param order
	 * @return
	 * @throws Exception
	 */
	public boolean or101ToOr103(OrderBean order, String operator, String memo) throws Exception {
		// TODO 未通过风控
		String statusCode = "OR103";
		OrderBean o = new OrderBean();
		o.setOrderId(order.getOrderId());
		o.setRiskStatus(statusCode);
		int i = orderService.updateOrderBean(o);
		if (i<=0) {
			throw new Exception("更新订单状态出错");
		}
		addOrderstatusInfo(o.getOrderId(), statusCode, memo, operator, true);
		// 取消订单
		o.setOrderStatus("OC202");
		i = orderService.updateOrderBean(o);
		addOrderstatusInfo(o.getOrderId(), "OC202", "", operator, true);
		// TODO 计算订单余额，全额退款
		// TODO 判断支付方式，以及操作（比如Void或Refund）
		int captureFlag = 0;
		if (captureFlag == 1) { // Paypal 支付，需要退款
			// TODO Paypal Refund
		}
		
		return i>0;
	}
	
	/**
	 * 进入退款状态
	 * 
	 * @param order
	 * @return
	 * @throws Exception
	 */
	public boolean setOr201(OrderBean order, String operator) throws Exception {
		// TODO 进入退款状态
		return false;
	}
	
	/**
	 * 退款处理
	 * 
	 * @param order
	 * @param refundStatus
	 * @return
	 * @throws Exception
	 */
	public boolean or201ToOr2XX(OrderBean order, String refundType, Double refundNum, String operator, String remark) throws Exception {
		// TODO 退款处理
		OrderStatusBean osb = dictService.getOrderStatusBean(refundType);
		if(order.getRefundStatus().equals("OR213")){
			if("OR203".equals(refundType)||"OR205".equals(refundType)){
				//订单状态变为订单完成，退款完成
				OrderBean o = new OrderBean();
				o.setOrderId(order.getOrderId());
				o.setRefundAmount(refundNum);
				o.setRefundStatus("OR214");
				o.setOrderStatus("OC101");
				this.updateOrder(o);
				//订单状态更新退款完成记录
				String memo = "refund num is :"+ refundNum;
				addOrderstatusInfo(order.getOrderId(), "OR214", memo, operator, true);
				if(remark != null){
					addOrderstatusInfo(order.getOrderId(), "OR214", remark, operator, false);
				}
				//退款金额记录
				this.addOrderAmount(order.getOrderId(), refundNum, 3, operator, remark);
			}else if("OR209".equals(refundType)||"OR210".equals(refundType)||"OR212".equals(refundType)){
				//订单状态变为待发货，退款完成
				OrderBean o = new OrderBean();
				o.setOrderId(order.getOrderId());
				o.setRefundAmount(refundNum);
				o.setRefundStatus("OR214");
				o.setOrderStatus("OS001");
				this.updateOrder(o);
				//订单状态更新
				String memo = "refund num is :"+ refundNum;
				addOrderstatusInfo(order.getOrderId(), "OR214", memo, operator, true);
				if(remark != null){
					addOrderstatusInfo(order.getOrderId(), "OR214", remark, operator, false);
				}
				addOrderstatusInfo(order.getOrderId(), "OS001", remark, operator, true);
			}
		}else{
			if("OR202".equals(refundType)||"OR204".equals(refundType)){
				//订单状态变为退款取消订单，退款完成
				OrderBean o = new OrderBean();
				o.setOrderId(order.getOrderId());
				o.setRefundAmount(refundNum);
				o.setRefundStatus("OR214");
				o.setOrderStatus("OC202");
				this.updateOrder(o);
				
				//订单状态更新
				String memo = "refund num is :"+ refundNum;
				addOrderstatusInfo(order.getOrderId(), refundType, memo, operator, true);
				addOrderstatusInfo(order.getOrderId(), "OR214", "", operator, true);
				if(remark != null){
					addOrderstatusInfo(order.getOrderId(), "OR214", remark, operator, false);
				}
				addOrderstatusInfo(order.getOrderId(), "OC202", "", operator, true);
				
				//退款金额记录
				this.addOrderAmount(order.getOrderId(), refundNum, 3, operator, remark);
			}else if("OR203".equals(refundType)||"OR205".equals(refundType)){
				//订单退款状态变为等待买家退货
				OrderBean o = new OrderBean();
				o.setOrderId(order.getOrderId());
				o.setRefundAmount(refundNum);
				o.setRefundStatus("OR213");
				this.updateOrder(o);
				//订单状态更新
				String memo =  "refund num is :"+ refundNum;
				addOrderstatusInfo(order.getOrderId(), refundType, memo, operator, true);
				addOrderstatusInfo(order.getOrderId(), "OR213", "", operator, true);
				if(remark != null){
					addOrderstatusInfo(order.getOrderId(), "OR213", remark, operator, false);
				}
				
			}else if("OR206".equals(refundType)||"OR208".equals(refundType)||"OR211".equals(refundType)){
				//订单状态变为待发货，退款完成
				OrderBean o = new OrderBean();
				o.setOrderId(order.getOrderId());
				o.setRefundAmount(refundNum);
				o.setRefundStatus("OR214");
				o.setOrderStatus("OS001");
				this.updateOrder(o);
				
				//订单状态更新
				String memo = "refund num is :"+ refundNum;
				addOrderstatusInfo(order.getOrderId(), refundType, memo, operator, true);
				addOrderstatusInfo(order.getOrderId(), "OR214", "", operator, true);
				if(remark != null){
					addOrderstatusInfo(order.getOrderId(), "OR214", remark, operator, false);
				}
				String memo2 = "Pending seller's confirmation";
				addOrderstatusInfo(order.getOrderId(), "OS001", memo2, operator, true);
				//退款金额记录
				this.addOrderAmount(order.getOrderId(), refundNum, 3, operator, remark);
				
			}else if("OR207".equals(refundType)){
				//订单状态变为订单完成，退款完成
				OrderBean o = new OrderBean();
				o.setOrderId(order.getOrderId());
				o.setRefundAmount(refundNum);
				o.setRefundStatus("OR214");
				o.setOrderStatus("OC101");
				this.updateOrder(o);
				
				//订单状态更新
				addOrderstatusInfo(order.getOrderId(), refundType, "", operator, true);
				addOrderstatusInfo(order.getOrderId(), "OR214", "", operator, true);
				if(remark != null){
					addOrderstatusInfo(order.getOrderId(), "OR214", remark, operator, false);
				}
				addOrderstatusInfo(order.getOrderId(), "OC101", "", operator, true);
				
			}else if("OR209".equals(refundType)||"OR210".equals(refundType)||"OR212".equals(refundType)){
				//订单退款状态变为等待买家退货
				OrderBean o = new OrderBean();
				o.setOrderId(order.getOrderId());
				o.setRefundAmount(refundNum);
				o.setRefundStatus("OR213");
				this.updateOrder(o);
				//订单状态更新
				String memo = osb.getStatusEn();
				addOrderstatusInfo(order.getOrderId(), refundType, memo, operator, true);
				addOrderstatusInfo(order.getOrderId(), "OR213", "", operator, true);
				if(remark != null){
					addOrderstatusInfo(order.getOrderId(), "OR213", remark, operator, false);
				}
				
			}
		}
		
		return true;
	}
	
	/**
	 * 退款处理
	 * 
	 * @param order
	 * @return
	 * @throws Exception
	 */
	public boolean or2XXToOr230(OrderBean order,Double refundNum, String operator,String remark) throws Exception {
		// TODO 退款处理

		return false;
	}

	/**
	 * 添加备注
	 * 
	 * @param orderStatusInfo
	 * @return
	 * @throws Exception
	 */
	public OrderstatusInfoBean insertOrderStatusInfo(
			OrderstatusInfoBean orderStatusInfo) throws Exception {
		if(orderStatusInfo!=null){
			boolean visible = false;
			if(orderStatusInfo.getStatus()==1){
				visible = true;
			}
			orderStatusInfo = addOrderstatusInfo(orderStatusInfo.getOrderId(), orderStatusInfo.getOrderStatus(), orderStatusInfo.getMemo(), orderStatusInfo.getOperator(), visible);
			if(orderStatusInfo!=null){
				String orderStatusCn = dictService.getOrderStatusBean(orderStatusInfo.getOrderStatus()).getStatusCn();
				orderStatusInfo.setOrderStatusCn(orderStatusCn);
			}
			
			return  orderStatusInfo;
		}
		return null;
	}
	
	/**
	 * 订单发货
	 * 
	 * @param order
	 * @return
	 * @throws Exception
	 */
	public void shipment(OrderShippingBean osb) throws Exception{
		osb = orderService.insertOrderShippingBean(osb);
		if(osb.getOrderShippingId() != null){
			//部分退款，重新发货
			
			//正常发货
			OrderBean order = new OrderBean();
			order.setOrderId(osb.getOrderId());
			order.setOrderStatus("OS005");
			orderService.updateOrderBean(order);
			
			if(order.getRefundStatus() != null){
				
			}else{
				this.os004ToOs005(order,osb,"SYSTEM");
			}
		}
	}

	/**买家前台Refund功能
	 * @param order
	 * @param operator
	 * @return
	 * @throws Exception
	 */
	public OrderVo statusToOR201(OrderBean order, String operator)
			throws Exception {
		String statusCode="OR201";
		OrderBean o = new OrderBean();
		o.setOrderId(order.getOrderId());
		o.setRefundStatus(statusCode);
		String memo = dictService.getOrderStatusBean("OR201").getStatusEn();
		addOrderstatusInfo(o.getOrderId(), statusCode, "", operator, true);
		boolean index = updateOrder(o)>0;
		if(index==true){
			OrderVo orderVo = new OrderVo();
			orderVo.setOrderId(o.getOrderId());
			orderVo.setRefundStatus(statusCode);
			orderVo.setOrderStatusCN(memo);
			return orderVo;
		}
		return null;
	}
	/**买家前台取消订单功能
	 * @param order
	 * @param operator
	 * @return
	 * @throws Exception
	 */
	public OrderVo cancelOrder(OrderBean order, String operator)
			throws Exception {
		String statusCode="OC201";
		OrderBean o = new OrderBean();
		o.setOrderId(order.getOrderId());
		o.setOrderStatus(statusCode);
		String memo = dictService.getOrderStatusBean("OC201").getStatusEn();
		addOrderstatusInfo(o.getOrderId(), statusCode, memo, operator, true);
		boolean  index = updateOrder(o)>0;
		if(index==true){
			OrderVo orderVo = new OrderVo();
			orderVo.setOrderId(o.getOrderId());
			orderVo.setOrderStatus(statusCode);
			orderVo.setOrderStatusCN(memo);
			return orderVo;
		}
		return null;
	}
	
	
}
