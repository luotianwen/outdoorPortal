
/*----------------- 添加点标记  ----------------*/
// 点标记的创建与添加
// 也可以在创建之后按需更改这些属性: var marker = new
// AMap.Marker(); marker.setMap(map);

var _active_markers=new Array();// marker集合
// 鼠标移近点标记时触发事件
function markerMouseover(e){
	var marker = e.target;
	// 加亮显示marker
	marker.setIcon('static/img/index/active/mark-yellow.png');
	// 获取每个marker对应的列表数据ID，加亮边框显示 
	$('#'+marker.getExtData().id).find("div.c-introduce").stop().animate({"height":"111px","opacity":"1"}, 300); 
	
	//$("body,html").animate({scrollTop:$('#'+marker.getExtData().id).offset().top},300);
}

//鼠标移出点标记时触发事件
function markerMouseout(e){
	var marker = e.target;; 
	// 恢复marker
	marker.setIcon('static/img/index/active/mark-green.png');
	// 恢复样式
	$('#'+marker.getExtData().id).find("div.c-introduce").stop().animate({"height":"0","opacity":"0"}, 300); 
}

// 处理Marker的单击事件
function markerClick(e){ 
	var marker = e.target;
	map.plugin('AMap.AdvancedInfoWindow', function () {
	  var infowindow = new AMap.AdvancedInfoWindow({
		panel: 'panel_search_result',
	    placeSearch: true,
	    asOrigin: true,
	    asDestination: true,
	    offset : new AMap.Pixel(0, -30),
	    content: marker.content
	  });
	  infowindow.open(map, marker.getPosition());
	});
}
// 地图创建marker标注
function setmarker(res){
	resetMap();// 重置地图覆盖物
	// 封装点标注对其进行窗体注入
	for(var i=0,len=res.result.length;i<len;i++){
		var active = res.result[i];
		var marker = new AMap.Marker({
			position: [active.coordinates.split(',')[1],active.coordinates.split(',')[0]],// 活动起点坐标
			map : map,
			icon:'static/img/index/active/mark-green.png',
			extData:{id:active.id}// 自定义属性{id:对应左侧列表div的ID}
		});
	    // 鼠标移近点标记时触发事件
	    marker.on('mouseover',markerMouseover);
	    // 鼠标移出点标记时触发事件
	    marker.on('mouseout',markerMouseout);
	    // 储存marker处理鼠标移到数据高亮显示marker
	    _active_markers.splice(i, 0, marker);
	}
	
	// 默认地图设置地图显示的中心点为第一条数据
	if(res.result.length > 0 ){
		map.setCenter(new AMap.LngLat(res.result[0].coordinates.split(',')[1], res.result[0].coordinates.split(',')[0]));
		centerNum=0;
	}
	
	
}
