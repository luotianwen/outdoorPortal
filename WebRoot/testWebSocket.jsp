<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String socketPath = "ws" + "://" + request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="/">
    
    <title>My JSP 'testWebSocket.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
	<script type="text/javascript" src="static/js/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="static/js/plugins/sockjs/sockjs-0.3.4.js"></script>

  </head>
  
  <body>
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
                $("#msgcount").html("<font color='red'>"+evnt.data+"</font>")
            };
            websocket.onerror = function (evnt) {
           		alert("onerror");
            };
            websocket.onclose = function (evnt) {
           		alert("onclose");
            }
 
        </script>
        
        <span id="msgcount"></span>
        <br>
        <input type="text" id="mess" >
        <button id="post" type="button">推送</button>
        
        <script type="text/javascript">
        $("#post").click(function(){
	        $.post('socketController/sendMessageTosessions.do',{message:$("#mess").val()},function(data){
	        
	        })
        })
        </script>
  </body>
</html>
