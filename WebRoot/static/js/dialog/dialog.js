$(function(){
	//获取url参数
    $.getUrlParam = function (name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) return unescape(r[2]); return null;
    }
	
	//发送消息
	$("#send").on("click",function(){
		send();
	});
	//ctrl+enter发送消息
	$("#content").keypress(function(e) {
		if (e.ctrlKey && e.which == 10){
			send();
		}
			
	})
	//切换聊天对象
	$("ul#chatList").on("click","li",function(){
		var $this = $(this);
		if($this.attr("class")!="now"&&close!=$this.attr("data-target")){
			$this.find("#dialogCount").removeClass("msg-num");
			$this.find("#dialogCount").html("");
			
			$("ul#chatList").find("li.now").find("#dialogCount").removeClass("msg-num");
			$("ul#chatList").find("li.now").find("#dialogCount").html("");
			
			$.ajax({
				type : "post",
				url : "dialog/updateDialog?id="+$this.attr("data-target")+"&userId="+$this.find("#userId").val(),
				async : false,
				success : function(data){
					if(data.RESPONSE_STATE=="200"){
						var html = "";
						if(data.listDialogContent.length>0){
							html = "<div class='more-info' id='moreInfo'>"+
									"	<a href='javascript:void(0)'>查看更多消息</a>"+
									"</div>";
						}
			
						for(var i=data.listDialogContent.length-1;i>=0;i--){
							if(data.listDialogContent[i].addressee==currentSessionUID){
								html += "<div class='chat-message clearfix' id='chatContent' data-target='"+data.listDialogContent[i].id+"'>"+
										"	<div class='message-send clearfix'>"+
										"		<img src='"+data.friendUser.uHeadImg+"' class='author' />"+
										"		<div class='msg'>"+data.listDialogContent[i].content+"</div>"+
										"		<div class='time'>"+data.listDialogContent[i].sendTime+"</div>"+
										"	</div>"+
										"</div>";
							}else{
								html += "<div class='chat-message clearfix' id='chatContent' data-target='"+data.listDialogContent[i].id+"'>"+
										"	<div class='message-receive clearfix'>"+
										"		<img src='"+data.createrUser.uHeadImg+"' class='author' />"+
										"		<div class='msg'>"+data.listDialogContent[i].content+"</div>"+
										"		<div class='time'>"+data.listDialogContent[i].sendTime+"</div>"+
										"	</div>"+
										"</div>";
							}
						}
						
						if(data.listDialogContent.length>0){
							html += "<div class='tips' id='history'>"+
									"	<span>以上是历史记录</span>"+
									"</div>";
						}
						
						$("ul#chatList").find("li.now").css("background","");
						$("ul#chatList").find("li.now").find("a").css("color","#555");
						$("ul#chatList").find("li.now").find(".btn-close").css("display","inline-block");
						
						$("ul#chatList").find("li").removeClass();
						
						$this.addClass("now");
						
						$("#dialogContent").html(html);
						
						dialogContent.scrollTop = dialogContent.scrollHeight-$("#dialogContent").height();
					}else if(data.RESPONSE_STATE=="500"){
						layer.alert('系统异常！！！请稍后再试！！！',{title:"错误信息",icon:0});
					}
				}
			});
		}
	});
	
	//鼠标滑过事件
	$("#chatList").on("mouseenter","li",function(){
		var $this = $(this);
		$this.css("background","#ff8a01");
		$this.find("a").css("color","#fff");
		$this.find(".btn-close").css("display","inline-block");
	})
	
	$("#chatList").on("mouseleave","li",function(){
		var $this = $(this);
		if($this.attr("class")!="now"){
			$this.css("background","");
			$this.find("a").css("color","#555");
			$this.find(".btn-close").css("display","inline-block");
		}
	})
	
	//聊天列表滚动条
	$('#chatList').niceScroll({
	    cursorcolor: "#ccc",//#CC0071 光标颜色
	    cursoropacitymax: 1, //改变不透明度非常光标处于活动状态（scrollabar“可见”状态），范围从1到0
	    touchbehavior: false, //使光标拖动滚动像在台式电脑触摸设备
	    cursorwidth: "5px", //像素光标的宽度
	    cursorborder: "0", // 	游标边框css定义
	    cursorborderradius: "5px",//以像素为光标边界半径
	    autohidemode: false //是否隐藏滚动条
	});
	
	//聊天内容滚动条
	$('#dialogContent').niceScroll({
	    cursorcolor: "#ccc",//#CC0071 光标颜色
	    cursoropacitymax: 1, //改变不透明度非常光标处于活动状态（scrollabar“可见”状态），范围从1到0
	    touchbehavior: false, //使光标拖动滚动像在台式电脑触摸设备
	    cursorwidth: "5px", //像素光标的宽度
	    cursorborder: "0", // 	游标边框css定义
	    cursorborderradius: "5px",//以像素为光标边界半径
	    autohidemode: false //是否隐藏滚动条
	});
	//滚动条到底部
	dialogContent.scrollTop = dialogContent.scrollHeight-$("#dialogContent").height();
	
	//鼠标滚动加载更多聊天信息
	$("#dialogContent").scroll(function () {
		if(dialogContent.scrollTop==0){
			if($("#more-info").find(a).html()!="已经是最后一条了"){
				moreInfo();
			}
		}
		var count = $("#chatList").find("li.now #dialogCount").html();
		if(count!=""){
			$("#chatList").find("li.now #dialogCount").removeClass("msg-num");
			$("#chatList").find("li.now #dialogCount").html("");
		}
		
    });
	//加载更多聊天信息
	$("#dialogContent").on("click","#moreInfo",function(){
		moreInfo();
	});
	//加载更多对话信息
	$("#moreDialog").on("click",function(){
		var id = $.getUrlParam('id');
		var count = 0;
		$("#chatList").find("li").each(function(index,element){
			if($(this).attr("data-target")!="0"&&$(this).find("#userId").val()!=id){
				count++;
			}
		});
		
		$.ajax({
    		type : "post",
    		url : "dialog/moreDialog.do",
    		data : {id:id,count:count},
    		async : false,
    		success : function(data){
    			if(data.RESPONSE_STATE=="200"){
    				var html = "";
    				var len = data.listDialog.length;
    				if(len<10){
    					$("#moreDialog").remove();
    				}
    				for(var i=0;i<len;i++){
    					var uName = "";
    					if(data.listDialog[i].user.uName.lenght>5){
    						uName = data.listDialog[i].user.uName.substring(0,4)+"...";
    					}else{
    						uName = data.listDialog[i].user.uName;
    					}
    					
    					html += "<li data-target='"+data.listDialog[i].id+"'>"+
								"	<input type='hidden' id='userId' value='"+data.listDialog[i].user.u_id+"'/>"+
								"	<a href='javascript:void(0)' id='dialogUser'>"+
								"		<img src='"+data.listDialog[i].user.uHeadImg+"' alt='"+data.listDialog[i].user.uName+"' />"+uName+
								"	</a>";
    					if(Number(data.listDialog[i].count)>0){
    						html += "	<span id='dialogCount' class='msg-num' style='display: inline;'>"+data.listDialog[i].count+"</span>";
    					}else{
    						html += "	<span id='dialogCount' style='display: inline;'></span>";
    					}
								
    					html +=	"	<i class='btn-close' title='关闭'></i>"+
								"</li>";
    				}
    				
    				$("#chatList").append(html);
    			}else if(data.RESPONSE_STATE=="500"){
    				layer.alert('系统异常！！！请稍后再试！！！',{title:"错误信息",icon:0});
    			}
            }
    	});
	});
	
	//关闭消息框
	var close = "";
	$("#chatList").on("click","li i#close",function(){
		close = $(this).parent().attr("data-target");
		
		var $class = $(this).parent().attr("class")
		$(this).closest("li").remove();
		if($class=="now"){
			$("#chatList").find("li").eq(0).trigger("click");
		}
		
		$.ajax({
    		type : "post",
    		url : "dialog/deleteDialog.do",
    		data : {id:close},
    		async : false,
    		success : function(data){
    			
            }
    	});
		
	});
	
})

var dialogContent = document.getElementById('dialogContent');

//发送消息
function send(){
	var id = $("#chatList").find("li.now").attr("data-target");
	var userId = $("#chatList").find("li.now #userId").val();
	var content = $("#content").val();
	
	if(content.trim()!=""){
		if(id){
			$.ajax({
        		type : "post",
        		url : "dialog/saveDialogContent.do",
        		data : {dialogId:id,addressee:userId,content:replaceEmoji(content)},
        		async : false,
        		success : function(data){
        			$("#content").val("");
                }
        	});
		}else{
			layer.alert('请选择私信对象！',{title:"错误信息",icon:0});
		}
	}
}

//鼠标滚动，查看更多消息
function moreInfo(){
	var id = $("#chatList").find("li.now").attr("data-target");
	var userId = $("#chatList").find("li.now #userId").val();
	var dialogContentId = $("#dialogContent").find("#chatContent").eq(0).attr("data-target");
	
	$.ajax({
		type : "post",
		url : "dialog/moreInfo.do",
		data : {id:id,userId:userId,dialogContentId:dialogContentId},
		async : false,
		success : function(data){
			if(data.RESPONSE_STATE=="200"){
				var html = "";
				if(data.listDialogContent.length==10){
					html = "<div class='more-info' id='moreInfo'>"+
							"	<a href='javascript:void(0)'>查看更多消息</a>"+
							"</div>";
				}else{
					if($("#dialogContent").find("#chatContent").length>0){
						html = "<div class='more-info' id='moreInfo'>"+
								"	<a href='javascript:void(0)'>已经是最后一条了</a>"+
								"</div>";
					}
				}
				
				for(var i=data.listDialogContent.length-1;i>=0;i--){
					if(data.listDialogContent[i].addressee==currentSessionUID){
						html += "<div class='chat-message clearfix' id='chatContent' data-target='"+data.listDialogContent[i].id+"'>"+
								"	<div class='message-send clearfix'>"+
								"		<img src='"+data.friendUser.uHeadImg+"' class='author' />"+
								"		<div class='msg'>"+data.listDialogContent[i].content+"</div>"+
								"		<div class='time'>"+data.listDialogContent[i].sendTime+"</div>"+
								"	</div>"+
								"</div>";
					}else{
						html += "<div class='chat-message clearfix' id='chatContent' data-target='"+data.listDialogContent[i].id+"'>"+
								"	<div class='message-receive clearfix'>"+
								"		<img src='"+data.createrUser.uHeadImg+"' class='author' />"+
								"		<div class='msg'>"+data.listDialogContent[i].content+"</div>"+
								"		<div class='time'>"+data.listDialogContent[i].sendTime+"</div>"+
								"	</div>"+
								"</div>";
					}
				}
				$("#moreInfo").remove();
				$("#dialogContent").prepend(html);
				
				if(data.listDialogContent.length>0){
					var height = 0;
					$("div#dialogContent").find("div#chatContent").each(function(index,element){
						if(index<=data.listDialogContent.length-1){
							height += $(this).height()+20;
						}
					})
					
					dialogContent.scrollTop = height;
				}
			}else if(data.RESPONSE_STATE=="500"){
				layer.alert('系统异常！！！请稍后再试！！！',{title:"错误信息",icon:0});
			}
        }
	});
	
	
}
