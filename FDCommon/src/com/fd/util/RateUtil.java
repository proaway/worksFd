/**
 * RateUtil.java
 * @author:  Zhou Rongyu
 */
package com.fd.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;

/**
 * @CreateDate:  2013-5-15 下午12:38:25 
 * @author:  Zhou Rongyu
 * @Description:  TODO
 * 
 * @version:  V1.0
 */
public class RateUtil {
	/**
	 * 获取当日汇率
	 * @author:  Zhou Rongyu
	 * @return
	 * @throws Exception
	 */
	public static String getRate() throws Exception {
		String rate ="6.35";
		try {
			HttpClient client = new HttpClient();
			Date d = new Date();
			SimpleDateFormat  sdf = new SimpleDateFormat ("yyyy-MM-dd");
			String dateStr = sdf.format(d);
			//String url = "http://www.bankofchina.com/sourcedb/lswhpj/1312/list.htm";
			String url = "http://srh.bankofchina.com/search/whpj/search.jsp?erectDate="+dateStr+"&nothing="+dateStr+"&pjname=1316";
			GetMethod method = new GetMethod(url);
			client.executeMethod(method);
		//	String rs = new String(method.getResponseBody(),"utf-8");
			String rs = method.getResponseBodyAsString();
			RegexUtil reg = new RegexUtil();
		//	rate = reg.getMatchedStr(rs, "美元</td>\\s*<td [^<>]*>([\\d\\.]+)</td>", 1);
			List<String> rss = reg.getAllMatched(rs, "美元</td>\\s*<td [^<>]*>([\\d\\.]+)</td>",1);
			rate = rss.get(rss.size()-1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rate;

	}
	
	public static void main(String[] args) throws Exception{
		//当前汇率
		String rate = getRate();
		rate  = CullNumUtil.cullNumTwo(Double.valueOf(rate)/100);
		System.out.println(rate);
	}
}
