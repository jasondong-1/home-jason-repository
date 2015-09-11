var option = {
    title : {
        text: '标签分布图'
    },
    tooltip : {
        trigger: 'axis'
    },
    calculable : true,
    xAxis : [
        {
            type : 'category',
            data : ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月']
        }
    ],
    yAxis : [
        {
            type : 'value'
        }
    ],
    series : [
        {
            name:'num',
            type:'bar',
            data:[2.0, 4.9, 7.0, 23.2, 25.6, 76.7, 135.6, 162.2, 32.6, 20.0, 6.4, 3.3]
        }
    ]
};

function onClick(event, treeId, treeNode) {
    var url = 'show_tag_chart.do'
    var data = {tagId: treeNode.id};
    X.post(url, data, treeClickCallback);
}

function treeClickCallback(r) {
    //alert(JSON.stringify(r));
    var type = r['type'];
    var data = r['data'];
    var success = r['success'];
    option.xAxis = data['xAxis'];
    option.series = data['series'];
    require('echarts').init(document.getElementById('main')).setOption(option);
    //var myChart = ec.init(document.getElementById('main'));
    //myChart.setOption(option);
}