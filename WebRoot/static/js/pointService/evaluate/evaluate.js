$(function(){
	$("div#content").each(function(){
		replyPage(1,$(this));
	});
	
	//回复框样式
	$("div#content").on("click","dd#reply",function(){
		$(this).addClass("showIn");
	})
	
	$("div#content").on("blur","dd#reply",function(){
		var $this = $(this);
		
		if($this.hasClass("showIn")){
			$this.removeClass("showIn");
		}
	})
	
	//回复
	$("div#content").on("click","dd#reply a.answer-btn",function(){
		var $this = $(this);
		var content = $this.closest("div#content");
		var pseId = content.attr("pcId");
		var comment = $this.parent().find("input").val();
		
		if(comment.trim()==""){
			layer.alert("回复内容不能为空！",{icon:0,title:"提醒"});
			return;
		}
		$this.parent().find("input").val("");
		
		var buId = content.attr("uId");
		
		$.post("pointEvaluate/replyEvaluate.json",{pseId:pseId,comment:comment,buId:buId},function(data){
			if(data.RESPONSE_STATE=="200"){
				var str = "";
				if(data.uName!=null&&data.uName!=''){
					str = " <span class='hf'>回复</span> <em>"+data.uName+"</em>";
				}
				
				var html ="<div class='rate-answer'>"+
							"	<img width='30' height='30' alt='"+data.ruName+"' src='"+data.ruHeadImg+"'>"+
							"	<em>"+data.ruName+"</em>"+str+"："+data.comment+
							"	<span class='date'>"+data.time+"</span>"+
							"</div>";
				
				content.find("#allReply").append(html);
			}else if(data.RESPONSE_STATE=="500"){
				layer.alert('回复失败，请稍后再试！',{title:"错误信息",icon:0});
			}
		})
	})
	
	//展开查看回复
	$("div#rate").on("click","a i",function(){
		var $this = $(this);
		if($this.hasClass("down")){
			$this.attr("class","up");
			$this.closest("div#rate").find("#replyComment").slideDown();
		}else if($this.hasClass("up")){
			$this.attr("class","down");
			$this.closest("div#rate").find("#replyComment").slideUp();
		}
	})
	
})

//回复分页
function replyPage(curr,$this){
	var id = $this.attr("pcId");
    $.getJSON('pointEvaluate/replyComment.json', {
        page: curr,id:id
    }, function(data){
        //替换回复内容
    	var replyHtml = "";
		var commentReply = data.pointEvaluateReplyDTOList;
		if(commentReply!=null&&commentReply.length>0){
			replyHtml += "<div id='allReply'>";
			for(var j=0;j<commentReply.length;j++){
				var str = "";
				if(commentReply[j].uName!=null&&commentReply[j].uName!=""){
					str = " <span class='hf'>回复</span> <em>"+commentReply[j].uName+"</em>";
				}
				
				replyHtml +="<div class='rate-answer'>"+
							"	<img width='30' height='30' alt='"+commentReply[j].ruName+"' src='"+commentReply[j].ruHeadImg+"'>"+
							"	<em>"+commentReply[j].ruName+"</em>"+str+"："+commentReply[j].pser_comment+
							"	<span class='date'>"+commentReply[j].pser_reply_time+"</span>"+
							"</div>";
			}
			replyHtml += "</div>";
			replyHtml += "<div id='page' style='margin-top: 10px;float:right;'></div>";
			
			$this.find("#replyComment").html(replyHtml);
			$this.closest("#rate").append("<a class='rate-more' href='javascript:void(0)'>展开<i class='down'></i></a>");
		}
		
        //显示分页
        laypage({
            cont: $this.find("#page"), //容器。值支持id名、原生dom对象，jquery对象。【如该容器为】
            pages: data.page.totalPage, //通过后台拿到的总页数
            curr: curr, //当前页
            prev: '<', //若不显示，设置false即可
            next: '>', //若不显示，设置false即可
            jump: function(obj, first){ //触发分页后的回调
                if(!first){ //点击跳页触发函数自身，并传递当前页：obj.curr
                	replyPage(obj.curr,$this);
                }
            }
        });
    });
};