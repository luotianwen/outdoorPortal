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

	         if (originVal != currentVal) {
	             $.data(this, 'originVal', $(this).val());
	             callback(this,currentVal);  
	         }  
	     });  
	 });  
	};
	// 监控标题长度，显示剩余字数
	$("textarea#activeTitle").watch(function(e,val){
		$("textarea#activeTitle").val(val.trim());
		var maxLength =30,length=maxLength-val.length;
		$(e).next().children().text(length);
		if(length==0){
			$(e).next().css("display","none");
		}else {
			$(e).next().css("display","block");
	    }
	});
	
	// 自定义皮肤
	laydate.skin('cheng');
	// 开始时间
	var startDate = {
	    elem: '#activityTime',
	    format: 'YYYY-MM-DD hh:mm',
	    min: laydate.now(), //设定最小日期为当前日期
	    max: '2099-06-16', //最大日期
	    istoday: false,
	    istime:true,
	    isclear: false,
	    festival: true, //是否显示节日
	    choose: function(datas){
	    	// 开始日选好后，重置结束日的最小日期
	    	endDate.min = datas; 
	    	
	    	// 将结束日的初始值设定为开始日
	    	endDate.start = datas;
	    	
	    	// 如果用户先选择了截止日,并且截止日比开始日大,要重置截止日
	    	var enrollEndTimeVal = $("#a_enroll_end_time").val();
	    	if(enrollEndTimeVal != "" && enrollEndTimeVal > datas){
		    	$("#a_enroll_end_time").val("");
	    	}
	    	
	    	// 将截止日的最大日期设置为开始日
	    	enrollEndTime.max=datas;
	    	
	    	// 将集合时间最大的可选日期改为开始时间
	    	gatherTime.max = datas;
	    }
	};
	// 结束时间
	var endDate = {
	    elem: '#endTime',
	    format: 'YYYY-MM-DD hh:mm',
	    min: laydate.now(),
	    max: '2099-06-16',
	    istoday: false,
	    isclear: false,
	    istime:true,
	    festival: true, //是否显示节日
	    choose: function(datas){
	    	// 结束日选好后，重置开始日的最大日期
	    	startDate.max = datas;
	    }
	};
	// 报名截止时间
	var enrollEndTime={
	    elem: '#a_enroll_end_time',
	    format: 'YYYY-MM-DD hh:mm',
	    min: laydate.now(),
	    max: '2099-06-16',
	    istoday: false,
	    isclear: false,
	    istime:true,
	    festival: true //是否显示节日
	}
	
	// 集合时间
	var gatherTime={
	    elem: '#gatherTime',
	    format: 'YYYY-MM-DD hh:mm',
	    min: laydate.now(),
	    max: '2099-06-16',
	    istoday: false,
	    isclear: false,
	    istime:true,
	    festival: true //是否显示节日
	}
	
	// 初始化日期组件
	laydate(startDate);
	laydate(endDate);
	laydate(enrollEndTime);
	laydate(gatherTime);

	// 活动多选
	$(".chosen-select").chosen({
		width : "369px",// style
		max_selected_options : 5 // 最多选5种活动类型
	});
	
	// 初始化儿童年龄
	var sc_id = $("select[name=sc_id]");
	if(sc_id.val() != 2 && sc_id.val() != 3){
		sc_id.parent().next().find("select").val(-1);
	}else{
		$("select[name=a_children_age]").prop("disabled",false).css({
			"background-color":"#FFFFFF"
		});
	}
	
	// 预览
	$("a#active_yl").on("click",function(){
		
		if($("input#active_finish_num:hidden").val() != "100"){
			// 跳转未完成的地方
			$("input[finish-num]").each(function(index){
				if($(this).val().trim() == "update"){
					return;
				}
				var saveElement = $(this).closest("form").find("a[handle-finish-num]").closest("div").css("display")!="none"? $(this).closest("form").find("a[handle-finish-num]"): $(this).closest("form").find("a.add");
				layer.tips("发布完成度不是100%哦！,请保存带有<span style='color:red;'>完成度</span>标识的元素！", saveElement, {
					  time:3*1000
				});
				$("body,html").animate({scrollTop:$(this).parent().offset().top});
				return false;
			})
			
			return;
		}
		window.open ('huodong/info/'+activityId+'.html')
	})
	
	
	$("a#submit_audit").on("click",function(){
		if($("input#active_finish_num:hidden").val() != "100"){
			// 跳转未完成的地方
			$("input[finish-num]").each(function(index){
				if($(this).val().trim() == "update"){
					return;
				}
				
				var saveElement = $(this).closest("form").find("a[handle-finish-num]").closest("div").css("display")!="none"? $(this).closest("form").find("a[handle-finish-num]"): $(this).closest("form").find("a.add");
				layer.tips("发布完成度不是100%哦！,请保存带有<span style='color:red;'>完成度</span>标识的元素！", saveElement, {
					  time:3*1000
				});
				$("body,html").animate({scrollTop:$(this).parent().offset().top});
				return false;
			})
			
			return;
		}
		
		layer.msg("提交审核中...",{icon:16,shade:0.5,time:20*1000})
		$.post("huodong/submit.json",{id:activityId},function(data){
			layer.closeAll("dialog");
			if(data.RESPONSE_STATE == "200"){
				swal({
					title : "提交成功。",
					text : "审核结果会在1-2个工作日以短信/邮箱通知您，请耐心等待审核！",
					type : "success",
					confirmButtonColor: "#ff8a01"
				},function(){
					beforeunload = false;
					window.location.href="huodong/myActive.html?type=3";
				});
			}else if(data.RESPONSE_STATE == "500"){
				swal({
					title : "提交失败!",
					text : "错误信息："+data.ERROR_INFO,
					type : "error",
					confirmButtonColor: "#ff8a01"
				});
			}
			
		})
	})
	
	// 草稿箱
	$("a#my_draft").on("click",function(){
		layer.open({
		    type: 2,
		    title: false,
		    closeBtn:false,
		    shadeClose:false,
		    shade:0.7,
		    icon:16,
			area:['700px','550px'],
		    content: "huodong/drafts.html"
		});
	})
})

/**
 * 判断开始日期和结束日期是否存在
 * @returns {Boolean}
 */
function checkStartEndDate(fn){
	if($("#activityTime").val().trim() == "" || $("#endTime").val().trim() == ""){
		return false;
	}
	fn();
}

/**
 * 上传文件
 */
fileUpload=function(bean){
	layer.msg("上传中，请稍等...",{
		icon:16,
		shade:0.6,
		time:10*1000
	})
	var para={
		ext:"jpg,png,gif",//允许上传的后缀
		isGeneraFileName:true,//生成新的文件名称
		width:bean.width,// 宽度
		height:bean.height,// 高度
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
	});
}

function checkLeave(){
	if(beforeunload){
		event.returnValue="您正在编辑的活动尚未保存草稿!";
	}
　　　　
}

/**
 * 选择活动适合人群
 * @param obj
 */
function chooseUserType(obj){
	var val = $(obj).val(),select=$(obj).parent().next().find("select");
	if(val == 2 || val == 3){
		if(!select.prop("disabled")){
			return;
		}
		select.val(1).prop("disabled",false).css({
			"background-color":"#FFFFFF"
		});
	}else{
		select.val(-1).prop("disabled",true).css({
			"background-color":"#E9E3E3"
		});
	}
}