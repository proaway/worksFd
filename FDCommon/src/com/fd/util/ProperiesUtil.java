package com.fd.util;

import java.util.List;

/**
 * @author Administrator
 * 
 */
public class ProperiesUtil {

	public static final String DEF_COMMON_PARA = "propertiesProxy";
	private static PropertiesBean propertyBean;
	
	
	/**
	 * 从默认的配置文件获取配置好的属性，配置文件路径：/filter.properties.xml
	 * 
	 * @param key
	 * @return
	 */
	public synchronized static String getProperty(String key) {
		if (key == null) {
			return null;
		}
		if (propertyBean == null) {
			propertyBean = (PropertiesBean) AppContextUtil.getBean(DEF_COMMON_PARA);
		}
		return propertyBean.getProperty(key);
	}
	
	/**
	 * 从默认的配置文件获取配置好的属性名称列表，配置文件路径：/filter.properties.xml
	 * 
	 * @param objClass 类
	 * @param filterType 过滤器类型：<br>
	 * 			security:	加密字段过滤器<br>
	 * 			word:		文字过滤器<br>
	 * 			brand:		品牌名称过滤器<br>
	 * 			danger:		危险字符过滤<br>
	 * @return
	 * @throws Exception
	 */
	public synchronized static List<String> getProperties(Class<?> objClass, String filterType)
			throws Exception {
		if (objClass == null) {
			return null;
		}
		if (propertyBean == null) {
			propertyBean = (PropertiesBean) AppContextUtil.getBean(DEF_COMMON_PARA);
		}
		List<String> list = propertyBean.getProperties(objClass.getName(), filterType);
		return list;
	}
	
	public static void main(String[] args) {
		
	}
}
