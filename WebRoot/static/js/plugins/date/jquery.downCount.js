/**
 * downCount: Simple Countdown clock with offset
 * Author: Sonny T. <hi@sonnyt.com>, sonnyt.com
 */

(function ($) {

    $.fn.downCount = function (options, successBack ,overBack) {
        var settings = $.extend({
                date: null,
                offset: null
            }, options);

        // Throw error if date is not set
        /*if (!settings.date) {
            $.error('Date is not defined.');
        }

        // Throw error if date is set incorectly
        if (!Date.parse(settings.date)) {
            $.error('Incorrect date format, it should look like this, 12/24/2012 12:00:00.');
        }*/

        // Save container
        var interval,container = this;

        /**
         * Change client's local date to match offset timezone
         * @return {Object} Fixed Date object.
         */
        /*var currentDate = function () {
            // get client's current date
            var date = new Date();

            // turn date to utc
            var utc = date.getTime() + (date.getTimezoneOffset() * 60000);

            // set new Date object
            var new_date = new Date(utc + (3600000*settings.offset))

            return new_date;
        };*/

        /**
         * Main downCount function that calculates everything
         */
        var current_date = typeof(settings.currentTime) != "undefined"?new Date(settings.currentTime):new Date();
        
        function countdown (fn) {
            var target_date = new Date(settings.date); // get fixed current date
            
            // difference of dates
            var difference = target_date - current_date;

            //alert("小时"+target_date.getHours()+":"+current_date.getHours());
            
            // if difference is negative than it's pass the target date
            if (difference < 0) {
                // stop timer
                window.clearInterval(interval);

                if (overBack && typeof overBack === 'function') overBack(container);

                return;
            }

            // basic math variables
            var _second = 1000,
                _minute = _second * 60,
                _hour = _minute * 60,
                _day = _hour * 24;

            // calculate dates
            var days = Math.floor(difference / _day),
                hours = Math.floor((difference % _day) / _hour),
                minutes = Math.floor((difference % _hour) / _minute);
                seconds = Math.floor((difference % _minute) / _second);

                // fix dates so that it will show two digets
                days = (String(days).length >= 2) ? days : '0' + days;
                hours = (String(hours).length >= 2) ? hours : '0' + hours;
                minutes = (String(minutes).length >= 2) ? minutes : '0' + minutes;
                seconds = (String(seconds).length >= 2) ? seconds : '0' + seconds;

            // based on the date change the refrence wording
            var ref_days = (days === 1) ? 'day' : 'days',
                ref_hours = (hours === 1) ? 'hour' : 'hours',
                ref_minutes = (minutes === 1) ? 'minute' : 'minutes';
                //ref_seconds = (seconds === 1) ? 'second' : 'seconds';


            if (successBack && typeof successBack === 'function') successBack(container,days,hours,minutes,seconds);
            current_date.setSeconds(current_date.getSeconds()+1);
            if (fn && typeof fn === 'function') fn();
            
        };
        
        function downInterval(){
        	interval = window.setInterval(countdown, 1000);
        }
        
        // start
        countdown(downInterval);
        
    };
    
    $.fn.djs=function(option){
    	this.downCount(
    		{
    			date : option.time,
    			currentTime : option.currentTime
    		},
    		function(e, days, hours, minutes, seconds) {
    			var str = (days == "00" ? "" : days + "天")
    					+ (hours == "00" ? "" : hours + "小时")
    					+ (minutes == "00" ? "" : minutes + "分钟")
    					+ (seconds + "秒");
    			e.html(str);
    		}, function(e) {
    			e.html(option.stop);
    		}
    	);
    }

})(jQuery);