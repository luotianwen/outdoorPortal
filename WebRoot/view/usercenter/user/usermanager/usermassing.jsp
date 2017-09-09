<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="/">

<title>常用联系人管理</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link rel="shortcut icon" href="static/images/logo/logo.jpg"/>

<style type="text/css">
#table1 tr {
	border-bottom-width: 1px;
	border-bottom-style: solid;
	border-bottom-color: rgb(180, 181, 181);
	height: 40px;
	background-color: rgb(220, 220, 220);
}

#table1 th {
	margin: 0px;
	padding: 0px;
	font-weight: normal;
	text-align: center;
	width: 60px;
	border-right-color: rgb(180, 181, 181);
}

p span {
	color: rgb(255, 255, 255);
	font-family: & #39; Microsoft Yahei&#39; , simsun;
	font-size: 14px;
	line-height: 33px;
	text-align: center;
	background-color: rgb(62, 170, 62);
}

#form1 {
	margin: 0px 0px 30px;
	padding: 30px 0px 0px;
	float: center;
	border: 1px solid rgb(220, 220, 220);
	color: rgb(89, 87, 87);
	font-family: & #39; Microsoft Yahei&#39; , simsun;
	font-size: 14px;
	white-space: normal;
	background-color: rgb(255, 255, 255);
}

#form1 ul {
	margin: 0px;
	padding: 0px;
}

#form1 th {
	margin: 0px;
	padding: 0px 25px 0px 30px;
	font-weight: normal;
	text-align: right;
	width: 130px;
	line-height: 18px;
}

#form1 span {
	padding-right: 10px;
	color: rgb(236, 105, 25);
	vertical-align: middle;
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

#form1 td {
	margin: 0px;
	padding: 0px;
}

#form1 th {
	margin: 0px;
	padding: 0px 25px 0px 30px;
	font-weight: normal;
	text-align: right;
	vertical-align: top;
	width: 130px;
	line-height: 18px;
}

#form1 label>input {
	margin-top: 0px;
	margin-right: 8px;
	margin-left: 0px;
	padding: 0px;
	font-family: inherit;
	font-size: inherit;
	font-weight: inherit;
	vertical-align: -2px;
}

button {
	display: block;
	width: 140px;
	height: 35px;
	line-height: 35px;
	margin: 0px auto;
	color: rgb(255, 255, 255);
	font-size: 18px;
	border-radius: 3px;
	cursor: pointer;
	font-family: & #39; miscrsoft yahei&#39;;
	border: none;
	background-color: rgb(60, 180, 60);
}

#form1 select {
	font-family: inherit;
	font-size: inherit;
	font-weight: inherit;
	margin: 0px;
	padding: 3px 9px;
	border-color: rgb(220, 220, 221);
}

label.error {
	color: #cc5965;
	display: inline-block;
	margin-left: 5px;
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
</head>

<body>
	<!-- 导航 -->
	<jsp:include page="/view/index/navigate.jsp"></jsp:include>
	<div align="center">
		<table width="1000" id="table1">
			<thead>
				<tr>
					<th width="60">序号</th>
					<th width="100px">姓名</th>
					<th width="45px">性别</th>
					<th width="90px">手机号码</th>
					<th width="75px">证件类型</th>
					<th width="140px">证件号</th>
					<th width="90px">应急联系人</th>
					<th width="110px">应急联系人手机</th>
					<th width="auto">操作</th>
				</tr>
			</thead>
			<c:forEach items="${listUser}" var="listUser" varStatus="Status">
				<tbody>
					<tr id="${listUser.lsuu_id }">
						<td>${Status.count }</td>
						<td>${listUser.lsuu_user_name }</td>
						<td><c:if test="${listUser.lsuu_user_sex==1 }">男</c:if> <c:if
								test="${listUser.lsuu_user_sex==2 }">女</c:if></td>
						<td><label>${listUser.lsuu_phone }</label></td>
						<td><c:if test="${listUser.lsuu_cart_type==1 }">身份证</c:if> <c:if
								test="${listUser.lsuu_cart_type==2 }">护照</c:if> <c:if
								test="${listUser.lsuu_cart_type==3 }">军官证</c:if> <c:if
								test="${listUser.lsuu_cart_type==4 }">港澳回乡证或台胞证</c:if></td>
						<td>${listUser.lsuu_cart_num }</td>
						<td>${listUser.lsuu_emgc_user_name }</td>
						<td>${listUser.lsuu_emgc_user_phone }</td>
						<td style="color: green;"><input type="button"
							onclick="upd(${listUser.lsuu_id})" value="修改"
							style="background-color: #3eaa3e;color: white;cursor: pointer;" /><input
							type="button" onclick="del('${listUser.lsuu_id}')" value="删除"
							style="background-color: #f97700;color: white;cursor: pointer;" />
						</td>
					</tr>
				</tbody>
			</c:forEach>
		</table>
		<p>
			<span style="cursor:pointer" id="addUser" name="addUser">添加新的常用联系人</span>
		</p>
		<form id="form1" style="display: none;">
			<ul>
				<li>
					<p>
						<br />
					</p></li>
				<li>
					<table width="940" align="center">
						<tbody>
							<tr>
								<th><span>*</span>真实姓名：</th>
								<td colspan="2"><input type="text" id="lsuu_user_name"
									name="lsuu_user_name" /></td>
							</tr>
							<tr>
								<th><span>*</span>性别：</th>
								<td colspan="2"><label><input type="radio"
										id="sex1" name="lsuu_user_sex" checked="checked" value="1" />男</label>
									<label><input type="radio" id="sex2"
										name="lsuu_user_sex" value="2" />女</label></td>
							</tr>
							<tr>
								<th><span>*</span>手机号码：</th>
								<td><input type="text" id="lsuu_phone" name="lsuu_phone" />
								</td>
								<td></td>
							</tr>
							<tr>
								<th>证件类型：</th>
								<td><select id="lsuu_cart_type" name="lsuu_cart_type"
									onchange="javascript:$('#lsuu_cart_num').val('');$('#lsuu_cart_num-error').remove();">
										<option value="1">身份证</option>
										<option value="2">护照</option>
										<option value="3">军官证</option>
										<option value="4">港澳回乡证或台胞证</option>
								</select></td>
								<td></td>
							</tr>
							<tr>
								<th>证件号码：</th>
								<td><input type="text" id="lsuu_cart_num"
									name="lsuu_cart_num" maxlength="50"/></td>
								<td></td>
							</tr>
							<tr>
								<th>出生日期：</th>
								<td><input type="text" class="laydate-icon" style="cursor: pointer;"
									id="lsuu_birthday" name="lsuu_birthday" readonly="readonly" /></td>
								<td></td>
							</tr>
						</tbody>
					</table>
					<table width="940">
						<tbody>
							<tr>
								<th>装备情况：</th>
								<td style="position: relative;"><textarea
										style="resize:vertical; height:100px; width:400px;"
										id="lsuu_equipment" name="lsuu_equipment" maxlength="150"></textarea></td>
							</tr>
							<tr style="height: 20px;">
								<td></td>
								<td></td>
							</tr>
							<tr>
								<th>户外经验：</th>
								<td style="position: relative;">
								<textarea
										style="resize:vertical; height:100px; width:400px;"
										id="lsuu_experience" name="lsuu_experience" maxlength="150"></textarea></td>
							</tr>
						</tbody>
					</table>
					<table width="940">
						<tbody>
							<tr>
								<th>应急联系人姓名：</th>
								<td colspan="2"><input type="text" id="lsuu_emgc_user_name"
									name="lsuu_emgc_user_name" maxlength="10" /></td>
							</tr>
							<tr>
								<th>应急联系人手机：</th>
								<td colspan="2"><input type="text"
									id="lsuu_emgc_user_phone" name="lsuu_emgc_user_phone" /></td>
							</tr>
						</tbody>
					</table></li>
			</ul>
			<p>
				<button type="button" id="sub" name="sub" value="Insert">保存</button>
			</p>
		</form>
	</div>
	<!-- 页脚 -->
	<jsp:include page="/view/index/footer.jsp"></jsp:include>
</body>
<script src="static/js/jquery-2.1.1.min.js" type="text/javascript"></script>
<script src="static/js/plugins/layer/layer.js"></script>
<!-- jQuery Validation plugin javascript-->
<script src="static/js/plugins/validate/jquery.validate.min.js"></script>
<script src="static/js/plugins/validate/messages_zh.min.js"></script>
<!-- 日期插件 -->
<script src="static/js/plugins/layer/laydate/laydate.js"></script>
<script>
  		laydate.skin('molv');
  		var start = {
		    elem: '#lsuu_birthday',
		    format: 'YYYY-MM-DD',
		    min: '1900-01-01', //设定最小日期为2000-01-01
		    max: laydate.now(), //最大日期
		    istime: false,
		    istoday: true,
		    choose: function(datas){}
		};
  		laydate(start);
  		
  		function check(){
  			if($("#lsuu_user_name").val()!=""){
  				return false;
  			}else if($("#lsuu_phone").val()!=""){
  				return false;
  			}else if($("#lsuu_cart_num").val()!=""){
  				return false;
  			}else if($("#lsuu_birthday").val()!=""){
  				return false;
  			}else if($("#lsuu_equipment").val()!=""){
  				return false;
  			}else if($("#lsuu_experience").val()!=""){
  				return false;
  			}else if($("#lsuu_emgc_user_name").val()!=""){
  				return false;
  			}else if($("#lsuu_emgc_user_phone").val()!=""){
  				return false;
  			}else{
  				return true;
  			}
  		}
  		
  		$("#addUser").click(
  			function(){
  				if(check()){
  					$("#form1").css("display","block");
  		  			$("#sub").val("Insert");
  				}else{
  					if(confirm("报名人信息未保存,是否继续?")){
  						$("#form1")[0].reset();
  						$("#form1").css("display","block");
  	  		  			$("#sub").val("Insert");
  					}
  				}
  			}
  		);
  		
  		function upda(val){
  			$.post(
				"SignupUser/selectUserId?lsuu_id="+val
				,function(data,status){
					data = eval('('+data+')');
					if(data.lsuu!=null){
						$("#form1")[0].reset();
			  			$("#form1").css("display","block");
			  			$("#sub").val("Update");
						
						$("#lsuu_user_name").val(data.lsuu.lsuu_user_name);
						$("#sex"+data.lsuu.lsuu_user_sex).prop("checked",true);
			  			$("#lsuu_phone").val(data.lsuu.lsuu_phone);
		  				$("option[value="+data.lsuu.lsuu_cart_type+"]").prop("selected","selected");
			  			$("#lsuu_cart_num").val(data.lsuu.lsuu_cart_num);
			  			$("#lsuu_birthday").val(data.lsuu.lsuu_birthday.substring(0,10));
			  			$("#lsuu_equipment").val(data.lsuu.lsuu_equipment);
			  			$("#lsuu_experience").val(data.lsuu.lsuu_experience);
			  			$("#lsuu_emgc_user_name").val(data.lsuu.lsuu_emgc_user_name);
			  			$("#lsuu_emgc_user_phone").val(data.lsuu.lsuu_emgc_user_phone);
					}else if(data.lsuu==null){
						alert("您已经删除了该联系人,无法进行修改操作!");
						location.reload();
					}else{
						alert(data.ERROR_INFO);
					}
				}
			);
  		}
  		
  		var lsuu_id="";
  		function upd(val){//修改单击事件
  			lsuu_id=val;
  			if(!check()){
  				if(confirm("报名人信息未保存,是否继续?")){
  					upda(val);
  				}
			}else{
				upda(val);
			}
  			
		}
				
		function del(val){//删除单击事件
			if(!check()){
  				if(confirm("报名人信息未保存,是否继续?")){
  					if(confirm("确认删除么?")==true){
						$.post(
								"SignupUser/deleteUser.html",{"lsuu_id":val}
								,function(data,status){
									data = eval('('+data+')');
									if(data.RESPONSE_STATE=="200"){
										alert("删除成功!");
										location.reload();
									}else{
										alert("删除失败!");
									}
								}
							);
					}
  				}
			}else{
				if(confirm("确认删除么?")==true){
					$.post(
							"SignupUser/deleteUser.html",{"lsuu_id":val}
							,function(data,status){
								data = eval('('+data+')');
								if(data.RESPONSE_STATE=="200"){
									alert("删除成功!");
									location.reload();
								}else{
									alert("删除失败!");
								}
							}
						);
				}
			}
		}
		
		
		jQuery.validator.addMethod("idCardCheck", function(value,element) {
				if($("#lsuu_cart_type").val()==1){//验证身份证格式
					var length = value.length;
			        var idCard = /^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}|([1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X))$/;
			        return this.optional(element) || ((length==15 || length==18) && idCard.test(value));
				}else{
					return true;
				}
			}, "请输入正确的身份证号");
  		
  		/**
  		*根据身份证号,获得相应的出生日期.
  		*/
		$("#lsuu_cart_num").keyup(
			function(){
				var reg = /^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}|([1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X))$/;
				if(reg.test($("#lsuu_cart_num").val())){
					if($("#lsuu_cart_num").val().length==15){//15位身份证号
						var year=$("#lsuu_cart_num").val().substring(6,8);//年
						var month=$("#lsuu_cart_num").val().substring(8,10);//月
						var day=$("#lsuu_cart_num").val().substring(10,12);//日
						$("#lsuu_birthday").val(19+year+"-"+month+"-"+day);
					}else if($("#lsuu_cart_num").val().length==18){
						var year=$("#lsuu_cart_num").val().substring(6,10);//年
						var month=$("#lsuu_cart_num").val().substring(10,12);//月
						var day=$("#lsuu_cart_num").val().substring(12,14);//日
						$("#lsuu_birthday").val(year+"-"+month+"-"+day);
					}
				}
			}
		);
		//手机格式验证
		jQuery.validator.addMethod("mobileCheck", function(value,element) {
	        var length = value.length;
	        var mobile = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1})|(17[0-9]{1}))+\d{8})$/;   
	        return this.optional(element) || (length==11 && mobile.test(value));   
			}, "请填写正确的手机号码");
		//应急联系人不能与本人手机号相同
		jQuery.validator.addMethod("emergencyMobile", function(value,element) {
			if(value==""){
				return true;
			}else if(value!="" && value!=$("#lsuu_phone").val()){
				return true;
			}else if(value!="" && value==$("#lsuu_phone").val()){
				return false;
			}
		},"应急联系人手机不能与本人手机相同!");
		$("#form1").validate({
			rules: {
				lsuu_user_name:{
					required:true,
					rangelength:[2,10],
				},
				lsuu_phone:{
					required:true,
					mobileCheck:true,
				},
				lsuu_emgc_user_phone:{
					mobileCheck:true,
					emergencyMobile:true,
				},
				lsuu_cart_num:{
					idCardCheck:true,
					maxlength:50
				},
				lsuu_emgc_user_name:{
					rangelength:[2,10],
				},
			},
			messages: {
				lsuu_user_name:{
					required:"请输入真实姓名",
					rangelength:"真实姓名不能少于2个字且不能大于10个字",
				},
				lsuu_phone:{
					required:"请输入手机号",
					mobileCheck:"请输入正确的手机号码"
				},
				lsuu_emgc_user_phone:{
					mobileCheck:"请输入正确的应急联系人手机!您也可以不输入!",
				},
				lsuu_cart_num:{
					idCardCheck:"请输入正确的身份证号",
				},
				lsuu_emgc_user_name:{
					rangelength:"应急联系人姓名不能少于2个字且不能大于10个字!您也可以不输入!"
				},
			}
		});
		
		$("#sub").click(//新增或修改操作
					function(){
						if($("#form1").valid()){
							if($("#sub").val()=="Insert"){
								$.post(
									"SignupUser/insertUser"
									,$("#form1").serialize()
									,function(data,status){
										data = eval('('+data+')');
										if(data.RESPONSE_STATE=="200"){
											alert("新增成功!");
											location.reload();
										}else{
											alert("新增失败!");
										}
									}
								);
							}else if($("#sub").val()=="Update"){
								$.post(
									"SignupUser/updateUser/"+lsuu_id
									,$("#form1").serialize()
									,function(data,status){
										data = eval('('+data+')');
										if(data.RESPONSE_STATE=="200"){
											alert("修改成功!");
											location.reload();
										}else{
											alert("修改失败!");
										}
									}
								);
							}
						}
					}
				);
	
	$(function(){
		$("dd").css("margin-left","0cm");
	});
  </script>
</html>
