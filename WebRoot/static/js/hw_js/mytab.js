(function ($) {
    $.extend({
        'mytab': function (con) {
            con = typeof con === 'number' ? con : 400;
            var $lis = $('#my-tabs>li'), $h = $('#my-tab-buoy')
            $lis.hover(function () {
                $h.stop().animate({
                    'left': $(this).offsetParent().context.offsetLeft
                }, con);
            }, function () {
                $h.stop().animate({
                    'left': _map_lp
                }, con);
            })
        }
    });
    $.mytab(300);
}(jQuery));