package com.fd.session;

import java.util.Properties;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.turbine.util.RunData;

import com.fd.memcached.MemCache;
import com.fd.util.RegexUtil;

/**
 * 将HttpSession封装为MicSession,以后所有应用中将不再出现HttpSession
 * @since: Dec 26, 2008
 * @author: Alan
 * @company: shopmadeinchina.com
 */
@SuppressWarnings("unchecked")
public class FdSessionFactory {
	
	protected static String CategoryKey = "FdSession";
	
	private static String cookieName = "FdSessionCookie";
	
	public static String cookieRoot;
	static {
		Properties prop = new Properties();
		try {
			prop.load(MemCache.class.getClassLoader().getResourceAsStream("memcached.properties"));
			cookieRoot = prop.getProperty("cookie.root");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static FdSession getFdSession(RunData data) {
		
		return new FdSessionImpl(getSessionID(data));
	}
	
	public static FdSession getFdSession(String sessionId) {
		
		return new FdSessionImpl(sessionId);
	}
	
	public static FdSession getFdSession(HttpServletRequest request, HttpServletResponse response) {
		
		return new FdSessionImpl(getSessionID(request, response));
	}
	
	private static String getSessionID(RunData data) {
        HttpServletRequest request = data.getRequest();
	    HttpServletResponse response = data.getResponse();

	    return getSessionID(request, response);
    }
	
	public static String getSessionID(HttpServletRequest request, HttpServletResponse response) {
        String sessionID = null;
        Cookie[] cookies = request.getCookies();
        if(cookies != null) {
        	
            for(int i = 0; sessionID == null && i < cookies.length; i++) {
            	
                Cookie cookie = cookies[i];
                if(cookie.getName().equals(cookieName) && !"".equals(cookie.getValue())) {
                    sessionID = cookie.getValue();
                    return sessionID;
                }
            }
        }
/*      String requestUrl = request.getRequestURL().toString();
        Pattern p = Pattern.compile("(?<=//|)((\\w)+\\.)+\\w+");  
        Matcher m = p.matcher(requestUrl);*/
        
        String domain = request.getRequestURL().toString();
        RegexUtil reg = new RegexUtil();
        domain = reg.getMatchedStr(domain, "//[^\\.]+(\\.[^/]+)", 1);
       
        if(sessionID == null) {
            sessionID = CategoryKey + "_" + request.getSession().getId();
            Cookie MicCookie = new Cookie(cookieName,sessionID);
            MicCookie.setPath("/");
            MicCookie.setDomain(domain);
            response.addCookie(MicCookie);
        }
        return sessionID;
    }
}
