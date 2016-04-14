package com.fd.fashion.product.constants;

/**
 * @CreateDate: 2013-3-18 下午08:35:06
 * @author Longli
 * @Description: 产品常量，包括产品状态等
 * 
 */
public class ProductConstants {
	/** 产品状态 -1 - 永久下架，页面404 */
	public static int DELETE = -1;
	/** 产品状态 0 - 下架，页面显示“Out of stock” */
	public static int OFFSALE = 0;
	/** 产品状态 1 - 在销 */
	public static int ONSALE = 1;
	/** 产品状态 2 - 草稿，页面404 */
	public static int DRAFT = 2;
	/** 产品状态 3 - 批量导入，页面404 */
	public static int IMPORT = 3;
}
