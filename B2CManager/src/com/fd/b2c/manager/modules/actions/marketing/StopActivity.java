/**
 * CreateCoupon.java
 * @author:  ZhouRongyu
 */
package com.fd.b2c.manager.modules.actions.marketing;

import java.util.Date;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.b2c.manager.modules.actions.SecureAction;
import com.fd.constants.SessionConstants;
import com.fd.fashion.product.bean.ActivityBean;
import com.fd.fashion.product.manager.IProductManager;
import com.fd.fashion.seller.bean.UsersBean;
import com.fd.session.FdSession;
import com.fd.session.FdSessionFactory;

/**
 * @CreateDate:  2013-4-24 下午4:23:54 
 * @author:  ZhouRongyu
 * @Description:  TODO
 * 
 * @version:  V1.0
 */
public class StopActivity extends SecureAction {
	public void doPerform(RunData data, Context context) throws Exception {
		super.doPerform(data, context);
		FdSession session = FdSessionFactory.getFdSession(data);
		UsersBean user = (UsersBean) session.getAttribute(SessionConstants.KEY_LOGIN_USER);
		ActivityBean activity = new ActivityBean();
		String activityId = data.getParameters().getString("activityId");
		activity.setActivityId(activityId);
		IProductManager productManager = (IProductManager) getBean("productManager");
		//activity = productManager.getActivityList(activity, null).get(0);
		activity.setStatus(2);
		activity.setLastEditTime(new Date());
		activity.setOperator(user.getLoginName());
		int flag = productManager.updateActivity(activity);
		
		context.put("contentdata", flag);
	}

}
