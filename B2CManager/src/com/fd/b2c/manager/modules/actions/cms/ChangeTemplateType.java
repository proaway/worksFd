/**
 * ChangeTemplateType.java
 * @author:  Zhou Rongyu
 */
package com.fd.b2c.manager.modules.actions.cms;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.b2c.manager.modules.actions.SecureAction;
import com.fd.constants.SessionConstants;
import com.fd.fashion.cms.bean.CmsTemplateBean;
import com.fd.fashion.cms.manager.ICmsManager;
import com.fd.fashion.seller.bean.UsersBean;
import com.fd.session.FdSession;
import com.fd.session.FdSessionFactory;

/**
 * @CreateDate:  2013-5-20 下午9:14:12 
 * @author:  Zhou Rongyu
 * @Description:  TODO
 * 
 * @version:  V1.0
 */
public class ChangeTemplateType extends SecureAction {
	@Override
	public void doPerform(RunData data, Context context) throws Exception {
		super.doPerform(data, context);
		FdSession session = FdSessionFactory.getFdSession(data);
		UsersBean user = (UsersBean) session.getAttribute(SessionConstants.KEY_LOGIN_USER);
		String templateIds = data.getParameters().getString("templateIds");
		int status = data.getParameters().getInt("status");
		ICmsManager cmsManager = (ICmsManager)this.getBean("cmsManager");
		String[] ids = templateIds.split(",");
		int flag;
		if(status == 0){
			flag = cmsManager.deleteCmsTemplate(ids, user.getLoginName());
		}else{
			flag = cmsManager.forbiddenCmsTemplate(ids,status,user.getLoginName());
		}

		context.put("contentdata", flag);
		
	}
}
