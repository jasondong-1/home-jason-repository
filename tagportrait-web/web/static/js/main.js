var option = {
    title : {
        text: '标签分布图'
    },
    tooltip : {
        trigger: 'axis'
    },
    calculable : true,
    grid: {
        borderWidth: 0,
        y: 80,
        y2: 60
    },
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

var itemStyle = {
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
};

function onClick(event, treeId, treeNode) {
    var url = 'show_tag_chart_top.do';
    var areaId = $("#form-field-select-1").val();
    var data = {tagId: treeNode.id, areaId: areaId};
    X.post(url, data, treeClickCallback);

    var url2='query_tag_description.do';
    X.post(url2, data, descriptionCallback);
}

function treeClickCallback(r) {
    alert(JSON.stringify(r));
    var type = r['type'];
    var data = r['data'];
    var success = r['success'];
    option.xAxis = data['xAxis'];
    option.series = data['series'];
    option.series[0].itemStyle = itemStyle;
    require('echarts').init(document.getElementById('main')).setOption(option);
}

function descriptionCallback(r) {
    //alert(JSON.stringify(r));
    var type = r['type'];
    var data = r['data'];
    var success = r['success'];
    $('#tag-description').val(data['description']);
    $('#current-tag-name').html(data['name']);
    $('#current-tag-id').val(data['id']);
    //alert(document.getElementById('tag-description').val());
}