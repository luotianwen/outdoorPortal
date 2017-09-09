/**
 * 
 * 所有页面通用js调用方法
 * WinZhong 2015-11-13
 */
$(document).ready(function() {
	$.scrollUp({
		scrollName : "scrollUp",
		topDistance : "300",
		topSpeed : 300,
		animation : "fade",
		animationInSpeed : 200,
		animationOutSpeed : 200,
		scrollText : '<i class="click-up"></i>',
		activeOverlay : !1
	});
});