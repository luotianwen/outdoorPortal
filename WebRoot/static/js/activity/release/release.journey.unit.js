$(function(){
	$.fn.junit=function(option){
		this.op = option;
		this.init();
	}
	
	$.fn.junit.prototype={
		// 初始化 
		init:function(){
			var _this = this;
			
			// 绑定切换天和时间事件
			_this.bind();
		},
		// 绑定事件
		bind:function(){
			var _this = this;
			
			// 绑定样式
			_this.bindCss();
		},
		// 绑定样式
		bindCss:function(){
			var _this = this;
			$(_this.op.target+" span").on("click",function(){
				if($(this).hasClass("current")){
					return;
				}
				
				// 修改默认类型
				_this.op.type = $(this).attr("type");
				
				// 保存每个表单的线路类型
				$("input[name=journey_type]:hidden").val(_this.op.type);
				
				// 修改样式
				$(_this.op.target).find("span").removeClass("current");
				$(_this.op.target).find("span[type="+_this.op.type+"]").addClass("current");

				// 根据选择的不同行程类型，实例化不同类型的日期插件
				_this.bindFn();
				
				// 操作显示天/小时
				_this.showTimeOrDay();
				
			})
		},
		// 操作显示天/小时
		showTimeOrDay:function(){
			var _this = this;
			if(_this.op.type=="day"){
				$(_this.op.show_time_or_day_span).each(function(index){
					$(this).text("第"+(index+1)+"天");
				})
			}else{
				$(_this.op.show_time_or_day_span).each(function(index){
					$(this).text("");
				})
			}
		},
		// 绑定功能 
		bindFn:function(){
			var _this = this;
			
			// 修改所有线路的日期格式
			$("input["+_this.op.name+"]").each(function(){
				var $this = $(this),
				$journey_div=$this.closest("div.journey"),
				$show_time_or_day_span=$journey_div.find(_this.op.show_time_or_day_span),
				id=$this.prop("id"),
				dd=$this.parent(),
				layerdate={
				    elem: "#"+id,
				    event: 'focus',
				    format: 'YYYY-MM-DD',
				    min: laydate.now(),
				    max: '2099-06-16',
				    istoday: false,
				    istime:false,
				    festival: true, //是否显示节日
				};
				dd.html("");
				
				if(_this.op.type=="day"){

					// 重新生成新的日期框代替之前的
					var str = '<input id="'+id+'" name="l_time_day" time-day-hour type="text" class="laydate-icon"  placeholder="日期"/>';
					dd.append(str);
					setTimeout(function(){
						laydate(layerdate);
					},100)
				}else{

					// 重新生成新的日期框代替之前的
					var str = '<input id="'+id+'" name="l_time_hour" time-day-hour type="text" class="laydate-icon"  placeholder="日期"/>';
					
					layerdate.istime=true;
					layerdate.format="YYYY-MM-DD hh:mm";
					// 开始时间选好后，重置结束时间的最小时间
					layerdate.choose=function(datas){
						layerdateto.min=datas;


				    	var start = $journey_div.find("input#"+id).val(),
				    		end   = $journey_div.find("input#"+id+"_to").val();
				    	
				    	if(start != "" && end != ""){
				    		$show_time_or_day_span.html(start+"&nbsp;-&nbsp;"+end);
				    	}
					}
					
					// 如果是以小时为单位，改成区间小时量
					var layerdateto={
						    elem: "#"+id+"_to",
						    event: 'focus',
						    format: 'YYYY-MM-DD hh:mm',
						    min: laydate.now(),
						    max: '2099-06-16',
						    istoday: false,
						    festival: true, //是否显示节日
						    istime:true, //是否显示节日
						    choose: function(datas){
						    	// 结束日选好后，重置开始日的最大日期
						    	layerdate.max = datas;
						    	
						    	var start = $journey_div.find("input#"+id).val(),
						    		end   = $journey_div.find("input#"+id+"_to").val();
						    	
						    	if(start != "" && end != ""){
						    		$show_time_or_day_span.html(start+"&nbsp;-&nbsp;"+end);
						    	}
						    }
						};

					str+='&nbsp;-&nbsp;'
						+'<input id="'+id+'_to" name="l_to_time" type="text" class="laydate-icon"  placeholder="日期"/>';
					
					dd.append(str);
					setTimeout(function(){
						laydate(layerdate);
						laydate(layerdateto);
					},100)
					
				}
				
			})
		}
	}
	
	new $.fn.junit({
		target:"div#journey_unit",// 要实例化的行程单位元素
		type:"day",// 默认行程类型
		name:"time-day-hour",// 所有行程日期插件的name值
		show_time_or_day_span:"span#show_time_or_day_span",// 显示天/小时元素
	})
})