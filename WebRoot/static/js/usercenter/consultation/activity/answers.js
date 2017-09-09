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
			activityId:$this.attr("activityId")// 活动ID
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
		},
		/**
		 * 展开/收起事件
		 */
		_showOrHide:function(){
			var _this = this;
			// 给【展开/收缩】添加click事件
			_this.op.$downOrUp.on("click",function(){
				
				// 获取是否加载过数据标识
				var load = $(this).attr("load");
				if(load == 0){
					// 加载回答数据，默认第一页
					_this._load(1);
					// 设置为已经加载过
					$(this).attr("load","1");
					// 切换展开/收缩icon样式
					$(this).attr("class","up");	
				}else{
					// 展开/收缩
					_this.op.$item.slideToggle();
					
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
		_load:function(page){
			var _this = this;
			//layer.msg("加载中...",{icon:16,shade:0.5,time:10*1000});
			$.post("activeConsultation/loadConsultation.json",{
				type:this.op.replyType,
				id:this.op.activityId,
				page:page,
				userId:userId,
				loadType:'reply'
			},function(data){
				//layer.closeAll("dialog");
				if(data.RESPONSE_STATE == "200"){
					// 成功回调
					_this._success(data);
				}else{
					layer.alert(data.ERROR_INFO,{icon:0});
				}

			})
		},
		/**
		 * 成功回调
		 * @param data
		 */
		_success:function(data){
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
					+'<dd>'
					+'<div class="answers">'
					+'<em class="m-1">我</em>回复<em class="m-2">'+item.uName+'</em>：'+item.ac_reply_comment
					+'<span class="date">'+item.ac_reply_time_str+'</span>'
					+'</div>';
				
					// 遍历回复的数据
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
					str+='</dd></dl>';
				
			}
			// 赋值
			_this.op.$item.html(str);
			
			// 分页
			_this._page(data);
			//$("body,html").animate({scrollTop:$("div.my-name").offset().top-230},0)
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