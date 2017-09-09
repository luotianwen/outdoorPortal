
$(function(){
	
	$.fn.crud=function(option){
		var _this = this;
		$(option.target).each(function(){
			_this.init(this,option);
		})
	}
	
	$.fn.crud.prototype={
		init:function(obj,option){
			var 
			$this = $(obj),// 当前JQ 对象
			$form = $this.closest("form"),// form表单元素
			$dc=$form.find(option.dc),// 删除字段名称
			$add = $this.find("a.add"),// 添加内容按钮
			$txt = $add.next(),// 描述标题
			$fr = $this.find("div.fr"),// 编辑、删除、保存按钮DIV
			$frEdit = $fr.find("a:first"),// 编辑
			$frDelete = $fr.find("a:eq(1)"),// 删除
			$frSave = $fr.find("a:last"),// 保存
			$mc = $this.find("div.mc"),// 添加内容和展示模块
			$showTextareaNum = $mc.children("div[show-textarea-num]"),// 剩余字数div
			$spanShowTextareaNum = $showTextareaNum.children("span#show_textarea_num"),// 显示剩余字数span
			$mcFirstP = $mc.find("pre:first"),// 展示内容
			$mcTextarea = $mc.find("textarea"),// 编辑内容
			$mcLastP = $mc.find("p:last"),// 完成按钮样式
			$mcSaveA = $mcLastP.find("a"),// 完成按钮
			$save = $this.find(option.save);// 保存对象
			
			// 添加内容
			$add.on("click",function(){
				$add.hide();
				$txt.fadeIn();
				$mc.fadeIn();
				$mcTextarea.focus();
				$showTextareaNum.show();
			})
			
				
			// 点击完成
			$mcSaveA.on("click",function(){
				// 对当前编辑内容进行校验
				var val = $mcTextarea.val();
				if(val.trim() == ""){
					layer.msg("编辑内容不能为空！",{icon:6,time:1.2*1000});
					$mcTextarea.focus();
					return;
				}else{
					$mcFirstP.text(val);
				}
				
				$mcTextarea.hide();
				$mcLastP.hide();
				$mcFirstP.fadeIn();
				$fr.fadeIn();
				$showTextareaNum.hide();
				
				// 编辑完成之后自动触发提交事件
				$save.trigger("click",{
					handle:"save"
				});
			})
			
			// 编辑
			$frEdit.on("click",function(){
		
		
				$fr.hide();
				$mcFirstP.hide();
				$mcTextarea.fadeIn();
				$mcTextarea.focus();
				$mcLastP.fadeIn();
				$showTextareaNum.show();
			})
			
			
			// 删除
			$frDelete.on("click",function(){
				layer.confirm("是否确认删除["+$txt.text()+"]?",{icon:3},function(index){
					layer.close(index);
					
					$mcFirstP.text("").hide();
					$mcLastP.show();
					$mcTextarea.val("").show();
					$mc.hide();
					$fr.hide();
					$txt.hide();
					$add.fadeIn();
					$showTextareaNum.hide();
					$spanShowTextareaNum.text(400);
					
					if(activityId != ""){
						$save.trigger("click",{
							handle:"d"
						});
					}
				})
			})
			
		
		}
	}
	
	new $.fn.crud({
		target:"div.activity-info",
		save:"a#saveActiveInfo",
		dc:"input[name=dc]:hidden"
	})
	

})