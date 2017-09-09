<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="/">
    
    <title>default_main</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <link href="static/css/index/default_main2.css" rel="stylesheet" >
	<div class="active-channel-main" id="active-channel-main_id">
		<div class="active-channel">
			<ul>
				<li class="channel-item">
				<i class="fa fa-users"></i>
				<p>
					<span>结伴行</span>
					结伴、交友，跟着您的朋友畅想户外
				</p>
				</li>
				<li class="channel-item">
				<i class="fa fa-child"></i>
				<p>
					<span>孩子和家庭</span>
					带孩子们划独木舟,冲浪...选择是无止境的
				</p>
				</li>
				<li class="channel-item">
				<i class="fa fa-info-circle"></i>
				<p>
					<span>最新资讯</span>
					最新的资讯,带您进入全新的户外世界
				</p>
				</li>
				<li class="channel-item">
				<i class="fa fa-plane"></i>
				<p>
					<span>私人度假游</span>
					个性方案达人设计,一价全包超省心
				</p>
				</li>
			</ul>
		</div>
	</div>
	<div id="active-channel-main_float_id"></div>
	<script type="text/javascript">
		layer.config({
		    extend: 'extend/layer.ext.js'
		}); 
	
		$(function(){
			xx.bindEvent(function(){
				xx.isSeeHeader('active-channel-main_float_id', 'active-channel-main_id','no-see-active-channel-main');
			})
		})
    	
    </script>
	
	<script type="text/javascript" src="static/js/plugins/layer/layer.min.js"></script>
	<script type="text/javascript" src="svgmap/js/lib/raphael-min.js"></script>
	<script type="text/javascript" src="svgmap/js/res/chinaMapConfig.js"></script>
	<script type="text/javascript" src="svgmap/js/res/worldMapConfig.js"></script>
	<script type="text/javascript" src="svgmap/js/map.js"></script>
	<style type="text/css">
	/* 提示自定义 */
	.stateTip, #StateTip{display:none; position:absolute; padding:8px; background:#fff; border:2px solid #2385B1; -moz-border-radius: 4px; -webkit-border-radius: 4px; border-radius: 4px; font-size:12px; font-family:Tahoma; color:#333;}
	
	.mapInfo i{ display:inline-block; width:15px; height:15px; margin-top:5px; line-height:15px; font-style:normal; background:#aeaeae; color:#fff; font-size:11px; font-family: Tahoma; -webkit-border-radius:15px; border-radius:15px; text-align:center}
	.mapInfo i.active{background:#E27F21;}
	.mapInfo span{ padding:0 5px 0 3px;}
	.mapInfo b{ font-weight:normal; color:#2770B5}
	</style>
	<div class="lbs-main">
		<div class="lbs">
			<div class="lbs-header-left">
			</div>
			<div class="lbs-header-right">
				<p>制定你梦想的户外假期</p>
			</div>
			<div class="lbs-description">
				<div class="title">
					<span>用我们的地图搜索</span>
				</div>
				<p>①&nbsp;私人制定你的户外假期</p>
				<p>②&nbsp;让我们为你推荐当地活动</p>
				<img alt="" src="static/img/index/index_lbs_bs.jpg" width="350" height="350">
			</div>
			<div class="lbs-show">
				<div id="chinaMap"></div>
				<script type="text/javascript">
					$('#chinaMap').SVGMap({
					    mapName: 'china',
					    mapWidth: 900,
					    mapHeight: 600,
					    stateInitColor: '9AACB5',
						stateHoverColor: '1D924C',
						stateSelectedColor: '1D924C',
						clickCallback: function(stateData, obj){
				           layer.msg('点击了:'+obj.name,{icon:6});
				       	},
				       	stateTipHtml: function(stateData, obj){  
				       	 return  obj.name;
				       	}
					});
				</script>
			</div>
		</div>
	</div>
	<link rel="stylesheet" type="text/css" href="svgmap/css/SyntaxHighlighter.css">
	<script type="text/javascript" src="svgmap/js/lib/SyntaxHighlighter.js"></script>
	
  </body>
</html>
