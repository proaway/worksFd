package com.fd.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.velocity.context.Context;

/**
 * Context 和 Map 转换
 * 
 * @CreateDate: 2013-3-15 上午11:04:42
 * @author Longli
 * @Description: TODO
 * 
 */
public class Context2Map {
	/**
	 * Context 包装到 Map
	 * 
	 * @param context
	 * @return
	 */
	public static Map<String, Object> context2Map(Context context) {
		Object keys[] = context.getKeys();
		Map<String, Object> map = new HashMap<String, Object>();
		for (Object key : keys) {
			if (key instanceof String) {
				String k = (String) key;
				map.put(k, context.get(k));
			}
		}
		return map;
	}
}
