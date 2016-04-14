package com.fd.fashion.dict.vo;

import java.util.List;

public class CatAttrNode {
	private List<CatAttrNode> nodes;
	
	private CatAttrVo attr;
	
	private boolean checked = false;

	/**
	 * @return the nodes
	 */
	public List<CatAttrNode> getNodes() {
		return nodes;
	}

	/**
	 * @param nodes the nodes to set
	 */
	public void setNodes(List<CatAttrNode> nodes) {
		this.nodes = nodes;
	}

	/**
	 * @return the attr
	 */
	public CatAttrVo getAttr() {
		return attr;
	}

	/**
	 * @param attr the attr to set
	 */
	public void setAttr(CatAttrVo attr) {
		this.attr = attr;
	}

	/**
	 * @return the checked
	 */
	public boolean isChecked() {
		return checked;
	}

	/**
	 * @param checked the checked to set
	 */
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
}
