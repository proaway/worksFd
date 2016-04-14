package com.fd.memcached;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * 实例化Memcache
 *
 */
@SuppressWarnings("unchecked")
public class MemCache {
	private static MemCache cache = null;

	//由于多服务器均有自己的实例，所以此处利用List存储各个服务器的Memcached实例
	public static List mcpool = new ArrayList();
	
	private String servers[];


	private MemCache() throws Exception {
		
		readMemcachedFile();
		for(int i=0;i<servers.length;i++) {
			
			MemCachedClient mc = null;
			String[] serverlist = {servers[i]};
			SockIOPool pool = SockIOPool.getInstance("conn"+i);
			pool.setServers(serverlist);
			pool.setInitConn(10);
			pool.setMinConn(5);
			pool.setMaxConn(250);
			pool.setMaintSleep(10*1000);
			pool.setNagle(false);
			pool.setSocketTO(3000);
			pool.setSocketConnectTO(0);
			pool.setAliveCheck(true);
			pool.initialize();
			mc = new MemCachedClient();
			mc.setPoolName("conn"+i);
			mc.setCompressEnable(false);
			mcpool.add(mc);
		}
	}

	public static MemCache getMemCache() throws Exception {
		
		if (cache==null) {
			
			cache = new MemCache();
		}
		return cache;
	}
	

	public void readMemcachedFile() throws Exception {
		
		// 取得XML路
		SAXReader reader = new SAXReader();
		// 获得XML文档对象
		Document document = reader.read(this.getClass().getClassLoader().getResourceAsStream("memcacheconfig.xml"));
		Element element = document.getRootElement();

		List list=element.elements();
		servers=new String[list.size()];
		for(int i=0;i<list.size();i++) {
			
			Element child=(Element)list.get(i);
			String ip=child.elementText("IP");
			String port=child.elementText("PORT");
			servers[i]=ip+":"+port;
		}
	}
}
