// 保存最近浏览产品ID，最多6个
function saveHistory(prodId) {
	var viewedProd = getCookie("ProductHistory");
	var viewedProdIds = prodId;
	if (viewedProd != null && viewedProd != "") {
		var viewedProds = viewedProd.split(",");
		for(i=0; i<viewedProds.length && i<6; i++) {
			var id=viewedProds[i];
			if (id != prodId) {
				viewedProdIds +=  "," + id;
			}
		}
	}
	setCookie("ProductHistory", viewedProdIds, 0);
}