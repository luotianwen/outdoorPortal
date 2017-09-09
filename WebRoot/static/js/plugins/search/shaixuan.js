$('.clearDd').show();

var okSelect = []; //已经选择好的
var oSelectList = document.getElementById('search-shaixuan');

var oClearList = $(".hasBeenSelected .clearList");
var oCustext1 = document.getElementById('custext1');
var oCustext2 = document.getElementById('custext2');
var aItemTxt = oSelectList.getElementsByTagName('a');
var isCusPrice = false;//是否自定义价格
var radioVal = '';
var priceRadioName = "priceRadio6";// 活动价格radio【name】
var pricesRadio = document.getElementsByName(priceRadioName);// 活动价格radio
var aRadio = $('div.screen-term input[type=radio]');
// 是否开启时时查询
var isAutoSearch = false;

oSelectList.onclick = function(e, a) {
	//是否已经扫描过自定义价格
	var isButtonPrice = false;

	// 判断点击的div中是否符合条件的元素
	var isClickInputOrA = false;
	
	// radio数组
	var radioVals = [];
    var ev = e || window.event;
    var tag = ev.target || ev.srcElement;
    if(!tag)return;
    var tagName = tag.nodeName.toUpperCase();
    
    var infor = '';
    if (tagName == 'INPUT') {
    	var _tag_type_toUpper = tag.getAttribute('type').toUpperCase();
        if (_tag_type_toUpper == 'CHECKBOX') { //如果点击 的是 input checkbox
        	isClickInputOrA = true;
            var val = next(tag);
            if (tag.checked) {
                var sType = prev(parents(tag, 'dd')).innerHTML;
                val && okSelect.push(trim(val.innerHTML) + '|' + sType)
            } else {
                var sType = prev(parents(tag, 'dd')).innerHTML;
                delStr(val.innerHTML + '|' + sType, okSelect)
            }
        } else if (_tag_type_toUpper == 'RADIO') { //如果点击的是 自定义价格按钮
        	isClickInputOrA = true;
        	// 选择单选后隐藏该选择条件，突出别的选择条件
        	$(tag).closest("dl").hide();
        	
        	if($(tag).attr('name') == priceRadioName){
        		isCusPrice = false;
        	}
        	tag.checked = true;
        } else if (_tag_type_toUpper == 'BUTTON') { //如果点击的是 自定义价格按钮
        	isClickInputOrA = true;
            radioVal = oCustext1.value + '-' + oCustext2.value + '元';
            radioVals.push({key: $(tag).closest("dl").children().prev().html(),val:radioVal,type:'zdy'})
            isCusPrice = true;
            isButtonPrice = true;// 防止重复
            
            for (var i = 0; i < pricesRadio.length; i++) {
            	pricesRadio[i].checked = false;
            }

        }
    }

    else if (tagName == 'A') { //如果点击 的是 A
        var oPrevInput = prev(tag);

        if (!oPrevInput) { //如果上一个节点没有则认为点击的是 '不限'
        	isClickInputOrA = true;
            var parent = parents(tag, 'dd');
            var aItem = $(parent).next().find("label");

            if (trim(prev(parent).innerHTML) == '活动价格') { //这里是直接根据 text来比较的.建议加个自定义属性作标识符
                for (var i = 0,len = pricesRadio.length; i < len; i++) {
                	pricesRadio[i].checked = false;
                }
                isCusPrice = false;
            } else {
                var sType = $(parent).prev().text();
                for (var i = 0, len = aItem.length; i < len; i++) {
                    delStr($(aItem[i]).find("a").text() + '|' + sType, okSelect);
                    aItem[i].children[0].checked = false;
                    $(aItem[i]).find("a").removeClass("selected");
                    $(tag).addClass("selected");
                }
            }

        } else {
            if (oPrevInput.getAttribute('type').toUpperCase() == 'RADIO') { //radio
            	var input = $(oPrevInput);
            	isClickInputOrA = true;
            	// 选择单选后隐藏该选择条件，突出别的选择条件
            	input.closest("dl").hide();
            	
            	if(input.attr('name') == priceRadioName){
            		isCusPrice = false;
            	}
            	input.prop("checked",true);
                
                
            }else if (oPrevInput.getAttribute('type').toUpperCase() == 'CHECKBOX') {
            	isClickInputOrA = true;
            	
            	var _dl = $(tag).closest("dl");
            	var _dt = _dl.find("dt");
            	var _dd = _dl.find("dd");
            	var _bx_a = _dd.eq(0).find("a");
            	
            	// 取消不限的选中样式
            	_bx_a.removeClass("selected");
                if (oPrevInput.checked) {
                    oPrevInput.checked = false;
                    var sType = _dt.html();
                    delStr(trim(tag.innerHTML) + '|' + trim(sType), okSelect);
                    $(oPrevInput).next().removeClass("selected");
                	
                	// 判断如果都没有选中，设置成不限
                    if(_dl.find("input:checked").length == 0){
                    	_bx_a.addClass("selected");
                    }
                } else {
                    oPrevInput.checked = true;
                    var sType = _dt.html();
                    okSelect.push(trim(tag.innerHTML) + '|' + trim(sType))
                    $(oPrevInput).next().addClass("selected");
                }
            }
        }
    };

    if(a == 'zdy'){// 删除自定义触发事件
    	isCusPrice = false;
    }
    
    // 	{isCusPrice:'缓存自定义数据',isButtonPrice:'如果已经添加了该对象，则不重复添加，这个方法主要是当点击其他按钮时候判断缓存'}
    if( isCusPrice && !isButtonPrice) {
      radioVal = oCustext1.value + '-' + oCustext2.value + '元';
      radioVals.push({key:'活动价格',val:radioVal,type:'zdy'})
    }

    // 	设置自定义数据和自定义缓存	
	if(radioVals.length > 0){
		for(var i=0,len=radioVals.length;i<len;i++){
			infor += '<div class=\"selectedInfor selectedShow\"><span>'+radioVals[i].key+'</span><label>' + radioVals[i].val + '</label><em type="'+radioVals[i].type+'"></em></div>';
		}
	}
    
    // 循环set Radio设置数据
    for (var i = 0; i < aRadio.length; i++) {
        if (aRadio[i].checked) {
        	var radio = aRadio[i];
            radioKey = $(radio).closest('dl').find("dt").text();
            radioVal = next(radio).innerHTML;
            infor += '<div class=\"selectedInfor selectedShow\"><span>'+radioKey+'</span><label>' + radioVal + '</label><em type=""></em></div>';
        }
    }
   //if (radioVal) infor += '<div class=\"selectedInfor selectedShow\"><span>活动价格</span><label>' + radioVal + '</label><em p="2"></em></div>';

	// 循环输入checkbox类型的数据
    var vals = [];
    for (var i = 0, size = okSelect.length; i < size; i++) {
        vals = okSelect[i].split('|');
        infor += '<div class=\"selectedInfor selectedShow\"><span>' + vals[1].trim() + '</span><label>' + vals[0].trim() + '</label><em></em></div>';
    }
    oClearList.html(infor);
    
    // 绑定新加元素的点击事件
    bindingEmClick();
    
    // 删除触发查询
    if(a == "1" || a == "zdy"){
    	isClickInputOrA = true;
    }
      
    // 删除元素触发查询事件
    if(isAutoSearch && isClickInputOrA){
    	search_active();
    }
};
// 设置默认显示的筛选条件
var inputs = $('#selectList input');
for(var i=0,len = inputs.length;i<len;i++){
	var input = inputs[i];
	if($(input).attr('default') && location.hash == ""){
		$(input).next().trigger("click");
	}
}
isAutoSearch = true;
// 清空全部点击事件
$('div.eliminateCriteria').on("click",function(){
    $("div#search-shaixuan input").each(function(){
    	var self = $(this);
		self.prop('checked',false);
    	if(self.prop("type")=="checkbox"){
    		self.next().removeClass("selected");
    		
      	    // 判断是否取消了当前类目所有的选项
    		cancelAll(self);
    	}
    })
    
    isCusPrice = false;
    okSelect.length = 0;
    $(oSelectList).trigger('click', 1);
    // 将非默认隐藏的radio条件更改为显示状态
    $("dl:hidden").each(function(){
    	var self = $(this);
    	// 当前状况为打开条件，无视是否默认为隐藏，都进行展示
    	if(_util_data._more_is_show == "1"){
            self.show();
    	}
    	// 当前状况为隐藏条件，并且不是默认条件进行展示
    	else if(self.attr("class").indexOf("more-none") == -1){
            self.show();
    	}
    })
})
function bindingEmClick(){
	// 逐个删除事件
	$('div.clearList em').on('click',function(){
	    var self = $(this);
	    var val = trim(self.prev().html()) + '|' + trim(self.prev().prev().html());
	    var n = -1;
	    var a = this.getAttribute('type') || 1;
	    //self.die('click'); //die() 方法移除所有通过 live() 方法向指定元素添加的一个或多个事件处理程序
	    for(var i = 0, len = aItemTxt.length; i < len; i++) {
	         var html = val.split('|')[0];
	         if(trim(aItemTxt[i].innerHTML) == html) {
	        	  var obj = prev(aItemTxt[i]);
	        	  obj.checked = false;
	        	  
	        	  // 移除checkbox下一级替代A标签的样式
	              if($(obj).prop("type").toUpperCase() == "CHECKBOX"){
	            	  $(obj).next().removeClass("selected");
	            	  
	            	  // 判断是否取消了当前类目所有的选项
	          		  cancelAll($(obj));
	            	  
	              }
	              
	              var dl = $(aItemTxt[i]).closest("dl");
	              
	              // 当前状况为打开条件，无视是否默认为隐藏，都进行展示
	          	  if(_util_data._more_is_show == "1"){
	          		dl.show();
	          	  }
	          	  
	          	  // 当前状况为隐藏条件，并且不是默认条件进行展示
	          	  else if(dl.attr("class").indexOf("more-none") == -1){
	                  self.show();
	          	  }
	              break;
	        }
	    };
	    delStr(val, okSelect);
	    // 删除重新生成已选条件
	    $(oSelectList).trigger('click', a);
	    
	})
}

function delStr(str, arr) { //删除数组给定相同的字符串
    var n = -1;
    for (var i = 0,len = arr.length; i < len; i++) {
        if (str == arr[i]) {
            n = i;
            break;
        }
    }
    n > -1 && arr.splice(n, 1);
};
function trim(str) {
    return str.replace(/^\s+|\s+$/g, '')
};
function text(e) {
    var t = '';
    e = e.childNodes || e;
    for (var j = 0; j < e.length; j++) {
        t += e[j].nodeType != 1 ? e[j].nodeValue: text(e[j].childNodes);
    }
    return trim(t);
}

function prev(elem) {
    do {
        elem = elem.previousSibling;
    } while ( elem && elem . nodeType != 1 );
    return elem;
};

function next(elem) {
    do {
        elem = elem.nextSibling;
    } while ( elem && elem . nodeType != 1 );
    return elem;
}

function parents(elem, parents) {  //查找当前祖先辈元素需要的节点  如 parents(oDiv, 'dd') 查找 oDiv 的祖先元素为dd 的
    if(!elem || !parents) return;
    var parents = parents.toUpperCase();
    do{
        elem = elem.parentNode;
    } while( elem.nodeName.toUpperCase() != parents );
    return elem;
};

/**
 * 判断是否取消了当前类目所有的选项
 */
function cancelAll(obj){
    var _dl = obj.closest("dl");
    if(_dl.find("input:checked").length == 0){
  	  _dl.find("dd:eq(0)").find("a").addClass("selected");
    }
}