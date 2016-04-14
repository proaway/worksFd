var loginCallbacks = jQuery.Callbacks();
function testLogin(callback) {
	loginCallbacks.empty();
	loginCallbacks.add(callback);
	var url = "/fashion/action/buyer.AjaxCheckLogin";
	jQuery.ajax({
    	url:url,
    	async:false,
    	data:"",
    	dataType:"script",
    	success:function(){
			if (info.LoginFlag == "1") {
				loginCallbacks.fire();
			} else {
				// show login div
				tipsWindown("Login","url:get?/fashion/template/buyer,LoginDiv.html","930","400","false","","true","text","");
			}
		}
	 });
}