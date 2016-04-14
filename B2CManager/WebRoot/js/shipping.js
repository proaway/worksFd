var countries = [];
var countryMap = {};
var shippingCostMap = {};
var continentTable = {};
var areaTable = {};
var currShippingComp = "";
var currentSelectCountry = {};
var currentGroup = {};
var currentGroups = {};
function loadCustomSetting(o) {
	loadCountries();
	var shippingCompany = jQuery(o).attr("id");
	currShippingComp = shippingCompany;
	jQuery("#selectedCountryDiv").empty();
	initSelectedCountry(shippingCompany);
	initGroup(shippingCompany);
	loadShippingCountries(shippingCompany);
	if (shippingCompany.indexOf("custCompany_")==0) {
		jQuery("#shippingCompanyTitle").html(jQuery("input:text#" + shippingCompany).val());
	} else {
		jQuery("#shippingCompanyTitle").html(jQuery("input:checkbox#" + shippingCompany).parent().text());
	}
	
	jQuery('#custom_fee').modal('show');
}
// 初始化已选定国家
function initSelectedCountry(shippingCompany) {
	if (shippingCostMap[shippingCompany] != null && shippingCostMap[shippingCompany]["selectedCountry"] != null) {
		currentSelectCountry = shippingCostMap[shippingCompany]["selectedCountry"];
	} else {
		currentSelectCountry = {};
	}
	if (shippingCostMap[shippingCompany] != null && shippingCostMap[shippingCompany]["groups"] != null) {
		// 已有选定运费组
		currentGroups = shippingCostMap[shippingCompany]["groups"];
		for(var item in currentGroups) {
			appendSelectedCountryRow(shippingCompany, currentGroups[item]);
		}
		jQuery("#accordion1").hide();
		jQuery("#shippingRateSetting").hide();
		jQuery("button#editGroupBtn").hide();
		jQuery("button#addGroupBtn").hide();
		jQuery("button#customBtn").show();
		jQuery("#addCustomShippingGroupDiv").show();
	} else {
		// 没有选定运费组
		currentGroups = {};
		jQuery("#accordion1").show();
		jQuery("#shippingRateSetting").show();
		
		jQuery("button#editGroupBtn").hide();
		jQuery("button#addGroupBtn").show();
		jQuery("button#customBtn").hide();
		
		jQuery("#addCustomShippingGroupDiv").hide();
	}
}
// 初始化分组
function initGroup(shippingCompany) {
	if (shippingCostMap[shippingCompany] == null || shippingCostMap[shippingCompany]["groups"]==null) {
		currentGroup = {};
		currentGroup["groupId"] = 1;
	} else {
		currentGroups = shippingCostMap[shippingCompany]["groups"];
	}
}
// 获取国家
function loadCountries() {
	if (countries.length == 0) {
    	jQuery.ajax({
    		url:"/manager/action/dict.AjaxGetCountries",
    		async:false,
    		dataType:"json",
    		success:function(data) {
    			countries = data;
    			for (var i=0; i<data.length; i++) {
    				var country = data[i];
    				countryMap[country.regionId] = country;
    			}
    		}
    	});
	}
}
// 物流国家设置
function loadShippingCountries(shippingCompany) {
	if (shippingCostMap[shippingCompany] == null || shippingCostMap[shippingCompany]["shipping"] == null) {
		if (shippingCostMap[shippingCompany] == null) {
			shippingCostMap[shippingCompany]={};
		}
    	jQuery.ajax({
    		url:"/manager/action/dict.GetShippingCost?shippingCompany=" + shippingCompany,
    		async:false,
    		dataType:"json",
    		success:function(data) {
    			for(var i=0; i<data.length; i++) {
    				var shippingCost = data[i];
    				shippingCost["countryObj"] = countryMap[shippingCost.country];
    			}
    			shippingCostMap[shippingCompany]["shipping"] = data;
    		}
    	});
	}
	createCountryDiv(shippingCompany);
}
// 创建国家样式
function createCountryDiv(shippingCompany) {
	createContinentTable(shippingCompany);
	var continent = jQuery("#collapseOne");
	var tb = continentTable[shippingCompany];
	var div = continent.find("table").parent();
	div.empty();
	div.append(tb);
	
	createAreaTable(shippingCompany);
	var area = jQuery("#collapseTwo");
	var areaTb = areaTable[shippingCompany];
	var areaDiv = area.find("table").parent();
	areaDiv.empty();
	areaDiv.append(areaTb);
	
	if (shippingCompany.indexOf("custCompany")==0) {
		jQuery("#shippingMethod").find("option[value='off']").hide();
		jQuery("#shippingMethod").val("custom");
		jQuery("#shippingMethod").change();
	} else {
		jQuery("#shippingMethod").find("option[value='off']").show();
	}
	
	for(var i=0; i<jQuery("input#chkAll").length; i++) {
		var all = jQuery(jQuery("input#chkAll")[i]).parents(".v_top");
		if (all.find("input#shippingCountry").length == all.find("input#shippingCountry:checked").length) {
			all.find("input#chkAll").attr("checked", "checked");
			all.find("#partSelect").hide();
		} else {
			all.find("input#chkAll").removeAttr("checked");
			if (all.find("input#shippingCountry:enabled:checked").length == 0) {
				all.find("#partSelect").hide();
			} else {
				all.find("#partSelect").show();
			}
		}
		if (all.find("input#shippingCountry").length == all.find("input#shippingCountry:disabled").length) {
			all.find("input#chkAll").attr("disabled", "disabled");
		} else {
			all.find("input#chkAll").removeAttr("disabled");
		}
	}
	if (jQuery("input#chkAll").length == jQuery("input#chkAll:checked").length) {
		jQuery("#chkAllContinent").attr("checked", "checked");
	} else {
		jQuery("#chkAllContinent").removeAttr("checked");
	}
	if (jQuery("input#chkAll").length == jQuery("input#chkAll:disabled").length) {
		jQuery("#chkAllContinent").attr("disabled", "disabled");
	} else {
		jQuery("#chkAllContinent").removeAttr("disabled");
	}
	
	jQuery("a#showAll").click(function(){
		jQuery(this).parents("table").find("div").hide();
		jQuery(this).parents("table").find("a#showAll").show();
		jQuery(this).parents("table").find("a#hideAll").hide();
		jQuery(this).parent().find("div").show();
		jQuery(this).next().show();
		jQuery(this).hide();
	});
	jQuery("a#hideAll").click(function(){
		jQuery(this).parent().find("div").hide();
		jQuery(this).prev().show();
		jQuery(this).hide();
	});
	jQuery("input#chkAll").click(function(){
		if (this.checked) {
			jQuery(this).parents("td.v_top").find("input#shippingCountry:enabled").attr("checked","checked")
		} else {
			jQuery(this).parents("td.v_top").find("input#shippingCountry:enabled").removeAttr("checked")
		}
		jQuery(this).parents("td.v_top").find("input#shippingCountry").change();
		if (tb.find("input#chkAll").length==tb.find("input#chkAll:checked").length) {
			tb.find("#chkAllContinent").attr("checked","checked")
		} else {
			tb.find("#chkAllContinent").removeAttr("checked")
		}
	});
	tb.find("#chkAllContinent").click(function(){
		if (this.checked) {
			tb.find("input:checkbox:enabled").attr("checked","checked")
			areaTb.find("input:checkbox:enabled").attr("checked","checked")
		} else {
			tb.find("input:checkbox:enabled").removeAttr("checked")
			areaTb.find("input:checkbox:enabled").removeAttr("checked")
		}
	});
	jQuery("input#shippingCountry").change(function(){
		var bro, cont = jQuery(this);
		if (jQuery(this).parents("#collapseOne").length>0) {
			bro = jQuery("#collapseTwo").find("input#shippingCountry[value='"+this.value+"']");
		} else {
			bro = jQuery("#collapseOne").find("input#shippingCountry[value='"+this.value+"']");
			cont = bro;
		}
		
		if (this.checked) {
			bro.attr("checked","checked");
		} else {
			bro.removeAttr("checked");
		}
		
		if (jQuery(this).parents("td.v_top").find("input#shippingCountry").length==jQuery(this).parents("td.v_top").find("input#shippingCountry:checked").length) {
			jQuery(this).parents("td.v_top").find("#chkAll").attr("checked","checked")
			jQuery(this).parents("td.v_top").find("#partSelect").hide();
		} else {
			jQuery(this).parents("td.v_top").find("#chkAll").removeAttr("checked")
			if (jQuery(this).parents("td.v_top").find("input#shippingCountry:enabled:checked").length == 0) {
				jQuery(this).parents("td.v_top").find("#partSelect").hide();
			} else {
				jQuery(this).parents("td.v_top").find("#partSelect").show();
			}
		}
		if (bro.parents("td.v_top").find("input#shippingCountry").length==bro.parents("td.v_top").find("input#shippingCountry:checked").length) {
			bro.parents("td.v_top").find("#chkAll").attr("checked","checked")
			bro.parents("td.v_top").find("#partSelect").hide();
		} else {
			bro.parents("td.v_top").find("#chkAll").removeAttr("checked")
			if (bro.parents("td.v_top").find("input#shippingCountry:enabled:checked").length == 0) {
				bro.parents("td.v_top").find("#partSelect").hide();
			} else {
				bro.parents("td.v_top").find("#partSelect").show();
			}
		}
		if (cont.parents("table").find("input#chkAll").length==cont.parents("table").find("input#chkAll:checked").length) {
			cont.parents("table").find("#chkAllContinent").attr("checked","checked")
		} else {
			cont.parents("table").find("#chkAllContinent").removeAttr("checked")
		}
	});
}
// 生成物流国家并缓存(大洲)
function createContinentTable(shippingCompany) {
	var continent = jQuery("#collapseOne");
	var tb = continent.find("table").clone();
	var divConts = tb.find("div#continentDiv");
	// 六个州
	for(var i=0; i<divConts.length; i++) {
		jQuery(divConts[i]).empty();
	}
	var shippings = shippingCostMap[shippingCompany].shipping;
	for (var i=0; i<shippings.length; i++) {
		var shipping = shippings[i];
		var selected = "";
		if (currentSelectCountry[shipping.country] != null) {
			selected = " checked='checked' "
			if (currentGroup.countries==null || currentGroup.countries[shipping.country]==null){
				selected += " disabled "
			}
		}
		if (shipping.countryObj.continent!=null && shipping.countryObj.continent>0) {
			var continentIdx = shipping.countryObj.continent;
			var div = jQuery(divConts[continentIdx-1]);
			div.append('<label class="checkbox"><input '+selected+' value="'+shipping.country+'" id="shippingCountry" name="shippingCountry" type="checkbox">'+shipping.countryObj.regionNameCn+'</label>');
		}
	}
	continentTable[shippingCompany] = tb;
}
// 生成物流国家并缓存(区)
function createAreaTable(shippingCompany) {
	var areaShippings = {};
	var shippings = shippingCostMap[shippingCompany].shipping;
	var areaCount = 0;
	for(var i=0; i<shippings.length; i++) {
		var shipping = shippings[i];
		var areaId = shipping.area;
		if (areaId != null && areaId > 0) {
			if (areaShippings[areaId] == null) {
				areaShippings[areaId] = [];
				areaCount++;
			}
			areaShippings[areaId].push(shipping);
		}
	}
	if (areaCount == 0) {
		jQuery("#collapseTwo").prev().find("a").hide();
	} else {
		jQuery("#collapseTwo").prev().find("a").show();
	}
	
	var area = jQuery("#collapseTwo");
	var tb = area.find("table").clone();
	tb.empty();
	var areaTds = [];
	for(var i=1; i<=areaCount; i++) {
		var td = jQuery('<td class="v_top"></td>');
		var len = areaShippings[i].length;
		td.append('<label class="checkbox left"><input id="chkAll" type="checkbox">'+i+'区（'+len+'）</label>');
		td.append('<a class="left mar_left_5" id="showAll" href="javascript:;">[显示全部]</a><a class="left mar_left_5 hide" id="hideAll" href="javascript:;">[收起]</a><span class="left mar_left_5 text-error hide" id="partSelect">部分选中</span>');
		var div = jQuery('<div id="area" class="alert clear black hide"></div>');
		td.append(div);
		for(var j=0; j<len; j++) {
			var shipping = areaShippings[i][j];
			var selected = "";
			if (currentSelectCountry[shipping.country] != null) {
				selected = " checked='checked' "
				if (currentGroup.countries==null || currentGroup.countries[shipping.country]==null){
					selected += " disabled "
				}
			}
			div.append('<label class="checkbox"><input '+selected+' id="shippingCountry" name="shippingCountry" value="'+shipping.country+'" type="checkbox">'+shipping.countryObj.regionNameCn+'</label>');
		}
		areaTds.push(td);
	}
	var tr = jQuery("<tr></tr>");
	tr.append(areaTds[0]);
	tr.append(areaTds[1]);
	tr.append(areaTds[2]);
	tb.append(tr);
	tr = jQuery("<tr></tr>");
	tr.append(areaTds[3]);
	tr.append(areaTds[4]);
	tr.append(areaTds[5]);
	tb.append(tr);
	tr = jQuery("<tr></tr>");
	tr.append(areaTds[6]);
	tr.append(areaTds[7]);
	tr.append(areaTds[8]);
	tb.append(tr);
	areaTable[shippingCompany] = tb;
}
var customShippingCompanyIdx = jQuery("input[id^='custCompany_']").length;
// 增加一个自定义运输方式
function addCustomShppingComany() {
	jQuery("tr#customShipping").show();
	customShippingCompanyIdx++;
	var idx = customShippingCompanyIdx;
	
	var tr = jQuery("<tr id='customRow"+idx+"'></tr>");
	jQuery("#add_zdy_ys_style").append(tr);
	// 前面的checkbox选择框
	tr.append('<td width="60"><input type="hidden" name="shippingCompany" value="custCompany_'+idx+'"/><input type="hidden" name="custCompany_'+idx+'_detailId"/></td>');
	tr.append('<td width="330" class="border_right"><label class="left mar_top_5 mar_right_10">名称：</label><input value="" type="text" id="custCompany_'+idx+'" name="custCompany_'+idx+'_name" onfocus="customShippingEnabled(this)" onblur="customShippingBlur(this)"></td>');
	tr.append('<td><label class="radio left mar_top_5"><input disabled id="custCompany_'+idx+'" name="custCompany_'+idx+'" value="freeshipping" id="custCompany_'+idx+'" type="radio">免运费</label> <label class="left mar_top_5 mar_left_20">物流天数</label> <input disabled type="text" placeholder="" id="custCompany_'+idx+'" name="custCompany_'+idx+'_transportDays" class="input-mini"></td>');
	tr.append('<td><label class="radio left"><input disabled id="custCompany_'+idx+'" name="custCompany_'+idx+'" data-toggle="modal" value="custom" type="radio" onclick="loadCustomSetting(this)">自定义运费</label><a class="left mar_left_10" onclick="removeCustomRow('+idx+')">删除</a></td>');
	tr.append('<td></td>');
	if (customShippingCompanyIdx >= 5) {
		jQuery("#addCustomShippingCompany").hide();
	}
}
function removeCustomRow(idx) {
	jQuery("#customRow" + idx).remove();
	customShippingCompanyIdx--;
	jQuery("#addCustomShippingCompany").show();
}
function customShippingEnabled(o) {
	var id = jQuery(o).attr("id");
	jQuery("input#" + id).removeAttr("disabled");
}
function customShippingBlur(o) {
	if (o.value == "") {
		var id = jQuery(o).attr("id");
		jQuery("input#" + id).attr("disabled", "disabled");
		jQuery(o).removeAttr("disabled");
	}
}
function disableOffInput(comp) {
	jQuery("input[name='"+comp+"_off']").attr("disabled","disabled");
}
// 确定自定义运费
function confirmCustom(){
	// 保存选定国家
	shippingCostMap[currShippingComp]["selectedCountry"] = currentSelectCountry;
	shippingCostMap[currShippingComp]["groups"] = currentGroups;
	
	// 保存已选定国家到form表单
	var cont = jQuery(":radio[id='"+currShippingComp+"'][value='custom']").parent().find("#optionDiv");
	if (cont.length==0){
		cont = jQuery("<div class='hide' id='optionDiv'></div>");
		jQuery(":radio[id='"+currShippingComp+"'][value='custom']").parent().append(cont);
	}
	cont.empty(); // 清空重构
	var i=1, comp=currShippingComp;
	for(var groupId in currentGroups) {
		if (currentGroups[groupId]==null) {
			continue;
		}
		var group = currentGroups[groupId];
		var shippingCountry = "";
		for (var id in group.countries) {
				shippingCountry += id + ",";
		}
		cont.append("<input type='hidden' name='"+comp + "_shippingCountry" + i+"' value='"+shippingCountry+"'/>");
		cont.append("<input type='hidden' name='"+comp + "_shippingType" + i+"' value='"+group.shippingType+"'/>");
		if (group.shippingType == "off") {
			cont.append("<input type='hidden' name='"+comp + "_discountRate" + i+"' value='"+group.discountRate+"'/>");
		} else if (group.shippingType == "custom") {
			cont.append("<input type='hidden' name='"+comp + "_weightPrice" + i+"' value='"+group.weightPrice+"'/>");
			cont.append("<input type='hidden' name='"+comp + "_reWeightPrice" + i+"' value='"+group.reWeightPrice+"'/>");
		}
		cont.append("<input type='hidden' name='"+comp + "_transportDays" + i+"' value='"+group.transportDays+"'/>");
		i++;
	}
	
	jQuery("input#"+currShippingComp+"[value='custom']").attr("checked","checked");
	disableOffInput(currShippingComp);
	jQuery('#custom_fee').modal('hide');
}
// 取消运费组合添加
function cancelAddGroup() {
	var count = 0;
	for(var item in currentGroups) {
		count++;
		break;
	}
	if (count>0) {
		jQuery("#accordion1").hide();
		jQuery("#shippingRateSetting").hide();
		jQuery("button#editGroupBtn").hide();
		jQuery("button#addGroupBtn").hide();
		jQuery("button#customBtn").show();
		jQuery("#addCustomShippingGroupDiv").show();
	} else {
		jQuery('#custom_fee').modal('hide');
	}
}
// 确定运费组合添加
function confirmAddGroup() {
	var areaTb = jQuery("#collapseOne").find("table");
	// 检查是否选择了国家
	if (areaTb.find("#shippingCountry:enabled:checked").length == 0) {
		alert("请选择国家");
		return ;
	}
	// 检查是否选择了运费方式或折扣
	var shippingType = jQuery("#shippingMethod").val();
	if (shippingType=="off") { // 校验折扣率
		if (jQuery("input#customDiscountRate").val() == "") {
			alert("请输入折扣率");
			return;
		}
	} else if (shippingType=="custom") {
		// 校验自定义运费设置是否输入
	}
	// 探测是否有旧数据，清空掉
	if (jQuery("#groupRow"+currentGroup.groupId) != null) {
		jQuery("#groupRow"+currentGroup.groupId).remove();
	}
	
	// 保存国家
	var groupId = currentGroup.groupId;
	currentGroup = {};
	currentGroup["groupId"] = groupId;
	currentGroup["countries"] = {};
	for (var i=0; i<areaTb.find("#shippingCountry:enabled:checked").length; i++) {
		currentGroup.countries[areaTb.find("#shippingCountry:enabled:checked")[i].value]=areaTb.find("#shippingCountry:enabled:checked")[i].value;
	}
	
	// 保存当前分组信息
	currentGroup["shippingType"] = shippingType;
	if (shippingType=="off") {
		currentGroup["discountRate"] = jQuery("input#customDiscountRate").val();
	} else if (shippingType=="custom") {
		currentGroup["weightPrice"] = jQuery("input#weightPrice").val();
		currentGroup["reWeightPrice"] = jQuery("input#reWeightPrice").val();
	}
	currentGroup["transportDays"] = jQuery("input#transportDays").val();
	currentGroups[currentGroup.groupId] = currentGroup;
	
	// 保存所有已选国家
	currentSelectCountry = {};
	for(var item in currentGroups) {
		var groupCountries = currentGroups[item].countries;
		for (var id in groupCountries) {
			currentSelectCountry[id] = id;
		}
	}
	
	// 保存选定信息到表头
	appendSelectedCountryRow(currShippingComp, currentGroup);

	// 设置样式
	jQuery("#accordion1").hide();
	jQuery("#shippingRateSetting").hide();
	jQuery("button#editGroupBtn").hide();
	jQuery("button#addGroupBtn").hide();
	jQuery("button#customBtn").show();
	jQuery("#addCustomShippingGroupDiv").show();
}
// 打开添加运费组合层
function addCustomShippingGroup() {
	var groupId = 0;
	var maxId = 1;
	for(var item in currentGroups) {
		if (currentGroups[item] == null) {
			groupId = parseInt(item);
		}
		if (maxId < item) {
			maxId = parseInt(item);
		}
	}
	if (groupId == 0) {
		groupId = maxId + 1;
	}
	currentGroup = {};
	currentGroup["groupId"] = groupId;
	createCountryDiv(currShippingComp);
	
	jQuery("#accordion1").show();
	jQuery("#shippingRateSetting").show();
	jQuery("button#editGroupBtn").hide();
	jQuery("button#addGroupBtn").show();
	jQuery("button#customBtn").hide();
	jQuery("#addCustomShippingGroupDiv").hide();
}
// 在层上方添加已确定运费组合
function appendSelectedCountryRow(shippingCompany, group) {
	var shortCountries = "";
	var countries = "";
	for(var item in group.countries) {
			var country = countryMap[item];
			if (country == null) {
				continue;
			}
			if (shortCountries.length < 100) {
				shortCountries += country.regionName + country.regionNameCn + ",";
			} else  {
				shortCountries += country.regionName + country.regionNameCn + ",";
				shortCountries = shortCountries.substring(0, 100) + "...";
			}
			countries += "<li>"+country.regionName + country.regionNameCn + "</li>";
	}
	var div = jQuery("#selectedCountryDiv");
	var divIn = jQuery('<div class="alert alert-info mar_bottom_5" id="groupRow'+group.groupId+'"></div>');
	div.append(divIn);
	var tb = jQuery('<table border="0" cellspacing="0" cellpadding="0" width="100%"></table>')
	divIn.append(tb);
	
	var shippingType = group.shippingType;
	if (shippingType == "off") {
		shippingType = "标准运费(折扣率"+group["discountRate"]+"%)"
	} else if (shippingType == "custom") {
		shippingType = "自定义运费"
	} else if (shippingType == "freeshipping") {
		shippingType = "卖家承担运费"
	} 
	
	if (tb.find("group" + group.groupId).length>0) {
		tr = tb.find("shortCountries").html(shortCountries);
	} else {
		var tr = jQuery('<tr></tr>');
		tr.append('<td width="25px"><strong id="groupId">'+group.groupId+'</strong>.</td>');
		tr.append('<td><ul class="unstyled" id="custom_fee_ul"><li>'+shortCountries+'<div class="alert padding_10"><ul class="inline">'+countries+'</ul></div></li></ul></td>');
		tr.append('<td width="160px"><span class="gray">'+shippingType+'</span></td>')
		tr.append('<td width="70px"><a href="javascript:;" class="left" onclick="editGroup('+group.groupId+')">编辑</a><a href="javascript:;" class="left mar_left_10" onclick="removeGroup('+group.groupId+')">删除</a></td>');
		tb.append(tr);
	}
}
// 编辑运费组合
function editGroup(groupId) {
	cancelEditGroup();
	currentGroup = currentGroups[groupId];
	createCountryDiv(currShippingComp);
	
	var shippingType = currentGroup.shippingType;
	jQuery("#shippingMethod").val(shippingType);
	jQuery("#shippingMethod").change();
	if (shippingType == "off") {
		jQuery("input#customDiscountRate").val(currentGroup.discountRate);
	} else if (shippingType == "custom") {
		jQuery("input#weightPrice").val(currentGroup.weightPrice);
		jQuery("input#reWeightPrice").val(currentGroup.reWeightPrice);
	}
	jQuery("input#transportDays").val(currentGroup.transportDays);
	
	jQuery("#groupRow"+groupId).hide()
	
	jQuery("#accordion1").show();
	jQuery("#shippingRateSetting").show();
	jQuery("button#editGroupBtn").show();
	jQuery("button#addGroupBtn").hide();
	jQuery("button#customBtn").hide();
	jQuery("#addCustomShippingGroupDiv").hide();
}
// 取消运费组合编辑
function cancelEditGroup() {
	jQuery("#groupRow"+currentGroup.groupId).show()
	
	jQuery("#accordion1").hide();
	jQuery("#shippingRateSetting").hide();
	jQuery("button#editGroupBtn").hide();
	jQuery("button#addGroupBtn").hide();
	jQuery("button#customBtn").show();
	jQuery("#addCustomShippingGroupDiv").show();
}
// 删除设置好的运费组合
function removeGroup(groupId) {
	jQuery("#groupRow"+groupId).remove();
	currentGroups[groupId] = null;
	
	currentSelectCountry = {};
	for(var item in currentGroups) {
		var groupCountries = currentGroups[item].countries;
		for (var id in groupCountries) {
				currentSelectCountry[id] = id;
		}
	}
	if (jQuery("div[id^='groupRow']").length == 0) {
		addCustomShippingGroup();
	}
}
// 加号变化
function changeExpan(o) {
	var span = jQuery(o).find("span");
	var i = span.attr("class").indexOf("icon-minus");
	
	jQuery(".icon-minus").addClass("icon-plus");
	jQuery(".icon-minus").removeClass("icon-minus");
	if(i>-1) {
		span.addClass("icon-plus");
	} else {
		span.addClass("icon-minus");
	}
}
// 
function saveShippingInfo() {
//	if (jQuery("#shippingName").val() == "") {
//		alert("请输入模版名称！");
//		return;
//	}
	if (jQuery("#shippingInfoForm").find(":radio:enabled:checked").length == 0) {
		alert("请选择物流方式");
		return;
	}
	
	var param = form2URL("shippingInfoForm");
	jQuery.ajax({
		url:"/manager/action/shipping.AjaxSaveShipping",
		type:"post",
		data:param,
		dataType:"json",
		success:function(data) {
			if (data.status == 1) {
				jQuery("#add_feeTemplet").hide();
				jQuery("#shippings").show();
				alert("成功");
			} else {
				alert("失败!");
			}
		}
	});
}

function initShippingTemplate(shippingInfo) {
	jQuery("#add_feeTemplet").attr("shippingId", shippingInfo.shippingInfoId);
	var details = shippingInfo.details;
	var custCompIdx = 0;
	for(var i=0; i<details.length; i++) {
		var detail = details[i];
		var comp = detail.shippingCompany;
		if (jQuery("input:checkbox#"+comp).length == 0) {
			customShippingCompanyIdx = custCompIdx;
			custCompIdx++;
			addCustomShppingComany();
			jQuery("input:text[id='custCompany_"+custCompIdx+"']")[0].value=decodeHtmlUnicode(comp);
			jQuery("input:text[id='custCompany_"+custCompIdx+"']")[1].value=detail.transportDays;
			jQuery("input[name='custCompany_"+custCompIdx+"_detailId']").val(detail.detailId);
			jQuery("input#custCompany_"+custCompIdx).removeAttr("disabled");
			jQuery("input#custCompany_"+custCompIdx+"[value='"+detail.shippingType+"']").attr("checked","checked");
			if (detail.shippingType == "custom") {
				initShippingOptions("custCompany_"+custCompIdx, detail.options);
			}
		} else {
			jQuery("input:checkbox#"+comp).attr("checked","checked");
			jQuery("input:checkbox#"+comp).after('<input type="hidden" name="' + comp + '_detailId" value="'+detail.detailId+'"/>');
			jQuery("input#" + comp).removeAttr("disabled");
			jQuery("input#" + comp+"[value='"+detail.shippingType+"']").attr("checked","checked");
			if (detail.shippingType == "off") {
				jQuery("input[name='" + comp + "_off']").removeAttr("disabled");
				jQuery("input[name='" + comp + "_off']").val(detail.discountRate);
			} else if (detail.shippingType == "custom") {
				initShippingOptions(comp, detail.options);
			}
		}
	}
}
function initShippingOptions(comp, options) {
	if (shippingCostMap[comp] == null) {
		shippingCostMap[comp]={};
	}
	if (shippingCostMap[comp]["groups"]==null) {
		shippingCostMap[comp]["groups"] = {};
	}
	if (shippingCostMap[comp]["selectedCountry"]==null) {
		shippingCostMap[comp]["selectedCountry"] = {};
	}
	
	
	// 保存已选定国家到form表单
	var cont = jQuery(":radio[id='"+comp+"'][value='custom']").parent().find("#optionDiv");
	if (cont.length==0){
		cont = jQuery("<div class='hide' id='optionDiv'></div>");
		jQuery(":radio[id='"+comp+"'][value='custom']").parent().append(cont);
	}
	cont.empty(); // 清空重构
	for (var j=1; j<=options.length; j++) {
		shippingCostMap[comp]["groups"][j]={};
		shippingCostMap[comp]["groups"][j]["groupId"]=j;
		shippingCostMap[comp]["groups"][j]["countries"]={};
		var group = options[j-1];
		if (group.shippingType == "off") {
			cont.append("<input type='hidden' name='"+comp + "_discountRate" + j+"' value='"+group.discountRate+"'/>");
			shippingCostMap[comp]["groups"][j]["discountRate"]=group.discountRate;
		} else if (group.shippingType == "custom") {
			cont.append("<input type='hidden' name='"+comp + "_weightPrice" + j+"' value='"+group.weightPrice+"'/>");
			cont.append("<input type='hidden' name='"+comp + "_reWeightPrice" + j+"' value='"+group.reWeightPrice+"'/>");
			shippingCostMap[comp]["groups"][j]["weightPrice"]=group.weightPrice;
			shippingCostMap[comp]["groups"][j]["reWeightPrice"]=group.reWeightPrice;
		}
		cont.append("<input type='hidden' name='"+comp + "_transportDays" + j+"' value='"+group.transportDays+"'/>");
		shippingCostMap[comp]["groups"][j]["transportDays"]=group.transportDays;
		cont.append("<input type='hidden' name='"+comp + "_shippingCountry" + j+"' value='"+group.shippingCountry+"'/>");
		cont.append("<input type='hidden' name='"+comp + "_shippingType" + j+"' value='"+group.shippingType+"'/>");
		shippingCostMap[comp]["groups"][j]["shippingType"]=group.shippingType;
		shippingCostMap[comp]["groups"][j]["shippingCountry"]=group.shippingCountry;
		var countries = group.shippingCountry.split(",");
		for(var k=0; k<countries.length; k++) {
			var countryId = countries[k];
			if (countryId != "") {
				shippingCostMap[comp]["groups"][j].countries[countryId]=countryId;
				shippingCostMap[comp]["selectedCountry"][countryId]=countryId;
			}
		}
	}
}