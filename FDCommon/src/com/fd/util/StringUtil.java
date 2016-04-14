/**
 * 
 */
package com.fd.util;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

/**
 * @author hexiaofeng 此类中封装一些常用的字符串操作。 所有方法都是静态方法，不需要生成此类的实例，
 *         为避免生成此类的实例，构造方法被申明为private类型的。
 * @since 0.1
 */
public class StringUtil {

	public static String LINE_SEPARATOR = "\n";

	private static final String PRINTF_CONVERSION_OPERATORS = "%cdiuoxXeEfgGspn";

	private static final int PRINTF_DEFAULT_PRECISION = 6;
	
	private static final String DEFAULT_DATE_FORMAT="yyyy-MM-dd hh:mm:ss";
	
	private static RegexUtil regexUtil = new RegexUtil();

	public StringUtil() {

	}
	
	/**
	 * Double类型转为货币格式
	 * 
	 * @param src
	 * @return
	 */
	public static String formatCurrency(Double src) {
		if (src == null)
			return null;
		BigDecimal bd = new BigDecimal(src);
		bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
		double bdn = bd.doubleValue();
		DecimalFormat df = new DecimalFormat("0.00");
		return df.format(bdn);
	}
	
	/**
	 * Double类型转为货币格式
	 * 
	 * @param src
	 * @return
	 */
	public static String formatCurrency(Double src, String format) {
		if (src == null)
			return null;
		if (isEmpty(format))
			format = "###0.00";
		DecimalFormat formater = new DecimalFormat(format);
		String s = formater.format(src);
		return s;
	}

	/**
	 * 此方法将给出的字符串source使用delim划分为单词数组。
	 * 
	 * @param source
	 *            需要进行划分的原字符串
	 * @param delim
	 *            单词的分隔字符串
	 * @return 划分以后的数组，如果source为null的时候返回以source为唯一元素的数组，
	 *         如果delim为null则使用逗号作为分隔字符串。
	 * @since 0.1
	 */
	public static synchronized String[] split(String source, String delim) {
		String[] wordLists;
		if (source == null) {
			wordLists = new String[1];
			wordLists[0] = source;
			return wordLists;
		}
		if (delim == null) {
			delim = ",";
		}
		StringTokenizer st = new StringTokenizer(source, delim);
		int total = st.countTokens();
		wordLists = new String[total];
		for (int i = 0; i < total; i++) {
			wordLists[i] = st.nextToken();
		}
		return wordLists;
	}

	/**
	 * 此方法将给出的字符串source使用delim划分为单词数组。
	 * 
	 * @param source
	 *            需要进行划分的原字符串
	 * @param delim
	 *            单词的分隔字符
	 * @return 划分以后的数组，如果source为null的时候返回以source为唯一元素的数组。
	 * @since 0.2
	 */
	public static synchronized String[] split(String source, char delim) {
		return split(source, String.valueOf(delim));
	}

	/**
	 * 此方法将给出的字符串source使用逗号划分为单词数组。
	 * 
	 * @param source
	 *            需要进行划分的原字符串
	 * @return 划分以后的数组，如果source为null的时候返回以source为唯一元素的数组。
	 * @since 0.1
	 */
	public static synchronized String[] split(String source) {
		return split(source, ",");
	}

	/**
	 * 循环打印字符串数组。 字符串数组的各元素间以指定字符分隔，如果字符串中已经包含指定字符则在字符串的两端加上双引号。
	 * 
	 * @param strings
	 *            字符串数组
	 * @param delim
	 *            分隔符
	 * @param out
	 *            打印到的输出流
	 * @since 0.4
	 */
	public static synchronized void printStrings(String[] strings,
			String delim, OutputStream out) {
		try {
			if (strings != null) {
				int length = strings.length - 1;
				for (int i = 0; i < length; i++) {
					if (strings[i] != null) {
						if (strings[i].indexOf(delim) > -1) {
							out.write(("\"" + strings[i] + "\"" + delim)
									.getBytes());
						} else {
							out.write((strings[i] + delim).getBytes());
						}
					} else {
						out.write("null".getBytes());
					}
				}
				if (strings[length] != null) {
					if (strings[length].indexOf(delim) > -1) {
						out.write(("\"" + strings[length] + "\"").getBytes());
					} else {
						out.write(strings[length].getBytes());
					}
				} else {
					out.write("null".getBytes());
				}
			} else {
				out.write("null".getBytes());
			}
			out.write(LINE_SEPARATOR.getBytes());
		} catch (IOException e) {

		}
	}

	/**
	 * 返回去除时间的日期（yyyy-MM-dd）
	 * 
	 * @return
	 */
	public static synchronized Date getDate() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		return cal.getTime();
	}

	/**
	 * 循环打印字符串数组到标准输出。 字符串数组的各元素间以指定字符分隔，如果字符串中已经包含指定字符则在字符串的两端加上双引号。
	 * 
	 * @param strings
	 *            字符串数组
	 * @param delim
	 *            分隔符
	 * @since 0.4
	 */
	public static synchronized void printStrings(String[] strings, String delim) {
		printStrings(strings, delim, System.out);
	}

	/**
	 * 循环打印字符串数组。 字符串数组的各元素间以逗号分隔，如果字符串中已经包含逗号则在字符串的两端加上双引号。
	 * 
	 * @param strings
	 *            字符串数组
	 * @param out
	 *            打印到的输出流
	 * @since 0.2
	 */
	public static synchronized void printStrings(String[] strings,
			OutputStream out) {
		printStrings(strings, ",", out);
	}

	/**
	 * 循环打印字符串数组到系统标准输出流System.out。 字符串数组的各元素间以逗号分隔，如果字符串中已经包含逗号则在字符串的两端加上双引号。
	 * 
	 * @param strings
	 *            字符串数组
	 * @since 0.2
	 */
	public static synchronized void printStrings(String[] strings) {
		printStrings(strings, ",", System.out);
	}

	/**
	 * 将字符串中的变量使用values数组中的内容进行替换。 替换的过程是不进行嵌套的，即如果替换的内容中包含变量表达式时不会替换。
	 * 
	 * @param prefix
	 *            变量前缀字符串
	 * @param source
	 *            带参数的原字符串
	 * @param values
	 *            替换用的字符串数组
	 * @return 替换后的字符串。 如果前缀为null则使用“%”作为前缀；
	 *         如果source或者values为null或者values的长度为0则返回source；
	 *         如果values的长度大于参数的个数，多余的值将被忽略；
	 *         如果values的长度小于参数的个数，则后面的所有参数都使用最后一个值进行替换。
	 * @since 0.2
	 */
	public static synchronized String getReplaceString(String prefix,
			String source, String[] values) {
		String result = source;
		if (source == null || values == null || values.length < 1) {
			return source;
		}
		if (prefix == null) {
			prefix = "%";
		}

		for (int i = 0; i < values.length; i++) {
			String argument = prefix + Integer.toString(i + 1);
			int index = result.indexOf(argument);
			if (index != -1) {
				String temp = result.substring(0, index);
				if (i < values.length) {
					temp += values[i];
				} else {
					temp += values[values.length - 1];
				}
				temp += result.substring(index + 2);
				result = temp;
			}
		}
		return result;
	}

	/**
	 * 将字符串中的变量（以“%”为前导后接数字）使用values数组中的内容进行替换。
	 * 替换的过程是不进行嵌套的，即如果替换的内容中包含变量表达式时不会替换。
	 * 
	 * @param source
	 *            带参数的原字符串
	 * @param values
	 *            替换用的字符串数组
	 * @return 替换后的字符串
	 * @since 0.1
	 */
	public static synchronized String getReplaceString(String source,
			String[] values) {
		return getReplaceString("%", source, values);
	}

	/**
	 * 字符串数组中是否包含指定的字符串。
	 * 
	 * @param strings
	 *            字符串数组
	 * @param string
	 *            字符串
	 * @param caseSensitive
	 *            是否大小写敏感
	 * @return 包含时返回true，否则返回false
	 * @since 0.4
	 */
	public static synchronized boolean contains(String[] strings,
			String string, boolean caseSensitive) {
		for (int i = 0; i < strings.length; i++) {
			if (caseSensitive == true) {
				if (strings[i].equals(string)) {
					return true;
				}
			} else {
				if (strings[i].equalsIgnoreCase(string)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 字符串数组中是否包含指定的字符串。大小写敏感。
	 * 
	 * @param strings
	 *            字符串数组
	 * @param string
	 *            字符串
	 * @return 包含时返回true，否则返回false
	 * @since 0.4
	 */
	public static synchronized boolean contains(String[] strings, String string) {
		return contains(strings, string, true);
	}

	/**
	 * 不区分大小写判定字符串数组中是否包含指定的字符串。
	 * 
	 * @param strings
	 *            字符串数组
	 * @param string
	 *            字符串
	 * @return 包含时返回true，否则返回false
	 * @since 0.4
	 */
	public static synchronized boolean containsIgnoreCase(String[] strings,
			String string) {
		return contains(strings, string, false);
	}

	/**
	 * 将字符串数组使用指定的分隔符合并成一个字符串。
	 * 
	 * @param array
	 *            字符串数组
	 * @param delim
	 *            分隔符，为null的时候使用""作为分隔符（即没有分隔符）
	 * @return 合并后的字符串
	 * @since 0.4
	 */
	public static synchronized String combineStringArray(String[] array,
			String delim) {
		int length = array.length - 1;
		if (delim == null) {
			delim = "";
		}
		StringBuffer result = new StringBuffer(length * 8);
		for (int i = 0; i < length; i++) {
			result.append(array[i]);
			result.append(delim);
		}
		result.append(array[length]);
		return result.toString();
	}

	/**
	 * 以指定的字符和长度生成一个该字符的指定长度的字符串。
	 * 
	 * @param c
	 *            指定的字符
	 * @param length
	 *            指定的长度
	 * @return 最终生成的字符串
	 * @since 0.6
	 */
	public static synchronized String fillString(char c, int length) {
		String ret = "";
		for (int i = 0; i < length; i++) {
			ret += c;
		}
		return ret;
	}

	/**
	 * 去除左边多余的空格。
	 * 
	 * @param value
	 *            待去左边空格的字符串
	 * @return 去掉左边空格后的字符串
	 * @since 0.6
	 */
	public static synchronized String trimLeft(String value) {
		String result = value;
		if (result == null)
			return result;
		char ch[] = result.toCharArray();
		int index = -1;
		for (int i = 0; i < ch.length; i++) {
			if (Character.isWhitespace(ch[i])) {
				index = i;
			} else {
				break;
			}
		}
		if (index != -1) {
			result = result.substring(index + 1);
		}
		return result;
	}

	/**
	 * 去除右边多余的空格。
	 * 
	 * @param value
	 *            待去右边空格的字符串
	 * @return 去掉右边空格后的字符串
	 * @since 0.6
	 */
	public static synchronized String trimRight(String value) {
		String result = value;
		if (result == null)
			return result;
		char ch[] = result.toCharArray();
		int endIndex = -1;
		for (int i = ch.length - 1; i > -1; i--) {
			if (Character.isWhitespace(ch[i])) {
				endIndex = i;
			} else {
				break;
			}
		}
		if (endIndex != -1) {
			result = result.substring(0, endIndex);
		}
		return result;
	}
	
	/**
	 * 替换双字节空格并去除首尾空格
	 * 
	 * @param value
	 * @return
	 */
	public static synchronized String trim(String value) {
		if (value == null)
			return null;
		value = value.replace('　', ' ');
		return value.trim();
	}

	/**
	 * 根据转义列表对字符串进行转义。
	 * 
	 * @param source
	 *            待转义的字符串
	 * @param escapeCharMap
	 *            转义列表
	 * @return 转义后的字符串
	 * @since 0.6
	 */
	public static synchronized String escapeCharacter(String source,
			HashMap<String,Object> escapeCharMap) {
		if (source == null || source.length() == 0)
			return source;
		if (escapeCharMap.size() == 0)
			return source;
		StringBuffer sb = new StringBuffer();
		StringCharacterIterator sci = new StringCharacterIterator(source);
		for (char c = sci.first(); c != StringCharacterIterator.DONE; c = sci
				.next()) {
			String character = String.valueOf(c);
			if (escapeCharMap.containsKey(character))
				character = (String) escapeCharMap.get(character);
			sb.append(character);
		}
		return sb.toString();
	}

	/**
	 * 得到字符串的字节长度。
	 * 
	 * @param source
	 *            字符串
	 * @return 字符串的字节长度
	 * @since 0.6
	 */
	public static synchronized int getByteLength(String source) {
		int len = 0;
		for (int i = 0; i < source.length(); i++) {
			char c = source.charAt(i);
			int highByte = c >>> 8;
			len += highByte == 0 ? 1 : 2;
		}
		return len;
	}

	/**
	 * 得到字符串中的子串的个数。
	 * 
	 * @param source
	 *            字符串
	 * @param sub
	 *            子串
	 * @return 字符串中的子串的个数
	 * @since 0.6
	 */
	public static synchronized int getSubtringCount(String source, String sub) {
		if (source == null || source.length() == 0) {
			return 0;
		}
		int count = 0;
		int index = source.indexOf(sub);
		while (index >= 0) {
			count++;
			index = source.indexOf(sub, index + 1);
		}
		return count;
	}

	/**
	 * Replace all occurences of a substring within a string with another
	 * string.
	 * 
	 * @param inString
	 *            String to examine
	 * @param oldPattern
	 *            String to replace
	 * @param newPattern
	 *            String to insert
	 * @return a String with the replacements
	 */
	public static synchronized String replace(String inString,
			String oldPattern, String newPattern) {
		if (inString == null) {
			return null;
		}
		if (oldPattern == null || newPattern == null) {
			return inString;
		}

		StringBuffer sbuf = new StringBuffer();
		// output StringBuffer we'll build up
		int pos = 0; // our position in the old string
		int index = inString.indexOf(oldPattern);
		// the index of an occurrence we've found, or -1
		int patLen = oldPattern.length();
		while (index >= 0) {
			sbuf.append(inString.substring(pos, index));
			sbuf.append(newPattern);
			pos = index + patLen;
			index = inString.indexOf(oldPattern, pos);
		}
		sbuf.append(inString.substring(pos));

		// remember to append any characters to the right of a match
		return sbuf.toString();
	}

	/**
	 * 获取引号字符
	 * 
	 * @param source
	 *            字符串
	 * @return 加引号的字符串
	 * @since 0.6
	 */
	public static synchronized String getQuotedStr(String source) {
		return "'" + replace(source, "'", "''") + "'";

	}

	/**
	 * 获取HashCode
	 * 
	 * @param source
	 * @return
	 */
	public static synchronized int getHashCode(String source) {
		return source == null ? 0 : source.hashCode();
	}

	/**
	 * 比较字符串相等
	 * 
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static synchronized boolean strEqual(String str1, String str2) {
		if (str1 == str2)
			return true;
		if (str1 == null && str2 == null)
			return true;
		if (str1 == null && str2 != null)
			return false;
		return str1.equals(str2);
	}

	/**
	 * 获取日期字符串
	 * 
	 * @param date
	 * @return
	 */
	public static synchronized String getDateString(Date date) {
		if (date == null)
			return null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
	}
	
	/**
	 * 获取指定格式的日期字符串
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static synchronized String getDateString(Date date, String format) {
		if (date==null)
			return null;
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}

	/**
	 * @param value
	 * @param defaultValue
	 * @return
	 */
	public static synchronized long getAsLong(String value, long defaultValue) {
		try {
			value = value.replaceAll(",", "");
			return Long.valueOf(value).intValue();
		} catch (Exception e) {
		}
		return defaultValue;
	}

	/**
	 * @param value
	 * @param defaultValue
	 * @return
	 */
	public static synchronized int getAsInt(String value, int defaultValue) {
		try {
			value = value.replaceAll(",", "");
			return Integer.valueOf(value).intValue();
		} catch (Exception e) {
		}
		return defaultValue;
	}
	
	/**
	 * @param value
	 * @param defaultValue
	 * @return
	 */
	public static synchronized Double getAsDouble(String value, Double defaultValue) {
		try {
			Double val = Double.valueOf(value);
			return val;
		} catch (Exception e) {
			return defaultValue;
		}
	}

	/**
	 * 获取日期及时间字符串
	 * 
	 * @param date
	 * @return
	 */
	public static synchronized String getDateTimeString(Date date) {
		if (date == null)
			return null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
	}

	/**
	 * 判断字符串是否为空
	 * 
	 * @param source
	 * @return
	 */
	public static synchronized boolean isEmpty(String source) {
		if (source == null || source.trim().equals(""))
			return true;
		else
			return false;

	}

	/**
	 * 判断是否包含汉字
	 * 
	 * @param source
	 * @return
	 */
	public static synchronized boolean isChinese(String source) {
		if (source == null)
			return false;
		for (int i = 0; i < source.length(); i++) {
			char chr = source.charAt(i);
			int value = (int) chr;
			if ((value >= 0x2E80 && value <= 0x9FFF)
					|| (value >= 0xE800 && value <= 0xE87F)
					|| (value >= 0xF900 && value <= 0xFAFF)
					// 全角符号
					|| (value >= 0xFF00 && value <= 0xFF5E)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 判断字符串是否未合法的短日期格式yyyy-MM-dd
	 * 
	 * @param strDate
	 * @return
	 */
	public static synchronized boolean validateDateString(String strDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			sdf.parse(strDate);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 判断字符串是否未合法的长日期格式yyyy-MM-dd HH:mm:ss
	 * 
	 * @param strDate
	 * @return
	 */
	public static synchronized boolean validateDateTimeString(String strDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			sdf.parse(strDate);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * @param n
	 *            the integer to convert to hex form
	 * @return a String in the form 0xNNNNNNNN where the value of N is in 8
	 *         digit hex
	 */
	public static synchronized String toHexString(final int n) {
		final StringBuffer sb = new StringBuffer(10);
		final String s = toUpperCase(Integer.toHexString(n));
		sb.append("0x");
		for (int i = 0; i < 8 - s.length(); i++) {
			sb.append("0");
		}
		sb.append(s);
		return sb.toString();
	}

	/**
	 * @param n
	 *            the long to convert to hex form
	 * @return a String in the form 0xNNNNNNNNNNNNNNNN where the value of N is
	 *         in 16 digit hex
	 */
	public static synchronized String toHexString(final long n) {
		final StringBuffer sb = new StringBuffer(18);
		final String s = toUpperCase(Long.toHexString(n));
		sb.append("0x");
		for (int i = 0; i < 16 - s.length(); i++) {
			sb.append("0");
		}
		sb.append(s);
		return sb.toString();
	}

	/**
	 * @param n
	 *            the integer to convert to hex form
	 * @return a String in the form 0xNNNN where the value of N is in 4 digit
	 *         hex
	 */
	public static synchronized String toHexString(final short n) {
		final StringBuffer sb = new StringBuffer(10);
		final String s;
		if (n >= 0) {
			s = toUpperCase(Integer.toHexString(n));
		} else {
			s = toUpperCase(Integer.toHexString(n)).substring(4);
		}
		sb.append("0x");
		for (int i = 0; i < 4 - s.length(); i++) {
			sb.append("0");
		}
		sb.append(s);
		return sb.toString();
	}

	/**
	 * @param n
	 *            the integer to convert to hex form
	 * @return a String in the form 0xNN where the value of N is in 2 digit hex
	 */
	public static synchronized String toHexString(final byte n) {
		final StringBuffer sb = new StringBuffer(10);
		final String s;
		if (n >= 0) {
			s = toUpperCase(Integer.toHexString(n));
		} else {
			s = toUpperCase(Integer.toHexString(n)).substring(6);
		}
		sb.append("0x");
		for (int i = 0; i < 2 - s.length(); i++) {
			sb.append("0");
		}
		sb.append(s);
		return sb.toString();
	}

	/**
	 * Converts a <tt>String</tt> to lower case. This method assumes that the
	 * string is in english. Specifically it only converts characters from A
	 * (0x41) through Z (0x5A) to lower case.
	 * 
	 * @param s
	 *            the <tt>String</tt> to convert
	 * @return the converted string
	 */
	public static synchronized String toLowerCase(final String s) {
		final char[] ca = s.toCharArray();
		for (int i = 0; i < ca.length; i++) {
			final char c = ca[i];
			if (c >= 'A' && c <= 'Z')
				ca[i] += 32;
		}
		return new String(ca);
	}

	/**
	 * Converts a <tt>String</tt> to upper case. This method assumes that the
	 * string is in english. Specifically it only converts characters from a
	 * (0x61) through z (0x7A) to upper case.
	 * 
	 * @param s
	 *            the <tt>String</tt> to convert
	 * @return the converted string
	 */
	public static synchronized String toUpperCase(final String s) {
		final char[] ca = s.toCharArray();
		for (int i = 0; i < ca.length; i++) {
			final char c = ca[i];
			if (c >= 'a' && c <= 'z')
				ca[i] -= 32;
		}
		return new String(ca);
	}

	/** */
	public static synchronized String toAlphaNumeric(String s) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < s.length(); i++) {
			if (Character.isLetterOrDigit(s.charAt(i)))
				sb.append(s.charAt(i));
		}
		return sb.toString();
	}

	/**
	 * @param inputString
	 *            the input String
	 * @param setString
	 *            the set of characters to include/exclude
	 * @param includes
	 *            whether the set is of includes
	 * @return the stripped String
	 */
	public static synchronized String strip(final String inputString,
			final String setString, final boolean includes) {
		final char[] input = inputString.toCharArray();
		final char[] set = setString.toCharArray();
		Arrays.sort(set);
		final StringBuffer sb = new StringBuffer();
		for (int i = 0; i < input.length; i++) {
			if ((Arrays.binarySearch(set, input[i]) >= 0) == includes) {
				sb.append(input[i]);
			}
		}
		return sb.toString();
	}

	/**
	 * See {@link StringUtil#sprintf}
	 * 
	 * @param s
	 * @param args
	 * @return an int representing the length of the output
	 * @throws IllegalArgumentException
	 */
	public static synchronized final int printf(final String s,
			final Object[] args) throws IllegalArgumentException {
		final String out = sprintf(s, args);
		// 方法功能就是实现标准输出
		System.out.print(out);
		return out.length();
	}

	/**
	 * Docs from: <a
	 * href=http://www.ssec.wisc.edu/~dglo/c_class/printf.html>http://www.ssec.wisc.edu/~dglo/c_class/printf.html</a>
	 * <p>
	 * <h2><code>int printf(const char *format, ...)</code></h2>
	 * <ul>
	 * <li>formats and prints its arguments as specified by the
	 * <code>format</code> string.
	 * <li>Plain characters in <code>format</code> are simply copied
	 * <li>Format specifications are made up of a the percent sign (<code>%</code>)
	 * followed by one of the following conversion operators, which determine
	 * what <code>printf</code> does with its arguments:
	 * 
	 * <ul>
	 * <li><strong>%</strong> - print a single <code>%</code> character
	 * <li><strong>c</strong> - convert an <code>int</code> to an
	 * <code>unsigned character</code> and print the resulting character
	 * <li><strong>d</strong> or <strong>i</strong> - print an
	 * <code>int</code> as a signed decimal number
	 * 
	 * <li><strong>u</strong> - print an <code>unsigned</code> as an
	 * unsigned decimal number
	 * <li><strong>o</strong> - print an <code>unsigned</code> as an
	 * unsigned octal number
	 * <li><strong>x</strong> or <strong>X</strong> - print an
	 * 
	 * <code>unsigned</code> as an unsigned hexadecimal number (where the
	 * letters <code>abcdef</code> are used with <code>x</code> and
	 * <code>ABCDEF</code> are used with <code>X</code>
	 * <li><strong>e</strong> or <strong>E</strong> - print a
	 * <code>double</code>
	 * 
	 * using an exponential format like:<br>
	 * <code>[-]<var>d</var>.<var>ddd</var>e[+-]<var>dd</var></code><br>
	 * (where the <code>e</code> is replaced by <code>E</code> if the
	 * uppercase <code>E</code> is specified)
	 * 
	 * <li><strong>f</strong> - print a <code>double</code> using a decimal
	 * format like <code>[-]<var>ddd</var>.<var>ddd</var></code>
	 * <li><strong>g</strong> or <strong>G</strong> - print a
	 * <code>double</code>
	 * 
	 * using the same decimal format used by the <code>f</code> specification
	 * unless the exponent is less than -4 or greater than or equal to the
	 * specified precision (as described below), in which case the
	 * <code>e</code> format is used (replacing the <code>e</code> with an
	 * <code>E</code> if the <code>G</code> format specification is used.)
	 * Trailing zeros are removed from the right side of the decimal point. If
	 * there would be no digits to the right of the decimal point, the decimal
	 * point is also omitted.
	 * <li><strong>s</strong> - print the string pointed to by a
	 * <code>char *</code>
	 * 
	 * <li><strong>p</strong> - print a <code>void *</code> argument in
	 * hexadecimal <em>(ANSI C only)</em>
	 * <li><strong>n</strong> - store the number of characters printed at this
	 * point in the interger pointed to by the <code>int *</code> argument.
	 * Nothing is printed. <em>(ANSI C only)</em>
	 * </ul>
	 * 
	 * <li>The conversion operator may be preceeded by zero or more of the
	 * following flag characters:
	 * <ul>
	 * <li><strong>#</strong> specifies that the value should be converted to
	 * an alternate form:
	 * <ul>
	 * <li>For <strong>o</strong>, the precision (described below) is
	 * increased so that the first digit printed is a <code>0</code>
	 * <li>For <strong>x</strong> or <strong>X</strong>, a non-zero value has
	 * a <code>0x</code> prepended (or <code>0X</code> for the
	 * <code>X</code>
	 * 
	 * specification)
	 * <li>For <strong>e</strong>, <strong>E</strong>, <strong>f</strong>,
	 * <strong>g</strong> or <strong>G</strong>, the result will always
	 * contain a decimal point, even if no digits follow it. Additionally,
	 * trailing zeros are not removed from numbers formatted with <strong>g</strong>
	 * or <strong>G</strong>
	 * 
	 * </ul>
	 * <li><strong>0</strong> specifies that the value printed should be
	 * padded on the left with zeros to the maximum width specified
	 * <li><strong>-</strong> specifies that the value should be left
	 * justified (and padded with spaces to the right). If both <strong>0</strong>
	 * and <strong>-</strong> flags are specified, the <strong>0</strong> flag
	 * is ignored.
	 * <li>A space character specifies that a blank should be left before a
	 * positive number in a <strong>d</strong>, <strong>e</strong>,
	 * 
	 * <strong>E</strong>, <strong>f</strong>, <strong>g</strong>, <strong>G</strong>
	 * or <strong>i</strong> conversion
	 * <li><strong>+</strong> specifies that a plus sign should placed before
	 * a positive number in a <strong>d</strong>, <strong>e</strong>,
	 * 
	 * <strong>E</strong>, <strong>f</strong>, <strong>g</strong>, <strong>G</strong>
	 * or <strong>i</strong> conversion. If both <strong>+</strong> and space
	 * character flags are specified, the space character flag is ignored.
	 * </ul>
	 * <li>The flag(s) (if any) may be followed by an optional minimum field
	 * width specification, written as a decimal integer. If the value to be
	 * printed is shorter than the field width, it is padded with spaces (or
	 * <code>0</code>s if the
	 * 
	 * <code>0</code> flag was specified) to the left (or, if the
	 * <code>-</code> flag was specified, to the right with spaces)
	 * <li>Alternatively, the minimum field width specification may be a
	 * <code>*</code>, in which case the value to be printed is assumed to be
	 * preceeded by an <code>int</code> argument which is used to specify the
	 * minimum width.
	 * <li>The flags(s) and/or field width may be followed by a precision
	 * specification, written as a period followed by a decimal integer, which
	 * specifies:
	 * <ul>
	 * <li>the minimum number of digits to be printed for <strong>d</strong>,
	 * 
	 * <strong>i</strong>, <strong>o</strong>, <strong>u</strong>, <strong>x</strong>,
	 * and <strong>X</strong> conversions
	 * <li>the number of digits to the right of the decimal-point for <strong>e</strong>,
	 * <strong>E</strong>, and <strong>f</strong> conversions
	 * 
	 * <li>the number of significant digits for <strong>g</strong> and
	 * <strong>G</strong> conversions
	 * <li>the maximum number of characters to be printed from a string for
	 * <strong>s</strong> conversions
	 * </ul>
	 * <li>Alternatively, the precision specification may be a <code>*</code>,
	 * in which case the value to be printed is assumed to be preceeded by an
	 * <code>int</code> argument (following the <code>int</code> for the
	 * minimum field width, if <code>*</code> is specified for it as well)
	 * which is used to specify the precision.
	 * 
	 * <li>Some conversion operators may also be preceeded by a size
	 * specification:
	 * <ul>
	 * <li><strong>h</strong> indicates that the argument associated with a
	 * <strong>d</strong>, <strong>i</strong>, <strong>o</strong>, <strong>u</strong>,
	 * <strong>x</strong> or <strong>X</strong> operator is a
	 * <code>short</code> or
	 * 
	 * <code>unsigned short</code>, or that the argument to an <strong>n</strong>
	 * is a <code>short *</code> <em>(ANSI C only)</em>
	 * <li><strong>l</strong> indicates that the argument associated with a
	 * <strong>d</strong>, <strong>i</strong>, <strong>o</strong>, <strong>u</strong>,
	 * <strong>x</strong> or
	 * 
	 * <strong>X</strong> operator is a <code>long</code> or
	 * <code>unsigned long</code>, or that the argument to an <strong>n</strong>
	 * is a <code>long *</code>
	 * <li><strong>L</strong> indicates that the argument associated with a
	 * <strong>e</strong>, <strong>E</strong>, <strong>f</strong>,
	 * 
	 * <strong>g</strong> or <strong>G</strong> operator is a
	 * <code>long double</code> <em>(ANSI C only)</em>
	 * </ul>
	 * </ul>
	 * 
	 * @param s
	 * @param args
	 * @return a String representing the output
	 * @throws IllegalArgumentException
	 */
	public static synchronized final String sprintf(final String s,
			final Object[] args) throws IllegalArgumentException {
		int intValue, argc = 0;
		final StringBuffer ret = new StringBuffer(s.length());
		boolean addPrefix = false;
		boolean padWithZeros = false;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '%' && (i == 0 || s.charAt(i - 1) != '\\')) {
				final StringBuffer controlBuffer = new StringBuffer();
				// ----- get control code
				while (PRINTF_CONVERSION_OPERATORS.indexOf(s.charAt(++i)) == -1) {
					controlBuffer.append(s.charAt(i));
				}
				controlBuffer.append(s.charAt(i));
				// ----- see if there are any formatting options
				int width = -1;
				int precision = -1;
				final char objCode = controlBuffer.charAt(controlBuffer
						.length() - 1);
				controlBuffer.deleteCharAt(controlBuffer.length() - 1);
				if (controlBuffer.length() >= 1) {
					if (controlBuffer.indexOf(".") >= 0) {
						precision = Integer.parseInt(controlBuffer.substring(
								controlBuffer.indexOf(".") + 1, controlBuffer
										.length()));
						controlBuffer.delete(controlBuffer.indexOf("."),
								controlBuffer.length());
					}
					if (controlBuffer.indexOf("#") >= 0) {
						addPrefix = true;
						controlBuffer.deleteCharAt(controlBuffer.indexOf("#"));
					}
					if (controlBuffer.indexOf("0") >= 0)
						padWithZeros = true;
					if (controlBuffer.length() > 0)
						width = Integer.parseInt(controlBuffer.toString());
				}
				// ----- get the associated argument
				if (args == null || argc >= args.length)
					throw new IllegalArgumentException(
							"Not enough arguments supplied");
				// ----- construct pattern
				// StringBuffer pattern = null;
				// if(width > -1 || precision > -1) {
				// pattern = new StringBuffer(width + precision);
				// pattern.append('.');
				// for(int j = 0; j < precision; j++) {
				// pattern.append('0');
				// }
				// }
				// %cdiuoxXeEfgGspn
				switch (objCode) {
				case ('f'):
					ret.append(padOutput(args[argc].toString(), width, objCode,
							padWithZeros));
					Float f = new Float(args[argc].toString());
					if (precision > -1)
						ret.append(roundToPrecision(f.toString(), precision));
					else
						ret.append(roundToPrecision(f.toString(),
								PRINTF_DEFAULT_PRECISION));
					break;
				case ('d'):
				case ('i'):
					ret.append(padOutput(args[argc].toString(), width, objCode,
							padWithZeros));
					ret.append(args[argc]);
					break;
				case ('s'):
					ret.append(args[argc]);
					ret.append(padOutput(args[argc].toString(), width, objCode,
							padWithZeros));
					break;
				case ('%'):
					ret.append(args[argc]);
					ret.append('%');
					break;
				case ('c'):
					ret.append(padOutput(args[argc].toString(), width, objCode,
							padWithZeros));
					ret.append(args[argc]);
					break;
				// case ('u'):
				// ret.append(padOutput(args[argc].toString(), width, objCode,
				// padWithZeros));
				// intValue = new Integer(args[argc].toString()).intValue();
				// ret.append(new UnsignedShort(intValue).unsignedShortValue());
				// break;
				case ('o'):
					ret.append(padOutput(args[argc].toString(), width, objCode,
							padWithZeros));
					if (addPrefix)
						ret.append('0');
					intValue = new Integer(args[argc].toString()).intValue();
					ret.append(Integer.toString(intValue, 8));
					break;
				case ('x'):
					ret.append(padOutput(args[argc].toString(), width, objCode,
							padWithZeros));
					if (addPrefix)
						ret.append("0x");
					ret.append(toLowerCase(Long.toHexString(new Long(args[argc]
							.toString()).longValue())));
					break;
				case ('X'):
					ret.append(padOutput(args[argc].toString(), width, objCode,
							padWithZeros));
					if (addPrefix)
						ret.append("0X");
					ret.append(toUpperCase(Long.toHexString(new Long(args[argc]
							.toString()).longValue())));
					break;
				case ('e'):
					// ret.append(padOutput(args[argc].toString(), width));
					ret.append(toScientific(args[argc].toString(), 'e',
							precision, width, padWithZeros));
					break;
				case ('E'):
					// ret.append(padOutput(args[argc].toString(), width));
					ret.append(toScientific(args[argc].toString(), 'E',
							precision, width, padWithZeros));
					break;
				case ('g'):
					ret.append(padOutput(args[argc].toString(), width, objCode,
							padWithZeros));
					ret.append(roundToPrecision(args[argc].toString(),
							PRINTF_DEFAULT_PRECISION));
					break;
				case ('G'):
					break;
				case ('p'):
				case ('n'):
				default:
					throw new IllegalArgumentException("Unsupported param: "
							+ objCode);
				}
				padWithZeros = false;
				addPrefix = false;
				argc++;
			} else {
				ret.append(s.charAt(i));
			}
		}
		return ret.toString();
	}

	/**
	 * 
	 * @param num
	 * @param type
	 * @return
	 */
	private static synchronized String toScientific(String num, char type,
			int precision, int fieldWidth, boolean padWithZeros) {
		double d = new Double(num).doubleValue();
		int e = 0;
		boolean absGreaterThanOne = false;
		// the number is not between 1 and -1
		if (Math.abs(d) > 1) {
			while (d % 10.0 != d) {
				d = d / 10.0;
				e++;
			}
			absGreaterThanOne = true;
		}
		// the number lies between 1 and -1
		else if (Math.abs(d) < 1 && Math.abs(d) > 0) {
			while (Math.abs(d) < 1) {
				d = d * 10.0;
				e++;
			}
		}

		String sign;
		if (absGreaterThanOne)
			sign = type + "+";
		else
			sign = type + "-";

		String exponent;
		if (e < 10)
			exponent = "0" + new Integer(e).toString();
		else
			exponent = new Integer(e).toString();

		String roundedResult = roundToPrecision(new Double(d).toString(),
				precision)
				+ sign + exponent;

		if (padWithZeros && d < 0)
			roundedResult = "-"
					+ padOutput(roundedResult.substring(1, roundedResult
							.length()), fieldWidth - 1, type, padWithZeros)
					+ roundedResult.substring(1, roundedResult.length());
		else
			roundedResult = padOutput(roundedResult, fieldWidth, type,
					padWithZeros)
					+ roundedResult;
		return roundedResult;
	}

	/**
	 * 
	 * @param num
	 * @param precision
	 * @return
	 */
	private static synchronized String roundToPrecision(String num,
			int precision) {
		if (precision < 0)
			precision = PRINTF_DEFAULT_PRECISION;
		String fracPart = num.substring(num.indexOf(".") + 1, num.length());
		if (fracPart.length() > precision) {
			fracPart = fracPart.substring(0, precision + 1);
			double d = new Double(fracPart).doubleValue();
			fracPart = new Integer((int) Math.round(d / 10)).toString();
			return num.substring(0, num.indexOf(".") + 1) + fracPart;
		} else if (fracPart.length() < precision) {
			while (fracPart.length() < precision) {
				fracPart += "0";
			}
			return num.substring(0, num.indexOf(".") + 1) + fracPart;
		}
		return num;
	}

	/**
	 * 
	 * @param input
	 * @param fieldSize
	 * @return
	 */
	private static synchronized String padOutput(String input, int fieldSize,
			char type, boolean padWithZeros) {
		String padding = "";
		// char[] validTypes = { 'd', 'i', 'f', 'u', };
		for (int i = 0; i < fieldSize - input.length(); i++) {
			if (padWithZeros)
				padding += '0';
			else
				padding += ' ';
		}
		return padding;
	}

	/**
	 * 按指定日期格式取得日期值
	 * 
	 * @param date
	 * @param dateFormat
	 * @return
	 */
	public static synchronized Date getAsDate(String date, String dateFormat) {
		try {
			SimpleDateFormat format = new SimpleDateFormat(dateFormat);
			return format.parse(date);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 按缺省日期格式取得日期值
	 * 
	 * @param date
	 * @return
	 */
	public static synchronized Date getAsDate(String date) {
		return getAsDate(date, DEFAULT_DATE_FORMAT);
	}
	
	/**
	 * 按字节长度截取字符串
	 * 
	 * @param str
	 *            将要截取的字符串参数
	 * @param toCount
	 *            截取的字节长度
	 * @return 返回截取后的字符串
	 */
	public static String substring(String str, int toCount) {
		int reInt = 0;
		String reStr = "";
		if (str == null)
			return "";
		char[] tempChar = str.toCharArray();
		for (int kk = 0; (kk < tempChar.length && toCount > reInt); kk++) {
			String s1 = String.valueOf(tempChar[kk]);
			byte[] b = s1.getBytes();
			reInt += b.length;
			reStr += tempChar[kk];
		}
		return reStr;
	}

	/**
	 * @param s_value
	 * @param delim
	 * @return
	 */
	public static synchronized String[] splitByStr(String s_value, String delim) {
		int pos = 0;
		String s_list[];

		if (s_value != null && delim != null) {

			ArrayList<String> list = new ArrayList<String>();

			pos = s_value.indexOf(delim);
			int len = delim.length();

			while (pos >= 0) {
				if (pos > 0)
					list.add(s_value.substring(0, pos));
				if ((pos + len) < s_value.length())
					s_value = s_value.substring(len + pos);
				else
					s_value = null;
				if (s_value != null)
					pos = s_value.indexOf(delim);
				else
					pos = -1;
			}
			if (s_value != null)
				list.add(s_value);
			s_list = new String[list.size()];
			for (int i = 0; i < list.size(); i++) {
				s_list[i] = (String) list.get(i);
			}
		} else {
			s_list = new String[0];
		}
		return s_list;
	}
	
	/**
	 * @param val
	 * @return
	 */
	public static synchronized boolean getAsBoolean(String val) {
		if ("true".equalsIgnoreCase(val))
			return true;
		else 
			return false;
	}

	public static String gbkToIso8859(String val) {
		if (val == null)
			return null;
		
		try {
			return new String(val.getBytes("GBK"), "ISO8859");
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}
	
	
	public static String getdirid(final String string){
		if (string == null)
			return null;
		String curstr=string;
		if (string.length()==1)
	        curstr="00"+curstr;
	    else if (string.length()==2)
	        curstr="0"+string;
	    else
	        curstr=string;
	    String result=curstr.substring(curstr.length() -3);
	    return result;
	}
	/**
	 * 时间的比较
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static long getInterval(Date date1, Date date2){
		long s1 = date1.getTime();
		long s2 = date2.getTime();
		if(s1 - s2  <= 0)
			return 0;
		
		long interlVal = (s1 - s2) / (1000 * 60 * 60);
		return interlVal;
	}
	
	 /**
  	 * 将一个以特殊符号分割的字符串组装成数组，空白的地方用""代替
  	 * @param str 要分割的字符串
  	 * @param delimiter 分隔符
  	 * */
  	public String[] convertStrToArray(String str,String delimiter){
  		if(str==null||"".equals(str.trim())){
  			return null;
  		}
  	     StringTokenizer st1 = new StringTokenizer(str,delimiter,true);
  	     StringTokenizer st2 = new StringTokenizer(str,delimiter);
  	     
  	     int lth = st1.countTokens()-st2.countTokens()+1;
  	     String[] tmp=new String[lth];
  	     
  	     int i=0;
  	     String last=null;
  	     while(st1.hasMoreTokens()){
  	    	 String current=st1.nextToken();
  	    	 if(!delimiter.equals(current)){
  	    		 tmp[i]=current;
  	    		 i++;
  	    	 }else if(last==null || delimiter.equals(last) || !st1.hasMoreTokens()){
  	    		 tmp[i]="";
  	    		 i++;
  	    		 if(delimiter.equals(last) && !st1.hasMoreTokens()){
  	    			 tmp[i]="";
  	    		 }
  	    	 }
  	    	 last=current;
  	     }
  	     return tmp;
  	}
  	
  	/**
  	 * 编码字符串
  	 * @param str
  	 * @return
  	 * @throws UnsupportedEncodingException
  	 */
  	public String encode(String str) throws UnsupportedEncodingException{
  		if(str==null)
  			return null;
  		return URLEncoder.encode(str, "UTF-8");
  	}
  	
  	public static String htmlEncode(String str) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < str.length(); i++) {
			int c = (int)str.charAt(i);
			if (c>127 && c!=160) {
				sb.append("&#").append(c).append(";");
			} else {
				sb.append((char)c);
			}
		}
		return sb.toString();
  	}


	/**
	 * 截取字符串
	 * 
	 */
	public static String stringformat(String str, int n) {
		String temp = null;
		if (str.length() <= n)// 如果长度比需要的长度n小,返回原字符串
		{
			temp = str;
		} else {
			String s = str.substring(0, n - 3);

			temp = s + "...";
		}
		return temp;
	}

	/**
	 * 
	 * 按字节长度截取字符串(支持截取带HTML代码样式的字符串)，当字符串超出规定长度，添加候补字符串
	 * 
	 * @param param
	 *            将要截取的字符串参数
	 * @param length
	 *            截取的字节长度
	 * @param width
	 *            折行宽度，为0不添加 br 标识
	 * @param end
	 *            字符串末尾补上的字符串 例子：StringUtil.subStringHTML(
	 *            "aaaaaabbbbddzz<span>adfdsfdsadfasd</span>ddeeewwwwsssscccccxxxxzzz"
	 *            , 40, 0, "...");
	 * @return 返回截取后的字符串
	 */

	public static String subStringHTML(String param, int length, int width,
			String end) {
		StringBuffer result = new StringBuffer();
		String dbresult = new String();
		int n = 0;
		char temp;
		boolean isCode = false; // 是不是HTML代码
		boolean isAddEnd = false;// 判断是否要添加候补字符串

		for (int i = 0; i < param.length(); i++) {
			temp = param.charAt(i);

			if (temp == '<') {
				isCode = true;
			} else if (temp == '>' && isCode) {
				n = n - 1;
				isCode = false;
			}
			boolean isAddBr = false;
			if (width > 0 && !isCode && temp != '>' && n % width == (width - 1))
				isAddBr = true;
			if (!isCode) {
				n = n + 1;
				// UNICODE码字符占两个字节
				if ((temp + "").getBytes().length > 1) {
					n = n + 1;
				}
			}

			result.append(temp);

			if (n == (length - end.length())) {
				dbresult = result.toString();
				// System.out.println("result:" + dbresult);
			}
			if (isAddBr)
				result.append("<br>");
			if (n >= length) {
				isAddEnd = true;
				result = new StringBuffer(dbresult);
				// System.out.println("result:" + result);
				break;
			}
		}

		// 取出截取字符串中的HTML标记
		String temp_result = result.toString().replaceAll("(>)[^<>]*(<?)",
				"$1$2");
		// 去掉不需要结素标记的HTML标记
		temp_result = temp_result
				.replaceAll(
						"</?(AREA|BASE|BASEFONT|BODY|BR|COL|COLGROUP|DD|DT|FRAME|HEAD|HR|HTML|IMG|INPUT|ISINDEX|LI|LINK|META|OPTION|P|PARAM|TBODY|TD|TFOOT|TH|THEAD|TR|area|base|basefont|body|br|col|colgroup|dd|dt|frame|head|hr|html|img|input|isindex|li|link|meta|option|p|param|tbody|td|tfoot|th|thead|tr)[^<>]*/?>",
						"");
		// 去掉成对的HTML标记
		temp_result = temp_result.replaceAll("<([a-zA-Z]+)[^<>]*>(.*?)</\\1>",
				"$2");
		// 用正则表达式取出标记
		Pattern p = Pattern.compile("<([a-zA-Z]+)[^<>]*>");
		Matcher m = p.matcher(temp_result);

		List endHTML = new ArrayList();

		while (m.find()) {
			endHTML.add(m.group(1));
		}
		// 补全不成对的HTML标记
		for (int i = endHTML.size() - 1; i >= 0; i--) {
			result.append("</");
			result.append(endHTML.get(i));
			result.append(">");
		}
		if (isAddEnd) {
			result.append(end);
		}
		return result.toString();
	}
	
	/**
	 * 获取32位的随机字符串
	 * 
	 * @return
	 */
	public static String getRandomStr() {
		String code = MD5Util.calcMD5(Math.random()*10 + StringUtil.getDateString(new Date(),"yyyyMMddHHmmssSSS" + Math.random()*10));
		return code;
	}
	
	/**
	 * 自动补全分类ID
	 * 
	 * @return
	 */
	public static String setCatId(String catId) {
		catId = new StringBuilder(catId).reverse().toString();     //先将字符串颠倒顺序  
        String result = "";  
        for(int i=0;i<catId.length();i++){  
        	String tmp = "";
            if(i*3+3>catId.length()){  
            	tmp = catId.substring(i*3, catId.length());
            	while(tmp.length()<3 && tmp.length() > 0){
                	tmp += "0";
                }
                result += tmp;
                break;
            } else{
                tmp = catId.substring(i*3, i*3+3);
                result += tmp;
            }

        }  

        //最后再将顺序反转过来  
        return new StringBuilder(result).reverse().toString();
	}
	
	public static String getRequestParameters(HttpServletRequest request,
			String key) {
		String value = request.getParameter(key);
		if (value == null) {
			String queryStr = request.getQueryString();
			if (queryStr != null) {
				value = getQueryParamters(queryStr, key);
			}
		}
		if (value == null) {
			String url = request.getParameter("url");
			if (url != null) {
				value = getQueryParamters(url, key);
			}
		}
		return value;
	}
	
	public static String getQueryParamters(String queryStr, String key) {
		String value = regexUtil.getMatchedStr(queryStr, "(^|\\?|&)" + key
				+ "=([^\\?\\&]*)", 2);
		if (!isEmpty(value))
			try {
				value = URLDecoder.decode(value, "utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		return value;
	}
	public static String getGuid() {
		return UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
	}
}
