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
  <!-- custom-->
  <link rel="stylesheet" href="${ctxRoot}/static/css/css.css">
  <!-- ztree -->
  <link href="${ctxRoot}/static/ztree/css/zTreeStyle/zTreeStyle.css" rel="stylesheet" type="text/css"/>
  <script src="${ctxRoot}/static/ztree/js/jquery.ztree.core-3.5.min.js"></script>
  <!-- custom js-->
  <script src="${ctxRoot}/static/js/tree.js"></script>
  <script src="${ctxRoot}/static/js/main.js"></script>
  <!-- echarts-->
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
          var url = 'show_tag_chart.do'
          var data = {tagId: 1};
          X.post(url, data, treeClickCallback);
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
              <h4 class="lighter smaller">标签体系</h4>
            </div>

            <div class="widget-body">
              <div class="widget-main padding-8">
                <div class="mainzuo" style="overflow:auto">
                  <!--<h5>查询条件</h5>-->
                  <ul id="tagTree" class="ztree" style="height:400px;"></ul>
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