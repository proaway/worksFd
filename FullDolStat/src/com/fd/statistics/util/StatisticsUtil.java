package com.fd.statistics.util;


import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.turbine.util.RunData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.fd.session.FdSession;
import com.fd.session.FdSessionFactory;
import com.fd.statistics.bean.VisitLoginBean;
import com.fd.statistics.bean.VisitRegisterBean;
import com.fd.statistics.bean.VisitSessionBean;
import com.fd.statistics.manager.IStatisticsManager;
import com.fd.util.IPAddrUtil;
import com.fd.util.StringUtil;

@Component
public class StatisticsUtil {
    @Autowired      
    @Qualifier("statisticsManager") 
	private IStatisticsManager statisticsManager;
    
    public final static String VISIT_SESSION_NAME = "VISIT_SESSION_NAME";
	
	public void addVisitStat(final VisitSessionBean visit,
			final VisitRegisterBean visitRegister,
			final VisitLoginBean visitLogin,
			final FdSession session) {
		if (visit == null) {
			return;
		}
		new Thread() {
			@Override
			public void run() {
				try {
					if (visit.getVisitId() == null) {
						statisticsManager.saveVisitSession(visit);
						session.setAttribute(VISIT_SESSION_NAME, visit);
					}
					if (visitRegister != null) {
						visitRegister.setVisitId(visit.getVisitId());
						statisticsManager.addVisitRegister(visitRegister);
					}
					if (visitLogin != null) {
						visitLogin.setVisitId(visit.getVisitId());
						statisticsManager.addVisitLogin(visitLogin);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}.start();
	}

	/**
	 * @param data
	 * @param url
	 * @param now
	 * @param statisticsManager
	 * @param session
	 * @return
	 * @throws Exception
	 */
	public static VisitSessionBean getVisitSession(RunData data, String url, Date now,
			IStatisticsManager statisticsManager, FdSession session)
			throws Exception {
		VisitSessionBean visit = (VisitSessionBean) session.getAttribute(StatisticsUtil.VISIT_SESSION_NAME);
		// 检查Cookie中是否有访问编码
		String visitorCode = data.getCookies().getString(GetCookie.COOKIE_VISITOR_CODE);
		if (StringUtils.isEmpty(visitorCode)) {
			// 访问编码空
			visitorCode = StringUtil.getGuid();
			GetCookie.addCookie(data.getResponse(), GetCookie.COOKIE_VISITOR_CODE, visitorCode, 60*60*24*365);
		}

		if (visit == null || visit.getVisitId() == null) {
			String sessionId = FdSessionFactory.getSessionID(data.getRequest(), data.getResponse());
			String ipAddress = IPAddrUtil.getIpAddr(data.getRequest());
			String userAgent = data.getRequest().getHeader("user-agent");
			String language = data.getRequest().getHeader("accept-language");
			visit = new VisitSessionBean();
			visit.setSessionId(sessionId);
			visit.setVisitTime(now);
			visit.setLanguage(language);
			visit.setIp(ipAddress);
			visit.setUserAgent(userAgent);
			visit.setVisitorCode(visitorCode);
			visit.setLandingPage(url);
			visit = statisticsManager.saveVisitSession(visit);
			session.setAttribute(StatisticsUtil.VISIT_SESSION_NAME, visit);
			// Cookie中重新保存访问编码
			GetCookie.addCookie(data.getResponse(), GetCookie.COOKIE_VISITOR_CODE, visitorCode, 60*60*24*365);
		}
		return visit;
	}

	/**
	 * @param data
	 * @param url
	 * @param now
	 * @param statisticsManager
	 * @param session
	 * @return
	 * @throws Exception
	 */
	public static VisitSessionBean getCacheVisitSession(RunData data, String url, Date now, FdSession session)
			throws Exception {
		VisitSessionBean visit = (VisitSessionBean) session.getAttribute(StatisticsUtil.VISIT_SESSION_NAME);
		// 检查Cookie中是否有访问编码
		String visitorCode = data.getCookies().getString(GetCookie.COOKIE_VISITOR_CODE);
		if (StringUtils.isEmpty(visitorCode)) {
			// 访问编码空
			visitorCode = StringUtil.getGuid();
			GetCookie.addCookie(data.getResponse(), GetCookie.COOKIE_VISITOR_CODE, visitorCode, 60*60*24*365);
		}

		if (visit == null || visit.getVisitId() == null) {
			String sessionId = FdSessionFactory.getSessionID(data.getRequest(), data.getResponse());
			String ipAddress = IPAddrUtil.getIpAddr(data.getRequest());
			String userAgent = data.getRequest().getHeader("user-agent");
			String language = data.getRequest().getHeader("accept-language");
			visit = new VisitSessionBean();
			visit.setSessionId(sessionId);
			visit.setVisitTime(now);
			visit.setLanguage(language);
			visit.setIp(ipAddress);
			visit.setUserAgent(userAgent);
			visit.setVisitorCode(visitorCode);
			visit.setLandingPage(url);
			// Cookie中重新保存访问编码
			GetCookie.addCookie(data.getResponse(), GetCookie.COOKIE_VISITOR_CODE, visitorCode, 60*60*24*365);
			session.setAttribute(StatisticsUtil.VISIT_SESSION_NAME, visit);
		}
		return visit;
	}
}
