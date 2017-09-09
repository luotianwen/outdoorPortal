$(function(){
	$.fn.loadcomment=function(){
		this.init();
	}
	
	$.fn.loadcomment.prototype={
		init:function(){
			this.create();
			this.post(this.$.currentPage);
			this.bind();
		},
		bind:function(){
			this.reply();
		},
		reply:function(){
			var _this = this;
			_this.$.reply.on("click",function(){
				layer.confirm("是否确认提交?",{icon:3},function(index){
					layer.close(index);
					layer.msg("提交中...",{icon:16,shade:0.7,time:10*1000});
					$.post("activeCommentBack/save.json",{
						content:_this.$.content.val(),
						id:_this.$.id
					},function(data){
						if(data.RESPONSE_STATE == "200"){
							layer.msg("回复成功",{icon:1,shade:0.7,time:0.8*1000},function(){
								_this.post(_this.$.currentPage);
							});
						}else{
							layer.alert(data.ERROR_INFO,{icon:0});
						}
					});
				})
			})
		},
		create:function(){
			this.$={
				comment:$("dl#user_comment"),
				id:$("dl#user_comment").attr("co-id"),
				infos:"dd#user_comment_answer",
				page:$("dd#page"),
				reply:$("a#reply"),
				content:$("input[name=content]"),
				currentPage:1
			}
		},
		post:function(page){
			var _this = this;
			$.post("activeCommentBack/loadReply.json",{id:this.$.id,page:page},function(data){
				if(data.RESPONSE_STATE == "200"){
					_this.success(data.page);
				}else{
					layer.alert(data.ERROR_INFO,{icon:0});
				}
			})
		},
		success:function(page){
			var res = page.resultList,
				str = "";
			for(var i=0,len=res.length;i<len;i++){
				var item = res[i];
				str +='<dd class="user-comment-answer" id="user_comment_answer">'
					+'<img class="self-image" alt="" src="'+item.headImg+'">'
					+'<p>'
					+item.content+'<span class="answer-user-name"> - '+item.uName+'</span>'
					+'</p>'
					+'</dd>';

			}
			this.$.comment.find(this.$.infos).remove();
			this.$.page.before(str);
			this.page(page);
		},
		page:function(page){
			var _this = this;
			this.$.currentPage = page.currentPage;
			// 分页
		    laypage({
		        cont: this.$.page, // 容器。值支持id名 
		        pages: page.totalPage, // 通过后台拿到的总页数
		        curr:  page.currentPage,// 当前页
		        //skip: true, //是否开启跳页
		        groups: 3, //连续显示分页数
		        skin: '#FF8A01',
		        first: 1,
		        prev:"<",
		        next:">",
		        last: page.totalPage, //在尾页追加总页数。
		        jump: function(e, first){ //触发分页后的回调
		        	 if(!first){
		        		_this.post(e.curr);
		             }
		        }
		    });
		}
	}
	
	new $.fn.loadcomment();
})