/**
 * AddTemplate.java
 * @author:  Zhou Rongyu
 */
package com.fd.b2c.manager.modules.actions.cms;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.fileupload.FileItem;
import org.apache.turbine.util.parser.ParameterParser;
import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.b2c.manager.modules.actions.SecureAction;
import com.fd.constants.SessionConstants;
import com.fd.fashion.cms.bean.CmsTemplateBean;
import com.fd.fashion.cms.manager.ICmsManager;
import com.fd.fashion.seller.bean.UsersBean;
import com.fd.session.FdSession;
import com.fd.session.FdSessionFactory;
import com.fd.util.ParametersUtil;
import com.fd.util.UploadFiles;
import com.fd.util.WebPropUtil;

/**
 * @CreateDate:  2013-5-20 上午11:02:03 
 * @author:  Zhou Rongyu
 * @Description:  TODO
 * 
 * @version:  V1.0
 */
public class AddTemplate extends SecureAction {
	@Override
	public void doPerform(RunData data, Context context) throws Exception {
		super.doPerform(data, context);
		FdSession session = FdSessionFactory.getFdSession(data);
		UsersBean user = (UsersBean) session.getAttribute(SessionConstants.KEY_LOGIN_USER);
		
		CmsTemplateBean cmsTemplateBean = new CmsTemplateBean();
		ParametersUtil.initParameters(data, cmsTemplateBean);
		
		cmsTemplateBean.setCreateTime(new Date());
		cmsTemplateBean.setCreateUser(user.getLoginName());
		cmsTemplateBean.setStatus(1);

		ICmsManager cmsManager = (ICmsManager)this.getBean("cmsManager");
		UploadFiles uploadFiles = new UploadFiles();
		ParameterParser params = data.getParameters();
		FileItem fileItem = params.getFileItem("fileurl");
		String name=fileItem.getName();

		WebPropUtil wpu = new WebPropUtil();
		String savePath = wpu.get("image.path.root")+"/cmsTemplate/";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String ymd = sdf.format(new Date());
		savePath += ymd + "/";
		//uploadpath=uploadpath+"/"+urlStr.substring(urlStr.lastIndexOf("\\")+1,urlStr.length());
		String furl= uploadFiles.UploadFileTemplate( fileItem, name, savePath); 

		cmsTemplateBean.setFileUrl(furl);

		if(null != cmsTemplateBean.getTemplateName() && furl != null){
			cmsManager.addCmsTemplate(cmsTemplateBean);
		}
		this.setTemplate(data, "cms,TemplateList.html");
		
	}

}
