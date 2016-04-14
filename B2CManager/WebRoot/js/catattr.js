function CatAttribute(catId, attrNodes) {
	var attrTb = jQuery("#attrTb");
	attrTb.empty();
	var fixNodes=[], sysNodes=[];
	var k = 1;
	// 属性拆分成固定值和非固定值两种
	for(var i=0; i<attrNodes.length; i++) {
		var node = attrNodes[i];
		if (node.nodes.length>0) {
			if (node.attr.showType == "fixValue") {
				fixNodes.push(node);
			} else {
				sysNodes.push(node);
			}
		}
	}
	// 先加载固定值属性
	for(var i=0; i<fixNodes.length; i++) {
		var node = fixNodes[i];
		var tr = jQuery("<tr></tr>");
		tr.append("<td class='td_136_r'>"+node.attr.valueCn+"：<input type='hidden' name='attrTitle"+k+"' value='"+node.attr.id+"'/></td>");
		var tdSub = jQuery("<td></td>");
		createSubContent(node, tdSub, k);
		tr.append(tdSub);
		attrTb.append(tr);
		k++;
	}
	
	// 后加载非固定值属性
	for(var i=0; i<sysNodes.length; i++) {
		var node = sysNodes[i];
		var tr = jQuery("<tr></tr>");
		tr.append("<td class='td_136_r'>"+node.attr.valueCn+"：<input type='hidden' name='attrTitle"+k+"' value='"+node.attr.id+"'/></td>");
		var tdSub = jQuery("<td></td>");
		createSubContent(node, tdSub, k);
		tr.append(tdSub);
		attrTb.append(tr);
		k++;
	}
	attrTb.append("<input type='hidden' name='attrCount' value='"+attrNodes.length+"'/>");
	
	// 创建属性内容
	function createSubContent(node, tdSu, k) {
//		if (node.nodes.length == 0) {
//			return;
//		}
		var style = node.attr.showType;
		if (style=="fixValue") { // 固定值
			createFixValue(node, tdSub);
			tdSub.find("select").attr("name", "attrId"+k)
		} else if (style=="list_box") { // 列表值
			createListBox(node, tdSub);
			tdSub.find("select").attr("name", "attrId"+k)
		} else if (style=="check_box") { // 复选框
			createCheckBox(node, tdSub);
			tdSub.find("input").attr("name", "attrId"+k)
		} else if (style=="input_string" || style=="interval") { // 文本输入框
			createInputText(node, tdSub);
			tdSub.find("input").attr("name", "attrValue"+k)
		} else if (style=="input_int") { // 整型输入框
			createInputInt(node, tdSub);
			tdSub.find("input").attr("name", "attrValue"+k)
		} else if (style=="unit") { // 隐藏单位名称
			createUnit(node, tdSub);
			tdSub.find("select").attr("name", "attrId"+k)
		}
	}
	// 固定值
	function createFixValue(node, tdSub) {
		for(var j=0; j<node.nodes.length; j++) {
			var nd = node.nodes[j];
			if (nd.attr.value == "") {
				tdSub.append(nd.attr.valueCn);
			} else {
				tdSub.append(" "+nd.attr.value + "(" + nd.attr.valueCn + ")");
			}
			tdSub.append("<input type='hidden' value='"+nd.attr.id+"'/>");
		}
	}
	// 复选框
	function createCheckBox(node, tdSub) {
		tdSub.addClass("norms_format");
		for(var j=0; j<node.nodes.length; j++) {
			var nd = node.nodes[j];
			var td = "<label class='checkbox'> <input value='"+nd.attr.id+"' type='checkbox'> "+nd.attr.value+"（"+nd.attr.valueCn+"） </label>";
			tdSub.append(td);
		}
	}
	// 列表值
	function createListBox(node, tdSub) {
		var select = jQuery("<select></select>");
		if (node.attr.showStyle != "selected") {
			select.append("<option value=''>请选择</option>");
		}
		for(var j=0; j<node.nodes.length; j++) {
			var nd = node.nodes[j];
			select.append("<option value='"+nd.attr.id+"'>"+nd.attr.value+"（"+nd.attr.valueCn+"）</option>");
		}
		tdSub.append(select);
	}
	// 文本输入框
	function createInputText(node, tdSub) {
		var nd = node.nodes[0];
		var td = "<input class='span3' type='text' placeholder='"+node.attr.valueCn+"'>";
		tdSub.append(td);
		//createSubContent(nd, tdSub);
	}
	// 整型输入框
	function createInputInt(node, tdSub) {
		var nd = node.nodes[0];
		var td = "<input class='span3' type='text' placeholder='"+node.attr.valueCn+"'>";
		tdSub.append(td);
		//createSubContent(nd, tdSub);
	}
	// 浮点型输入框
	function createInputFloat(node, tdSub) {
		var nd = node.nodes[0];
		var td = "<input class='span3' type='text' placeholder='"+node.attr.valueCn+"'>";
		tdSub.append(td);
		//createSubContent(nd, tdSub);
	}
	// unit
	function createUnit(node, tdSub) {
		var nd = node.nodes[0];
		createSubContent(nd, tdSub);
	}
	
	var setProductAttribute = function(productAttrs){
		for(var i=0; i<productAttrs.length; i++) {
			var prodAttr = productAttrs[i];
			var attrId = prodAttr.valueAttrId;
			var value = prodAttr.value;
			var o = jQuery("table#attrTb").find("[value='"+attrId+"']")[0];
			
			if (o.localName == "input" && o.type=="text") { // 文本框
				o.value = value;
			} else if (o.localName == "input" && o.type=="checkbox") { // 复选框
				o.checked = true;
			} else if (o.localName == "option") {
				jQuery(o).parent().val(attrId);
			}
		}
	};
	
	return {
		setProductAttribute : setProductAttribute
	}
}