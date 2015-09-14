/**
 * Created by yaotianli on 2015/9/11.
 */
var option = {
    title : {
        text: '热度值分布图',
        subtext: '默认数据为“教育文化”'
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
            name:'热度值',
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
//select
$(function () {
    $(".chosen-select").chosen();
});
function onFirstChange() {
    var url = 'index_second.do'
    var data = {id: $("#form-field-select-1").val()};
    X.post(url, data, callback_first)
}

function callback_first(r) {
    var type = r['type'];
    var data = r['data'];
    var success = r['success'];
    if (success) {
        //alert(JSON.stringify(r));
        var first = $("#form-field-select-1").val();
        if (0== first) {
            $("#form-field-select-2").empty();
            $("#form-field-select-2").append("<option value=\"0\" selected=\"selected\">无内容</option>");
            $("#form-field-select-3").empty();
            $("#form-field-select-3").append("<option value=\"0\" selected=\"selected\">无内容</option>");
        } else {
            $("#form-field-select-2").empty();
            $("#form-field-select-2").append("<option value=\"0\" selected=\"selected\">请选择</option>");
            for (var i = 0; i < data.length; i++) {
                var item = data[i];
                var option = "<option value='" + item.id + "'>" + item.name + "</option>";
                $("#form-field-select-2").append(option);
            }
        }
        $("#form-field-select-2").trigger("chosen:updated");
        $("#form-field-select-3").trigger("chosen:updated");
    }
}

function onSecondChange() {//alert($("#form-field-select-1").val())
    var url = 'index_third.do'
    var data = {id: $("#form-field-select-2").val()};
    X.post(url, data, callback_second)
}
function callback_second(r) {
    var type = r['type'];
    var data = r['data'];
    var success = r['success'];
    if (success) {
        var second = $("#form-field-select-2").val();
        if (0 == second) {
            $("#form-field-select-3").empty();
            $("#form-field-select-3").append("<option value=\"0\" selected=\"selected\">无内容</option>");
        } else {
            $("#form-field-select-3").empty();
            $("#form-field-select-3").append("<option value=\"0\" selected=\"selected\">请选择</option>");
            for (var i = 0; i < data.length; i++) {
                var item = data[i];
                var option = "<option value='" + item.id + "'>" + item.name + "</option>";
                $("#form-field-select-3").append(option);
            }
        }
        $("#form-field-select-3").trigger("chosen:updated");
    }
}
//查询按钮点击事件
function onClick() {
    var city=getSelectedCity();
    var id1=$("#form-field-select-1").val();
    var id2=$("#form-field-select-2").val();
    var id3=$("#form-field-select-3").val();
    //alert(city);

    var url2= 'show_tag_chart_city.do';
    var url1 = 'index_heartValueAndCity.do'


    if(0==$("#form-field-select-2").val()&&0==$("#form-field-select-3").val()){
       var id=id1;
    }else if(0==$("#form-field-select-3").val()){
       var id=id2;
    }else{
       var id=id3;
    }
    var data={id:id,city:city};
    X.post(url1, data, callback_firstTable);
    X.post(url2, data, heatValueCallback);

}
function getSelectedCity() {
    var city = $("#cmbCity").multiselect("getChecked").map(function () {
        return this.value;
    }).get();
    return city.toString();
}
function heatValueCallback(r) {
    //alert(JSON.stringify(r));
    var type = r['type'];
    var data = r['data'];
    var success = r['success'];
    option.xAxis = data['xAxis'];
    option.series = data['series'];
    option.series[0].itemStyle = itemStyle;
    require('echarts').init(document.getElementById('main')).setOption(option);
}
function callback_firstTable(r) {
    var type = r['type'];
    var data = r['data'];
    var success = r['success'];
    if (success) {
       // alert(JSON.stringify(r));
        $("#table_body").empty();
        for (var i = 0; i < data.length; i++) {
            var item = data[i];
            var t=i+1;
            var tr = "<tr><td>"+t+"</td><td>"+item[1]+"</td><td>"+item[2]+"</td></tr>";
            $("#table_body").append(tr);
        }
    }
}
