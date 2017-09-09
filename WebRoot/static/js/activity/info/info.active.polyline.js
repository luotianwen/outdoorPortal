
$(function(){
	$.fn.activeMap=function(op){
		this.option=op;
		// 初始化
		this.init();
	}
	
	$.fn.activeMap.prototype={
		// 初始化
		init:function(){
			this.initMap();// 初始化地图信息
			this.initStart();// 初始化起点
			this.initEnd();// 初始化终点
			this.initLine();// 初始化整条线路
			this.toolBar();// 添加地图工具
		},
		// 初始化地图信息
		initMap:function(){
		    this.map = new AMap.Map(this.option.mapId, {
		        resizeEnable: true,
		        zoom: 11,
		        center: [this.option.l_start_location.split(',')[0], this.option.l_start_location.split(',')[1]],
		    });
		},
		// 初始化起点
		initStart:function(){
	   		new AMap.Marker({ //添加自定义点标记
		        map: this.map,
		        position: [this.option.l_start_location.split(',')[0], this.option.l_start_location.split(',')[1]], //基点位置
		        offset: new AMap.Pixel(-17, -42), //相对于基点的偏移位置
		        content: '<div class="marker-route marker-marker-bus-from"></div>'   //自定义点标记覆盖物内容
		    });
		},
		// 初始化终点
		initEnd:function(){
	   		new AMap.Marker({ //添加自定义点标记
		        map: this.map,
		        position: [this.option.l_last_location.split(',')[0], this.option.l_last_location.split(',')[1]], //基点位置
		        offset: new AMap.Pixel(-17, -42), //相对于基点的偏移位置
		        content: '<div class="marker-route marker-marker-bus-from-end"></div>'   //自定义点标记覆盖物内容
		    });
		},
		// 初始化整条线路
		initLine:function(){
	    	var line_arr = this.option.coordinates.split('|'),lineArr=[],line="",i=0,len=0;
		    for(len=line_arr.length;i<len;i++){
		    	line = line_arr[i];
		    	if(line != ''){
			    	line = line.split(',');
			    	lineArr.splice(i, 0, [line[0],line[1]]);
		    	}
		    }
		    
		    var polyline = new AMap.Polyline({
		    	map:this.map,
		        path: lineArr,          //设置线覆盖物路径
		        strokeColor: "#3366FF", //线颜色
		        strokeOpacity: 1,       //线透明度
		        strokeWeight: 5,        //线宽
		        strokeStyle: "solid",   //线样式
		        strokeDasharray: [10, 5] //补充线样式
		    });
	    
		},
		// 添加地图工具
		toolBar:function(){
			var _this=this;
			_this.map.plugin(["AMap.ToolBar"], function() {
				_this.map.addControl(new AMap.ToolBar());
			});
			
		}
	}
	
	new $.fn.activeMap({
		"mapId":"container_map",
		"l_start_location":l_start_location,
		"l_last_location":l_last_location,
		"coordinates":coordinates
	})
	
})

