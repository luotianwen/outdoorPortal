<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="/">
    
    <title>修改密码</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="修改">
	<meta http-equiv="description" content="修改密码">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
	<!-- 导航 -->
	<jsp:include page="/view/index/navigate.jsp"></jsp:include>
	
	
<div style="width: 1200px;margin: 50 auto;">
	当前账号所绑定手机号码：${sessionScope.SESSION_USER.uPhone }
    <br>
    <br>
	
  <div style="width: 120px;float: left;">
  	手机验证码：
  </div>
    <input type="text" id="yzm">&nbsp;&nbsp;<button type="button" id="getOldPhoneYzm" >获取手机验证码</button>
	
    <br>
    <br>
    
  <div style="width: 120px;float: left;">
  	新密码：
  </div>
    <input type="password" id="newPassword" >
    
    <br>
    <br>
    
  <div style="width: 120px;float: left;">
  	重复新密码：
  </div>
    <input type="password" id="reNewPassword">
    
    <br>
    <br>
    
    <button type="button" id="submit">确认修改</button>
  </div>
  
	<!-- 页脚 -->
	<jsp:include page="/view/index/footer.jsp"></jsp:include>
	
	<script type="text/javascript">
	
	$(function(){
		$("#getOldPhoneYzm").click(function(){
			var this_obj = $(this);
			this_obj.prop("disabled",true);
			
			layer.load(1,{shade:0.3});
			$.post("emayInfo/sendOldPhoneYzm.do",{
				template:"updatePassword"
			},function(data){
				layer.closeAll('loading');
				data = eval("("+data+")");
				if(data.RESPONSE_STATE == "200"){
					getPhoneYzmDjs(this_obj,"获取手机验证码");
				}else{
					layer.alert(data.ERROR_INFO,{icon:0});
					this_obj.prop("disabled",false);
				}
			});
		});
		$("#submit").click(function(){
			var pwdReg = /^(?=^.{8,15}$)((?=.*\d)|(?=.*\W+))(?![.\n])(?=.*[A-Z])(?=.*[a-z]).*$/;
			var newPassword = $("#newPassword").val();
			if(!pwdReg.test(newPassword)){
				layer.msg("密码至少包含一个大写字母、一个小写字母，长度8-15位，",{icon:0});
				return;
			}
			if($("#reNewPassword").val() != newPassword){
				layer.msg("两次输入密码不一致!",{icon:0});
				return;
			}
			if($("#yzm").val().trim() == ""){
				layer.msg("请输入验证码",{icon:0});
				return;
			}
			layer.confirm("是否确认修改",{icon:3},function(index){
				layer.close(index);
				layer.load(1,{shade:0.3});
				
				$.post("users/updatePassword.do",{
					newPassword:newPassword,
					yzm:$("#yzm").val()
				},function(data){
					data = eval("("+data+")");
					if(data.RESPONSE_STATE == "200"){
						layer.msg("修改成功！",{icon:1,time:900},function(){
							location.reload();
						});
					}else{
						layer.closeAll('loading');
						layer.alert(data.ERROR_INFO,{icon:0});
					}
				});
			});
			
		});
	
	});
	
	
	var get_old_djs=60;
	function getPhoneYzmDjs(obj,txt){
		if(get_old_djs > 0){
			obj.html("已发送,"+get_old_djs+"秒后可再次发送");
			get_old_djs--;
			setTimeout(function(){
				getPhoneYzmDjs(obj,txt);
			},999);
		}else{
			obj.prop("disabled",false);
			obj.html(txt);
			get_old_djs = 60;
		}
	}
	
	</script>
</html>
