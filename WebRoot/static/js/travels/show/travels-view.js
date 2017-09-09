$(function(){
	var oTip = document.getElementById("mo11");
	var s = oTip.innerHTML;

	var timer;
	$("#mo11").hover(
		function(){
			if ($(this).css("width") == "288px") {
				if (s.length > 16) {
					timer = window.setInterval(function() {
						var start = oTip.scrollLeft;
						oTip.scrollLeft += 1;
						var end = oTip.scrollLeft;
						if (start === end) {
							oTip.innerHTML += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+ s;
						}
					}, 10);
				}
			}
		},function(){
			oTip.innerHTML = s;
			oTip.scrollLeft=0;
			clearInterval(timer);
		})

	$("#click").on("click",function(){
	    var _this = $(this);
	    if(_this.find("span.home").length>0){
	        _this.css("color","#6c6c6c");
	        _this.find(".home").addClass("mo-middle9");
	        _this.find(".home").removeClass("home");
	    }else{
	        _this.css("color","#ff8a01");
	        _this.find(".mo-middle9").addClass("home");
	        _this.find(".mo-middle9").removeClass("mo-middle9");
	    }
	})

	$("#one").on("click",function(){
	    var _this=$(this);
	    if(!_this.hasClass("one-one")){
	        _this.addClass("one-one")
	    }else{

	        _this.removeClass("one-one")

	    }
	})
	
	
	var titleHeight = $("#blockR").height();
	$("#blockR").height(0);
	
	function fn(){
	    if($(window).scrollTop()>=$("#scroll").offset().top){
	        $("#top").css({"position":"fixed","top":"0","display":"block","left":"0"});
	        $("#blockR").css({"position":"fixed","top":$("#top").height()+"px","right":($(document.body).width()-1200)/2+"px"});
	    }else if($(window).scrollTop()<=$("#scroll").offset().top){
	        $("#top").css("display","none");
	        $("#blockR").css({"position":"absolute","top":"0px","right":"0px"});
	    }
	}
	window.onscroll=fn;
	window.onresize=fn;
	$("#blockR").css("max-height",$(window).height()-64+"px");
	$("#title1").on("click",function(){
	    window.onscroll=fn;
	    $("#blockR").css("display","block");
	    if($("#blockR").height()==0){
	        $("#blockR").animate({height:titleHeight+"px"});
	    }else{
	        $("#blockR").animate({height:"0px"});
	    }


	});
	/*$("#blockR").on("mouseleave",function(){
		var $this = $(this);
		$this.animate({height:"0"});
		$this.css("display","none");
	});*/

	$("#title2").on("click",function(){
	    window.onscroll=fn;
	    $("#blockR").css({"position":"fixed","top":$("#top").height()+"px","right":($(document.body).width()-1200)/2+"px"});

	    $("#blockR").css("display","block");

	    if($("#blockR").height()==0){
	        $("#blockR").animate({height:titleHeight+"px"});
	    }else{
	        $("#blockR").animate({height:"0px"});
	    }

	});
	$('#blockR').niceScroll({
	    cursorcolor: "#ccc",
	    cursoropacitymax: 1,
	    touchbehavior: false,
	    cursorwidth: "5px",
	    cursorborder: "0",
	    cursorborderradius: "5px",
	    autohidemode: true
	});
	//目录栏点击状态
	$("div#blockR").on("click","ul li span#title",function(){
		var _this=$(this)
		
		$('html,body').animate({scrollTop:$("#"+_this.attr('data-target')).offset().top},1000)
		
	    if($(_this).next().css("display")!="none"){
	        $(_this).next().css("display","none");
	        _this.find("i").css("backgroundPosition","-8px 3px");
	    }else{
	        $(_this).next().css("display","block");
	        _this.find("i").css("backgroundPosition","-26px 3px");
	    }
	    return false;
	});

})