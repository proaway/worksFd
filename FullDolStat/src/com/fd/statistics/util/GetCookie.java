package com.fd.statistics.util;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

public class GetCookie {
	public static String COOKIE_VISITOR_CODE = "COOKIE_VISITOR_CODE";
	
	/*public void addCookie(HttpServletRequest request,
			HttpServletResponse response, BuyerBean buyerVo) {
		String domainName="";
		String password=buyerVo.getPassword();
		
		try {
			domainName=SecurityUtil.encryptMode(buyerVo.getDomainName());
		} catch (Exception e) {
		   e.printStackTrace();
		}
		boolean flag1 = false;
		boolean flag2 = false;
		boolean flag3 = false;
		String cookieKey1 = "domainName_" + "#";// 需要验证的cookie1键
		String cookieKey2 = "password_" + "#";// 需要验证的cookie2键
		Cookie[] cookies = request.getCookies();// 获得客户端所有cookie数组
		if (cookies != null) {// 如果不为空
			for (int i = 0; i < cookies.length; i++) {
				String ckName = cookies[i].getName();
				if (cookieKey1.equals(ckName)) {
					flag3 = true;
					String ckValue = cookies[i].getValue();
					if (ckValue != null) {
						flag1 = true;
					}
				}
				if (cookieKey2.equals(ckName)) {
					flag3 = true;
					String ckValue = cookies[i].getValue();
					if (ckValue != null) {
						flag2 = true;
					}
				}
			}
		} else {
			// 第一次创建cookie
			addCookie(response, cookieKey1, domainName, 60 * 60 * 24 * 30);
			addCookie(response, cookieKey2, password, 60 * 60 * 24 * 30);
		}
		if ((flag1 && flag2) || !flag3) {
			// 第一次创建cookie
			addCookie(response, cookieKey1, domainName, 60 * 60 * 24 * 30);
			addCookie(response, cookieKey2, password, 60 * 60 * 24 * 30);
		}
	}

	public BuyerVo getCookieValue(HttpServletRequest request,
			HttpServletResponse response) {
		BuyerVo buyerVo = new BuyerVo();
		String cookieKey1 = "domainName_" + "#";// 需要验证的cookie1键
		String cookieKey2 = "password_" + "#";// 需要验证的cookie2键
		Cookie[] cookies = request.getCookies();//获得客户端所有cookie数组
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				String ckName = cookies[i].getName();
				if (cookieKey1.equals(ckName)) {
					String domainName="";
					try {
					domainName=SecurityUtil.decryptMode(cookies[i].getValue());
					} catch (Exception e) {
						e.printStackTrace();
					}
					buyerVo.setDomainName(domainName);
				}
				if (cookieKey2.equals(ckName)) {
					String password=cookies[i].getValue();
					buyerVo.setPassword(password);
				}
			}
		}
		return buyerVo;
	}

	public static String getCookieSearchFlag(RunData data, MicSession micSession) {
		try {
			String buyerName = data.getCookies().getString(Constants.BUYER_LASTLOGIN_NAME);
			if (StringUtils.isEmpty(buyerName)) {
				return "0";
			}
			String searchFlag = (String) micSession.getAttribute(Constants.BUYER_SEARCH_FLAG);
			if (searchFlag == null) {
				IBuyerManager buyerManager = (IBuyerManager) AppContextUtil.getBean("buyerManager");
				BuyerVo buyerVo = new BuyerVo();
				buyerVo.setNickName(buyerName);
				buyerVo = buyerManager.getBuyer(buyerVo);
				if (buyerVo == null)
					searchFlag = "0";
				else {
					searchFlag = String.valueOf(buyerVo.getSearchFlag());
					micSession.setAttribute(Constants.BUYER_SEARCH_FLAG, searchFlag);
				}
			}
			if ("0" == searchFlag) {
				// 清除Cookie
				addCookie(data.getResponse(), Constants.BUYER_LASTLOGIN_NAME, buyerName, 0);
			}
			return searchFlag;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "0";
	}
	}
	*/
	/**
	 * 添加Cookie，默认参数：Path = "/"；Domain = ".shopmadeinchina.com"
	 * 
	 * @param response
	 * @param key
	 * @param value
	 * @param time
	 */
	public static void addCookie(HttpServletResponse response, String key, String value, int time) {
		Cookie cookie = new Cookie(key, value);
		cookie.setPath("/");
		cookie.setDomain(".fulldol.com");
		cookie.setMaxAge(time);
		response.addCookie(cookie);
	}
}
