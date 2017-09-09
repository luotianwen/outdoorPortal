var data = "";
$(function() {
	$.post("pointService/updatePointServiceShow.json",{id:psId},function(data){
		//项目分数
		$("#avgPoint").html(data.avgPoint+"分");
		//是否收藏
		if(data.isCollection){
			$("#conllection").addClass();
		}
		//商家项目
		var projectHtml = "";
		
		for(var i=0,len=data.pointServiceProjectDTOList.length;i<len;i++){
			var project = data.pointServiceProjectDTOList[i];
			var purchaseNumber = 0;
			var commentNumber = 0;
			
			if(project.purchaseNumber!=""&&project.purchaseNumber!=null){
				purchaseNumber = project.purchaseNumber;
			}
			
			if(project.commentNumber!=""&&project.commentNumber!=null){
				commentNumber = project.commentNumber;
			}
			
			projectHtml += '<tr>'+
							'	<td width="270">'+
							'		<img src="'+project.pointProjectsImg+'" class="item-img" />'+
							'	</td>'+
							'	<td>'+
							'		<h2>'+project.psp_item_name+'</h2>'+
							'		<p class="p1">'+purchaseNumber+'人已购买</p>'+
							'		<P class="p1">'+commentNumber+'人评论</P>'+
							'	</td>'+
							'	<td width="250">'+
							'		<p>'+project.psp_validity_date_start+' 至 '+project.psp_validity_date_end+'</p>'+
							'	</td>'+
							'	<td width="150">'+
							'		<div class="item-price">'+
							'			<strong>'+project.psp_discount_price+'</strong><span>元</span>'+
							'			<del style="display:block">原价 '+project.psp_item_price+'元</del>'+
							'		</div>'+
							'	</td>'+
							'	<td width="130">'+
							'		<a href="pointServiceProjects/detail/'+project.psp_id+'.html" target="_blank" class="venue-btn">立即前往</a>'+
							'	</td>'+
							'</tr>';
		}
		
		$("#projectList").html(projectHtml);
		
		//购买人数
		$("#purchaseNumber").html(data.purchaseNumber);
		
		//商家认证
		if(data.isAuth){
			$("#rz").html("<i></i>已经认证");
		}else{
			$("#rz").html("<a style='color: #555;' href='pointService/auth.html?id="+psId+"'><i></i>去认证</a>");
		}
		
	})
	
	page(1);

	layer.config({
		extend: 'extend/layer.ext.js'
	});
	
	$.post("pointService/pointServiceImg.json",{id:psId},function(json){
		$("#pointImg").html("<img style='width:375px;height:250px;' src='"+json.data[0].src+"'/><div style='text-align: center;'>查看全部 "+json.data.length+" 张照片</div>");
		data = json;
	})
	//场馆图片
	$("#pointImg").on("click",function(){
		layer.photos({
			photos: data
		});
		
	})
	
	//收藏
	$("#conllection").on("click",function(){
		var $this = $(this);
		
		if($this.hasClass("selected")){
			$.post("pointService/cancelCollection.json",{id:psId},function(data){
				$this.removeClass("selected");
				layer.msg("取消收藏成功。",{icon:1,time:1*1000,shade:0.3})
			})
		}else{
			$.post("pointService/conllectionPoint.json",{id:psId},function(data){
				$this.addClass("selected");
				layer.msg("收藏成功。",{icon:1,time:1*1000,shade:0.3})
			})
		}
	})
	
	//只看带图评论
	$("#imgComment").on("click",function(){
		var $this = $(this);
		if($this.hasClass("current")){
			$this.removeClass("current")
			
			img = false;
			$.getJSON('pointEvaluate/pointComment.json', {
		        page: 1,id:psId,img:img
		    }, function(data){
		    	commentShow(1,data);
		    });
		}else{
			$this.addClass("current")
			
			img = true;
			$.getJSON('pointEvaluate/pointComment.json', {
		        page: 1,id:psId,img:img
		    }, function(data){
		    	commentShow(1,data);
		    });
		}
	})
	
	
	//回复框样式
	$("ul#projectComment").on("click","#comment-text",function(){
		var $this = $(this);
		
		$this.addClass("showIn");
	})
	
	$("ul#projectComment").on("blur","#comment-text",function(){
		var $this = $(this);
		
		if($this.hasClass("showIn")){
			$this.removeClass("showIn");
		}
	})
	
	$("ul#projectComment").on("click","#reply-text",function(){
		var $this = $(this);
		
		$this.addClass("showIn");
	})
	
	$("ul#projectComment").on("blur","#reply-text",function(){
		var $this = $(this);
		
		if($this.hasClass("showIn")){
			$this.removeClass("showIn");
		}
	})
	
	
	//隐藏回复
	$("ul#projectComment").on("click","#reply-comment",function(){
		var down = $(this).closest(".user-comment").find("#down");
		
		if(down.css("display")=="none"){
			$(this).html("隐藏回复");
			down.slideDown();
		}else{
			$(this).html("显示回复");
			down.slideUp();
		}
	})
	//回复其他人
	$("ul#projectComment").on("click","#reply-other",function(){
		var dd = $(this).closest("dd");
		
		var reply = dd.find("#reply-text");
		
		if(reply.length>0){
			if(reply.css("display")=="none"){
				$("dd#reply-text").css("display","none");
				reply.css("display","block");
				
				reply.trigger("click");
				reply.find("input").focus(); 
			}else{
				reply.css("display","none");
			}
		}else{
			$("dd#reply-text").css("display","none");
			dd.append(ddhtml);
			
			var input = dd.find("#reply-text").find("input");
			var name = dd.attr("uName");
			input.attr("placeholder","回复 "+name);
			input.select();
		}
		
	})
	
	//回复评论
	$("ul#projectComment").on("click","#reply",function(){
		var $this = $(this);
		var li = $this.closest("li");
		var pseId = li.attr("pcId");
		var comment = $this.parent().find("input").val();
		
		if(comment.trim()==""){
			layer.alert("回复内容不能为空！",{icon:0,title:"提醒"});
			return;
		}
		
		$this.parent().find("input").val("");
		
		var buId = "";
		if($this.closest("#reply-text").length>0){
			buId = $this.closest("#reply-text").parent().attr("uId");
			$this.closest("#reply-text").css("display","none");
		}
		
		$.post("pointEvaluate/replyEvaluate.json",{pseId:pseId,comment:comment,buId:buId},function(data){
			if(data.RESPONSE_STATE=="200"){
				var str = "";
				if(data.uName!=null&&data.uName!=''){
					str = " 回复 "+data.uName;
				}
				var html = "<dd class='user-comment-answer' uName='"+data.ruName+"' uId='"+data.ruId+"'>"+
							"	<img class='self-image' alt='"+data.ruName+"' src='"+data.ruHeadImg+"'>"+
							"	<p>"+
							"		<span class='answer-user-name'>"+data.ruName+str+"：</span>"+data.comment+
							"	</p>"+
							"	<p>"+
							"		<span class='answer-user-time'>"+data.time+"</span>"+
							"		<a href='javascript:void(0)' id='reply-other' class='answer-link'>回复</a>"+
							"	</p>"+
							"</dd>";
				
				li.find("#replyComment").append(html);
			}else if(data.RESPONSE_STATE=="500"){
				layer.alert('回复失败，请稍后再试！',{title:"错误信息",icon:0});
			}
		})
		
	})
	
})

var img = false;

var ddhtml = "<dd class='user-comment-self-input showIn' id='reply-text'>"+
			"	<div class='input-block'>"+
			"		<input type='text'>"+
			"		<a class='answer-btn' id='reply' href='javascript:void(0);'>回复</a>"+
			"	</div>"+
			"</dd>";


//场馆评论分页
function page(curr){
    $.getJSON('pointEvaluate/pointComment.json', {
        page: curr,id:psId,img:img
    }, function(data){
    	commentShow(curr,data);
    });
};

function commentShow(curr,data){
	//评论人数
	if(curr==1){
		$("#commentNumber").html(data.page.totalResult);
	}
	
    //替换评论内容
	var commentHtml = "";
	var pointComment = data.pointEvaluateDTOList;
	if(pointComment!=null&&pointComment.length>0){
		for(var i=0,len=pointComment.length;i<len;i++){
			commentHtml += "<li pcId='"+pointComment[i].pse_id+"'>"+
							"	<div class='user-image'>"+
							"		<img src='"+pointComment[i].uHeadImg+"' width='90' height='90' />"+
							"		<h3><a href='users/center.html?id="+pointComment[i].uId+"' target='_blank'>"+pointComment[i].uName+"</a></h3>"+
							"	</div>"+
							"	<dl class='user-comment'>"+
							"		<dt class='user-comment-content'>"+
							"			<p class='content-detail'>"+pointComment[i].pse_evaluate_comment+
							"			</p>"+
							"			<p class='user-time'>"+
							"				<span>"+pointComment[i].pse_create_time+"</span>"+
							"				<a href='javascript:void(0)' id='reply-comment'>隐藏回复</a>"+
							"			</p>";
							
			//评论图片
			var commentImg = pointComment[i].pointEvaluateImgList;
			if(commentImg!=null&&commentImg.length>0){
				commentHtml += "<div class='content-img'>";
				for(var j=0;j<commentImg.length;j++){
					commentHtml += "<img src='"+commentImg[j].psei_img_url+"' width='150' height='150' />";
				}
				commentHtml += "</div>";
			}
						
			commentHtml +=	"		</dt>"+
							"<div id='down'><dd class='user-comment-self-input' id='comment-text'>"+
							"	<div class='input-block'>"+
							"		<input type='text' placeholder='回复'>"+
							"		<a class='answer-btn' id='reply' href='javascript:void(0);'>回复</a>"+
							"	</div>"+
							"</dd>";
			//评论回复
			commentHtml += "<div id='replyComment'></div>"+
							"<div id='page' style='margin-top: 10px;float:right;'></div>"+
							"	</div></dl>"+
							"</li>";
		}
		
	}else{
		commentHtml = "暂无评论";
	}
	$("#projectComment").html(commentHtml);
	
	$("#projectComment").find("li").each(function(index,element){
		replyPage(1,$(this));
	})
	
    //显示分页
    laypage({
        cont: 'projectCommentPage', //容器。值支持id名、原生dom对象，jquery对象。【如该容器为】
        pages: data.page.totalPage, //通过后台拿到的总页数
        curr: curr, //当前页
        prev: '<', //若不显示，设置false即可
        next: '>', //若不显示，设置false即可
        skin: '#ff8a01',
        jump: function(obj, first){ //触发分页后的回调
            if(!first){ //点击跳页触发函数自身，并传递当前页：obj.curr
            	page(obj.curr);
            }
        }
    });

}

//场馆回复分页
function replyPage(curr,$this){
	var id = $this.attr("pcId");
	
    $.getJSON('pointEvaluate/replyComment.json', {
        page: curr,id:id,img:img
    }, function(data){
        //替换回复内容
    	var replyHtml = "";
		var commentReply = data.pointEvaluateReplyDTOList;
		if(commentReply!=null&&commentReply.length>0){
			for(var j=0;j<commentReply.length;j++){
				var str = "";
				if(commentReply[j].uName!=null&&commentReply[j].uName!=""){
					str = " 回复 "+commentReply[j].uName;
				}
				
				replyHtml +=	"	<dd class='user-comment-answer' uName='"+commentReply[j].ruName+"' uId='"+commentReply[j].ruId+"'>"+
							"			<img class='self-image' alt='"+commentReply[j].ruName+"' src='"+commentReply[j].ruHeadImg+"'>"+
							"			<p>"+
							"				<span class='answer-user-name'>"+commentReply[j].ruName+str+"：</span>"+commentReply[j].pser_comment+
							"			</p>"+
							"			<p>"+
							"				<span class='answer-user-time'>"+commentReply[j].pser_reply_time+"</span>"+
							"				<a href='javascript:void(0)' id='reply-other' class='answer-link'>回复</a>"+
							"			</p>"+
							"		</dd>";
			}
		}
		
		$this.find("#replyComment").html(replyHtml);
		
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
