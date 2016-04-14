package com.fd.util;

public class ReplaceParam {
	private String replaceRegex;
	
	private String replaceStr;
	
	public ReplaceParam() {
	}
	
	public ReplaceParam(String replaceRegex, String replaceStr) {
		this.replaceRegex = replaceRegex;
		this.replaceStr = replaceStr;
	}

	public String getReplaceRegex() {
		return replaceRegex;
	}

	public void setReplaceRegex(String replaceRegex) {
		this.replaceRegex = replaceRegex;
	}

	public String getReplaceStr() {
		return replaceStr;
	}

	public void setReplaceStr(String replaceStr) {
		this.replaceStr = replaceStr;
	}
}
