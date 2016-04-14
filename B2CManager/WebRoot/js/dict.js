function getVaildStatus(status) {
	switch(status) {
	case 0:
		return '禁用';
	case 1:
		return '启用';
	default:
		return '未知状态';
	}
}
function getSupplierLevel(supplierLevel) {
	switch (supplierLevel) {
	case 1:
		return "普通";
	case 2:
		return "高级";
	}
	return null;
}
function getSupplierType(supplierType) {
	switch (supplierType) {
	case 1:
		return "个人";
	case 2:
		return "工厂";
	case 3:
		return "贸易公司";
	}
	return null;
}
function getProductStatus(productStatus) {
	switch (productStatus) {
	case -1:
		return "垃圾箱";
	case 0:
		return "下架产品";
	case 1:
		return "在销产品";
	case 2:
		return "草稿箱";
	case 3:
		return "批量导入产品";
	}
	return null;
}