
$(function(){
	/*-----------------------添加工具条和比例尺-----------------*/
	AMap.plugin(['AMap.ToolBar','AMap.Scale'],function(){
	    var toolBar = new AMap.ToolBar();
	    var scale = new AMap.Scale();
	    if(typeof(map) != 'undefined'){
	        map.addControl(toolBar);
	        map.addControl(scale);
	    }
	    if(typeof(map_lxt) != 'undefined'){
	    	map_lxt.addControl(toolBar);
	    	map_lxt.addControl(scale);
	    }

	    if(typeof(map_detail_lxt) != 'undefined'){
	    	map_detail_lxt.addControl(toolBar);
	    	map_detail_lxt.addControl(scale);
	    }
	})
})

