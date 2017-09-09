<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="/">
    
    <title>解析查询地址经纬度</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	    <link rel="stylesheet" href="http://cache.amap.com/lbs/static/main1119.css"/>
	    
	    <style type="text/css">
	     #panel {
            position: absolute;
            background-color: white;
            max-height: 90%;
            overflow-y: auto;
            top: 10px;
            right: 10px;
            width: 280px;
        }
	    </style>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="static/js/jquery-2.1.1.min.js"></script>
	<!-- 地图 -->
	<script type="text/javascript" src="http://webapi.amap.com/maps?v=1.3&key=36f68f1175206b118ada135da3492691"></script> 
	<script type="text/javascript" src="http://cache.amap.com/lbs/static/addToolbar.js"></script>
  </head>
  
  <body>
  	<div style="width: 500px;height:50px;margin-top: 30px;">
  		搜索关键字：<input type="text" name="shuju" id="shuju" />
  		<button onclick="search()">搜索</button>
  	</div>
   	<div id="container" style="width: 1300px;height: 700px;margin-top: 100px;"></div>
   	<!-- <div id="tip">可以移动地图，得到城市的信息哦！<br><span id="info"></span></div> -->
   	<div id="panel"></div>
   	
   	<script type="text/javascript">
	 var map = new AMap.Map("container", {
        resizeEnable: true,
        
    });
    
    map.on('moveend', getCity);
    function getCity() {
    	console.log('当前中心坐标:'+map.getCenter()+';获取当前地图视图范围:'+map.getBounds( ).getCenter( ));
        /* map.getCity(function(data) {
            if (data['province'] && typeof data['province'] === 'string') {
                document.getElementById('info').innerHTML = '区县：' + (data['district'] );
            }
        }); */
    }
    
    function search(){
    	var poisList = new Array();
    	var address = $('#shuju').val();
	    AMap.service(["AMap.PlaceSearch"], function() {
	        var placeSearch = new AMap.PlaceSearch({ //构造地点查询类
	            pageSize: 50,
	            pageIndex: 1,
	            city: "010", //城市
	            extensions : 'base',
	            map: map,
	            panel: "panel",
	        });
	        //关键字查询 
	        placeSearch.search(address,function(status,result){
	       		if(status == 'complete'){
		        	var poiList = result.poiList;// 发生事件时返回兴趣点列表
		        	var count = poiList.count;// 查询结果总数 
		        	var pois = poiList.pois;// Poi列表
		        	
		        	for( var i=0;i<pois.length;i++){
		        		var point = pois[i];
		        		var poi = {id:point.id,name:point.name,type:point.type,lng:point.location.getLng(),lat:point.location.getLat(),address:point.address,tel:point.tel};
		        		poisList.splice(i, 0, poi);
		        	}
		        	console.log(poisList);
		        	
	           		 $.ajax({
	     	            type:"POST",
	     	            url:"activity/test.html",
	     	            data:{str:JSON.stringify(poisList)},
	     	            datatype: "json",
	     	            success:function(data){
	     	            	console.log(data);
	         	        }      
	     	         });
	        	}
	        });
	    });
    }
   	</script>
  </body>
</html>