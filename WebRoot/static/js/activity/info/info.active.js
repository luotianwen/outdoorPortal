
function imgReload()
{
	var imgHeight = 0;
	var wtmp = 1200;
	$("#b06 ul li").each(function(){
		$(this).css({width:wtmp + "px"});
	});
	$(".sliderimg").each(function(){
		$(this).css({width:wtmp + "px"});
		imgHeight = $(this).height();
	});
}

$(window).resize(function(){imgReload();});
$(function() {
	var data06 = [];
	var actdata = [];
	imgReload();
	//多图切换
	for (var i=0;i<activitys;i++){
		var unslider06 = $('#banneractivity-'+i).unslider({
				dots: true,
				fluid: true
			}),
		    actdata= unslider06.data('unslider');
		    data06[i]=actdata;
	}
		$('.unslider-arrow06').click(function() {
			var fn = this.className.split(' ')[1];
			var id = this.className.split(' ')[2];
			data06[id][fn]();
		});
	//修改地图高度

	$('.route').each(function (index, element) {
		routeHeight=$(this).height();
		if(routeHeight>500){
			$(this).next().height(routeHeight);
		}
	});



	// 收藏
	$("div.event-btns").on("click","a#follow",function() {

		
		var self = $(this), likeState = self.attr("isFollow");
		layer.msg("操作中...", {
			icon : 16,
			shade : 0.3,
			time : 10 * 1000
		});
		
		$.post("follow/activity/" + $(this).attr("activity_id") + ".json", function(data) {
			if(data){

				if (data.RESPONSE_STATE == "200") {
					if (likeState == "yes") {
						layer.msg("取消成功！", {
							icon : 1,
							time:1000
						});
						self.attr("isFollow", "no").text("收藏").prop("class", "btn02");
					} else {
						layer.msg("收藏成功！", {
							icon : 1,
							time:1000
						});
						self.attr("isFollow", "yes").text("取消收藏").prop("class", "disabled-btn02");
					}

				} else {
					layer.msg(data.ERROR_INFO, {
						icon : 0
					});
				}
			}
		});
	});
	
	// 行程安排
	_affix_days = $("div#affix_day ul li");
	_affix_days.each(function(index){
		$(this).on("click",function(){
			
			_affix_days.removeClass("selected");
			$(this).addClass("selected");
			
			$("body,html").animate({scrollTop:($("div#perday_info:eq("+index+")").offset().top-(_event_tabs_height+20))+"px"},0);
			
		})
	})
	
	// window窗口绑定滚动事件
	windowBindScroll();
	
	// 初始化lazyload并设置图片显示方式
	$(".lazy-img").lazyload({effect: "fadeIn"});
	
	// 跳转锚点
	$("div#event_tabs_box a[F]").on("click",function(){
		$("div#event_tabs_box a[F]").removeClass("selected");
		a = $(this),F=a.attr("F");a.addClass("selected");
		$("body,html").animate({scrollTop:($("#"+F).offset().top-(_event_tabs_height+20))+"px"});
	});
	
	// 总体介绍、行程介绍、装备要求锚点
	$("a[BREAK-F]").on("click",function(){
		var $id = $(this).attr("BREAK-F");
		$("body,html").animate({scrollTop:($("div#"+$id).offset().top-(_event_tabs_height+20))+"px"});
	})
})

/**
 *	控制tabs置顶和行程安排目录悬浮 
 */
function windowBindScroll(){

	// tabs置顶
	$(window).on("scroll",function(){
		_window_scrollTop=$(this).scrollTop();
		
		// 导航菜单
		if(_window_scrollTop >= $("div#event_tabs_stick").offset().top){
			$("div.event-tabs").addClass("event-tabs-stick");
		}else{
			$("div.event-tabs").removeClass("event-tabs-stick");
		}
		/*--------------------------------	 行程安排	begin	-----------------------*/
		if($("div#perday_info").length>1 && _window_scrollTop >= $("div#perday_info:first").offset().top){
			$("div#affix_day").addClass("affix-day-fixed");
			
			$("div#perday_info").each(function(index){
				if((_window_scrollTop+(_event_tabs_height+20)) >= ($(this).offset().top)){
					_affix_days.removeClass("selected");
					_affix_days.eq(index).addClass("selected");
				}
			})
			
			// 当超过最后一个卡片的高度时要恢复原来的样式
			if(_window_scrollTop >= $("div#perday_info:last").offset().top){
				$("div#affix_day").removeClass("affix-day-fixed");
			}
			
		}else{
			$("div#affix_day").removeClass("affix-day-fixed");
		}

		/*--------------------------------	 行程安排	end	  -----------------------*/
		
		
		/*---------------------------------		延迟加载评价和提问信息		--------------------------*/
		
		if(_window_scrollTop>($("div#6F").offset().top-$(window).height()) && is_auto_load_buyer){
			// 开始加载报名信息
			new $.fn.loadBuyer({
				target:"div#6F"
			});
		}
		
		if(_window_scrollTop>($("div#4F").offset().top-$(window).height()) && is_auto_load_evaluate){
			// 开始加载评价信息
			loadEvaluates();
		}

		if(_window_scrollTop>($("div#5F").offset().top-$(window).height()) && is_auto_load_consultation){
			// 开始加载提问信息
			loadConsultation();
		}
		
	});
	
}