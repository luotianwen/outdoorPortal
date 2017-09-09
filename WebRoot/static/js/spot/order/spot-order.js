$(function(){
	$("ul#allLinkman").on("click","li",function(){
		if(limitNumber==0){
			return ;
		}
		var $this = $(this);
		var span = $this.find("span#linkman");
		if(span.hasClass("now")){
			$("#people").find("tr").each(function(index){
				var $tr = $(this);
				var i = span.find("i");
				if($tr.attr("data_id")==i.attr("lsuuId")){
					$tr.attr("data_id","0")
					$tr.find("td input[name='visitorName']").val("");
					$tr.find("td input[name='visitorPhone']").val("");
					$tr.find("td input[name='tp_cardNum']").val("");
					span.removeClass("now");
				}
			})
		}else{
			$("#people").find("tr").each(function(index){
				if(index>0){
					var $tr = $(this);
					var name = $tr.find("td input[name='visitorName']").val();
					if(name==null||name==""){
						var i = span.find("i");
						$tr.attr("data_id",i.attr("lsuuId"))
						$tr.find("td input[name='visitorName']").val(i.attr("name"));
						$tr.find("td input[name='visitorPhone']").val(i.attr("phone"));
						$tr.find("td input[name='tp_cardNum']").val(i.attr("cardnum"));
						span.addClass("now");
						return false;
					}
				}
			})
		}
	})
	$(".ck").click(function  () {
		$(".hidejing").slideToggle();
	});
	$(".fen").click(function  () {
		$(".baoxian").css("display","block");
		$(this).css({"border":"1px solid #ff8a01","color":"#ff8a01"})
			.siblings().not(".tleft").css({"border":"1px solid #e4e4e4","color":"#666666"});
	});
	$(".close").click(function  () {
		$(".baoxian").css("display","none");
	});
	$(".no").click(function  () {
		$(".baoxian").css("display","none");
	});
	$(".yh").click(function  () {
		$(".more").slideToggle();
	});
	$(".no").click(function  () {
		$(".more").css("display","none");
		$(this).css({"border":"1px solid #ff8a01","color":"#ff8a01"})
			.siblings().not(".tleft").css({"border":"1px solid #e4e4e4","color":"#666666"});
	});
	$(".jian").click(function  () {
		var nub=$(".num").val()
		if (nub>1) {
			nub--;
			$(".num").val(nub);
			$("#totalPrice").html((nub*price).toFixed(2));
			if(limitNumber!=0) {
				removeLinkman();
			}
		}else if(nub<1){
			layer.msg("您至少购买一张。",{icon:1,time:1*1000,shade:0.3});
		};
	});
	$(".jia").click(function  () {
		var nub=parseInt($(".num").val());
		if (stock > nub) {
			nub++;
			$(".num").val(nub);
			$("#totalPrice").html((nub*price).toFixed(2));
			if(limitNumber!=0) {
				addLinkman();
			}
		};
	});
	$("#people").on("click","tr td span.keep",function  () {
		$(this).toggleClass("nouse");
	})

	$("#startDate").bind("change", function(){
		date = $(this).val();
		product = $("input[name='d_"+date+"']");
		stock = parseInt(product.attr("stock"));
		price = (product.val()*1).toFixed(2);
		$("#stockNumber").html(stock);
		$("#totalPrice").html((product.val()*$(".num").val()).toFixed(2));
		if(limitNumber!=0) {
			addLinkman();
		}
	});


	//保存
	$("#subBut").on("click" , function(){

		var startDate=$("#startDate").val();
		if(startDate==""){
			layer.tips('日期必填', '#startDate', {
				tips: [1, '#019F95'],
				time: 1500
			});
			$("#startDate").focus();
			return;
		}
       var fetchName=$("#fetchName").val();
		if(fetchName==""){
			layer.tips('取票人姓名必填', '#fetchName', {
				tips: [1, '#019F95'],
				time: 1500
			});
			$("#fetchName").focus();
			return;
		}
		var fetchPhone=$("#fetchPhone").val();
		if(fetchPhone==""){

			layer.tips('取票人手机必填', '#fetchPhone', {
				tips: [1, '#019F95'],
				time: 1500
			});
			$("#fetchPhone").focus();
			return;
		}

		var cardNum=$("#cardNums").val().trim();
		if(cardNum==""){
			layer.tips('身份证号码必填', '#cardNums', {
				tips: [1, '#019F95'],
				time: 1500
			});
			$("#cardNums").focus();
			return;
		}

		if(!validator.isValid(cardNum)){
			layer.tips('身份证号码不正确', '#cardNums', {
				tips: [1, '#019F95'],
				time: 1500
			});
			$("#cardNums").focus();
			return;
		}
		if(limitNumber==0){
			save();
		}
		else{
			var ya=true;
			//判断真实姓名
			$("input#visitorName").each(function(){

				if(!pdusername($(this))){
					ya=false;
					return false;
				}
			});
			if(ya){
			//验证报名人手机号
			$("input#visitorPhone").each(function(){
				if(!pdphone($(this))){
					ya=false;
					return false;
				}
			});
			}
			if(ya){
		//验证身份证号是否有效
			$("input#tp_cardNum").each(function(){
				if(!IdentityCodeValid($(this))){
					ya=false;
					return false;
				}
			});
			}
			if(ya){
				save();
			}
		}

	});
})

//验证身份证号是否有效
function IdentityCodeValid($this) {
	var code = $this.val();
	var pass= true;

	if(code==""){
		layer.tips("* 身份证号不能为空",$this,{tipsMore: true});
	}else{
		//验证身份证是否有效
		if(!validator.isValid(code)){
			tip = "身份证号格式不正确";
			pass =false;
		}
		if(pass){
			var number = 0;
			if(code[16]%2==number){
				tip = "身份证号与本人不符";
				pass =false;
			}
		}
	}

	if(!pass){
		layer.tips("* "+tip,$this,{tipsMore: true});
	}

	return pass;
}
//判断真实姓名
function pdusername($this){
	var username = $this.val();
	if(username.trim()==""){

			layer.tips("* 请输入真实姓名",$this,{tipsMore: true});
			return false;

	}else{
		if(username.length<2){
			layer.tips("* 真实姓名最少两个汉字",$this,{tipsMore: true});
			return false;
		}
	}
	return true;
}

//判断手机号
function pdphone($this){
	var phone = $this.val();
	if(phone.trim()==""){
			layer.tips("* 请输入手机号码",$this,{tipsMore: true});
			return false;
	}else{
		var re= /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1})|(17[0-9]{1}))+\d{8})$/;
		if(!re.test(phone)){
			layer.tips("* 手机号码格式不正确",$this,{tipsMore: true});
			return false;
		}
	}
	return true;
}
function save(){
	$.ajax({
		type:"POST",
		url:"ticket/submitOrder.html",
		data:$("#ticketOrder").serialize(),
		datatype: "json",
		success:function(data){
			if(data.RESPONSE_STATE == "200"){
				location.replace("/ticket/payInfo?orderId="+data.orderId)

			}else{
				layer.alert(data.ERROR_INFO, {icon: 0});
			}
		},
		error: function(){
			layer.alert("未知错误！", {icon: 0});
		}
	});
}
//添加出行
function addLinkman(){
	$("#people").append(htmlStr("","",""));
}

//删除出行人
function removeLinkman(){
	$("#people").find("tr:last").remove();
}

function htmlStr(name,phone,card){
	var html ='';
	if(limitNumber==0){
		return "";
	}
		html ='<tr data_id="0">'+
				'	<td><input type="text" id="visitorName" name="visitorName" value="'+name+'" class="plp"></td>'+
				'	<td><input type="text" id="visitorPhone" name="visitorPhone" class="telephone" value="'+phone+'"></td>'+
				'	<td><input type="text" id="tp_cardNum"  name="tp_cardNum" value="'+card+'" class="id"></td>'+
				'	<td><span class="keep">保存至常用</span></td>'+
				'</tr>';

	return html;
}
