var _spot_markers=new Array();// 重置marker数组
var begin=0;//操作时间
var _clickid;

//添加marker标记
function addMarker(lng, lat,data) {
	var marker = new AMap.Marker({
		map : map,
		position : [ lng, lat ], //基点位置
		icon:'static/img/index/active/mark-green.png'
	});

	marker.setExtData(data);
	// 鼠标点击点标记时触发事件
    marker.on('click',markerClick);
	// 鼠标移近点标记时触发事件
    marker.on('mouseover',markerMouseover);
    // 鼠标移出点标记时触发事件
    marker.on('mouseout',markerMouseout);
    
	_spot_markers.push(marker);
}

function markerClick(e){
	var marker = e.target;
	
	$('html,body').animate({scrollTop:$("#"+marker.getExtData().id).offset().top},500)
}

function removeMarker(){
	for(var i=0,len=_spot_markers.length;i<len;i++){
		_spot_markers[i].setMap();
	}
}

var infoWindow = null;

//鼠标移到点标记时触发事件
function markerMouseover(e){
	var marker = e.target;
	// 加亮显示marker
	marker.setIcon('static/img/index/active/mark-yellow.png');
	
	//实例化信息窗体
	var data = marker.getExtData();
    var title = data.name+'<span style="font-size:11px;color:#F00;">价格:'+data.price+'</span>',
    content = [];
    content.push("<img src='"+data.url+"' style='width:100px;height:65px;'>地址："+data.address);
    infoWindow = new AMap.InfoWindow({
        isCustom: true,  //使用自定义窗体
        content: createInfoWindow(title, content.join("<br/>")),
        offset: new AMap.Pixel(16, -45)
    });
    
    infoWindow.open(map, marker.getPosition());
}
//鼠标移出点标记时触发事件
function markerMouseout(e){
	var marker = e.target;
	
	// 恢复marker
	marker.setIcon('static/img/index/active/mark-green.png');
	
	closeInfoWindow();
}
//操作时间验证
function getBounds(){
	var end= new Date().getSeconds();
	if(begin==0||end-begin>refreshTime){
		$("#page").attr("page",1);
		search(true);
	}
//	else{
//		layer.msg('操作太快.');
//	}
}

//构建自定义信息窗体
function createInfoWindow(title, content) {
    var info = document.createElement("div");
    info.className = "info";

    //可以通过下面的方式修改自定义窗体的宽高
    //info.style.width = "400px";
    // 定义顶部标题
    var top = document.createElement("div");
    var titleD = document.createElement("div");
    var closeX = document.createElement("img");
    top.className = "info-top";
    titleD.innerHTML = title;
    closeX.src = "http://webapi.amap.com/images/close2.gif";
    closeX.onclick = closeInfoWindow;

    top.appendChild(titleD);
    top.appendChild(closeX);
    info.appendChild(top);

    // 定义中部内容
    var middle = document.createElement("div");
    middle.className = "info-middle";
    middle.style.backgroundColor = 'white';
    middle.innerHTML = content;
    info.appendChild(middle);

    // 定义底部内容
    var bottom = document.createElement("div");
    bottom.className = "info-bottom";
    bottom.style.position = 'relative';
    bottom.style.top = '0px';
    bottom.style.margin = '0 auto';
    var sharp = document.createElement("img");
    sharp.src = "http://webapi.amap.com/images/sharp.png";
    bottom.appendChild(sharp);
    info.appendChild(bottom);
    return info;
}

//关闭信息窗体
function closeInfoWindow() {
    map.clearInfoWindow();
}



$(function(){
	$("#map-big").on("click",function(){
		var bounds = map.getBounds();//地物对象的经纬度矩形范围。
		var center = bounds.getCenter();//获取当前Bounds的中心点经纬度坐标。
		layer.open({
			type : 2,
			area : [ '98%', '98%' ],
			title : '景点地图',
			shade : 0.5,
			fix : true,
			shift : 0,
			maxmin : false,
			closeBtn : 1,
			content : 'spot/bigMap.html?center='+center
		});
		
		
	})
	
	//鼠标滑入景点
	$("#scenic-list").on("mouseover",".scenic-item",function(){
		for(var i=0,len=_spot_markers.length;i<len;i++){
			if($(this).attr("id")==_spot_markers[i].getExtData().id){
				// 加亮显示marker
				_spot_markers[i].setIcon('static/img/index/active/mark-yellow.png');
			}
		}
	});
	
	//鼠标滑出景点
	$("#scenic-list").on("mouseleave",".scenic-item",function(){
		for(var i=0,len=_spot_markers.length;i<len;i++){
			if($(this).attr("id")==_spot_markers[i].getExtData().id){
				// 恢复marker
				_spot_markers[i].setIcon('static/img/index/active/mark-green.png');
			}
		}
	});
	
})
