
$(function(){
	
	$.fn.home=function(){
		this.init();
	}
	
	$.fn.home.prototype={
		init:function(){
			this.nicenav();
			this.bind();
		},
		nicenav:function(){
			// 导航滑动效果js
		    $.extend({
		        'nicenav': function (con) {
		            con = typeof con === 'number' ? con : 400;
		            var $lis = $('#nav>li'), $h = $('#buoy')
		            $lis.hover(function () {
		                $h.stop().animate({
		                    'left': $(this).offsetParent().context.offsetLeft
		                }, con);
		            }, function () {
		                $h.stop().animate({
		                    'left': _map_lp
		                }, con);
		            })
		        }
		    });
			$.nicenav(300);
		},
		bind:function(){
			this.apply();
			this.release();
		},
		apply:function(){
			var _this = this;
			
			// 申请领队
			$("a#apply_leader").on("click",function(){
				_this.getData(function(data){
					if(data == 1 || data == 2){
						layer.alert("您已经是["+(data=="1"?"领队":"俱乐部")+"],无需再次申请!",{icon:6,title:"资质提醒"});
					}else if(data){
						_this.layer("选择申请类型", "选择申请类型!");
					}
				})
			});
		},
		release:function(){
			
			var _this = this;
			
			// release active
			$("a#release").on("click",function(){
				_this.getData(function(data){
					if(data){
						if(data != 1 && data != 2){
							_this.layer("资质提醒", "您还不具有发布活动的资质，请申请，谢谢！");
						}else{
							window.location.href="huodong/release.html";
						}
					}
				});
			})
		},
		getData:function(fn){
			$.post("users/uType.json",function(data){
				fn(data);
			})
		},
		layer:function(title,content){
			layer.open({
				  type: 1,
				  title: title,
				  shade: 0.6,
				  btn:['申请领队','申请企业'],
				  btn1:function(index,layero){
					  $.post("uCheck/check.json",function(data){
						  if(data.RESPONSE_STATE=="200"){
							  location.href="uCheck/reply.html?type=leader";
						  }else if(data.RESPONSE_STATE=="500"){
							  layer.alert(data.ERROR_INFO,{title:"错误信息",icon:0});
						  }
					  })
				  },
				  btn2:function(index,layero){
					  $.post("uCheck/check.json",function(data){
						  if(data.RESPONSE_STATE=="200"){
							  location.href="uCheck/reply.html?type=group";
						  }else if(data.RESPONSE_STATE=="500"){
							  layer.alert(data.ERROR_INFO,{title:"错误信息",icon:0});
						  }
					  })
				  },
				  content: "<div class='layer-anim layui-layer-dialog' type='dialog' times='20' showtime='0' contype='string'>"
							+"	<div class='layui-layer-content layui-layer-padding'>"
							+"		<i class='layui-layer-ico layui-layer-ico3'></i>"+content
							+"	</div>"
							+"</div>"
			});
		}
	}
	
	new $.fn.home();
})
