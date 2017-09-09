<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="/">

<title>个人信息</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link rel="shortcut icon" href="static/images/logo/logo.jpg"/>

		<link href="static/css/bootstrap.min.css" rel="stylesheet">
	    <link href="static/css/font-awesome.min.css" rel="stylesheet">
		<link href="static/css/plugins/cropper/cropper.min.css" rel="stylesheet">
	    <link href="static/css/animate.min.css" rel="stylesheet">
	    <link href="static/css/style.min.css" rel="stylesheet">
		 
		<style type="text/css">
		.image-crop{
		width: 200px; height: 200px;
		}
		.img-preview{
		width: 100px; height: 100px;
		}
		</style>



<style type="text/css">
#div1 {
	margin: 0cm 10cm;
	font-size: 16px;
	font-weight: 100px;
	color: #999;
	background-color: #F0F0F0;
	width: 1000px;
	border: 0px solid #ccc;
}

#div2 {
	background-color: #FFF;
	float: right;
	width: 300px;
	height: 120px;
	margin: 15px;;
	padding: 2px;
	border: 1px solid #ccc;
}

tr input {
	line-height: 120%;
	padding: 2px 5px;
}

.text {
	width: 133px;
	height: 20px;
}

span {
	color: red;
	font-size: 5px;
	padding-top: 0px;
	margin-top: 0px;
}

.radio {
	width: 15px;
	height: 15px;
	display: inherit;
}

button {
	width: 50px;
	height: 35px;
	float: left;
	cursor: pointer;
	background-color: green;
	color: white;
}

.touxiang {
	float: left;
	border-radius: 50%;
	margin: 2px;
	padding: 2px;
	width: 100px;
	height: 100px
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
label{
	margin-bottom: 1px;
}
</style>
</head>

<body>
	<!-- 导航 -->
	<jsp:include page="/view/index/navigate.jsp"></jsp:include>
	
	<div>
		<form method="post" id="usersForm">
			<div id="div1">
				<div id="div2">
					<img src='<c:if test="${Users.uHeadImg==null}">static/portrait/default.jpg</c:if><c:if test="${Users.uHeadImg!=null}">${Users.uHeadImg}</c:if>' style="cursor: pointer;" onclick="heading()"
					 class="touxiang" alt="用户头像" /><br />
					<strong>用户名:<label>${Users.uName}<label style="color:#FF047F;">${Users.grade_name}</label></label></strong><br/>
					 当前积分:<label style="color:#FF047F;">${Users.integral}</label><br/>
					 用户类型:<label>
						<c:if test="${Users.uType==1}">个人发布者</c:if> <c:if
							test="${Users.uType==2}">企业</c:if> <c:if test="${Users.uType==3}">普通用户</c:if>
						<c:if test="${Users.uType==4}">后台管理用户</c:if> </label> <br /> 评分剩余量:<label>${Users.uScoreNum}
						</label>
				</div>
				<h1>个人信息</h1>
				<table width="600" border="0">
					<tr>
						<td width="157" align="right" valign="middle"><span>*</span>昵称:</td>
						<td width="433" align="left" valign="middle"><input
							type="text" class="text" id="uName" name="uName"
							value="${Users.uName}" maxlength="20" />
						</td>
					</tr>
					<tr>
						<td align="right" valign="middle"><span>*</span>性别:</td>
						<td align="left" valign="middle"><input type="radio"
							class="radio" id="sex1" name="uSex" value="1"
							<c:if test="${Users.uSex==1}">checked="checked"</c:if> /><label
							for="sex1">男</label> <input type="radio" class="radio" id="sex2"
							name="uSex" value="2"
							<c:if test="${Users.uSex==2}">checked="checked"</c:if> /><label
							for="sex2">女</label>
					</tr>
					<tr>
						<td align="right" valign="middle">常用登录地区:</td>
						<td align="left" valign="middle"><input type="text"
							id="uLoginAreaCode" name="uLoginAreaCode"
							value="${Users.uLoginAreaCode}" maxlength="20" />
						</td>
					</tr>
					<tr>
						<td align="right" valign="middle">邮箱:</td>
						<td align="left" valign="middle"><label id="uEmail"
							name="uEmail">${Users.uEmail }</label>
						</td>
					</tr>
					<tr>
						<td align="right" valign="middle">手机:</td>
						<td align="left" valign="middle"><label id="uPhone"
							name="uPhone">${Users.uPhone}</label>
						</td>
					</tr>
					<tr>
						<td align="right" valign="middle">账户状态:</td>
						<td align="left" valign="middle">
							<c:if test="${Users.isFroZen==1}"><label style="color: red;">冻结中……</label></c:if>
							<c:if test="${Users.isFroZen==2}"><label style="color: green;">正常 </label></c:if>
						</td>
					</tr>
					<tr>
						<td align="right" valign="middle"></td>
						<td align="right" valign="middle"><button type="button"
								id="subUser" name="subUser">提交</button></td>
					</tr>
				</table>
			</div>
		</form>
	</div>
	<!-- 页脚 -->
	<jsp:include page="/view/index/footer.jsp"></jsp:include>

</body>

    <script src="static/js/plugins/layer/layer.js"></script>
    <script src="static/js/bootstrap.min.js"></script>
 
    
    

<script type="text/javascript">
	function heading(){
		layer.open({
	        type: 2,
	        title: "修改头像",
	        shade: 0.2,
	        area: ["25%", "80%"],
	        offset:["120px","650px"],
	        content: "users/selectHead.html"
   		 });
	}

	$("#subUser").click(
		function() {
			if ($("#uName").val().trim() == "") {
				layer.alert("请输入昵称!", {
					icon : 7,
					title : "友情提示"
				});
			}else {
				$.post("users/updateUsers",$("#usersForm").serialize(),
					function(data) {
						if (data.RESPONSE_STATE == '200') {
							alert(data.SUCCESS_INFO);
							history.go(0);
						} else {
							alert(data.ERROR_INFO);
						}
					}
				);
			}
		}
	);
	$(function(){
		$("dd").css("margin-left","0cm");
		$("div>span").css({"color":"#676a6c","font-size":"13px"});
	});
</script>
</html>
