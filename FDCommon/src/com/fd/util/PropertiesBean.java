package com.fd.util;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class PropertiesBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4428890041605410026L;
	
	private HashMap<String, HashMap<String, List<String>>> filters;
	
	private Properties properties = new Properties();

	/**
	 * @return the properties
	 */
	public Properties getProperties() {
		return properties;
	}

	/**
	 * @param properties the properties to set
	 */
	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	/**
	 * @return the filters
	 */
	public HashMap<String, HashMap<String, List<String>>> getFilters() {
		return filters;
	}

	/**
	 * @param filters the filters to set
	 */
	public void setFilters(HashMap<String, HashMap<String, List<String>>> filters) {
		this.filters = filters;
	}
	
	/**
	 * 获取properties属性中值
	 * 
	 * @param key
	 * @return
	 */
	public String getProperty(String key){
		return properties.getProperty(key);
	}
	
	public List<String> getProperties(String name, String type) throws Exception {
		if (name == null || type == null) {
			return null;
		}
		HashMap<String, List<String>> filter = filters.get(type);
		if (filter == null) {
			return null;
		}
		List<String> properties = filter.get(name);
		return properties;
	}
}
