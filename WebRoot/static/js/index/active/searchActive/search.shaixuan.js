// 解析筛选条件
function pst(datas) {
	
	// 获取所有已经选中的条件
	var search = $("input[search]:checked");
	var obj,key,val,_is_zdy_price = true;
	for ( var i = 0, len = search.length; i < len; i++) {
		
		// JQ对象
		var input = $(search[i]);
		
		
		key = input.attr("search-key");
		val = input.val().trim();
		
		// 封装查询对象
		obj = {
				// 该数据对应的key
				key : key,
				
				// 该数据对应的val
				val : val
			};
		
		
		// 判断是否采用历史hash数据
		var a = 0; 
		// checkbox的数据采用拼接判断的方式
		if(input.prop("type").toUpperCase() == "CHECKBOX"){
			for (var j = datas.length-1; j > -1;j--) {
				if (datas[j].key == obj.key) {
					datas[j].val += "," + obj.val;
					a++;
					break;
				}
			}
		}
		if (a == 0) {
			if(key != "prices"){
				datas.push(obj);
			}else{	
				datas.push({key:"price_start",val:input.attr("start")});
				datas.push({key:"price_end",val:input.attr("end")});
			}
		}
		
		// 采用radio非自定义价格
		if(input.prop("name") == "priceRadio6"){
			_is_zdy_price = false;
		}
	}
	if(_is_zdy_price){
		var okSelect = $(".hasBeenSelected .clearList span");
		for(var i=0,len = okSelect.length;i<len;i++){
			var self = $(okSelect[i]);
			if(self.html().trim() == "活动价格"){
				// 价格区间
				if($("#custext1").val() != ""){
					datas.push({
						key : "price_start",
						val : $("#custext1").val()
					});
				}
				if($("#custext2").val() != ""){
					datas.push({
						key : "price_end",
						val : $("#custext2").val()
					});
				}
				break;
			}
		}
	}
	
}
//	更多
$("div#selectList span.more:eq(0)").on("click",function(){
	if(_util_data._more_is_show == "0"){
		_util_data._more_is_show = "1";
	}else{
		_util_data._more_is_show = "0";
	}
	var more = $(this);
	$("dl.more-none").each(function(){
		var self = $(this);
		if(_util_data._more_is_show == "0"){
			more.children('em').attr('class','close-choose');
			self.hide();
		}else{
			if(self.find("input[type=radio]:checked").length > 0){
				return;
			}
			more.children('em').attr('class','open-choose');
			self.show();
		}
	});
});