package com.fd.fashion.dict.vo;

import java.util.List;

public class CatConfNode {
	private List<CatConfNode> nodes;
	
	private CatConfigVo config;
	
	private boolean checked = false;

	/**
	 * @return the nodes
	 */
	public List<CatConfNode> getNodes() {
		return nodes;
	}

	/**
	 * @param nodes the nodes to set
	 */
	public void setNodes(List<CatConfNode> nodes) {
		this.nodes = nodes;
	}

	/**
	 * @return the config
	 */
	public CatConfigVo getConfig() {
		return config;
	}

	/**
	 * @param config the config to set
	 */
	public void setConfig(CatConfigVo config) {
		this.config = config;
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
