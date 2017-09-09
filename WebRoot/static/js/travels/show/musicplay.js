var oAudio = $("#music").get(0);

$("div#scroll").on("click","#user-head",function(){
	if(oAudio.paused){
		oAudio.play();
		$(this).addClass("user-head");
		$("div#top a#user-head").addClass("user-head");
	}else{
		oAudio.pause();
		$(this).removeClass("user-head");
		$("div#top a#user-head").removeClass("user-head");
	}
});

$("div#top").on("click","#user-head",function(){
	if(oAudio.paused){
		oAudio.play();
		$(this).addClass("user-head");
		$("div#scroll a#user-head").addClass("user-head");
	}else{
		oAudio.pause();
		$(this).removeClass("user-head");
		$("div#scroll a#user-head").removeClass("user-head");
	}
});


window.onload = function() {
	if($("#music").length>0){
		oAudio.preload = "auto";// 首先让音频文件开始下载
		// canplay：当前的音频文件可以播放了，但是中途可能因为网速的原因断断续续；canplaythrough：当前音频可以播放，并且不会出现卡顿；
		oAudio.addEventListener("canplay", function() {
			oAudio.play();// 让音频文件开始播放
		}, false);
	}
}