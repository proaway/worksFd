package com.fd.fashion.order.util;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import com.fd.util.StringUtil;


public class MD5To16Str {

	public static void main(String args[]){   
//    	DecimalFormat df = new DecimalFormat();
//    	String pattern = "%010d";
//    	df.applyPattern(pattern);
    	for(int i=1;i<110;i++){
    		
    		Date date = new Date();
    		
    		getStrByMd5(StringUtil.getDateString(date,"yyyyMMddHHmmssSSS") + flushLeft(3,String.format("%010d", i))+ (int)(Math.random()*10));//df.format(i) );
    	}
//    	System.out.print(flushLeft(3,"12345678"));
        
    }   
    public static String flushLeft( long l, String string)    
    {       
        String cs = "";    
        if (string.length() > l) {
        	for (int i = string.length()-1; i > string.length()-l-1; i--)    
                cs = string.charAt(i)+ cs; 
        }
  
        return cs;    
    }  

    public static String getStrByMd5(String plainText){   
        String result="";   
        try {   
                MessageDigest md = MessageDigest.getInstance("MD5");   
                md.update(plainText.getBytes());   
                byte b[] = md.digest();   
                int i;   
                StringBuffer buf = new StringBuffer("");   
                for (int offset = 0; offset < b.length; offset++){   
                        i = b[offset];   
                        if (i < 0)   
                        i += 256;   
                        if (i < 16)   
                        buf.append("0");   
                        buf.append(Integer.toHexString(i));   
                }   
                //System.out.println("txt:" + plainText + ",result: " + buf.toString());// 32λ?????   
//        System.out.println("txt:" + plainText + ",result: " + (result=buf.toString().substring(8, 24)));// 16λ?????   
                result=buf.toString().substring(8, 24);
    } catch (NoSuchAlgorithmException e){   
                 e.printStackTrace();   
        }     
    return result;   
  }   
}
