/**
 * Category selector
 * <input type='hidden' name='baseCatId' id='baseCatId' value="$!cond.baseCatId"/>
 * <script language="javascript" src="$content.getURI('js/categoryselector.js')"></script> 
 * <script>
 * new CategorySelector(4, "baseCatId");
 * </script>
 */
function CategorySelector(level, tarObjId, showId) {
	level = level <= 4 ? level : 4;
	level = level <= 0 ? 1 : level;
	
	var catMap = {};
	var categoriesMap = {};
	var catparents;
	
	jQuery.getJSON("/manager/action/category.AjaxGetcategories?catId=", function(data) {
		addCatOptions(data, 1);
		catparents = data;
		if (jQuery("#"+tarObjId).val() != "") {
			selectCatId(jQuery("#"+tarObjId).val());
		}
		jQuery("body").hideLoading();
	});
//	jQuery.ajax({
//    	url:"/manager/action/category.AjaxGetcategories?catId=",
//    	type:"post",
//    	async:false,
//    	data:"",
//    	dataType:"json",
//    	success:function(data){
//			addCatOptions(data, 1);
//			catparents = data;
//			if (jQuery("#"+tarObjId).val() != "") {
//				selectCatId(jQuery("#"+tarObjId).val());
//			}
//		}
//	 });	

	function addCatOptions(data, id) {
		var s = jQuery("#catId" + id);
		for(i=id; i<=level; i++) {
			removeCatOptions(i);
		}
		for(i=0; i<data.length; i++) {
			var cat = data[i];
			s.append("<option value='"+cat.catId+"'>" + cat.catNameCn + "</option>");
			categoriesMap[cat.catId] = cat;
		}
		s.unbind();
		if (id < level) {
			// 绑定事件，获取下级目录
			s.bind("change", function(){
				var catId = jQuery("#catId" + id).val();
				readSubCats(catId);
			});
		} else {
			s.bind("change", function(){
				var catId = this.value;
				setCatId(catId, id);
				jQuery("#confirmCatBtn").removeAttr("disabled");
			});
		}
	}
	function readSubCats(catId) {
		if(catMap[catId] == null) {
			jQuery.ajax({
	        	url:"/manager/action/category.AjaxGetcategories?catId=" + catId,
	        	type:"post",
	        	async:false,
	        	data:"",
	        	dataType:"json",
	        	success:function(data){
					catMap[catId] = data; // 缓存目录
				}
	    	 });	
		} 
		var data = catMap[catId];
		addCatOptions(data, catId.length/3 + 1);
		if (data.length > 0) {
			jQuery("#confirmCatBtn").attr("disabled","");
		} else {
			jQuery("#confirmCatBtn").removeAttr("disabled");
		}
		setCatId(catId, catId.length/3);
		return data;
	}
	function removeCatOptions(id) {
		jQuery("#catId" + id).empty();
	}
	function setCatId(catId, id) {
		jQuery("#"+tarObjId).val(catId);
		if (catId == "0" && id > 1) {
			catId = jQuery("#catId" + (id-1) + " option:selected").val();
			jQuery("#"+tarObjId).val(catId);
		} else if (catId == "0" && id == 1) {
			jQuery("#"+tarObjId).val("");
		}
		jQuery("#"+tarObjId).change();
		var selectName = "";
		for(var i=1; i<=id; i++) {
			var cat = categoriesMap[jQuery("#catId" + i + " option:selected").val()];
			var cname = cat.catNameCn + "(" + cat.catName + ")";
			if (i==1) {
				selectName = cname;
			} else {
				selectName += "&gt;&gt;" + cname;
			}
		}
		jQuery("#" + showId).html(selectName);
	}
	var selectCatId = function(catId) {
		for(var j=1; j<=catId.length/3; j++) {
			var cid = catId.substring(0, j*3);
			jQuery("#catId" + j).val(cid);
			readSubCats(cid);
		}
		setCatId(catId, catId.length/3)
		jQuery.holdReady(false);
	};
	return {
		selectCatId : selectCatId
	}
}
