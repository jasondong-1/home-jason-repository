<%@ page import="com.ideal.tagportrait.security.ShiroDbRealm" %>
<%@ page import="org.apache.shiro.SecurityUtils" %>
<%@ page import="org.apache.shiro.subject.Subject" %>
<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/include/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <title>登录</title>
    <meta name="description" content="User login page"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>

    <!-- bootstrap & fontawesome -->
    <link rel="stylesheet" href="${ctxRoot}/static/framework/bootstrap/assets/css/bootstrap.css"/>
    <link rel="stylesheet" href="${ctxRoot}/static/framework/bootstrap/assets/css/font-awesome.css"/>

    <!-- text fonts -->
    <link rel="stylesheet" href="${ctxRoot}/static/framework/bootstrap/assets/css/ace-fonts.css"/>

    <!-- ace styles -->
    <link rel="stylesheet" href="${ctxRoot}/static/framework/bootstrap/assets/css/ace.css"/>

    <!--[if lte IE 9]>
    <link rel="stylesheet" href="${ctxRoot}/static/framework/bootstrap/assets/css/ace-part2.css"/>
    <![endif]-->
    <link rel="stylesheet" href="${ctxRoot}/static/framework/bootstrap/assets/css/ace-rtl.css"/>

    <!--[if lte IE 9]>
    <link rel="stylesheet" href="${ctxRoot}/static/framework/bootstrap/assets/css/ace-ie.css"/>
    <![endif]-->

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->

    <!--[if lt IE 9]>
    <script src="${ctxRoot}/static/framework/bootstrap/assets/js/html5shiv.js"></script>
    <script src="${ctxRoot}/static/framework/bootstrap/assets/js/respond.js"></script>
    <![endif]-->
    <script type="text/javascript" src="${ctxRoot}/static/js/jquery-1.8.1.min.js"></script>
    <style>
        .widget-body, .widget-main {
            background: transparent !important;
            /*border: 1px solid #ccc;*/
            -webkit-border-radius: 5px;
            -moz-border-radius: 5px;
            border-radius: 5px;;
        }
        .title{
            color: #ffffff;
            font-family: Microsoft YaHei;
        }
        .block{
            margin-bottom: 20px;
        }
        .form-control{
            height: 40px;
        }
        .login{
            border-radius:3px !important;
            height: 40px;
        }
/*验证码----------start*/
        #checkHR {
            float:left;

        }
        #checkCode {
            float:left;
            width:90px;
            height:15px;
            padding: 0px 10px;
            color:red;
            font-size:16px;
            font-family:Arial;
            font-style:italic;
            color:Red;
            border:0;
            padding:2px 3px;
            letter-spacing:3px;
            font-weight:bolder;
        }
        .refresh {
            font-size: 14px;
            color: red;
        }
        .text {
            font-size: 16px;
            color: #F00;
        }
/*验证码----------end*/

    </style>
</head>
<body class="login-layout " style="background-image: url('${ctx}/static/images/bg.jpg'); background-size:100% 100%; " onload="createCode();">
<div class="main-container">
    <div class="main-content">
        <div class="row">
            <div class="col-sm-10 col-sm-offset-1">
                <div class="center" style="margin-top: 20px">
                    <img src="${ctxRoot}/static/images/login-logo2.png">
                    <img src="${ctxRoot}/static/images/login-title1.png">
                    <%--<span class="white" id="id-text2">大数据</span>--%>
                    <%--<h2 class="title">精准营销平台</h2>--%>
                </div>
                <div class="login-container">
                    <div class="space-6"></div>
                    <div class="position-relative" style="margin-top: 25px">
                        <div id="login-box" class="login-box visible widget-box no-border">
                            <div class="widget-body">
                                <div class="widget-main">
                                    <%--<h4 class="header blue lighter bigger">--%>
                                        <%--<i class="ace-icon fa fa-key green"></i>--%>
                                        <%--用户登录--%>
                                    <%--</h4>--%>

                                    <div class="space-6"></div>
                                    <form action="${ctxRoot}/jsp/login.jsp" method="post">
                                        <fieldset>
                                            <label class="block">
														<span class="block input-icon input-icon-right">
															<input type="text" name="username" class="form-control"
                                                                   placeholder="Username"/>
															<i class="ace-icon fa fa-user"></i>
														</span>
                                            </label>

                                            <label class="block">
														<span class="block input-icon input-icon-right">
															<input type="password" name="password" class="form-control"
                                                                   placeholder="Password"/>
															<i class="ace-icon fa fa-lock"></i>
														</span>
                                            </label>
                                            <label class="block">
														<span class="block input-icon input-icon-right">
															<input type="text"  id="checkNum" name="checkCode" style="height: 40px;width: 200px" placeholder="验证码"/>
                                                            <a href="javascript:void(0);" style="height: 40px;line-height: 40px"><div id="checkCode" onclick="createCode()";></div></a>
														</span>
                                            </label>
                                            <div class="space"></div>

                                            <div class="clearfix">
                                                <button type="button"
                                                        class="width-100  btn btn-sm btn-primary  login"
                                                        onclick="checkForm();">
                                                    <span class="bigger-120">登录</span>
                                                </button>
                                            </div>
                                            <%
                                                Subject subject = SecurityUtils.getSubject();
                                                ShiroDbRealm.ShiroUser shiroUser = (ShiroDbRealm.ShiroUser) subject.getPrincipal();
                                                if (shiroUser != null) {
                                                    String uri = request.getContextPath();
                                                    response.sendRedirect(uri + "/main/index.do");
                                                }
                                                String error = (String) request.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
                                                if (error != null) {
                                                    out.print("<span style='color: red'>用户名或密码错误。</span>");
                                                }
                                            %>
                                            <div class="space-4"></div>
                                        </fieldset>
                                    </form>
                                </div>
                                <!-- /.widget-main -->
                            </div>
                            <!-- /.widget-body -->
                        </div>
                        <!-- /.login-box -->
                    </div>
                    <!-- /.position-relative -->
                </div>
            </div>
            <!-- /.col -->
        </div>
        <!-- /.row -->
    </div>
    <!-- /.main-content -->
</div>
<!-- /.main-container -->

<h6 class="grey" style="position: absolute ;left: 45%;bottom: 30px" id="id-company-text">
    &copy; 中国电信云公司
</h6>

<!-- basic scripts -->
<!--[if !IE]> -->
<script type="text/javascript">
    window.jQuery || document.write("<script src='${ctxRoot}/static/framework/bootstrap/assets/js/jquery.js'>" + "<" + "/script>");
</script>

<!-- <![endif]-->

<!--[if IE]>
<script type="text/javascript">
    window.jQuery || document.write("<script src='${ctxRoot}/static/framework/bootstrap/assets/js/jquery1x.js'>" + "<" + "/script>");
</script>
<![endif]-->
<script type="text/javascript">
    if ('ontouchstart' in document.documentElement) document.write("<script src='${ctxRoot}/static/framework/bootstrap/assets/js/jquery.mobile.custom.js'>" + "<" + "/script>");
</script>

<!-- inline scripts related to this page -->
<script type="text/javascript">
    function checkForm() {
        var username = $('input[name="username"]').val();
        var password = $('input[name="password"]').val();
        var inputCode = document.getElementById("checkNum").value.toUpperCase();
        if(inputCode.length <=0) {
            alert("请输入验证码！");
            return false;
        }
        else if(inputCode != code ){
            alert("验证码输入错误！");
            createCode();
            return false;
        }
       else if (username.length > 0 && password.length > 0 && inputCode==code){
            $('form').submit();
        } else {
            alert("用户名和密码必须填写！");
        }
    }
    $(document).keydown(function (event) {
        if (event.keyCode == 13) {
            $(".login").click();
        }
    });
</script>
<script language="javascript" type="application/javascript">
    var code="" ; //在全局 定义验证码
    function createCode(){
        code = "";
        var codeLength = 6;//验证码的长度
        var checkCode = document.getElementById("checkCode");
        checkCode.value = "";
        var selectChar = new Array(0,1,2,3,4,5,6,7,8,9,'A','B','C','D','E','F','G','H','J','K','L','M','N','P','Q','R','S','T','U','V','W','X','Y','Z');

        for(var i=0;i<codeLength;i++) {
            var charIndex = Math.floor(Math.random()*32);
            code +=selectChar[charIndex];
        }
        if(code.length != codeLength){
            createCode();
        }
        document.getElementById("checkCode").innerHTML = code;
    }
</script>
</body>
</html>
