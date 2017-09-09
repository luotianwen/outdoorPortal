<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="/">
    
    <title>玩嘛-用户头像</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
		<link href="static/css/bootstrap.min.css" rel="stylesheet">
	    <link href="static/css/font-awesome.min.css" rel="stylesheet">
		<link href="static/css/plugins/cropper/cropper.min.css" rel="stylesheet">
	    <link href="static/css/animate.min.css" rel="stylesheet">
	    <link href="static/css/style.min.css" rel="stylesheet">

  </head>
  
  <body>
  <form id="Headform" method="post">
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-content">
                        <div class="row">
                            <div class="col-md-3">
                                <div class="image-crop" style="width: 250px; height: 250px;">
                                    <img src="<c:if test="${uHeadImg==null}">static/portrait/default.jpg</c:if><c:if test="${uHeadImg!=null}">${uHeadImg}</c:if>">
                                </div>
                            </div>
                            <div class="col-md-6" style="width:100%;">
                                <h4>图片预览：</h4>
                                <div class="img-preview img-preview-sm" style="width: 150px; height: 150px;">
                                </div>
                                <h4>上传成功预览：</h4>
                                <div>
                                	<img alt="" id="image" width="150px" height="150px" src="">
                                </div>
                                <div class="btn-group">
                                    <label title="上传图片" for="inputImage" class="btn btn-primary">
                                        <input type="file" accept="image/*" name="file" id="inputImage" class="hide"> 上传新图片
                                    </label>
                                    <input type="hidden" id="uHeadImg" name="uHeadImg" value="${uHeadImg}"/>
                                    <label title="保存图片" id="save" class="btn btn-primary">保存</label>
                                </div>
                                <div class="btn-group">
                                    <button class="btn btn-white" id="zoomIn" type="button">放大</button>
                                    <button class="btn btn-white" id="zoomOut" type="button">缩小</button>
                                    <button class="btn btn-white" style="width:60px;" id="rotateLeft" type="button">左旋转</button>
                                    <button class="btn btn-white" style="width:60px;" id="rotateRight" type="button">右旋转</button>
                                </div>						                                
                                	<button id="sub" type="button" style="background-color:#1ab394;border-color:#1ab394;color:#fff;font-size:12px;padding:5px 10px;">修改头像</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
  </form>
  </body>
  <!-- 全局js -->
    <script src="static/js/jquery-2.1.1.min.js"></script>
  <!-- Image cropper -->
    <script src="static/js/plugins/cropper/cropper.min.js"></script>
    <script>
    	$(document).ready(function() {
	        var d = $(".image-crop > img");
	        $(d).cropper({
	            aspectRatio: 1,
	            preview: ".img-preview",
	            done: function(k) {}
	        });
	        var e = $("#inputImage");
	        if (window.FileReader) {
	            e.change(function() {
	                var k = new FileReader(),
	                    m = this.files,
	                    l;
	                if (!m.length) {
	                    return
	                }
	                l = m[0];
	                if (/^image\/\w+$/.test(l.type)) {
	                    k.readAsDataURL(l);
	                    k.onload = function() {
	                        e.val("");
	                        d.cropper("reset", true).cropper("replace", this.result);
	                    };
	                } else {
	                    showMessage("请选择图片文件");
	                }
	            });
	        } else {
	            e.addClass("hide");
	        }
	        $("#zoomIn").click(function() {
	            d.cropper("zoom", 0.1);
	        });
	        $("#zoomOut").click(function() {
	            d.cropper("zoom", -0.1);
	        });
	        $("#rotateLeft").click(function() {
	            d.cropper("rotate", 45);
	        });
	        $("#rotateRight").click(function() {
	            d.cropper("rotate", -45);
	        });
	        $("#save").click(function() {
	            $.post("headUpload.html",{image:d.cropper("getDataURL")},function(data){
					data=eval("("+data+")");
				 	if(data.result = "true"){
				 		$("#image").prop("src",data.src);
				 		$("#uHeadImg").val(data.src);
				 	}else{
				 		alert("图片上传出错!请稍后重试!");
				 	}
				});
	        });
		});
		
		$("#sub").click(
			function(){
			$.post("users/updateHead.html",$("#Headform").serialize(),
				function(data) {
					if (data.RESPONSE_STATE == '200') {
						alert(data.SUCCESS_INFO);
						parent.window.location.reload();
					}else{
						alert(data.ERROR_INFO);
					}
				}
			)
			}
		);
    </script>
</html>
