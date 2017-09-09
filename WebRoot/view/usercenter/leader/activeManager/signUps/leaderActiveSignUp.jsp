<%@ page language="java" import="java.util.*,com.op.util.ZdGetValue" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="/">

<title>活动报名</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="活动报名 管理">
<meta http-equiv="description" content="领队活动报名信息">

<link href="static/css/bootstrap.min.css" rel="stylesheet">
<link href="static/css/font-awesome.min.css" rel="stylesheet">
<link href="static/css/animate.min.css" rel="stylesheet">
<link href="static/css/style.min.css" rel="stylesheet">
<link href="static/css/scrollUp/scrollUp.css" rel="stylesheet">
<link href="static/css/page.css" rel="stylesheet">

</head>


<body>
	<!-- 导航 -->
	<jsp:include page="/view/index/navigate.jsp"></jsp:include>


	<div class="wrapper wrapper-content animated fadeInRight"
		style="margin: 0 auto;width: 80%;">
		<div class="row">
			<div class="ibox float-e-margins">
				<span style="font-size: 16px;color: red;">
					活动名称：
				
					<c:choose>
						<c:when test="${fn:length(activeSignUps) > 0 }">
							<c:forEach items="${activeSignUps }" var="l" begin="0" end="1" >
								<a href="javascript:window.open('activity/detail/${l.asu_active_id }.html')">
									${l.title }
								</a>
							</c:forEach>
						</c:when>
						<c:otherwise>
							ID为【<%=request.getParameter("activeId") %>】的活动不存在，或者没有报名信息
						</c:otherwise>
					</c:choose>
				</span>
				<br>
				<br>
				<table class="table table-bordered">
					<thead>
						<tr>
							<th>订单编号</th>
							<th>姓名</th>
							<th>性别</th>
							<th>联系人手机号码</th>
							<th>订单备注</th>
							<th>报名状态</th>
							<th>是否支付</th>
							<th>已付款总额</th>
							<th>付款类型</th>
							<th>报名时间</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${activeSignUps }" var="as" varStatus="vs">
							<tr>
								<td>${as.asu_order_id }</td>
								<td>${as.asu_user_name }</td>
								<td>${as.asu_user_sex }</td>
								<td>${as.asu_link_user_phone }</td>
								<td>${as.asu_order_area }</td>
								<td>${as.asu_state }</td>
								<td>${as.asu_pay_state }</td>
								<td>${as.asu_account_paid }</td>
								<td>${as.asu_price_type }</td>
								<td>
									<fmt:formatDate value="${as.asu_create_time }" pattern="yyyy-MM-dd hh:mm:ss" />
								</td>
								<td>
									<c:if test="${as.asu_state == '等待领队确认' }">
										<a class="btn btn-outline btn-primary" href="javascript:leaderConfirm(${as.asu_active_id },'${as.asu_id }')" >确认报名</a>
									</c:if>
									<c:if test="${as.asu_state == '等待领队确认取消' }">
										<a class="btn btn-outline btn-primary" href="javascript:cancelSignUp(${as.asu_active_id },'${as.asu_id }')" >确认取消</a>
									</c:if>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<script type="text/javascript">
				function cancelSignUp(activeId,asuId){
					layer.confirm('请确认是否要取消该用户报名状态',{icon:3},function(index){
						layer.close(index);
						layer.load(0,{
							shade: [0.3, '#393D49']
						});
						
						$.post('activeSignup/cancelSignUp.do',{
							activeId:activeId,
							asuId:asuId
						},function(data){
							data = eval('('+data+')');
							if(data.RESPONSE_STATE == '200'){
								layer.msg('修改成功!',{icon:1,time:800},function(){
									location.reload();
								});
							}else{
								layer.closeAll('loading');
								layer.alert(data.ERROR_INFO,{icon:0});
							}
						
						});
						
					})
				}
				
				</script>
				<script type="text/javascript">
				function leaderConfirm(activeId,asuId){
					layer.confirm('请确认是否要修改该报名信息状态？',{icon:3},function(index){
						layer.close(index);
						layer.load(0,{
							shade: [0.3, '#393D49']
						});
						
						$.post('activeSignup/confirmSignUp.do',{
							activeId:activeId,
							asuId:asuId
						},function(data){
							data = eval('('+data+')');
							if(data.RESPONSE_STATE == '200'){
								layer.msg('修改成功!',{icon:1,time:800},function(){
									location.reload();
								});
							}else{
								layer.closeAll('loading');
								layer.alert(data.ERROR_INFO,{icon:0});
							}
						
						});
						
					})
				}
				</script>
			</div>
		</div>
		<div class="text-center">${page.pageStr }</div>
	</div>

	
	<!-- 页脚 -->
	<jsp:include page="/view/index/footer.jsp"></jsp:include>

	<script src="static/js/jquery-2.1.1.min.js"></script>
	<script src="static/js/plugins/layer/layer.min.js"></script>
	<script src="static/js/scrollUp/jquery.scrollUp.min.js"></script>
	<script src="static/js/default.js"></script>
	<script src="static/js/bootstrap.min.js"></script>
	<script src="static/js/content.min.js"></script>

</body>
</html>
