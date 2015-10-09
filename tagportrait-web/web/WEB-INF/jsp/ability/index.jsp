<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/include/taglib.jsp" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>homepage</title>
    <!-- basic styles -->
    <link href="${ctxRoot}/static/framework/bootstrap/assets/css/bootstrap.css" rel="stylesheet">
    <link rel="stylesheet" href="${ctxRoot}/static/framework/bootstrap/assets/css/font-awesome.css">
    <!-- fonts -->
    <link rel="stylesheet" href="${ctxRoot}/static/framework/bootstrap/assets/css/ace-fonts.css">
    <!-- ace styles -->
    <link rel="stylesheet" href="${ctxRoot}/static/framework/bootstrap/assets/css/ace.css">
    <link rel="stylesheet" href="${ctxRoot}/static/framework/bootstrap/assets/css/ace-rtl.css">
    <link rel="stylesheet" href="${ctxRoot}/static/framework/bootstrap/assets/css/ace-skins.css">

    <link rel="stylesheet" href="${ctxRoot}/static/css/css.css">
    <%--    <script src="${ctxRoot}/static/ztree/js/jquery.ztree.core-3.5.min.js"></script>
        <script src="${ctxRoot}/static/js/tree.js"></script>--%>


    <script src="${ctxRoot}/static/framework/echarts/echarts.js"></script>
    <script src="${ctxRoot}/static/js/ability.js"></script>
    <script type="text/javascript">

       $(function () {
            require.config({
                paths: {
                    echarts: '${ctxRoot}/static/framework/echarts'
                }
            });
            require(
                    [
                        'echarts',
                        'echarts/chart/pie',   // 按需加载所需图表，如需动态类型切换功能，别忘了同时加载相应图表
                    ],
                    function (ec) {
                        var name="体育"; //现在tb_tag表里体育id为27 是最小的，查询是按id正向排序的，如果tb_tag表id变化这里要做相应的调整
                        var url = 'show_tag_chart.do'
                        var data = {tagName: name};
                        X.post(url, data, mapCallback);
                        var data2={};
                        var url2='show_firstTag_chart.do';
                        X.post(url2,data2,mapFirstCallback);
                    }
            );
        });
    </script>
</head>
<body>
<div class="page-content">
    <div class="page-header">
        <h1>
            平台能力展现
        </h1>
    </div>
    <!-- /.page-header -->
    <div class="widget-box">
        <div class="widget-header header-color-blue2">
            <h4 class="lighter smaller">各级标签热度分布图</h4>
        </div>

        <div class="widget-body">
            <div class="widget-main padding-8">
                <div class="mainzuo" style="overflow:auto">
                    <div id="main" style="height:600px;"></div>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
<!-- /.row -->
</div>
<%--<span id="wrong-message"></span>--%>
</body>
</html>