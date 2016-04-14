package com.fd.util;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;

public class SpiderApi {
	static HttpClient client = new HttpClient();
	
	public static JSONObject readDate(String act, Map<String, Object> params) throws Exception {
		String url = "http://spider.fulldol.com/api/GetSpiderData?act=" + act;
		for (String key : params.keySet()) {
			url += "&" + key + "=" + params.get(key);
		}
		GetMethod method = new GetMethod(url);
		client.executeMethod(method);
		String res = method.getResponseBodyAsString();
		
		JSONObject obj = JSONObject.fromObject(res);
		return obj;
	}
	
	public static JSONObject readVerify(String verifyUrl, String verifyCode) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("verifyUrl", URLEncoder.encode(verifyUrl, "utf-8"));
		params.put("verifyCode", verifyCode);
		return readDate("GetVerify", params);
	}
	
	
}
