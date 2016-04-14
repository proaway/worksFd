package com.fd.util;

import java.util.List;

/**
 * MIC所有平台获取commission的公共类
 * 
 * @since: Jan 02, 2009
 * @author: Alan
 * @company: shopmadeinchina.com
 */
@SuppressWarnings("unchecked")
public class CommissionUtil {/*
	private IDictManager dictManager = (IDictManager) AppContextUtil
			.getBean("dictManager");

	private ISellerRegManager sellerManager = (ISellerRegManager) AppContextUtil
			.getBean("sellerRegManager");

	private IProductManager productManager = (IProductManager) AppContextUtil
			.getBean("productManager");

	*//**
	 * 获取commission
	 * 
	 * @param commissionVo
	 * @return
	 * @throws Exception
	 *//*
	public Double getCommission(CommissionVO commissionVo) throws Exception {
		ProductVo product = commissionVo.getProduct();
		SellerVo seller = commissionVo.getSeller();
		if (product != null && product.getSellerCommissionDiscount() == null) {
			if (seller != null && seller.getCommissionDiscount() != null) {
				product.setSellerCommissionDiscount(seller
						.getCommissionDiscount());
			} else if (product.getSellerId() == null) {
				if (product.getProductId() == null
						&& product.getItemCode() == null) {
					throw new RuntimeException(
							"get commission error, cannot get seller commission discount, sellerId is null");
				} else {
					ProductVo productVo = productManager.getProductVo(product);
					if (productVo == null) {
						throw new RuntimeException("get commission error");
					}
					product = productVo;
				}
			} else {
				SellerVo sellerVo = new SellerVo();
				sellerVo.setSellerId(product.getSellerId());
				sellerVo = sellerManager.getSeller(sellerVo);
				product.setSellerCommissionDiscount(sellerVo
						.getCommissionDiscount());
			}
		}
		return calCommission(product, seller);
	}

	*//**
	 * 根据分类计算Commission
	 * 
	 * @param product
	 * @return
	 * @throws Exception
	 *//*
	public Double calCommission(ProductVo product, SellerVo seller) throws Exception {
		if (product == null) {
			if (seller != null && seller.getCommissionDiscount() != null) {
				return dictManager.getDefaultCommission() * seller.getCommissionDiscount() / 100.0;
			}
			return dictManager.getDefaultCommission();
		}
		CategoryVo cat = product.getCategory();
		if (cat == null) {
			String catId = product.getCatalogId();
			List<CategoryVo> cats = dictManager.getCategory(catId);
			if (cats != null && cats.size() > 0) {
				cat = cats.get(0);
				product.setCategory(cat);
			} else {
				throw new RuntimeException("get commission error, cannot get category! catId="
						+ catId);
			}
		}
		Double commission = null;
		while (cat.getCommission() == null && cat.getParent() != null) {
			cat = cat.getParent();
		}
		if (cat.getCommission() == null) {
			commission = dictManager.getDefaultCommission();
			commission = 1 + commission / 100
					* product.getSellerCommissionDiscount() / 100.0;
		} else {
			commission = 1 + cat.getCommission() / 100
					* product.getSellerCommissionDiscount() / 100.0;
		}
		return commission;
	}
*/
	public static Double calCommission(Double commission, Double total){
		return total/commission;
				
	}
}
