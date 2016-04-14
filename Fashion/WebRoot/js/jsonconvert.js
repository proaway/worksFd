/**
 * 将form表单里的所有参数初始化到JSON对象中返回
 * 
 * @param formId form表单的ID
 * @returns
 */
function form2JSON(formId) {
	return form2JSONbySelector("#" + formId + " input,#" + formId + " select,#" + formId + " textarea,#" + formId + " button");
}

/**
 * 使用jQuery选择器，将符合选择器规则的表单数据转成JSON格式
 * 
 * @param selector
 * @returns
 */
function form2JSONbySelector(selector) {
	var objs = jQuery("*").find(selector);
	var params = {};
	if (objs.length > 0) {
		for ( i = 0; i < objs.length; i++) {
			var o = objs[i];
			var name=jQuery(o).attr("name");
			var value=jQuery(o).attr("value");
			var disabled = o.disabled;
			var type = jQuery(o).attr("type");
			if (name != null && name != "" && value!=null && value!="") {
				if (disabled != null && disabled) {
					continue;
				}
				var append = true;
				if (type=="checkbox" || type=="radio") {
					append = o.checked;
				}
				if (append) {
					if (params[name]==null) {
						params[name] = encodeURIComponent(value);
					} else {
						if (jQuery(params[name]) != "array") {
							var oldVal = params[name];
							params[name] = [];
							params[name].push(oldVal);
						}
						params[name].push(encodeURIComponent(value));
					}
				}
			}
		}
	}
	return params;
}

/**
 * 将form表单里的所有参数初始化成参数字符串返回
 * 
 * @param formId form表单的ID
 * @returns
 */
function form2URL(formId) {
	return form2URLbySelector("#" + formId + " input,#" + formId + " select,#" + formId + " textarea,#" + formId + " button");
}

/**
 * 使用jQuery选择器，将符合选择器规则的表单数据转成参数字符串格式
 * 
 * @param selector
 * @returns
 */
function form2URLbySelector(selector) {
	var objs = jQuery("*").find(selector);
	var params = "";
	if (objs.length > 0) {
		for ( i = 0; i < objs.length; i++) {
			var o = objs[i];
			var name=jQuery(o).attr("name");
			var value=jQuery(o).attr("value");
			//alert(value);
			var disabled = o.disabled;
			var type = jQuery(o).attr("type");
			if (name != null && name != "" && value!=null && value!="") {
				if (disabled) {
					continue;
				}
				var append = true;
				if (type=="checkbox" || type=="radio") {
					append = o.checked;
				}
				if (append) {
					params += '&' + name + '=' + encodeURIComponent(value); 
				}
			}
		}
	}
	if (params.length>0) {
		params = params.substring(1);
	}
	return params;
}