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
                        var myChart = ec.init(document.getElementById('main'));
                        var ecConfig = require('echarts/config');
                        myChart.on(ecConfig.EVENT.PIE_SELECTED, function (param){
//                            alert(JSON.stringify(param));
                            var selected = param.selected;
                            var serie;
                            var str = '当前选择： ';
                            for (var idx in selected) {
                                serie = option.series[idx];
                                for (var i = 0, l = serie.data.length; i < l; i++) {
                                    if (selected[idx][i]) {
                                        if('0'==idx){
                                            var name=serie.data[i].name
                                            alert(name);
                                            var url = 'show_tag_chart.do'
                                            var data = {tagName: name};
                                            X.post(url, data, mapCallback);

                                        }
                                    }
                                }
                            }
                        })
                        myChart.setOption(option);
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
                    <div id="main" style="height:400px;"></div>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
<!-- /.row -->
</div>
<span id="wrong-message"></span>
</body>
</html>