<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

<head>
	<base href="/">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>投诉领队(副页面)</title>

    <link href="static/css/bootstrap.min.css" rel="stylesheet">
    <link href="static/css/font-awesome.min.css" rel="stylesheet">
    <link href="static/css/animate.min.css" rel="stylesheet">
    <link href="static/css/style.min.css" rel="stylesheet">

    <link href="static/css/plugins/awesome-bootstrap-checkbox/awesome-bootstrap-checkbox.css" rel="stylesheet">
    
    <link rel="stylesheet" type="text/css" href="static/css/plugins/webuploader/webuploader.css">
    <link rel="stylesheet" type="text/css" href="static/css/demo/webuploader-demo.css">

</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content  animated fadeInRight">
        <div class="row">
            <div id="user-box" class="col-sm-12">
                <div class="ibox-content">
					<form class="form-horizontal" id="complaintLead">
                            <div class="form-group">
                                <label class="col-sm-3 control-label"><span style="color: rgb(236, 105, 25);">*</span>投诉内容：</label>
                                <div class="col-sm-8">
                                    <textarea id="conent" name="conent" class="form-control" style="resize:vertical;"></textarea>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label"><span style="color: rgb(236, 105, 25);">*</span>手机号码：</label>
                                <div class="col-sm-8">
                                    <input type="text" id="phone" name="phone" class="form-control" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">证据图片：(注:最多5张)</label>
                                <div class="col-sm-8">
                                    <div class="wrapper wrapper-content animated fadeInRight">
										<div class="row">
											 <div class="ibox-content">
							                      <div class="page-container">
							                          <div id="uploader" class="wu-example">
								                              <div class="queueList">
								                                  <div id="dndArea" class="placeholder">
								                                      <div id="filePicker"></div>
								                                  </div>
						                              	  	  </div>
								                              <div class="statusBar" style="display:none;">
								                                  <div class="progress">
								                                      <span class="text">0%</span>
								                                      <span class="percentage"></span>
								                                  </div>
								                                  <div class="info"></div>
								                                  <div class="btns">
								                                      <div id="filePicker2"></div>
								                                      <div class="uploadBtn">开始上传</div>
								                                  </div>
								                              </div>
							                          </div>
							                      </div>
							                  </div>
										</div>
	                                </div>
                            	</div>
                            <div class="form-group">
                                <div class="col-sm-offset-3 col-sm-8">
                                    <button class="btn btn-sm btn-primary m-t-n-xs" id="sub" type="button">保存</button>
                                </div>
                            </div>
                        </form>
                </div>
            </div>
        </div>
    </div>

    <!-- 全局js -->
    <script src="static/js/jquery-2.1.1.min.js"></script>
	<script src="static/js/plugins/layer/layer.min.js"></script>
	
	<!-- 全局js -->
    <script src="static/js/plugins/webuploader/webuploader.min.js"></script>
    <script src="static/js/index/complaintlead/complaintlead.js"></script>
    
    <!-- jQuery Validation plugin javascript-->
<script src="static/js/plugins/validate/jquery.validate.min.js"></script>
<script src="static/js/plugins/validate/messages_zh.min.js"></script>
    
    <script type="text/javascript">
	    // 添加全局站点信息
	    var BASE_URL = 'static/js/plugins/webuploader';
	    //图片URL
	    var IMAGE_URL="";
    </script>
	    
    <script>
            //设置本页layer皮肤
            layer.config({
            	skin:'layui-layer-molv',
            });
            
            jQuery.validator.addMethod("mobileCheck", function(value,element) {
	        var length = value.length;
	        var mobile = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1})|(17[0-9]{1}))+\d{8})$/;   
	        return this.optional(element) || (length==11 && mobile.test(value));   
			}, "请填写正确的手机号码");
            
            $("#complaintLead").validate({
			rules: {
				conent:{
					required:true,
					maxlength:300,
				},
				phone:{
					required:true,
					mobileCheck:true,
				},
			},
			messages: {
				conent:{
					required:"请输入投诉内容",
					rangelength:"投诉内容长度不能超过300",
				},
				phone:{
					required:"请输入手机号",
					mobileCheck:"请输入正确的手机号码"
				}
			}
		});
		
		$("#sub").click(
			function(){
				if($("#complaintLead").valid()){
					if(IMAGE_URL==""){
						if(confirm("您还没有上传图片!确认继续么?")){
							$.post("complaintLead/complaint.html",{"conent":$("#conent").val(),"phone":$("#phone").val(),"orderId":window.location.search.substr(4),"images":IMAGE_URL},function(data){
									data = eval('('+data+')');
									if(data.RESPONSE_STATE=="200"){
										alert(data.SUCCESS_INFO);
										parent.location.reload()
									}else{
										alert(data.ERROR_INFO);
									}
								  }
							);
						}
					}else{
							$.post("complaintLead/complaint.html",{"conent":$("#conent").val(),"phone":$("#phone").val(),"orderId":window.location.search.substr(4),"images":IMAGE_URL},function(data){
									data = eval('('+data+')');
									if(data.RESPONSE_STATE=="200"){
										alert(data.SUCCESS_INFO);
										parent.location.reload()
									}else{
										alert(data.ERROR_INFO);
									}
								  }
							);
					}
				}
			}
		);

        
    </script>


</body>


</html>