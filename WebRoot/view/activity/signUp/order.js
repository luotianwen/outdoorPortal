$(function () {

    $(document).on("click", "#order-confirm .modifyBtn", function (ev) {
        $("#order-confirm").find(".bmInfoBox").find(".bm_user_list").html("");
        $("#order-confirm").hide();
        $("#order-info").show();
        ev.preventDefault();
    });
    var date = new Array();
    /*$(document).on("click", "#order-confirm .submitBtn", function (ev) {
        if ($(this).hasClass("disabled")) {
            return false;
        }
        //检测人数
        if (checkCfmPNum(10)) {
            $('#order-info .submitBtn').alertMsg('提示', bm_overUpLimit);
            return false;
        }
        //不是disabled状态1秒内阻止再次提交
        date.push(new Date());
        //小于1秒则认为重复提交
        if (date.length > 1 && (date[date.length - 1].getTime() - date[date.length - 2].getTime() < 1000)) {
            return false;
        }
        //加disabled防止1秒后重复提交
        $("#order-confirm input[name='fullPay']").val(0);
        orderSubmit(0);
    });*/
    $(document).on("click", "#order-confirm .comfirmBtn", function (ev) {
        // ev.preventDefault();
        if ($(this).hasClass("disabled")) {
            return false;
        }
        //检测人数
        if (checkCfmPNum(10)) {
            $('#order-info .comfirmBtn').alertMsg('提示', bm_overUpLimit);
            return false;
        }
        //不是disabled状态1秒内阻止再次提交
        date.push(new Date());
        //小于1秒则认为重复提交
        if (date.length > 1 && (date[date.length - 1].getTime() - date[date.length - 2].getTime() < 1000)) {
            return false;
        }
        //加disabled防止1秒后重复提交
        $("#order-confirm input[name='fullPay']").val(1);
        orderSubmit(1);
    });
    $(document).on("click", "#order-info .comfirmBtn", function () {

        var checked = $("input[name='aggrementSign']").is(":checked");
        if(checked){
            $("#order-form input[name='aggrement']").val(1);
        }else{
            showMessage("您未同意<<玩嘛活动报名须知>>");
            return false;
        }
        var hasSavedPeople = $("#bmInfoForm .contactGroupList ul li").length;
        calculateMoney();
        var needSavePeople = 0;
        var tipSavePeopleOverflow=false;
        if(!$("#order-info").find(".contactItem") || $("#order-info").find(".contactItem").length == 0){
            showMessage("您还没添加报名人哦");
            return false;
        }
        if(!$("#bmInfoForm").validationEngine("validate")){
            return false;
        }
        if (!checkPeopleNum(11)) {
            $('#order-info .comfirmBtn').alertMsg('提示', bm_overUpLimit);
            return false;
        }
        //拷贝费用
        var feehtml = $("#order-info").find(".finishBox .freebox").clone().html();
        $("#order-confirm").find(".finishBox .freebox").html(feehtml);
        //拷贝备注
        var memo = $("#order-info").find("#orderArea").val();
        $("#order-confirm").find(".orderBzBox").find(".cont").find("p").html(memo == "" ? "无" : memo);
        $("#order-confirm input[name='notes']").val(memo);
        $("#order-confirm").find(".bmInfoBox").find(".bm_user_list").html("");
        var insure = $("#bmInfoForm input[name='insure']").val();
        var emrequire = $("#bmInfoForm input[name='emrequire']").val();
        var exprequire = $("#bmInfoForm input[name='exprequire']").val();
        var eqprequire = $("#bmInfoForm input[name='eqprequire']").val();
        if(!Number(insure)){
            insure = 0;
        }
        if(!Number(emrequire)){
            emrequire = 0;
        }
        if(!Number(exprequire)){
            exprequire = 0;
        }
        if(!Number(eqprequire)){
            eqprequire = 0;
        }
        $("#order-info").find(".contactItem").each(function (idx) {
            var name = $(this).find(".truename").val();
            var sex = $(this).find(".sexrow").find("input:checked").val();
            var gender = sex == 1 ? "男" : "女";
            var mb = $(this).find(".mobile").val();
            var cdy = $(this).find(".id_card").val();
            var cdyName = $(this).find(".cart_type_" + cdy).text();
            var cdn = $(this).find(".id_card_num").val();
            var btd = $(this).find(".birthday").val();
            var eqp = $(this).find(".devices").val();
            var exp = $(this).find(".experience").val();
            var emn = $(this).find(".emgcusername").val();
            var emmb = $(this).find(".emgcmobile").val();
            var spId = $(this).find("input[name='spId']").val();
            spId = spId == 0 ? "" : spId;
            var nsv = "";
            if (!$(this).find("input[name='needSave']").hasClass("disabled")) {
                if ($(this).find("input[name='needSave']:checked").length > 0) {
                    nsv = 1;
                    //增加的报名人
                    if(spId == ""){
                        needSavePeople +=1;
                        if(hasSavedPeople+needSavePeople>20){
                            tipSavePeopleOverflow = true;
                            nsv = "";
                        }
                    }
                }
            }
            var ilk = 0;
            if ($(this).find("input[name='linkMan']:checked").length > 0) {
                ilk = 1;
            }
            $("#order-confirm").find(".bmInfoBox").find(".bm_user_list").append(tmpl(idx, spId, nsv, ilk, name, sex, gender, mb, cdy, cdyName, cdn, btd, eqp, exp, emn, emmb,insure,emrequire,exprequire,eqprequire));
            if (ilk == 1) {
                $("#order-confirm").find(".bmInfoBox").find(".bm_user_list .uList").last().find(".status").text("已设为报名联系人");
            }
        })
        $("#order-info").hide();
        $("#order-confirm").show();
        if(tipSavePeopleOverflow){
            showMessage("常用报名人已经超过数量上限， 您可以到 常用报名人管理中 编辑常用报名人信息!");
        }
    });
})
function orderSubmit(){
	layer.load(0,{
		shade: [0.3, '#393D49']
	});
	$.post("activeSignup/insertActiveSignUp.do",$("#order-form").serialize(),function(data){
		layer.closeAll('loading');
		if(data.RESPONSE_STATE == '200'){
			location.href="view/activity/signUp/pay.jsp?out_trade_no="+data.out_trade_no;
		}else{
			layer.alert(data.ERROR_INFO,{icon:0});
		}
		
	})
	
    /*var jqXHR = $.ajax({
        url:ctx+"/od/create.shtml",
        type:"post",
        data:$("#order-form").serialize()
    })
    jqXHR.done(function (data, textStatus, jqXHR) {
        var contType = jqXHR.getResponseHeader("Content-type");
        if(contType == "text/html;charset=UTF-8") {
           $("body").html(data);
        }else{
            var fd = data.fd;
            if(fd.message && fd.message != ""){
               showMessage(fd.message+"<br/>您可以选择<a href='"+eventCtx+"/activity/--/'>其他活动</a>");
            }else{
                var items = fd.data;
                var tip  = "";
                for (var key in items) {
                  tip  = items[key]+"<br/>";
                }
                showMessage(tip);
            }
            $("#order-confirm .comfirmBtn").removeClass("disabled");
            $("#order-confirm .submitBtn").removeClass("disabled");
        }
    });
    jqXHR.fail(function (jqXHR, textStatus, errorThrown) {
        if (jqXHR.status == 500) {
            alert("系统忙,请稍后再试...");
        }
        $("#order-confirm .comfirmBtn").removeClass("disabled");
        $("#order-confirm .submitBtn").removeClass("disabled");
    });*/
    
    
}
function checkCfmPNum(num) {
    var length = $("#order-confirm").find(".bmInfoBox").find(".bm_user_list").find(".uList");
    return length > num;
}
function tmpl(idx, spId, nsv, ilk, name, sex, gender, mobile, cdy, cdyn, cdn, btd, eqp, exp, emn, emmb,insure,emrequire,exprequire,eqprequire) {
    return "<dl class='uList clearfix'>" +
        "<dt><span class='order'>" + (idx + 1) + "</span>" +
        "<input type='hidden' name='signUp[" + idx + "].lsuu_id' value='" + spId + "' />" +// 常用联系人ID
        "<input type='hidden' name='signUp[" + idx + "].needSave' value='" + nsv + "' />" +// 是否需要保存为常用联系人
        "<input type='hidden' name='signUp[" + idx + "].isLinkman' value='" + ilk + "' />" +// 是否为这些报名人的联系人
        "</dt>" +
        "<dd>" +
        "<ul>" +
        "<li>真实姓名：" + name + "<span class='status'></span><input type='hidden' name='signUp[" + idx + "].asu_user_name' value='" + name + "'></li>" +
        "<li>性别：" + gender + "<input type='hidden' name='signUp[" + idx + "].asu_user_sex' value='" + sex + "'></li>" +
        "<li>手机号码：" + mobile + "<input type='hidden' name='signUp[" + idx + "].asu_user_phone' value='" + mobile + "'></li>" +
        "</ul>" +
        "</dd>" +
        "</dl>";
}
