$(function(){
	// textarea长度提示
	textareaNum();
})

function textareaNum(){
	$("div[show-textarea-num]").each(function(){
		var $span = $(this).find("span#show_textarea_num");
		$(this).parent().find("textarea").watch(function(e,val){
			var str = "";
			str = val.replaceAll("\n","占位");
			
			if(str.length>2000){
				$(e).val($(e).val().substr(0,(2000+val.length-str.length)));
				str = $(e).val().replaceAll("\n","占位");
			}
			
			var maxlength = $(e).prop("maxlength"),
				residueNum = maxlength-str.length;
			if(residueNum==-1){
				residueNum = 0;
			}
		
			$span.text(residueNum);
		})
	})
}

//批量替换
String.prototype.replaceAll = function(reallyDo, replaceWith, ignoreCase) {  
	if (!RegExp.prototype.isPrototypeOf(reallyDo)) {  
		return this.replace(new RegExp(reallyDo, (ignoreCase ? "gi": "g")), replaceWith);  
	} else {  
		return this.replace(reallyDo, replaceWith);  
	}  
}