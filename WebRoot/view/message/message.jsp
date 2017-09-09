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

<title>站内信</title>

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
        $("#sendMessage").html("您收到一条新消息<br/>标题："+data.title+"<br/>内容："+data.content);
        
    };
    websocket.onerror = function (evnt) {d
   		alert("onerror");
    };
    websocket.onclose = function (evnt) {
   		alert("onclose");
    }

</script>

  <body>
    
    <div id="sendMessage">
    	
    </div>
  </body>
</html>
