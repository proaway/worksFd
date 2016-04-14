///-------------------------------------------------------------------------
//jQuery弹出窗口
//--------------------------------------------------------------------------
/*参数：[可选参数在调用时可写可不写,其他为必写]
----------------------------------------------------------------------------
    title:	窗口标题
  content:  内容(可选内容为){ text | id | img | url | iframe }
    width:	内容宽度
   height:	内容高度
	 drag:  是否可以拖动(ture为是,false为否)
     time:	自动关闭等待的时间，为空是则不自动关闭
   showbg:	[可选参数]设置是否显示遮罩层(0为不显示,1为显示)
  cssName:  [可选参数]附加class名称
 btnValue:  [可选参数]底部按钮显示的名称
 ------------------------------------------------------------------------*/
 //示例:
 //------------------------------------------------------------------------
 //simpleWindown("例子","text:例子","500","400","true","3000","0","exa")
 //------------------------------------------------------------------------
var showWindown = true;
var templateSrc = "images"; 
var closeWindown = function() {
	jQuery("#windownbg").remove();
	jQuery("#windown-box").fadeOut("slow",function(){jQuery(this).remove();});
}
function tipsWindown(title,content,width,height,drag,time,showbg,cssName,btnValue) {
	jQuery("#windown-box").remove(); 
	var width = width>= 950?this.width=950:this.width=width;	    
	var height = height>= 527?this.height=527:this.height=height;  
	if(showWindown == true) {
		var simpleWindown_html = new String;
			simpleWindown_html = "<div id=\"windownbg\" style=\"height:"+jQuery(document).height()+"px;filter:alpha(opacity=0);opacity:0;z-index: 999901\"></div>";
			simpleWindown_html += "<div id=\"windown-box\">";
			simpleWindown_html += "<div id=\"windown-title\"><h2></h2><span id=\"windown-close\">Close</span></div>";
			simpleWindown_html += "<div id=\"windown-content-border\"><div id=\"windown-content\"></div>"; 
			if (btnValue != null && btnValue != "") {
				simpleWindown_html += "<div align='center'><input type='button' id='btn-close' value='"+btnValue+"'/></div>"; 
			}
			simpleWindown_html += "</div>"; 
			simpleWindown_html += "</div>";
			jQuery("body").append(simpleWindown_html);
			show = false;
	}
	contentType = content.substring(0,content.indexOf(":"));
	content = content.substring(content.indexOf(":")+1,content.length);
	switch(contentType) {
		case "text":
		jQuery("#windown-content").html(content);
		break;
		case "id":
		jQuery("#windown-content").html(jQuery("#"+content+"").html());
		break;
		case "img":
		jQuery("#windown-content").html("<img src='/static/prototype/product_fashion/inc/tips/loading.gif' class='loading' />");
		jQuery.ajax({
			error:function(){
				jQuery("#windown-content").html("<p class='windown-error'>Loading failed ...</p>");
			},
			success:function(html){
				jQuery("#windown-content").html("<img src="+content+" alt='' />");
			}
		});
		break;
		case "url":
		var content_array=content.split("?");
		jQuery("#windown-content").html("<img src='/static/prototype/product_fashion/inc/tips/loading.gif' class='loading' />");
		jQuery.ajax({
			type:content_array[0],
			url:content_array[1],
			data:content_array[2],
			error:function(){
				jQuery("#windown-content").html("<p class='windown-error'>Loading failed ...</p>");
			},
			success:function(html){
				jQuery("#windown-content").html(html);
			}
		});
		break;
		case "iframe":
		jQuery("#windown-content").html("<img src='/static/prototype/product_fashion/inc/tips/loading.gif' class='loading' />");
		jQuery.ajax({
			error:function(){
				jQuery("#windown-content").html("<p class='windown-error'>Loading failed ...</p>");
			},
			success:function(html){
				jQuery("#windown-content").html("<iframe src=\""+content+"\" width=\"100%\" height=\""+parseInt(height)+"px"+"\" scrolling=\"auto\" frameborder=\"0\" marginheight=\"0\" marginwidth=\"0\"></iframe>");
			}
		});
	}
	jQuery("#windown-title h2").html(title);
	if(showbg == "true") {jQuery("#windownbg").show();}else {jQuery("#windownbg").remove();};
	jQuery("#windownbg").animate({opacity:"0.5"},"normal");
	jQuery("#windown-box").show();
	if( height >= 527 ) {
		jQuery("#windown-title").css({width:(parseInt(width)+22)+"px"});
		jQuery("#windown-content").css({width:(parseInt(width)+17)+"px",height:height+"px"});
	}else {
		jQuery("#windown-title").css({width:(parseInt(width)+10)+"px"});
		jQuery("#windown-content").css({width:width+"px",height:height+"px"});
	}
	var	cw = document.documentElement.clientWidth,ch = document.documentElement.clientHeight,est = document.documentElement.scrollTop; 
	var _version = jQuery.browser.version;
	if ( _version == 6.0 ) {
		jQuery("#windown-box").css({left:"50%",top:(parseInt((ch)/2)+est)+"px",marginTop: -((parseInt(height)+53)/2)+"px",marginLeft:-((parseInt(width)+32)/2)+"px",zIndex: "999999"});
	}else {
		jQuery("#windown-box").css({left:"50%",top:"50%",marginTop:-((parseInt(height)+53)/2)+"px",marginLeft:-((parseInt(width)+32)/2)+"px",zIndex: "999999"});
	};
	var Drag_ID = document.getElementById("windown-box"),DragHead = document.getElementById("windown-title");
		
	var moveX = 0,moveY = 0,moveTop,moveLeft = 0,moveable = false;
		if ( _version == 6.0 ) {
			moveTop = est;
		}else {
			moveTop = 0;
		}
	var	sw = Drag_ID.scrollWidth,sh = Drag_ID.scrollHeight;
		DragHead.onmouseover = function(e) {
			if(drag == "true"){DragHead.style.cursor = "move";}else{DragHead.style.cursor = "default";}
		};
		DragHead.onmousedown = function(e) {
		if(drag == "true"){moveable = true;}else{moveable = false;}
		e = window.event?window.event:e;
		var ol = Drag_ID.offsetLeft, ot = Drag_ID.offsetTop-moveTop;
		moveX = e.clientX-ol;
		moveY = e.clientY-ot;
		document.onmousemove = function(e) {
				if (moveable) {
				e = window.event?window.event:e;
				var x = e.clientX - moveX;
				var y = e.clientY - moveY;
					if ( x > 0 &&( x + sw < cw) && y > 0 && (y + sh < ch) ) {
						Drag_ID.style.left = x + "px";
						Drag_ID.style.top = parseInt(y+moveTop) + "px";
						Drag_ID.style.margin = "auto";
						}
					}
				}
		document.onmouseup = function () {moveable = false;};
		Drag_ID.onselectstart = function(e){return false;}
	}
	jQuery("#windown-content").attr("class","windown-"+cssName);
	if( time == "" || typeof(time) == "undefined") {
		jQuery("#windown-close").click(closeWindown);
		jQuery("#btn-close").each(function() {
			jQuery(this).click(closeWindown);
		});
	}else { 
		setTimeout(closeWindown,time);
	}
}