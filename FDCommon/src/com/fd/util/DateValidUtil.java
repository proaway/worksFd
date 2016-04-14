package com.fd.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * @CreateDate: 2013-3-14 下午05:55:36
 * @author Longli
 * @Description: 日期处理工具类。
 * 
 */
public class DateValidUtil {
	/**
	 * 某个日期离现在还有多少天
	 * 
	 * @param d1
	 * @return
	 * @throws Exception
	 */
	public long getDayNumBetweenTwoDate(String d1) throws Exception{
		if(null == d1 || "".equals(d1)){
			return 0;
		}
		long DAY = 24L * 60L * 60L * 1000L;    
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");    
		Date d = df.parse(d1);    
		Date dd = new Date();
		long number = (( d.getTime() - dd.getTime()) / DAY );
		return number;
	}
	
	/**
	 * 某个日期离现在还有多少天
	 * 
	 * @param d1
	 * @return
	 * @throws Exception
	 */
	public long getDaysToNow(Date d1) {
		try {
			if (null == d1) {
				return 0;
			}
			long DAY = 24L * 60L * 60L * 1000L;
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date d = df.parse(df.format(d1));
			Date dd = new Date();
			long number = ((dd.getTime() - d.getTime()) / DAY);
			return number;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	/**判断某活动是否有效(是否已经开始并没有结束)
	 * @param d1
	 * @param d2
	 * @return
	 * @throws Exception
	 */
	public boolean betweenTwoDate(Date d1,Date d2) throws Exception{
		Date d = new Date();
		boolean flag = false;
		if(d1.getTime()<d.getTime()){
			if(d2.getTime()>d.getTime()){
				flag = true;
			}
		}
		 return flag;
	}
}
