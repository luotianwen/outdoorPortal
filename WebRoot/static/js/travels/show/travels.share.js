$(function(){

	$("div.bshare-custom").each(function(){
		bShare.addEntry({
			title : "[哒哒]我发现了一篇关于【"+address+"】的#玩嘛游记#",
			summary : title,
			pic : $(this).attr("pic")
		});
	})
})