package com.fd.util.report;

import java.lang.reflect.Method;

public class GetProperty {

	/**
	 * 
	 * @param propertyName 方法名<br>
	 * @param cls 类Class<br>
	 * @param obj 对象<br>
	 * @return 返回对象obj.propertyName的执行结果<br>
	 */
	public Object getProperty(String propertyName,Class<?> cls,Object obj){
		try{
			if(propertyName!=null &&!"".equals(propertyName)){
				Method method = cls.getMethod(propertyName);
				return method.invoke(obj);
			}
			return null;
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
}
