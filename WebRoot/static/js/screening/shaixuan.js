$(function(){
	//选择条件
	$("#selectList").on("click","dl dd label",function(){
		var $this = $(this);
		//不限与自定义条件不触发事件
		if(!$this.hasClass("buxian")&&!$this.hasClass("zdy")){
			if($this.find("input").length>0){
				//input类型
				var type = $this.find("input").attr("type");
				//条件内容
				var tagParent;
				if($this.closest("dl").find("dt").length>0){
					tagParent = $this.closest("dl").find("dt").html();
				}else{
					tagParent = $this.closest("dl").closest("div").closest("dl").find("dt").html();
				}
				
				var $thisa = $this.find("a");
				
				//多选框
				if(type == "checkbox"){
					//已选择
					if($thisa.hasClass("selected")){
						$thisa.removeClass("selected");
						$(".hasBeenSelected .clearList").find("div label").each(function(){
							var label_this = $(this);
							if(label_this.html() == $thisa.html()){
								label_this.closest("div").remove();
								return false;
							}
						})
						var count = 0;
						if($this.closest("dl").find("dt").length>0){
							$this.closest("dl").find("dd label a").each(function(){
								if($(this).hasClass("selected")){
									count++;
								}
							})
							if(count==0){
								$this.closest("dl").find("dd label.buxian a").addClass("selected");
							}
						}else{
							$this.closest("div").closest("dl").find("dd label a").each(function(){
								if($(this).hasClass("selected")){
									count++;
								}
							})
							if(count==0){
								$this.closest("div").closest("dl").find("dd label.buxian a").addClass("selected");
							}
						}
					}
					//未选择
					else{
						$thisa.addClass("selected");
						addLabel(tagParent,$thisa.html(),$thisa.attr("search-key"));
						if($this.closest("dl").find("dt").length>0){
							$this.closest("dl").find("dd label.buxian a").removeClass("selected");
						}else{
							$this.closest("div").closest("dl").find("dd label.buxian a").removeClass("selected");
						}
					}
				}
				//单选框
				else if(type == "radio"){
					if($this.closest("dl").find("dt").length>0){
						$this.closest("dl").hide();
					}else{
						$this.closest("dl").closest("div").closest("dl").hide();
					}
					addLabel(tagParent,$thisa.html(),$thisa.attr("search-key"));
				}
			}
		}else if($this.hasClass("buxian")){
			var $thisa = $this.find("a");
			if(!$thisa.hasClass("selected")){
				$this.closest("dl").find("dd label a.selected").each(function(){
					var _this = $(this);
					_this.removeClass("selected");
					$(".hasBeenSelected .clearList").find("div label").each(function(){
						var label_this = $(this);
						if(label_this.html() == _this.html()){
							label_this.closest("div").remove();
							return false;
						}
					})
				})
				$thisa.addClass("selected");
			}
			
		}
		search();
	})
	
	//删除已选择条件
	$(".hasBeenSelected .clearList").on("click","div em",function(){
		var $this = $(this);
		var del = $this.closest("div");
		
		var span = del.find("span").html();
		var label = del.find("label").html();
		
		$("div#selectList dl dt").each(function(){
			var _this = $(this);
			if(_this.html() == span){
				if(_this.closest("dl").css("display") == "none"){
					_this.closest("dl").css("display","");
				}else{
					_this.closest("dl").find("dd label a").each(function(){
						var label_this = $(this);
						if(label_this.html() == label){
							label_this.removeClass("selected");
							
							var count = 0;
							if(label_this.closest("dl").find("dt").length>0){
								label_this.closest("dl").find("dd label a").each(function(){
									if($(this).hasClass("selected")){
										count++;
									}
								})
								if(count==0){
									label_this.closest("dl").find("dd label.buxian a").addClass("selected");
								}
							}else{
								label_this.closest("div").closest("dl").find("dd label a").each(function(){
									if($(this).hasClass("selected")){
										count++;
									}
								})
								if(count==0){
									label_this.closest("div").closest("dl").find("dd label.buxian a").addClass("selected");
								}
							}
							
							return false;
						}
					})
				}
				return false;
			}
		});
		
		del.remove();
	})
	
	//清空全部
	$('div.eliminateCriteria').on("click", function(){
		if($(".hasBeenSelected .clearList").html()!=""){
			$(".hasBeenSelected .clearList").html("");
			$("dl:hidden").each(function(){
				$(this).show();
			})
			
			$("#selectList dl dd label a").removeClass("selected");
			
			$("#selectList").find("dd label.buxian a").addClass("selected");
			search();
		}
	})
	
	//自定义价格
	$("#cusbtn").on("click",function(){
		var custext1 = $("#custext1").val();
		var custext2 = $("#custext2").val();
		
		var dl = $(this).closest("dl");
		var tagParent = dl.find("dt").html();
		var label = "";
		
		if(custext1==""){
			if(custext2!=""){
				label = "0-"+custext2;
				dl.hide();
				addLabel(tagParent,label,$(this).attr("search-key"));
				search();
			}
		}else{
			if(custext2!=""){
				label = custext1 +" - "+ custext2;
			}else{
				label = custext1+"以上";
			}
			dl.hide();
			addLabel(tagParent,label,$(this).attr("search-key"));
			search();
		}
		
	})
	
})

function addLabel(tagParent,label,search_key){
	var infor = '<div class=\"selectedInfor selectedShow\" search-key="'+search_key+'">'+
					'<span>' + tagParent + '</span>'+
					'<label>' + label + '</label>'+
					'<em type=""></em>'+
				'</div>';
	$(".hasBeenSelected .clearList").append(infor);
}

var map = new Object();

function search(){
	map = new Object();
	$(".hasBeenSelected .clearList").find("div.selectedShow").each(function(){
		var $this = $(this);
		
		if(map[$this.find("span").html()]!=""&&map[$this.find("span").html()]!=null){
			map[$this.find("span").html()] = map[$this.find("span").html()]+","+$this.find("label").html();
		}else{
			map[$this.find("span").html()] = $this.find("label").html();
		}
	});
}



