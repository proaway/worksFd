package com.fd.b2c.manager.modules.screens.order;

import java.util.ArrayList;
import java.util.List;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.b2c.manager.modules.screens.SecureScreen;
import com.fd.fashion.dict.bean.OrderStatusBean;
import com.fd.fashion.dict.manager.IDictManager;
import com.fd.fashion.order.bean.OrderBean;
import com.fd.fashion.order.bean.PickingBean;
import com.fd.fashion.order.bean.PickingInfoBean;
import com.fd.fashion.order.manager.IOrderManager;
import com.fd.fashion.order.vo.PickingVo;
import com.fd.fashion.seller.bean.UsersBean;
import com.fd.session.FdSession;
import com.fd.session.FdSessionFactory;
import com.fd.util.PageInfo;

public class PickingOrder extends SecureScreen{
	public void doBuildTemplate(RunData data, Context context) throws Exception {
		super.doBuildTemplate(data, context);
		data.setLayoutTemplate("/Layout.html");
		
		//后台操作人员信息
		FdSession session = FdSessionFactory.getFdSession(data);
		UsersBean user = (UsersBean)session.getAttribute("KEY_LOGIN_USER");
		PickingBean pb = new PickingBean();
		pb.setPickingUser(user.getLoginName());
		pb.setStatus(1);
		context.put("picker", user.getLoginName());
		/**分页信息*/
		PageInfo pageInfo = new PageInfo();
		int nowPage = data.getParameters().getInt("pageIndex", 1);
		pageInfo.setPageIndex(nowPage);
		pageInfo.setPageSize(100);
		context.put("pageInfo", pageInfo);
		
		IOrderManager orderManager = (IOrderManager)getBean("orderManager");
		IDictManager dictManager = (IDictManager)getBean("dictManager");
		
		List<PickingBean> pickList = orderManager.getPickingBeans(pb, pageInfo);
		if(pickList!=null && pickList.size()>0){
			List<PickingVo> pickListVo = new ArrayList<PickingVo>();
			for(PickingBean p :pickList){
				PickingVo  pkv = new PickingVo();
				pkv.setPick(p);
				List<PickingInfoBean> pickInfos = orderManager.getPickingInfoBeans(p.getOrderId());
				if(pickInfos!=null && pickInfos.size()>0){
					Integer count = 0;
					for(PickingInfoBean pickBean :pickInfos){
						if(pickBean.getProductCount()!=null && pickBean.getProductCount()>0 ){
							count = count + pickBean.getProductCount();
						}
					}
					pkv.setTotalCount(count);
					pkv.setCalCounts(pickInfos.size());
					pkv.setPickInfos(pickInfos);
				}
				OrderBean	orderBean = orderManager.getOrder(p.getOrderId());
				OrderStatusBean status = dictManager.getOrderStatus(orderBean.getOrderStatus());
				pkv.setOrderStatus(orderBean.getOrderStatus());
				pkv.setOrderStatusCn(status.getStatusCn());
				pickListVo.add(pkv);
			}
			if(pickListVo!=null && pickListVo.size()>0){
				context.put("pickList", pickListVo);
			}
			
		}
		
	}

}
