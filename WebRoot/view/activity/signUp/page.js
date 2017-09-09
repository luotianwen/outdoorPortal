/*
 * The new version of leader'details page
 * author: zhangxuemei
 * 2014-11-19
 * last-modify: 2014-11-19
 */
//报名人超过上限
var bm_overUpLimit = "<div class='alertMsgDialog'>" +
    "<span class='close'></span>" +
    "<div class='cont'>" +
    "<p class='message'>提示：</p>" +
    "<div class='newsCont'>" +
    "<p>同一个订单最多只能报名10个人</p>" +
    "<button class='submitBtn1'>确定</button>" +
    "</div>" +
    "</p>" +
    "</div>";
//常用联系人超过上限
var cn_overUpLimit = "<div class='alertMsgDialog'>" +
    "<span class='close'></span>" +
    "<div class='cont'>" +
    "<p class='message'>提示：</p>" +
    "<div class='newsCont'>" +
    "<p>常用报名人已超过数量上限，</p>" +
    "<p>编辑常用报名人信息，请到买家用户中心。</p>" +
    "<button class='submitBtn1'>确定</button>" +
    "</div>" +
    "</p>" +
    "</div>";

$(function () {
    var usualBmSerial = 1;
    var order = 1;
    $(".tabs1").tabs();

    $('.showTip').hover(function () {
        $(this).next().show();
    }, function () {
        $(this).next().hide();
    });

    $(document).on("click",".alertMsgDialog .submitBtn1",function() {
        $(this).parents('.alertMsgDialog').find('.close').click();
    });

    $('.contactGroupList dt i').hover(function () {
        $(this).parents('.innerBmInfo').find('.helpTip').show();
    }, function () {
        $(this).parents('.innerBmInfo').find('.helpTip').hide();
    });

    //勾选常用联系人
    $("input[name=contact]").change(function () {
        var spId = $(this).attr("spid");
    	if($(this).is(":checked")) {
            if($("#sp-"+spId).length > 0){return false;}
            if(!checkPeopleNum(10)){
                console.log($(this)[0].checked);
                $(this)[0].checked=0;
                alertMsg('提示', bm_overUpLimit);
                return false;
            }
            var tn = $(this).attr("tn");
            var mb = $(this).attr("mb");
            var sex = $(this).attr("gd");
            var card = $(this).attr("cad");
            var emmb=$(this).attr("emmb");
            var emn=$(this).attr("emn");
            var exp=$(this).attr("exp");
            var btd=$(this).attr("btd");
            var eqp=$(this).attr("eqp");
            var cdy=$(this).attr("cdy");
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
			$('.addBmUser').before(getTmpl(order,spId,tn,mb,card,btd,eqp,exp,emn,emmb,insure,emrequire,exprequire,eqprequire));
			deleteSignUpUser();
            $('.contactItemLIst .contactItem').each(function(){
                if($(this).attr('spid') == spId) {
                    $(this).find(".id_sex_"+sex).attr("checked","checked");
                    $(this).find(".cart_type_"+cdy).attr("selected","selected");
                    if(cdy==2){
                        $(this).find(".cardBox").html("<input type='text' name='sp["+order+"].idCart' class='hzCardNum text-input validate[required,custom[hzId],minSize[1],maxSize[20],notequalscard[id_card_num]] id_card_num' data-errormessage-value-missing='* 请输入护照号码' value='"+card+"'>")
                    }else if(cdy==3){
                        $(this).find(".cardBox").html("<input type='text' name='sp["+order+"].idCart' class='gatCardNum text-input validate[required,custom[gatId],minSize[1],maxSize[20],notequalscard[id_card_num]] id_card_num' data-errormessage-value-missing='* 请输入港澳回乡证或台胞证号码' value='"+card+"'>")
                    }else if(cdy==4){
                        $(this).find(".cardBox").html("<input type='text' name='sp["+order+"].idCart' class='hzCardNum text-input validate[required,custom[jgId],minSize[1],maxSize[20],notequalscard[id_card_num]] id_card_num' data-errormessage-value-missing='* 请输入军官证号码' value='"+card+"'>")
                    }else{
                        $(this).find(".cardBox").html("<input type='text' name='sp["+order+"].idCart' class='idCardNum text-input validate[required,custom[chinaId],notequalscard[id_card_num]] id_card_num' data-errormessage-value-missing='* 请输入身份证号码' value='"+card+"'>");
                    }
                    $(this).find("input[name='needSave']").attr("checked","checked");
                }
            })
            usualBmSerial++;
            order++;
		}else {
            $('.contactItemLIst .contactItem').each(function(){
                if($(this).attr('spid') == spId) {
                    $(this).remove();
                }
            })
            usualBmSerial--;
        }
        handBMBtn(usualBmSerial);
        calculateMoney();
        calculateOrder(usualBmSerial);
        checkLinkman();
    });

    $('.close').click(function () {
        closeAlertMsg();
    });
    //新增常用联系人
    $('#addUser').click(function (e) {
        if (!checkPeopleNum(10)) {
            $('#addUser').alertMsg('提示', bm_overUpLimit);
            return false;
        }
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
        $(this).parent().before(getTmpl(order, 0, '', '', '', '', '', '', '', '',insure,emrequire,exprequire,eqprequire));
        deleteSignUpUser();
        $(this).parent().prev().find("input[name='needSave']").click();
        usualBmSerial++;
        order++;
        handBMBtn(usualBmSerial);
        //动态计算序号
        calculateOrder(usualBmSerial);
        //计算钱
        calculateMoney();
        checkLinkman();
        e.preventDefault();
    })


    //常用联系人模块收缩
    $('.enfold').click(function () {
        var spId = $(this).parents(".contactItem").find("input[name='spId']").val();
        var _this = $(this);
        if (_this.hasClass('retract')) {
            _this.addClass('deploy').removeClass('retract');
            _this.html('展开');
            if((spId && spId>0) || usualBmSerial>2){
                _this.parents().prev('.ci_del').hide();
            }
        } else {

            _this.removeClass('deploy').addClass('retract');
            _this.html('收起');
            if((spId && spId>0)|| usualBmSerial>2) {
                _this.parents().prev('.ci_del').show();
            }
        }
        _this.parent().find('dd').slideToggle();
    })

    $('.addNewContact').click(function () {
        $("#sp-form input[name='spId']").remove();
        resetForm();
        $(this).next().show();
    })

    //订单备注
    $('.orderArea').textAreaLimit('100');

    //常用报名人-表单验证
    jQuery("#bmInfoForm").validationEngine('attach', {
    });

    //协议书
    var massNews = "<div class='alertMsgDialog innerProtocol'>" +
                        "<span class='close'></span>" +
                        "<div class='cont'>" +
                            "<h5 class='title'><p>玩嘛网用户参加活动协议</p></h5>" +
                            "<div class='newsCont'>" +
                                "<p>1.  玩嘛网的户外&户外旅游活动&活动均由供应商提供，玩嘛公司并非活动组织者。</p>" +
                                "<p>2.  为了您的权益行到更好的保障，请使用玩嘛“自游通”结算系统。如果供应商不兑现活动计划中承诺的责任和义务，你可以在旅行活动结束三天之内，向“自游通”旅行结算系统发起拒绝支付申请，我们将尽量保全您的出行费用，并协助您挽回损失。</p>" +
                                "<p>3.  户外出行有风险，玩嘛建议您参加活动前购买户外保险。玩嘛保险和各大保险公司以及中国最大的电子商务保险公司战略合作，为大家推出多款保险。几分钟操作，即可多一份保障，最低5元，详情请点击 玩嘛保险。</p>" +
                                "<p>4.  了解活动强度，确定自己能够参与，参见 玩嘛活动强度分级。</p>" +
                                "<p>5.  如果选择自行购买保险，风险自担。 </p>" +
                                "<p>特别提示：玩嘛网的户外&户外旅游活动&活动均由供应商提供。供应商充分借用玩嘛网的网络平台，推出全方位的户外旅游活动，活动的行程安排以及合同签订都是由合作户外旅游活动供应商为您提供。 玩嘛网作为您获取户外旅游活动的地点，本协议的签署并不意味着玩嘛网成为户外旅游活动交易的参与者，对前述交易玩嘛网仅提供技术支持，不对供应商行为的合法性、有效性及户外旅游活动的真实性、合法性及有效性作任何明示或暗示的担保。在报名由玩嘛网为您展示的户外旅游活动前，请您仔细阅读本须知，并注意本须知及活动页面中的其它重要条款也作为双方协议的补充内容。当您开始报名户外旅游活动时，即表明您已经仔细阅读并接受本协议的所有条款。</p>" +
                            "</div>" +
                        "</div>" +
                    "</div>";

    $('.popProtocol').alertMsg("绿野领队备案承诺书", massNews);
})

function deleteSignUpUser(){


    //删除此报名人
    $('.ci_del').on('click',function(){
        if(confirm("确定删除此联系人？")) {
            //反选
            var spid = $(this).parents(".contactItem").find("input[name='spId']").val();
            if(Number(spid)){
                $(".contactGroupList").find("input[spid='"+spid+"']")[0].checked=0;
            }

            $(this).parents('.contactItem').remove();
            usualBmSerial--;
            handBMBtn(usualBmSerial);
            //动态计算序号
            calculateOrder(usualBmSerial);
            checkLinkman();
            calculateMoney();
        }
    })
	
}

function handBMBtn(usualBmSerial){
    if(usualBmSerial==3){
        var obj = $(".contactItem").first();
        if(obj.find(".retract").length>0){
            $(".ci_del").show();
        }
    }
    if(usualBmSerial==1){
        $('#addUser').click();
        $(".ci_del").hide();
    }
    //判断最后一个不是常用联系人的
    if(usualBmSerial==2){
        var spid = $(".contactItem").find("input[name='spId']").val();
        if(!Number(spid)){
            $(".ci_del").hide();
        }
    }
    if(usualBmSerial>10){
        $(".addBmUser").hide();
    }else{
        $(".addBmUser").show();
    }
}

function checkLinkman(){
    if($("input[name='linkMan']:checked") && $("input[name='linkMan']:checked").length>0){
    }else{
        $(".contactItem").eq(0).find("input[name='linkMan']").attr("checked","checked");
    }
}

function calculateOrder(n) {
    $('.contactItemLIst .contactItem').each(function(){
        var _order = $(this).find('.order');
        _order.html('');
        for(var i = 0;i < n-1; i++) {
           $('.contactItemLIst .contactItem:eq('+i+')').find('.order').text(i+1); 
        }
        
    })
}

function checkPeopleNum(num){
    if($('.contactItem').length>=num){
        return false;
    }
    return true;
}

function resetForm() {
    $("#id_sex_0").attr("checked", "checked");
    $("#cart_type_1").attr("selected", "selected");
    $("#sp-form input[name='mobile']").val("");
    $("#sp-form input[name='truename']").val("");
    $("#sp-form input[name='idCart']").val("");
    $("#sp-form input[name='birthday']").val("");
    $("#sp-form textarea[name='equipment']").val("");
    $("#sp-form textarea[name='experience']").val("");
    $("#sp-form input[name='emgcusername']").val("");
    $("#sp-form input[name='emgcmobile']").val("");
    $("#sp-form input[name='spId']").remove();
}
function calculateMoney() {
    var num = $('.contactItem').length;
    var price = $("#order-form input[name='eventPrice']").val();
    var preFee = $("#order-form input[name='eventPrepayFee']").val();
    var totalPrice = price * num;
    var totalPreFee = preFee * num;
    var applyNumber = $("#order-form input[name='applyNumber']").val(num);
    var feeAmount = $("#order-form input[name='feeAmount']").val(totalPrice);
    var prepayFee = $("#order-form input[name='prepayFee']").val(totalPreFee);
    $("#fee-amount-sp").html(totalPrice + "元");
    $("#fee-amount-i").html("（" + price + "元×" + num + "）");
    $("#pre-fee-sp").html(totalPreFee + "元");
    $("#pre-fee-i").html("（" + preFee + "元×" + num + "）");
}


function getTmpl(order,spid,tn,mb,card,btd,eqp,exp,emn,emmb,insureBuy,emrequire,exprequire,eqprequire){
    return "<div class='contactItem clearfix' id='sp-"+spid+"' spid='"+spid+"'>"+
        "<a class='ci_del'>删除此报名人</a>"+
        "<dl>"+
        "<div class='enfold retract'>收起</div>"+
        "<div class='ci-header fn-pr'>"+
        "<input type='hidden' name='spId' id='spId' value='"+spid+"' />"+
        "<table class='tableheader'><tbody>"+
        "<tr><span class='order'>"+order+"</span></tr>"+
        "<tr>"+
        "<th><span class='mustfill'>*</span>真实姓名：</th>"+
        "<td colspan='2'>"+
            "<input type='text' name='sp["+order+"].truename' maxlength='' class='titleinput text-input validate[required,minSize[2],maxSize[10]] truename' data-errormessage-value-missing='* 请输入真实姓名' title='只能输入2~10个汉字' value='"+tn+"'>"+
            "<div class='setContact'><input id='setcontact' name='linkMan' type='radio' value=''><label for='setcontact'>设为报名联系人</label></div>"+
        "</td>"+
        "</tr>"+
        "</tbody></table>"+
        "</div>"+
        "<dt></dt>"+
        "<dd>"+
        "<table class='basicinfo'><tbody>"+
        "<tr>"+
        "<th><span class='mustfill'>*</span>性别：</th>"+
        "<td colspan='2'>"+
        "<ul class='sexrow'>"+
        "<li><label for='id_sex_1'><input class='id_sex_1' name='sp["+order+"].sex' type='radio' value='1' checked='checked'>男</label></li>"+
        "<li><label for='id_sex_2'><input class='id_sex_2' name='sp["+order+"].sex' type='radio' value='2'>女</label></li>"+
        "</ul>"+
        "</td>"+
        "</tr>"+
        "<tr>"+getInsureCardTmpl(order,card,insureBuy)+
        "</tr>"+
        "<tr>"+getInsureBirthTmpl(order,btd,insureBuy)+
        "<th><span class='mustfill'>*</span>手机号码：</th>"+
        "<td>"+
        "<input type='text' name='sp["+order+"].mobile' class='text-input validate[required,custom[telphone]] mobile' data-errormessage-value-missing='* 请输入手机号码' value='"+mb+"'/>"+
        "</td>"+
        "</tr></tbody></table>"+
            eqpAndexpTmpl(order,exp,eqp,exprequire,eqprequire)+
        "<table class='basicinfo'><tbody>"+
            getEmgMobileTmpl(order,emn,emmb,emrequire)+
        "<tr>"+
        "<th></th>"+
        "<td><input type='checkbox' name='needSave' class='saveUserBtn'><label for='saveUser' class='saveUserLabel'>保存为常用报名人</label></td>"+
        "</tr>"+
        "</tbody></table>"+
        "</dd>"+
        "</dl>"+
        "</div>";
}
/**
 * 保险相关模板
 */
function getInsureCardTmpl(order,card,insureBuy){
    if(insureBuy == 1){
        return "<th><span class='mustfill'>*</span>证件类型：</th>"+
            "<td>"+
            "<label class='styledselect'>"+
            "<select class='validate[required] id_card id_card_select' name='sp["+order+"].cartType' index='"+order+"'>"+
            "<option value='1' class='cart_type_1' selected='selected'>身份证</option>"+
            "<option value='2' class='cart_type_2'>护照</option>"+
            "<option value='4' class='cart_type_4'>军官证</option>"+
            "<option value='3' class='cart_type_3'>港澳回乡证或台胞证</option>"+
            "</select>"+
            "</label>"+
            "</td>"+
            "<th><span class='mustfill'>*</span>证件号码：</th>"+
            "<td class='cardBox'><input type='text' name='sp["+order+"].idCart' class='idCardNum text-input validate[required,custom[chinaId],notequalscard[id_card_num]] id_card_num' data-errormessage-value-missing='* 请输入证件号码' value='"+card+"'/></td>";
    }
    return "";
}

function getInsureBirthTmpl(order,btd,insureBuy){
    if(insureBuy == 1){
        return "<th><span class='mustfill'>*</span>出生日期：</th>"+
            "<td>"+
            "<input type='text' value='"+btd+"' name='sp["+order+"].birthday' class='text-input validate[required] birthday' data-errormessage-value-missing='* 请输入出生日期' onclick='WdatePicker()'>"+
            "</td>";
    }
    return "";
}

function getEmgMobileTmpl(order,emn,emmb,emrequire){
    if(emrequire == 1){
        return "<tr>"+
            "<th><span class='mustfill'>*</span>应急联系人姓名：</th>"+
            "<td colspan='2'><input type='text' value='"+emn+"' name='sp["+order+"].emgcusername' class='titleinput text-input validate[required,minSize[2],maxSize[10]] emgcusername' data-errormessage-value-missing='* 请输入应急联系人姓名' title='只能输入2~10个汉字'></td>"+
            "</tr>"+
            "<tr>"+
            "<th><span class='mustfill'>*</span>应急联系人电话：</th>"+
            "<td colspan='2'>"+
            "<input type='text' name='sp["+order+"].emgcmobile' value='"+emmb+"' class='text-input validate[required,custom[telphone] notbmequals[mobile]] emgcmobile' data-errormessage-value-missing='* 请输入应急联系人电话'>"+
            "</td>"+
            "</tr>";
    }
    return "";
}

function eqpAndexpTmpl(order,exp,eqp,exprequire,eqprequire){
    if(exprequire == 1 && eqprequire == 1){
        return "<table class='attchInfo'><tbody>"+
            getEquipmentTmpl(order,eqp,eqprequire)+
            "<tr class='splitline'></tr>"+
            getExperienceTmpl(order,exp,exprequire)+
            "</tbody></table>";
    }else if(exprequire != 1 && eqprequire != 1){
        return "";
    }else{
        return "<table class='attchInfo'><tbody>"+
            getEquipmentTmpl(order,eqp,eqprequire)+
            getExperienceTmpl(order,exp,exprequire)+
            "</tbody></table>";
    }


}

function getExperienceTmpl(order,exp,exprequire){
    if(exprequire == 1) {
        return "<tr class='hwTr'>" +
            "<th class='vtop'><span class='mustfill'>*</span>户外经验：</th>" +
            "<td>" +
            "<textarea class='experience validate[required,maxSize[150]] experience' name='sp[" + order + "].experience' cols='30' rows='5' title='装备情况' data-errormessage-value-missing='* 请输入户外经验'>" + exp + "</textarea>" +
            "<div class='tipMsg'></div>" +
            "</td>" +
            "</tr>";
    }
    return "";
}

function getEquipmentTmpl(order,eqp,eqprequire){
    if(eqprequire == 1) {
        return  "<tr class='devTr'>"+
            "<th class='vtop'><span class='mustfill'>*</span>装备情况：</th>"+
            "<td>"+
            "<textarea class='devices validate[required,maxSize[150]] equipment' name='sp["+order+"].equipment' cols='30' rows='5' title='装备情况' data-errormessage-value-missing='* 请输入装备情况'>"+eqp+"</textarea>"+
            "<div class='tipMsg'></div>"+
            "</td>"+
            "</tr>";
    }
    return "";
}