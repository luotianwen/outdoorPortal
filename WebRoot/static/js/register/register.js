$(function(){
	
	$.fn.register=function(){
		this.validateForm="";
		this.init();
	}
	
	$.fn.register.prototype={
		init:function(){
			this.bind();
		},
		bind:function(){
			this.getCode();
			this.checkCookie();
			this.submit();
			this.valid();
			this.clickReadAgreement();
		},
		clickReadAgreement:function(){
			$("i#consentAgreement").on("click",function(){
				if($(this).hasClass("box-y")){
					$(this).removeClass("box-y")
				}else{
					$(this).addClass("box-y");
				}
			})
		},
		checkCookie:function(){
			var _this = this;
			 if($.cookie("registerCaptcha")){
		            var count = $.cookie("registerCaptcha");
		            var btn = $("a#get_code");
		            btn.text(count+"秒后重新获取").attr("disabled",true).addClass("btn-disabled");
		            var resend = setInterval(function(){
		                count--;
		                if (count > 0){
		                    btn.text(count+"秒后重新获取").attr("disabled",true).addClass("btn-disabled");
		                    $.cookie("registerCaptcha", count, {path: "/", expires: (1/86400)*count});
		                }else {
		                    clearInterval(resend);
		                    btn.text("获取验证码").attr("disabled",false).removeClass("btn-disabled");
		                }
		            }, 1000);
		     }
		},
		getCode:function(){
			var _this = this;
	        /*点击改变按钮状态，发送短信验证的代码*/
			 $("a#get_code").on("click",function(){
		         if(!_this.validateForm.element($("input[name=uPhone]"))) 
		         {
					return;
		         }else{
					 // 防止重复提交，注销事件
		        	$("a#get_code").off("click");
		            var btn = $(this);
		            //发送验证码
	            	$.post("register/registerYzm.json",{
	            		phone:$("input[name=uPhone]").val(),verify:$("#verify").val()
	            	},function(data){
	            		if(data.RESPONSE_STATE == "200"){
	        	            btn.addClass("btn-disabled");
	        	            var count = 60;
    	                    btn.text(count+"秒后重新获取");
	        	            var resend = setInterval(function(){
	        	                count--;
	        	                if (count > 0){
	        	                    btn.text(count+"秒后重新获取");
	        	                    $.cookie("registerCaptcha", count, {path: "/", expires: (1/86400)*count});
	        	                }else {
	        	                    clearInterval(resend);
	        	                    // 绑定重新获取验证码事件
	        	                    _this.getCode();
	        	                    btn.text("获取验证码").removeClass("btn-disabled");
	        	                }
	        	            }, 1000);
	            		}else{
    	                    _this.getCode();
	            			layer.alert(data.ERROR_INFO,{icon:0});
	            		}
	            	});
		         }
	        });
			
		},
		valid:function(){
			var icon = "<i></i>";
			
			 // 密码验证，以字母开头，长度在8-15之间，密码至少包含一个大写字母、一个小写字母。
			 jQuery.validator.addMethod("passwordCheck", function(value, element) {
				 //var passwordReg = /^(?=^.{8,15}$)((?=.*\d)|(?=.*\W+))(?![.\n])(?=.*[A-Z])(?=.*[a-z]).*$/;
				 var passwordReg = /^[A-Za-z0-9]{6,22}$/;
				 return value != "" && passwordReg.test(value);
			 }, icon+"请输入6-22位的密码(仅支持字母和数字)"); 
			
			 // 密码验证，以字母开头，长度在8-15之间，密码至少包含一个大写字母、一个小写字母。
			 jQuery.validator.addMethod("phoneCheck", function(value, element) {
				 var phoneReg = /^(((13[0-9]{1})|(15[0-9]{1})|(17[0-9]{1})|(18[0-9]{1}))+\d{8})$/;
				 return value != "" && phoneReg.test(value);
			 }, icon+"请输入有效的手机号码"); 
			 
			 this.validateForm = $("form#register").validate({
			    rules: {
			      uPhone:{
			        phoneCheck:true,
			        number:true
			      },
			      uName: {
			        required: true,
			        maxlength:10,
			        remote:{
			        	url:"register/validateName.json",     //后台处理程序
			            type: "post",               //数据发送方式
			            dataType: "json",           //接受数据格式  
			        }
			      },
			      yzm: {
			        required: true,
			        minlength:4
			      },
			      uPassword: {
			        passwordCheck:true
			      },
			      reuPassword: {
			        required: true,
			        equalTo:'#uPassword'
			      }
			    },
	            messages:{
	            	uPhone:{
				        number: icon+"请输入有效的手机号码(只包含数字)"
				    },
			        uName:{
	                    required:icon+"请输入昵称",
	                    maxlength:icon+"输入长度最大为 10 位",
	                    remote:icon+"昵称已被占用"
	                },
	                yzm:{
	                    required:icon+"请输入验证码",
	                    minlength:icon+"请输入4位的验证码"
	                },
	                reuPassword:{
		                required:icon+"请再次输入密码",
		                equalTo:icon+"两次输入密码不一致"  
		            }                             
	            },
			    errorElement:"span",
			    errorClass:"error-tips",
			    // 插入错误信息
			    errorPlacement: function(error, element) {
			    	$(element).closest("form").find("div[for='"+element.attr("name")+"-error']").append(error).addClass("login-error");
			    },
			    // element出错时触发
			    highlight: function(element) {
			    	$(element).closest("form").find("div[for='"+$(element).attr("name")+"-error']").addClass("login-error");
			    },
			    // element通过验证时触发
			    unhighlight: function(element) {
			    	$(element).closest("form").find("div[for='" + $(element).attr("name") + "-error']").removeClass("login-error");
			    }
			});
		},
		submit:function(){
			var _this = this;
			$("button#submit").on("click",function(){
				
				if(!$("form#register").valid()){
					return;
				}

				
				if(!$("i#consentAgreement").hasClass("box-y")){
					layer.tips('请阅读并同意《玩嘛用户协议》', 'a#agreement',{
						tips:[3,'#78BA32']
					});
					return;
				}
				
				// 防止重复提交，注销事件
				$("button#submit").off("click");
				
				layer.msg("注册中，请稍等...",{icon:16,time:10*1000})
				$.post("register/save.json",$("form#register").serialize(),function(data){
					// 绑定事件
					_this.submit();
					
            		if(data.RESPONSE_STATE == "200"){
            			window.location.href="login.html";
            		}else{
            			layer.alert(data.ERROR_INFO,{icon:0});
            		}
            	})
			})
		}
	}
	new $.fn.register();
})