package com.fd.util;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

public class ClassCreator {
	String regTable = "create table ([^\\r\\n]+).*? comment '([^\"']+?)';";
	String regLong = "bigint";
	String regInteger = "int";
	String regString = "varchar";
	String regDate = "date";
	String regFloat = "float";
	String regDouble = "double";
//	String regProp = "   [^\\r\\n,]+,";
	String regPropDetail = "\\s+([^\\s]+)\\s+([^\\s]+)\\s+(not null |default \\d+ )?(auto_increment comment|comment)\\s+'([^']+)',";
	
	String classPath = "D:\\work\\FDCommon\\src\\com\\fd\\util\\";
	String classPackage = "com.fd.fashion.";
	String sqlmapPath = "D:\\work\\FDCommon\\conf\\sqlMap\\";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			ClassCreator c = new ClassCreator();
			c.mergeService("D:\\work\\FDSeller\\src\\com\\fd\\fashion\\seller\\service", "com.fd.fashion.seller.service", "SellerService");
			//		c.run();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void mergeService(String path, String packageName, String className) throws Exception {
		String interfaceBodyReg = "public (interface|class)[^\\r\\n]*\\{(.*?)\\}\\s*$";
		String interfaceImportReg = "(import[^\\r\\n]+;)";
		RegexUtil reg = new RegexUtil();
		File root = new File(path);
		File[] files = root.listFiles();
		String interfaceName = "I" + className;
		StringBuffer inetrfaceHead = new StringBuffer();
		inetrfaceHead.append("package ").append(packageName).append(";\r\n");
		StringBuffer inetrfaceBody = new StringBuffer();
		
		StringBuffer classHead = new StringBuffer();
		classHead.append("package ").append(packageName).append(";\r\n");
		StringBuffer classBody = new StringBuffer();
		for (File file : files) {
			if (file.isDirectory()) {
				continue;
			}
			String content = FileUtil.readFile(file);
			String body = reg.getMatchedStr(content, interfaceBodyReg, 0);
			List<String> importStrs = reg.getAllMatched(content, interfaceImportReg, 1);
			for (String str : importStrs) {
				inetrfaceHead.append(str).append("\r\n");
				classHead.append(str).append("\r\n");
			}
			if (content.contains("public interface")) {
				inetrfaceBody.append(body).append("\r\n");
			} else {
				classBody.append(body).append("\r\n");
			}
		}
		inetrfaceHead.append("public interface "+interfaceName+" {\r\n");
		inetrfaceHead.append(inetrfaceBody);
		inetrfaceHead.append("}");
		classHead.append("@Component\r\n");
		classHead.append("public class "+className+" implements "+interfaceName+" {\r\n");
		classHead.append(classBody);
		classHead.append("}");
		File interfaceFile = new File(root, interfaceName + ".java");
		FileUtil.writeFile(inetrfaceHead.toString(), interfaceFile);
		File classFile = new File(root, className + ".java");
		FileUtil.writeFile(classHead.toString(), classFile);
	}
	
	public void run() {
		try {
			RegexUtil reg = new RegexUtil();
			URL url = ClassCreator.class.getClassLoader().getResource(
					"FASHION.sql");
			String content = FileUtil.readFile(url.getPath(), "gb2312");
			List<String> tables = reg.getAllMatched(content, regTable, 0);
			for (String table : tables) {
				Table t = new Table();
				t.name = reg.getMatchedStr(table, regTable, 1);
				t.className = getJavaName(t.name);
				t.className = t.className.replaceAll("^t(Sys|c)?", "") + "Bean";
				t.comment = reg.getMatchedStr(table, regTable, 2).replaceAll("表", "");
				List<Prop> props = new ArrayList<ClassCreator.Prop>();
				List<String> propstrs = reg.getAllMatched(table, regPropDetail, 0);
				for (String prop : propstrs) {
					Prop p = new Prop();
//					System.out.println(prop);
					p.dbName = reg.getMatchedStr(prop, regPropDetail, 1);
					p.type = reg.getMatchedStr(prop, regPropDetail, 2);
					if (reg.isMatch(p.type, regLong)) {
						p.type = "Long";
					} else if (reg.isMatch(p.type, regInteger)) {
						p.type = "Integer";
					} else if (reg.isMatch(p.type, regString)) {
						p.type = "String";
					} else if (reg.isMatch(p.type, regDate)) {
						p.type = "Date";
					} else if (reg.isMatch(p.type, regFloat)) {
						p.type = "Float";
					} else if (reg.isMatch(p.type, regDouble)) {
						p.type = "Double";
					}
					p.memo = reg.getMatchedStr(prop, regPropDetail, 4);
					p.comment = reg.getMatchedStr(prop, regPropDetail, 5);
					p.javaName = getJavaName(p.dbName);
					props.add(p);
				}
				t.props = props;
				makeBean(t);
				makeIService(t);
				makeService(t);
				makeIbatisXml(t);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void makeService(Table t) throws Exception {
		String className = t.className.replaceAll("Bean", "Service");
		String paramName = getJavaName(t.name.replaceAll("T_(SYS_)?", ""));
		String servicePath = classPath + "service/" + className + ".java";
		StringBuffer sb = new StringBuffer();
		sb.append("package " + classPackage + "service;\r\n");
		sb.append("import java.util.List;\r\n");
		sb.append("import com.fd.util.PageInfo;\r\n");

		sb.append("/** "+t.comment+" */");
		sb.append("@Component");
		sb.append("@SuppressWarnings(\"unchecked\")");
		
		sb.append("public class "+className+" implements I" + className + " {");
		sb.append("	@Autowired\r\n");
		sb.append("	@Qualifier(\"dao\")\r\n");
		sb.append("	private IBaseDao dao;\r\n");
		
		sb.append("	/**\r\n");
		sb.append("	 * 增加"+t.comment+"\r\n");
		sb.append("	 * \r\n");
		sb.append("	 * @param "+paramName+"\r\n");
		sb.append("	 * @return\r\n");
		sb.append("	 * @throws Exception\r\n");
		sb.append("	 */\r\n");
		sb.append("	public "+t.className+" insert"+t.className+"("+t.className+" "+paramName+") throws Exception {\r\n");
		sb.append("		dao.insertObj(\"insert"+t.className+"\", "+paramName+");\r\n");
		sb.append("		return "+paramName+";\r\n");
		sb.append("	}\r\n");
		sb.append("	\r\n");
		sb.append("	/**\r\n");
		sb.append("	 * 修改"+t.comment+"\r\n");
		sb.append("	 * \r\n");
		sb.append("	 * @param "+paramName+"\r\n");
		sb.append("	 * @return\r\n");
		sb.append("	 * @throws Exception\r\n");
		sb.append("	 */\r\n");
		sb.append("	public int update"+t.className+"("+t.className+" "+paramName+") throws Exception {\r\n");
		sb.append("		return dao.updateObj(\"update"+t.className+"\", "+paramName+");\r\n");
		sb.append("	}\r\n");
		sb.append("	\r\n");
		sb.append("	/**\r\n");
		sb.append("	 * 获取"+t.comment+"\r\n");
		sb.append("	 * \r\n");
		sb.append("	 * @param "+paramName+"\r\n");
		sb.append("	 * @return\r\n");
		sb.append("	 * @throws Exception\r\n");
		sb.append("	 */\r\n");
		sb.append("	public List<"+t.className+"> get"+t.className+"s("+t.className+" "+paramName+") throws Exception {\r\n");
		sb.append("		return dao.getAsList(\"get"+t.className+"\", "+paramName+");\r\n");
		sb.append("	}\r\n");
		sb.append("	\r\n");
		sb.append("	/**\r\n");
		sb.append("	 * 获取"+t.comment+"\r\n");
		sb.append("	 * \r\n");
		sb.append("	 * @param "+paramName+"\r\n");
		sb.append("	 * @return\r\n");
		sb.append("	 * @throws Exception\r\n");
		sb.append("	 */\r\n");
		sb.append("	public List<"+t.className+"> get"+t.className+"s("+t.className+" "+paramName+", PageInfo pageInfo) throws Exception {\r\n");
		sb.append("		Integer count = (Integer) dao.getAsObject(\"get"+t.className+"Count\", "+paramName+");\r\n");
		sb.append("		pageInfo.setRecordCount(count == null ? 0 : count);\r\n");
		sb.append("		if (count != null && count>0) {\r\n");
		sb.append("			return dao.getAsList(\"get"+t.className+"\", "+paramName+", pageInfo);\r\n");
		sb.append("		}\r\n");
		sb.append("		return null;\r\n");
		sb.append("	}\r\n");
		sb.append("	\r\n");
		sb.append("	/**\r\n");
		sb.append("	 * 删除"+t.comment+"\r\n");
		sb.append("	 * \r\n");
		sb.append("	 * @param "+paramName+"\r\n");
		sb.append("	 * @return\r\n");
		sb.append("	 * @throws Exception\r\n");
		sb.append("	 */\r\n");
		sb.append("	public int delete"+t.className+"("+t.className+" "+paramName+") throws Exception {\r\n");
		sb.append("		return dao.deleteObj(\"delete"+t.className+"\", "+paramName+");\r\n");
		sb.append("	}\r\n");

		sb.append("}");
		FileUtil.writeFile(sb.toString(), servicePath);
	}
	
	private void makeIService(Table t) throws Exception {
		String className = t.className.replaceAll("Bean", "Service");
		String paramName = getJavaName(t.name.replaceAll("T_(SYS_)?", ""));
		String interfacePath = classPath + "service/I" + className + ".java";
		StringBuffer sb = new StringBuffer();
		sb.append("package " + classPackage + "service;\r\n");
		sb.append("import java.util.List;\r\n");
		sb.append("import com.fd.util.PageInfo;\r\n");

		sb.append("/** "+t.comment+"接口 */\r\n");
		sb.append("public interface I" + className + " {\r\n");
		
		sb.append("	/**\r\n");
		sb.append("	 * 增加"+t.comment+"\r\n");
		sb.append("	 * \r\n");
		sb.append("	 * @param "+paramName+"\r\n");
		sb.append("	 * @return\r\n");
		sb.append("	 * @throws Exception\r\n");
		sb.append("	 */\r\n");
		sb.append("	public "+t.className+" insert"+t.className+"("+t.className+" "+paramName+") throws Exception;\r\n");
		sb.append("	\r\n");
		sb.append("	/**\r\n");
		sb.append("	 * 修改"+t.comment+"\r\n");
		sb.append("	 * \r\n");
		sb.append("	 * @param "+paramName+"\r\n");
		sb.append("	 * @return\r\n");
		sb.append("	 * @throws Exception\r\n");
		sb.append("	 */\r\n");
		sb.append("	public int update"+t.className+"("+t.className+" "+paramName+") throws Exception;\r\n");
		sb.append("	\r\n");
		sb.append("	/**\r\n");
		sb.append("	 * 获取"+t.comment+"\r\n");
		sb.append("	 * \r\n");
		sb.append("	 * @param "+paramName+"\r\n");
		sb.append("	 * @return\r\n");
		sb.append("	 * @throws Exception\r\n");
		sb.append("	 */\r\n");
		sb.append("	public List<"+t.className+"> get"+t.className+"s("+t.className+" "+paramName+") throws Exception;\r\n");
		sb.append("	\r\n");
		sb.append("	/**\r\n");
		sb.append("	 * 获取"+t.comment+"\r\n");
		sb.append("	 * \r\n");
		sb.append("	 * @param "+paramName+"\r\n");
		sb.append("	 * @return\r\n");
		sb.append("	 * @throws Exception\r\n");
		sb.append("	 */\r\n");
		sb.append("	public List<"+t.className+"> get"+t.className+"s("+t.className+" "+paramName+", PageInfo pageInfo) throws Exception;\r\n");
		sb.append("	\r\n");
		sb.append("	/**\r\n");
		sb.append("	 * 删除"+t.comment+"\r\n");
		sb.append("	 * \r\n");
		sb.append("	 * @param "+paramName+"\r\n");
		sb.append("	 * @return\r\n");
		sb.append("	 * @throws Exception\r\n");
		sb.append("	 */\r\n");
		sb.append("	public int delete"+t.className+"("+t.className+" "+paramName+") throws Exception;\r\n");

		sb.append("}");
		FileUtil.writeFile(sb.toString(), interfacePath);
	}
	
	private void makeBean(Table t) throws Exception {
		String path = classPath + "bean/" + t.className + ".java";
		StringBuffer sb = new StringBuffer();
		sb.append("package " + classPackage + "bean;\r\n");
		sb.append("import java.util.Date;\r\n");
		//sb.append("import com.fd.bean.AbstractBean;\r\n");
		sb.append("/** "+t.comment+" */");
		sb.append("public class " + t.className + " {");
		
		for (Prop p : t.props) {
			sb.append("\r\n");
			sb.append("/** " + p.comment + "*/\r\n");
			sb.append("private " + p.type + " " + p.javaName + ";");
		}
		
		for (Prop p : t.props) {
			String jName = upcaseFirstChar(p.javaName);
			sb.append("\r\n");
			sb.append("/** " + p.comment + "*/\r\n");
			sb.append("public void set" + jName + "(" + p.type+ " " + p.javaName + ") {\r\n");
			sb.append("this." + p.javaName + " = " + p.javaName + ";");
			sb.append("}\r\n");
			sb.append("/** " + p.comment + "*/\r\n");
			sb.append("public " + p.type + " get" + jName + "() {");
			sb.append("return this." + p.javaName + ";");
			sb.append("}");
		}
		
		sb.append("}");
		FileUtil.writeFile(sb.toString(), path);
	}
	
	private void makeIbatisXml(Table t) throws Exception {
		String path = sqlmapPath + t.className + ".xml";
		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\r\n");
		sb.append("<!DOCTYPE sqlMap\r\n");
		sb.append("    PUBLIC \"-//ibatis.apache.org//DTD SQL Map 2.0//EN\"\r\n");
		sb.append("    \"http://ibatis.apache.org/dtd/sql-map-2.dtd\">\r\n");
		sb.append("<sqlMap>\r\n");
		// insert
		appendInsert(t, sb);
		sb.append("\r\n");
		// update
		appendUpdate(t, sb);
		sb.append("\r\n");
		// select
		appendSelect(t, sb);
		sb.append("\r\n");
		// delete
		appendDelete(t, sb);
		sb.append("</sqlMap>");
		FileUtil.writeFile(sb.toString(), path);
	}

	private void appendSelect(Table t, StringBuffer sb) {
		sb.append("    <sql id=\"get"+t.className+"Condition\">\r\n");
		for (int j = 0; j<t.props.size(); j++) {
			Prop p = t.props.get(j);
			sb.append("            <isNotNull property=\""+p.javaName+"\" prepend=\"and\" removeFirstPrepend=\"true\">\r\n");
			sb.append("                <![CDATA["+p.dbName+" = #"+p.javaName+"#]]>\r\n");
			sb.append("            </isNotNull>\r\n");
		}
		sb.append("    </sql>\r\n");
		sb.append("\r\n");
		sb.append("    <select id=\"get"+t.className+"Count\" parameterClass=\""+classPackage+t.className+"\"\r\n");
		sb.append("        resultClass=\"int\">\r\n");
		sb.append("        <![CDATA[\r\n");
		sb.append("select \r\n");
		sb.append("	count(0)\r\n");
		sb.append("from "+t.name+" \r\n");
		sb.append("        ]]>\r\n");
		sb.append("        <dynamic prepend=\"where\">\r\n");
		sb.append("            <include refid=\"get"+t.className+"Condition\"/>\r\n");
		sb.append("        </dynamic>\r\n");
		sb.append("    </select>\r\n");
		sb.append("\r\n");
		
		sb.append("    <select id=\"get"+t.className+"\" parameterClass=\""+classPackage+t.className+"\"\r\n");
		sb.append("        resultClass=\"package."+t.className+"\">\r\n");
		sb.append("        <![CDATA[\r\n");
		sb.append("select \r\n");
		for (int j = 0; j<t.props.size(); j++) {
			Prop p = t.props.get(j);
			sb.append("	"+p.dbName+"	as "+p.javaName);
			if (j < t.props.size() - 1) {
				sb.append(",");
			}
			sb.append("\r\n");
		}
		sb.append("from "+t.name+" \r\n");
		sb.append("        ]]>\r\n");
		sb.append("        <dynamic prepend=\"where\">\r\n");
		sb.append("            <include refid=\"get"+t.className+"Condition\"/>");
		sb.append("        </dynamic>\r\n");
		sb.append("    </select>\r\n");
	}

	private void appendDelete(Table t, StringBuffer sb) {
		sb.append("    <delete id=\"delete"+t.className+"\" parameterClass=\""+classPackage+t.className+"\">\r\n");
		sb.append("        <![CDATA[\r\n");
		sb.append("delete from "+t.name+" where "+t.props.get(0).dbName+"=#"+t.props.get(0).javaName+"#\r\n");
		sb.append("        ]]>\r\n");
		sb.append("    </delete>\r\n");
	}

	private void appendInsert(Table t, StringBuffer sb) {
		try {
			sb.append("    <insert id=\"insert" + t.className
					+ "\" parameterClass=\"" + classPackage + t.className
					+ "\">\r\n");
			sb.append("        <![CDATA[\r\n");
			sb.append("insert into " + t.name + "\r\n");
			sb.append("  (");
			for (int j = 0; j < t.props.size(); j++) {
				Prop p = t.props.get(j);
				if (p.memo.contains("auto_increment")) {
					continue;
				}
				sb.append(p.dbName);
				if (j < t.props.size() - 1) {
					sb.append(", ");
				}
			}
			sb.append(")\r\n");
			sb.append("values\r\n");
			sb.append("  (");
			for (int j = 0; j < t.props.size(); j++) {
				Prop p = t.props.get(j);
				if (p.memo.contains("auto_increment")) {
					continue;
				}
				sb.append("#" + p.javaName + "#");
				if (j < t.props.size() - 1) {
					sb.append(", ");
				}
			}
			sb.append(")\r\n");
			sb.append("        ]]>\r\n");
			sb.append("        <selectKey keyProperty=\""
					+ t.props.get(0).javaName + "\" resultClass=\"long\">\r\n");
			sb.append("            <![CDATA[select @@identity as "
					+ t.props.get(0).javaName + "]]>\r\n");
			sb.append("        </selectKey>\r\n");
			sb.append("    </insert>\r\n");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void appendUpdate(Table t, StringBuffer sb) {
		sb.append("    <update id=\"update"+t.className+"\" parameterClass=\""+classPackage+t.className+"\">\r\n");
		sb.append("        <![CDATA[\r\n");
		sb.append("update "+t.name+" \r\n");
		sb.append("        ]]>\r\n");
		sb.append("        <dynamic prepend=\"set\">\r\n");
		for (int j = 0; j<t.props.size(); j++) {
			Prop p = t.props.get(j);
			sb.append("            <isNotNull property=\""+p.javaName+"\" prepend=\",\" removeFirstPrepend=\"true\">\r\n");
			sb.append("                <![CDATA["+p.dbName+" = #"+p.javaName+"#]]>\r\n");
			sb.append("            </isNotNull>\r\n");
		}
		sb.append("        </dynamic>\r\n");
		sb.append("        <![CDATA[\r\n");
		sb.append("where "+t.props.get(0).dbName+" = #"+t.props.get(0).javaName+"#\r\n");
		sb.append("        ]]>\r\n");
		sb.append("    </update>\r\n");
	}
	
	private String upcaseFirstChar(String str) {
		StringBuffer sb = new StringBuffer();
		char c = str.charAt(0);
		String cc = String.valueOf(c);
		cc = cc.toUpperCase();
		sb.append(cc);
		sb.append(str.substring(1));
		return sb.toString();
	}
	
	private String getJavaName(String dbName) {
		StringBuffer sb = new StringBuffer();
		boolean up = false;
		for (int i = 0; i < dbName.length(); i++) {
			char c = dbName.charAt(i);
			if ('_' == c) {
				up = true;
				continue;
			}
			String cc = String.valueOf(c);
			if (up) {
				sb.append(cc.toUpperCase());
				up = false;
			} else {
				sb.append(cc.toLowerCase());
			}
		}
		return sb.toString();
	}

	class Prop{
		public String dbName;
		public String javaName;
		public String type;
		public String memo;
		public String comment;
	}
	
	class Table {
		public String name;
		public String className;
		public String comment;
		public List<Prop> props;
	}
}
