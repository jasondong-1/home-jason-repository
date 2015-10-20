/**
 * @author yaotianli
 * @mail 18514733097@189.cn
 * created on 2015/09/24 16:30
 */

//现在option内的data值只是占位用的没有实际意义
option = {
    backgroundColor: '#F2F2F2',
    tooltip: {
        trigger: 'item',
        formatter: "{a} <br/>{b} : {c} ({d}%)"
    },
    toolbox: {
        show: true,
        feature: {
            dataView: {show: true, readOnly: false},
            restore: {show: true},
            saveAsImage: {show: true},
        }
    },
    calculable: false,
    series: [
        {
            name: '一级标签',
            type: 'pie',
            selectedMode: 'single',
            radius: [0, 100],
            itemStyle: {
                normal: {
                    label: {
                        position: 'inner',
                        distance: 0.7
                    },
                    labelLine: {
                        show: false,
                    }
                }
            },
            data: [
                {value: 35, name: '时尚', selected: true},
                {value: 79, name: '健康'},
                {value: 148, name: '数码资讯'},
                {value: 335, name: '娱乐'},
                {value: 379, name: '体育'},
                {value: 335, name: '出行'}
            ]
        },
        {
            name: '二级标签',
            type: 'pie',
            radius: [120, 180],
            data: [
                {value: 335, name: '直达'},
                {value: 310, name: '邮件营销'},
                {value: 234, name: '联盟广告'},
                {value: 135, name: '视频广告'},
                {value: 1048, name: '百度'},
                {value: 251, name: '谷歌'},
                {value: 147, name: '必应'},
                {value: 102, name: '其他'}
            ]
        }
    ]
};
var itemStyle = {
    normal: {
        label: {
            position: 'outer',
            distance: 0.7,
            textStyle: {
                color: '#ffffff'
            }
        },
        labelLine: {
            show: true
        }
    }
};
$(function () {
    $(".chosen-select").chosen();
});
//更新外环圆的数据
function pieCallback(r) {
    var type = r['type'];
    var data = r['data'];
    var success = r['success'];
    var series = data['series'];
    option.series[1].data = series[0].data;
    require('echarts').init(document.getElementById('main')).setOption(option);
    flushDiagram();
}
//加载内环圆的数据
function pieFirstCallback(r) {
    //alert(JSON.stringify(r));
    var type = r['type'];
    var data = r['data'];
    var success = r['success'];
    var series = data['series'];
    option.series[0].itemStyle = itemStyle;
    option.series[0].data = series[0].data;
    option.series[0].data[0].selected = true;
    require('echarts').init(document.getElementById('main')).setOption(option);
    flushDiagram();
}
//查询按钮点击事件
function onClick() {
    var areaId = $("#form-field-select-1").val();
    var url1 = 'show_firstTag_chart.do';
    var data1 = {areaId: areaId};
    X.post(url1, data1, pieFirstCallback);

    var name = "体育"; //现在tb_tag表里体育id为27 是最小的，查询是按id正向排序的，如果tb_tag表id变化这里要做相应的调整
    var areaId = $("#form-field-select-1").val();
    var url2 = 'show_tag_chart.do'
    var data2 = {tagName: name, areaId: areaId};
    X.post(url2, data2, pieCallback);
}
function flushDiagram() {
    var myChart = require('echarts').init(document.getElementById('main'));
    var ecConfig = require('echarts/config');
    myChart.on(ecConfig.EVENT.PIE_SELECTED, function (param) {
        var selected = param.selected;
        var serie;
        for (var idx in selected) {
            serie = option.series[idx];
            for (var i = 0, l = serie.data.length; i < l; i++) {
                if (selected[idx][i]) {
                    if ('0' == idx) {
                        //遍历里面的圆，先把选中取消
                        $.each(serie.data, function (i, item) {
                            item.selected = false;
                        })
                        var name = serie.data[i].name;
                        //选中当前点击的项
                        option.series[0].data[i].selected = true;
                        var url = 'show_tag_chart.do';
                        var areaId = $("#form-field-select-1").val();
                        var data = {tagName: name, areaId: areaId};
                        X.post(url, data, pieCallback);
                    }
                }
            }
        }
    })
    myChart.setOption(option);
}

