<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>


<head>
	<base href="/">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    

    <title>付款 - 玩嘛</title>

    <link href="static/css/bootstrap.min.css" rel="stylesheet">
    <link href="static/css/font-awesome.min.css" rel="stylesheet">
    <link href="static/css/animate.min.css" rel="stylesheet">

    <link href="static/css/style.min.css" rel="stylesheet">
    <!-- iCheck -->
    <link href="static/css/plugins/iCheck/custom.css" rel="stylesheet">

<style type="text/css">
#bank img{
background-image: url(static/css/bank/bank-logo.png);
width: 125px;height: 28px;
background-repeat: no-repeat;
}

</style>


</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
		<div class="row">
			<div id="user-box" class="col-sm-12">
				<div class="ibox">
					<div class="ibox-content">
                        <form action="alipay/pay.html"  method="post" class="form-horizontal" target="_blank">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">订单号</label>
                                <div class="col-sm-10">
                                    <input type="text" class="form-control"  placeholder="订单号" id="out_trade_no"  name="out_trade_no" value="">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">订单名称 </label>
                                <div class="col-sm-10">
                                    <input type="text" class="form-control"  placeholder="订单名称 " id="subject"  name="subject" value="">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">付款金额</label>
                                <div class="col-sm-10">
                                    <input type="text" class="form-control"  placeholder="付款金额" id="total_fee"  name="total_fee" value="">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">订单描述</label>
                                <div class="col-sm-10">
                                    <input type="text" class="form-control"  placeholder="订单描述" id="body"  name="body" value="">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">商品地址</label>
                                <div class="col-sm-10">
                                    <input type="text" class="form-control"  placeholder="商品地址" id="show_url"  name="show_url" value="">
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">付款方式</label>
								<div class="col-sm-10" id="bank">
	                                <div class="col-sm-12">
	                                        <label>
	                                            <input type="radio"  value="ZFB" name="bank" class="radio i-checks radio-primary">
	                                             <i></i> <img name="支付宝" src="static/css/bank/165x58.png">    </label>
	                                </div>
	                                <div class="col-sm-12">
	                                        <label>
	                                            <input type="radio"  value="ICBCBTB" name="bank" class="radio i-checks radio-primary">
	                                             <i></i> <img name="工商银行" style="background-position: 0px -140px;"></label>
	                                            &nbsp;&nbsp;&nbsp;
	                                        <label>
	                                            <input type="radio"  value="CCBBTB" name="bank" class="radio i-checks"> 
	                                            <i></i> <img name="建设银行" style="background-position: 0px -336px;"></label>
	                                                &nbsp;&nbsp;&nbsp;
	                                        <label>
	                                            <input type="radio"  value="CMBBTB" name="bank" class="radio i-checks"> 
	                                            <i></i> <img name="招商银行" style="background-position: 0px -784px;"></label>
	                                </div>
	                                <div class="col-sm-12">
	                                        <label>
	                                            <input type="radio"  value="ABCBTB" name="bank" class="radio i-checks radio-primary">
	                                             <i></i> <img name="农业银行" style="background-position: 0px -532px;"></label>
	                                            &nbsp;&nbsp;&nbsp;
	                                        <label>
	                                            <input type="radio"  value="SPDBB2B" name="bank" class="radio i-checks"> 
	                                            <i></i> <img name="浦发银行" style="background-position: 0px -588px;"></label>
	                                                &nbsp;&nbsp;&nbsp;
	                                        <label>
	                                            <input type="radio"  value="BOCBTB" name="bank" class="radio i-checks"> 
	                                            <i></i> <img name="中国银行" style="background-position: 0px -840px;"></label>
	                                </div> 
	                                <div class="col-sm-12">
	                                        <label>
	                                            <input type="radio"  value="POSTGC" name="bank" class="radio i-checks radio-primary">
	                                             <i></i> <img name="中国邮政储蓄银行" style="background-position: 0px -868px;"></label>
	                                            &nbsp;&nbsp;&nbsp;
	                                        <label>
	                                            <input type="radio"  value="CIB" name="bank" class="radio i-checks"> 
	                                            <i></i> <img name="兴业银行" style="background-position: 0px -756px;"></label>
	                                                &nbsp;&nbsp;&nbsp;
	                                        <label>
	                                            <input type="radio"  value="GDB" name="bank" class="radio i-checks"> 
	                                            <i></i> <img name="广发银行" style="background-position: 0px -196px;"></label>
	                                </div>  
	                                <div class="col-sm-12">
	                                        <label>
	                                            <input type="radio"  value="SPABANK" name="bank" class="radio i-checks radio-primary">
	                                             <i></i> <img name="平安银行" style="background-position: 0px -560px;"></label>
	                                            &nbsp;&nbsp;&nbsp;
	                                         <label>
	                                            <input type="radio"  value="BJBANK" name="bank" class="radio i-checks radio-primary">
	                                             <i></i> <img name="北京银行" style="background-position: 0px -28px;"></label>
	                                            &nbsp;&nbsp;&nbsp;
	                                         <label>
	                                            <input type="radio"  value="COMM-DEBIT" name="bank" class="radio i-checks radio-primary">
	                                             <i></i> <img name="交通银行" style="background-position: 0px -392px;"></label>
	                                </div>  
	                                <div class="col-sm-12">
	                                        
	                                        <label>
	                                            <input type="radio"  value="CEB-DEBIT" name="bank" class="radio i-checks"> 
	                                            <i></i> <img name="中国光大银行" style="background-position: 0px -168px;"></label>
	                                                &nbsp;&nbsp;&nbsp;
	                                        <label>
	                                            <input type="radio"  value="CITIC-DEBIT" name="bank" class="radio i-checks"> 
	                                            <i></i> <img name="中信银行" style="background-position: 0px -896px;"></label>
	                                </div>   
	                                 
	                                
                               </div>
                            </div> 
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <div class="col-sm-4 col-sm-offset-2">
                                    <button class="btn btn-primary" type="submit" >确认支付</button>
                                </div>
                            </div>
                        </form>
                    </div>
				</div>
			</div>
	 </div>
</div>
	<!-- 全局js -->
    <script src="static/js/jquery-2.1.1.min.js"></script>
    <script src="static/js/bootstrap.min.js"></script>
	<script src="static/js/plugins/layer/layer.min.js"></script>
    <!-- jQuery Validation plugin javascript-->
    <script src="static/js/plugins/validate/jquery.validate.min.js"></script>
    <script src="static/js/plugins/validate/messages_zh.min.js"></script>
    <!-- iCheck -->
    <script src="static/js/plugins/iCheck/icheck.min.js"></script>
    <script>
     
		$(function() {
			 $(".i-checks").iCheck({
			    radioClass: "iradio_square-green",
			 });
	    });
		
	 
	</script>


</body>


</html>