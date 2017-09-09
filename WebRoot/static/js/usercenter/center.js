$(function(){
			$("#fprevious").on("click",function(){
				if(fcurrent*1>1){
					$.post("users/userFollow.json",{page:fcurrent*1-1,userId:userId},function(data){
						if(data){
							fcurrent = data.page.currentPage;
							ftotal = data.page.totalPage;
							var html = "";
							for(var i=0;i<data.list.length;i++){
								
								html += "<li>"+
											"<a target='_blank' href='users/center.html?id="+data.list[i].u_id+"'>"+
											"	<img src='"+data.list[i].uHeadImg+"' width='50' height='50' />"+
											"</a>"+
											"<p>"+
											"	<a title='"+data.list[i].uName+"' target='_blank' href='users/center.html?id="+data.list[i].u_id+"'>"+data.list[i].uName+"</a>"+
											"</p>"+
										"</li>";
							}
							$("#follow").html(html);
						}
					})
				}
			})
			$("#fnext").on("click",function(){
				if(fcurrent*1!=ftotal*1){
					$.post("users/userFollow.json",{page:fcurrent*1+1,userId:userId},function(data){
						if(data){
							fcurrent = data.page.currentPage;
							ftotal = data.page.totalPage;
							var html = "";
							for(var i=0;i<data.list.length;i++){
								html += "<li>"+
											"<a target='_blank' href='users/center.html?id="+data.list[i].u_id+"'>"+
											"	<img src='"+data.list[i].uHeadImg+"' width='50' height='50' />"+
											"</a>"+
											"<p>"+
											"	<a title='"+data.list[i].uName+"' target='_blank' href='users/center.html?id="+data.list[i].u_id+"'>"+data.list[i].uName+"</a>"+
											"</p>"+
										"</li>";
							}
							$("#follow").html(html);
						}
					})
				}
			})
			
			
			
			
			$("#rprevious").on("click",function(){
				if(rcurrent*1>1){
					$.post("users/recentVisit.json",{page:rcurrent*1-1,userId:userId},function(data){
						if(data){
							rcurrent = data.page.currentPage;
							rtotal = data.page.totalPage;
							var html = "";
							for(var i=0;i<data.list.length;i++){
								
								html += "<li>"+
											"<a target='_blank' href='users/center.html?id="+data.list[i].u_id+"'>"+
											"	<img src='"+data.list[i].uHeadImg+"' width='50' height='50' />"+
											"</a>"+
											"<p>"+
											"	<a title='"+data.list[i].uName+"' target='_blank' href='users/center.html?id="+data.list[i].u_id+"'>"+data.list[i].uName+"</a>"+
											"</p>"+
										"</li>";
							}
							$("#recentVisit").html(html);
						}
					})
				}
			})
			$("#rnext").on("click",function(){
				if(rcurrent*1!=rtotal*1){
					$.post("users/recentVisit.json",{page:rcurrent*1+1,userId:userId},function(data){
						if(data){
							rcurrent = data.page.currentPage;
							rtotal = data.page.totalPage;
							var html = "";
							for(var i=0;i<data.list.length;i++){
								html += "<li>"+
											"<a target='_blank' href='users/center.html?id="+data.list[i].u_id+"'>"+
											"	<img src='"+data.list[i].uHeadImg+"' width='50' height='50' />"+
											"</a>"+
											"<p>"+
											"	<a title='"+data.list[i].uName+"' target='_blank' href='users/center.html?id="+data.list[i].u_id+"'>"+data.list[i].uName+"</a>"+
											"</p>"+
										"</li>";
							}
							$("#recentVisit").html(html);
						}
					})
				}
			})
			
			$("#iacb").on("click","#follow-button",function(){
				var $this = $(this);
				$.post("users/addFollow",{userId:userId},function(data){
					if(data.RESPONSE_STATE=="200"){
						layer.msg("已关注",{icon:1,time:1*1000,shade:0.3})
						$this.attr("id","follow-cancel");
						$this.html("取消关注");
					}else if(data.RESPONSE_STATE=="500"){
						layer.alert('数据异常，请稍后再试！！！',{title:"错误信息",icon:0});
					}
				});
			})
			
			$("#iacb").on("click","#follow-cancel",function(){
				var $this = $(this);
				$.post("users/deleteFollow",{userId:userId},function(data){
					if(data.RESPONSE_STATE=="200"){
						layer.msg("已取消关注",{icon:1,time:1*1000,shade:0.3})
						$this.attr("id","follow-button");
						$this.html("<i></i>关注");
					}else if(data.RESPONSE_STATE=="500"){
						layer.alert('数据异常，请稍后再试！！！',{title:"错误信息",icon:0});
					}
				});
			})
			
})