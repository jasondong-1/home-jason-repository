<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2015/9/10
  Time: 14:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/include/taglib.jsp" %>
<html>
<head>
    <meta charset="utf-8"/>
    <title>text</title>
    <meta name="description" content="Common form elements and layouts"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <!--[if IE 7]>
    <link rel="stylesheet" href="${ctxRoot}/static/chosen/font-awesome-ie7.min.css"/>
    <![endif]-->
    <link rel="stylesheet" href="${ctxRoot}/static/framework/bootstrap/assets/css/chosen.css"/>
    <link rel="stylesheet" href="${ctxRoot}/static/framework/bootstrap/assets/css/ace.css"/>
    <!--[if lte IE 8]>
    <link rel="stylesheet" href="${ctxRoot}/static/framework/bootstrap/assets/css/ace-ie.css"/>
    <![endif]-->
    <link rel="stylesheet" type="text/css" href="${ctxRoot}/static/css/jquery-ui.css"/>
    <script type="text/javascript" src="${ctxRoot}/static/framework/ui/jquery.ui.core.js"></script>
    <script type="text/javascript" src="${ctxRoot}/static/framework/ui/jquery.ui.widget.js"></script>
    <!-- jquery  multiselect-->
    <link rel="stylesheet" type="text/css" href="${ctxRoot}/static/framework/multiselect/jquery.multiselect.css"/>
    <script type="text/javascript" src="${ctxRoot}/static/framework/multiselect/jquery.multiselect.js"></script>
    <style>
        .chosen-container {
            margin-right: 15px;
        }

        .widget-box {
            border: 0px;
        }

        .ui-multiselect {
            height: 32px;
            border-radius: 0px;
            background: #FAFAFA;
            border: 1px solid #AAAAAA;
        }
    </style>
    <script type="text/javascript">
        $(function () {
            $("#cmbCity").multiselect({
                noneSelectedText: "北京",
                checkAllText: '全选',
                uncheckAllText: '全不选',
                selectedList: 4,
                minWidth: 136
            });
        });

        function getSelectedYear() {
            var year = $("#cmbCity").multiselect("getChecked").map(function () {
                return this.value;
            }).get();
            return year.toString();
        }
    </script>
</head>
<body>
<div class="main-container" id="main-container">
    <script type="text/javascript">
        try {
            ace.settings.check('main-container', 'fixed')
        } catch (e) {
        }
    </script>
    <div class="main-container-inner">
        <div class="main-content">
            <div class="page-content">
                <div class="page-header">
                    <h1>
                        标签热度排行
                    </h1>
                </div>
                <!-- /.page-header -->
                <div class="row">
                    <div class="col-xs-12">

                        <%--<form class="form-horizontal" role="form">--%>
                        <div class="row">
                            <div class="col-xs-10 col-xs-offset-1 ">
                                <div class="widget-box">
                                    <div class="widget-body">
                                        <div class="widget-main">
                                            <div>
                                                一级标签：
                                                <select class="width-10 chosen-select" id="form-field-select-1"
                                                        data-placeholder="请选择"
                                                        onchange="onFirstChange()">
                                                    <option value="0">无内容</option>
                                                    <c:forEach var="item" items="${tagFirstList}">
                                                        <option value="${item.id}">${item.name}</option>
                                                    </c:forEach>
                                                </select>
                                                二级标签：
                                                <select class="width-10 chosen-select" id="form-field-select-2"
                                                        data-placeholder="请选择"
                                                        onchange="onSecondChange()">
                                                    <option value="0">无内容</option>
                                                </select>
                                                三级标签：
                                                <select class="width-10 chosen-select" id="form-field-select-3"
                                                        data-placeholder="请选择">
                                                    <%--<option value="">&nbsp;</option>--%>
                                                    <option value="0">无内容</option>
                                                </select>
                                                城市: <select name="" size="6" class="select_s" multiple="multiple"
                                                            id="cmbCity">
                                                <c:forEach var="item" items="${areaList}">
                                                    <option value="${item.id}">${item.name}</option>
                                                </c:forEach>
                                            </select>
                                                <button class="btn btn-sm btn-info"
                                                        style="width: 80px;float: right" onclick="onClick()">查 询
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-10 col-xs-offset-1">
                                <div id="main"
                                     style="width: 100%;height: 500px;border: 1px solid #d0d0d0;margin: 0px auto"></div>
                            </div>
                            <div class="col-xs-10 col-xs-offset-1">
                                <h3 class="header smaller lighter blue"></h3>

                                <div class="table-header">
                                    热度排行
                                </div>
                                <div class="table-responsive">
                                    <table id="sample-table-2" class="table table-striped table-bordered table-hover">
                                        <thead>
                                        <tr>
                                            <th>排名</th>
                                            <th>城市</th>
                                            <th class="hidden-480">热度值</th>
                                        </tr>
                                        </thead>
                                        <tbody id="table_body">
                                        <tr>
                                            <td>没有内容</td>
                                            <td>没有内容</td>
                                            <td>没有内容</td>
                                        </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                        <%--</form>--%>
                    </div>
                </div>

            </div>
        </div>
    </div>
</div>
<script src="${ctxRoot}/static/framework/bootstrap/assets/js/chosen.jquery.js"></script>
<%--<script src="${ctxRoot}/static/framework/bootstrap/assets/js/jquery.dataTables.js"></script>--%>
<%--<script src="${ctxRoot}/static/framework/bootstrap/assets/js/jquery.dataTables.bootstrap.js"></script>--%>
<script src="${ctxRoot}/static/js/hot.js"></script>
<script src="${ctxRoot}/static/framework/echarts/echarts.js"></script>
<script>
    $(function() {
        require.config({
            paths: {
                echarts: '${ctxRoot}/static/framework/echarts'
            }
        });
        require(
                [
                    'echarts',
                    'echarts/chart/line',   // 按需加载所需图表，如需动态类型切换功能，别忘了同时加载相应图表
                    'echarts/chart/bar'
                ],
                function (ec) {
                    var url = 'show_tag_chart.do'
                    var data = {id: 1};
                    X.post(url, data, heatValueCallback);
                    var url2= 'index_heartValue.do'
                    var data2 = {id: 1,name:"文化教育"};
                    X.post(url2, data2, callback_firstTable);
                }
        );
    })
</script>
</body>
</html>

