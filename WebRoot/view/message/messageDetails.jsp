<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="/">

<title>站内信详情</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<script src="static/js/jquery-2.1.1.min.js"></script>
  
  <body>
    <div>
    	<c:if test="${type==1 }">
    		标题：${messagePrivate.mp_title }<br />
	    	内容：${messagePrivate.mp_content }<br />
	    	发信人：系统管理员<br />
	    	收信人：${messagePrivate.mp_addRessee }<br />
	    	发送时间：<fmt:formatDate value="${messagePrivate.mp_sendTime }" pattern="yyyy-MM-dd HH:mm:ss" /><br />
    	</c:if>
    	<c:if test="${type==2 }">
    		标题：${messageAddressee.messagePrivate.mp_title }<br />
	    	内容：${messageAddressee.messagePrivate.mp_content }<br />
	    	发信人：系统管理员<br />
	    	收信人：${messageAddressee.mar_addRessee }<br />
	    	发送时间：<fmt:formatDate value="${messageAddressee.messagePrivate.mp_sendTime }" pattern="yyyy-MM-dd HH:mm:ss" /><br />
    	</c:if>
    </div>
  </body>
</html>
