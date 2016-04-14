package com.fd.util;

import java.beans.PropertyDescriptor;
import java.security.Security;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;


/**
 * 字符串 DESede(3DES) 加密
 * 
 * @author 龙力
 * 
 */
public class SecurityUtil {
	
	static {
		// 添加新安全算法,如果用JCE就要把它添加进去
		Security.addProvider(new com.sun.crypto.provider.SunJCE());
	}

	/**
	 * 加密算法
	 * 
	 * @param src 需要加密的数据
	 * @return 返回加密后的字符串
	 * @throws Exception
	 */
	public static String encryptMode(String src)
			throws Exception {
		if (src == null) {
			return null;
		}
		return DESadeUtil.encryptMode(src);
	}


	/**
	 * 解密算法
	 * 
	 * @param src 需要解密的数据
	 * @return 返回解密后的字符串
	 * @throws Exception
	 */
	public static String decryptMode(String src)
			throws Exception {
		if (src == null) {
			return null;
		}
		return DESadeUtil.decryptMode(src);
	}
	

	/**
	 * 对对象属性进行加密处理
	 * 
	 * @param source
	 * @throws Exception
	 */
	public static void encryptObject(Object source) throws Exception {
		if (source == null) {
			return;
		}
		
		//获取需要加密的字段属性名
		List<String> propList = ProperiesUtil.getProperties(source.getClass(), "security");
		// 获取属性描述
		PropertyDescriptor[] properties = PropertyUtils
				.getPropertyDescriptors(source.getClass());
		// 遍历属性
		for (PropertyDescriptor descriptor : properties) {
			String propName = descriptor.getName();
			if (propList.contains(propName)) {
				// 取得字段值，若字段不为空，加密字段
				String propContent = (String)PropertyUtils.getProperty(source, propName);
				if (propContent != null) {
					propContent = encryptMode(propContent);
					PropertyUtils.setProperty(source, propName, propContent);
				}
			}
		}
	}

	/**
	 * 对对象属性进行解密处理
	 * 
	 * @param source
	 * @throws Exception
	 */
	public static void decryptObject(Object source) throws Exception {
		if (source == null) {
			return;
		}
		
		//获取需要解密的字段属性名
		List<String> propList = ProperiesUtil.getProperties(source.getClass(), "security");
		// 获取属性描述
		PropertyDescriptor[] properties = PropertyUtils
				.getPropertyDescriptors(source.getClass());
		// 遍历属性
		for (PropertyDescriptor descriptor : properties) {
			String propName = descriptor.getName();
			if (propList.contains(propName)) {
				// 取得字段值，若字段不为空，解密字段
				String propContent = (String)PropertyUtils.getProperty(source, propName);
				if (propContent != null) {
					propContent = decryptMode(propContent);
					PropertyUtils.setProperty(source, propName, propContent);
				}
			}
		}
	}
	
	public static void main(String[] args){
		try {
			System.out.println(decryptMode("bc43dc6f471fa16a"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
