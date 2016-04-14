package com.fd.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.security.SecureRandom;
import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class DESadeUtil {
	
	// 定义加密算法,可用DES,DESede,Blowfish
	private static final String Algorithm = "DESede"; 
	
	private static final String CharSet = "gbk";
	
	private static final String keyName = "key";
	
	static {
		// 添加新安全算法,如果用JCE就要把它添加进去
		Security.addProvider(new com.sun.crypto.provider.SunJCE());
	}

	/**
	 * 加密算法
	 * 
	 * @param keybyte 密钥，长度为24字节
	 * @param src 需要加密的数据
	 * @return 返回加密后的byte数组
	 * @throws Exception
	 */
	public static byte[] encryptMode(byte[] keybyte, byte[] src)
			throws Exception {
		try {
			// 生成密钥
			SecretKey deskey = new SecretKeySpec(keybyte, Algorithm);

			// 加密
			Cipher c1 = Cipher.getInstance(Algorithm);
			c1.init(Cipher.ENCRYPT_MODE, deskey);
			return c1.doFinal(src);
		} catch (Exception e) {
			throw e;
		}
	}
	

	/**
	 * 解密算法
	 * 
	 * @param keybyte 密钥，长度为24字节
	 * @param src 需要加密的数据
	 * @return 返回解密后的字符串
	 * @throws Exception
	 */
	public static byte[] decryptMode(byte[] keybyte, byte[] src)
			throws Exception {
		try {
			// 生成密钥
			SecretKey deskey = new SecretKeySpec(keybyte, Algorithm);

			// 解密
			Cipher c1 = Cipher.getInstance(Algorithm);
			c1.init(Cipher.DECRYPT_MODE, deskey);
			return c1.doFinal(src);
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * 加密算法
	 * 
	 * @param keyName 密钥文件名
	 * @param src 需要加密的数据
	 * @return 返回加密后的字符串
	 * @throws Exception
	 */
	public static String encryptMode(String src)
			throws Exception {
		if (src == null) {
			return null;
		}
		// 读取密钥文件，密钥文件读入到byte数组中
		InputStream in = DESadeUtil.class.getResourceAsStream(keyName);
		byte key[] = new byte[in.available()];
		in.read(key);
		in.close();
		// 加密
		byte[] result = DESadeUtil.encryptMode(key, src.getBytes(CharSet));
		return byteArr2HexStr(result);
	}


	/**
	 * 解密算法
	 * 
	 * @param keyName 密钥文件名
	 * @param src 需要解密的数据
	 * @return 返回解密后的字符串
	 * @throws Exception
	 */
	public static String decryptMode(String src) {
		try {
			// 读取密钥文件，密钥文件读入到byte数组中
			InputStream in = DESadeUtil.class.getResourceAsStream(keyName);
			byte key[] = new byte[in.available()];
			in.read(key);
			in.close();
			// 解密
			byte[] result = DESadeUtil.decryptMode(key, hexStr2ByteArr(src));
			return new String(result, CharSet);
		} catch (Exception e) {
			return src;
		}
	}

	/**
	 * 将byte数组转换为表示16进制值的字符串， 如：byte[]{8,18}转换为：0813， 和public static byte[]
	 * hexStr2ByteArr(String strIn) 互为可逆的转换过程
	 * 
	 * @param arrB 需要转换的byte数组
	 * @return 转换后的字符串
	 * @throws Exception
	 */
	private static String byteArr2HexStr(byte[] arrB) throws Exception {
		int iLen = arrB.length;
		// 每个byte用两个字符才能表示，所以字符串的长度是数组长度的两倍
		StringBuffer sb = new StringBuffer(iLen * 2);
		for (int i = 0; i < iLen; i++) {
			int intTmp = arrB[i];
			// 把负数转换为正数
			while (intTmp < 0) {
				intTmp = intTmp + 256;
			}
			// 小于0F的数需要在前面补0
			if (intTmp < 16) {
				sb.append("0");
			}
			sb.append(Integer.toString(intTmp, 16));
		}
		return sb.toString();
	}

	/**
	 * 将表示16进制值的字符串转换为byte数组， 和public static String byteArr2HexStr(byte[] arrB)
	 * 互为可逆的转换过程
	 * 
	 * @param strIn 需要转换的字符串
	 * @return 转换后的byte数组
	 * @throws Exception
	 */
	private static byte[] hexStr2ByteArr(String strIn) throws Exception {
		byte[] arrB = strIn.getBytes();
		int iLen = arrB.length;
		// 两个字符表示一个字节，所以字节数组长度是字符串长度除以2
		byte[] arrOut = new byte[iLen / 2];
		for (int i = 0; i < iLen; i = i + 2) {
			String strTmp = new String(arrB, i, 2);
			arrOut[i / 2] = (byte) Integer.parseInt(strTmp, 16);
		}
		return arrOut;
	}

	/**
	 * 密钥生成方法
	 * 
	 * @param keyName 密钥文件存放全路径
	 */
	public static void createKey(String keyName) throws Exception {
		// 创建一个可信任的随机数源，DES算法需要
		SecureRandom sr = new SecureRandom();
		// 用DESede算法创建一个KeyGenerator对象
		KeyGenerator kg = KeyGenerator.getInstance(Algorithm);
		// 初始化此密钥生成器,使其具有确定的密钥长度
		kg.init(sr);
		// 生成密匙
		SecretKey key = kg.generateKey();
		// 获取密钥数据
		byte rawKeyData[] = key.getEncoded();
		// 将获取到密钥数据保存到文件中，待解密时使用
		FileOutputStream fo = new FileOutputStream(new File(keyName));
		fo.write(rawKeyData);
		fo.close();
	}
	
	public static void main(String[] args) {
		try {
			System.out.println(decryptMode("43213002d2ee3da4"));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
