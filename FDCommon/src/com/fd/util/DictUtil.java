package com.fd.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @CreateDate: 2013-3-14 下午05:55:36
 * @author Longli
 * @Description: 字典工具类，包装了部分ID号和文字描述的转换，及字典数据返回，这部分字典数据在数据库中一般是由注释说明，不同编号的不同含义。
 * 
 */
public class DictUtil {
	private static Properties sortFields;
	
	/**
	 * 排序字段值，一般在后台查询的排序参数中用到，用编号代替实际排序字段名
	 * 
	 * @return
	 * @throws Exception
	 */
	private static Properties getSortFieldsProps() throws Exception {
		if (sortFields == null) {
			sortFields = new Properties();
			sortFields.load(DictUtil.class.getClassLoader().getResourceAsStream("sortFields.properties"));
		}
		return sortFields;
	}
	
	/**
	 * 获取排序字段值，一般在后台查询的排序参数中用到，用编号代替实际排序字段名
	 * 
	 * @param key
	 * @return
	 */
	public static String getSortField(String key) {
		try {
			return getSortFieldsProps().getProperty(key);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 返回Supplier类型
	 * 
	 * @param supplierType
	 * @return
	 */
	public static String getSupplierType(int supplierType) {
		switch (supplierType) {
		case 1:
			return "个人";
		case 2:
			return "工厂";
		case 3:
			return "贸易公司";
		}
		return null;
	}
	
	/**
	 * 返回所有supplier类型
	 * 
	 * @return
	 */
	public static List<NameValuePair> getSupplierTypes() {
		List<NameValuePair> list = new ArrayList<NameValuePair>();
		list.add(new NameValuePair(1, "个人"));
		list.add(new NameValuePair(2, "工厂"));
		list.add(new NameValuePair(3, "贸易公司"));
		return list;
	}
	
	/**
	 * 返回supplier级别
	 * 
	 * @param supplierLevel
	 * @return
	 */
	public static String getSupplierLevel(int supplierLevel) {
		switch (supplierLevel) {
		case 1:
			return "普通";
		case 2:
			return "高级";
		}
		return null;
	}
	
	/**
	 * 返回所有supplier级别
	 * @return
	 */
	public static List<NameValuePair> getSupplierLevels() {
		List<NameValuePair> list = new ArrayList<NameValuePair>();
		list.add(new NameValuePair(1, "普通"));
		list.add(new NameValuePair(2, "高级"));
		return list;
	}
	
	/**
	 * 返回所有可用状态
	 * 
	 * @return
	 */
	public static List<NameValuePair> getVaildStatus() {
		List<NameValuePair> list = new ArrayList<NameValuePair>();
		list.add(new NameValuePair(1, "启用"));
		list.add(new NameValuePair(0, "禁用"));
		return list;
	}
	
	/**
	 * 返回可用状态
	 * 
	 * @param status
	 * @return
	 */
	public static String getVaildStatus(int status) {
		switch (status) {
		case 1:
			return "启用";
		case 0:
			return "禁用";
		}
		return null;
	}
	
	/**
	 * 获取运费方式的中文简短描述
	 * 
	 * @param type
	 * @return
	 */
	public static String getShippingDesc(String type) {
		if ("off".equals(type)) {
			return "折";
		}
		if ("freeshipping".equals(type)) {
			return "免";
		}
		if ("custom".equals(type)) {
			return "自";
		}
		return type;
	}
	
	/**
	 * 用缩写获取各种航空小包的全名称
	 * 
	 * @param shortName
	 * @return
	 */
	public static String getShippingCompany(String shortName) {
		if ("CPAM".equals(shortName)) {
			return "China Post Air Mail";
		}
		if ("CPAP".equals(shortName)) {
			return "China Post Air Parcel";
		}
		if ("HKPAM".equals(shortName)) {
			return "Hongkong Post Air Mail";
		}
		if ("HKPAP".equals(shortName)) {
			return "Hongkong Post Air Parcel";
		}
		return shortName;
	}
	
	/**
	 * 产品状态描述
	 * 
	 * @param productStatus
	 * @return
	 */
	public static String getProductStatus(int productStatus) {
		if (productStatus == -1) {
			return "删除，详细页404";
		}
		if (productStatus == 0) {
			return "下架，详细页“out of stock”";
		}
		if (productStatus == 1) {
			return "在销";
		}
		if (productStatus == 2) {
			return "草稿，详细页404";
		}
		if (productStatus == 3) {
			return "批量导入，详细页404";
		}
		return "";
	}
	
	/**
	 * 产品状态描述
	 * 
	 * @param productStatus
	 * @return
	 */
	public static String getProductStatusBtn(int productStatus) {
		if (productStatus == -1) {
			return "垃圾箱";
		}
		if (productStatus == 0) {
			return "下架产品";
		}
		if (productStatus == 1) {
			return "在销产品";
		}
		if (productStatus == 2) {
			return "草稿箱";
		}
		if (productStatus == 3) {
			return "批量导入产品";
		}
		return "";
	}
	
	/**
	 * 获取用户权限级别
	 * 
	 * @return
	 */
	public static List<NameValuePair> getUserPriorities() {
		List<NameValuePair> list = new ArrayList<NameValuePair>();
		list.add(new NameValuePair(0, "私有"));
		list.add(new NameValuePair(1, "所有"));
		return list;
	}
	
	/**
	 * 获取用户权限级别
	 * 
	 * @param priority
	 * @return
	 */
	public static String getUserPriority(int priority) {
		switch (priority) {
		case 0:
			return "私有";
		case 1:
			return "所有";
		}
		return "";
	}
	
	/**
	 * 频道发布类型
	 * 
	 * @param updateType
	 * @return
	 */
	public static String getChannelUpdateType(int updateType) {
		switch(updateType) {
		case 1:
			return "手动";
		case 2:
			return "自动";
		}
		return "";
	}
	
	/**
	 * 频道状态
	 * 
	 * @param updateType
	 * @return
	 */
	public static String getChannelStatus(int status) {
		switch(status) {
		case 1:
			return "启用";
		case 2:
			return "禁用";
		case 3:
			return "删除";
		}
		return "";
	}
	
	/**
	 * 频道模版类型 1：首页，2：频道，3：活动，4：文章，5：邮件
	 * 
	 * @param updateType
	 * @return
	 */
	public static String getTemplateType(int templateType) {
		switch(templateType) {
		case 1:
			return "首页";
		case 2:
			return "频道";
		case 3:
			return "活动";
		case 4:
			return "文章";
		case 5:
			return "邮件";
		}
		return "";
	}
}
