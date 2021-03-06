
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

/**
 * 上传文件名字是随机文件名, 后缀保留
 */
function check_object_radio() {
	
	g_object_name_type = "random_name";
    /*var tt = document.getElementsByName('myradio');
    for (var i = 0; i < tt.length ; i++ )
    {
        if(tt[i].checked)
        {
            g_object_name_type = tt[i].value;
            break;
        }
    }*/
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
	browse_button : 'selectfiles', 
    //multi_selection: false,
	container: document.getElementById('container'),
	flash_swf_url : 'lib/plupload-2.1.8/js/Moxie.swf',
	silverlight_xap_url : 'lib/plupload-2.1.8/js/Moxie.xap',
    url : 'http://oss.aliyuncs.com',
	init: {
		PostInit: function() {
			//document.getElementById('ossfile').innerHTML = '';
			document.getElementById('postfiles').onclick = function() {
            set_upload_param(uploader, '', false);
            return false;
			};
		},

		FilesAdded: function(up, files) {
			plupload.each(files, function(file) {
				makeThumb(file, function (imgsrc) {
                    /*$('#ossfile').append(
                        '<div style="float:left" class="pic_list" id="' + file.id + '">'
                        + ' (' + plupload.formatSize(file.size) +
                        ')<a href="###" class="pic_delete" data-val=' + file.id +
                        '>删除</a><br/>' +
                    '<img class="listview" width="90" height="60" src="' + imgsrc + '" name="' + file.name + '" /></div>');*/

					// 获取img下标
					var lastInputName = $("li#"+_current_choose_photo_id).parent().find("input#img:last").attr("name"),
						imgIndex=0;
					if(lastInputName){
					    var start = lastInputName.indexOf("imgs[")+5;
					    var end = lastInputName.indexOf("].ppi_url");
					    imgIndex = lastInputName.substring(start, end)*1+1;
					}
					
					// 生成缩略图
                    var str='<img src="' + imgsrc + '" alt="" name="' + file.name + '" />'
		                    +'<div class="progress-bar">'
		                    +'<div class="completed" style="width:0%"></div>'
		                    +'</div>'
		                    +'<div class="edit-img-box">'
		                    +'<a class="edit-again" role="button" title="编辑"></a>'
		                    +'<a class="edit-del" role="button" title="删除"></a>'
    						+'</div>'
    						+'<input name="projects['+_current_project_index+'].imgs['+imgIndex+'].ppi_url" type="hidden" id="img" />';
                    
                    $("li#"+_current_choose_photo_id).html(str);
                    
                    $("a#postfiles").trigger("click");
                    
                    var $ul = $("li#"+_current_choose_photo_id).parent();
                    
                    if($ul.children("li").length<5 && $ul.find("a.edit-add").length == 0){
                		var str = '<li id="'+new Date().getTime()+'"><a class="edit-add"><i></i>添加照片</a></li>';
                		$("li#"+_current_choose_photo_id).after(str);
            		}
                })

				/*document.getElementById('ossfile').innerHTML += '<div id="' + file.id + '">' + file.name + ' (' + plupload.formatSize(file.size) + ')<b></b>'
				+'<div class="progress"><div class="progress-bar" style="width: 0%"></div></div>'
				+'</div>';*/
			});
		},

		BeforeUpload: function(up, file) {
            check_object_radio();
            set_upload_param(up, file.name, true);
        },

		UploadProgress: function(up, file) {
			/*var d = document.getElementById(file.id);
			d.getElementsByTagName('b')[0].innerHTML = '<span>' + file.percent + "%</span>";
            var prog = d.getElementsByTagName('div')[0];
			var progBar = prog.getElementsByTagName('div')[0]
			progBar.style.width= 2*file.percent+'px';
			progBar.setAttribute('aria-valuenow', file.percent);*/
			
			var width = file.percent;
			$("li#"+_current_choose_photo_id+" div.completed").animate({
				width:width+"%"
			});
		},

		FileUploaded: function(up, file, info) {
            if (info.status == 200)
            {
            	var json =  eval("("+info.response+")");
            	if(json.resultCode == 0){
            		var $li = $("li#"+_current_choose_photo_id),
            			url = host+g_object_name;
            		
            			$li.find("input#img:hidden").val(url).end().find("div.progress-bar").remove();
            		
            		  /*document.getElementById(file.id).getElementsByTagName('b')[0].innerHTML = '<br/>upload to oss success, object name:' + get_uploaded_object_name(file.name)
                      +'<br/><img src="'+host+get_uploaded_object_name(file.name)+'">';*/
            	}else{
            		alert(json.resultMsg);
            	}
              
            }
            else
            {
            	layer.alert('上传异常，请稍后尝试或者联系客服，给您带来的不便敬请谅解!',{title:"错误信息",icon:0});
                //document.getElementById(file.id).getElementsByTagName('b')[0].innerHTML = info.response;
            } 
		},

		Error: function(up, err) {
			document.getElementById('console').appendChild(document.createTextNode("\nError xml:" + err.response));
		}
	}
});
 
//生产缩略图
function makeThumb(file, callback) {//file为plupload事件监听函数参数中的file对象,callback为预览图片准备完成的回调函数
    if (!file || !/image\//.test(file.type)) return; //确保文件是图片
    if (file.type == 'image/gif') {//gif使用FileReader进行预览,因为mOxie.Image只支持jpg和png
        var fr = new mOxie.FileReader();
        fr.onload = function () {
            callback(fr.result);
            fr.destroy();
            fr = null;
        }
        fr.readAsDataURL(file.getSource());
    } else {
        var preloader = new mOxie.Image();
        preloader.onload = function () {
            //preloader.downsize(550, 400);//先压缩一下要预览的图片,宽300，高300
            var imgsrc = preloader.type == 'image/jpeg' ? preloader.getAsDataURL('image/jpeg', 80) : preloader.getAsDataURL(); //得到图片src,实质为一个base64编码的数据
            callback && callback(imgsrc); //callback传入的参数为预览图片的url
            preloader.destroy();
            preloader = null;
        };
        preloader.load(file.getSource());
    }
}
uploader.init();