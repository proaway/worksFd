package com.fd.util;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.Properties;

import com.fd.util.MD5Util;

public class WebPropUtil {
	static Properties properties = new Properties();
	static String fileMd5 = "";
	
	public WebPropUtil() {
		try {
			URL propUrl = WebPropUtil.class.getClassLoader().getResource(
					"velocity.properties");
			File propFile = new File(propUrl.getPath());
			String md5 = MD5Util.calcFileMD5(propFile);
			if (!md5.equals(fileMd5)) {
				properties.load(new FileInputStream(propFile));
				fileMd5 = md5;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getProperty(String key) {
		return properties.getProperty(key);
	}
	
	public String get(String key) {
		return properties.getProperty(key);
	}
}
