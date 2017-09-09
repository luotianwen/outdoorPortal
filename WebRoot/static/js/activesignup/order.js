$(function(){
	//验证紧急联系人姓名
	$("#bm_linkmanname").on("blur",function(){
		var bm_linkmanname = $("#bm_linkmanname").val();
		var bm_linkmanphone = $("#bm_linkmanphone").val();
		var type = "";
		if(bm_linkmanname!=""||bm_linkmanphone!=""){
			pdusername($(this),"Yjinji");
		}else{
			pdusername($(this),"Njinji");
		}
	});
	
	//验证紧急联系人手机号
	$("#bm_linkmanphone").on("blur",function(){
		var bm_linkmanname = $("#bm_linkmanname").val();
		var bm_linkmanphone = $("#bm_linkmanphone").val();
		var type = "";
		if(bm_linkmanname!=""||bm_linkmanphone!=""){
			pdphone($(this),"Yjinji");
		}else{
			pdphone($(this),"Njinji");
		}
	});
	
	//验证紧急联系人手机号
	$("#addbaoming").on("blur","#vcardnum",function(){
		IdentityCodeValid($(this));
	});
	
	//修改性别
	$("#addbaoming").on("click","#choicesex i",function() {
		var $this = $(this)
		$this.closest("#choicesex").find("span").removeClass("now");
		$this.parent().addClass("now");
	})

	//更改报名联系人
	$("#addbaoming").on("click","#set-linkman span",function() {
		var $this = $(this)
//		var isinsurance = $(isinsurance).val();
//		if(isinsurance==1){
//			$("#email").remove();
//			$this.parent().parent().find("#emails").append("<dl id='email'>"+
//						"	<dt>"+
//						"		<label><i class='c1'> * </i>邮箱地址：</label>"+
//						"	</dt>"+
//						"	<dd>"+
//						"		<input class='wid01' id='vemail' type='text' value=''/>"+
//						"	</dd>"+
//						"</dl>");
//		}
		
		$("#addbaoming #set-linkman").find("span").removeClass("now");
		$this.addClass("now");
	})
	
	//修改是否阅读报名须知
	$("#aggrementSign").on("click",function() {
		var $this = $(this)
		if($this.attr('class')=='checkbox'){
			$this.addClass("now");
		}else{
			$this.removeClass("now");
		}
	})
	
	//是否保存为常用报名人
	$("body").on("click","span#cycheckbox",function() {
		var $this = $(this)
		if($this.attr('class')=='checkbox'){
			$this.addClass("now");
		}else{
			$this.removeClass("now");
		}
	})
	
	$("span#kxcheckbox").on("click",function(){
		var $this = $(this)
		if($this.attr('class')=='checkbox'){
			$this.addClass("now");
			sumPrice();
		}else{
			$this.removeClass("now");
			sumPrice();
		}
	});
	
	//选择常用报名人
	$("span#allLinkman i").on("click",function() {
		var $this = $(this)
		var id = $this.attr('lsuuId');
		var name = $this.attr('name');
		var sex = $this.attr('sex');
		var phone = $this.attr('phone');
		var cardtype = $this.attr("cardtype");
		var cardnum = $this.attr("cardnum");

		if($this.parent().attr('class')=='checkbox'){
			$this.parent().addClass("now");
			if(numberfull()){
				$("#addbaoming").append(addstr(countLink(),id,name,sex,phone,cardtype,cardnum));
				sumPrice();
			}else{
				$this.parent().removeClass("now");
			}
		}else{
			$this.parent().removeClass("now");
			
			var bool = true;
			$("#addbaoming #count").each(function(index,element){
				$this = $(this);
				if(bool){
					if($this.parent().find("#vusername").val()==name&&$this.parent().find("#vphone").val()==phone){
						var classstr = $this.parent().find("#set-linkman span").attr("class");
						name = $this.parent().find("#vusername").val();
						phone = $this.parent().find("#vphone").val();
						var id = $this.parent().find("#vid").val()

						$this.parent().remove();
						
						if(classstr=="radio now"){
							$("#addbaoming #set-linkman").find("span").each(function(index,element){
								if(index==0){
									$(this).addClass("now");
								}
							});
						}
						
						$("span.checkbox i").each(function(index,element){
							if($this.parent().attr('class')!='checkbox'){
								if($this.attr('name')==name&&$this.attr('phone')==phone){
									$this.parent().removeClass("now");
								}
							}
						});
						
						if(countLink()==1){
							$("#addbaoming").append(addstr(countLink(),"","","","","1",""));
						}else{
							var num = 0;
							$("#addbaoming #count").each(function(index,element){
								num = index;
								$(this).text(num+1);
							});
						}
						
						if(id!=""){
							$("span#allLinkman i").each(function(index,element){
								$this = $(this);
								if($this.parent().attr('class')!='checkbox'){
									if($this.attr('name')==name&&$this.attr('phone')==phone){
										$this.parent().removeClass("now");
									}
								}
							});
						}
						
						numberfull("delete")
						
						bool = false;
					}
				}else{
					$this.text(index);
				}
			});
			sumPrice();
		}
	})
	
	//打开页面默认选择第一个联系人
	if($("#allLinkman").length > 0){
		$("#allLinkman:first i.icon-select").trigger("click");
	}else{
		if(numberfull()){
			$("#addbaoming").append(addstr(countLink(),"","","","","1",""));
		}
	}
	
	//显示姓名（仅显示报名人姓名）
	$("a#bm_showAll").on("click",function(){
		$("div#bm_show").slideDown();
		$(this).css("display","none");
		$("a#bm_showName").css("display","block");
	});
	$("a#qr_showAll").on("click",function(){
		$("div#qr_show").slideDown();
		$(this).css("display","none");
		$("a#qr_showName").css("display","block");
	});
	//显示全部信息（显示报名人所有信息）
	$("a#bm_showName").on("click",function(){
		$("div#bm_show").slideUp();
		$(this).css("display","none");
		$("a#bm_showAll").css("display","block");
	});
	$("a#qr_showName").on("click",function(){
		$("div#qr_show").slideUp();
		$(this).css("display","none");
		$("a#qr_showAll").css("display","block");
	});
	
	//删除报名人
	$("#addbaoming").on("click","#delete",function() {
		var $this = $(this);
		
		var classstr = $this.parent().find("#set-linkman span").attr("class");
		var name = $this.parent().find("#vusername").val();
		var phone = $this.parent().find("#vphone").val();
		var id = $this.parent().find("#vid").val()
		
		$this.parent().remove();
		
		if(classstr=="radio now"){
			$("#addbaoming #set-linkman").find("span").each(function(index,element){
				if(index==0){
					$(this).addClass("now");
				}
			});
		}
		
		if(id!=""){
			$("span#allLinkman i").each(function(index,element){
				$this = $(this);
				if($this.parent().attr('class')!='checkbox'){
					if($this.attr('name')==name&&$this.attr('phone')==phone){
						$this.parent().removeClass("now");
					}
				}
			});
		}
		
		if(countLink()==1){
			$("#addbaoming").append(addstr(countLink(),"","","","","1",""));
		}else{
			var num = 0;
			$("#addbaoming #count").each(function(index,element){
				num = index;
				$(this).text(num+1);
			});
		}
		numberfull("delete")
		sumPrice();
	})
	
	//增加报名人(默认值为空)
	$("#addlink").click(function(){
		if(numberfull()){
			$("#addbaoming").append(addstr(countLink(),"","","","","1",""));
			sumPrice();
		}
	});
	
	//验证当前点击的姓名
	$("#addbaoming").on("blur","#vusername",function(){
		// 判断报名人真实姓名格式是否正确
		pdusername($(this));
	});
	
	//验证当前点击的手机号
	$("#addbaoming").on("blur","#vphone",function(){
		// 判断报名人手机号格式是否正确
		pdphone($(this));
	});
	
	//确认报名信息
	$("#baoming_next").click(function () {
		//是否同意报名须知
		if($("#aggrementSign").attr('class')=="checkbox now"){
		    //将同意报名的值传到隐藏域，暂时保留
			
		}else{
			layer.alert('您未同意<<玩嘛活动报名须知>>', {
				title:"错误信息",
				icon:0
			});
		    return false;
		}
		
		var bool = 0;
		var jjbool = true;
		//验证报名人姓名
		$("input#vusername").each(function(){
			if(bool==0){
				if(!pdusername($(this))){
					bool = $(this).parent().parent().parent().find("#count").text();
				}
			}else{
				pdusername($(this));
			}
		});
		//验证报名人手机号
		$("input#vphone").each(function(){
			if(bool==0){
				if(!pdphone($(this))){
					bool = $(this).parent().parent().parent().parent().find("#count").text();
				}
			}else{
				pdphone($(this));
			}
		});
		
		//验证报名人身份证号
		$("input#vcardnum").each(function(){
			if(bool==0){
				if(!IdentityCodeValid($(this))){
					bool = $(this).parent().parent().parent().parent().parent().find("#count").text();
				}
			}else{
				IdentityCodeValid($(this));
			}
		});
		
		var bm_linkmanname = $("#bm_linkmanname").val();
		var bm_linkmanphone = $("#bm_linkmanphone").val();
		var type = "";
		if(bm_linkmanname!=""||bm_linkmanphone!=""){
			type = "Yjinji";
		}else{
			type = "Njinji";
		}
		
		//验证紧急联系人姓名
		if(jjbool){
			jjbool = pdusername($("#bm_linkmanname"),type);
		}else{
			pdusername($("#bm_linkmanname"),type);
		}
		
		//验证紧急联系人手机号
		if(jjbool){
			jjbool = pdphone($("#bm_linkmanphone"),type);
		}else{
			pdphone($("#bm_linkmanphone"),type);
		}
		
		if(bool!=0){
			//返回报名信息位置
			document.getElementById("linkman"+(bool-1)).scrollIntoView()
			return false;
		}
		if(!jjbool){
			//返回紧急联系人位置
			document.getElementById('linkman').scrollIntoView()
			return false;
		}
		
		//判断常用报名人总数
		var cynum = $("#allLinkman").length;
		var nownum = 0;
		$("#addbaoming #vid").each(function(index,element){
			var $this = $(this);
			if($this.val()==""&&$this.parent().attr('class')=='checkbox now'){
				nownum++;
			}
		});

		if(cynum+nownum>20){
			layer.alert("<p>常用报名人已超过数量上限，</p><p>您可以到 常用报名人管理中 编辑常用报名人信息!</p>", {
				title:"错误信息",
				icon:0
			});
			return false;
		}
		
		//复制报名人信息
		var linkman = 0;
		$("div#baoming #set-linkman").find("span").each(function(index,element){
			if($(this).attr("class")=="radio now"){
				linkman = index+1;
			}
		});
		$("#querenAll").html("");
		$("#alldata").html("");
		$("div#baoming").each(function(index,element){
			var $this = $(this);
			
			var id = $this.find("#vid").val();
			var count = $this.find("#count").text();
			var vusername = $this.find("#vusername").val();
			var vphone = $this.find("#vphone").val();
			var sex = 0;
			var vcardtype = $this.find("#vcardtype").val();
			var vcardtypeStr = $this.find("#vcardtype").find("option:selected").text();
			var vcardnum = $this.find("#vcardnum").val();
			var vlinkman = 0;
			var issave = 0;
			
			$this.find("dd#choicesex .radio").each(function(index,element){
				if($(this).attr("class")!="radio"){
					sex = index+1;
				}
			});
			
			if(linkman==count){
				vlinkman = 1;
			}else{
				vlinkman = 2;
			}
			
			if($this.find("#cycheckbox").attr('class')=="checkbox now"){
				issave = 1;
			}else{
				issave = 2;
			}
			
			$("#alldata").append(linkSignUpUser(count,id,vusername,sex,vphone,vcardtype,vcardnum,vlinkman,issave));
			
			$("#querenAll").append(addqrstr(count,id,vusername,sex,vphone,linkman,vcardtypeStr,vcardnum));
		});
		
		//复制紧急联系人信息
		if(bm_linkmanname==""){
			$("#qr_linkman").css("display","none");
		}else{
			$("#qr_linkmanname").text(bm_linkmanname);
			$("#qr_linkmanphone").text(bm_linkmanphone);
			$("#qr_rslinkman").text($("#bm_rslinkman").find("option:selected").text());
			$("#qr_linkman").css("display","black");
		}
		
		//复制可选费用信息
		var count = 0;
		var html = "<table>"+
				        "<tr>"+
					        "<th width='250'>费用名称</th>"+
					        "<th width='250'>补差费用</th>"+
					        "<th colspan='2'>费用说明</th>"+
				        "</tr>";
		$("#qr_kx_content").html("");
		$("span#kxcheckbox").each(function(index,element){
			var $this = $(this);
			var fymc = $this.attr("fymc");
			var danjia = $this.attr("danjia");
			var fysm = $this.attr("fysm");
			var id = $this.attr("acpId");
			
			if($this.attr("class")!="checkbox"){
				html += kexuanstr(fymc,danjia,fysm);
				$("#alldata").append(optionalStr(count,id));
				count ++;
			}
		});
		html += "<tr class='all-cost'>"+
				"<td style='text-align: left;' >费用个数：<span class='c1'>"+$("#kx_count").text()+"</span></td>"+
				"<td>单价合计：<span class='c1'>"+$("#kx_danjia").text()+"</span></td>"+
				"<td width='200'>报名人数：<span class='c1'>"+$("#peoplenum").text()+"</span></td>"+
				"<td style='text-align: right;'>费用合计：<span class='font-yuan-01'>"+$("#kx_price").text()+"</span></td>"+
		    "</tr>"+
		"</table>";
		
		//复制订单备注
		var bm_remark = $("#bm_remark").val();
		if(bm_remark!=""){
			$("#qr_remark").text(bm_remark);
			$("#qr_remark_page").css("display","block");
		}else{
			$("#qr_remark_page").css("display","none");
		}
		
		$("#bm_page").css("display","none");
		$("#bm_kx_page").css("display","none");
		$("#bm_remark_page").css("display","none");
		$("#bm_as_page").css("display","none");
		
		$("#qr_page").css("display","block");
		$("#qr_as_page").css("display","block");
		if(count>0){
			$("#qr_kx_content").html(html);
			$("#qr_kx_page").css("display","block");
		}
		
		$("#navigation a").each(function(index,element){
			var $this = $(this);
			if(index==1){
				$this.removeClass();
				$this.addClass("selected cs");
			}else{
				$this.removeClass();
				$this.addClass("cs");
			}
		});
		
		//返回顶部
		scrollTo(0,0);
	});
	
	//确认页面返回报名页面
	$("#baoming_back").click(function () {
		$("#bm_page").css("display","block");
		$("#bm_kx_page").css("display","block");
		$("#bm_remark_page").css("display","block");
		$("#bm_as_page").css("display","block");
		
		$("#qr_page").css("display","none");
		$("#qr_kx_page").css("display","none");
		$("#qr_as_page").css("display","none");
		
		$("#navigation a").each(function(index,element){
			var $this = $(this);
			if(index==0){
				$this.removeClass();
				$this.addClass("selected cs");
			}else{
				$this.removeClass();
				$this.addClass("cs");
			}
		});
		
		//返回顶部
		scrollTo(0,0);
	});
	
	//确认提交
	$("#queren_next").click(function () {
		var $this = $(this);
		
		layer.confirm("是否确认报名?",{icon:3},function(index){
			if($("#uEmail")==""){
				layer.close(index);
				
				layer.confirm("该活动含有保险费用，需要绑定邮箱才可以提交报名",{btn: ['去完善信息','稍后再去'],icon:0},
				function(){
					layer.open({
						type: 2,
						title:'绑定邮箱',
						skin: 'layui-layer-rim', //加上边框
						area: ['800px', '400px'], //宽高
						content: 'users/bindingEmail.html'
					});
				});
			}else{
				layer.close(index);
				
				var $form = $this.closest("form");
				
				layer.msg("提交中...",{icon:16,time:3*1000,shade:0.5});
				$.post("activeSignup/tijiaodingdan.html",$form.serialize(),function(data){
					var tjdata = data;
					if(tjdata.RESPONSE_STATE=="200"){
						var asu_id = tjdata.asu_id;
						if(asu_id!=null&&asu_id!=""){
							if(tjdata.price!=0){
								var paytype = $("#paytype").val();
								
								if(paytype!="2"||(paytype=="2"&&$("#zf_allreserveprice").html()!=0)){
									$.post("activeSignup/payOrder.html",{"asu_id":tjdata.asu_id},function(data){
										if(data.RESPONSE_STATE=='200'){
											layer.msg(tjdata.SUCCESS_INFO,{icon:16,time:1*1000,shade:0.5},function(){
												$("#page").css("display","none");
												$("#bm_page").css("display","none");
												$("#qr_page").css("display","none");
												$("#cost").css("display","none");
												$("#bm_kx_page").css("display","none");
												$("#qr_kx_page").css("display","none");
												$("#bm_remark_page").css("display","none");
												$("#qr_remark_page").css("display","none");
												$("#summary").css("display","none");
												
												if($("#zf_allprice").html()!=0){
													$("#success").css("display","block");
													$("#bank").css("display","block");
													$("#out_trade_no").val(data.out_trade_no);
													
													$("#navigation a").each(function(index,element){
														var $this = $(this);
														if(index==2){
															$this.removeClass();
															$this.addClass("selected cs");
														}else{
															$this.removeClass();
															$this.addClass("cs");
														}
													});
													
													$("#lasttime").text(tjdata.time);

												}else{
													
												}
												
												//返回顶部
												scrollTo(0,0);
												//倒计时
												timer(tjdata.time);
											});
										}else{
											layer.alert(data.ERROR_INFO, {
												title:"错误信息",
												icon:0
											});
										}
									});
								}else{
									layer.msg(tjdata.SUCCESS_INFO,{icon:16,time:1*1000,shade:0.5},function(){
										window.location.href="activeSignup/signUpFree.html?asu_id="+asu_id+"&activityId="+$("#asu_active_id").val();
									});
								}
							}else{
								layer.msg(tjdata.SUCCESS_INFO,{icon:16,time:1*1000,shade:0.5},function(){
									window.location.href="activeSignup/signUpFree.html?asu_id="+asu_id+"&activityId="+$("#asu_active_id").val();
								});
							}
						}
					}else if(tjdata.RESPONSE_STATE=="500"){
						layer.alert(data.ERROR_INFO, {
							title:"错误信息",
							icon:0
						});
					}
				})
			}
			
		});
	});
	
	//付款
	var paysuccess;
	$("label img#paybankimg").on("click",function(){
		var $this = $(this);
		$("label img#paybankimg").each(function(index,element){
			$(this).parent().find("input").removeAttr("name");
		});
		$this.parent().find("input").attr("name","bank");
		
		var $form = $this.closest("form");
		
		window.open("alipay/pay?"+$form.serialize());
		
		paysuccess = layer.open({
			type: 1,
            title:false,
            closeBtn: 0,
            shade: 0.2,
			area:['800px', '280px'], //宽高
		    content: $("#paySuccess")
		});
	});
	
	$("#closePay").on("click",function(){
		layer.close(paysuccess);
	});
	
	$("#showactive").on("click","#viewshow",function(){
		$("#activeinfo").slideDown();
		$("#showactive").html("<a id='viewhide' target='_blank' class='c1 cs' style='margin-left:30px;'>收起</a>");
	});
	
	$("#showactive").on("click","#viewhide",function(){
		$("#activeinfo").slideUp();
		$("#showactive").html("<a id='viewshow' target='_blank' class='c1 cs' style='margin-left:30px;'>查看详情</a>");
	});
	
});
//新增报名人name
var numcount = 0;

/*
 * 新增报名人HTML
 * count 数量
 * name 报名人名称
 * sex 性别(1/2)
 * phone 手机号
 */
function addstr(count,id,name,sex,phone,cardtype,cardnum){
	var isinsurance = $("#isinsurance").val();
	if(cardtype==""||cardtype==null||cardtype=="0"){
		cardtype = 1;
	}
	var radio = "";
	if(count==1){
		radio = " now";
	}
	var sexclass1 = " now";
	var sexclass2 = "";
	if(sex==2){
		sexclass1="";
		sexclass2=" now";
	}
	
	var html = "<div class='linkman-info' id='baoming' >"+
					"<div id='linkman"+numcount+"'></div>"+
					"<div class='add-num' id='count'>"+count+"</div>"+
					"<div class='set-linkman' id='set-linkman'>"+
					"	<span class='radio"+radio+"'><i class='icon-select'></i>设为报名联系人</span>"+
					"</div>"+
					"<div class='del-linkman' id='delete' title='删除此联系人'></div>"+
					"<dl>"+
					"	<dt>"+
					"		<label><i class='c1'> * </i>真实姓名：</label>"+
					"	</dt>"+
					"	<dd>"+
					"		<input maxlength='10' type='text' id='vusername' value='"+name+"'>"+	
					"	</dd>"+
					"</dl>"+
					"<div id='bm_show'>"+
					"<dl>"+
					"	<dt>"+
					"		<label><i class='c1'> * </i>性 别：</label>"+
					"	</dt>"+
					"	<dd class='sex' id='choicesex'>"+
					"		<span class='radio"+sexclass1+"'><i class='icon-select'></i>男</span>"+
					"		<span class='radio"+sexclass2+"'><i class='icon-select'></i>女</span>"+
					"	</dd>"+
					"</dl>";
			if(isinsurance==1){
				html +=	"<dl>"+
				        "    <dt>"+
				        "      <label><i class='c1'> * </i>证件类型：</label>"+
				        "    </dt>"+
				        "    <dd class='card' id='vcard'>" +
				        "		<select id='vcardtype' class='wid01' >"+
				        "          <option ";if(cardtype==1){html+="selected ";}html+=" value='1'>身份证</option>"+
//				        "          <option ";if(cardtype==2){html+="selected ";}html+=" value='2'>护照</option>"+
//				        "          <option ";if(cardtype==3){html+="selected ";}html+=" value='3'>出生证</option>"+
//				        "          <option ";if(cardtype==6){html+="selected ";}html+=" value='6'>军官证</option>"+
//				        "          <option ";if(cardtype==7){html+="selected ";}html+=" value='7'>台胞证</option>"+
				        "		</select>"+
				        "		号码：<input id='vcardnum' type='text' value='"+cardnum+"' class='wid03'/>"+
				        "     </dd>"+
				        "  </dl>";
			}
			html +=	"<dl>"+
					"	<dt>"+
					"		<label><i class='c1'> * </i>手机号码：</label>"+
					"	</dt>"+
					"	<dd>"+
					"		<input class='wid01' id='vphone' type='text' value='"+phone+"'/>"+
					"	</dd>"+
					"</dl><div id='emails'>";
//			if(radio!=""&&isinsurance==1){
//				html +=	"<dl id='email'>"+
//					"	<dt>"+
//					"		<label><i class='c1'> * </i>邮箱地址：</label>"+
//					"	</dt>"+
//					"	<dd>"+
//					"		<input class='wid01' id='vemail' type='text' value=''/>"+
//					"	</dd>"+
//					"</dl>";
//			}
			html += "</div></div><dl>"+
					"	<dt></dt>"+
					"	<dd>"+
					"		<span id='cycheckbox' class='checkbox now'><i class='icon-select'></i>"+
					"			保存为常用报名人</span>"+
					"	</dd>"+
					"</dl>"+
					"<input id='vid' type='hidden'  value='"+id+"'/>"+
				"</div>";
	numcount++;
	return html;
}

function addqrstr(count,id,name,sex,phone,linkman,cardType,cardnum){
	var isinsurance = $("#isinsurance").val();
	if(sex==2){
		sex = "女";
	}else{
		sex = "男";
	}
	
	if(linkman==count){
		linkman = "<div class='set-linkman c1'>已设为报名联系人</div>";
	}else{
		linkman = "<div class='set-linkman'></div>";
	}
	
	var html = "<div class='linkman-info' id='queren'>"+
					"<div class='add-num'>"+count+"</div>"+linkman+
					"<dl>"+
					"	<dt>"+
					"		<label><i class='c1'> * </i>真实姓名：</label>"+
					"	</dt>"+
					"	<dd>"+name+	
					"	</dd>"+
					"</dl>"+
					"<div id='qr_show'>"+
					"<dl>"+
					"	<dt>"+
					"		<label><i class='c1'> * </i>性 别：</label>"+
					"	</dt>"+
					"	<dd class='sex'>"+sex+
					"	</dd>"+
					"</dl>";
		if(isinsurance==1){
			html += "<dl>"+
			        "	<dt class='card'>"+
			        "		<label><i class='c1'> * </i>证件类型：</label>"+
			        "	</dt>"+
			        "	<dd>"+cardType+
					"	</dd>"+
					"</dl>"+
					"<dl>"+
					"	<dt>"+
			        "		<label><i class='c1'> * </i>证件号码：</label>"+
			        "	</dt>"+
			        "	<dd>"+cardnum+
					"	</dd>"+
			        "</dl>";
		}		
			html += "<dl>"+
					"	<dt>"+
					"		<label><i class='c1'> * </i>手机号码：</label>"+
					"	</dt>"+
					"	<dd>"+phone+
					"	</dd>"+
					"</dl>"+
					"</div>"+
					"<input class='id' type='hidden' value='"+id+"'/>"+
				"</div>";
	return html;
}

//获取当前报名人数
function countLink(){
	var num = -1;
	$("#addbaoming #count").each(function(index,element){
		num = index;
	});
	return num+2;
	
}

//限制报名人数，同一订单最多只能报名10个人
function numberfull(id){
	if(countLink()<10){
		$("#addlink").parent().parent().css("display","block");
		return true;
	}else if(countLink()==10){
		if(id=='delete'){
			$("#addlink").parent().parent().css("display","block");
		}else{
			$("#addlink").parent().parent().css("display","none");
		}
		return true;
	}else{
		$("#addlink").parent().parent().css("display","none");
		layer.alert('同一订单最多只能报名10个人', {
			title:"错误信息",
			icon:0
		});
		return false;
	}
}

//统计金额
function sumPrice(){
	var num = countLink()-1;
	//报名人数
	$("td span#peoplenum").text(num);
	//费用包括
	$("#fymx").text(($("#vprice").val()*num).toFixed(2)+"元");
	//可选费用
	var kexuan = 0;
	var count = 0;
	$("span#kxcheckbox").each(function(index,element){
		var $this = $(this);
		if($this.attr("class")=="checkbox now"){
			count++;
			kexuan += Number($this.attr("danjia"));
		}
	});
	$("td span#kx_count").text(count);
	$("td span#kx_danjia").text(kexuan.toFixed(2)+"元");
	$("td span#kx_price").text((kexuan*num).toFixed(2)+"元");
	
	//活动总额
	var allprice = "";
	var allreserveprice = "";
	var zfallprice = "";
	var zfallreserveprice = "";
	allprice += (($("#vprice").val()*1+kexuan)*num).toFixed(2)+"元";
	allreserveprice += (($("#vreserveprice").val()*1+kexuan)*num).toFixed(2)+"元";
	zfallprice += (($("#vprice").val()*1+kexuan)*num).toFixed(2);
	zfallreserveprice += (($("#vreserveprice").val()*1+kexuan)*num).toFixed(2);
	
	if(kexuan!=0){
		allprice += "("+($("#vprice").val()*1).toFixed(2)+"X"+num+"+"+kexuan.toFixed(2)+"元X"+num+")";
		allreserveprice += "("+($("#vreserveprice").val()*1).toFixed(2)+"X"+num+"+"+kexuan.toFixed(2)+"元X"+num+")";
	}
	$("#qr_allprice").html(allprice);
	$("#qr_allreserveprice").html(allreserveprice);
	
	$("#zf_allprice").html(zfallprice);
	$("#zf_allreserveprice").html(zfallreserveprice);
}

//判断真实姓名
function pdusername($this,id){
	var username = $this.val();
	if(username.trim()==""){
		if(id=="Njinji"){
			return true;
		}else{
			layer.tips("* 请输入真实姓名",$this,{tipsMore: true});
			return false;
		}
	}else{
		if(username.length<2){
			layer.tips("* 真实姓名最少两个汉字",$this,{tipsMore: true});
			return false;
		}else{
			return true;
		}
	}
}

//判断手机号
function pdphone($this,id){
	var phone = $this.val();
	if(phone.trim()==""){
		if(id=="Njinji"){
			return true;
		}else{
			layer.tips("* 请输入手机号码",$this,{tipsMore: true});
			return false;
		}
	}else{
		var re= /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1})|(17[0-9]{1}))+\d{8})$/;
		if(!re.test(phone)){
			layer.tips("* 手机号码格式不正确",$this,{tipsMore: true});
			return false;
		}else{
			if(id=="Yjinji"){
				var bool = true;
				$("div#baoming").each(function(index,element){
					if(phone==$(this).find("#vphone").val()){
						bool = false;
					}
				});
				if(!bool){
					layer.tips("* 紧急联系人手机号不能与本次参与活动的人员手机号相同",$this,{tipsMore: true});
					return false;
				}else{
					return true;
				}
			}
			return true;
		}
	}
}

//可选费用html
function kexuanstr(fymc,danjia,fysm){
	var html = "";
	html = "<tr>"+         
    		"<td>"+fymc+"</td>"+
    		"<td>"+danjia+"元/人</td>"+
    		"<td colspan='2'>"+fysm+"</td>"+
    	   "</tr>";
	return html;
}

//报名人隐藏域
function linkSignUpUser(count,id,name,sex,phone,cartType,cartId,vlinkman,issave){
	var html = "<input type='hidden' name='linkSignUpUser["+(count-1)+"].lsuu_id' value='"+id+"' />"+
			"<input type='hidden' name='linkSignUpUser["+(count-1)+"].lsuu_user_name' value='"+name+"' />"+
			"<input type='hidden' name='linkSignUpUser["+(count-1)+"].lsuu_user_sex' value='"+sex+"' />"+
			"<input type='hidden' name='linkSignUpUser["+(count-1)+"].lsuu_phone' value='"+phone+"' />";
	if($("#isinsurance").val()){
		html += "<input type='hidden' name='linkSignUpUser["+(count-1)+"].lsuu_cart_type' value='"+cartType+"' />"+
			"<input type='hidden' name='linkSignUpUser["+(count-1)+"].lsuu_cart_num' value='"+cartId+"' />";
	}
		html += "<input type='hidden' name='linkSignUpUser["+(count-1)+"].aa_signup_linkman' value='"+vlinkman+"' />"+
			"<input type='hidden' name='linkSignUpUser["+(count-1)+"].issave' value='"+issave+"' />";
	return html;
	
}

//可选费用隐藏域
function optionalStr(count,id){
	var html = "<input type='hidden' name='optional["+count+"].acp_id' value='"+id+"' />";
	return html;
}

//倒计时
function timer(showdate) {
	$("#countdown").djs({
		time:showdate,
		stop:"付款时间已过，交易失败！"
	})
}

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
			$this.parent().parent().parent().find("#choicesex span").each(function(index,element){
				if($(this).attr("class")=="radio now"){
					number = index;
				}
			})
			if(code[16]%2==number){
				tip = "身份证号与本人不符";
				pass =false;
			}
		}
	}
	
	if(!pass){
		layer.tips("* "+tip,$this,{tipsMore: true});
	}else{
		var bool = true;
		var count = $this.parent().parent().parent().parent().find("#count").text();
		$("#addbaoming #vcardnum").each(function(index,element){
			var $nowthis = $(this);
			if(count!=index+1){
				if($this.val()==$nowthis.val()){
					bool = false;
				}
			}
		});
		
		if(!bool){
			layer.tips("* 身份证号不能重复",$this,{tipsMore: true});
			pass = false;
		}
	}
	
	return pass;
}
