package com.fd.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPOutputStream;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLResult;
import org.dom4j.io.XMLWriter;

@SuppressWarnings({"rawtypes","unchecked"})
public class FileUtil {
	public static final String DEFAULT_CHARSET = "UTF-8";
	
	/**
	 * 读取文件
	 * 
	 * @param file
	 *            文件全路径
	 * @param charset
	 *            字符编码
	 * @return
	 * @throws Exception
	 */
	public static String readFile(File file, String charset) throws Exception {
		if (file == null) {
			return null;
		}
		if (!file.exists()) {
			throw new Exception("file not exists - " + file.getPath());
		}
		if (charset == null) {
			charset = DEFAULT_CHARSET;
		}
		
		InputStreamReader in = new InputStreamReader(new FileInputStream(file), charset);
		BufferedReader reader = new BufferedReader(in);
		StringBuffer sb = new StringBuffer();
		String str = reader.readLine();
		
		while (str != null) {
			sb.append(str);
			sb.append("\r\n");
			str = reader.readLine();
		}
		reader.close();
		
		return sb.toString();
	}
	
	/**
	 * 读取文件
	 * 
	 * @param path
	 *            文件全路径
	 * @param charset
	 *            字符编码
	 * @return
	 * @throws Exception
	 */
	public static String readFile(String path, String charset) throws Exception {
		return readFile(new File(path), charset);
	}
	
	/**
	 * 读取文件
	 * 
	 * @param path
	 *            文件全路径
	 * @return
	 * @throws Exception
	 */
	public static String readFile(String path) throws Exception {
		return readFile(new File(path), null);
	}
	
	/**
	 * 读取文件
	 * 
	 * @param file
	 *            文件
	 * @return
	 * @throws Exception
	 */
	public static String readFile(File file) throws Exception {
		return readFile(file, null);
	}
	
	/**
	 * 写文件
	 * 
	 * @param src
	 *            写入字符串
	 * @param path
	 *            文件全路径
	 * @param charset
	 *            字符编码
	 * @throws Exception
	 */
	public static void writeFile(String src, File tarFile, String charset, boolean append) throws Exception {
		if (src == null || tarFile == null) {
			return;
		}
		if (!tarFile.getParentFile().exists()) {
			tarFile.getParentFile().mkdirs();
		}
		if (charset == null) {
			charset = DEFAULT_CHARSET;
		}
		
		OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(tarFile, append), charset);
		BufferedWriter writer = new BufferedWriter(out);
		
		writer.write(src);
		writer.close();
	}
	
	/**
	 * 写文件
	 * 
	 * @param src
	 *            写入字符串
	 * @param path
	 *            文件全路径
	 * @param charset
	 *            字符编码
	 * @throws Exception
	 */
	public static void writeFile(String src, String path, String charset, boolean append) throws Exception {
		writeFile(src, new File(path), charset, append);
	}
	
	/**
	 * 写文件
	 * 
	 * @param src
	 *            写入字符串
	 * @param path
	 *            文件全路径
	 * @throws Exception
	 */
	public static void writeFile(String src, String path, boolean append) throws Exception {
		writeFile(src, new File(path), null, append);
	}
	
	/**
	 * 写文件
	 * 
	 * @param src
	 *            写入字符串
	 * @param file
	 *            文件
	 * @throws Exception
	 */
	public static void writeFile(String src, File file, boolean append) throws Exception {
		writeFile(src, file, null, append);
	}
	
	/**
	 * 写文件
	 * 
	 * @param src
	 *            写入字符串
	 * @param path
	 *            文件全路径
	 * @param charset
	 *            字符编码
	 * @throws Exception
	 */
	public static void writeFile(String src, File tarFile, String charset) throws Exception {
		writeFile(src, tarFile, charset, false);
	}
	
	/**
	 * 写文件
	 * 
	 * @param src
	 *            写入字符串
	 * @param path
	 *            文件全路径
	 * @param charset
	 *            字符编码
	 * @throws Exception
	 */
	public static void writeFile(String src, String path, String charset) throws Exception {
		writeFile(src, new File(path), charset);
	}
	
	/**
	 * 写文件
	 * 
	 * @param src
	 *            写入字符串
	 * @param path
	 *            文件全路径
	 * @throws Exception
	 */
	public static void writeFile(String src, String path) throws Exception {
		writeFile(src, new File(path), null);
	}
	
	/**
	 * 写文件
	 * 
	 * @param src
	 *            写入字符串
	 * @param file
	 *            文件
	 * @throws Exception
	 */
	public static void writeFile(String src, File file) throws Exception {
		writeFile(src, file, null);
	}

	/**
	 * 
	 * 
	 * @param FileName
	 * @throws Exception
	 */
	public static void createGZ(String FileName) throws Exception {
		// Create the GZIP output stream
		String outFilename = FileName + ".gz";
		GZIPOutputStream out = new GZIPOutputStream(new FileOutputStream(
				outFilename));

		// Open the input file
		String inFilename = FileName;
		FileInputStream in = new FileInputStream(inFilename);

		// Transfer bytes from the input file to the GZIP output stream
		byte[] buf = new byte[1024];
		int len;
		while ((len = in.read(buf)) > 0) {
			out.write(buf, 0, len);
		}
		in.close();

		// Complete the GZIP file
		out.finish();
		out.close();
	}

	public static void appendNode(List<String> listFile, String fileName, Date date, boolean clear) {
		try {
			SAXReader reader = new SAXReader();
			Document root = reader.read(new InputStreamReader(new FileInputStream(fileName), "utf-8"));
			Element info = root.getRootElement();
			List list = info.elements();
			if (clear) {
				list.clear();
			}
			XMLResult result = new XMLResult(new OutputStreamWriter(new FileOutputStream(fileName), "utf-8"));
			XMLWriter writer = result.getXMLWriter();
			for (String url : listFile) {
				Element sitemap = info.addElement("sitemap");
				Element loc = sitemap.addElement("loc");
				Element lastmod = sitemap.addElement("lastmod");
				loc.setText(url);
				lastmod.setText(StringUtil.getDateString(date));

				list.remove(sitemap);
				list.add(0, sitemap);
			}
			writer.write(root);
			writer.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * 按字节长度截取字符串(支持截取带HTML代码样式的字符串)，当字符串超出规定长度，添加候补字符串
	 * 
	 * @param param 将要截取的字符串参数
	 * @param length 截取的字节长度
	 * @param width 折行宽度，为0不添加 br 标识
	 * @param end 字符串末尾补上的字符串
	 * 例子：StringUtil.subStringHTML( "aaaaaabbbbddzz<span>adfdsfdsadfasd</span>ddeeewwwwsssscccccxxxxzzz", 40, 0, "...");
	 * @return 返回截取后的字符串
	 */
	public static String subStringHTML(String param, int length, int width, String end) {
		StringBuffer result = new StringBuffer();
		String dbresult = new String();
		int n = 0;
		char temp;
		boolean isCode = false; // 是不是HTML代码
		boolean isAddEnd = false;//判断是否要添加候补字符串
		
		for (int i = 0; i < param.length(); i++) {
			temp = param.charAt(i);
			
			if (temp == '<') {
				isCode = true;
			} else if (temp == '>' && isCode) {
				n = n - 1;
				isCode = false;
			}
			boolean isAddBr = false;
			if(width > 0  && !isCode  && temp != '>' && n%width == (width-1)) isAddBr = true;
			if (!isCode ) {
				n = n + 1;
				// UNICODE码字符占两个字节
				if ((temp + "").getBytes().length > 1) {
					n = n + 1;
				}
			}

			result.append(temp);
			
			if(n == (length-end.length())){
				dbresult = result.toString();
//				System.out.println("result:" + dbresult);
			}
			if(isAddBr) result.append("<br>");
			if (n >= length) {
				isAddEnd = true;
				result = new StringBuffer(dbresult);
//				System.out.println("result:" + result);
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
		if(isAddEnd){
			result.append(end);
		}
		return result.toString();
	}
	
	/**
	 * 截取长度，保持单词完整
	 * 
	 * @param str 
	 * @param length 
	 * @return
	 */
	public static String trimWords(String str, int length, String split) {
		if (StringUtil.isEmpty(str)) {
			return "";
		}
		String s = str;
		try {
			if (s.length() > length) {
				s = s.substring(0, s.lastIndexOf(split,
						length));
			}
			if (s.endsWith(split)) {
				s = s.substring(0, s.length()-1);
			}
			return s.trim();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}

	public static void main(String[] args) {
		try {
			String p = "E:\\Incoming\\[变形金刚3].Transformers.Dark.Of.The.Moon.2011.1080p.BluRay.x264-SECTOR7.(ED2000.COM).srt";
			String c = FileUtil.readFile(p, "big5");
			FileUtil.writeFile(c, p, "gbk");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
