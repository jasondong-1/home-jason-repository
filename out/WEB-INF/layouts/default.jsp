<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/include/taglib.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>精准营销--用户画像系统</title>
    <!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
    <link href="${ctxRoot}/static/css/zongcss.css" rel="stylesheet" type="text/css"/>
    <link href="${ctxRoot}/static/css/main.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${ctxRoot}/static/js/jquery-1.8.1.min.js"></script>
    <script type="text/javascript" src="${ctxRoot}/static/js/scriptLib.js"></script>
    <script type="text/javascript" src="${ctxRoot}/static/js/loding.js"></script>
    <script type="text/javascript" src="${ctxRoot}/static/js/plugin.js"></script>
    <script type="text/javascript" src="${ctxRoot}/static/js/jquery-validation/jquery.validate.min.js"></script>
    <script type="text/javascript" src="${ctxRoot}/static/js/jquery-validation/localization/messages_zh.js"></script>
    <sitemesh:head/>
</head>

<!--[if lt IE 7 ]>
<body class="ie ie6"> <![endif]-->
<!--[if IE 7 ]>
<body class="ie ie7 "> <![endif]-->
<!--[if IE 8 ]>
<body class="ie ie8 "> <![endif]-->
<!--[if IE 9 ]>
<body class="ie ie9 "> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!-->
<body class="zong">
<!--<![endif]-->
<%@ include file="/WEB-INF/layouts/header.jsp" %>
<sitemesh:body/>
</body>
</html>
