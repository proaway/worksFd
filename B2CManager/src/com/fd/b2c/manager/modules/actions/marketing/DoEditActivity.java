/**
 * DoEditActivity.java
 * @author:  Zhou Rongyu
 */
package com.fd.b2c.manager.modules.actions.marketing;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.b2c.manager.modules.actions.SecureAction;
import com.fd.constants.SessionConstants;
import com.fd.fashion.product.bean.ActivityBean;
import com.fd.fashion.product.bean.GiftsBean;
import com.fd.fashion.product.bean.GroupsBean;
import com.fd.fashion.product.manager.IProductManager;
import com.fd.fashion.seller.bean.UsersBean;
import com.fd.session.FdSession;
import com.fd.session.FdSessionFactory;
import com.fd.util.ParametersUtil;

/**
 * @CreateDate: 2013-5-23 下午9:46:56
 * @author: Zhou Rongyu
 * @Description: TODO
 * 
 * @version: V1.0
 */
public class DoEditActivity extends SecureAction {
	public void doPerform(RunData data, Context context) throws Exception {
		super.doPerform(data, context);
		FdSession session = FdSessionFactory.getFdSession(data);
		UsersBean user = (UsersBean) session.getAttribute(SessionConstants.KEY_LOGIN_USER);
		ActivityBean activity = new ActivityBean();
		ParametersUtil.initParameters(data, activity);

		String[] countrys = data.getParameters().getStrings("countryIds");
		if (countrys != null) {
			String country = "";
			for (int i = 0; i < countrys.length; i++) {
				country += countrys[i] + ",";
			}
			activity.setCountry(country);
		}
		//String activityId = String.valueOf(System.nanoTime());
		String activityId = data.getParameters().getString("activityId");
		activity.setActivityId(activityId);
		Date date = new Date();
		activity.setLastEditTime(date);
		activity.setOperator(user.getLoginName());
		if (date.getTime() - activity.getStartTime().getTime() >= 0) {
			activity.setStatus(1);
		} else {
			activity.setStatus(0);
		}
		if (date.getTime() - activity.getEndTime().getTime() > 0) {
			activity.setStatus(2);
		}
		IProductManager productManager = (IProductManager) getBean("productManager");

		// 1：赠品 0：组合优惠
		if (1 == activity.getType()) {
			long[] zpProductIds = data.getParameters().getLongs("zpProductId");
			List<GiftsBean> pList = new ArrayList<GiftsBean> ();
			for(int i=0;i<zpProductIds.length;i++){
				GiftsBean gb = new GiftsBean();
				long productId = zpProductIds[i];
				int ntype = data.getParameters().getInt("ntype_"+productId);
				gb.setProductId(productId);
				gb.setNumType(ntype);
				if(1==ntype){
					int num = data.getParameters().getInt("num_"+productId);
					gb.setNum(num);
				}
				pList.add(gb);
			}
			
			productManager.updateGiftsActivity(activity, pList);
			context.put("contentdata", 1);
		} else if (0 == activity.getType()) {
			long[] groupProductIds = data.getParameters().getLongs("groupProductId");
			GroupsBean gb = new GroupsBean();
			if(groupProductIds.length >= 1){
				gb.setGroupProductId1(groupProductIds[0]);
				Double groupDiscount1 = data.getParameters().getDouble("groupDiscount1");
				gb.setGroupDiscount1(groupDiscount1);
			}
			if(groupProductIds.length >= 2){
				gb.setGroupProductId2(groupProductIds[1]);
				Double groupDiscount2 = data.getParameters().getDouble("groupDiscount2");
				gb.setGroupDiscount2(groupDiscount2);
			}
			if(groupProductIds.length >= 3){
				gb.setGroupProductId3(groupProductIds[2]);
				Double groupDiscount3 = data.getParameters().getDouble("groupDiscount3");
				gb.setGroupDiscount3(groupDiscount3);
			}
			if(groupProductIds.length >= 4){
				gb.setGroupProductId4(groupProductIds[3]);
				Double groupDiscount4 = data.getParameters().getDouble("groupDiscount4");
				gb.setGroupDiscount4(groupDiscount4);
			}
			productManager.updateGroupActivity(activity, gb);
			context.put("contentdata", 1);
		}

	}

}
