/*----------------- 按照默认属性创建地图  ----------------*/
	// 初始加载：给地图按需设定中心点和坐标等属性
	// [经度值在前，纬度值在后]
	// 地图初始化过后，任何需要的地方通过方法来改变地图的中心点和级别map.setZoom(10); map.setCenter([116.39,39.9]);

var begin=0;
var district, map = new AMap.Map('container', {
	// 缩放级别范围，在PC上，默认为[3,18]，取值范围[3-18]；
	zoom : 9,
	// 初始化加载地图时，若center及level属性缺省，地图默认显示用户当前城市范围
	resizeEnable: true,
	//center : _map_center
});
/* 自定义图层：栅格图(谷歌)	*/
googleLayer = new AMap.TileLayer({
    zIndex:2,
    getTileUrl: function(x,y,z){
       return 'http://mt1.google.cn/vt/lyrs=m@142&hl=zh-CN&gl=cn&x='+ x +'&y='+ y +'&z='+ z +'&s=Galil';
    }
 });
googleLayer.setMap(map);
var centerNum=0;
// 地图平移结束后触发。如地图有拖拽缓动效果，则在缓动结束后触发
map.on('moveend', function(){
	// 当用户点击搜索得到的数据，地图要平移到该数据的坐标地点
	if(centerNum>0){
		getBoundsData();
		begin= new Date().getSeconds();
	}
	centerNum++;
});
function getBoundsData(){
	var end= new Date().getSeconds();
	if(begin==0||end-begin>refreshTime){
		getBounds();
	}
//	else{
//		layer.msg('操作太快.休息3s');
//	}
}

// 获取当前地图显示参数
function getBounds() {
	var bounds = map.getBounds();//地物对象的经纬度矩形范围。
	var center = bounds.getCenter();//获取当前Bounds的中心点经纬度坐标。
	var northEast = bounds.getNorthEast();// 获取东北角坐标。

	searchActive({data:[
	                    default_page,// 默认第一页
	                    {key:'bl',val:center.getLat()+","+center.getLng()},// 转换格式纬度，经度
	                    {key:'northEast',val:northEast.toString()}
	             ]});
	
}

//逆地理编码(将用户输入的地址获取上一级地区信息)
function regeocoder(address,data) {
	var geocoder = new AMap.Geocoder({
	});
	geocoder.getLocation(address, function(status, result) {
		 if(status == "no_data"){
			 _util_data._lbs_no_data();
		 }else if(status == "error"){
			 _util_data._search_error();
		 }else if (status == 'complete' && result.info == 'OK') {

        	/* var province = result.geocodes[0].addressComponent.province;// 省
        	 var city = result.geocodes[0].addressComponent.city;// 市
        	 var district = result.geocodes[0].addressComponent.district;// 区/县
        	 if(district != null && district != ''){// 拼接上一级地址
        		 address=district;
        	 }else if(city != null && city != ''){
        		 address=city;
        	 }else if(province != null && province != ''){
        		 address=province;
        	 }*/
			 var bl=result.geocodes[0].location.getLat()+ ", " + result.geocodes[0].location.getLng();
			 data.push({
     			key : 'position',
     			val : address
     		 },{
					 key : 'bl',
					 val : bl
				 }
     		 );
     		 searchActive({// 查询
     			reset : true,
     			data : data
     		 });
         }
    });
}


// map超出窗口视野范围置顶
$(window).on("scroll",function(){
	if($(this).scrollTop() >= $("div#check_map_height").offset().top)
	{
		$("div#active_map").addClass("active-map-stick");
		
	}else{
		$("div#active_map").removeClass("active-map-stick");
	}
});