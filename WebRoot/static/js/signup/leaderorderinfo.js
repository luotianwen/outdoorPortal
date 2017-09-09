var _this = null;
$(function(){
	$("#confirm").on("click",function(){
		var $this = $(this);
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
		
		var asu_id = $("#asu_id").val()
		var activityId = $("#activityId").val()
		
		if(aa_id!=""){
			layer.confirm('您确定要确认报名？', {
				icon :0,btn: ['确定','取消'] //按钮
			}, function(index){
				layer.close(index);
				$.post("activeSignup/confirmSignUp.html?asu_id="+asu_id+"&aaIds="+aa_id+"&activityId="+activityId,function(data){
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
			layer.alert('请选择要确认报名的人', {
				title:"错误信息",
				icon:0
			});
		}
		
	});

	var close;
	$("#sure").on("click",function(){
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
			close = layer.open({
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
		layer.close(close);
	})

	$("#submit").on("click",function(){
		var refund_money = $("#price").val();
		var refund_remark = $("#refund_remark").val();
		if(!refund_money){
			layer.alert("退款金额不能为空！", {
				title:"错误信息",
				icon:2
			});
			return;
		}
		var reg = /^(\d*\.)?\d+$/;
		
		if(!reg.test(refund_money)){
			layer.alert("退款金额必须为数字！", {
				title:"错误信息",
				icon:2
			});
			return;
		}
		
		if(refund_money*1>$("#maxrefund").html()*1){
			layer.alert("退款金额不能大于付款金额！", {
				title:"错误信息",
				icon:2
			});
			return;
		}
		
		if(!refund_remark){
			layer.alert("退款原因不能为空！", {
				title:"错误信息",
				icon:2
			});
			return;
		}
		
		var asu_id = $("#asu_id").val();
		var aa_id = "";
		$("#activeApplicantRefunding").find("ul.now").each(function(index,element){
			if(index==0){
				aa_id += $(this).attr("aa_id");
			}else{
				aa_id += ","+$(this).attr("aa_id");
			}
		});
		var activityId = $("#activityId").val();
		
		layer.confirm('是否确认提交退款金额?<span style="color:red;">注：待用户同意之后将交由财务部退款</span>', {
			icon :0,btn: ['确定','取消'] //按钮
		}, function(index){
			layer.close(index);
			$.post("activeSignup/cancelSignUp.html?asu_id="+asu_id+"&aaIds="+aa_id+"&activityId="+activityId+"&refund_money="+refund_money+"&refund_remark="+refund_remark,function(data){
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