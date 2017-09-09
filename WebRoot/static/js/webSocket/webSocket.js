var websocket;
var basePath;
var socketPath;
$.ajax({
	type : "post",
	url : "webSocket/path.json",
	async : false,
	success : function(data){
		basePath = data.basePath;
		socketPath = data.socketPath;
    }
});

if ('WebSocket' in window) {
    websocket = new WebSocket(socketPath+"webSocketServer");
} else if ('MozWebSocket' in window) {
    websocket = new MozWebSocket(socketPath+"webSocketServer");
} else {
    websocket = new SockJS(basePath+"sockjs/webSocketServer");
}
websocket.onopen = function (evnt) {
	console.log("onopen");
};
websocket.onmessage = function (evnt) {
	var data = JSON.parse(evnt.data);
	
	//收到信息
    if(data.type=="addMessage"){
    	//alert(data.messageTypeCount.sysAll+"    "+data.messageTypeCount.acAll+"   ")
    	var count = 0;
		if(data.messageTypeCount.sysAll){
			$("#sysAll").html(data.messageTypeCount.sysAll);
			count += Number(data.messageTypeCount.sysAll);
		}else{
			$("#sysAll").html("0");
		}
		if($("#acAll").length>0){
			if(data.messageTypeCount.acAll){
				$("#acAll").html(data.messageTypeCount.acAll);
				count += Number(data.messageTypeCount.acAll);
			}else{
				$("#acAll").html("0");
			}
		}
		if($("#orderAll").length>0){
			if(data.messageTypeCount.orderAll){
				$("#orderAll").html(data.messageTypeCount.orderAll);
				count += Number(data.messageTypeCount.orderAll);
			}else{
				$("#orderAll").html("0");
			}
		}
		if(data.messageTypeCount.askAll){
			$("#askAll").html(data.messageTypeCount.askAll);
			count += Number(data.messageTypeCount.askAll);
		}else{
			$("#askAll").html("0");
		}
		if(data.messageTypeCount.travelAll){
			$("#travelAll").html(data.messageTypeCount.travelAll);
			count += Number(data.messageTypeCount.travelAll);
		}else{
			$("#travelAll").html("0");
		}
		if(data.messageTypeCount.walletAll){
			$("#walletAll").html(data.messageTypeCount.walletAll);
			count += Number(data.messageTypeCount.walletAll);
		}else{
			$("#walletAll").html("0");
		}
		
		$("#messageAll").html(count);
    }else if(data.type=="dialog"){//收到私信
    	//还在私信页面
    	if($("#chatList").length>0){
    		//直接显示消息
    		
    		var linow = $("#chatList").find("li.now");
    		if(linow.find("#userId").val()==data.userId||linow.find("#userId").val()==data.addressee){
    			var dialogContent = document.getElementById('dialogContent');
    			//推送消息(发信人当前用户)
    			if(data.userId==currentSessionUID){
        			$("#dialogContent").append("<div class='chat-message clearfix'>"+
    											"	<div class='message-receive clearfix'>"+
    											"		<img src='"+data.uHeadImg+"' class='author' />"+
    											"		<div class='msg'>"+data.content+"</div>"+
    											"		<div class='time'>"+data.time+"</div>"+
    											"	</div>"+
    											"</div>");
        			
        			dialogContent.scrollTop = dialogContent.scrollHeight-$("#dialogContent").height();
        		}else if(data.addressee==currentSessionUID){//推送消息(消息接收人)
        			var bool = false;
        			
        			if(dialogContent.scrollTop == dialogContent.scrollHeight-$("#dialogContent").height()){
        				bool = true;
        			}
        			$("#dialogContent").append("<div class='chat-message clearfix'>"+
    											"	<div class='message-send clearfix'>"+
    											"		<img src='"+data.uHeadImg+"' class='author' />"+
    											"		<div class='msg'>"+data.content+"</div>"+
    											"		<div class='time'>"+data.time+"</div>"+
    											"	</div>"+
    											"</div>");
        			
        			if(bool){
        				dialogContent.scrollTop = dialogContent.scrollHeight-$("#dialogContent").height();
        			}
        			
        		}
    			
    			linow.attr("data-target",data.dialogId);
    			
    			//收到消息置顶
				$("#chatList").prepend("<li class='now' data-target='"+data.dialogId+"'>"+linow.html()+"</li>");
				linow.remove();
    		}else{
    			var bool = true;
    			//发信人在列表中
    			$("#chatList").find("li").each(function(index,element){
        			var $this = $(this);
        			
        			if($this.find("#userId").val()==data.userId){
        				bool = false;
        				$this.attr("data-target",data.dialogId);
        				
        				if(data.userId!=currentSessionUID){
        					$this.find("#dialogCount").addClass("msg-num");
            				var count = $this.find("#dialogCount").html();
                			if(count!=""){
                				if(Number(count)+1>=99){
                					$this.find("#dialogCount").html("99+");
                				}else{
                					$this.find("#dialogCount").html(Number(count)+1);
                				}
                			}else{
                				$this.find("#dialogCount").html("1");
                			}
                			$("#dialogAll").html(Number($("#dialogAll").html())+1);
                			$("#messageAll").html(Number($("#messageAll").html())+1);
            			}
        				//收到消息置顶
        				$("#chatList").prepend("<li data-target='"+data.dialogId+"'>"+$this.html()+"</li>");
        				$this.remove();
        			}
        			
        		});

    			//发信人未加载
    			if(bool){
    				if($("#chatList").length>0){
    					var uName = "";
    					if(data.uName.lenght>5){
    						uName = data.uName.substring(0,4)+"...";
    					}else{
    						uName = data.uName;
    					}

    					var html = "<li data-target='"+data.dialogId+"'>"+
    							"	<input type='hidden' id='userId' value='"+data.userId+"'/>"+
    							"	<a href='javascript:void(0)' id='dialogUser'>"+
    							"		<img src='"+data.uHeadImg+"' alt='"+data.uName+"' />"+uName+
    							"	</a>"+
    							"	<span id='dialogCount' class='msg-num' style='display: inline;'>1</span>"+
    							"	<i class='btn-close' title='关闭'></i>"+
    							"</li>";
    					$("#chatList").prepend(html);
    				}
    			}
    			
    		}
    	}else{
    		if(data.addressee==currentSessionUID){
    			$("#dialogAll").html(Number($("#dialogAll").html())+1);
    			$("#messageAll").html(Number($("#messageAll").html())+1);
    		}
    	}
    	
    }else if(data.type=="dialogCount"){
    	$("#messageAll").html(Number($("#messageAll").html())-Number($("#dialogAll").html())+Number(data.dialogCount));
    	$("#dialogAll").html(data.dialogCount);
    }
    
};
websocket.onerror = function (evnt) {
	console.log("onerror");
};
websocket.onclose = function (evnt) {
	console.log("onclose");
}