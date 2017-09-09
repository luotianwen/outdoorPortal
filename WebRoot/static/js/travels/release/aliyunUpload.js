
accessid = ''
accesskey = ''
host = ''
policyBase64 = ''
signature = ''
callbackbody = ''
filename = ''
key = ''
expire = 0
g_object_name = ''
g_object_name_type = ''
now = timestamp = Date.parse(new Date()) / 1000; 

function send_request()
{
    var xmlhttp = null;
    if (window.XMLHttpRequest)
    {
        xmlhttp=new XMLHttpRequest();
    }
    else if (window.ActiveXObject)
    {
        xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
    }
  
    if (xmlhttp!=null)
    {
        phpUrl = './oss/upload.html?type=img'
        xmlhttp.open( "GET", phpUrl, false );
        xmlhttp.send( null );
        return xmlhttp.responseText
    }
    else
    {
        alert("Your browser does not support XMLHTTP.");
    }
};

function check_object_radio() {
	// 默认生成新的文件名称
	g_object_name_type = "random_name";
	return;
    var tt = document.getElementsByName('myradio');
    for (var i = 0; i < tt.length ; i++ )
    {
        if(tt[i].checked)
        {
            g_object_name_type = tt[i].value;
            break;
        }
    }
}

function get_signature(){
    //可以判断当前expire是否超过了当前时间,如果超过了当前时间,就重新取一下.3s 做为缓冲
    now = timestamp = Date.parse(new Date()) / 1000; 
    if (expire < now + 3){
        body = send_request()
        var obj = eval ("(" + body + ")");
        host = obj['host']
        policyBase64 = obj['policy']
        accessid = obj['accessid']
        signature = obj['signature']
        expire = parseInt(obj['expire'])
        callbackbody = obj['callback'] 
        key = obj['dir']
        return true;
    }
    return false;
};

function random_string(len) {
　　len = len || 32;
　　var chars = 'ABCDEFGHJKMNPQRSTWXYZabcdefhijkmnprstwxyz2345678';   
　　var maxPos = chars.length;
　　var pwd = '';
　　for (i = 0; i < len; i++) {
    　　pwd += chars.charAt(Math.floor(Math.random() * maxPos));
    }
    return pwd;
}

function get_suffix(filename) {
    pos = filename.lastIndexOf('.')
    suffix = ''
    if (pos != -1) {
        suffix = filename.substring(pos)
    }
    return suffix;
}

function calculate_object_name(filename)
{
    if (g_object_name_type == 'local_name')
    {
        g_object_name += "${filename}"
    }
    else if (g_object_name_type == 'random_name')
    {
        suffix = get_suffix(filename)
        g_object_name = key + random_string(10) + suffix
    }
    return ''
}

function get_uploaded_object_name(filename)
{
    if (g_object_name_type == 'local_name')
    {
        tmp_name = g_object_name
        tmp_name = tmp_name.replace("${filename}", filename);
        return tmp_name
    }
    else if(g_object_name_type == 'random_name')
    {
        return g_object_name
    }
}

function set_upload_param(up, filename, ret)
{
    if (ret == false)
    {
        ret = get_signature()
    }
    g_object_name = key;
    if (filename != '') {
        suffix = get_suffix(filename)
        calculate_object_name(filename)
    }
    new_multipart_params = {
        'key' : g_object_name,
        'policy': policyBase64,
        'OSSAccessKeyId': accessid, 
        'success_action_status' : '200', //让服务端返回200,不然，默认会返回204
        'callback' : callbackbody,
        'signature': signature,
    };

    up.setOption({
        'url': host,
        'multipart_params': new_multipart_params
    });

    up.start();
}

var uploader = new plupload.Uploader({
	runtimes : 'html5,flash,silverlight,html4',
	browse_button : 'selectfiles', 
    //multi_selection: false,
	container: document.getElementById('container'),
	flash_swf_url : 'lib/plupload-2.1.8/js/Moxie.swf',
	silverlight_xap_url : 'lib/plupload-2.1.8/js/Moxie.xap',
    url : 'http://oss.aliyuncs.com',

	init: {
		PostInit: function() {
		},

		FilesAdded: function(up, files) {
			
			// 设置样式
			if($("div#paragraphAddImgDiv").css("display") == "none"){
				$("div#paragraphAddImgDiv>div.img-panel").css({
					"max-height":($(window).height()-250)+"px"
				})
				travels.e.$layerAddImgIndex=layer.open({
					type:1,
					title:false,
					closeBtn:false,
					area:[$(window).width()+"px",$(window).height()+"px"],
					content:$("div#paragraphAddImgDiv")
				})
			}
			/*plupload.each(files, function(file) {*/
				makeThumb(files,0, function (imgsrc,file) {
					/*var img=new Image();
			    	img.src = imgsrc; // Set source path 
			    	img.onload = function(){*/  
				    	var time = new Date().getTime();
				    	var image='<li id="'+file.id+'" wait-upload-img >'
				    		+'<img src="'+imgsrc+'" id="uploadImgBase64" upload-type="no" />'
				    		+'<div class="progress-bar"><div class="completed" style="width:0%"></div></div>'
				    		+'<div class="img-mask">'
				    		+'待上传'
				    		+'</div>'
				    		+'<span class="btn-upClose" close-type="img"></span>'
				    		+'</li>';
				    	$("a#addImg").closest("li").before(image);
			    		
						// 显示图片数量
				    	$("i#iImgNum").text($("ul#uploadImgUl").find("li").length-1);
			    	/*}  */
					
                })
			/*});*/
    		
    		// 更改上传按钮显示
	    	$("a#doneUpload").text("上传").css("background-color","#ff8a01");
		},

		BeforeUpload: function(up, file) {
            check_object_radio();
            set_upload_param(up, file.name, true);
        },

		UploadProgress: function(up, file) {
			var li = $("li#"+file.id),
				completed = li.find("div.completed"),
				width = file.percent;
			
			completed.animate({
				width:width+"%"
			})
		},

		FileUploaded: function(up, file, info) {
            if (info.status == 200)
            {

            	var json =  eval("("+info.response+")");
            	if(json.resultCode == 0){
            		 
                	var _this = travels,
                		$this = $("li#"+file.id).find("img#uploadImgBase64[upload-type=no]"),
    					$div = $("li#"+file.id+" div.img-mask"),
    					img = new Image(),
    					width;
    	            	img.src = host+get_uploaded_object_name(file.name);
    	            	img.onload = function(){
    	            		if(img.width<330){
    	            			return;
    	            		}
    	            		
    	            		width = img.width>_this.e.maxWidth?_this.e.maxWidth:img.width;
    	            		
    	            		var day = $this.closest("#day"),
    				    	html = icoHtml(),
    				    	contentId="";
    				    
    						if(day.html()!=null){
    					    	contentId = day.attr("content");
    					    	if(contentId==""||contentId==null){
    					    		contentId = addDay(day);
    					    	}
    					    	html = icoHtml("day");
    						}
    						
    						// 发送请求
    			   			$.post("imageUpload/base64Img.json",{
    			   				// base64位码
    			   				dataUrl:img.src,
    			   				sort:sort,
    			   				id:travelsId,
    			   				width:width,
    			   				content_id:contentId
    			   			},function(data){
    			   				
    			   				if(data.RESPONSE_STATE == "200"){
    			   					// 更新排序
    			   					sort = data.sort;
    			   					
    			   					// 上传成功
    			   					$div.html("").css({
    			   						"background":"none"
    			   					});
    			   					$this.attr("upload-type","yes");
    	
    		   	   			  		// 保存添加的图片数据
    		   	   			  		_this.e.imgDataArray.push({
    		   	   			  			width:width,// 设定最高宽度
    		   	   			  			//height:height,// 根据最高宽度自动计算高度
    		   	   			  			src:data.savePath+"@!yjxq",// 阿里云地址
    		   	   			  			tag:"",// 标签默认为空
    		   	   			  			tagId:"",// 标签ID
    		   	   			  			tagName:"",// 标签名称
    		   	   			  			scenicType:"",// 标签景点类型
    		   	   			  			seq:new Date().getTime(),// 默认排序
    		   	   			  			sort:data.sort,// 数据库排序
    		   	   			  			isNew:true,// 标识为新增加的图片，在有历史数据的时候优先显示
    		   	   			  			contentId:data.contentId// ID
    		   	   			  		})
    		   	   			  		
    			   				}else{
    			   					$this.after("<span style='color:red;'>error</span>");
    			   				}
    			   				
    			   				
    			   				var images = $("img#uploadImgBase64[upload-type=no]");
    			   				
    			   				if(images.length==0){
    			   					
    			   					window.clearInterval(_this.e.uploadInterval);
    			   					_this.e.uploadIntervalNum=1;
    	
    								// 更改上传按钮显示
    								_this.e.$doneUpload.text("完成").css("background-color","#ff8a01");
    	
    								// 绑定完成按钮事件，插入页面图片
    								_this.insertPageImg(html,_this.e.$layerAddImgIndex);
    								
    		   	   			  		// 展示历史标签（未关联/已关联的标签）和图片（未关联的图片）
    								_this.showTagAndUnLinkImg();
    			   				}
    			   			})
    						
                	}
            		
            		
            	}else{
            		alert("上传失败");
            	}
            	
                /*document.getElementById(file.id).getElementsByTagName('b')[0].innerHTML = '<br/>upload to oss success, object name:' + get_uploaded_object_name(file.name)
                +'<br/><img src="'+host+get_uploaded_object_name(file.name)+'">';*/
            }
            else
            {
                //document.getElementById(file.id).getElementsByTagName('b')[0].innerHTML = info.response;
            } 
		},

		Error: function(up, err) {
			document.getElementById('console').appendChild(document.createTextNode("\nError xml:" + err.response));
		}
	}
});
 
//生产缩略图
function makeThumb(files, index,callback) {//file为plupload事件监听函数参数中的file对象,callback为预览图片准备完成的回调函数
	var file = files[index];
	var maxSize = 10*1024*1024;
    if (!file || !/image\//.test(file.type)){ //确保文件是图片
    	layer.msg("请上传小于等于[10M]的<span style='color:red;'>图片</span>。",{icon:0});
    }
    else{
	    if(file.size > maxSize){
			layer.msg("请上传<span style='color:red;'>小于等于[10M]</span>的图片",{icon:0});
		}else if (file.type == 'image/gif') {//gif使用FileReader进行预览,因为mOxie.Image只支持jpg和png
	        var fr = new mOxie.FileReader();
	        fr.onload = function () {
	            var img = new Image();
	            img.src = fr.result;
	            
	            img.onload=function(){
		    		if(img.width<330){
		    			layer.msg("为了更好地展示效果，请上传宽度不小于330px的图片!",{icon:0,shade:0.6,time:1.5*1000});
		    			return;
		    		}
		            callback(fr.result,file);
		            fr.destroy();
		            fr = null;
		            
		            // 同步加载
		            files.splice(0,1);
		            if(files.length>0){
		            	makeThumb(files,0,callback);
		            }
	            }
	        }
	        fr.readAsDataURL(file.getSource());
	    }else {
	        var preloader = new mOxie.Image();
	        preloader.onload = function () {
	            //preloader.downsize(550, 400);//先压缩一下要预览的图片,宽300，高300
	            var imgsrc = preloader.type == 'image/jpeg' ? preloader.getAsDataURL('image/jpeg', 80) : preloader.getAsDataURL(); //得到图片src,实质为一个base64编码的数据
	            
	            var img = new Image();
	            img.src = imgsrc;
	            
	            img.onload=function(){
		            
		    		if(img.width<330){
		    			layer.msg("为了更好地展示效果，请上传宽度不小于330px的图片!",{icon:0,shade:0.6,time:1.5*1000});
		    			return;
		    		}
		            callback && callback(imgsrc,file); //callback传入的参数为预览图片的url
		            preloader.destroy();
		            preloader = null;

		            // 同步加载
		            files.splice(0,1);
		            if(files.length>0){
		            	makeThumb(files,0,callback);
		            }
		            
	            }
	        };
            preloader.load(file.getSource());
	    }
    }
}
uploader.init();