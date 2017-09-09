var layerLogin,// 该登录对象
    alreadyBind=false;// 是否已经绑定事件
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
	}
	$.fn.login.prototype={
		init:function(){
			this.isHasLayer();
			this.fn=null;
			this.jm={
				publicKey : ""
			};
			this.getJm();
		},
		getJm:function(){
			var _this = this;
			$.post("login/mi.json",function(data){
				_this.jm.publicKey=RSAUtils.getKeyPair(data.exponent, '', data.modulus)
			});
		},
		// 判断是否引入了layerjs
		isHasLayer:function(){
			var $scripts = $("script"),
				has = false;
			for(var i=0,len=$scripts.length;i<len;i++){
				var $src = $scripts.eq(i).prop("src");
				if($src.indexOf("layer.min.js") != -1 || $src.indexOf("layer.js") != -1){
					has = true;
				}
			}
			if(!has){
				var str = '<script type="text/javascript" src="static/js/plugins/layer/layer.min.js" ></script>';
				$("body").append(str);
			}
			// 初始化拦截器
			this.interceptor();
		},
		// 登录拦截器
		interceptor:function(){
			var _this = this;
			//请求完成时执行函数
			$(document).ajaxComplete(function(event,xhr,settings){
				if(typeof(xhr.getResponseHeader) != "undefined"){
				    var timeout=xhr.getResponseHeader("timeout"); //通过XMLHttpRequest取得响应头，sessionstatus，  
				    if(timeout=="true"){
				    	_this.open();
				    }
				}
			});
		},
		// 弹窗异步登录
		open:function(){
			var _this = this;
			layer.msg("未登录或登录超时，请重新登录!",{shade:0.5,time:1*1000,icon:0},function(){
				var index =layer.open({
				    type: 1,
				    title: false,
				    closeBtn: false,
				    shadeClose: false,
				    shade:0.5,
					area:'400px',
				    content: $("div#layer_login_div")
				});
				_this.close(index);
				
				// 判断是否已经绑定过事件
				if(!alreadyBind){
					// 绑定登录页面事件
					_this.bind();
					alreadyBind = true;
				}
				
				// 如果验证码打开的状态则更新验证码
				if($("div#yzm_div").css("display") == "block"){
					$("img#check_code").prop("src","checkCode/generateCode.json?width=86&height=35&temp="+new Date().getTime());
				}
			})
		},
		bind:function(){
			this.yzm();
			this.submit();
		},
		// 绑定验证码
		yzm:function(){
			$("img#check_code").on("click",function(){
				$(this).prop("src","checkCode/generateCode.json?width=86&height=35&temp="+new Date().getTime());
			})
		},
		// 关闭弹窗
		close:function(index){
			var _this = this;
			$("a#popup_close").on("click",function(){
				layer.close(index);
				_this.clearError();
			})
		},
		// 登录
		submit:function(){
			var _this = this;
			$("button#layer_login_submit").on("click",function(){
				// 非空验证
				if(!_this.checkNull()){
					return;
				}
				
				// 注销登录事件
				$("button#layer_login_submit").off("click");
				// 请求登录数据
				$.post("login/asyn.json",{
					n:$("input[name=n]").val().trim(),
					p:RSAUtils.encryptedString(_this.jm.publicKey, $("input[name=p]").val()),
					y:$("input[name=y]").val().trim()
				},function(data){
					// 注册登录事件
					_this.submit();
					
					if(data.RESPONSE_STATE == "200"){
						_this.success(data);
						// 自动登录
						_this.autoSaveLoginInfo();
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
			var phoneReg = /^(((13[0-9]{1})|(15[0-9]{1})|(17[0-9]{1})|(18[0-9]{1}))+\d{8})$/;
			var emailReg = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
			var name = $("input[name=n]").val().trim();
			if(name == "" || (!phoneReg.test(name) && !emailReg.test(name))){
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
				var phoneReg = /^(((13[0-9]{1})|(15[0-9]{1})|(17[0-9]{1})|(18[0-9]{1}))+\d{8})$/;
				var emailReg = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
				if(val == "" || (!phoneReg.test(val) && !emailReg.test(val))){
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
			// 打开验证码
			this.openYzm(data.SESSION_LOGIN_ERROR_NUM);

			if(data.RESPONSE_STATE == "500"){
				this.nError("<i></i>用户名或密码不正确");
				
				if(data.SESSION_LOGIN_ERROR_NUM >= 2){
					$("input[name=y]").val("");
					$("img#check_code").prop("src","checkCode/generateCode.json?width=86&height=35&temp="+new Date().getTime());
				}
			}else if(data.RESPONSE_STATE == "400"){
				this.yError("<i></i>验证码不正确");
			}
		},
		// 登录成功
		success:function(data){
			// 关闭验证码
			$("div#yzm_div").hide();
			$("input[name=y]").val("");
			
			// 清空错误信息
			this.clearError();
			
			// 关闭弹窗
			$("a#popup_close").trigger("click");
			layer.msg(data.uName+",欢迎您!",{shade:0.6,time:0.8*1000,icon:6})
			
			// 更新头部登录
			this.updateHeader(data);
			
			// 调取第三方回调函数
			if(this.fn && typeof(this.fn) == "function"){
				this.fn();
			}
			
		},
		// 提供第三方回调入口
		setFn:function(fn){
			this.fn=fn;
		},
		// 更改欢迎信息
		updateHeader:function(data){
			var $info = $("div#layerLoginUpdateDiv");
			if($info.length > 0){
					
				var str='<div class="fr head_user">'
					+'<a href="users/center.html?id='+data.uId+'" title="'+data.uName+'" class="user_link">'
					+'<img src="'+data.uHeadImg+'" /><i></i>'
					+'</a>'
					+'<ul class="dropdown_group">'
					+'<li><a href="users/center.html?id='+data.uId+'" target="_blank"><i class="icon_my"></i>我的玩嘛</a>'
					+'</li>'
					+'<li><a href="travels/draft.html" target="_blank"><i class="icon_writeNote"></i>写游记</a>'
					+'</li>';
				
				if(data.uType == "1"||data.uType == "2"){
					str +='<li><a href="javascript:void(0)" id="releaseActivity" target="_blank"><i class="icon_releaseActivity"></i>发布活动</a>'
						+'</li>';
				}else if(data.uType == "50"){
					//str +='<li><a href="javascript:void(0)" id="releaseProject" target="_blank"><i class="icon_releaseProject"></i>发布项目</a>'
					//	+'</li>';
				}
				
				//str += '<li><a href="pointService/myfind.html" target="_blank"><i class="icon_findshop"></i>发现好店</a></li>';
				if(data.uType == "3"){
					str += '<li><a href="javascript:void(0)" id="apply_leader" target="_blank"><i class="icon_appLeader"></i>申请领队</a></li>';
					//str += '<li><a href="pointService/apply.html" target="_blank"><i class="icon_append"></i>申请商户</a></li>';
				}
					
					str += '<li><a href="huodong/myActive.html" target="_blank"><i class="icon_myActivity"></i>我的活动</a>'
					+'</li>'
					+'<li><a href="activeConsultation/consultation.html?id='+data.uId+'" target="_blank"><i class="icon_myQuestion"></i>我的问答</a>'
					+'</li>'
					+'<li><a href="follow/myActive.html" target="_blank"><i class="icon_myCollect"></i>我的收藏</a>'
					+'</li>'
					+'<li><a href="activeSignup/MySignUp.html" target="_blank"><i class="icon_myOrder"></i>我的订单</a>'
					+'</li>'
					+'<li><a href="users/userInfo.html" target="_blank"><i class="icon_settings"></i>设置</a>'
					+'</li>'
					+'<li><a href="javascript:void(0)" id="logout"><i class="icon_logout"></i>退出</a>'
					+'</li>'
					+'</ul>'
					+'</div>';

				
				$info.html(str);

			}
		},
		// 连续登录2次失败打开验证码
		openYzm:function(num){
			if(num >= 2){
				if(num == 2){
					$("img#check_code").prop("src","checkCode/generateCode.json?width=86&height=35&temp="+new Date().getTime());
				}
				$("div#yzm_div").show();
			}
		}
	}
	layerLogin = new $.fn.login();

})
