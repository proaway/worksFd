/**
 * 
 */
package com.fd.util;

import org.apache.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import org.apache.commons.beanutils.Converter;

/**
 * @author 何小锋
 * 
 */
@SuppressWarnings("rawtypes")
public class DateConverter implements Converter {

	/** 默认短日期格式 */
	public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";

	/** 默认长日期格式 */
	public static final String DEFAULT_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(DateConverter.class);

	private static final SimpleDateFormat longFormat = new SimpleDateFormat(
			DEFAULT_DATETIME_FORMAT);

	private static final SimpleDateFormat defaultFormat = new SimpleDateFormat(
			DEFAULT_DATE_FORMAT);

	private static final SimpleDateFormat defaultCnFormat = new SimpleDateFormat(
			"yyyy年MM月dd日");

	private static final SimpleDateFormat shortFormat = new SimpleDateFormat(
			"yyyy-M-d");

	private static final SimpleDateFormat shortCnFormat = new SimpleDateFormat(
			"yyyy年M月d日");

	private static final SimpleDateFormat yearFormat = new SimpleDateFormat(
			"yyyy");

	private static final SimpleDateFormat usFormat = new SimpleDateFormat(
			"EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.commons.beanutils.Converter#convert(java.lang.Class,
	 *      java.lang.Object)
	 */
	public Object convert(Class arg0, Object arg1) {
		if (arg1 != null && arg1 instanceof String) {
			String value = ((String) arg1).trim();
			if (value.equals(""))
				return null;
			try {
				try {
					return defaultFormat.parse(value);
				} catch (ParseException e) {
					;
				}
				try {
					return longFormat.parse(value);
				} catch (ParseException e) {
					;
				}
				try {
					return shortFormat.parse(value);
				} catch (ParseException e) {
					;
				}
				try {
					return defaultCnFormat.parse(value);
				} catch (ParseException e) {
					;
				}
				try {
					return shortCnFormat.parse(value);
				} catch (ParseException e) {
					;
				}
				try {
					return usFormat.parse(value);
				} catch (ParseException e) {
					;
				}
				try {
					return yearFormat.parse(value);
				} catch (ParseException e) {
					;
				}
			} catch (Exception e) {
				logger.error(e);
				return null;
			}
		}
		return arg1;
	}
}
