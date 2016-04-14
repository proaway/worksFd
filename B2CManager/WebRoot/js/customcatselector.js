/**
 * Category selector
 * <input type='hidden' name='baseCatId' id='baseCatId' value="$!cond.baseCatId"/>
 * <script language="javascript" src="$content.getURI('js/categoryselector.js')"></script> 
 * <script>
 * new CategorySelector(4, "baseCatId");
 * </script>
 */
function CustomCategorySelector(level, tarObjId, showId, beginIdx, enabledBtn) {
	level = level <= 4 ? level : 4;
	level = level <= 0 ? 1 : level;
	beginIdx = beginIdx == null ? 1 : 0;
	
	var catMap = {};
	var categoriesMap = {};
	var catparents;
	
	jQuery.getJSON("/manager/action/category.AjaxGetCustomCats?catId=", function(data) {
		addCatOptions(data, 1);
		catparents = data;
		if (jQuery("*#"+tarObjId).val() != "") {
			selectCatId(jQuery("*#"+tarObjId).val());
		}
	});

	function addCatOptions(data, id) {
		var s = jQuery("*#"+tarObjId + id);
		for(i=id; i<=level; i++) {
			removeCatOptions(i);
		}
		for(i=0; i<data.length; i++) {
			var cat = data[i];
			s.append("<option value='"+cat.catId+"'>" + cat.catNameCn + "(" + cat.catName + ")</option>");
			categoriesMap[cat.catId] = cat;
		}
		s.unbind();
		if (id < level) {
			// 绑定事件，获取下级目录
			s.bind("change", function(){
				var catId = jQuery("*#"+tarObjId + id).val();
				readSubCats(catId);
			});
		} else {
			s.bind("change", function(){
				var catId = this.value;
				setCatId(catId, id);
				if (jQuery("*#customCatIdFlag").length>0) {
					jQuery("*#customCatIdFlag").val("1");
					hideOtextOptions('customCatIdErrDiv');
				}
				if (jQuery("*#"+enabledBtn).length>0) {
					jQuery("*#"+enabledBtn).removeAttr("disabled");
				}
			});
		}
	}
	function readSubCats(catId) {
		if(catMap[catId] == null) {
			jQuery.ajax({
	        	url:"/manager/action/category.AjaxGetCustomCats?catId=" + catId,
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
			if (jQuery("*#customCatIdFlag").length>0) {
				jQuery("*#customCatIdFlag").val("0");
			}
			if (jQuery("*#"+enabledBtn).length>0) {
				jQuery("*#"+enabledBtn).attr("disabled","");
			}
		} else {
			if (jQuery("*#customCatIdFlag").length>0) {
				jQuery("*#customCatIdFlag").val("1");
				hideOtextOptions('customCatIdErrDiv');
			}
			if (jQuery("*#"+enabledBtn).length>0) {
				jQuery("*#"+enabledBtn).removeAttr("disabled");
			}
		}
		setCatId(catId, catId.length/3);
		return data;
	}
	function removeCatOptions(id) {
		var selectors = jQuery("*#"+tarObjId + id);
		for(var j=0; j<selectors.length; j++) {
			var selector = selectors[j];
			jQuery(selector).find("option").each(function(i, n){
				if (jQuery(n).val()=="" || isNaN(jQuery(n).val()) || parseFloat(jQuery(n).val())==0) {
					return;
				}
				jQuery(n).remove();
			});
		}
	}
	function setCatId(catId, id) {
		jQuery("*#"+tarObjId).val(catId);
		if (parseInt(catId) == 0 && id > 1) {
			catId = jQuery("*#"+tarObjId + (id-1) + " option:selected").val();
			jQuery("*#"+tarObjId).val(catId);
		} else if (parseInt(catId) == 0 && id == 1) {
			jQuery("*#"+tarObjId).val("");
		}
		jQuery("*#"+tarObjId).change();
		var selectName = "";
		for(var i=1; i<=id; i++) {
			var cat = categoriesMap[jQuery("*#"+tarObjId + i + " option:selected").val()];
			var cname = cat.catNameCn + "(" + cat.catName + ")";
			if (i==1) {
				selectName = cname;
			} else {
				selectName += "&gt;&gt;" + cname;
			}
		}
		jQuery("*#" + showId).html(selectName);
	}
	var selectCatId = function(catId) {
		for(var j=1; j<=catId.length/3; j++) {
			var cid = catId.substring(0, j*3);
			jQuery("*#"+tarObjId + j).val(cid);
			readSubCats(cid);
		}
		setCatId(catId, catId.length/3)
	};
	var appendCat = function(cat) {
		var catId = cat.catId;
		var parentId = cat.parentId;
		if (categoriesMap[catId] != null) {
			jQuery("select[id^='"+tarObjId + "']").find("option[value='"+catId+"']").html(cat.catNameCn+"(" + cat.catName+")");
			categoriesMap[catId] = cat;
			if (parentId != null) {
				var cats = catMap[parentId];
				if(cats != null)
					for(var i=0; i<cats.length; i++) {
						if(cats[i].catId == catId) {
							cats[i] = cat;
						}
					}
			}
		} else {
			if (parentId == null || parentId == "") {
				jQuery("*#"+tarObjId + "1").append("<option value='"+catId+"'>"+cat.catNameCn+"(" + cat.catName+")</option>");
				return;
			}
			categoriesMap[catId] = cat;
			var cats = catMap[parentId];
			if (cats == null) {
				cats = [];
			}
			cats.push(cat);
		}
	}
	var getCat = function(catId) {
		return categoriesMap[catId];
	}
	var getSubCats = function(catId) {
		return catMap[catId];
	}
	var deleteCat = function(catId) {
		if (categoriesMap[catId] != null) {
			categoriesMap[catId] = null;
		}
		if (catMap[catId] != null) {
			catMap[catId] = null;
		}
		if (catId.length > 3) {
			var parentId = catId.substring(0, catId.length-3);
			if(catMap[parentId] != null) {
				for(var i=0; i<catMap[parentId].length; i++) {
					var c = catMap[parentId][i];
					if (c.catId == catId) {
						delete catMap[parentId][i];
						return c;
					}
				}
			}
		}
	}
	return {
		selectCatId : selectCatId,
		appendCat : appendCat,
		getCat : getCat,
		getSubCats : getSubCats,
		deleteCat : deleteCat
	}
}
