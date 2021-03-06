
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
	g_object_name_type = "random_name";
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
        //alert(tmp_name)
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
	browse_button : 'saveimg', 
    //multi_selection: false,
	container: document.getElementById('container'),
	flash_swf_url : 'lib/plupload-2.1.8/js/Moxie.swf',
	silverlight_xap_url : 'lib/plupload-2.1.8/js/Moxie.xap',
    url : 'http://oss.aliyuncs.com',
    filters : [{title : "Image files", extensions : "jpg,png"}],
	init: {
		PostInit: function() {
//			document.getElementById('postfiles').onclick = function() {
//            set_upload_param(uploader, '', false);
//            return false;
//			};
		},

		FilesAdded: function(up, files) {
			var imgcount = $("#thumbnail").find("li").length;
			
			if(files.length+imgcount<=4){
				/*plupload.each(files, function(file) {*/
					var count = files.length;
					makeThumb(files,0, function (imgsrc,file) {
						if(imgcount<=4){
							$('#thumbnail').append(
								"<li>"+
									"<img src='"+imgsrc+"' name='"+file.name+"' />"+
									"<div class='delete'>"+
									"	<a href='javascript:void(0)' id='delete' class='del-img' title='删除'></a>"+
									"</div>"+
									"<span class='progress' style='width:100%'><span id='"+file.id+"' class='progress-bar' style='width: 0%;height: 15px;float: none;'></span></span>"+
								"</li>"
							);
							$("#imgcount").html($("#thumbnail").find("li").length+"/4");
							count--;
							if(count==0){
								set_upload_param(uploader, '', false);
							}
						}else{
							layer.alert('最多上传4张图片', {
								title:"错误信息",
								icon:0
							});
						}
	                })
				/*});*/
			}else{
				layer.alert('最多上传4张图片', {
					title:"错误信息",
					icon:0
				});
			}
		},

		BeforeUpload: function(up, file) {
            check_object_radio();
            set_upload_param(up, file.name, true);
        },

		UploadProgress: function(up, file) {
			var d = document.getElementById(file.id);
			d.style.width= 1.5*file.percent+'px';
			d.setAttribute('aria-valuenow', file.percent);
		},

		FileUploaded: function(up, file, info) {
			console.log(info);
            if (info.status == 200)
            {
            	var json =  eval("("+info.response+")");
            	if(json.resultCode == 0){
            		$("#"+file.id).closest("li").find("img").attr("data-src",host+get_uploaded_object_name(file.name));
            	}else{
            		layer.alert(json.resultMsg, {
    					title:"错误信息",
    					icon:0
    				});
            	}
              
            }
            else
            {
                layer.alert(info.response, {
					title:"错误信息",
					icon:0
				});
            } 
		},

		Error: function(up, err) {
			layer.alert(document.createTextNode("\nError xml:" + err.response), {
				title:"错误信息",
				icon:0
			});
		}
	}
});
 
//生产缩略图
function makeThumb(files,index, callback) {//file为plupload事件监听函数参数中的file对象,callback为预览图片准备完成的回调函数
	var file = files[index];
    if (!file || !/image\//.test(file.type)) return; //确保文件是图片
    if (file.type == 'image/gif') {//gif使用FileReader进行预览,因为mOxie.Image只支持jpg和png
        var fr = new mOxie.FileReader();
        fr.onload = function () {
            callback(fr.result,file);
            fr.destroy();
            fr = null;
            
            // 同步加载
            files.splice(0,1);
            if(files.length>0){
            	makeThumb(files,0,callback);
            }
        }
        fr.readAsDataURL(file.getSource());
    } else {
        var preloader = new mOxie.Image();
        preloader.onload = function () {
            //preloader.downsize(550, 400);//先压缩一下要预览的图片,宽300，高300
            var imgsrc = preloader.type == 'image/jpeg' ? preloader.getAsDataURL('image/jpeg', 80) : preloader.getAsDataURL(); //得到图片src,实质为一个base64编码的数据
            callback && callback(imgsrc,file); //callback传入的参数为预览图片的url
            preloader.destroy();
            preloader = null;

            // 同步加载
            files.splice(0,1);
            if(files.length>0){
            	makeThumb(files,0,callback);
            }
        };
        preloader.load(file.getSource());
    }
}
uploader.init();