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
  <script src="${ctxRoot}/static/ztree/js/jquery.ztree.core-3.5.min.js"></script>
  <script src="${ctxRoot}/static/js/tree.js"></script>

  <script src="${ctxRoot}/static/framework/echarts/echarts.js"></script>
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
          var option = {
            title : {
              text: '某地区蒸发量和降水量',
              subtext: '纯属虚构'
            },
            tooltip : {
              trigger: 'axis'
            },
            legend: {
              data:['蒸发量','降水量']
            },
            toolbox: {
              show : true,
              feature : {
                mark : {show: true},
                dataView : {show: true, readOnly: false},
                magicType : {show: true, type: ['line', 'bar']},
                restore : {show: true},
                saveAsImage : {show: true}
              }
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
                name:'蒸发量',
                type:'bar',
                data:[2.0, 4.9, 7.0, 23.2, 25.6, 76.7, 135.6, 162.2, 32.6, 20.0, 6.4, 3.3],
                markPoint : {
                  data : [
                    {type : 'max', name: '最大值'},
                    {type : 'min', name: '最小值'}
                  ]
                },
                markLine : {
                  data : [
                    {type : 'average', name: '平均值'}
                  ]
                }
              },
              {
                name:'降水量',
                type:'bar',
                data:[2.6, 5.9, 9.0, 26.4, 28.7, 70.7, 175.6, 182.2, 48.7, 18.8, 6.0, 2.3],
                markPoint : {
                  data : [
                    {name : '年最高', value : 182.2, xAxis: 7, yAxis: 183, symbolSize:18},
                    {name : '年最低', value : 2.3, xAxis: 11, yAxis: 3}
                  ]
                },
                markLine : {
                  data : [
                    {type : 'average', name : '平均值'}
                  ]
                }
              }
            ]
          };
          myChart.setOption(option);
        }
    );
  </script>
</head>
<body>
<div class="page-content">
  <div class="page-header">
    <h1>
      标签体系页面
    </h1>
  </div><!-- /.page-header -->

  <div class="row">
    <div class="col-xs-12">
      <!-- PAGE CONTENT BEGINS -->

      <div class="row">
        <div class="col-sm-6">
          <div class="widget-box">
            <div class="widget-header header-color-blue2">
              <h4 class="lighter smaller">Choose Categories</h4>
            </div>

            <div class="widget-body">
              <div class="widget-main padding-8">
                <div class="mainzuo" style="overflow:auto">
                  <h5>查询条件</h5>
                  <ul id="tagTree" class="ztree"></ul>
                </div>
                <!--
                <div id="tree1" class="tree tree-selectable"><div class="tree-folder" style="display:none;">				<div class="tree-folder-header">					<i class="icon-plus"></i>					<div class="tree-folder-name"></div>				</div>				<div class="tree-folder-content"></div>				<div class="tree-loader" style="display: none;"></div>			</div>			<div class="tree-item" style="display:none;">				<i class="icon-remove"></i>				<div class="tree-item-name"></div>			</div><div class="tree-folder" style="display: block;">				<div class="tree-folder-header">					<i class="icon-plus"></i>					<div class="tree-folder-name">For Sale</div>				</div>				<div class="tree-folder-content" style="display: none;"><div class="tree-item" style="display: block;">				<i class="icon-remove"></i>				<div class="tree-item-name">Appliances</div>			</div><div class="tree-item" style="display: block;">				<i class="icon-remove"></i>				<div class="tree-item-name">Arts &amp; Crafts</div>			</div><div class="tree-item" style="display: block;">				<i class="icon-remove"></i>				<div class="tree-item-name">Clothing</div>			</div><div class="tree-item" style="display: block;">				<i class="icon-remove"></i>				<div class="tree-item-name">Computers</div>			</div><div class="tree-item" style="display: block;">				<i class="icon-remove"></i>				<div class="tree-item-name">Jewelry</div>			</div><div class="tree-item" style="display: block;">				<i class="icon-remove"></i>				<div class="tree-item-name">Office &amp; Business</div>			</div><div class="tree-item" style="display: block;">				<i class="icon-remove"></i>				<div class="tree-item-name">Sports &amp; Fitness</div>			</div></div>				<div class="tree-loader" style="display: none;"><div class="tree-loading"><i class="icon-refresh icon-spin blue"></i></div></div>			</div><div class="tree-folder" style="display: block;">				<div class="tree-folder-header">					<i class="icon-minus"></i>					<div class="tree-folder-name">Vehicles</div>				</div>				<div class="tree-folder-content" style="display: block;"><div class="tree-folder" style="display: block;">				<div class="tree-folder-header">					<i class="icon-plus"></i>					<div class="tree-folder-name">Cars</div>				</div>				<div class="tree-folder-content"></div>				<div class="tree-loader" style="display: none;"><div class="tree-loading"><i class="icon-refresh icon-spin blue"></i></div></div>			</div><div class="tree-item" style="display: block;">				<i class="icon-remove"></i>				<div class="tree-item-name">Motorcycles</div>			</div><div class="tree-item" style="display: block;">				<i class="icon-remove"></i>				<div class="tree-item-name">Boats</div>			</div></div>				<div class="tree-loader" style="display: none;"><div class="tree-loading"><i class="icon-refresh icon-spin blue"></i></div></div>			</div><div class="tree-folder" style="display: block;">				<div class="tree-folder-header">					<i class="icon-plus"></i>					<div class="tree-folder-name">Rentals</div>				</div>				<div class="tree-folder-content"></div>				<div class="tree-loader" style="display: none;"><div class="tree-loading"><i class="icon-refresh icon-spin blue"></i></div></div>			</div><div class="tree-folder" style="display: block;">				<div class="tree-folder-header">					<i class="icon-plus"></i>					<div class="tree-folder-name">Real Estate</div>				</div>				<div class="tree-folder-content"></div>				<div class="tree-loader" style="display: none;"><div class="tree-loading"><i class="icon-refresh icon-spin blue"></i></div></div>			</div><div class="tree-folder" style="display: block;">				<div class="tree-folder-header">					<i class="icon-plus"></i>					<div class="tree-folder-name">Pets</div>				</div>				<div class="tree-folder-content"></div>				<div class="tree-loader" style="display: none;"><div class="tree-loading"><i class="icon-refresh icon-spin blue"></i></div></div>			</div><div class="tree-item" style="display: block;">				<i class="icon-remove"></i>				<div class="tree-item-name">Tickets</div>			</div><div class="tree-item" style="display: block;">				<i class="icon-remove"></i>				<div class="tree-item-name">Services</div>			</div><div class="tree-item" style="display: block;">				<i class="icon-remove"></i>				<div class="tree-item-name">Personals</div>			</div></div>
                -->
              </div>
            </div>
          </div>
        </div>

        <div class="col-sm-6">
          <div class="widget-box">
            <div class="widget-header header-color-green2">
              <h4 class="lighter smaller">Browse Files</h4>
            </div>

            <div class="widget-body">
              <div class="widget-main padding-8">
                <div id="main" style="height:400px;"></div>
                <!--
                <div id="tree2" class="tree tree-unselectable"><div class="tree-folder" style="display:none;">				<div class="tree-folder-header">					<i class="icon-folder-close"></i>					<div class="tree-folder-name"></div>				</div>				<div class="tree-folder-content"></div>				<div class="tree-loader" style="display: none;"></div>			</div>			<div class="tree-item" style="display:none;">								<div class="tree-item-name"></div>			</div><div class="tree-folder" style="display: block;">				<div class="tree-folder-header">					<i class="icon-folder-close red"></i>					<div class="tree-folder-name">Pictures</div>				</div>				<div class="tree-folder-content"></div>				<div class="tree-loader" style="display: none;"><div class="tree-loading"><i class="icon-refresh icon-spin blue"></i></div></div>			</div><div class="tree-folder" style="display: block;">				<div class="tree-folder-header">					<i class="icon-folder-close orange"></i>					<div class="tree-folder-name">Music</div>				</div>				<div class="tree-folder-content"></div>				<div class="tree-loader" style="display: none;"><div class="tree-loading"><i class="icon-refresh icon-spin blue"></i></div></div>			</div><div class="tree-folder" style="display: block;">				<div class="tree-folder-header">					<i class="icon-folder-close blue"></i>					<div class="tree-folder-name">Video</div>				</div>				<div class="tree-folder-content"></div>				<div class="tree-loader" style="display: none;"><div class="tree-loading"><i class="icon-refresh icon-spin blue"></i></div></div>			</div><div class="tree-folder" style="display: block;">				<div class="tree-folder-header">					<i class="icon-folder-close green"></i>					<div class="tree-folder-name">Documents</div>				</div>				<div class="tree-folder-content"></div>				<div class="tree-loader" style="display: none;"><div class="tree-loading"><i class="icon-refresh icon-spin blue"></i></div></div>			</div><div class="tree-folder" style="display: block;">				<div class="tree-folder-header">					<i class="icon-folder-close"></i>					<div class="tree-folder-name">Backup</div>				</div>				<div class="tree-folder-content"></div>				<div class="tree-loader" style="display: none;"><div class="tree-loading"><i class="icon-refresh icon-spin blue"></i></div></div>			</div><div class="tree-item" style="display: block;">								<div class="tree-item-name"><i class="icon-file-text grey"></i> ReadMe.txt</div>			</div><div class="tree-item" style="display: block;">								<div class="tree-item-name"><i class="icon-book blue"></i> Manual.html</div>			</div></div>
                -->
              </div>
            </div>
          </div>
        </div>
      </div>

      <script type="text/javascript">
        var $assets = "assets";//this will be used in fuelux.tree-sampledata.js
      </script>

      <!-- PAGE CONTENT ENDS -->
    </div><!-- /.col -->
  </div><!-- /.row -->
</div>
</body>
</html>