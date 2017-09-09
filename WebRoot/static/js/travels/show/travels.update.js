$(function(){
	$.fn.travelsUpdate=function(){
		this.init();
	}
	
	$.fn.travelsUpdate.prototype={
		/**
		 * 初始化
		 */
		init:function(){
			this.post();
			
			this.setFn();
		},
		/**
		 * 登录回调
		 */
		setFn:function(){
			// 如果用户没有登录，设置登录回调
			layerLogin.setFn(function(){
				$.post("travels/findUserTravelsPraiseOrFollow.json",{
					id:travelsId
				},function(data){
					if(data && data.RESPONSE_STATE == "200"){
						var info = data.info;
						// 修改当前用户点赞状态
						if(info.currentUserPraiseState){
							$("span#travelsPraise").addClass("praise");
						}
						// 修改当前用户收藏状态
						if(info.currentUserFollowState){
							$("span#travlesFollow").addClass("follow");
						}

						var $img = $("img#comment-current-uHeadImg");
						$img.attr("data-original",info.uHeadImg).lazyload({effect: "fadeIn"});
						
						$("span#comment-current-uName").text(info.uName);
					}else{
						layer.msg("数据更新失败，请刷新重试或联系客服，给您带来的不便敬请谅解!",{icon:5,shade:0.5,time:1*1000})
					}
				})
			});
		},
		/**
		 * 请求加载最新数据
		 */
		post:function(){
			var _this = this;
			$.post("travels/updateNewInfo.json",{
				id:travelsId
			},function(data){
				if(data && data.RESPONSE_STATE == "200"){
					//判断是否游记发布者
					_this.updateTravels(data.info);
					
					// 修改游记完善信息
					_this.updatePerfect(data.info);
					
					// 修改点赞数量
					_this.updatePraiseNum(data.info);
					
					// 修改收藏数量
					_this.updateFollowNum(data.info);
					
					// 修改被查看次数
					_this.updateSeeNum(data.info);
					
					// 修改当前用户点赞状态
					_this.updateCurrentUserPraiseState(data.info);
					
					// 修改当前用户收藏状态
					_this.updateCurrentUserFollowState(data.info);
					
					// 修改页面当前用户登录信息
					if(data.info.uType){
						// 修改头部登录状态
						//_this.updateHeader(data.info);
						
						// 修改评论显示信息
						_this.updateCommentUser(data.info);
					}else{
						//用户未登录，删除评论区用户头像
						_this.removeCommentUser();
					}
				}else{
					layer.msg("数据更新失败，请刷新重试或联系客服，给您带来的不便敬请谅解!",{icon:5,shade:0.5,time:3*1000})
				}
			})
		},
		/**
		 * 判断是否为游记发布者
		 */
		updateTravels:function(info){
			if(info.uId==travelsUserId){
				$("body").find("#edit-travels").css("display","");
				$("body").find("#delete").css("display","");
				$("#top").find("#edit-travels").css("display","");
				$("#top").find("#delete").css("display","");
				$("#edit").css("display","");
				$("#edit-perfect").css("display","");
			}else{
				$("body").find("#edit-travels").remove();
				$("body").find("#delete").remove();
				$("#edit").remove();
				$("#edit-perfect").remove();
				$("#perfect-box").remove();
			}
		},
		/**
		 * 修改游记完善信息
		 */
		updatePerfect:function(info){
			if(info.departure_time_str!="null"&&info.departure_time_str!=""&&info.departure_time_str!=null){
				$("#show_departure_time").html("出发日期："+info.departure_time_str);
				$("#show_travel_days").html("出行天数："+info.travel_days+"天");
				$("#show_travel_person").html("人物："+info.travel_person);
				$("#show_per_capita_cost").html("人均费用："+info.per_capita_cost+"RMB");
				
				$("#departure_time").val(info.departure_time_str);
				$("#travel_days").val(info.travel_days);
				$("#travel_person").val(info.travel_person);
				$("#per_capita_cost").val(info.per_capita_cost);
				
				$("#show-perfect").css("display","block");
				$("#edit-perfect").remove();
			}else{
				$("#show-perfect").remove();
			}
		},
		/**
		 * 修改评论显示信息
		 * @param data
		 */
		updateCommentUser:function(data){
			var $img = $("img#comment-current-uHeadImg");
			$img.attr("data-original",data.uHeadImg).lazyload({effect: "fadeIn"});
			
			$("span#comment-current-uName").text(data.uName);
		},
		/**
		 * 用户未登录，删除评论区用户头像
		 */
		removeCommentUser:function(){
			$("img#comment-current-uHeadImg").remove();
			$("span#comment-current-uName").remove();
		},
		/**
		 * 修改头部登录状态
		 * @param data
		 */
		updateHeader:function(data){
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
						
						str += '<li><a href="#" target="_blank"><i class="icon_myActivity"></i>我的活动</a>'
						+'</li>'
						+'<li><a href="activeConsultation/consultation.html?id='+data.uId+'" target="_blank"><i class="icon_myQuestion"></i>我的问答</a>'
						+'</li>'
						+'<li><a href="follow/myActive.html" target="_blank"><i class="icon_myCollect"></i>我的收藏</a>'
						+'</li>'
						+'<li><a href="activeSignup/MySignUp.html" target="_blank"><i class="icon_myOrder"></i>我的订单</a>'
						+'</li>'
						+'<li><a href="users/userInfo.html" target="_blank"><i class="icon_settings"></i>设置</a>'
						+'</li>'
						+'<li><a href="javascript:void(0)" id="logout" ><i class="icon_logout"></i>退出</a>'
						+'</li>'
						+'</ul>'
						+'</div>';

					$info.html(str);

			}
		
		},
		/**
		 * 修改点赞数量
		 */
		updatePraiseNum:function(info){
			$("span#travelsPraise").next().text(info.praiseNum)
		},
		/**
		 * 修改收藏数量
		 */
		updateFollowNum:function(info){
			$("span#travlesFollow").next().text(info.followNum)
		},
		/**
		 * 修改查看次数
		 */
		updateSeeNum:function(info){
			$("span#readNum").text("累计被查看"+info.seeNum+"次");
		},
		/**
		 * 修改当前用户点赞状态
		 */
		updateCurrentUserPraiseState:function(info){
			if(info.currentUserPraiseState){
				$("span#travelsPraise").addClass("praise");
			}
		},
		/**
		 * 修改当前用户收藏状态
		 */
		updateCurrentUserFollowState:function(info){
			if(info.currentUserFollowState){
				$("span#travlesFollow").addClass("follow");
			}
		}
	}
	
	new $.fn.travelsUpdate();
})