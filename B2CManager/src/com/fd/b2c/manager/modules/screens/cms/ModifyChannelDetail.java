/**
 * EditTemplate.java
 * @author:  Zhou Rongyu
 */
package com.fd.b2c.manager.modules.screens.cms;

import java.io.File;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.b2c.manager.modules.screens.SecureScreen;
import com.fd.fashion.cms.bean.CmsChannelBean;
import com.fd.fashion.cms.bean.CmsTemplateBean;
import com.fd.fashion.cms.manager.ICmsManager;
import com.fd.fashion.cms.util.CmsTagsConfig;
import com.fd.fashion.cms.util.CmsUtil;
import com.fd.util.FileUtil;
import com.fd.util.RegexUtil;
import com.fd.util.WebPropUtil;

/**
 * @CreateDate:  2013-5-20 下午4:10:46 
 * @author:  Zhou Rongyu
 * @Description:  TODO
 * 
 * @version:  V1.0
 */
public class ModifyChannelDetail extends SecureScreen {
	@Override
	public void doBuildTemplate(RunData data, Context context) throws Exception {
		super.doBuildTemplate(data, context);
		data.setLayoutTemplate("/NoLayout.html");
		
		String channelId = data.getParameters().getString("channelId");
		ICmsManager cmsManager = (ICmsManager) getBean("cmsManager");
		CmsChannelBean channel = cmsManager.getCmsChannel(channelId);
		CmsTemplateBean template = cmsManager.getCmsTemplate(channel.getTemplateId());
		String channelUrl = channel.getChannelUrl();
		if (!channelUrl.startsWith("/")) {
			channelUrl = "/" + channelUrl;
			channel.setChannelUrl(channelUrl);
		}
		String channelPath = CmsUtil.getChannelPath(channelUrl);
		
		File channelFile = new File(channelPath);
		String channelContent = null;
		if (!channelFile.exists()) {
			String channelViewUrl = CmsUtil.getChannelViewUrl(channelUrl);
			channelPath = CmsUtil.getChannelPath(channelViewUrl);
			channelFile = new File(channelPath);
		}
		if (!channelFile.exists()) {
			channelFile = new File(template.getFileUrl());
			channelContent = FileUtil.readFile(channelFile);
			channelContent = RegexUtil.replaceAll(channelContent, CmsTagsConfig.BLICK_BEGIN, "<div block>");
			channelContent = RegexUtil.replaceAll(channelContent, CmsTagsConfig.BLICK_END, "</div>");
//			channelContent = RegexUtil.replaceAll(channelContent, "<html[^<>]*?>", "");
//			channelContent = RegexUtil.replaceAll(channelContent, "<title.*?></title>", "");
//			channelContent = RegexUtil.replaceAll(channelContent, "<meta[^<>]*?>", "");
//			channelContent = RegexUtil.replaceAll(channelContent, "<head[^<>]*?>", "");
//			channelContent = RegexUtil.replaceAll(channelContent, "</head>", "");
//			channelContent = RegexUtil.replaceAll(channelContent, "<body[^<>]*?>", "");
//			channelContent = RegexUtil.replaceAll(channelContent, "</body>", "");
			channelContent = channelContent.replaceAll(".*jquery.*\\.js.*", "");
		} else {
			channelContent = FileUtil.readFile(channelFile);
			channelContent = channelContent.replaceAll(".*jquery.*\\.js.*", "");
		}
		WebPropUtil prop = new WebPropUtil();
		String webroot = prop.getProperty("website.httproot");
		context.put("webroot", webroot);
		context.put("channel", channel);
		context.put("template", template);
		context.put("channelContent", channelContent);
	}
}
