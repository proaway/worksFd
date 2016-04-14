/**
 * 数据校验js，用法示例：
 * var ProductVerify = new VerifyUtil();
 * ProductVerify.addParam({id:"productName", isNotNull:true, maxLength:200, invalidReg:/[^\w\.\/ \-\']/ig, errmsg:"产品名称错误，只能由数字、字母、下划线、空格、撇号组成，长度不超过200字符。"});
 * ProductVerify.addParam({id:"productName", fn:verifyProductNameRepeat, errmsg:"产品名称已存在，请更换。"});
 * ProductVerify.addParam({id:"keyword", maxLength:200, errmsg:"关键词错误，长度不超过200字符。"});
 * 
 * return ProductVerify.verified();
 * 
 * 方法说明：
 * verified ：无参数，通过校验返回true，没通过返回false
 * addParam ：添加校验参数，具体见校验参数说明
 * removeParam ：id，字符串，删除id号等于传入参数的校验参数
 * verifyList ：无参数，返回所有校验参数列表
 * getParams ：id，字符串，返回指定id号的校验参数列表
 * 
 * 校验参数说明如下：
 * id : 必填，字符串，用于获取校验值的ID号，
 * focusId : 可选，字符串，出错信息定位时，用于获取焦点的ID号，如果没填，默认将使用id项设置
 * valueAttr ：可选，字符串，用于获取校验值的属性名称，默认"value"
 * isNotNull : 可选，(true|false)，判断是否非空
 * maxLength : 可选，整数，最大长度
 * isNum : 可选，(true|false)，判断是否数值
 * isInt : 可选，(true|false)，判断是否整数
 * isMail : 可选，(true|false)，判断是否邮箱地址
 * validReg : 可选，正则表达式，判断是否必须包含某项正则，没通过正则测试算数据校验失败
 * invalidReg : 可选，正则表达式，判断是否不能包含某项正则，通过正则测试算数据校验失败
 * fn : 可选，function，特殊校验时，自定义的校验方法，返回true算通过数据校验，false算数据校验失败
 * errmsg : 必填，字符串，数据没通过校验时返回的提示信息
 * errId : 用于显示错误信息的id
 */
function VerifyUtil(showWindow) {
	if (showWindow == null) {
		showWindow = true;
	}
	var verifyList = [];
	var mailReg = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
	var intReg = /^-?[0-9]+$/;
	var errors = [];
	var verified = function() {
		errors = [];
		for(var i=0; i<verifyList.length; i++) {
			var param = verifyList[i];
			if (param == null) {
				continue;
			}
			if (param.focusId == null) {
				param["focusId"] = param.id;
			}
			var obj = jQuery("*#" + param.id);
			if (obj.length == 0) {
				continue;
			}
			var pre = obj.prevAll("a[name='a_"+param.id+"']");
			if (pre.length == 0) {
				pre = jQuery("<a name='a_"+param.id+"'></a>");
				obj.before(pre);
			}
			if (param.valueAttr == null) {
				param["valueAttr"] = "value";
			}
			if (obj.attr(param.valueAttr) != null) {
				var value = jQuery.trim(obj.attr(param.valueAttr));
				// 非空，放到最前面校验
				if (param.isNotNull!=null && param.isNotNull && (value == null || value == "")) {
					addError(param);
					continue;
				} else if (value == null || value == "") {
					continue;
				}
				// 最大长度
				var maxLength = param.maxLength;
				if (maxLength != null && returnLen(value) > maxLength) {
					addError(param);
					continue;
				}
				// 数字
				if (param.isNum!=null && param.isNum && isNaN(value)) {
					addError(param);
					continue;
				}
				// 整数
				if (param.isInt!=null && param.isInt && !intReg.test(value)) {
					addError(param);
					continue;
				}
				// 必须通过校验的正则
				if (param.validReg!=null && !param.validReg.test(value)) {
					addError(param);
					continue;
				}
				// 不能通过校验的正则
				if (param.invalidReg!=null && param.invalidReg.test(value)) {
					addError(param);
					continue;
				}
				// 邮箱地址
				if (param.isMail!=null && param.isMail && !mailReg.test(value)) {
					addError(param);
					continue;
				}
			}
			// 自定义校验方法，返回false为不通过校验
			if (param.fn != null) {
				if (!param.fn()) {
					addError(param);
				}
				continue;
			}
		}
		if (errors.length>0) {
			for(var i=0; i<errors.length; i++) {
				var err = errors[i];
				if (err.errId != null) {
					jQuery("#" + err.errId).html(err.errmsg);
					jQuery("#" + err.errId).show();
				}
			}
			if (showWindow) {
				if (jQuery("#paramErrDiv").length == 0) {
					jQuery("body").append("<div id='paramErrDiv' class='hide'></div>");
				}
				jQuery("#paramErrDiv").empty();
				for(var i=0; i<errors.length; i++) {
					var err = errors[i];
					jQuery("#paramErrDiv").append("<span align='left'>"+err.errmsg+"</span> <a onclick='gotoAnchor(\""+err.id+"\",\""+err.focusId+"\")'>定位</a><br/>");
				}
				tipsWindown("数据校验失败","id:paramErrDiv","350","200","true","","false","text","关闭");
			}
		}
		return errors == 0;
	}
	
	function addError(param) {
		errors.push(param);
	}
	
	var addParam = function (param) {
		verifyList.push(param);
	}
	
	var removeParam = function (id) {
		for(var i=verifyList.length-1; i>=0; i--) {
			var param = verifyList[i];
			if (param.id == id) {
				verifyList.splice(i,1);
			}
		}
	}
	
	var setVerifyList = function (vlist) {
		verifyList = vlist
	}
	
	var getParams = function (id) {
		var params = [];
		for(var i=verifyList.length-1; i>=0; i--) {
			var param = verifyList[i];
			if (param.id == id) {
				params.push(param);
			}
		}
		return params;
	}
	
	function returnLen(strObj) {
		var len = 0;
		for (p = 0; p < strObj.length; p++) {
			if (strObj.charCodeAt(p) > 256) {
				len += 2;
			} else {
				len++;
			}
		}
		return len;
	}
	
	return {
		verified : verified,
		addParam : addParam,
		removeParam : removeParam,
		verifyList : verifyList,
		getParams : getParams
	}
}
// 跳转到锚点
function gotoAnchor(id, focusId) {
	location.hash="a_" + id;
	var left = jQuery("body").width() - 200;
	jQuery("#windown-box").animate({left: left},300)
	jQuery("*#" + focusId).focus();
}