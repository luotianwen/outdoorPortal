<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>


<head>

	<base href="/">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    

    <title> 后台 - 团队管理</title>

    <link href="static/css/bootstrap.min.css" !important rel="stylesheet">
    <link href="static/css/font-awesome.min.css" rel="stylesheet">
    <link href="static/css/animate.min.css" rel="stylesheet">
    <link href="static/css/style.min.css" rel="stylesheet">
    <link href="static/css/scrollUp/scrollUp.css" rel="stylesheet">
    <script src="static/js/jquery-2.1.1.min.js" type="text/javascript"></script>

  	<!-- 轮播图需要引用的文件   Start-->  
    <!-- LayerSlider stylesheet -->
	<link rel="stylesheet" href="static/layerslider/css/layerslider.css" >
    <link rel="stylesheet" href="static/css/index/header.css" >
	
	<script src="static/layerslider/js/greensock.js" type="text/javascript"></script>
	<!-- LayerSlider script files -->
	<script src="static/layerslider/js/layerslider.transitions.js" type="text/javascript"></script>
	<script src="static/layerslider/js/layerslider.kreaturamedia.jquery.js" type="text/javascript"></script>
	
	<script type="text/javascript" src="static/js/plugins/layer/layer.min.js"></script>
	<script type="text/javascript" src="static/js/xx/xx.js"></script>
    <!-- 轮播图需要引用的文件 End  -->  

   	<style type="text/css">
   	/* search */
	.search-area{
		width:100%;
		height:118px;
	    position: absolute;
	    left: 0;
	    bottom: 0;
	    z-index: 99;
		background: url(static/img/home/alpha-bj.png);
	}
	.search-main{
		margin: 0 auto;
		width: 1190px;
		margin-top: 40px;
		overflow: hidden;
	}
	
	.active-choose {
		position: absolute;
		padding-right: 0px;
		width: 930px;
		padding-left: 15px;
	}
	
	.solr .solr-item {
		overflow: inherit;
		cursor: pointer;
		position: relative;
		float: left;
		padding-left: 15px;
		padding-right: 15px;
		width: 208px;
	}
	
	.auto-search{
		padding-right: 8px;
	}
   	</style>
</head>

<body>
	<link rel="stylesheet" type="text/css" href="static/css/home/index.css" >
<!-- <nav class="navbar navbar-inverse navbar-fixed-top" style="margin-bottom:0px;border-radius:0px;background-color: #1D924C;border: 1px solid #1D924C;">
  <div class="container-fluid" style="max-width: 1190px;margin: 0 auto;">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>                        
      </button>
      <a class="navbar-brand" href="static/#">Wan Ma</a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav">
        <li class="active"><a href="static/#">Home</a></li>
        <li class="dropdown">
          <a class="dropdown-toggle" data-toggle="dropdown" href="static/#">Page 1 <span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="static/#">Page 1-1</a></li>
            <li><a href="static/#">Page 1-2</a></li>
            <li><a href="static/#">Page 1-3</a></li>
          </ul>
        </li>
        <li><a href="static/#">Page 2</a></li>
        <li><a href="static/#">Page 3</a></li>
        <li><a href="view/location.jsp" target="_blank" >定位demo</a></li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <li><a href="register.html"><span class="glyphicon glyphicon-user"></span> 注册</a></li>
        <li><a href="login.html"><span class="glyphicon glyphicon-log-in"></span> 登录</a></li>
      </ul>
    </div>
  </div>
</nav> -->
 
 <!-- 轮播图 Start  -->    
  <div id="full-slider-wrapper" style="padding-top: -10px;">
		<div id="layerslider" style="width:100%;height:630px;">
			
			<!-- search page -->
			<jsp:include page="/view/index/index_search.jsp"></jsp:include>
			
			<div class="ls-slide" data-ls="slidedelay:4500;transition2d:24,25,27,28,34,35,37,38,110,113;">
				<img src="static/sliderimages/bg1.jpg" class="ls-bg" alt="Slide background"/><img class="ls-l" style="top:544px;left:333px;white-space: nowrap;" data-ls="offsetxin:0;offsetyin:bottom;durationin:4600;easingin:easeOutQuad;fadein:false;rotatein:-10;offsetxout:0;durationout:1500;" src="static/sliderimages/ladybug.png" alt="Ladybug">
				<h5 class="ls-l" style="top:257px;left:50%;text-align: center; background: black; background: rgba(0,0,0,.75); font-weight: normal;width:350px;height:100px;font-size:50px;line-height:100px;color:#eee;border-radius:5px;white-space: nowrap;" data-ls="offsetxin:0;scalexin:0;scaleyin:0;offsetxout:0;offsetyout:top;durationout:750;showuntil:500;fadeout:false;">玩嘛户外</h5>
				<h5 class="ls-l" style="top:363px;left:50%;text-align: center; font-weight: normal;width:400px;height:100px;font-size:50px;line-height:100px;color:#444;background:white;border-radius:5px;white-space: nowrap;" data-ls="offsetxin:0;scalexin:0;scaleyin:0;offsetxout:0;offsetyout:bottom;durationout:750;showuntil:500;fadeout:false;">懂你所爱</h5>
				<h5 class="ls-l" style="top:249px;left:384px;text-align: center; font-weight: normal;width:100px;height:70px;font-size:40px;line-height:70px;color:white;background:#cf431d;border-radius:5px;white-space: nowrap;" data-ls="offsetxin:0;durationin:2000;delayin:2000;rotatein:-90;scalexin:2.5;scaleyin:2.5;offsetxout:0;durationout:1000;rotateout:-90;scalexout:0;scaleyout:0;">冲浪</h5>
				<h5 class="ls-l" style="top:249px;left:490px;text-align: center; font-weight: normal;width:285px;height:70px;font-size:40px;line-height:70px;color:white;background:#cf431d;border-radius:5px;white-space: nowrap;" data-ls="offsetxin:0;offsetyin:top;durationin:1500;delayin:1600;easingin:easeInOutQuart;fadein:false;scalexin:5;scaleyin:5;offsetxout:0;offsetyout:top;durationout:1000;fadeout:false;"><a target="_blank" href="static/index3.html">骑行</a></h5>
				<h5 class="ls-l" style="top:249px;left:781px;text-align: center; font-weight: normal;width:115px;height:70px;font-size:40px;line-height:70px;color:white;background:#cf431d;border-radius:5px;white-space: nowrap;" data-ls="offsetxin:0;durationin:2000;delayin:2200;rotatein:90;scalexin:2.5;scaleyin:2.5;offsetxout:0;durationout:1000;rotateout:90;scalexout:0;scaleyout:0;">徒步</h5>
				<h5 class="ls-l" style="top:325px;left:384px;text-align: center; background: black; background: rgba(0,0,0,.75); font-weight: normal;width:270px;height:70px;font-size:40px;line-height:70px;color:white;border-radius:5px;white-space: nowrap;" data-ls="offsetxin:left;durationin:1500;delayin:1800;easingin:easeInOutQuart;fadein:false;scalexin:5;scaleyin:5;offsetxout:left;durationout:1000;fadeout:false;">登山</h5>
				<h5 class="ls-l" style="top:325px;left:660px;text-align: center; background: black; background: rgba(0,0,0,.75); font-weight: normal;width:236px;height:70px;font-size:40px;line-height:70px;color:white;border-radius:5px;white-space: nowrap;" data-ls="offsetxin:right;durationin:1500;delayin:1500;easingin:easeInOutQuart;fadein:false;scalexin:5;scaleyin:5;offsetxout:right;durationout:1000;fadeout:false;">露营</h5>
				<h5 class="ls-l" style="top:401px;left:384px;text-align: center; font-weight: normal;width:110px;height:70px;font-size:40px;line-height:70px;color:#444;background:white;border-radius:5px;white-space: nowrap;" data-ls="offsetxin:0;durationin:2000;delayin:2100;rotatein:90;scalexin:2.5;scaleyin:2.5;offsetxout:0;durationout:1000;rotateout:90;scalexout:0;scaleyout:0;">攀岩</h5>
				<h5 class="ls-l" style="top:401px;left:500px;text-align: center; font-weight: normal;width:205px;height:70px;font-size:40px;line-height:70px;color:#444;background:white;border-radius:5px;white-space: nowrap;" data-ls="offsetxin:0;offsetyin:bottom;durationin:1500;delayin:1700;easingin:easeInOutQuart;fadein:false;scalexin:5;scaleyin:5;offsetxout:0;offsetyout:bottom;durationout:1000;fadeout:false;">探险</h5>
				<h5 class="ls-l" style="top:401px;left:711px;text-align: center; font-weight: normal;width:185px;height:70px;font-size:40px;line-height:70px;color:#444;background:white;border-radius:5px;white-space: nowrap;" data-ls="offsetxin:0;durationin:2000;delayin:2300;rotatein:-90;scalexin:2.5;scaleyin:2.5;offsetxout:0;durationout:1000;rotateout:-90;scalexout:0;scaleyout:0;">潜水</h5>			
				<h5 class="ls-l" style="top:480px;left:50%;text-align: center; font-weight: normal;width:400px;height:70px;font-size:40px;line-height:70px;color:#444;background: greenyellow;border-radius:5px;white-space: nowrap;" data-ls="offsetxin:0;durationin:2000;delayin:2300;rotatein:-90;scalexin:2.5;scaleyin:2.5;offsetxout:0;durationout:1000;rotateout:-90;scalexout:0;scaleyout:0;">户外我们更专业</h5>
			</div>
			<div class="ls-slide" data-ls="slidedelay:5000;transition2d:76,77,78,79;">
				<img src="static/sliderimages/bg3.jpg" class="ls-bg" alt="Slide background"/>
				<h5 class="ls-l" style="top:200px;left:20px;text-align: center; font-weight: normal;width:50px;height:50px;font-size:50px;line-height:50px;color:#eee;background:#2e69ad;border-radius:100px;white-space: nowrap;" data-ls="offsetxin:0;durationin:750;easingin:easeOutQuint;rotatein:90;scalexin:0.5;scaleyin:0.5;offsetxout:0;durationout:750;rotateout:90;scalexout:0.5;scaleyout:0.5;">+</h5>
				<h5 class="ls-l" style="top:205px;left:85px; font-weight: normal;font-size:30px;color:#f0f6fd;white-space: nowrap;" data-ls="offsetxin:left;easingin:easeOutBack;fadein:false;scalexin:0.1;scaleyin:0.1;offsetxout:left;durationout:750;fadeout:false;scalexout:0.1;scaleyout:0.1;">touch-enabled</h5>
				<h5 class="ls-l" style="top:260px;left:20px; font-weight: normal; text-align: center;width:50px;height:50px;font-size:50px;line-height:50PX;color:#eee;background:#2e69ad;border-radius:100px;white-space: nowrap;" data-ls="offsetxin:0;durationin:750;delayin:500;easingin:easeOutQuint;rotatein:90;scalexin:0.5;scaleyin:0.5;offsetxout:0;durationout:750;rotateout:90;scalexout:0.5;scaleyout:0.5;">+</h5>
				<h5 class="ls-l" style="top:265px;left:85px; font-weight: normal;font-size:30px;color:#f0f6fd;white-space: nowrap;" data-ls="offsetxin:left;delayin:500;easingin:easeOutBack;fadein:false;scalexin:0.1;scaleyin:0.1;offsetxout:left;durationout:750;fadeout:false;scalexout:0.1;scaleyout:0.1;">responsive</h5>
				<h5 class="ls-l" style="top:90%;left:79%; font-weight: normal;padding-top:10px;padding-right:25px;padding-bottom:10px;padding-left:25px;font-size:30px;color:#f0f6fd;background:#2e69ad;border-radius:100px;white-space: nowrap;" data-ls="offsetxin:0;offsetyin:bottom;durationin:750;delayin:1000;easingin:easeOutQuint;fadein:false;scalexin:0.5;scaleyin:0.5;offsetxout:0;offsetyout:bottom;durationout:750;fadeout:false;">with timed layers captions</h5>
				<h5 class="ls-l" style="top:570.2899932861328px;left:1002.4000244140625px; font-weight: bold;padding-top:15px;padding-right:30px;padding-bottom:15px;padding-left:30px;font-size:100px;color:#f4f8fc;white-space: nowrap;" data-ls="offsetxin:0;delayin:1250;easingin:easeOutQuint;scalexin:3;scaleyin:3;offsetxout:0;durationout:1000;scalexout:3;scaleyout:3;">&amp;</h5>
				<h5 class="ls-l" style="top:63%;left:60%; font-weight: normal;padding-top:10px;padding-right:25px;padding-bottom:10px;padding-left:25px;font-size:30px;color:#2e69ad;background:#f0f6fd;border-radius:100px;white-space: nowrap;" data-ls="offsetxin:0;durationin:450;delayin:1750;easingin:easeOutQuart;scalexin:0;scaleyin:0;offsetxout:0;durationout:1000;showuntil:51;easingout:easeInQuart;scalexout:3;scaleyout:3;">Clown Fish</h5>
				<img class="ls-l" style="top:35%;left:60%;white-space: nowrap;" data-ls="offsetxin:0;offsetyin:top;durationin:750;delayin:1500;easingin:easeOutQuart;fadein:false;offsetxout:right;durationout:1000;showuntil:1;easingout:easeInQuart;fadeout:false;" src="static/sliderimages/fish-showcase-3.png" alt="Clown Fish">
				<h5 class="ls-l" style="top:63%;left:60%; font-weight: normal;padding-top:10px;padding-right:25px;padding-bottom:10px;padding-left:25px;font-size:30px;color:#2e69ad;background:#f0f6fd;border-radius:100px;white-space: nowrap;" data-ls="offsetxin:0;durationin:450;delayin:3250;easingin:easeOutQuart;scalexin:0;scaleyin:0;offsetxout:0;durationout:1000;showuntil:51;easingout:easeInQuart;scalexout:3;scaleyout:3;">Blue Sergeonfish</h5>
				<img class="ls-l" style="top:35%;left:60%;white-space: nowrap;" data-ls="offsetxin:0;offsetyin:top;durationin:750;delayin:3000;easingin:easeOutQuart;fadein:false;offsetxout:right;durationout:1000;showuntil:1;easingout:easeInQuart;fadeout:false;" src="static/sliderimages/fish-showcase-2.png" alt="Blue Sergeonfish">
				<h5 class="ls-l" style="top:63%;left:60%; font-weight: normal;padding-top:10px;padding-right:25px;padding-bottom:10px;padding-left:25px;font-size:30px;color:#2e69ad;background:#f0f6fd;border-radius:100px;white-space: nowrap;" data-ls="offsetxin:0;durationin:450;delayin:4750;easingin:easeOutQuart;scalexin:0;scaleyin:0;offsetxout:0;durationout:1000;easingout:easeInQuart;scalexout:3;scaleyout:3;">Yellow Tang</h5>
				<img class="ls-l" style="top:35%;left:60%;white-space: nowrap;" data-ls="offsetxin:0;offsetyin:top;durationin:750;delayin:4500;easingin:easeOutQuart;fadein:false;offsetxout:right;durationout:1000;easingout:easeInQuart;fadeout:false;" src="static/sliderimages/fish-showcase-1.png" alt="Yellow Tang">
			</div>
			<div class="ls-slide" data-ls="slidedelay:3300;transition2d:11;">
				<img src="static/sliderimages/bg41.jpg" class="ls-bg" alt="Slide background"/><img class="ls-l" style="top:-200px;left:-78px;white-space: nowrap;" data-ls="offsetxin:0;durationin:3600;easingin:easeOutQuad;rotatein:-35;scalexin:2;scaleyin:2;offsetxout:0;durationout:1500;rotateout:35;scalexout:2;scaleyout:2;" src="static/sliderimages/bokeh.jpg" alt="Bokeh"><img class="ls-l" style="top:81px;left:434px;white-space: nowrap;" data-ls="offsetxin:right;durationin:4000;easingin:easeOutQuart;fadein:false;rotatein:20;scalexin:1.2;scaleyin:1.2;offsetxout:0;offsetyout:bottom;durationout:1000;fadeout:false;rotateout:-10;" src="static/sliderimages/tucan.png" alt="Tucan"><img class="ls-l" style="top:254px;left:634px;white-space: nowrap;" data-ls="offsetxin:right;durationin:4000;easingin:easeOutQuart;fadein:false;rotatein:20;scalexin:1.2;scaleyin:1.2;offsetxout:0;offsetyout:bottom;durationout:1000;fadeout:false;" src="static/sliderimages/brench.png" alt="Brench">
				<h5 class="ls-l" style="top:50%;left:30%; border-radius: 5px;padding-top:5px;padding-right:15px;padding-bottom:5px;padding-left:15px;font-size:30px;color:#94e60f;background:black;white-space: nowrap;" data-ls="offsetxin:0;durationin:1500;offsetxout:right;durationout:1000;showuntil:701;easingout:easeInQuart;fadeout:false;rotateout:35;scalexout:5;scaleyout:5;">with</h5>
				<h5 class="ls-l" style="top:57%;left:30%;box-shadow: -2px -2px 15px -3px black; border-radius: 5px;padding-top:10px;padding-right:20px;padding-bottom:10px;padding-left:20px;font-size:40px;color:black;background:#94e60f;white-space: nowrap;" data-ls="offsetxin:0;durationin:1500;delayin:250;offsetxout:right;durationout:1000;showuntil:351;easingout:easeInQuart;fadeout:false;rotateout:35;scalexout:5;scaleyout:5;">the famous</h5>
				<h5 class="ls-l" style="top:67%;left:30%;box-shadow: -3px -3px 20px -3px black; border-radius: 5px;padding-top:15px;padding-right:30px;padding-bottom:15px;padding-left:30px;font-size:60px;color:#94e60f;background:black;white-space: nowrap;" data-ls="offsetxin:0;durationin:1500;delayin:500;offsetxout:right;durationout:1000;showuntil:1;easingout:easeInQuart;fadeout:false;rotateout:35;scalexout:5;scaleyout:5;">parallax effect</h5>
			</div>
			<div class="ls-slide" data-ls="slidedelay:3400;transition2d:92,93;">
				<img src="static/sliderimages/455293175_1280.jpg" class="ls-bg" alt="Slide background"/>
				<!-- <div class="ls-l" style="top:0px;left:0px;white-space: nowrap;" data-ls="offsetxin:0;durationin:1500;offsetxout:0;durationout:1500;">
					<iframe src="http://player.video.qiyi.com/9e694bf7eeafe249e3c04c6ebbb7675b/0/2751/v_19rrkrj4tc.swf-albumId=202749601-tvId=413908300-isPurchase=0-cnId=2" width="1280" height="720" frameborder="0" webkitallowfullscreen mozallowfullscreen allowfullscreen>
					</iframe>
				</div> -->
				<h5 class="ls-l" style="top:25px;left:85px; font-weight: normal; text-shadow: 0px 0px 20px #224177;font-size:30px;color:#eee;white-space: nowrap;" data-ls="offsetxin:0;offsetyin:top;delayin:500;easingin:easeOutQuint;fadein:false;rotatein:-90;offsetxout:0;offsetyout:top;durationout:750;showuntil:4000;fadeout:false;rotateout:-90;">optional auto-play</h5>
				<h5 class="ls-l" style="top:85px;left:85px; font-weight: normal; text-shadow: 0px 0px 20px #224177;font-size:30px;color:#eee;white-space: nowrap;" data-ls="offsetxin:0;offsetyin:top;delayin:1000;easingin:easeOutQuint;fadein:false;rotatein:-90;offsetxout:0;offsetyout:top;durationout:750;showuntil:4000;fadeout:false;rotateout:-90;">with auto-pause slideshow</h5>
				<h5 class="ls-l" style="top:620px;left:640px; font-weight: normal;padding-top:10px;padding-right:25px;padding-bottom:10px;padding-left:25px;font-size:30px;color:#224177;background:#eee;border-radius:100px;white-space: nowrap;" data-ls="offsetxin:right;durationin:750;easingin:easeOutQuint;fadein:false;offsetxout:0;offsetyout:bottom;durationout:750;showuntil:4500;fadeout:false;">easy to add full-sized or windowed videos</h5>
				<h5 class="ls-l" style="top:20px;left:20px; font-weight: normal; text-align: center;width:50px;height:50px;font-size:50px;line-height:55px;color:#224177;background:#eee;border-radius:100px;white-space: nowrap;" data-ls="offsetxin:0;durationin:750;delayin:500;easingin:easeOutQuint;rotatein:90;scalexin:0.5;scaleyin:0.5;offsetxout:0;durationout:750;showuntil:4000;rotateout:90;scalexout:0.5;scaleyout:0.5;">+</h5>
				<h5 class="ls-l" style="top:80px;left:20px; font-weight: normal; text-align: center;width:50px;height:50px;font-size:50px;line-height:50px;color:#224177;background:#eee;border-radius:100px;white-space: nowrap;" data-ls="offsetxin:0;durationin:750;delayin:1000;easingin:easeOutQuint;rotatein:90;scalexin:0.5;scaleyin:0.5;offsetxout:0;durationout:750;showuntil:4000;rotateout:90;scalexout:0.5;scaleyout:0.5;">+</h5>
				<h5 class="ls-l" style="top:50%;left:40%; font-weight: normal; opacity: .6; filter: alpha(opacity=60);padding-top:4px;padding-right:15px;padding-bottom:7px;padding-left:15px;font-size:24px;color:#444f70;background:#ffffff;border-radius:100px;white-space: nowrap;" data-ls="offsetxin:left;delayin:2000;easingin:easeOutQuint;fadein:false;offsetxout:left;durationout:750;showuntil:2000;fadeout:false;">click to play</h5>
			</div>
			<div class="ls-slide" data-ls="slidedelay:2000;transition2d:1;">
				<img src="static/sliderimages/bg7a.jpg" class="ls-bg" alt="Slide background"/>
				<h5 class="ls-l" style="top:630px;left:40px; font-weight: normal; text-align: center;width:50px;height:50px;font-size:50px;line-height:50px;color:#3b173d;background:#eee;border-radius:100px;white-space: nowrap;" data-ls="offsetxin:0;durationin:750;easingin:easeOutQuint;rotatein:90;scalexin:0.5;scaleyin:0.5;offsetxout:0;durationout:750;showuntil:4000;rotateout:-90;scalexout:0.5;scaleyout:0.5;">+</h5>
				<h5 class="ls-l" style="top:635px;left:105px; font-weight: normal;font-size:30px;color:#eee;white-space: nowrap;" data-ls="offsetxin:0;delayin:300;easingin:easeOutQuint;scalexin:0.8;scaleyin:0.8;offsetxout:0;durationout:750;showuntil:4000;scalexout:0.8;scaleyout:0.8;">you can now use long backgrounds on multiple slides...</h5>
			</div>
			<div class="ls-slide" data-ls="slidedelay:2000;transition2d:1;">
				<img src="static/sliderimages/bg7b.jpg" class="ls-bg" alt="Slide background"/>
				<h5 class="ls-l" style="top:635px;left:40px; font-weight: normal;font-size:30px;color:#eee;white-space: nowrap;" data-ls="offsetxin:0;easingin:easeOutQuint;scalexin:0.8;scaleyin:0.8;offsetxout:0;durationout:750;showuntil:4000;scalexout:0.8;scaleyout:0.8;">...to create a spectacular panorama slideshow!</h5>
			</div>
			<div class="ls-slide" data-ls="slidedelay:4500;transition2d:105,106;">
				<img src="static/sliderimages/bg5.jpg" class="ls-bg" alt="Slide background"/>
				<h5 class="ls-l" style="top:100px;left:700px; font-weight: normal;font-size:60px;color:#ff7700;white-space: nowrap;" data-ls="offsetxin:0;scalexin:0.5;scaleyin:0.5;offsetxout:0;durationout:1000;scalexout:0.5;scaleyout:0.5;">...and much more!</h5>
				<h5 class="ls-l" style="top:335px;left:761px; font-weight: normal;font-size:30px;color:#85c201;white-space: nowrap;" data-ls="offsetxin:0;delayin:800;easingin:easeOutQuint;scalexin:0.8;scaleyin:0.8;offsetxout:0;durationout:750;scalexout:0.8;scaleyout:0.8;">use with any HTML content</h5>
				<h5 class="ls-l" style="top:395px;left:761px; font-weight: normal;font-size:30px;color:#85c201;white-space: nowrap;" data-ls="offsetxin:0;delayin:1300;easingin:easeOutQuint;scalexin:0.8;scaleyin:0.8;offsetxout:0;durationout:750;scalexout:0.8;scaleyout:0.8;">built-in powerful API</h5>
				<h5 class="ls-l" style="top:455px;left:761px; font-weight: normal;font-size:30px;color:#85c201;white-space: nowrap;" data-ls="offsetxin:0;delayin:1800;easingin:easeOutQuint;scalexin:0.8;scaleyin:0.8;offsetxout:0;durationout:750;scalexout:0.8;scaleyout:0.8;">supports all major browsers</h5>
				<h5 class="ls-l" style="top:515px;left:761px; font-weight: normal;font-size:30px;color:#85c201;white-space: nowrap;" data-ls="offsetxin:0;delayin:2300;easingin:easeOutQuint;scalexin:0.8;scaleyin:0.8;offsetxout:0;durationout:750;scalexout:0.8;scaleyout:0.8;">SEO friendly</h5>
				<h5 class="ls-l" style="top:575px;left:761px; font-weight: normal;font-size:30px;color:#85c201;white-space: nowrap;" data-ls="offsetxin:0;delayin:2800;easingin:easeOutQuint;scalexin:0.8;scaleyin:0.8;offsetxout:0;durationout:750;scalexout:0.8;scaleyout:0.8;">sample sliders included</h5>
				<h5 class="ls-l" style="top:635px;left:761px; font-weight: normal;font-size:30px;color:#85c201;white-space: nowrap;" data-ls="offsetxin:0;delayin:3300;easingin:easeOutQuint;scalexin:0.8;scaleyin:0.8;offsetxout:0;durationout:750;scalexout:0.8;scaleyout:0.8;">free updates &amp; support</h5>
				<h5 class="ls-l" style="top:330px;left:700px; font-weight: normal; text-align: center;width:50px;height:50px;font-size:50px;line-height:50px;color:white;background:#85c201;border-radius:100px;white-space: nowrap;" data-ls="offsetxin:0;durationin:750;delayin:500;easingin:easeOutQuint;rotatein:90;scalexin:0.5;scaleyin:0.5;offsetxout:0;durationout:750;rotateout:90;scalexout:0.5;scaleyout:0.5;">+</h5>
				<h5 class="ls-l" style="top:390px;left:700px; font-weight: normal; text-align: center;width:50px;height:50px;font-size:50px;line-height:50px;color:white;background:#85c201;border-radius:100px;white-space: nowrap;" data-ls="offsetxin:0;durationin:750;delayin:1000;easingin:easeOutQuint;rotatein:90;scalexin:0.5;scaleyin:0.5;offsetxout:0;durationout:750;rotateout:90;scalexout:0.5;scaleyout:0.5;">+</h5>
				<h5 class="ls-l" style="top:450px;left:700px; font-weight: normal; text-align: center;width:50px;height:50px;font-size:50px;line-height:50px;color:white;background:#85c201;border-radius:100px;white-space: nowrap;" data-ls="offsetxin:0;durationin:750;delayin:1500;easingin:easeOutQuint;rotatein:90;scalexin:0.5;scaleyin:0.5;offsetxout:0;durationout:750;rotateout:90;scalexout:0.5;scaleyout:0.5;">+</h5>
				<h5 class="ls-l" style="top:510px;left:700px; font-weight: normal; text-align: center;width:50px;height:50px;font-size:50px;line-height:50px;color:white;background:#85c201;border-radius:100px;white-space: nowrap;" data-ls="offsetxin:0;durationin:750;delayin:2000;easingin:easeOutQuint;rotatein:90;scalexin:0.5;scaleyin:0.5;offsetxout:0;durationout:750;rotateout:90;scalexout:0.5;scaleyout:0.5;">+</h5>
				<h5 class="ls-l" style="top:570px;left:700px; font-weight: normal; text-align: center;width:50px;height:50px;font-size:50px;line-height:50px;color:white;background:#85c201;border-radius:100px;white-space: nowrap;" data-ls="offsetxin:0;durationin:750;delayin:2500;easingin:easeOutQuint;rotatein:90;scalexin:0.5;scaleyin:0.5;offsetxout:0;durationout:750;rotateout:90;scalexout:0.5;scaleyout:0.5;">+</h5>
				<h5 class="ls-l" style="top:630px;left:700px; font-weight: normal; text-align: center;width:50px;height:50px;font-size:50px;line-height:50px;color:white;background:#85c201;border-radius:100px;white-space: nowrap;" data-ls="offsetxin:0;durationin:750;delayin:3000;easingin:easeOutQuint;rotatein:90;scalexin:0.5;scaleyin:0.5;offsetxout:0;durationout:750;rotateout:90;scalexout:0.5;scaleyout:0.5;">+</h5>
				<img class="ls-l" style="top:607px;left:164px;white-space: nowrap;" data-ls="offsetxin:left;durationin:1500;delayin:1400;fadein:false;offsetxout:left;durationout:1000;fadeout:false;" src="static/sliderimages/layerslider-on-cellphone.png" alt="LayerSlider on cellphone"><img class="ls-l" style="top:583px;left:222px;white-space: nowrap;" data-ls="offsetxin:left;durationin:1500;delayin:1200;fadein:false;offsetxout:left;durationout:1000;fadeout:false;" src="static/sliderimages/layerslider-on-tablet.png" alt="LayerSlider on tablet"><img class="ls-l" style="top:457px;left:316px;white-space: nowrap;" data-ls="offsetxin:left;durationin:1500;delayin:1000;fadein:false;offsetxout:left;durationout:1000;fadeout:false;" src="static/sliderimages/layerslider-on-computer.png" alt="LayerSlider on computer">
			</div>
		</div>
	</div>

	


    <!-- 全局js -->
    <script src="static/js/bootstrap.min.js"></script>   
    <script src="static/js/scrollUp/jquery.scrollUp.min.js"></script>
    
    <!-- 地图辅助 -->
    <script type="text/javascript" src="http://webapi.amap.com/maps?v=1.3&key=36f68f1175206b118ada135da3492691&plugin=AMap.Autocomplete,AMap.CitySearch"></script>
    <script type="text/javascript" src="static/js/index/index_search.js"></script>
    
    <!-- 通用js -->
    <script src="static/js/default.js"></script>
 	<script type = "text/javascript" >
 
   /* <!-- 轮播图需要引用的文件   Start-->      */
 
		jQuery("#layerslider").layerSlider({
			 
			responsiveUnder: 1280,
			layersContainer: 1280,
		 
			skinsPath: 'static/layerslider/skins/'
		});
 
 /* <!-- 轮播图需要引用的文件  End--> */   
 
		</script>     
    
  
    
</body>

 
</html>