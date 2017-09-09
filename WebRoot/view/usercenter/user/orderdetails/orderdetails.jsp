<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="/">

<title>订单详情</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<style type="text/css">
dd {
	margin-left: 0cm;
}

h5 {
	margin: 0px;
	padding: 15px 30px;
	font-size: 20px;
	font-weight: normal;
}

a {
	color: #333;
	text-decoration: none;
	text-decoration: none !important;
	cursor: pointer !important;
	font-size: 13px;
}

a:focus,a:hover {
	color: #23527c;
	text-decoration: underline;
}

.p1 {
	margin-top: 0px;
	margin-right: 45px;
	margin-bottom: 0px;
	padding: 0px;
	display: inline-block;
	font-size: 18px;
}

.p2 {
	margin-top: 0px;
	margin-right: 45px;
	margin-bottom: 0px;
	padding: 0px;
	display: inline-block;
	font-size: 12px;
	color: rgb(180, 181, 181);
}

.ul {
	margin: 0px;
	padding: 30px 100px;
	border-bottom-width: 1px;
	border-bottom-style: dotted;
	border-bottom-color: rgb(220, 220, 220);
}

.span1 {
	width: 38px;
	height: 35px;
	font-size: 24px;
	color: rgb(60, 180, 60);
	display: inline-block;
	background-image: url(http://myevent.lvye.com/images/obg.png);
	background-attachment: initial;
	background-size: initial;
	background-origin: initial;
	background-clip: initial;
	background-position: 4px 4px;
	background-repeat: no-repeat;
}

.span2 {
	display: inline-block;
	margin-left: 165px;
	color: rgb(52, 156, 216);
}
</style>
</head>

<body>
	<!-- 导航 -->
	<jsp:include page="/view/index/navigate.jsp"></jsp:include>
	<div style="margin: 0px 8cm;padding:0cm;">
		<div
			style="margin: 10px 15px 20px;
    border: 1px solid #dcdcdc;
    padding: 20px 100px;">
			<h5 style="padding: 0px; font-size: 16px;color: rgb(60, 180, 60);">
				<a href="activity/detail/${ActiveSignupInfo.asu_active_id}.html" target="_blank" style="color:rgb(60, 180, 60);font-size: 20px;">玩嘛奉送!!${ActiveSignupInfo.title
					}</a>
			</h5>
			<p class=".p1">
				报名状态：<span style="color: rgb(60, 180, 60);margin-right: 40px;">
					<c:forEach items="${StateList}" var="StateList">
						<c:if test="${StateList.sus_id==ActiveSignupInfo.asu_state}">${StateList.sus_description }</c:if>
					</c:forEach> </span> 订单费用总额：<span style="color: rgb(236, 105, 25);margin-right: 40px;">${ActiveSignupInfo.price}</span>

				已付款：<span style="color: rgb(236, 105, 25);margin-right: 40px;">${ActiveSignupInfo.asu_account_paid}
					元</span>
			</p>
			<p class="p2">
				报名编号：<span style="margin-right: 40px;">${ActiveSignupInfo.asu_id}</span>

				玩嘛交易号：<span style="margin-right: 40px;"> 
				<c:choose>
					<c:when test="${empty ActiveSignupInfo.asu_user_account_num}">--</c:when>
					<c:otherwise>${ActiveSignupInfo.asu_user_account_num }</c:otherwise>
				</c:choose>
				</span> 玩嘛账号：<span style="margin-right: 40px;"><a href=""
					target="_blank" style="color: rgb(93, 93, 93);">${ActiveSignupInfo.uname}</a>
				</span>
			</p>
		</div>
		<div
			style="margin: 10px 15px 20px;border:1px solid #dcdcdc;color: #595757;">
			<h5>报名信息</h5>
			<div style="border: 1px solid #dcdcdc;color: #595757;padding: 20px 55px;">
				<ul style="list-style-type: none;">
					<li>
						<p>
							<span class="span1"><strong>1</strong> </span>
						</p></li>
					<ul style="list-style-type: none;">
						<li>
							<p>
								真实姓名：${ActiveSignupInfo.asu_user_name}&nbsp;<span class="span2">已设为报名联系人</span>
							</p></li>
						<li>
							<p>
								性别：
								<c:if test="${ActiveSignupInfo.asu_user_sex==1}">男</c:if>
								<c:if test="${ActiveSignupInfo.asu_user_sex==2}">女</c:if>
							</p></li>
						<li>
							<p>手机号码：${ActiveSignupInfo.asu_user_phone}</p></li>
					</ul>
				</ul>
			</div>
		</div>
		<div
			style="margin: 10px 15px 20px;border:1px solid #dcdcdc;color: #595757;">
			<h5>订单备注</h5>
			<div
				style="border: 1px solid #dcdcdc;color: #595757;padding: 20px 55px;">
				<p style="margin-top: 10px; margin-bottom: 0px; padding: 20px 55px;">
					${ActiveSignupInfo.asu_order_area}</p>
			</div>
		</div>
		<div
			style="margin: 10px 15px 20px;border:1px solid #dcdcdc;color: #595757;">
			<h5>投诉领队</h5>
			<div
				style="border: 1px solid #dcdcdc;color: #595757;padding: 20px 200px;">
				
					<c:choose>
						<c:when test="${ActiveSignupInfo.complain=='true' && cl.STATE==null}">
							<p style="margin-top: 10px; margin-bottom: 0px; padding: 20px 200px;">
								<button type="type" style="font-size:25px;color:white;background-color:green; padding: 20px 100px; cursor: pointer;" id="complain" name="complain" onclick="complain('${ActiveSignupInfo.asu_order_id}')">投诉领队</button>
								<script src="static/js/jquery-2.1.1.min.js"></script>
								<script type="text/javascript">
									function complain(id){
										layer.open({
								        type: 2,
								        title: "投诉领队",
								        shade: 0.2,
								        area: ["50%", "80%"],
								        offset:["150px","420px"],
								        content: "view/usercenter/user/complaintmanager/complaintlead.jsp?id="+id
							       		 });  
									}
								</script>
							</p>
						</c:when>
						<c:when test="${ActiveSignupInfo.complain=='false'}">
							<p style="margin-top: 10px; margin-bottom: 0px; padding: 20px 200px;">
								<p style="color: red; font-size: 25px;margin-top: 10px; margin-bottom: 0px; padding: 20px 100px;">您现在还不具备投诉领队的条件</p>
							</p>
						</c:when>
						<c:when test="${ActiveSignupInfo.complain=='true' && cl.STATE==1}"><!-- 待受理 -->
							<p style="color: green; font-size: 25px;margin-top: 10px; margin-bottom: 0px; padding: 20px 100px;">等待客服处理</p>
						</c:when>
						<c:when test="${ActiveSignupInfo.complain=='true' && cl.STATE==2}"><!-- 处理中 -->
							<p style="color: green; font-size: 25px;margin-top: 10px; margin-bottom: 0px; padding: 20px 100px;">客服正在处理中</p>
						</c:when>
						<c:when test="${ActiveSignupInfo.complain=='true' && cl.STATE==3}"><!-- 处理完成 -->
						<p style="color: blank; font-size: 20px;margin-top: 10px; margin-bottom: 0px; padding: 20px 50px;">
							处理结果:<span style="color: green;">${cl.HANDLERESULTS }</span>
							处理时间:<span style="color: green;">${cl.HANDLINGTIME }</span>
							客服:<span style="color: green;">${cl.UNAME }</span>
						</c:when>
					</c:choose>
				
			</div>
		</div>
	</div>
	<!-- 页脚 -->
	<jsp:include page="/view/index/footer.jsp"></jsp:include>
</body>
</html>
