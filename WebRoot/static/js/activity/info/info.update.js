$(function(){
	$.fn.update=function(){
		this.init();
	}
	
	$.fn.update.prototype={
		init:function(){
			// 请求更新
			this.post();
		},
		post:function(){
			var _this = this;
			// 更新活动
			$.post("huodong/updateActivityData.json",{
				id:activeId
			},function(data){
				if(data && data.RESPONSE_STATE == "200"){
					
					// 修改截止时间
					_this.time(data.info);
					
					// 修改参加人数和剩余人数
					_this.signupNum(data.info);
					
					// 修改评论和咨询数量
					_this.commentAndconsultationNum(data.info);
					
					// 修改活动状态
					_this.updateState(data.info);
					
					// 修改收藏状态
					_this.updateFollow(data.info);
					
					// 修改头部登录状态
					if(data.info.userId){
						//_this.updateHeader(data.info);
					}
					
				}else{
					layer.msg("数据更新失败，请刷新重试或联系客服，给您带来的不便敬请谅解!",{icon:5,shade:0.5,time:1*1000})
				}
			})
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

					$info.html(str);

			}
		
		},
		/**
		 * 修改截止时间
		 * @param data
		 */
		time:function(data){
			$("div.event-content span.c1").djs({
				time:data.enrollTime,
				currentTime:data.currentTime,
				stop:"报名已截止"
			})
		},
		/**
		 * 修改参加人数和剩余人数
		 * @param data
		 */
		signupNum:function(data){
			$("span#alreadyInNumSpan").text("参加"+data.confirmUserNum+"人");
			$("span#residueNumSpan").text("余位"+(data.needUserNum-data.confirmUserNum)+"人");
		},
		/**
		 * 修改评论和咨询数量
		 * @param data
		 */
		commentAndconsultationNum:function(data){
			$("a[F=4F]").text("活动评价("+data.commentNum+")");
			$("a[F=5F]").text("咨询问答("+data.consultationNum+")");
		},
		/**
		 * 修改活动状态
		 * @param data
		 */
		updateState:function(data){
			var	_this = this,
				activityTime = _this.stringToDate(data.activityTime),
				endTime = _this.stringToDate(data.endTime),
				enrollTime =_this.stringToDate(data.enrollTime),
				currentTime = new Date(),
				$show = $("div#showActivityState"),
				follow='<a id="follow" activity_id="'+activeId+'" class="btn02">收藏</a>';
			
			if(currentTime.getTime() > endTime.getTime()){
				$show.html('<a class="disabled-btn01">活动已结束</a>'+follow);
				$("div#event_tabs_box").append('<a class="disabled-reserve-btn">活动已结束</a>');
			}else if(currentTime.getTime() > activityTime.getTime()){
				$show.html('<a class="disabled-btn01">活动已开始</a>'+follow);
				$("div#event_tabs_box").append('<a class="disabled-reserve-btn">活动已开始</a>');
			}else if(currentTime.getTime() > enrollTime.getTime()){
				$show.html('<a class="disabled-btn01">报名截止</a>'+follow);
				$("div#event_tabs_box").append('<a class="disabled-reserve-btn">报名截止</a>');
			}else if(data.signUpNum >= data.needUserNum){
				$show.html('<a class="disabled-btn01">报名人数已满</a>'+follow);
				$("div#event_tabs_box").append('<a class="disabled-reserve-btn">报名人数已满</a>');
			}else if(data.state == 5){
				$show.html('<a href="huodong/signUp.html?activeId='+activeId+'" class="btn01">我要预订</a>'+follow);
				$("div#event_tabs_box").append('<a href="huodong/signUp.html?activeId='+activeId+'" class="reserve-btn">我要预订</a>');
			}else{
				$show.html('<a class="disabled-btn01">'+data.stateVal+'</a>'+follow);
				$("div#event_tabs_box").append('<a class="disabled-reserve-btn">'+data.stateVal+'</a>');
			}
			
			
		},
		/**
		 * 修改收藏状态
		 * @param data
		 */
		updateFollow:function(data){
			if(data.follow){
				$("a#follow").attr("isFollow", "yes").text("取消收藏").prop("class", "disabled-btn02");
			}else{
				$("a#follow").attr("isFollow", "no").text("收藏").prop("class", "btn02");
			}
		},
		/**
		 * yyyy-MM-dd HH:mm:ss 转为date类型日期
		 * @param s
		 * @returns {Date}
		 */
		stringToDate:function(s) { 
			var d = new Date(); 
			d.setYear(parseInt(s.substring(0,4),10)); 
			d.setMonth(parseInt(s.substring(5,7)-1,10)); 
			d.setDate(parseInt(s.substring(8,10),10)); 
			d.setHours(parseInt(s.substring(11,13),10)); 
			d.setMinutes(parseInt(s.substring(14,16),10)); 
			d.setSeconds(parseInt(s.substring(17,19),10)); 
			return d; 
		}
	}
	
	new $.fn.update();
})