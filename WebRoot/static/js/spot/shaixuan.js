var map;
var hasCity = false;
$(function(){
	//选择条件
	$("#selectList").on("click","dl dd label",function(){
		select($(this));
		$("#page").attr("page",1);
		search();
	})

	$(".add").click(function  () {
		$(".hidecitys").hide();$(".hidecountys").hide();$(".hideprovince").show();
		var cla=$(this).attr("class");
		if (cla=="add addnow") {

			$(this).removeClass("addnow");
		}else{
			$(this).addClass("addnow").siblings().removeClass("addnow");
		};
	});
	$(".cityss").click(function  () {
		$(".hidecitys").show();$(".hidecountys").hide();$(".hideprovince").hide();
		var cla=$(this).attr("class");
		if (cla=="add addnow") {

			$(this).removeClass("addnow");
		}else{
			$(this).addClass("addnow")
				.siblings().removeClass("addnow");
		};
	});
	$(".countyss").click(function  () {
		$(".hidecitys").hide();$(".hidecountys").show();$(".hideprovince").hide();
		var cla=$(this).attr("class");
		if (cla=="add addnow") {
			$(this).removeClass("addnow");
		}else{
			$(this).addClass("addnow")
				.siblings().removeClass("addnow");
		};
	});

	//删除已选择条件
	$(".hasBeenSelected .clearList").on("click","div em",function(){
		var $this = $(this);
		var del = $this.closest("div");
		
		var span = del.find("span").html();
		var label = del.find("label").html();
		
		$("div#selectList dl dt").each(function(){
			var _this = $(this);
			if(_this.html() == span){
				if(_this.closest("dl").css("display") == "none"){
					_this.closest("dl").css("display","");
				}else{
					_this.closest("dl").find("dd label a").each(function(){
						var label_this = $(this);
						if(label_this.html() == label){
							label_this.removeClass("selected");
							
							var count = 0;
							if(label_this.closest("dl").find("dt").length>0){
								label_this.closest("dl").find("dd label a").each(function(){
									if($(this).hasClass("selected")){
										count++;
									}
								})
								if(count==0){
									label_this.closest("dl").find("dd label.buxian a").addClass("selected");
								}
							}else{
								label_this.closest("div").closest("dl").find("dd label a").each(function(){
									if($(this).hasClass("selected")){
										count++;
									}
								})
								if(count==0){
									label_this.closest("div").closest("dl").find("dd label.buxian a").addClass("selected");
								}
							}
							
							return false;
						}
					})
				}
				return false;
			}
		});
		
		del.remove();
		$("#page").attr("page",1);
		search();
	})
	
	//清空全部
	$('div.eliminateCriteria').on("click", function(){
		if($(".hasBeenSelected .clearList").html()!=""){
			$(".hasBeenSelected .clearList").html("");
			$("dl:hidden").each(function(){
				$(this).show();
			})
			
			$("#selectList dl dd label a").removeClass("selected");
			
			$("#selectList").find("dd label.buxian a").addClass("selected");
			$("#page").attr("page",1);
			search();
		}
	})
	
	//区域
	$("#qybuxian").on("click",function(){
		$(".hidecitys").hide();$(".hidecountys").hide();$(".hideprovince").hide();
		$("#provinces").val("");
		$("#citys").val("");
		$("#countys").val("");
		$("#provincess").html("省份");
		$("#cityss").html("市区");
		$("#countyss").html("县/镇");
		$("#hidecitys").html("");
		$("#hidecountys").html("");

		$("#provincess").removeClass("current");

		$("#cityss").removeClass("current");

		$("#countyss").removeClass("current");
	});

	$("#search").on("click",function(){
		$("#page").attr("page",1);
		search();
	})

	//回车搜索
	$("#index_where").keypress(function(e) {
		if (e.which == 13){
			$("#search").trigger("click");
		}
	})

	
})
function init() {
	history();
}
function cssCurr(id){
	var cla=$("#"+id+"").attr("class")
	if (cla=="current") {
		$("#"+id+"").removeClass("current");
	}else{
		$("#"+id+"").addClass("current")
			.siblings().removeClass("current");
	}

	$("#qybuxian").removeClass("selected");
}
function cprovinces(id){
	if(id=="")
		return "";
	$("#provincess").html($("#"+id+"").html());
	$("#provinces").val(id);
	$("#provincess").addClass("current");
	$("#citys").val("");
	$("#countys").val("");
	$("#cityss").html("市区");
	$("#countyss").html("县/镇");
	$("#countyss").removeClass("current");
	$("#cityss").removeClass("current");
	$(".hideprovince").hide();
	cssCurr(id);
	$.ajax({
		type : "post",
		url : "spot/citys.json",
		data : {id:id},
		async : false,
		success : function(data){
			if(data.RESPONSE_STATE=="200"){

				var _html="";
				for(var i=0,len=data.citys.length;i<len;i++){
					_html += "<span id="+data.citys[i].code+" onclick='ccitys("+data.citys[i].code+")'>"+data.citys[i].name+"</span>";
				}

				$("#hidecitys").html(_html);
			}else{
				layer.alert("服务器异常，请稍后再试！",{icon:0,title:"失败提醒"});
			}
		}
	});

	if($("#history").val()!="history"){
		$("#page").attr("page",1);
		search();
	}
};

function ccountys(id){
	if(id=="")
		return "";
	$("#countyss").html($("#"+id+"").html());
	$("#countyss").addClass("current");
	$("#countys").val(id);
	$(".hidecountys").hide();
	cssCurr(id);
	if($("#history").val()!="history"){
		$("#page").attr("page",1);
		search();
	}
}
function ccitys(id){
	if(id=="")
		return "";
	$("#cityss").html($("#"+id+"").html());

	$("#citys").val(id);
	$("#countys").val("");
	$("#countyss").html("县/镇");
	$("#countyss").removeClass("current");
	$(".hidecitys").hide();
	cssCurr(id);
	$.ajax({
		type : "post",
		url : "spot/countys.json",
		data : {id:id},
		async : false,
		success : function(data){
			if(data.RESPONSE_STATE=="200"){
				var _html="";
				for(var i=0,len=data.countys.length;i<len;i++){
					_html += '<span id='+data.countys[i].code+' onclick="ccountys('+data.countys[i].code+')">'+data.countys[i].name+'</span>';
				}

				$("#hidecountys").html(_html);
				$("#cityss").addClass("current");
			}else{
				layer.alert("服务器异常，请稍后再试！",{icon:0,title:"失败提醒"});
			}
		}
	});

	if($("#history").val()!="history"){
		$("#page").attr("page",1);
		search();
	}
};
function select($this){
	//不限与自定义条件不触发事件
	if(!$this.hasClass("buxian")&&!$this.hasClass("zdy")){
		if($this.find("input").length>0){
			//input类型
			var type = $this.find("input").attr("type");
			//条件内容
			var tagParent;
			if($this.closest("dl").find("dt").length>0){
				tagParent = $this.closest("dl").find("dt").html();
			}else{
				tagParent = $this.closest("dl").closest("div").closest("dl").find("dt").html();
			}
			
			var $thisa = $this.find("a");
			
			//多选框
			if(type == "checkbox"){
				//已选择
				if($thisa.hasClass("selected")){
					$thisa.removeClass("selected");
					$(".hasBeenSelected .clearList").find("div label").each(function(){
						var label_this = $(this);
						if(label_this.html() == $thisa.html()){
							label_this.closest("div").remove();
							return false;
						}
					})
					if($this.closest("dl").find("dt").length>0){
						$this.closest("dl").find("dd label.buxian a").addClass("selected");
					}else{
						$this.closest("div").closest("dl").find("dd label.buxian a").addClass("selected");
					}
				}
				//未选择
				else{
					if($this.closest("dl").find("dt").length>0){
						$thisa.closest("dl").find("dd label a.selected").each(function(){
							var _this = $(this);
							_this.removeClass("selected");
							$(".hasBeenSelected .clearList").find("div label").each(function(){
								var label_this = $(this);
								if(label_this.html() == _this.html()){
									label_this.closest("div").remove();
									return false;
								}
							})
						})
					}else{
						$thisa.closest("div").closest("dl").find("dd label a.selected").each(function(){
							var _this = $(this);
							_this.removeClass("selected");
							$(".hasBeenSelected .clearList").find("div label").each(function(){
								var label_this = $(this);
								if(label_this.html() == _this.html()){
									label_this.closest("div").remove();
									return false;
								}
							})
						})
					}
					
					$thisa.addClass("selected");
					if($thisa.attr("search-key")=="tp"){
						addLabel(tagParent,$thisa.html(),$thisa.attr("search-key"),$this.find("input").attr("value"),$thisa.attr("star"),$thisa.attr("end"));
					}else{
						addLabel(tagParent,$thisa.html(),$thisa.attr("search-key"),$this.find("input").attr("value"));
					}
					if($this.closest("dl").find("dt").length>0){
						$this.closest("dl").find("dd label.buxian a").removeClass("selected");
					}else{
						$this.closest("div").closest("dl").find("dd label.buxian a").removeClass("selected");
					}
				}
			}
			//单选框
			else if(type == "radio"){
				if($this.closest("dl").find("dt").length>0){
					$this.closest("dl").hide();
				}else{
					$this.closest("dl").closest("div").closest("dl").hide();
				}
				addLabel(tagParent,$thisa.html(),$thisa.attr("search-key"),$this.find("input").attr("value"));
			}
		}
	}else if($this.hasClass("buxian")){
		var $thisa = $this.find("a");
		if(!$thisa.hasClass("selected")){
			$this.closest("dl").find("dd label a.selected").each(function(){
				var _this = $(this);
				_this.removeClass("selected");
				$(".hasBeenSelected .clearList").find("div label").each(function(){
					var label_this = $(this);
					if(label_this.html() == _this.html()){
						label_this.closest("div").remove();
						return false;
					}
				})
			})
			$thisa.addClass("selected");
		}
		
	}
}

function addLabel(tagParent,label,search_key,value,star,end){
	var str = "";
	if(search_key=="tp"){
		str = 'star="'+star+'" end="'+end+'"';
	}
	
	var infor = '<div class=\"selectedInfor selectedShow\" search-key="'+search_key+'" search-value="'+value+'" '+str+'>'+
					'<span>' + tagParent + '</span>'+
					'<label>' + label + '</label>'+
					'<em type=""></em>'+
				'</div>';
	$(".hasBeenSelected .clearList").append(infor);
}

//显示上次选择数据
function history(){
	var url =  window.location.href;
	url = url.split("#");

	if(url.length>1){
		var urls = url[1].split("&");
		for(var i=0,len=urls.length;i<len;i++){
			var type = urls[i].split("=");
			$("#selectList").find("dl dd label").each(function(){
				var $this = $(this);
				if($this.find("input").attr("search-key")==type[0]){
					if($this.find("input").val()==type[1]){
						select($this);
					}
				}
			});
			if(type[0]=="province"){
				cprovinces(type[1]);
				hasCity = true;
			}else if(type[0]=="city"){
				ccitys(type[1]);

			}else if(type[0]=="district"){
				ccountys(type[1]);

			}else if(type[0]=="page"){
				$("#page").attr("page",type[1]);
			}else if(type[0]=="keyword"){
				$("#index_where").val(type[1]);
			}else if(type[0]=="bl"){
				$("#bl").val(type[1]);
			}else if(type[0]=="northEast"){
				$("#northEast").val(type[1]);
			}
			
		}
	}
	
//	if(!hasCity){
//		showCityInfo();
//	}
	
	$("#history").val("now");
	search();
}

function search(object){
	var str = "";
	
	if($("#index_where").val()!="" && $("#index_where").val()!=null){
		str = "keyword="+$("#index_where").val();
	}
	
	$(".hasBeenSelected .clearList").find("div.selectedShow").each(function(){
		var $this = $(this);
		if(str!=""){
			if($this.attr("search-key")=="tp"){
				str += "&ps="+$this.attr("star")+"&pe="+$this.attr("end");
			}else{
				str += "&"+$this.attr("search-key")+"="+$this.attr("search-value");
			}
		}else{
			if($this.attr("search-key")=="tp"){
				str += "ps="+$this.attr("star")+"&pe="+$this.attr("end");
			}else{
				str += $this.attr("search-key")+"="+$this.attr("search-value");
			}
		}
	});
	
	if(str!=""){
		str += "&page="+$("#page").attr("page");
	}else{
		str += "page="+$("#page").attr("page");
	}
	
	if(str!=""){
		location.hash = str;
	}
	
	if(object==true){
		var bounds = map.getBounds();//地物对象的经纬度矩形范围。
		var northEast = bounds.getNorthEast();// 获取东北角坐标。
		var center = bounds.getCenter();//获取当前Bounds的中心点经纬度坐标。
		if(str!=""){
			str += "&bl="+center.getLat()+","+center.getLng()+"&northEast="+northEast.toString();
		}else{
			str += "bl="+center.getLat()+","+center.getLng()+"&northEast="+northEast.toString();
		}
		
		if(str!=""){
			location.hash = str;
		}
	}else{
		if($("#provinces").val()!=""){
			if(str!=""){
				str += "&province="+$("#provinces").val();
			}else{
				str += "province="+$("#provinces").val();
			}
			
			if($("#citys").val()!=""){
				str += "&city="+$("#citys").val();
				if($("#countys").val()!=""){
					str += "&district="+$("#countys").val();
				}
			}
			$("#bl").val("");
			$("#northEast").val("");
		}else{
			if($("#bl").val()!=""&&$("#northEast").val()!=""){
				if(str!=""){
					str += "&bl="+$("#bl").val()+"&northEast="+$("#northEast").val();
				}else{
					str += "bl="+$("#bl").val()+"&northEast="+$("#northEast").val();
				}
			}
		}
		if(str!=""){
			location.hash = str;
		}
	}
	
	$.post("spot/search.json",str,function(data){
		$("#data_num").html(data.rowCount);
		var html = "";
		for(var i=0,len=data.result.length;i<len;i++){
			var scenicspotintroduction = data.result[i].scenicspotintroduction!=null?data.result[i].scenicspotintroduction.length>75?data.result[i].scenicspotintroduction.substr(0,75)+"......":data.result[i].scenicspotintroduction:'';
			html += "<div class='scenic-item clearfix' id='"+data.result[i].id+"'>"+
					"	<div class='imgbox'>"+
					"		<a href='spot/xiang/"+data.result[i].id+".html' target='_blank'><img src='"+data.result[i].url+"' width='320' height='200' /></a>"+
					"	</div>"+
					"	<div class='sc'>"+
					"		<a target='_blank' href='spot/xiang/"+data.result[i].id+".html' class='title'>"+data.result[i].name+"</a>"+
					"		<p class='txt'>"+scenicspotintroduction+"</p>"+
					"		<div class='clearfix'>";
//			if(data.result[i].labelss!=null){
//				for(var j=0,j=data.result[i].labelss.length;j<len;i++){
//					html += "<a href='javascript:void(0)' class='tag'>"+data.result[i].labelss[j]+"</a>";
//				}
//			}
			html += "<a href='spot/xiang/"+data.result[i].id+".html' target='_blank' class='btn-buy'>购买门票</a></div>";
			html +=	"		<div class='clearfix'>"+
					"			<span class='address' title='"+data.result[i].address+"'>"+data.result[i].address+"</span>"+
					"			<span class='price'>"+data.result[i].price+"元</span>"+
					"		</div>"+
					"	</div>"+
					"</div>";
			
		}
		
		if(object!=true){
			if(data.result.length>0){
				var arr = [39.90871575,116.39748037];
				if($("#bl").val()!=null){
					for(var i=0,len=data.result.length;i<len;i++){
						if(data.result[i].coordinate!=null){
							arr = data.result[i].coordinate.split(",");
							break;
						}
					}
				}else{
					arr = [$("#bl").val(),$("#northEast").val()];
				}
				
				map = new AMap.Map("container", {
					resizeEnable : true,
					center : [arr[1],arr[0]],
					zoom : 11
				});
				
				map.on('moveend', function(){
					// 当用户点击搜索得到的数据，地图要平移到该数据的坐标地点
					getBounds();
					begin= new Date().getSeconds();
				});
				
				_spot_markers=new Array();
				
				for(var i=0,len=data.result.length;i<len;i++){
					if(data.result[i].coordinate!=null){
						arr = data.result[i].coordinate.split(",");
						addMarker(arr[1],arr[0],data.result[i]);
					}
				}
				
			}
		}else{
			removeMarker();
			_spot_markers=new Array();
			for(var i=0,len=data.result.length;i<len;i++){
				if(data.result[i].coordinate!=null){
					arr = data.result[i].coordinate.split(",");
					addMarker(arr[1],arr[0],data.result[i]);
				}
			}
			
		}
		
		if(html!=""){
			$("#scenic-list").html(html);
			// 分页
		    laypage({
		        cont: $("#page"), // 容器。值支持id名 
		        pages: data.pageCount, // 通过后台拿到的总页数
		        curr:  data.pageNow,// 当前页
		        groups: 3, //连续显示分页数
		        skin: '#FF8A01',
		        first: 1,
		        last: data.pageCount, //在尾页追加总页数。
		        skip: true, //是否开启跳页
		        jump: function(obj, first){ //触发分页后的回调
	                if(!first){ //点击跳页触发函数自身，并传递当前页：obj.curr
	                	$("#page").attr("page",obj.curr);
	                	layer.msg("加载中...",{icon:16,shade:0.5,time:5*1000})
	                	search();
	                	layer.closeAll('dialog');
	                	$("#scrollUp").trigger("click");
	                }
	            }
		    });
			
		}else{
			layer.msg("暂无数据!!!",{icon:5,shade:0,time:1*1000});
		}
	})
	
}

//根据IP获取城市
//获取用户所在城市信息(IP定位)
function showCityInfo() {
	//实例化城市查询类
	var citysearch = new AMap.CitySearch();
	//自动获取用户IP，返回当前城市
	citysearch.getLocalCity(function(status, result) {
		if (status === 'complete' && result.info === 'OK') {
			if (result && result.city && result.bounds) {
				var cityinfo = result.city;// 城市名称
				//var citybounds = result.bounds;// 地图展示该城市时使用的矩形区域
				$.post("spot/nowCity.json",{name:cityinfo},function(data){
					if(data.RESPONSE_STATE=="200"){
						cprovinces(data.province);
						if(data.city!=null){
							ccitys(data.city);
						}
					}
				})
			}
		}
	});
	
}
