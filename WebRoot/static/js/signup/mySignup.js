	$("div#orderAll").on("click","div a#complete",function(){
		var $this = $(this);
		layer.confirm('交易完成后将无法退款，您想好了吗？', {
			icon :0,btn: ['确定','取消'] //按钮
		}, function(index){
			layer.close(index);
			var asu_id = $this.closest("div#orderAll").find("#asu_id").val();
			var activityId = $this.closest("div#orderAll").find("#activityId").val()
			
			$.post("activeSignup/complete.html",{id:asu_id},function(data){
				if(data.RESPONSE_STATE=="200"){
					window.location.replace("activeSignup/paysuccess.html?asu_id="+asu_id+"&activeId="+activityId);
				}else if(data.RESPONSE_STATE=="500"){
					layer.alert(data.ERROR_INFO, {
						title:"错误信息",
						icon:2
					});
				}
				
			});
		});
	});
	
	$("a#comment").on("click",function(){
		var $this = $(this);
		var $orderAll = $this.closest("div#orderAll");
		
		var asu_id = $orderAll.find("#asu_id").val()
		var activityId = $orderAll.find("#activityId").val()
		
		window.location.replace("activeSignup/paysuccess.html?asu_id="+asu_id+"&activeId="+activityId);
	});
	
	$(function(){
		$("a#showAll").on("click",function(){
			var $this = $(this);
			if($this.find("i").attr("class")=="up"){
				$this.parent().parent().find("#allapplicant").slideUp();
				$this.find("i").removeClass();
				$this.find("i").addClass("down");
			}else if($this.find("i").attr("class")=="down"){
				$this.parent().parent().find("#allapplicant").slideDown();
				$this.find("i").removeClass();
				$this.find("i").addClass("up");
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