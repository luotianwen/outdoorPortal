<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="/">
    
    <title>意见建议</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <div style="margin-left:100px; padding:2px; width:400px; border:#CCC solid 1px; " id="noneDiv">
		<form id="proposeForm">
		<table width="400" border="0" align="center">
		  <tr>
		    <td align="center" valign="middle">反馈类型</td>
		    <td align="left" valign="middle">
		    	<select style="width:200px; height:25px; margin:1px; padding:2px;" id="issuetype" name="issuetype">
		            <option value="1">BUG问题</option>
		            <option value="2">体验问题</option>
		            <option value="3">其它</option>
		        </select>
		    </td>
		  </tr>
		  <tr>
		    <td align="center" valign="middle">反馈内容:</td>
		    <td align="left" valign="middle"><textarea id="content" name="content" style="width:200px;height:200px; resize:none; margin:1px; padding:5px;" maxlength="500"></textarea></td>
		  </tr>
		  <tr>
		    <td align="center" valign="middle">联系方式:</td>
		    <td align="left" valign="middle"><input type="text" style="width:200px; height:20px; margin:1px; padding:2px;" id="contact" name="contact" maxlength="50" /></td>
		  </tr>
		  <tr>
		    <td align="center" valign="middle"></td>
		    <td align="left" valign="middle"><button type="button" style="height:25px; width:50px; background-color:#0F0; color:white; margin:1px; padding:0px; border-color:#0F0; cursor:pointer" id="sub" name="sub">提交</button></td>
		  </tr>
		</table>
		</form>
	</div>
  </body>
  
  <script src="static/js/jquery-2.1.1.min.js"></script>
  <script src="static/js/plugins/layer/layer.js"></script>
  <script type="text/javascript">
  	$("#sub").click(//意见建议功能
  	function(){
  		if($("#content").val().trim()==""){
  			layer.alert("请输入反馈内容",{icon:2});
  		}else if($("#contact").val().trim()==""){
  			layer.alert("请输入联系方式",{icon:2});
  		}else{
  			$.post("issue/insertIssue.html",$("#proposeForm").serialize(),
					function(data){
						data=eval('('+data+')');
						if(data.RESPONSE_STATE=="200"){
							layer.alert(data.SUCCESS_INFO,{icon:1});
						}else{
							layer.alert(data.ERROR_INFO,{icon:2});
						}
					}
				);
  		}
  	}
  );
  </script>
</html>
