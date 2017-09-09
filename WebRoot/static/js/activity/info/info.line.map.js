$(function(){
	
	$.fn.lineMap=function(op){
		var _this = this;
		$(op.lineMapClass).each(function(){
			var $item = $(this);
			_this.init({
				mapId:$item.prop("id"),
				start:$item.attr("start-location"),
				end:$item.attr("end-location"),
				coordinates:$item.attr("coordinates")
			})
		})
	}
	
	$.fn.lineMap.prototype={
		// 初始化
		init:function(option){
			this.initMap(option);// 初始化地图信息
			this.initStart(option);// 初始化起点
			this.initEnd(option);// 初始化终点
			this.initLine(option);// 初始化整条线路
			this.toolBar(option);// 添加地图工具
		},
		// 初始化地图信息
		initMap:function(option){
		    this.map = new AMap.Map(option.mapId, {
		        resizeEnable: true,
		        zoom: 11,
		        center: [option.start.split(',')[0], option.start.split(',')[1]],
		    });
		},
		// 初始化起点
		initStart:function(option){
	   		new AMap.Marker({ //添加自定义点标记
		        map: this.map,
		        position: [option.start.split(',')[0], option.start.split(',')[1]], //基点位置
		        offset: new AMap.Pixel(-17, -42), //相对于基点的偏移位置
		        content: '<div class="marker-route marker-marker-bus-from"></div>'   //自定义点标记覆盖物内容
		    });
		},
		// 初始化终点
		initEnd:function(option){
	   		new AMap.Marker({ //添加自定义点标记
		        map: this.map,
		        position: [option.end.split(',')[0], option.end.split(',')[1]], //基点位置
		        offset: new AMap.Pixel(-17, -42), //相对于基点的偏移位置
		        content: '<div class="marker-route marker-marker-bus-from-end"></div>'   //自定义点标记覆盖物内容
		    });
		},
		// 初始化整条线路
		initLine:function(option){
	    	var line_arr = option.coordinates.split('|'),lineArr=[],line="",i=0,len=0;
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
		toolBar:function(option){
			var _this=this;
			this.map.plugin(["AMap.ToolBar"], function() {
				_this.map.addControl(new AMap.ToolBar());
			});
			
		}
	}
	
	new $.fn.lineMap({
		"lineMapClass":"div.line-map"
	})
	

})