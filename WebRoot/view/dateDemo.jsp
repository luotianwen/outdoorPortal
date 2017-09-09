<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>


<head>
	<base href="/">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    

    <title>搜索demo</title>

    <link href="static/css/bootstrap.min.css" rel="stylesheet">
    <link href="static/css/font-awesome.min.css" rel="stylesheet">
    <link href="static/css/animate.min.css" rel="stylesheet">
    <link href="static/css/style.min.css" rel="stylesheet">

</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
		<div class="row">
			<div id="user-box" class="col-sm-12">
				<div class="ibox">
					<div class="ibox-content">
                        	 
                          
                         <div class="form-group">
								<input name="act_start_time" type="text" class="text-box" value="" placeholder="开始时间≥当前时间" title="开始时间≥当前时间" readonly="readonly" style="cursor:pointer;"/>
								<input name="act_stop_time" type="text" class="text-box" value="" placeholder="结束时间>开始时间" title="结束时间>开始时间" readonly="readonly" style="cursor:pointer;"/>
								
								
								
							<input name="publishesTime" id="test" type="text" class="laydate-icon" value=""  />	
							 <span class="input-group-btn"> 
                                          <a class="btn btn-primary" id="submit">搜索</a>
                                         </span>
							</div>
                          
                    </div>
				</div>
			</div>
	 </div>
</div>
	<!-- 全局js -->
    <script src="static/js/jquery-2.1.1.min.js"></script>
    
    <!-- 时间选择选择 Start -->
	<link rel="stylesheet" type="text/css" href="static/css/jquery-ui/jquery-ui.css" />
	<link rel="stylesheet" type="text/css" href="static/css/jquery-ui/jquery.ui.datepicker.css" />
	<script type="text/javascript" src="static/js/plugins/jquery-ui/jquery-ui.min.js"></script>
	<script type="text/javascript" src="static/js/plugins/jquery-ui/i18n/jquery.ui.datepicker-zh-CN.js" ></script>
	<script type="text/javascript" src="static/js/plugins/jquery-ui/jquery-ui-timepicker-addon.js"></script>
	<script type="text/javascript" src="static/js/plugins/jquery-ui/i18n/jquery-ui-timepicker-zh-CN.js"></script>
	
	<script type="text/javascript" src="static/js/plugins/layer/laydate/laydate.js"></script>
	 <!-- 时间选择选择 End -->
	    
    
    <script src="static/js/bootstrap.min.js"></script>
    <script>
    
    laydate.skin('molv');
    laydate({
        elem: '#test',
        min: laydate.now(),  
        istime: true,
        format: 'YYYY-MM-DD hh:mm:ss'
    });
    
		$(function() {
			 
			 $( "input[name='act_start_time'],input[name='act_stop_time']" ).datetimepicker(); 
			 
		 
			            }); 
			
		
	     $("#submit").on("click" , function(){
    		 $.ajax({
    	            type:"POST",
    	            url:"testUser/de.html",
    	            data:{activityTime:$("#test").val()},
    	            datatype: "json",
    	            //成功返回之后调用的函数             
    	            success:function(data){
    	            	 $("#Result").html(data);
        	        },
    	            error: function(){
    	            }         
    	         });
    }); 
		
	</script>


</body>


</html>