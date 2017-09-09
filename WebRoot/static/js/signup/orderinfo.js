var _this = null;
$(function(){
	$("div a#quxiao").on('click',function(){
		_this = $(this);
		
		var aa_id = "";
		var count = 0;
		_this.parent().parent().find("div#applicant ul").each(function(index,element){
			if($(this).attr("class")=="checkbox now"){
				if(count==0){
					aa_id += $(this).attr("aa_id");
					count++;
				}else{
					aa_id += ","+$(this).attr("aa_id");
				}
			}
		});
		
		if(aa_id!=""){
			layer.open({
				type: 1,
				shade: false,
				title: false, //不显示标题
				area: ['800px', '400px'], //宽高
				content: $("#refund"),
				cancel: function(index){
					layer.close(index);
					this.content.show();
					$("#refund").css("display","none");
					$("#refundreason").val("");
				}
			});	
		}else{
			layer.alert('请选择要取消的报名人', {
				title:"错误信息",
				icon:0
			});
		}
	});
	
	$("#close").on("click",function(){
		layer.closeAll(); 
		$("#refundreason").val("");
	});
	
	$("#surerefund").on("click",function(){
		var refundreason = $("#refundreason").val();
		if(refundreason==""){
			layer.alert('退款原因不能为空！', {
				title:"错误信息",
				icon:0
			});
		}else{
			var $this = $(this);
			layer.closeAll(); 
			
			var asu_id = $("#asu_id").val();
			var aa_id = "";
			
			var count = 0;
			_this.parent().parent().find("div#applicant ul").each(function(index,element){
				if($(this).attr("class")=="checkbox now"){
					if(count==0){
						aa_id += $(this).attr("aa_id");
						count++;
					}else{
						aa_id += ","+$(this).attr("aa_id");
					}
				}
			});
			
			if(aa_id==""){
				layer.alert('请选择要取消的报名人', {
					title:"错误信息",
					icon:0
				});
			}else{
				layer.confirm('您确定要取消订单？', {
					icon :0,btn: ['确定','取消'] //按钮
				}, function(index){
					layer.close(index);
					$.post("activeSignup/quxiaodigndan?asu_id="+asu_id+"&aa_refund_area="+refundreason+"&aa_id="+aa_id,function(data){
						if(data.RESPONSE_STATE=="500"){
							layer.alert(data.ERROR_INFO, {
								title:"错误信息",
								icon:2
							});
						}else if(data.RESPONSE_STATE=="200"){
							layer.confirm(data.SUCCESS_INFO, {
								icon :1,btn: ['确定'] //按钮
							}, function(index){
								location.reload();
							}, function(index){
								location.reload();
							});
						}
					});
				});
			}
			
		}
	});
	
	$("#kefu").on("click",function(){
		var $this = $(this);
		var aa_id = "";
		var count = 0;
		$this.parent().parent().find("div#applicant ul").each(function(index,element){
			if($(this).attr("refund")=="1"){
				if(count==0){
					aa_id += $(this).attr("aa_id");
					count++;
				}else{
					aa_id += ","+$(this).attr("aa_id");
				}
			}
		});
		
		if(aa_id!=""){
			layer.confirm("是否申请客服处理？", {
				icon :1,btn: ['是','否'] //按钮
			}, function(index){
				var asu_id = $("#asu_id").val();
				var activityId = $("#activityId").val();
				
				$.post("activeSignup/updateRefund.html?asu_id="+asu_id+"&aaIds="+aa_id+"&activityId="+activityId+"&isagree=true",function(data){
					if(data.RESPONSE_STATE=="200"){
						layer.confirm(data.SUCCESS_INFO, {
							icon :1,btn: ['确定'] //按钮
						}, function(index){
							location.reload();
						}, function(index){
							location.reload();
						});
					}else if(data.RESPONSE_STATE=="500"){
						layer.alert(data.ERROR_INFO, {
							title:"错误信息",
							icon:2
						});
					}
				});
			});
		}else{
			layer.alert('没有需要申请客服的报名人！', {
				title:"错误信息",
				icon:0
			});
		}
	});
	
	$("a#sure").on('click',function(){
		var $this = $(this);
		var $orderAll = $this.closest("div#orderAll");
		
		var asu_id = $("#asu_id").val()
		var activityId = $("#activityId").val()
		var aa_id = "";
		var count = 0;
		$this.parent().parent().find("div#applicant ul").each(function(index,element){
			if($(this).attr("class")=="checkbox now"){
				if(count==0){
					aa_id += $(this).attr("aa_id");
					count++;
				}else{
					aa_id += ","+$(this).attr("aa_id");
				}
			}
		});
		
		if(aa_id!=""){
			layer.confirm("您对退款真的满意么?一经选择将不能更改了哦!",{icon:3},function(index){
				layer.close(index);
				$.post("activeSignup/updateRefund.html?asu_id="+asu_id+"&aaIds="+aa_id+"&activityId="+activityId,function(data){
					if(data.RESPONSE_STATE=="200"){
						layer.confirm(data.SUCCESS_INFO, {
							icon :1,btn: ['确定'] //按钮
						}, function(index){
							location.reload();
						}, function(index){
							location.reload();
						});
					}else if(data.RESPONSE_STATE=="500"){
						layer.alert(data.ERROR_INFO, {
							title:"错误信息",
							icon:2
						});
					}
				});
			});
		}else{
			layer.alert('请选择要同意退款的报名人', {
				title:"错误信息",
				icon:0
			});
		}
		
	});
	
	$("a#nosure").on("click",function(){
		layer.open({
			type: 1, //page层
			area: ['310px', '180px'],
			title: "提示",
			shade: 0.6, //遮罩透明度
			btn:['是','否'],
			btn1:function(index){
				$.post("activeSignup/updateRefund.html?asu_id="+asu_id+"&aaIds="+aa_id+"&activityId="+activityId+"&isagree=true",function(data){
					if(data.RESPONSE_STATE=="200"){
						layer.confirm(data.SUCCESS_INFO, {
							icon :1,btn: ['确定'] //按钮
						}, function(index){
							location.reload();
						}, function(index){
							location.reload();
						});
					}else if(data.RESPONSE_STATE=="500"){
						layer.alert(data.ERROR_INFO, {
							title:"错误信息",
							icon:2
						});
					}
				});
			},
			btn2:function(){
				$.post("activeSignup/updateRefund.html?asu_id="+asu_id+"&aaIds="+aa_id+"&activityId="+activityId+"&isagree=false",function(data){
					if(data.RESPONSE_STATE=="200"){
						layer.confirm(data.SUCCESS_INFO, {
							icon :1,btn: ['确定'] //按钮
						}, function(index){
							location.reload();
						}, function(index){
							location.reload();
						});
					}else if(data.RESPONSE_STATE=="500"){
						layer.alert(data.ERROR_INFO, {
							title:"错误信息",
							icon:2
						});
					}
				});
			},
			content: 
				"<div class='layer-anim layui-layer-dialog' type='dialog' times='20' showtime='0' contype='string'>"+
				"	<div class='layui-layer-content layui-layer-padding'>"+
				"		<i class='layui-layer-ico layui-layer-ico0'></i>"+
				"		是否申请客服处理？"+
				"	</div>"+
				"</div>"
		});
	});
	
	$("li").on("click","i.icon-select",function(){
		var $this = $(this);
		if($this.parent().parent().attr("class")=="checkbox"){
			$this.parent().parent().addClass("now");
		}else{
			$this.parent().parent().removeClass("now");
		}
	});
	
	$("li").on("click","a#downup",function(){
		var $this = $(this);
		if($this.find("i").attr("class")=="up"){
			if($this.closest("ul").next().attr("id")=="reason"){
				$this.closest("ul").next().slideUp();
				$this.html("退款原因<i class='down'></i>");
			}
		}else if($this.find("i").attr("class")=="down"){
			if($this.closest("ul").next().attr("id")=="reason"){
				$this.closest("ul").next().slideDown();
				$this.html("收起<i class='up'></i>");
			}
		}
		
	});
})

function immediatePay(asu_id,activityId){
	$.post("activeSignup/toImmediatePay.html",{asu_id:asu_id},function(data){
		if(data.RESPONSE_STATE=='200'){
			window.location.replace("activeSignup/immediatePay.html?asu_id="+asu_id+"&activeId="+activityId);
		}else{
			layer.alert(data.ERROR_INFO, {
				title:"错误信息",
				icon:0
			});
		}
	});
}

