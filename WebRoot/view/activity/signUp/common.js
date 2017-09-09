(function ($) {
    //消息弹出框    
    $.fn.alertMsg = function (message, winBody) {
        var winCover = true;
        var cover = {
            width: $(window).width(),
            height: $(window).height(),
            cont: "<div class='cover'></div>"
        };
        return this.each(function () {
            var target = $(this);
            var winObj = $(".alertMsgDialog");
            var msgObj = $(".alertMsgDialog .message");
            target.click(function () {
                if (winCover) {
                    $("body").append(cover.cont);
                    $('.cover').css({'width': cover.width, 'height': cover.height});
                }
                if (winObj.length == 0) {
                    $("body").append(winBody);
                    winObj.show();
                }
                msgObj.html(message);
            })
            $('.close').click(function () {
                $(".alertMsgDialog").remove();
                $(".cover").remove();
            })
        })
    };

    $.fn.tabs = function (options) {
        var opt = $.extend({}, {
            active: "on"
        }, options);

        return this.each(function () {
            var tab = $(this);
            var tabCtrs = tab.find($("[data-panel]"));
            var tabPanel = tab.find(".tab-panel")
            tabCtrs.eq(0).addClass(opt.active);
            tabPanel.hide().eq(0).show();
            tabCtrs.click(function (event) {
                var tabCtr = $(this);
                var name = tabCtr.data("panel");
                tabCtrs.removeClass(opt.active);
                tabCtr.addClass(opt.active);
                tabPanel.hide();
                $(name).show();
            });
        })
    }

    // textarea输入框字数限制
    $.fn.textAreaLimit = function(word){
        var wordNum = word;
        var oT = $(this);
        var oDiv = $(this).parent().parent();
        var oP = $(this).next();
        var bBtn = true;
        var ie = !-[1,];
        var timer = null;
        var iNum = 0;

        return this.each(function() {
            $(this).focus(function() {
                if(bBtn) {
                    oP.html("<p id='a-tips'>还可以输入<i>"+ wordNum +"</i>字</p>");
                    bBtn = false;
                    toChange();
                }
            })
            $(this).blur(function() {
                if(oT.val() == '') {
                    oP.html("");
                    bBtn = true;
                }
            })

            oT.keyup(function(){
                toChange();
            })

            function toChange() {
                //var num = Math.ceil(getLength(oT.val())/2);
                var num = Math.ceil(oT.val().length);
                var oI = oDiv.find('i');
                if(num <= wordNum) {
                    oI.html(wordNum - num);
                    oI.css('color','');
                }else {
                    oI.html(-(num - wordNum));
                    oI.css('color','red');
                }
            }
            function getLength(str){
                return String(str).replace(/[^\x00-\xff]/g,'aa').length;
            }
        })
    };    

})(jQuery);

function textAreaLimitFocus(obj,word) {
    var wordNum = word;
    var oT = obj;
    var oDiv = obj.parent().parent();
    var oP = obj.next();
    var bBtn = true;
    var ie = !-[1,];
    var timer = null;
    var iNum = 0;

    if(bBtn) {
        oP.html("<p id='a-tips'>还可以输入<i>"+ wordNum +"</i>字</p>");
        bBtn = false;
        toChange();
    }
    
    if(ie) {    // 兼容性处理
        oT.onpropertychange = toChange;
    }else {
        oT.keyup(function(){
            toChange();
        })
    }
    function toChange() {
        var num = Math.ceil(oT.val().length);
        var oI = oDiv.find('i');
        if(num <= wordNum) {
            oI.html(wordNum - num);
            oI.css('color','');
        }else {
            oI.html(-(num - wordNum));
            oI.css('color','red');
        }
    }
    function getLength(str){
        return String(str).replace(/[^\x00-\xff]/g,'aa').length;
    }

} 
function textAreaLimitBlur(obj,word) {
    var wordNum = word;
    var oT = obj;
    var oDiv = obj.parent().parent();
    var oP = obj.next();
    var bBtn = true;
    var ie = !-[1,];
    var timer = null;
    var iNum = 0;
    if(oT.val() == '') {
        oP.html("");
        bBtn = true;
    }
}

//错误提示
function showMessage(message) {
    if(!$('.showMessage_cover')[0]) {
        $("body").append('<div class="showMessage_cover"><div class="messageWin"><span class="mwl"></span><span class="mwr"></span><p></p></div></div>');
        $(".messageWin p").html(message);
        $(".messageWin").fadeIn(600);
        $(".messageWin").fadeOut(5000);
        // 3秒后移除节点和遮罩层
        setTimeout("$('body').find('.showMessage_cover').remove()", "3000");
    }else {
        return false;
    }
    // 设置遮罩层的宽高
    $(".showMessage_cover").css({
        height: function () {
            return $(document).height();
        },
        width: function () {
            return $(document).width();
        }
    });
}

function alertMsg(message, winObj) {
    var winCover = true;
    var cover = {
        width: $(window).width(),
        height: $(window).height(),
        cont: "<div class='cover'></div>"
    };
    var target = $(this);
    var coverObj = $(".cover");

    if (winCover) {
        $("html").append(cover.cont);
        $('.cover').css({'width': cover.width, 'height': cover.height});
        $('.cover').append(winObj);
    }
    if (coverObj.length == 0) {
        winObj.show();
    }
}

function closeAlertMsg() {
    $(".alertMsgDialog").hide();
    $(".cover").remove();
}

