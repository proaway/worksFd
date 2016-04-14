package com.fd.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

public class ReplaceUtil {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ReplaceUtil.class);

	RegexUtil regexUtil = new RegexUtil();
	
	public void replaceFile(File root, String fileRegex, String replaceRegex, String replaceStr, File outRoot) {
		if (root == null) {
			return;
		}
		if (!root.exists()) {
			throw new RuntimeException("file not exists - " + root);
		}
		String srcRootPath = root.getPath().replaceAll("\\\\", "/");
		String outRootPath = outRoot.getPath().replaceAll("\\\\", "/");;
		List<File> dirs = new ArrayList<File>();
		if (root.isFile()) {
			execReplaceFile(root, replaceRegex, replaceStr, outRoot);
			return;
		}
		if (root.isDirectory()) {
			dirs.add(root);
		}
		while (dirs.size()>0) {
			File dir = dirs.remove(0);
			File[] subFiles = dir.listFiles();
			for (File file : subFiles) {
				if (file.isDirectory()) {
					dirs.add(file);
				} else {
					String name = file.getName();
					if (regexUtil.isMatch(name, fileRegex)) {
						String filePath = file.getPath().replaceAll("\\\\", "/");
						filePath = filePath.replaceAll(srcRootPath, outRootPath);
						execReplaceFile(file, replaceRegex, replaceStr, new File(filePath));
						logger.info("replace file: " + filePath);
					}
				}
			}
		}
	}
	
	public void execReplaceFile(File srcFile, String regex, String replace, File tarFile) {
		List<ReplaceParam> params = new ArrayList<ReplaceParam>();
		ReplaceParam param = new ReplaceParam();
		param.setReplaceRegex(regex);
		param.setReplaceStr(replace);
		params.add(param);
		execReplaceFile(srcFile, tarFile, params);
	}
	
	public void execReplaceFile(File srcFile, File tarFile, List<ReplaceParam> params) {
		try {
			String content = FileUtil.readFile(srcFile);
			for (ReplaceParam param : params) {
				content = replaceAll(content, param.getReplaceRegex(), param.getReplaceStr());
			}
			FileUtil.writeFile(content, tarFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String replaceAll(String content, String regex, String replace) {
		Pattern pattern = Pattern.compile(regex, Pattern.DOTALL|Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(content);
		content = matcher.replaceAll(replace);
		return content;
	}
	
	public String replaceAll(String content, ReplaceParam param) {
		return replaceAll(content, param.getReplaceRegex(), param.getReplaceStr());
	}
	
	public String replaceAll(String content, List<ReplaceParam> params) {
		for (ReplaceParam param : params) {
			content = replaceAll(content, param.getReplaceRegex(), param.getReplaceStr());
		}
		return content;
	}
	
	public boolean isMatch(String content, String regex) {
		Pattern pattern = Pattern.compile(regex, Pattern.DOTALL|Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(content);
		return matcher.find();
	}
	
	public static void main(String[] args) {
		File src = new File("D:\\Apache2.2\\htdocs\\cz\\index.shtml");
		File out = new File("D:\\index.shtml");
		String fileRegex = ".*\\.s?html$";
		String replaceRegex = "<!--Start Bottom-->.*?<!--End Bottom-->";
		String replaceStr = "<!--start bottom--><!--#include virtual=\"/inc/foot.html\" --><!--end bottom-->";
		ReplaceUtil replace = new ReplaceUtil();
		replace.replaceFile(src, fileRegex, replaceRegex, replaceStr, out);
	}
}
