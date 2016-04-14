package com.fd.session;

import java.util.Date;

import com.fd.util.StringUtil;

/**
 * MicSession的具体实现类
 * @since: Dec 26, 2008
 * @author: Alan
 * @company: shopmadeinchina.com
 */
@SuppressWarnings("unchecked")
public class FdSessionImpl implements FdSession {
	
	private String sessionKey;
	
	private String categoryKey;
	
	// session过期时间360分钟
	public long liveTime = 360L;
	
	public FdSessionImpl(String SessionKey) {
		
		this.sessionKey = SessionKey;
	}
	
	public void setAttribute(String key, Object obj) {
		if (key == null) {
			throw new IllegalArgumentException("key not be null!");
		}
		if(obj != null) {
			categoryKey = sessionKey + "_" + key;
			CacheManager.setData(categoryKey, obj, liveTime);
		}
	}
	
	public void setAttribute(String key, Object obj, long liveTime) {
		if (key == null) {
			throw new IllegalArgumentException("key not be null!");
		}
		if(obj != null) {
			categoryKey = sessionKey + "_" + key;
			CacheManager.setData(categoryKey, obj, liveTime);
		}
	}
	
	public Object getAttribute(String key) {
		if (key == null) {
			
			throw new IllegalArgumentException("key not be null!");
		}
		categoryKey = sessionKey + "_" + key;
		Object obj = CacheManager.getData(categoryKey);
		if (obj != null) {
			CacheManager.setData(categoryKey, obj, liveTime);
		}
		return obj;
	}

	public void removeAttribute(String key) {
		
		if (key == null) {
			
			throw new IllegalArgumentException("key not be null!");
		}
		categoryKey = sessionKey + "_" + key;
		CacheManager.deleteData(categoryKey);
	}
}
