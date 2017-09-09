jQuery(function($){

    $.supersized({

        // Functionality
        slide_interval     : 3000,    // Length between transitions
        transition         : 1,    // 0-None, 1-Fade, 2-Slide Top, 3-Slide Right, 4-Slide Bottom, 5-Slide Left, 6-Carousel Right, 7-Carousel Left
        transition_speed   : 3000,    // Speed of transition
        performance        : 1,    // 0-Normal, 1-Hybrid speed/quality, 2-Optimizes image quality, 3-Optimizes transition speed // (Only works for Firefox/IE, not Webkit)

        // Size & Position
        min_width          : 0,    // Min width allowed (in pixels)
        min_height         : 0,    // Min height allowed (in pixels)
        vertical_center    : 1,    // Vertically center background
        horizontal_center  : 1,    // Horizontally center background
        fit_always         : 0,    // Image will never exceed browser width or height (Ignores min. dimensions)
        fit_portrait       : 1,    // Portrait images will not exceed browser height
        fit_landscape      : 0,    // Landscape images will not exceed browser width
        // Components
        slide_links        : 'blank',    // Individual links for each slide (Options: false, 'num', 'name', 'blank')
        slides             : [    // Slideshow Images
                                 {image : './static/js/plugins/supersized/img/1.jpg'},
                                 {image : './static/js/plugins/supersized/img/2.jpg'},
                                 {image : './static/js/plugins/supersized/img/3.jpg'}
                             ],
        random			   : false

    });
    
    /*参数说明：
    vertical_center：是否让图像垂直居中，如果为0，则图像为顶端对齐。
    slideshow：是否显示展示工具条，包括标题、图片数字和导航按钮。
    navigation：是否展示导航按钮。
    thumbnail_navigation：为1时可以通过单击缩略图导航切换图片，为0时，缩略图不显示。
    pause_hover：是否鼠标滑向图片时暂停图片切换。
    transition：图片切换效果，0-无，1-淡入淡出，2-向上滑动，3-向右滑动，4-向下滑动，5-向左滑动。
    slide_counter：是否显示切换图片的数字。
    slide_captions：是否显示图片标题。
    slide_interval：图片切换间隔时间（毫秒）
    slides：所切换的图片集合，包括图片地址image，图片标题title，图片链接url。
    autoplay：是否自动播放。
    transition_speed：切换速度，默认750。
    keyboard_nav：是否支持键盘操作切换。
    random：是否随机切换图片*/

});
