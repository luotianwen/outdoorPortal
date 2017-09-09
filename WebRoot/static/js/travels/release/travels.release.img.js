

/**
 *  拖动什么 - ondragstart 和 setData()
 *	然后，规定当元素被拖动时，会发生什么。
 *	ondragstart 属性调用了一个函数，drag(event)，它规定了被拖动的数据。
 *	方法设置被拖数据的数据类型和值
 */
// 存储多图拖拽选中的图片
var cacheChooseImg=new Array(),
	travels,//上传图片对象
	currentClickAddImg;// 当前点击添加图片的元素
function imgStartDrag(ev)
{
	ev.dataTransfer.setData("imgs",ev.target.id);
	cacheChooseImg.push(ev.target.id);
}
/**
 * 放到何处 - ondragover
 *	ondragover 事件规定在何处放置被拖动的数据。
 *	默认地，无法将数据/元素放置到其他元素中。如果需要设置允许放置，我们必须阻止对元素的默认处理方式。
 *	这要通过调用 ondragover 事件的 event.preventDefault() 方法：
 */
function allowDrop(ev)
{
	ev.preventDefault();
}

/**
 * 进行放置 - ondrop
 * 当放置被拖数据时，会发生 drop 事件。
 */
function drop(ev)
{
	// 允许放置
	ev.preventDefault();
	
	// 获取到拖动的数据
	var data=ev.dataTransfer.getData("imgs");
		src = $("#"+data).find("img").prop("src");
		
	
	var evId = ev.target.id,// 被放置元素的ID
		$li = $("#"+evId).closest("li"),// 被放置的元素父标签
		type = $li.attr("tag-type"),// 标签的类型
		scenicType = $li.attr("scenic-type"),// 景点类型
		typeId=$li.attr("tag-id"),// 标签ID
		typeName = $li.find("div.p-title").text(),// 标签名称
		imgDataArray = travels.e.imgDataArray;// 图片数据
	
	// 替换背景图片
	$li.find("div.img-area").css({'background':'url('+src+') no-repeat center top','background-size': 'cover'});
	
	// 关联图片标签，然后删除
	for(var i=0,len=cacheChooseImg.length;i<len;i++){
		var cacheItem = cacheChooseImg[i];
		for(var j=0,imgl=imgDataArray.length;j<imgl;j++){
			var imgItem = imgDataArray[j];
			// 关联标签
			if(imgItem.seq == cacheItem){
				imgDataArray[j].tag=type;
				imgDataArray[j].tagId=typeId;
				imgDataArray[j].tagName=typeName;
				imgDataArray[j].scenicType=scenicType;
			}
		}
		$("#"+cacheChooseImg[i]).remove();
	}
	
	// 清空
	cacheChooseImg.splice(0,cacheChooseImg.length);
	
}

var imgIndex=0;
function getIndex(){
	imgIndex++;
	return imgIndex;
}

$(function(){

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
	$.fn.travels=function(){
		this.e={
			$addImgBtn:$("a#addImg"),// 添加图片按钮
			$imgFileElement:$("input#imgFileElement"),// 上传文件input
			$uploadImgUl:$("ul#uploadImgUl"),// 图片父标签UL
			$doneUpload:$("a#doneUpload"),// 上传按钮
			$iImgNum:$("i#iImgNum"),// 图片数量
			$taskMin:0.5,// 单位：秒；设置定时任务为x秒上传一次
			$layerAddImgIndex:null,
			$tagRemoveImgLayerIndex:null,
			$updateImgTag:null,
			$closeAddImgParagraph:$("a#closeAddImgParagraph"),
			imgDataArray:new Array(),
			tagDataArray:new Array(),
			maxWidth:820,// 图片显示最大宽度
			$inTag:$("input#inTag"),// 辅助搜索框
			uploadInterval:null,
			uploadIntervalNum:1
		}
		
		// 初始化
		this.init();
	}
	$.fn.travels.prototype={
		init:function(){
			this.bind();
		},
		/**
		 * 绑定事件
		 */
		bind:function(){
			
			// 页面加载图片
			this.initLoadImg();
			
			// 触发选择文件事件
			this.triggerFile();
			
			// 生成缩略图
			this.uploadImg();
			
			// 上传
			this.done();
			
			// 关闭
			this.close();
			
			// 辅助搜索
			this.watchInTag();
			
			// 添加标签
			this.doneChooseImgTag();
			
			// 绑定点击标签移除事件
			this.tagClick();
			
			// 图片解除标签的关联[完成按钮]事件
			this.tagRemoveImgDone();
			
			// 页面点击修改标签事件
			this.updateImgTagA();
			
			// 删除图片或标签事件
			this.btnUpClose();
			
			// 多图拖拽
			this.imgsDrag();
		},
		initLoadImg:function(){

			// 回调解析页面重新进行排序
			// 清空图片缓存
			var imgDataArray = this.e.imgDataArray;
			imgDataArray.splice(0,imgDataArray.length);
	  		// 获取所有的添加图片
	  		$("div[insertPageImg]").each(function(index){
	  			var img = $(this).find("img");
	  			// 重新排序
			  	imgDataArray.push({
		  			width:img.attr("width"),// 宽度
		  			//height:img.attr("height"),// 高度
		  			src:img.attr("data-original"),// 阿里云地址
		  			tag:img.attr("tag"),// 标签默认为空
		  			tagId:img.attr("tagId"),// 标签ID
		  			tagName:img.attr("tagName"),// 标签名称
		  			scenicType:img.attr("scenic-type"),// 景点标签类型
		  			seq:img.attr("seq"),// 默认排序
		  			sort:"",// 数据库排序
		  			isNew:false,// 标识为新增加的图片，在有历史数据的时候优先显示
		  			contentId:img.attr("contentId")// ID
		  		});
		  	
	  		})
		},
		/**
		 * 删除图片或标签
		 */
		btnUpClose:function(){
			var _this = this;
			$(document).on("click","span.btn-upClose",function(){
				var $this = $(this),
					$li = $this.closest("li"),
					closeType = $this.attr("close-type");
				if(closeType == "img"){
					$li.remove();
				}else{
					var ttId = $li.attr("tt-id"),
						tagId = $li.attr("tag-id"),
						imgDataArray = _this.e.imgDataArray,
						isHasImg=false,
						item;

					// 判断该标签是否关联着图片
					for(var i=0,len=imgDataArray.length;i<len;i++){
						item = imgDataArray[i];
						if(item.tagId == tagId){
							isHasImg=true;
							break;
						}
					}
					
					// 删除提示
					if(isHasImg){
						layer.confirm("删除该标签，其对应的图片也一并解除关联关系，是否继续删除？",{icon:3,title:"删除提示"},function(index){
							layer.close(index);
							_this.deleteTagPost(ttId, $li ,tagId);
						})
					}else{
						_this.deleteTagPost(ttId, $li ,tagId);
					}
					
				}
			})
		},
		/**
		 * 请求后台删除标签
		 * @param tagId 标签id
		 * @param $li 父元素进行页面更新
		 */
		deleteTagPost:function(ttId,$li,tagId){
			var _this = this,
				item,
				tagDataArray = _this.e.tagDataArray;
			$.post("travelsTag/delete.json",{
				tagId:ttId
			},function(data){
				if(data){
					if(data.RESPONSE_STATE == "200"){
						$li.remove();
						
						// 更新标签数组项目
						for(var i = 0,len = tagDataArray.length;i<len;){
							if(tagDataArray[i].ttId == ttId){
								// 移除该下标元素
								tagDataArray.splice(i,1);
								
								// 更新数组长度
								len = tagDataArray.length;
							}else{
								// 在数组没有发生变化的情况下才可自增
								i++;
							}
						}
						
						// 更新图片关联的数据
						$.each(_this.e.imgDataArray,function(index,item){
							if(item.tagId == tagId){
								item.tag="",
								item.tagId="";
								item.tagName="";
							}
						})
						
						_this.showTagAndUnLinkImg();
					}else{
						layer.msg(data.ERROR_INFO,{icon:0})
					}
				}
			})
		},
		/**
		 * 图片修改标签事件
		 */
		updateImgTagA:function(){
			var _this = this;
			$(document).on("click","a[update-img-tag-a]",function(){
				_this.initUpdateImgTagPage();
				
				_this.e.$updateImgTag=layer.open({
					type:1,
					title:false,
					closeBtn:false,
					area:[$(window).width()+"px",$(window).height()+"px"],
					content:$("div#paragraphAddImgDiv")
				})
			})
		},
		/**
		 * 初始化更改图片标签的页面
		 */
		initUpdateImgTagPage:function(){
			var _this = this;
			
			// 隐藏关闭按钮
			_this.e.$closeAddImgParagraph.hide();
			
			// 绑定完成按钮事件，插入页面图片
			_this.insertPageImg(icoHtml(),_this.e.$updateImgTag);
			
			// 展示历史标签（未关联/已关联的标签）和图片（未关联的图片）
			_this.showTagAndUnLinkImg();
			
			// 更改上传按钮显示
			_this.e.$doneUpload.text("完成").css("background-color","#ff8a01");
			
			// 关闭选择图片上传事件
			_this.e.$addImgBtn.remove();
			
			_this.e.$imgFileElement.val("");
		},
		/**
		 * 点击上传触发选择文件元素
		 */
		triggerFile:function(){
			var _this = this;
			$(document).on("click","a#addImg",function(){
				//_this.e.$imgFileElement.trigger("click");
				$("a#selectfiles").parent().find("input[multiple]").trigger("click");
			})
		},
		/**
		 * 上传图片
		 */
		uploadImg:function(){
			var _this = this;
			_this.e.$imgFileElement.on("change",function(){
				
				// 设置样式
				if($("div#paragraphAddImgDiv").css("display") == "none"){
					$("div#paragraphAddImgDiv>div.img-panel").css({
						"max-height":($(window).height()-250)+"px"
					})
					_this.e.$layerAddImgIndex=layer.open({
						type:1,
						title:false,
						closeBtn:false,
						area:[$(window).width()+"px",$(window).height()+"px"],
						content:$("div#paragraphAddImgDiv")
					})
				}
				
	    		var $this = $(this),
	    			files = this.files,
	    			file,
	    			image,
	    			reader,
	    			max = 1*1024*1024;

				// 清除历史图片
				//_this.e.$addImgBtn.closest("ul").find("li[wait-upload-img]").remove();
	    		for(var i=0,len=files.length;i<len;i++){
	    			file = files[i];
	    			if(file.size > max){
	    				layer.msg("请上传<span style='color:red;'>小于等于[1M]</span>的图片",{icon:0});
	    				if(len==1){
		    				$this.val("");
	    				}
	    				continue;
	    			}
	    			if(file.type.indexOf("image",0) == -1){
	    				layer.msg("请上传<span style='color:red;'>图片</span>(小于等于[1M])。",{icon:0});
	    				if(len==1){
		    				$this.val("");
	    				}
	    				continue;
	    			}
					
	    			reader = new FileReader();
				    reader.readAsDataURL(file);
				    reader.onload = function(e) {
				    	var time = new Date().getTime();
				    	var image='<li id="'+time+'_img" wait-upload-img >'
				    		+'<img src="'+this.result+'" id="uploadImgBase64" upload-type="no" />'
				    		+'<div class="progress-bar"><div class="completed" style="width:20%"></div></div>'
				    		+'<div class="img-mask">'
				    		+'待上传'
				    		+'</div>'
				    		+'<span class="btn-upClose" close-type="img"></span>'
				    		+'</li>';
						_this.e.$addImgBtn.closest("li").before(image);
						reader = null;
			    		
						// 显示图片数量
			    		_this.e.$iImgNum.text(_this.e.$uploadImgUl.find("li").length-1);
			    		
			    		// 更改上传按钮显示
						_this.e.$doneUpload.text("上传").css("background-color","#ff8a01");
						
						// 设置被拖数据的数据类型和值
						
				    }
	    		}
			})
		},
		/**
		 * 上传事件
		 */
		done:function(){
			var _this = this;

			_this.e.$doneUpload.on("click",function(){
				var images = $("img#uploadImgBase64[upload-type=no]");
				if(images.length == 0){
					$("a#selectfiles").parent().find("input[multiple]").trigger("click");
				}else{
					// 关闭选择图片上传事件
					_this.e.$addImgBtn.remove();
					
					// 删除关闭页面入口
					_this.e.$closeAddImgParagraph.hide();
					
					// 开启队列
					//_this.imageUpload(images.eq(0));
					
					// 更改上传按钮显示
					//_this.e.$doneUpload.text("剩余:"+images.length+"张").css("background-color","#999999");
					
					_this.e.$imgFileElement.val("");
				}
				
				set_upload_param(uploader, '', false);
				
				// 关闭上传功能
				_this.e.$doneUpload.off("click");
				_this.e.$doneUpload.text("上传中").css("background-color","#DDDCDC")
				
				_this.e.uploadInterval=window.setInterval(function(){
					var str = "上传中";
					for(var i=0;i<_this.e.uploadIntervalNum;i++){
						str += ".";
					}
					_this.e.$doneUpload.text(str);
					if(_this.e.uploadIntervalNum==3){
						_this.e.uploadIntervalNum=1;
					}else{
						_this.e.uploadIntervalNum++;
					}
				},300)
				
				return false;
			});
			/*_this.e.$doneUpload.on("click",function(){
				
				// 获取需要上传的图片
				var images = $("img#uploadImgBase64[upload-type=no]");
				
				if(images.length == 0){
					_this.e.$imgFileElement.trigger("click");
				}else{
					// 关闭选择图片上传事件
					_this.e.$addImgBtn.remove();
					
					// 删除关闭页面入口
					_this.e.$closeAddImgParagraph.hide();
					
					// 开启队列
					_this.imageUpload(images.eq(0));
					
					// 更改上传按钮显示
					_this.e.$doneUpload.text("剩余:"+images.length+"张").css("background-color","#999999");
					
					_this.e.$imgFileElement.val("");
				}
					
			})*/
		},
		/**
		 * 关闭
		 */
		close:function(){
			var _this = this;
			_this.e.$closeAddImgParagraph.on("click",function(){

				layer.close(_this.e.$layerAddImgIndex)
				
			})
		},
		/**
		 * 上传图片
		 * @param $this	图片对象
		 */
		imageUpload:function($this){
			var _this = this,
				$div = $this.next(),
				img = new Image(),
				width;
				//height;
			

			// 将该图片页面调整后的宽和高保存至db
			img.src = $this.prop("src");
	  		img.onload = function(){
	  			width = img.width>_this.e.maxWidth?_this.e.maxWidth:img.width;
	  			//height = img.width>_this.e.maxWidth?_this.e.maxWidth*img.height/img.width:img.height;
				
				// 设置上传中样式
				$div.html("<a></a>");
				
				var day = $this.closest("#day"),
			    	html = icoHtml(),
			    	contentId="";
			    
				if(day.html()!=null){
			    	contentId = day.attr("content");
			    	if(contentId==""||contentId==null){
			    		contentId = addDay(day);
			    	}
			    	html = icoHtml("day");
				}

				// 发送请求
	   			$.post("imageUpload/base64Img.json",{
	   				// base64位码
	   				dataUrl:$this.attr("src"),
	   				sort:sort,
	   				id:travelsId,
	   				width:width,
	   				content_id:contentId
	   			},function(data){
	   				
	   				if(data.RESPONSE_STATE == "200"){
	   					// 更新排序
	   					sort = data.sort;
	   					
	   					// 上传成功
	   					$div.html("").css({
	   						"background":"none"
	   					});
	   					$this.attr("upload-type","yes");

	   					// 存储数据
	   					img.src = data.savePath;
	   			  		img.onload = function(){
	   	   			  		// 保存添加的图片数据
	   	   			  		_this.e.imgDataArray.push({
	   	   			  			width:width,// 设定最高宽度
	   	   			  			//height:height,// 根据最高宽度自动计算高度
	   	   			  			src:data.savePath,// 阿里云地址
	   	   			  			tag:"",// 标签默认为空
	   	   			  			tagId:"",// 标签ID
	   	   			  			tagName:"",// 标签名称
	   	   			  			scenicType:"",
	   	   			  			seq:new Date().getTime(),// 默认排序
	   	   			  			sort:data.sort,// 数据库排序
	   	   			  			isNew:true,// 标识为新增加的图片，在有历史数据的时候优先显示
	   	   			  			contentId:data.contentId// ID
	   	   			  		})
	   	   			  		
	   			  		}
	   				}else{
	   					$this.after("<span style='color:red;'>error</span>");
	   				}
	   				
	   				// 上传完成后x秒上传一次
	   				setTimeout(function(){
	   					
	   					// 获取需要上传的图片
						var images = $("img#uploadImgBase64[upload-type=no]");
						if(images.length>0){
							// 开启队列
							_this.imageUpload(images.eq(0));
							
							// 更改上传按钮显示
							_this.e.$doneUpload.text("剩余:"+images.length+"张").css("background-color","#999999");
								
						}else{
							
							// 更改上传按钮显示
							_this.e.$doneUpload.text("完成").css("background-color","#ff8a01");

							// 绑定完成按钮事件，插入页面图片
							_this.insertPageImg(html,_this.e.$layerAddImgIndex);
							
	   	   			  		// 展示历史标签（未关联/已关联的标签）和图片（未关联的图片）
							_this.showTagAndUnLinkImg();

						}
	   					 
	   				},_this.e.$taskMin*1000)
	   			})
	  		}
		},
		/**
		 * 向页面插入数据
		 */
		insertPageImg:function(html,layerIndex){
			var _this = this;
			// 关闭上传事件
			_this.e.$doneUpload.off("click");
			_this.e.$doneUpload.on("click",function(){
					// 删除历史数据
					var	str="",
						item,
						contentId="",
						imgDataArray = _this.e.imgDataArray;
					for(var i=0,len=imgDataArray.length;i<len;i++){
						item = imgDataArray[i];
						if(item.isNew){
							str+='<div insertPageImg id="content-img" class="add-img-box clearfix" content='+item.contentId+'>'
								+'<div class="img-list">'
								+'<img src="'+item.src+'" width="'+item.width+'" tag="'+item.tag+'" scenic-type="'+item.scenicType+'" tagId="'+item.tagId+'" tagName="'+item.tagName+'" seq="'+item.seq+'" sort="'+item.sort+'" isNew="false" contentId="'+item.contentId+'" />'
								+'<div class="turn" style="width:'+item.width+'px;">'
								+'<i class="ico-cover"></i>'
								+'<a href="javascript:void(0)" id="cover" class="set-cover">设为封面</a>'
								+'<a class="edit-del w del-cover" style="margin-left:'+(item.width-135)+'px;" id="delete-photos" title="删除" role="button" ></a>'
								+'</div>'
								+'<div class="img-tag" >'
								+'<a update-img-tag-a href="javascript:void(0)" show-tagName >'
								+'<i></i>'+(item.tagName==""?"为照片关联地点":item.tagName)
								+'</a>';
							
							if(item.tagName != ""){
								str+='<a update-img-tag-a class="edit-again" title="编辑" role="button"></a>';
							}
								
							str+='</div>'
								+'</div>'
								+'</div>'
								+html;
						}
						
					}
					if(currentClickAddImg){
						// 向页面写入数据
						currentClickAddImg.closest("div.add-button").after(str);
					}
					
					// 防止图片后来新关联了标签页面未更新
					for(var i=0,len=imgDataArray.length;i<len;i++){
						item = imgDataArray[i];
						contentId = item.contentId;
						if(!item.isNew){
							var $div = $("div[content="+contentId+"]");
							// 更新标签
							$div.find("div.img-tag>a[show-tagName]").html("<i></i>"+(item.tagName==""?"为照片关联地点":item.tagName));
							
							// 是否显示编辑域
							if(item.tagName!="" && $div.find("div.img-tag>a.edit-again").length == 0){
								$div.find("div.img-tag").append('<a update-img-tag-a class="edit-again" title="编辑" role="button"></a>');
							}else if(item.tagName == ""){
								$div.find("div.img-tag>a.edit-again").remove();
							}
							
							// 更新img
							var img = '<img src="'+item.src+'" width="'+item.width+'" tag="'+item.tag+'" tagId="'+item.tagId+'" tagName="'+item.tagName+'" seq="'+item.seq+'" sort="'+item.sort+'" isNew="false" contentId="'+item.contentId+'" />';
							$div.find("img").remove().end().find("div.img-list").prepend(img);
						}
					}
					
					// 关闭添加图片页面
					layer.closeAll();
					
					// 初始化添加图片页面
					_this.initPage();
					
					// 更新DB图片关联的标签
					_this.updateDBImgTag();
			})
		},
		/**
		 * 更新DB图片关联的标签
		 */
		updateDBImgTag:function(){
			var _this = this;
			
			var param={}
			$.each(_this.e.imgDataArray,function(index,item){
				param["img["+index+"].content_id"]=item.contentId;
				param["img["+index+"].tag_id"]=item.tagId;
				param["img["+index+"].tag_type"]=item.tag;
				param["img["+index+"].scenicType"]=item.scenicType;
			})
			
			$.post("travels/updateImgTag.json",param,function(data){
				// db sort
				contentsort(function(){
					// 回调解析页面重新进行排序
					// 清空图片缓存
					var imgDataArray = travels.e.imgDataArray;
					imgDataArray.splice(0,imgDataArray.length);
	   			  		// 获取所有的添加图片
	   			  		$("div[insertPageImg]").each(function(index){
	   			  			var img = $(this).find("img");
	   			  			
	   			  			// 重新排序
		   	   			  	imgDataArray.push({
		   			  			width:img.attr("width"),// 宽度
		   			  			//height:img.attr("height"),// 高度
		   			  			src:img.attr("src"),// 阿里云地址
		   			  			tag:img.attr("tag"),// 标签默认为空
		   			  			tagId:img.attr("tagId"),// 标签ID
		   			  			tagName:img.attr("tagName"),// 标签名称
		   			  			scenicType:img.attr("scenic-type"),// 标签景点类型
		   			  			seq:img.attr("seq"),// 默认排序
		   			  			sort:"",// 数据库排序
		   			  			isNew:false,// 标识为新增加的图片，在有历史数据的时候优先显示
		   			  			contentId:img.attr("contentId")// ID
		   			  		});
	   	   			  	
	   			  		})
				});
			})
		},
		/**
		 * 初始化页面
		 */
		initPage:function(){
			var _this = this;
			// 隐藏标签
			$("div[img-tag]").hide();
			$("div[img-tag-show]").hide();
			
			// 写入添加图片按钮
			_this.e.$uploadImgUl.html('<li><a class="btn-add" id="addImg" title="添加图片" role="button"><i></i></a></li>');
			
			// 重新绑定添加图片按钮
			_this.e.$addImgBtn = $("a#addImg");
			
			// 注销按钮完成事件
			_this.e.$doneUpload.off("click");
			
			// 展示关闭按钮
			_this.e.$closeAddImgParagraph.show();
			
			// 注册上传按钮
			_this.done();
			
			// 清空注册框
			_this.e.$inTag.val("");
			
			// 清空标签数据
			$("div[img-tag-show]").find("ul").html("");
			
		},
		/**
		 * 标签辅助搜索
		 */
		watchInTag:function(){
			$("input#inTag").watch(function(val){
				
				if(val.trim() != ""){
					var type = $("select#chooseImgTag").val();
					$.post("travels/selectTags.json",{
						keyword:val,
						type:type
					},function(data){
						if(data){
							var item,i=0,lis="",en;
							for(var len=data.length;i<len;i++){
								item = data[i];
								en=item.en_name==null?"":"("+item.en_name.trim()+")";
								lis += "<li data-id='"+item.id+"' tagType="+type+" scenic-type="+item.type+" zhName="+item.zh_name+">"+item.zh_name+en+"<span class='span1'>"+item.belongs+"</span></li>";
								
							}
							$("ul#showTagsUl").html(lis);
							$("ul#showTagsUl").show();
							$("div#show-drag-img-title").show();
						}
					});
				}else{
					$("ul#showTagsUl").hide();
				}
				
			})
		},
		/**
		 * 选择标签生成标签卡
		 */
		doneChooseImgTag:function(){
			var _this = this;
			$("ul#showTagsUl").on("click","li",function(){
				var $this = $(this),
					time = new Date().getTime();
				// 标签类型
				var type="";
				if($this.attr("tagType") == "spot"){
					type=3;
				}else if($this.attr("tagType") == "dining"){
					type=1;
				}else{
					type=2;
				}
				
				// 请求添加标签
				$.post("travelsTag/insert.json",{
					tt_type:type,
					tt_type_id:$this.attr("data-id"),
					tt_travels_id:travelsId,
					scenicType:$this.attr("scenic-type")
				},function(data){
					if(data){
						if(data.RESPONSE_STATE == "200"){
							// 更新DB页面生成标签
							var str='<li class="type-item" scenic-type="'+$this.attr("scenic-type")+'" tt-id="'+data.ttId+'" tag-title="'+$this.attr("zhName")+'" tag-type="'+type+'" tag-id="'+$this.attr("data-id")+'" >'
							+'<div class="img-area" trigger-remove-img id="'+time+'_area" ondragover="allowDrop(event)" ondrop="drop(event)" >'
							+'<div class="img-mask" id="'+time+'_mask" >'
							+'<i class="'+$this.attr("tagType")+'" id="'+time+'_i" ></i>'
							+'</div>'
							+'</div>'
							+'<div class="p-title">'+$this.attr("zhName")+'</div>'
							+'<span class="btn-upClose" close-type="tag"></span>'
							+'</li>';
						
							$("div[img-tag-show]").find("ul").append(str).end().show();
							$("ul#showTagsUl").hide();
							
							
							// 更新标签缓存
							_this.e.tagDataArray.push({
								"tagType":type,
								"tagId":$this.attr("data-id"),
								"scenicType":$this.attr("scenic-type"),
								"time":time,
								"iClass":$this.attr("tagType"),
								"pTitle":$this.attr("zhName"),
								"ttId":data.ttId,
								"scenicType":data.scenicType
							})
							
						}else{
							layer.msg(data.ERROR_INFO,{icon:0})
						}
					}
				})
			})
		},
		/**
		 * 点击标签移除事件
		 */
		tagClick:function(){
			var _this = this;
			$(document).on("click","div[trigger-remove-img]",function(){
				var num = 0,
					$this = $(this),
					$li = $this.parent(),
					lis="",
					title=$li.attr("tag-title"),
					tagId=$li.attr("tag-id"),
					imgDataArray = _this.e.imgDataArray,
					item;
			 
				// 计算该标签关联的数据
				for(var i=0,len=imgDataArray.length;i<len;i++){
					item = imgDataArray[i];
					if(item.tagId == tagId){
						num++;
						lis+='<li remove-img-link-tag contentId='+item.contentId+' >'
							+'<img src="'+item.src+'" />'
							+'<div class="img-mask"><a class="remove-a" href="javascript:void(0)">移出 X</a></div>'
							+'</li>';
					}
				}
				
				// 如果不存在关联则return
				if(num == 0){
					return;
				}else{
					
					var type="";
					if($this.attr("tag-type") == "3"){
						type="spot";
					}else if($this.attr("tag-type") == "1"){
						type="dining";
					}else{
						type="hotel";
					}
					
					// 更改要移除的数据
					$("div#remove_img_page i#tag_img_num").text(num);
					$("div#remove_img_page ul#tag_show_img_ul").html(lis);
					$("div#remove_img_page #show_tag_title").html('<i class="ico-'+type+'"></i>'+title);
				}
				
				// 打开移除页面
				_this.e.$tagRemoveImgLayerIndex=layer.open({
					type:1,
					title:false,
					closeBtn:false,
					area:[$(window).width()+"px",$(window).height()+"px"],
					content:$("div#remove_img_page")
				})
			})
			
			// 绑定未来移除事件
			$("div#remove_img_page").on("click","li[remove-img-link-tag]",function(){
				var $this = $(this),
					imgDataArray = _this.e.imgDataArray,
					contentId = $this.attr("contentId"),
					item,
					updateImgTagItem;
				// 修改图片关联属性
				for(var i=0,len=imgDataArray.length;i<len;i++){
					item = imgDataArray[i];
					if(item.contentId == contentId){
						item.tag="";// 标签默认为空
						item.tagId="";// 标签ID
						item.tagName="";// 标签名称
						break;
					}
				}
				$this.fadeOut(300,function(){
					$this.remove();
				});
			})
		},
		/**
		 * 标签删除图片页面完成按钮
		 */
		tagRemoveImgDone:function(){
			var _this = this;
			
			$("div#remove_img_page a#tag_remove_img_done").on("click",function(){
				var imgItem,
					tagItem,
					src="",
					imagesNew="",
					images="",
					num,
					tagDataArray = _this.e.tagDataArray;
					imgDataArray = _this.e.imgDataArray;
					
				// 排序重组（包含历史未关联标签的图片）
				for(var i=0,len=imgDataArray.length;i<len;i++){
					imgItem = imgDataArray[i];
					if(imgItem.tag == ""){
						if(imgItem.isNew){
							imagesNew += '<li draggable="true" isChooseCache="false" ondragstart="imgStartDrag(event)" id="'+imgItem.seq+'" >'
				    		+'<img src="'+imgItem.src+'" id="uploadImgBase64" upload-type="no" />'
				    		+'<div class="img-mask" style="background:none;">'
				    		+'</div>'
				    		+'</li>';
						}
						// 历史图片
						else{
							images += '<li draggable="true" isChooseCache="false" ondragstart="imgStartDrag(event)" id="'+imgItem.seq+'" >'
					    		+'<img src="'+imgItem.src+'" id="uploadImgBase64" upload-type="no" />'
					    		+'<div class="img-mask" style="background:none;">'
					    		+'</div>'
					    		+'</li>';
						}
					}
				}
				// 排序后重新展示图片缩略图
				_this.e.$uploadImgUl.html(imagesNew+images);
				
				// 更新标签背景
				for(var i=0,len=tagDataArray.length;i<len;i++){
					tagItem = tagDataArray[i];
					num = 0;
					// 匹配该标签是否还有关联的图片
					for(var j=0,jlen=imgDataArray.length;j<jlen;j++){
						imgItem = imgDataArray[j];
						if(tagItem.tagId == imgItem.tagId){
							num++;
							src = imgItem.src;
							break;
						}
					}
					// 如果已经没有关联的数据那么设置为null背景
					if(num==0){
						$("div[img-tag-show] li[tag-id="+tagItem.tagId+"] div.img-area").css({"background":"none"});
					}else{
						$("div[img-tag-show] li[tag-id="+tagItem.tagId+"] div.img-area").css({'background':'url('+src+') no-repeat center top','background-size': 'cover'});
					}
						
				}
				
				// 关闭页面
				layer.close(_this.e.$tagRemoveImgLayerIndex);
			})
		},
		/**
		 * 展示历史标签（未关联/已关联的标签）和图片（未关联的图片）
		 */
		showTagAndUnLinkImg:function(){
			var _this = this,
				imgDataArray = _this.e.imgDataArray,
				item,
				style,
				imgItem,
				images="",
				imagesNew="",
				tagDataArray = _this.e.tagDataArray;
			
			// 展示选择标签div
			$("div[img-tag]").slideDown(300);
			
			// 判断是否含有历史标签库
			if(tagDataArray.length>0){
				var str = "";
				for(var i=0,len=tagDataArray.length;i<len;i++){
					// 获取该标签
					item = tagDataArray[i];
					
					// 是否已经关联图片，展示样式
					style="";
					
					// 判断已经关联的照片是否跟该标签已经进行关联
					for(var j=0,jlen=imgDataArray.length;j<jlen;j++){
						imgItem = imgDataArray[j];
						if(imgItem.tagId == item.tagId ){
							style = 'style="background:url('+imgItem.src+') no-repeat center top;background-size:cover"';
							break;
						}
					}
					
					// 循环展示标签数据
					str+='<li class="type-item" scenic-type="'+item.scenicType+'" tt-id="'+item.ttId+'" tag-title="'+item.pTitle+'" tag-type="'+item.tagType+'" tag-id="'+item.tagId+'" >'
					+'<div class="img-area" trigger-remove-img '+style+' id="'+item.time+'_area" ondragover="allowDrop(event)" ondrop="drop(event)" >'
					+'<div class="img-mask" id="'+item.time+'_mask" >'
					+'<i class="'+item.iClass+'" id="'+item.time+'_i" ></i>'
					+'</div>'
					+'</div>'
					+'<div class="p-title">'+item.pTitle+'</div>'
					+'<span class="btn-upClose" close-type="tag"></span>'
					+'</li>';
				}
				$("div[img-tag-show]").find("ul").append(str).end().show();
			}
			
			// 排序重组（包含历史未关联标签的图片）
			for(var i=0,len=imgDataArray.length;i<len;i++){
				item = imgDataArray[i];
				if(item.tag == ""){
					if(item.isNew){
						imagesNew += '<li draggable="true" isChooseCache="false" ondragstart="imgStartDrag(event)" id="'+item.seq+'" >'
			    		+'<img src="'+item.src+'" id="uploadImgBase64" upload-type="no" />'
			    		+'<div class="img-mask" style="background:none;">'
			    		+'</div>'
			    		+'</li>';
					}
					// 历史图片
					else{
						images += '<li draggable="true" isChooseCache="false" ondragstart="imgStartDrag(event)" id="'+item.seq+'" >'
				    		+'<img src="'+item.src+'" id="uploadImgBase64" upload-type="no" />'
				    		+'<div class="img-mask" style="background:none;">'
				    		+'</div>'
				    		+'</li>';
					}
				}
			}
			
			// 排序后重新展示图片缩略图
			_this.e.$uploadImgUl.html(imagesNew+images);
		},
		/**
		 * 多图拖拽
		 */
		imgsDrag:function(){
			var _this = this,
				isPp=true,
				$li,
				item,
				draggable;
			// 选中需要拖拽的图片集合
			$(document).on("click","li[ondragstart]",function(){
				$li = $(this);
				isChooseCache=$li.attr("isChooseCache");
				
				if(isChooseCache == "false"){

					// 添加选中样式
					$(this).find("div.img-mask").html("<i></i>").css({"background":"rgba(0, 0, 0, 0.4)"});
					
					$.each(cacheChooseImg,function(index,item){
						if(item == $li.prop("id")){
							isPp=false;
						}
					})
					
					if(isPp){
						// 缓存选择的图片
						cacheChooseImg.push($(this).prop("id"))
					}
					
					// 更改状态
					$li.attr("isChooseCache","true");
				}else{

					// 添加选中样式
					$(this).find("div.img-mask").html("").css({"background":"none"});
					
					for(var i=0,len=cacheChooseImg.length;i<len;){
						item = cacheChooseImg[i];
						
						if(item == $li.prop("id")){
							cacheChooseImg.splice(i, 1);
							len=cacheChooseImg.length
						}else{
							i++
						}
					}
					
					// 更改状态
					$li.attr("isChooseCache","false");
				}
			})
			
		}
	}
	
	travels = new $.fn.travels();
	
})
