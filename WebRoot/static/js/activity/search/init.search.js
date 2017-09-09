$(function(){
	// 双日历插件
	for(i in _util_data._default_search_data){
		for(j in _util_data._default_search_data[i]){
			if(j == "st"){
				_util_data._de_st = _util_data._default_search_data[i][j];
			}else if(j == "lt"){
				_util_data._de_lt = _util_data._default_search_data[i][j];
			}
		}
	}
	if(_util_data._de_st != ""){
		$("input[name=st]").val(_util_data._de_st);
	}
	if(_util_data._de_lt != ""){
		$("input[name=lt]").val(_util_data._de_lt);
	}
	

	new pickerDateRange('show-time', {
		autoSubmit : true,// 自动提交
        isTodayValid : true,
        minValidDate: _util_data._minValidDate,// 最小时间为当前日期
        stopToday : false,// 是否截止到当前日期
        startDate: _util_data._de_st,
        endDate: _util_data._de_lt,
        defaultText : ' 至 ',
        theme : 'ta',
        success : function(obj) {
        	$("input[name=st]:hidden").val(obj.startDate);
        	$("input[name=lt]:hidden").val(obj.endDate);
        	$("input#show-time").trigger("click");
        }
	});
	
	// 扩大地图
	$("#expand_map_id").click(function(){
		layer.open({
			type : 2,
			area : [ "97%", "95%" ],
			title : false,
			shade : 0.3,
			fix : true, 
			shift :0,
			maxmin : false,
			closeBtn: 2,
			content : "view/huodong/search/expandMap.html"
		});
	});
	
})