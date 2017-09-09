var map = new AMap.Map("container", {
	// 缩放级别范围，在PC上，默认为[3,18]，取值范围[3-18]；
	zoom : 9,
	// 初始化加载地图时，若center及level属性缺省，地图默认显示用户当前城市范围
	resizeEnable : true,
});

/* 自定义图层：栅格图(谷歌) */
googleLayer = new AMap.TileLayer({
	zIndex : 2,
	getTileUrl : function(x, y, z) {
		return 'http://mt1.google.cn/vt/lyrs=m@142&hl=zh-CN&gl=cn&x=' + x
				+ '&y=' + y + '&z=' + z + '&s=Galil';
	}
});
googleLayer.setMap(map);