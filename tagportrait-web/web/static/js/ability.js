/**
 * @author yaotianli
 * @mail 18514733097@189.cn
 * created on 2015/09/24 16:30
 */
option = {
    tooltip : {
        trigger: 'item',
        formatter: "{a} <br/>{b} : {c} ({d}%)"
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
            radius : [0,100],
            itemStyle : {
                normal : {
                    label : {
                        position : 'inner',
                        distance: 0.7
                    },
                    labelLine : {
                        show : false
                    }
                }
            },
            data:[
                {value: 335, name: '时尚', selected: true},
                {value: 379, name: '健康'},
                {value: 148, name: '数码资讯'},
                {value: 335, name: '娱乐'},
                {value: 379, name: '体育'},
                {value: 335, name: '出行'}
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
function mapCallback(r) {
    //这个不知道怎么写了
    alert(JSON.stringify(r));
    var type = r['type'];
    var data = r['data'];
    var success = r['success'];
    var series = data['series']
    option.series = series;
    //option.series[0].itemStyle = itemStyle;
    //option.legend.data=[];
    //option.legend.data.push(series[0].name);
    //option.legend.data = [series[0]['name']];
    require('echarts').init(document.getElementById('main')).setOption(option);
}