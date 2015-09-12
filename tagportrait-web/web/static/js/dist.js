var option = {
    title : {
        text: '标签分布统计',
        x:'center'
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
        data:[]
    },
    dataRange: {
        min: 0,
        max: 5000,
        x: 'left',
        y: 'bottom',
        text:['高','低'],           // 文本，默认为数值文本
        calculable : true
    },
    series : [
        {
            name: 'iphone3',
            type: 'map',
            mapType: 'china',
            roam: false,
            itemStyle:{
                normal:{label:{show:true}},
                emphasis:{label:{show:true}}
            },
            data:[
                {name: '北京',value: Math.round(Math.random()*1000)},
                {name: '天津',value: Math.round(Math.random()*1000)},
                {name: '上海',value: Math.round(Math.random()*1000)},
                {name: '重庆',value: Math.round(Math.random()*1000)},
                {name: '河北',value: Math.round(Math.random()*1000)},
                {name: '河南',value: Math.round(Math.random()*1000)},
                {name: '云南',value: Math.round(Math.random()*1000)},
                {name: '辽宁',value: Math.round(Math.random()*1000)},
                {name: '黑龙江',value: Math.round(Math.random()*1000)},
                {name: '湖南',value: Math.round(Math.random()*1000)},
                {name: '安徽',value: Math.round(Math.random()*1000)},
                {name: '山东',value: Math.round(Math.random()*1000)},
                {name: '新疆',value: Math.round(Math.random()*1000)},
                {name: '江苏',value: Math.round(Math.random()*1000)},
                {name: '浙江',value: Math.round(Math.random()*1000)},
                {name: '江西',value: Math.round(Math.random()*1000)},
                {name: '湖北',value: Math.round(Math.random()*1000)},
                {name: '广西',value: Math.round(Math.random()*1000)},
                {name: '甘肃',value: Math.round(Math.random()*1000)},
                {name: '山西',value: Math.round(Math.random()*1000)},
                {name: '内蒙古',value: Math.round(Math.random()*1000)},
                {name: '陕西',value: Math.round(Math.random()*1000)},
                {name: '吉林',value: Math.round(Math.random()*1000)},
                {name: '福建',value: Math.round(Math.random()*1000)},
                {name: '贵州',value: Math.round(Math.random()*1000)},
                {name: '广东',value: Math.round(Math.random()*1000)},
                {name: '青海',value: Math.round(Math.random()*1000)},
                {name: '西藏',value: Math.round(Math.random()*1000)},
                {name: '四川',value: Math.round(Math.random()*1000)},
                {name: '宁夏',value: Math.round(Math.random()*1000)},
                {name: '海南',value: Math.round(Math.random()*1000)},
                {name: '台湾',value: Math.round(Math.random()*1000)},
                {name: '香港',value: Math.round(Math.random()*1000)},
                {name: '澳门',value: Math.round(Math.random()*1000)}
            ]
        }
    ]
};

var itemStyle = {
    normal:{label:{show:true}},
    emphasis:{label:{show:true}}
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
        if("无"==first){
            $("#form-field-select-2").empty();
            $("#form-field-select-2").append("<option value=\"无\" selected=\"selected\">无内容</option>");
            $("#form-field-select-3").empty();
            $("#form-field-select-3").append("<option value=\"无\" selected=\"selected\">无内容</option>");
        }else{
            $("#form-field-select-2").empty();
            $("#form-field-select-2").append("<option value=\"无\" selected=\"selected\">请选择</option>");
            for(var i=0;i<data.length;i++){
                var item = data[i];
                var option = "<option value='" + item.id +"'>" + item.name + "</option>";
                $("#form-field-select-2").append(option);
            }
        }
        $("#form-field-select-2").trigger("chosen:updated");
        $("#form-field-select-3").trigger("chosen:updated");
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
        alert(second);
        if("无"==second){
            //alert(dsds+second);
            $("#form-field-select-3").empty();
            $("#form-field-select-3").append("<option value=\"无\" selected=\"selected\">无内容</option>");
        }else{
            $("#form-field-select-3").empty();
            $("#form-field-select-3").append("<option value=\"无\" selected=\"selected\">请选择</option>");
            for(var i=0;i<data.length;i++){
                var item = data[i];
                var option = "<option value='" + item.id +"'>" + item.name + "</option>";
                $("#form-field-select-3").append(option);
            }
        }
        $("#form-field-select-3").trigger("chosen:updated");
    }
}

function mapCallback(r) {
    //alert(JSON.stringify(r));
    var type = r['type'];
    var data = r['data'];
    var success = r['success'];
    var series = data['series']
    option.series = series;
    option.series[0].itemStyle = itemStyle;
    option.legend.data=[];
    option.legend.data.push(series[0].name);
    //alert(JSON.stringify(option));
    //option.legend.data = [series[0]['name']];
    require('echarts').init(document.getElementById('map')).setOption(option);
}