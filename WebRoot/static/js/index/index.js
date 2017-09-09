var _lis = $("input[show-search],span[show-search]");
_lis.on("click",function(){
	var self = $(this);
	var _show_type = self.attr("show-type");
	$("div#"+_show_type).show();
	
	// icon
	self.next().on("click",function(){
		$("div#"+_show_type).show();
	})
	
});
$(document).on("click",function(e,type){
	// 排除自动轮播事件
	if(type == "auto"){
		return;
	}
	var obj = e.target;
	isShowOrHide(obj);
});

//切换显示div
function isShowOrHide(obj){
	// 当前位置
	if($(obj).attr("show-type") != "in_position" && $(obj).prop("id") != "auto_search" && $(obj).closest("div[id=auto_search]").length == 0 ){
		$('div#auto_search').hide();
	}
	
	// 去哪
	if($(obj).attr("show-type") != "areas_div_id" && $(obj).prop("id") != "areas_div_id" && $(obj).closest("div[id=areas_div_id]").length == 0 ){
		$("div#areas_div_id").hide();
	}
	
	// 玩什么
	if($(obj).attr("show-type") != "lequ_div_id" && $(obj).prop("id") != "lequ_div_id" && $(obj).closest("div[id=lequ_div_id]").length == 0 ){
		$("div#lequ_div_id").hide();
	}
	
	if($(obj).attr("show-type") == "span_in_time"){
		$("input#show-time").trigger("click");
	}
	
}

//监控表单数据
$.fn.watch = function(callback) {
return this.each(function() {
   //缓存历史数据
   $.data(this, 'originVal', $(this).val());  
   //event  
   $(this).on('keyup paste', function() {
       var originVal = $(this, 'originVal');  
       var currentVal = $(this).val();  

       if (originVal !== currentVal) {
           $.data(this, 'originVal', $(this).val());
           callback(currentVal);  
       }  
   });  
});  
};

/*----------------------------------------------------	当前位置	begin	----------------------------------*/
//监听当前位置，为空串隐藏搜索列表
var _default_placeholder;
//搜索补全
var auto = new AMap.Autocomplete();
$('input#current_address').watch(function(val){
	if(val != ''){
		autoSearch(val);
	}else{
		$('#auto_search').hide();
	}
}).on({
	mouseover:function(){
		_default_placeholder =$(this).attr("placeholder");
		$(this).attr("placeholder","请输入你的出发位置");
	},
	mouseout:function(){
		$(this).attr("placeholder",_default_placeholder);
	}
});

//搜索补全数据显示
function autoSearch(val){
	auto.search(val,function(state,result){
		if(state == 'complete'){
			var tips = result.tips;
			var dyh = "'";
			var str = '';
			var tips_length = tips.length;
			// 在当前数据不换行的情况下最多显示六条数据
			for(var i=0;i<tips_length;i++){
				var tip = tips[i];
				str += '<li onclick="selected_address('+dyh+tip.name+dyh+','+dyh+tip.location+dyh+')">'+tip.name+'<span>'+tip.district+'</span></li>';
			}
			
			if(tips_length > 0){
				$('div#auto_search').find("ul").html(str).end().show();
			}else{
				$('div#auto_search').hide();
			}

		}
	})
}

//选中搜索补全列表触发的事件
function selected_address(name,loca) {// 回调
	var LngLat=loca.split(",");
	$('input#current_address').val(name);
		$("input[name=bl]:hidden").val(LngLat[1]+","+LngLat[0]);
		$('div#auto_search').hide();
}


//获取用户所在城市信息(IP定位)
showCityInfo();
function showCityInfo() {
 //实例化城市查询类
 var citysearch = new AMap.CitySearch();
 //自动获取用户IP，返回当前城市
 citysearch.getLocalCity(function(status, result) {
     if (status === 'complete' && result.info === 'OK') {
         if (result && result.city && result.bounds) {
             var cityinfo = result.city;// 城市名称
             //var citybounds = result.bounds;// 地图展示该城市时使用的矩形区域
             $('input#current_address').attr('placeholder','当前位置：'+cityinfo);
             $("input[name=default_position]:hidden").val(cityinfo);
         }
     } else {
         $('input#current_address').attr('placeholder','当前位置：不能识别');
     }
 });
}



/*----------------------------------------------------	去哪儿	begin	----------------------------------*/
var _choose_where_as = $("ul#choose_where_tag>li");
_choose_where_as.on("click",function(){
	_choose_where_as.removeClass("aa2");
	var self = $(this);
	self.addClass("aa2");
	$("input[name=d]:hidden").val(self.attr("value"));
	$("input[show-type=areas_div_id]").val(self.text());
	$("#areas_div_id").css("display","none");
})




/*----------------------------------------------------	玩什么	begin	----------------------------------*/
var _choose_active_type_as = $("ul#choose_lequ_tag>a");
_choose_active_type_as.on("click",function(){
	var self = $(this);
	if(self.hasClass("current")){
		self.removeClass("current");
	}else{
		self.addClass("current");
	}
	
	setKeyWord();
})

function setKeyWord(){
	var keywords = $("ul#choose_lequ_tag>a.current");
	var str="",
		showTag="";
	var _len = keywords.length;
	if(_len==0){
		$("input[name=keyword]:hidden").val("");
		$("input[show-type=lequ_div_id]").val("");
	}else{
		keywords.each(function(index){
			str += $(this).attr("value");
			showTag += $(this).text();
			if(index == (_len-1)){
				$("input[name=keyword]:hidden").val(str);
				$("input[show-type=lequ_div_id]").val(showTag);
			}else{
				str += ",";
				showTag += "、";
			}
		})
	}
	$("#lequ_div_id").css("display","none");
}
