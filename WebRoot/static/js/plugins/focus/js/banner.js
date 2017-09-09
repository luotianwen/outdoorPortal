$(function(){
	var focusBanner = function(obj){
		var $focusBanner=obj,
			$bannerList=$focusBanner.find("#focus-banner-list li"),
			$focusHandle=$focusBanner.find(".focus-handle"),
			$bannerImg=$focusBanner.find(".focus-banner-img"),
			$nextBnt=$focusBanner.find("#prev-img"),
			$prevBnt=$focusBanner.find("#next-img"),
			$focusBubble=$focusBanner.find("#focus-bubble"),
			bannerLength=$bannerList.length,
			_index=0,
			_timer="";

			var _height=$focusBanner.find(".focus-banner-img").find("img").height();
			$focusBanner.height(_height);
			$bannerImg.height(_height);
			
			/*$(window).resize(function(){
				window.location.reload()
			});*/

			for(var i=0; i<bannerLength; i++){
				$bannerList.eq(i).css("zIndex",bannerLength-i);
				$focusBubble.append("<li></li>");
			}
			$focusBubble.find("li").eq(0).addClass("current");
			var bubbleLength=$focusBubble.find("li").length;
			$focusBubble.css({
				"width":bubbleLength*22,
				"marginLeft":-bubbleLength*11
			});//初始化

			$focusBubble.on("click","li",function(){
				$(this).addClass("current").siblings().removeClass("current");
				_index=$(this).index();
				changeImg(_index);
			});//点击轮换

			$nextBnt.on("click",function(){
				_index++
				if(_index>bannerLength-1){
					_index=0;
				}
				changeImg(_index);
			});//下一张

			$prevBnt.on("click",function(){
				_index--
				if(_index<0){
					_index=bannerLength-1;
				}
				changeImg(_index);
			});//上一张

			function changeImg(_index){
				
				var bannerOrScrollId = $bannerList.eq(0).find("img").data("index");
				var data_image = $bannerList.eq(_index).find("img").data("image");
				$("div#bannerOrScroll"+bannerOrScrollId+" img").prop("src",data_image);
				
				
				/*$bannerList.eq(_index).fadeIn(250);
				$bannerList.eq(_index).siblings().fadeOut(200);*/
				$bannerList.each(function(){
					$(this).find("div.focus-banner-text").hide();
				})
				$bannerList.removeClass("current");
				$bannerList.eq(_index).addClass("current");
				$bannerList.eq(_index).find("div.focus-banner-text").show();
				$focusBubble.find("li").removeClass("current");
				$focusBubble.find("li").eq(_index).addClass("current");
			}//切换主函数
			_timer=setInterval(function(){$nextBnt.click()},4000)
	}
	
	$("div#focus-banner").each(function(){
		new focusBanner($(this));
	})
	
})// JavaScript Document