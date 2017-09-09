
/*---------------------------------	搜索附近的活动	begin */
function search_active() {
	// 打开地图平移效果,显示中心点
	is_load_moveend = true;
	
	// 换了关键字重新设置分页
	var data = [ default_page ];
	
	/*--------------------------时间	----------*/
	search_fujin_method_st_lt(data);
	
	/*--------------------------去哪儿 -----------*/
	search_fujin_method_d(data);
	
	/*--------------------------玩什么 -----------*/
	search_fujin_method_keyword(data)
	
	/*--------------------------活动地址 -----------------*/
	search_fujin_method_position(data);
	
}

/**
 * 时间
 */
function search_fujin_method_st_lt(data){
	var _st_v = $("input[name=st]").val();
	var _lt_v = $("input[name=lt]").val();
	if(_st_v != '' && _lt_v !=''){
		data.push({
			key : 'st',
			val : _st_v
		});
		data.push({
			key : 'lt',
			val : _lt_v
		});
	}
	
}

/**
 * 去哪儿
 */
function search_fujin_method_d(data){
	var _d_v = $("input[name=d]").val().trim();
	if(_d_v != ''){
		data.push({
			key : 'd',
			val : _d_v
		});
	}
}

/**
 * 玩什么
 */
function search_fujin_method_keyword(data){

	// 判断用户是否自定义输入活动类型
	var _i_v = $('input[name=in_keyword]').val().trim();
	var _k_v = $('input[name=keyword]:hidden').val();
	if(_i_v != ''){
		data.push({
			key : 'keyword',
			val : _i_v
		});
	}
	if(_k_v != '') {
		data.push({
			key : 'types',
			val : _k_v
		});
	}
}

/**
 * 活动地址
 */
function search_fujin_method_position(data){
	var _p_v = $('input[name=position]').val().trim();
	if (_p_v != '') {// 判断输入的地址
		// 解析用户输入的地址，返回上一级数据进行检索例如：输入 通州北苑，经过逆地理解析，返回通州北苑+通州区；输入通州区，经过逆地理解析，返回通州区+北京市;这样操作是为了当输入的地区没有搜索到数据，返回上一级进行搜索
		//regeocoder(_p_v,data);
		data.push({
 			key : 'position',
 			val : _p_v
 		 });
		// 搜索
		searchActive({
			reset : true,
			data : data
		});
	}
	// 如果用户没有输入地址，那么采用默认的城市地址
	else{
		data.push({
			key : 'position',
			val : city_name
		});
		// 搜索
		searchActive({
			reset : true,
			data : data
		});
	}
}