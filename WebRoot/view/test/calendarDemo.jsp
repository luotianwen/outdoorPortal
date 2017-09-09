<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<!DOCTYPE HTML>
<html>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<head>
		<base href="/">
		<title> GRI calendar Demo </title>
		<script type="text/javascript" src="static/js/jquery-2.1.1.min.js"></script>
		<script type="text/javascript" src="static/js/plugins/pickerDateRange/dateRange.js"></script>
		<script type="text/javascript" src="static/js/plugins/pickerDateRange/monthPicker.js"></script>
		<link rel="stylesheet" type="text/css" href="static/js/plugins/pickerDateRange/dateRange.css"/>
		<link rel="stylesheet" type="text/css" href="static/js/plugins/pickerDateRange/monthPicker.css"/>
		<style type="text/css">
			::selection {
				background-color: #E13300;
				color: white;
			}
			::moz-selection {
				background-color: #E13300;
				color: white;
			}
			::webkit-selection {
				background-color: #E13300;
				color: white;
			}
			body {
				background-color: #fff;
				margin: 40px;
				font: 12px     /20px normal Helvetica, Arial, sans-serif;
				color: #4F5155;
			}
			a {
				color: #003399;
				background-color: transparent;
				font-weight: normal;
			}
			input {
				color: #333333;
				font: 12px /1.5 Tahoma, Helvetica, 'SimSun', sans-serif;
			}
			code {
				font-family: Consolas, Monaco, Courier New, Courier, monospace;
				font-size: 12px;
				background-color: #f9f9f9;
				border: 1px solid #D0D0D0;
				color: #002166;
				display: block;
				margin: 14px 0 14px 0;
				padding: 12px 10px 12px 10px;
			}
			h1 {
				color: #444;
				background-color: transparent;
				border-bottom: 1px solid #D0D0D0;
				font-size: 19px;
				font-weight: normal;
				margin: 0 0 14px 0;
				padding: 14px 15px 10px 15px;
			}
			.wrapper {
				margin: 0 15px 0 15px;
			}
			
		</style>
	</head>

	<body bgcolor="white" text="black">
		<h1>Calendar Demo</h1>
		<div class="wrapper">
			<!-- calendar demo1 :start -->
			
			<!-- calendar demo 2 :end-->
			
			<!-- calendar demo 3 : start-->
			<h4>一、选择单天的时间段选择(TA的日期选择器)：</h4>
			<div id="dCon_demo3">
				开始时间：2013-04-14
				<br/>
				结束时间：2013-04-14
				<br/>
			</div>
			<div>
			<a href="javascript:;" id="aRecent7DaysDemo3" >最近七天</a>
			</div>
			 <div class="ta_date" id="div_date_demo3">
                    <span class="date_title" id="date_demo3"></span>
                    <a class="opt_sel" id="input_trigger_demo3" href="#">
                        <i class="i_orderd"></i>
                    </a>
                </div>
                <div id="datePicker"></div>
			<br/>
			<script type="text/javascript">
                var dateRange = new pickerDateRange('date_demo3', {
                    isTodayValid : true,
                    minValidDate: "<%=com.op.util.DateUtil.YYYYMMDDfomatDate(com.op.util.DateUtil.YYYYMMDDgetDay()).getTime()/1000%>",// 最小选择时间的秒数
                    stopToday : false,// 是否截止到当前日期
                    startDate : "<%=com.op.util.DateUtil.YYYYMMDDgetDay()%>",
                    endDate : "<%=com.op.util.DateUtil.YYYYMMDDgetAfterDay("7")%>",
                    defaultText : ' 至 ',
                    inputTrigger : 'input_trigger_demo3',
                    theme : 'ta',
                    success : function(obj) {
                       $("#dCon_demo3").html('开始时间 : ' + obj.startDate + '<br/>结束时间 : ' + obj.endDate);
                    }
                });
			
			</script>
			<br/>
			<p>
				示例代码：(更多参数，参见用户手册)
			</p>
			<code>
				&lt;script type="text/javascript"&gt;
				<br/>
				var dateRange = new pickerDateRange('date', {
				<br/>
				isTodayValid : true,
				<br/>
				startDate : '2013-04-14',
				<br/>
				endDate : '2013-04-21',
				<br/>
				<!-- isSingleDay : true,
				<br/> -->
				theme : 'ta',
				<br/>
				defaultText : ' 至 ',
				<br/>
				success : function(obj) {
				<br/>
				//设置回调句柄
				<br/>
				}
				<br/>
				});
				<br/>
				&lt;/script&gt;
				<br/>
			</code>
			<!-- END -->
<!-- 			<iframe src="/portal/views/calendar_month.php" height="500" width="100%" frameborder="0"></iframe> -->

			<h4>二、支持点击结束日期自动提交，无须确定按钮(ta,gri主题均支持)：</h4>
			<div id="dCon2">
				开始时间：2012-06-14
				<br/>
				结束时间：2012-07-10
				<br/>
			</div>
			<!-- <div id="datePicker">
				<input type="text" name="date" id="date" value="" class="gri_date" style="float:left"/>
			</div -->
			 <div class="ta_date" id="div_date1">
                    <span class="date_title" id="date1"></span>
                    <a class="opt_sel" id="input_trigger1" href="#">
                        <i class="i_orderd"></i>
                    </a>
                </div>
                <div id="datePicker1"></div>
			<br/>
			<script type="text/javascript">
				//var STATS_START_TIME = '1329148800';
				var dateRange1 = new pickerDateRange('date1', {
					isTodayValid : true,
					startDate : '2012-06-14',
					endDate : '2012-07-10',
					needCompare : false,
					defaultText : ' 至 ',
					autoSubmit : true,
					inputTrigger : 'input_trigger1',
					theme : 'ta',
					success : function(obj) {
						$("#dCon2").html('开始时间 : ' + obj.startDate + '<br/>结束时间 : ' + obj.endDate);
					}
				});

			</script>
			<br/>
			<p>
				示例代码：(更多参数，参见用户手册)
			</p>
			<code>
				&lt;script type="text/javascript"&gt;
				<br/>
				var dateRange = new pickerDateRange('date', {
				<br/>
				isTodayValid : true,
				<br/>
				startDate : '2012-06-14',
				<br/>
				endDate : '2012-07-10',
				<br/>
				<strong>autoSubmit : true,</strong>
				<br/>
				theme : 'ta',
				<br/>
				defaultText : ' 至 ',
				<br/>
				success : function(obj) {
				<br/>
				//设置回调句柄
				<br/>
				}
				<br/>
				});
				<br/>
				&lt;/script&gt;
				<br/>
			</code>
			<!-- END -->
			<h4>三、月份选择器：</h4>
			<div id="monthContainer">
				开始时间：2012-06-14
				<br/>
				结束时间：2012-07-10
				<br/>
			</div>
			<!-- <div id="datePicker">
				<input type="text" name="date" id="date" value="" class="gri_date" style="float:left"/>
			</div -->
			 <div class="tool_date cf">
				<div class="ta_date" id="div_month_picker">
					<span class="date_title" id="month_picker"></span>
					<a class="opt_sel" id="month_trigger" href="javascript:;">
						<i class="i_orderd"></i>
					</a>
				</div>
			</div>
			<br/>
			<script type="text/javascript">
				monthPicker.create('month_picker', {
					  		trigger : 'month_trigger',
					  		autoCommit : true,
					  		callback : function(obj){
					  			$("#monthContainer").html('开始时间 : ' + obj.startDate + '<br/>结束时间 : ' + obj.endDate);
					  		}
					  	});

			</script>
			<p>
				示例代码：需额外引入日期的js和css
			</p>
			<code>
				&lt;script type="text/javascript"&gt;
				<br/>
				monthPicker.create('month_picker', {
				<br/>
				trigger : 'month_trigger',
				<br/>
				autoCommit : true,
				<br/>
				callback : function(obj){
				<br/>
				$("#monthContainer").html('开始时间 : ' + obj.startDate + '<br/>结束时间 : ' + obj.endDate);
				<br/>
				}
				<br/>
				});
				<br/>
				&lt;/script&gt;
				<br/>
			</code>
			<h4> 四、时间段对比选择：</h4>
			<div id="dCon_demo1">
			开始时间：2012-06-12
			<br/>
			结束时间：2012-07-10
			<br/>
			</div>
			<div id="datePicker_demo1">
				<input type="text" name="date" id="date_demo1" value="" class="gri_date" style="float:left" />
				
			</div> 
			<script type="text/javascript">
				//var STATS_START_TIME = '1329148800';
				var dateRange1 = new pickerDateRange('date_demo1', {
					isTodayValid : true,
					//isSingleDay : true,
					startDate : '2012-06-12',
					endDate : '2012-07-10',
					needCompare : false,
					defaultText : ' 至 ',
					target : 'datePicker_demo1',
					calendars : 3,
					//theme : 'ta',
					//shortOpr : true,
					success : function(obj) {
						$("#dCon_demo1").html('开始时间 : ' + obj.startDate + '<br/>结束时间 : ' + obj.endDate);
					}
				});
			</script>
			<br/>
			<br/>
			<p>
				示例代码：(更多参数，参见用户手册)
			</p>
			<code>
				&lt;script type="text/javascript"&gt;
				<br/>
				var dateRange = new pickerDateRange('date', {
				<br/>
				isTodayValid : true,
				<br/>
				startDate : '2012-06-12',
				<br/>
				endDate : '2012-07-10',
				<br/>
				needCompare : false,
				<br/>
				defaultText : ' 至 ',
				<br/>
				success : function(obj) {
				<br/>
				//设置回调句柄
				<br/>
				}
				<br/>
				});
				<br/>
				&lt;/script&gt;
				<br/>
			</code>
			<!-- calendar demo1 : end-->
			
			
			<!-- calendar demo2 : start -->
			<h4> 五、单天对比选择：</h4>
			<div id="dCon_demo2">
			开始时间：2012-06-11
			<br/>
			结束时间：2012-07-15
			<br/>
			</div>
			<div id="datePicker_demo2">
				<input type="text" name="date" id="date_demo2" value="" class="gri_date" style="float:left" />
			</div> 
			<br/>
			<script type="text/javascript">
				//var STATS_START_TIME = '1329148800';
				var dateRange = new pickerDateRange('date_demo2', {
					isTodayValid : true,
					startDate : '2012-06-11',
					startCompareDate : '2012-07-15',
					needCompare : true,
					singleCompare : true,
					calendars : 3,
					//theme:'ta',
					success : function(obj) {
						$("#dCon_demo2").html('开始时间 : ' + obj.startDate + '<br/>对比开始时间 : ' + obj.startCompareDate);
					}
				});
	
			</script>
			<br/>
			<p>
				示例代码：(更多参数，参见用户手册)
			</p>
			<code>
				&lt;script type="text/javascript"&gt;
				<br/>
				var dateRange = new pickerDateRange('date', {
				<br/>
				isTodayValid : true,
				<br/>
				startDate : '2012-06-11',
				<br/>
				startCompareDate : '2012-07-10',
				<br/>
				needCompare : true,
				<br/>
				<strong>singleCompare : true,</strong>
				<br/>
				defaultText : ' 对比 ',
				<br/>
				success : function(obj) {
				<br/>
				//设置回调句柄
				<br/>
				}
				<br/>
				});
				<br/>
				&lt;/script&gt;
				<br/>
			</code>
		</div>
	</body>
</html>
