/**
 * 发布活动，额外费用
 */
$(function(){
	
	$.fn.cost=function(option){
		var _this = this;
		$(option.target).each(function(){
			_this.init(this);
		})
	}
	$.fn.cost.prototype={
		init:function(obj){
			var $this = $(obj),
			$saveActiveInfo = $this.find("a#saveActiveInfo"),
			$table = $this.find("table"),// 当前table
			$addCost=$table.find("a#add_cost"),// 添加其他费用
			$costSum = $this.find("span#cost_sum"),// 费用总计
			$fyIndex = $this.find("input#cost_num:hidden").val(),// 额外费用起始下标
			$checkAll = $table.find("thead label.checkbox");// 全选/反选
			
			// 全选/反选
			$checkAll.on("click",function(){
				var $checkbox = $(this);
				if($checkbox.hasClass("now")){
					$checkbox.removeClass("now");
					$table.find("tbody label.checkbox").each(function(){
						$(this).removeClass("now");
						$(this).next().val("false");
					});
				}else{
					$checkbox.addClass("now");
					$table.find("tbody label.checkbox").each(function(){
						$(this).addClass("now");
						$(this).next().val("true");
					})
				}
			})
			
			// checkbox选择额外费用
			$table.on("click","tbody label.checkbox",function(){
				var $checkbox = $(this);
				if($checkbox.hasClass("now")){
					$checkbox.removeClass("now");
					$checkbox.next().val("false");
				}else{
					$checkbox.addClass("now");
					$checkbox.next().val("true");
				}
			});
			
			// 删除按钮样式
			$table.on("mouseover","i.btn-del",function(){
				$(this).addClass("selected");
			});
			
			// 删除按钮样式
			$table.on("mouseout","i.btn-del",function(){
				$(this).removeClass("selected");
			});
			
			// 删除当前额外费用
			$table.on("click","i.btn-del",function(){
				var $btn=$(this);
				layer.confirm("是否确认删除这条费用?",{icon:3},function(index){
					layer.close(index);
					$btn.closest("tr").remove();
					
					if(activityId != ""){
						$saveActiveInfo.trigger("click",{
							handle:"d"
						});
					}
				});
			});
			
			// 添加其他费用
			$addCost.on("click",function(){
				var str='<tr>'
					+'<td>'
					+'<label class="checkbox now" >'
					+'<i class="icon-select"></i>'
					+'</label>'
					+'<input type="hidden" name="costs['+$fyIndex+'].isSave" value="true" />'
					+'</td>'
					+'<td>'
					+'<input name="costs['+$fyIndex+'].acp_cost_name" maxlength="20" type="text" class="wid01" placeholder="费用名称"/>'
					+'</td>'
					+'<td>'
					+'<input name="costs['+$fyIndex+'].acp_cost" type="text"  min="0" class="wid04" placeholder="输入金额" maxlength="6" /> 元/人'
					+'</td>'
					+'<td>'
					+'<input name="costs['+$fyIndex+'].acp_comment" maxlength="50" type="text" class="wid03" placeholder="亲！输入您的费用说明"/>'
					+'</td>'
					+'<td>'
					+'<i class="btn-del"></i>'
					+'</td>'
					+'</tr>';

				$fyIndex++;
				$(this).closest("tr").before(str);
			});
			
		
			
			
			
			// 当前费用总计
			$table.on("blur","input[type=number]",function(){
				var sum=0,
				$numbers = $table.find("input[type=number]");// 金额集合
				// 统计金额
				$numbers.each(function(index){
					var val = $(this).val();
					if(val != "" && val != 0){
						// 累计金额
						sum=sum+parseInt(val);
					}
					
					if($numbers.length-1 == index){
						$costSum.text(sum);
					}
				})
			})
		}
	}
	
	
	new $.fn.cost({
		target:"div#cost-detail"
	})
	
	$("div#cost-detail").on("keyup","input.wid04",function(){
		var $this = $(this);
		$this.val($this.val().replace(/\D/g,''));
	})
	
	$("div#cost-detail").on("onafterpaste","input.wid04",function(){
		var $this = $(this);
		$this.val($this.val().replace(/\D/g,''));
	})
	
	
})