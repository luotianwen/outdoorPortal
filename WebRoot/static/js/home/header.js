
$(function(){
	
	$.fn.home=function(){
		this.init();
	}
	
	$.fn.home.prototype={
		init:function(){
			this.bind();
		},
		bind:function(){
			this.apply();
			this.release();
			this.releaseProject();
			this.nicenav();
			this.autoLogin();
			this.logout();
		},
		logout:function(){
			$(document).on("click","a#logout",function(){
				$.cookie("wanrma.com.uName",null,{path:'/'});
				$.cookie("wanrma.com.uPassword",null,{path:'/'});
				window.location="logout.html";
			})
		},
		/**
		 * 自动登录
		 */
		autoLogin:function(){
			var _this = this;
			if(currentSessionUID == ""){
				var uName = $.cookie("wanrma.com.uName"),
					uPaddword = $.cookie("wanrma.com.uPassword");
				if(uName && uName != "null"){
					// 请求登录数据
					$.ajax({
			    		type : "post",
			    		url : "login/asyn.json",
			    		data : {n:uName,p:uPaddword,y:"",c:true},
			    		async : false,
			    		success : function(data){
			    			if(data){
								if(data.RESPONSE_STATE == "200"){
									_this.updateHeader(data);
								}
							}
			    		}
					});
				}
			}
		},
		// 更改欢迎信息
		updateHeader:function(data){
			currentSessionUID = data.uId;
			var $info = $("div#layerLoginUpdateDiv");
			if($info.length > 0){
					
				var str='<div class="fr head_user">'
					+'<a href="users/center.html?id='+data.uId+'" title="'+data.uName+'" class="user_link">'
					+'<img src="'+data.uHeadImg+'" /><i></i>'
					+'</a>'
					+'<ul class="dropdown_group">'
					+'<li><a href="users/center.html?id='+data.uId+'" target="_blank"><i class="icon_my"></i>我的玩嘛</a>'
					+'</li>'
					+'<li><a href="travels/draft.html" target="_blank"><i class="icon_writeNote"></i>写游记</a>'
					+'</li>';
				
				if(data.uType == "1"||data.uType == "2"){
					str +='<li><a href="javascript:void(0)" id="releaseActivity" target="_blank"><i class="icon_releaseActivity"></i>发布活动</a>'
						+'</li>';
				}else if(data.uType == "50"){
					//str +='<li><a href="javascript:void(0)" id="releaseProject" target="_blank"><i class="icon_releaseProject"></i>发布项目</a>'
					//	+'</li>';
				}
				
				//str += '<li><a href="pointService/myfind.html" target="_blank"><i class="icon_findshop"></i>发现好店</a></li>';
				if(data.uType == "3"){
					str += '<li><a href="javascript:void(0)" id="apply_leader" target="_blank"><i class="icon_appLeader"></i>申请领队</a></li>';
					//str += '<li><a href="pointService/apply.html" target="_blank"><i class="icon_append"></i>申请商户</a></li>';
				}
					
					str += '<li><a href="huodong/myActive.html" target="_blank"><i class="icon_myActivity"></i>我的活动</a>'
					+'</li>'
					+'<li><a href="activeConsultation/consultation.html?id='+data.uId+'" target="_blank"><i class="icon_myQuestion"></i>我的问答</a>'
					+'</li>'
					+'<li><a href="follow/myActive.html" target="_blank"><i class="icon_myCollect"></i>我的收藏</a>'
					+'</li>'
					+'<li><a href="activeSignup/MySignUp.html" target="_blank"><i class="icon_myOrder"></i>我的订单</a>'
					+'</li>'
					+'<li><a href="users/userInfo.html" target="_blank"><i class="icon_settings"></i>设置</a>'
					+'</li>'
					+'<li><a href="javascript:void(0)" id="logout"><i class="icon_logout"></i>退出</a>'
					+'</li>'
					+'</ul>'
					+'</div>';

				str += "<div class='fr head_msg'>"+
						"	<a href='javascript:void(0)' class='drop_trigger'>"+
						"		<i class='icon-msg'></i>消息"+
						"		<span class='msg-num' id='messageAll' style='display: inline;'>0</span>"+
						"	</a>"+
						"	<ul class='dropdown_msg'>"+
						"		<li>"+
						"			<a href='messagePrivate/receive.html?type=11' target='_blank'>"+
						"				系统通知<span class='msg-num' id='sysAll' style='display: inline;'>0</span>"+
						"			</a>"+
						"		</li>"+
						"		<li>"+
						"			<a href='messagePrivate/receive.html?type=51' target='_blank'>"+
						"				文章消息<span class='msg-num' id='travelAll' style='display: inline;'>0</span>"+
						"			</a>"+
						"		</li>";
				if(data.uType == "1" || data.uType == "2"){
					str += "<li>"+
							"	<a href='messagePrivate/receive.html?type=21' target='_blank'>"+
							"		活动消息<span class='msg-num' id='acAll' style='display: inline;'>0</span>"+
							"	</a>"+
							"</li>";
				}else{	
					str += "<li>"+
							"	<a href='messagePrivate/receive.html?type=31' target='_blank'>"+
							"		订单消息<span class='msg-num' id='orderAll' style='display: inline;'>0</span>"+
							"	</a>"+
							"</li>"
				}
									
						str += "<li>"+
								"	<a href='messagePrivate/receive.html?type=41' target='_blank'>"+
								"		问答消息<span class='msg-num' id='askAll' style='display: inline;'>0</span>"+
								"	</a>"+
								"</li>"+
								"<li>"+
								"	<a href='dialog/msg.html' target='_blank'>"+
								"		私信<span class='msg-num' id='dialogAll' style='display: inline;'>0</span>"+
								"	</a>"+
								"</li>"+
						"	</ul>"+
						"</div>";
				
				$info.html(str);

			}
		},
		nicenav:function(){
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
		                    'left': header_lp
		                }, con);
		            })
		        }
		    });
		    $.nicenav(300);
		},
		/**
		 * 申请领队
		 */
		apply:function(){
			var _this = this;
			
			// 申请领队
			$("div#m_toolbar,div#footer").on("click","a#apply_leader",function(){
				_this.getData(function(data){
					if(data == 1 || data == 2){
						layer.alert("您已经是["+(data=="1"?"领队":"俱乐部")+"],无需再次申请!",{icon:6,title:"资质提醒"});
					}else if(data){
						_this.layer("选择申请类型", "选择申请类型!");
					}
				})
			});
		},
		/**
		 * 发布活动
		 */
		release:function(){
			
			var _this = this;
			
			// release active
			$(document).on("click","a#releaseActivity",function(){
				_this.getData(function(data){
					if(data){
						if(data != 1 && data != 2){
							_this.layer("资质提醒", "您还不具有发布活动的资质，请先申请，谢谢！");
						}else{
							window.location.href="huodong/release.html";
						}
					}
				});
			})
		},
		
		/**
		 * 获取当前用户登录身份
		 * @param fn
		 */
		getData:function(fn){
			$.post("users/uType.json",function(data){
				fn(data);
			})
		},
		/**
		 * 申请领队弹窗
		 * @param title
		 * @param content
		 */
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
		},
		/**
		 * 发布项目
		 */
		releaseProject:function(){
			
			var _this = this;
			
			// release project
			$(document).on("click","a#releaseProject",function(){
				_this.getPointDate(function(data){
					if(data.uType!="50"){
						layer.alert('您还不具有发布项目的资质，请先申请，谢谢！',{title:"错误信息",icon:0});
					}else{
						if(data.list==null){
							layer.alert('您还不具有发布项目的资质，请先申请，谢谢！',{title:"错误信息",icon:0});
						}else{
							if(data.list.length==1){
								window.location.href="pointService/reply.html?id="+data.list[0].ps_id;
							}else if(data.list.length>1){
								var countent = "<div class='layer-anim layui-layer-dialog' type='dialog' times='20' showtime='0' contype='string'>"
												+"	<div class='layui-layer-content layui-layer-padding'>";
								
								for(var i=0,len=data.list.length;i<len;i++){
									countent += "<a href='pointService/reply.html?id="+data.list[i].ps_id+"' onclick='layer.closeAll();' target='_blank' style='color:#555'>"+(i+1)+"、"+data.list[i].ps_zh_name+"</a><br/>";
								}
								
								countent += "	</div>"
											+"</div>";
								
								layer.open({
									type: 1,
									title: "选择场馆",
									shade: 0.6,
									content: countent
								});
							}
						}
					}
				});
			})
		},
		getPointDate:function(fn){
			$.post("pointService/allPoint.json",function(data){
				fn(data);
			})
		},
	}
	
	new $.fn.home();
})
