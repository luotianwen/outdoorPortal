/**
 * @fileoverview Giant Interective Group, Inc. Javascript Library v#version.
 * 该Javascript UI库是基于jQuery的扩展。
 * <pre>
 * Copyright (C) 2004-2009 Giant Interective Group, Inc. All rights reserved.
 * 版权所有 2004-2009 上海巨人网络科技有限公司
 * </pre>
 * Download by http://sc.xueit.com
 * @version 1.0.0, #date 2009-04-06
 * @author  Zhangkai
 * Depend on jQuery 1.3.x
 */
 (function($) {
    var isShow = false;
    $.fn.tab = function(options) {
        this.opts = $.extend({},$.fn.tab.defaults, options);
		this._init();
		this.disableArr=[];
	}	
	$.fn.tab.prototype={
		_init:function(){
			var _this = this;
			if($(_this.opts.tabList).length>0){
				$(_this.opts.tabList).each(function(index){
					$(this).bind(_this.opts.eventType,function(){
						
						$(document).off("mouseover");
						
						$(_this.opts.tabList).removeClass(_this.opts.tabActiveClass);
						$(this).addClass(_this.opts.tabActiveClass);
						_this._showContent(index);
						
						if(_this.opts.eventType == "mouseover"){
							$(document).on("mouseover",function(e){
								var target = e.target;
								var jq_target = $(target);
								if(jq_target.closest("div.ui-tab-container").length == 0 
										&& jq_target.prop("class") != _this.opts.shadeClass){
									$(_this.opts.tabList).removeClass(_this.opts.tabActiveClass);
									$(_this.opts.contentList+":visible").hide();
									$(document).off("mouseover");
								}
							});
						}
						
					})
				});
			}
		},
		_showContent:function(index){
			isShow = true;
			var _this = this;
			switch(_this.opts.showType){
				case "show":
					$(_this.opts.contentList+":visible").hide();
					$(_this.opts.contentList).eq(index).show();
					isShow =false;
					break;
				case "fade":
					$(_this.opts.contentList+":visible").fadeOut(_this.opts.showSpeed,function(){
						$(_this.opts.contentList).eq(index).fadeIn(_this.opts.showSpeed,function(){
							isShow =false;
						});
					});
					break;
				case "slide":
					$(_this.opts.contentList+":visible").hide();
					/*$(_this.opts.contentList+":visible").slideUp(_this.opts.showSpeed,function(){
						
					});*/
					$(_this.opts.contentList).eq(index).slideDown(function(){
						isShow =false;
					});
					break;
			}
		},
		setDisable:function(index){
			//如果不存在
			var _this = this;
			if($.inArray(index,this.disableArr)==-1){
				this.disableArr.push(index);
				$(_this.opts.tabList).eq(index).addClass(_this.opts.tabDisableClass);
			}
		},
		setEnable:function(index){
			//如果不存在
			var _this = this;
			var i =$.inArray(index,this.disableArr);
			if(i>-1){
				this.disableArr.splice(i,1);
				$(_this.opts.tabList).eq(index).removeClass(_this.opts.tabDisableClass);
			}
		},
		triggleTab:function(index){
			$(this.opts.tabList).eq(index).trigger(this.opts.eventType);
		}
	}
    $.fn.tab.defaults = {
	   tabList:".ui-tab-container .ui-tab-list li",
	   contentList:".ui-tab-container .ui-tab-content",
       tabActiveClass:"ui-tab-active",
	   tabDisableClass:"ui-tab-disable",
	   eventType:"mouseover",									//触发事件，有click和mouseover两种类型
	   showType:"show",										//显示方式，show 直接显示，fade渐变，slide滑动
	   showSpeed:200,
	   shadeClass:"layui-layer-shade"										// 遮罩
    };
})(jQuery);
 

 new $.fn.tab({// 一级选项卡控制
 	tabList:"#search_list .ui-tab-container .ui-tab-list li",
 	contentList:"#search_list .ui-tab-container .ui-tab-content"
 });