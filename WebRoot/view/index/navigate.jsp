<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="/">
    
    <title>导航</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="static/css/index/navigate.css">
  </head>
  
  <body>
  	<div style="display: none;font-size: 15px;padding: 20px;" id="dispatch">
  		<ul>
  			<li>-----------------Y-----------------</li>
  			<li><a href="activity/actives.html">活动信息（领队）</a></li>
  			<li><a href="activeSignup/MySignUp.html">报名管理（领队）</a></li>
  			<li><a href="activeSignup/lRefunds.html">退款订单（领队）</a></li>
  			<li><a href="users/changeMobile.html">修改手机号码</a></li>
  			<li><a href="users/password.html">修改登录密码</a></li>
  			<li><a href="activity/myActive.html">个人中心→我的活动</a></li>
  			<li><a href="activeConsultation/consultation.html">个人中心→我的问答</a></li>
  			<br>
  			<li>-----------------P-----------------</li>
  			<li><a href="activeSignup/selectActive.html">我的报名(用户)</a></li>
  			<li><a href="activeSignup/selectActive.html?param=refund">我的退款(用户)</a></li>
  			<li><a href="SignupUser/selectUser.html">常用联系人管理(用户)</a></li>
  			<li><a href="users/selectUsers.html">个人信息</a></li>
  			<li><a href="javascript:propose()">意见建议(针对玩嘛平台)</a></li>
  			<li>-----------------S-----------------</li>
  			<li><a href="activeSignup/MySignUp.html">我的订单(用户)</a></li>
  			<li><a href="users/userInfo.html">用户信息</a></li>
  			<li>-----------------Win Zhong-----------------</li>
  			<li><a href="balance/details.html">我的钱包</a></li>
  		</ul>
  	</div>
  
    <div class="zdy-header">
		<div class="hd-navigate">
			<ul>
				<li><a href="javascript:dispatch()">分发器</a></li>
				<li><a>个人中心</a></li>
				<li><a>微信</a></li>
				<li><a>微博</a></li>
				<li><a>手机版</a></li>
				<li><a>联系客服</a></li>
				<li><a>帮助</a></li>
				<li><a>english</a></li>
				<li><a>网站导航</a></li>
			</ul>
		</div>
		<div class="hd-logo-handle">
			<div class="wm">
				<strong>玩嘛</strong>
			</div>
			<div class="introduction">
				<p>玩的就是个性</p>
				<p>户外运动导航搜索平台</p>
			</div>
			<div class="welcome">
				<span>欢迎登录玩嘛，带你一起玩世界!</span>
			</div>
			<div class="release">
				<a href="activity/activityInfo.html">我要发布</a>
			</div>
			<div class="release">
				<a onclick="sqrz()">申请入驻</a>
			</div>
		</div>
	</div>
	
	<div class="channel-bg">
		<div class="channel">
			<div class="classification">
				<ul>
					<li class="active"><a href="index.html">首页</a></li>
					<li><a href="http://www.seebong.com/" target="_blank">装备</a></li>
					<li><a href="http://bbs.seebong.com/" target="_blank">社区</a></li>
					<li><a>线路</a></li>
					<li><a>活动</a></li>
					<li><a>资讯</a></li>
					<li><a>品牌</a></li>
					<li><a>赛事</a></li>
					<li><a>知识</a></li>
				</ul>
			</div>
			
			<div class="register-login">
				<ul>
					<li><a href="login.html">登录</a></li>
					<li><a href="register.html">注册</a></li>
				</ul>
			</div>
		</div>
	</div>
  </body>
  <script src="static/js/jquery-2.1.1.min.js" type="text/javascript"></script>
  <script src="static/js/plugins/layer/layer.js"></script>
  <script type="text/javascript">
  
  function sqrz(){
	layer.confirm('选择申请领队类型',{
		icon:6,
		btn:['个人申请','关闭','企业申请'],
		btn3:function(index,layero){
			location.href="user_check/ldzc.html?lingduishenqing=qiyelingdui";
		}
	},function(index, layero){
		location.href="user_check/ldzc.html?lingduishenqing=gerenlingdui";
	},function(index){
		layer.close(index);
	});
  }
  var index;
  function dispatch(){
	  index=layer.open({
	    type: 1,
	    title: '功能分发器',
	    closeBtn: 1,
	    area: ['500px','800px'],
	    shadeClose: false,
	    content: $('#dispatch')
	  });
  }
  
  function propose(){//意见建议
  	layer.close(index);
    layer.open({
        type: 2,
        title:"建议",
        area: ["40%", "50%"],
        shadeClose: false, //点击遮罩不关闭
        content: "view/usercenter/user/propose/propose.jsp"
    }); 
    
  }
  
  </script>
</html>
