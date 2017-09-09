$(function(){
	$.fn.comment=function(){
		this.init();
	}
	
	$.fn.comment.prototype={
		init:function(){
			// 绑定鼠标滚动事件，监听滚动轴变化延迟加载评论
			this.bindScroll();
			// 标识是否已经加载过评论信息
			this.isLoadData=false;
			// 加载评论回复
			this.bindCommentLoadReply();
			// 回复评论
			this.replyComment();
			// 评价游记
			this.bindComment();
		},
		/**
		 * 评价游记
		 */
		bindComment:function(){
			$("span#comment-travels-btn").on("click",function(){
				var $this = $(this),
					$textarea = $("textarea#comment-content");
				if($textarea.val().trim()==""){
					layer.msg("请输入内容后再做评论操作！",{icon:6,shade:0.6,time:1*1000},function(){
						$textarea.trigger("focus");
					})
					return;
				}else if($textarea.val().length > 100){
					
				}
				
				layer.confirm("是否确认提交评价内容？",{icon:3,shade:0.6},function(index){
					layer.close(index);
					layer.msg("评价中...",{icon:16,shade:0.6,time:10*1000});
					$.post("travels/comment.json",{
						id:travelsId,
						comment:replaceEmoji($textarea.val())
					},function(data){
						if(data){
				
							
							if(data.RESPONSE_STATE == "200"){
								layer.msg("评价成功!")
								$textarea.val("");
								var comment = data.comment;
								var str ='<div class="my-reply" id="my-reply">'
									+'<img class="headP" src="'+comment.uHeadImg+'" />'
									+'<div class="headP1">'
									+'<p>'
									+comment.tc_content
									+'</p>';
								
								// 评论图片
								if(comment.tc_img){
									str +='<div><img src="'+comment.tc_img+'" alt="#" class="map"/></div>';
								}
								
								// 回复框
								str +='</div>'
									+'<div class="import1">'
									+'<input type="text" maxlength="100" placeholder="回复" name="username" class="import"/>'
									+'<input type="button" name="button" class="button" value="回复" userId="'+comment.tc_createUser+'" commentId="'+comment.tc_id+'"  id="replyCommentButton" />'
									+'</div>';
								
								// 评论人信息
								str +='<div class="comment-buttom">'
									+'<span>'+comment.uName+'</span><span class="marT">'+comment.tc_createTime+'</span>'
									+'</div>'
									+'</div>';

								if($("#travelsComment").css("display")=="none"){
									$("#travelsComment").css("display","block");
								}
								
								$("div#showCommentDiv").append(str);
							}else{
								layer.msg(data.ERROR_INFO,{icon:0,shade:0.6,time:2*1000})
							}
						}
					})
				})
			})
		},
		/**
		 * 绑定鼠标滚动事件，监听滚动轴变化延迟加载评论
		 */
		bindScroll:function(){
			var _this = this;
			$(window).on("scroll",function(){
				var _window_scrollTop=$(this).scrollTop()+$(window).height(),
					$comment = $("div#travelsComment"),
					_comment_scrollTop = $comment.offset().top;
				if(_window_scrollTop >= _comment_scrollTop && !_this.isLoadData){
					_this.loadComment(1);
				}
			});
		},
		/**
		 * 加载评论
		 * @param page
		 */
		loadComment:function(page){
			// 已经加载
			this.isLoadData=true;
			var _this = this;
			$.post("travels/loadComments.json",{
				id:travelsId,
				page:page
			},function(data){
				if(data.RESPONSE_STATE == "200"){
					// 分页
					_this.page(data.page,_this);
					
					// 向页面写入数据
					_this.writePageData(data.page);
				}
			})
		},
		writePageData:function(page){
			var str="",
				result=page.resultList;
			for(var i=0,len=result.length;i<len;i++){
				var comment =  result[i];
				str +='<div class="my-reply" id="my-reply">'
					+'<img class="headP" src="'+comment.uHeadImg+'" />'
					+'<div class="headP1">'
					+'<p>'
					+comment.tc_content
					+'</p>';
				
				// 评论图片
				if(comment.tc_img){
					str +='<div><img src="'+comment.tc_img+'" alt="#" class="map"/></div>';
				}
				
				// 回复框
				str +='</div>'
					+'<div class="import1">'
					+'<input type="text" placeholder="回复" name="username" class="import"/>'
					+'<input type="button" name="button" class="button" value="回复" userId="'+comment.tc_createUser+'" commentId="'+comment.tc_id+'"  id="replyCommentButton" />'
					+'</div>';
				// 评论回复信息
				if(comment.replys!=null&&comment.replys!=""){
					$.each(comment.replys,function(i,reply){
						str +='<div class="coatImg">'
							+'<img src="'+reply.uHeadImg+'" alt="'+reply.uName+'" class="favicon"/>'
							+'<span class="critic">'+reply.tcr_replyContent+'<em class="line">--</em><span class="line">'+reply.uName+'</span></span>'
							+'</div>';
					})
				}
				
				// 查看更多评论<img src="static/images/hw_img/a18.png" alt="#"/><img src="static/images/hw_img/a19.png" alt="#"/>
				if(comment.replysCount>5){
					str +='<p class="shrink">'
						+'<span page="1" commentId="'+comment.tc_id+'" id="loadMoreReply">查看更多回复<img src="static/images/hw_img/a18.png" alt="#"/></span>'
						+'</p>';
						
				}
				
				// 评论人信息
				str +='<div class="comment-buttom">'
					+'<span>'+comment.uName+'</span><span class="marT">'+comment.tc_createTime+'</span>'
					+'</div>'
					+'</div>';
			}
			if(str!=""){
				$("div#showCommentDiv").html(str);
			}else{
				$("#travelsComment").css("display","none");
			}
		},
		/**
		 * 用户回复评论
		 */
		replyComment:function(){
			$("div#showCommentDiv").on("click","input#replyCommentButton",function(){
				var $this = $(this),
					$input = $this.prev(),
					commentId=$this.attr("commentId"),
					beUserId = $this.attr("userId");
				if($input.val().trim() == ""){
					layer.msg("请输入内容后再做回复操作！",{icon:6,shade:0.6,time:1*1000},function(){
						$input.trigger("focus");
					})
					return;
				}else if($input.val().length>100){
					layer.msg("长度不能超过100！",{icon:6,shade:0.6,time:1*1000},function(){
						$input.trigger("focus");
					})
					return;
				}
				
				layer.confirm("是否确认回复？",{icon:3,shade:0.6},function(index){
					layer.close(index);
					layer.msg("回复中...",{icon:16,shade:0.6,time:10*1000});
					$.post("travels/replyComment.json",{
						val:$input.val().trim(),
						uId:beUserId,
						cId:commentId
					},function(data){
						if(data){
							if(data.RESPONSE_STATE == "200"){
								layer.msg("回复成功")
								$input.val("");
								var str ='<div class="coatImg">'
									+'<img src="'+data.uHeadImg+'" alt="'+data.uName+'" class="favicon"/>'
									+'<span class="critic">'+data.val+'<em class="line">--</em><span class="line">'+data.uName+'</span></span>'
									+'</div>';
								var $replyDiv = $this.closest("div#my-reply");
								if($replyDiv.find("div.coatImg").length>0){
									$replyDiv.find("div.coatImg:last").after(str);
								}else{
									$this.parent().after(str);
								}
							}
						}
					})
				})
			});
		},
		/**
		 * 分页
		 * @param res
		 */
		page:function(res,_this){
	        laypage({
	            cont: 'travelsCommentPage', // 容器。值支持id名 
	            pages: res.totalPage, // 通过后台拿到的总页数
	            curr:  res.currentPage,// 当前页
	            //skip: true, //是否开启跳页
	            groups: 3, //连续显示分页数
	            skin: '#FF8A01',// 如果背景为白色，要设置css
	            first: 1,
	            prev:"<",
	            next:">",
	            last: res.totalPage, //在尾页追加总页数。
	            jump: function(obj, first){ //触发分页后的回调
	            	 if(!first){
	            		 _this.loadComment(obj.curr);
	                 }
	            }
	        });
		},
		/**
		 * 加载评论回复
		 */
		bindCommentLoadReply:function(){
			$("div#showCommentDiv").on("click","span#loadMoreReply",function(){
				var $span = $(this),
					page = $span.attr("page")*1+1,
					id = $span.attr("commentId");
					
				$.post("travels/loadCommentsReplys.json",{
					page:page,
					id:id
				},function(data){
					if(data.RESPONSE_STATE == "200"){
						var page = data.page,
							str = "";
						// 展示数据
						$.each(page.resultList,function(i,item){
							str +='<div class="coatImg">'
								+'<img src="'+item.uHeadImg+'" alt="#" class="favicon"/>'
								+'<span class="critic">'+item.tcr_replyContent+'<em class="line">--</em><span class="line">'+item.uName+'</span></span>'
								+'</div>'
						})
						$span.parent().before(str);
						
						// 更新当前页
						$span.attr("page",page.currentPage)
						
						if(page.currentPage == page.totalPage){
							$span.parent().remove();
						}
					}
				})
				
			})
		}
	}
	
	new $.fn.comment();
})