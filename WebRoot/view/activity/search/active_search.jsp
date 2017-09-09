<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="/">
    
    <title>search</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="活动">
	<meta http-equiv="description" content="This is my page">

	<link rel="stylesheet" href="static/css/index/active/active_search.css" >
	
  </head>
  
  <body>
    <div class="search-area ">
		<div class="search-main">
			<div class="solr">
				<div class="solr-item" >
					<input type="text" id="current_address" class="in-address" value="" />
					<i class="fa fa-map-marker" onclick="showCityInfo()"></i>
					<div id="auto_search" class="auto-search">
					</div>
				</div>
				<div class="solr-item">
					<input type="text" placeholder="时间" readonly="readonly" id="current_time" get-search="show_item" show-type="show_time" choose="0" />
					<i class="fa fa-calendar" get-search="show_item" show-type="show_time" ></i>
				</div>
				<div class="solr-item">
					<input type="text" placeholder="去哪儿" id="current_where" get-search="show_item" show-type="show_areas" choose="0" />
					<i class="fa fa-location-arrow" get-search="show_item" show-type="show_areas"></i>
				</div>
				<div class="solr-item" >
					<input type="text" placeholder="玩什么"  id="current_what" get-search="show_item" show-type="show_lequ" choose="0" />
					<i class="fa fa-bicycle" get-search="show_item" show-type="show_lequ" ></i>
				</div>
				<div class="solr-item-btn">
					<button class="active-search-btn" onclick="search_active()"><i class="fa fa-search"></i>&nbsp;搜索附近的活动</button>
				</div>
			</div>
		
		
			<div class="active-choose" id="find_active">
				<div class="row show-choose" id="show_time" >
					<div class="col-md-12 show-time">
						<div class="row border-content">
							<div class="col-md-7">
								<div class="row choose-when-title">
								<div class="col-md-12 center">选择时间</div>
								</div>
								<div class="row">
									<div class="col-md-5 choose-date">
										<a click-type="choose-date" value="" class="data-curr" >未来所有日期</a><br />
										<a click-type="choose-date" value="7" >未来七天</a><br />
										<a click-type="choose-date" value="30" >未来一月</a>
										<input type="hidden" id="choose_active_a" />
									</div>
									<div class="col-md-5 choose-date-in">
										<span>从：</span><br />
										<input id="start_date" class="laydate-icon form-control"  placeholder="开始日期(yyyy-mm-dd)" /><br />
										<span>到：</span><br />
										<input id="end_date" class="laydate-icon form-control"  placeholder="结束日期(yyyy-mm-dd)" />
									</div>
								</div>
							</div>
							<div class="col-md-4 choose-day">
								<div class="row choose-when-title">
									<div class="col-md-12 center">一周</div>
								</div>
								<div class="row">
									<a value="一" choose="0" click-type="choose-week" >周一</a>
									<a value="二"  choose="0" click-type="choose-week" >周二</a>
									<a value="三"  choose="0" click-type="choose-week" >周三</a>
									<a value="四"  choose="0" click-type="choose-week" >周四</a>
									<a value="五"  choose="0" click-type="choose-week" >周五</a>
									<a value="六"  choose="0" click-type="choose-week" >周六</a>
									<a value="日"  choose="0" click-type="choose-week" >周日</a>
									<input type="hidden" id="choose_day" >
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12 reset-down">
						<div class="row border-content">
							<div class="col-md-3 reset-choose-time">
								<a href="javascript:resetTime()">重置过滤器</a>
							</div>
							<div class="col-md-4 down-choose-time">
								<a class="other" href="javascript:chooseAreas()">选择其他条件</a>
							</div>
						</div>
					</div>
					
					
				</div>
					
				<div class="row show-choose" id="show_areas" >
					<div class="col-md-12 show-areas">
						<div class="row border-content">
							<div class="col-md-7" >
								<div class="row choose-where-title">
									<div class="col-md-12 center">距离</div>
								</div>
								<div class="row choose-where-tag">
									<div class="col-md-5">
										<a id="where_rhjl" choose="0"  type="choose-where-a" value="" class="data-curr">任何距离</a><br />
										<a id="where_jl_25" choose="0"  type="choose-where-a" value="25" >25公里</a><br />
										<a id="where_jl_50" choose="0"  type="choose-where-a" value="50" >50 公里</a>
									</div>
									<div class="col-md-5">
										<a id="where_jl_75" choose="0"  type="choose-where-a" value="75" >75公里</a><br />
										<a id="where_jl_100" choose="0"  type="choose-where-a" value="100" >100公里</a><br />
										<a id="where_jl_200" choose="0"  type="choose-where-a" value="200" >200公里</a>
									</div>
								</div>
								<div class="clr"></div>
								<input type="hidden" id="choose_where_id" >
							</div>
							<div class="col-md-4" >
								<div class="row choose-where-title">
									<div class="col-md-12 center">家庭住址</div>
								</div>
								<div class="row" >
									<div class="col-md-12 center where-home-tile">
										寻找附近的活动
									</div>
									<div class="col-md-12 center where-add-home">
										<a href="search/actives.html" ><i class="fa fa-home"></i>添加家庭住址</a>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12 reset-down">
						<div class="row border-content">
							<div class="col-md-3 reset-choose-time">
								<a href="javascript:resetDistance()">重置过滤器</a>
							</div>
							<div class="col-md-4 down-choose-time">
								<a class="other" href="javascript:chooseLequ()">选择其他条件</a>
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
								<c:forEach items="${types }" var="type" >
									<a click-type="active-tag" choose="0" value="${type.cl_id }" >${type.cl_name }</a>
								</c:forEach>
								<input type="hidden" id="choose_active_vals" >
							</div>
						</div>
					</div>
					<div class="col-md-12 reset-down">
						<div class="row border-content">
							<div class="col-md-3 reset-choose-time">
								<a href="javascript:resetLequ()">重置过滤器</a>
							</div>
							<div class="col-md-4 down-choose-time">
								<a class="other" href="javascript:chooseTime()">选择其他条件</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="static/js/plugins/layer/laydate/laydate.js"></script>
  </body>
</html>
