$(function(){
	$.fn.replyGroup=function(){
		this.init();
	}
	
	$.fn.replyGroup.prototype={
		init:function(){
			this.bind();
			this.validateForm;
			this.page=1;
			this.reg={
				phoneReg : /^(((13[0-9]{1})|(15[0-9]{1})|(17[0-9]{1})|(18[0-9]{1}))+\d{8})$/,
				telephoneReg:/^0\d{2,3}-\d{5,9}$/,
				qqReg:/^[1-9][0-9]{4,14}$/
			}
		},
		bind:function(){
			// 省份、城市级联
			this.chooseProvince();
			// 验证
			this.validate();
			// 下一步
			this.next();
			// 上一步
			this.prev();
			// 重置
			this.reset();
			// 上传证件照
			this.fileUp();
			// 提交
			this.submit();
			// 法人/非法人切换
			this.legal();
		},
		/**
		 * 法人/非法人切换
		 */
		legal:function(){
			var _this = this;
			$("input[name=proposer]").on("click",function(){
				var val = $(this).val();
				if(val == 1){
					$("span#un-legal").hide();
					$("span#legal").show();
					$("span#legal").each(function(){
						var input = $(this).closest("dl").find("input");
						if(input.attr("placeholder")){
							input.attr("placeholder",input.attr("placeholder").replace("企业联系人","法人"));
						}
					});
				}else {
					$("span#legal").hide();
					$("span#un-legal").show();
					$("span#un-legal").each(function(){
						var input = $(this).closest("dl").find("input");
						if(input.attr("placeholder")){
							input.attr("placeholder",input.attr("placeholder").replace("法人","企业联系人"));
						}
					});
					
				}
			})
		},
		/**
		 * 验证身份证号是否有效
		 */
		idcardNumValidate:function(code){
			if(validator.isValid(code)){
				return true;
			}else{
				return false;
			}
		},
		fileUp:function(){
			var _this = this;
			
			// 转移上传事件
			$("a[upload-a]").on("click",function(){
				currentUploadFileId = $(this).attr('id');
				$("a#selectfiles").parent().find("input[multiple]").trigger("click");
			})
		},

		/**
		 * 上传文件
		 */
		fileUpload:function(bean){
			/*layer.msg("上传中，请稍等...",{
				icon:16,
				shade:0.6,
				time:10*1000
			})
			var para={
				ext:"jpg,png",//允许上传的后缀
				isGeneraFileName:true//生成新的文件名称
			};
			$.ajaxFileUpload({
				type: "POST", 
				url:"uploadFile.json",  
				secureuri:false,
				fileElementId:bean.id,
				data:para,
				dataType:'json',
				success:function(data){
					data = eval("("+data+")");
					if(data.RESPONSE_STATE == "200"){
						bean.success(data);
						layer.closeAll('dialog');
					}else if(data.RESPONSE_STATE == "500"){
						if(data.ERROR_INFO == "no_login"){
							layerLogin.open();
						}else{
							layer.alert(data.ERROR_INFO,{
								title:"错误信息",
								icon:0,
								shade:0.6
							});
						}
					}
				}
			});*/
		},
		submit:function(){
			var _this = this;
			$("a#submit").on("click",function(){
				if(!$("form#replyGroupForm").valid()){
					return;
				}
				layer.msg("提交中....",{icon:16,shade:0.5,time:10*1000})
				$.post("uCheck/apply.json",$("form#replyGroupForm").serialize(),function(data){
					if(data.RESPONSE_STATE == "200"){
						layer.msg("提交成功，审核结果会在1-2个工作日以短信/邮箱通知您，请耐心等待审核！",{icon:1,shade:0.5,time:1.5*1000},function(){
							window.location.href="index.html";
						})
					}else{
						layer.alert(data.ERROR_INFO);
					}
				});
			})
		},
		next:function(){
			var _this = this;
			$("a#next").on("click",function(){
				if(!$("form#replyGroupForm").valid()){
					return;
				}
				if(_this.page==1){
					$("div#page1").hide().find("input").addClass("ignore");
					$("div#page2").show().find("input").removeClass("ignore");
					$("a#prev").show();
					_this.page=2;
				}else if(_this.page==2){
					$("div#page2").hide().find("input").addClass("ignore");
					$("div#page3").show().find("input,textarea").removeClass("ignore");
					$(this).hide();
					$("a#submit").show();
					_this.page=3;
				}
			})
		},
		prev:function(){
			var _this = this;
			$("a#prev").on("click",function(){
				if(_this.page==2){
					$("div#page2").hide().find("input").addClass("ignore");
					$("div#page1").show().find("input").removeClass("ignore");
					$(this).hide();
					_this.page=1;
				}else if(_this.page==3){
					$("div#page3").hide().find("input,textarea").addClass("ignore");
					$("div#page2").show().find("input").removeClass("ignore");
					$("a#next").show();
					$("a#submit").hide();
					_this.page=2;
				}
			})
		},
		reset:function(){
			$("a#reset").on("click",function(){
				layer.confirm("是否确认重置表单？",{icon:3},function(index){
					$("form#replyGroupForm").find("input,textarea").not(".ignore").val("");
					$("select").each(function(){
						if($(this).closest("div#page1").css("display") != "none" && $(this).attr("name") != "cityId" && $(this).attr("name") != "countyId"){
							$(this).find("option:first").prop("selected",true);
						}
					})
					
					// 删除图片
					$("form#replyGroupForm").find("input[file]:hidden").not(".ignore").parent().find("div[img]").remove();
					
					// 更新城市级联菜单
					$("select[name=provinceId]").trigger("change");
					layer.close(index);
				})
			})
		},
		validate:function(){

			var icon = "<i></i>",
				_this = this;
			
			 // 手机号码验证
			 jQuery.validator.addMethod("mobileCheck", function(value, element) {
				 return value != "" && _this.reg.phoneReg.test(value);
			 }, icon+"请输入有效的手机号码"); 
			 
			 // 固定电话
			 jQuery.validator.addMethod("telephoneCheck", function(value, element) {
				 return value != "" && _this.reg.telephoneReg.test(value);
			 }, icon+"请输入有效的固定电话"); 
			 
			 // qq
			 jQuery.validator.addMethod("qqCheck", function(value, element) {
				 return value != "" && _this.reg.qqReg.test(value);
			 }, icon+"请输入有效的QQ号"); 
			 
			 // 身份证
			 jQuery.validator.addMethod("idCardCheck", function(value, element) {
				 return _this.idcardNumValidate(value);
			 }, icon+"请输入有效的身份证号码"); 
			 
			 
			 this.validateForm = $("form#replyGroupForm").validate({
			    rules: {
			    	enterpriseName:{
			    		required:true
			    	},
			    	address:"required",
			    	telephone:"telephoneCheck",
			    	email:{
			    		email:true,
			    		required:true
			    	},
			    	enterpriseHomepage:{
			    		required:true,
			    		url:true
			    	},
			    	licenseNum:"required",
			    	licensePictureUrl:"required",
			    	realName:"required",
			    	mobile:"mobileCheck",
			    	qq:"qqCheck",
			    	idcardNum:"idCardCheck",
			    	idcartFrontUrl:"required",
			    	idcartBackUrl:"required",
			    	idcartHandUrl:"required",
			    	clubName:"required",
			    	clubLogo:"required",
			    	clubIntro:"required",
			    	enterpriseIntro:"required"
			    },
	            messages:{
	            	enterpriseName:{
	            		required:icon+"请填写企业注册名称"
	            	},
	            	address:icon+"请填写企业所在地的详细地址",
			    	email:{
			    		email:icon+"请填写有效的邮箱地址",
			    		required:icon+"请填写有效的邮箱地址"
			    	},
			    	enterpriseHomepage:{
			    		required:icon+"请填写有效的企业网址",
			    		url:icon+"请填写有效的企业网址"
			    	},
			    	licenseNum:icon+"请填写营业执照号码",
			    	licensePictureUrl:icon+"请上传营业执照",
			    	realName:icon+"请填写姓名",
			    	idcartFrontUrl:icon+"请上传证件照（正面）",
			    	idcartBackUrl:icon+"请上传证件照（反面）",
			    	idcartHandUrl:icon+"请上传手持证件照",
			    	clubName:icon+"请填写俱乐部名称",
			    	clubLogo:icon+"请上传俱乐部LOGO",
			    	clubIntro:icon+"请填写俱乐部介绍",
			    	enterpriseIntro:icon+"请填写经营范围"
	            },
	            ignore:".ignore",
			    errorElement:"span",
			    errorClass:"error-tips",
			    // 插入错误信息
			    errorPlacement: function(error, element) {
			    	error.appendTo(element.parent());
			    }
			});
			 
			 currentValidateForm = this.validateForm;
		
		},
		/**
		 * 省份、城市级联
		 */
		chooseProvince:function(){
			$("select[name=provinceId]").on("change",function(){
				$.get("uCheck/selectCitys.json",{pcId:$(this).val()},function(data){
					var str = '',item;
					for( var i = 0,len = data.length;i<len;i++){
						item = data[i];
						str += "<option value='"+item.code+"' >"+item.name+"</option>";
					}
				    $("select[name=cityId]").html(str);
				    // 选择省份城市变化的时候要更新城市对应的区县
				    $("select[name=cityId]").trigger("change");
				});
			})
			$("select[name=cityId]").on("change",function(){
				$.get("uCheck/selectCounty.json",{ccId:$(this).val()},function(data){
					var str = '',item;
					for( var i = 0,len = data.length;i<len;i++){
						item = data[i];
						str += "<option value='"+item.code+"' >"+item.name+"</option>";
					}
				    $("select[name=countyId]").html(str);
				});
			})
			$("select[name=provinceId]").trigger("change");
		}
	}
	
	new $.fn.replyGroup();
})