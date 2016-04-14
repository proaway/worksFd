package com.fd.fashion.cms.util;

/**
 * 标签配置文件
 * 
 * @author mqr
 * 
 */
public class CmsTagsConfig {

	// <!-- begin 正则表达式 -->
	/**
	 * 栏目正则表达式
	 */
	public final static String BLOCK = "<!--CMS:\\s*Block.*?<!--CMS:\\s*Block\\s*End-->";
	public final static String BLICK_BEGIN = "<!--CMS:\\s*Block Name.*?-->";
	public final static String BLICK_END = "<!--CMS:\\s*Block\\s*End-->";
	/**
	 * Title 区域
	 */
	public final static String TITLEINFO = "<!--CMS:\\s*Title-->";
	/**
	 * Description 区域
	 */
	public final static String METADESC = "<!--CMS:\\s*Meta-Description-->";
	/**
	 * Keywords 区域
	 */
	public final static String METAKEYWORDS = "<!--CMS:\\s*Meta-Keywords-->";
	/**
	 * name属性正则表达式
	 */
	public final static String MICBLOCK_NAME = "<!--CMS:\\s*Block[^>]+?Name='(.*?)'";
	/**
	 * number属性正则表达式
	 */
	public final static String MICBLOCK_CODE = "<!--CMS:\\s*Block[^>]+?ItemNum=(.*?)-->";

	/**
	 * 信息标识
	 */
	public final static String BLOCKINFO = "<!--CMS:\\s*Item.*?<!--CMS:Item\\s*End-->";

	/**
	 * 信息类型
	 */
	public final static String BLOCKINFOTYPE = "<!--CMS:\\s*Item[^>]*?Type='(.*?)'";

	/** *********************START 标题**************** */
	// 标题：<!-- CMS: InfoTitle=40-->
	public static final String INFO_TITLE = "<!--\\s*CMS:\\s*InfoTitle=(\\d+)\\s*(DelLine)?-->";
	// 摘要：<!-- CMS: InfoSummary=40-->
	public static final String INFO_SUMMARY = "<!--\\s*CMS:\\s*InfoSummary=(\\d+)\\s*(DelLine)?-->";
	// 图片：<!--\\s*CMS:\\s*Img\\s*(.*?)\\s*-->
	public static final String INFO_IMG = "<!--\\s*CMS:\\s*Img\\s+(.*?)\\s*(DelLine)?-->";
	// 图片描述：<!--\\s*CMS:\\s*ImgAlt\\s*-->
	public static final String INFO_IMG_ALT = "<!--\\s*CMS:\\s*ImgAlt\\s*(DelLine)?-->";
	// 链接：<!--\\s*CMS:\\s*LinkURL\\s*-->
	public static final String INFO_LINK = "<!--\\s*CMS:\\s*LinkURL\\s*(DelLine)?-->";
	// 频道自身链接: <!-- CMS: EDM URL -->
	public static final String EDM_URL = "<!--\\s*CMS:\\s*EDM\\s*URL\\s*(DelLine)?-->";
	// 价格：<!-- CMS: Price -->
	public static final String INFO_PRICE = "<!--\\s*CMS:\\s*Price\\s*(DelLine)?-->";
	// 原价格标签：<!-- CMS: OriginalPrice -->
	public static final String INFO_ORIGINALPRICE = "<!--\\s*CMS:\\s*OriginalPrice\\s*(DelLine)?-->";
	// 批发价格：<!-- CMS: WholesalePrice -->
	public static final String INFO_WHOLESALERICE = "<!--\\s*CMS:\\s*WholesalePrice\\s*(DelLine)?-->";
	// 节省金额 <!-- CMS: SavePrice -->
	public static final String INFO_SAVEPRICE = "<!--\\s*CMS:\\s*SavePrice\\s*(DelLine)?-->";
	// 折扣
	public static final String INFO_DISCOUNT = "<!--\\s*CMS:\\s*ProductDiscount\\s*(DelLine)?-->";
	// 分类一级
	public static final String INFO_CATEGORY1 = "<!--\\s*CMS:\\s*Product\\s*Category\\s*Lv1\\s*(DelLine)?-->";
	// 分类二级
	public static final String INFO_CATEGORY2 = "<!--\\s*CMS:\\s*Product\\s*Category\\s*Lv2\\s*(DelLine)?-->";
	// 分类三级
	public static final String INFO_CATEGORY3 = "<!--\\s*CMS:\\s*Product\\s*Category\\s*Lv3\\s*(DelLine)?-->";
	// 分类四级
	public static final String INFO_CATEGORY4 = "<!--\\s*CMS:\\s*Product\\s*Category\\s*Lv4\\s*(DelLine)?-->";
	
	// 产品综合评分星星的标签  <!-- CMS: ProductStar -->
	public static final String INFO_PRODUCTSTART = "<!--\\s*CMS:\\s*ProductStar\\s*(DelLine)?-->";
	// 评价数标签：<!-- CMS: Reviews -->
	public static final String INFO_REVIEWS = "<!--\\s*CMS:\\s*Reviews\\s*(DelLine)?-->";
	// 评价描述节选标签： <!-- CMS: ReviewsDes=60 -->
	public static final String INFO_REVIEWSDES = "<!--\\s*CMS:\\s*ReviewsDes=(\\d+)\\s*(DelLine)?-->";
	// 订单数标签：<!-- CMS: Orders -->
	public static final String INFO_ORDERS = "<!--\\s*CMS:\\s*Orders\\s*(DelLine)?-->";
	// 是否为freeshipping 产品：<!-- CMS: Freeshipping -->
	public static final String INFO_FREESHIPPING = "<!--\\s*CMS:\\s*Freeshipping\\s*(DelLine)?-->";
}
