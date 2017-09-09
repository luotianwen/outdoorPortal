
/*------------	手动标注 bein	----------*/
// 手动选择活动起点
function chooseAddress() {
	if (marker_handle.start_marker_select) {// 控制为只有单个起点
		map.setCenter(marker_handle.start_marker.getPosition());// 视野范围移至地图中心
		regeocoder('', marker_handle.start_marker);
	} else {
		layer.msg('请在地图点击!', {
			icon : 6
		});
	}
	map.on('click', function(e) {
		if (!marker_handle.start_marker_select) {// 控制为只有单个起点
			layer.msg('可<span class="span-red">拖拽</span>重新选择哦！',{icon:6});
			$('#choose_address_id').html('<i class="fa fa-map-marker"></i>&nbsp;查看已标记活动起点');
			marker_handle.start_marker_select = true;
			start_marker_create(e);
		}
	});
}

// 手动创建起点marker
function start_marker_create(e) {

	var lng = e.lnglat.getLng();// 经度
	var lat = e.lnglat.getLat();// 纬度

	var marker = new AMap.Marker(
			{ // 添加自定义点标记
				map : map,
				position : [ lng, lat ], // 基点位置
				offset : new AMap.Pixel(-17, -42), // 相对于基点的偏移位置
				draggable : true, // 是否可拖动
				content : '<div class="marker-route marker-marker-bus-from"></div>', // 自定义点标记覆盖物内容
			});
	regeocoder('',marker);// 标记完显示该标注的基本信息
	addListener_handle_address(marker);// 给该起点绑定事件
}

// 给该起点绑定事件
function addListener_handle_address(marker){

	// 绑定点击事件
	AMap.event.addListener(marker, 'click', regeocoder);
	// 绑定拖拽结束后事件
	AMap.event.addListener(marker, 'dragend', regeocoder);
	// 记录起点marker
	marker_handle.start_marker = marker;
	
}

// 逆地理编码
function regeocoder(e,mk) {
	var marker = e == ''?mk:e.target;
	var geocoder = new AMap.Geocoder({
		radius : 1000
	});
	geocoder.getAddress(marker.getPosition(), function(status, result) {
		if (status === 'complete' && result.info === 'OK') {
			var address = result.regeocode.formattedAddress;
			marker.setExtData({address:address});
			// @param addressComponent 地址组成元素
			zdyInfoWindow(marker,result.regeocode.addressComponent);
		}
	});

}

/*---------------------信息窗体----------------*/
// 自定义手动添加marker提示信息
function zdyInfoWindow(marker,addressComponent) {
	var extData = marker.getExtData();// 自定义对象
	var content = '<div class="amap-lib-infowindow">'
			+ '<div class="amap-lib-infowindow-content">'
			+ '<div class="amap-lib-infowindow-content-wrap">';
	if (extData.address != '') {
		content += '<div>地址：' + extData.address + '</div>';
	}
	content += '<div style="padding-top:5px;">'
			+ '<button type="button" style="padding: 5px;" class="btn btn-primary btn-xs" onclick="selectAddress(' 
			+ dyh + marker.getPosition() + dyh + ',' 
			+ dyh + extData.address + dyh + ',' 
			+ dyh + addressComponent.province + dyh + ',' 
			+ dyh + addressComponent.city + dyh + ',' 
			+ dyh + addressComponent.district + dyh
			+ ')">'
			+' <i class="fa fa-map-pin"></i>&nbsp;标记为活动起点</button>'
			+ '</div>';
	content += '</div></div></div>';

	infoWindow = new AMap.InfoWindow({
		content : content,
		offset : new AMap.Pixel(0, -36)
	// 使用默认信息窗体框样式，显示信息内容
	});
	infoWindow.open(map, marker.getPosition());
}

//拾取活动起点坐标
function selectAddress(position,address,province,city,district) {
	layer.confirm('确认<span class="span-red">【'+address+'】</span>为活动起点吗？', {
		skin : 'layui-layer-molv',
		icon : 3,
		btn : [ '确认', '再看看' ],
		title : '坐标选择提示'
	}, function(index) {
		layer.close(index);
		parent.$('#activity_province').val(province);// poi所在省份
		parent.$('#activity_city').val(city);// poi所在城市名称
		parent.$('#activity_district').val(district);// poi所在行政区名称
		parent.$('#activity_ADDRESS').val(address);// 活动地址
		parent.$('#show_active_address').html(address);// 父窗体页面显示地址
		parent.$('#active_position').val(position)// 坐标
		parent.layer.close(parent.layer.getFrameIndex(window.name));// 关闭当前弹窗
	});
}


/*------------	手动标注 end	----------*/