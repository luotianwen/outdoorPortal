$(function(){
	
	// 点赞
	$("span#travelsPraise").on("click",function(){
		var $this = $(this);
		$.post("travels/praise.json",{
			id:travelsId
		},function(data){
			if(data){
				if(data.RESPONSE_STATE=="500"){
					layer.msg(data.alert,{shade:0.6,time:2*1000,icon:0});
				}else{
					layer.msg(data.alert,{shade:0.6,time:1*1000,icon:6});
					if(!$this.hasClass("praise")){
						$("span#travelsPraise").addClass("praise");
					}
					if(data.RESPONSE_STATE == "200"){
						$("span#travelsPraise").next().html(Number($("span#travelsPraise").next().html())+1);
					}
				}
			}
				
		})
	})
	// 收藏
	$("span#travlesFollow").on("click",function(){
		var $this = $(this);
		$.post("travels/follow.json",{
			id:travelsId
		},function(data){
			if(data){
				if(data.RESPONSE_STATE=="500"){
					layer.msg(data.alert,{shade:0.6,time:2*1000,icon:0});
				}else{
					layer.msg(data.alert,{shade:0.6,time:1*1000,icon:6});
					if(!$this.hasClass("follow")){
						$("span#travlesFollow").addClass("follow");
					}
					if(data.RESPONSE_STATE == "200"){
						$("span#travlesFollow").next().html(Number($("span#travlesFollow").next().html())+1);
					}
				}
			}
				
		})
	})
})