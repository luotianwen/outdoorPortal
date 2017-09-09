$(function(){
	
	var dataisInit = false;
	
	$.fn.consultation=function(index,$this,op){
		// 封装对象
		this.op = {
			index:index,// 标识下标，初始加载第一个的问答信息
			$this:$this,// 当前jq对象
			$downOrUp:$this.find(op.downOrUp),// 收缩/展开按钮
			$item:$this.find(op.item),// 问答信息聚合DIV
			$page:$this.find(op.page),// page对象
			replyType:$this.attr("replyType"),// 回复类型(待回复、已回复、追问)
			activityId:$this.attr("activityId"),// 活动ID
			$consultationNum:$this.find("em#consultation_num")
		};
		// 初始化对象
		this._init();
	}
	
	$.fn.consultation.prototype={
		/**
		 * 初始化对象
		 * @param $obj
		 */
		_init:function(){
			// 绑定事件
			this._bind();
			
			// 初始加载第一个活动的问答数据
			if(this.op.index == 0){
				this.op.$downOrUp.trigger("click");
			}
		},
		/**
		 * 绑定事件
		 */
		_bind:function(){
			// 展开/收起事件
			this._showOrHide();
			
			// 回复内容
			this._reply();
		},
		/**
		 * 回复内容
		 */
		_reply:function(){
			var _this = this;
			
			// 给未来元素绑定回复内容事件
			_this.op.$this.on("click","a#reply_btn",function(){
				var $this = $(this),
					comment = $(this).prev().val();
				
				// 回复内容非空验证
				if(comment.trim() == ""){
					layer.alert("回复内容不能为空!",{icon:0},function(index){
						layer.close(index);
						$this.prev().focus();
					});
					return;
				}
				// 持久化
				layer.confirm("是否确认回复？",{icon:3},function(index){
					layer.close(index);
					// 遮罩
					layer.msg("加载中...",{icon:16,shade:0.5,time:10*1000});
					$.post("activeConsultation/reply.json",{
						comment:comment,
						replyType:$this.attr("reply-type"),
						ac_id:$this.attr("consultation-id"),
						userId:$this.attr("reply-by-user")
					},function(data){
						layer.closeAll("dialog");
						if(data.RESPONSE_STATE == "200"){
							layer.msg("修改成功!",{icon:1,shade:0.5,time:0.8*1000},function(){
								$this.closest("dl").remove();
								_this._updateNum("-",1);
							})
						}else if(data.RESPONSE_STATE == "500"){
							layer.alert(data.ERROR_INFO,{icon:0});
						}
					})
					
				})
				
			})
		},
		/**
		 * 修改问答数量
		 * @param type	修改类型
		 * @param num	修改数量
		 */
		_updateNum:function(type,num){
			if(type == "+"){
				this.op.$consultationNum.text(this.op.$consultationNum.text()*1+1);
			}else{
				var num = this.op.$consultationNum.text()*1;
				
				if(num == 1 || num == 0){
					this.op.$this.remove();
				}else{
					this.op.$consultationNum.text(num-1);
				}
			}
		},
		/**
		 * 展开/收起事件
		 */
		_showOrHide:function(){
			var _this = this;
			// 给【展开/收缩】添加click事件
			_this.op.$downOrUp.on("click",function(){
				
				// 获取是否加载过数据标识
				var $i = $(this),
					load = $(this).attr("load");
				
				if(load == 0){
					// 加载回答数据，默认第一页
					_this._load(1,function(){

						// 加载成功设置为已经加载过
						$i.attr("load","1");
						
						// 切换展开/收缩icon样式
						$i.attr("class","up");
					});
				}else{
					// 展开/收缩
					_this.op.$item.toggle();
					
					// 切换展开/收缩图标样式
					$(this).prop("class",$(this).prop("class") == "up"?"down":"up");
					
					// 因为page分页是独立的一个div，所以还要同步控制
					if($(this).prop("class") == "down"){
						_this.op.$page.hide();
					}else{
						_this.op.$page.show();
					}
				}
				
				if(!dataisInit){
					dataisInit=true;
					return;
				}
				// 防止打开的时候看不到显示的数据，所以将视野移至该活动
				//$("body,html").animate({scrollTop:_this.op.$this.offset().top})
			})
		},
		/**
		 * ajax加载数据
		 */
		_load:function(page,fn){
			var _this = this;
			//layer.msg("加载中...",{icon:16,shade:0.5,time:10*1000});
			$.post("activeConsultation/loadConsultation.json",{
				type:this.op.replyType,
				id:this.op.activityId,
				page:page,
				loadType:'question',
				userId:userId
			},function(data){
				//layer.closeAll("dialog");
				if(data.RESPONSE_STATE == "200"){
					// 成功回调
					_this._success(data,fn);
				}else if(data.RESPONSE_STATE == "500"){
					layer.alert(data.ERROR_INFO,{icon:0});
				}

			})
		},
		/**
		 * 成功回调
		 * @param data
		 */
		_success:function(data,fn){
			var list = data.list,
				str="",
				_this = this;
			// 遍历问答数据
			for(var i=0,len=list.length;i<len;i++){
				var item = list[i];
				str+='<dl>'
					+'<dt>'
					+'<div class="user-image">'
					+'<img width="50" height="50" src="'+item.uHeadImg+'">'
					+'</div>'
					+'<div class="user-info">'
					+'<span>'+item.uName+'</span><span>'+item.create_time_str+'</span>'
					+'</div>'
					+'<div class="user-question">'+item.ac_comment+'</div>'
					+'</dt>'
					+'<dd>';
				
					// 追问的条件下要遍历已回复的数据
					if(item.ac_is_reply == 2){

						// 这条数据的回复
						str +='<div class="answers">'
							+'<em class="m-1">我</em>回复<em class="m-2">'+item.uName+'</em>：'+item.ac_reply_comment
							+'<span class="date">'+item.ac_reply_time_str+'</span>'
							+'</div>';
						
						
						// 追问及回复集合
						for(var j=0;j<item.acrs.length;j++){
							var ac = item.acrs[j];
							
							// 将自己回复的信息称呼换成‘我’
							if(ac.acr_create_user_id == userId){
								ac.acr_create_user_name="我";
							}else if(ac.acr_ac_user_id == userId){
								ac.acr_ac_user_name="我";
							}
							// 回复信息
							str +='<div class="answers">'
								+'<em class="m-1">'+ac.acr_create_user_name+'</em>回复<em class="m-2">'+ac.acr_ac_user_name+'</em>：'+ac.acr_comment
								+'<span class="date">'+ac.acr_create_time_str+'</span>'
								+'</div>';

						}
					}

					// 回复框
					str	+='<div class="input-block">'
						+'<input type="text" name="comment" placeholder="回复内容..."  maxlength="70">' 
						+'<a href="javascript:void(0);" class="answer-btn" id="reply_btn" reply-type="'+item.ac_is_reply+'" consultation-id="'+item.ac_id+'" reply-by-user="'+item.ac_create_user_id+'" >回复</a>'
						+'</div>'
						+'</dd>'
						+'</dl>';
			}
			// 赋值
			_this.op.$item.html(str);
			
			// 分页
			_this._page(data);
			
			//$("body,html").animate({scrollTop:$("div.my-name").offset().top-230},0)
			
			// 加载成功回调函数
			if(typeof(fn) == "function"){
				fn();
			}
		},
		/**
		 * 分页
		 */
		_page:function(data){
			var _this = this;
			// 分页
		    laypage({
		        cont: this.op.$page, // 容器。值支持id名 
		        pages: data.totalPage, // 通过后台拿到的总页数
		        curr:  data.currentPage,// 当前页
		        //skip: true, //是否开启跳页
		        groups: 3, //连续显示分页数
		        skin: '#FF8A01',
		        first: 1,
		        prev:"<",
		        next:">",
		        last: data.totalPage, //在尾页追加总页数。
		        jump: function(e, first){ //触发分页后的回调
		        	 if(!first){
		        		_this._load(e.curr);
		             }
		        }
		    });
		    
		}
	}
	
	$("div#question-list").each(function(index){
		new $.fn.consultation(index,$(this),{
			downOrUp:"i#down_up",
			item:"div#activity-item",
			page:"div#page"
		})
	})
})