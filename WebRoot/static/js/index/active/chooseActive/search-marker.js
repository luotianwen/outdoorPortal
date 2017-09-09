var map;
var _city_info = {
	adcode : ''
};
var dyh = "'";
/*------------	手动标注对象	----------*/
var marker_handle = {
	start_marker_select : false,
	start_marker : ''
};
$(function() {
	// 构造地图
	map = new AMap.Map("container", {
		resizeEnable : true,
	});

	// 输入提示
	var autoOptions = {
		input : "address_in"
	};
	var auto = new AMap.Autocomplete(autoOptions);
	AMap.event.addListener(auto, "select", select);// 注册监听，当选中某条记录时会触发
	function select(e) {
		_city_info.adcode = e.poi.adcode;// 保存城市code
		$('#address_in').val(e.poi.name);
		search('');// 关键字查询查询
	}
})

// 地点检索
var pageSize = 10;// 默认为一页10条数据
var page_num = 10;// 最多显示10条页码
var before_page_num = 5;// 最多显示已经浏览过的页码数量
function search(pageIndex) {
	var address = $('#address_in').val();// 获取关键字
	pageIndex = typeof (pageIndex) == 'undefined' || pageIndex == '' ? 1
			: pageIndex;// 默认当前页码为1

	AMap.service([ "AMap.PlaceSearch" ], function() {
		var placeSearch = new AMap.PlaceSearch({ // 构造地点查询类
			pageSize : pageSize,
			pageIndex : pageIndex,
			map : map,
			extensions : 'all',// 返回基本+详细信息
			city : _city_info.adcode
		});
		// 关键字查询
		placeSearch.search(address, function(status, result) {
			switch (status) {
			case 'complete':
				res_complete(result);
				break;
			case 'no_data':
				no_data();
				break;
			case 'error':
				layer.msg('查询异常，请稍后重试，给您带来的不便敬请谅解!!!', {
					icon : 5
				});
				break;
			}
		});
	});
}

// 封装查询结果集
function res_complete(result) {
	var poiList = result.poiList;// 发生事件时返回兴趣点列表
	var count = poiList.count;// 查询结果总数
	var pois = poiList.pois;// Poi列表
	var pageIndex = poiList.pageIndex;// 当前页码
	if (count == 0) {
		no_data();
		return;
	}

	resetMap();// 重构地图

	setResult(pois);// 生成数据列表
	setPage(pageIndex, count);// 分页
	setMarker(pois);// marker
	$('#panel').show();// 显示搜索结果
}

// 没有结果集提醒
function no_data() {
	layer.tips('没有检索到数据，请调整搜索的关键字!!!', '#address_in', {
		tips : [ 1, '#3595CC' ],
		time : 2000,
		color : 'white'
	});
	$('#address_in').focus();
}

// 重置操作
function resetMap() {
	map.clearMap();// 删除地图上所有的覆盖物
	if(marker_handle.start_marker_select){// 如果已经手动选择了地点，保留之前的数据
		handle_marker_history(marker_handle.start_marker.getPosition());
	}
}

//如果已经手动选择了地点，保留之前的数据
function handle_marker_history(position){
	var marker = new AMap.Marker(
	{ // 添加自定义点标记
		map : map,
		position : position, // 基点位置
		offset : new AMap.Pixel(-17, -42), // 相对于基点的偏移位置
		draggable : true, // 是否可拖动
		content : '<div class="marker-route marker-marker-bus-from" id="handle_start_address"></div>', // 自定义点标记覆盖物内容
	});
	addListener_handle_address(marker);// 给该起点绑定事件
}

// marker
var _markers;
function setMarker(pois) {
	_markers = new Array();// 保存最新的marker信息
	// 封装点标注对其进行窗体注入
	for ( var i = 0; i < pois.length; i++) {
		var pointer = pois[i];
		var marker = new AMap.Marker({
			position : pointer.location.toString().split(','),// 坐标
			title : pointer.name,
			map : map,
			content : '<div class="amap_lib_placeSearch_poi poibox-icon">'
					+ (i + 1) + '</div>',// 图标
			extData : {
				id : pointer.id,// 全局唯一ID
				num : i + 1,// 顺序标识
				name : pointer.name,// 名称
				address : pointer.address,// 地址
				tel : pointer.tel,// 电话
				pname : pointer.pname,// poi所在省份
				cityname : pointer.cityname,// poi所在城市名称
				adname: pointer.adname// poi所在行政区名称
			}
		// 自定义对象
		});
		// 给Marker注册事件
		AMap.event.addListener(marker, 'click', markerClick)
		// 保存marker集合
		_markers.splice(i, 0, marker);
	}
	
	map.setZoom(17);// 缩放级别
	map.setCenter(pois[0].location);// 默认地图中心为查询数据第一条
	markerClick('',_markers[0]);// 默认显示第一个marker的窗体提示信息

}

// 处理Marker的单击事件
function markerClick(e, mk) {
	var marker = e == '' ? mk : e.target;
	var extData = marker.getExtData();// 自定义对象
	var content = '<div class="amap-lib-infowindow">'
			+ '<div class="amap-lib-infowindow-title">' + extData.num + '.'
			+ extData.name + '&nbsp;' + '</div>'
			+ '<div class="amap-lib-infowindow-content">'
			+ '<div class="amap-lib-infowindow-content-wrap">';
	if (extData.address != '') {
		content += '<div>地址：' + extData.address + '</div>';
	}
	if (extData.tel != '') {
		content += '<div>电话：' + extData.tel + '</div>';
	}

	content += '<div style="padding-top:5px;">'
			+ '<button type="button" class="btn btn-primary btn-xs" style="padding: 5px;" '
			+ 'onclick="setStartAddress(' 
			+ dyh + extData.name + dyh + ',' 
			+ dyh + marker.getPosition() + dyh + ',' 
			+ dyh + extData.address + dyh + ',' 
			+ dyh + extData.pname + dyh + ','
			+ dyh + extData.cityname + dyh + ','
			+ dyh + extData.adname + dyh
			+ ')">'
			+ '<i class="fa fa-map-pin"></i>&nbsp;标记为活动起点</button>'
			+ '</div>';
	content += '</div></div></div>';

	infoWindow = new AMap.InfoWindow({
		content : content,
		offset : new AMap.Pixel(0, -30)
	// 使用默认信息窗体框样式，显示信息内容
	});
	infoWindow.open(map, marker.getPosition());
}

// 标记搜索起点
function setStartAddress(name, position, address ,pname ,cityname ,adname) {
	layer.confirm('确认标注<span class="span-red">【' + name + '】</span>为活动起点吗？', {
		skin : 'layui-layer-molv',
		icon : 3,
		btn : [ '确认', '再看看' ],
		title : '坐标选择提示'
	}, function(index) {
		layer.close(index);
		parent.$('#activity_province').val(pname);// poi所在省份
		parent.$('#activity_city').val(cityname);// poi所在城市名称
		parent.$('#activity_district').val(adname);// poi所在行政区名称
		parent.$('#activity_ADDRESS').val(address);// 活动地址
		parent.$('#show_active_address').html(address);// 父窗体页面显示地址
		parent.$('#active_position').val(position)// 坐标
		parent.layer.close(parent.layer.getFrameIndex(window.name));//关闭弹窗
	});
}

// 分页
function setPage(pageIndex, count) {
	var pageSum = 0;// 总页数
	if (count > pageSize) {
		if (count % pageSize == 0) {
			pageSum = count / pageSize;
		} else {
			pageSum = parseInt(count / pageSize) + 1;
		}
	}
	var page_str = '';

	var before_num = 0;// 记录循环次数，用来标记显示后面的页码数量

	var before_page_start_num = 1;// 对已经浏览过的页码进行计算设置起始循环页码
	if (pageIndex > before_page_num) {
		before_page_start_num = pageIndex - before_page_num;
	}
	// 当前页码大于1页时，要把前几页的页码展示出来以供选择，最多显示前5页
	for ( var i = before_page_start_num; i < pageIndex; i++) {
		page_str += '<span><a href="javascript:search(' + i + ')" class="pageLink">' + i + '</a></span>';
		before_num++;
	}

	page_str += '<span>' + pageIndex + '</span>';// 当前页

	// 循环页码 条件为：页码总数小于默认值10条页码并且小于等于总页数
	for ( var i = 1; i < (page_num - before_num) && (pageIndex + i) <= pageSum; i++) {
		page_str += '<span><a href="javascript:search(' + (pageIndex + i) + ')" class="pageLink">' + (pageIndex + i) + '</a></span>';
	}

	// 下一页
	if (pageIndex < pageSum) {
		page_str += '<span><a href="javascript:search(' + (pageIndex + 1) + ')" class="pageLink" >下一页</a></span>';
	}

	$('#result_page_id').html(page_str);
}
// 生成数据列表
function setResult(pois) {
	var datas = '';
	console.log(pois[0]);
	for ( var i = 0; i < pois.length; i++) {
		var point = pois[i];
		datas += '<li class="poibox" onclick="setMapCenter(' + point.location.getLng() + ',' + point.location.getLat() + ',' + dyh + point.id + dyh + ')">'
					+ '<div class="amap_lib_placeSearch_poi poibox-icon">' + (i + 1) + '</div>' 
					+ '<h3 class="poi-title">'
						+ '<span class="poi-name">' + point.name + '</span>' 
					+ '</h3>'
					+ '<div class="poi-info">' 
						+ '<p class="poi-addr">地址：' + point.address + '</p>';
		if (point.tel != '') {
			datas += '<p class="poi-tel">电话：' + point.tel + '</p>';
		}
		datas += '</div></li>';
	}
	$('#search_result_list').html(datas);
}

// 设置地图中心点
function setMapCenter(lng, lat, id) {
	map.setCenter(new AMap.LngLat(lng, lat));// 地图中心
	// 匹配点击的marker展示信息窗口
	// _markers:查询结果集合
	var _markers_length = _markers.length;
	for ( var i = 0; i < _markers_length; i++) {
		var marker = _markers[i];
		if (marker.getExtData().id == id) {
			markerClick('', marker);
			break;
		}
	}
}

/*-------------	快捷删除历史数据 begin	----------------*/
//上传参数
//监控表单数据
$.fn.watch = function(callback) {
return this.each(function() {
   //缓存以前的值  
   $.data(this, 'originVal', $(this).val());  
   //event  
   $(this).on('keyup paste', function() {
       var originVal = $(this, 'originVal');  
       var currentVal = $(this).val();  

       if (originVal !== currentVal) {
           $.data(this, 'originVal', $(this).val());
           callback(currentVal);  
       }  
   });  
});  
};


$('#address_in').watch(function(val){
	if(val.trim() != ''){
		$('#remove_in').show();
	}else{
		$('#remove_in').hide();
	}
})
$('#remove_in').click(function(){
	$('#address_in').val('').focus();
	$('#remove_in').hide();
});
/*-------------	快捷删除历史数据 end	----------------*/