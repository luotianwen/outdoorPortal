<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="/">

<title>我的退款</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link rel="shortcut icon" href="static/images/logo/logo.jpg"/>

<style type="text/css">
#form1 th {
	margin: 0px;
	padding: 0px 20px 0px 0px;
	font-weight: normal;
	text-align: right;
	width: 100px;
}

#form1 td {
	margin: 0px;
	padding: 0px;
}

#form1 input {
	margin: 0px;
	padding: 3px 5px;
	font-family: inherit;
	font-size: inherit;
	font-weight: inherit;
	height: 23px;
	border: 1px solid rgb(220, 220, 221);
	line-height: 20px;
}

#form1 span {
	padding-right: 10px;
	color: rgb(236, 105, 25);
	vertical-align: middle;
}

#form1 button {
	width: 110px;
	height: 36px;
	line-height: 36px;
	margin: 20px 0px;
	color: rgb(255, 255, 255);
	font-size: 18px;
	border: none;
	font-family: & #39; microsoft yahei&#39;;
	border-radius: 5px;
	cursor: pointer;
	background-color: #1ab394;
}

#form1 label {
	font-family: inherit;
	font-size: inherit;
	font-weight: inherit;
	width: 110px;
	height: 31px;
	margin: 0px;
	padding: 0px;
	border-color: rgb(220, 220, 221);
	color: rgb(89, 87, 87);
}

#form1 option {
	height: 30px;
	line-height: 30px;
}

#table1 th {
	margin: 0px;
	padding: 0px;
	font-weight: normal;
	text-align: center;
	width: 34px;
	border-right-color: rgb(180, 181, 181);
}

#table1 td {
	margin: 0px;
	padding: 0px;
}

#table1 tr {
	border-bottom-width: 1px;
	border-bottom-style: solid;
	border-bottom-color: rgb(180, 181, 181);
	height: 50px;
	background-color: rgb(220, 220, 220);
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
</style>
<!-- iCheck -->
<link href="static/css/style.min.css" rel="stylesheet">
<link href="static/css/page.css" rel="stylesheet">
<script src="static/js/jquery-2.1.1.min.js" type="text/javascript"></script>
</head>

<body>
	<!-- 导航 -->
	<jsp:include page="/view/index/navigate.jsp"></jsp:include>
	<div align="center">
		<form autocomplete="off" method="post" id="activeSignup"
			action="activeSignup/selectActive.html?param=refund">
			<table id="form1">
				<tbody>
					<tr>
						<th>活动ID：</th>
						<td width="360" height="45"><input type="text"
							id="replace_aaid" name="replace_aaid"
							value="<c:if test="${activeSignup.asu_active_id!=0}">${activeSignup.asu_active_id}</c:if>" />
							<input type="hidden" id="asu_active_id" name="asu_active_id" value="${activeSignup.asu_active_id}"/>
						</td>
						<th>活动名称：</th>
						<td width="360" height="45"><input type="text" id="title"
							name="title" value="${activeSignup.title }" /></td>
					</tr>
					<tr>
						<th>报名编号：</th>
						<td width="360" height="45"><input type="text" id="asu_id"
							name="asu_id" value="${activeSignup.asu_id }" /></td>
						<th>支付流水号：</th>
						<td width="360" height="45"><input type="text"
							id="asu_user_account_num" name="asu_user_account_num"
							value="${activeSignup.asu_user_account_num }" /></td>
					</tr>
					<tr>
						<th>退款状态：</th>
						<td width="360" height="45"><label><select
								name="asu_refund_state" id="asu_refund_state"
								style="width:150px; margin: 0px; padding:5px; border-width:1px;">
									<option value="0">全部状态</option>
									<c:forEach items="${StateList}" var="StateList">
										<option value="${StateList.sus_id}"
											<c:if test="${StateList.sus_id==activeSignup.asu_refund_state}"> selected="selected" </c:if>>
											${StateList.sus_description }</option>
									</c:forEach>
							</select>
						</label></td>
					</tr>
					<tr>
						<th>申请退款时间：</th>
						<td width="420" height="45" colspan="2"><input type="text"
							class="laydate-icon" id="start" name="starttime"
							readonly="readonly" style="cursor:pointer; margin: 0px; padding: 3px 5px; font-family: inherit; font-size: inherit; font-weight: inherit; width: 200px; height: 23px; border: 1px solid rgb(220, 220, 221); line-height: 20px;"
							value="<fmt:formatDate value='${activeSignup.starttime}' pattern='yyyy-MM-dd'/>" /><span
							class="timeto" style="margin: 0px 13px;">到</span><input
							type="text" class="laydate-icon" id="end" name="endtime"
							readonly="readonly" style="cursor:pointer; margin: 0px; padding: 3px 5px; font-family: inherit; font-size: inherit; font-weight: inherit; width: 200px; height: 23px; border: 1px solid rgb(220, 220, 221); line-height: 20px;"
							value="<fmt:formatDate value='${activeSignup.endtime}' pattern='yyyy-MM-dd'/>" />
						</td>
					</tr>
					<tr>
						<th></th>
						<td colspan="2" width="360" height="45">
							<button type="button" id="chaxun" name="chaxun">查询</button></td>
						<td colspan="1" width="360" height="45"><button type="button" style="background-color: rgb(249, 119, 0);" id="reset" name="reset">重置</button></td>
					</tr>
				</tbody>
			</table>
		<p>当前记录共${page.totalResult}条</p>
		<table width="1200" id="table1" style="word-break:break-all;overflow:hidden;">
			<thead>
				<tr style="background-color:#999;">
					<th>序号</th>
					<th>订单编号</th>
					<th>支付流水号</th>
					<th>活动名称</th>
					<th>领队电话</th>
					<th>申请退款时间</th>
					<th>已付款金额(元)</th>
					<th>退款总额(元)</th>
					<th>退款状态</th>
					<th>退款备注</th>
					<th>操作</th>
				</tr>
			</thead>
			<c:forEach items="${listActiveSignup}" var="listActiveSignup"
				varStatus="status">
				<tbody align="center">
					<tr>
						<td width="8%">${page.currentResult+status.count}</td>
						<td width="8%"><a href="activeSignup/orderDetails.html?asu_id=${listActiveSignup.asu_id}" target="_blank">${listActiveSignup.asu_order_id }</a></td>
						<td width="8%">${listActiveSignup.asu_user_account_num }</td>
						<td width="8%"><a
							href="activity/detail/${listActiveSignup.asu_active_id}.html"
							target="_blank">${listActiveSignup.title }</a></td>
						<td width="8%">${listActiveSignup.mobile }</td>
						<td width="13%"><fmt:formatDate
								value="${listActiveSignup.asu_refund_time }"
								pattern="yyyy-MM-dd HH:mm:ss" /></td>
						<td width="8%">${listActiveSignup.asu_account_paid }</td>
						<td width="8%">${listActiveSignup.asu_refund_price }</td>
						<td width="8%"><c:forEach items="${StateList}" var="StateList">
								<c:if
									test="${StateList.sus_id==listActiveSignup.asu_refund_state}">  
								${StateList.sus_description }
                    		</c:if>
							</c:forEach></td>
						<td width="15%">${listActiveSignup.asu_leader_refund_area }</td>
						<td width="8%">
							<c:choose>
								<c:when test="${listActiveSignup.asu_refund_state==90}">
									<button type="button" id="yes" style="color:white; background-color: green; cursor: pointer;" onclick="result('${listActiveSignup.asu_id}','yes')">退款同意</button>
									<button type="button" id="no" style="color:white; background-color: red; cursor: pointer;" onclick="result('${listActiveSignup.asu_id}','no')">退款不同意</button>
								</c:when>
							</c:choose>
						</td>
					</tr>
				</tbody>
			</c:forEach>
		</table>
		<div class="hr-line-dashed"></div>
		<div class="text-center">${page.pageStr }</div>
		</form>
	</div>
	<!-- 页脚 -->
	<jsp:include page="/view/index/footer.jsp"></jsp:include>
</body>
<!-- 日期插件 -->
<script src="static/js/plugins/layer/laydate/laydate.js"></script>
<!-- iCheck -->
<script src="static/js/plugins/iCheck/icheck.min.js"></script>
<script>
	laydate.skin('molv');
	var start = {
		elem : '#start',
		format : 'YYYY-MM-DD',
		min : '2000-01-01', //设定最小日期为2000-01-01
		max : laydate.now(), //最大日期
		istime : false,
		istoday : false,
		choose : function(datas) {
			end.min = datas; //开始日选好后，重置结束日的最小日期
			end.start = datas; //将结束日的初始值设定为开始日
			if ($("#start").val() > $("#end").val()) {
				$("#end").trigger("click");
			}
		}
	};
	var end = {
		elem : '#end',
		format : 'YYYY-MM-DD',
		min : '2000-01-01',
		max : laydate.now(),
		istime : false,
		istoday : false,
		choose : function(datas) {
			start.max = datas;
			if ($("#end").val() < $("#start").val()) {
				$("#start").trigger("click");
			}
		}
	};
	laydate(start);
	laydate(end);
	
	function result(asu_id,oper){
		if(oper=="yes"){
			if(confirm("您对退款真的满意么?一经选择将不能更改了哦!")){
				$.post("activeSignup/updateRefund",{"asu_id":asu_id,},
					function(data){
						data=eval('('+data+')');
						if(data.RESPONSE_STATE=="200"){
							alert(data.SUCCESS_INFO);
						}else{
							alert(data.ERROR_INFO);
						}
						location=location;
					}
				);
			}
		}else if(oper=="no"){
		var propose=prompt("请输入您对退款不满意的地方:");
			if(propose.trim()==""){
				alert("请输入退款不满意备注信息!");
				$("#no").click();
			}else if(propose.length>100){
				alert("退款不满意备注不能超过100个字哦!");
			}else if(propose.trim()!=""){
				$.post("activeSignup/updateRefund",{"asu_id":asu_id,"propose":propose},
					function(data){
						data=eval('('+data+')');
						if(data.RESPONSE_STATE=="200"){
							alert(data.SUCCESS_INFO);
						}else{
							alert(data.ERROR_INFO);
						}
						location=location;
					}
				);
			}
		}
	}
	
	$("#chaxun").click(
			function(){
					var reg=/^[0-9]{0,10}$/;
					if(!reg.test($("#replace_aaid").val())){
						alert("您输入的活动id格式不正确");
					}else{
						$("#asu_active_id").val($("#replace_aaid").val()==""?0:$("#replace_aaid").val());
						$("#activeSignup").submit();
					}
			}
		);
	$("#reset").click(//重置事件
		function(){
			$("input").val("");
			$("#asu_active_id").val(0);
			$("select").val(0);
			$("#activeSignup").submit();
		}
	);
	$(function(){
		$("dd").css("margin-left","0cm");
	});
</script>
</html>
