$.fn.page=function(a){
	
	if(a.param){
		var str = "";
		for(i in a.param){
			str +=i+"="+a.param[i]+"&";
		}
		a.link+="?"+str;
	}else{
		a.link+="?";
	}
	
	// 分页
    laypage({
        cont: this, // 容器。值支持id名 
        pages: a.totalPage, // 通过后台拿到的总页数
        curr:  a.currentPage,// 当前页
        //skip: true, //是否开启跳页
        groups: 3, //连续显示分页数
        skin: '#FF8A01',// 如果背景为白色，要设置css
        first: 1,
        prev:"<",
        next:">",
        last: a.totalPage, //在尾页追加总页数。
        jump: function(e, first){ //触发分页后的回调
        	 if(!first){
        		window.location.href=a.link+"page="+e.curr;
             }
        }
    });
}
