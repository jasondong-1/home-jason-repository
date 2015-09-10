<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/include/taglib.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <title>500 Error Page</title>

    <meta name="description" content="500 Error Page"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>

    <!-- bootstrap & fontawesome -->
    <link rel="stylesheet" href="${ctxRoot}/static/framework/bootstrap//assets/css/bootstrap.css"/>
    <link rel="stylesheet" href="${ctxRoot}/static/framework/bootstrap//assets/css/font-awesome.css"/>

    <!-- page specific plugin styles -->

    <!-- text fonts -->
    <link rel="stylesheet" href="${ctxRoot}/static/framework/bootstrap//assets/css/ace-fonts.css"/>

    <!-- ace styles -->
    <link rel="stylesheet" href="${ctxRoot}/static/framework/bootstrap//assets/css/ace.css" class="ace-main-stylesheet"
          id="main-ace-style"/>

    <!--[if lte IE 9]>
    <link rel="stylesheet" href="${ctxRoot}/static/framework/bootstrap//assets/css/ace-part2.css"
          class="ace-main-stylesheet"/>
    <![endif]-->

    <!--[if lte IE 9]>
    <link rel="stylesheet" href="${ctxRoot}/static/framework/bootstrap//assets/css/ace-ie.css"/>
    <![endif]-->

    <!-- inline styles related to this page -->

    <!-- ace settings handler -->
    <script src="${ctxRoot}/static/framework/bootstrap//assets/js/ace-extra.js"></script>

    <!-- HTML5shiv and Respond.js for IE8 to support HTML5 elements and media queries -->

    <!--[if lte IE 8]>
    <script src="${ctxRoot}/static/framework/bootstrap//assets/js/html5shiv.js"></script>
    <script src="${ctxRoot}/static/framework/bootstrap//assets/js/respond.js"></script>
    <![endif]-->
</head>

<body class="no-skin">
<!-- #section:basics/navbar.layout -->
<div id="navbar" class="navbar navbar-default">
    <script type="text/javascript">
        try {
            ace.settings.check('navbar', 'fixed')
        } catch (e) {
        }
    </script>

    <div class="navbar-container" id="navbar-container">
        <!-- #section:basics/sidebar.mobile.toggle -->
        <button type="button" class="navbar-toggle menu-toggler pull-left" id="menu-toggler" data-target="#sidebar">
            <span class="sr-only">Toggle sidebar</span>

            <span class="icon-bar"></span>

            <span class="icon-bar"></span>

            <span class="icon-bar"></span>
        </button>

        <!-- /section:basics/sidebar.mobile.toggle -->
        <div class="navbar-header pull-left">
            <!-- #section:basics/navbar.layout.brand -->
            <a href="#" class="navbar-brand">
                <small>
                    <i class="fa fa-cogs red"></i>
                    分布式管理系统
                </small>
            </a>

            <!-- /section:basics/navbar.layout.brand -->

            <!-- #section:basics/navbar.toggle -->

            <!-- /section:basics/navbar.toggle -->
        </div>

        <!-- #section:basics/navbar.dropdown -->


        <!-- /section:basics/navbar.dropdown -->
    </div>
    <!-- /.navbar-container -->
</div>

<!-- /section:basics/navbar.layout -->
<div class="main-container" id="main-container">
    <script type="text/javascript">
        try {
            ace.settings.check('main-container', 'fixed')
        } catch (e) {
        }
    </script>

    <!-- #section:basics/sidebar -->

    <!-- /section:basics/sidebar -->
    <div class="main-content">
        <div class="main-content-inner">
            <!-- #section:basics/content.breadcrumbs -->

            <!-- /section:basics/content.breadcrumbs -->
            <div class="page-content">
                <!-- #section:settings.box -->

                <!-- /section:settings.box -->
                <div class="row">
                    <div class="col-xs-12">
                        <!-- PAGE CONTENT BEGINS -->

                        <!-- #section:pages/error -->
                        <div class="error-container">
                            <div class="well">
                                <h1 class="grey lighter smaller">
											<span class="blue bigger-125">
												<i class="ace-icon fa fa-random"></i>
												500
											</span>
                                    Something Went Wrong
                                </h1>

                                <hr/>
                                <h3 class="lighter smaller">
                                    But we are working
                                    <i class="ace-icon fa fa-wrench icon-animated-wrench bigger-125"></i>
                                    on it!
                                </h3>

                                <div class="space"></div>

                                <div>
                                    <h4 class="lighter smaller">Meanwhile, try one of the following:</h4>

                                    <ul class="list-unstyled spaced inline bigger-110 margin-15">
                                        <li>
                                            <i class="ace-icon fa fa-hand-o-right blue"></i>
                                            Read the faq
                                        </li>

                                        <li>
                                            <i class="ace-icon fa fa-hand-o-right blue"></i>
                                            Give us more info on how this specific error occurred!
                                        </li>
                                    </ul>
                                </div>

                                <hr/>
                                <div class="space"></div>

                                <div class="center">
                                    <a href="javascript:history.back()" class="btn btn-grey btn-round">
                                        <i class="ace-icon fa fa-arrow-left"></i>
                                        返回
                                    </a>

                                    <a href="${ctxRoot}" class="btn btn-primary btn-round">
                                        <i class="ace-icon fa fa-home"></i>
                                        回到首页
                                    </a>
                                </div>
                            </div>
                        </div>

                        <!-- /section:pages/error -->

                        <!-- PAGE CONTENT ENDS -->
                    </div>
                    <!-- /.col -->
                </div>
                <!-- /.row -->
            </div>
            <!-- /.page-content -->
        </div>
    </div>
    <!-- /.main-content -->

    <a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
        <i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
    </a>
</div>
<!-- /.main-container -->

<!-- basic scripts -->

<!--[if !IE]> -->
<script type="text/javascript">
    window.jQuery || document.write("<script src='${ctxRoot}/static/framework/bootstrap//assets/js/jquery.js'>" + "<" + "/script>");
</script>

<!-- <![endif]-->

<!--[if IE]>
<script type="text/javascript">
    window.jQuery || document.write("<script src='${ctxRoot}/static/framework/bootstrap//assets/js/jquery1x.js'>" + "<" + "/script>");
</script>
<![endif]-->
<script type="text/javascript">
    if ('ontouchstart' in document.documentElement) document.write("<script src='${ctxRoot}/static/framework/bootstrap//assets/js/jquery.mobile.custom.js'>" + "<" + "/script>");
</script>
<script src="${ctxRoot}/static/framework/bootstrap//assets/js/bootstrap.js"></script>

<!-- page specific plugin scripts -->

<!-- ace scripts -->
<script src="${ctxRoot}/static/framework/bootstrap//assets/js/ace/elements.scroller.js"></script>
<script src="${ctxRoot}/static/framework/bootstrap//assets/js/ace/elements.colorpicker.js"></script>
<script src="${ctxRoot}/static/framework/bootstrap//assets/js/ace/elements.fileinput.js"></script>
<script src="${ctxRoot}/static/framework/bootstrap//assets/js/ace/elements.typeahead.js"></script>
<script src="${ctxRoot}/static/framework/bootstrap//assets/js/ace/elements.wysiwyg.js"></script>
<script src="${ctxRoot}/static/framework/bootstrap//assets/js/ace/elements.spinner.js"></script>
<script src="${ctxRoot}/static/framework/bootstrap//assets/js/ace/elements.treeview.js"></script>
<script src="${ctxRoot}/static/framework/bootstrap//assets/js/ace/elements.wizard.js"></script>
<script src="${ctxRoot}/static/framework/bootstrap//assets/js/ace/elements.aside.js"></script>
<script src="${ctxRoot}/static/framework/bootstrap//assets/js/ace/ace.js"></script>
<script src="${ctxRoot}/static/framework/bootstrap//assets/js/ace/ace.ajax-content.js"></script>
<script src="${ctxRoot}/static/framework/bootstrap//assets/js/ace/ace.touch-drag.js"></script>
<script src="${ctxRoot}/static/framework/bootstrap//assets/js/ace/ace.sidebar.js"></script>
<script src="${ctxRoot}/static/framework/bootstrap//assets/js/ace/ace.sidebar-scroll-1.js"></script>
<script src="${ctxRoot}/static/framework/bootstrap//assets/js/ace/ace.submenu-hover.js"></script>
<script src="${ctxRoot}/static/framework/bootstrap//assets/js/ace/ace.widget-box.js"></script>
<script src="${ctxRoot}/static/framework/bootstrap//assets/js/ace/ace.settings.js"></script>
<script src="${ctxRoot}/static/framework/bootstrap//assets/js/ace/ace.settings-rtl.js"></script>
<script src="${ctxRoot}/static/framework/bootstrap//assets/js/ace/ace.settings-skin.js"></script>
<script src="${ctxRoot}/static/framework/bootstrap//assets/js/ace/ace.widget-on-reload.js"></script>
<script src="${ctxRoot}/static/framework/bootstrap//assets/js/ace/ace.searchbox-autocomplete.js"></script>

<!-- inline scripts related to this page -->

<!-- the following scripts are used in demo only for onpage help and you don't need them -->
<link rel="stylesheet" href="${ctxRoot}/static/framework/bootstrap//assets/css/ace.onpage-help.css"/>
<link rel="stylesheet" href="${ctxRoot}/static/framework/bootstrap//docs/assets/js/themes/sunburst.css"/>

<script type="text/javascript"> ace.vars['base'] = '${ctxRoot}'; </script>
<script src="${ctxRoot}/static/framework/bootstrap//assets/js/ace/elements.onpage-help.js"></script>
<script src="${ctxRoot}/static/framework/bootstrap//assets/js/ace/ace.onpage-help.js"></script>
<script src="${ctxRoot}/static/framework/bootstrap//docs/assets/js/rainbow.js"></script>
<script src="${ctxRoot}/static/framework/bootstrap//docs/assets/js/language/generic.js"></script>
<script src="${ctxRoot}/static/framework/bootstrap//docs/assets/js/language/html.js"></script>
<script src="${ctxRoot}/static/framework/bootstrap//docs/assets/js/language/css.js"></script>
<script src="${ctxRoot}/static/framework/bootstrap//docs/assets/js/language/javascript.js"></script>
</body>
</html>
