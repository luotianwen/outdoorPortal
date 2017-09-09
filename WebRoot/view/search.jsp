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
    <!-- iCheck -->
    <link href="static/css/plugins/iCheck/custom.css" rel="stylesheet">

</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
		<div class="row">
			<div id="user-box" class="col-sm-12">
				<div class="ibox">
					<div class="ibox-content">
                        <form  id="search"  class="form-horizontal">
                        	 
                         <div class="col-sm-12 text-center">
                                    <div class="input-group">
                                        <input type="text" class="form-control" placeholder="关键字" id="keyword"  name="keyword" value="大运河"> 
                                        <span class="input-group-btn"> 
                                          <a class="btn btn-primary" id="submit">搜索</a>
                                         </span>
                                         
                                          <div class="input-group-btn">
                                            <button type="button" class="btn btn-white dropdown-toggle" data-toggle="dropdown">
                                                <span class="caret"></span>
                                            </button>
                                            <ul class="dropdown-menu dropdown-menu-right" role="menu">
                                            </ul>
                                        </div>
                                         
                             		</div>
                          </div>
                            <div class="form-group">
                               <div class="col-sm-12" id="Result"> </div> 
                            </div>
                           
                           
                        </form>
                    </div>
				</div>
			</div>
	 </div>
</div>
	<!-- 全局js -->
    <script src="static/js/jquery-2.1.1.min.js"></script>
    <script src="static/js/bootstrap.min.js"></script>
	<script src="static/js/plugins/layer/layer.min.js"></script>
    <!-- iCheck -->
    <script src="static/js/plugins/iCheck/icheck.min.js"></script>
    <script src="static/js/plugins/suggest/bootstrap-suggest.min.js"></script>
    <script>
    var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
		$(function() {
			 $(".i-checks").iCheck({
			    radioClass: "iradio_square-green",
			 });
			 
			 $("#keyword").bsSuggest({
	                allowNoKeyword: false,
	                multiWord: true,
	                separator: ",",
	                getDataMethod: "url",
	                 url: "search/sug.html?keyword=", 
	                processData: function(c) {
	                    var b, a, d = {
	                        value: []
	                    };
	                    if (!c || !c.s || c.s.length === 0) {
	                        return false
	                    }
	                    console.log(c.s);
	                    a = c.s.length;
	                    for (b = 0; b < a; b++) {
	                        d.value.push({
	                            word: c.s[b]
	                        });
	                    }
	                    return d;
	                }
	            });     
			 
	    }).on('onSetSelectValue',function (e){
	    	 // $("#submit").click();  
	    });    
			 		 
			          
			           
	</script>


</body>


</html>