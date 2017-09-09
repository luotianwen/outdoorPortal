	(function ($) {
			$.fn.inputInit=function(){
		         $(this).each(function(){
		        	 id=$(this).attr("id");
		        	 var html = "<a href=\"javascript:;\" class=\"file\">"
						     +"+选择文件"
						     +"<input type=\"file\" accept=\"image/png,image/jpeg\"  name=\"file\"  id=\""+id+"\">"
						     +"</a>"
						     +"<input  type=\"hidden\" name=\""+id+"\" />"
						     +"<ul style=\"display: none;\" class=\"ace-thumbnails clearfix\" id=\""+id+"_ul\" style=\"display:inline;text-align: center;\">"                                   							
						     +"<li>"
						     +"<img width=\"150\" id=\""+id+"_img\" height=\"150\" src=\"img/no_pic.jpg\" alt=\"150x150\">"
						     +"<div class=\"tools tools-bottom\">"
						     +"<a id=\"fileDelete\" data_id=\""+id+"\">"
						     +"<i class=\"ace-icon fa fa-times red\"></i>删除"
						     +"</a>"
						     +"</div>"
						     +"</li>"
						     +"</ul>";
						     //$(this).parent().html(html);   
						     $(this).after(html); 
						     $(this).remove();
		       });
		         $.fileChange();
		         $.fileDelete();
			 };  
		    $.fileChange = function () {
		    	$("input[type='file']").each(function(){
			    	$(this).change(function(){ 
			    		id = $(this).attr("id"); 
		 				 	$.ajaxFileUpload({  
							 	type: "POST", 
				                url:"ajaxFileUpload/upload.php",  
				                secureuri:false,  
				                fileElementId:id,  
				                dataType:'json',
				                success:function(data){
				                	data = eval("("+data+")");
				                	if(data.result == "ok"){
				                		$("#"+id+"_img").prop("src",data.url);
				                		$("input[name='"+id+"']").val(data.url);
				                		$("#"+id).parent().hide();
				                		$("#"+id+"_ul").show();
				                	}else{
				                		layer.msg('图片传输失败', {icon: 8});
				                	} 
				                } 
			             });
			    	 });
		    	});	
		    };
		    $.fileDelete = function () {
		    	$("a[id='fileDelete']").each(function(){
			    	$(this).on("click",function (){
			    		  id = $(this).attr("data_id");
				          $("#"+id+"_ul").hide();
				          $("input[name='"+id+"']").val("");
				          $("#"+id).parent().show();
				          $.fileChange();
					      $.fileDelete();
			    	 });
		    	});

		    };
		})(jQuery);