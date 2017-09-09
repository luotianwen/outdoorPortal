(function ($) {
	//获取url参数
    $.getUrlParam = function (name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) return unescape(r[2]); return null;
    }
    
})(jQuery);

//当前IP所在城市
var currentCity;
$(function(){
	showCityInfo();
	var pop_box_layer;
	$("#info-link1").click(function(){
		pop_box_layer = layer.open({
			type:1,
			title:false,
			closeBtn:false,
			area:['860px','436px'],
			content:$('div#pop-box1')
		})
	});

	$("div#pop-box1").on("click","ul li a.btns",function(){
		var target = $(this).attr("data-target");
		var bool = true;
		
		if(target=="head_photo"){
			document.getElementById("travelsImage").scrollIntoView();
		}else if(target=="photo_poi"){
			if($("div#travelsContent").find("div#content-img").length>0){
				document.getElementById("content-img").scrollIntoView()
			}else{
				layer.msg("请先上传照片",{icon:0,time:1000});
				bool = false;
			}
		}else if(target=="paragraph_title"){
			if($("div#travelsContent").find("div#content-title").length==0){
				var ico = $("#ico").eq(0);
				ico.find(".a1").animate({marginLeft: "25px"}, 500);
				document.getElementById("address").scrollIntoView()
			}
		}else if(target=="cover_photo"){
			if($("div#travelsContent").find("div.ico-cover-now").length==0){
				if($("div#travelsContent").find("div#content-img").length>0){
					document.getElementById("content-img").scrollIntoView()
				}else{
					layer.msg("请先上传照片",{icon:0,time:1000});
					bool = false;
				}
			}
		}else if(target=="music"){
			document.getElementById("music").scrollIntoView()
		}
		
		if(bool){
			layer.close(pop_box_layer);
		}
		
	})
	
	$("#close1").click(function () {
		layer.close(pop_box_layer);
	});
	
	$("a#preservation").click(function(){
		$("div#travelsContent").find("div#ico a#addwordfianl").each(function(index,element){
			$(this).trigger("click");
		});
		$("div#travelsContent").find("div#ico a#updatewordfianl").each(function(index,element){
			$(this).trigger("click");
		});
		
		layer.msg("草稿保存成功",{icon:1,time:1000});
		$("body")
	});
	
	//删除游记音乐
	$("#m_delete").click(function(){
		$.ajax({
    		type : "post",
    		url : "travels/create.json",
    		data : {id:$.getUrlParam('id'),act:'update',"data.o":'music',"data.c":"","data.s":""},
    		async : false,
    		success : function(data){
            	if(data.RESPONSE_STATE=="200"){
            		$("#m-showname").html("背景音乐请选择后缀为.mp3的音乐文件");
            		$("#m_modify").css("display","none");
            		$("#m_delete").css("display","none");
            		$("#selectMusic").val("浏览");
            		$("#music_name").val("");
            		
            		var music = $("a[data-target='music_final']");
            		if($("#music_name").val()==""){
            			music.attr("data-target","music");
            			music.attr("title","前往");
            			music.attr("class","btns");
            			music.html("前往");
            			music.parent().removeClass("on");
            			
            			var count = 0;
            			
            			$("div#complete-percent i.completed").each(function(index,element){
            				if(index>=$("div#complete-percent i.completed").length-2){
            					$(this).removeClass("completed");
            				}
            			})
            			$("#percent").html((Number($("#percent").html().replace(/[^0-9]/ig,""))-20)+"%");
            		}
            		
            	}else if(data.RESPONSE_STATE=="500"){
        			layer.alert(data.ERROR_INFO, {
        				title:"错误信息",
        				icon:2
        			});
        		}
            }
    	});
    	
	});
	
	//修改游记音乐名
	$("#m_modify").click(function(){
		$("#m-showname").css("display","none");
		$("#m_modify").css("display","none");
		$("#m_delete").css("display","none");
		$("#music_name").css("display","");
		$("#m_save").css("display","");
		$("#m_cancel").css("display","");
	});
	
	//保存修改文件名
	$("#m_save").click(function(){
		var musicname = $("#music_name").val();
		if(musicname.length<=50){
			$.ajax({
	    		type : "post",
	    		url : "travels/create.json",
	    		data : {id:$.getUrlParam('id'),act:'update',"data.o":'musicname',"data.c":musicname},
	    		async : false,
	    		success : function(data){
	            	if(data.RESPONSE_STATE=="200"){
	            		$("#m-showname").html("已上传-"+$("#music_name").val());
	    				$("#music_name").css("display","none");
	    				$("#m_save").css("display","none");
	    				$("#m_cancel").css("display","none");
	    				$("#m-showname").css("display","");
	    				$("#m_modify").css("display","");
	    				$("#m_delete").css("display","");
	            	}else if(data.RESPONSE_STATE=="500"){
	        			layer.alert(data.ERROR_INFO, {
	        				title:"错误信息",
	        				icon:2
	        			});
	        		}
	            }
	    	});
		}else{
			layer.alert("音乐名称过长", {
				title:"错误信息",
				icon:2
			});
		}
	});
	
	//取消修改
	$("#m_cancel").click(function(){
		$("#music_name").css("display","none");
		$("#m_save").css("display","none");
		$("#m_cancel").css("display","none");
		$("#m-showname").css("display","");
		$("#m_modify").css("display","");
		$("#m_delete").css("display","");
	});
	
	//发布游记
	$("#publish").click(function(){
		$("#preservation").trigger("click");
		if($(headline).html()!=null&&$(headline).html()!=""){
			$.ajax({
	    		type : "post",
	    		url : "travels/address.json",
	    		data : {id:$.getUrlParam('id')},
	    		async : false,
	    		success : function(data){
	    			var html = "";
	    			var bool = true;
	            	if(data.length>0){
	            		for(var i=0;i<data.length;i++){
	            			var classStr = "";
	            			if(travels_address==data[i]){
	            				classStr = "class='on'";
		            			bool = false;
		            		}
		            		if(i==0){
		            			html += "<li "+classStr+">"+data[i]+"</li>";
		            		}else{
		            			html += "<li "+classStr+">"+data[i]+"</li>";
		            		}
		            	}
	            		if(bool){
	            			if(travels_address){
	            				html += "<li class='on'>"+travels_address+"</li>";
	            			}else{
	            				$("ul#address-update").find("li").eq(0).addClass("on");
	            			}
	            		}
	            		
	            	}else{
	            		if(travels_address){
	            			html += "<li class='on'>"+travels_address+"</li>";
	            		}else{
	            			html += "<li class='on'>"+currentCity+"</li>";
	            		}
	            	}
	            	$("ul#address-update").html(html);
	            	
	            	layer.open({
	            		type:1,
	            		title:false,
	            		closeBtn:false,
	            		area:['540px','420px'],
	            		content:$('div#travels_address')
	            	});
	            	
	            	var address = "";
	        		if($("ul#address-update li.on").length>0){
	        			address = $("ul#address-update li.on").eq(0).html();
	        		}else if($("#travelsaddress").val()!=""){
	        			address = $("#travelsaddress").val();
	        		}else{
	        			if($("ul#address-update li").length>0){
	        				address = $("ul#address-update li").eq(0).html();
	        			}else{
	        				address = currentCity;
	        			}
	        		}
	        		
	        		$.ajax({
	            		type : "post",
	            		url : "travels/create.json",
	            		data : {id:$.getUrlParam('id'),act:'update',"data.o":'address',"data.c":address},
	            		async : false,
	            		success : function(data){
	                    	if(data.RESPONSE_STATE=="200"){
	                    		$.ajax({
	                        		type : "post",
	                        		url : "travels/newTravels/"+$.getUrlParam('id'),
	                        		async : false,
	                        		success : function(data){
	                        			if(data.RESPONSE_STATE=="500"){
	                        				layer.alert(data.ERROR_INFO, {
	                            				title:"错误信息",
	                            				icon:2
	                            			});
	                        			}
	                                }
	                        	});
	                    	}else if(data.RESPONSE_STATE=="500"){
	                			layer.alert(data.ERROR_INFO, {
	                				title:"错误信息",
	                				icon:2
	                			});
	                		}
	                    }
	            	});
	            	
	            }
	    	});
		}else{
			layer.alert("请填写标题!", {
				title:"提醒",
				icon:2
			});
		}
		
	});
	
	//修改关联目的地
	$("ul#address-update").on("click","li",function(){
		$("ul#address-update li").removeClass("on");
		$(this).addClass("on");
		$("#travelsaddress").val("");
	});
	//手动输入关联目的地
	$("#travelsaddress").on("click",function(){
		$("ul#address-update li").removeClass("on");
	})
	//手动输入地址为空，默认选中第一个城市
	$("#travelsaddress").on("blur",function(){
		if($("#travelsaddress").val()==""){
			$("ul#address-update li").eq(0).addClass("on");
		}
	})
	
	//关闭关联地址
	$("#travels_address_close").one("click",function(){
		window.location.href="travels/detail/"+$.getUrlParam('id')+".html";
	})
	
	//确定关联地点并发布游记
	$("#address-btn").one("click",function(){
		var address = "";
		if($("ul#address-update li.on").length>0){
			address = $("ul#address-update li.on").eq(0).html();
		}else if($("#travelsaddress").val()!=""){
			address = $("#travelsaddress").val();
		}else{
			layer.alert("游记相关地点必须填写！", {
				title:"错误信息",
				icon:2
			});
			return;
		}
		
		$.ajax({
    		type : "post",
    		url : "travels/create.json",
    		data : {id:$.getUrlParam('id'),act:'update',"data.o":'address',"data.c":address,"data.w":word_count(),"data.p":photo_count()},
    		async : false,
    		success : function(data){
            	if(data.RESPONSE_STATE=="200"){
        			$.ajax({
                		type : "post",
                		url : "travels/newTravels/"+$.getUrlParam('id'),
                		async : false,
                		success : function(data){
                			if(data.RESPONSE_STATE=="200"){
                				window.location.href="travels/detail/"+$.getUrlParam('id')+".html";
                			}else{
                				layer.alert(data.ERROR_INFO, {
                    				title:"错误信息",
                    				icon:2
                    			});
                			}
                        }
                	});
            	}else if(data.RESPONSE_STATE=="500"){
        			layer.alert(data.ERROR_INFO, {
        				title:"错误信息",
        				icon:2
        			});
        		}
            }
    	});
	});
	
})

//输入框
String.prototype.myTrim = function () {
    return this.replace(/(^\s*|\s*$)/g, "")
}

//修改游记标题
$("#oInput").blur(function () {
    var val = this.value.myTrim();
    if (val === "") {
        return;
    } else {
    	$.ajax({
    		type : "post",
    		url : "travels/create.json",
    		data : {id:$.getUrlParam('id'),act:'update',"data.o":'title',"data.c":val},
    		async : false,
    		success : function(data){
            	if(data.RESPONSE_STATE=="200"){
            		$("#travelstitle").css("display","none");
                    $("#headline").html(val);
                    $("#headline-top").css("display","block");
            	}else if(data.RESPONSE_STATE=="500"){
        			layer.alert(data.ERROR_INFO, {
        				title:"错误信息",
        				icon:2
        			});
        		}
            }
    	});
    	
    }
})

//点击编辑，显示游记标题
$("#a1").click(function () {
	$("#oInput").val($("#headline").html());
    $("#travelstitle").css("display","block");
    $("#headline-top").css("display","none");
})

//设置游记封面
$("div#travelsContent").on("click","div#content-img a#cover",function(){
	var _this = $(this);
    var content = _this.closest("#content-img");
    var contentId = content.attr("content");
    
    $.ajax({
		type : "post",
		url : "travels/create.json",
		data : {id:$.getUrlParam('id'),act:'update',"data.o":'cover',"content_id":contentId},
		async : false,
		success : function(data){
        	if(data.RESPONSE_STATE=="200"){
        		$(".ico-cover-now").addClass("ico-cover");
        		$(".ico-cover-now").after("<a href='javascript:void(0)' class='set-cover' id='cover'>设为封面</a>")
        		$(".ico-cover-now").removeClass("ico-cover-now");
        		content.find(".ico-cover").addClass("ico-cover-now");
        		content.find(".ico-cover").removeClass("ico-cover");
        		content.find("#cover").remove();
        		
        		var cover_photo = $("a[data-target='cover_photo']");
        		if(cover_photo.length>0){
        			cover_photo.attr("data-target",cover_photo.attr("data-target")+"_final");
        			cover_photo.attr("title","已完成");
        			cover_photo.attr("class","btns1");
        			cover_photo.parent().addClass("on");
        			cover_photo.html("已完成");
        			
        			var count = 0;
        			$("div#complete-percent i").each(function(index,element){
        				if(count<2){
        					if($(this).attr("class")!="completed"){
        						$(this).addClass("completed");
        						count++;
        					}
        				}
        			})
        			$("#percent").html((Number($("#percent").html().replace(/[^0-9]/ig,""))+20)+"%");
        		}
        		
        	}else if(data.RESPONSE_STATE=="500"){
        		layer.alert(data.ERROR_INFO, {
    				title:"错误信息",
    				icon:2
    			});
        	}
        }
	});
})

//添加栏动画
$("div#travelsContent").on("click","#remove",function () {
	$(this).parent().find(".a1").each(function(index,element){
        var num = parseInt($(this).css('marginLeft'));
        if (num == -134) {
            $(this).animate({marginLeft: "25px"}, 500);
        } else {
            $(this).animate({marginLeft: "-134px"}, 500);
        }
    })
    
});

var sort = $("#sort").val();;

//新增文字框
$("div#travelsContent").on("click","#addWord",function(){
	$("body").attr("onbeforeunload","checkLeave()")
    var _this = $(this);
    if(_this.closest("#ico").find("div#word").html()==null){
        _this.closest("#ico").append(wordHtml("","addwordfianl"));
        
        _this.closest("#ico").find("#content").select();
    }
});

// 新增文字完成
$("div#travelsContent").on("click","div#ico a#addwordfianl",function(){
	$("body").attr("onbeforeunload","")
    var _this = $(this);
    var word = _this.closest("#word")
    var content = word.find("#content");
    var ico = _this.closest("#ico");
    
    if(content.val()==""){
        word.remove();
        return;
    }
    
    var day = _this.closest("#day");
    var o = "1";
    var html = icoHtml();
    
	if(day.html()!=null){
		o = "51";
    	var contentId = day.attr("content");
    	if(contentId==""||contentId==null){
    		contentId = addDay(day);
    	}
    	html = icoHtml("day");
	}else{
		html = icoHtml();
	}


	$.ajax({
		type : "post",
		url : "travels/create.json",
		data : {id:$.getUrlParam('id'),act:'insert',sort:sort,"data.o":o,"data.c":replaceEmoji(content.val()),content_id:contentId},
		async : false,
		success : function(data){
			if(data.RESPONSE_STATE=="200"){
				ico.after("<div class='article-content' id='content-word' content='"+data.contentId+"'>"+
		    			"<span>"+data.content+"</span>"+
		    			"<a class='edit-again' title='编辑' role='button' id='update-word'></a>"+
		    			"<a class='edit-del' title='删除' role='button' id='delete-word'></a>"+
		    		  "</div>"+html);
			    word.remove();
			    $(".a1").animate({marginLeft: "-134px"}, 500);
			    sort = data.sort;
			    
			}else if(data.RESPONSE_STATE=="500"){
				layer.alert(data.ERROR_INFO, {
					title:"错误信息",
					icon:2
				});
			}
		}
	});
	
	if(ico.next().attr("id")=="ico"||ico.next().next().attr("id")=="ico"||ico.next().next().next().attr("id")=="ico"){
		contentsort();
    }

});

//修改文字显示
$("div#travelsContent").on("click","div#content-word a#update-word",function(){
	$("body").attr("onbeforeunload","checkLeave()")
    var _this = $(this);
    var content = _this.parent();
    content.find("span").find("img").each(function(index,element){
    	updateImg($(this));
    });
    
    var v = content.find("span").html();
	v = v.replaceAll("<br>","\r");
	
    var ico = _this.closest("#content-word").prev("div#ico");

    content.css("display","none");

    ico.append(wordHtml(v,"updatewordfianl",content.attr("content")));

});

//修改文字完成
$("div#travelsContent").on("click","div#ico a#updatewordfianl",function(){
	$("body").attr("onbeforeunload","")
	var _this = $(this);
	var word = _this.closest("#word")
	var content = word.find("#content");
	var ico = _this.closest("#ico");

	$.ajax({
		type : "post",
		url : "travels/create.json",
		data : {id:$.getUrlParam('id'),act:'update',content_id:word.attr("content"),sort:sort,"data.o":'1',"data.c":replaceEmoji(content.val())},
		async : false,
		success : function(data){
	    	if(data.RESPONSE_STATE=="200"){
	    		ico.next().find("span").html(data.content+"");
	    	    ico.next().css("display","block");
	    	    word.remove();
	    	    sort = data.sort;
	    	}else if(data.RESPONSE_STATE=="500"){
				layer.alert(data.ERROR_INFO, {
					title:"错误信息",
					icon:2
				});
			}
	    }
	});
	
});

//删除文字
$("div#travelsContent").on("click","div#content-word a#delete-word",function(){
    var _this = $(this);
    var content = _this.closest("#content-word");
	
    var contentId = content.attr("content");
    $.ajax({
		type : "post",
		url : "travels/create.json",
		data : {id:$.getUrlParam('id'),act:'delete',"data.o":'1',content_id:contentId},
		async : false,
		success : function(data){
	    	if(data.RESPONSE_STATE=="200"){
	    		content.next().remove();
	    		content.remove();
	    	}else if(data.RESPONSE_STATE=="500"){
				layer.alert(data.ERROR_INFO, {
					title:"错误信息",
					icon:2
				});
			}
	    	
	    }
	});
    

});

var title_this;
var tityletype = "";
var title_box_layer;
//段落弹窗
$("div#travelsContent").on("click","#addTitle",function(){
	title_this = $(this);
	tityletype = "insert";
	title_box_layer = layer.open({
		type:1,
		title:false,
		closeBtn:false,
		area:['740px','420px'],
		content:$('div#title-box')
	})
});

//关闭段落弹窗
$("#title-box-close").on("click",function(){
	$("#title-box-content").val("");
	
	$("#paragraph-pics ul li").removeClass("on");
	$("#paragraph-pics ul li").eq(0).addClass("on");
	
	layer.close(title_box_layer);
	title_this = null;
	
});

//切换样式
$("#paragraph-pics").on("click","ul li",function(){
	$(this).parent().find("li").removeClass("on");
	$(this).addClass("on");
});

$("#title-btn").on("click",function(){
	//新增段落完成
	if(tityletype=="insert"){
	    var content = $("#title-box-content").val();
	    var ico = title_this.closest("#ico");
	    var style = $("#paragraph-pics ul li.on").attr("data-target");
	    
	    if(content.length>30){
	    	layer.alert("段落标题不能超过30个字！", {
				title:"错误信息",
				icon:2
			});
	    	return;
	    }
	    
	    if(content==""){
	    	layer.alert("请输入段落名称！", {
				title:"错误信息",
				icon:2
			});
	        return;
	    }
	    
	    var day = title_this.closest("#day");
	    var o = "4";
	    var html = "";
		if(day.html()!=null){
			o = "54";
			
	    	var contentId = day.attr("content");
	    	if(contentId==""||contentId==null){
	    		contentId = addDay(day);
	    	}
	    	html = icoHtml("day");
		}else{
			html = icoHtml();
		}
		$.ajax({
			type : "post",
			url : "travels/create.json",
			data : {id:$.getUrlParam('id'),act:'insert',sort:sort,"data.o":o,"data.c":content,content_id:contentId,"data.s":style},
			async : false,
			success : function(data){
				if(data.RESPONSE_STATE=="200"){
					ico.after("<div class='article-title' id='content-title' content='"+data.contentId+"'>"+
							"<p class='"+style+"'>"+
							"	<i></i><span>"+data.title+"</span>"+
							"	<a class='edit-again' title='编辑' role='button' id='update-title'></a>"+
		    				"	<a class='edit-del' title='删除' role='button' id='delete-title'></a>"+
							"</p>"+
							"</div>"+icoHtml());
					$('#title-box-close').trigger("click");
				    $(".a1").animate({marginLeft: "-134px"}, 500);
				    sort = data.sort;
				    
				    var paragraph_title = $("a[data-target='paragraph_title']");
	        		if(paragraph_title.length>0&&$("div#content-title").length>0){
	        			paragraph_title.attr("data-target",paragraph_title.attr("data-target")+"_final");
	        			paragraph_title.attr("title","已完成");
	        			paragraph_title.attr("class","btns1");
	        			paragraph_title.html("已完成");
	        			paragraph_title.parent().addClass("on");
	        			
	        			var count = 0;
	        			$("div#complete-percent i").each(function(index,element){
	        				if(count==0){
	        					if($(this).attr("class")!="completed"){
	        						$(this).addClass("completed");
	        						count++;
	        					}
	        				}
	        			})
	        			$("#percent").html((Number($("#percent").html().replace(/[^0-9]/ig,""))+10)+"%");
	        		}
				}else if(data.RESPONSE_STATE=="500"){
					layer.alert(data.ERROR_INFO, {
						title:"错误信息",
						icon:2
					});
				}
		    }
		});
	
		if(ico.next().attr("id")=="ico"||ico.next().next().attr("id")=="ico"||ico.next().next().next().attr("id")=="ico"){
			contentsort();
	    }
	}else{
	//修改段落完成
		var _this = $(this);
		var title = _this.closest("#title")

		var content = $("#title-box-content").val();
	    var ico = title_this;
	    var style = $("#paragraph-pics ul li.on").attr("data-target");
		
		$.ajax({
			type : "post",
			url : "travels/create.json",
			data : {id:$.getUrlParam('id'),act:'update',content_id:tityletype.attr("content"),sort:sort,"data.o":'4',"data.c":content,"data.s":style},
			async : false,
			success : function(data){
		    	if(data.RESPONSE_STATE=="200"){
		    		ico.next().find("span").html(data.title);
		    		ico.next().find("p").attr("class",style);
		    	    $('#title-box-close').trigger("click");
		    	    sort = data.sort;
		    	}else if(data.RESPONSE_STATE=="500"){
					layer.alert(data.ERROR_INFO, {
						title:"错误信息",
						icon:2
					});
				}
		    }
		});
		
	}
});

//修改段落显示
$("div#travelsContent").on("click","div#content-title a#update-title",function(){
    var _this = $(this);
    var content = _this.parent().parent();
    var v = _this.parent().find("span").html();
    var style = content.find("p").attr("class");
    title_this = content.prev("div#ico");
    tityletype = content;
    
    $("#paragraph-pics ul li").removeClass("on");
    $("#paragraph-pics ul li").each(function(index,element){
    	if($(this).attr("data-target")==style){
    		$(this).addClass("on");
    	}
    });
    
	$("#title-box-content").val(v);
    title_box_layer = layer.open({
		type:1,
		title:false,
		closeBtn:false,
		area:['740px','420px'],
		content:$('div#title-box')
	});
    
});

//删除段落
$("div#travelsContent").on("click","div#content-title a#delete-title",function(){
    var _this = $(this);
    var content = _this.closest("#content-title");
    var contentId = content.attr("content");
    
    $.ajax({
		type : "post",
		url : "travels/create.json",
		data : {id:$.getUrlParam('id'),act:'delete',"data.o":'4',content_id:contentId},
		async : false,
		success : function(data){
	    	if(data.RESPONSE_STATE=="200"){
	    		content.next().remove();
	    		content.remove();
	    		
	    		var paragraph_title = $("a[data-target='paragraph_title_final']");
        		if($("div#content-title").length==0){
        			paragraph_title.attr("data-target","paragraph_title");
        			paragraph_title.attr("title","前往");
        			paragraph_title.attr("class","btns");
        			paragraph_title.html("前往");
        			paragraph_title.parent().removeClass("on");
        			
        			var count = 0;
        			
        			$("div#complete-percent i.completed").each(function(index,element){
        				if(index==$("div#complete-percent i.completed").length-1){
        					$(this).removeClass("completed");
        				}
        			})
        			$("#percent").html((Number($("#percent").html().replace(/[^0-9]/ig,""))-10)+"%");
        		}
	    		
	    	}else if(data.RESPONSE_STATE=="500"){
				layer.alert(data.ERROR_INFO, {
					title:"错误信息",
					icon:2
				});
			}
	    	
	    }
	});
    
});

//删除图片
$("div#travelsContent").on("click","div#content-img a#delete-photos",function(){
    var _this = $(this);
    var content = _this.closest("#content-img");
    var contentId = content.attr("content");
    
    $.ajax({
		type : "post",
		url : "travels/create.json",
		data : {id:$.getUrlParam('id'),act:'delete',"data.o":'2',content_id:contentId},
		async : false,
		success : function(data){
	    	if(data.RESPONSE_STATE=="200"){
	    		content.next().remove();
	    		content.remove();
	    		
	    		var cover_photo = $("a[data-target='cover_photo_final']");
        		if($("div.ico-cover-now").length==0){
        			cover_photo.attr("data-target","cover_photo");
        			cover_photo.attr("title","前往");
        			cover_photo.attr("class","btns");
        			cover_photo.html("前往");
        			cover_photo.parent().removeClass("on");
        			
        			$("div#complete-percent i.completed").each(function(index,element){
        				if(index>=$("div#complete-percent i.completed").length-2){
        					$(this).removeClass("completed");
        				}
        			})
        			$("#percent").html((Number($("#percent").html().replace(/[^0-9]/ig,""))-20)+"%");
        		}
        		
        		// 删除图片缓存中对应的数据
        		$.each(travels.e.imgDataArray,function(i,item){
        			if(item.contentId == contentId){
        				travels.e.imgDataArray.splice(i,1);
        			}
        		})
        		
	    	}else if(data.RESPONSE_STATE=="500"){
				layer.alert(data.ERROR_INFO, {
					title:"错误信息",
					icon:2
				});
			}
	    	
	    }
	});
    
});


//新增day模板
$("div#travelsContent").on("click","#addDay",function(){
    var _this = $(this),
    	time = new Date().getTime();
    _this.closest("#ico").after(dayHtml(time));
    	new $.fn.travelsImg({
    		parentElementId:time,// 父元素标示
    		triggerUploadElementId:"trigger_upload",// 触发选择文件的按钮ID
    		originalInputId:"main_image",// 原件保存的inputID，上传原图后会自动向该input赋值
    		fileId:time+"uploadImgFileHidden",
    		originalImgMinWidth:600,// 原图最小宽度
    		originalImgMinHeight:300,// 原图最小高度
    		ratioWidth:3.2,// 裁剪比例（宽）
    		ratioHeight:1,// 裁减比例（高）
    		draftCallFn:function(data){// 截图完成后的回调函数， data.savePath为截图的服务器地址
    			var $parent = $("div[upload-main-img-div="+time+"]");
    			// 保存截图路径
    			$parent.find("input#show_image").val(data.savePath);
    	  		
    	  		// 替换背景
    			$parent.find("div.set-banner-day").css({'background':'url('+data.savePath+') no-repeat center top','background-size': 'cover'});
    			
    			// 隐藏设置主图div
    			$parent.find("div#trigger_upload").hide();
    			
    			// 打开操作主图按钮
    			$parent.find("div#setMainImgDiv").show();
    			
    	    	var contentId = $parent.attr("content");
    	    	if(contentId==""||contentId==null){
    	    		contentId = addDay($parent);
    	    	}
    			
    			$.ajax({
    	    		type : "post",
    	    		url : "travels/create.json",
    	    		data : {id:$.getUrlParam('id'),act:'update',"data.o":'dayphotos',"data.c":$parent.find("input#main_image").val(),"data.s":$parent.find("input#show_image").val(),"content_id":contentId},
    	    		async : false,
    	    		success : function(data){
    	            	if(data.RESPONSE_STATE=="200"){
    	            		_this.closest("#travelstitle").css("display","none");
    	            		_this.parent().parent().find("#headline").html(val);
    	            		_this.parent().parent().find("#headline-top").css("display","block");
    	            	}else if(data.RESPONSE_STATE=="500"){
    	        			layer.alert(data.ERROR_INFO, {
    	        				title:"错误信息",
    	        				icon:2
    	        			});
    	        		}
    	            }
    	    	});
    			
    		},
    		customFn:function(draftImg){// 需要注册的自定义事件 （主要作用是编辑原图重新裁剪功能）@param draftImg 传递一个url即可进行新的裁剪，成功回调还是draftCallFn方法
    			var $parent = $("div[upload-main-img-div="+time+"]");
    			
    			// 重新上传主图
    			$parent.find("a#resetMainImg").on("click",function(){
    				// 该按钮相当于重新选择文件所以触发的是clickElementId元素的事件
    				$("input#"+time+"uploadImgFileHidden").trigger("click");
    			})
    			
    			// 编辑主图
    			$parent.find("a#editMainImg").on("click",function(){
    				draftImg.draftImg($parent.find("input#main_image").val());
    			})
    		}
    	});
    
});

function addDay(day){
	var contentId = "";
	$.ajax({
		type : "post",
		url : "travels/create.json",
		data : {id:$.getUrlParam('id'),sort:sort,act:'insert',"data.o":'5'},
		async : false,
		success : function(data){
			sort = data.sort;
			contentId = data.contentId;
			day.attr("content",contentId);
		}
	});
	var ico = day.prev();
	if(ico.next().attr("id")=="ico"||ico.next().next().attr("id")=="ico"||ico.next().next().next().attr("id")=="ico"){
		contentsort();
    }
	
	return contentId;
}

//删除day模板
$("div#travelsContent").on("click","div#day a#delete-day",function(){
	var day = $(this).closest("#day");
	var contentId = day.attr("content");
	
	if(contentId==null){
		day.next().remove();
		day.remove();
	}else{
		$.ajax({
			type : "post",
			url : "travels/create.json",
			data : {id:$.getUrlParam('id'),act:'delete',"data.o":'5',content_id:contentId},
			async : false,
			success : function(data){
		    	if(data.RESPONSE_STATE=="200"){
		    		day.next().remove();
		    		day.remove();
		    	}else if(data.RESPONSE_STATE=="500"){
					layer.alert(data.ERROR_INFO, {
						title:"错误信息",
						icon:2
					});
				}
		    	
		    }
		});
	}
});

//修改day标题
$("div#travelsContent").on("blur","div#day #oInput",function () {
	var _this = $(this);
    var val = _this.val().myTrim();
    if (val === "") {
        return;
    } else {
    	var day = _this.closest("#day");
    	var contentId = day.attr("content");
    	if(contentId==""||contentId==null){
    		contentId = addDay(day);
    	}
    	
    	$.ajax({
    		type : "post",
    		url : "travels/create.json",
    		data : {id:$.getUrlParam('id'),act:'update',"data.o":'daytitle',"data.c":val,"content_id":contentId},
    		async : false,
    		success : function(data){
            	if(data.RESPONSE_STATE=="200"){
            		_this.closest("#travelstitle").css("display","none");
            		_this.parent().parent().find("#headline").html(val);
            		_this.parent().parent().find("#headline-top").css("display","block");
            	}else if(data.RESPONSE_STATE=="500"){
        			layer.alert(data.ERROR_INFO, {
        				title:"错误信息",
        				icon:2
        			});
        		}
            }
    	});
    	
    }
})
//点击编辑，显示day标题
$("div#travelsContent").on("click","#a1",function () {
	var _this = $(this);
	var div = _this.parent().parent().parent();
	div.find("#oInput").val(div.find("#headline").html());
	div.find("#travelstitle").css("display","block");
	_this.parent().parent().css("display","none");
})











//文字输入框html
function wordHtml(v,id,contentId){
	if(contentId!=""&&contentId!=null){
		contentId = "content='"+contentId+"'";
	}
	var html = "<div class='add-word-box clearfix emoji-container' id='word' "+contentId+">"+
        "<textarea id='content' class='edit-txtarea' maxlength='5000' placeholder='亲！在这输入您的文字描述··' style='height: 380px;'>"+v+"</textarea>"+
        "<div class='add-article' id='emoji'>"+
        "	<a class='add-face' title='添加表情' id='expression'><i></i>添加表情</a>"+
        "	<a class='fr btn-complete' title='完成' id='"+id+"'>完成</a>"+
        "</div>"+
        "</div>";
	return html;
}

//段落输入框html
function titleHtml(v,id,contentId){
	if(contentId!=""&&contentId!=null){
		contentId = "content='"+contentId+"'";
	}
	var html = "<div class='add-word-box clearfix' id='title' "+contentId+">"+
			"	<input type='text' id='content' class='edit-txtarea' maxlength='30' placeholder='亲！在这输入您的文字段落标题··' style='height:50px;' id='title' value='"+v+"'/>"+
			"	<div class='add-article'>"+
			"		<a class='fr btn-complete' title='完成' id='"+id+"'>完成</a>"+
			"	</div>"+
			"</div>";
	return html;
}

//添加day模板
function dayHtml(time){
	
	var html = "<div id='day' upload-main-img-div='"+time+"' ><div class='split-line'></div>"+
				"<input type='file' name='file' id='"+time+"uploadImgFileHidden' style='display:none;' />"+
				"<div class='complete-percent' id='top-there' style='position: relative;'>"+
				"<a class='edit-del w' style='margin-left:1110px;margin-top:24px;' title='删除' id='delete-day' role='button' ></a>"+
				"</div>"+
				"<div class='set-banner-day'>"+
				"	<div class='boxw clearfix'>"+
				"		<div class='set-main-day'>"+
				"			<div class='set-img'  id='trigger_upload'>"+
				"				<a href='javascript:void(0)' class='set-add'><i></i>设置Day主图</a>"+
				"				<span>建议选择尺寸大于600x300的高清大图</span>"+
				"			</div>"+
				"			<input type='hidden' name='main_image' id='main_image' />"+
				"			<input type='hidden' name='show_image' id='show_image' />"+
				"			<div class='set-title' id='travelstitle'>"+
				"				<i></i>"+
				"				<input type='text' id='oInput' maxlength='50' placeholder='填写Day标题' value='' >"+
				"			</div>"+
				"			<div class='banner-title' style='display: none' id='headline-top'>"+
				"				<p>"+
				"					<span id='headline'></span>"+
				"					<a class='edit-again' id='a1' title='编辑' role='button'></a>"+
				"				</p>"+
				"			</div>"+
				"		</div>"+
				"	</div>"+
				"</div>"+
				"<div class='complete-percent'>"+
				"	<div class='boxw set-btn-box'>"+
				"		<div class='set-btn' style='display:none' id='setMainImgDiv'>"+
				"			<ul class='oUl'>"+
				"				<li class='oli'><a class='main-img-add' title='重新上传主图' id='resetMainImg' role='button'><i></i><span class='ospan'>重新上传主图</span></a></li>"+
				"				<li class='oli'><a class='main-img-add' title='重新编辑主图' id='editMainImg' role='button'><i></i><span class='ospan'>重新编辑主图</span></a></li>"+
				"			</ul>"+
				"			<a class='main-img-set' trigger-upload-img title='设置Day主图' role='button' id=''><i></i><span>设置Day主图</span></a>"+
				"		</div>"+
				"	</div>"+
				"</div>"+icoHtml("day")+"<div class='split-line'></div></div>"+icoHtml();
	return html;
}

//添加选择按钮
function icoHtml(type){
	var html ="<div class='add-button clearfix' id='ico'>"+
			  	"<a class='add-ico' title='添加' role='button' style='display:none;'></a>"+
			  	"<div class='edit-link clearfix'>"+
			  		"<li class='nopad' id='remove'><a class='del-ico' title='添加' role='button'></a></li>"+
			  		"<ul id='remove-ul'>"+
			
				  		"<li><a title='添加文字' role='button' class='edit-word a1' id='addWord'><i></i>添加文字</a></li>"+
				  		"<li><a title='添加图片' role='button' class='edit-img a1' id='addPhotos'><i></i>添加图片</a></li>";
				  		
				  		if(type=="day"){
				  			html += "<li><a title='添加标题' role='button' class='edit-title a1' id='addTitle'><i></i>添加标题</a></li>";
				  		}else{
				  			html += "<li><a title='添加标题' role='button' class='edit-title a1' id='addTitle'><i></i>添加标题</a></li>"+
					  		"<li><a title='添加Day' role='button' class='edit-day a1' id='addDay'><i></i>添加Day</a></li>";
				  		}
				  		
			  	html +="</ul>"+
			  	"</div>"+
			
			  "</div>";
	return html;
}

function contentsort(fn){
	var contentIds = "",
		$this,
		id;
	$("div#travelsContent").find("div[content]").each(function(index,element){
		$this = $(this),
		id=$this.attr("id");
		
		if(id=="content-word"||id=="content-title"||id=="day"||id=="content-img"){
			var content = $this.attr("content");
			if(content){
				if(contentIds==""){
					contentIds = content;
				}else{
					contentIds += ","+content;
				}
			}
		}
	});
	
	if(contentIds!=""){
		$.ajax({
			type : "post",
			url : "travels/sort.html.json",
			data : {contentIds:contentIds,id:$.getUrlParam('id')},
			async : false,
			success : function(data){
				if(data.RESPONSE_STATE=="500"){
					layer.alert(data.ERROR_INFO, {
						title:"错误信息",
						icon:2
					});
				}else if(data.RESPONSE_STATE=="200"){
					if(typeof(fn) == "function"){
						fn();
					}
				}
			}
		});
	}

}

//批量替换
String.prototype.replaceAll = function(reallyDo, replaceWith, ignoreCase) {  
	if (!RegExp.prototype.isPrototypeOf(reallyDo)) {  
		return this.replace(new RegExp(reallyDo, (ignoreCase ? "gi": "g")), replaceWith);  
	} else {  
		return this.replace(reallyDo, replaceWith);  
	}  
}

function txtarea_this(obj){
	return $(obj).closest("#word").find("#content").get(0);
}

function updateImg(obj){
	var $this = obj;
	var reg = /\\+1|-1|100|109|1234|8ball|a|ab|abc|abcd|accept|aerial_tramway|airplane|alarm_clock|alien|ambulance|anchor|angel|anger|angry|anguished|ant|apple|aquarius|aries|arrows_clockwise|arrows_counterclockwise|arrow_backward|arrow_double_down|arrow_double_up|arrow_down|arrow_down_small|arrow_forward|arrow_heading_down|arrow_heading_up|arrow_left|arrow_lower_left|arrow_lower_right|arrow_right|arrow_right_hook|arrow_up|arrow_upper_left|arrow_upper_right|arrow_up_down|arrow_up_small|art|articulated_lorry|astonished|athletic_shoe|atm|b|baby|baby_bottle|baby_chick|baby_symbol|back|baggage_claim|balloon|ballot_box_with_check|bamboo|banana|bangbang|bank|barber|bar_chart|baseball|basketball|bath|bathtub|battery|bear|bee|beer|beers|beetle|beginner|bell|bento|bicyclist|bike|bikini|bird|birthday|black_circle|black_joker|black_large_square|black_medium_small_square|black_medium_square|black_nib|black_small_square|black_square|black_square_button|blossom|blowfish|blue_book|blue_car|blue_heart|blush|boar|boat|bomb|book|bookmark|bookmark_tabs|books|boom|boot|bouquet|bow|bowling|bowtie|boy|bread|bride_with_veil|bridge_at_night|briefcase|broken_heart|bug|bulb|bullettrain_front|bullettrain_side|bus|busstop|busts_in_silhouette|bust_in_silhouette|cactus|cake|calendar|calling|camel|camera|cancer|candy|capital_abcd|capricorn|car|card_index|carousel_horse|cat|cat2|cd|chart|chart_with_downwards_trend|chart_with_upwards_trend|checkered_flag|cherries|cherry_blossom|chestnut|chicken|children_crossing|chocolate_bar|christmas_tree|church|cinema|circus_tent|city_sunrise|city_sunset|cl|clap|clapper|clipboard|clock1|clock10|clock1030|clock11|clock1130|clock12|clock1230|clock130|clock2|clock230|clock3|clock330|clock4|clock430|clock5|clock530|clock6|clock630|clock7|clock730|clock8|clock830|clock9|clock930|closed_book|closed_lock_with_key|closed_umbrella|cloud|clubs|cn|cocktail|coffee|cold_sweat|collision|computer|confetti_ball|confounded|confused|congratulations|construction|construction_worker|convenience_store|cookie|cool|cop|copyright|corn|couple|couplekiss|couple_with_heart|cow|cow2|credit_card|crescent_moon|crocodile|crossed_flags|crown|cry|crying_cat_face|crystal_ball|cupid|curly_loop|currency_exchange|curry|custard|customs|cyclone|dancer|dancers|dango|dart|dash|date|de|deciduous_tree|department_store|diamonds|diamond_shape_with_a_dot_inside|disappointed|disappointed_relieved|dizzy|dizzy_face|dog|dog2|dollar|dolls|dolphin|door|doughnut|do_not_litter|dragon|dragon_face|dress|dromedary_camel|droplet|dvd|e-mail|ear|earth_africa|earth_americas|earth_asia|ear_of_rice|egg|eggplant|eight|eight_pointed_black_star|eight_spoked_asterisk|electric_plug|elephant|email|end|envelope|envelope_with_arrow|es|euro|european_castle|european_post_office|evergreen_tree|exclamation|expressionless|eyeglasses|eyes|facepunch|factory|fallen_leaf|family|fast_forward|fax|fearful|feelsgood|feet|ferris_wheel|file_folder|finnadie|fire|fireworks|fire_engine|first_quarter_moon|first_quarter_moon_with_face|fish|fishing_pole_and_fish|fish_cake|fist|five|flags|flashlight|floppy_disk|flower_playing_cards|flushed|foggy|football|footprints|fork_and_knife|fountain|four|four_leaf_clover|fr|free|fried_shrimp|fries|frog|frowning|fuelpump|full_moon|full_moon_with_face|game_die|gb|gem|gemini|ghost|gift|gift_heart|girl|globe_with_meridians|goat|goberserk|godmode|golf|grapes|green_apple|green_book|green_heart|grey_exclamation|grey_question|grimacing|grin|grinning|guardsman|guitar|gun|haircut|hamburger|hammer|hamster|hand|handbag|hankey|hash|hatched_chick|hatching_chick|headphones|heart|heartbeat|heartpulse|hearts|heart_decoration|heart_eyes|heart_eyes_cat|hear_no_evil|heavy_check_mark|heavy_division_sign|heavy_dollar_sign|heavy_exclamation_mark|heavy_minus_sign|heavy_multiplication_x|heavy_plus_sign|helicopter|herb|hibiscus|high_brightness|high_heel|hocho|honeybee|honey_pot|horse|horse_racing|hospital|hotel|hotsprings|hourglass|hourglass_flowing_sand|house|house_with_garden|hurtrealbad|hushed|icecream|ice_cream|id|ideograph_advantage|imp|inbox_tray|incoming_envelope|information_desk_person|information_source|innocent|interrobang|iphone|it|izakaya_lantern|jack_o_lantern|japan|japanese_castle|japanese_goblin|japanese_ogre|jeans|joy|joy_cat|jp|key|keycap_ten|kimono|kiss|kissing|kissing_cat|kissing_closed_eyes|kissing_face|kissing_heart|kissing_smiling_eyes|koala|koko|kr|large_blue_circle|large_blue_diamond|large_orange_diamond|last_quarter_moon|last_quarter_moon_with_face|laughing|leaves|ledger|leftwards_arrow_with_hook|left_luggage|left_right_arrow|lemon|leo|leopard|libra|light_rail|link|lips|lipstick|lock|lock_with_ink_pen|lollipop|loop|loudspeaker|loud_sound|love_hotel|love_letter|low_brightness|m|mag|mag_right|mahjong|mailbox|mailbox_closed|mailbox_with_mail|mailbox_with_no_mail|man|mans_shoe|man_with_gua_pi_mao|man_with_turban|maple_leaf|mask|massage|meat_on_bone|mega|melon|memo|mens|metal|metro|microphone|microscope|milky_way|minibus|minidisc|mobile_phone_off|moneybag|money_with_wings|monkey|monkey_face|monorail|moon|mortar_board|mountain_bicyclist|mountain_cableway|mountain_railway|mount_fuji|mouse|mouse2|movie_camera|moyai|muscle|mushroom|musical_keyboard|musical_note|musical_score|mute|nail_care|name_badge|neckbeard|necktie|negative_squared_cross_mark|neutral_face|new|newspaper|new_moon|new_moon_with_face|ng|night_with_stars|nine|non-potable_water|nose|notebook|notebook_with_decorative_cover|notes|no_bell|no_bicycles|no_entry|no_entry_sign|no_good|no_mobile_phones|no_mouth|no_pedestrians|no_smoking|nut_and_bolt|o|o2|ocean|octocat|octopus|oden|office|ok|ok_hand|ok_woman|older_man|older_woman|on|oncoming_automobile|oncoming_bus|oncoming_police_car|oncoming_taxi|one|open_file_folder|open_hands|open_mouth|ophiuchus|orange_book|outbox_tray|ox|package|pager|page_facing_up|page_with_curl|palm_tree|panda_face|paperclip|parking|partly_sunny|part_alternation_mark|passport_control|paw_prints|peach|pear|pencil|pencil2|penguin|pensive|performing_arts|persevere|person_frowning|person_with_blond_hair|person_with_pouting_face|phone|pig|pig2|pig_nose|pill|pineapple|pisces|pizza|plus1|point_down|point_left|point_right|point_up|point_up_2|police_car|poodle|poop|postal_horn|postbox|post_office|potable_water|pouch|poultry_leg|pound|pouting_cat|pray|princess|punch|purple_heart|purse|pushpin|put_litter_in_its_place|question|rabbit|rabbit2|racehorse|radio|radio_button|rage|rage1|rage2|rage3|rage4|railway_car|rainbow|raised_hand|raised_hands|raising_hand|ram|ramen|rat|recycle|red_car|red_circle|registered|relaxed|relieved|repeat|repeat_one|restroom|revolving_hearts|rewind|ribbon|rice|rice_ball|rice_cracker|rice_scene|ring|rocket|roller_coaster|rooster|rose|rotating_light|round_pushpin|rowboat|ru|rugby_football|runner|running|running_shirt_with_sash|sa|sagittarius|sailboat|sake|sandal|santa|satellite|satisfied|saxophone|school|school_satchel|scissors|scorpius|scream|scream_cat|scroll|seat|secret|seedling|see_no_evil|seven|shaved_ice|sheep|shell|ship|shipit|shirt|shit|shoe|shower|signal_strength|six|six_pointed_star|ski|skull|sleeping|sleepy|slot_machine|small_blue_diamond|small_orange_diamond|small_red_triangle|small_red_triangle_down|smile|smiley|smiley_cat|smile_cat|smiling_imp|smirk|smirk_cat|smoking|snail|snake|snowboarder|snowflake|snowman|sob|soccer|soon|sos|sound|space_invader|spades|spaghetti|sparkle|sparkler|sparkles|sparkling_heart|speaker|speak_no_evil|speech_balloon|speedboat|squirrel|star|star2|stars|station|statue_of_liberty|steam_locomotive|stew|straight_ruler|strawberry|stuck_out_tongue|stuck_out_tongue_closed_eyes|stuck_out_tongue_winking_eye|sunflower|sunglasses|sunny|sunrise|sunrise_over_mountains|sun_with_face|surfer|sushi|suspect|suspension_railway|sweat|sweat_drops|sweat_smile|sweet_potato|swimmer|symbols|syringe|tada|tanabata_tree|tangerine|taurus|taxi|tea|telephone|telephone_receiver|telescope|tennis|tent|thought_balloon|three|thumbsdown|thumbsup|ticket|tiger|tiger2|tired_face|tm|toilet|tokyo_tower|tomato|tongue|top|tophat|tractor|traffic_light|train|train2|tram|triangular_flag_on_post|triangular_ruler|trident|triumph|trolleybus|trollface|trophy|tropical_drink|tropical_fish|truck|trumpet|tshirt|tulip|turtle|tv|twisted_rightwards_arrows|two|two_hearts|two_men_holding_hands|two_women_holding_hands|u5272|u5408|u55b6|u6307|u6708|u6709|u6e80|u7121|u7533|u7981|u7a7a|uk|umbrella|unamused|underage|unlock|up|us|v|vertical_traffic_light|vhs|vibration_mode|video_camera|video_game|violin|virgo|volcano|vs|walking|waning_crescent_moon|waning_gibbous_moon|warning|watch|watermelon|water_buffalo|wave|wavy_dash|waxing_crescent_moon|waxing_gibbous_moon|wc|weary|wedding|whale|whale2|wheelchair|white_check_mark|white_circle|white_flower|white_large_square|white_medium_small_square|white_medium_square|white_small_square|white_square|white_square_button|wind_chime|wine_glass|wink|wink2|wolf|woman|womans_clothes|womans_hat|womens|worried|wrench|x|yellow_heart|yen|yum|zap|zero|zzz/g;
	if(reg.test($this.attr("title"))){
		$this.replaceWith("[:"+$this.attr("title")+":]")
	}
}

//获取用户所在城市信息(IP定位)
function showCityInfo() {
 //实例化城市查询类
 var citysearch = new AMap.CitySearch();
 //自动获取用户IP，返回当前城市
 citysearch.getLocalCity(function(status, result) {
     if (status === 'complete' && result.info === 'OK') {
         if (result && result.city && result.bounds) {
             var cityinfo = result.city;// 城市名称
             //var citybounds = result.bounds;// 地图展示该城市时使用的矩形区域
             currentCity = cityinfo.replace("市","");
         }
     } else {
    	 currentCity = "北京";
     }
 });
}

//统计游记字总数
function word_count(){
	var word = "";
	$("div#travelsContent").find("div#content-word").each(function(index,element){
		word += $(this).find("span").html();
	});
	
	$("body").append("<div id='imgnone' style='display:none;'>"+word+"</div>");
	
	$("#imgnone").find("img").each(function(index,element){
    	$(this).replaceWith("1");
    });
	
	var regnbsp = /&nbsp;/g;
	var regamp = /&amp;/g;
	var reglt = /&lt;/g;
	var reggt = /&gt;/g;
	var regbr = /<br>/g;
	
	word = $("#imgnone").html().replace(regnbsp,"").replace(regamp,"1").replace(reglt,"1").replace(reggt,"1").replace(regbr,"")
	$("#imgnone").remove();
	return word.length;
}

//统计游记图片总数
function photo_count(){
	return $("div#travelsContent").find("div#content-img").length;
}

//离开页面提醒
function checkLeave(){
	event.returnValue="您正在编辑的游记尚未保存草稿!";
}
