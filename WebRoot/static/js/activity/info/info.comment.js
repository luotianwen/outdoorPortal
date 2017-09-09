/**
 * 开始加载评价信息
 */
function loadEvaluates() {
	// 将标识改为正在加载
	is_auto_load_evaluate = false;

	// 操作加载样式
	updateLoadMoreEvaluateCss();

	// 请求数据
	loadEvaluateData();

}

/**
 * 操作加载样式
 */
function updateLoadMoreEvaluateCss() {
	$("div.comment-more").html('<img src="static/images/hw_img/loading-2.gif" class="iconimg" />&nbsp;加载中...');
}

/**
 * 请求加载数据
 */
function loadEvaluateData(num) {
	var searchObj={};
	if(searchData(searchObj)){
		// 发送请求
		$.post("activeComments/loadEvaluateData.json", searchObj, function(data) {
			if(data.RESPONSE_STATE == "200"){
				dto = data.RESPONSE_DATA;
				
				// 向页面写入满意度数据
				writeSatisfactionData(dto);
				
				// 写入最新评价数据
				if(dto.findNe){
					writeNewDatas(dto);
				}
				
				// 写入最热评价数据
				if(dto.findHe){
					writeHotDatas(dto);
				}
				
				// 更新总页数和当前页
				updatePageData(dto);
				
				// 加载次数
				loadNum++;
			}else{
				$("div.comment-more").html("加载失败,点击重新加载...").on("click",function(){
					updateLoadMoreEvaluateCss();
					loadEvaluateData();
					$("div.comment-more").off("click");
				});
			}
		});
	}else{
		layer.msg("暂无评价信息!",{icon:5});
	}
		
}

/**
 * 封装查询数据
 */
function searchData(searchObj){
	isSearch = false;
	searchObj["aId"]=activeId;
	searchObj["nE.pageSize"]=nEShowCount;
	searchObj["hE.pageSize"]=hEShowCount;
	
	if(nCurrentPage < nTotalPage){
		isSearch = true;
		searchObj["findNe"] = true;
		searchObj["nE.currentPage"]=nCurrentPage+1;
	}
	if(hCurrentPage < hTotalPage){
		isSearch = true;
		searchObj["findHe"] = true;
		searchObj["hE.currentPage"]=hCurrentPage+1;
	}
	
	return isSearch;
}

/**
 * 更新总页数和当前页
 */
function updatePageData(dto){
	
	if(dto.findNe){
		nCurrentPage = dto.nE.currentPage;
		nTotalPage = dto.nE.totalPage;
	}
	
	if(dto.findHe){
		hCurrentPage = dto.hE.currentPage;
		hTotalPage = dto.hE.totalPage;
	}
	
	if(nCurrentPage == nTotalPage && hCurrentPage == hTotalPage){
		$("div.comment-more").html("已是最后一页");
	}else if(nTotalPage == 0 && hTotalPage == 0){
		$("div.comment-more").html("暂无评价信息");
	}else{
		$("div.comment-more").html("加载更多<i class='iconfont'></i>").on("click",function(){
			updateLoadMoreEvaluateCss();
			loadEvaluateData();
			$("div.comment-more").off("click");
		});
	}
	
}


/**
 * 向页面写入数据
 * @param dto
 */
function writeSatisfactionData(dto){
	if(loadNum > 0){
		return;
	}
	
	str = '<div class="comment-groom" style="display:none;">'
			+'<ul>'
			+'<li class="percent">'
			+'	<div class="per-num">'+dto.satisfaction+'%</div>'
			+'<div class="per-title">参加后满意度</div>'
			+'<div class="per-amount">'+dto.evaluateNum+'参与者进行了评价</div>'
			+'</li>'
			/*+'<li class="groom-content">'
			+'<dl>'
			+'<dt>'
			+'	<div class="groom-content-userImage">'
			+'		<img src="static/images/hw_img/hw_90.jpg" width="90" height="90" />'
			+'	</div>'
			+'	<div class="groom-content-userName">Baymax</div>'
			+'	<div class="groom-content-commentNum">116人有相似评价</div>'
			+'</dt>'
			+'<dd>不错！很好玩，领队人很好。</dd>'
			+'	</dl>'
			+'</li>'
			+'<li class="groom-content">'
			+'<dl>'
			+'<dt>'
			+'<div class="groom-content-userImage">'
			+'	<img src="static/images/hw_img/hw_90.jpg" width="90" height="90" />'
			+'	</div>'
			+'	<div class="groom-content-userName">Baymax</div>'
			+'	<div class="groom-content-commentNum">116人有相似评价</div>'
			+'</dt>'
			+'<dd>不错！很好玩，领队人很好。</dd>'
			+'</dl>'
			+'</li>'*/
			+'</ul>'
			+'</div>';
	
	$("div#4F").after(str);
	$("div.comment-groom").fadeIn();
	
}

/**
 * 写入最新评价数据
 */
function writeNewDatas(dto){
	
	var str = '',
		res = dto.nE.resultList;
	
	if(res.length == 0){
		$("div.comment-timeline").hide();
		return;
	}
	for(var i=0,len=res.length;i<len;i++){
		var comment = res[i];
		str	+= '<li style="display:none;">'
			+'	<h4 class="line-time">'+comment.interval+'</h4>'
			+'	<p class="line-content">'
			+'		<a target="_blank" href="activeComments/comment/'+comment.co_id+'.html" >'+comment.content +'</a>'
			+'	</p>'
			+'	<p class="line-foot">来自于 '+comment.uName+'</p>'
			+'	<div class="line-dot item-rainbow-'+(i+1)+'"></div>'
			+'</li>';
	}
	
	$("ul.comment-timeline-list").append(str);
	$("ul.comment-timeline-list>li").fadeIn();
	
	
	
}

/**
 * 写入最热评价数据
 * @param dto
 */
function writeHotDatas(dto){
	var str="",
	res = dto.hE.resultList;
	
	if(res.length == 0){
		$("div.comment-list").hide();
		return;
	}
	
	for(var i=0,len=res.length;i<len;i++){
		var comment = res[i],
			dyh = "'",
			commentTime =  comment.commentTime;
		str += '<li class="item-rainbow-0'+(i+1)+'" style="display:none;">'
				+'<div class="user-image">'
				+'	<img src="'+comment.uHeadImg+'" width="90" height="90" />'
				+'</div>'
				+'<div class="user-name-info">'
				+'<span class="pro-info">'+comment.uName+'</span><span class="user-time">'+commentTime.substring(0,commentTime.indexOf(" "))+'</span>'
				+'</div>'
				+'<dl class="user-comment">'
				+'	<dt class="user-comment-content">'
				+'		<p class="content-detail">'
				+'			<a target="_blank" href="activeComments/comment/'+comment.co_id+'.html" >'+comment.content+'</a>'
				+'	</p>';
				
		if(i==0){
			str += '	<div class="content-img">'
				+'		<img src="static/images/hw_img/hw_150.jpg" width="150" height="150" />'
				+'		<img src="static/images/hw_img/hw_150.jpg" width="150" height="150" />'
				+'		<img src="static/images/hw_img/hw_150.jpg" width="150" height="150" />'
				+'		<img src="static/images/hw_img/hw_150.jpg" width="150" height="150" />'
				+'	</div>';
		}
				
		
		str += 	'</dt>'
				+'<dd class="user-comment-self-input showIn">'
				+'	<div class="input-block">'
				+'		<input type="text" placeholder="回复楼主(最多100字)" maxLength="100" /> '
				+'		<a class="answer-btn" href="javascript:void(0)" onclick="backComment('+dyh+comment.co_id+dyh+',this)">回复</a>'
				+'	</div>'
				+'</dd>';
				
				for(var j=0,blen=comment.acbs.length;j<blen;j++){
					var back = comment.acbs[j];
					str +=   '<dd class="user-comment-answer">'
							+'	<img class="self-image" alt="" src="'+back.uHeadImg+'">'
							+'		<p>'
							+'			'+back.content+'<span class="answer-user-name"> - '+back.uName+'</span>'
							+'		</p>'
							+'</dd>';
				}
				str += '</dl>'
				+'</li>';
	}
	
	$("ul.comment-box-list").append(str);
	$("ul.comment-box-list>li").fadeIn();
	
	
}

/**
 * 回复楼主
 * @param coId
 */
function backComment(coId,obj){
	var val = $(obj).prev().val().trim();
	if(val == ""){
		$(obj).prev().focus();
		return;
	}
	layer.confirm("确认提交回复内容吗？",{icon:3,title:"回复提醒"},function(index){
		layer.close(index);
		layer.msg("回复中,请稍等...",{icon:16,shade:0.3,time:5*1000});
		$.post("activeCommentBack/back.json",{co_id:coId,content:val},function(data){
			if(data.RESPONSE_STATE == "200"){
				layer.msg("回复成功",{icon:1,time:1000});
				
				// 生成回复数据
				generateBackCotent(data,obj);
				
				// 清空回复数据 
				$(obj).prev().val("");
			}else{
				layer.msg(data.ERROR_INFO,{icon:0});
			}
		});
	});
}

/**
 * 生成回复数据
 * @param data
 */
function generateBackCotent(data,obj){
	
	str =   '<dd class="user-comment-answer">'
			+'	<img class="self-image" alt="" src="'+data.uHeadImg+'">'
			+'		<p>'
			+'			'+data.content+'<span class="answer-user-name"> - '+data.uName+'</span>'
			+'		</p>'
			+'</dd>';
	$(obj).closest("dl").append(str);
}