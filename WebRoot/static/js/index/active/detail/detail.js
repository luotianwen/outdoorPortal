jQuery(document).ready(function() {
    var pageID = $("#page_ID").val();

    //评分读取
    $.ajax({
        url: '/package/' + pageID + '/star/',
        success: function(data) {
            data = JSON.parse(data);

            $("#aa-star").raty({
                score: data.aa_star,
                readOnly: true,
                path: '/static/detail/images/'
            })
            $("#bb-star").raty({
                score: data.bb_star,
                readOnly: true,
                path: '/static/detail/images/'
            })
            $("#cc-star").raty({
                score: data.cc_star,
                readOnly: true,
                path: '/static/detail/images/'
            })
            $("#dd-star").raty({
                score: data.dd_star,
                readOnly: true,
                path: '/static/detail/images/'
            })
        }
    });

    //日历模块
    var eventsData = {},
        date = new Date(),
        year = date.getFullYear(),
        month = date.getMonth();
    $.ajax({
        url: '/package/' + pageID + '/tuan_taocan/',
        //url: '/package/' + pageID + '/price_list/',
        success: function(data, status) {
            eventsData = JSON.parse(data);
			tuandata = eventsData;
            if (eventsData.length) {
                var minDate = 99999999;
                var min_i = 0;
                for (var i = 0, len = eventsData.length; i < len; i++) {
                    var date_i = parseInt(eventsData[i].start.substr(0, 4) + '' + eventsData[i].start.substr(5, 2))
                    if (minDate >= date_i) {
                        minDate = date_i;
                        min_i = i;
                    } else {
                        continue;
                    }
                }
                var eventYear = eventsData[min_i].start.substr(0, 4);
                var eventMonth = eventsData[min_i].start.substr(5, 2);
                year = year < eventYear ? year : eventYear;
                month = month < eventMonth - 1 ? eventMonth - 1 : month;
                var calendar = $("#calendar_box").find(".fullcalendar");
                var calendarHover = $("#calendarHover").find(".fullcalendar");
                var popCalendar = $("#pop-calendar"),
                    popCalendars = popCalendar.find(".fullcalendar");
                popCalendars.fullCalendar({
                    header: {
                        left: '',
                        center: 'title',
                        right: ''
                    },
                    height: 460,
                    aspectRatio: 1,
                    weekMode: 'variable',
                    year: year,
                    month: month,
                    editable: false,
                    events: eventsData
                }).eq(1).fullCalendar('gotoDate', year, month + 1);
                $(".reserve-btn").on('click', function() {
                    popCalendar.css({visibility: 'visible'});
                });
                $("#prePop").on('click', function () {
                    popCalendars.fullCalendar('prev');
                });
                $("#nextPop").on('click', function () {
                    popCalendars.fullCalendar('next');
                });
                $("#close-pop").on('click', function () {
                    popCalendar.css({visibility: 'hidden'});
                })
                calendar
                    .fullCalendar({
                        header: {
                            left: '',
                            center: 'title',
                            right: ''
                        },
                        height: 460,
                        aspectRatio: 1,
                        weekMode: 'variable',
                        year: year,
                        month: month,
                        editable: false,
                        events: eventsData
                    })
                    .eq(1).fullCalendar('gotoDate', year, month + 1);
                calendarHover
                    .fullCalendar({
                        header: {
                            left: '',
                            center: 'title',
                            right: ''
                        },
                        aspectRatio: 1,
                        weekMode: 'variable',
                        year: year,
                        month: month,
                        editable: false,
                        events: eventsData
                    })
                    .eq(1).fullCalendar('gotoDate', year, month + 1);
                $("#calendarHover").hide();
                $(".monthCtr").show();
                $("#nextMonth").on("click", function() {
                    calendar.fullCalendar('next')
                });
                $("#preMonth").on("click", function() {
                    calendar.fullCalendar('prev')
                });
                $("#nextMonth-hover").on("click", function() {
                    calendarHover.fullCalendar('next')
                });
                $("#preMonth-hover").on("click", function() {
                    calendarHover.fullCalendar('prev')
                });


				//add by bai



            }
        }
    });
    // 显隐更多日历
    if ($("#showDate").length) {
        $("#showDate").hover(function() {
            $("#calendarHover").show()
        }, function() {
            $("#calendarHover").hide()
        });
    };
    // 更多推荐
    $.ajax({
        url: '/merchant_recommend',
        data: {
            merchant: $('input[name=recommend_store]').val(),
            pk: $('input[name=recommend_pk]').val()
        },
        success: function(data, status) {
            var data = JSON.parse(data);
            var html = "";
            if (data.len) {
                $("#recommend-link").attr('class', 'link2recommend');
                html = '相关推荐';
                $("#recommend-link").append(html);
                html = '<div class="trip-recommend" id="recommend"><div id="marquee" class="recommend-wrap"><ul id="recommend-data" class="clearfix"></ul></div> <a href="javascript:;" class="prev" id="goR"></a><a href="javascript:;" class="next" id="goL"></a></div>';

                $("#recommend-cover").append(html);
                var html = "";
                for (var i = 0; i < data.len; i++) {
                    var dataItem = data.list[i],
                        place = dataItem.dest_place,
                        img = dataItem.img,
                        price = dataItem.price,
                        title = dataItem.title,
                        url = dataItem.url;

                    html += '<li><div><a href="' + url + '" target="_blank">' +
                        '<img src="' + img + '"></a>' +
                        '<div class="r-text"><a href="' + url + '" class="r-title" target="_blank">' + title + '</a>' +
                        '<p class="r-info clearfix"><span class="r-place">' + place + '</span><span class="pull-right"><b class="r-price">￥' + price + '</b>起</span></p></div></div></li>'
                }
                $("#recommend-data").append(html);
                $('#marquee').kxbdSuperMarquee({
                    isAuto: true,
                    distance: 1220,
                    time: 3,
                    duration: 40,
                    direction: 'left',
                    btnGo: {
                        left: '#goL',
                        right: '#goR'
                    }
                });

            }
        }
    })
    // 锚点跟随
    $('.title-bar>li>a,#myAffix>ul>li>a').click(function(event) {
        var offset = 70;
        event.preventDefault();
        $($(this).attr('href'))[0].scrollIntoView();
        scrollBy(0, -offset);
    });
    $('#title-bar-affix')
        .affix({
            offset: {
                top: function() {
                    return (this.top = $('#topbar').outerHeight(true) + $('.menubox').outerHeight(true) + $('#baseInfo').outerHeight(true) + 90)
                }
            }
        })
        .on("activate.bs.scrollspy", function() {
            if (!$(this).find("li").eq(0).hasClass("active")) {
                $('#myAffix').hide();
            } else {
                $('#myAffix').show();
            }
        }).find(".title-bar a").focus(function() {
            $(".title-bar").find("li").removeClass("active");
            $(this).blur().parent().addClass("active");
            $("#myAffix").removeClass("affix");
        });
    $('#myAffix')
        .affix({
            offset: {
                top: function() {
                    return (this.top = $('#topbar').outerHeight(true) + $('.menubox').outerHeight(true) + $('#baseInfo').outerHeight(true) + 200)
                },
                bottom: function() {
                    return (this.bottom = $('#feeinfo').outerHeight(true) + $('#dateprice').outerHeight(true) + $('#visainfo').outerHeight(true) + $('#booknotice').outerHeight(true) + $('#leader-introduce').outerHeight(true) + $('#faq').outerHeight(true) + $('.footer').outerHeight(true) + 460 + 55 + 165 + 338)
                }
            }
        })
        .on("activate.bs.scrollspy", function() {
            $(this).show();
            $(".title-bar>li").eq(0).addClass("active")
        })

});
