/**
 * ChangeTemplateType.java
 * @author:  Zhou Rongyu
 */
package com.fd.b2c.manager.modules.screens.cms;

import java.util.List;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.b2c.manager.modules.screens.SecureScreen;
import com.fd.fashion.cms.bean.CmsTemplateBean;
import com.fd.fashion.cms.manager.ICmsManager;

/**
 * @CreateDate:  2013-5-20 下午9:14:12 
 * @author:  Zhou Rongyu
 * @Description:  TODO
 * 
 * @version:  V1.0
 */
public class AjaxGetTemplateByType extends SecureScreen {
	@Override
	public void doBuildTemplate(RunData data, Context context) throws Exception {
		super.doBuildTemplate(data, context);
		data.setLayoutTemplate("/NoLayout.html");
		int type = data.getParameters().getInt("type");
		ICmsManager cmsManager = (ICmsManager)this.getBean("cmsManager");
		CmsTemplateBean template = new CmsTemplateBean();
		template.setTemplateType(type);
		List<CmsTemplateBean> cmsTemplatelist = cmsManager.getCmsTemplate(template, null);
		context.put("cmsTemplatelist", cmsTemplatelist);
		
	}
}
