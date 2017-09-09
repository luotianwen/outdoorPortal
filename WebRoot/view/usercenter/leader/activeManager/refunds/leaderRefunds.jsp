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

    <title>退款管理</title>

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
                <form action="activeSignup/lRefunds.html" id="refunds_form" method="post">
                    <div class="ibox-content">
                    
                    
                        <div class="input-group col-md-12">
                        	<div class="col-md-3">
                           		<fmt:formatDate value="${requestScope.search.asu_refund_time_start }" pattern="yyyy-MM-dd HH:mm:ss" var="asu_refund_time_start" scope="page" />
                           		<input type="text" name="asu_refund_time_start" value="${pageScope.asu_refund_time_start }" id="asu_refund_time_start" readonly placeholder="申请退款起始时间" class="input form-control">
                            </div> 
                        	<div class="col-md-3">
	                       		<fmt:formatDate value="${requestScope.search.asu_refund_time_end }" pattern="yyyy-MM-dd HH:mm:ss" var="asu_refund_time_end" scope="page" />
	                            <input type="text" name="asu_refund_time_end" value="${pageScope.asu_refund_time_end }" id="asu_refund_time_end" readonly placeholder="申请退款终止时间" class="input form-control">
                            </div> 
                            
                        	<div class="col-md-3">
                               <input type="number" name="asu_order_id" value="${requestScope.search.asu_order_id }" placeholder="订单编号" class="input form-control">
                            </div> 
                        	<div class="col-md-3">
                               <select name="asu_refund_state" class="form-control m-b" >
                               		<option value="0" <c:if test="${requestScope.search.asu_refund_state == 0 }">selected="selected"</c:if>>退款状态</option>
                                    <option value="80" <c:if test="${requestScope.search.asu_refund_state == 80 }">selected="selected"</c:if>>等待领队确认</option>
                                    <option value="90" <c:if test="${requestScope.search.asu_refund_state == 90 }">selected="selected"</c:if>>等待用户确认</option>
                                    <option value="100" <c:if test="${requestScope.search.asu_refund_state == 100 }">selected="selected"</c:if>>等待退款认</option>
                                    <option value="105" <c:if test="${requestScope.search.asu_refund_state == 105 }">selected="selected"</c:if>>等待退款到账</option>
                                    <option value="110" <c:if test="${requestScope.search.asu_refund_state == 110 }">selected="selected"</c:if>>退款关闭</option>
                                    <option value="120" <c:if test="${requestScope.search.asu_refund_state == 120 }">selected="selected"</c:if>>等待客服处理</option>
                                    <option value="130" <c:if test="${requestScope.search.asu_refund_state == 130 }">selected="selected"</c:if>>退款完成</option>
                                </select>
                            </div>
                        </div>
                        
                        <br>
                    
                        <div class="input-group col-md-12">
                        	<div class="col-md-3">
                           		<fmt:formatDate value="${requestScope.search.asu_create_time_start }" pattern="yyyy-MM-dd HH:mm:ss" var="asu_create_time_start" scope="page" />
                           		<input type="text" name="asu_create_time_start" value="${pageScope.asu_create_time_start }" id="asu_create_time_start" readonly placeholder="报名起始时间" class="input form-control">
                            </div> 
                            
                        	<div class="col-md-3">
	                       		<fmt:formatDate value="${requestScope.search.asu_create_time_end }" pattern="yyyy-MM-dd HH:mm:ss" var="asu_create_time_end" scope="page" />
	                            <input type="text" name="asu_create_time_end" value="${pageScope.asu_create_time_end }" id="asu_create_time_end" readonly placeholder="报名终止时间" class="input form-control">
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
														<th>活动标题</th>
														<th>付款总额(元)</th>
														<th>退款总额</th>
														<th>退款状态</th>
														<th>报名时间</th>
														<th>付款时间</th>
														<th>申请退款时间</th>
														<th>退款时间</th>
														<th>退款备注</th>
														<th>操作</th>
					                                </tr>
					                            </thead>
                                            
                                                <tbody>
                                                
                                                	<c:forEach items="${list }" var="l" varStatus="ls" >
														<tr>
															<td>${l.asu_order_id }</td>
															<td>
																<a href="javascript:detail(${l.asu_active_id })">
																	${l.title }
																</a>
															</td>
															<td>${l.asu_account_paid }</td>
															<td>${l.asu_refund_price }</td>
															<td>
																<a class="btn 
																btn-w-m
																<c:choose>
																	<c:when test="${l.asu_refund_state == '等待领队确认' }">btn-danger</c:when>
																	<c:when test="${l.asu_refund_state == '等待用户确认' }">btn-success</c:when>
																	<c:when test="${l.asu_refund_state == '等待退款' }">btn-info</c:when>
																	<c:when test="${l.asu_refund_state == '等待退款到账' }">btn-warning</c:when>
																	<c:when test="${l.asu_refund_state == '退款关闭' }">btn-default</c:when>
																	<c:when test="${l.asu_refund_state == '等待客服处理' }">btn-danger btn-rounded btn-outline</c:when>
																	<c:otherwise>btn-primary</c:otherwise>
																</c:choose>
																">${l.asu_refund_state }</a>
															</td>
															<td>
																<fmt:formatDate value="${l.asu_create_time }" pattern="yyyy-MM-dd HH:mm:ss" />
															</td>
															<td>
																<fmt:formatDate value="${l.asu_pay_time }" pattern="yyyy-MM-dd HH:mm:ss" />
															</td>
															<td>
																<fmt:formatDate value="${l.asu_refund_time }" pattern="yyyy-MM-dd HH:mm:ss" />
															</td>
															<td>
																<fmt:formatDate value="${l.asu_refund_date }" pattern="yyyy-MM-dd HH:mm:ss" />
															</td>
															<td>${l.asu_leader_refund_area }</td>
															<td>
																<c:if test="${l.asu_refund_state == '等待领队确认' }">
																	<a class="btn btn-outline btn-primary" href="javascript:setRefund(${l.asu_active_id },'${l.asu_id }')" >设置退款金额</a>
																</c:if>
															</td>
														</tr>
													</c:forEach>
													
													
                                                </tbody>
                                            </table>
                                            
                                            <div style="display: none;font-size: 15px;padding: 20px;" id="set_refund">
										  		<input type="number" min="0" max="99999" style="width: 260px;height: 25px;" placeholder="退款金额" id="refund_money"  />
										  		<br>
										  		<br>
										  		<textarea rows="3" cols="34" placeholder="退款备注" maxlength="100" id="refund_area" ></textarea>
										  		<br>
										  		<br>
										  		<button type="button" onclick="updateOrder()" >确认</button>
										  	</div>
											<script type="text/javascript">
											function detail(id){
												window.open('activity/detail/'+id+'.html');
											}
											var activeId;
											var orderId;
											function setRefund(a,o){
												activeId=a;
												orderId=o;
											  layer.open({
											    type: 1,
											    title: '设置退款金额',
											    closeBtn: 1,
											    area: ['355px','260px'],
											    shadeClose: false,
											    content: $('#set_refund')
											  });
											}
											
										  	function updateOrder(){
										  		if($('#refund_money').val() == ''){
										  			layer.msg('请填写金额');
										  			$('#refund_money').focus();
										  			return;
										  		}
										  		if($('#refund_area').val().trim() == ''){
										  			layer.msg('请填写退款备注');
										  			$('#refund_area').focus();
										  			return;
										  		}
										  		layer.confirm('是否确认提交退款金额?<span style="color:red;">注：待用户同意之后将交由财务部退款</span>',{icon:3},function(index){
										  			layer.close(index);
										  			layer.load(1,{shade:0.1});
										  			$.post('activeSignup/updateRefundOfLeader.do',{
										  				activeId:activeId,
										  				orderId:orderId,
										  				refundMoney:$('#refund_money').val(),
										  				refundArea:$('#refund_area').val()
										  			},function(data){
														data = eval('('+data+')');
														if(data.RESPONSE_STATE == '200'){
															layer.msg('修改成功!',{icon:1});
															setTimeout(function(){
																location.reload();
															},1000);
														}else{
															layer.closeAll('loading');
															layer.alert(data.ERROR_INFO,{icon:0});
														}
										  			})
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
    		$("#refunds_form input").val("");
    		$("#refunds_form select").val("0");
    		$("#refunds_form").submit();
    	});
    	
    	
    	laydate.skin('molv');
		var asu_refund_time_start = {
			elem : '#asu_refund_time_start',
			format : 'YYYY-MM-DD hh:mm:ss',
			max : '2099-06-16 23:59:59', // 最大日期
			istime : true,
			istoday : true,
			choose : function(datas) {
				asu_refund_time_end.min = datas; // 开始日选好后，重置结束日的最小日期
				asu_refund_time_end.start = datas; // 将结束日的初始值设定为开始日
			}
		};
		var asu_refund_time_end = {
			elem : '#asu_refund_time_end',
			format : 'YYYY-MM-DD hh:mm:ss',
			max : '2099-06-16 23:59:59', // 最大日期
			istime : true,
			istoday : true,
			choose : function(datas) {
				asu_refund_time_start.max = datas; // 结束日选好后，重置开始日的最大日期
			}
		};
		
		laydate(asu_refund_time_start);
		laydate(asu_refund_time_end);
		
		
		var asu_create_time_start = {
			elem : '#asu_create_time_start',
			format : 'YYYY-MM-DD hh:mm:ss',
			max : '2099-06-16 23:59:59', // 最大日期
			istime : true,
			istoday : true,
			choose : function(datas) {
				asu_create_time_end.min = datas; // 开始日选好后，重置结束日的最小日期
				asu_create_time_end.start = datas; // 将结束日的初始值设定为开始日
			}
		};
		var asu_create_time_end = {
			elem : '#asu_create_time_end',
			format : 'YYYY-MM-DD hh:mm:ss',
			max : '2099-06-16 23:59:59', // 最大日期
			istime : true,
			istoday : true,
			choose : function(datas) {
				asu_create_time_start.max = datas; // 结束日选好后，重置开始日的最大日期
			}
		};
		
		laydate(asu_create_time_start);
		laydate(asu_create_time_end);
    
    });
    </script>
    
</body>


	<!-- 页脚 -->
	<jsp:include page="/view/index/footer.jsp"></jsp:include>
</html>