package com.fd.statistics.modules.actions;

import org.apache.commons.lang.StringUtils;
import org.apache.turbine.modules.actions.VelocitySecureAction;
import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.constants.SessionConstants;
import com.fd.session.FdSession;
import com.fd.session.FdSessionFactory;
import com.fd.util.AppContextUtil;
import com.fd.util.DictUtil;
import com.fd.util.JSONUtil;
import com.fd.util.StringUtil;
import com.fd.util.WebPropUtil;

public class SecureAction extends VelocitySecureAction {
	public SecureAction()
	{
	
	}

	public Object getBean(String name)
	{
		return AppContextUtil.getBean(name);
	}

	public void doPerform(RunData data, Context context) throws Exception {
		data.setCharSet("UTF-8");
		data.setTemplateEncoding("UTF-8");
		data.setContentType("text/html;charset=UTF-8");
		data.getRequest().setCharacterEncoding("UTF-8");
		data.getResponse().setCharacterEncoding("UTF-8");
		data.getResponse().setContentType("text/html;charset=UTF-8");
		data.getParameters().setCharacterEncoding("UTF-8");
		context.put("DictUtil", new DictUtil());
		context.put("StringUtil", new StringUtil());
		context.put("WebPropUtil", new WebPropUtil());
		context.put("JSONUtil", new JSONUtil());
	}

	/**
	 * @param 	data
	 * @return	boolean		是否能继续访问请求Action
	 * @throws Exception
	 */
	protected boolean isAuthorized(RunData data) throws Exception {
		
		return true;
	}
}
