//检测邮箱
function isEmail(str) { 
	var reg=/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
	return reg.test(str);
} 
//检查用户名  	     
function validUsername(str) {
	var pattern = /^[a-zA-Z][a-zA-Z0-9]*[a-zA-Z0-9]$/;
	return pattern.test(str); 
}
function checkByteLength(str,minlen,maxlen) {
	if (str == null) return false;
	var l = str.length;
	var blen = 0;
	for(i=0; i<l; i++) {
		if ((str.charCodeAt(i) & 0xff00) != 0) {
			blen ++;
		}
		blen ++;
	}
	if (blen > maxlen || blen < minlen) {
		return false;
	}
	return true;
}
function chkName(obj) {
	var name = jQuery(obj).val();
	jQuery(obj).parents("td").find("label").each(function(i,o){jQuery(o).hide();});
	if (name=="") {
		jQuery(obj).parents("td").find("#empty").show();
		return false;
	}
	return true;
}