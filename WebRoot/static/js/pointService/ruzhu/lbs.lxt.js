/*
 * 
 * lng:经度
 * lat:纬度
 */

/*----------------- 按照默认属性创建地图  ----------------*/
// 初始加载：给地图按需设定中心点和坐标等属性
// [经度值在前，纬度值在后]
// 地图初始化过后，任何需要的地方通过方法来改变地图的中心点和级别map.setZoom(10); map.setCenter([116.39,39.9]);
var map_lxt,
	placeSearch;

/*-------------- 起点和终点虽有相同之处，建议写两个方法更灵活和容易维护------------------------*/
var marker_handle={start_marker_select:false,end_marker_select:false,start_marker:'',end_marker:''};
createMap();
function createMap(){

	map_lxt = new AMap.Map('container_lxt', {
		// 缩放级别范围，在PC上，默认为[3,18]，取值范围[3-18]；
		zoom : 10,
		// 初始化加载地图时，若center及level属性缺省，地图默认显示用户当前城市范围
		resizeEnable: true,
		// 设置地图上显示的元素种类，支持bg（地图背景）、point（兴趣点）、road（道路）、building（建筑物）
		features:['bg','point','road','building'],
		//layers: [new AMap.TileLayer.Satellite()],// 卫星图
	});
	
}

//输入提示
var autoOptions = {
    input: "keyword"
};
var auto = new AMap.Autocomplete(autoOptions);

placeSearch = new AMap.PlaceSearch({ //构造地点查询类
    pageSize: 5,
    pageIndex: 1,
    map: map_lxt,
    panel: "panel"
});
//构造地点查询类
AMap.event.addListener(auto, "select", select);//注册监听，当选中某条记录时会触发
function select(e) {
    placeSearch.setCity(e.poi.adcode);
    placeSearch.search(e.poi.name,function(status,result){
		if(status == "complete"){
			$("#panel").show();
		}
	});  //关键字查询查询
}

/**
 * 关键字查询
 * @param value
 */
function search(){
	placeSearch.search($("input#keyword").val(),function(status,result){
		if(status == "complete"){
			$("#panel").show();
		}
	});
}
/*------------ 绑定地图点击事件	---------------*/
function start_marker(){
	if(!marker_handle.start_marker_select){// 控制为只有单个起点
		layer.msg('点击地图标记地点!',{icon:6,shade:0.5,time:1.5*1000});
	}else{
		layer.confirm("已经存在标记拖动它到您想要点的位置吧。",{icon:6,btn:['定位当前标记','取消','重新创建标记'],btn3:function(index, layero){
			marker_handle.start_marker_select = false;
			marker_handle.start_marker.setMap(null);
			marker_handle.start_marker = '';
			layer.close(index);
			start_marker();
		}},function(index){
			layer.close(index);
			map_lxt.setCenter(marker_handle.start_marker.getPosition());
		});
	}
	
	map_lxt.on('click', function(e) {
		if(!marker_handle.start_marker_select){
			marker_handle.start_marker_select = true;
			start_marker_create(e,'',true);
		}
	});
}
// 创建起点marker
function start_marker_create(e,location,draggable){
	var lng = '';
	var lat = '';
	if(location == ''){
		lng = e.lnglat.getLng();// 经度
		lat = e.lnglat.getLat();// 纬度
	}else{
		lng = location.split(',')[0];// 经度
		lat = location.split(',')[1];// 经度
	}
	
	var marker = new AMap.Marker({ //添加自定义点标记
        map: map_lxt,
        position: [lng, lat], //基点位置
        offset: new AMap.Pixel(-17, -42), //相对于基点的偏移位置
        draggable: draggable,  //是否可拖动
        content: '<div class="marker-route marker-marker-bus-from"></div>'   //自定义点标记覆盖物内容
    });
	
	
	marker_handle.start_marker = marker; // 记录起点marker
	
}

function get_markers(){

	if(marker_handle.start_marker == ''){
		layer.msg('请标注商户地点!');
		return;
	}
	/*layer.confirm('确定保存当前路线图吗？',{
		icon : 3,
		btn : [ '确认', '再看看' ],
		title : '提示信息'
	},function(index){*/
		regeocoder();
	/*});*/
}
//逆地理编码
function regeocoder() {
	// 起点-终点提示名称
	var start_marker = marker_handle.start_marker;
	
	var geocoder = new AMap.Geocoder({
		radius : 1000
	});
	
	/*--起终点坐标→地址解析--*/
	geocoder.getAddress(start_marker.getPosition(), function(status, result) {
		if (status === 'complete' && result.info === 'OK') {
			var address = result.regeocode.formattedAddress, //返回地址描述
				parentAddressId = $('#parentAddressId').val();// 父页面需要赋值的下标
			
			var showAddress = '<span>地点：'+address+'</span><input type="hidden" name="ps_coordinates" value="'+start_marker.getPosition()+'" />'
			+'<a href="javascript:void(0)" class="btn-edit-box" id="editAlreadyMapLine" start="'+start_marker.getPosition()+'" >编辑</a>';
			
			parent.$("#"+parentAddressId).html(showAddress).show();
			
			parent.layer.close(parent.layer.getFrameIndex(window.name));// 关闭当前窗体
		}else{
			layer.closeAll();
			layer.msg('活动起点地理编码异常，请重试，给您带来不便敬请谅解!',{icon:5});
		}
	});

}

//重置
function lxt_reset(){
	marker_handle = {start_marker_select:false,end_marker_select:false,start_marker:'',end_marker:''};
	route=null;// 销毁拖拽保存的线路坐标
	createMap();// 重新创建地图
	
}

$(function(){
	// 创建历史marker
	if($("input#start:hidden").val() != ""){
		var start = $("input#start:hidden").val(),
			lng = start.split(",")[0],
			lat = start.split(",")[1];
		var marker = new AMap.Marker({ //添加自定义点标记
	        map: map_lxt,
	        position: [lng, lat], //基点位置
	        offset: new AMap.Pixel(-17, -42), //相对于基点的偏移位置
	        draggable: true,  //是否可拖动
	        content: '<div class="marker-route marker-marker-bus-from"></div>'   //自定义点标记覆盖物内容
	    });
		

		marker_handle.start_marker_select = true;
		marker_handle.start_marker = marker; // 记录起点marker
		
		// 默认显示已经定位到的坐标
		map_lxt.setCenter(marker.getPosition());
	}
})