$(function() {
	$.post("pointServiceProjects/updateProjectShow.json",{id:pspId,venueId:psId},function(data){
		if(data.RESPONSE_STATE=="200"){
			//修改购买人数，收藏人数，项目评分，评论人数
			$("ul#data").each(function(index,element){
				var $this = $(this);
				$this.find("li").eq(0).html(data.purchaseNumber+"人已购买");
				$this.find("li").eq(1).html(data.projectCollection+"人收藏");
				if(data.avgProject.length>3){
					$this.find("li").eq(2).html(data.avgProject.substring(0,3)+"分");
				}else{
					$this.find("li").eq(2).html(data.avgProject+"分");
				}
				
				//$this.find("li").eq(3).html(data.evaluateNumber+"人评论");
			})
			
			if(data.isCollection){
				$(".v-btns #collection").removeClass("btn-star").addClass("btn-no-star").html("取消收藏");
			}
			
			//显示项目SQ
			var allProjects = data.allProjects
			var projecthtml = "";
			if(allProjects!=null&&allProjects.length>0){
				for(var i=0,len=allProjects.length;i<len;i++){
					var name = allProjects[i].psp_item_name;
					if(name.length>8){
						name = name.substr(0,7)+"...";
					}
					
					if(allProjects[i].psp_id==pspId){
						projecthtml += "<a role='button' class='selected'>"+name+"</a>";
					}else{
						projecthtml += "<a role='button' href='pointServiceProjects/detail/"+allProjects[i].psp_id+".html'>"+name+"</a>";
					}
				}
			}
			$("dd#project").html(projecthtml);
			
			//显示评论内容
			page(1);
			
		}else if(data.RESPONSE_STATE=="500"){
			layer.alert('服务器数据异常，请稍后再试！',{title:"错误信息",icon:0});
		}
	})
	
	//减
	$(".wrap-input").on("click", ".sub-count", function() {
		var $this = $(this);
		var count = $this.parent().find("input");

		if (count.val() == "1" || count.val() == "") {
			count.val(1);
		} else {
			count.val(Number(count.val()) - 1);
		}
	})

	//加
	$(".wrap-input").on("click", ".add-count", function() {
		var $this = $(this);
		var count = $this.parent().find("input");

		if (count.val() == "") {
			count.val(1);
		}else{
			count.val(Number(count.val()) + 1);
		}
	})

	//只能输入数字
	$(".wrap-input").on("keyup", "input", function() {
		var tmptxt = $(this).val();
		$(this).val(tmptxt.replace(/\D|^0/g, ''));
	}).bind("paste", function() {
		var tmptxt = $(this).val();
		$(this).val(tmptxt.replace(/\D|^0/g, ''));
	}).css("ime-mode", "disabled");

	//光标离开输入框，数量不能为空
	$(".wrap-input").on("blur", "input", function() {
		var tmptxt = $(this).val();
		if(tmptxt == ""){
			$(this).val(1);
		}
	});
	
	//收藏
	$(".v-btns").on("click","#collection",function(){
		var $this = $(".v-btns").find("#collection");
		if($(this).hasClass("btn-star")){
			$.post("pointServiceProjects/conllectionProject.json",{id:pspId},function(data){
				$this.addClass("btn-no-star");
				$this.html("取消收藏");
				$this.removeClass("btn-star");
			})
		}else{
			$.post("pointServiceProjects/cancelCollection.json",{id:pspId},function(data){
				$this.addClass("btn-star");
				$this.html("<i></i>收藏");
				$this.removeClass("btn-no-star");
			})
		}
	})
	
	//立即购买
	$(".v-btns").on("click","#buy",function(){
		var $this = $(this);
		
		var num = $this.parent().parent().find("#txtCount").val();
		if(num!=""&&num!=null){
			num = "1";
		}
		window.location.href="pointServiceProjects/buyProject.html?id="+pspId+"&n="+num;
	})
	
	//只看带图评论
	$("#imgComment").on("click",function(){
		var $this = $(this);
		if($this.hasClass("current")){
			$this.removeClass("current")
			
			img = false;
			$.getJSON('pointEvaluate/projectComment.json', {
		        page: 1,id:pspId,img:img
		    }, function(data){
		    	commentShow(1,data);
		    });
		}else{
			$this.addClass("current")
			
			img = true;
			$.getJSON('pointEvaluate/projectComment.json', {
		        page: 1,id:pspId,img:img
		    }, function(data){
		    	commentShow(1,data);
		    });
		}
	})
	
	//定位tab
	$("#tab").on("click","a",function(){
		$("#tab").find("a").removeClass("selected");
		$(this).addClass("selected");
		$('html,body').animate({scrollTop:$("#"+$(this).attr("data-scroll")).offset().top-80},500);
	})
	//定位tab
	$("#tab-top").on("click","a",function(){
		$("#tab-top").find("a").removeClass("selected");
		$(this).addClass("selected");
		$('html,body').animate({scrollTop:$("#"+$(this).attr("data-scroll")).offset().top-80},500);
	})
	//下滑定位
	$(window).scroll(function(event){
		//滚动条高度
		var winPos = $(window).scrollTop();
		var top = $("#tab").offset().top+80;
		
		if(winPos>=top){
			if($("#tab-top").css("display")=="none"){
				$("#tab-top").css("display","block")
				$("#fl-info").css("display","block")
			}
		}else{
			$("#tab-top").css("display","none")
			$("#fl-info").css("display","none")
		}
		
		var count = 0;
		$("#tab-top").find("a").each(function(index,element){
			var top = $("#"+$(this).attr("data-scroll")).offset().top-80;
			if(winPos>=top){
				count = index;
			}
		})
		
		$("#tab-top").find("a").removeClass("selected");
		$("#tab-top").find("a").eq(count).addClass("selected");
		
		$("#tab").find("a").removeClass("selected");
		$("#tab").find("a").eq(count).addClass("selected");
		
    });
	
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


//评论分页
function page(curr){
    $.getJSON('pointEvaluate/projectComment.json', {
        page: curr,id:pspId,img:img
    }, function(data){
    	commentShow(curr,data);
    });
};

//评论显示内容
function commentShow(curr,data){
    //替换评论内容
	var commentHtml = "";
	var pointComment = data.pointEvaluateDTOList;
	
	if(curr==1){
		$("ul#data").each(function(index,element){
			var $this = $(this);
			$this.find("li").eq(3).html(data.page.totalResult+"人评论");
		})
		
		//修改评论人数
		$("#commentCount").html("("+data.page.totalResult+")");
	}
	
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

//回复分页
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

