var perfect_box_layer;
$(function(){
	$("#perfect").on("click",function(){
		perfect_box_layer = layer.open({
			type:1,
			title:false,
			closeBtn:false,
			area:['620px','420px'],
			content:$('div#perfect-box')
		})
	});
	
	$("#show").on("click","#edit",function(){
		perfect_box_layer = layer.open({
			type:1,
			title:false,
			closeBtn:false,
			area:['620px','420px'],
			content:$('div#perfect-box')
		})
	});
	
	$("#perfect-close").on("click",function(){
		layer.close(perfect_box_layer);
	});
	
	$("#perfect-box-btn").on("click",function(){
		var departure_time = $("#departure_time").val();
		var travel_days = $("#travel_days").val();
		var travel_person = $("#travel_person").val();
		var per_capita_cost = $("#per_capita_cost").val();
		
		
		
		if(departure_time==""||departure_time==null){
			layer.alert("出发时间不能为空", {
				title:"提醒",
				icon:0
			});
			return;
		}
		if(travel_days==""||travel_days==null){
			layer.alert("出行天数不能为空", {
				title:"提醒",
				icon:0
			});
			return;
		}
		if(travel_person==""||travel_person==null){
			layer.alert("人物不能为空", {
				title:"提醒",
				icon:0
			});
			return;
		}
		if(per_capita_cost==""||per_capita_cost==null){
			layer.alert("人均费用不能为空", {
				title:"提醒",
				icon:0
			});
			return;
		}else{
			var count = getNum(per_capita_cost);
			if(count.length>0){
				$("#per_capita_cost").val(count);
			}else{
				layer.alert("请输入有效的费用信息(数字)", {
					title:"提醒",
					icon:0
				});
				return;
			}
		}
		
		var $form = $("#myform");
		var $this = $(this);
		$.post("travels/perfectinfo.html?travelsId="+travelsId,$form.serialize(),function(data){
			if(data.RESPONSE_STATE=="200"){
				var html = "<div class='w8 backFFF box' id='show-perfect'>"+
								"<div style='padding-top: 15px;height: 100px;'>"+
						            "<span class=' a6 index position-r'>出发日期："+departure_time+"</span>"+
						            "<span class=' a7 index position-r'>出行天数："+travel_days+"天</span>"+
						            "<span class=' a8 index position-r'>人物："+travel_person+"</span>"+
						            "<span class=' a9 index position-r'>人均费用："+per_capita_cost+"RMB</span>"+
						            "<span class='backf colorFFF button1-1 float-r mR-4' id='edit'>编辑</span>"+
					            "</div>"+
					        "</div>";
				
				if($("#edit-perfect").length>0){
					$("#edit-perfect").replaceWith(html);
				}else{
					if($("#show-perfect").length>0){
						$("#show-perfect").replaceWith(html);
					}
				}
				layer.close(perfect_box_layer);
			}else if(data.RESPONSE_STATE=="500"){
				layer.alert(data.ERROR_INFO, {
					title:"错误信息",
					icon:0
				});
			}
		})
	});
})


function getNum(str){
	return str.replace(/[^0-9]/ig,""); 
}