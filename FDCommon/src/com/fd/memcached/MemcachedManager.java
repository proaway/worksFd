package com.fd.memcached;

@SuppressWarnings("unchecked")
public class MemcachedManager {
	
	private IMemcachedDAO iMemcachedDAO=new HibernateMemcachedDAO();
	
	public Object getMemcached(String key) {
		return iMemcachedDAO.getMemcached(key);
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
	}

	/**
	 * 将数据存储到Memcached中（带有效时间）
	 * @param key 键值
	 * @param value 对象
	 * @param time 过期时间，若要设置过期时间，需time值大于1000，如果小于1000
	 * 			   则过期时间均为0，即为永不过期
	 */
	public void setMemcached(String key , Object value, long time) {
		iMemcachedDAO.setMemcached(key, value, time);
	}
	
	public void deleteMemcached(String key , long time) {
		iMemcachedDAO.deleteMemcached(key, time);
	}
	
	public void deleteMemcached(String key) {
		iMemcachedDAO.deleteMemcached(key);
	}
}
