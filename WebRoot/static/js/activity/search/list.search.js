// 显示辅助搜索
var _lis = $("input[show-search]");
_lis.on("click",function(){
	var self = $(this);
	var _show_type = self.attr("show-type");
	$("div#"+_show_type).show();
	
	// icon
	self.next().on("click",function(){
		$("div#"+_show_type).show();
	})
})

$(document).on("click",function(e){
	var obj = e.target;
	isShowOrHide(obj);
})

// 切换显示div
function isShowOrHide(obj){
	if($(obj).attr("show-type") != "in_position" && $(obj).closest("div#auto_search").length == 0){
		$('div#auto_search').hide();
	}
	
	if($(obj).attr("show-type") != "areas_div_id" && $(obj).closest("div#areas_div_id").length == 0){
		$("div#areas_div_id").hide();
	}
	
	if($(obj).attr("show-type") != "lequ_div_id" && $(obj).closest("div#lequ_div_id").length == 0){
		$("div#lequ_div_id").hide();
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
// 监听当前位置，为空串隐藏搜索列表
var _default_placeholder;
//搜索补全
var auto = new AMap.Autocomplete();
$('input#current_address').watch(function(val){
//	if(val != ''){
//		autoSearch(val);
//	}else{
//		$('#auto_search').hide();
//	}
}).on({
	mouseover:function(){
		_default_placeholder =$(this).attr("placeholder");
		$(this).attr("placeholder","请输入你的出发位置");
	},
	mouseout:function(){
		$(this).attr("placeholder",_default_placeholder);
	}
});

// 搜索补全数据显示
function autoSearch(val){
	auto.search(val,function(state,result){
		if(state == 'complete'){
			var tips = result.tips;
			var dyh = "'";
			var str = '';
			var tips_length = tips.length;
			for(var i=0;i<tips_length;i++){
				var tip = tips[i];
				str += '<div class="auto-item" onclick="selected_address('+dyh+tip.name+dyh+','+dyh+tip.location+dyh+')">'+tip.name+'<span class="auto-item-span">'+tip.district+'</span></div>';
			}
			
			
			if(tips_length > 0){
				$('div#auto_search').html(str).show();
			}else{
				$('div#auto_search').hide();
			}

		}
	})
}

// 选中搜索补全列表触发的事件
function selected_address(name,location) {// 回调
	$('input#current_address').val(name);
	$('div#auto_search').hide();
}
// 获取用户所在城市信息(IP定位)
showCityInfo();
function showCityInfo() {
    //实例化城市查询类
    var citysearch = new AMap.CitySearch();
    //自动获取用户IP，返回当前城市
    citysearch.getLocalCity(function(status, result) {
        if (status === 'complete' && result.info === 'OK') {
            if (result && result.city && result.bounds) {
                var cityinfo = result.city;// 城市名称
                $('input#current_address').attr('placeholder','当前位置：'+cityinfo);
                $("input[name=default_position]:hidden").val(cityinfo);
            }
        } else {
            $('input#current_address').attr('placeholder','当前位置：不能识别');
        }
    });
}

/*----------------------------------------------------	当前位置	end	----------------------------------*/



/*----------------------------------------------------	去哪儿	begin	----------------------------------*/
var _choose_where_as = $("div .choose-where-tag a");
_choose_where_as.on("click",function(){
	for(var i=0,len=_choose_where_as.length;i<len;i++){
		$(_choose_where_as[i]).removeClass();
	}
	var self = $(this);
	self.addClass("choose-current-a");
	$("input[name=d]:hidden").val(self.attr("value"));
	$("input[show-type=areas_div_id]").val(self.text());
	
	$("#areas_div_id").css("display","none");
})
/*----------------------------------------------------	去哪儿	end	----------------------------------*/





/*----------------------------------------------------	玩什么	begin	----------------------------------*/
var _choose_active_type_as = $("div .choose-active-type a");
_choose_active_type_as.on("click",function(){
	var self = $(this);
	if(self.attr("choose") == "0"){
		self.addClass("choose-current-a").attr("choose","1");
	}else{
		self.removeClass("choose-current-a").attr("choose","0");
	}
	
	setKeyWord();
	setindex_what();
})
function setindex_what(){

	var keywords = $("div .choose-active-type a[choose=1]");
	var str="";
	var _len = keywords.length;
	if(_len==0){
		$("#index_what").val("");
	}else{
		keywords.each(function(index){
			str += $(this).html() + ",";
			if(index == (_len-1)){
				str=str.substring(0,str.length-1);
				$("#index_what").val(str);
			}
		})
	}
}
function setKeyWord(){
	var keywords = $("div .choose-active-type a[choose=1]");
	var str="";
	var _len = keywords.length;
	if(_len==0){
		$("input[name=keyword]:hidden").val(str);
	}else{
		keywords.each(function(index){
			str += $(this).attr("value") + ",";
			if(index == (_len-1)){
				str=str.substring(0,str.length-1);
				$("input[name=keyword]:hidden").val(str);
			}
		})
	}
	$("#lequ_div_id").css("display","none");
}
/*----------------------------------------------------	玩什么	end	----------------------------------*/