<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>

<head>
	<base href="/">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>报名管理</title>

    <link href="static/css/bootstrap.min.css" rel="stylesheet">
    <link href="static/css/font-awesome.min.css" rel="stylesheet">
    <link href="static/css/style.min.css" rel="stylesheet">
    <link href="static/css/page.css" rel="stylesheet">
    
    <style type="text/css">
	
	.laydate_box {
		box-sizing: content-box !important;
		-moz-box-sizing: content-box !important; /* Firefox */
		-webkit-box-sizing: content-box !important; /* Safari */
	}
	
	.laydate_box div {
		box-sizing: content-box !important;
		-moz-box-sizing: content-box !important; /* Firefox */
		-webkit-box-sizing: content-box !important; /* Safari */
	}
    </style>

</head>

	<!-- 导航 -->
	<jsp:include page="/view/index/navigate.jsp"></jsp:include>
	
	
<body>
    <div class="wrapper wrapper-content  animated fadeInRight">
        <div class="row"  style="width: 80%;margin: 0 auto;">
            <div id="user-box" class="col-sm-12">
                <div class="ibox">
                <form action="activeSignup/MySignUp.html" id="signup_form" method="post">
                    <div class="ibox-content">
                    
                    
                        <div class="input-group col-md-12">
                        	<div class="col-md-3">
                               <input type="number" name="asu_order_id" value="${requestScope.search.asu_order_id }" placeholder="订单编号" class="input form-control">
                            </div> 
                        	<div class="col-md-3">
                               <input type="text" name="asu_user_name" value="${requestScope.search.asu_user_name }" placeholder="姓名" class="input form-control">
                            </div> 
                        	<div class="col-md-3">
                               <input type="number" name="asu_link_user_phone" value="${requestScope.search.asu_link_user_phone }" placeholder="手机号码" class="input form-control">
                            </div> 
                        	<div class="col-md-3">
                               <select name="asu_state" class="form-control m-b" >
                                    <option value="0" <c:if test="${requestScope.search.asu_state == 0 }">selected="selected"</c:if>>报名状态</option>
                                    <option value="10" <c:if test="${requestScope.search.asu_state == 10 }">selected="selected"</c:if>>等待领队确认</option>
                                    <option value="20" <c:if test="${requestScope.search.asu_state == 20 }">selected="selected"</c:if>>领队已确认</option>
                                    <option value="30" <c:if test="${requestScope.search.asu_state == 30 }">selected="selected"</c:if>>交易完成</option>
                                    <option value="40" <c:if test="${requestScope.search.asu_state == 40 }">selected="selected"</c:if>>报名已取消</option>
                                    <option value="50" <c:if test="${requestScope.search.asu_state == 50 }">selected="selected"</c:if>>等待领队确认取消</option>
                                </select>
                            </div> 
                        </div>
                        
                        <br>
                    
                        <div class="input-group col-md-12">
                        	<div class="col-md-3">
                               <select name="asu_pay_state" class="form-control m-b" >
                                    <option value="0" <c:if test="${requestScope.search.asu_pay_state == 0 }">selected="selected"</c:if>>是否支付</option>
                                    <option value="1" <c:if test="${requestScope.search.asu_pay_state == 1 }">selected="selected"</c:if>>未支付</option>
                                    <option value="2" <c:if test="${requestScope.search.asu_pay_state == 2 }">selected="selected"</c:if>>已支付</option>
                                </select>
                            </div> 
                            
                        	<div class="col-md-3">
                           		<fmt:formatDate value="${requestScope.search.start_asu_create_time }" pattern="yyyy-MM-dd HH:mm:ss" var="start_asu_create_time" scope="page" />
                           		<input type="text" name="start_asu_create_time" value="${pageScope.start_asu_create_time }" id="start_asu_create_time" readonly placeholder="报名起始时间" class="input form-control">
                            </div> 
                            
                        	<div class="col-md-3">
	                       		<fmt:formatDate value="${requestScope.search.end_asu_create_time }" pattern="yyyy-MM-dd HH:mm:ss" var="end_asu_create_time" scope="page" />
	                            <input type="text" name="end_asu_create_time" value="${pageScope.end_asu_create_time }" id="end_asu_create_time" readonly placeholder="报名终止时间" class="input form-control">
                            </div> 
                           <div class="col-md-2" >
                              <button type="submit" class="btn btn btn-primary"> <i class="fa fa-search"></i> 搜索</button>
                              <button type="button" id="form_reset" class="btn btn btn-danger"> <i class="fa fa-refresh"></i> 重置</button>
                           </div>
                        </div>
                        
                       <div class="hr-line-dashed"></div> 
                            <div class="tab-content">
                                <div class="tab-pane active">
                                    <div class="full-height-scroll">
                                        <div class="table-responsive">
                                            <table class="table table-striped table-hover">
	                                            <thead>
					                                <tr>
														<th>订单编号</th>
														<th>姓名</th>
														<th>性别</th>
														<th>联系人手机号码</th>
														<th>订单备注</th>
														<th>报名状态</th>
														<th>是否支付</th>
														<th>已付款总额</th>
														<th>付款类型</th>
														<th>报名时间</th>
														<th>操作</th>
					                                </tr>
					                            </thead>
                                            
                                                <tbody>
                                                
                                                	<c:forEach items="${activeSignUps }" var="as" varStatus="vs">
														<tr>
															<td>${as.asu_order_id }</td>
															<td>${as.asu_user_name }</td>
															<td>${as.asu_user_sex }</td>
															<td>${as.asu_link_user_phone }</td>
															<td>${as.asu_order_area }</td>
															<td>
																<a class="btn 
																btn-w-m
																<c:choose>
																	<c:when test="${as.asu_state == '等待领队确认' }">btn-danger</c:when>
																	<c:when test="${as.asu_state == '领队已确认' }">btn-info</c:when>
																	<c:when test="${as.asu_state == '交易完成' }">btn-primary</c:when>
																	<c:when test="${as.asu_state == '报名已取消' }">btn-default</c:when>
																	<c:otherwise>btn-warning</c:otherwise>
																</c:choose>
																">${as.asu_state }</a>
															</td>
															<td>
																<a class="btn 

																<c:choose>
																	<c:when test="${as.asu_pay_state == '已支付' }">btn-primary</c:when>
																	<c:otherwise>btn-danger</c:otherwise>
																</c:choose>
																
																btn-rounded">${as.asu_pay_state }</a>
															</td>
															<td>${as.asu_account_paid }</td>
															<td>${as.asu_price_type }</td>
															<td>
																<fmt:formatDate value="${as.asu_create_time }" pattern="yyyy-MM-dd HH:mm:ss" />
															</td>
															<td>
																<c:if test="${as.asu_state == '等待领队确认' }">
																	<a class="btn btn-outline btn-info" href="javascript:leaderConfirm(${as.asu_active_id },'${as.asu_id }')" >确认报名</a>
																</c:if>
																<c:if test="${as.asu_state == '等待领队确认取消' }">
																	<a class="btn btn-outline btn-info" href="javascript:cancelSignUp(${as.asu_active_id },'${as.asu_id }')" >确认取消</a>
																</c:if>
															</td>
														</tr>
													</c:forEach>
													
													
                                                </tbody>
                                            </table>
										<script type="text/javascript">
										function cancelSignUp(activeId,asuId){
											layer.confirm('请确认是否要取消该用户报名状态',{icon:3},function(index){
												layer.close(index);
												layer.load(0,{
													shade: [0.3, '#393D49']
												});
												
												$.post('activeSignup/cancelSignUp.do',{
													activeId:activeId,
													asuId:asuId
												},function(data){
													data = eval('('+data+')');
													if(data.RESPONSE_STATE == '200'){
														layer.msg('修改成功!',{icon:1,time:800},function(){
															location.reload();
														});
													}else{
														layer.closeAll('loading');
														layer.alert(data.ERROR_INFO,{icon:0});
													}
												
												});
												
											})
										}
										
										function leaderConfirm(activeId,asuId){
											layer.confirm('请确认是否要修改该报名信息状态？',{icon:3},function(index){
												layer.close(index);
												layer.load(0,{
													shade: [0.3, '#393D49']
												});
												
												$.post('activeSignup/confirmSignUp.do',{
													activeId:activeId,
													asuId:asuId
												},function(data){
													data = eval('('+data+')');
													if(data.RESPONSE_STATE == '200'){
														layer.msg('修改成功!',{icon:1,time:800},function(){
															location.reload();
														});
													}else{
														layer.closeAll('loading');
														layer.alert(data.ERROR_INFO,{icon:0});
													}
												
												});
												
											})
										}
										</script>                                       
									</div>
                                    </div>
			                       <div class="hr-line-dashed"></div>
				                        <div class="text-center">
										  ${page.pageStr }
				                        </div>
                                </div>
                               
                            </div>
 						</div>
                   </form> 
                </div>
            </div>
          
                <div class="ibox" id="userInfo-box" style="display:none;">
                
                	
                	<div id="content-loding" class="ibox-content" style="display:none;">
                        <div class="spiner-example">
                            <div class="sk-spinner sk-spinner-three-bounce">
                                <div class="sk-bounce1"></div>
                                <div class="sk-bounce2"></div>
                                <div class="sk-bounce3"></div>
                            </div>
                        </div>
                    </div>
                	
                	
                </div>
           
        </div>
    </div>
  	

    <script src="static/js/jquery-2.1.1.min.js"></script>
    <script src="static/js/bootstrap.min.js"></script>
	<script src="static/js/plugins/layer/layer.min.js"></script>
	<script type="text/javascript" src="static/js/plugins/layer/laydate/laydate.js"></script>
    <script src="static/js/content.min.js"></script>
    
    <script type="text/javascript">
    $(function(){
    	$("#form_reset").click(function(){
    		$("#signup_form input").val("");
    		$("#signup_form select").val("0");
    		$("#signup_form").submit();
    	});
    	
    	
    	laydate.skin('molv');
		var start = {
			elem : '#start_asu_create_time',
			format : 'YYYY-MM-DD hh:mm:ss',
			max : '2099-06-16 23:59:59', // 最大日期
			istime : true,
			istoday : true,
			choose : function(datas) {
				start_to.min = datas; // 开始日选好后，重置结束日的最小日期
				start_to.start = datas; // 将结束日的初始值设定为开始日
			}
		};
		var start_to = {
			elem : '#end_asu_create_time',
			format : 'YYYY-MM-DD hh:mm:ss',
			max : '2099-06-16 23:59:59', // 最大日期
			istime : true,
			istoday : true,
			choose : function(datas) {
				start.max = datas; // 结束日选好后，重置开始日的最大日期
			}
		};
		
		laydate(start);
		laydate(start_to);
    
    });
    </script>
    
</body>


	<!-- 页脚 -->
	<jsp:include page="/view/index/footer.jsp"></jsp:include>
</html>