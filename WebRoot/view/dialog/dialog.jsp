<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String socketPath = "ws" + "://" + request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="/">

<title>聊天</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<script src="static/js/jquery-2.1.1.min.js"></script>
<link href="static/css/page.css" rel="stylesheet">

<script>
	var websocket;
    if ('WebSocket' in window) {
        websocket = new WebSocket("<%=socketPath%>webSocketServer");
    } else if ('MozWebSocket' in window) {
        websocket = new MozWebSocket("<%=socketPath%>webSocketServer");
    } else {
        websocket = new SockJS("<%=basePath%>sockjs/webSocketServer");
    }
    websocket.onopen = function (evnt) {
    	console.log("onopen");
    };
    websocket.onmessage = function (evnt) {
    	var data = JSON.parse(evnt.data);
    	if(data.type=="dialog"){
    		$("#dialog").val(data.dialogId);
    		var html = "<div>";
    		if($("#addresser").val()==data.userName){
    			html += "<span style='color:red;'>"
    		}else{
    			html += "<span>"
    		}
    		html += data.userName+":</span>"+data.content+"<br/>"+data.time+"</div>";
            $("#sendMessage").html($("#sendMessage").html()+html);
            
            $.ajax({
                url: 'dialog/updateDialogContent?dialogId='+data.dialogId,
                success: function(data) {
                	
                }
            });
    	}
        
    };
    websocket.onerror = function (evnt) {
   		alert("onerror");
    };
    websocket.onclose = function (evnt) {
   		alert("onclose");
    }
	
    
    function abc(){
    	$.ajax({
            url: 'dialog/saveDialogContent?dialogId='+$("#dialog").val()+'&addresser='+$("#addresser").val()+'&addressee='+$("#friend").text()+'&content='+$("#content").val(),
            success: function(data) {
            	$("#content").val("");
            }
        });
    	
    }
</script>

  <body>
  	<input id="addresser" type="hidden" value="${creater }" />
    <div>
    	<div style="width:800px;height: 600px;">
	    	<div style="float: left;width:200px;height: 500px;">
	    		<c:forEach items="${finallist }" var="list">
		    		<input id="dialog" type="hidden" value="${list.id }"/><span id="friend"><c:if test="${list.friend==creater  }">${list.creater }</c:if><c:if test="${list.friend!=creater  }">${list.friend }</c:if></span><br/>
		    	</c:forEach>
	    	</div>
	    
	    	<div id="sendMessage" style="width:600px;height: 500px;float:right;">
		    	<c:forEach items="${listDialogContent }" var="list">
		    		<div>
		    			<span <c:if test="${creater==list.addresser }">style="color:red;"</c:if></span>${list.addresser }</span>:${list.content }<br/>
		    			<fmt:formatDate value="${list.sendTime }" pattern="yyyy-MM-dd HH:mm:ss" />
		    		</div>
		    	</c:forEach>
		    </div>
	    	
	    </div>
	    <div>
	    	<input type="hidden" value="${name }" />
	    	<input type="text" id="content"/><button onclick="abc()">发送</button>
	    </div>
    </div>
  </body>
</html>
