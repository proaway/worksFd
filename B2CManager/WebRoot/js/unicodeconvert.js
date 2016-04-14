function decodeHtmlUnicode(val) {
	var reg = /&#([0-9]+);/
	while(reg.test(val)) {
		var v = reg.exec(val)[1];
		v = String.fromCharCode(v);
		val = val.replace(reg, v);
	}
	return val;
}