<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/include/taglib.jsp" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>homepage</title>
    <!-- basic styles -->
    <link href="${ctxRoot}/static/framework/bootstrap/assets/css/bootstrap.css" rel="stylesheet">

    <link rel="stylesheet" href="${ctxRoot}/static/framework/bootstrap/assets/css/chosen.css"/>
    <link rel="stylesheet" href="${ctxRoot}/static/framework/bootstrap/assets/css/ace.css">

    <link rel="stylesheet" href="${ctxRoot}/static/css/css.css">

    <%--    <script src="${ctxRoot}/static/ztree/js/jquery.ztree.core-3.5.min.js"></script>
        <script src="${ctxRoot}/static/js/tree.js"></script>--%>

    <link rel="stylesheet" type="text/css" href="${ctxRoot}/static/css/jquery-ui.css"/>
    <style>
        .chosen-container {
            margin-right: 15px;
        }

        .widget-box {
            border: 0px;
        }
    </style>
    <script type="text/javascript" src="${ctxRoot}/static/framework/ui/jquery.ui.core.js"></script>
    <script type="text/javascript" src="${ctxRoot}/static/framework/ui/jquery.ui.widget.js"></script>

    <script src="${ctxRoot}/static/framework/bootstrap/assets/js/chosen.jquery.js"></script>
    <script src="${ctxRoot}/static/framework/echarts/echarts.js"></script>
    <script src="${ctxRoot}/static/js/ability.js"></script>
    <style>
        .ss{
            color: #ff7f50;
            color: #87cefa;
            color: #da70d6;
            color: #32cd32;
            color: #6495ed;
            color: #1e90ff;
            color: #b8860b;
            color: #30e0e0;
            color: #6b8e23;
            color: #ff00ff;
            color: #3cb371;
            color: #ff00ff;
            color: #40e0d0;
            color: #7b68ee;
            color: #00fa9a;
        '#ff7f50', '#87cefa', '#da70d6', '#32cd32', '#6495ed',
        '#ff69b4', '#ba55d3', '#cd5c5c', '#ffa500', '#40e0d0',
        '#1e90ff', '#ff6347', '#7b68ee', '#00fa9a', '#ffd700',
        '#6b8e23', '#ff00ff', '#3cb371', '#b8860b', '#30e0e0'
        }
    </style>
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
                        var name = "文化教育"; //现在tb_tag表里体育id为27 是最小的，查询是按id正向排序的，如果tb_tag表id变化这里要做相应的调整
                        var areaId = $("#form-field-select-1").val();
                        var url = 'show_tag_chart.do'
                        var data = {tagName: name, areaId: areaId};
                        X.post(url, data, pieCallback);
                        var data2 = {areaId: areaId};
                        var url2 = 'show_firstTag_chart.do';
                        X.post(url2, data2, pieFirstCallback);
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
            <h4 class="lighter smaller">标签主题分布图</h4>
        </div>
        <div class="widget-body">
            <div class="widget-main padding-8">
                <div class="col-xs-10 col-xs-offset-1">
                    城市: <select class="width-14 chosen-select" id="form-field-select-1">
                    <c:forEach var="item" items="${areaList}">
                        <option value="${item.id}">${item.name}</option>
                    </c:forEach>
                </select>
                    <button class="btn btn-sm btn-info"
                            style="width: 80px;" onclick="onClick()">查 询
                    </button>
                </div>
            </div>
        </div>
        <div class="col-xs-10 col-xs-offset-1">
            <div id="main" style="width: 100%;height: 500px;border: 1px solid #F2F2F2;margin: 10px auto;"></div>
        </div>

    </div>
</div>
<%--<span id="wrong-message"></span>--%>
</body>
</html>