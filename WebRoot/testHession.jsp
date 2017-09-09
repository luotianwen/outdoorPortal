<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.caucho.hessian.client.HessianProxyFactory,com.op.plugin.hession.UpdateRemote"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="/">
    
    <title>My JSP 'testHession.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <%
	HessianProxyFactory factory = new HessianProxyFactory();
	UpdateRemote basic = (UpdateRemote) factory.create(UpdateRemote.class,basePath+"remote/updateRemote");
	basic.updateShiro(null);
	%>
  </body>
</html>
