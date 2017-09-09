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

//默认路线
var lineStr = parent.$("#"+$("#parentAddressId").val()).find("input[name='l_line_coordinate']").val();

function init(){
	createMap();
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
			
		}
		
		if($("input#end:hidden").val() != ""){
			var end = $("input#end:hidden").val(),
			lng = end.split(",")[0],
			lat = end.split(",")[1];
			
			var marker = new AMap.Marker({ //添加自定义点标记
		        map: map_lxt,
		        position: [lng, lat], //基点位置
		        offset: new AMap.Pixel(-17, -42), //相对于基点的偏移位置
		        draggable: true,  //是否可拖动
		        content: '<div class="marker-route marker-marker-bus-from-end"></div>'   //自定义点标记覆盖物内容
		    });
			
			marker_handle.end_marker = marker; // 记录终点marker
			marker_handle.end_marker_select = true;
			
			start_end_marker_path();
		}
	})
}

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
		layer.msg('请在地图点击某处作为起点!',{icon:6,shade:0.5,time:0.8*1000});
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
	debugger;
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
	
	layer.msg('接下来请在地图标记终点!',{icon:6,shade:0.5,time:0.8*1000});
	
	map_lxt.on('click', function(e) {
		if(!marker_handle.end_marker_select){// 控制为只有单个终点
			marker_handle.end_marker_select = true;
			end_marker_create(e);
			map_lxt.off("click");
		}
	});
	
}

// 创建终点marker
function end_marker_create(e){
	var lng = e.lnglat.getLng();// 经度
	var lat = e.lnglat.getLat();// 纬度
	
	var marker = new AMap.Marker({ //添加自定义点标记
        map: map_lxt,
        position: [lng, lat], //基点位置
        offset: new AMap.Pixel(-17, -42), //相对于基点的偏移位置
        draggable: true,  //是否可拖动
        content: '<div class="marker-route marker-marker-bus-from-end"></div>'   //自定义点标记覆盖物内容
    });
	
	marker_handle.end_marker = marker; // 记录终点marker
	
	start_end_marker_path();
}

// 起点、终点连线
var path,route,editor;
function start_end_marker_path(){
	map_lxt.clearMap();
	//绘制初始路径
	path = [];
	editor = {};
	
	var start = marker_handle.start_marker.getPosition();// 起点marker
	var end = marker_handle.end_marker.getPosition();// 终点marker

	if(lineStr!=null&&lineStr!=""){
		var lines = lineStr.split("|");
		for(var i=0;i<lines.length;i++){
			var line = [];
			
			line.push(lines[i].split(",")[0]);
			line.push(lines[i].split(",")[1]);
			path.push(line);
		}
	}else{
		path.push(new AMap.LngLat(start.getLng(),start.getLat()));
		path.push(new AMap.LngLat(end.getLng(),end.getLat()));
	}
	
	var marker = new AMap.Marker({ //添加自定义点标记
        map: map_lxt,
        position: path[0], //基点位置
        offset: new AMap.Pixel(-17, -42), //相对于基点的偏移位置
        draggable: true,  //是否可拖动
        content: '<div class="marker-route marker-marker-bus-from"></div>'   //自定义点标记覆盖物内容
    });
	
	var marker = new AMap.Marker({ //添加自定义点标记
        map: map_lxt,
        position: path[path.length-1], //基点位置
        offset: new AMap.Pixel(-17, -42), //相对于基点的偏移位置
        draggable: true,  //是否可拖动
        content: '<div class="marker-route marker-marker-bus-from-end"></div>'   //自定义点标记覆盖物内容
    });
	
//	map_lxt.plugin("AMap.DragRoute", function() {
//	  route = new AMap.DragRoute(map_lxt, path, AMap.DrivingPolicy.LEAST_FEE ,{
//		  startMarkerOptions:{ // 起点样式
//			  content: '<div class="marker-route marker-marker-bus-from"></div>'
//		  },
//		  endMarkerOptions:{// 终点样式
//			  content: '<div class="marker-route marker-marker-bus-from-end"></div>'
//		  }
//	  }); //构造拖拽导航类
//	  route.search(); //查询导航路径并开启拖拽导航
//	});

	editor._line=(function(){
		var lineArr = path;
		
        return new AMap.Polyline({
            map: map_lxt,
            path: lineArr,
            strokeColor: "#00AA00",//线颜色
            strokeOpacity: 3,//线透明度
            strokeWeight: 10,//线宽
            strokeStyle: "solid"//线样式
        });
    })();
	
	map_lxt.setFitView();
	
    editor._lineEditor= new AMap.PolyEditor(map_lxt, editor._line);
    editor._lineEditor.open();
	
	layer.msg('系统已为您自动规划线路图，您可以按照自己的要求<span style="color:red;">拖拽已有的线路,改变线路规则！</span>',{icon:6,time:0.8*1000});
}

function reduction_markers(){
	if(editor == null){
		layer.msg('请标注活动线路!',{time:0.8*1000});
		return;
	}
	layer.confirm('确定还原当前路线图吗？',{
		icon : 3,
		btn : [ '确认', '再看看' ],
		title : '提示信息'
	},function(index){
		layer.close(index);
		
		lineStr = "";
		$("#start").val("");
		$("#end").val("");
		
		marker_handle={start_marker_select:false,end_marker_select:false,start_marker:'',end_marker:''};
		
		init();
	});
	
}

function get_markers(){

	if(editor == null){
		layer.msg('请标注活动线路!',{time:0.8*1000});
		return;
	}
	
	layer.confirm('确定保存当前路线图吗？',{
		icon : 3,
		btn : [ '确认', '再看看' ],
		title : '提示信息'
	},function(index){
//		var routes=route.getRoute(),
//			item,str="";
//		for(var i=0,len=routes.length;i<len;i++){
//			item = routes[i];
//			str += item.getLng()+","+item.getLat()+(i==(len-1)?"":"|");
//		}
		
//		regeocoder(str);
		
		var item,str = "";
		for(var i=0,len=editor._line.getPath().length;i<len;i++){
			item = editor._line.getPath()[i];
			str += item.getLng()+","+item.getLat()+(i==(len-1)?"":"|");
		}
		
		brokenline(str);
	});
}

//折线点
function brokenline(lines){
	// 起点-终点提示名称
	var start_marker = editor._line.getPath()[0];
	var end_marker = editor._line.getPath()[editor._line.getPath().length-1];
	
	var geocoder = new AMap.Geocoder({
		radius : 1000
	});
	
	/*--起终点坐标→地址解析--*/
	geocoder.getAddress([start_marker,end_marker], function(status, result) {
		if (status === 'complete' && result.info === 'OK') {
			//var start_address = result.regeocodes[0].formattedAddress;// 起点名称
			//var end_address = result.regeocodes[1].formattedAddress;// 终点名称
			var start_pcd = result.regeocodes[0].addressComponent,// 起点地址组成元素
			end_pcd = result.regeocodes[1].addressComponent,// 终点地址组成元素
			start_city = start_pcd.city,// 起点市
			start_district = start_pcd.district,// 起点区
			end_city = end_pcd.city,// 终点市
			end_district = end_pcd.district;// 终点区

			var parentAddressId = $('#parentAddressId').val();// 父页面需要赋值的下标
			
			var showAddress = '<span>线路：'+start_city+'&nbsp;'+start_district+'——'+end_city+'&nbsp;'
			+end_district+'</span><input type="hidden" name="l_line_coordinate" value="'+lines+'" />'
			+'<a href="javascript:void(0)" class="btn-edit-box" id="editAlreadyMapLine" start="'+start_marker.toString()
			+'" end="'+end_marker.toString()+'">编辑</a>';
			
			parent.$("#"+parentAddressId).html(showAddress).show().prev().hide();
			
			parent.layer.close(parent.layer.getFrameIndex(window.name));// 关闭当前窗体
		}else{
			layer.closeAll();
			layer.msg('活动起点地理编码异常，请重试，给您带来不便敬请谅解!',{icon:5});
		}
	});

}

//逆地理编码
function regeocoder(lines) {
	// 起点-终点提示名称
	var start_marker = route.getRoute()[0];//marker_handle.start_marker;
	var end_marker = route.getRoute()[route.getRoute().length-1];//marker_handle.end_marker;
	
	var geocoder = new AMap.Geocoder({
		radius : 1000
	});
	
	/*--起终点坐标→地址解析--*/
	geocoder.getAddress([start_marker,end_marker], function(status, result) {
		if (status === 'complete' && result.info === 'OK') {
			//var start_address = result.regeocodes[0].formattedAddress;// 起点名称
			//var end_address = result.regeocodes[1].formattedAddress;// 终点名称
			var start_pcd = result.regeocodes[0].addressComponent,// 起点地址组成元素
			end_pcd = result.regeocodes[1].addressComponent,// 终点地址组成元素
			start_city = start_pcd.city,// 起点市
			start_district = start_pcd.district,// 起点区
			end_city = end_pcd.city,// 终点市
			end_district = end_pcd.district;// 终点区

			var parentAddressId = $('#parentAddressId').val();// 父页面需要赋值的下标
			
			var showAddress = '<span>线路：'+start_city+'&nbsp;'+start_district+'——'+end_city+'&nbsp;'
			+end_district+'</span><input type="hidden" name="l_line_coordinate" value="'+lines+'" />'
			+'<a href="javascript:void(0)" class="btn-edit-box" id="editAlreadyMapLine" start="'+start_marker.toString()
			+'" end="'+end_marker.toString()+'">编辑</a>';
			
			parent.$("#"+parentAddressId).html(showAddress).show().prev().hide();
			
			parent.layer.close(parent.layer.getFrameIndex(window.name));// 关闭当前窗体
		}else{
			layer.closeAll();
			layer.msg('活动起点地理编码异常，请重试，给您带来不便敬请谅解!',{icon:5,time:0.8*1000});
		}
	});

}

//重置
function lxt_reset(){
	marker_handle = {start_marker_select:false,end_marker_select:false,start_marker:'',end_marker:''};
	route=null;// 销毁拖拽保存的线路坐标
	createMap();// 重新创建地图
	
}

