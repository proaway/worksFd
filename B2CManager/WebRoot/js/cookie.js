//写cookies
function setCookie(name, value, days){
	var str = name + "="+ escape (value)
	if (days > 0) {
		var exp = new Date(); 
		exp.setTime(exp.getTime() + days*24*60*60*1000);
		str = str + ";expires=" + exp.toGMTString()+";path=/;domain=.fashion.com";
	}
	document.cookie = str;
}
//读取cookies
function getCookie(name) {
	var arr;
	var reg = new RegExp("(^| )"+name+"=([^;]*)(;|$)");
	if(arr=document.cookie.match(reg)) 
		return unescape(arr[2]);
	else 
		return null;
}

//删除cookies
function delCookie(name) {
	var exp = new Date();
	exp.setTime(exp.getTime() - 1);
	var cval=getCookie(name);
	if(cval!=null) 
		document.cookie= name + "="+cval+";expires="+exp.toGMTString();
}