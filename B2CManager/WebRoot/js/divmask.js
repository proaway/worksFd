jQuery("document").ready(function(){
	var blocks = jQuery("[block]");
	var blockIds = [];
	for(var i=0; i<blocks.length; i++) {
		var block = blocks[i];
		var blockId = jQuery(block).attr("block");
		var content = jQuery(block).html();
		var productIdReg = /product\/[^_\/]*_(\d+)\.html/ig;
		var pid = productIdReg.exec(content)[1];
		if (pid != null) {
			blockIds.push(blockId+"_"+pid);
		}
		block.append('<div mask class="cms_page_mask center_t"><input type="button" onclick="showEditBlock(\''+blockId+'\')" class="btn btn-success mar_top_130 pad_left_30 pad_right_30" value="编辑产品"/> </div>');
	}
	if (productId.length > 0) {
		jQuery.getJSON("/manager/action/cms,AjaxGetInvalidProd", {blockIds:blockIds}, function(data){
			for(var i=0; i<data.length; i++) {
				var block = data[i];
				jQuery("[block='"+block.blockId+"']").find("[mask]").removeClass("cms_page_mask");
				jQuery("[block='"+block.blockId+"']").find("[mask]").addClass("cms_page_mask_off");
			}
		});
	}
});
function showEditBlock(blockId){
	var div = jQuery("#blockDiv");
	if (div.length == 0) {
		div = jQuery("<div id='blockDiv'></div>");
		jQuery("body").append(div);
	}
	div.empty();
	div.load("/manager/action/cms,EditBlock?blockId=" + blockId);
}
