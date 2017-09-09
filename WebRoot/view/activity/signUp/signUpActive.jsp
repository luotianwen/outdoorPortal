<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>
<base href="/">
<title>填写报名信息_多人</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="robots" content="index, follow">
<meta name="keywords" content="户外运动，户外旅游，驴友活动">
<meta name="description"
	content="玩嘛户外网是中国第一家户外运动网站,也是最具影响力的驴友论坛,作为优秀户外旅行活动网站,绿野为用户提供户外知识资讯,自助游线路,旅行装备评测,活动发起,户外旅行保险等服务">
<meta name="renderer" content="webkit">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet" type="text/css" href="http://order.lvye.com/css/od/common.css">
<link rel="stylesheet" type="text/css" href="http://order.lvye.com/css/od/validationEngine.jquery.css?v=1">
<link rel="stylesheet" type="text/css" href="http://order.lvye.com/css/od/page.css?v01">
<link rel="stylesheet" type="text/css" href="http://order.lvye.com/css/header/header.css?v=1">
<script>
	var ctx = "http://order.lvye.com";
	var cssCtx = "http://order.lvye.com";
	var imageCtx = "http://order.lvye.com";
	var jsCtx = "http://order.lvye.com";
	var eventCtx = "http://huodong.lvye.com";
	var perCtx = "http://sns.lvye.cn/home.php";
</script>
<script type="text/javascript" src="static/js/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="static/js/plugins/layer/layer.min.js"></script>
</head>
<body>


	<!-- 导航 -->
	<jsp:include page="/view/index/navigate.jsp"></jsp:include>
	<br>
	
	<script type="text/javascript">
		// 导航下拉菜单
		$('#newnavi li').hover(function() {
			$(this).addClass('lihover');
			$(this).find('.navdropBox').show();
		}, function() {
			$(this).removeClass('lihover');
			$(this).find('.navdropBox').hide();
		});
		$(".searchtype").hover(function() {
			$('.searchdrop').show();
		}, function() {
			$('.searchdrop').hide();
		})
		$("#search_bar").find("li").click(
				function() {
					var s = $(this);
					if (s.data('searchtype')) {
						$("#indexSearchTypeValue").val(s.attr("data-searchtype"));
						$("#searchselect").text(s.text());
						if (s.data('searchtype') == "lvxing") {
							$("#commonSearchForm").attr("action","http://www.lvye.com/search")
						} else {
							$("#commonSearchForm").attr("action","http://www.lvye.cn//huodong/search")
						}
					}
					$('.searchdrop').hide();
				});
		$('.topnavul li').hover(
				function() {
					if ($(this).find('dl').html() == ''
							|| $(this).find('dl').html() == undefined) {
						$(this).addClass('topnavhover1');
					} else {
						$(this).addClass('topnavhover');
						$(this).find('dl').show();
					}
				}, function() {
					$(this).removeClass('topnavhover');
					$(this).removeClass('topnavhover1');
					$(this).find('dl').hide();
				})
		$(".searchdrop li").click(function() {
			var st = $(this).attr("data-searchtype");
			if (st && st != "") {
				$("#commonSearchForm input[name='ser1']").val(st);
			}
		});

		$('.close_x').bind('click', function() {
			$('.newsbox').remove();
		})

		$("#commonSearchForm")
				.submit(
						function(ev) {
							var searchsite = "http://search.lvye.com/";
							var website = "http://www.lvye.cn/";
							var comsite = "http://www.lvye.com/";
							var stype = $(this).find("input[name='ser1']")
									.val();
							var kw = $(this).find("input[name='q']").val();
							if (stype == "activity") {
								if (kw && kw != "") {
									kw = "?keyword=" + kw
								}
								window.open(searchsite + "event/search.jhtml"
										+ kw);
							} else if (stype == "lvxing") {
								if (kw && kw != "") {
									kw = "?q=" + kw
								}
								window.open(comsite + "search/" + kw);
							} else if (stype == "portal") {
								if (kw && kw != "") {
									kw = "kw=" + kw
								}
								window.open(website + "search.php?mod=portal&"
										+ kw);
							} else if (stype == "forum") {
								if (kw && kw != "") {
									kw = "kw=" + kw
								}
								window.open(website + "search.php?mod=forum&"
										+ kw);
							} else if (stype == "user") {
								if (kw && kw != "") {
									kw = "username=" + kw
								}
								window.open(website
												+ "home.php?mod=spacecp&ac=search&type=all&searchsubmit=true&"
												+ kw);
							}
							ev.preventDefault();
						})
	</script>
	<!-- header end -->

	<!-- container begin -->
	<div class="container wrapper clearfix">
		<div class="inner_container wrapper fn-pr" id="order-info">
			<div class="mainbox clearfix">
				<div class="steps steps-3">
					<ol>
						<li class="steps-1 active"><i>1</i> <span class="tsl">填写报名信息</span>
						</li>
						<li><i>2</i> <span class="tsl">确认报名信息</span></li>
						<li><i>3</i> <span class="tsl">报名成功</span></li>
					</ol>
				</div>
				<div class="content hdInfoBox">
					<h5>活动信息</h5>
					<div class="innerHdInfo">
						<p class="t">
							<a href="http://huodong.lvye.com/event/1398198/" target="_blank">${requestScope.active.title }</a>
						</p>
						<div class="d clearfix">
							<ul>
								<li><dl>
										<dt>出 发 地：</dt>
										<dd>
										${requestScope.active.province }&nbsp;
										${requestScope.active.city }&nbsp;
										${requestScope.active.district }&nbsp;
										</dd>
									</dl>
								</li>
								<li><dl>
										<dt>目 的 地：</dt>
										<dd>
										${requestScope.active.l_province }&nbsp;
										${requestScope.active.l_city }&nbsp;
										${requestScope.active.l_district }&nbsp;
										</dd>
									</dl>
								</li>
								<li><dl>
										<dt>出发日期：</dt>
										<dd>
											<fmt:formatDate value="${requestScope.active.activityTime }" pattern="yyyy-MM-dd" />
										</dd>
									</dl>
								</li>
								<li><dl>
										<dt>返程日期：</dt>
										<dd>
											<fmt:formatDate value="${requestScope.active.endTime }" pattern="yyyy-MM-dd" />
										</dd>
									</dl>
								</li>
								<li><dl>
										<dt>活动费用：</dt>
										<dd>
											<span class="r">${requestScope.active.price }元/人</span>
										</dd>
									</dl>
								</li>
								<li><dl>
										<dt>预付款费用：</dt>
										<dd>
											<span class="r">${requestScope.active.a_reserve_price }元/人</span>
										</dd>
									</dl>
								</li>
							</ul>
						</div>
					</div>
				</div>
				<form id="bmInfoForm" action="javascript:void(0);">
		            <div class="content bmInfoBox">
		                <h5>报名信息<span class="itips">请如实填写报名信息，以免出行中发生问题，您所填写的信息我们将会严格保密，请放心填写。</span></h5>
		                <div class="innerBmInfo fn-pr">
		                    <p class="helpTip fn-none">编辑常用报名人信息<br>请到买家订单中心</p>
		                                                            <input type="hidden" name="emrequire" value="0">
		                    <input type="hidden" name="exprequire" value="0">
		                    <input type="hidden" name="eqprequire" value="0">
		                    <input type="hidden" name="insure" value="0">
		                    <div class="contactGroupList clearfix">
		                            <dl>
		                                <dt><i></i>常用联系人</dt>
		                                <dd>
		                                	<ul>
		                                		<c:forEach items="${requestScope.users }" var="user" >
			                                        <li>
			                                            <input id="${user.lsuu_id }" type="checkbox" name="contact" spid="${user.lsuu_id }" emmb="" emn="" exp="" btd="" eqp="" cdy="1" cad="" tn="${user.lsuu_user_name }" mb="${user.lsuu_phone }" 
			                                            gd="${user.lsuu_user_sex }">
			                                            <label for="${user.lsuu_id }">${user.lsuu_user_name }&nbsp;
			                                            <c:choose>
			                                            	<c:when test="${user.lsuu_user_sex == 1 }">男</c:when>
			                                            	<c:otherwise>女</c:otherwise>
			                                            </c:choose>
			                                            &nbsp; ${user.lsuu_phone }</label>
			                                        </li>
		                                		</c:forEach>
		                                    </ul>
		                                </dd>
		                            </dl>
		                        </div>
		                        <div class="contactItemLIst clearfix">
		                        <div class="addBmUser">
		                            <button name="addUser" id="addUser"><span class="addSyl">+</span>增加报名人</button>
		                        </div>
		                    </div>
		                </div>
		            </div>
		            <div class="content orderBzBox">
		                <h5>订单备注</h5>
		                <div class="innerOrderInfo">
		                    <textarea class="orderArea validate[maxSize[100]]" name="orderArea" id="orderArea" cols="30" rows="5" title="订单备注" data-errormessage-value-missing="* 请输入订单备注"></textarea>
		                    <div class="tipMsg"></div>
		                </div>
		            </div>
		        </form>
				<div class="finishBox">
					<div class="freebox">
						<p>
							活动费用总额：<span id="fee-amount-sp">${requestScope.active.price }元</span>
						</p>
						<p>
							预付费总额：<span id="pre-fee-sp">${requestScope.active.a_reserve_price }元</span>
						</p>
						<label
							style="color: #ec6919; font-size:14px;margin-top:10px; font-family: 'Microsoft Yahei'; font-family: 'Microsoft Yahei'; display:block">为保证您的利益，请通过玩嘛网在线支付。否则，一旦活动出现问题，请与领队联系，自行承担风险。</label>
					</div>
					<div class="protocolBox">
						<input type="checkbox" name="aggrementSign"
							class="text-input validate[required]"
							data-errormessage-value-missing="* 请勾选我已阅读并同意此协议" checked=""
							value="1">
						<p class="popProtocol">
							我已阅读并同意<span>《玩嘛活动报名须知》</span>
						</p>
					</div>
					<div class="submitBox">
						<button name="" class="comfirmBtn">下一步&nbsp;&nbsp;确认订单</button>
					</div>
				</div>
			</div>
		</div>

		<div class="inner_container wrapper fn-pr" id="order-confirm"
			style="display: none">
			<div class="header_crumbs">
				<h5>
					<span>报名</span><span class="spacer">&gt;</span>
				</h5>
				<ul>
					<li><a href="javascript:void(0);">${requestScope.active.title }</a>
					</li>
				</ul>
			</div>
			<div class="mainbox module clearfix">
				<div class="steps steps-3">
					<ol>
						<li class="steps-1"><i>1</i> <span class="tsl">填写报名信息</span>
						</li>
						<li class="active"><i>2</i> <span class="tsl">确认报名信息</span></li>
						<li><i>3</i> <span class="tsl">报名成功</span></li>
					</ol>
				</div>
				<form method="POST" id="order-form">
					<!-- 活动信息 -->
					<div class="content hdInfoBox">
						<h5>活动信息</h5>

						<div class="innerHdInfo">
						<p class="t">
							<a href="http://huodong.lvye.com/event/1398198/" target="_blank">${requestScope.active.title }</a>
						</p>
						<div class="d clearfix">
							<ul>
								<li><dl>
										<dt>出 发 地：</dt>
										<dd>
										${requestScope.active.province }&nbsp;
										${requestScope.active.city }&nbsp;
										${requestScope.active.district }&nbsp;
										</dd>
									</dl>
								</li>
								<li><dl>
										<dt>目 的 地：</dt>
										<dd>
										${requestScope.active.l_province }&nbsp;
										${requestScope.active.l_city }&nbsp;
										${requestScope.active.l_district }&nbsp;
										</dd>
									</dl>
								</li>
								<li><dl>
										<dt>出发日期：</dt>
										<dd>
											<fmt:formatDate value="${requestScope.active.activityTime }" pattern="yyyy-MM-dd" />
										</dd>
									</dl>
								</li>
								<li><dl>
										<dt>返程日期：</dt>
										<dd>
											<fmt:formatDate value="${requestScope.active.endTime }" pattern="yyyy-MM-dd" />
										</dd>
									</dl>
								</li>
								<li><dl>
										<dt>活动费用：</dt>
										<dd>
											<span class="r">${requestScope.active.price }元/人</span>
										</dd>
									</dl>
								</li>
								<li><dl>
										<dt>预付款费用：</dt>
										<dd>
											<span class="r">${requestScope.active.a_reserve_price }元/人</span>
										</dd>
									</dl>
								</li>
							</ul>
						</div>
					</div>
					</div>
					<input type="hidden" name="activeId" value="${requestScope.active.id }" />
					<input type="hidden" name="eventPrice" value="${requestScope.active.price }" />
					<input type="hidden" name="eventPrepayFee" value="${requestScope.active.a_reserve_price }" /> 
					<input type="hidden" name="applyNumber" /> <!-- 报名人数 -->
					<input type="hidden" name="feeAmount" /> <!-- 费用总额 -->
					<input type="hidden" name="prepayFee" /> <!-- 预付费 -->
					<input type="hidden" name="aggrement" /><!-- 是否同意协议 -->
					<!-- 报名信息 -->
					<div class="content bmInfoBox">
						<h5>报名信息</h5>

						<div class="bm_user_list clearfix"></div>
					</div>
					<!-- 订单备注 -->
					<div class="content orderBzBox">
						<h5>订单备注</h5>

						<div class="cont">
							<p>无</p>
						</div>
						<input type="hidden" name="notes" />
					</div>
					<!-- 费用支付 -->
					<div class="finishBox">
						<input type="hidden" name="fullPay" value="0" />

						<div class="freebox"></div>
						<div class="submitBox">
							<button type="button" name="" class="modifyBtn">返回修改订单</button>
							<button type="button" name="" class="comfirmBtn">提交，并付全款</button>
						</div>
					</div>
				</form>
			</div>
		</div>

	</div>
	<!-- container end -->

  	<br>
	<!-- 页脚 -->
	<jsp:include page="/view/index/footer.jsp"></jsp:include>

	<script>
		(function(w, d, s, g, a, m) {
			a = d.createElement(s), m = d.getElementsByTagName(s)[0];
			a.async = 1;
			a.src = g;
			m.parentNode.insertBefore(a, m);
		})(window, document, 'script', 'http://tracklog.lvye.com/referrer.js');
	</script>

	<script>
		(function(i, s, o, g, r, a, m) {
			i['GoogleAnalyticsObject'] = r;
			i[r] = i[r] || function() {
				(i[r].q = i[r].q || []).push(arguments)
			}, i[r].l = 1 * new Date();
			a = s.createElement(o), m = s.getElementsByTagName(o)[0];
			a.async = 1;
			a.src = g;
			m.parentNode.insertBefore(a, m)
		})(window, document, 'script',
				'//www.google-analytics.com/analytics.js', 'ga');

		ga('create', 'UA-49878689-1', 'auto');
		ga('require', 'linker');
		ga('linker:autoLink', [ 'lvye.cn', 'lvye.com' ], true);
		ga('require', 'displayfeatures');
		ga('send', 'pageview');
	</script>

	<script>
		var _hmt = _hmt || [];
		(function() {
			var hm = document.createElement("script");
			hm.src = "//hm.baidu.com/hm.js?a2b7ef33f37d317f6fc93d9263d197e5";
			var s = document.getElementsByTagName("script")[0];
			s.parentNode.insertBefore(hm, s);
		})();
	</script>
	<!-- footer end -->
	<script type="text/javascript" src="view/activity/signUp/common.js"></script>
	<script type="text/javascript"
		src="http://order.lvye.com/js/My97DatePicker/WdatePicker.js?v=1.0"></script>
	<script type="text/javascript"
		src="http://order.lvye.com/js/jquery.validationEngine.js?v=1"></script>
	<script type="text/javascript"
		src="http://order.lvye.com/js/jquery.validationEngine-zh_CN.js?v=1"></script>
	<script type="text/javascript" src="http://order.lvye.com/js/date.js?v=1"></script>
	<script type="text/javascript" src="view/activity/signUp/page.js"></script>
	<script type="text/javascript" src="view/activity/signUp/order.js"></script>
	<script type="text/javascript">
		$(function() {
			var spLength = 0;
			spLength = $("input[name=contact]").length;
			if (spLength > 0) {
				$("input[name=contact]").each(function() {
					$(this)[0].checked = 0;
				})
				$("input[name=contact]").eq(0).click();
			} else {
				$('#addUser').click();
			}
		})
	</script>
</body>
</html>