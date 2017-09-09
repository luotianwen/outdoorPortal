
$(function(){
	$.fn.parseLine=function(){
		var _this = this;
		$("span#line_start_end").each(function(){
			_this.init($(this));
		})
	}
	
	$.fn.parseLine.prototype={
		init:function($this){
			if($this.attr("start")=="" || $this.attr("end") == ""){
				return;
			}
			var start=[$this.attr("start").split(",")[0],$this.attr("start").split(",")[1]],
				end=[$this.attr("end").split(",")[0],$this.attr("end").split(",")[1]];
			this.parse(start, end, $this);
		},
		parse:function(start_marker, end_marker, $this){
			var geocoder = new AMap.Geocoder({
				radius : 1000
			});
			/*--起终点坐标→地址解析--*/
			geocoder.getAddress([start_marker,end_marker], function(status, result) {
				if (status === 'complete' && result.info === 'OK') {
					var start_pcd = result.regeocodes[0].addressComponent,// 起点地址组成元素
					end_pcd = result.regeocodes[1].addressComponent,// 终点地址组成元素
					start_city = start_pcd.city,// 起点市
					start_district = start_pcd.district,// 起点区
					end_city = end_pcd.city,// 终点市
					end_district = end_pcd.district;// 终点区
					
					var str = '线路：'+start_city+'&nbsp;'+start_district+'——'+end_city+'&nbsp;'+end_district+'';
					
					$this.html(str);
				}else{
				}
			});
		}
	}
	
	new $.fn.parseLine();
	
})