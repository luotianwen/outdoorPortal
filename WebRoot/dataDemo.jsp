<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>

<head>
	<base href="/">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>在线用户维护</title>

    <link href="static/css/bootstrap.min.css" rel="stylesheet">
    <link href="static/css/font-awesome.min.css" rel="stylesheet">
    <link href="static/css/style.min.css" rel="stylesheet">
    <link href="static/css/page.css" rel="stylesheet">

    <!-- 全局js -->
    <script src="static/js/jquery-2.1.1.min.js"></script>

</head>

	<!-- 导航 -->
	<jsp:include page="/view/index/navigate.jsp"></jsp:include>
	
	
<body>
    <div class="wrapper wrapper-content  animated fadeInRight">
        <div class="row"  style="width: 1200px;margin: 0 auto;">
            <div id="user-box" class="col-sm-12">
                <div class="ibox">
                <form action="webSocketController/soud.html" id="usersForm" method="post">
                    <div class="ibox-content">
                        <div class="input-group col-md-8">
                        	<div class="col-md-4">
                               <input type="text" id="u_name" placeholder="用户昵称" name="uName" value="${search.uName }" class="input form-control">
                            </div> 
                        	<div class="col-md-4">
                               <select name="uType" class="form-control m-b" id="u_type" >
                                    <option value="0" <c:if test="${search.uType == 0 }">selected="selected"</c:if> >用户身份</option>
                                    <option value="1" <c:if test="${search.uType == 1 }">selected="selected"</c:if>>个人</option>
                                    <option value="2" <c:if test="${search.uType == 2 }">selected="selected"</c:if>>企业</option>
                                    <option value="3" <c:if test="${search.uType == 3 }">selected="selected"</c:if>>普通用户</option>
                                    <option value="4" <c:if test="${search.uType == 4 }">selected="selected"</c:if>>后台管理用户</option>
                                </select>
                            </div> 
                           <div class="col-md-4">
                              <button type="submit" class="btn btn btn-primary"> <i class="fa fa-search"></i> 搜索</button>
                              <button type="button" id="form_reset" class="btn btn btn-danger"> <i class="fa fa-refresh"></i> 重置</button>
                              <button type="button" id="refresh_url" class="btn btn btn-warning"> <i class="fa fa-refresh fa-spin"></i> 刷新缓存对象</button>
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
					                                	<th>
						                                   <label><input type="checkbox" id="checkAll" class="i-checks"></label>
														</th>
					                                    <th></th>
					                                    <th>姓名</th>
					                                    <th>性别</th>
					                                    <th>Email</th>
					                                    <th>电话</th>
					                                    <th>注册时间</th>
					                                    <th>身份类型</th>
					                                    <th class="center">操作</th>
					                                </tr>
					                            </thead>
                                            
                                                <tbody>
                                                
                                                	<c:forEach items="${users }" var="user" >
                                                	<tr>
                                                		<td>
						                                   <label><input type="checkbox" id="uIds" value="${user.uId }"  class="i-checks" ></label>
                                                		</td>
                                                        <td class="client-avatar">
                                                        	<img alt="image" src="${user.uHeadImg }" > 
                                                        </td>
                                                        <td>
                                                        	<a data-toggle="tab" href="#contact-1" class="client-link">${user.uName }</a>
                                                        </td>
                                                        <td>
                                                          <c:if test="${user.uSex == 1 }">
                                                          		男
                                                          </c:if>
                                                          <c:if test="${user.uSex == 2 }">
                                                          		女
                                                          </c:if>
                                                        	
                                                        </td>
                                                        <td>
                                                        	<a href="mailto:${user.uEmail }">
		                                                        <i class="fa fa-envelope"></i> 
		                                                        ${user.uEmail }
	                                                        </a>
                                                        </td>
                                                        <td>
	                                                        <i class="fa fa-phone"></i> 
	                                                         ${user.uPhone }
                                                        </td>
                                                        <td>  <fmt:formatDate value="${user.uCreateTime }" type="both"/></td>
                                                        <td>
                                                          <c:if test="${user.uType == 1 }">
                                                          		个人发布者
                                                          </c:if>
                                                          <c:if test="${user.uType == 2 }">
                                                        		企业
                                                          </c:if>
                                                          <c:if test="${user.uType == 3 }">
                                                      			 普通用户   
                                                          </c:if> 
                                                          <c:if test="${user.uType == 4 }">
                                                      			 后台管理用户
                                                          </c:if> 
                                                        
                                                        </td>
                                                        </td>
                                                        <td class="center">
                                                        
	                                                        <p data-id="${user.uId }">
										                        <button type="button" id="post_message" class="btn btn-outline btn-success"><i class="fa fa-clipboard"></i> 发送消息</button>
										                        <button type="button" id="user-show" class="btn btn-outline btn-success"><i class="fa fa-clipboard"></i> 查看</button>
										                        <button type="button" id="user-edit" class="btn btn-outline btn-primary"><i class="fa fa-paste"></i> 编辑</button>
										                        <button type="button" id="user-delete" class="btn btn-outline btn-danger"><i class="fa fa-trash"></i> 删除</button>
										                        <c:if test="${user.isGAG == 2 }">
										                        <button type="button" id="isGAG" name="user-gag" class="btn btn-outline btn-warning"><i class="fa fa-minus-square"></i> 禁言</button>
										                        </c:if>
										                        <c:if test="${user.isGAG == 1 }">
										                        <button type="button" id="isGAG" name="user-speak" class="btn btn-outline btn-warning"><i class="fa fa-commenting-o"></i> 发言</button>
										                        </c:if>
										                        <c:if test="${user.isFroZen == 2 }">
										                        <button type="button"  id="user-frozen" class="btn btn-outline btn-danger"><i class="fa fa-lock"></i> 冻结</button>
										                        </c:if>
										                        <c:if test="${user.isFroZen == 1 }">
										                        <button type="button" id="user-frozen" class="btn btn-outline btn-danger"><i class="fa fa-unlock"></i> 解冻</button>
										                        </c:if>
										                       
										                    </p>
                                                         
                                                        </td>
                                                    </tr>
                                                	</c:forEach>
                                                </tbody>
                                            </table>
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
  	

    <!-- 全局js -->
    <script src="static/js/bootstrap.min.js"></script>
	<script src="static/js/plugins/layer/layer.min.js"></script>
    <script src="static/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>

     <!-- 自定义js -->
    <script src="static/js/content.min.js"></script>
    
</body>


	<!-- 页脚 -->
	<jsp:include page="/view/index/footer.jsp"></jsp:include>
</html>