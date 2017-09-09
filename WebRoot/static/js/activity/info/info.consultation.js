/**
 * 加载提问数据
 * @returns
 */
function loadConsultation(){
	// 关闭滚动轴触发查询事件
	is_auto_load_consultation = false;
	
	// 操作加载样式
	updateLoadMoreConsultationCss();
	
	// 请求数据
	loadConsultationDatas();
}


/**
 * 操作加载样式
 */
function updateLoadMoreConsultationCss() {
	$("div.more-question").html('<img src="static/images/hw_img/loading-2.gif" class="iconimg" />&nbsp;加载中...');
}

/**
 * 请求数据
 * @returns
 */
function loadConsultationDatas(){
	var search = {};
	if(isSearchConsultation(search)){
		$("div.more-question").off("click");
		$.post("activeConsultation/loadMore/"+search.method+".do",search,function(data){
			if(data.RESPONSE_STATE == "200"){
				
				// 向页面写入提问数据
				writeConsultationData(data.page,search.method);
				
				// 更改样式绑定查询事件
				updateConsultationPageData(data.page,search.method);
			}else{
				$("div.more-question").text("加载失败,点击重新加载...").on("click",function(){
					updateLoadMoreConsultationCss();
					loadConsultationDatas();
					$("div.more-question").off("click");
				});
			}
		});
	}
}

/**
 * 向页面写入提问数据
 * @param page
 */
function writeConsultationData(page,method){
	if(page.resultList.length == 0){
		$("div#question_order").hide();
		$("div#more_question").text("暂无数据,快来提问吧");
		return;
	}
	
	var str="";
	for(var i=0,len=page.resultList.length;i<len;i++){
		var res = page.resultList[i],
		time=res.ac_create_time,
		_praises_class = res.acp_id==null?"hand-block":"hand-block hand-current";
		
	    str += '<li ctype="'+method+'">'
			    +'<div class="'+_praises_class+'" praises="'+res.ac_id+'" id="praises"><i>赞</i></div>'
			    +'<div class="hand-number">'+res.ac_praises+'</div>'
				+'<div class="question-title">'
				+'<h3>'+res.ac_comment+'</h3>'
				+'<span class="question-date">'+time.substring(0,time.indexOf(" "))+'</span>'
				+'</div>'
				+'<div class="answer-content">'+res.ac_reply_comment+'</div>'
				+'</li>';
	}
	
	if(method == _CONST_NEW){
		$("ul#question_list>li[ctype="+_CONST_PRAISES+"]").hide();
	}else{
		$("ul#question_list>li[ctype="+_CONST_NEW+"]").hide();
	}
	
	$("ul#question_list").append(str).fadeIn();
}

/**
 * 更改样式绑定查询事件
 */
function updateConsultationPageData(page,method){
	if(method == _CONST_NEW){
		cnCurrentPage=page.currentPage;
		cnTotalPage=page.totalPage;
	}else{
		cpCurrentPage=page.currentPage;
		cpTotalPage=page.totalPage;
	}
	
	if(page.currentPage == page.totalPage){
		$("div.more-question").text('已经是最后一页');
	}else if(page.totalPage == 0){
		$("div.more-question").text('暂无数据');
	}else if(page.currentPage < page.totalPage){
		$("div.more-question").html('加载更多<i class="iconfont"></i>').on("click",function(){
			updateLoadMoreConsultationCss();
			loadConsultationDatas();
			$("div.more-question").off("click");
		});
	}
}

/**
 * 查询数据初始化
 * @param search
 * @returns
 */
function isSearchConsultation(search){
	isSearch = false;
	
	if(consultation_default_show == _CONST_NEW && cnCurrentPage < cnTotalPage){
		isSearch = true;
		search["currentPage"] = cnCurrentPage+1;
		search["pageSize"] = cnShowCount;
		search["activeId"] = activeId;
		search["method"] = _CONST_NEW;
	}else{
		$("div.more-question").text('已经是最后一页');
	}
	if(consultation_default_show != _CONST_NEW && cpCurrentPage < cpTotalPage){
		isSearch = true;
		search["currentPage"] = cpCurrentPage+1;
		search["pageSize"] = cpShowCount;
		search["activeId"] = activeId;
		search["method"] = _CONST_PRAISES;
	}else{
		$("div.more-question").text('已经是最后一页');
	}
	
	return isSearch;
}

$(function(){

	// 绑定未来点赞事件
	$("ul#question_list").on("click","div#praises",function(){
		if(isPostPraises){
			return;
		}
		isPostPraises = true;
		var _self = $(this);
		var type = _self.hasClass("hand-current")?"delete":"add";
		
		$.post("activeConsultation/praises.json",{
			type:type,
			ac_id:_self.attr("praises")
		},function(data){
			if(data.RESPONSE_STATE == "200"){
				var next = _self.next();
				if(type == "delete"){
					_self.removeClass("hand-current");
					next.text(parseInt(next.text())-1);
				}else{
					_self.addClass("hand-current");
					next.text(parseInt(next.text())+1);
				}
			}else if(data.RESPONSE_STATE  == "500"){
				layer.msg(data.ERROR_INFO,{icon:0});
			}
			isPostPraises = false;
		});
		
	})
	
	//监控表单数据
	$.fn.watch = function(callback) {
	 return this.each(function() {
	     //缓存以前的值  
	     $.data(this, 'originVal', $(this).val());  
	     //event  
	     $(this).on('keyup paste', function() {
	         var originVal = $(this, 'originVal');  
	         var currentVal = $(this).val();  

	         if (originVal !== currentVal) {
	             $.data(this, 'originVal', $(this).val());
	             callback(currentVal);  
	         }  
	     });  
	 });  
	};
	
	
	// 咨询问答内容排序{时间和最有帮助}
	$("div#order_block a").on("click",function(){
		$("div#order_block a").removeClass("current");
		$(this).addClass("current");
		
		consultation_default_show = $(this).attr("ctype");
		
		if(consultation_default_show == _CONST_NEW){
			// 判断是否还有数据
			isCheckHasPage();
			
			// 第一次切换加载数据
			loadnum = $(this).attr("loadnum");
			if(loadnum > 0){
				$("ul#question_list>li[ctype="+_CONST_PRAISES+"]").hide();
			}else{
				$(this).attr("loadnum","1");
				
				updateLoadMoreConsultationCss();
				loadConsultationDatas();
			}
			$("ul#question_list>li[ctype="+_CONST_NEW+"]").fadeIn();
		}else{
			// 判断是否还有数据
			isCheckHasPage();
			
			$("ul#question_list>li[ctype="+_CONST_NEW+"]").hide();
			$("ul#question_list>li[ctype="+_CONST_PRAISES+"]").fadeIn();
		}
	})
	
	
	// 提交 提问数据
	$("div#consultation").on("click",function(){
		var $this = $(this);
		var val = $this.prev().val().trim();
		if(val == ""){
			$this.prev().focus();
			return;
		}
		
		layer.confirm("您确认提交此问题吗？",{btn:["提交","取消"],icon:3},function(index){
			layer.close(index);
			layer.msg("提交中...",{icon:16,shade:0.3,time:5*1000});
			$.post("activeConsultation/sc.do",{
				activeId:activeId,
				value:val
			},function(data){
				if(data.RESPONSE_STATE == "200"){
					$this.prev().val("");
					layer.msg("提问成功,等待领队回复!",{icon:1,time:1.2*1000});
				}else{
					layer.msg(data.ERROR_INFO,{icon:0});
				}
			});
		});
		
	});
	
	
	// 咨询问答搜索引擎
	$("input#search_consult_input").watch(function(val){
		$("ul#solr_search_consulation_list").remove();
		if(val != ""){
			loadSearchConsulation(val);
		}else{

			$("div#question_order").show();
			$("ul#question_list").show();
			$("div#more_question").show();
			$("div#question_none").hide();
		}
		$("body,html").animate({
			scrollTop:($("div#5F").offset().top)+"px"
		})
	})
})

function loadSearchConsulation(val){

	$.post("activeConsultation/selectConsultation.json",{
		activeId:activeId,
		keyword:val,
		type:consultation_default_show,
		leaderId:leaderId
	},function(data){
		
		if(data.RESPONSE_STATE == "200"){
			$("div#question_order").hide();
			$("ul#question_list").hide();
			$("div#more_question").hide();
			
			var page = data.page;
			if(page.pageCount == 0){
				$("div#question_none").show();
			}else{
				$("div#question_none").hide();
				var str='<ul class="question-list" id="solr_search_consulation_list">';
				for(var i=0,len=page.result.length;i<len;i++){
					var res = page.result[i],
					time=res.ac_create_time,// 提问时间
					_praises_class = res.acp_id==null?"hand-block":"hand-block hand-current";// 当前用户是否点赞
					
				    str += '<li>'
						    +'<div class="'+_praises_class+'" praises="'+res.ac_id+'" id="praises"><i>赞</i></div>'
						    +'<div class="hand-number">'+res.ac_praises+'</div>'
							+'<div class="question-title">'
							+'<h3>'+res.ac_comment+'</h3>'
							+'<span class="question-date">'+time.substring(0,time.indexOf(" "))+'</span>'
							+'</div>'
							+'<div class="answer-content">'+res.ac_reply_comment+'</div>'
							+'</li>';
				}
				str += "</ul>";

				$("div#question_content").append(str).fadeIn();
			}
		}else{
			$("div#question_none").show();
		}
	});
}

// 切换[最有帮助	or	最新]判断是否还有数据
function isCheckHasPage(){
	if(cnCurrentPage < cnTotalPage || cpCurrentPage < cpTotalPage){
		$("div.more-question").off("click");
		$("div.more-question").html('加载更多<i class="iconfont"></i>').on("click",function(){
			updateLoadMoreConsultationCss();
			loadConsultationDatas();
			$("div.more-question").off("click");
		});
	}else{
		$("div.more-question").text('已经是最后一页');
	}
}