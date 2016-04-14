package com.fd.session;

/**
 * MicSession的接口,提供与HttpSession一样的setAttribute、getAttribute、removeAttribute等方法
 * @since: Dec 26, 2008
 * @author: Alan
 * @company: shopmadeinchina.com
 */
@SuppressWarnings("unchecked")
public interface FdSession {
	
	public void setAttribute(String key, Object obj);
	
	public void setAttribute(String key, Object obj, long liveTime);
	
	public Object getAttribute(String key);
	
	public void removeAttribute(String key);
}
