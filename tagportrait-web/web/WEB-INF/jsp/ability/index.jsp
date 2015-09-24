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

                        option = {
                            tooltip : {
                                trigger: 'item',
                                formatter: "{a} <br/>{b} : {c} ({d}%)"
                            },
                            legend: {
                                orient : 'vertical',
                                x : 'left',
                                data:['直达','邮件营销','联盟广告','视频广告','百度','谷歌','必应','其他']
                            },
                            toolbox: {
                                show : true,
                                feature : {
                                    dataView : {show: true, readOnly: false},
                                    restore : {show: true},
                                    saveAsImage : {show: true}
                                }
                            },
                            calculable : false,
                            series : [
                                {
                                    name:'访问来源',
                                    type:'pie',
                                    selectedMode: 'single',
                                    radius : [0, 100],
                                    itemStyle : {
                                        normal : {
                                            label :
                                                {
                                                    position : 'inner',
                                                    distance:0.75
                                                }
                                            ,
                                            labelLine : {
                                                show : false
                                            }
                                        }
                                    },
                                    data:[
                                        {value:335, name:'时尚', selected:true},
                                        {value:379, name:'健康'},
                                        {value:148, name:'数码资讯'},
                                        {value:335, name:'娱乐'},
                                        {value:379, name:'体育'},
                                        {value:335, name:'出行'}
                                    ]
                                },
                                {
                                    name:'访问来源',
                                    type:'pie',
                                    radius : [120, 160],
                                    data:[
                                        {value:335, name:'直达'},
                                        {value:310, name:'邮件营销'},
                                        {value:234, name:'联盟广告'},
                                        {value:135, name:'视频广告'},
                                        {value:1048, name:'百度'},
                                        {value:251, name:'谷歌'},
                                        {value:147, name:'必应'},
                                        {value:102, name:'其他'}
                                    ]
                                }
                            ]
                        };
                        var ecConfig = require('echarts/config');
                        myChart.on(ecConfig.EVENT.PIE_SELECTED, function (param){
                            alert(JSON.stringify(param));
                            var selected = param.selected;
                            var serie;
                            var str = '当前选择： ';
                            for (var idx in selected) {
                                serie = option.series[idx];
                                for (var i = 0, l = serie.data.length; i < l; i++) {
                                    if (selected[idx][i]) {
                                        if('0'==idx){
                                            //把data[i].name 的值传给 查询二级表签的方法
                                            str += '【系列' + idx + '】' + serie.name + ' : ' +
                                                    '【数据' + i + '】' + serie.data[i].name + ' ';
                                        }
                                    }
                                }
                            }
//                            alert(str);
                            document.getElementById('wrong-message').innerHTML = str;
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