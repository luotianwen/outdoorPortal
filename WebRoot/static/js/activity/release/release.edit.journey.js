/**
 * 行程介绍
 */
var line_img_width=1920,line_img_height=850;
$(function(){
	// 行程功能方法
	$.fn.journey = function(option){
		var _this = this;
		$(option.target).each(function(){
			// 初始化绑定事件
			_this._init($(this));
		})
	}
	
	$.fn.journey.prototype={
		_init:function($this){
			var _this = this;
			var $form = $this.closest("form"),// form 
			$bottom = $form.find("div#get_bottom"),// 判断如果滚动超过此高度隐藏保存按钮 
			$addAddress = $this.find("i#addAddress"), // 添加地点按钮
			$dd = $addAddress.parent(),// 所有地点的父元素，[dd]
			$addressInputs = $dd.find("input"),// 获取所有的地点集合
			$addressStartInputs=$dd.find("input:first"),// 起点
			$date = $this.find("input.laydate-icon"),// 日期框
			$reset = $this.find("a#reset"),// 清空按钮
			$fr = $reset.parent(),// 置顶div
			$del = $this.find("a#deleteJourney"),// 删除当前行程
			$inputs = $this.find("input:visible"),// 所有的输入框，用作清空数据
			$textareas = $this.find("textarea"),// 所有的textarea，用作清空数据
			$addLineMap = $this.find("a#addLineMap"),// 添加该线路地图按钮
			$showLineAddressDiv =  $this.find("div[show-line-address]"),// 用作绑定未来事件（编辑已选择的坐标）
			$showLineAddressId = $showLineAddressDiv.prop("id"),// 展示该线路地图起点和终点的divID
			$imgListDiv = $this.closest("form").find("div#img_list_div"),// 图片列表
			$show_time_or_day_span=$this.find("span#show_time_or_day_span");
			
			
			// 绑定添加地点事件
			$addAddress.on("click",function(){
				// 将所有的占位符都改为【经过地点】
				$addressInputs.prop("placeholder","经过地点");
				// 将第一个的input占位符改为起点
				$addressInputs.eq(0).prop("placeholder","起点");
				// 添加新的元素
				var str = ' <label class="input-box">- <input name="didian['+$addressInputs.length+'].la_address_name" type="text" class="wid02"  placeholder="终点" /><i class="input-icon-del"></i></label>';
				$(this).before(str);
				
				// 添加新数据后，重新实例化input集合对象
				$addressInputs = $dd.find("input");
			})
			
			// 给未来元素绑定删除事件
			$dd.on("click","i.input-icon-del",function(){
				$(this).parent().remove();
				
				// 删除数据后，重新实例化input集合对象
				$addressInputs = $dd.find("input");
			})
			
			// 初始化日期组件(避免动态添加html代码造成实例化日期对象失败问题)
			setTimeout(function(){
				
				var format=$date.length==1?'YYYY-MM-DD':'YYYY-MM-DD hh:mm',
					istime=$date.length==1?false:true;
				var startDate={
				    elem: '#'+$date.eq(0).prop("id"),
				    event: 'focus',
				    format: format,
				    istime:istime,
				    min: laydate.now(),
				    max: '2099-06-16',
				    istoday: false,
				    festival: true, //是否显示节日
				};
				
				if($date.length>1){
					
					startDate.choose=function(time){
						endDate.min=time;
						
						var start = $date.eq(0).val(),end = $date.eq(1).val();
						if(start != "" &&  end != ""){
							$show_time_or_day_span.html(start+"&nbsp;-&nbsp;"+end);
						}
						
					}
					
					var endDate={
					    elem: '#'+$date.eq(1).prop("id"),
					    event: 'focus',
					    format: format,
					    istime:istime,
					    min: laydate.now(),
					    max: '2099-06-16',
					    istoday: false,
					    festival: true, //是否显示节日
					    choose:function(time){
					    	startDate.max=time;
							
							var start = $date.eq(0).val(),end = $date.eq(1).val();
							if(start != "" &&  end != ""){
								$show_time_or_day_span.html(start+"&nbsp;-&nbsp;"+end);
							}
					    }
					}
					laydate(endDate);
				}
				
				laydate(startDate);
			},100)
			
			// 清空
			$reset.on("click",function(){
				layer.confirm("是否清空这一天行程内容？",{
					icon:3,
					btn:['清空','取消'],
				},function(index){
					layer.close(index);
					
					// 清空input表单已填写的数据
					$inputs.val("");
					$textareas.val("");
					$addressInputs.val("");
					
				});
			})
			
			// 选择当前线路地图
			$addLineMap.on("click",function(){
				layer.open({
					type : 2,
					area : [ '80%', '90%' ],
					title : ['选择活动线路'],
					shade : 0.5,
					fix : true,
					shift : 0,
					maxmin : false,
					closeBtn : 1,
					content : 'huodong/chooseActiveLine.html?showLineAddressId='+$showLineAddressId
				});
			})
			
			
			// 绑定未来事件（编辑已选择的坐标）
			$showLineAddressDiv.on("click","a#editAlreadyMapLine",function(){
				layer.open({
					type : 2,
					area : [ '80%' , '90%' ],
					title : ['选择活动线路'],
					shade : 0.5,
					fix : true,
					shift : 0,
					maxmin : false,
					closeBtn : 1,
					content : 'huodong/chooseActiveLine.html?showLineAddressId='+$showLineAddressId
					 +"&start="+$(this).attr("start")
					 +"&end="+$(this).attr("end")
				});
				
			})
			
			
			// 绑定未来事件（对每张图片加点击切换图片事件）
			$imgListDiv.on("click","li[li-type!=add-img]",function(e){
				
				var $this = $(this),// 当前对象
				$liImg = $this.children("img"),// 当前li的img元素
				$routeImg = $this.closest("div#route_img"),// 背景元素
				$imgTxt = $routeImg.find("div#img_txt"),// 描述集合外层DIV
				$index = $this.attr("index"),// 当前元素对应的下标
				$textareas = $imgTxt.find("textarea");// 描述集合
				
				
				// 点击li切换该图片及对应的描述信息
				$routeImg.css({"background":"url("+$liImg.prop("src")+") no-repeat center top","background-size": "cover"});
				
				// 隐藏所有的描述框
				$textareas.hide();
				
				// 展示当前li图片对应的描述
				$imgTxt.find("textarea[index="+$index+"]").show();
				
			})
			
			// 编辑该图片
			$imgListDiv.on("click","a#edit_img_a",function(){
				// 绑定上传图片功能
				var $li = $(this).closest("li"),
				$file = $li.find("input[upload-img]"),
				$id=$file.prop("id"),
				$routeImg = $li.closest("div#route_img"),
				$liLocalUrl= $li.find("input#li_local_url"),
				$img = $li.find("img#img_slt");
				
				  new $.fn.dragImg({
				  	jq:$file,
				  	width:2.26,
				  	height:1,
				  	fn:function(data){
						$img.prop("src",data.savePath);
						$liLocalUrl.val(data.savePath);
						$routeImg.css({"background":"url("+data.savePath+") no-repeat center top","background-size": "cover"});
					}
				  });
				
				// 触发上传图片事件
				$file.trigger("click");
				
			})
			
			// 删除该图片
			$imgListDiv.on("click","a#del_img_a",function(){
				var $li = $(this).closest("li"),
				$ul = $li.parent(),
				$routeImg = $li.closest("div#route_img"),
				$imgTxt = $routeImg.children("div#img_txt"),
				time=new Date().getTime();
				layer.confirm("确认删除该图片及对应的描述？",{icon:3,shade:0.5,btn:['删除','取消']},function(index){
					layer.close(index);
					// 判断是否为最后一张图片
					if($ul.find("li[li-type!=add-img]").length == 1){
						layer.msg("最少保留一张图片！",{icon:6});
					}else{
						$imgTxt.find("textarea[index="+$li.attr('index')+"]").remove();
						$li.remove();
						// 默认删除后显示前一张的图片
						$routeImg.css({"background":"url("+$ul.find("li[li-type!=add-img]:first img#img_slt").prop("src")+") no-repeat center top","background-size": "cover"});
						$imgTxt.find("textarea[index="+$ul.find("li[li-type!=add-img]:first").attr("index")+"]").show();
						
						
						layer.alert("请点击修改，保存最新的图片",{icon:6,title:"修改提醒"});
						
						// 如果少于5张支持继续上传，多余五张不可再进行上传
						if($ul.find("li[li-type!=add-img]").length<5 && $ul.find("li[li-type=add-img]").length == 0){
							var str='<li li-type="add-img">'
								+'<a href="javascript:void(0)" class="btn-add-img" >'
								+'<input type="file" name="file" id="'+time+'_imgs" />'
								+'<img src="static/images/hw_img/img-add.png" width="200" height="150" />'
								+'</a>'
								+'</li>';
							$ul.append(str);
						}else{
							return;
						}
						  
						  // 线路上传
						  new $.fn.dragImg({
						  	jq:$("input#"+time+"_imgs"),
						  	width:2.26,
						  	height:1,
						  	fn:function(data){
								var $obj = $("input#"+time+"_imgs"),
								$id=$obj.prop("id"),
								$routeImg = $obj.closest("div#route_img"),
								$imgUl = $obj.closest("ul"),
								$imgIndex="",
								$imgNumber=$imgUl.find("li[li-type!=add-img]").length,
								$imgTxt=$obj.closest("div#add_img_box").next(),
								$divTxt = $imgTxt.children(),
								$textareas=$divTxt.children(),
								times = new Date().getTime();
								
								// 判断当前图片下标
								if($imgUl.find("li[li-type!=add-img]").length == 0){
									$imgIndex=0;
								}else{
									$imgIndex=parseInt($imgUl.find("li[li-type!=add-img]:last").attr("index"))+1;
								}
								
								// 替换背景图片
								$routeImg.css({"background":"url("+data.savePath+") no-repeat center top","background-size": "cover"});
								
								// 生成新的缩略图
								var str = '<li index="'+$imgIndex+'">'
									+'<div class="edit-img-box">'
									+'<a href="javascript:void(0)" id="edit_img_a" >编辑</a>'
									+'<input type="file" upload-img name="file" id="'+times+'_edit_img" style="display:none;" />'
									+'<a href="javascript:void(0)" id="del_img_a" >删除</a>'
									+'</div>'
									+'<img src="'+data.savePath+'" id="img_slt" width="200" height="150" />'
									+'<input id="li_local_url" type="hidden" name="images['+$imgIndex+'].li_local_url" value="'+data.savePath+'" />'
									+'</li>';
								// 添加缩略图
								$("#"+$id).closest("li").before(str);
								
								// 生成对该图片的描述
								$textareas.hide();
								var textareas = '<textarea  index="'+$imgIndex+'" name="images['+$imgIndex+'].li_description" maxlength="200" placeholder="亲！添加您对这张照片当时的美好记忆！"></textarea>';
								$divTxt.append(textareas);
								
								// 目前最多支持五张图片
								if($imgNumber >= 4){
									$imgUl.find("li[li-type=add-img]").remove();
								}
							
						  	}
						  });
					}
					
				});
			})
			
			_this._showBtn($show_time_or_day_span,$bottom,$fr);
			// 绑定删除事件
			_this._del($del,$form);
			
		},
		_showBtn:function($show,$bottom,$fr){
			$(window).on("scroll",function(){
				var $this = $(this);
				$("a#reset").each(function(){
					var _this = $(this);
					var img = _this.closest("form").find("div#img_list_div");
					if(img.css("display")=="none"){
						img = $("#add_new_line_div");
					}
					
					if($this.scrollTop() >= _this.parent().parent().offset().top && $this.scrollTop() <= img.offset().top){
						_this.parent().addClass("suspension");
					}else{
						_this.parent().removeClass("suspension");
					}
				})
			});
		},
		_del:function($del,$form){
			var _this = this;
			$del.on("click",function(){
				layer.confirm("确认删除当前行程吗？",{icon:3},function(l_index){
					var $lineId = $form.find("input[name=l_id]:hidden");
					if($lineId.length != 0){
						_this._post($lineId.val(),$form);
					}else{
						$form.remove();
						_this._success();
					}
					
					layer.close(l_index);
				})
			})
		},
		_post:function(id,$form){
			var _this = this;
			layer.msg("保存中...",{icon:16,time:20*1000,shade:0.5})
			$.post("lines/del.json",{id:id},function(data){
				if(data.RESPONSE_STATE == "200"){
					_this._finish();
					$form.remove();
					_this._success();
				}else if(data.RESPONSE_STATE == "500"){
					_this._error(data);
				}
			})
		},
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
		//成功处理
		_success:function(){
			if($("div#journey_div").length>1){
				var bool = true;
				var count = 1;
				$("div#journey_div").each(function(index){
					if(bool){
						if(index==0){
							if(!$(this).find("span.current").attr("type")=="day"){
								bool = false;
							}
						}else{
							var span = $(this).find("span#show_time_or_day_span");
							span.html("第"+count+"天");
							count++;
						}
					}
				})
			}else{
				$("#add_new_line").trigger("click");
			}
		},
		// 错误处理
		_error:function(data){
			layer.closeAll('dialog'); //关闭信息框
			swal("保存失败!", "错误信息："+data.ERROR_INFO, "error");
		}
	}
	
	// 实例化行程对象
	new $.fn.journey({
		target:"div#journey_div"
	})


	// 增加新的一天
	$("a#add_new_line").on("click",function(){
		var $this = $(this);
		// 判断按照顺序来添加
		var lines = $("form[action='huodong/insertLine.do']");
		lines.each(function(l_index){
			if($(this).find("input[name=l_id]:hidden").length == 0){
				layer.alert("请按照顺序保存线路信息!",{icon:6,shade:0.6,shadeClose:true})
				$("body,html").animate({scrollTop:$(this).offset().top});
				return false;
			}
			
			if(l_index == lines.length-1){
				var time = new Date().getTime(),
					index=$("div.journey").length,
					journeyType=$("input[name=journey_type]:hidden:eq(0)").val();
				
				var str = '<form action="huodong/insertLine.do" >'
				+'<input name="journey_type" value="'+journeyType+'" type="hidden" />'
				+'<div class="detail-info no-margin clearfix journey" id="'+time+'_journey">'
				+'<!--add area begin -->'
				+'<input name="handletype" type="hidden" value="line" />'
				+'<div class="container">'
				+'<div class="add-content-box">'
				+'<div class="mt">'
				+'<div class="fl mleft">'
				+'<span class="txt" id="show_time_or_day_span">第'+index+'天</span>'
				+'</div>'
				+'<div class="fr">'
				+'<a href="javascript:void(0)" id="reset" class="btn-box">清空</a>'
				+'<a href="javascript:void(0)" id="saveActiveInfo" class="btn-box" >保存</a>'
				+'<a href="javascript:void(0)" id="deleteJourney" class="btn-box" >删除</a>'
				+'</div>'
				+'</div>'
				+'<div class="mc">'
				+'<div class="day-txt">'
				+'<dl>'
				+'<dt>地　　点：</dt>'
				+'<dd>'
				+'<label class="input-box"><input name="didian[0].la_address_name" type="text" class="wid02" placeholder="起点"/></label> -'
				+'<label class="input-box"><input name="didian[1].la_address_name" type="text" class="wid02" placeholder="终点"/></label>'
				+'<i id="addAddress" class="input-icon-add"></i>'
				+'</dd>'
				+'</dl>'
				+'<dl class="dl-w01">'
				+'<dt>出行方式：</dt>'
				+'<dd>'
				+'<input name="l_vehicle" type="text" class="journey-wid" placeholder="出行方式"/>'
				+'</dd>'
				+'</dl>'
				+'<dl class="dl-w01">'
				+'<dt>日　　期：</dt>'
				+'<dd>';
				
				if(journeyType == "hour"){
					str+='<input id="layer_'+time+'_date" name="l_time_hour"  time-day-hour type="text" class="laydate-icon"  placeholder="日期"/>'
						+'&nbsp;-&nbsp;'
						+'<input id="layer_'+time+'_date_to" name="l_to_time" type="text" class="laydate-icon"  placeholder="日期"/>';
				}else{
					str+='<input id="layer_'+time+'_date" name="l_time_day"  time-day-hour type="text" class="laydate-icon"  placeholder="日期"/>';
				}
				
				str += '</dd>'
				+'</dl>'
				+'<dl class="dl-w01">'
				+'<dt>餐　　饮：</dt>'
				+'<dd>'
				+'<input name="l_diet" type="text" class="journey-wid" placeholder="餐饮"/>'
				+'</dd>'
				+'</dl>'
				+'<dl class="dl-w01">'
				+'<dt>住　　宿：</dt>'
				+'<dd>'
				+'<input name="l_accommodation" type="text" class="journey-wid" placeholder="住宿" />'
				+'</dd>'
				+'</dl>'
				+'<dl>'
				+'<dt>活动介绍：</dt>'
				+'<dd style="position: relative;">'
				+'<textarea name="l_active_description" maxlength="2000" class="wid08"  placeholder="活动介绍"></textarea>'
				+'<div style="position: absolute;right: 24px;bottom: 13px;" show-textarea-num>剩余<span style="color: red;" id="show_textarea_num">2000</span>个字</div>'
				+'</dd>'
				+'</dl>'
				+'<dl>'
				+'<dt>温馨提示：</dt>'
				+'<dd style="position: relative;">'
				+'<textarea name="l_prompt" maxlength="2000" class="wid08" placeholder="温馨提示"></textarea>'
				+'<div style="position: absolute;right: 24px;bottom: 13px;" show-textarea-num>剩余<span style="color: red;" id="show_textarea_num">2000</span>个字</div>'
				+'</dd>'
				+'</dl>'
				+'</div>'
				+'</div>'
				+'</div>'
				+'</div>'
				+'<!--add area end -->'
				+'<!--add area begin -->'
				+'<div class="container" style="margin-top:20px;">'
				+'<div class="add-content-box">'
				+'<div class="mt">'
				+'<div class="mleft" >'
				+'<a href="javascript:void(0)" class="add" id="addLineMap">'
				+'<i class="iconadd"></i>增加第'+index+'天的路线'
				+'</a>'
				+'</div>'
				+'<div class="mleft route-all" show-line-address id="'+time+'_line" style="display: none;">'
				+'<span>线路：北京——海南</span>'
				+'<input type="hidden" name="l_line_coordinate" />'
				+'<a href="javascript:void(0)" class="btn-edit-box" id="editAlreadyMapLine">编辑</a>'
				+'</div>'
				+'</div>'
				+'</div>'
				+'</div>'
				+'<!--add area end -->'
				+'</div>'
				+'<div class="route-img" id="route_img">'
				+'<div class="add-img-box" id="add_img_box">'
				+'<div class="set-main-img" id="set_main_img">'
				+'<div class="set-box01">'
				+'<a href="javascript:void(0)">设置该路段的图片</a>'
				+'<p>图片建议选择尺寸大于1680px的高清大图，如相机原图</p>'
				+'</div>'
				+'<div class="set-box02">'
				+'<ul>'
				+'<li>'
				+'<a href="javascript:void(0)" class="file">'
				+'设置线路的主图'
				+'<input type="file" name="file" id="'+time+'_img" />'
				+'</a>'
				+'</li>'
				+'</ul>'
				+'</div>'
				+'</div>'
				+'<div id="img_list_div" class="img-list" style="display: none;">'
				+'<ul>'
				+'<li li-type="add-img">'
				+'<a href="javascript:void(0)" class="btn-add-img" >'
				+'<input type="file" name="file" id="'+time+'_imgs"  />'
				+'<img src="static/images/hw_img/img-add.png" width="200" height="150" />'
				+'</a>'
				+'</li>'
				+'</ul>'
				+'</div>'
				+'</div>'
				+'<div class="img-txt" id="img_txt" style="display: none;" >'
				+'<div class="txt">'
				+'<textarea  index="0" name="images[0].li_description" maxlength="200" placeholder="亲！添加您对这张照片当时的美好记忆！" ></textarea>'
				+'</div>'
				+'</div>'
				+'</div>'
				+'<div id="get_bottom"></div>'
				+'</form>';

				// 添加新的
				$this.closest("div#add_new_line_div").before(str);

				// textarea长度提示
				textareaNum();
				
				// 页面视野调至该新的一天
				$("body,html").animate({scrollTop:$("div#"+time+"_journey").offset().top})
				
				// 初始化对象
				new $.fn.journey({
					target:"div#"+time+"_journey"
				})
				
				  // 线路主图
				  new $.fn.dragImg({
				  	jq:$("input#"+time+"_img"),
				  	width:2.26,
				  	height:1,
				  	fn:function(data){
				  		var $img = $("input#"+time+"_img");
						$id=$img.prop("id"),
						$routeImg = $img.closest("div#route_img"),
						$setMainImg = $img.closest("div#set_main_img"),
						$imgList = $setMainImg.next(),
						$imgIndex=$img.closest("ul>li").length,
						$imgTxt=$img.closest("div#add_img_box").next(),
						times = new Date().getTime();
				  		
						// 生成新的缩略图
						var str = '<li index="0">'
							+'<div class="edit-img-box">'
							+'<a href="javascript:void(0)" id="edit_img_a" >编辑</a>'
							+'<input type="file" upload-img name="file" id="'+times+'_edit_img" style="display:none;" />'
							+'<a href="javascript:void(0)" id="del_img_a" >删除</a>'
							+'</div>'
							+'<img src="'+data.savePath+'" id="img_slt" width="200" height="150" />'
							+'<input id="li_local_url" type="hidden" name="images[0].li_local_url" value="'+data.savePath+'" />'
							+'</li>';
				  		
				  		$routeImg.css({"background":"url("+data.savePath+") no-repeat center top","background-size": "cover"});
							
						// 添加缩略图
						$setMainImg.next().find("ul").prepend(str);
						
						// 展示该图片的描述框
						$imgTxt.show();
						
						// 添加附图
						$imgList.show();
						
						// 删除增加主图入口
						$setMainImg.remove();
				  	}
				  });
					
					
					
				// 线路附图
				  new $.fn.dragImg({
				  	jq:$("input#"+time+"_imgs"),
				  	width:2.26,
				  	height:1,
				  	fn:function(data){
						var $obj = $("input#"+time+"_imgs"),
						$id=$obj.prop("id"),
						$routeImg = $obj.closest("div#route_img"),
						$imgUl = $obj.closest("ul"),
						$imgIndex="",
						$imgNumber=$imgUl.find("li[li-type!=add-img]").length,
						$imgTxt=$obj.closest("div#add_img_box").next(),
						$divTxt = $imgTxt.children(),
						$textareas=$divTxt.children(),
						times = new Date().getTime();
						
						// 判断当前图片下标
						if($imgUl.find("li[li-type!=add-img]").length == 0){
							$imgIndex=0;
						}else{
							$imgIndex=parseInt($imgUl.find("li[li-type!=add-img]:last").attr("index"))+1;
						}
						
						// 替换背景图片
						$routeImg.css({"background":"url("+data.savePath+") no-repeat center top","background-size": "cover"});
						
						// 生成新的缩略图
						var str = '<li index="'+$imgIndex+'">'
							+'<div class="edit-img-box">'
							+'<a href="javascript:void(0)" id="edit_img_a" >编辑</a>'
							+'<input type="file" upload-img name="file" id="'+times+'_edit_img" style="display:none;" />'
							+'<a href="javascript:void(0)" id="del_img_a" >删除</a>'
							+'</div>'
							+'<img src="'+data.savePath+'" id="img_slt" width="200" height="150" />'
							+'<input id="li_local_url" type="hidden" name="images['+$imgIndex+'].li_local_url" value="'+data.savePath+'" />'
							+'</li>';
						// 添加缩略图
						$("#"+$id).closest("li").before(str);
						
						// 生成对该图片的描述
						$textareas.hide();
						var textareas = '<textarea  index="'+$imgIndex+'" name="images['+$imgIndex+'].li_description" maxlength="200" placeholder="亲！添加您对这张照片当时的美好记忆！"></textarea>';
						$divTxt.append(textareas);
						
						// 目前最多支持五张图片
						if($imgNumber >= 4){
							$imgUl.find("li[li-type=add-img]").remove();
						}
					
				  	}
				  });
				
			}
		})
		
		if(lines.length==0){
			var time = new Date().getTime(),
				index=1,
				journeyType=$("div#journey_div").eq(0).find("span.current").attr("type");
			
			var str = '<form action="huodong/insertLine.do" >'
			+'<input name="journey_type" value="'+journeyType+'" type="hidden" />'
			+'<div class="detail-info no-margin clearfix journey" id="'+time+'_journey">'
			+'<!--add area begin -->'
			+'<input name="handletype" type="hidden" value="line" />'
			+'<div class="container">'
			+'<div class="add-content-box">'
			+'<div class="mt">'
			+'<div class="fl mleft">'
			+'<span class="txt" id="show_time_or_day_span">';

			if(journeyType == "day"){
				str += '第'+index+'天';
			}
			
			str += '</span>'
			+'</div>'
			+'<div class="fr">'
			+'<a href="javascript:void(0)" id="reset" class="btn-box">清空</a>'
			+'<a href="javascript:void(0)" id="saveActiveInfo" class="btn-box" >保存</a>'
			+'<a href="javascript:void(0)" id="deleteJourney" class="btn-box" >删除</a>'
			+'</div>'
			+'</div>'
			+'<div class="mc">'
			+'<div class="day-txt">'
			+'<dl>'
			+'<dt>地　　点：</dt>'
			+'<dd>'
			+'<label class="input-box"><input name="didian[0].la_address_name" type="text" class="wid02" placeholder="起点"/></label> -'
			+'<label class="input-box"><input name="didian[1].la_address_name" type="text" class="wid02" placeholder="终点"/></label>'
			+'<i id="addAddress" class="input-icon-add"></i>'
			+'</dd>'
			+'</dl>'
			+'<dl class="dl-w01">'
			+'<dt>出行方式：</dt>'
			+'<dd>'
			+'<input name="l_vehicle" type="text" class="journey-wid" placeholder="出行方式"/>'
			+'</dd>'
			+'</dl>'
			+'<dl class="dl-w01">'
			+'<dt>日　　期：</dt>'
			+'<dd>';
			
			if(journeyType == "hour"){
				str+='<input id="layer_'+time+'_date" name="l_time_hour"  time-day-hour type="text" class="laydate-icon"  placeholder="日期"/>'
					+'&nbsp;-&nbsp;'
					+'<input id="layer_'+time+'_date_to" name="l_to_time" type="text" class="laydate-icon"  placeholder="日期"/>';
			}else{
				str+='<input id="layer_'+time+'_date" name="l_time_day"  time-day-hour type="text" class="laydate-icon"  placeholder="日期"/>';
			}
			
			str += '</dd>'
			+'</dl>'
			+'<dl class="dl-w01">'
			+'<dt>餐　　饮：</dt>'
			+'<dd>'
			+'<input name="l_diet" type="text" class="journey-wid" placeholder="餐饮"/>'
			+'</dd>'
			+'</dl>'
			+'<dl class="dl-w01">'
			+'<dt>住　　宿：</dt>'
			+'<dd>'
			+'<input name="l_accommodation" type="text" class="journey-wid" placeholder="住宿" />'
			+'</dd>'
			+'</dl>'
			+'<dl>'
			+'<dt>活动介绍：</dt>'
			+'<dd style="position: relative;">'
			+'<textarea name="l_active_description" maxlength="2000" class="wid08"  placeholder="活动介绍"></textarea>'
			+'<div style="position: absolute;right: 24px;bottom: 13px;" show-textarea-num>剩余<span style="color: red;" id="show_textarea_num">2000</span>个字</div>'
			+'</dd>'
			+'</dl>'
			+'<dl>'
			+'<dt>温馨提示：</dt>'
			+'<dd style="position: relative;">'
			+'<textarea name="l_prompt" maxlength="2000" class="wid08" placeholder="温馨提示"></textarea>'
			+'<div style="position: absolute;right: 24px;bottom: 13px;" show-textarea-num>剩余<span style="color: red;" id="show_textarea_num">2000</span>个字</div>'
			+'</dd>'
			+'</dl>'
			+'</div>'
			+'</div>'
			+'</div>'
			+'</div>'
			+'<!--add area end -->'
			+'<!--add area begin -->'
			+'<div class="container" style="margin-top:20px;">'
			+'<div class="add-content-box">'
			+'<div class="mt">'
			+'<div class="mleft" >'
			+'<a href="javascript:void(0)" class="add" id="addLineMap">'
			+'<i class="iconadd"></i>增加第'+index+'天的路线'
			+'</a>'
			+'</div>'
			+'<div class="mleft route-all" show-line-address id="'+time+'_line" style="display: none;">'
			+'<span>线路：北京——海南</span>'
			+'<input type="hidden" name="l_line_coordinate" />'
			+'<a href="javascript:void(0)" class="btn-edit-box" id="editAlreadyMapLine">编辑</a>'
			+'</div>'
			+'</div>'
			+'</div>'
			+'</div>'
			+'<!--add area end -->'
			+'</div>'
			+'<div class="route-img" id="route_img">'
			+'<div class="add-img-box" id="add_img_box">'
			+'<div class="set-main-img" id="set_main_img">'
			+'<div class="set-box01">'
			+'<a href="javascript:void(0)">设置该路段的图片</a>'
			+'<p>图片建议选择尺寸大于1680px的高清大图，如相机原图</p>'
			+'</div>'
			+'<div class="set-box02">'
			+'<ul>'
			+'<li>'
			+'<a href="javascript:void(0)" class="file">'
			+'设置线路的主图'
			+'<input type="file" name="file" id="'+time+'_img" />'
			+'</a>'
			+'</li>'
			+'</ul>'
			+'</div>'
			+'</div>'
			+'<div id="img_list_div" class="img-list" style="display: none;">'
			+'<ul>'
			+'<li li-type="add-img">'
			+'<a href="javascript:void(0)" class="btn-add-img" >'
			+'<input type="file" name="file" id="'+time+'_imgs"  />'
			+'<img src="static/images/hw_img/img-add.png" width="200" height="150" />'
			+'</a>'
			+'</li>'
			+'</ul>'
			+'</div>'
			+'</div>'
			+'<div class="img-txt" id="img_txt" style="display: none;" >'
			+'<div class="txt">'
			+'<textarea  index="0" name="images[0].li_description" maxlength="200" placeholder="亲！添加您对这张照片当时的美好记忆！" ></textarea>'
			+'</div>'
			+'</div>'
			+'</div>'
			+'<div id="get_bottom"></div>'
			+'</form>';

			// 添加新的
			$this.closest("div#add_new_line_div").before(str);

			// textarea长度提示
			textareaNum();
			
			// 页面视野调至该新的一天
			$("body,html").animate({scrollTop:$("div#"+time+"_journey").offset().top})
			
			// 初始化对象
			new $.fn.journey({
				target:"div#"+time+"_journey"
			})
			
			  // 线路主图
			  new $.fn.dragImg({
			  	jq:$("input#"+time+"_img"),
			  	width:2.26,
			  	height:1,
			  	fn:function(data){
			  		var $img = $("input#"+time+"_img");
					$id=$img.prop("id"),
					$routeImg = $img.closest("div#route_img"),
					$setMainImg = $img.closest("div#set_main_img"),
					$imgList = $setMainImg.next(),
					$imgIndex=$img.closest("ul>li").length,
					$imgTxt=$img.closest("div#add_img_box").next(),
					times = new Date().getTime();
			  		
					// 生成新的缩略图
					var str = '<li index="0">'
						+'<div class="edit-img-box">'
						+'<a href="javascript:void(0)" id="edit_img_a" >编辑</a>'
						+'<input type="file" upload-img name="file" id="'+times+'_edit_img" style="display:none;" />'
						+'<a href="javascript:void(0)" id="del_img_a" >删除</a>'
						+'</div>'
						+'<img src="'+data.savePath+'" id="img_slt" width="200" height="150" />'
						+'<input id="li_local_url" type="hidden" name="images[0].li_local_url" value="'+data.savePath+'" />'
						+'</li>';
			  		
			  		$routeImg.css({"background":"url("+data.savePath+") no-repeat center top","background-size": "cover"});
						
					// 添加缩略图
					$setMainImg.next().find("ul").prepend(str);
					
					// 展示该图片的描述框
					$imgTxt.show();
					
					// 添加附图
					$imgList.show();
					
					// 删除增加主图入口
					$setMainImg.remove();
			  	}
			  });
				
				
				
			// 线路附图
			  new $.fn.dragImg({
			  	jq:$("input#"+time+"_imgs"),
			  	width:2.26,
			  	height:1,
			  	fn:function(data){
					var $obj = $("input#"+time+"_imgs"),
					$id=$obj.prop("id"),
					$routeImg = $obj.closest("div#route_img"),
					$imgUl = $obj.closest("ul"),
					$imgIndex="",
					$imgNumber=$imgUl.find("li[li-type!=add-img]").length,
					$imgTxt=$obj.closest("div#add_img_box").next(),
					$divTxt = $imgTxt.children(),
					$textareas=$divTxt.children(),
					times = new Date().getTime();
					
					// 判断当前图片下标
					if($imgUl.find("li[li-type!=add-img]").length == 0){
						$imgIndex=0;
					}else{
						$imgIndex=parseInt($imgUl.find("li[li-type!=add-img]:last").attr("index"))+1;
					}
					
					// 替换背景图片
					$routeImg.css({"background":"url("+data.savePath+") no-repeat center top","background-size": "cover"});
					
					// 生成新的缩略图
					var str = '<li index="'+$imgIndex+'">'
						+'<div class="edit-img-box">'
						+'<a href="javascript:void(0)" id="edit_img_a" >编辑</a>'
						+'<input type="file" upload-img name="file" id="'+times+'_edit_img" style="display:none;" />'
						+'<a href="javascript:void(0)" id="del_img_a" >删除</a>'
						+'</div>'
						+'<img src="'+data.savePath+'" id="img_slt" width="200" height="150" />'
						+'<input id="li_local_url" type="hidden" name="images['+$imgIndex+'].li_local_url" value="'+data.savePath+'" />'
						+'</li>';
					// 添加缩略图
					$("#"+$id).closest("li").before(str);
					
					// 生成对该图片的描述
					$textareas.hide();
					var textareas = '<textarea  index="'+$imgIndex+'" name="images['+$imgIndex+'].li_description" maxlength="200" placeholder="亲！添加您对这张照片当时的美好记忆！"></textarea>';
					$divTxt.append(textareas);
					
					// 目前最多支持五张图片
					if($imgNumber >= 4){
						$imgUl.find("li[li-type=add-img]").remove();
					}
				
			  	}
			  });
			
		
		}
		
	})
})



/**
 * 上传线路主图
 */
function uploadLineImage(obj){
	var $id=$(obj).prop("id"),
	$routeImg = $(obj).closest("div#route_img"),
	$setMainImg = $(obj).closest("div#set_main_img"),
	$imgList = $setMainImg.next(),
	$imgIndex=$(obj).closest("ul>li").length,
	$imgTxt=$(obj).closest("div#add_img_box").next(),
	time = new Date().getTime();
	
	fileUpload({
		id:$id,
		width:line_img_width,
		height:line_img_height,
		success:function(data){
			// 替换背景图片
			$routeImg.css({"background":"url("+data.savePath+") no-repeat center top","background-size": "cover"});
			
			// 生成新的缩略图
			var str = '<li index="0">'
				+'<div class="edit-img-box">'
				+'<a href="javascript:void(0)" id="edit_img_a" >编辑</a>'
				+'<input type="file" upload-img name="file" id="'+time+'_edit_img" style="display:none;" />'
				+'<a href="javascript:void(0)" id="del_img_a" >删除</a>'
				+'</div>'
				+'<img src="'+data.savePath+'" id="img_slt" width="200" height="150" />'
				+'<input id="li_local_url" type="hidden" name="images[0].li_local_url" value="'+data.savePath+'" />'
				+'</li>';
			
			// 添加缩略图
			$setMainImg.next().find("ul").prepend(str);
			
			// 展示该图片的描述框
			$imgTxt.show();
			
			// 添加附图
			$imgList.show();
			
			// 删除增加主图入口
			$setMainImg.remove();
		}
	});
}



/**
 * 上传线路附图
 */
function uploadLineImages(obj){
	var $id=$(obj).prop("id"),
	$routeImg = $(obj).closest("div#route_img"),
	$imgUl = $(obj).closest("ul"),
	$imgIndex="",
	$imgNumber=$imgUl.find("li[li-type!=add-img]").length,
	$imgTxt=$(obj).closest("div#add_img_box").next(),
	$divTxt = $imgTxt.children(),
	$textareas=$divTxt.children(),
	time = new Date().getTime();
	
	// 判断当前图片下标
	if($imgUl.find("li[li-type!=add-img]").length == 0){
		$imgIndex=0;
	}else{
		$imgIndex=parseInt($imgUl.find("li[li-type!=add-img]:last").attr("index"))+1;
	}
	
	fileUpload({
		id:$id,
		width:line_img_width,
		height:line_img_height,
		success:function(data){
			// 替换背景图片
			$routeImg.css({"background":"url("+data.savePath+") no-repeat center top","background-size": "cover"});
			
			// 生成新的缩略图
			var str = '<li index="'+$imgIndex+'">'
				+'<div class="edit-img-box">'
				+'<a href="javascript:void(0)" id="edit_img_a" >编辑</a>'
				+'<input type="file" upload-img name="file" id="'+time+'_edit_img" style="display:none;" />'
				+'<a href="javascript:void(0)" id="del_img_a" >删除</a>'
				+'</div>'
				+'<img src="'+data.savePath+'" id="img_slt" width="200" height="150" />'
				+'<input id="li_local_url" type="hidden" name="images['+$imgIndex+'].li_local_url" value="'+data.savePath+'" />'
				+'</li>';
			// 添加缩略图
			$("#"+$id).closest("li").before(str);
			
			// 生成对该图片的描述
			$textareas.hide();
			var textareas = '<textarea  index="'+$imgIndex+'" name="images['+$imgIndex+'].li_description" maxlength="200" placeholder="亲！添加您对这张照片当时的美好记忆！"></textarea>';
			$divTxt.append(textareas);
			
			// 目前最多支持五张图片
			if($imgNumber >= 4){
				$imgUl.find("li[li-type=add-img]").remove();
			}
		}
	});
}