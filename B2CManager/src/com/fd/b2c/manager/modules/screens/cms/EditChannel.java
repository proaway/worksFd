/**
 * EditTemplate.java
 * @author:  Zhou Rongyu
 */
package com.fd.b2c.manager.modules.screens.cms;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.b2c.manager.modules.screens.SecureScreen;
import com.fd.fashion.cms.bean.CmsChannelBean;
import com.fd.fashion.cms.bean.CmsTemplateBean;
import com.fd.fashion.cms.manager.ICmsManager;

/**
 * @CreateDate:  2013-5-20 下午4:10:46 
 * @author:  Zhou Rongyu
 * @Description:  TODO
 * 
 * @version:  V1.0
 */
public class EditChannel extends SecureScreen {
	@Override
	public void doBuildTemplate(RunData data, Context context) throws Exception {
		super.doBuildTemplate(data, context);
		data.setLayoutTemplate("/NoLayout.html");
		String channelId = data.getParameters().getString("channelId");
		ICmsManager cmsManager = (ICmsManager)this.getBean("cmsManager");
		CmsChannelBean channel = cmsManager.getCmsChannel(channelId);
		context.put("channel", channel);
		
	}
}
