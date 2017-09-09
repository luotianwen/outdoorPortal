<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>


<head>
	<base href="/">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    

    <title>头像裁剪Demo</title>

    <link href="static/css/bootstrap.min.css" rel="stylesheet">
    <link href="static/css/font-awesome.min.css" rel="stylesheet">
	<link href="static/css/plugins/cropper/cropper.min.css" rel="stylesheet">
    <link href="static/css/animate.min.css" rel="stylesheet">
    <link href="static/css/style.min.css" rel="stylesheet">
<style type="text/css">
.image-crop{
width: 400px; height: 400px;
}
.img-preview{
width: 200px; height: 200px;
}
</style>
</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
       
       
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-content">
                        <p>
                            		 jQuery图片裁剪插件Demo
                        </p>
                        <div class="row">
                            <div class="col-md-3">
                                <div class="image-crop">
                                    <img src="static/img/a3.jpg">
                                </div>
                            </div>
                            <div class="col-md-6">
                                <h4>图片预览：</h4>
                                <div class="img-preview img-preview-sm"></div>
                                <h4>上传成功预览：</h4>
                                <div>
                                	<img alt="" id="image" width="200px" height="200px" src="">
                                </div>
                                <h4>说明：</h4>
                                <p>
                                   	 你可以选择新图片上传，然后下载裁剪后的图片
                                </p>
                                <div class="btn-group">
                                    <label title="上传图片" for="inputImage" class="btn btn-primary">
                                        <input type="file" accept="image/*" name="file" id="inputImage" class="hide"> 上传新图片
                                    </label>
                                    <label title="下载图片" id="download" class="btn btn-primary">下载</label>
                                    <label title="保存图片" id="save" class="btn btn-primary">保存</label>
                                </div>
                                <h4>其他说明：</h4>
                                <p>
                                    你可以使用<code>$({image}).cropper(options)</code>来配置插件
                                </p>
                                <div class="btn-group">
                                    <button class="btn btn-white" id="zoomIn" type="button">放大</button>
                                    <button class="btn btn-white" id="zoomOut" type="button">缩小</button>
                                    <button class="btn btn-white" id="rotateLeft" type="button">左旋转</button>
                                    <button class="btn btn-white" id="rotateRight" type="button">右旋转</button>
                                    <button class="btn btn-warning" id="setDrag" type="button">裁剪</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>


    

    <!-- 全局js -->
    <script src="static/js/jquery-2.1.1.min.js"></script>
    <script src="static/js/bootstrap.min.js"></script>
 
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
        $("#download").click(function() {
            window.open(d.cropper("getDataURL"));
        });
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
        $("#setDrag").click(function() {
            d.cropper("setDragMode", "crop");
        });
        $("#save").click(function() {
            $.post("headUpload.html",{image:d.cropper("getDataURL")},function(data){
				var data=eval("("+data+")");
			 	if(data.result = "true"){
			 		$("#image").prop("src",data.src);
			 		alert(data.src);
			 	}
			});
        });
        
    });
    </script>


</body>


</html>