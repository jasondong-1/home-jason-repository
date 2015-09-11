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
    <style>
        .chosen-container {
            margin-right: 15px;
        }

        .widget-box {
            border: 0px;
        }
    </style>
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
                                                        data-placeholder="Choose a Country..." onchange="onChange()">
                                                    <option value="">请选择</option>
                                                    <c:forEach var="item" items="${tagFirstList}">
                                                        <option value="${item.id}">${item.name}</option>
                                                    </c:forEach>
                                                </select>
                                                <select class="width-15 chosen-select" id="form-field-select-2"
                                                        data-placeholder="Choose a Country...">
                                                    <option value="二级标签">请选择</option>
                                                </select>
                                                <select class="width-15 chosen-select" id="form-field-select-3"
                                                        data-placeholder="Choose a Country...">
                                                    <%--<option value="">&nbsp;</option>--%>
                                                    <option value="">请选择</option>
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
<div>
    sjkdhskeassk
</div>
<script src="${ctxRoot}/static/framework/bootstrap/assets/js/chosen.jquery.js"></script>
<script src="${ctxRoot}/static/framework/echarts/echarts.js"></script>
<script src="${ctxRoot}/static/framework/bootstrap/assets/js/jquery.dataTables.js"></script>
<script src="${ctxRoot}/static/framework/bootstrap/assets/js/jquery.dataTables.bootstrap.js"></script>

<script type="text/javascript">
    jQuery(function ($) {
//        $(".chosen-select").chosen();
//        $('#chosen-multiple-style').on('click', function (e) {
//            var target = $(e.target).find('input[type=radio]');
//            var which = parseInt(target.val());
//            if (which == 2) $('#form-field-select-4').addClass('tag-input-style');
//            else $('#form-field-select-4').removeClass('tag-input-style');
//        });
    });
    function onChange() {//alert($("#form-field-select-1").val())
        var url='index_second.do'
        var data={id: $("#form-field-select-1").val()};
        X.post(url, data, callback)
    }

    function callback(r) {
        var type = r['type'];
        var data = r['data'];
        var success = r['success'];
        //require('echarts').init(document.getElementById('form-field-select-2')).setOption(option);
        if(success){
            alert(JSON.stringify(r));
            //$("#form-field-select-2").options.length=1;
            for(var i=0;i<data.length;i++){
                var item = data[i];
                var option = "<option value='" + item.id +"'>" + item.name + "</option>";
                $("#form-field-select-2").append(option);

                //jQuery("<option></option>").attr({val:data.id,text:data.name}).appendTo("#form-field-select-2");
            }
            $("#form-field-select-2").chosen();
        }
    }
</script>

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

<script type="text/javascript">
    jQuery(function ($) {
        var oTable1 = $('#sample-table-2').dataTable({
            "aoColumns": [
                {"bSortable": false},
                null, null, null, null, null,
                {"bSortable": false}
            ]
        });


        $('table th input:checkbox').on('click', function () {
            var that = this;
            $(this).closest('table').find('tr > td:first-child input:checkbox')
                    .each(function () {
                        this.checked = that.checked;
                        $(this).closest('tr').toggleClass('selected');
                    });

        });


        $('[data-rel="tooltip"]').tooltip({placement: tooltip_placement});
        function tooltip_placement(context, source) {
            var $source = $(source);
            var $parent = $source.closest('table')
            var off1 = $parent.offset();
            var w1 = $parent.width();

            var off2 = $source.offset();
            var w2 = $source.width();

            if (parseInt(off2.left) < parseInt(off1.left) + parseInt(w1 / 2)) return 'right';
            return 'left';
        }
    })
</script>
</body>
</html>

