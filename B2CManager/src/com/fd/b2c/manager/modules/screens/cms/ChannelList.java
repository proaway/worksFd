package com.fd.b2c.manager.modules.screens.cms;

import java.util.List;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.b2c.manager.modules.screens.SecureScreen;
import com.fd.fashion.cms.bean.CmsChannelBean;
import com.fd.fashion.cms.manager.ICmsManager;
import com.fd.util.PageInfo;
import com.fd.util.ParametersUtil;

/**
 * @CreateDate: 2013-5-16 下午09:10:02
 * @author Longli
 * @Description: 模版管理
 * 
 */
public class ChannelList extends SecureScreen {
	@Override
	public void doBuildTemplate(RunData data, Context context) throws Exception {
		super.doBuildTemplate(data, context);
		data.setLayoutTemplate("/Layout.html");
		ICmsManager cmsManager = (ICmsManager)this.getBean("cmsManager");
		CmsChannelBean cmsChannelBean = new CmsChannelBean();
		ParametersUtil.initParameters(data, cmsChannelBean);
		
		//**********设置PageInfo分页信息
		int pageSize = 20;
		int pageIndex = data.getParameters().getInt("pageIndex", 1);
		PageInfo pageInfo = new PageInfo();
		pageInfo.setPageIndex(pageIndex);
		pageInfo.setPageSize(pageSize);
		
		List<CmsChannelBean> cmsChannelList = cmsManager.getCmsChannels(cmsChannelBean, pageInfo);
		context.put("cmsChannelList", cmsChannelList);
		context.put("pageInfo", pageInfo);
		context.put("channelId", cmsChannelBean.getChannelId());
		context.put("channelName", cmsChannelBean.getChannelName());
		context.put("channelUrl", cmsChannelBean.getChannelUrl());
		context.put("createDateStart", cmsChannelBean.getCreateDateStart());
		context.put("createDateEnd", cmsChannelBean.getCreateDateEnd());
		context.put("pubDateStart", cmsChannelBean.getPubDateStart());
		context.put("pubDateEnd", cmsChannelBean.getPubDateEnd());
		context.put("createUser", cmsChannelBean.getCreateUser());
		context.put("templateName", cmsChannelBean.getTemplateName());
		context.put("templateType", cmsChannelBean.getTemplateType());
		
		
	}
}
