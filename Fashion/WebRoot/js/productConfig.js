function ProductConfig(prodId, productConfig) {
	// 初始化数据
	var standards = productConfig.standards;
	var mainShow = productConfig.mainShow;
	var mainConfigs = mainShow.configs;
	var subShow = productConfig.subShow;
	
	var mainShowTr = jQuery("#mainShowTr");
	var subShowTr = jQuery("#subShowTr");
	var mainUl = mainShowTr.find("ul");
	var subUl = subShowTr.find("ul");
	// 初始产品信息保存
	var srcImgUl = jQuery("#imageMenu").html();
	var srcImgBig = jQuery("#midimg").attr("src");
	var srcImgSrc = jQuery("#bigView").find("img").attr("src");
	var srcDiscountPrice = jQuery("#currDiscountPrice").html();
	var srcPrice = jQuery("#currPrice").html();
	var srcDiscount = jQuery("#discount").html();
	var srcSavePrice = jQuery("#savePrice").html();
	var srcSku = jQuery("#sku").html();
	var srcStockNum = jQuery("#stockNumber").html();
	
	var imageSet = false;
	var priceSet = false;
	var skuSet = false;
	
	// 重置图片
	function resetImage() {
		jQuery("#imageMenu").html(srcImgUl);
		jQuery("#midimg").attr("src",srcImgBig);
		jQuery("#bigView").find("img").attr("src",srcImgSrc);
		imageSet = false;
	}
	// 重置价格
	function resetPrice() {
		jQuery("#currDiscountPrice").html(srcDiscountPrice);
		jQuery("#currPrice").html(srcPrice);
		jQuery("#discount").html(srcDiscount);
		jQuery("#savePrice").html(srcSavePrice);
		var discount = parseFloat(srcDiscount);
		showDiscount(discount>0 && srcPrice!=srcDiscountPrice);
		calGroupPrice();
		priceSet = false;
	}
	function showDiscount(show) {
		if (show) {
			jQuery("*").find("#showDiscount").each(function(){jQuery(this).show();});
		} else {
			jQuery("*").find("#showDiscount").each(function(){jQuery(this).hide();});
		}
	}
	// 重置sku
	function resetSku() {
		jQuery("#sku").html(srcSku);
		skuSet = true;
	}
	
	function resetAll() {
		setStockNum(srcStockNum);
		resetImage();
		resetPrice();
		resetSku();
	}
	
	// 缓存库存
	for(i=0; i<standards.length; i++) {
		var standard = standards[i];
		jQuery("body").append("<input standardId='"+standard.productStandardBean.standardId+"' type='hidden' id='standard_"+standard.productStandardBean.mainConfigId+"_"+standard.productStandardBean.subConfigId+"' idx='"+i+"' value='"+standard.productStandardBean.stock+"'/>");
	}

	// 主属性数据
	if (mainShow.showType=='color') {
		mainUl.addClass("small_swatches");
	}
	for(i=0; i<mainShow.configs.length; i++) {
		var config = mainShow.configs[i];
		var mainId = config.productConfigBean.productConfigId;
		var subId = "";
		for(j=0; j<config.subConfigs.length; j++) {
			var subC = config.subConfigs[j];
			subId = subId + subC.productConfigBean.productConfigId + ",";
		}
		if (mainShow.showType=='color') {
			var colorid = parseInt(config.attribute.attrId)-9000000000;
			mainUl.append("<li mainId='"+config.productConfigBean.productConfigId+"' subId='"+subId+"' idx='"+i+"'><span style='display:none;'>"+config.attribute.value+"</span><span class='color_"+colorid+"' alt='"+config.attribute.value+"' title='"+config.attribute.value+"'></span></li>");
		} else {
			mainUl.append("<li mainId='"+config.productConfigBean.productConfigId+"' subId='"+subId+"' idx='"+i+"'>"+config.attribute.value+"</li>");
		}
	}
	// 从属性数据
	if (subShow != null && subShow.configs != null) {
		for(i=0; i<subShow.configs.length; i++) {
			var config = subShow.configs[i];
			subUl.append("<li subId='"+config.productConfigBean.productConfigId+"' prodId='"+prodId+"'>"+config.attribute.value+"</li>");
		}
	}
	
	// 单击主属性事件
	mainUl.find("li").each(function(i,o){
		jQuery(o).click(function(){
			var config = jQuery(o);
			if (config.attr("class")=="choose") {
				resetAll();
				config.removeClass("choose");
				subUl.find("li[class='choose']").each(function(ii,oo){jQuery(oo).removeClass("choose");});
				return;
			}
			
			mainUl.find("li[class='choose']").each(function(ii,oo){jQuery(oo).removeClass("choose");}); // 清除主属性选择
			subUl.find("li[class='choose']").each(function(ii,oo){jQuery(oo).removeClass("choose");}); // 清除从属性选择
			config.addClass("choose"); // 选择主属性
			changeSubConfig(o); // 更新从属性显示
			changeGroupPhoto(o); // 更新图片显示
			if (subUl.find("li").length==0) {
				var mainId = mainUl.find("li[class='choose']").attr("mainId");
				var subId = 0;
				changeSkuPrice(mainId, subId);
			} else {
				resetAll();
			}
		});
	});
	
	// 单击从属性事件
	subUl.find("li").each(function(i,o){
		jQuery(o).click(function(){
			subUl.find("li[class='choose']").each(function(ii,oo){jQuery(oo).removeClass("choose");}); // 清除从属性选择
			jQuery(o).addClass("choose"); // 选择从属性
			mainConfig = mainUl.find("li[class='choose']");
			if (mainConfig.length>0) {
				var mainId = mainConfig.attr("mainId");
				var subId = jQuery(this).attr("subId");
				changeSkuPrice(mainId, subId);
			}
		});
	});
	
	function changeSkuPrice(mainId, subId) {
		var standard = jQuery("#standard_"+mainId+"_"+subId);
		var idx = standard.attr("idx");
		var substock = parseInt(standard.val()); // 判断库存
		setStockNum(substock); // 更新库存显示
		
		// sku更换
		var sku=standards[idx].productStandardBean.sku;
		if (sku != "") {
			skuSet = true;
			jQuery("#sku").html(sku);
		} else {
			if (skuSet) {
				resetSku();
			}
		}
		// 价格更换
		var discountPrice = standards[idx].discountPrice;
		if (parseFloat(discountPrice) > 0) {
			priceSet = true;
			jQuery("#currDiscountPrice").html(discountPrice);
			var price = standards[idx].price;
			if (parseFloat(price) > 0) {
				jQuery("#currPrice").html(price);
				jQuery("#discount").html(standards[idx].priceBean.discount);
				jQuery("#savePrice").html(standards[idx].save);
				showDiscount(standards[idx].priceBean.discount>0 && price!=discountPrice);
			}
			calGroupPrice();
		} else {
			if (priceSet) {
				resetPrice();
			}
		}
	}
	
	//  更新图片显示
	function changeGroupPhoto(o) {
		var productConfigId = jQuery(o).attr("mainId");
		var photo = jQuery("body").find("#prodPhoto_" + productConfigId);
		if (photo.length == 0) { // ajax读取图片地址并用div缓存
			var url="/fashion/action/product.AjaxGetConfigImages";
			jQuery.getJSON(url, {productConfigId:productConfigId}, function(data) {
				jQuery("body").append("<div id='prodPhoto_" + productConfigId + "' class='hide'></div>");
				if (data != null && data.length>0) {
					var div = jQuery("#prodPhoto_" + productConfigId);
					for(i=0; i<data.length; i++) {
						var img = data[i];
						div.append("<span idx='"+img.indexId+"'>"+img.imageUrl+"</span>");
					}
				}
				showProductConfigImage(productConfigId);
			});
		} else {
			showProductConfigImage(productConfigId);
		}
	}
	// 更换显示图片
	function showProductConfigImage(productConfigId) {
		// 更换左侧所有小图，及默认大图
		var div = jQuery("#prodPhoto_" + productConfigId);
		if(div.find("span").length>0) {
			imageSet = true;
			var firstImage = "";
			// 清空原有小图，重新添加
			jQuery("ul[id='imageMenu']").empty();
			div.find("span").each(function(i,o){
				var imgUrl = jQuery(o).html();
				jQuery("ul[id='imageMenu']").append("<li class=\"track\" name=\"Windows-track-"+i+"\"> <img title=\"\" alt=\"\" src=\""+imgUrl+"\" width=\"95\" height=\"95\"></li>");
				if (i==0) {
					firstImage = imgUrl;
				}
			});
			jQuery("li[name='Windows-track-0']").attr("id", "onlickImg");
			// 更换第一张大图
			var bigImg = firstImage.replace(".m.", ".");
			jQuery("#midimg").attr("src",bigImg);
			// 更换第一张原图
			var srcImg = firstImage.replace(".m.", ".src.");
			jQuery("#bigView").find("img").attr("src",srcImg);
		} else {
			if (imageSet) {
				resetImage();
			}
		}
	}
	// 更新从属性显示
	function changeSubConfig(o) {
		var subIds = jQuery(o).attr("subId");
		if (subIds == "") {
			subIds = "0";
		}
		subIds = subIds.split(",");
		var mainId = jQuery(o).attr("mainId");
		subUl.find("li").each(function(){jQuery(this).hide()}); // 先隐藏所有，后显示私有
		var stock = 0;
		for(i=0; i<subIds.length; i++) {
			subId = subIds[i];
			subUl.find("li[subId='"+subId+"']").show(); // 显示私有
			var substock = parseInt(jQuery("#standard_"+mainId+"_"+subId).val()); // 判断库存
			if (substock > 0) {
				stock += substock;  // 累加库存
			}
		}
		setStockNum(stock);
	}
	function setStockNum(stock) {
		if (stock == 0) {
			jQuery("#addCartDiv").hide();
			jQuery("#outStockDiv").show();
		} else {
			jQuery("#addCartDiv").show();
			jQuery("#outStockDiv").hide();
		}
		jQuery("#stockNumber").html(stock);
	}
}