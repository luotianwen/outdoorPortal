(function($) {
	$.ajaxImgUpload = function(d) {
		var e = false;
		d.ace_file_input({
			style: 'well',
			btn_choose: '选择文件',
			btn_change: null,
			no_icon: 'icon-cloud-upload',
			droppable: true,
			onchange: null,
			thumbnail: 'small',
			maxSize: 10240,
			allowExt: ["jpeg", "jpg", "png"],
			allowMime: ["image/jpg", "image/jpeg", "image/png"],
			before_change: function(a, b) {
				e = false;
				if (a instanceof Array || ( !! window.FileList && a instanceof FileList)) {
					for (var i = 0; i < a.length; i++) {
						var c = a[i];
						if (!(/^image\//i).test(c.type) && !c.size < 10240) {
							layer.msg('文件格式不正确', {
								icon: 2
							});
							return false
						}
					}
					e = true;
					return true
				}
			},
			before_remove: function() {
				$("input[name='" + inputId + "']").val(a.url)
				return true
			}
		}).on('change', function() {
			if (e) {
				var b = $(this);
				inputId = b.attr("id");
				$.ajaxFileUpload({
					type: "POST",
					url: "ajaxFileUpload/upload.php",
					secureuri: false,
					fileElementId: inputId,
					dataType: 'json',
					success: function(a) {
						a = eval("(" + a + ")");
						if (a.result == "ok") {
							$("input[name='" + inputId + "']").val(a.url)
						} else {
							layer.msg('图片传输失败', {
								icon: 8
							});
							b.parent().siblings().find("a").click()
						}
					}
				})
			}
		})
	}
})(jQuery);