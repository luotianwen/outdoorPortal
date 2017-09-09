

	// 可视区高度
	var _window_height = $(window).height();
	
	// 头部高度
	var _m_header_height = 170;/*$(window.frames["headeriframe"].document).find("body").height()*/;
	// 尾部高度
	var _active_channel_main_height = $("div.active-channel-main").height();
	// 头尾总高度
	var _content_height = _m_header_height
			+ _active_channel_main_height + 30;

	// banner高度(可视区高度-头尾总高度)
	var _banner_height = _window_height - _content_height;
	
	// 设置bannerDIV高度
	$("div.m_main").css("height", _banner_height);
	$("div#m_banner_div").css("height", _banner_height);
	$("div#layerslider").css("height", _banner_height);

	// 轮播图
	$("div#layerslider").layerSlider({

		responsiveUnder : 1280,
		layersContainer : 1280,

		skinsPath : 'static/layerslider/skins/'
	});

	// banner最小高度
	var _small_height = 700 - _content_height;
	
	// 当浏览器大小变化时
	$(window).on("resize",function(){
		_window_height = $(window).height();
		_banner_height = _window_height - _content_height;

		// banner不可压缩超过最小高度
		if (_banner_height < _small_height) {
			_banner_height = _small_height;
		}

		// bannerDIV最小高度
		$("div.m_main").css("height", _banner_height);
		$("div#m_banner_div").css("height", _banner_height);
		$("div#layerslider").css("height", _banner_height);
	})

