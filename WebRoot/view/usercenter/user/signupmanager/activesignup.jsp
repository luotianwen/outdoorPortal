<%@page import="java.io.Console"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="/">

<title>我的报名</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link rel="shortcut icon" href="static/images/logo/logo.jpg"/>


<style type="text/css">
#activity1 th {
	margin: 0px;
	padding: 0px 20px 0px 0px;
	font-weight: normal;
	text-align: right;
	width: 100px;
}

#activity1 td {
	margin: 0px;
	padding: 0px;
}

#activity1 input {
	margin: 0px;
	padding: 3px 5px;
	font-family: inherit;
	font-size: inherit;
	font-weight: inherit;
	height: 23px;
	border: 1px solid rgb(220, 220, 221);
	line-height: 20px;
}

#activity1 span {
	padding-right: 10px;
	color: rgb(236, 105, 25);
	vertical-align: middle;
}

#activity1 button {
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

#activity1 label {
	font-family: inherit;
	font-size: inherit;
	font-weight: inherit;
	width: 110px;
	height: 31px;
	margin: 0px;
	padding: 4px 9px;
	border-color: rgb(220, 220, 221);
	color: rgb(89, 87, 87);
}

#activity1 option {
	height: 30px;
	line-height: 30px;
}

#activity2 th {
	margin: 0px;
	padding: 0px;
	font-weight: normal;
	text-align: center;
	width: 34px;
	border-right-color: rgb(180, 181, 181);
}

#activity2 td {
	margin: 0px;
	padding: 0px;
}

#activity2 tr {
	border-bottom-width: 1px;
	border-bottom-style: solid;
	border-bottom-color: rgb(180, 181, 181);
	height: 50px;
	background-color: rgb(220, 220, 220);
}
a{
	color: #333;
    text-decoration: none;
    text-decoration: none !important;
    cursor: pointer !important;
    font-size: 13px;}
a:focus, a:hover {
    color: #23527c;
    text-decoration: underline;
}
</style>

<!-- iCheck -->
<link href="static/css/style.min.css" rel="stylesheet">
<link href="static/css/page.css" rel="stylesheet">
</head>


<body>
	<!-- 导航 -->
	<jsp:include page="/view/index/navigate.jsp"></jsp:include>
	
	<div align="center">
		<form id="activeSignup" action="activeSignup/selectActive.html?param=myactivity" method="post" style="margin-left:5cm;">
			<table id="activity1">
				<thead>
					<tr>
						<th>活动ID：</th>
						<td width="360" height="45"><input type="text"
							id="replace_aaid" name="replace_aaid"
							value="<c:if test="${activeSignup.asu_active_id!=0}">${activeSignup.asu_active_id}</c:if>" />
							<input type="hidden" id="asu_active_id" name="asu_active_id" value="${activeSignup.asu_active_id}"/>
						</td>
						<th>活动名称：</th>
						<td width="360" height="45"><input type="text" id="title"
							name="title" value="${activeSignup.title }" />
						</td>
					</tr>
					<tr>
						<th>报名编号：</th>
						<td width="360" height="45"><input type="text" id="asu_id"
							name="asu_id" value="${activeSignup.asu_id }" />
						</td>
						<th><span></span>报名状态：</th>
						<td width="360" height="45"><select
								id="asu_state" name="asu_state" style="height: 25px; width: 206px;">
									<option value="0">全部状态</option>
									<c:forEach items="${StateList}" var="StateList">
										<option value="${StateList.sus_id}"
											<c:if test="${StateList.sus_id==activeSignup.asu_state}"> selected="selected" </c:if>>
											${StateList.sus_description }</option>
									</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<th>报名时间：</th>
						<td width="300" height="45" colspan="2"><input type="text"
							class="laydate-icon" id="start" name="starttime"
							style="cursor:pointer; margin: 0px; padding: 3px 5px; font-family: inherit; font-size: inherit; font-weight: inherit; width: 200px; height: 23px; border: 1px solid rgb(220, 220, 221); line-height: 20px;"
							readonly="readonly" value="<fmt:formatDate value='${activeSignup.starttime}' pattern='yyyy-MM-dd'/>" /><span
							class="timeto" style="margin: 0px 13px;">到</span><input
							type="text" class="laydate-icon" id="end" name="endtime"
							style="cursor:pointer; margin: 0px; padding: 3px 5px; font-family: inherit; font-size: inherit; font-weight: inherit; width: 200px; height: 23px; border: 1px solid rgb(220, 220, 221); line-height: 20px;"
							readonly="readonly" value="<fmt:formatDate value='${activeSignup.endtime}' pattern='yyyy-MM-dd'/>" />
						</td>
					</tr>
					<tr>
						<th></th>
						<td colspan="2" width="360" height="45">
							<button type="button" id="chaxun" name="chaxun">查询</button>
						</td>
						<td colspan="1" width="360" height="45"><button type="button" style="background-color: rgb(249, 119, 0);" id="reset" name="reset">重置</button></td>
					</tr>
				</thead>
			</table>
		</form>
		<p style="font-size: 18px;">当前记录共${page.totalResult}条</p>
		<table id="activity2" width="1200" style="word-break:break-all;overflow:hidden;">
			<thead>
				<tr align="center" style="background-color:#999;">
					<th>序号</th>
					<th>订单编号</th>
					<th>活动名称</th>
					<th>参加人</th>
					<th>参加人性别</th>
					<th>参加人手机</th>
					<th>报名时间</th>
					<th>活动费用总额(元)</th>
					<th>已付款总额(元)</th>
					<th>报名状态</th>
					<th>操作</th>
				</tr>
			</thead>
			<c:forEach items="${listActiveSignup}" var="listActiveSignup"
				varStatus="status">
				<tbody>
					<tr align="center">
						<td width="8%">${page.currentResult+status.count}</td>
						<td width="8%"><a href="activeSignup/orderDetails.html?asu_id=${listActiveSignup.asu_id}" target="_blank">${listActiveSignup.asu_order_id }</a></td>
						<td width="8%"><a href="activity/detail/${listActiveSignup.asu_active_id}.html" target="_blank">${listActiveSignup.title }</a></td>
						<td width="8%">${listActiveSignup.asu_user_name }</td>
						<td width="8%">
							<c:choose>
								<c:when test="${listActiveSignup.asu_user_sex==1}">男</c:when>
								<c:when test="${listActiveSignup.asu_user_sex==2}">女</c:when>
							</c:choose>
						
						</td>
						<td width="8%"><c:if test="${listActiveSignup.asu_user_phone!=0}">${listActiveSignup.asu_user_phone}</c:if></td>
						<td width="12%"><fmt:formatDate
								value="${listActiveSignup.asu_create_time }"
								pattern="yyyy-MM-dd HH:mm:ss" />
						</td>
						<td width="8%">${listActiveSignup.price }</td>
						<td width="8%">${listActiveSignup.asu_account_paid }</td>
						<td width="8%">
							<c:forEach items="${StateList}"
								var="StateList">
								<c:if test="${StateList.sus_id==listActiveSignup.asu_state}">  
								${StateList.sus_description }
                    		</c:if>
							</c:forEach>
						</td>
						<td width="8%">
								<!-- 领队已经确认，并且已经支付 -->
								<c:if test="${listActiveSignup.asu_state == 20 && listActiveSignup.asu_pay_state == 2}">
									<button type="button" onclick="complete('${listActiveSignup.asu_id }')" style="cursor: pointer;">交易完成</button>
								</c:if>
								
								<!-- 交易完成	且	没有评价过的订单	-->
								<c:if test="${listActiveSignup.asu_state == 30 && listActiveSignup.asu_is_comment == 0}">
									<button type="button" onclick="comments(${listActiveSignup.asu_active_id},'${listActiveSignup.asu_id }')" style="cursor: pointer;">立即评价</button>
								</c:if>
								
							<!-- 取消报名 -->
								<c:if
									test="${listActiveSignup.asu_state!=40 && listActiveSignup.asu_state!=50}">
									<button type="button" id="cancel"
										style="cursor: pointer;background-color: red;color:white;"
										onclick="cl('${listActiveSignup.asu_id}')">取消报名</button>
									<!-- 立即支付 -->
									<c:if test="${listActiveSignup.asu_pay_state == 1}">
									<button type="button" id="cancel" style="cursor: pointer;background-color: yellow;color:blank;" onclick="pay('${listActiveSignup.asu_id}')">立即支付</button>
									</c:if>
								</c:if>
								<!-- 报名详情 -->
								<button type="button" id="cancel"style="cursor: pointer;background-color: blue;color:white;"
										onclick="window.open('activeSignup/orderDetails.html?asu_id=${listActiveSignup.asu_id}')">查看报名详情</button>
								
						</td>
					</tr>
				</tbody>
			</c:forEach>
		</table>
		<div class="hr-line-dashed"></div>
		<div class="text-center">${page.pageStr }</div>
	</div>
	
	
	<!-- 页脚 -->
	<jsp:include page="/view/index/footer.jsp"></jsp:include>
</body>
<!-- 日期插件 -->
<script type="text/javascript"
	src="static/js/plugins/layer/laydate/laydate.js"></script>


<script>
if(${not empty requestScope.isAlreadySignup}){
	layer.msg("您已经报名该活动",{icon:6,time:1000,shade:0.3});
}

laydate.skin('molv');
var start = {
    elem: '#start',
    format: 'YYYY-MM-DD',
    min: '2000-01-01', //设定最小日期为2000-01-01
    max: laydate.now(), //最大日期
    istime: false,
    istoday: false,
    choose: function(datas){
    	end.min = datas; //开始日选好后，重置结束日的最小日期
    	if($("#start").val()>$("#end").val()){
    		$("#end").trigger("click"); 
	    }
    }
};
var end = {
    elem: '#end',
    format: 'YYYY-MM-DD',
    min: '2000-01-01',
    max: laydate.now(),
    istime: false,
    istoday: false,
    choose: function(datas){
    	start.max=datas;
    	if($("#end").val()<$("#start").val()){
    		$("#start").trigger("click");
    	}
    }
};
laydate(start);
laydate(end);


	function cl(asu_id){//取消报名
		if(confirm("您真的要取消报名么?")){
			$.post("activeSignup/updateActive",{"asu_id":asu_id}
				,function(data){
					data = eval('('+data+')');
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
	
	function pay(asu_id){
		$.post("activeSignup/gso.do",{"asu_id":asu_id},function(data){
			data=eval('('+data+')');
			if(data.RESPONSE_STATE=='200'){
				var url = "view/activity/signUp/pay.jsp?out_trade_no="+data.out_trade_no;
				window.open(url);
			}else{
				alert(data.ERROR_INFO);
			}
		});
	}
	
	$("#chaxun").click(
		function(){
				var reg=/^[0-9]{0,38}$/;
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
		$("div>span").css({"color":"#676a6c","font-size":"13px"});
	});
	
// 评价
function comments(activeId,suId){
	layer.open({
	    type: 2,
	    title:'活动评价',
	    skin: 'layui-layer-molv',
	    area: ['700px', '530px'],
	    fix: true,
	    content: 'view/usercenter/user/signupmanager/activeComments.jsp?activeId='+activeId+'&suId='+suId
	});
}	
	
// 交易完成
function complete(id){
	layer.confirm('是否确认交易完成？',{icon:3},function(index){
		layer.close(index);
		layer.load(0,{
			shade: [0.3, '#393D49']
		});
		$.post('activeSignup/complete.do',{id:id},function(data){
			data = eval('('+data+')');
			if(data.RESPONSE_STATE == '200'){
				layer.msg(data.SUCCESS_INFO,{icon:1,time:800},function(){
					location.reload();
				});
			}else{
				layer.closeAll('loading');
				layer.alert(data.ERROR_INFO,{icon:0});
			}
		});
	});
}

</script>

</html>