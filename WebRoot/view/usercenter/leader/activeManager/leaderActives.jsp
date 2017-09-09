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

    <title>活动管理</title>

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
                <form action="activity/actives.html" id="active_form" method="post">
                    <div class="ibox-content">
                    
                    
                        <div class="input-group col-md-12">
                        	<div class="col-md-2">
                               <input type="text" name="title" value="${sa.title }" placeholder="活动标题" class="input form-control">
                            </div> 
                        	<div class="col-md-5">
	                        	<div class="col-md-5">
                               		<fmt:formatDate value="${sa.activityTime }" pattern="yyyy-MM-dd HH:mm:ss" var="activityTime" scope="page" />
                               		<input type="text" name="activityTime" value="${pageScope.activityTime }" id="start_time" readonly placeholder="开始时间" class="input form-control">
	                            </div>
	                        	<div class="col-md-1">
                               		----
	                            </div>
	                        	<div class="col-md-5">
	                        		<fmt:formatDate value="${sa.activityTimeTo }" pattern="yyyy-MM-dd HH:mm:ss" var="activityTimeTo" scope="page" />
                               		<input type="text" name="activityTimeTo" value="${pageScope.activityTimeTo }" id="start_time_to" readonly placeholder="开始时间" class="input form-control">
	                            </div>
                            </div> 
                            
                        	<div class="col-md-5">
	                        	<div class="col-md-3">
                               		<input type="number" name="price_start" value="${sa.price_start }" placeholder="活动费用" class="input form-control">
	                            </div>
	                        	<div class="col-md-1">
                               	---
	                            </div>
	                        	<div class="col-md-3">
                               		<input type="number" name="price_to" value="${sa.price_to }" placeholder="活动费用" class="input form-control">
	                            </div>
                            </div> 
                        </div>
                        
                        <br>
                    
                        <div class="input-group col-md-12">
                        	<div class="col-md-2">
                               <select name="de_state" class="form-control m-b" >
                                    <option value="0" <c:if test="${sa.de_state == 0 }">selected="selected"</c:if>>活动状态</option>
                                    <option value="1" <c:if test="${sa.de_state == 1 }">selected="selected"</c:if>>草稿</option>
                                    <option value="2" <c:if test="${sa.de_state == 2 }">selected="selected"</c:if>>发布待审核</option>
                                    <option value="3" <c:if test="${sa.de_state == 3 }">selected="selected"</c:if>>审核中</option>
                                    <option value="4" <c:if test="${sa.de_state == 4 }">selected="selected"</c:if>>审核不通过</option>
                                    <option value="5" <c:if test="${sa.de_state == 5 }">selected="selected"</c:if>>审核成功</option>
                                    <option value="15" <c:if test="${sa.de_state == 15 }">selected="selected"</c:if>>活动结束</option>
                                </select>
                            </div> 
                        	<div class="col-md-5">
	                        	<div class="col-md-5">
                               		<fmt:formatDate value="${sa.a_enroll_end_time }" pattern="yyyy-MM-dd HH:mm:ss" var="a_enroll_end_time" scope="page" />
                                	<input type="text" name="a_enroll_end_time" value="${pageScope.a_enroll_end_time }" id="a_enroll_end_time" readonly  placeholder="报名截止时间" class="input form-control">
	                            </div>
	                        	<div class="col-md-1">
                               		----
	                            </div>
	                        	<div class="col-md-5">
	                        		<fmt:formatDate value="${sa.a_enroll_end_time_to }" pattern="yyyy-MM-dd HH:mm:ss" var="a_enroll_end_time_to" scope="page" />
                               		<input type="text" name="a_enroll_end_time_to" value="${pageScope.a_enroll_end_time_to }" id="a_enroll_end_time_to" readonly  placeholder="报名截止时间" class="input form-control">
	                            </div>
                            </div> 
                           <div class="col-md-2" >
	                        	<div class="col-md-12">
	                              <button type="submit" class="btn btn btn-primary"> <i class="fa fa-search"></i> 搜索</button>
	                              <button type="button" id="form_reset" class="btn btn btn-danger"> <i class="fa fa-refresh"></i> 重置</button>
	                            </div>
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
														<th>编号</th>
														<th>活动标题</th>
														<th>活动类型</th>
														<th>活动费用(￥)</th>
														<th>已报名&nbsp;/&nbsp;需要人数</th>
														<th>确认人数</th>
														<th>开始时间</th>
														<th>报名截止时间</th>
														<th>活动状态</th>
														<th>操作</th>
					                                </tr>
					                            </thead>
                                            
                                                <tbody>
                                                
                                                	<c:forEach items="${lsa }" var="l" varStatus="ls" >
														<tr>
															<td>${l.activeId }</td>
															<td>
																<a href="javascript:detail(${l.activeId })">
																	${l.title }
																</a>
															</td>
															<td>
																<c:forEach items="${l.ats }" var="t" varStatus="vs">
																	${t.cl_name }
																	<c:if test="${fn:length(l.ats)-1 != vs.index }">
																	、
																	</c:if>
																</c:forEach>
															</td>
															<td>${l.price }</td>
															<td>${l.alreadyInNum }&nbsp;/&nbsp;${l.needUserNum }</td>
															<td>${l.confirmUserNum }</td>
															<td>
																<fmt:formatDate value="${l.activityTime }" pattern="yyyy-MM-dd HH:mm:ss" />
															</td>
															<td>
																<fmt:formatDate value="${l.endTime }" pattern="yyyy-MM-dd HH:mm:ss" />
															</td>
															<td>
																<a class="btn 
																btn-w-m
																<c:choose>
																	<c:when test="${l.state == '草稿' }">btn-warning</c:when>
																	<c:when test="${l.state == '发布待审核' }">btn-info</c:when>
																	<c:when test="${l.state == '审核中' }">btn-success</c:when>
																	<c:when test="${l.state == '审核不通过' }">btn-danger</c:when>
																	<c:when test="${l.state == '审核成功' }">btn-primary</c:when>
																	<c:otherwise>btn-default</c:otherwise>
																</c:choose>
																">${l.state }</a>
															</td>
															<td>
																<a class="btn btn-outline btn-info" href="javascript:activeSignUps(${l.activeId },${l.alreadyInNum })" >报名</a>
																<a class="btn btn-outline btn-info" href="javascript:reply(${l.activeId })" >回复咨询</a>
															</td>
														</tr>
													</c:forEach>
													
													
                                                </tbody>
                                            </table>
                                            <script type="text/javascript">
											function activeSignUps(id,num){
												if(num == 0){
													layer.msg('暂时无人报名');
													return;
												}
												window.open('activeSignup/activesSignUps.html?activeId='+id);
											}
											function detail(id){
												window.open('activity/detail/'+id+'.html');
											}
											function reply(id){
												window.open('activity/detail/'+id+'.html#active-consultation');
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
    		$("#active_form input").val("");
    		$("#active_form select").val("0");
    		$("#active_form").submit();
    	});
    	
    	
    	laydate.skin('molv');
		var start = {
			elem : '#start_time',
			format : 'YYYY-MM-DD hh:mm:ss',
			min : laydate.now(), // 设定最小日期为当前日期
			max : '2099-06-16 23:59:59', // 最大日期
			istime : true,
			istoday : true,
			choose : function(datas) {
				start_to.min = datas; // 开始日选好后，重置结束日的最小日期
				start_to.start = datas; // 将结束日的初始值设定为开始日
			}
		};
		var start_to = {
			elem : '#start_time_to',
			format : 'YYYY-MM-DD hh:mm:ss',
			min : laydate.now(), // 设定最小日期为当前日期
			max : '2099-06-16 23:59:59', // 最大日期
			istime : true,
			istoday : true,
			choose : function(datas) {
				start.max = datas; // 结束日选好后，重置开始日的最大日期
			}
		};
		
		
		var end = {
			elem : '#a_enroll_end_time',
			format : 'YYYY-MM-DD hh:mm:ss',
			min : laydate.now(),
			max : '2099-06-16 23:59:59',
			istime : true,
			istoday : false,
			choose : function(datas) {
				end.min = datas; // 开始日选好后，重置结束日的最小日期
				end.start = datas; // 将结束日的初始值设定为开始日
			}
		};
		
		var end_to = {
			elem : '#a_enroll_end_time_to',
			format : 'YYYY-MM-DD hh:mm:ss',
			min : laydate.now(),
			max : '2099-06-16 23:59:59',
			istime : true,
			istoday : false,
			choose : function(datas) {
				end.max = datas; // 结束日选好后，重置开始日的最大日期
			}
		};
		
		laydate(start);
		laydate(start_to);
		laydate(end);
		laydate(end_to);
    
    });
    </script>
    
</body>


	<!-- 页脚 -->
	<jsp:include page="/view/index/footer.jsp"></jsp:include>
</html>