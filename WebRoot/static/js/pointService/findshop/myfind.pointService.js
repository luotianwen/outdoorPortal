var _current_choose_photo_id=null;

$(function(){
	
	$.fn.venues=function(){
		// 场馆类型缓存对象
		this.types = null;
		
		// 城市信息缓存json对象
		this.citys = null;

		// 营业时间层级
		this.timeZIndex=6;
		
		// 营业时间数据下标
		this.dateIndex = 1;
		
		// 初始加载
		this.init();
	}
	
	$.fn.venues.prototype={
		init:function(){
			
			// 场馆类型级联操作
			this.venueType();
			
			// 地理位置级联菜单
			this.addressLink();
			
			// 选择营业时间
			this.chooseTime();
			
			// 添加营业时间
			this.addTime();
			
			// 选择营业时间段
			this.timeSelect();
			
			// 点击外部关闭展示标签信息
			this.clickClose();
			
			// 添加联系方式
			this.addPhone();
			
			// 监听选择照片元素
			this.monitorChoosePhoto();
			
			// 提交审核
			this.submitInfo();
			
			// 标记地图坐标
			this.addMapAddress();
			
		},
		/**
		 * 标记地图坐标
		 */
		addMapAddress:function(){
			// 标记地图坐标
			$("a#addMapAddress").on("click",function(){
				layer.open({
					type : 2,
					area : [ '80%', '90%' ],
					title : '标记商户地图',
					shade : 0.5,
					fix : true,
					shift : 0,
					maxmin : false,
					closeBtn : 1,
					content : 'pointService/line.html?showAddressId=addMapAddressDD'
				});
			})
			
			
			
			// 绑定未来事件（编辑已选择的坐标）
			$("dd#addMapAddressDD").on("click","a#editAlreadyMapLine",function(){
				layer.open({
					type : 2,
					area : [ '80%', '90%' ],
					title : '标记商户地图',
					shade : 0.5,
					fix : true,
					shift : 0,
					maxmin : false,
					closeBtn : 1,
					content : 'pointService/line.html?showAddressId=addMapAddressDD&start='+$(this).attr("start")
				});
			})
			
		},
		/**
		 * 提交审核
		 */
		submitInfo:function(){
			
			var	_this = this;
				$("a#submitInfo").on("click",function(){
					if(_this.check()){
						var typeId = _this.getType(),
							params = $("form#pointServiceForm").serialize()+"&ps_type="+typeId;
						
						layer.msg("提交中",{icon:16,shade:0.3,time:10*1000});
						$.post("pointService/ruzhu.json",params,function(data){
							if(data){
								if(data.RESPONSE_STATE == "200"){
									$("a#submitInfo").off("click");
									layer.msg("提交成功。",{icon:1,time:1*1000,shade:0.3},function(){
										// 跳转首页
										layer.msg("提交成功，审核结果会在1-2个工作日以短信/邮箱通知您，请耐心等待审核！",{icon:1,shade:0.5,time:1.5*1000},function(){
											window.location.href="index.html";
										})
									})
									
								}else{
									layer.alert(data.ERROR_INFO,{icon:0})
								}
							}
						})
					}
					
				})
		},
		/**
		 * data check
		 */
		check:function(){
			var _this = this,
				$name = $("input[name=ps_zh_name]"),
				$chooseType = $("dl#chooseType>dd>select:last"),
				$address = $("dl#chooseAddress>dd>select"),
				isChoose = false,
				$ps_address = $("input[name=ps_address]"),
				$contacts = $("dl#contact>dd>input"),
				$ps_traffic = $("textarea[name=ps_traffic]"),
				$weekDIV = $("div#weekDIV"),
				imgs = $("input#img").length;
			
//			if($name.val().trim() == ""){
//				layer.tips('商户名不可为空', $name);
//				$name.focus();
//				return false;
//			}
//			
//			if($chooseType.val() == ""){
//				layer.tips('请选择类型', $chooseType);
//				_this.animate($chooseType.offset().top);
//				return false;
//			}
			
			
			/*$address.each(function(){
				if($(this).val() == ""){
					layer.tips('请选择地理位置', $(this));
					_this.animate($(this).offset().top);
					isChoose = true;
					return false;
				}
			})
			if(isChoose){
				return false;
			}
			
			if($ps_address.val().trim() == ""){
				layer.tips('请填写详细地址', $ps_address);
				$ps_address.focus();
				return false;
			}
			
			$contacts.each(function(){
				var $this = $(this);
				if($this.val().trim() == ""){
					layer.tips('请填写联系方式', $this);
					$this.focus();
					isChoose = true;
					return false;
				}
			})
			if(isChoose){
				return false;
			}
			
			if($ps_traffic.val().trim() == ""){
				layer.tips('请填写交通方式', $ps_traffic);
				$ps_traffic.focus();
				return false;
			}
			
			$weekDIV.each(function(){
				var $parent = $(this).parent(),
					$input = $parent.find("input:hidden"),
					b = false;
				
				$input.each(function(){
					if($(this).val() == ""){
						b = true;
					}
				})
				
				if(b){
					layer.tips('请选择营业时间', $(this));
					_this.animate($(this).offset().top);
					isChoose = true;
					return false;
				}
			})
			if(isChoose){
				return false;
			}*/
			
			if(imgs == 0){
				var ul = $("ul#uploadUl>li");
				layer.tips('最少上传一张图片', ul);
				_this.animate(ul.offset().top);
				return false;
			}else{
				var count = 0;
				$("ul#uploadUl li").each(function(){
					var $this = $(this);
					if($this.find("img").length>0){
						if($this.find("div.progress-bar").length>0){
							count++;
						}
					}
				});
				
				if(count>0){
					layer.alert('图片正在上传，请稍后提交！',{icon:0});
					return false;
				}
			}
			return true;
		},
		/**
		 * update height
		 * @param h
		 */
		animate:function(h){
			$("body,html").animate({scrollTop:h})
		},
		/**
		 * 获取选择的类型
		 */
		getType:function(){
			return $("dl#chooseType").find("select:last").val();
		},
		/**
		 * 监听选择照片元素
		 */
		monitorChoosePhoto:function(){
			$("ul#uploadUl").on("click","li>a.edit-add,a.edit-again",function(e){
				$("div#container").find("input:file").removeAttr("multiple");
				_current_choose_photo_id = $(this).closest("li").attr("id");
				$("div#container").find("input:file").trigger("click");
			})
			
			// 删除图片
			$("ul#uploadUl").on("click","a.edit-del",function(){
				$(this).closest("li").remove();
				if($("ul#uploadUl>li>a.edit-add").length==0){
					var str = '<li id="'+new Date().getTime()+'"><a class="edit-add"><i></i>添加照片</a></li>';
					$("ul#uploadUl").append(str);
				}
			})
		},
		/**
		 * 添加联系方式
		 */
		addPhone:function(){
			var str='<input  type="text" name="contact[1]" style="width:150px;" placeholder="座机，手机或邮箱" maxlength="20" >';
			$("a#addPhone").on("click",function(){
				var $this = $(this),
					$handleType = $this.attr("handle-type");
				if($handleType == "add"){
					$this.before("&nbsp;"+str);
					$this.html("&nbsp;删除联系方式");
					$this.attr("handle-type","del");
				}else{
					$this.prev().remove();
					$this.attr("handle-type","add");
					$this.html("<i></i>添加联系方式");
				}
			})
		},
		/**
		 * 点击外部关闭展示标签信息
		 */
		clickClose:function(){
			$(document).on("click",function(e){
				var $ele = $(e.target);
				if(!$ele.hasClass("time-select") && $ele.closest("a.time-select").length==0){
					$("div.time-list").hide();
				}
			})
		},
		/**
		 * 选择营业时间段
		 */
		timeSelect:function(){
			var _this = this;
			// 绑定选择时间段事件
			$("dd[elementType=chooseWeek]").on("click","a.time-select",function(){
				var $this = $(this),
					$divTimeList = $this.children('div.time-list'),
					disabled=$this.attr("disabled");
				
				
				if(disabled){
					return;
				}
				
				// 关闭同一组另一时间段div
				$('div.time-list').not($divTimeList).hide();
				
				// 展开/隐藏
				$divTimeList.toggle();
			}).on("click","div.time-list ul>li",function(){// 选择时间段
				var $this = $(this);
				
				// 移除同胞元素的选中效果
				$this.addClass("selected").siblings().removeClass("selected");
				
				// 显示已选择的日期
				$this.closest("div").prevAll("showtime").text($this.text());
				
				// 保存选择的时间段
				$this.closest("ul").siblings("input").val($this.text());
			}).on("click","a.hours-24",function(){// 24小时营业
				var $this = $(this);
				if($this.hasClass("selected")){
					$this.siblings("a.time-select,span").show().end().removeClass("selected");
					$this.closest("div").find("input").eq(0).val("");
					$this.closest("div").find("input").eq(1).val("");
				}else{
					$this.siblings("a.time-select,span").hide().end().addClass("selected");
					$this.closest("div").find("input").val("0:00");
				}
			}).on("click","a.icon-close",function(){// 删除当前组
				var $this = $(this);
				layer.confirm("是否确认删除?",{icon:3},function(index){
					layer.close(index);
					$this.closest("div.business-hours").remove();
					_this.timeZIndex = $("div.business-hours:last").css("z-index")*1-1;
					$("a#addTime").closest("div").show();
				});
			})
			
		},
		/**
		 * 添加营业时间
		 */
		addTime:function(){
			var _this = this;
			$("a#addTime").on("click",function(){
				
			var str='<div class="business-hours clearfix">'
				+'<a class="icon-close" title="关闭"></a>'
				+'<div class="week clearfix" id="weekDIV">'
				+'<a class="all-week " id="allWeek">整&nbsp;&nbsp;周</a>'
				+'<a>周一</a>'
				+'<a>周二</a>'
				+'<a>周三</a>'
				+'<a>周四</a>'
				+'<a>周五</a>'
				+'<a>周六</a>'
				+'<a>周日</a>'
				+'<span>可以多选</span>'
	          	+'<input name="dates['+_this.dateIndex+'].days" type="hidden" value="周一,周二,周三,周四,周五,周六,周日" />'
				+'</div>'
				+'<div class="hour clearfix">'
				+'<a class="hours-24">24小时营业</a>'
				+'<a class="time-select">'
				+'<showtime>选择开始时间</showtime>'
				+'<b></b>'
				+'<div class="time-list" style="display: none;" >'
				+'<ul>'
				+'<li>0:00</li><li>0:30</li><li>1:00</li><li>1:30</li><li>2:00</li><li>2:30</li><li>3:00</li><li>3:30</li><li>4:00</li><li>4:30</li><li>5:00</li><li>5:30</li><li>6:00</li><li>6:30</li><li>7:00</li><li>7:30</li><li>8:00</li><li>8:30</li><li>9:00</li><li>9:30</li><li>10:00</li><li>10:30</li><li>11:00</li><li>11:30</li><li>12:00</li><li>12:30</li><li>13:00</li><li>13:30</li><li>14:00</li><li>14:30</li><li>15:00</li><li>15:30</li><li>16:00</li><li>16:30</li><li>17:00</li><li>17:30</li><li>18:00</li><li>18:30</li><li>19:00</li><li>19:30</li><li>20:00</li><li>20:30</li><li>21:00</li><li>21:30</li><li>22:00</li><li>22:30</li><li>23:00</li><li>23:30</li>'
				+'</ul>'
				+'<input type="hidden" name="dates['+_this.dateIndex+'].startDate" >'
				+'</div>'
				+'</a>'
				+'<span>至</span>'
				+'<a class="time-select">'
				+'<showtime>选择结束时间</showtime>'
				+'<b></b>'
				+'<div class="time-list" style="display: none;" >'
				+'<ul>'
				+'<li>0:00</li><li>0:30</li><li>1:00</li><li>1:30</li><li>2:00</li><li>2:30</li><li>3:00</li><li>3:30</li><li>4:00</li><li>4:30</li><li>5:00</li><li>5:30</li><li>6:00</li><li>6:30</li><li>7:00</li><li>7:30</li><li>8:00</li><li>8:30</li><li>9:00</li><li>9:30</li><li>10:00</li><li>10:30</li><li>11:00</li><li>11:30</li><li>12:00</li><li>12:30</li><li>13:00</li><li>13:30</li><li>14:00</li><li>14:30</li><li>15:00</li><li>15:30</li><li>16:00</li><li>16:30</li><li>17:00</li><li>17:30</li><li>18:00</li><li>18:30</li><li>19:00</li><li>19:30</li><li>20:00</li><li>20:30</li><li>21:00</li><li>21:30</li><li>22:00</li><li>22:30</li><li>23:00</li><li>23:30</li>'
				+'</ul>'
				+'<input type="hidden" name="dates['+_this.dateIndex+'].endDate" >'
				+'</div>'
				+'</a>'
				+'</div>'
				+'</div>';

			
			
				$(this).parent().before(str);
				
				// 自增下标
				_this.dateIndex++;
				
				// 设置层级关系
				$("div.business-hours:last").css({
					"z-index":_this.timeZIndex
				})
				
				_this.timeZIndex--;
				
				if($("div.business-hours").length >= 7){
					$(this).closest("div").hide();
				}
			})
		},
		/**
		 * 选择营业时间
		 */
		chooseTime:function(){
			
			$("dd[elementType=chooseWeek]").on("click","div#weekDIV>a",function(){
				var $this = $(this);
				// 整周链接要去掉所有的小周的样式及加上本身的样式
				if($this.hasClass("all-week")){
					$this.siblings("a").removeClass("selected").end().addClass("selected");
					$this.siblings("input:hidden").val("周一,周二,周三,周四,周五,周六,周日");
				}else{
					// 关闭整周的样式
					$this.siblings("a#allWeek").removeClass("selected");
					
					// 天在选中情况下提供再次点击移除功能
					if($this.hasClass("selected")){
						$this.removeClass("selected");
					}else{
						$this.addClass("selected");
					}	
					
					// 保存选择的营业日
					var days = "";
					$this.parent().find("a[id!=allWeek].selected").each(function(index){
						if(days == ""){
							days = $(this).text();
						}else{
							days += ","+$(this).text();
						}
					})
					$this.siblings("input:hidden").val(days);
					
				}
				
			})
		},
		/**
		 * 地理位置级联菜单
		 */
		addressLink:function(){
			var _this = this;
			$("select[addresstype=province]").on("change",function(){
				var $this = $(this);
				
				$.post("pointService/getCitys.json",{
					id:$this.val()
				},function(data){
					if(data.RESPONSE_STATE == "200"){
						_this.citys = data.citys
						var select = "<option value=''>城市/地区</option>";
						$.each(_this.citys,function(i,item){
							select += "<option value='"+item.code+"'>"+item.name+"</option>"
						})
						$("select[addresstype=city]").html(select);
						
					}else{
						layer.alert(data.ERROR_INFO,{icon:0})
					}
				})
				
			})
			
			$("select[addresstype=city]").on("change",function(){
				var $this = $(this),
					code = $this.val();
				
				$.each(_this.citys,function(i,city){
					if(city.code == code){
						var countys = city.countys,
						select = "<option value=''>区/县</option>";
						$.each(countys,function(j,county){
							select += "<option value='"+county.code+"'>"+county.name+"</option>"
						})
						$("select[addresstype=county]").html(select);
					}
					
				})
				
			})
		},
		/**
		 * 场馆类型级联操作
		 */
		venueType:function(){
			var _this = this;
		    $("select[venuetype=1]").on("change",function(){
		      var $this = $(this);
		    	$.post("pointService/getChildrens.json",{
		    		id:$(this).val()
		    	},function(data){
		    		if(data.RESPONSE_STATE == "200"){
		    			$("select[venuetype]").not("[venuetype=1]").remove();
		    			
		    			_this.types = data.types;
		    			if(_this.types.length>0){
			      			var select ="<select venuetype='2'><option value=''>请选择</option>",
			      				index=0;
						    $.each(_this.types,function(i,item){
						    	if(item.categoryLevel == 2){
						    		select += "<option value='"+item.categoryId+"'>"+item.categoryName+"</option>";
						    		index++;
						    	}
						    })
						    select += "</select>";
						    $("select[venuetype=2]").remove();
						    if(index == 0){
						    	return;
						    }
						    $this.after("&nbsp;"+select);
						    
						    // 动态生成子数据集合
						    _this.dynamicLink(2);
		    			}
		    			
		    		}else{
		    			layer.alert(data.ERROR_INFO,{icon:0})
		    		}
		    	})
		    })
		},
		/**
		 * 生成级联菜单
		 */
		dynamicLink:function(index){
			var _this = this;
		    $("select[venuetype="+index+"]").on("change",function(){
	  		var $this = $(this),
	  			select ="<select venuetype='"+(index+1)+"'><option value=''>请选择</option>",
	  			index_num=0;
		    	$.each(_this.types,function(i,item){
			    	if(item.categoryLevel == (index+1) && item.parentId == $this.val()){
			    		select += "<option value='"+item.categoryId+"'>"+item.categoryName+"</option>";
			    		index_num++;
			    	}
			    })
		    	select += "</select>";
			    $("select[venuetype="+(index+1)+"]").remove();
			    if(index_num == 0){
			    	return;
			    }
			    $this.after("&nbsp;"+select);
			    _this.dynamicLink(index+1);
		    })
	    
		}
	}
	
	new $.fn.venues();
})
