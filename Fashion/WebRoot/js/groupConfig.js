function GroupConfig(prodId, groupConfig) {
	// 初始化数据
	var standards = groupConfig.standards;
	var mainShow = groupConfig.mainShow;
	var subShow = groupConfig.subShow;
	var mainDiv = jQuery("#mainConfig_"+prodId);
	var parentDiv = mainDiv.parents(".pro_R_M_1");
	mainDiv.append("<ul class='small_swatches' name='"+mainShow.titleAttr.value+"' mainUl></ul>");
	var mainUl = mainDiv.find("ul");
	var baseDiscountPrice = groupConfig.price.discountPrice;
	var basePrice = groupConfig.price.price;
	
	var subDiv = jQuery("#subConfig_"+prodId);
	if (subShow != null) {
		subDiv.append("<ul name='"+subShow.titleAttr.value+"' subUl></ul>");
		var subUl = subDiv.find("ul");
	}
	// 缓存库存
	for(i=0; i<standards.length; i++) {
		var standard = standards[i];
		mainDiv.parent().append("<input type='hidden' standardId='"+standard.productStandardBean.standardId+"' id='groupStandard_"+standard.productStandardBean.mainConfigId+"_"+standard.productStandardBean.subConfigId+"' value='"+standard.productStandardBean.stock+"' idx='"+i+"'/>");
	}
	
	// 生成主属性
	for(i=0; i<mainShow.configs.length; i++) {
		var config = mainShow.configs[i];
		var subId = "";
		for(j=0; j<config.subConfigs.length; j++) {
			var subC = config.subConfigs[j];
			subId = subId + subC.productConfigBean.productConfigId + ",";
		}
		if (mainShow.showType=='color') {
			var colorid = parseInt(config.attribute.attrId)-9000000000;
			mainUl.append("<li id='mainConfig' prodId='"+prodId+"' mainId='"+config.productConfigBean.productConfigId+"' subId='"+subId+"'><span style='display:none;'>"+config.attribute.value+"</span><span class='color_"+colorid+"' alt='"+config.attribute.value+"' title='"+config.attribute.value+"'></span></li>");
		} else {
			mainUl.append("<li id='mainConfig' prodId='"+prodId+"' mainId='"+config.productConfigBean.productConfigId+"' subId='"+subId+"'>"+config.attribute.value+"</li>");
		}
	}
	
	// 生成从属性
	if (subShow != null) {
		for(i=0; i<subShow.configs.length; i++) {
			var config = subShow.configs[i];
			subUl.append("<li id='subConfig' subId='"+config.productConfigBean.productConfigId+"' prodId='"+prodId+"'>"+config.attribute.value+"</li>");
		}
	}
	
	// 单击主属性事件
	mainDiv.find("li").each(function(i,o){
		jQuery(o).click(function(){
			mainDiv.find("li").each(function(ii,oo){jQuery(oo).removeClass("choose");}); // 清除主属性选择
			subDiv.find("li").each(function(ii,oo){jQuery(oo).removeClass("choose");}); // 清除从属性选择
			parentDiv.find("#errInfo").html(""); // 清除错误信息
			jQuery(o).addClass("choose"); // 选择主属性
			if (subShow != null) {
				changeSubConfig(o); // 更新从属性显示
			}
			changeGroupPhoto(o); // 更新图片显示
		});
	});
	// 单击从属性事件
	if (subShow != null) {
		subDiv.find("li").each(function(i,o){
			jQuery(o).click(function(){
				subDiv.find("li").each(function(ii,oo){jQuery(oo).removeClass("choose");}); // 清除从属性选择
				jQuery(o).addClass("choose"); // 选择从属性
			});
		});
	}
	// OK按钮单击事件
	parentDiv.find("input[value='OK']").click(function(){
		var selectedInfo = "";
		var c1 = mainDiv.find("li[class='choose']").text(); // 寻找选中主属性
		var errInfo = parentDiv.find("#errInfo"); // 未选主属性，提示错误
		if (c1 == null || c1=="") {
			errInfo.html("Please choose " + mainUl.attr("name"));
			return;
		} else {
			errInfo.html("");
		}
		if (subShow != null) {
			var c2 = subDiv.find("li[class='choose']").html();
			if (c2 == null) {
				errInfo.html("Please choose " + subUl.attr("name"));
				return;
			} else {
				errInfo.html("");
			}
		}
		
		var mainId = mainDiv.find("li[class='choose']").attr("mainId");
		if (subShow != null) {
			var subId = subDiv.find("li[class='choose']").attr("subId");
		}
		if (subShow != null) {
			parentDiv.find("[name='groupChoosed']").find(".content").html(c1 + ", " + c2);
		} else {
			parentDiv.find("[name='groupChoosed']").find(".content").html(c1);
		}
		parentDiv.find("[name='groupChoosed']").attr("mainChoose", mainId);
		if (subShow != null) {
			parentDiv.find("[name='groupChoosed']").attr("subChoose", subId);
		}
		
		// 更新价格
		var st = jQuery("#groupStandard_"+mainId+"_"+subId);
		var discountPrice = st.attr("discountPrice");
		if (parseFloat(discountPrice) > 0) {
			parentDiv.find("#gprice").html(discountPrice);
		}
		parentDiv.find("[id^='add_pro_tit_']").hide();
		parentDiv.find("#groupProd").attr("checked","checked");
		parentDiv.find("[name='groupChoosed']").show();
		calGroupPrice();
	});
	
	// Cancel按钮单击
	parentDiv.find("input[value='Cancel']").click(function(){
		parentDiv.find("[id^='add_pro_tit_']").hide();
	});
	
	// 单击选中项修改选择
	parentDiv.find("[name='groupChoosed']").click(function(){
		jQuery("*[id^='add_pro_tit_']").hide();
		var mainChoose = parentDiv.find("[name='groupChoosed']").attr("mainChoose");
		var subChoose = parentDiv.find("[name='groupChoosed']").attr("subChoose");
		parentDiv.find("li[class='choose']").removeClass("choose");
		parentDiv.find("li[mainId='"+mainChoose+"']").addClass("choose");
		if (subShow != null) {
			changeSubConfig(parentDiv.find("li[class='choose']")[0]);
		}
		parentDiv.find("li[subId='"+subChoose+"']").addClass("choose");
		parentDiv.find("[id^='add_pro_tit_']").show();
		showGroupConfigImage(mainChoose);
	});
	
	//  更新图片显示
	function changeGroupPhoto(o) {
		var productConfigId = jQuery(o).attr("mainId");
		var photo = mainDiv.find("#photo_" + productConfigId);
		if (photo.length == 0) { // ajax读取首图地址并用div缓存
			var url="/fashion/action/product.AjaxGetFirstConfigImage";
			jQuery.getJSON(url, {productConfigId:productConfigId}, function(data) {
				mainDiv.append("<div id='photo_" + productConfigId + "' class='hide'></div>");
				if (data != null) {
					mainDiv.find("#photo_" + productConfigId).html(data.imageUrl);
					showGroupConfigImage(productConfigId);
				}
			});
		} else {
			showGroupConfigImage(productConfigId);
		}
	}
	// 更换显示图片
	function showGroupConfigImage(productConfigId) {
		var imgUrl = mainDiv.find("#photo_" + productConfigId).html();
		if (imgUrl != "") {
			jQuery("#groupImg_" + prodId).attr("src",imgUrl);
		}
	}
	// 更新从属性显示
	if (subShow != null) {
		function changeSubConfig(o) {
			var subIds = jQuery(o).attr("subId").split(",");
			var pId = jQuery(o).attr("prodId");
			var mainId = jQuery(o).attr("mainId");
			subDiv.find("li[prodId='"+pId+"']").each(function(){jQuery(this).hide()});
			var stock = 0;
			for(i=0; i<subIds.length; i++) {
				subId = subIds[i];
				var substock = parseInt(jQuery("#groupStandard_"+mainId+"_"+subId).val()); // 判断库存
				if (substock > 0) {
					subDiv.find("[subId='"+subId+"']").show();
					stock += substock;
				}
			}
			if (stock == 0) {
				parentDiv.find("#errInfo").html("Out of stock.");// 显示无库存
			}
		}
	}
}
function selectGroupProd(o) {
	jQuery("*[id^='add_pro_tit_']").hide();
	var parentDiv = jQuery(o).parents(".pro_R_M_1");
	if (o.checked) { // 选中复选框
		if (parentDiv.find("li").length==0) {
			calGroupPrice();
			return true;
		}
		for(var i=0; i<parentDiv.find("li").length; i++) {
			var o = parentDiv.find("li")[i];
			jQuery(o).show();
		}
		
    	parentDiv.find("[id^='add_pro_tit_']").show();
    	parentDiv.find("[id='errInfo']").html("");
    	//parentDiv.find("li[class='choose']").each(function(i,o){jQuery(o).removeClass("choose");});
		for(var i=0; i<parentDiv.find("li").length; i++) {
			var o = parentDiv.find("li")[i];
			jQuery(o).show();
		}
    	return false;
	} else { // 取消复选框
		parentDiv.find("[name='groupChoosed']").hide();
		//parentDiv.find("li[class='choose']").each(function(i,o){jQuery(o).removeClass("choose");});
		
		for(var i=0; i<parentDiv.find("li[class='choose']").length; i++) {
			var o = parentDiv.find("li[class='choose']")[i];
			jQuery(o).removeClass("choose");
		}
		
		parentDiv.find("[id='errInfo']").html("");
		calGroupPrice();
	}
}
function calGroupPrice() {
	var discountIndex = jQuery("input#groupProd:checked").length - 1;
	var discount = discounts[discountIndex];
	var groupCount = 1;
	var groupDiscountPrice = parseFloat(jQuery("#currDiscountPrice").html());
	var groupPrice = parseFloat(jQuery("#currPrice").html());
	for (var i=0; i<jQuery("input#groupProd").length; i++) {
		var o = jQuery("input#groupProd")[i];
		if (o.checked) {
			var price = parseFloat(jQuery(o).next().html());
			groupPrice += price;
			groupDiscountPrice += price*discount/100;
			groupCount++;
			jQuery(o).parents(".pro_R_M_1").prev().attr("style","color:#900;");
		} else {
			jQuery(o).parents(".pro_R_M_1").prev().removeAttr("style");
		}
	}
	
	jQuery("#groupSelectedCount").html(groupCount);
	jQuery("#groupDiscountPrice").html(Math.round(groupDiscountPrice*100)/100);
	jQuery("#groupPrice").html(Math.round(groupPrice*100)/100);
}
function getGroupParams() {
	var groups=[];
	for(var i=0; i<jQuery("#groupProd:checked").length; i++) {
		var param = {};
		
		var o = jQuery("#groupProd:checked")[i];
		var parentDiv = jQuery(o).parents(".pro_R_M_1");
		var productId = parentDiv.attr("prodId");
		
		var mainConfigId = "";
		var mainTitle = "";
		var mainId = "";
		var subConfigId = "";
		var subTitle = "";
		var subId = "";
		var standardId = "";
		
		if (parentDiv.find("li#mainConfig").length>0) {
			mainConfigId = parentDiv.find("[name='groupChoosed']").attr("mainChoose");
			mainTitle = parentDiv.find("ul[mainUl]").attr("name");
			mainId = parentDiv.find("li#mainConfig[class='choose']").text();
		}
		
		if (parentDiv.find("li#subConfig").length>0) {
			subConfigId = parentDiv.find("[name='groupChoosed']").attr("subChoose");
			subTitle = parentDiv.find("ul[subUl]").attr("name");
			subId = parentDiv.find("li#subConfig[class='choose']").text();
		}
		
		if (mainConfigId != null || subConfigId != null) {
			mid = mainConfigId == null ? 0 : mainConfigId
			sid = subConfigId == null ? 0 : subConfigId
			standardId = jQuery("#groupStandard_" + mid + "_" + sid).attr("standardId");
		}
		var price = parentDiv.find("#gprice").text();
		var p = parentDiv.find("input[groupProd]");
		
		param["productId"] = productId;
		param["mainConfigId"] = mainConfigId;
		param["mainId"] = mainId;
		param["mainTitle"] = mainTitle;
		param["subConfigId"] = subConfigId;
		param["standardId"] = standardId;
		param["subId"] = subId;
		param["subTitle"] = subTitle;
		param["quantity"] = p.attr("quantity");
		param["sku"] = p.attr("sku");
		param["price"] = price;
		param["productName"] =  parentDiv.find("img").attr("title");
		param["imageUrl"] = parentDiv.find("img").attr("src");
		param["weight"] = p.attr("weight");
		param["height"] = p.attr("height");
		param["width"] = p.attr("width");
		param["length"] = p.attr("length");
		param["catId"] = p.attr("catId");
		param["maxStockDays"] = p.attr("maxStockDays");
		
		groups.push(param);
	}
	
	return groups;
}