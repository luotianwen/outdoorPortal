$(function(){
	$("#change").on("click",function(){
		var _this = $(this);
		var type = $("input[name='n']").attr("data-type");
		if(type=="mobile"){
			_this.text("使用手机号找回");
			$("#get_code").text("获取邮箱验证码");
			$("input[name='n']").attr("placeholder","您的邮箱");
			$("input[name='n']").attr("data-type","email");
			$("input[name='y']").attr("placeholder","邮箱验证码");
		}else if(type=="email"){
			_this.text("使用邮箱账号找回");
			$("#get_code").text("获取短信验证码");
			$("input[name='n']").attr("placeholder","您的手机号");
			$("input[name='n']").attr("data-type","mobile");
			$("input[name='y']").attr("placeholder","短信验证码");
		}
	});
	
	$.fn.register=function(){
		this.init();
	}
	
	$.fn.register.prototype={
		init:function(){
			this.bind();
		},
		bind:function(){
			this.getCode();
			this.submit();
		},
		getCode:function(){
			var _this = this;
			var type = ""
			if($.cookie("forget")){
				var count = $.cookie("forget");
				var btn = $("a#get_code");
				btn.text(count+"秒后重新获取").attr("disabled",true).addClass("btn-disabled");
				var resend = setInterval(function(){
					count--;
					if (count > 0){
						btn.text(count+"秒后重新获取").attr("disabled",true).addClass("btn-disabled");
						$.cookie("forget", count, {path: "/", expires: (1/86400)*count});
					}else {
						clearInterval(resend);
						btn.text("获取验证码").attr("disabled",false).removeClass("btn-disabled");
					}
				}, 1000);
			}
			
			/*点击改变按钮状态，发送短信验证的代码*/
			$("a#get_code").on("click",function(){
				type = $("input[name='n']").attr("data-type");
				
				if(type=="mobile"){
					var myreg = /^(((13[0-9]{1})|(15[0-9]{1})|(17[0-9]{1})|(18[0-9]{1}))+\d{8})$/; 
					if(!myreg.test($("input[name=n]").val().trim())) 
					{ 
						_this.nError("<i></i>请输入有效的手机号码");
						$("input[name=n]").focus();
						return;
					}else{
						_this.nError("");
					}
				}else if(type=="email"){
					var myreg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;
					if(!myreg.test($("input[name=n]").val().trim())) 
					{ 
						_this.nError("<i></i>请输入有效的邮箱账号");
						$("input[name=n]").focus();
						return;
					}else{
						_this.nError("");
					}
				}
				
				// 防止重复提交，注销事件
				$("a#get_code").off("click");
				
				var btn = $(this);
				//发送验证码
				if(!btn.hasClass("btn-disabled")){
					$.post("forget/postYzm.json",{
						n:$("input[name=n]").val(),type:type,verify:$("#verify").val()
					},function(data){
						// 绑定事件
						_this.getCode();
	            		if(data.RESPONSE_STATE == "200"){
	            			if(type=="mobile"){
	            				var count = 60;
		            			var resend = setInterval(function(){
		        	                count--;
		        	                if (count > 0){
		        	                	btn.text(count+"秒后重新获取");
		        	                	$.cookie("forget", count, {path: "/", expires: (1/86400)*count});
		        	                }else {
		        	                    clearInterval(resend);
		        	                    btn.text("获取验证码").attr("disabled",false).removeClass("btn-disabled");
		        	                }
		            			}, 1000);
		        	            btn.attr("disabled",true).addClass("btn-disabled");
	            			}else if(type=="email"){
	            				layer.alert(data.SUCCESS_INFO, {
	    							title:"成功信息",
	    							icon:1
	    						});
	    	    	        	
	    	    	        	$("#get_code").attr("href",data.url);
	            				$("#get_code").text("前往邮箱");
	            				$("#get_code").attr("id","");
	            			}
	            		}else{
	            			layer.alert(data.ERROR_INFO,{icon:0});
	            		}
	            	});
	            };
	        });
			
		},
		submit:function(){
			var type = "";
			var _this = this;
			$("button#submit").on("click",function(){
				type = $("input[name='n']").attr("data-type");
				if(!_this.checkNull()){
					return;
				}
				// 防止重复提交，注销事件
				$("button#submit").off("click");
				
				$.post("forget/update.json",{
					n:$("input[name=n]").val().trim(),
					uPassword:$("input[name=p]").val().trim(),
					y:$("input[name=y]").val().trim(),
					type:type
				},function(data){
					// 绑定事件
					_this.submit();
					
            		if(data.RESPONSE_STATE == "200"){
            			window.location.href="login.html";
            		}else{
            			layer.alert(data.ERROR_INFO,{icon:0});
            		}
            	})
			})
		},
		checkNull:function(){
			var type = $("input[name='n']").attr("data-type");
			if(type == "mobile"){
				var myreg = /^(((13[0-9]{1})|(15[0-9]{1})|(17[0-9]{1})|(18[0-9]{1}))+\d{8})$/; 
				if(!myreg.test($("input[name=n]").val().trim())) 
				{ 
					this.nError("<i></i>请输入有效的手机号码");
					$("input[name=n]").focus();
					return false;
				}else{
					this.nError("");
				}
				if(this.nullstr($("input[name=y]").val())){
					this.yError("<i></i>短信验证码不能为空");
					$("input[name=y]").focus();
					return false;
				}else{
					this.yError("");
				}
			}else if(type == "email"){
				var myreg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;
				if(!myreg.test($("input[name=n]").val().trim())) 
				{ 
					this.nError("<i></i>请输入有效的邮箱账号");
					$("input[name=n]").focus();
					return false;
		        }else{
					this.nError("");
				}
				if(this.nullstr($("input[name=y]").val())){
					this.yError("<i></i>邮箱验证码不能为空");
					$("input[name=y]").focus();
					return false;
				}else{
					this.yError("");
				}
			}
			
			var pwdReg = /^[A-Za-z0-9]{6,22}$/;
			if(!pwdReg.test($("input[name=p]").val().trim())){
				this.pError("<i></i>请输入6-22位的密码(仅支持字母和数字)");
				$("input[name=p]").focus();
				return false;
			}else{
				this.pError("");
			}
			
			if(this.nullstr($("input[name=p]").val())){
				this.pError("<i></i>密码不能为空");
				$("input[name=p]").focus();
				return false;
			}else{
				this.pError("");
			}
			if(this.nullstr($("input[name=rp]").val())){
				this.rpError("<i></i>密码确认不能为空");
				$("input[name=rp]").focus();
				return false;
			}else{
				this.rpError("");
			}
			if($("input[name=rp]").val() != $("input[name=p]").val()){
				this.rpError("<i></i>两次输入的密码不一致");
				$("input[name=rp]").focus();
				return false;
			}else{
				this.rpError("");
			}
			this.clearError();
			return true;
		},
		nullstr:function(val){
			if(val.trim() == ""){
				return true;
			}
			return false;
		},
		clearError:function(){
			$("div#n_error").removeClass("login-error").text("");
			$("div#p_error").removeClass("login-error").text("");
			$("div#y_error").removeClass("login-error").text("");
			$("div#rp_error").removeClass("login-error").text("");
		},
		nError:function(html){
			this.clearError();
			if(html!=""){
				$("div#n_error").addClass("login-error").html(html);
				$("input[name=n]").focus();
			}
		},
		pError:function(html){
			this.clearError();
			if(html!=""){
				$("div#p_error").addClass("login-error").html(html);
				$("input[name=p]").focus();
			}
		},
		rpError:function(html){
			this.clearError();
			if(html!=""){
				$("div#rp_error").addClass("login-error").html(html);
				$("input[name=rp]").focus();
			}
		},
		yError:function(html){
			this.clearError();
			if(html!=""){
				$("div#y_error").addClass("login-error").html(html);
				$("input[name=y]").focus();
			}
		}
	}
	new $.fn.register();
	
})
