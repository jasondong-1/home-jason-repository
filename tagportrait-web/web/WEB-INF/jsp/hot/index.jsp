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
    <link rel="stylesheet" type="text/css" href="${ctxRoot}/static/css/jquery-ui.css" />
    <script type="text/javascript" src="${ctxRoot}/static/framework/ui/jquery.ui.core.js"></script>
    <script type="text/javascript" src="${ctxRoot}/static/framework/ui/jquery.ui.widget.js"></script>
    <!-- jquery  multiselect-->
    <link rel="stylesheet" type="text/css" href="${ctxRoot}/static/framework/multiselect/jquery.multiselect.css" />
    <script type="text/javascript" src="${ctxRoot}/static/framework/multiselect/jquery.multiselect.js"></script>
    <style>
        .chosen-container {
            margin-right: 15px;
        }

        .widget-box {
            border: 0px;
        }
    </style>
    <script type="text/javascript">
        $(function(){
            $("#cmbYear").multiselect({
                noneSelectedText: "2015",
                checkAllText: '全选',
                uncheckAllText: '全不选',
                selectedList:4,
                minWidth:170
            });
        });

        function getSelectedYear(){
            var year = $("#cmbYear").multiselect("getChecked").map(function(){
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
                                                <select class="width-15 chosen-select" id="form-field-select-1"
                                                        data-placeholder="Choose a Country..." onchange="onFirstChange()">
                                                    <option value="无">请选择</option>
                                                    <c:forEach var="item" items="${tagFirstList}">
                                                        <option value="${item.id}">${item.name}</option>
                                                    </c:forEach>
                                                </select>
                                                <select class="width-15 chosen-select" id="form-field-select-2"
                                                        data-placeholder="Choose a Country..." onchange="onSecondChange()">
                                                    <option value="无">无内容</option>
                                                </select>
                                                <select class="width-15 chosen-select" id="form-field-select-3"
                                                        data-placeholder="Choose a Country...">
                                                    <%--<option value="">&nbsp;</option>--%>
                                                    <option value="无">无内容</option>
                                                </select>
                                                年 <select name="" size="6" class="select_s" multiple="multiple" id="cmbYear">
                                                        <option value="2015">2015</option>
                                                        <option value="2014">2014</option>
                                                        <option value="2013">2013</option>
                                                        <option value="2012">2012</option>
                                                        <option value="2011">2011</option>
                                                        <option value="2010">2010</option>
                                                    </select>
                                                <button class="btn btn-sm btn-info"
                                                        style="width: 80px;float: right">查 询
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

                                        <tbody>
                                        <tr>
                                            <td>1</td>
                                            <td>北京</td>
                                            <td>10.0</td>
                                        </tr>
                                        <tr>
                                            <td>2</td>
                                            <td>上海</td>
                                            <td>9.0</td>
                                        </tr>
                                        <tr>
                                            <td>3</td>
                                            <td>深圳</td>
                                            <td>8.0</td>
                                        </tr>
                                        <tr>
                                            <td>4</td>
                                            <td>广州</td>
                                            <td>7.0</td>
                                        </tr>
                                        <tr>
                                            <td>5</td>
                                            <td>南京</td>
                                            <td>6.0</td>
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
<script src="${ctxRoot}/static/framework/echarts/echarts.js"></script>
<script src="${ctxRoot}/static/framework/bootstrap/assets/js/jquery.dataTables.js"></script>
<script src="${ctxRoot}/static/framework/bootstrap/assets/js/jquery.dataTables.bootstrap.js"></script>
<script src="${ctxRoot}/static/js/hot.js"></script>

<script type="text/javascript">
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
                var myChart = ec.init(document.getElementById('main'));
                option = {
                    title: {
                        text: '热度值排行',
                        subtext: '纯属虚构'
                    },
                    tooltip: {
                        trigger: 'axis'
                    },
                    toolbox: {
                        show: true,
                        feature: {
                            mark: {show: true},
                            dataView: {show: true, readOnly: false},
                            magicType: {show: true, type: ['line', 'bar']},
                            restore: {show: true},
                            saveAsImage: {show: true}
                        }
                    },
                    calculable: true,
                    xAxis: [
                        {
                            type: 'category',
                            data: ['北京', '广州', '上海', '重庆', '杭州']
                        }
                    ],
                    yAxis: [
                        {
                            type: 'value'
                        }
                    ],
                    series: [
                        {
                            name: '热度值',
                            type: 'bar',
                            data: [10.0, 8.0, 9.0, 7.0, 6.0],
                            markPoint: {
                                data: [
                                    {type: 'max', name: '最大值'},
                                    {type: 'min', name: '最小值'}
                                ]
                            },
                            markLine: {
                                data: [
                                    {type: 'average', name: '平均值'}
                                ]
                            }
                        },
                    ]
                };

                var ecConfig = require('echarts/config');
                myChart.on(ecConfig.EVENT.PIE_SELECTED, function (param) {
                    var selected = param.selected;
                    var serie;
                    var str = '当前选择： ';
                    for (var idx in selected) {
                        serie = option.series[idx];
                        for (var i = 0, l = serie.data.length; i < l; i++) {
                            if (selected[idx][i]) {
                                str += '【系列' + idx + '】' + serie.name + ' : ' +
                                        '【数据' + i + '】' + serie.data[i].name + ' ';
                            }
                        }
                    }
                    document.getElementById('wrong-message').innerHTML = str;
                })
                myChart.setOption(option);
            }
    );
</script>
</body>
</html>

