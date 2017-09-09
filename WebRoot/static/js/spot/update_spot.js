$(function(){
	jQuery("#picScroll-left").slide({mainCell:".bd ul",autoPage:true,effect:"left",autoPlay:true,vis:3,trigger:"click"});
	jQuery("#tuijian").slide({mainCell:".bd ul",autoPage:true,effect:"left",autoPlay:true,vis:3,trigger:"click"});
	$("#footer").css("position","");
	
	page(1);
	//门票
	$.post("spot/getProductBySpotId.json",{spotid:sid},function(data){
	 $("#ticket-list").html(data);
	});
	//点评
	$.post("spotcomment/project.json",function(data){
		var html = "";
		for(var i=0;i<data.length;i++){
			html += '<dt>*'+data[i].name+'</dt>'+
			        '<dd data_id="'+data[i].id+'" data_name="'+data[i].name+'" class="star">'+
			        '    <img src="static/images/hw_img/star3.png">'+
			        '    <img src="static/images/hw_img/star3.png">'+
			        '    <img src="static/images/hw_img/star3.png">'+
			        '    <img src="static/images/hw_img/star4.png">'+
			        '    <img src="static/images/hw_img/star4.png">'+
			        '</dd>';
		}
		
		$("#project").html(html);
	})
	
	//点评星级
	$("dl#project").on("click","dd img",function(){
		var $this = $(this);
		$this.closest("dd").find("img").each(function(index){
			if(index<=$this.index()){
				$(this).attr("src","static/images/hw_img/star3.png");
			}else{
				$(this).attr("src","static/images/hw_img/star4.png");
			}
		})
		
		var count = $("dl#project").find("dd img[src='static/images/hw_img/star3.png']").length/$("dl#project").find("dd").length
		
		$("dd#all").find("img").each(function(index){
			if(index<=count-0.5){
				$(this).attr("src","static/images/hw_img/star3.png");
			}else{
				$(this).attr("src","static/images/hw_img/star4.png");
			}
		})
		
	})
	
	//删除点评图片
	$("ul#commentimg").on("click","li div#delete",function(){
		layer.confirm("是否删除?",{icon:3},function(index){
			layer.close(index);
			$(this).remove();
		});
	})
	
	//发表点评
	$("#submit").on("click",function(){
		if($("#centent").val()==""){
			layer.alert("点评内容不能为空！",{icon:0,title:"提醒"})
			return;
		}
		
		var all = $("dd#all").find("img[src='static/images/hw_img/star3.png']").length;
		
		if(all==0){
			layer.alert("必须选择总体评价星级！",{icon:0,title:"提醒"})
			return;
		}
		
		var project;
		
		var str = "";
		var count = 0;
		$("#project").find("dd").each(function(){
			var $this = $(this);
			var star = $this.find("img[src='static/images/hw_img/star3.png']").length;
			if(star>0){
				if(str==""){
					str += "commentProject["+count+"].pid="+$this.attr("data_id")+"&commentProject["+count+"].num="+star+"&commentProject["+count+"].cname="+$this.attr("data_name");
				}else{
					str += "&commentProject["+count+"].pid="+$this.attr("data_id")+"&commentProject["+count+"].num="+star+"&commentProject["+count+"].cname="+$this.attr("data_name");
				}
				count++;
			}
		})
		
		count = 0;
		$("ul#commentimg").find("li").each(function(){
			var $this = $(this);
			if(str==""){
				str += "commentPho["+count+"].pho="+$this.find("img").attr("data-src");
			}else{
				str += "&commentPho["+count+"].pho="+$this.find("img").attr("data-src");
			}
		})
		
		$.post("spotcomment/saveComment.json?"+str,{content:$("#centent").val(),num:all,sid:sid},function(data){
			if(data.RESPONSE_STATE=="200"){
				$("#centent").val("");
				$("dd#all").find("img").each(function(index){
					if(index>2){
						$(this).attr("src","static/images/hw_img/star4.png");
					}
				})
				
				$("dl#project").find("dd img").each(function(index){
					if(index>2){
						$(this).attr("src","static/images/hw_img/star4.png");
					}
				})
				
				$("ul#commentimg").find("li").remove();
				
				layer.msg("点评成功。",{icon:1,time:1*1000,shade:0.3})
			}else{
				layer.alert("点评失败，请稍后再试！",{icon:0,title:"提醒"})
			}
		})
		
	})
	
	//用户是否登录
	if($(".head_user").length>0){
		$("#uName").html($(".head_user").find("a.user_link").attr("title"));
		$("#uImg").html('<img width="90" height="90" src="'+$(".head_user").find("a.user_link").find("img").attr("src")+'">');
	}else{
		$("#uName").remove();
		$("#uImg").remove();
		$("#addimg").remove();
	}
	
	$(window).on("scroll",function(){
		if($(this).scrollTop() >= $("div#tabs").offset().top)
		{
			$("div#scenic-tabs").addClass("scenic-tabs-top");
		}else{
			$("div#scenic-tabs").removeClass("scenic-tabs-top");
		}
	});
})

function selected(_this,adv){
	var _top=$("#"+adv).offset().top-60;
	//$(window).scrollTop(_top);
	$("body,html").animate({scrollTop:_top+"px"});
	$(".tp").removeClass("selected");
	$(_this).addClass("selected");
}
function init(){
	map = new AMap.Map('container', {
		resizeEnable: true,
		center: lnglat,
		zoom: 15
	});
	 map.setStatus({dragEnable: true,zoomEnable: false});
	var marker = new AMap.Marker({
		position: lnglat
	});
	marker.setMap(map);
	var content='<div class="info-title">'+spotname+'</div><div class="info-content">' +
		'<img src="'+spoturl+'" style="zoom:1;overflow:hidden;width:80px;height:60px;margin-left:3px;"/>' +
		spotaddress+'<br/>' +spotlevelname+
		'</div>';
	var  infowindow1 = new AMap.AdvancedInfoWindow({
		content: content,
		offset: new AMap.Pixel(0, -30)
	});
   if(loads)
	infowindow1.open(map,lnglat);

}
//景点评论分页
function page(curr){
    $.getJSON('spotcomment/spotComment.json', {
        page: curr,id:sid
    }, function(data){
    	if(data.spotComment.length>0){
    		commentShow(curr,data);
    	}
    });
};

function commentShow(curr,data){
    //替换评论内容
	var commentHtml = "";
	var spotComment = data.spotComment;
	if(spotComment!=null&&spotComment.length>0){
		commentHtml += "<div class='scenic-comment boxw clearfix'><ul>";
		for(var i=0,len=spotComment.length;i<len;i++){
			commentHtml += "<li>"+
					       "     <div class='user-image'>" +
					       "		<img width='90' height='90' src='"+spotComment[i].userpho+"'>" +
					       "	 </div>"+
					       "     <div class='user-comment'>"+
					       "         <div class='user-name'>"+spotComment[i].username+"<span class='date'>"+spotComment[i].sdate+"</span></div>"+
					       "         <div class='content-detail'>"+spotComment[i].content+"</div>";
			
			for(var j=0,jlen=spotComment[i].commentPho.length;j<jlen;j++){
				if(j==0){
					commentHtml += "<div class='content-img'>";
				}
				commentHtml += "<img width='150' height='150' src='"+spotComment[i].commentPho[j].pho+"'>";
			    if(j==jlen-1){
			    	commentHtml += "</div>";
			    }
			}
		    commentHtml += "</div></li>";
							
		}
		commentHtml += "</ul><div id='commentPage' style='margin-top: 30px;text-align: center;height: 50px;'></div></div>";
	}
	$("#comment").html(commentHtml);
	
    //显示分页
    laypage({
        cont: 'commentPage', //容器。值支持id名、原生dom对象，jquery对象。【如该容器为】
        pages: data.page.totalPage, //通过后台拿到的总页数
        curr: curr, //当前页
        prev: '<', //若不显示，设置false即可
        next: '>', //若不显示，设置false即可
        skin: '#ff8a01',
        jump: function(obj, first){ //触发分页后的回调
            if(!first){ //点击跳页触发函数自身，并传递当前页：obj.curr
            	page(obj.curr);
            }
        }
    });

}
