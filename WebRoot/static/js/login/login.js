$(function(){
	
	//监控表单数据
	$.fn.watch = function(callback) {
	 return this.each(function() {
	     //缓存以前的值  
	     $.data(this, 'originVal', $(this).val());  
	     //event  
	     $(this).on('keyup paste', function() {
	         var originVal = $(this, 'originVal');  
	         var currentVal = $(this).val();  

	         if (originVal !== currentVal) {
	             $.data(this, 'originVal', $(this).val());
	             callback(currentVal);  
	         }  
	     });  
	 });  
	};
	$.fn.login=function(){
		this.init();
		this.maxLoginNum=3;
		this.reg={
			phoneReg : /^(((13[0-9]{1})|(15[0-9]{1})|(17[0-9]{1})|(18[0-9]{1}))+\d{8})$/,
			emailReg : /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/
		};
		this.jm={
			publicKey : RSAUtils.getKeyPair($("#exponent").val(), '', $("#modulus").val())
		}
	}
	$.fn.login.prototype={
		init:function(){
			this.bind();
			this.autoLogin();
		},
		autoLogin:function(){
			$("span#autoLogin").on("click",function(){
				var $i = $(this).find("i");
				if($i.hasClass("box-y")){
					$i.removeClass("box-y");
				}else{
					$i.addClass("box-y");
				}
			})
		},
		// 绑定事件
		bind:function(){
			this.yzm();
			this.submit();
			this.keyup();
		},
		keyup:function(){
			$(document).on("keyup",function(event){
				if (event.keyCode == 13) {
					$("button#submit").trigger("click");
				}
			})
		},
		// 绑定验证码
		yzm:function(){
			$("img#check_code").on("click",function(){
				$(this).prop("src","checkCode/generateCode.json?width=86&height=35&temp="+new Date().getTime());
			})
		},
		// 登录
		submit:function(){
			var _this = this;
			$("button#submit").on("click",function(){
				// 非空验证
				if(!_this.checkNull()){
					return;
				}
				
				// 注销登录事件
				$("button#submit").off("click");
				
				layer.msg("登录中",{icon:16,shade:0.3,time:10*1000});
				
				// 请求登录数据
				$.post("login/asyn.json",{
					n: $("input[name=n]").val(),
					p: RSAUtils.encryptedString(_this.jm.publicKey, $("input[name=p]").val()),
					y: $("input[name=y]").val()
				},function(data){
					
					layer.closeAll('dialog');
					if(data.RESPONSE_STATE == "200"){
						// 自动登录
						_this.autoSaveLoginInfo();
						
						_this.success(data);
					}else{
						_this.error(data);
					}
				})
			})
		},
		/**
		 * 自动登录
		 */
		autoSaveLoginInfo:function(){
			if($("i#autoLogin").hasClass("box-y")){
				$.cookie("wanrma.com.uName", $("input[name=n]").val(), {path: "/", expires: 365 });
				$.cookie("wanrma.com.uPassword", RSAUtils.encryptedString(this.jm.publicKey, $("input[name=p]").val()), {path: "/", expires: 365 });
			}
		},
		checkNull:function(){
			var name = $("input[name=n]").val().trim();
			if(name == "" || (!this.reg.phoneReg.test(name) && !this.reg.emailReg.test(name))){
				this.nError("<i></i>请输入手机号/邮箱")
				this.watchName();
				return false;
			}
			if($("input[name=p]").val().trim() == ""){
				this.pError("<i></i>密码不能为空")
				this.watchPassword();
				return false;
			}
			if($("div#yzm_div").css("display") == "block" && $("input[name=y]").val().trim() == ""){
				this.yError("<i></i>验证码不能为空");
				this.watchYzm();
				return false;
			}
			
			return true;
		},
		watchName:function(){
			var _this = this;
			$("input[name=n]").watch(function(val){
				if(val == "" || (!_this.reg.phoneReg.test(val) && !_this.reg.emailReg.test(val))){
					_this.nError("<i></i>请输入手机号或者邮箱")
				}else{
					$("div#n_error").removeClass("login-error").text("");
				}
			})
		},
		watchYzm:function(){
			var _this = this;
			$("input[name=y]").watch(function(val){
				if(val == ""){
					_this.yError("<i></i>验证码不能为空");
				}else if(val.length < 6){
					_this.yError("<i></i>请输入6位的验证码");
				}
				else{
					$("div#y_error").removeClass("login-error").text("");
				}
			})
		},
		watchPassword:function(){
			var _this = this;
			$("input[name=p]").watch(function(val){
				if(val == ""){
					_this.pError("<i></i>密码不能为空");
				}else{
					$("div#p_error").removeClass("login-error").text("");
				}
			})
		},
		clearError:function(){
			$("div#n_error").removeClass("login-error").text("");
			$("div#p_error").removeClass("login-error").text("");
			$("div#y_error").removeClass("login-error").text("");
		},
		nError:function(html){
			this.clearError();
			$("div#n_error").addClass("login-error").html(html);
			$("input[name=n]").focus();
		},
		pError:function(html){
			this.clearError();
			$("div#p_error").addClass("login-error").html(html);
			$("input[name=p]").focus();
		},
		yError:function(html){
			this.clearError();
			$("div#y_error").addClass("login-error").html(html);
			$("input[name=y]").focus();
		},
		// 登录失败
		error:function(data){
			// 注册登录事件
			this.submit();
			
			// 打开验证码
			this.openYzm(data.SESSION_LOGIN_ERROR_NUM);

			if(data.RESPONSE_STATE == "500"){
				this.nError("<i></i>用户名或密码不正确");
				
				if(data.SESSION_LOGIN_ERROR_NUM >= this.maxLoginNum){
					$("input[name=y]").val("");
					$("img#check_code").prop("src","checkCode/generateCode.json?width=86&height=35&temp="+new Date().getTime());
				}
			}else if(data.RESPONSE_STATE == "400"){
				this.yError("<i></i>验证码不正确");
			}
		},
		// 登录成功
		success:function(data){
			window.location.href=data.url;
		},
		// 更改欢迎信息
		openYzm:function(num){
			if(num >= 2){
				if(num == 2){
					$("img#check_code").prop("src","checkCode/generateCode.json?width=86&height=35&temp="+new Date().getTime());
				}
				$("div#yzm_div").show();
			}
		}
	}
	new $.fn.login();
})
