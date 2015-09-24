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
</head>

<body class="login-layout " style="background-image: url('${ctx}/static/images/1.jpg'); background-size:100% 100%; ">
<div class="main-container">
    <div class="main-content">
        <div class="row">
            <div class="col-sm-10 col-sm-offset-1">
                <div class="login-container2">
                    <div class="center">
                        <h1>
                            <%--<i class="ace-icon fa fa-cogs green"></i>--%>
                            <img src="${ctxRoot}/static/images/logo1.png">
                            <span class="white" id="id-text2">精准营销平台</span>
                        </h1>
                        <h4 class="grey" id="id-company-text"> 中国电信云计算公司</h4>
                    </div>

                    <div class="space-6"></div>

                    <div class="position-relative">
                        <div id="login-box" class="login-box visible widget-box no-border">
                            <div class="widget-body" style="opacity: 0.95">
                                <div class="widget-main">
                                    <h4 class="header blue lighter bigger">
                                        <i class="ace-icon fa fa-key green"></i>
                                        用户登录
                                    </h4>
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

                                            <div class="space"></div>

                                            <div class="clearfix">
                                                <label class="inline">
                                                    <input type="checkbox" class="ace"/>
                                                    <span class="lbl">自动登录</span>
                                                </label>

                                                <button type="button"
                                                        class="width-35 pull-right btn btn-sm btn-primary btn-white btn-round login"
                                                        onclick="checkForm();">
                                                    <span class="bigger-110">登录</span>
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
        if (username.length > 0 && password.length > 0) {
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
</body>
</html>
