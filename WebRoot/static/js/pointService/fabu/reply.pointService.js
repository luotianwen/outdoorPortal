var _current_choose_photo_id=null,
	_current_project_index=0;
/**
 * 发布项目、完善信息
 */
$(function(){
	$.fn.replyPointService=function(){
		this.init();
	}
	
	$.fn.replyPointService.prototype={
		init:function(){
			this.tabs();
			this.validateForm = null;
			this.$form = $("form#replyPointServiceForm");
			this.validate();
			this.step();
			this.step=1;
			this.$step1=$("div#step1");
			this.$step2=$("div#step2");
			this.$step3=$("div#step3");
			this.$stepUl = $("ul#stepUl");
			this.$addProjectDIV = $("div#addProjectDIV");
			this.addProject();
			this.monitorChoosePhoto();
			// 预约时间
			this.layerdate('defaultDate');
			// 有效期
			this.layerdate('validity_date_start');
			// 截止日期
			this.layerdate('validity_date_end');
			// 项目预约时间
			this.yuyue();
			// 时间插件自定义皮肤
			laydate.skin('cheng');
			this.projectIndex=1;
			// 适合人群多选
			this.selectMultiple($(".chosen-select"));
			// 删除项目
			this.deleteProject();
			
			this.count_down=5;
		},
		/**
		 * 删除项目
		 */
		deleteProject:function(){
			$(document).on("click","a#deleteProject",function(){
				var $this = $(this);
				layer.confirm("确认删除该项目吗?删除后无法恢复。",{icon:3},function(index){
					if($("project").length > 1){
						$this.closest("project").parent().remove();
					}else{
						layer.alert('最少保留一个项目',{title:"错误信息",icon:0});
					}
					layer.close(index);
				});
			})
		},
		/**
		 * 适合人群多选
		 * @param $select
		 */
		selectMultiple:function($select){
			$select.chosen({
				width : "369px",// style
				max_selected_options : 5 // 最多选5种
			});
		},
		/**
		 * 选择项目预约
		 */
		yuyue:function(){
			$(document).on("click","dd#yuyuedd input:radio",function(){
				var $this = $(this);
				if($this.val() == 1){
					$this.closest("dd").find("input.laydate-icon").show();
				}else{
					$this.closest("dd").find("input.laydate-icon").hide();
				}
			})
		},
		/**
		 * 预约时间插件
		 */
		layerdate:function(id){
			// 开始时间
			new laydate({
			    elem: '#'+id,
			    format: 'YYYY-MM-DD hh:mm',
			    min: laydate.now(), //设定最小日期为当前日期
			    max: '2099-06-16', //最大日期
			    istoday: false,
			    istime:true,
			    festival: true, //是否显示节日
			    choose: function(datas){}
			});
		},
		/**
		 * 监听选择照片元素
		 */
		monitorChoosePhoto:function(){
			$(document).on("click","ul#uploadUl>li>a.edit-add,a.edit-again",function(e){
				$("div#container").find("input:file").removeAttr("multiple");
				_current_choose_photo_id = $(this).closest("li").attr("id");
				_current_project_index = $(this).closest("project").attr("index");
				$("div#container").find("input:file").trigger("click");
			})
			
			// 删除图片
			$(document).on("click","ul#uploadUl a.edit-del",function(e){
				var $li = $(this).closest("li"),
					$ul = $li.parent();
				$li.remove();
				if($ul.find("li>a.edit-add").length==0){
					var str = '<li id="'+new Date().getTime()+'"><a class="edit-add"><i></i>添加照片</a></li>';
					$ul.append(str);
				}
			})
		},
		/**
		 * 添加项目
		 */
		addProject:function(){
			var _this = this;
			$("a#addProjectA").on("click",function(){
				if(_this.projectCheck()){
					
					var time = new Date().getTime(),
						str='<div class="venue-box clearfix" style="padding-top:0;position:relative;">'
						+'<project index="'+_this.projectIndex+'">'
						+'<a class="icon-close" id="deleteProject" title="删除"></a>'
						+'<div class="item-tit" style="margin-bottom:22px;">项目 '+($("project").length+1)
						+'	<span>(<em>*</em>为必填选项)</span> '
						+'</div>'
						+'<dl class="clearfix">'
						+'<dt><em>*</em>项目名称：</dt>'
						+'<dd>'
						+'<input required type="text" name="projects['+_this.projectIndex+'].psp_item_name"  placeholder="亲！最多不要超过 50 字哦！">'
						+'</dd>'
						+'</dl>'
						+'<dl class="clearfix">'
						+'<dt><em>*</em>项目价格：</dt>'
						+'<dd>'
						+'<input required type="text" name="projects['+_this.projectIndex+'].psp_item_price"  placeholder="所需的价格" style="width:200px;">'
						+'元 </dd>'
						+'</dl>'
						+'<dl class="clearfix">'
						+'<dt><em>*</em>项目打折价：</dt>'
						+'<dd>'
						+'<input required type="text" name="projects['+_this.projectIndex+'].psp_discount_price"  placeholder="所需的价格" style="width:200px;">'
						+'元 </dd>'
						+'</dl>'
						+'<dl class="clearfix">'
						+'<dt><em>*</em>适合人群：</dt>'
						+'<dd>'
						+'<select name="projects['+_this.projectIndex+'].crowds" style="width:218px;"  data-placeholder="选择适合人群" class="chosen-select" multiple  >'
						+crowds
						+'</select>'
						+'</dd>'
						+'</dl>'
						+'<dl class="clearfix">'
						+'<dt><em>*</em>支持预约：</dt>'
						+'<dd id="yuyuedd">'
						+'<label><span class="cssradio">'
						+'<input type="radio" value="1" name="projects['+_this.projectIndex+'].psp_is_yuyue">'
						+'<span></span></span>是</label>'
						+'<label><span class="cssradio">'
						+'<input type="radio" value="0" name="projects['+_this.projectIndex+'].psp_is_yuyue" checked="true">'
						+'<span></span></span>否</label>'
						+'<input class="laydate-icon" id="defaultDate'+time+'" type="text" name="projects['+_this.projectIndex+'].psp_yuyue_time" style="width:222px;display: none;" placeholder="选择预约时间" readonly="readonly" >'
						+'</dd>'
						+'</dl>'
						+'<dl class="clearfix">'
						+'<dt><em>*</em>支持退款：</dt>'
						+'<dd>'
						+'<label>'
						+'<span class="cssradio">'
						+'<input type="radio" value="0" name="projects['+_this.projectIndex+'].psp_is_refund" checked="true">'
						+'<span></span>'
						+'</span>'
						+'是'
						+'</label>'
						+'<label>'
						+'<span class="cssradio">'
						+'<input type="radio" value="1" name="projects['+_this.projectIndex+'].psp_is_refund">'
						+'<span></span>'
						+'</span>'
						+'否'
						+'</label>'
						+'</dd>'
						+'</dl>'
						+'<dl class="clearfix">'
						+'<dt><em>*</em>有效期：</dt>'
						+'<dd>'
						+'<input class="laydate-icon" id="validity_date'+time+'_start" type="text" name="projects['+_this.projectIndex+'].psp_validity_date_start" style="width:222px;" placeholder="选择生效期" readonly="readonly" >'
						+'&nbsp;--&nbsp;'
						+'<input class="laydate-icon" id="validity_date'+time+'_end" type="text" name="projects['+_this.projectIndex+'].psp_validity_date_end" style="width:222px;" placeholder="选择截止日期" readonly="readonly" >'
						+'</dd>'
						+'</dl>'
						+'<dl class="clearfix">'
						+'<dt><em>*</em>图片上传：</dt>'
						+'<dd>'
				        +'<ul id="uploadUl" class="venue-img clearfix">'
				        +'<li id="'+time+'"><a class="edit-add"><i></i>添加照片</a></li>'
				        +'</ul>'
						+'</dd>'
						+'</dl>'
						+'<dl class="clearfix">'
						+'<dt><em>*</em>项目备注：</dt>'
						+'<dd>'
						+'<textarea  maxlength="100"  name="projects['+_this.projectIndex+'].psp_item_comment" placeholder="亲！最多不超过 500 字" style="height:150px;"></textarea>'
						+'</dd>'
						+'</dl>'
						+'</project>'
						+'</div>';
					
					$("project:last").parent().after(str);
					// 多选
					_this.selectMultiple($("select.chosen-select:last"));
					
					// 日期
					setTimeout(function(){
						_this.layerdate("defaultDate"+time);
						_this.layerdate("validity_date"+time+"_start");
						_this.layerdate("validity_date"+time+"_end");
					},100);
					
					// 项目下标
					_this.projectIndex++;

				}else{
					return;
				}
			})
		},
		/**
		 * 项目验证
		 */
		projectCheck:function(){
			var check = true;
			$("project").each(function(){
				var $project = $(this);
				$project.find("input[required],textarea").each(function(){
					var $this = $(this);
					if($this.val().trim() == ""){
						layer.tips('请填写信息', $this);
						$this.focus();
						check=false;
						return false;
					}
					
				})
			})
			
			return check;
		},
		/**
		 * 步骤操作
		 */
		step:function(){
			var _this = this,
				$nextStep = $("a#nextStep"),
				$prevStep = $("a#prevStep");
			
			$nextStep.on("click",function(){
				if(_this.step == 1){
					if(_this.$form.valid()){
						_this.$step1.hide();
						_this.$step2.show();
						_this.$addProjectDIV.show();
						$prevStep.show();
						_this.step = 2;
						_this.stepClass(2);
					}
				}else if(_this.step == 2){
					if(_this.projectCheck()){
						layer.msg("提交数据中...",{icon:16,shade:0.3,time:10*1000});
						$.post("pointService/fabu.json",$("form#replyPointServiceForm").serialize(),function(data){
							if(data){
								if(data.RESPONSE_STATE == "200"){
									layer.closeAll("dialog");
									_this.$step2.hide();
									_this.$step3.show();
									_this.step = 3;
									_this.stepClass(3);
									$nextStep.hide();
									_this.$addProjectDIV.hide();
									_this.countDown();
								}else{
									layer.alert(data.ERROR_INFO,{icon:0})
								}
							}
						});
					}
				}
			});
			
			$prevStep.on("click",function(){
				if(_this.step == 2){
					_this.$step2.hide();
					_this.$step1.show();
					_this.step = 1;
					_this.stepClass(1);
					$prevStep.hide();
					_this.$addProjectDIV.hide();
				}else if(_this.step == 3){
					_this.$step3.hide();
					_this.$step2.show();
					$nextStep.show();
					_this.step = 2;
					_this.stepClass(2);
					_this.$addProjectDIV.show();
				}
			});
			
			$("ul#stepUl>li").on("click",function(){
				var step = $(this).attr("step");
				if(step == 1){
					_this.$step3.hide();
					_this.$step2.hide();
					_this.$step1.show();
					_this.step = 1;
					_this.stepClass(1);
					$prevStep.hide();
					_this.$addProjectDIV.hide();
				}else if(step == 2){
					if(_this.step == 1){
						if(_this.$form.valid()){
							_this.$step1.hide();
							_this.$step2.show();
							$prevStep.show();
							_this.step = 2;
							_this.stepClass(2);
							_this.$addProjectDIV.show();
						}
					}else if(_this.step == 3){
						_this.$step3.hide();
						_this.$step2.show();
						$nextStep.show();
						_this.step = 2;
						_this.stepClass(2);
						_this.$addProjectDIV.show();
					}
				}else if(step == 3){
					if(_this.step == 1){
						if(_this.$form.valid()){
							// 判断第二步是否通过
							if(_this.projectCheck()){
								layer.msg("提交数据中...",{icon:16,shade:0.3,time:10*1000});
								$.post("pointService/fabu.json",$("form#replyPointServiceForm").serialize(),function(data){
									if(data){
										if(data.RESPONSE_STATE == "200"){
											layer.closeAll("dialog");
											_this.$step2.hide();
											_this.$step3.show();
											_this.step = 3;
											_this.stepClass(3);
											$nextStep.hide();
											_this.$addProjectDIV.hide();
											_this.countDown();
										}else{
											layer.alert(data.ERROR_INFO,{icon:0})
										}
									}
								});
							}else{
								_this.$step1.hide();
								_this.$step2.show();
								$prevStep.show();
								_this.step = 2;
								_this.stepClass(2);
								_this.$addProjectDIV.show();
							}
						}
					}else if(_this.step == 2){
						// 判断第二步是否通过
						if(_this.projectCheck()){
							layer.msg("提交数据中...",{icon:16,shade:0.3,time:10*1000});
							$.post("pointService/fabu.json",$("form#replyPointServiceForm").serialize(),function(data){
								if(data){
									if(data.RESPONSE_STATE == "200"){
										layer.closeAll("dialog");
										_this.$step2.hide();
										_this.$step3.show();
										_this.step = 3;
										_this.stepClass(3);
										$nextStep.hide();
										_this.$addProjectDIV.hide();
										_this.countDown();
									}else{
										layer.alert(data.ERROR_INFO,{icon:0})
									}
								}
							});
						}
					}
				}
				
			})
		},
		/**
		 * 发布成功倒计时
		 */
		countDown:function(){
			var _this = this;
			$("b#count_down").text(_this.count_down);
			if(_this.count_down>0){
				_this.count_down--;
				setTimeout(function(){
					_this.countDown();
				},1*1000)
			}else{
				window.location="pointService/allProject.html";
			}
		},
		/**
		 * 步骤样式
		 */
		stepClass:function(index){
			var _this = this;
			_this.$stepUl.find("li").removeClass().eq(index-1).addClass("currented");
			$("body").animate({scrollTop:"0px"})
		},
		/**
		 * js验证
		 */
		validate:function(){
			var _this = this,
				icon = "<i></i>";
			
			_this.validateForm = _this.$form.validate({
			    	rules: {
			    		psi_pay_info:{
				    		required:true,
				    		maxlength:500
					    },
					    psi_service:{
				    		required:true,
				    		maxlength:500
					    },
					    psi_introduce:{
				    		required:true
					    }
				    },
		            messages:{
		            	psi_pay_info:{
		            		required:icon+"请您输入购买须知",
		            		maxlength:icon+"输入长度最多是 500 的字符串（汉字算一个字符）。"
					    },
					    psi_service:{
		            		required:icon+"请您输入提供的服务",
		            		maxlength:icon+"输入长度最多是 500 的字符串（汉字算一个字符）。"
					    },
					    psi_introduce:{
		            		required:icon+"请您输入场馆介绍"
					    }
		            },
				    errorElement:"span",
				    ignore:":hidden",
				    errorClass:"error-tips",
				    // 插入错误信息
				    errorPlacement: function(error, element) {
				    	$(element).closest("form").find("div[for='"+element.attr("name")+"-error']").append(error);
				    }
				});
		},
		/**
		 * 标签
		 */
		tabs:function(){
			var TAB_TEMP = '<a tab-sign="${date}" class="tag-box">${tab}<span sign="delTab"></span></a> ',
				HIDDEN_INPUT_TEMP = '<input for-tab-sign="${date}" name="tabs[${index}].psf_name" value="${tab}" type="hidden">',
				INDEX = 0;
			
			// 添加标签
			$("dd[sign=tab]").on("click","a#addTabs",function(){
				var $TABINPUT = $("input[name=tabName]");
				if($TABINPUT.val().trim() == ""){
					layer.tips('标签不能为空', $TABINPUT);
					$TABINPUT.focus();
				}else{
					var date_id = new Date().getTime(),
						comment = TAB_TEMP.replace("${tab}", $TABINPUT.val()).replace("${date}", date_id),
						hiddeninput = HIDDEN_INPUT_TEMP.replace("${index}", INDEX).replace("${tab}", $TABINPUT.val()).replace("${date}", date_id);
					$TABINPUT.before(comment+hiddeninput);
					INDEX++;
					$TABINPUT.val("");
					$TABINPUT.focus();
					
					if($("span[sign=delTab]").length==5){
						$(this).remove();
						$TABINPUT.remove();
					}
				}
			})
			
			// 删除标签
			$("dd[sign=tab]").on("click","span[sign=delTab]",function(){
				var $a = $(this).closest("a"),
					$hiddenInput = $a.parent().find("input[for-tab-sign="+$a.attr("tab-sign")+"]:hidden");
				$a.remove();
				$hiddenInput.remove();
				if($("a#addTabs").length == 0){
					var str='<input type="text" name="tabName" style="width:150px;" placeholder="输入商家标签">'
						  + '<a class="add-rel" id="addTabs" role="button"><i></i>添加标签</a>';
					$("dd[sign=tab]").append(str);
				}
			})
		}
	}
	
	new $.fn.replyPointService();
})