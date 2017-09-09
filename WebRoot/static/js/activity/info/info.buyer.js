
$(function(){

	/**
	 * 初始加载报名列表信息
	 */
	$.fn.loadBuyer=function(option){
		this.options=$.extend({},$.fn.loadBuyer.defaults,option);
		
		// 将标识改为正在加载
		is_auto_load_buyer = false;
		
		// 加载报名列表信息
		this.post(this.options.currentPage);
	}
	
	/**
	 * 默认属性
	 */
	$.fn.loadBuyer.defaults={
		target:"",
		pageId:"buyer_page",
		currentPage:1,
		tableId:"buyer_table"
	}
	
	/**
	 * 加载报名列表信息
	 */
	$.fn.loadBuyer.prototype={
		post:function(cp,fn){
			var _this = this; 
			$.post("huodong/activeBuyer.json",{
				currentPage:cp,
				activityId:activeId
			},function(res){
				if(res.RESPONSE_STATE == "200"){
					_this.success(res);
				}else{
					_this.error(res);
				}
				if(typeof(fn) == "function"){
					fn();
				}
			})
		},
		success:function(res){
			var _this = this;
			// 向页面写入数据
			_this.writeData(res);
		},
		error:function(res){
			var str='<tr>'
				+'<td colspan="5">'+res.ERROR_INFO+'</td>'
				+'</tr>';
			$("#"+this.options.tableId+">tbody").html(str);
			
			// 再次开启监听滚动轴查询
			is_auto_load_buyer = true;
		},
		writeData:function(res){
			var page = res.page,_this=this;
			
			if(page.totalPage == 0){
				var str='<tr>'
					+'<td colspan="5">暂无</td>'
					+'</tr>';
				$("#"+_this.options.tableId+">tbody").html(str);
			}else{
				var str="",result=page.resultList,item="";
				
				for(var i=0,len=result.length;i<len;i++){
					item = result[i];
					str += '<tr>'
						+'<td>'+item.user_name+'</td>'
						+'<td>'+item.asu_create_time+'</td>'
						+'<td>'+item.peoplenum+'</td>'
						+'<td>'+item.asu_order_area+'</td>'
						+'<td>'+item.asu_state+'</td>'
						+'</tr>';
				}
				$("#"+_this.options.tableId+">tbody").html(str);

				// 将视野移至报名列表顶部
				$("body,html").animate({scrollTop:($(_this.options.target).offset().top-(_event_tabs_height+20))+"px"});
				
				// 分页
				_this.page(res);
			}
		},
		page:function(res){
			var _this = this,page = res.page;
			// 分页
		    laypage({
		        cont: _this.options.pageId, // 容器。值支持id名 
		        pages: page.totalPage, // 通过后台拿到的总页数
		        curr:  page.currentPage,// 当前页
		        groups: 3, //连续显示分页数
		        skin: '#FF8A01',
		        first: 1,
		        prev:"<",
		        next:">",
		        last: page.totalPage, //在尾页追加总页数。
		        jump: function(obj, first){ //触发分页后的回调
	                if(!first){ //点击跳页触发函数自身，并传递当前页：obj.curr
	                	layer.msg("加载中...",{icon:16,shade:0.5,time:5*1000})
	                	_this.post(obj.curr,function(){
	                		layer.closeAll('dialog');
	                	});
	                }
	            }
		    });
		}

	}

})