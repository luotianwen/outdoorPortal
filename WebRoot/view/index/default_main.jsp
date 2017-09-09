<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="/">
    
    <title>default main</title>
    
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
    <!-- choose begin -->  	
     	<!-- 选择时间、地点、活动 css -->
		<link rel="stylesheet" type="text/css" href="static/css/home/search-modular.css"/>
		<!-- 活动和项目	begin -->
		<link rel="stylesheet" type="text/css" href="static/css/home/show-active.css"/>
		<div class="container-fluid active-div ">
		<div class="row active-main">
			<div class="col-md-12 active-choose" id="find_active">
	      		<div class="row row-set-left choose-div">
					<div class="col-md-12 solr">
						<div class="row header">
							<h1 class="active-head-title">活动和项目</h1>
							<p>下面是活动所提供的各种各样的组织和专门的志愿者。 所有的项目都是免费的,除非下面提到。 项目由 IRC-Certified志愿者
								需要预先登记,当天下午4点前关门 的事件。 不允许无电梯的注册这些事件。 其他组织的政策,请使用提供的注册信息。 移动设备的政策 点击这里 。</p>
							<p>
								<strong>注意: </strong>未成年人必须由家长或监护人陪同这些事件。 项目需要在线注册,每个成人和孩子必须注册。
							</p>
							<p>
								<strong>需要帮忙吗? </strong>最快的答案,寻找organization-speccific联系信息在每个事件清单完整列表(点击更多信息)。
								如果没有列出的联系人信息,电子邮件 info@irconservancy.org 寻求帮助。
							</p>
						</div>
						<div class="row">
							<div class="col-md-3 solr-item" >
								<input type="text" placeholder="当前位置:北京" />
								<i class="fa fa-map-marker"></i>
							</div>
							<div class="col-md-3 solr-item">
								<input type="text" placeholder="时间" readonly="readonly" id="show_item" show-type="show_time" choose="0" />
								<i class="fa fa-calendar"></i>
							</div>
							<div class="col-md-3 solr-item">
								<input type="text" placeholder="去哪儿"  id="show_item" show-type="show_areas" choose="0" />
								<i class="fa fa-location-arrow"></i>
							</div>
							<div class="col-md-3 solr-item" >
								<input type="text" placeholder="玩什么"  id="show_item" show-type="show_lequ" choose="0" />
								<i class="fa fa-bicycle"></i>
							</div>
							<!-- <div class="col-md-2 solr-item-btn">
								<button class="active-search-btn"><i class="fa fa-search"></i>&nbsp;&nbsp;&nbsp;搜索</button>
							</div> -->
						</div>
					</div>
				</div>
			
			<div class="row show-choose" id="show_time" >
				<div class="col-md-12 show-time">
					<div class="row border-content">
						<div class="col-md-7">
							<div class="row choose-when-title">
								<div class="col-md-12 center">选择时间</div>
							</div>
							<div class="row">
								<div class="col-md-6 choose-date">
									<a class="curr">未来所有日期</a><br />
									<a>未来七天</a><br />
									<a>过去的时间</a>
								</div>
								<div class="col-md-6 choose-date-in">
									<span>从：</span><br />
									<input type="date" /><br />
									<span>到：</span><br />
									<input type="date" />
								</div>
							</div>
						</div>
						<div class="col-md-5 choose-day">
							<div class="row choose-when-title">
								<div class="col-md-12 center">一周</div>
							</div>
							<div class="row">
								<div class="col-md-12">
									<a>周一</a>
									<a>周二</a>
									<a>周三</a>
									<a>周四</a>
									<a>周五</a>
									<a>周六</a>
									<a>周日</a>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-12 reset-down">
					<div class="row border-content">
						<div class="col-md-3 reset-choose-time">
							<a>重置所有过滤器</a>
						</div>
						<div class="col-md-9 down-choose-time">
							<a class="done">确定</a>
							<a class="other">选择其他条件</a>
						</div>
					</div>
				</div>
			</div>
			
			<div class="row show-choose" id="show_areas" >
				<div class="col-md-12 show-areas">
					<div class="row border-content">
						<div class="col-md-12">
							<div class="row">
								<div class="col-md-7" >
									<div class="row choose-where-title">
										<div class="col-md-12 center">距离</div>
									</div>
									<div class="row choose-where-tag">
										<div class="col-md-6">
											<a class="curr">任何距离</a><br />
											<a>在25公里</a><br />
											<a>在50 公里</a>
										</div>
										<div class="col-md-6">
											<a>在75公里</a><br />
											<a>在100公里</a><br />
											<a>在200公里</a>
										</div>
									</div>
										<div class="clr"></div>
								</div>
								<div class="col-md-5" >
									<div class="row choose-where-title">
										<div class="col-md-12 center">家庭住址</div>
									</div>
									<div class="row">
										<div class="col-md-12 center where-home-tile">
											寻找附近的活动
										</div>
										<div class="col-md-12 center where-add-home">
											<a><i class="fa fa-home"></i>添加家庭住址</a>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-12 reset-down">
					<div class="row border-content">
						<div class="col-md-3 reset-choose-time">
							<a>重置所有过滤器</a>
						</div>
						<div class="col-md-9 down-choose-time">
							<a class="done">确定</a>
							<a class="other">选择其他条件</a>
						</div>
					</div>
				</div>
			</div>
			
			<div class="row show-choose" id="show_lequ">
				<div class="col-md-12 show-active">
					<div class="row border-content">
						<div class="col-md-12 center choose-active">
							<span>选择活动</span>
						</div>
						<div class="col-md-12 active-tag">
							<a href="active/showActive.html?keywords=1">滑雪</a>
							<a href="active/showActive.html?keywords=1">运行</a>
							<a href="active/showActive.html?keywords=1">走</a>
							<a href="active/showActive.html?keywords=1">骑自行车</a>
							<a href="active/showActive.html?keywords=1">游泳</a>
							<a href="active/showActive.html?keywords=1">三项全能运动</a>
							<a href="active/showActive.html?keywords=1">跑步</a>
							<a href="active/showActive.html?keywords=1">篮球</a>
							<a href="active/showActive.html?keywords=1">棒球</a>
							<a href="active/showActive.html?keywords=1">垒球</a>
							<a href="active/showActive.html?keywords=1">足球</a>
							<a href="active/showActive.html?keywords=1">排球</a>
							<a href="active/showActive.html?keywords=1">网球</a>
							<a href="active/showActive.html?keywords=1">高尔夫球</a>
							<a href="active/showActive.html?keywords=1">长曲棍球</a>
							<a href="active/showActive.html?keywords=1">武术</a>
							<a href="active/showActive.html?keywords=1">水上运动</a>
							<a href="active/showActive.html?keywords=1">冬季运动</a>
							<a href="active/showActive.html?keywords=1">拉拉队</a>
							<a href="active/showActive.html?keywords=1">体操</a>
							<a href="active/showActive.html?keywords=1">跳舞</a>
							<a href="active/showActive.html?keywords=1">音乐</a>
							<a href="active/showActive.html?keywords=1">艺术和工艺</a>
							<a href="active/showActive.html?keywords=1">健身</a>
							<a href="active/showActive.html?keywords=1">瑜伽</a>
							<a href="active/showActive.html?keywords=1">减肥</a>
							<a href="active/showActive.html?keywords=1">营养</a>
						</div>
					</div>
				</div>
				<div class="col-md-12 reset-down">
					<div class="row border-content">
						<div class="col-md-3 reset-choose-time">
							<a>重置所有过滤器</a>
						</div>
						<div class="col-md-9 down-choose-time">
							<a class="done">确定</a>
							<a class="other">选择其他条件</a>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- choose end -->
			<div class="col-md-9" class="div-main-le">
			<!-- left -->
				<div class="row">
					<!-- <div class="col-md-12 header">
						<h1 class="active-head-title">活动和项目</h1>
						<p>下面是活动所提供的各种各样的组织和专门的志愿者。 所有的项目都是免费的,除非下面提到。 项目由 IRC-Certified志愿者
							需要预先登记,当天下午4点前关门 的事件。 不允许无电梯的注册这些事件。 其他组织的政策,请使用提供的注册信息。 移动设备的政策 点击这里 。</p>
						<p>
							<strong>注意: </strong>未成年人必须由家长或监护人陪同这些事件。 项目需要在线注册,每个成人和孩子必须注册。
						</p>
						<p>
							<strong>需要帮忙吗? </strong>最快的答案,寻找organization-speccific联系信息在每个事件清单完整列表(点击更多信息)。
							如果没有列出的联系人信息,电子邮件 info@irconservancy.org 寻求帮助。
						</p>
					</div> -->
					<!-- <div class="col-md-12 active-search">
						<div class="col-md-2 search-item">
							<input type="text" placeholder="关键词" />
						</div>
						<div class="col-md-5 search-item">
							<div class="row">
								<div class="col-md-2" style="padding-right: 0px;">
									<span style="vertical-align: middle;">活动时间:</span>
								</div>
								<div class="col-md-4" style="padding-right: 0px;">
									<input type="date" style="width: 90%;" />
								</div>
								<div class="col-md-1">
									<span style="vertical-align: middle;">到</span>
								</div>
								<div class="col-md-4" style="padding-right: 0px;">
									<input type="date" style="width: 90%;" />
								</div>
							</div>
						</div>
						<div class="col-md-2 search-item">
							<select class="select-active-type">
								<option>活动类型</option>
								<option>徒步旅游和健身</option>
								<option>山地自行车</option>
							</select>
						</div>
						<div class="col-md-2 search-item">
							<select class="select-active-areas">
								<option>活动地点</option>
								<option>通州运河公园</option>
								<option>香山5A级风景区</option>
							</select>
						</div>
						<div class="col-md-1 search-item">
							<button class="active-search-btn">搜索</button>
						</div>
					</div> -->
					<!-- 活动列表 -->
					<div class="col-md-12 active-list">
						<div class="row active-item-time">
							<h2>2015年11月3日，星期二</h2>
						</div>

						<!-- item begin -->
						<div class="row active-item" style="padding-left:15px;padding-right:15px;">
							<div class="col-md-12 active-item-head">
								<h2>周二早上远足通州运河大道</h2>
							</div>
							<div class="col-md-12 active-item-main">
								<div class="row" style="margin-top:20px;">
									<div class="col-md-3 main-le">
										<img alt=""
											src="static/img/test-active/After-Work-Fitness-HHR-20120822-170x170.jpg"
											width="170" height="170" style="border: 3px solid #3D3D3B">
									</div>
									<div class="col-md-6 active-abstract  main-ce">
										<p>准备好锻炼了吗? 忘记一天的健身房和头部外面! 参加我们的一个令人兴奋的早上健身走在了希克斯运材道路。 徒步旅行允许你选择一个
											3、6或8英里的路线 ,都是在3.0到3.5英里每小时的速度。</p>
										<p>回顾1到5 困难评级 将帮助你评估你的愿意参与这个level-3/4活动在陆地上。</p>
										<p>路上铺,地形是光滑的,这是一个伟大的步行的人想要一个“荒野体验”不脏! 我们将有两个相当陡峭的山坡上升和下降。
											在这个过程中,你会有全面的看法的奥兰治县和圣安娜山脉。 各种动物,如鹰、鹿、和蜥蜴也经常看到在这一领域。</p>
										<p>步行者应该自信的能力提高3、6或8英里速度稳定。 16岁或以上。 低的解释。</p>
									</div>
									<div class="col-md-3  main-ri">
										<dl class="active-item-dl">
											<dt>
												<strong>日期：</strong>
											</dt>
											<dd>星期三，11/04/2015</dd>
											<dt>
												<strong>时间：</strong>
											</dt>
											<dd>下午8点</dd>
											<dt>
												<strong>地点：</strong>
											</dt>
											<dd>通州运河公园</dd>
											<dt>
												<strong>难度等级：</strong>
											</dt>
											<dd>3</dd>
											<dt>
												<strong>距离：</strong>
											</dt>
											<dd>8公里</dd>
											<dt>
												<strong>最低年龄：</strong>
											</dt>
											<dd>16</dd>
										</dl>
										<a class="find-active-detail"> 更多信息>> </a>
									</div>
								</div>
							</div>
						</div>
						<!-- item end -->

						<!-- item begin -->
						<div class="row active-item" style="padding-left:15px;padding-right:15px;">
							<div class="col-md-12 active-item-head">
								<h2>周二早上远足通州运河大道</h2>
							</div>
							<div class="col-md-12 active-item-main">
								<div class="row" style="margin-top:20px;">
									<div class="col-md-3 main-le">
										<img alt=""
											src="static/img/test-active/After-Work-Fitness-HHR-20120822-170x170.jpg"
											width="170" height="170" style="border: 3px solid #3D3D3B">
									</div>
									<div class="col-md-6 active-abstract  main-ce">
										<p>准备好锻炼了吗? 忘记一天的健身房和头部外面! 参加我们的一个令人兴奋的早上健身走在了希克斯运材道路。 徒步旅行允许你选择一个
											3、6或8英里的路线 ,都是在3.0到3.5英里每小时的速度。</p>
										<p>回顾1到5 困难评级 将帮助你评估你的愿意参与这个level-3/4活动在陆地上。</p>
										<p>路上铺,地形是光滑的,这是一个伟大的步行的人想要一个“荒野体验”不脏! 我们将有两个相当陡峭的山坡上升和下降。
											在这个过程中,你会有全面的看法的奥兰治县和圣安娜山脉。 各种动物,如鹰、鹿、和蜥蜴也经常看到在这一领域。</p>
										<p>步行者应该自信的能力提高3、6或8英里速度稳定。 16岁或以上。 低的解释。</p>
									</div>
									<div class="col-md-3  main-ri">
										<dl class="active-item-dl">
											<dt>
												<strong>日期：</strong>
											</dt>
											<dd>星期三，11/04/2015</dd>
											<dt>
												<strong>时间：</strong>
											</dt>
											<dd>下午8点</dd>
											<dt>
												<strong>地点：</strong>
											</dt>
											<dd>通州运河公园</dd>
											<dt>
												<strong>难度等级：</strong>
											</dt>
											<dd>3</dd>
											<dt>
												<strong>距离：</strong>
											</dt>
											<dd>8公里</dd>
											<dt>
												<strong>最低年龄：</strong>
											</dt>
											<dd>16</dd>
										</dl>
										<a class="find-active-detail"> 更多信息>> </a>
									</div>
								</div>
							</div>
						</div>
						<!-- item end -->
					</div>
				</div>
			</div>
			
			<!-- right -->
			<div class="col-md-3 div-main-ri" >
				<div class="row">
					<div class="col-md-12">
						<h1 class="active-head-title">最受欢迎的文章</h1>
					</div>
				</div>
				<!-- article item begin -->
				<div class="row article">
					<div class="col-md-5 article-le" >
						<img alt="" src="static/img/test-active/7-Exercises-for-a-Full-Body-Workout.jpg" width="117" height="87" >
					</div>
					<div class="col-md-7 article-ri">
						<a class="img-alt">最佳全身部位锻炼</a>
					</div>
				</div>
				<!-- article item end -->
				
				<!-- article item begin -->
				<div class="row article">
					<div class="col-md-5 article-le" >
						<img alt="" src="static/img/test-active/nut-berry-oatmeal-580.jpg" width="117" height="87" >
					</div>
					<div class="col-md-7 article-ri">
						<a class="img-alt">活跃的食谱：干净的饮食习惯让你充满热能量</a>
					</div>
				</div>
				<!-- article item end -->
				
				<!-- article item begin -->
				<div class="row article">
					<div class="col-md-5 article-le" >
						<img alt="" src="static/img/test-active/workout-stretch.jpg" width="117" height="87" >
					</div>
					<div class="col-md-7 article-ri">
						<a class="img-alt">你应该在锻炼之前还是在锻炼后？</a>
					</div>
				</div>
				<!-- article item end -->
				
				<!-- article item begin -->
				<div class="row article">
					<div class="col-md-5 article-le" >
						<img alt="" src="static/img/test-active/Half-Marathon-Training.jpg" width="117" height="87" >
					</div>
					<div class="col-md-7 article-ri">
						<a class="img-alt">你的12周半马拉松训练计划</a>
					</div>
				</div>
				<!-- article item end -->
				
				<!-- article item begin -->
				<div class="row article">
					<div class="col-md-5 article-le" >
						<img alt="" src="static/img/test-active/Butt-Kicking-Tabata-Exercises-460.jpg" width="117" height="87" >
					</div>
					<div class="col-md-7 article-ri">
						<a class="img-alt">11 Butt-Kicking Tabata	练习</a>
					</div>
				</div>
				<!-- article item end -->
			</div>
		</div>
	</div>
  		<!-- 活动和项目	end -->
  </body>
  <script type="text/javascript">
  
  // 选择活动类型
  $(function(){
		var _lis = $('input[id=show_item]');
		_lis.each(function(){
			$(this).focus(function(){
				var _obj = $(this);
				if($(this).attr('choose') == 0){
					_lis.each(function(_index){
						$(this).attr('choose','0');
						$('#'+$(this).attr('show-type')).hide();
						if(_index == _lis.length - 1){
							_obj.attr('choose','1');
							$('#'+_obj.attr('show-type')).fadeIn();
						}
					});
				}else{
					$(this).attr('choose','0');
					$('#'+$(this).attr('show-type')).fadeOut();
				}
			});
		});
		$(document).click(function(e){
			var obj = e.target;
			if($(obj).prop('id') != 'show_item' && typeof($(obj).closest('div.show-choose').prop('id')) == 'undefined'){
				_lis.each(function(){
					$(this).attr('choose','0');
					$('#'+$(this).attr('show-type')).fadeOut();
				});
			};
		});
	});
  
  
  </script>
</html>
