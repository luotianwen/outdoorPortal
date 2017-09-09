/*
* @Author: xuechengwei
* @Date:   2014-03-24 15:18:41
* @Last Modified by:   xuechengwei
* @Last Modified time: 2014-05-29 16:38:51
*/

$(document).ready(function(){
    // 下拉菜单左偏移
    //menuOffset($('#theme'));
    //menuOffset($('#destination'));
    //menuOffset($('#interest'));
    //menuOffset($('#community'));

    // 扫描二维码
    $('#qrcode').hover(function(){
        $(this).find('span').show();
        $(this).find('img').hide();
        $(this).parent().find('.qrcode').show();
    },function(){
        $(this).find('span').hide();
        $(this).find('img').show();
        $(this).parent().find('.qrcode').hide();
    })

    $('#online').hover(function(){
        $(this).find('span').css('display','inline-block');
        $(this).find('img').hide();
    },function(){
        $(this).find('span').hide();
        $(this).find('img').show();
    })

    $('#backtop').hover(function(){
        $(this).find('span').css('display','inline-block');
        $(this).find('img').hide();
    },function(){
        $(this).find('span').hide();
        $(this).find('img').show();
    })

    //返回顶部
    $('#backtop').click(function(){
        $('html,body').animate({scrollTop: 0}, 500);
    })

    //menu选中状态
    var _menu = $('.lc-menu-list li');
    _menu.hover(function(){
        var _submenu = $(this).find('.subitem');
        var _mname = $(this).find('.mtype');
            if(_submenu.css('visibility') === 'hidden') {
                _mname.removeClass('mselect');
            }else {
                _mname.addClass('mselect');
            }
    },function(){
        var _mname = $(this).find('.mtype');
        _mname.removeClass('mselect');
    })

    // 微信弹出二维码
    $('.qrcodepreview').click(function() {
        $('.weichatpopup').toggle();
    })
    $(document).click(function(e) {
        var target = $(e.target);
        if (!target.is('.weichatpopup') && !target.parents().is('.weichatpopup') && !target.is('.qrcodepreview')) {
            $('.weichatpopup').hide();
        }
    })

});

function menuOffset(id) {
    var gap = 20;
    var m_offset = id.find('.mtype').offset().left;
    id.find('.subitem').css('padding-left', m_offset - gap);
}
