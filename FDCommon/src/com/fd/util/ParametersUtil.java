package com.fd.util;

import java.beans.PropertyDescriptor;
import java.net.URLDecoder;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.turbine.util.RunData;

public class ParametersUtil {
	public static void initParameters(RunData data, Object obj) {
		Object keys[] = data.getParameters().getKeys();
		for (Object key : keys) {
			String k = (String) key;
			try {
				if (k.matches("^[0-9\\.]+$")) {
					continue;
				}
				PropertyDescriptor pd = PropertyUtils.getPropertyDescriptor(obj, k);
				if (pd == null) {
					continue;
				}
				String value = data.getParameters().getString(k);
				if (StringUtils.isEmpty(value)) {
					continue;
				}
				if ("sortField".equals(k)) {
					// 排序字段，从字典类读取实际字段值
					BeanUtil.setProperty(obj, k, DictUtil.getSortField(value));
				} else {
					Object v = data.getParameters().getObject(k);
					if (v instanceof java.lang.String) {
						String vl = (String)v;
						BeanUtil.setProperty(obj, k, URLDecoder.decode(vl, "utf-8"));
					} else {
						BeanUtil.setProperty(obj, k, v);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static boolean isEmpty(Object obj) {
		if (obj == null) {
			return true;
		}
		PropertyDescriptor[] properties = PropertyUtils
				.getPropertyDescriptors(obj.getClass());
		boolean empty = true;
		for (PropertyDescriptor descriptor : properties) {
			String propName = descriptor.getName();
			try {
				// 如果不为空，则复制
				Object o = PropertyUtils.getProperty(obj, propName);
				empty = empty && (o==null || "".equals(o));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return empty;
	}
}
