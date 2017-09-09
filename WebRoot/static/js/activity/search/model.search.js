var _util_data={
	_de_st:"",// 搜索开始日期
	_de_lt:"",// 搜索结束日期
	_more_is_show:"0",// 筛选条件[更多分类]显示状态
	_minValidDate:"",// 最小可用时间，控制日期选择器的可选力度（单位：秒）
	_default_search_data:[],// 存储主页搜索链接的数据,
	_auto_load:false,// 避免js运行导致的时间过长，采取页面初始加载就开启遮罩 
	_load_msg:function(){
		//layer.msg("搜索中,请稍等...",{icon:16,shade:0.3,time:8*1000})
	},
	_no_data:function(){
		layer.msg("暂无数据!!!",{icon:5,shade:0,time:1*1000});
	},
	_lbs_no_data:function(){
		layer.tips("<span style='color: white;font-size:14px;'>地理位置无结果,请调整位置关键词...</span>", "#current_address",{
			tips:[4,"#FF8A01"],
			time:4*1000
		});
	},
	_search_error:function(){
		layer.tips("<span style='color: white;font-size:14px;'>该地理位置搜索异常,请稍后重试...</span>", "#current_address",{
			tips:[4,"#FF8A01"],
			time:4*1000
		});
	}
}


_util_data._load_msg();
_util_data._auto_load = true;
