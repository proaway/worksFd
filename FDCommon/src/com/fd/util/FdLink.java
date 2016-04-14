package com.fd.util;

import org.apache.commons.lang.StringUtils;
import org.apache.turbine.services.pull.tools.TemplateLink;

public class FdLink extends TemplateLink {
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String url = super.toString();
		if (StringUtils.isNotEmpty(url)) {
			url = url.replaceAll(";jsessionid=[a-zA-Z0-9]+", "");
		}
		return url;
	}
}
