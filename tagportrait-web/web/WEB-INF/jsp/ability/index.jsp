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
    <script type="text/javascript">
        $(function(){
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
                                x: 'center',
                                text: '兴趣标签热度',
                                subtext: '一级标签热度分布柱状图'
                            },
                            tooltip: {
                                trigger: 'item'
                            },
                            toolbox: {
                                show: true,
                                feature: {
                                    dataView: {show: true, readOnly: false},
                                    restore: {show: true},
                                    saveAsImage: {show: true}
                                }
                            },
                            calculable: true,
                            grid: {
                                borderWidth: 0,
                                y: 80,
                                y2: 60
                            },
                            xAxis: [
                                {
                                    type: 'category',
                                    show: false,
                                    data: ['工具/软件', '娱乐', '电商购物', '资讯', '社交/沟通', '房产', '生活服务', '汽车', '健康', '时尚', '财经']
                                }
                            ],
                            yAxis: [
                                {
                                    type: 'value',
                                    show: false
                                }
                            ],
                            series: [
                                {
                                    name: '兴趣标签热度',
                                    type: 'bar',
                                    itemStyle: {
                                        normal: {
                                            color: function(params) {
                                                // build a color map as your need.
                                                var colorList = [
                                                    '#C1232B','#B5C334','#FCCE10','#E87C25','#27727B',
                                                    '#FE8463','#9BCA63','#FAD860','#F3A43B','#60C0DD',
                                                    '#D7504B','#C6E579','#F4E001','#F0805A','#26C0C0'
                                                ];
                                                return colorList[params.dataIndex]
                                            },
                                            label: {
                                                show: true,
                                                position: 'top',
                                                formatter: '{b}\n{c}'
                                            }
                                        }
                                    },
                                    data: [3,31,29,4,12,5,6,5,5,23,7]

                                }
                            ]
                        };

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
    </div><!-- /.page-header -->

    <div class="col-xs-12">
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



</div><!-- /.row -->
</div>
</body>
</html>