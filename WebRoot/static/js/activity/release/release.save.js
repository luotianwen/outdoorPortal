$(function(){
	
	$.fn.save=function(option){
		this.op=option;
		this._init();
	}
	
	$.fn.save.prototype={
		_init:function(){
			this._bind();
		},
		// 绑定保存事件
		_bind:function(){
			var _this = this;
			$("body").on("click",_this.op.target,function(e,o){
				var $this = $(this);
				
				// 删除后保存操作
				if(o && o.handle == "d"){

					var $form = $this.closest("form");
					
					// 获取form表单数据进行修改
					handleParams = $form.serialize();
					
					// 封装传递数据
					// 类型为装备需求和额外费用，需要标识为删除，后台则直接删除关联的数据重新保存form表单数据
					_this._setdel();
					
					// post请求
					_this._post($form, $this ,o);
				}else if(o && o.handle == "save"){
					var $form = $this.closest("form");
					
					// 获取form表单数据进行修改
					handleParams = $form.serialize();
					
					// 封装传递数据
					_this._set();
					
					// post请求
					_this._post($form, $this ,o);
				}else{
					layer.confirm("是否确认保存内容?",{icon:3},function(index){
						layer.close(index);
						
						var $form = $this.closest("form");
						
						// 获取form表单数据进行修改
						handleParams = $form.serialize();
						
						// 封装传递数据
						_this._set();
						
						// post请求
						_this._post($form, $this ,o);
					});
				}
				
			})
		},
		// 封装传送参数
		_set:function(){
			// 添加基本参数：活动ID和完成度
			handleParams += "&activityId="+activityId+"&activityPrice="+$("input[name=price]").val();
		},
		// 封装删除传送参数
		_setdel:function(){
			// 添加基本参数：活动ID和完成度
			handleParams += "&activityId="+activityId +"&crud=d";
		},
		// post请求
		_post:function($form,$this,o){
			var _this = this;
			layer.msg("保存中...",{icon:16,time:20*1000,shade:0.5})
			$.post($form.prop("action"),handleParams,function(data){
				if(data){
					if(data.RESPONSE_STATE == "200"){
						_this._success(data, $this ,o);
					}else{
						_this._error(data);
					}
				}
			})
		},
		_success:function(data,$this,o){
			var _this = this;
			// 活动ID
			activityId = data.ACTIVITY_ID;
			
			// 线路处理
			if(data.HANDLE_TYPE == "line"){
				_this._handleLine(data, $this);
			}
			
			$form = $this.closest("form");
			//费用小计
			if($form.find("input[name='acp_type']").length>0){
				var costSum = 0;
				$form.find("table tbody tr").each(function(){
					var cost = $(this).find("td").eq(2).find("input").val();
					if(cost!=null && cost!=""){
						costSum += Number(cost);
					}
				})
				$form.find("#cost_sum").html(costSum+".00");
			}
			
			// 修改状态以及统计完成度
			_this._updateState($this,data);
			
			if(o!=null && o.fn && typeof(o.fn) == "function"){
				o.fn();
			}
			
			if(data.type=="deletebx"){
				if($("#cud").find("#delete").length>0){
					$("#insurance").find("input:hidden").val("");
					$("#insurance #show").text("");
					$("#cud").hide();
					$("span#title").hide();
					$("a#add_insurance").show();
					$("a#saveActiveInfo").text("保存").css({background:"#ff8a01"});
				}
			}
		},
		// 线路处理
		_handleLine:function(data,$this){
			var $form = $this.closest("form");
			// 保存线路ID
			if($form.find("input[name=l_id]").length == 0){
				var input = '<input name="l_id" type="hidden" value="'+data.LINE_ID+'" />';
				$form.append(input);
			}
		},
		// 完成度判断
		_updateState:function($this,data){
			var _this = this;
			
			if(typeof($this.attr("handle-finish-num")) != "undefined"){
				var $state;
				
				if($this.closest("div#journey_div").length==0){
					$state = $this.closest("form").find("input[name=addOrUpdate]:hidden");
				}else{
					if($("div#journey_div").index($this.closest("div#journey_div"))>=1){
						$state = $("div#journey_div").eq(0).find("input[name=addOrUpdate]:hidden");
					}else{
						return;
					}
				}
				
				if($state.length>0){
					if($state.val().trim()=="add" && data.DATA_NUM*1 > 0){
						$state.val("update");
						$this.text("修改").css({background:"#A9A9A9"});
					}else if($state.val().trim()=="update" && data.DATA_NUM*1 > 0){
						$this.text("修改").css({background:"#A9A9A9"});
					}else if($state.val().trim()=="update" && data.DATA_NUM*1 == 0){
						$state.val("add");
						$this.text("保存").css({background:"#ff8a01"});
					}
				}else{
					if(data.DATA_NUM*1 > 0){
						$this.text("修改").css({background:"#A9A9A9"});
					}else if(data.DATA_NUM*1 == 0){
						$this.text("保存").css({background:"#ff8a01"});
					}
				}
			}else{
				$this.text("修改").css({background:"#A9A9A9"});
			}
			
			// 统计完成度
			_this._finish();
		},
		_finish:function(){
			// 完成度判断
			var _this = this,
				_init_finish=0,
				$target = $(_this.op.handleFinish);
			
			// 循环所有可统计的元素
			$target.each(function(index){
				var $state;
				
				if($(this).closest("div#journey_div").length==0){
					$state = $(this).closest("form").find("input[name=addOrUpdate]:hidden");
				}else{
					if($("div#journey_div").index($(this).closest("div#journey_div"))==1){
						$state = $("div#journey_div").eq(0).find("input[name=addOrUpdate]:hidden");
					}else{
						return;
					}
				}
				
				if($state.length == 0){
					return;
				}
				if($state.val().trim()=="update"){
					_init_finish+=($state.attr("finish-num")*1);
				}
				
				if(index == $target.length-1){
					layer.closeAll('dialog'); //关闭信息框
					$("i#activeFinish").css({"width":_init_finish+"%"});
					$("span#activeFinish").text(_init_finish+"%");
					$("input#active_finish_num:hidden").val(_init_finish);
					swal({
						title : "保存成功!",
						text : "当前完成度："+_init_finish+"%",
						type : "success",
						confirmButtonColor: "#ff8a01"
					});
				}
			})
		},
		// 错误处理
		_error:function(data){
			var _this = this;
			layer.closeAll('dialog'); //关闭信息框
			if(data.FN){
				_this._error_fn(data);
			}else{
				swal({
					title : "保存失败!",
					text : "错误信息："+data.ERROR_INFO,
					type : "error",
					confirmButtonColor: "#ff8a01"
				});
			}
		},
		// 精确提示错误信息
		_error_fn:function(data){
			if(data.FN == 1){
				$("#saveActiveInfo").focus();
				layer.tips(data.ERROR_INFO, '#saveActiveInfo', {
				  tips: 1
				});
			}
		}
	}
	
	// 实例化保存事件
	new $.fn.save({
		target:"a#saveActiveInfo",
		handleFinish:"a#saveActiveInfo[handle-finish-num]"
	})
})
