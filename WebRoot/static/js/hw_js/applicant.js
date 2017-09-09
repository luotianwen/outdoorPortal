$(function(){
	$("#addApplicants").on("click",function(){
		$("#oneApplicant").html(addhtml("","","","","","","",""));
	});

	$("#oneApplicant").on("click","#submit",function(){
		var lsuu_id = $("#lsuu_id").val();
		
		var bool = true;
		if(bool){
			bool = pdusername($("#lsuu_user_name"));
		}else{
			pdusername($("#lsuu_user_name"));
		}
		if(bool){
			bool = pdphone($("#lsuu_phone"));
		}else{
			pdphone($("#lsuu_phone"));
		}
		if(bool){
			bool = IdentityCodeValid($("#lsuu_cart_num"));
		}else{
			IdentityCodeValid($("#lsuu_cart_num"));
		}
		
		if(lsuu_id==""){
			if(bool){
				var $form = $("form");
				$.post("SignupUser/insertUser.html",$form.serialize(),function(data){
					if(data.RESPONSE_STATE=="200"){
						layer.confirm(data.SUCCESS_INFO, {
							icon :1,btn: ['确定'] //按钮
						}, function(index){
							location.reload();
						}, function(index){
							location.reload();
						});
					}else if(data.RESPONSE_STATE=="500"){
						layer.alert(data.ERROR_INFO, {
							title:"错误信息",
							icon:0
						});
					}
				
				});
			}
			
		}else{
			if(bool){
				var $form = $("form");
				$.post("SignupUser/updateUser.html",$form.serialize(),function(data){
					if(data.RESPONSE_STATE=="200"){
						layer.confirm(data.SUCCESS_INFO, {
							icon :1,btn: ['确定'] //按钮
						}, function(index){
							location.reload();
						}, function(index){
							location.reload();
						});
					}else if(data.RESPONSE_STATE=="500"){
						layer.alert(data.ERROR_INFO, {
							title:"错误信息",
							icon:0
						});
					}
				
				});
			}
		}
	});

	$("#oneApplicant").on("blur","#lsuu_user_name",function(){
		pdusername($(this));
	});
	$("#oneApplicant").on("blur","#lsuu_phone",function(){
		pdphone($(this));
	});
	$("#oneApplicant").on("blur","#lsuu_cart_num",function(){
		IdentityCodeValid($(this));
	});
})

function addhtml(id,name,sex,phone,cardtype,cardnum,equipment,experience){
	var checked = "checked='true'";
	var checked1 = "";
	
	if(sex=="2"){
		checked = "";
		checked1 = "checked='true'";
	}
	
	var html = "<form><input type='hidden' id='lsuu_id' name='lsuu_id' value='"+id+"' />"+
	"<dl class='clearfix'>"+
		"<dt>"+
		"	<em class='c-red'>*</em>真实姓名："+
		"</dt>"+
		"<dd>"+
		"	<input type='text' id='lsuu_user_name' name='lsuu_user_name' value='"+name+"'>"+
		"</dd>"+
	"</dl>"+
	"<dl class='clearfix'>"+
	"	<dt>"+
	"		<em class='c-red'>*</em>性别："+
	"	</dt>"+
	"	<dd>"+
	"		<label>"+
	"			<span class='cssradio'>"+
	"				<input type='radio' value='1' name='lsuu_user_sex' "+checked+">"+
	"				<span></span>"+
	"			</span>男"+
	"		</label>"+
	"		<label>"+
	"			<span class='cssradio'>"+
	"				<input type='radio' value='2' name='lsuu_user_sex' "+checked1+">"+
	"				<span></span>"+
	"			</span>女"+
	"		</label>"+
	"	</dd>"+
	"</dl>"+
	"<dl class='clearfix'>"+
	"	<dt>"+
	"		<em class='c-red'>*</em>手机号码："+
	"	</dt>"+
	"	<dd>"+
	"		<input type='text' id='lsuu_phone' name='lsuu_phone' value='"+phone+"'>"+
	"	</dd>"+
	"</dl>"+
	"<dl class='clearfix'>"+
	"	<dt>"+
	"		证件类型："+
	"	</dt>"+
	"	<dd>"+
	"		<select id='lsuu_cart_type' name='lsuu_cart_type' style='width: 240px;height: 40px;border: 1px solid #aaa;padding: 2px 4px;font-size: 14px;color: #333;'>"+
	"			<option value='1'>身份证</option>"+
	"		</select>"+
	"	</dd>"+
	"</dl>"+
	"<dl class='clearfix'>"+
	"	<dt>"+
	"		证件号码："+
	"	</dt>"+
	"	<dd>"+
	"		<input type='text' id='lsuu_cart_num' name='lsuu_cart_num' value='"+cardnum+"'>"+
	"	</dd>"+
	"</dl>"+
	"<dl class='clearfix'>"+
	"	<dt>装备情况：</dt>"+
	"	<dd>"+
	"		<textarea maxlength='100' name='lsuu_equipment'>"+equipment+"</textarea>"+
	"	</dd>"+
	"</dl>"+
	"<dl class='clearfix'>"+
	"	<dt>户外经验：</dt>"+
	"	<dd>"+
	"		<textarea maxlength='100' name='lsuu_experience'>"+experience+"</textarea>"+
	"	</dd>"+
	"</dl></form>"+
	"<div style='text-align:center;width:700px;padding:10px 0;'>"+
	"	<button id='submit' style='width: 150px;height: 40px; display: inline-block; line-height: 36px;background-color: #ffa134;text-align: center; font-size: 18px; color: #fff; border: none; cursor: pointer;'>保存</button>"+
	"</div>";
	return html;
}

function findApplicant(lsuu_id){
	$.post("SignupUser/selectUserId.html",{lsuu_id:lsuu_id},function(data){
		if(data.RESPONSE_STATE=='200'){
			$("#oneApplicant").html(addhtml(data.lsuu.lsuu_id,data.lsuu.lsuu_user_name,data.lsuu.lsuu_user_sex,data.lsuu.lsuu_phone,data.lsuu.lsuu_cart_type,data.lsuu.lsuu_cart_num,data.lsuu.lsuu_equipment,data.lsuu.lsuu_experience));
		}else if(data.RESPONSE_STATE=='500'){
			layer.alert(data.ERROR_INFO, {
				title:"错误信息",
				icon:0
			});
		}
	});
}

function deleteApplicant(lsuu_id){
	layer.confirm('您确定要删除该联系人，删除后将无法修复？', {
		btn: ['确定','取消'] //按钮
	}, function(){
		$.post("SignupUser/deleteUser.html",{lsuu_id:lsuu_id},function(data){
			if(data.RESPONSE_STATE=='200'){
				layer.confirm(data.SUCCESS_INFO, {
					icon :1,btn: ['确定'] //按钮
				}, function(index){
					location.reload();
				}, function(index){
					location.reload();
				});
			}else if(data.RESPONSE_STATE=='500'){
				layer.alert(data.ERROR_INFO, {
					title:"错误信息",
					icon:0
				});
			}
		});
	});
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
		}else{
			return true;
		}
	}
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
		}else{
			return true;
		}
	}
}

//验证身份证号是否有效
function IdentityCodeValid($this) { 
	var code = $this.val();
	var pass= true;
	
	if(code!=""){
		if(!validator.isValid(code)){
			tip = "身份证号格式不正确";
			pass = false;
		}else{
			var number = $("input[name='lsuu_user_sex']:checked").val();
			
			if(code[16]%2!=number){
				tip = "请确认是不是本人身份证";
				pass =false;
			}
		}
	}
	
	if(!pass){
		layer.tips("* "+tip,$this,{tipsMore: true});
	}
	return pass;
}