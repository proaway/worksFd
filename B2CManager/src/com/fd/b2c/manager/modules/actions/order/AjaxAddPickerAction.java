package com.fd.b2c.manager.modules.actions.order;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.b2c.manager.modules.actions.SecureAction;
import com.fd.fashion.dict.bean.AttributeBean;
import com.fd.fashion.order.bean.OrderBean;
import com.fd.fashion.order.bean.OrderProductBean;
import com.fd.fashion.order.bean.PickingBean;
import com.fd.fashion.order.bean.PickingInfoBean;
import com.fd.fashion.order.manager.IOrderManager;
import com.fd.fashion.order.manager.IOrderStatusManager;
import com.fd.fashion.product.manager.IProductManager;
import com.fd.fashion.product.vo.ProductVo;
import com.fd.fashion.seller.bean.UsersBean;
import com.fd.fashion.seller.manager.ISellerManager;
import com.fd.util.JSONUtil;
import com.fd.util.StringUtil;

/**
 * @author zhangling
 * 
 * 添加拣货人
 *
 */
public class AjaxAddPickerAction extends SecureAction {
	@Override
	public void doPerform(RunData data, Context context) throws Exception {
		super.doPerform(data, context);
		String orderIds = data.getParameters().getString("orderIds");
		String picker = data.getParameters().getString("picker");
		String pickDate = data.getParameters().getString("pickDate");
		IOrderManager orderManager = (IOrderManager)getBean("orderManager");
		ISellerManager sellerManager = (ISellerManager)getBean("sellerManager");
		UsersBean user  = sellerManager.getUser(Long.valueOf(picker));
		String actionFlag = data.getParameters().getString("actionFlag");
		String flag = data.getParameters().getString("flag");
		if(orderIds==null || orderIds.equals("")){
			return;
		}else{
			PickingBean pick = new PickingBean();
			pick.setPickingUser(user.getLoginName());
			pick.setStatus(1);
			if(pickDate==null || pickDate.equals("")){
				pick.setPickingDate(new Date());
			}else
				pick.setPickingDate(StringUtil.getAsDate(pickDate));
			
			if(actionFlag!=null && actionFlag.equals("printOrderFlag")&&(flag==null || flag.equals(""))){
				String[] ids = orderIds.split(",");
				for(int i=0;i<ids.length;i++){
					pick.setOrderId(Long.valueOf(ids[i]));
					pick = orderManager.insertPickingBean(pick);
					//pickingInfo插入待拣产品信息
					List<OrderProductBean> orderProducts = orderManager.getOrderProductInfo(pick.getOrderId());
					if(orderProducts!=null && orderProducts.size()>0){
						IProductManager productManager = (IProductManager)getBean("productManager");
						for(OrderProductBean orderPro :orderProducts){
							PickingInfoBean pif = new PickingInfoBean();
							pif.setOrderId(pick.getOrderId());
							pif.setPickingStatus(2);
							pif.setCurrentCount(0);
							pif.setStatus(2);
							pif.setProductCount(orderPro.getQuantity());
							ProductVo p = productManager.getProductVo(orderPro.getProductId());
							if(p!=null){
								pif.setProductId(p.getProduct().getProductId());
								pif.setProductName(p.getProduct().getProductName());
								pif.setSku(p.getProduct().getSku());
								if(orderPro.getStandardId()!=null){
									HashMap<String,AttributeBean> configs = productManager.getProductConfigs(orderPro.getStandardId());
									orderPro.setConfig(configs);
									if(configs!=null&& configs.get("mainValue")!=null){
										pif.setProductStandar1(configs.get("mainValue").getValue());
									}
									if(configs!=null&& configs.get("subValue")!=null){
										pif.setProductStandar1(configs.get("subValue").getValue());
									}
								}
							}
							pif = orderManager.insertPickingInfoBean(pif);
						}
					}
					
					
					//变更订单状态 //已打单待拣货OS002
					IOrderStatusManager orderStatusManager = (IOrderStatusManager)this.getBean("orderStatusManager");
					OrderBean order = new OrderBean();
					order.setOrderId(pick.getOrderId());
					orderStatusManager.os001ToOs002(order, user.getLoginName());
				}
				//打印动作
				
			}
			if(pick!=null){
				Map<String,String> map = new HashMap<String,String>();
				map.put("user", picker);
				map.put("pickerName", pick.getPickingUser());
				map.put("date", StringUtil.getDateString(pick.getPickingDate(), "yyyy-MM-dd HH:mm:ss"));
				if(flag==null || flag.equals("")){
				}else{
					map.put("flag", flag);
				}
				context.put("contentdata", JSONUtil.obj2JSON(map));
			}
		}
	}

}
