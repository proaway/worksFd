package com.fd.memcached;

@SuppressWarnings("unchecked")
public interface IMemcachedDAO {

	/**
	 * 将数据存储到Memcached中（带有效时间）
	 * @param key 键值
	 * @param value 对象
	 * @param time 过期时间，若要设置过期时间，需time值大于1000，如果小于1000
	 * 			   则过期时间均为0，即为永不过期
	 */
	public void setMemcached(String key,Object value,long time);
	
	/**
	 * 将数据存储到Memcached中
	 * @param key 键值
	 * @param value 对象
	 * 
	 * 此方法存储的value在Memcached中永远都不会过期，只有当Memcached的内存占满时
	 * 才会根据Memcached自身的最长时间不使用的算法将其中的一些value清除
	 */
	public void setMemcached(String key,Object value);
	
	/**
	 * 获取Memcached中对应key的值
	 * @param key
	 * @return
	 */
	public Object getMemcached(String key);
	
	public void deleteMemcached(String key,long time);
	
	public void deleteMemcached(String key);
}
