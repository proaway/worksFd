package com.fd.fashion.product.bean;

import java.io.Serializable;


/** 产品配置自定义图片 */
public class ProductConfImgBean implements Serializable {
	private static final long serialVersionUID = 9104641759030191991L;
	/** 图片配置ID */
	private Long configImgId;
	/** 配置Id */
	private Long productConfigId;
	/** 图片ID */
	private Long imageId;
	/** 图片显示顺序 */
	private Integer indexId;

	/** 图片配置ID */
	public void setConfigImgId(Long configImgId) {
		this.configImgId = configImgId;
	}

	/** 图片配置ID */
	public Long getConfigImgId() {
		return this.configImgId;
	}

	/** 配置Id */
	public void setProductConfigId(Long productConfigId) {
		this.productConfigId = productConfigId;
	}

	/** 配置Id */
	public Long getProductConfigId() {
		return this.productConfigId;
	}

	/** 图片ID */
	public void setImageId(Long imageId) {
		this.imageId = imageId;
	}

	/** 图片ID */
	public Long getImageId() {
		return this.imageId;
	}

	/** 图片显示顺序 */
	public void setIndexId(Integer indexId) {
		this.indexId = indexId;
	}

	/** 图片显示顺序 */
	public Integer getIndexId() {
		return this.indexId;
	}
}