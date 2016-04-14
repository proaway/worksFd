package com.fd.b2c.manager.modules.actions.order;

import java.util.Date;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.b2c.manager.modules.actions.SecureAction;
import com.fd.fashion.order.bean.OrderBean;
import com.fd.fashion.order.bean.PickingBean;
import com.fd.fashion.order.bean.PickingInfoBean;
import com.fd.fashion.order.manager.IOrderManager;
import com.fd.fashion.order.manager.IOrderStatusManager;
import com.fd.fashion.product.bean.ProductBean;
import com.fd.fashion.product.bean.StockoutBean;
import com.fd.fashion.product.manager.IProductManager;
import com.fd.fashion.seller.bean.UsersBean;
import com.fd.session.FdSession;
import com.fd.session.FdSessionFactory;
import com.fd.util.JSONUtil;

/**
 * @author zhangling
 * 
 * 检测拣包时订单及产品信息
 *
 */
public class AjaxCheckPickingAction extends SecureAction {
	@Override
	public void doPerform(RunData data, Context context) throws Exception {
		super.doPerform(data, context);
		IOrderManager orderManager = (IOrderManager)getBean("orderManager");
		String id = data.getParameters().getString("orderId");
		String sku = data.getParameters().getString("sku");
		String status = data.getParameters().getString("status");
		String count = data.getParameters().getString("productCount");
		String flag  =data.getParameters().getString("flag");
		Long orderId = Long.valueOf(id);
		//后台操作人员信息
		FdSession session = FdSessionFactory.getFdSession(data);
		UsersBean user = (UsersBean)session.getAttribute("KEY_LOGIN_USER");
		if(flag!=null && flag.equals("checkOrderNoFlag")){
			//判断该订单是否存在
			OrderBean order = orderManager.getOrder(orderId);
			if(order!=null){
				context.put("contentdata", 1);
			}
		}else if(flag!=null && flag.equals("saveProductSkuFlag")){
			//新增或者更新拣货信息
			PickingInfoBean pif = new PickingInfoBean();
			pif.setOrderId(orderId);
			pif.setSku(sku);
			if(count==null || count.equals("")){
				//插入错误状态
				pif.setStatus(Integer.parseInt(status));
				pif = orderManager.insertPickingInfoBean(pif);
				if(pif!=null){
					context.put("contentdata", 1);
				}
			}else{
				//插入拣货状态
				pif.setStatus(Integer.parseInt(status));
				pif.setCurrentCount(Integer.parseInt(count));
					//当数量大于1时执行更新操作
				int suCount = orderManager.updatePickingInfoBean(pif);
				if(suCount>0){
					context.put("contentdata", 1);
				}
			}
		}else if(flag!=null && flag.equals("deleteOrderFlag")){
			//删除错误状态数据
			PickingInfoBean pif = new PickingInfoBean();
			pif.setOrderId(orderId);
			pif.setSku(sku);
			int suCount = -1 ;
			count = count.trim();
			if(count==null || count.equals("") || Integer.parseInt(count)<1){
				suCount = orderManager.deletePickingInfoBean(pif);
			}else{
				pif.setCurrentCount(0);
				pif.setStatus(2);
				suCount = orderManager.updatePickingInfoBean(pif);
			}
			if(suCount>0){
				context.put("contentdata", 1);
			}
		}else if(flag!=null && flag.equals("updatePickFlag")){
			PickingBean pick = new PickingBean();
			pick.setOrderId(orderId);
			pick.setStatus(2);
			int suCount = orderManager.updatePickingBean(pick);
			if(suCount>0){
				IOrderStatusManager orderStatusManager = (IOrderStatusManager)this.getBean("orderStatusManager");
				OrderBean order = new OrderBean();
				order.setOrderId(orderId);
				boolean f = orderStatusManager.os002ToOs003(order, user.getLoginName());
				if(f){
					context.put("contentdata", 1);
				}
			}
		}else if(flag!=null && flag.equals("noticeProductCountFlag")){
			int suCount = -1;
			if(count==null || count.equals("")){
				count = "0";
			}else{
				count = count.trim();
			}
			if(Integer.parseInt(count)<1){
				//到货处理
				IProductManager productManager = (IProductManager)this.getBean("productManager");
				StockoutBean stockOut  = new StockoutBean();
				stockOut.setSku(sku);
				stockOut.setStockoutCount(0);
				stockOut.setVald(2);
				suCount = productManager.updateStockoutBean(stockOut);
				if(suCount>0){
					PickingInfoBean pif = new PickingInfoBean();
					pif.setOrderId(orderId);
					pif.setSku(sku);
					pif.setStatus(2);
					suCount = orderManager.updatePickingInfoBean(pif);
					if(suCount>0){
						PickingInfoBean pf = new PickingInfoBean();
						pf.setOrderId(orderId);
						pf.setSku(sku);
						pif = orderManager.getPickingInfoBean(pf);
						context.put("contentdata", JSONUtil.obj2JSON(pif));
					}
				}
			}else{
				//缺货登记操作
				IProductManager productManager = (IProductManager)this.getBean("productManager");
				StockoutBean stockOut  = new StockoutBean();
				stockOut.setSku(sku);
				stockOut = productManager.getStockoutBean(stockOut);
				if(stockOut!=null && stockOut.getStockoutDate()!=null){
					//执行缺货登记修改缺货数量功能
					StockoutBean stock  = new StockoutBean();
					Integer cc =  stockOut.getStockoutCount()+Integer.parseInt(count);
					stock.setStockoutCount(cc);
					stock.setVald(1);
					stock.setSku(sku);
					suCount = productManager.updateStockoutBean(stock);
					
				}else{
					//执行缺货登记插入新记录功能
					StockoutBean stock  = new StockoutBean();
					stock.setSku(sku);
					stock.setStockoutCount(Integer.parseInt(count));
					stock.setStockoutDate(new Date());
					stock.setStockoutUser(user.getLoginName());
					stock.setVald(1);
					ProductBean pro = productManager.getProductBeanBySku(sku);
					stock.setProductId(pro.getProductId());
					stock = productManager.insertStockoutBean(stock);
					if(stock!=null){
						suCount = 1;
					}
				}
				if(suCount>0){
					PickingInfoBean pif = new PickingInfoBean();
					pif.setOrderId(orderId);
					pif.setSku(sku);
					pif.setStatus(3);
					suCount = orderManager.updatePickingInfoBean(pif);
					if(suCount>0){
						context.put("contentdata", 1);
					}
				}
			}
			
			
		}
		
	}

}
