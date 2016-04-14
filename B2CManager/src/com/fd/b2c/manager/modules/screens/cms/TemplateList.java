package com.fd.b2c.manager.modules.screens.cms;

import java.util.List;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.b2c.manager.modules.screens.SecureScreen;
import com.fd.constants.SessionConstants;
import com.fd.fashion.cms.bean.CmsTemplateBean;
import com.fd.fashion.cms.manager.ICmsManager;
import com.fd.fashion.cms.util.CmsUtil;
import com.fd.fashion.seller.bean.UsersBean;
import com.fd.session.FdSession;
import com.fd.session.FdSessionFactory;
import com.fd.util.PageInfo;
import com.fd.util.ParametersUtil;

/**
 * @CreateDate: 2013-5-16 下午09:10:02
 * @author Longli
 * @Description: 模版管理
 * 
 */
public class TemplateList extends SecureScreen {
	@Override
	public void doBuildTemplate(RunData data, Context context) throws Exception {
		super.doBuildTemplate(data, context);
		data.setLayoutTemplate("/Layout.html");
		ICmsManager cmsManager = (ICmsManager)this.getBean("cmsManager");
		CmsTemplateBean cmsTemplateBean = new CmsTemplateBean();
		ParametersUtil.initParameters(data, cmsTemplateBean);
		
		//**********设置PageInfo分页信息
		int pageSize = 4;
		int pageIndex = data.getParameters().getInt("pageIndex", 1);
		PageInfo pageInfo = new PageInfo();
		pageInfo.setPageIndex(pageIndex);
		pageInfo.setPageSize(pageSize);
				
		List<CmsTemplateBean> cmsTemplateList = cmsManager.getCmsTemplate(cmsTemplateBean, pageInfo);
		for(int i=0;i<cmsTemplateList.size();i++){
			CmsTemplateBean ctb = cmsTemplateList.get(i);
			String[] tmp = ctb.getFileUrl().split("/");
			String name = tmp[tmp.length-1];
			//ctb.setFileUrl(name);
			ctb.setOperateUser(name);
		}
		context.put("cmsTemplateList", cmsTemplateList);
		context.put("pageInfo", pageInfo);
		context.put("createTimeStart",cmsTemplateBean.getCreateTimeStart());
		context.put("createTimeEnd", cmsTemplateBean.getCreateTimeEnd());
		context.put("templateId", cmsTemplateBean.getTemplateId());
		context.put("templateName", cmsTemplateBean.getTemplateName());
		context.put("templateType", cmsTemplateBean.getTemplateType());
		context.put("createUser", cmsTemplateBean.getCreateUser());
		context.put("languageId", cmsTemplateBean.getLanguageId());
		context.put("CmsUtil",new CmsUtil());
	}
}
