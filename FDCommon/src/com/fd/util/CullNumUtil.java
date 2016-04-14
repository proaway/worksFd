package com.fd.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 截取数字后两位
 * 
 * @author w
 * 
 */
public class CullNumUtil {

	/**
	 * 取小数点后两位,如果是整数,会小数点后加上0,再输出(如 11,输出11.0)
	 * 
	 * @param doubleNum
	 * @return
	 */
	public static double getNum(double totalNum, double otherNum) {
		double doubleNum = totalNum - otherNum;
		BigDecimal bd = new BigDecimal(doubleNum);
		bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
		return bd.doubleValue();
	}

	/**
	 * 取小数点后两位,如果是整数,会小数点后加上0,再输出(如 11,输出11.0)
	 * 
	 * @param doubleNum
	 * @return
	 */
	public static double cullNum(double doubleNum) {
		BigDecimal bd = new BigDecimal(doubleNum);
		bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
		return bd.doubleValue();
	}

	/*
	 * 
	 * (如 11,输出11.00)
	 * 
	 */
	public static String cullNumT(double doubleNum) {
		int a=(int) doubleNum;
		String str=a+"";
		return str;
	}

	/*
	 * 
	 * 取小数点后两位,如果是整数,会小数点后加上00,再输出(如 11,输出11.00)
	 * 
	 */
	public static String cullNumTwo(double doubleNum) {
		return getDoubleToString(doubleNum, "0.00");
	}

	/**
	 * 取小数点后 digitLen 位
	 * 
	 * @param doubleNum
	 * @param digitLen
	 * @return
	 */
	public static double cullNum(double doubleNum, int digitLen) {
		BigDecimal bd = new BigDecimal(doubleNum);
		bd = bd.setScale(digitLen, BigDecimal.ROUND_HALF_UP);
		return bd.doubleValue();
	}
	/**
	 * 切割字符串
	 * 
	 * @param doubleNum
	 * @param digitLen
	 * @return
	 */
	public  List strSp(String str) {
		String newStr=null;
		if(str!=null&&str.length()>0){
		    String s = str.substring(str.length()-1,str.length());
		    if(s.equals(",")){
				newStr=str.substring(0,str.length()-1);
			}else{
				newStr=str;
			}
			String sL[]=newStr.split(",");
			List<String> sList=new ArrayList<String>();
			for (int i = 0; i < sL.length; i++) {
				sList.add(sL[i]);
			}
			return sList;
		}
        return null;
	}

	/**
	 * 将double型不以科学计数表示并转化为String
	 * 
	 * @param freight
	 * 
	 * @return
	 * @throws Exception
	 */
	public static String getDoubleToString(double num, String pattern) {
		if (pattern == null || "".equals(pattern)) {
			pattern = "0.00";
		}
		DecimalFormat decimalFormat = new DecimalFormat(pattern);
		String s = decimalFormat.format(num);
		try {
			Number o = decimalFormat.parse(s);
			if (o.doubleValue() == 0d) {
				o = 0d;
			}
			return decimalFormat.format(o);
		} catch (Exception e) {
			return s;
		}
	}
	public static int getInt(int priority_increment){
		if(priority_increment!=0){
			priority_increment*=-1;
		}
		return priority_increment;
	}
}
