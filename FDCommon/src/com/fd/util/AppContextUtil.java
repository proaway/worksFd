package com.fd.util;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppContextUtil {
	// 容器
	private static ClassPathXmlApplicationContext appContext;
	
	// XML文件
	private static String[] beans_xml = new String[] { "/applicationContext.xml" };

	/**
	 * @return
	 */
	public static synchronized ClassPathXmlApplicationContext getContext() {
		if (appContext == null) {
			try {
				appContext = new ClassPathXmlApplicationContext(beans_xml);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return appContext;
	}
	
	/**
	 * 从Spring配置获取bean
	 * 
	 * @param name
	 * @return
	 */
	public static Object getBean(String name) {
		return getContext().getBean(name);
	}
}
