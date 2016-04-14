package com.fd.util;

import java.net.InetAddress;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 * 获得IP的地址
 * @author a3user
 *
 */
public class IPAddrUtil {
	private static final Logger logger = Logger.getLogger(IPAddrUtil.class);
	public static String getIpAddr(HttpServletRequest request){
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		
		//如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串Ｉｐ值
		//这时取X-Forwarded-For中第一个非unknown的有效IP字符串
		if(ip.indexOf(",")>-1){
			String[] str = ip.split(",");	//有0，1两种情况
			for(String clientIp:str){
				if(clientIp!=null&&!"unknown".equalsIgnoreCase(clientIp)){
					ip = clientIp;
					break;
				}
			}
		}
		
		return ip;
	}
	
	/**
	 * IP地址转换为十进制数
	 * 
	 * @param ipAddress
	 * @return
	 * @throws Exception
	 */
	public static long ip2Long(String ipAddress) throws Exception {
		if (StringUtils.isEmpty(ipAddress)) {
			return 0;
		}
		ipAddress = ipAddress.trim();
		if (!ipAddress.matches("^\\d\\d?\\d?(\\.\\d\\d?\\d?){3}$")) {
			throw new Exception("非法的IP地址:" + ipAddress);
		}
		String ipStr[] = ipAddress.split("\\.");
		long ip[] = new long[4];
        for(int i=0; i<ipStr.length; i++) {
        	String s = ipStr[i];
        	long j = Long.parseLong(s);
        	if (j > 255) {
    			throw new Exception("非法的IP地址:" + ipAddress);
        	}
        	ip[i] = j;
        }
		long ipLong = (ip[0] << 24) + (ip[1] << 16) + (ip[2] << 8) + ip[3];
		return ipLong;
	}
	
	/**
	 * 十进制数转换为IP地址字符串
	 * 
	 * @param ipLong
	 * @return
	 */
	public static String long2Ip(long ipLong) {
		StringBuffer sb = new StringBuffer("");
        //直接右移24位
        sb.append(String.valueOf((ipLong >>> 24)));
        sb.append(".");
        //将高8位置0，然后右移16位
        sb.append(String.valueOf((ipLong & 0x00FFFFFF) >>> 16));
        sb.append(".");
        //将高16位置0，然后右移8位
        sb.append(String.valueOf((ipLong & 0x0000FFFF) >>> 8));
        sb.append(".");
        //将高24位置0
        sb.append(String.valueOf((ipLong & 0x000000FF)));
        return sb.toString();
	}
	
	public static String getLocalIp() throws Exception {
		return InetAddress.getLocalHost().getHostAddress();
	}
}
