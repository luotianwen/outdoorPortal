$(function(){
	$(window).on("scroll",function(){
		if($(this).scrollTop() >= $("div#check_map_height").offset().top)
		{
			$("div#spotMap").addClass("spot-map");
			
		}else{
			$("div#spotMap").removeClass("spot-map");
		}
	});
	
	
})