$(function(){
	if(currentSessionUID!=null&&currentSessionUID!=''){
		//获取站内信数量
		
		$.ajax({
    		type : "post",
    		url : "messagePrivate/messageTypeCount",
    		async : false,
    		success : function(data){
    			var count = 0;
    			if(data.sysAll){
    				if(Number(data.sysAll)>99){
    					$("#sysAll").html("99+");
    				}else{
    					$("#sysAll").html(data.sysAll);
    				}
    				
    				$("#sysAll").html(data.sysAll);
    				count += Number(data.sysAll);
    			}else{
    				$("#sysAll").html("0");
    			}
    			if($("#acAll").length>0){
    				if(data.acAll){
    					if(Number(data.acAll)>99){
    						$("#acAll").html("99+");
    					}else{
    						$("#acAll").html(data.acAll);
    					}
    					
    					$("#acAll").html(data.acAll);
    					count += Number(data.acAll);
    				}else{
    					$("#acAll").html("0");
    				}
    			}
    			if($("#orderAll").length>0){
    				if(data.orderAll){
    					if(Number(data.orderAll)>99){
    						$("#orderAll").html("99+");
    					}else{
    						$("#orderAll").html(data.orderAll);
    					}
    					
    					$("#orderAll").html(data.orderAll);
    					count += Number(data.orderAll);
    				}else{
    					$("#orderAll").html("0");
    				}
    			}
    			if(data.askAll){
    				if(Number(data.askAll)>99){
    					$("#askAll").html("99+");
    				}else{
    					$("#askAll").html(data.askAll);
    				}
    				
    				$("#askAll").html(data.askAll);
    				count += Number(data.askAll);
    			}else{
    				$("#askAll").html("0");
    			}
    			if(data.travelAll){
    				if(Number(data.travelAll)>99){
    					$("#travelAll").html("99+");
    				}else{
    					$("#travelAll").html(data.travelAll);
    				}
    				
    				$("#travelAll").html(data.travelAll);
    				count += Number(data.travelAll);
    			}else{
    				$("#travelAll").html("0");
    			}
    			if(data.walletAll){
    				if(Number(data.walletAll)>99){
    					$("#walletAll").html("99+");
    				}else{
    					$("#walletAll").html(data.walletAll);
    				}
    				
    				count += Number(data.walletAll);
    			}else{
    				$("#walletAll").html("0");
    			}
    			
    			if(count>99){
    				$("#messageAll").html("99+");
    			}else{
    				$("#messageAll").html(count);
    			}
    		}
		});
		
		//获取私信数量
		$.ajax({
    		type : "post",
    		url : "dialog/dialogContentCount.json",
    		async : false,
    		success : function(data){
				var count = Number($("#messageAll").html());
				
				if(data.dialogAll){
					if(Number(data.dialogAll)>99){
						$("#dialogAll").html("99+");
					}else{
						$("#dialogAll").html(data.dialogAll);
					}
					
					count += Number(data.dialogAll);
				}else{
					$("#dialogAll").html("0");
				}
				
				if(count>99){
					$("#messageAll").html("99+");
				}else{
					$("#messageAll").html(count);
				}
    		}
		});
		
	}
	
})

