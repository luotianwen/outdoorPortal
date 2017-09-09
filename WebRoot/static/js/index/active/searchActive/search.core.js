
	var search_parameter = {};// 搜索对象
	var search_obj = {};// 查询缓存对象
	var default_page = {key:'page',val:'1'};// 更换搜索条件一定要重新设置分页{key:'page',val:'1'}
	var city_name='';// 当前所在城市
	
	// 共通搜索入口 
	// @parame search:{reset:boolean/false(默认),data:[{}]} 
	function searchActive(search){
		
		// 虑到带hash的查询涉及到分享，为保留数据的完整性，默认的属性将不参与查询
		if(search.sealing != "hash"){
			pst(search.data);
		}
		
		// 重置缓存对象，默认false
		if(search.reset){
			search_obj={};
		}
		
		// 解析搜索对象
		for(var i=0,len=search.data.length;i<len;i++){
			var data = search.data[i];
			var key = data.key;// 参数key
			var val = data.val;// 参数值
			
			// 判断是否添加或者删除 默认add 
			if(data.type == 'delete'){
				delete search_obj[key];
			}else{
				search_obj[key] = val;
			}
		}
		
		// 获取json数据
		getData();
	}
	
	 //异步分页
	function getData(){
		if(!_util_data._auto_load){
			_util_data._load_msg();
		}else{
			_util_data._auto_load = false;
		}
	    $.post("search/active.do", search_obj, function(res){
	        //显示分页
	        laypage({
	            cont: 'page', // 容器。值支持id名 
	            pages: res.pageCount, // 通过后台拿到的总页数
	            curr:  res.pageNow,// 当前页
	            skip: true, //是否开启跳页
	            groups: 3, //连续显示分页数
	            last: "尾页:"+res.pageCount, //在尾页追加总页数。
	            jump: function(obj, first){ //触发分页后的回调
	            	 if(!first){
	            		 //点击跳页触发函数自身，并传递点击的当前页：obj.curr
	            		 
	                     //search_parameter.pageNow = obj.curr;// 保存当前页
	                     searchActive({data:[{key:'page',val:obj.curr}]});// 搜索指定页数据
	                 }
	            }
	        });
	        
	        // 总数据量
	        $('#data_num').text(res.rowCount);
	        
	        // 解析parameters构建hash
	        setHash();
	        
	        // 展示结果results
	        show_res(res);
	    });
	};
	
	// parameters构建hash
	function setHash(){
        var i=0;
        var hash;
        for(var item in search_obj){// 遍历查询参数
        	if(i==0){
        		hash = item+"="+search_obj[item];
        	}else{
        		hash += '#'+item+"="+search_obj[item];
        	}
        	i++;
        }
        //alert(hash.replace("&","?"));
        // 记录当前页,当前参数便于复制查询
        location.hash = hash;
	}
	
	// 封装结果集
	function show_res(res){
		if(res && typeof(res.result) != 'undefined' && res.result.length != 0){
			eachdata(res);// 循环展示信息
			setmarker(res);// 地图标注marker
			/* -------------隐藏what列表框*/
			$('#current_what').attr('choose', '0');
			$('#show_lequ').hide();
			/* 隐藏what列表框-------------*/
		}else{
			_util_data._no_data();
		}
	}
	
	// 重置操作
	function resetMap() {
		map.clearMap();// 删除地图上所有的覆盖物
		_active_markers=new Array();// 重置marker数组
      	// _active_markers 自定义地图marker数组
      	$('div.active-item').on({
      		mouseover:function(){
          		var self = $(this);
          		self.find("div.c-introduce").stop().animate({"height":"111px","opacity":"1"}); 
          		for(var i=0;i<_active_markers.length;i++){
          			var marker = _active_markers[i];
          			if(marker.getExtData().id == self.attr('id')){
          				// 当鼠标移到列表数据高亮显示图标
          				marker.setIcon('static/img/index/active/mark-yellow.png');
          			}
          		}
          	},
          	mouseout:function(){
      			var self = $(this);
    			self.find("div.c-introduce").stop().animate({"height":"0","opacity":"0"}); 
          		for(var i=0;i<_active_markers.length;i++){
          			var marker = _active_markers[i];
          			if(marker.getExtData().id == self.attr('id')){
          				// 当鼠标移开列表数据还原图标样式
          				marker.setIcon('static/img/index/active/mark-green.png');
          			}
          		}
          	}
      	});
	}
	// 循环展示信息
	function eachdata(res){
		var _length = res.result.length,
			str = '',
			dyh="'";
		
		for(var i=0;i<_length;i++){
			var a = res.result[i];
			
			str+='<div class="active-item clearfix" onclick="window.open('+dyh+'huodong/info/'+a.id+'.html'+dyh+')" id='+a.id+'>'
				+'<div class="imgbox"><img class="lazy-img" data-original="'+a.a_active_img+'" width="320" height="190" /></div>'//'+a.a_active_img+'
				+'<div class="item-content">'
				+'<div class="c-title">'
				+'<a target="_blank">'+a.title+'</a>'// 标题
				+'<div class="c-price"><span >'+a.price+'</span><b>元</b></div>'// 活动价格
				+'</div>'
				+'<div class="c-SpecificItem">'
				+'<ul>'
				+'<li>开始时间：'+a.activityTimeStr+'</li>'
				+'<li class="f14" ><b class="c1">参加 '+a.alreadyInNum+' 人/</b> 余位 '+(a.needUserNum-a.alreadyInNum)+' 人</li>'
				+'<li>结束时间：'+a.endTimeStr+'</li>'
				+'<li>发布者：'+a.createUserName+'</li>'
				+'<li>出发地：'+a.a_start_location+'</li>'
				+'<li>目的地：'+a.a_end_location+'</li>'
				+'<li class="w">报名截止时间：'+a.a_enroll_end_timeStr+'</li>'
				+'</ul>'
				+'<div class="item-base">'
				+'<ul>'
				+'<li>提问 '+a.consultationNum+'</li>'
				+'<li class="last">满意度 '+a.satisfaction+'%</li>'
				+'</ul>'
				+'</div>'
				+'</div>'
				+'<div class="c-introduce">'+a.characteristic
				+'</div>'
				+'</div>'
				+'</div>';
		}
		
		$('#show_actives').html(str);
		
		// 初始化lazyload并设置图片显示方式
		$("img.lazy-img").lazyload({effect: "fadeIn"});
		
	}

	
	// 点击自助搜索框判断显示或者隐藏
	var _lis = $('[get-search=show_item]');
	_lis.on("click",function(){
		var _show_type = $(this).attr("show-type");
		$("#"+_show_type).show();
		
	});	
	$(document).on("click",function(e) {
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
	// 是否需要解析页面跳转自带参数
	if(_util_data._default_search_data.length > 0){
		// 从主页链接过来的
		if(location.hash == ""){
			// 解析主页跳转搜索参数
			parseSearchData();
		}else{
			// 初始加载解析hash值
			var hash = location.hash;
			var datas = [];// 创建数据数组
			var parameters = location.hash.split('#');// 解析参数
			isAutoSearch = false;
			for(var i=1;i<parameters.length;i++){// 循环添加参数(i=1，因为split会包含#之前的空串)
				var data = parameters[i].split('=');
				datas.push({key:data[0],val:data[1]});// 封装查询对象

				// 初始加载主搜索域赋值样式
				parseSetClass(data[0],data[1]);
				
				// 此处根据【hash值】选中【筛选条件】
				parseParameterSetShaiXuan(data[0],data[1]);
			}
			isAutoSearch = true;
			
			//查询
			searchActive({reset:true,data:datas,sealing:"hash"});
			
		}
	}else{
	    // 获取用户所在城市信息(IP定位)
		showCityInfo();
	}

	/*-------------------------------------		if(_search_data.length > 0)		start	-----------------------*/
	
	/**
	 * 解析主页跳转搜索参数
	 */ 
	function parseSearchData(){
		var datas = [default_page];
		
		for(var i=0,len=_util_data._default_search_data.length;i<len;i++){
			var data = _util_data._default_search_data[i];
			for(key in data){
				
				var val = data[key];
				datas.push({"key":key,val:val});
				
				// 初始加载赋值样式
				parseSetClass(key,val);
			}
		}
		
		//查询
		searchActive({reset:true,data:datas});
	}
	

	
	/*-------------------------------------		if(_search_data.length > 0)		end	-----------------------*/
	
	
	
	/*-------------------------------------		if(_search_data.length == 0)		start	-----------------------*/
	
	
    // 获取用户所在城市信息(IP定位)
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
                    
                    // 初始加载解析hash，如果包含参数，那么不进行默认加载
                    parseHash(cityinfo);
                    city_name = cityinfo;// 当没有查询地址时，采取默认地址
                }
            } else {
                $('#current_address').attr('placeholder','当前位置：不能识别');
                _util_data._lbs_no_data();
            }
        });
    }	
	
	/**
	 * 初始加载解析hash值
	 * @param cityinfo
	 */
	function parseHash(cityinfo){
		var hash = location.hash;
		if(hash != ''){
			var datas = [];// 创建数据数组
			var parameters = location.hash.split('#');// 解析参数

			isAutoSearch = false;
			for(var i=1;i<parameters.length;i++){// 循环添加参数(i=1，因为split会包含#之前的空串)
				var data = parameters[i].split('=');
				datas.push({key:data[0],val:data[1]});// 封装查询对象

				// 初始加载赋值样式
				parseSetClass(data[0],data[1]);
				
				// 此处根据【hash值】选中【筛选条件】
				parseParameterSetShaiXuan(data[0],data[1]);
				
			}
			isAutoSearch = true;
			searchActive({reset:true,data:datas,sealing:"hash"});//查询
		}else{
			var datas = [default_page,{key:'position',val:cityinfo}];
			
	        searchActive({
	        	data:datas
	        });
		}
	}
	
	/*-------------------------------------		if(_search_data.length == 0)		end	-----------------------*/
	
	
	
	/*-------------------------------------		通用解析函数		start	-----------------------*/
	
	/**
	 * 初始加载主搜索域赋值样式
	 */
	function parseSetClass(key,val){
		/*--------		地址-----------*/
		// 位置
		if(key == "position" || key == "default_position"){
			city_name = val;
			$('input[name=position]').val(val);
		}
		
		/*---------------------------------		去哪		-------------------------*/
		if(key == "d"){
			$("div .choose-where-tag a").each(function(){
				var self = $(this);
				if(self.attr("value") == val){
					self.addClass("choose-current-a");
					return;
				}
				self.removeClass("choose-current-a");
			})
			$("input[name=d]:hidden").val(val);
			$("input[show-type=areas_div_id]").val("≤"+val);
		}
		
		/*---------------------------------		玩什么		-------------------------*/
		if(key == "keyword" || key == "types"){
			var vals = val.split(",");
			var showLequ = "";
			var as = $("div.choose-active-type a")
			if(vals.length > 1){
				$("input[name=keyword]").val(val);
				for(var i=0,len = vals.length;i<len;i++){
					for(var j=0,alen=as.length;j<alen && vals[i] != "";j++){
						if($(as[j]).attr("value") == vals[i]){
							showLequ+=$(as[j]).text()+"、";
							$(as[j]).addClass("choose-current-a").attr("choose","1");
							break;
						}
					}
				}
				$("input[name=in_keyword]").val(showLequ.substring(0, showLequ.length-1));
			}else{
				$.each(as,function(index,item){
					if($(item).attr("value") == val){
						$("input[name=in_keyword]").val($(item).text());
						$(item).addClass("choose-current-a").attr("choose","1");
					}
				})
				
			}
		}
	}
	
	/**
	 * 根据【hash值】选中【筛选条件】
	 * @param key
	 * @param val
	 */
	function parseParameterSetShaiXuan(key,val){
		var is = $("input[search-key="+key+"]");
		if(is.length > 0){
			var vals = val.split(",");
			for(var i=0,len=vals.length;i<len && vals[i] != "";i++){
				for(var j=0,islen = is.length;j<islen;j++){
					if($(is[j]).val() == vals[i].trim()){
						var obj = $(is[j]);
						if(obj.prop("type") == "radio"){
							obj.trigger("click");
							// 单选按钮要隐藏整条搜索数据
							obj.closest("dl").hide();
						}else if(obj.prop("type") == "checkbox"){
							obj.next().trigger("click");
						}
						break;
					}
				}
			}
		}
		
		// 区间
		if(key == "price_start"){
			$("#custext1").val(val);
			$("#cusbtn").trigger("click");
		}
		
		// 区间
		if(key == "price_end"){
			$("#custext2").val(val);
			$("#cusbtn").trigger("click");
		}
		
		// 触发筛选条件
		$("#selectList").trigger("click");
		
	}
	
	
	/*-------------------------------------		通用解析函数		end	-----------------------*/
	