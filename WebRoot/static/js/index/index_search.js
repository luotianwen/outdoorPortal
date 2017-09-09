
	// 点击自助搜索框判断显示或者隐藏
	var _lis = $('[get-search=show_item]');
	_lis.click(function(){
		var _show_type = $(this).attr("show-type");
		$("#"+_show_type).show();
		
	});
	$(document).click(function(e) {
		var obj = e.target;
		
		if($(obj).attr("show-type") != "show_time" && $(obj).closest("div[id=show_time]").prop("id") != "show_time"){
			$("#show_time").hide();
		}
		
		if($(obj).attr("show-type") != "show_areas" && $(obj).closest("div[id=show_areas]").prop("id") != "show_areas"){
			$("#show_areas").hide();
		}
		
		if($(obj).attr("show-type") != "show_lequ" && $(obj).closest("div[id=show_lequ]").prop("id") != "show_lequ"){
			$("#show_lequ").hide();
		}
		
	});
	
	
	/*-------------搜索补全-----------------*/
	// 监听当前位置，如果没值隐藏搜索列表
	$('#current_address').watch(function(val){
		if(val == '')
			$('#auto_search').hide();
	})
	
	// 输入提示
	var autoOptions = {
		input : "current_address"
	};
	
	// 搜索补全
	var auto = new AMap.Autocomplete(autoOptions);
	AMap.event.addListener(auto, "complete", complete);// 注册监听，当查询成功时触发此事件
	function complete(e){// 当查询成功时触发此事件
		var tips = e.tips;// tips数据集合
		var str = '';
		var tips_length = tips.length;
		for(var i=0;i<tips_length;i++){
			var tip = tips[i];
			str += '<div class="auto-item" id="amap-sug0" onclick="selected_address('+xx.dyh+tip.name+xx.dyh+','+xx.dyh+tip.location+xx.dyh+')">'+tip.name+'<span class="auto-item-span">'+tip.district+'</span></div>';
		}
		
		if(tips_length > 0){
			$('#auto_search').show().html(str);
		}else{
			$('#auto_search').hide();
		}
	}
	
	// 选中搜索补全列表触发的事件
	function selected_address(name,location) {// 回调
		$('#current_address').val(name);
		$('#auto_search').hide();
		// 关键字搜索
		searchActive([{key:'position',val:name}]);
	}
	
    // 获取用户所在城市信息(IP定位)
	showCityInfo();
    function showCityInfo() {
    	$('#current_address').attr('placeholder','......');
        //实例化城市查询类
        var citysearch = new AMap.CitySearch();
        //自动获取用户IP，返回当前城市
        citysearch.getLocalCity(function(status, result) {
            if (status === 'complete' && result.info === 'OK') {
                if (result && result.city && result.bounds) {
                    var cityinfo = result.city;// 城市名称
                    var citybounds = result.bounds;// 地图展示该城市时使用的矩形区域
                    $('#current_address').attr('placeholder','当前位置：'+cityinfo);
                    $("#default_position").val(cityinfo);
                }
            } else {
                $('#current_address').attr('placeholder','当前位置：不能识别');
            }
        });
    }
    
    //-------------------------------------------------------------------------------------------------------------------
    //-------------------------------------------------------------------------------------------------------------------
    
	// 未来日期选择
    var _choose_dates = $("a[click-type=choose-date]");
    _choose_dates.click(function(){
    	for(var i=0,len=_choose_dates.length;i<len;i++){
    		_choose_dates.eq(i).removeClass();
    	}
    	$(this).addClass("data-curr");
    	var day = $(this).attr("value");
    	$('#choose_active_a').val(day);
    	if(day == ""){
    		$("div.choose-date-in input").prop("disabled",false);
    	}else{
    		$("div.choose-date-in input").prop("disabled",true);
    	}
    })
    
    // 重置日期选择
    function reset_choose_dates(){
    	_choose_dates.each(function(index){
    		$(this).removeClass();
    		if(index == _choose_dates.length-1){
    			$('#choose_active_a').val("");
    			_choose_dates.eq(0).addClass("data-curr");
        		$("div.choose-date-in input").prop("disabled",false);
    		}
    	})
    	
    }
    
    // 周选择
    var chooseDays = [];
    var _choose_weeks = $("a[click-type=choose-week]");
    _choose_weeks.click(function(){
    	if($(this).attr("choose") == 0){
    		$(this).addClass("current-week").attr("choose","1");
    		chooseDays.push({
    			key : $(this).attr("value")
    		});// 缓存数据
    	}else{
    		$(this).attr("choose","0").removeClass();
    		for(var i=0,len=chooseDays.length;i<len;i++){
    			if(chooseDays[i].key == $(this).attr("value")){
    				chooseDays.splice(i,1);
    			}
    		}
    	}
    	// 赋值
    	setChooseDay();
    })

 // 重新设置选择的周删除缓存数据
 function setChooseDay() {
 	var str = '';
 	for ( var i = 0; i < chooseDays.length; i++) {// 删除符合的缓存数据
 		str += chooseDays[i].key + ',';
 	}
 	$('#choose_day').val(str);
 }
    
    // 重置日期选择
    function reset__choose_weeks(){
    	_choose_weeks.each(function(index){
    		$(this).removeClass().attr("choose","0");
    		if(index == _choose_weeks.length-1){
    			chooseDays = []
    			$('#choose_day').val("");
    		}
    	})
    	
    }

    // layer日期插件
    var start_date = {
    	elem : '#start_date',
    	format : 'YYYY-MM-DD',
    	start : laydate.now(),// 开始日期
    	min : laydate.now(), // 设定最小日期为当前日期
    	max : '2099-06-16', // 最大日期
    	istime : false,
    	istoday : true,
    	choose : function(datas) {
    		end_date.min = datas; // 开始日选好后，重置结束日的最小日期
    		end_date.start = datas; // 将结束日的初始值设定为开始日
    	}
    };
    var end_date = {
    	elem : '#end_date',
    	format : 'YYYY-MM-DD',
    	min : laydate.now(),
    	max : '2099-06-16',
    	istime : false,
    	istoday : false,
    	choose : function(datas) {
    		start_date.max = datas; // 结束日选好后，重置开始日的最大日期
    	}
    };
    laydate(start_date);
    laydate(end_date);
    laydate.skin('molv');// 日期插件样式
    function reset_start_date_end_date(){
    	$("#start_date").val("");
    	$("#end_date").val("");
    	end_date.min = laydate.now();
    	end_date.start = laydate.now();
    }
    
    // 重置该过滤器
    function resetTime(){
    	reset_choose_dates();
    	reset_start_date_end_date();
    	reset__choose_weeks();
    }
    
    function chooseAreas(){
    	$("#show_time").hide();
    	$("#show_areas").show();
    }
    
    //-------------------------------------------------------------------------------------------------------------------
    //-------------------------------------------------------------------------------------------------------------------
    
    // 选择距离
    var _choose_where_as = $("a[click-type=choose-where-a]");
    _choose_where_as.click(function(){
    	for(var i=0,len=_choose_where_as.length;i<len;i++){
    		_choose_where_as.eq(i).removeClass().attr("choose","0");
    	}
    	$(this).addClass("data-curr").attr("choose","1");
    	$('#choose_where_id').val($(this).attr("value"));
    })
    
    function resetDistance(){
    	_choose_where_as.each(function(index){
    		$(this).removeClass().attr("choose","0");
    		if(index == _choose_where_as.length-1){
    			_choose_where_as.eq(0).addClass("data-curr").attr("choose","1");
    	    	$('#choose_where_id').val("");
    		}
    		
    	})
    }
    
    function chooseLequ(){

    	$("#show_areas").hide();
    	$("#show_lequ").show();
    }
    
    //-------------------------------------------------------------------------------------------------------------------
    //-------------------------------------------------------------------------------------------------------------------

    // 搜索活动参数拼接
    var activeDatas = [];
    var _choose_active_tags = $("a[click-type=active-tag]");
    _choose_active_tags.click(function(){
    	if($(this).attr("choose") == "0"){
    		activeDatas.push({
    			key : $(this).attr("value")
    		});
        	$(this).addClass("curr").attr("choose","1");
    	}else{
    		$(this).removeClass().attr("choose","0");
        	for ( var i = 0; i < activeDatas.length; i++) {// 删除符合的缓存数据
    			if (activeDatas[i].key == $(this).attr("value")) {
    				activeDatas.splice(i, 1);
    			}
    		}
    	}
    	setChooseActive();
    })

	 // 保存已选的活动类型
	 function setChooseActive() {
	 	var str = '';
	 	for ( var i = 0; i < activeDatas.length; i++) {
	 		str += activeDatas[i].key + ',';
	 	}
	 	$('#choose_active_vals').val(str);
	 }
    
    function resetLequ(){
    	_choose_active_tags.each(function(index){
    		$(this).removeClass().attr("choose","0");
        	$('#choose_active_vals').val("");
    	})
    	
    }
    
    function chooseTime(){

    	$("#show_lequ").hide();
    	$("#show_time").show();
    }
    
