/**
 * 设置游记主图
 */
$(function(){
	$.fn.travelsImg=function(e){
		this.parent=$("div[upload-main-img-div="+e.parentElementId+"]");
		this.e=e;
		this.init();
	}
	
	$.fn.travelsImg.prototype={
		init:function(){
			this.bind();
		},
		bind:function(){
			// 绑定点击设置游记主图事件
			this.uploadMainImg();
			
			// 注册操作主图按钮事件
			if(this.e.customFn){
				this.customFn();
			}
		},
		/**
		 * 绑定点击设置游记主图事件
		 */
		uploadMainImg:function(){
			var _this = this;
			
			// 注册图片裁剪插件
			_this.initDraftImg();
			
			if(_this.e.triggerUploadElementId){
				// 点击触发图片选择事件
				_this.parent.find("#"+_this.e.triggerUploadElementId).on("click",function(){
					$("input#"+_this.e.fileId).trigger("click");
				})
			}
		},
		initDraftImg:function(){
			var _this = this;
			  // 活动主图
			 _this.e.dragImg = new $.fn.dragImg({
			  	jqId:_this.e.fileId,
			  	ratioWidth:_this.e.ratioWidth,
			  	ratioHeight:_this.e.ratioHeight,
				originalImgMinWidth:_this.e.originalImgMinWidth,// 原图最小宽度
				originalImgMinHeight:_this.e.originalImgMinHeight,// 原图最小高度
			  	target:{
			  		original:_this.parent.find("input#"+_this.e.originalInputId)
			  	},
			  	fn:function(data){
			  		
			  		// 截图完回调
			  		if(_this.e.draftCallFn){
				  		_this.e.draftCallFn(data);
			  		}
			  		
			  	}
			  });
		},
		/**
		 * 自定义事件
		 */
		customFn:function(){
			this.e.customFn(this.e.dragImg)
		}
	}
})