  var option = {
    backgroundColor: '#1b1b1b',
    color: ['gold','aqua','lime'],
    title : {
        text: '标签分布统计',
        x:'center',
        textStyle : {
            color: '#fff'
        }
    },
    tooltip : {
        trigger: 'item',
        formatter : function (params) {
            var tooltip = params.seriesName + '<br/>' + params.name + ' : ' + params.value
                        + '<br/>' + '占比' + ' : ';
            if(params.data.percent != undefined) {
                tooltip +=  params.data.percent;
            } else {
                tooltip += "-";
            }
            return tooltip ;
        }
    },
    legend: {
        orient: 'vertical',
        x:'left',
        data:[],
        textStyle : {
            color: '#fff'
        }
    }, toolbox: {
          show: true,
          orient: 'horizontal',
          x: 'right',
          y: 'top',
          feature: {
              mark: {show: true},
              dataView: {show: true, readOnly: false},
              restore: {show: true},
              saveAsImage: {show: true}
          }
      },
    dataRange: {
        min: 0,
        max: 10000000,
        x: 'left',
        y: 'bottom',
        text:['高','低'],           // 文本，默认为数值文本
        calculable : true,
        color: [ '#ff7f50', '#87cefa', '#da70d6', '#32cd32', '#6495ed',
            '#ff69b4', '#ba55d3', '#cd5c5c', '#ffa500', '#40e0d0',
            '#1e90ff', '#ff6347', '#7b68ee', '#00fa9a', '#ffd700',
            '#6b8e23', '#ff00ff', '#3cb371', '#b8860b', '#30e0e0' ],
        textStyle:{
            color:'#fff'
        }
    },
    series : [
        {
            name: '',
            type: 'map',
            roam: true,
            hoverable: false,
            mapType: 'china',
            itemStyle:{
                normal:{
                    label : {
                        show: false
                    },
                    borderColor:'rgba(100,149,237,1)',
                    borderWidth:2,
                    areaStyle:{
                        color: '#1b1b1b'
                    }
                }
            },
            data:[],
            markPoint : {
                symbolSize: 10,
                itemStyle: {
                    normal: {
                        borderColor: '#87cefa',
                        borderWidth: 1,
                        label: {
                            show: true
                        }
                    },
                    emphasis: {
                        borderColor: 'white',
                        borderWidth: 1,
                        label: {
                            show: true
                        }
                    }
                },
                data : [
                    {name: '北京',value: 53},
                    {name: '天津',value: 38},
                    {name: '上海',value: 33},
                    {name: '重庆',value: 45},
                    {name: '河北',value: 10},
                    {name: '河南',value: 58},
                    {name: '云南',value: 5},
                    {name: '辽宁',value: 30},
                    {name: '黑龙江',value: 89},
                    {name: '湖南',value: 20},
                    {name: '安徽',value: 47},
                    {name: '山东',value: 80},
                    {name: '新疆',value: 13},
                    {name: '江苏',value: 21},
                    {name: '浙江',value: 16},
                    {name: '江西',value: 80},
                    {name: '湖北',value: 48},
                    {name: '广西',value: 19},
                    {name: '甘肃',value: 57},
                    {name: '山西',value: 87},
                    {name: '内蒙古',value: 37},
                    {name: '陕西',value: 80},
                    {name: '吉林',value: 60},
                    {name: '福建',value: 83},
                    {name: '贵州',value: 69},
                    {name: '广东',value: 62},
                    {name: '青海',value: 80},
                    {name: '西藏',value: 54},
                    {name: '四川',value: 54},
                    {name: '宁夏',value: 4},
                    {name: '海南',value: 29},
                    {name: '台湾',value: 58},
                    {name: '香港',value: 20},
                    {name: '澳门',value: 48}
                ]
            },
            geoCoord: {
                '安徽':[117.17,31.52],
                '北京':[116.24,39.55],
                '重庆':[106.54,29.59],
                '福建':[119.18,26.05],
                '甘肃':[103.51,36.04],
                '广东':[113.14,23.08],
                '广西':[108.19,22.48],
                '贵州':[106.42,26.35],
                '海南':[110.20,20.02],
                '河北':[114.30,38.02],
                '河南':[113.40,34.46],
                '黑龙江':[126.36,45.44],
                '湖北':[114.17,30.35],
                '湖南':[112.59,28.12],
                '吉林':[125.19,43.54],
                '江苏':[118.46,32.03],
                '江西':[115.55,28.40],
                '辽宁':[123.25,41.48],
                '内蒙古':[111.41,40.48],
                '宁夏':[106.16,38.27],
                '青海':[101.48,36.38],
                '山东':[117.00,36.40],
                '山西':[112.33,37.54],
                '陕西':[108.57,34.17],
                '上海':[121.29,31.14],
                '四川':[104.04,30.40],
                '天津':[117.12,39.02],
                '西藏':[91.08,29.39],
                '新疆':[87.36,43.45],
                '云南':[102.42,25.04],
                '浙江':[120.10,30.16],
                '香港':[115.12,21.23],
                '澳门':[115.07,21.33],
                '台湾':[121.30,25.03]
            }
        },
        {
            name: '',
            type: 'map',
            mapType: 'china',
            data: [],
            markPoint: {
                symbol: 'emptyCircle',
                symbolSize: 5,
                effect: {
                    show: true,
                    shadowBlur: 0
                },
                itemStyle: {
                    normal: {
                        label: {
                            show: false
                        }
                    },
                    emphasis: {
                        label: {
                            show: false
                        }
                    }
                },
                data: [
                    {name: '北京', value: 53},
                    {name: '天津', value: 38},
                    {name: '上海', value: 33},
                    {name: '重庆', value: 45},
                    {name: '河北', value: 10},
                    {name: '河南', value: 58},
                    {name: '云南', value: 5},
                    {name: '辽宁', value: 30},
                    {name: '黑龙江',value: 89},
                    {name: '湖南', value: 20},
                    {name: '安徽', value: 47},
                    {name: '山东', value: 80},
                    {name: '新疆', value: 13},
                    {name: '江苏', value: 21},
                    {name: '浙江', value: 16},
                    {name: '江西', value: 80},
                    {name: '湖北', value: 48},
                    {name: '广西', value: 19},
                    {name: '甘肃', value: 57},
                    {name: '山西', value: 87},
                    {name: '内蒙古', value: 37},
                    {name: '陕西', value: 80},
                    {name: '吉林', value: 60},
                    {name: '福建', value: 83},
                    {name: '贵州', value: 69},
                    {name: '广东', value: 62},
                    {name: '青海', value: 80},
                    {name: '西藏', value: 54},
                    {name: '四川', value: 54},
                    {name: '宁夏', value: 4},
                    {name: '海南', value: 29},
                    {name: '台湾', value: 58},
                    {name: '香港', value: 20},
                    {name: '澳门', value: 48}
                ]
            }
        },
    ]
};
$(function() {
    $(".chosen-select").chosen();
});
function onFirstChange() {
    var url='index_second.do'
    var data={id: $("#form-field-select-1").val()};
    X.post(url, data, callback_first)
}

function callback_first(r) {
    var type = r['type'];
    var data = r['data'];
    var success = r['success'];
    if(success){
        //alert(JSON.stringify(r));
        var  first= $("#form-field-select-1").val();
        if("0"==first){
            $("#form-field-select-2").empty();
            $("#form-field-select-3").empty();
            $("#form-field-select-4").empty();
            $("#form-field-select-2").append("<option value=\"0\" selected=\"selected\">待选择上级标签</option>");
            $("#form-field-select-3").append("<option value=\"0\" selected=\"selected\">待选择上级标签</option>");
            $("#form-field-select-4").append("<option value=\"0\" selected=\"selected\">待选择上级标签</option>");
        }else{
            $("#form-field-select-2").empty();
            $("#form-field-select-3").empty();
            $("#form-field-select-4").empty();
            $("#form-field-select-2").append("<option value=\"0\" selected=\"selected\">请选择</option>");
            $("#form-field-select-3").append("<option value=\"0\" selected=\"selected\">待选择上级标签</option>");
            $("#form-field-select-4").append("<option value=\"0\" selected=\"selected\">待选择上级标签</option>");
            for(var i=0;i<data.length;i++){
                var item = data[i];
                var option = "<option value='" + item.id +"'>" + item.name + "</option>";
                $("#form-field-select-2").append(option);
            }
        }
        $("#form-field-select-2").trigger("chosen:updated");
        $("#form-field-select-3").trigger("chosen:updated");
        $("#form-field-select-4").trigger("chosen:updated");
    }
}

function onSecondChange() {//alert($("#form-field-select-1").val())
    var url='index_third.do'
    var data={id: $("#form-field-select-2").val()};
    X.post(url, data, callback_second)
}

function callback_second(r) {
    var type = r['type'];
    var data = r['data'];
    var success = r['success'];
    if(success){
        var  second=$("#form-field-select-2").val();
        if("0"==second){
            //alert(dsds+second);
            $("#form-field-select-3").empty();
            $("#form-field-select-4").empty();
            $("#form-field-select-3").append("<option value=\"0\" selected=\"selected\">待选择上级标签</option>");
            $("#form-field-select-4").append("<option value=\"0\" selected=\"selected\">待选择上级标签</option>");
        }else{
            $("#form-field-select-3").empty();
            $("#form-field-select-3").append("<option value=\"0\" selected=\"selected\">请选择</option>");
            $("#form-field-select-4").append("<option value=\"0\" selected=\"selected\">待选择上级标签</option>");
            for(var i=0;i<data.length;i++){
                var item = data[i];
                var option = "<option value='" + item.id +"'>" + item.name + "</option>";
                $("#form-field-select-3").append(option);
            }
        }
        $("#form-field-select-3").trigger("chosen:updated");
        $("#form-field-select-4").trigger("chosen:updated");
    }
}

//3
function onThrChange() {//alert($("#form-field-select-1").val())
    var url='index_thrs.do'
    var data={id: $("#form-field-select-3").val()};
    X.post(url, data, callback_Thr)
}

function callback_Thr(r) {
    var type = r['type'];
    var data = r['data'];
    var success = r['success'];
    if(success){
        var  second=$("#form-field-select-3").val();
        if("0"==second){
            //alert(dsds+second);
            $("#form-field-select-4").empty();
            $("#form-field-select-4").append("<option value=\"0\" selected=\"selected\">待选择上级标签</option>");
        }else{
            $("#form-field-select-4").empty();
            $("#form-field-select-4").append("<option value=\"0\" selected=\"selected\">请选择</option>");
            for(var i=0;i<data.length;i++){
                var item = data[i];
                var option = "<option value='" + item.id +"'>" + item.name + "</option>";
                $("#form-field-select-4").append(option);
            }
        }
        $("#form-field-select-4").trigger("chosen:updated");
    }
}

function mapCallback(r) {
    //alert(JSON.stringify(r));
    var type = r['type'];
    var data = r['data'];
    var success = r['success'];
    var series = data['series']
    option.series[0].markPoint.data = series[0].data;
    option.series[0].name=series[0].name;
    option.series[1].markPoint.data = series[0].data;
    option.series[1].name=series[0].name;
    option.legend.data = [series[0]['name']];
    require('echarts').init(document.getElementById('map')).setOption(option);
}