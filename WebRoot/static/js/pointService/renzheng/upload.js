
var uploader_rz = new plupload.Uploader({
	runtimes : 'html5,flash,silverlight,html4',
	browse_button : 'selectfiles-rz', 
    //multi_selection: false,
	container: document.getElementById('container-rz'),
	flash_swf_url : 'lib/plupload-2.1.8/js/Moxie.swf',
	silverlight_xap_url : 'lib/plupload-2.1.8/js/Moxie.xap',
    url : 'http://oss.aliyuncs.com',
	init: {
		PostInit: function() {
			//document.getElementById('ossfile').innerHTML = '';
			document.getElementById('postfiles-rz').onclick = function() {
            set_upload_param(uploader_rz, '', false);
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

                    
                    var $li = $("li#"+_current_choose_photo_id),
                    	str='<img src="' + imgsrc + '" alt="" name="' + file.name + '" />'
		                    +'<div class="progress-bar">'
		                    +'<div class="completed" style="width:0%"></div>'
		                    +'</div>'
		                    +'<div class="edit-img-box">'
		                    +'<a class="edit-again" role="button" title="编辑"></a>'
		                    +'<a class="edit-del" role="button" title="删除"></a>'
    						+'</div>'
    						+'<input name="'+$li.attr("id")+'" type="hidden" id="img" />';
                    
                    $li.html(str);
                    
                    $("a#postfiles-rz").trigger("click");
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
uploader_rz.init();