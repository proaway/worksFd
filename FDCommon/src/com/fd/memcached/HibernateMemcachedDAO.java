package com.fd.memcached;

import java.util.Date;
import java.util.List;
import java.util.Random;

@SuppressWarnings("unchecked")
public class HibernateMemcachedDAO implements IMemcachedDAO {

	List mcpool=null; //存储所有服务器Memcached的实例
	public HibernateMemcachedDAO() {
		
		try {
			mcpool = MemCache.getMemCache().mcpool;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 随机从一台服务器上查找对应key的值
	 * 若有，则返回
	 * 若没有，则遍历所有服务器
	 */
	public Object getMemcached(String key) {
		
		int num=(Math.abs(new Random().nextInt()))%mcpool.size();
		Object object=null;
		object=((MemCachedClient)mcpool.get(num)).get(key);
		
		if(object!=null) {
			
			return object;
		}
		for(int i=0;i<mcpool.size();i++) {
			
			MemCachedClient mc=(MemCachedClient)mcpool.get(i);
			object=mc.get(key);
			if(object!=null) {
				
				return object;
			}
		}
		return object;
	}

	/**
	 * 将数据存储到Memcached中
	 * @param key 键值
	 * @param value 对象
	 * 
	 * 此方法存储的value在Memcached中永远都不会过期，只有当Memcached的内存占满时
	 * 才会根据Memcached自身的最长时间不使用的算法将其中的一些value清除
	 */
	public void setMemcached(String key, Object value) {
		
		for(int i=0;i<mcpool.size();i++) {
			
			MemCachedClient mc=(MemCachedClient)mcpool.get(i);
			mc.set(key, value);
		}
	}

	/**
	 * 将数据存储到Memcached中（带有效时间）
	 * @param key 键值
	 * @param value 对象
	 * @param time 过期时间，若要设置过期时间，需time值大于1000，如果小于1000
	 * 			   则过期时间均为0，即为永不过期
	 */
	public void setMemcached(String key, Object value, long time) {
		
		for(int i=0;i<mcpool.size();i++) {
			
			MemCachedClient mc=(MemCachedClient)mcpool.get(i);
			mc.set(key, value ,new Date(time));
		}
	}

	public void deleteMemcached(String key, long time) {
		
		for(int i=0;i<mcpool.size();i++) {
			
			MemCachedClient mc=(MemCachedClient)mcpool.get(i);
			mc.delete(key ,new Date(time));
		}
	}

	public void deleteMemcached(String key) {
		
		for(int i=0;i<mcpool.size();i++) {
			
			MemCachedClient mc=(MemCachedClient)mcpool.get(i);
			mc.delete(key);
		}
	}
}
