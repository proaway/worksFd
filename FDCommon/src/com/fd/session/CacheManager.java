package com.fd.session;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.fd.memcached.MemcachedManager;
import com.fd.memcached.MemoryCacheKeyConstants;

@SuppressWarnings("unchecked")
public class CacheManager {
	
	private static MemcachedManager memcachedManager;
	private static Field fields [] = MemoryCacheKeyConstants.class.getDeclaredFields();
	
	public static String CACHEKEYLIST = "memoryCacheKeyList";
	
	public static boolean setData(String CategoryKey, Object obj, long liveTime) {
		if (CategoryKey == null) {
			return false;
		}
		getMemcachedManager().setMemcached(CategoryKey, obj, liveTime*60*1000);
		saveKey(CategoryKey);
		return true;
	}
	
	public static Object getData(String CategoryKey) {
		if (CategoryKey == null) {
			return false;
		}
		return getMemcachedManager().getMemcached(CategoryKey);
	}
	
	public static boolean deleteData(String CategoryKey) {
		if (CategoryKey == null) {
			return false;
		}
		getMemcachedManager().deleteMemcached(CategoryKey);
		removeKey(CategoryKey);
		return true;
	}
	
	private static MemcachedManager getMemcachedManager() {
		if (memcachedManager == null) {
			memcachedManager = new MemcachedManager();
		}
		return memcachedManager;
	}
	
	private static void removeKey(String cacheKey) {
		Object obj = getMemcachedManager().getMemcached(CACHEKEYLIST);
		if (obj == null) {
			return;
		}
		List<String> keyList = (List<String>) obj;
		keyList.remove(cacheKey);
		getMemcachedManager().setMemcached(CACHEKEYLIST, keyList, 0);
	}
	
	private static void saveKey(String cacheKey) {
		for (Field field : fields) {
			try {
				Object value = field.get(null);
				if (value instanceof String) {
					String key = (String) value;
					if (cacheKey.startsWith(key)) {
						Object obj = getMemcachedManager().getMemcached(CACHEKEYLIST);
						List<String> keyList = null;
						if (obj == null) {
							keyList = new ArrayList<String>();
						} else {
							keyList = (List<String>) obj;
						}
						if (!keyList.contains(cacheKey)) {
							keyList.add(cacheKey);
						}
						getMemcachedManager().setMemcached(CACHEKEYLIST, keyList, 0);
						break;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
