$(function(){
	$("body").on("click","#delete",function(){
		layer.confirm("是否确定删除？",{btn: ['确定','取消'],icon:0},function(index){
			layer.close(index);
			
			$.ajax({
	    		type : "post",
	    		url : "travels/create.json",
	    		data : {id:travelsId,act:'delete',"data.o":'travels'},
	    		async : false,
	    		success : function(data){
	    			layer.msg("删除成功",{icon:1,time:1*1000,shade:0.3},function(){
	    				window.location.href = "travels/travelsNote.html?id="+travelsUserId;
	    			})
	            }
	    	});
			
		});
	})
})