<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="/">
    
    <title>修改手机</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="修改">
	<meta http-equiv="description" content="修改手机">
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
  	旧手机验证码：
  </div>
    <input type="text" id="oldPhoneYzm">&nbsp;&nbsp;<button type="button" id="getOldPhoneYzm" >获取手机验证码</button>
	
    <br>
    <br>
    
  <div style="width: 120px;float: left;">
  	新手机号：
  </div>
    <input type="number" id="newPhone" >
    
    <br>
    <br>
    
  <div style="width: 120px;float: left;">
  	新手机验证码：
  </div>
    <input type="text" id="newPhoneYzm">&nbsp;&nbsp;<button type="button" id="getNewPhoneYzm">获取更换手机验证码</button>
    
    <br>
    <br>
    
    <button type="button" id="submit">确认修改</button>
  </div>
  
	<!-- 页脚 -->
	<jsp:include page="/view/index/footer.jsp"></jsp:include>
	
	<script type="text/javascript">
	
	var mobile = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1})|(17[0-9]{1}))+\d{8})$/;
	
	$(function(){
		
		$("#getOldPhoneYzm").click(function(){
			var this_obj = $(this);
			this_obj.prop("disabled",true);
			
			layer.load(1,{shade:0.3});
			$.post("emayInfo/sendOldPhoneYzm.do",{
				template:"oldPhoneInfo"
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
	
		$("#getNewPhoneYzm").click(function(){
			if($("#newPhone").val().trim().length == 11 && mobile.test($("#newPhone").val())){
				var this_obj = $(this);
				this_obj.prop("disabled",true);
				
				layer.load(1,{shade:0.3});
				$.post("emayInfo/sendNewPhoneYzm.do",{
					phone:$("#newPhone").val().trim()
				},function(data){
					layer.closeAll('loading');
					data = eval("("+data+")");
					if(data.RESPONSE_STATE == "200"){
						getPhoneYzmDjs(this_obj,"获取更换手机验证码");
					}else{
						layer.alert(data.ERROR_INFO,{icon:0});
						this_obj.prop("disabled",false);
					}
				});
			}else{
				layer.alert("手机号码不正确",{icon:0});
			}
		});
	
		$("#submit").click(function(){
			var oldPhoneYzm = $("#oldPhoneYzm").val().trim();
			var newPhoneYzm = $("#newPhoneYzm").val().trim();
			var newPhone = $("#newPhone").val().trim();
			if(newPhone.length != 11 || !mobile.test(newPhone)){
				layer.alert("手机号码不正确",{icon:0});
				return;
			}
			if(oldPhoneYzm == ""){
				layer.alert("请填写旧手机验证码",{icon:0});
				$("#getOldPhoneYzm").focus();
				return;
			}
			if(newPhoneYzm == ""){
				layer.alert("请填写新手机验证码",{icon:0});
				$("#getNewPhoneYzm").focus();
				return;
			}
			
			layer.confirm("是否确认修改",{icon:3},function(index){
				layer.close(index);
				layer.load(1,{shade:0.3});
				
				$.post("emayInfo/updatePhone.do",{
					oldPhoneYzm:oldPhoneYzm,
					newPhoneYzm:newPhoneYzm,
					newPhone:newPhone
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
