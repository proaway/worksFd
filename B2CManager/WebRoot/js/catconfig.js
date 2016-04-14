function CatConfig(catId, configNodes) {
	var configTb = jQuery("#configTb");
	var mainTr = jQuery("#mainTr");
	var subTr = jQuery("#subTr");
	var configs = {};
	var mainNode, subNode;
	
	// 生成配置属性元素
	for (var i=0; i<configNodes.length; i++) {
		var configNode = configNodes[i];
		configs[configNode.config.id]=configNode.config;
		var trId = "subConfig";
		var tr = subTr;
		if (i==0) {
			trId = "mainConfig";
			tr = mainTr;
			mainNode = configNode;
		} else {
			subNode = configNode;
		}
		tr.empty();
		tr.append("<td class='td_136_r'>"+configNode.config.valueCn+"：<input type='hidden' name='"+trId+"Title' value='"+configNode.config.id+"'/></td>");
		var td = "<td class='norms_format pad_bottom_10'>";
		for(var j=0; j<configNode.nodes.length; j++) {
			var node = configNode.nodes[j];
			configs[node.config.id]=node.config;
			td += "<label class='checkbox'>";
			td += "<input idx='"+j+"' id='catConf' value='"+node.config.id+"' attrValue='"+node.config.value+"' attrValueCn='"+node.config.valueCn+"' type='checkbox' name='"+trId+"' allowCustom='"+configNode.config.allowCustom+"'> ";
			if (configNode.config.showType == "color") {
				var colorid = parseInt(node.config.id)-9000000000;
				td += " <span class='color_"+colorid+"' alt='"+node.config.valueCn+"' title='"+node.config.valueCn+"'></span>"
			} else {
				td += node.config.valueCn;
			}
			td += " </label>"
		}
		td += "</td>";
		tr.append(td);
		tr.show();
	}
	// 配置属性点击事件
	configTb.find("#catConf").each(function(i,o){
		jQuery(o).change(function(){
			// 显示或隐藏零售价
			showRetailPrice(this);
			var allowCustom = jQuery(this).attr("allowCustom");
			if (allowCustom != null && allowCustom=="true") {
				// 可自定义名称图片，显示自定义名称图片
				addCustomRow(this);
			}
			// 普通配置属性，定义规格库存、价格等数据
			if (o.checked) {
				addStandardRow(this);
			} else {
				removeStandardRow(this);
			}
			resetInputName();
		});
	});
	// 显示或隐藏零售价
	function showRetailPrice(o) {
		if (configTb.find("#catConf:checked").length>0) {
			configTb.find("#retailPriceTr").hide();
			configTb.find("input#retailPrice").val("");
			ProductVerify.removeParam("retailPrice");
			ProductVerify.removeParam("stock");
		} else {
			configTb.find("#retailPriceTr").show();
			ProductVerify.addParam({id:"retailPrice", isNotNull:true, isNum:true, errmsg:"请正确填写销售价格。"});
			ProductVerify.addParam({id:"stock", isNotNull:true, isInt:true, errmsg:"请正确填写库存。"});
		}
	}
	// 删除规格行
	function removeStandardRow(o) {
		var standardTb = jQuery("#standardTb");
		
		var ismain = jQuery(o).parents("tr").attr("id")=="mainTr";
		var hasmain = standardTb.find("#sdMain").length>0;
		var hassub = standardTb.find("#sdSub").length>0;
		
		if (ismain) {// 当前是主
			var mainId = jQuery(o).attr("value");
			if (hassub) { // 有从属性
				var mainInputs = mainTr.find("input:checked");
				if (mainInputs.length>0) { // 还有剩余主属性，直接删行
					standardTb.find("tr[mainId='"+mainId+"']").remove();
				} else { // 无剩余主属性，删除主属性列
					standardTb.find("td[id='sdMain']").remove();
					standardTb.find("tr[subId]").removeAttr("mainId");
				}
				
			} else { // 没有从属性，直接删除行
				standardTb.find("tr[mainId='"+mainId+"']").remove();
			}
		} else {// 当前是从
			var subId = jQuery(o).attr("value");
			if (hasmain) { // 有主属性
				var subInputs = subTr.find("input:checked");
				if (subInputs.length>0) { // 还有剩余从属性，直接删行
					standardTb.find("tr[subId='"+subId+"']").remove();
				} else { // 无剩余从属性，删除从属性列
					standardTb.find("td[id='sdSub']").remove();
					standardTb.find("tr[mainId]").removeAttr("subId");
				}
				
			} else { // 没有主属性，直接删除行
				standardTb.find("tr[subId='"+subId+"']").remove();
			}
		}
		
		showStandardRow(o);
	}
	// 规格表显示隐藏
	function showStandardRow(o) {
		var standardTb = jQuery("#standardTb");
		var mainInputs = mainTr.find("input:checked");
		var subInputs = subTr.find("input:checked");
		var mainLength = mainInputs.length;
		var subLength = subInputs.length;
		if(mainLength>0 || subLength>0) {
			jQuery("#standardTb").show();
		} else {
			standardTb.find("td[id='sdMain']").remove();
			standardTb.find("td[id='sdSub']").remove();
			jQuery("#standardTb").hide();
		}
	}
	
	// 普通配置属性，定义规格库存、价格等数据
	function addStandardRow(o) {
		// 规格表显示隐藏
		var mainInputs = mainTr.find("input:checked");
		var subInputs = subTr.find("input:checked");
		var standardTb = jQuery("#standardTb");
		var ismain = jQuery(o).parents("tr").attr("id")=="mainTr";
		var hasmain = standardTb.find("#sdMain").length>0;
		var hassub = standardTb.find("#sdSub").length>0;
		
		// 当前是主
		if (ismain) {
			var mainId = jQuery(o).attr("value");
			var mainCKBox = mainTr.find("[value='"+mainId+"']");
			var beforeMainId = findBeforeId(mainInputs, mainId, jQuery(o).attr("idx"));
			var tdMain;
			if (hasmain && hassub) {// 表格有主有从
				// 增加完整行
				var leng = subInputs.length;
				var lastTr;
				for(var i=0; i<leng; i++) {
					var subId = subInputs[i].value;
					if (jQuery("tr[id='standardRow'][mainId='"+mainId+"'][subId='"+subId+"']").length>0) {
						continue;
					}
					var tr = createStandardRow(mainId, subId);
					//standardTb.append(tr);
					if (lastTr == null) {
						if (beforeMainId == mainId) {
							jQuery(standardTb.find("tr[subId='"+subId+"']")[0]).before(tr);
						} else {
							var k = standardTb.find("tr[mainId='"+beforeMainId+"']").length;
							jQuery(standardTb.find("tr[mainId='"+beforeMainId+"']")[k-1]).after(tr);
						}
					} else {
						lastTr.after(tr);
					}
					lastTr = tr;
				}
			} else if (hassub) {// 表格只有从
				// 增加主列
				standardTb.find("tr[subId]").attr("mainId",mainId);
				for(var i=0; i<standardTb.find("#sdSub").length; i++) {
					var td = jQuery(standardTb.find("#sdSub")[i]);
					if (i==0) {
						tdMain = "<td id='sdMain'>"+mainNode.config.valueCn+"</td>";
					} else {
						tdMain = createMainTd(mainCKBox, mainId);
					}
					td.before(tdMain);
				}
				
			} else {// 表格空 或 表格只有主
				if (!hasmain) { // 表格空，增加表头
					tdMain = "<td id='sdMain' class='center_t'>"+mainNode.config.valueCn+"</td>";
					standardTb.find("#standardTitle").prepend(tdMain);
				}
				if (jQuery("tr[id='standardRow'][mainId='"+mainId+"']").length==0) {
					// 增加主行
					var tr = createStandardRow(mainId, "");
					//standardTb.append(tr);
					if (beforeMainId==mainId) { // 插入到第一行
						standardTb.find("#standardTitle").after(tr);
					} else {
						standardTb.find("tr[mainId='"+beforeMainId+"']").after(tr);
					}
				}
			}
		}
		
		// 当前是从
		if (!ismain) {
			var subId = jQuery(o).attr("value");
			var beforeSubId = findBeforeId(subInputs, subId, jQuery(o).attr("idx"));
			var subCKBox = subTr.find("[value='"+subId+"']");
			var tdSub;
			if (hasmain && hassub) {// 表格有主有从
				// 增加完整行
				var leng = mainInputs.length;
				for(var i=0; i<leng; i++) {
					var mainId = mainInputs[i].value;
					if (jQuery("tr[id='standardRow'][mainId='"+mainId+"'][subId='"+subId+"']").length>0) {
						continue;
					}
					var tr = createStandardRow(mainId, subId);
					//standardTb.append(tr);
					if (beforeSubId == subId) {
						jQuery(standardTb.find("tr[mainId='"+mainId+"']")[0]).before(tr);
					} else {
						standardTb.find("tr[mainId='"+mainId+"'][subId='"+beforeSubId+"']").after(tr);
					}
				}
			} else if (hasmain) {// 表格只有主
				// 增加从列
				standardTb.find("tr[mainId]").attr("subId",subId);
				for(var i=0; i<standardTb.find("#sdMain").length; i++) {
					var td = jQuery(standardTb.find("#sdMain")[i]);
					if (i==0) {
						tdSub = "<td id='sdSub' class='center_t'>"+subNode.config.valueCn+"</td>";
					} else {
						tdSub = createSubTd(subCKBox, subId);
					}
					td.after(tdSub);
				}
			} else {// 表格只有从 或 表格空
				if (!hassub) { // 表格空，增加表头
					tdSub = "<td id='sdSub'>"+subNode.config.valueCn+"</td>";
					standardTb.find("#standardTitle").prepend(tdSub);
				}
				if (jQuery("tr[id='standardRow'][subId='"+subId+"']").length==0) {
					// 增加从行
					var tr = createStandardRow("", subId);
					//standardTb.append(tr);
					if (beforeSubId==subId) { // 插入到第一行
						standardTb.find("#standardTitle").after(tr);
					} else {
						standardTb.find("tr[subId='"+beforeSubId+"']").after(tr);
					}
				}
			}
		}
		showStandardRow(o);
	}
	function resetInputName() {
		var standardTb = jQuery("#standardTb");
		standardTb.find("#standardRow").each(function(i,o){
			var tr = jQuery(o)
			var mainId = tr.attr("mainId");
			var subId = tr.attr("subId");
			mainId = mainId == null ? "" : mainId;
			subId = subId == null ? "" : subId;
			tr.find("input[name^='price_']").attr("name", "price_" + mainId + "_" + subId);
			tr.find("input[name^='wholesale_']").attr("name", "wholesale_" + mainId + "_" + subId);
			tr.find("input[name^='discount_']").attr("name", "discount_" + mainId + "_" + subId);
			tr.find("input[name^='stockNum_']").attr("name", "stockNum_" + mainId + "_" + subId);
			tr.find("input[name^='sku_']").attr("name", "sku_" + mainId + "_" + subId);
			tr.find("input[id^='configPrice']").attr("id", "configPrice_" + mainId + "_" + subId);
			tr.find("input[id^='configStock']").attr("id", "configStock_" + mainId + "_" + subId);
			tr.find("input[id^='configSku']").attr("id", "configSku_" + mainId + "_" + subId);
			if(ProductVerify.getParams("configPrice_" + mainId + "_" + subId).length == 0) {
				ProductVerify.addParam({id:"configPrice_" + mainId + "_" + subId, isNotNull:true, isNum:true, errmsg:"请正确填写销售价格。"});
			}
			if(ProductVerify.getParams("configStock_" + mainId + "_" + subId).length == 0) {
				ProductVerify.addParam({id:"configStock_" + mainId + "_" + subId, isNotNull:true, isInt:true, errmsg:"请正确填写库存。"});
			}
			if(ProductVerify.getParams("configSku_" + mainId + "_" + subId).length == 0) {
				ProductVerify.addParam({id:"configSku_" + mainId + "_" + subId, isNotNull:true, maxLength:200, invalidReg:/[^\w\-]/ig, errmsg:"请正确填写规格SKU，不可重复。"});
			}
		});
	}
	// 查找指定id号前一个行的id，用于插入行定位
	function findBeforeId(inputs, id, idx) {
		var beforeId = id;
		var rid =  parseInt(idx);
		for(var i=0; i<inputs.length; i++) {
			var idx = parseInt(jQuery(inputs[i]).attr("idx"));
			if (idx>=rid) {
				break;
			}
			beforeId = inputs[i].value;
		}
		return beforeId;
	}
	// 创建规格从属性列
	function createSubTd(box, subId) {
		var tdSub="<td id='sdSub'><label class='mar_top_15'>"+box.attr("attrValueCn")+"</label></td>";
		return tdSub;
	}
	// 创建规格主属性列
	function createMainTd(box, mainId) {
		var tdMain, showType = mainNode.config.showType;
		if (showType=='color') {
			var colorid = parseInt(mainId)-9000000000;
			tdMain = "<td id='sdMain' class='norms_format_td'><span class='color_"+colorid+"' alt='"+box.attr("attrValueCn")+"' title='"+box.attr("attrValueCn")+"'></span></td>";
		} else {
			tdMain = "<td id='sdMain' class='norms_format_td'>"+box.attr("attrValueCn")+"</td>";
		}
		return tdMain;
	}
	// 创建规格行
	function createStandardRow(mainId, subId) {
		var baseSku = jQuery("#sku").val();
		var trStr = "<tr id='standardRow' ";
		var tdMain=""; 
		var tdSub="";
		if (mainId != "") {
			trStr += "mainId='" + mainId + "' ";
			var box = mainTr.find("[value='"+mainId+"']");
			tdMain = createMainTd(box, mainId);
		}
		if (subId != "") {
			trStr += "subId='" + subId + "' ";
			var box = subTr.find("[value='"+subId+"']");
			tdSub = createSubTd(box, subId);
		}
		trStr += "></tr>";
		var tr = jQuery(trStr);
		if (mainId != "") {
			tr.append(tdMain);
		}
		if (subId != "") {
			tr.append(tdSub);
		}

		tr.append("<td><label class='left mar_top_15'>US $</label><input name='price_' id='configPrice' style='ime-mode:disabled' onkeyup='value=value.replace(/[^0-9\.]/ig,\"\")' class='input-small left mar_top_10' type='text' placeholder='零售价'><label class='left mar_top_15'>/件</label> </td>");
		tr.append("<td><label class='mar_top_15' id='salePrice'></label></td>");
		tr.append("<td><input name='stockNum_' id='configStock' class='input-mini mar_top_10' onkeyup='this.value=this.value.replace(/\D/g,\"\");' type='text' placeholder='库存'></td>");
		tr.append("<td><div class='input-prepend'><span class='add-on mar_top_10' id='baseSku'>"+baseSku+"-</span><input name='sku_' id='configSku' style='ime-mode:disabled' onkeyup='value=value.replace(/[^\\w\\.\\/\\-]/ig,\"\")' maxlength='10' class='span2 mar_top_10' type='text' placeholder='SKU编码'></div></td>");
		tr.append("<td><label class='checkbox'><input name='discount_' id='configDiscount' type='checkbox' value='1'> 支持折扣 </label><label class='checkbox'><input name='wholesale_' id='configWholesale' type='checkbox' value='1'>支持批发</label></td>");
		if (!jQuery("#if_discount")[0].checked) {
			tr.find("#configDiscount").attr("disabled","disabled");
		}
		if (!jQuery("#if_wholesale")[0].checked) {
			tr.find("#configWholesale").attr("disabled","disabled");
		}
		tr.find("[id^='configPrice']").change(function(){
			var price = Math.round(this.value * commission*100)/100;
			tr.find("#salePrice").html("US $"+price);
		});
		tr.find("#configDiscount").parent().hover(
			function(){
				var price = parseFloat(tr.find("[id^='configPrice']").val());
				var isDiscount = jQuery("#if_discount")[0].checked;
				if (price > 0 && isDiscount) {
					var discount = parseInt(jQuery("input#discount").val());
					var div = jQuery(this).next("div");
					if (div.length==0) {
						div=jQuery("<div class='alert alert-info hover_alert hide'>折扣价：<br class='clear' /><strong></strong></div>");
						jQuery(this).after(div);
					}
					var discountPrice = Math.round(price * (1-discount/100) * 100)/100;
					div.find("strong").html("$" + price + " x " + (100-discount) + "% = $" + discountPrice);
					jQuery(this).next("div").show();
				}
			},
			function(){
				jQuery(this).next("div").hide();
			}
		);
		tr.find("#configWholesale").parent().hover(
			function(){
				var price = parseFloat(tr.find("[id^='configPrice']").val());
				var isWholesale = jQuery("#if_wholesale")[0].checked;
				if (price > 0 && isWholesale) {
					var discount = parseInt(jQuery("input#wholesaleDiscount").val());
					var div = jQuery(this).next("div");
					if (div.length==0) {
						div=jQuery("<div class='alert alert-info hover_alert hide'>批发价：<br class='clear' /><strong></strong></div>");
						jQuery(this).after(div);
					}
					var discountPrice = Math.round(price * (1-discount/100) * 100)/100;
					div.find("strong").html("$" + price + " x " + (100-discount) + "% = $" + discountPrice);
					jQuery(this).next("div").show();
				}
			},
			function(){
				jQuery(this).next("div").hide();
			}
		);
		return tr;
	}
	
	// 可自定义名称图片，显示自定义名称图片
	function addCustomRow(o) {
		var mainInputs = mainTr.find("input:checked");
		customTb = jQuery(o).parents("td").find("#mainConfig");
		if (customTb.length == 0) { // 添加表头
			jQuery(o).parents("td").append("<table class='table table-bordered mar_top_10 table-hover' id='mainConfig'></table>");
			customTb = jQuery(o).parents("td").find("#mainConfig");
			var tr = "<tr class='warning' id='customTitle'>";
			tr += "<td width='24' class='center_t'>"+mainNode.config.valueCn+"</td>";
			tr += "<td width='170px'>自定义名称</td>"
			tr += "<td>图片（无图片可以不填）<span class='font12 gray'>每种最多可以上传10张</span>"
			tr += "</td>"
			tr += "</tr>";
			customTb.append(tr);
		}
		if (o.checked) {
			if (customTb.find("tr[id='"+jQuery(o).attr("value")+"']").length > 0) {
				return;
			}
			var mainId = o.value;
			var tr = jQuery("<tr id='"+mainId+"'></tr>");
			var showType = mainNode.config.showType;
			if (showType=="color") {
				// 颜色
				var colorid = parseInt(mainId)-9000000000;
				tr.append("<td class='norms_format_td'><span class='color_"+colorid+"' alt='"+jQuery(o).attr("attrValueCn")+"' title='"+jQuery(o).attr("attrValueCn")+"' ></span></td>");
			} else {
				tr.append("<td class='norms_format_td'>"+jQuery(o).attr("attrValueCn")+"</td>");
			}
			tr.append("<td><input name='attrName_"+mainId+"' class='mar_top_10 input-medium' type='text' placeholder='自定义名称'> </td>");
			tr.append("<td><input mainId='"+mainId+"' id='selectLocalImg' type='button' class='btn left mar_top_10' value='选择文件' /><input mainId='"+mainId+"' id='selectImg' type='button' class='btn left mar_top_10' value='图片空间' /><span class='help-block left mar_top_15 mar_left_15'>未选择文件</span><ul class='inline pu_gg_color_img_ul'></ul></td>");
			var beforeId = findBeforeId(mainInputs, mainId, jQuery(o).attr("idx"));
			
			if (beforeId == mainId) {
				customTb.find("#customTitle").after(tr);
			} else {
				customTb.find("tr[id='"+beforeId+"']").after(tr);
			}
		} else {
			customTb.find("tr[id='"+jQuery(o).attr("value")+"']").remove();
			if (customTb.find("tr").length==1) {
				customTb.remove();
			}
		}
	}
	
	var setProductConfig = function() {
		var standards = productConfig.standards;
		var mainShow = productConfig.mainShow;
		var mainConfigs = mainShow.configs;
		var subShow = productConfig.subShow;
		var prodConfigs = {};

		// 选定复选框
		for(var i=0; i<mainShow.configs.length; i++) {
			var config = mainShow.configs[i];
			jQuery("[name='mainConfig'][value='"+config.attribute.attrId+"']").attr("checked","checked");
			if (jQuery("tr[id='"+config.attribute.attrId+"']").length==0) {
				jQuery("[name='mainConfig'][value='"+config.attribute.attrId+"']").change();
			}
			prodConfigs[config.productConfigBean.productConfigId] = config;
		}
		if(subShow != null && subShow.configs != null) {
			for(var i=0; i<subShow.configs.length; i++) {
				var config = subShow.configs[i];
				jQuery("[name='subConfig'][value='"+config.attribute.attrId+"']").attr("checked","checked");
				jQuery("[name='subConfig'][value='"+config.attribute.attrId+"']").change();
				prodConfigs[config.productConfigBean.productConfigId] = config;
			}
		}
		
		// 填写数据 主属性
		for(var i=0; i<mainShow.configs.length; i++) {
			var config = mainShow.configs[i];
			jQuery("[name='attrName_"+config.attribute.attrId+"']").val(config.productConfigBean.attrName);
			var tr = jQuery("tr[id='"+config.attribute.attrId+"']");
			// 图片
			if (config.images.length>0) {
				for (var j=0; j<config.images.length; j++) {
					tr.find("span:contains('未选择文件')").hide();
					tr.find("ul").append();
					
					tr.find("ul").append('<input type="hidden" name="configImg_'+config.attribute.attrId+'" value="' + config.images[j].imageId +'" />');
					tr.find("ul").append('<li><img src="' + config.images[j].imageUrl + '" alt="" title="" /><br class="clear" /><button class="btn btn-link" onclick="removeImg(this)" type="button">删除</button></li>');
				}
			}
		}
		
		// 规格数据
		for(var i=0; i<standards.length; i++) {
			var standard = standards[i];
			var mainConfig = prodConfigs[standard.productStandardBean.mainConfigId];
			var subConfig = prodConfigs[standard.productStandardBean.subConfigId];
			var tr;
			if (mainConfig != null && subConfig != null) {
				var mainId = mainConfig.attribute.attrId;
				var subId = subConfig.attribute.attrId;
				tr = jQuery("tr#standardRow[mainId='"+mainId+"'][subId='"+subId+"']");
			} else if (mainConfig != null) {
				var mainId = mainConfig.attribute.attrId;
				tr = jQuery("tr#standardRow[mainId='"+mainId+"']");
			} else if (subConfig != null) {
				var subId = subConfig.attribute.attrId;
				tr = jQuery("tr#standardRow[subId='"+subId+"']");
			}
			tr.find("[id^='configPrice']").val(standard.productStandardBean.price);
			tr.find("[id^='configPrice']").change();
			tr.find("[id^='configStock']").val(standard.productStandardBean.stock);
			tr.find("[id^='configSku']").val(standard.productStandardBean.sku);
			if(standard.productStandardBean.discount) {
				tr.find("#configDiscount").attr("checked","checked");
			}
			if(standard.productStandardBean.wholesale) {
				tr.find("#configWholesale").attr("checked","checked");
			}
		}
	};
	return {
		setProductConfig : setProductConfig
	}
}