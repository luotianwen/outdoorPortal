<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="/">
    
    <title>活动评价页面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" href="static/css/bootstrap.min.css" >
	<script type="text/javascript" src="static/js/jquery-2.1.1.min.js" ></script>
    <script src="static/js/plugins/layer/layer.js"></script>
	
	<link rel="stylesheet" href="static/css/plugins/star/star-rating.min.css" >
    <script type="text/javascript" src="static/js/plugins/star/star-rating.js"></script>

  </head>
  
  <body>
    <!-- 评论 -->
  	<div style="font-size: 15px;padding: 20px;" id="comments">
  		<input type="hidden" id="activeId" value="<%=request.getParameter("activeId")%>">
  		<input type="hidden" id="suId" value="<%=request.getParameter("suId")%>">
  		<!-- 活动打分 -->
  		<div style="width: 100%;overflow: hidden;">
  			<div style="width: 20%;float: left;line-height: 65px;">
  				活动打分：
  			</div>
  			<div style="width: 80%;">
			    <input id="input-1ba" type="number" class="rating" min="0" max="5" step="0.5" data-stars=5
			    data-symbol="&#xe005;" data-default-caption="{rating} 星" data-star-captions="{}">
  			</div>
  		</div>
  		<!-- 领队打分 -->
  		<div style="width: 100%;overflow: hidden;">
  			<div style="width: 20%;float: left;line-height: 65px;">
  				领队打分：
  			</div>
  			<div style="width: 80%;">
			    <input id="input-2ba" type="number" class="rating" min="0" max="5" step="0.5" data-stars=5
			    data-symbol="&#xe005;" data-default-caption="{rating} 星" data-star-captions="{}">
  			</div>
  		</div>
  		<!-- 评论内容 -->
  		<div style="width: 100%;overflow: hidden;">
  			<div style="width: 20%;float: left;">
  				评论内容：
  			</div>
  			<div style="width: 80%;">
  				<textarea id="comment" rows="" cols="" placeholder="评论内容" style="margin: 0px; width: 386px; height: 175px;padding: 10px;" maxlength="100" ></textarea>
  			</div>
  		</div>
  		
  		<div style="width: 100%;overflow: hidden;text-align: center;margin-top: 30px;">
  			<button type="button" class="btn btn-w-m btn-info" onclick="submit()">确认</button>
  			&nbsp;&nbsp;&nbsp;&nbsp;
  			<button type="button" class="btn btn-w-m btn-danger" onclick="cancle()" >取消</button>
  		</div>
  		<script type="text/javascript">
  		function submit(){
  			if($('#input-1ba').val() == 0){
  				layer.msg('请给活动打分!');
  				return;
  			}
  			if($('#input-2ba').val() == 0){
  				layer.msg('请给领队打分!');
  				return;
  			}
  			if($('#comment').val().trim() == ''){
  				layer.msg('请撰写评论内容');
  				$('#comment').focus();
  				return;
  			}
  			layer.confirm('是否保存评价内容？',{icon:3,btn:['保存','再看看']},function(index){
				layer.load(0,{
					shade: [0.3, '#393D49']
				});
  				$.post('activeComments/comment.do',{
  					activity_id:$('#activeId').val(),
  					suId:$('#suId').val(),
  					content:$('#comment').val(),
  					activityScore:$('#input-1ba').val(),
  					leaderScore:$('#input-2ba').val()
  				},function(data){
					data = eval('('+data+')');
					if(data.RESPONSE_STATE=="200"){
						layer.msg('评论成功',{icon:1,time:800},function(){
							parent.location.reload();
							parent.layer.close(parent.layer.getFrameIndex(window.name));
						});
					}else{
						layer.closeAll('loading');
						layer.alert(data.ERROR_INFO,{icon:0});
					}
  				})
  			});
  		}
  		
  		function cancle(){
  			if($('#input-1ba').val() != 0 || $('#input-2ba').val() != 0  || $('#comment').val().trim() != ''){
  				layer.confirm('确认取消未保存的内容吗？',{icon:3},function(index){
  					layer.close(index);
  					parent.layer.close(parent.layer.getFrameIndex(window.name));
  				});
  			}else{
  				parent.layer.close(parent.layer.getFrameIndex(window.name));
  			}
  		}
  		</script>
  	</div>
  </body>
</html>
