/**
 * AddChannel.java
 * @author:  Zhou Rongyu
 */
package com.fd.b2c.manager.modules.actions.cms;

import java.util.Date;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.b2c.manager.modules.actions.SecureAction;
import com.fd.constants.SessionConstants;
import com.fd.fashion.cms.bean.CmsChannelBean;
import com.fd.fashion.cms.manager.ICmsManager;
import com.fd.fashion.seller.bean.UsersBean;
import com.fd.session.FdSession;
import com.fd.session.FdSessionFactory;
import com.fd.util.ParametersUtil;

/**
 * @CreateDate:  2013-5-21 上午11:28:36 
 * @author:  Zhou Rongyu
 * @Description:  TODO
 * 
 * @version:  V1.0
 */
public class DoEditChannel extends SecureAction {
	@Override
	public void doPerform(RunData data, Context context) throws Exception {
		super.doPerform(data, context);
		FdSession session = FdSessionFactory.getFdSession(data);
		UsersBean user = (UsersBean) session.getAttribute(SessionConstants.KEY_LOGIN_USER);
		
		CmsChannelBean cmsChannel = new CmsChannelBean();
		ParametersUtil.initParameters(data, cmsChannel);
		cmsChannel.setOperateTime(new Date());
		cmsChannel.setOperateUser(user.getLoginName());
		ICmsManager cmsManager = (ICmsManager)this.getBean("cmsManager");
		int flag = cmsManager.updateCmsChannel(cmsChannel);
		//data.setTemplate(data, "cms,ChannelList.html"); 
		
		context.put("contentdata",flag);
	}

}
