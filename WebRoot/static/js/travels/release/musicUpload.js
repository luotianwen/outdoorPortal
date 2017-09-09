
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

function send_request_music()
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
        phpUrl = './oss/upload.html?type=file'
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
        body = send_request_music()
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

var musicUploader = new plupload.Uploader({
	runtimes : 'html5,flash,silverlight,html4',
	browse_button : 'selectMusic', 
    multi_selection: false,
	container: document.getElementById('musicContainer'),
	flash_swf_url : 'lib/plupload-2.1.8/js/Moxie.swf',
	silverlight_xap_url : 'lib/plupload-2.1.8/js/Moxie.xap',
    url : 'http://oss.aliyuncs.com',
    filters : [{title : "音频文件(.mp3)",extensions : "mp3"}],
	init: {
		PostInit: function() {
            return false;
		},

		FilesAdded: function(up, files) {
			var bool = true;
			plupload.each(files, function(file) {
				if(file.name.length>50){
					bool = false;
					layer.alert("音乐名称过长", {
						title:"错误信息",
						icon:2
					});
					return;
				}
				document.getElementById('m-showname').innerHTML = "正在上传-"+file.name.replace(".mp3","");
				document.getElementById('music_name').innerHTML = file.name.replace(".mp3","");
				document.getElementById("m-showname").style.position = "absolute";
				document.getElementById("selectMusic").style.display = "none";
				document.getElementById("m_modify").style.display = "none";
				document.getElementById("m_delete").style.display = "none";
				document.getElementById("m_save").style.display = "none";
				document.getElementById("m_cancel").style.display = "none";
				document.getElementById("music_name").style.display = "none";
			});
			if(bool){
				set_upload_param(musicUploader, '', false);
			}
		},

		BeforeUpload: function(up, file) {
            check_object_radio();
            set_upload_param(up, file.name, true);
        },

		UploadProgress: function(up, file) {
			var d = document.getElementById("musicContainer");
			var prog = d.getElementsByTagName('div')[0];
			prog.style.width= 9.05*file.percent+'px';
			prog.setAttribute('aria-valuenow', file.percent);
		},

		FileUploaded: function(up, file, info) {
            if (info.status == 200)
            {
            	var json =  eval("("+info.response+")");
            	if(json.resultCode == 0){
            		$.ajax({
        	    		type : "post",
        	    		url : "travels/create.json",
        	    		data : {id:$.getUrlParam('id'),act:'update',"data.o":'music',"data.c":host+get_uploaded_object_name(file.name),"data.s":file.name.replace(".mp3","")},
        	    		async : false,
        	    		success : function(data){
        	            	if(data.RESPONSE_STATE=="200"){
        	            		var d = document.getElementById("musicContainer");
        	        			var prog = d.getElementsByTagName('div')[0].style.width = "0px";
        	        			document.getElementById("m-showname").style.position = "";
        	        			document.getElementById('m-showname').innerHTML = "已上传-"+file.name.replace(".mp3","");
        	        			document.getElementById("selectMusic").style.display = "";
        	                	document.getElementById('m_modify').style.display = "";
        	                	document.getElementById('m_delete').style.display = "";

        	                	var music = $("a[data-target='music']");
        	            		if(music.length>0){
        	            			music.attr("data-target",music.attr("data-target")+"_final");
        	            			music.attr("title","已完成");
        	            			music.attr("class","btns1");
        	            			music.parent().addClass("on");
        	            			music.html("已完成");
        	            			
        	            			var count = 0;
        	            			$("div#complete-percent i").each(function(index,element){
        	            				if(count<2){
        	            					if($(this).attr("class")!="completed"){
        	            						$(this).addClass("completed");
        	            						count++;
        	            					}
        	            				}
        	            			})
        	            			$("#percent").html((Number($("#percent").html().replace(/[^0-9]/ig,""))+20)+"%");
        	            		}
        	            	}else if(data.RESPONSE_STATE=="500"){
        	        			layer.alert(data.ERROR_INFO, {
        	        				title:"错误信息",
        	        				icon:2
        	        			});
        	        		}
        	            }
        	    	});
            	}else{
            		layer.alert(json.resultMsg, {
        				title:"错误信息",
        				icon:2
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
 
musicUploader.init();