/**
 * 
 */
package com.fd.util;

import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import com.twmacinta.util.MD5;

/**
 * @author 何小锋
 * 
 */
public class MD5Util {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(MD5Util.class);

	private static ThreadLocal<MD5> md5Local = new ThreadLocal<MD5>(){
		@Override
		protected MD5 initialValue() {
			return new MD5();
		}
	};

	/**
	 * 计算字符串的MD5
	 * 
	 * @param str
	 * @return
	 */
	public static String calcMD5(String str) {
		MD5 md5 = md5Local.get();
		md5.Init();
		md5.Update(str);
		return md5.asHex();
	}

	/**
	 * 计算字符串的MD5
	 * 
	 * @param str
	 * @param chaSet
	 *            编码
	 * @return
	 */
	public static String calcMD5(String str, String chaSet) {
		try {
			MD5 md5 = md5Local.get();
			md5.Init();
			md5.Update(str, chaSet);
			return md5.asHex();
		} catch (UnsupportedEncodingException e) {
			logger.error(e);
		}
		return null;
	}

	/**
	 * 获取文件的MD5
	 * 
	 * @param str
	 * @return
	 */
	public static String calcFileMD5(File file) {
		if (file == null)
			return null;
		try {
			MD5 md5 = md5Local.get();
			md5.Init();
			md5.Update(MD5.getHash(file));
			return md5.asHex();
		} catch (IOException e) {
			logger.error(e);
		}
		return null;
	}

}
