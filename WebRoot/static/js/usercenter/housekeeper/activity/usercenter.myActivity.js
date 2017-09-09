$(function(){
	$("div#linkMyActive li[type]").on("click",function(){
		window.location.href="huodong/myActive.html?type="+$(this).attr("type");
	})
	
	$("div#activity-list li").on("click",function(){
		var url = "huodong/info/"+$(this).data("id")+".html";
		window.open(url);
	})
	
	$("div#activity-list ul li").on("click","div a#template",function(){
		var $this = $(this);
		var id = $this.closest("li").attr("data-id");
		if($this.attr("template")=="0"){
			$.post("huodong/saveTemplate.do?id="+id,function(data){
				if(data.RESPONSE_STATE=="200"){
					layer.msg("添加活动模板成功。",{icon:1,time:1*1000,shade:0.3});
					
					$this.attr("template","1");
					$this.html("<i></i><span>取消模板</span>");
				}else{
					layer.alert("添加活动模板失败，请稍后再试！",{icon:0,title:"失败提醒"})
				}
			})
		}else{
			layer.confirm("确认删除该模板么?",{icon:3},function(index){
				layer.close(index);
				layer.msg("删除中...",{icon:16,shade:0.3,time:10*1000});
				$.post("huodong/deleteTemplate.json?id="+id,function(data){
					if(data){
						if(data.RESPONSE_STATE == "200"){
							layer.msg("删除成功。",{icon:1,time:1*1000,shade:0.3})
							
							$this.attr("template","1");
							$this.html("<i></i><span>设为模板</span>");
						}else{
							layer.alert(data.ERROR_INFO,{icon:0})
						}					
					}
					
				})
			})
		}
		
		
		return false;
	})
	
	$("div#activity-list ul li").on("click","div a#close",function(){
		if($(this).attr("close")=="0"){
			var id = $(this).closest("li").attr("data-id");
			$.post("huodong/closeActive.do?id="+id,function(data){
				if(data.RESPONSE_STATE=="200"){
					layer.msg("已申请关闭活动，请耐心等待。",{icon:1,time:1*1000,shade:0.3});
				}else{
					layer.alert("申请关闭活动是吧，请稍后再试！",{icon:0,title:"失败提醒"})
				}
			})
		}
		
		return false;
	})
	
})