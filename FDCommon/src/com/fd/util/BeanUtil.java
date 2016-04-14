/**
 * 
 */
package com.fd.util;

import java.beans.PropertyDescriptor;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.ConvertUtilsBean;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.beanutils.PropertyUtilsBean;
import org.apache.log4j.Logger;

/**
 * @author 何小锋
 * 
 */
@SuppressWarnings("rawtypes")
public class BeanUtil {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(BeanUtil.class);

	private static BeanUtilsBean utilsBean;

	static {
		ConvertUtilsBean convertUtils = new ConvertUtilsBean();
		convertUtils.register(new DateConverter(), Date.class);
		utilsBean = new BeanUtilsBean(convertUtils, new PropertyUtilsBean());
	}

	/**
	 * 拷贝属性，排除Null值
	 * 
	 * @param target
	 * @param source
	 */
	public synchronized static void copyProperties(Object target, Object source) {

		if (target == null || source == null)
			return;

		// 获取属性描述
		PropertyDescriptor[] properties = PropertyUtils
				.getPropertyDescriptors(source.getClass());
		// 遍历属性
		for (PropertyDescriptor descriptor : properties) {
			String propName = descriptor.getName();
			try {
				// 如果不为空，则复制
				Object obj = PropertyUtils.getProperty(source, propName);
				if (obj != null && descriptor.getWriteMethod() != null)
					utilsBean.setProperty(target, propName, obj);

			} catch (Exception e) {
				logger.error(e);
				logger.error("property name=" + propName);
				e.printStackTrace();
			}
		}
	}
	

	/**
	 * 拷贝属性，排除Null值，合并mergeList中的属性值(String类型属性)
	 * 
	 * @param target
	 * @param source
	 * @param mergePropList
	 */
	public synchronized static void copyProperties(Object target, Object source, List<String> mergePropList) {

		if (target == null || source == null)
			return;
		
		if (mergePropList == null) {
			copyProperties(target, source);
			return;
		}

		// 获取属性描述
		PropertyDescriptor[] properties = PropertyUtils
				.getPropertyDescriptors(source.getClass());
		// 遍历属性
		for (PropertyDescriptor descriptor : properties) {
			String propName = descriptor.getName();
			try {
				// 如果不为空，则复制
				Object objSrc = PropertyUtils.getProperty(source, propName);
				if (objSrc != null && descriptor.getWriteMethod() != null) {
					Object objTar = PropertyUtils.getProperty(target, propName);
					if (objTar != null && (objTar instanceof String) 
							&& mergePropList.contains(propName)) {
						// 合并属性
						utilsBean.setProperty(target, propName, (objTar + " <br> " + objSrc));
					} else 
						utilsBean.setProperty(target, propName, objSrc);
				} 

			} catch (Exception e) {
				logger.error(e);
				logger.error("property name=" + propName);
				e.printStackTrace();
			}
		}
	}

	/**
	 * @param target
	 * @param property
	 */
	public synchronized static String getStringProperty(Object target,
			String property) throws Exception {
		if (target == null)
			return null;
		return BeanUtils.getProperty(target, property);
	}

	/**
	 * @param target
	 * @param property
	 */
	public synchronized static Object getProperty(Object target, String property)
			throws Exception {
		if (target == null)
			return null;
		return PropertyUtils.getProperty(target, property);
	}

	/**
	 * 设置属性
	 * 
	 * @param obj
	 * @param propertyName
	 * @param value
	 * @throws Exception
	 */
	public synchronized static void setProperty(Object obj,
			String propertyName, Object value) throws Exception {
		if (obj == null)
			return;
		if (propertyName == null || propertyName.trim().equals(""))
			return;
		if (obj instanceof HashMap) {
			HashMap map = (HashMap) obj;
			setProperty(map, propertyName, value);
			return;
		}

		String pros[] = StringUtil.split(propertyName, ".");
		Object parentObj = obj;
		// 多级对象属性设置
		if (pros.length > 1) {
			for (int k = 0; k < pros.length - 1; k++) {
				Object subObj = PropertyUtils.getProperty(parentObj, pros[k]);
				if (subObj == null) {
					PropertyDescriptor pd = PropertyUtils
							.getPropertyDescriptor(parentObj, pros[k]);
					subObj = pd.getPropertyType().newInstance();
					BeanUtils.setProperty(parentObj, pros[k], subObj);
				}
				parentObj = subObj;
			}
		}
		utilsBean.setProperty(obj, propertyName, value);
	}

	@SuppressWarnings("unchecked")
	private synchronized static void setProperty(HashMap map,
			String propertyName, Object value) throws Exception {
		if (propertyName == null || propertyName.trim().equals(""))
			return;
		String pros[] = StringUtil.split(propertyName, ".");
		// 多级对象属性设置
		HashMap parentMap = map;
		if (pros.length > 1) {
			for (int k = 0; k < pros.length; k++) {
				if (k == pros.length - 1) {
					parentMap.put(pros[k], value);
					continue;
				}
				Object subMap = map.get(pros[k]);
				if (subMap == null) {
					subMap = new HashMap();
					parentMap.put(pros[k], subMap);
				}
				parentMap = (HashMap)subMap;
			}
		} else {
			map.put(propertyName, value);
		}
	}

}
