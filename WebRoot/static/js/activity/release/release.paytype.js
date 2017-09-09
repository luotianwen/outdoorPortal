$(function(){
	$.fn.pay=function(op){
		this.op={
			$div:$(op.div),
			$radio:$(op.radio),
			payType:op.payType
		}
		
		this._init();
	}
	
	$.fn.pay.prototype={
		_init:function(){
			this._radio();
		},
		_radio:function(){
			var _this = this;
			_this.op.$radio.on("click",function(){
				_this.op.$radio.removeClass("now");
				$(this).addClass("now");
				$(this).find(_this.op.payType).prop("checked",true);
				$(this).closest("tbody").find("input[name=a_reserve_price]").prop("disabled",true).css({cursor:"no-drop"});
				$(this).closest("tr").find("input[name=a_reserve_price]").prop("disabled",false).css({cursor:"text"});
			})
		}
	}
	
	new $.fn.pay({
		div:"div#payType",
		radio:"span.radio",
		payType:"input[name=payType]"
	})
})