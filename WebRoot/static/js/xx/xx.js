$.fn.isEmpty = function(){
	var val = $(this).val().trim();
	if( val == null || val == '' || typeof(val) == 'undefined'){
		return true;
	}
	return false;
}

//上传参数
//监控表单数据
$.fn.watch = function(callback) {
 return this.each(function() {
     //缓存以前的值  
     $.data(this, 'originVal', $(this).val());  
     //event  
     $(this).on('keyup paste', function() {
         var originVal = $(this, 'originVal');  
         var currentVal = $(this).val();  

         if (originVal !== currentVal) {
             $.data(this, 'originVal', $(this).val());
             callback(currentVal);  
         }  
     });  
 });  
};
var xx = {
	dyh:"'",
	layerIndex:parent.layer.getFrameIndex(window.name),
	/*
	 * 关闭当前窗口
	 */
	closeLayer:function(){
		parent.layer.close(xx.layerIndex);
	},
	uploadObj:function(){// ajaxupload 指定对象
		return {
			width:'',
			height:'',
			maxSize:'',
			ext:'',
			nameMaxLength:'',
			isGeneraFileName:false
		};
	},
	getTime:function(){// 获取当前系统时间
		return new Date().getTime();
	},
	/* layer.tips提示
	 * @param
	 * 	id:定向的id;
	 * 	mess:提示信息;
	 * 	color:提示的背景色;
	 * 	time:提示存在页面的时间
	 */
	tips:function(id,mess,color,time){
		layer.tips(mess, '#' + id, {
		    tips: [2, typeof color === 'undefined' || color == '' || color == 'default'?'#5293C4':color],
		    time: typeof time === 'undefined' || time == '' || time == 'default'?1500:time
		});
		$('#'+id).focus();
	},
	/*
	 * 关闭当前窗口，刷新父页面
	 */
	closeLayerIndex:function(){
		parent.self.location.reload();
		parent.layer.close(xx.layerIndex);
	},
	/* layer.open 
	 * @param
	 * 	obj {
	 * 			type:'layer提供了5种层类型。可传入的值有：0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）。 若你采用layer.open({type: 1})方式调用，则type为必填项（信息框除外）',
	 * 			width:'iframe页面宽度,例:600px',
	 * 			height:'iframe页面高度',
	 * 			title:'弹窗标题',
	 * 			url:'跳转的controller',
	 * 			data:'传递的参数例子:para1=1&para2=2'
	 * 		}
	 */
	open:function(obj){
		var type = typeof obj.type === 'undefined'?'2':obj.type;
		layer.open({
			type : parseInt(type),
			area : [ obj.width, obj.height ],
			title : obj.title,
			shade : 0.3,
			fix : true, 
			shift :0,
			maxmin : false,
			closeBtn: 1,
			skin : 'layui-layer-lan',
			content : obj.url+(typeof obj.data === 'undefined'?'':'?temp='+xx.getTime()+'&'+obj.data)
		});
	},
	/* 重置表单
	 * @param
	 * 	btn_id:触发事件的buttonId
	 * 	form_id:重置后需要提交的表单ID
	 */
	reset:function(btn_id,form_id){
		$('#'+btn_id).click(function(){
			var form = typeof form_id === 'undefined'?'form':'#'+form_id;
	        layer.load(1, {
	            shade: 0.1
	        });
			$(':input', ''+form+'').not(':button, :submit, :reset, :hidden').val('').removeAttr('checked').removeAttr('selected');
			if($('.select2').length > 0){
				$('.select2').each(function(index){
					$(this).select2('val','');
					if(index == $('.select2').length-1){
						typeof form_id === 'undefined'?$('form').submit():$('#'+form_id).submit();
					}
				});
			}else{
				typeof form_id === 'undefined'?$('form').submit():$('#'+form_id).submit();
			}
		})
	},
	submit:function(btn_id,form_id){// 提交表单，参数(1：触发事件的button;参数(2:需要提交的form表单id
		$('#'+btn_id).click(function(){
	        layer.load(1, {
	            shade: 0.1
	        });
			typeof form_id === 'undefined'?$('form').submit():$('#'+form_id).submit();
		})
	},
	/* 在当前页面创建一个iframe页面层，内容为当前div的img图片
	 * @param
	 * 	id（divId）
	 */
	openImg:function(id){
		var img = new Image();
		img.src =$('#'+id).find('img').attr("src") ;
		var w = img.width;
		var h = img.height;
		layer.open({
		    type: 1,
		    title: false,
		    closeBtn: true,
		    area: [w, h],
		    shadeClose: true,
		    content: $('#'+id)
		});
	},
	/* 图片放大查看器(页面需要引入xx.css)
	 * @param
	 * 	obj {
	 * 			dom:'需要放大的图片元素集合',(非必填项，默认：img[id=logo])
	 * 			width:'放大后的宽度',(非必填项，默认：400)
	 * 			height:'放大后的高度'(非必填项，默认：200)
	 * 		}
	 */
	fdImg:function(obj){
		var dom;
		var width;
		var height;
		if(typeof obj === 'undefined'){
			dom = 'img[id=logo]';
			width = '400';
			height = '200';
		}else{
			if(typeof obj.dom === 'undefined' || obj.dom == null || obj.dom == ''){
				dom = 'img[id=logo]';
			}else{dom = obj.dom}
			if(typeof obj.width === 'undefined' || obj.width == null || obj.width == ''){
				width = '400';
			}else{width = obj.width}
			if(typeof obj.height === 'undefined' || obj.height == null || obj.height == ''){
				height = '200';
			}else{height = obj.height}
			
		}
	    $(dom).mouseover(function(e) {
	        this.myTitle = this.title;
	        this.title = "";
	        var imgTitle = this.myTitle ? "<br/>" + this.myTitle: "";
	        var tooltip = "<div id='tooltip'><img style='width: "+width+"px; height: "+height+"px;' src='" + this.src + "' alt='产品预览图'/>" + imgTitle + "<\/div>"; //创建 div 元素
	        $("body").append(tooltip); //把它追加到文档中
	        if (this.getBoundingClientRect().top < height) {
	            $("#tooltip").css({
	                "top": (e.pageY + 10) + "px",
	                "left": (e.pageX + 20) + "px"
	            }).show("fast"); //设置x坐标和y坐标，并且显示
	        } else {
	            $("#tooltip").css({
	                "top": (e.pageY - height) + "px",
	                "left": (e.pageX + 20) + "px"
	            }).show("fast"); //设置x坐标和y坐标，并且显示
	        }
	
	    }).mouseout(function() {
	        this.title = this.myTitle;
	        $("#tooltip").remove(); //移除
	    }).mousemove(function(e) {
	        if (this.getBoundingClientRect().top < height) {
	            $("#tooltip").css({
	                "top": (e.pageY + 10) + "px",
	                "left": (e.pageX + 20) + "px"
	            });
	        } else {
	            $("#tooltip").css({
	                "top": (e.pageY - height) + "px",
	                "left": (e.pageX + 20) + "px"
	            });
	        }
	    });
	},
	/* 复选框  指定该页面所有table th input类型为checkbox绑定click选中当前表格td第一个子元素为checkbox的表单
	 * @param 
	 * 	obj {
	 * 			target:tableId
	 * 		}
	 */
	checkbox:function(obj){
		var target = typeof obj === 'undefined' || typeof obj.target === 'undefined'?'table':'#'+obj.target;
	    $(target+'  th input:checkbox').on('click',
	    function() {
	        var that = this;
	        $(this).closest('table').find('tr > td:first-child input:checkbox').each(function() {
	        	if(!$(this).prop('disabled')){
		            this.checked = that.checked;
		            $(this).closest('tr').toggleClass('selected');
	        	}
	        });
	    });
	},
	/* 获取指定table 所有选中的obj集合
	 * @param
	 * 	table_id
	 */
	getChecked:function(table_id){
		return $('#'+table_id+' tbody input:checked');
	},
	load:function(){
		layer.load(1,{shade:0.1});
	},
	/*
	 * 关闭当前加载的所有进度icon
	 */
	closeLoad:function(){
		layer.closeAll('loading');
	},
	/* 价格正则   parame:需要验证的值；返回boolean
	 * @param
	 * 	value
	 */
	priceRegex:function(value){
		var priceRegex = /^\d{0,8}\.{0,1}(\d{1,2})?$/;
		return priceRegex.test(value);
	},
	/* 对象元素{target:'目标class',width:'下拉框宽度'}
	 * @param
	 * 	obj {
	 * 			target:'className',(必填项)
	 * 			width : 'selectWidth'(非必填项)
	 * 		}
	 */
	select2:function(obj){
		$('.'+obj.target+'').select2({
			allowClear:true//单选
		});
		if(!(typeof obj.width === 'undefined')){
			$('.'+obj.target+'').css('width',obj.width+'px');
		}
	},
	/*
	 * websocket推送服务
	 * @param
	 * 	obj{
	 * 			shade:'遮罩',(非必填项，默认：[0.5, '#393D49'])
	 * 			time:'存在时间'(非必填项)，默认2秒后自动关闭
	 * 		}
	 */
	push:function(obj){
     	layer.open({
		    type: 1,
		    title: false,
		    closeBtn: false,
		    shade: typeof obj === 'undefined' || typeof obj.shade === 'undefined'?false:obj.shade,// 默认不采取遮罩 遮罩格式[0.5, '#393D49']
		    area: ['340px', '215px'],
		    offset: 'rb', //右下角弹出
		    time: typeof obj === 'undefined' || typeof obj.time === 'undefined'?2000:obj.time, //默认2秒后自动关闭
		    shift: 2,
		    content: $('#'+obj.target)
		});
	},
	/**
	 * 判断该数组中是否包含该参数
	 * @param array
	 * @param element
	 * @returns {Boolean}
	 */
	contains:function(array,element) {
		var i = array.length; 
		while (i--) {
			if (array[i] == element) {
				return true; 
			}
		} 
		return false; 
	},
	/**
	 * 生成百分数
	 * @param num
	 * @param total
	 * @returns
	 */
	getPercent:function(num, total){
		num = parseFloat(num);
		total = parseFloat(total);
		if (isNaN(num) || isNaN(total)) {
			return "";
		}
		return total <= 0 ? "0"/*+"%"*/ : (Math.round(num / total * 10000) / 100.00 /*+ "%"*/);
	},
	/**
	 * 判断当前元素是否在客户端可视区域之内(只适用于底部元素不适合头部判断)
	 * @param id 需要判断的元素id
	 * @param isOk 当前元素在可视区域之内的回调函数
	 * @param isNo 当前元素不在可视区域之内的回调函数
	 * @returns {Boolean}
	 * 		true:在可视区域之内
	 * 		false:不在可视区域之内
	 */
	isSeeFooter:function(id,isOk,isNo){
		var iScroll=document.documentElement.scrollTop||document.body.scrollTop;////获取滚动条距离顶部的位置
		var iClient=document.documentElement.clientHeight;/////获取内容区高度
		var _obj_index = document.getElementById(id);
		// 当前可视区域的高度小于div距离顶部的高度,或者可视区域的高度减去div距离顶部的位置大于用户可见区域高度都为显示状态
		if(((iClient+iScroll) < xx.posTop(_obj_index)) || (((iClient+iScroll)-xx.posTop(_obj_index)) > iClient))
		{
			if(typeof isNo === 'function')
				isNo();
			else
				return false;
		}else{
			if(typeof isOk === 'function')
				isOk();
			else
				return true;
		}
	},
	/**
	 * 只适用于头部元素不适合底部判断
	 * @param check_id 需要对比判断的divID
	 * @param show_or_false_id 需要显示和隐藏的模块
	 * @param css 调节样式
	 */
	isSeeHeader:function(check_id,show_or_false_id,css)
	{
		var iScroll=document.documentElement.scrollTop||document.body.scrollTop;////获取滚动条距离顶部的位置
		if(iScroll > xx.posTop(document.getElementById(check_id)))////判断当前滚动条距离顶部的位置是否大于,div距离顶部的距离  
		{
			$('#'+show_or_false_id).addClass(css);
		}else{
			$('#'+show_or_false_id).removeClass(css);
		}
	},
	/**
	 * 绑定滚动时间
	 * @param fn 回调函数
	 */
	bindEvent:function(fn){
		if(window.attachEvent)
		{
			window.attachEvent('onscroll', fn)
		}
		else
		{
			window.addEventListener('scroll', fn, false)
		}
	},
	/**
	 * 当前位置距离顶部的位置
	 * @param obj
	 * @returns {Number}
	 */
	posTop:function(obj){
		var itop = 0;
		while(obj){
			itop += obj.offsetTop;
			obj = obj.offsetParent;
		}
		return itop;
	},
	/**
	 *	滚动到当前元素位置 
	 * @param obj jQuery对象
	 */
	scrollIntoView:function(obj){
		var offset = obj.offset();
		$('body,html').animate({scrollTop:offset.top},1000);
	},
	/**
	 * 判断客户端种类
	 * {true:'PC',false:'"Android", "iPhone","SymbianOS", "Windows Phone","iPad", "iPod"'}
	 * @returns {Boolean}
	 */
	checkPc:function(){
	    var userAgentInfo = navigator.userAgent;
	    var Agents = ["Android", "iPhone",
	                "SymbianOS", "Windows Phone",
	                "iPad", "iPod"];
	    var flag = true;
	    for (var v = 0; v < Agents.length; v++) {
	        if (userAgentInfo.indexOf(Agents[v]) > 0) {
	            flag = false;
	            break;
	        }
	    }
	    return flag;
	},
	/**
	 * 加载信息提示
	 */
	loadMsg:function(msg,time,shade,fn){
		layer.msg(msg, {icon: 16,time:time*1000,shade:typeof shade == "undefined"?0.3:shade},function(){
			if(typeof fn === "function"){
				fn();
			}
		});
	}
}
