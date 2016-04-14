package com.fd.util;

import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.beanutils.PropertyUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class XmlObjUtil {
	static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public static void outputObj2Xml(String outFilePath, Object data, String rootName) throws Exception {
		if (data == null) {
			return;
		}
		Document doc = DocumentHelper.createDocument();
		Element root = doc.addElement("DocRoot");
		root.addAttribute("type", "java.util.HashMap");
		
		appendObj(root, data, rootName);

		// 文件路径
		String xmlFileName = outFilePath;
		// 用文件输出流开始生成xml 文档
		org.dom4j.io.XMLWriter xmlWriter = new org.dom4j.io.XMLWriter(
				new FileOutputStream(xmlFileName));
		xmlWriter.write(doc);
		xmlWriter.close();
		TarUtil.compress(new File(xmlFileName));
	}
	
	private static void appendMap(Element parent, Map<String, Object> map) throws Exception {
		List<Prop> props = getMapKeys(map);
		for (Prop prop : props) {
			Object o = map.get(prop.name);
			appendObj(parent, o, prop.name);
		}
	}
	
	private static void appendList(Element element, List<Object> list) throws Exception {
		if (list == null || list.size() == 0) {
			return;
		}
		String name = list.get(0).getClass().getSimpleName();
		for (Object o : list) {
			appendObj(element, o, name);
		}
	}
	
	private static void appendObj(Element parent, Object data, String nodeName) throws Exception {
		Element element = parent.addElement(nodeName);
		String type = data.getClass().getName();
		element.addAttribute("type", type);
		if (type.equals("java.lang.String")
				|| type.equals("java.lang.Integer")
				|| type.equals("java.lang.Float")
				|| type.equals("java.lang.Boolean")
				|| type.equals("java.lang.Character")
				|| type.equals("java.lang.Short")
				|| type.equals("java.lang.Long")
				|| type.equals("java.lang.Byte")
				|| type.equals("java.lang.Double")
				|| type.equals("int")
				|| type.equals("long")
				|| type.equals("boolean")
				|| type.equals("float")
				|| type.equals("char")
				|| type.equals("byte")
				|| type.equals("double")) {
			// 基本类型
			element.setText(data==null?"":data+"");
			return;
		}
		if (type.equals("java.lang.StringBuffer")) {
			// StringBufffer
			element.setText(((StringBuffer)data).toString());
			return;
		}
		if (type.equals("java.util.Date")) {
			//　时间类型
			element.setText(dateFormat.format((Date)data));
			return;
		}
		if (data instanceof Map) {
			Map m = (Map)data;
			appendMap(element, m);
			return;
		}
		if (data instanceof List) {
			List l = (List) data;
			appendList(element, l);
			return;
		}
		List<Prop> props = getObjProps(data);
		for (Prop prop : props) {
			Object o = PropertyUtils.getProperty(data, prop.name);
			appendObj(element, o, prop.name);
		}
	}
	
	private static List<Prop> getMapKeys(Map<String, Object> map) {
		List<Prop> props = new ArrayList<Prop>();
		Set keySet = map.keySet();
		for (Object key : keySet) {
			Prop prop = new Prop();
			prop.name = (String)key;
			prop.type = map.get(key).getClass().getName();
			props.add(prop);
		}
		return props;
	}
	
	private static List<Prop> getObjProps(Object o) {
		List<Prop> props = new ArrayList<Prop>();
		PropertyDescriptor[] ps = PropertyUtils.getPropertyDescriptors(o.getClass());
		for (PropertyDescriptor p : ps) {
			String propName = p.getName();
			if ("class".equals(propName)) {
				continue;
			}
			try {
				String type = PropertyUtils.getPropertyType(o, propName).toString();
				Prop prop = new Prop();
				prop.name = propName;
				prop.type = type;
				props.add(prop);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return props;
	}
	
	public static Object importXml2Obj(String xmlFile, String mappingProperties) throws Exception {
		Properties properties = new Properties();
		properties.load(new FileInputStream(mappingProperties));
		SAXReader reader = new SAXReader();
		Document doc = reader.read(new FileInputStream(xmlFile));
		
		Element root = doc.getRootElement();
		Object obj = parseElement(root, properties);
		return obj;
//		Iterator elements = root.elementIterator();
//		while(elements.hasNext()) {
//			Element element = (Element) elements.next();
//			System.out.println(element.getName() + " - " + element.attributeValue("type") 
//					+ " - " + element.getText());
//		}
	}
	
	private static Object parseElement(Element parent, Properties properties) throws Exception {
		String type = parent.attributeValue("type");
		if (properties != null) {
			type = properties.getProperty(type);
		}
		Class c = Class.forName(type);
		
		Iterator elements = parent.elementIterator();
		if (type.equals("java.util.ArrayList")) {
			ArrayList list = (ArrayList)c.newInstance();
			while(elements.hasNext()) {
				Element element = (Element) elements.next();
				list.add(parseElement(element, properties));
			}
			return list;
		}
		if (type.equals("java.util.HashMap")) {
			HashMap map = (HashMap)c.newInstance();
			while(elements.hasNext()) {
				Element element = (Element) elements.next();
				String name = element.getName();
				map.put(name, parseElement(element, properties));
			}
			return map;
		}
		String value = parent.getText();
		if (type.equals("java.util.Date")) {
			Date d = dateFormat.parse(value);
			return d;
		}
		if (type.equals("int") || type.equals("java.lang.Integer")) {
			Integer i = new Integer(value);
			return i;
		}
		if (type.equals("byte") || type.equals("java.lang.Byte")) {
			Byte b = new Byte(value);
			return b;
		}
		if (type.equals("short") || type.equals("java.lang.Short")) {
			Short s = new Short(value);
			return s;
		}
		if (type.equals("long") || type.equals("java.lang.Long")) {
			Long l = new Long(value);
			return l;
		}
		if (type.equals("double") || type.equals("java.lang.Double")) {
			Double d = new Double(value);
			return d;
		}
		if (type.equals("float") || type.equals("java.lang.Float")) {
			Float f = new Float(value);
			return f;
		}
		if (type.equals("boolean") || type.equals("java.lang.Boolean")) {
			Boolean b = new Boolean(value);
			return b;
		}
		if (type.equals("java.lang.StringBuffer")) {
			StringBuffer sb = new StringBuffer();
			return sb;
		}
		if (type.equals("java.lang.String") || type.equals("char")
				|| type.equals("java.lang.Character")) {
			return value;
		}
		Object obj = c.newInstance();
		while(elements.hasNext()) {
			Element element = (Element) elements.next();
			String name = element.getName();
			
			Object val = parseElement(element, properties);
			try {
				PropertyUtils.setProperty(obj, name, val);
			} catch (Exception e) {
			}
		}
		return obj;
	}
	
	public static void main(String[] args) {
		try {
			String file = "d:/test.xml";
			String propFile = "d:/propMapping.properties";
			Object obj = XmlObjUtil.importXml2Obj(file, propFile);
			System.out.println(obj);
//			String rootName = "Products";
//			HashMap<String, Object> map = new HashMap<String, Object>();
//			map.put("name", "testMap");
//			map.put("index", 1);
//			map.put("createDate", new Date());
//			List<PageInfo> pages = new ArrayList<PageInfo>();
//			PageInfo p = new PageInfo();
//			p.setStartNo(1);
//			p.setEndNo(100);
//			p.setPageSize(10);
//			pages.add(p);
//			p = new PageInfo();
//			p.setStartNo(2);
//			p.setEndNo(200);
//			p.setPageSize(20);
//			pages.add(p);p = new PageInfo();
//			p.setStartNo(3);
//			p.setEndNo(300);
//			p.setPageSize(30);
//			pages.add(p);
//			map.put("pages", pages);
//			XmlObjUtil.outputObj2Xml(file, map, rootName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
class Prop {
	public String name;
	public String type;
}
