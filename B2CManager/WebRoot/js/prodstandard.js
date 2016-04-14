jQuery(document).ready(function(){
	// 价格
	jQuery("input#retailPrice").blur(function(){
		checkPrice();
		var price = Math.round(this.value * commission*100)/100;
		jQuery("#priceSpan").html("$"+price);
	});
	// 折扣设置
	jQuery("#if_discount").click(function(){
		if (this.checked) {
			if (jQuery('#discount_content').css("display")=="none") {
				jQuery(this).parent().next().hide();
		    	jQuery('#discount_content').show();
			} else {
				jQuery('#discount_content').hide();
			}
	    	return false;
		} else {
			jQuery("#discount_content_sure").hide();
			jQuery("input#configDiscount").attr("disabled", "disabled");
		}
	});
	var discountContent = jQuery("#discount_content");
	discountContent.find("button[id='cancel']").click(function(){
		if (jQuery("#if_discount")[0].checked) {
			jQuery("#discount_content_sure").show();
		}
		discountContent.hide();
	});
	discountContent.find("button[id='confirm']").click(function(){
		if (discountContent.find("#discount").val() == "") {
			discountContent.find("#err").html("请您填写商品折扣值！");
			return false;
		}
		if (discountContent.find("#discount_datepickera").val() == "" || 
			discountContent.find("#discount_datepickerb").val() == "" ) {
			discountContent.find("#err").html("请您选择折扣时间！");
			return false;
		}
		discountContent.hide();
		var contentSure = jQuery("#discount_content_sure");
		contentSure.find("#startDate").html(discountContent.find("#discount_datepickera").val());
		contentSure.find("#endDate").html(discountContent.find("#discount_datepickerb").val());
		contentSure.find("#discount").html(discountContent.find("#discount").val() + "%");
		var discount = parseInt(discountContent.find("#discount").val());
		contentSure.find("#discountShow").html(Math.round((1-discount/100)*100)/10);
		contentSure.show();
		jQuery("#if_discount").attr("checked", "checked");
		jQuery("input#configDiscount").removeAttr("disabled");
	});
	discountContent.find("input#discount").focus(function(){
		discountContent.find("#err").html("");
	});
	// 批发设置
	jQuery("#if_wholesale").click(function(){
		if (this.checked) {
			if (jQuery('#wholesale_content').css("display")=="none") {
				jQuery(this).parent().next().hide();
		    	jQuery('#wholesale_content').show();
			} else {
				jQuery('#wholesale_content').hide();
			}
	    	return false;
		} else {
			jQuery("#wholesale_content_sure").hide();
			jQuery("input#configWholesale").attr("disabled", "disabled");
		}
	});
	var wholeContent = jQuery("#wholesale_content");
	wholeContent.find("button[id='cancel']").click(function(){
		if (jQuery("#if_wholesale")[0].checked) {
			jQuery("#wholesale_content_sure").show();
		}
		wholeContent.hide();
	});
	wholeContent.find("button[id='confirm']").click(function(){
		if (wholeContent.find("#wholesaleCount").val() == "" || 
			wholeContent.find("#wholesaleDiscount").val() == "" ) {
			wholeContent.find("#err").show();
			return false;
		}
		wholeContent.find("#err").hide();
		wholeContent.hide();
		var wholeSure = jQuery("#wholesale_content_sure");
		wholeSure.find("#wholesaleCount").html(wholeContent.find("#wholesaleCount").val());
		wholeSure.find("#wholesaleDiscount").html(wholeContent.find("#wholesaleDiscount").val() + "%");
		var discount = parseInt(wholeContent.find("#wholesaleDiscount").val());
		wholeSure.find("#wholesaleDiscountShow").html(Math.round((1-discount/100)*100)/10);
		wholeSure.show();
		jQuery("#if_wholesale").attr("checked", "checked");
		jQuery("input#configWholesale").removeAttr("disabled");
	});
	// 批量设置零售价
	jQuery("#setAllPrice").click(function(){
		if (jQuery(this).prev().val() == "") {
			jQuery(this).next().show();
			return;
		}
		jQuery(this).next().hide();
		var price = jQuery(this).prev().val();
		jQuery("input[id^='configPrice']").val(price);
		jQuery("input[id^='configPrice']").change();
	});
	// 批量设置折扣
	jQuery("#setAllDiscount").click(function(){
		for(i=0; i<jQuery("input#configDiscount").length; i++) {
			var chk = jQuery("input#configDiscount")[i];
			if (jQuery(chk).attr("disabled") == null) {
				chk.checked = this.checked;
			}
		}
	});
	// 批量设置批发
	jQuery("#setAllWholesale").click(function(){
		for(i=0; i<jQuery("input#configWholesale").length; i++) {
			var chk = jQuery("input#configWholesale")[i];
			if (jQuery(chk).attr("disabled") == null) {
				chk.checked = this.checked;
			}
		}
	});
});
// 判断零售价是否填写
function getRetailPrice() {
	if (jQuery("input[id^='configPrice']").length>0) { // 有规格
		var len = jQuery("input[id^='configPrice']").length;
		for(var i=0; i<len; i++) {
			var configPrice = jQuery("input[id^='configPrice']")[i];
			if (configPrice.value > 0) {
				return configPrice.value;
			}
		}
		return 0;
	} else  {
		if(jQuery("input#retailPrice").val()=="") {
			return 0;
		}
		var price = parseFloat(jQuery("input#retailPrice").val());
		return price;
	}
}