package com.fd.b2c.manager.modules.screens;

import java.text.SimpleDateFormat;
import java.util.HashMap;

import org.apache.turbine.modules.screens.VelocityScreen;
import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.session.FdSession;
import com.fd.session.FdSessionFactory;
import com.fd.util.AppContextUtil;
import com.fd.util.DictUtil;
import com.fd.util.JSONUtil;
import com.fd.util.RewriteUtil;
import com.fd.util.StringUtil;
import com.fd.util.WebPropUtil;


public class BaseScreen extends VelocityScreen {

	private HashMap hmModuleList = new HashMap();

	public Object getBean(String name)
	{
		return AppContextUtil.getBean(name);
	}

	public void doBuildTemplate(RunData data, Context context) throws Exception
	{
		data.setCharSet("UTF-8");
		data.setTemplateEncoding("UTF-8");
		data.setContentType("text/html;charset=UTF-8");
		data.getRequest().setCharacterEncoding("UTF-8");
		data.getResponse().setCharacterEncoding("UTF-8");
		data.getResponse().setContentType("text/html;charset=UTF-8");
		data.getParameters().setCharacterEncoding("UTF-8");
		data.getParameters().setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
		context.put("DictUtil", new DictUtil());
		context.put("WebPropUtil", new WebPropUtil());
		context.put("StringUtil", new StringUtil());
		context.put("RewriteUtil", new RewriteUtil());
		context.put("JSONUtil", new JSONUtil());
	}

	public boolean hasModulePrimission(String moduleId, RunData data)
	{
		FdSession session = FdSessionFactory.getFdSession(data);
		hmModuleList = (HashMap) session.getAttribute("moduleList");

		if (hmModuleList == null || hmModuleList.get(moduleId) == null)
		{
			return false;
		}
		else
			return true;

	}

	public boolean hasUserPrimission(RunData data)
	{
		//		if(session.getAttribute("logUser")==null ||( (EbaseUser)session.getAttribute("logUser")).getId()==null ||"".equals(( (EbaseUser)session.getAttribute("logUser"))))
		//		{
		//			return false;
		//		}
		//		else
		return true;
	}
}
