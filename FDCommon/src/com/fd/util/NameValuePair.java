package com.fd.util;

/**
 * 键值对
 * 
 * @author longli
 *
 */
public class NameValuePair {
	/** 键 */
	private Object name;
	/** 值 */
	private Object value;
	
	public NameValuePair() {
	}
	
	public NameValuePair(Object name, Object value) {
		this.name = name;
		this.value = value;
	}

	/** 键 */
	public Object getName() {
		return name;
	}
	/** 键 */
	public void setName(Object name) {
		this.name = name;
	}
	/** 值 */
	public Object getValue() {
		return value;
	}
	/** 值 */
	public void setValue(Object value) {
		this.value = value;
	}
}
