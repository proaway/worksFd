/**
 * 
 */
package com.fd.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.oro.text.regex.MatchResult;
import org.apache.oro.text.regex.Pattern;
import org.apache.oro.text.regex.PatternCompiler;
import org.apache.oro.text.regex.PatternMatcherInput;
import org.apache.oro.text.regex.Perl5Compiler;
import org.apache.oro.text.regex.Perl5Matcher;

/**
 * 正则表达式工具类
 * 
 * @author 何小锋
 * 
 */
public class RegexUtil {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(RegexUtil.class);

	/** 正则之间的分隔符 "||" */
	public static final String TAG_REGEX_SPLIT = "||";

	private String url;

	/**
	 * 
	 */
	public RegexUtil() {
	}

	/**
	 * 设置模式
	 * 
	 * @param findStr
	 * @return
	 * @throws Exception
	 */
	private Pattern getPattern(String findStr) throws Exception {
		Pattern pattern = null;
		PatternCompiler compiler = new Perl5Compiler();
		pattern = compiler.compile(findStr, Perl5Compiler.CASE_INSENSITIVE_MASK
				| Perl5Compiler.SINGLELINE_MASK);
		return pattern;
	}

	/**
	 * 返回匹配的字符串
	 * 
	 * @param source
	 *            字符传
	 * @param patten
	 *            正则表达式
	 * @param index
	 *            匹配的索引
	 * @return
	 */
	public String getMatchedStr(String source, String patten, int index) {
		MatchResult result = getMatchResult(source, patten);
		if (result != null)
			return result.group(index);
		return null;
	}

	/**
	 * 返回匹配的字符串
	 * 
	 * @param source
	 *            字符传
	 * @param patten
	 *            正则表达式
	 * @param replaceRegex
	 *            替换正则表达式
	 * @return
	 */
	public String getMatchedStr(String source, String patten,
			String replaceRegex) {
		MatchResult match = getMatchResult(source, patten);
		if (match == null)
			return null;
		String result = match.group(1);
		// 替换replaceRegex中的^1、^2、^3...等参数
		if (replaceRegex != null && !replaceRegex.trim().equals("")) {
			result = replaceRegex;
			int count = match.groups();
			for (int i = 1; i < count; i++) {
				result = result.replaceAll("\\^" + i, match.group(i));
			}
		}
		return result;
	}

	/**
	 * 返回所有匹配的字符串
	 * 
	 * @param source
	 *            字符传
	 * @param patten
	 *            正则表达式
	 * @param replaceRegex
	 *            替换正则表达式
	 * @param split
	 *            分割富豪
	 * @return
	 */
	public String getAllMatchedStr(String source, String patten,
			String replaceRegex, String split) {

		try {
			// 捕获所有匹配
			List<MatchResult> list = getAllMatchResult(source, patten);
			if (list == null || list.size() == 0)
				return null;

			StringBuffer buf = new StringBuffer();
			MatchResult match = null;
			String result = null;
			// 遍历匹配
			for (int j = 0; j < list.size(); j++) {
				match = list.get(j);
				result = match.group(1);
				// 替换replaceRegex中的^1、^2、^3...等参数
				if (replaceRegex != null && !replaceRegex.trim().equals("")) {
					result = replaceRegex;
					int count = match.groups();
					for (int i = 1; i < count; i++) {
						result = result.replaceAll("\\^" + i, match.group(i));
					}
				}
				if (j > 0)
					buf.append(split);
				buf.append(result);
			}
			return buf.toString();
		} catch (Exception e) {
			logger.warn(e.getMessage());
		}
		return null;
	}

	/**
	 * 返回是否匹配
	 * 
	 * @return
	 */
	public boolean isMatch(String source, String patten) {
		if (patten == null || source == null)
			return false;
		boolean flag = true;
		if (patten.startsWith("!!")) {
			patten = patten.substring(2);
			flag = false;
		}
		MatchResult result = getMatchResult(source, patten);
		return flag ? result != null : result == null;
	}

	/**
	 * @param source
	 * @param patten
	 * @return
	 */
	public MatchResult getMatchResult(String source, String patten) {
		if (source == null || patten == null) {
			return null;
		}
		try {
			String pattens[] = StringUtil.splitByStr(patten, TAG_REGEX_SPLIT);
			for (int i = 0; i < pattens.length; i++) {
				Perl5Matcher matcher = new Perl5Matcher();
				Pattern pattern = getPattern(pattens[i]);
				if (matcher.contains(source, pattern)) {
					return matcher.getMatch();
				}
			}
		} catch (Exception e) {
			logger.warn(e.getMessage());
		}
		return null;
	}

	/**
	 * @param source
	 * @param patten
	 * @return
	 * @throws Exception
	 */
	public List<MatchResult> getAllMatchResult(String source, String patten)
			throws Exception {
		if (source == null || patten == null)
			return null;
		List<MatchResult> list = new ArrayList<MatchResult>();
		String pattens[] = StringUtil.splitByStr(patten, TAG_REGEX_SPLIT);
		PatternMatcherInput input = new PatternMatcherInput(source);
		for (int i = 0; i < pattens.length; i++) {
			Perl5Matcher matcher = new Perl5Matcher();
			Pattern pattern = getPattern(pattens[i]);
			while (matcher.contains(input, pattern)) {
				list.add(matcher.getMatch());
			}
		}
		return list;
	}

	/**
	 * 替换匹配到的第一个字符串
	 * 
	 * @param source
	 * @param patten
	 * @param replace
	 * @return
	 * @throws Exception
	 */
	public static String replaceFirst(String source, String patten,
			String replace) throws Exception {
		if (source == null)
			return null;
		if (patten == null || replace == null)
			return null;
		Perl5Matcher matcher = new Perl5Matcher();
		PatternCompiler compiler = new Perl5Compiler();
		Pattern pattern = compiler.compile(patten,
				Perl5Compiler.CASE_INSENSITIVE_MASK
						| Perl5Compiler.SINGLELINE_MASK);
		StringBuffer buf = new StringBuffer(100);
		if (matcher.contains(source, pattern)) {
			MatchResult result = matcher.getMatch();
			buf.delete(0, buf.length());
			int begin = result.beginOffset(0);
			int end = result.endOffset(0);
			buf.append(source.substring(0, begin));
			buf.append(replace);
			buf.append(source.substring(end));
			source = buf.toString();
		}
		return source;
	}

	/**
	 * 正则表达式替换
	 * 
	 * @param source
	 * @param patten
	 * @param replace
	 * @return
	 * @throws Exception
	 */
	public static String replaceAll(String source, String patten, String replace)
			throws Exception {
		if (source == null)
			return null;
		if (patten == null || replace == null)
			return null;
		Perl5Matcher matcher = new Perl5Matcher();
		PatternCompiler compiler = new Perl5Compiler();
		Pattern pattern = compiler.compile(patten,
				Perl5Compiler.CASE_INSENSITIVE_MASK
						| Perl5Compiler.SINGLELINE_MASK);
		StringBuffer buf = new StringBuffer(100);
		while (matcher.contains(source, pattern)) {
			MatchResult result = matcher.getMatch();
			buf.delete(0, buf.length());
			int begin = result.beginOffset(0);
			int end = result.endOffset(0);
			buf.append(source.substring(0, begin));
			buf.append(replace);
			buf.append(source.substring(end));
			source = buf.toString();
		}
		return source;
	}

	/**
	 * 返回所有的匹配
	 * 
	 * @param source
	 * @param patten
	 * @param index
	 * @return
	 */
	public List<String> getAllMatched(String source, String patten, int index) {
		List<String> list = new ArrayList<String>();
		if (source != null && patten != null) {
			try {
				Perl5Matcher matcher = new Perl5Matcher();
				Pattern pattern = getPattern(patten);
				PatternMatcherInput input = new PatternMatcherInput(source);
				while (matcher.contains(input, pattern)) {
					list.add(matcher.getMatch().group(index));
				}
			} catch (Exception e) {
				logger.warn(e.getMessage());
			}
		}
		return list;
	}

	/**
	 * 获取匹配的中间字符串
	 * 
	 * @param source
	 * @param beginPatten
	 * @param endPatten
	 * @return
	 * @throws ContentError
	 */
	public String getBetween(String source, String beginPatten, String endPatten)
			throws Exception {
		if (source == null)
			return null;
		int beginPos = 0;
		int endPos = -1;

		MatchResult resultBegin = null;
		// 查找开始位置
		if (beginPatten != null && !beginPatten.equals("")) {
			resultBegin = getMatchResult(source, beginPatten);
			if (resultBegin == null)
				throw new Exception("beginPatten not found," + beginPatten);
			String begin = resultBegin.group(0);
			beginPos = resultBegin.beginOffset(0) + begin.length();
		}

		// 查找结束位置
		if (endPatten != null && !endPatten.equals("")) {
			String tail = source;
			if (resultBegin != null)
				tail = source.substring(beginPos);
			MatchResult resultEnd = getMatchResult(tail, endPatten);
			if (resultEnd == null)
				throw new Exception("endPatten not found," + endPatten);
			endPos = beginPos + resultEnd.beginOffset(0);

		}

		if (beginPos >= 0 && endPos >= 0) {
			return source.substring(beginPos, endPos);
		} else if (beginPos > 0)
			return source.substring(beginPos);
		else if (beginPos == 0)
			return source;
		else {
			return null;
		}
	}

	/**
	 * 过滤字符串中所有html代码,但是不包括<br>
	 * <p>
	 * 
	 * @param s
	 * @return
	 */
	public static synchronized String filterHTML(String s) {

		if (s != null) {
			s = s.replaceAll("\\s+", " ");// 去掉多余的空格和回车换行

			s = s.replaceAll("<\\s*[Bb][Rr]\\s*/?>", "/r/n/br");
			s = s.replaceAll("<\\s*/[Pp]\\s*>", "/r/n/p/r");
			s = s.replaceAll("<\\s*[Pp]\\s*/?>", "/r/n/p/l");

			s = s.replaceAll("&nbsp;", " ");
			s = s.replaceAll("<.*?>", "");
			s = s.replaceAll("·", ",");

			// 处理全角空格
			s = s.replaceAll("　+", " ");

			s = s.replaceAll("/r/n/br", "<br>");
			s = s.replaceAll("/r/n/p/r", "<br>");
			s = s.replaceAll("/r/n/p/l", "<br>");
			// s = s.replaceAll("/r/n/p/r", "</p>");
			// s = s.replaceAll("/r/n/p/l", "<p>");
			// 去多个空合格

			s = s.replaceAll("\\s+", " ");

			// 最后处理转移
			s = s.replaceAll("&amp;", "&");
			s = s.replaceAll("&quot;", "\"");
			s = s.replaceAll("&apos;", "'");
			s = s.replaceAll("&gt;", ">");
			s = s.replaceAll("&lt;", "<");
			// 去首尾空格
			s = s.trim();
		}
		return s;
	}

	/**
	 * 过滤字符串中所有html代码,但是不包括hodeTags中包含的标记
	 * 
	 * @param src
	 * @param hodeTags
	 *            用来匹配保留标记的正则，多个标记正则之间用“,”分隔
	 * @return
	 */
	public static synchronized String filterHTML(String src, String hodeTags) {
		if (hodeTags == null)
			hodeTags = "";

		RegexUtil regex = new RegexUtil();
		String[] tags = hodeTags.split(",");
		HashMap<String, String> tagMap = new HashMap<String, String>();
		for (int i = 0; i < tags.length; i++) {
			tagMap.put(tags[i], MD5Util.calcMD5(tags[i]));
		}

		if (src != null) {
			HashMap<String, List<String>> tagM = new HashMap<String, List<String>>();
			src = src.replaceAll("\\s+", " ");// 去掉多余的空格和回车换行
			// 保存标记
			for (Iterator<String> iter = tagMap.keySet().iterator(); iter
					.hasNext();) {
				String tag = iter.next();
				List<String> tagList = regex.getAllMatched(src, tag, 0);
				tagM.put(tag, tagList);
				src = src.replaceAll(tag, tagMap.get(tag));
			}

			src = src.replaceAll("&nbsp;", " ");
			src = src.replaceAll("<.*?>", "");
			src = src.replaceAll("·", ",");

			// 处理全角空格
			src = src.replaceAll("　+", " ");

			// 恢复标记
			for (Iterator<String> iter = tagMap.keySet().iterator(); iter
					.hasNext();) {
				String tag = iter.next();
				List<String> tagList = tagM.get(tag);
				for (String tagS : tagList) {
					src = src.replaceFirst(tagMap.get(tag), tagS);
				}
			}

			// 去多个空格
			src = src.replaceAll("\\s+", " ");

			// 最后处理转移
			src = src.replaceAll("&amp;", "&");
			src = src.replaceAll("&quot;", "\"");
			src = src.replaceAll("&apos;", "'");
			// src = src.replaceAll("&gt;", ">");
			// src = src.replaceAll("&lt;", "<");
			// 去首尾空格
			src = src.trim();
		}
		return src;
	}

	public static synchronized String filterUrl(String s) {
		if (s != null) {

			s = s.replaceAll("&nbsp;", " ");
			s = s.replaceAll("&amp;", "&");
			s = s.replaceAll("&quot;", "\"");
			s = s.replaceAll("&apos;", "'");
			s = s.replaceAll("&gt;", ">");
			s = s.replaceAll("&lt;", "<");
			// 区首尾空格
			s = s.trim();
		}
		return s;
	}

	/**
	 * 过滤字符串中所有html代码
	 * 
	 * @param s
	 * @return
	 */
	public static synchronized String filterAllHTML(String s) {

		if (s != null) {
			s = s.replaceAll("\\s+", " ");// 去掉多余的空格和回车换行
			s = s.replaceAll("<.*?>", "");
			// 处理全角空格
			s = s.replaceAll("　+", " ");
			// 去多个空合格
			s = s.replaceAll("&nbsp;", " ");
			s = s.replaceAll("\\s+", " ");
			s = s.replaceAll("·", ",");

			// 最后处理转移
			s = s.replaceAll("&amp;", "&");
			s = s.replaceAll("&quot;", "\"");
			s = s.replaceAll("&apos;", "'");
			s = s.replaceAll("&gt;", ">");
			s = s.replaceAll("&lt;", "<");
			// 区首尾空格
			s = s.trim();
		}
		return s;
	}

	/**
	 * 获取网站域名
	 * 
	 * @param url
	 * @return
	 */
	public static synchronized String getDomain(String url) {
		if (url == null)
			return null;
		String first = "";

		// 协议头
		int pos = url.indexOf("://");
		if (pos >= 0) {
			first = url.substring(0, pos + 3);
			url = url.substring(pos + 3);
		}

		// 第一个路径
		pos = url.indexOf("/");
		if (pos >= 0)
			url = url.substring(0, pos);
		pos = url.indexOf("?");
		if (pos >= 0)
			url = url.substring(0, pos);
		return first + url;
	}

	/**
	 * 获取连接
	 * 
	 * @param url
	 * @param relativeUrl
	 * @return
	 */
	public static synchronized String getPath(String url, String relativeUrl) {
		if (relativeUrl == null)
			return null;
		if (url == null)
			return relativeUrl;
		if (relativeUrl.startsWith("/")) {
			return getDomain(url) + relativeUrl;
		} else if (relativeUrl.toLowerCase().startsWith("http:")
				|| relativeUrl.toLowerCase().startsWith("https:")) {
			return relativeUrl;
		} else if (relativeUrl.startsWith("?")) {
			int pos = url.indexOf("?");
			if (pos >= 0)
				return url.substring(0, pos) + relativeUrl;
			return url + relativeUrl;
		} else {
			// 协议头
			String first = "";
			int pos = url.indexOf("://");
			if (pos >= 0) {
				first = url.substring(0, pos + 3);
				url = url.substring(pos + 3);
			}
			// 取得参数名之前的地址
			int idx = url.indexOf('?');
			if (idx > 0)
				url = url.substring(0, idx);
			// 取路径
			idx = url.lastIndexOf('/');
			if (idx > 0)
				url = url.substring(0, idx);

			int flag = 3;
			// 处理../或./
			while (flag != 0) {
				if (relativeUrl.startsWith("../"))
					flag = 3;
				else if (relativeUrl.startsWith("./"))
					flag = 2;
				else
					flag = 0;
				if (flag == 3) {
					pos = url.lastIndexOf('/');
					if (pos >= 1)
						url = url.substring(0, pos);
				}
				if (flag > 0)
					relativeUrl = relativeUrl.substring(flag);
			}
			// 相对路径
			return first + url + "/" + relativeUrl;
		}

	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url
	 *            the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
}
