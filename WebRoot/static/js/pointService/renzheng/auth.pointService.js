var _current_choose_photo_id=null;
$(function(){
	$.fn.pointserviceauth=function(){
		this.$form = $("form#insertAuthForm");
		this.init();
	}
	
	$.fn.pointserviceauth.prototype={
		init:function(){
			this.validateform();
			this.last();
			this.threeinone();
			this.submit();
			this.monitorChoosePhoto();
		},
		/** 
		 * 监听选择照片元素
		 */
		monitorChoosePhoto:function(){
			$("li[addphoto]").on("click","a.edit-add,a.edit-again",function(e){
				$("div#container-rz").find("input:file").removeAttr("multiple");
				_current_choose_photo_id = $(this).closest("li").attr("id");
				$("div#container-rz").find("input:file").trigger("click");
			})
			
			// 删除图片
			$("li[addphoto]").on("click","a.edit-del",function(){
				var str = '<a class="edit-add" ><i></i>上传证件照</a>';
				$(this).closest("li").html(str);
			})
		},
		last:function(){
			$("#last").on("click",function(){
				$("#ruzhu").css("display","block");
				$("#renzheng").css("display","none");
				$('body,html').animate({scrollTop:0});
			})
		},
		threeinone:function(){
			$("input[name='threeinone']").on("click",function(){
				if($(this).val()=="0"){
					$("#threeinone").css("display","none");
				}else{
					$("#threeinone").css("display","block");
				}
			})
		},
		submit:function(){
			var _this = this;
			$("a#submit").on("click",function(){
				if($.fn.venues.prototype.check()){
					if(_this.$form.valid() && _this.checkImg()){
						var typeId = $.fn.venues.prototype.getType(),
						params = $("form#pointServiceForm").serialize()+"&ps_type="+typeId;
					
						layer.msg("提交中",{icon:16,shade:0.3,time:10*1000});
						$.post("pointService/ruzhu.json",params,function(data){
							if(data){
								if(data.RESPONSE_STATE == "200"){
									$("#ps_id").val(data.ps_id);
									$.post("pointService/insertAuth.json",_this.$form.serialize(),function(data){
										if(data){
											if(data.RESPONSE_STATE == "200"){
												layer.closeAll("dialog");
												
												$("a#submit").off("click");
												layer.msg("提交成功，审核结果会在3-5个工作日以短信/邮箱通知您，请耐心等待审核！",{icon:1,shade:0.5,time:1.5*1000},function(){
													window.location.href="index.html";
												})
												
											}else{
												layer.alert(data.ERROR_INFO,{icon:0})
											}
										}
									})
								}else{
									layer.alert(data.ERROR_INFO,{icon:0})
								}
							}
						})
					}
					
				}else{
					$("#ruzhu").css("display","block");
					$("#renzheng").css("display","none");
					$('body,html').animate({scrollTop:0});
				}
				
			})
		},
		checkImg:function(){
			var $idCard_p_src = $("input[name=idCard_p_src]:hidden"),
				$idCard_i_src2 = $("input[name=idCard_i_src2]:hidden"),
				$license_src = $("input[name=license_src]:hidden"),
				$organizationCode_src = $("input[name=organizationCode_src]:hidden"),
				$tax_registration_certificate = $("input[name=tax_registration_certificate]:hidden");

			if($idCard_p_src.length == 0 && $idCard_p_src.val() != ""){
				layer.alert("请上传身份证（正面）",{icon:6,title:"错误提醒"});
				return false;
			}
			if($idCard_i_src2.length == 0 && $idCard_i_src2.val() != ""){
				layer.alert("请上传身份证（反面）",{icon:6,title:"错误提醒"});
				return false;
			}
			if($license_src.length == 0 && $license_src.val() != ""){
				layer.alert("请上传营业执照扫描件",{icon:6,title:"错误提醒"});
				return false;
			}
			
			if($("input[name='threeinone']").val()=="0"){
				if($organizationCode_src.length == 0 && $organizationCode_src.val() != ""){
					layer.alert("请上组织机构代码证扫描件",{icon:6,title:"错误提醒"});
					return false;
				}
				if($tax_registration_certificate.length == 0 && $tax_registration_certificate.val() != ""){
					layer.alert("请上税务登记证扫描件",{icon:6,title:"错误提醒"});
					return false;
				}
			}
			
			return true;
		},
		validateform:function(){
			var _this = this,
			icon = "<i></i>";
			
			 // 密码验证，以字母开头，长度在8-15之间，密码至少包含一个大写字母、一个小写字母。
			 jQuery.validator.addMethod("phoneCheck", function(value, element) {
				 var phoneReg = /^(((13[0-9]{1})|(15[0-9]{1})|(17[0-9]{1})|(18[0-9]{1}))+\d{8})$/;
				 return value != "" && phoneReg.test(value);
			 }, icon+"请输入有效的手机号码"); 
			 
			 //组织机构代码
			 jQuery.validator.addMethod("organizationCode", function(value, element) {
				 var organizationCode = /^[a-zA-Z0-9]{8}-[a-zA-Z0-9]$/;
				 return value != "" && organizationCode.test(value);
			 }, icon+"请输入有效的组织机构代码"); 
			 
			 // 身份证
			 jQuery.validator.addMethod("idcardNumValidate", function(value, element) {
				 return _this.idcardNumValidate(value);
			 }, icon+"身份证号格式不正确");
			 
			_this.$form.validate({
		    	rules: {
		    		contactName:{
			    		required:true,
			    		maxlength:4
				    },
				    idCard:{
				    	idcardNumValidate:true,
			    		required:true,
			    		maxlength:18
				    },
				    mobile:{
				    	phoneCheck:true
				    },
			        email:{
			    	    required:true,
			    	    email:true,
			    	    maxlength:50
			        },
			        license_number:{
			    	    required:true,
			    	    number:true,
			    	    maxlength:15
			        },
			        organizationCode:{
			        	organizationCode:true,
			    		required:true
			        },
			        taxpayer_identity_number:{
			    	    required:true,
			    	    number:true,
			    	    maxlength:20
			        }
			    },
	            messages:{
	            	contactName:{
	            		required:icon+"请您输入真实姓名。",
	            		maxlength:icon+"输入长度最多是 4 的字符串（汉字算一个字符）。"
				    },
				    idCard:{
	            		required:icon+"请您输入身份证号。",
	            		maxlength:icon+"输入长度最多是 18 的字符串（汉字算一个字符）。"
				    },
			        email:{
				        required:icon+"请输入有效邮箱。",
			    	    email:icon+"请输入有效邮箱。",
			    	    maxlength:icon+"请输入长度不超过50位的邮箱地址。"
			        },
			        license_number:{
				        required:icon+"请输入营业执照注册号。",
				        number:icon+"请输入为纯数字的营业执照注册号",
			    	    maxlength:icon+"请输入长度不超过15位的营业执照注册号。"
			        },
			        organizationCode:{
			        	required:icon+"请您输入组织机构代码。",
			        },
			        taxpayer_identity_number:{
				        required:icon+"请输入纳税人识别号。",
				        number:icon+"请输入为纯数字的纳税人识别号",
			    	    maxlength:icon+"请输入长度不超过20位的纳税人识别号。"
			        }
	            },
			    errorElement:"span",
			    ignore:":hidden",
			    errorClass:"error-tips",
			    // 插入错误信息
			    errorPlacement: function(error, element) {
			    	$(element).after(error);
			    }
			});
		},
		/**
		 * 验证身份证号是否有效
		 */
		idcardNumValidate:function(code){
			return validator.isValid(code);
		}
	}
	
	new $.fn.pointserviceauth();
})