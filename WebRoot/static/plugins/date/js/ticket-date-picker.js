
var jqObj = []; //保存对象，便于点击时做操作
var ticketDateTemp = 0;
var ticketCallBack = function(){}
var ticketDate = function(k_date){
	removeCalendar();
	appendCalendar();
	//重写k_date的参数，把所有的值初始化
	k_date = {
		isDisabled : k_date.isDisabled || '0',
		maxDate : k_date.maxDate || '',
		minDate : k_date.minDate || '',
		isDayDisabled : k_date.isDayDisabled || new array(),
		className : k_date.className,
		target: k_date.target || '',
		callBack : k_date.callBack || function(){} 
	};
	ticketCallBack = k_date.callBack;
	var kDate;
	kDate = $(k_date.target).find(".doubledate");
	ticket_date();
	function ticket_date(){ //var d = new Date().getTime();
		// 给日期插件定位 
		var txt_left = kDate.offset().left;
		var txt_top = kDate.offset().top + kDate.outerHeight();
		var txt_wid = kDate.outerWidth();
		var scrollWidth = $(window).width();
		if(txt_left + 370 < scrollWidth){
			// 判断文本框的下方是否够显示弹出框的高度
			if($(document).clientHeight - txt_top < 217 && $(document).clientHeight > 217){
				$('.ticket_d_pane').attr('style','left:'+ txt_left +'px; top:'+ (kDate.offset().top - 197) +'px;');
			}
			else{
				$('.ticket_d_pane').attr('style','left:'+ txt_left +'px; top:'+ txt_top +'px;');
			}
		}
		else{
			$('.ticket_d_pane').attr('style','left:'+(txt_left+txt_wid-370)+'px; top:'+ txt_top +'px;');
		}
		$('.ticket_d_pane').show();
		//alert(111);
		// 获取当前系统时间
		var ticket_dd = new Date();
		var ticket_year = ticket_dd.getFullYear();
		var ticket_month = ticket_dd.getMonth()+1;
		var ticket_date = ticket_dd.getDate();
		var ticket_day = ticket_dd.getDay();
		var ticket_hours = ticket_dd.getHours();
		var ticket_minutes = ticket_dd.getMinutes();
		var ticket_seconds = ticket_dd.getSeconds();
		var n_time = ticket_dd.getTime();

		var todayDate = getNowDay(ticket_year,ticket_month,ticket_date);
			newDate = resetStartTime(todayDate,k_date.minDate);
			ticket_year = todayDate.split("-")[0];
			ticket_month = todayDate.split("-")[1];
		var ticket_year_now = ticket_year;
		var ticket_month_now = ticket_month;
			if(newDate && newDate != todayDate){
				ticket_year_now = newDate.split("-")[0];
				ticket_month_now = newDate.split("-")[1];
			}

		var vals = kDate.val();
		var now_year = $.trim(vals) == '' ? ticket_year_now : $.trim(vals).substring(0,4);
		var now_month = $.trim(vals) == '' ? ticket_month_now : $.trim(vals).substring(5,7);
		var now_d =  $.trim(vals) == '' ? ticket_date : $.trim(vals).substring(8,10);
		//$('.ticket_today').text(now_year+'年'+now_month+'月');
		
		

		$('.ticket_today').text(now_year+'年'+now_month+'月');
		// 上月下月
		$('a.ticket_prev_m').click(function(){
			var ticket_y = now_year;
			var ticket_m = now_month;
			if(ticket_m==1){
				now_year = ticket_y-1;
				now_month = '12';
			}
			if(ticket_m>1 && ticket_m <11){
				now_month = '0'+(ticket_m-1);
			}
			if(ticket_m>10 && ticket_m <13){
				now_month = ticket_m-1;
			}
			$('.ticket_today').text(now_year+'年'+now_month+'月');
			change_date('left');
			change_date('right');
			getTicketPrice();
		});
		$('a.ticket_next_m').click(function(){
			var ticket_y = now_year;
			var ticket_m = now_month;
			if(ticket_m>0 && ticket_m <9){
				now_month = '0'+(parseInt(ticket_m,10)+1);
			}
			if(ticket_m>8 && ticket_m <12){
				now_month = parseInt(ticket_m,10)+1;
			}
			if(ticket_m==12){
				now_year ++;
				now_month = '01';
			}
			$('.ticket_today').text(now_year+'年'+now_month+'月');
			change_date('left');
			change_date('right');
			getTicketPrice();
		});
		change_date('left');
		change_date('right');

		getTicketPrice();

		//获取门票价格
		function getTicketPrice(){
			var allPriceDates = $(".ticket_d_pane dt[class*='ticket_not_kong']");
			var length = allPriceDates.length;
			for(var d=0;d<length;d++){
				$("p",allPriceDates[d]).remove();
				var day = $(allPriceDates[d]).text() < 10 ? "0"+ $(allPriceDates[d]).text() : $(allPriceDates[d]).text();
                var date = now_year + "-" + now_month + "-" + day;
                var price = $("input[name='d_"+date+"']").val();
                if(price == undefined){
                	$(allPriceDates[d]).removeClass("ticket_not_kong").addClass("ticket_td_hui");
                }else{
                	$(allPriceDates[d]).append("<p style='color:#ff6600;margin-top:-10px;'>¥"+price+"</p>");
                    $(allPriceDates[d]).css({"line-height":"35px"});
                }
                
			}
		}
		// 日期变化函数 
		function change_date(dir){ 
			jqObj.pop(); jqObj.push(kDate);
			// 日期 -- 根据年和月计算出来 
			var ticket_y = now_year;
			var ticket_m = now_month;
			if(dir == 'right'){
				if(ticket_m == 12){
					ticket_y ++;
					ticket_m = '01';
				}
				else{
					ticket_m++;
					if(ticket_m<10){
						ticket_m = '0'+ticket_m;
					}
				}
				$('.ticket_tomorrow').text(ticket_y+'年'+ticket_m+'月');
			}
			else{
				ticket_m = (ticket_m < 10) ? '0'+parseInt(ticket_m,10) : ticket_m;
			}
			var ticket_d = now_d;
			var now_date = '';
			
			if(vals == ''){
				now_date = ticket_y+'-'+ticket_m+'-'+ticket_d;
			}
			else{
				now_date = $.trim(vals);
			}
			var ticket_max_date = new Array(31,28,31,30,31,30,31,31,30,31,30,31);
			if (((ticket_y % 4 == 0) && (ticket_y % 100 != 0)) || (ticket_y % 400 == 0)){
				ticket_max_date[1] = 29;
			}
			var this_max_date = ticket_max_date[ticket_m-1];
			// 计算星期数 
			var C = 1;  // C是从这一年的元旦算起到这一天为止（包括这一天是内）的天数
			for(var i=0;i < ticket_m - 1;i++){
				C += ticket_max_date[i];
			}
			var ticket_si = ((ticket_y - 1)%4) == 0 ? ((ticket_y - 1)/4) : ((ticket_y - 1 - (ticket_y - 1)%4)/4);
			var ticket_yibai = ((ticket_y - 1)%100) == 0 ? ((ticket_y - 1)/100) : ((ticket_y - 1 - (ticket_y - 1)%100)/100);
			var ticket_sibai = ((ticket_y - 1)%400) == 0 ? ((ticket_y - 1)/400) : ((ticket_y - 1 - (ticket_y - 1)%400)/400);
			var S= ticket_y - 1 + ticket_si - ticket_yibai + ticket_sibai + C; //求出S的值之后，除以7，余几就是星期几；除尽了就是星期日 
			var aa = (ticket_date - 1)%7;
			var bb = S%7; // 每月1号的星期数
			// TD表格的行数
			var ticket_td_lines = (bb + this_max_date)%7 == 0 ? (bb + this_max_date)/7 : (bb + this_max_date - (bb + this_max_date)%7)/7 +1;
			
			//动态添加表格数据
			var ticket_tbody;
			if(dir == 'left'){
				ticket_tbody = $('.ticket_left_t');
			}
			else{
				ticket_tbody = $('#ticket_right_t');
			}
			ticket_tbody.html('');
			var arr_tr = [];
			for(var i=0;i<ticket_td_lines;i++){
				var m_ = ticket_month < 10 ? '0'+ticket_month : ticket_month;
				var k_d_ = ticket_date < 10 ? '0'+ticket_date : ticket_date;
				var dd1 = ticket_year+'-'+m_+'-'+k_d_; //拼接当前系统时间的年月日
				if(i == 0){
					// 第一行中有空白的单元格
					for(var j = 1;j < bb+1;j ++){
						arr_tr.push('<dt class="ticket_td_kong">&nbsp;</dt>');
					}
					// 第一行中有值单元格
					var ticket_i = 1;
					for(var j=bb+1;j<=7;j++){
						var d_ = (7*i+ticket_i) < 10 ? '0'+(7*i+ticket_i) : (7*i+ticket_i);
						var mm_ = ticket_m < 10 ? '0'+parseInt(ticket_m,10) : ticket_m;
						var dd2 = ticket_y+'-'+mm_+'-'+d_;

						var cla = '';
						//alert(dd2);
						if(dd2 >= dd1){
							// disabled 特定日期
							var temp = 0;
							for (x in k_date.isDayDisabled){
								//var temp = 0;
								if(k_date.isDayDisabled[x] == dd2){
									temp ++;
								}	
							}
							if(limitMaxMinDate(dd2,k_date.maxDate,k_date.minDate)){
								cla = 'ticket_td_hui';
							}				
							else if(temp > 0){
								cla = 'ticket_td_hui';
							}
							else if(vals == ''){
								cla = 'ticket_not_kong';
								if(todayDate == dd2 && dir == 'left'){
									cla = 'ticket_not_kong td_select';
								}
							}
							else if(vals == dd2){
								cla = 'ticket_not_kong td_select';
							}
							else{
								cla = 'ticket_not_kong';
							}
						}
						else{
							if(k_date.isDisabled == '1'){
								if(vals == ''){
									cla = 'ticket_not_kong';
									if(ticket_d == d_ && dir == 'left'){
										cla = 'ticket_not_kong td_select';
									}
								}
								else if(vals == dd2){
									cla = 'ticket_not_kong td_select';
								}
								else{
									cla = 'ticket_not_kong';
								}
							}
							else{
								cla = 'ticket_td_hui';
							}
						}
						arr_tr.push('<dt class="'+cla+'" onmouseover="ticket_mouseover_(this)" onmouseout="ticket_mouseout_(this)" onclick="ticket_click_(this,'+now_date+','+ticket_y+','+ticket_m+','+ticket_d+');">'+(7*i+ticket_i)+'</dt>');
						ticket_i++;
					}
					$('.ticket_top_tr').removeClass('ticket_top_tr');
				}
				else if(i==ticket_td_lines-1){
					var ticket_i = 8-bb;
					for(var j=1;j<=7;j++){
						var dd2 = ticket_y+'-'+ticket_m+'-'+(7*(i-1)+ticket_i);
						var cla = '';
						if((7*(i-1)+ticket_i) > this_max_date){
							arr_tr.push('<dt class="ticket_td_kong">&nbsp;</dt>');
						}
						else{
							if(dd2 >= dd1){
								// disabled 特定日期
								var temp = 0;
								for (x in k_date.isDayDisabled){
				
									if(k_date.isDayDisabled[x] == dd2){
										temp ++;
									}
										
								}
								//alert(limitMaxMinDate(dd2,k_date.maxDate,k_date.minDate));
								if(limitMaxMinDate(dd2,k_date.maxDate,k_date.minDate)){
									
									cla = 'ticket_td_hui';
								}
								else if(temp > 0){
									cla = 'ticket_td_hui';
								}
								else if(vals == ''){
									cla = 'ticket_not_kong';
									//alert(7*(i-1)+ticket_i);
									if(todayDate == dd2 && dir == 'left'){
										cla = 'ticket_not_kong td_select';
									}
								}
								else if(vals == dd2){
									cla = 'ticket_not_kong td_select';
								}
								else{
									cla = 'ticket_not_kong';
								}
							}
							else{
								if(k_date.isDisabled == '1'){
									if(vals == ''){
										cla = 'ticket_not_kong';
										if(ticket_d == (7*(i-1)+ticket_i) && dir == 'left'){
											cla = 'ticket_not_kong td_select';
										}
									}
									else if(vals == dd2){
										cla = 'ticket_not_kong td_select';
									}
									else{
										cla = 'ticket_not_kong';
									}
								}
								else{
									cla = 'ticket_td_hui';
								}
							}
							arr_tr.push('<dt class="'+cla+'" onmouseover="ticket_mouseover_(this)" onmouseout="ticket_mouseout_(this)" onclick="ticket_click_(this,'+now_date+','+ticket_y+','+ticket_m+','+ticket_d+');">'+(7*(i-1)+ticket_i)+'</dt>');
						}
						ticket_i++;
					}
				}
				else{
					var ticket_i = 8 - bb;
					for(var j=1;j<=7;j++){
						var d_ = (7*(i-1)+ticket_i) < 10 ? '0'+(7*(i-1)+ticket_i) : (7*(i-1)+ticket_i);
						var mm_ = ticket_m < 10 ? '0'+parseInt(ticket_m,10) : ticket_m;
						var dd2 = ticket_y+'-'+mm_+'-'+d_;
						var cla = '';
						if(dd2 >= dd1){
							// disabled 特定日期
							var temp = 0;
								for (x in k_date.isDayDisabled){
									//var temp = 0;
									if(k_date.isDayDisabled[x] == dd2){
										temp ++;
									}
										
								}
								if(limitMaxMinDate(dd2,k_date.maxDate,k_date.minDate)){
									cla = 'ticket_td_hui';
								}
								else if(temp > 0){
									cla = 'ticket_td_hui';
								}
							else if(vals == ''){
								cla = 'ticket_not_kong';
								if(todayDate == dd2 && dir == 'left'){
									cla = 'ticket_not_kong td_select';
								}
							}
							else if(vals == dd2){
								cla = 'ticket_not_kong td_select';
							}
							else{
								cla = 'ticket_not_kong';
							}
						}
						else{
							if(k_date.isDisabled == '1'){
								if(vals == ''){
									cla = 'ticket_not_kong';
									if(ticket_d == d_ && dir == 'left'){
										cla = 'ticket_not_kong td_select';
									}
								}
								else if(vals == dd2){
									cla = 'ticket_not_kong td_select';
								}
								else{
									cla = 'ticket_not_kong';
								}
							}
							else{
								cla = 'ticket_td_hui';
							}
						}
						arr_tr.push('<dt class="'+cla+'" onmouseover="ticket_mouseover_(this)" onmouseout="ticket_mouseout_(this)" onclick="ticket_click_(this,'+now_date+','+ticket_y+','+ticket_m+','+ticket_d+',1);">'+(7*(i-1)+ticket_i)+'</dt>');
						ticket_i++;
					}
				}
			}
			ticket_tbody.html(arr_tr.join(''));
		}
	}
	$('.ticket_d_pane').show();
}

// 点击文档的其它地方让日期插件关闭
function hideCalendar(){
	$(document).click(function(e){
		var e = e || window.event;
		var r_target = e.target || e.srcElement;
		var data_pane = $(r_target).closest('.ticket_data_content_pane'); 
		if(data_pane.length > 0 || $(r_target).hasClass("doubledate")){
			return false;
		}
		else{
			$('.ticket_d_pane').hide();
		}
	});
	
}
hideCalendar();
function appendCalendar(){
	/* 日期插件的HTML元素 */
	var ticket_css = ""
	var ticket_div_date = '<div class="ticket_d_pane" style=""><iframe class="ticket_frame_d" width="370" height="203" frameborder="0"></iframe></iframe><div class="ticket_data_content_pane clearfix"><div class="ticket_prev_next_month"><a href="javascript:;" class="ticket_prev_m"></a><span class="ticket_today"></span><a href="javascript:;" class="ticket_next_m"></a></div><div class="left_table"><dl class="ticket_data_tab"><dt class="d_th_bg">日</dt><dt>一</dt><dt>二</dt><dt>三</dt><dt>四</dt><dt>五</dt><dt class="d_th_bg">六</dt></dl><dl class="ticket_date_info ticket_left_t"></dl></div></div></div>';
		$('body').append(ticket_div_date);
}
function removeCalendar(){
	/* 移除日期插件的HTML元素 */
	$(".ticket_d_pane").remove();
}
//鼠标移上
function ticket_mouseover_(obj){
	if(!$(obj).hasClass("ticket_td_kong")){
		$(obj).addClass('td_hover');
	}
}
//鼠标移走
function ticket_mouseout_(obj){
	$(obj).removeClass('td_hover');
}

//点击事件
function ticket_click_(obj,now_date,ticket_y,ticket_m,ticket_d){
	var cla = obj.className;
	if(cla.indexOf('ticket_td_hui') < 0){
		var now_month;
		if(ticket_m < 10){
			now_month = '0'+parseInt(ticket_m,10);
		}
		else{
			now_month = ticket_m;
		}
		//ticket_d = $(obj).html() == null ? now_date.substring(8,10) : ($(obj).html() < 10 ? 0 + $(obj).html() : $(obj).html());
		ticket_d = $(obj).html() == null ? now_date.substring(8,10) : (($(obj).text()).split("¥")[0] < 10 ? 0 + ($(obj).text()).split("¥")[0] : ($(obj).text()).split("¥")[0]);
		jqObj[0].val(ticket_y +'-'+ now_month +'-'+ ticket_d);
		jqObj[0].trigger("change");
		$('.ticket_d_pane').hide();
	}

}
function limitMaxMinDate(t,max,min){
	if(t <= max && t >= min){
		return 0;
	}
	else{
		return 1;
	}
}
function getNowDay(y,m,d){
	if(m < 10){
		m = "0"+m;
	}
	if(d < 10){
		d = "0" + d;
	}
	return y+"-"+m+"-"+d;
}
function resetStartTime(t,m){
	var t_arr = t.split("-");
	var m_arr = m.split("-");
	//if(m > t){
	//	for(i=0;i<t_arr.length;i++){
	if(m_arr[0] > t_arr[0]){
		return m;
	}else if(m_arr[1] > t_arr[1]){
		return m;
	}
	return false;
}
