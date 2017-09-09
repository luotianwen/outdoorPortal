$(function(){
	
	$.fn.insurance=function(option){
		var $div = $(option.target),
			$cud = $div.find(option.cud);
		this.op={
			$div:$div,
			$cud:$cud,
			$addInsurance:$div.find(option.addInsurance),
			$title:$div.find(option.title),
			$edit:$cud.find(option.edit),
			$del:$cud.find(option.del),
			$save:$cud.find(option.save),
			$listteneInsurance:$div.find(option.listtenerInsurance),
			$show:$div.find(option.show),
			$insure:$div.find(option.insuranceName),
			$chanpin:$div.find(option.chanpin),
			$jihua:$div.find(option.jihua),
			$qixian:$div.find(option.qixian),
			$jiage:$div.find(option.jiage)
		};
		
		// 初始化绑定事件
		this._init();
	}

	$.fn.insurance.prototype={
		_init:function(){
			// 注册添加保险弹窗事件
			this._bindAddInsurance();
			
			// 注册监听添加事件
			this._listteneInsurance();
			
			// 编辑
			this._edit();
			
			// 删除
			this._del();
		},
		/**
		 * 注册添加保险弹窗事件
		 */
		_bindAddInsurance:function(){
			var _this = this;
			_this.op.$addInsurance.on("click",function(){
				var status = $("#saveActiveInfo").parent().parent().find("input[name='addOrUpdate']").val().trim();
				if(status=="update"){
					layer.open({
					  type: 2,
					  title:"选择保险",
					  area: ['80%', '643px'],
					  shade:0.8,
					  fix: true,
					  icon:16,
					  content: ['huodong/selectInsure.html','no']
					});
				}else{
					$("#saveActiveInfo").focus();
					layer.tips("请先填写并保存活动基本信息!", '#saveActiveInfo', {
					  tips: 1
					});
				}
			})
		},
		/**
		 * 注册监听添加事件
		 */
		_listteneInsurance:function(){
			var _this = this;
			
			_this.op.$listteneInsurance.on("click",function(e,bean){
				
				// 保存保险ID
				_this.op.$insure.val(bean.id);
				
				// 保险产品名称
				_this.op.$chanpin.val(bean.chanpin);
				
				// 保险计划名称
				_this.op.$jihua.val(bean.jihua);
				
				// 保险期限
				_this.op.$qixian.val(bean.qixian);
				
				// 保险价格
				_this.op.$jiage.val(bean.jiage);
				
				// 切换样式
				_this.op.$addInsurance.hide();
				_this.op.$cud.show();
				_this.op.$title.show();
				
				// 触发保存或者修改事件
				_this.op.$save.trigger("click",{
					handle:"save",
					fn:function(){
						// 显示保险信息
						_this.op.$show.html(bean.str);
					}
				});
			})
		},
		/**
		 * 编辑保险
		 */
		_edit:function(){
			var _this = this;
			_this.op.$edit.on("click",function(){
				_this.op.$addInsurance.trigger("click");
			})
		},
		/**
		 * 删除已选择的保险
		 */
		_del:function(){
			var _this = this;
			
			_this.op.$del.on("click",function(){
				layer.confirm("确认删除已选择的保险？",function(index){
					
					if(activityId != ""){
						_this._post_del();
					}else{
						_this.op.$show.text("");
						_this.op.$cud.hide();
						_this.op.$title.hide();
						_this.op.$addInsurance.show();
						_this.op.$save.text("保存").css({background:"#ff8a01"});
					}
					layer.close(index);
				})
			})
		},
		/**
		 * 请求服务器删除
		 */
		_post_del:function(){
			var _this = this;
			$.post("activityInsurance/del.json",{
				activityId:activityId,
				insuranceId:_this.op.$insure.val()
			},function(data){

				if(data.RESPONSE_STATE == "200"){
					_this._success();
				}else if(data.RESPONSE_STATE == "500"){
					_this._error(data);
				}
			})
		},
		/**
		 * 删除的成功回调
		 */
		_success:function(){
			var _this = this;

			_this.op.$div.find("input:hidden").val("");
			_this.op.$show.text("");
			_this.op.$cud.hide();
			_this.op.$title.hide();
			_this.op.$addInsurance.show();
			_this.op.$save.text("保存").css({background:"#ff8a01"});
			
			_this._finish();
		},
		/**
		 * 判断整个活动完成度
		 */
		_finish:function(){

			// 完成度判断
			var _this = this,
				_init_finish=0,
				$target = $("a#saveActiveInfo[handle-finish-num]");
			
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
				
				if($state.val().trim()=="update"){
					_init_finish+=($state.attr("finish-num")*1);
				}
				
				if(index == $target.length-1){
					layer.closeAll('dialog'); //关闭信息框
					$("i#activeFinish").css({"width":_init_finish+"%"});
					$("span#activeFinish").text(_init_finish+"%");
					$("input#active_finish_num:hidden").val(_init_finish);
					swal("保存成功!", "当前完成度："+_init_finish+"%", "success");
				}
			})
		},
		_error:function(data){
			layer.closeAll('dialog'); //关闭信息框
			swal("保存失败!", "错误信息："+data.ERROR_INFO, "error");
		}
		
	}

	new $.fn.insurance({
		target:"div#insurance",// 保险元素
		addInsurance:"a#add_insurance",// 弹窗添加保险按钮
		title:"span#title",// 添加完成后显示的标题
		cud:"div#cud",// 编辑，删除，保存操作DIV
		edit:"a#edit",// 编辑
		del:"a#delete",// 删除
		save:"a#saveActiveInfo",// 保存
		listtenerInsurance:"a#listtener_insurance",// 用于监听弹窗选择保险事件
		show:"p#show",// 添加完成后显示的保险信息
		insuranceName:"input[name=insuranceId]:hidden",// form表单用于保存保险ID
		chanpin:"input[name=chanpin]:hidden",
		jihua:"input[name=jihua]:hidden",
		qixian:"input[name=qixian]:hidden",
		jiage:"input[name=jiage]:hidden"
	});




})
