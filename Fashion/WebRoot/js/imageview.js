// JavaScript Document
$(document).ready(function() {
    //点击到中图
    var midChangeHandler = null;
    $("#imageMenu li img").live("click", function() {

        if ($(this).attr("id") != "onlickImg") {
            midChange($(this).attr("src").replace(".m.", ".b."));
            $("#imageMenu li").removeAttr("id");
            $(this).parent().attr("id", "onlickImg");
        }
    }).live("mouseover", function() {
        if ($(this).attr("id") != "onlickImg") {
            window.clearTimeout(midChangeHandler);
            midChange($(this).attr("src").replace(".m.", ".b."));
            $(this).css({ "background": "none repeat scroll 0 0 #ccc", "border": "2px solid #999" });
        }
    }).live("mouseout", function() {
        if ($(this).attr("id") != "onlickImg") {
            $(this).removeAttr("style");
            midChangeHandler = window.setTimeout(function() { midChange($("#onlickImg img").attr("src").replace(".m.", ".b.")); }, 100);
        }
    });

    function midChange(src) {
        $("#midimg").attr("src", src).load(function() { changeViewImg(); });
    }

    //滑动左边小图
    $("#imageMenu").scrollTop(0).scrollLeft(0);

    var data = [];
    data["smallImgDown"] = {
        direction: 1,
        target: "last-child",
        visible: function() {
            return $("#imageMenu").scrollTop() + $("#imageMenu").height() == $("#imageMenu").attr("scrollHeight");

        },
        span: function($dom) {
            return $dom.position().top;
        },
        scrollSpan: function($dom) {
            return $dom.scrollTop();
        },
        animateParam: function(move) {
            return { scrollTop: move };
        }
    };
    data["smallImgUp"] =
    {
        direction: -1,
        target: "first",
        visible: function() {
            return $("#imageMenu").scrollTop() == 0;
        },
        span: function($dom) {
            return $dom.position().top;
        },
        scrollSpan: function($dom) {
            return $dom.scrollTop();
        },
        animateParam: function(move) {
            return { scrollTop: move };
        }
    };

    data["smallImgRight"] = {
        direction: 1,
        target: "last-child",
        visible: function() {
            return $("#imageMenu").scrollLeft() + $("#imageMenu").width() == $("#imageMenu").attr("scrollWidth");

        },
        span: function($dom) {
            return $dom.position().left;
        },
        scrollSpan: function($dom) {
            return $dom.scrollLeft();
        },
        animateParam: function(move) {
            return { scrollLeft: move };
        }
    };
    data["smallImgLeft"] =
    {
        direction: -1,
        target: "first",
        visible: function() {
            return $("#imageMenu").scrollLeft() == 0;
        },
        span: function($dom) {
            return $dom.position().left;
        },
        scrollSpan: function($dom) {
            return $dom.scrollLeft();
        },
        animateParam: function(move) {
            return { scrollLeft: move };
        }
    };

    //控制上下箭头
    $(".smallImgDown,.smallImgUp,.smallImgRight,.smallImgLeft").mouseover(function() {
        var className = $(this).attr("class");

        if ($("." + className + " img").attr("src").indexOf(className + "02.gif")) {
            var mydata = data[className];
            var num = 0;
            while (mydata.span($("#imageMenu")) > mydata.span($("#imageMenu li").eq(num))) {
                num++;
            }

            num += 5 * mydata.direction;

            var $li = $("#imageMenu li").eq(num);
            if ($li.length == 0 || num < 0) {
                $li = $("#imageMenu li:" + mydata.target);
            }

            var move = mydata.scrollSpan($("#imageMenu")) + mydata.span($li) - mydata.span($("#imageMenu"));
            $("#imageMenu").animate(mydata.animateParam(move), 500, checkMenuStatus);
        }
    });

    function checkMenuStatus() {
        for (key in data) {
            var $img = $("." + key + " img");
            if ($img.length > 0) {
                if (data[key].visible()) {
                    if ($img.attr("src").indexOf(key + "01.gif") > -1) {
                        $img.attr("src", $img.attr("src").replace(key + "01.gif", key + "02.gif"));
                    }
                } else {
                    if ($img.attr("src").indexOf(key + "02.gif") > -1) {
                        $img.attr("src", $img.attr("src").replace(key + "02.gif", key + "01.gif"));
                    }
                }
            }
        }
    }

    //大视窗看图
    function mouseEnter(e) {
        if ($("#winSelector").css("display") == "none") {
            $("#winSelector,#bigView").show();
        }

        $("#winSelector").css(fixedPosition(e));
        e.stopPropagation();
    }

    //    function mouseMove(e) {
    //        $("#winSelector").css(fixedPosition(e));
    //        e.stopPropagation();
    //    }

    function mouseOut(e) {
        if ($("#winSelector").css("display") != "none") {
            $("#winSelector,#bigView").hide();
        }
        e.stopPropagation();
    }


    $("#midimg").mouseenter(mouseEnter); //中图事件
    $("#midimg,#winSelector").mousemove(mouseEnter).mouseout(mouseOut); //选择器事件

    var $divWidth = $("#winSelector").width(); //选择器宽度
    var $divHeight = $("#winSelector").height(); //选择器高度
    var $imgWidth = $("#midimg").width(); //中图宽度
    var $imgHeight = $("#midimg").height(); //中图高度
    var $viewImgWidth = $viewImgHeight = $height = null; //IE加载后才能得到 大图宽度 大图高度 大图视窗高度

    // download by http://www.codefans.net

    function changeViewImg() {
        $("#bigView img").attr("src", $("#midimg").attr("src").replace(".b.jpg", ".jpg"));
    }
    changeViewImg();

    $("#bigView").scrollTop(0);
    function fixedPosition(e) {
        if (e == null) {
            return;
        }
        var $imgLeft = $("#midimg").offset().left; //中图左边距
        var $imgTop = $("#midimg").offset().top; //中图上边距
        X = e.pageX - $imgLeft - $divWidth / 2; //selector顶点坐标 X
        Y = e.pageY - $imgTop - $divHeight / 2; //selector顶点坐标 Y
        X = X < 0 ? 0 : X;
        Y = Y < 0 ? 0 : Y;
        X = X + $divWidth > $imgWidth ? $imgWidth - $divWidth : X;
        Y = Y + $divHeight > $imgHeight ? $imgHeight - $divHeight : Y;

        //if ($viewImgWidth == null) {
            $viewImgWidth = $("#bigView img").outerWidth();
            $viewImgHeight = $("#bigView img").height();
            if ($viewImgWidth < 200 || $viewImgHeight < 200) {
                $viewImgWidth = $viewImgHeight = 800;
            }
            $height = $divHeight * $viewImgHeight / $imgHeight;
            $("#bigView").width($divWidth * $viewImgWidth / $imgWidth);
            $("#bigView").height($height);
        //}

        var scrollX = X * $viewImgWidth / $imgWidth;
        var scrollY = Y * $viewImgHeight / $imgHeight;
        $("#bigView img").css({ "left": scrollX * -1, "top": scrollY * -1 });
        //确定上边距
        //用户视窗高度
        var viewH = document.documentElement.clientHeight == 0 ? document.body.clientHeight : document.documentElement.clientHeight;
        var top = ((viewH - $height) / 2) + $(document).scrollTop();
        top = top < 360 ? 360 : top;
        var left = 530;
        if ($(window).width() > $(document.body).width()) {
            //left = left - (($(window).width() - $(document.body).width()) / 2);
        }
        $("#bigView").css({ "top": top, "left": left });


        return { left: X, top: Y };
    }
});
