package com.fd.fashion.dict.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.fd.fashion.dict.bean.AttributeBean;
import com.fd.fashion.dict.bean.CatAttrBean;
import com.fd.fashion.dict.bean.CatConfigBean;
import com.fd.fashion.dict.manager.IDictManager;
import com.fd.util.AppContextUtil;
import com.fd.util.FileUtil;
import com.fd.util.RegexUtil;

public class AttributeImport {
	public static void main(String[] args) {
		try {
//			importAttributes();
			saveCatConfig();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * @throws Exception
	 * @throws UnsupportedEncodingException
	 * @throws FileNotFoundException
	 * @throws NumberFormatException
	 */
	private static void saveCatConfig() throws IOException, Exception,
			UnsupportedEncodingException, FileNotFoundException,
			NumberFormatException {
		File log = new File("E:\\aliconfigs.log");
		if (!log.exists()) {
			log.createNewFile();
		}
		String s = FileUtil.readFile(log);
		String logss[] = s.split("[\\r\\n]+");
		List<String> logs = new ArrayList<String>();
		for (String string : logss) {
			logs.add(string);
		}
		OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(log, true), "utf-8");
		PrintWriter writer = new PrintWriter(out);
		IDictManager dictManager = (IDictManager) AppContextUtil.getBean("dictManager");
		File root = new File("E:\\aliattrs\\");
		File files[] = root.listFiles();
		RegexUtil reg = new RegexUtil();
		for (File file : files) {
			String path = file.getPath();
			if (logs.contains(path)) {
				continue;
			}
			String catId = reg.getMatchedStr(path, "catId=(\\d+)", 1);
			catId = dictManager.getCatIdbySrcId(catId);
			String content = FileUtil.readFile(file);
//				String content = FileUtil.readFile("E:\\aliattrs\\catId=200000798.htm");
			System.out.println("read file: " + file.getPath());
			List<String> list = reg.getAllMatched(content, "=\\s*(\\[.*?\\]) ;", 1);
			JSONArray array = JSONArray.fromObject(list.get(2));
			for (Object o : array) {
				JSONObject jo = (JSONObject)o;
//						System.out.println(data.get("id") + " " + data.get("nodeType") + " " + data.get("value"));
				long pid = jo.getLong("pId") + 9000000000L;
				CatConfigBean catConf = new CatConfigBean();
				catConf.setCatId(catId);
				catConf.setAllowCustom(jo.getBoolean("allowCustomImage"));
				catConf.setAttrId(pid);
				if (jo.getBoolean("required")) {
					catConf.setShowStyle("required");
				}
				catConf.setShowType(jo.getString("showType"));
				
				AttributeBean att = new AttributeBean();
				att.setAttrId(pid);
				att.setValue(jo.getString("pNameEn"));
				att.setValueCn(jo.getString("pName"));
				att.setNoteType("name");
				dictManager.addAttribute(att);
				dictManager.addCatConfig(catConf);
				
				
				
				JSONArray subIds = jo.getJSONArray("pValueId");
				JSONArray subValues = jo.getJSONArray("pValueTitleEn");
				JSONArray subValueCns = jo.getJSONArray("pValueTitle");
				if (subIds != null) {
					for (int i=0; i<subIds.size(); i++) {
						long id = Long.valueOf(""+subIds.get(i)) + 9000000000L;;
						String value = (String) subValues.get(i);
						String valueCn = (String) subValueCns.get(i);
						CatConfigBean conf = new CatConfigBean();
						conf.setAttrId(id);
						conf.setCatId(catId);
						
						AttributeBean attr = new AttributeBean();
						attr.setAttrId(id);
						attr.setNoteType("value");
						attr.setValue(value);
						attr.setValueCn(valueCn);
						attr.setParentId(att.getAttrId());
						dictManager.addAttribute(attr);
						dictManager.addCatConfig(conf);
					}
				}
			}
			writer.println(file.getPath());
			writer.flush();
		}
		writer.close();
	}

	/**
	 * @throws Exception
	 * @throws UnsupportedEncodingException
	 * @throws FileNotFoundException
	 */
	private static void importAttributes() throws Exception,
			UnsupportedEncodingException, FileNotFoundException {
		File log = new File("E:\\aliattrs.log");
		String s = FileUtil.readFile(log);
		String logss[] = s.split("[\\r\\n]+");
		List<String> logs = new ArrayList<String>();
		for (String string : logss) {
			logs.add(string);
		}
		OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(log, true), "utf-8");
		PrintWriter writer = new PrintWriter(out);
		IDictManager dictManager = (IDictManager) AppContextUtil.getBean("dictManager");
		File root = new File("E:\\aliattrs\\");
		File files[] = root.listFiles();
		RegexUtil reg = new RegexUtil();
		for (File file : files) {
			String path = file.getPath();
			if (logs.contains(path)) {
				continue;
			}
			String catId = reg.getMatchedStr(path, "catId=(\\d+)", 1);
			catId = dictManager.getCatIdbySrcId(catId);
			String content = FileUtil.readFile(file);
//			String content = FileUtil.readFile("E:\\ffff.txt");
			System.out.println("read file: " + file.getPath());
			List<String> list = reg.getAllMatched(content, "=\\s*(\\[.*?\\]) ;", 1);
			JSONArray array = JSONArray.fromObject(list.get(1));
			for (Object o : array) {
				JSONObject jo = (JSONObject)o;
				JSONObject data = jo.getJSONObject("data");
//					System.out.println(data.get("id") + " " + data.get("nodeType") + " " + data.get("value"));
				AttributeBean att = saveAttribute(dictManager, reg, data);
				saveCatAttr(dictManager, catId, data, att);
				JSONArray nodes = (JSONArray) jo.get("nodes");
				if (nodes != null) {
					for (Object obj : nodes) {
						JSONObject joo = (JSONObject)obj;
						JSONObject subData = joo.getJSONObject("data");
//							System.out.println("   - " + subData.get("id") + " " + subData.get("nodeType") + " " + subData.get("value"));
						if (StringUtils.isNotEmpty(subData.getString("id"))) {
							AttributeBean attr = saveSubAttribute(dictManager, reg, att, subData);
							saveCatAttr(dictManager, catId, subData, attr);
						}
					}
				}
			}
			writer.println(file.getPath());
			writer.flush();
		}
		writer.close();
	}

	/**
	 * @param dictManager
	 * @param catId
	 * @param data
	 * @param att
	 * @throws Exception
	 */
	private static void saveCatAttr(IDictManager dictManager, String catId,
			JSONObject data, AttributeBean att) throws Exception {
		CatAttrBean catAtt = new CatAttrBean();
		catAtt.setCatId(catId);
		catAtt.setAttrId(att.getAttrId());
		catAtt.setShowStyle((String)data.get("showStyle"));
		catAtt.setShowType((String)data.get("showType"));
		dictManager.addCatAttr(catAtt);
	}

	/**
	 * @param dictManager
	 * @param reg
	 * @param att
	 * @param subData
	 * @throws Exception
	 */
	private static AttributeBean saveSubAttribute(IDictManager dictManager,
			RegexUtil reg, AttributeBean att, JSONObject subData)
			throws Exception {
		AttributeBean attr = new AttributeBean();
		attr.setAttrId(subData.getLong("id"));
		attr.setNoteType(subData.getString("nodeType"));
		attr.setParentId(att.getAttrId());
		setValue(subData, reg, attr);
		dictManager.addAttribute(attr);
		return attr;
	}

	/**
	 * @param dictManager
	 * @param reg
	 * @param data
	 * @return
	 * @throws Exception
	 */
	private static AttributeBean saveAttribute(IDictManager dictManager,
			RegexUtil reg, JSONObject data) throws Exception {
		AttributeBean att = new AttributeBean();
		att.setAttrId(data.getLong("id"));
		att.setNoteType(data.getString("nodeType"));
		setValue(data, reg, att);
		dictManager.addAttribute(att);
		return att;
	}
	
	private static void setValue(JSONObject data, RegexUtil reg, AttributeBean att) {
		String value = data.getString("value");
		List<String> values = reg.getAllMatched(value, "([^\\(\\)]+)", 1);
		if (values.size()>1) {
			att.setValue(values.get(0));
			att.setValueCn(values.get(1));
		} else {
			att.setValueCn(values.get(0));
		}
	}
}
