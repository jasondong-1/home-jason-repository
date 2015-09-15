<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/include/taglib.jsp" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>homepage</title>
    <!-- basic styles -->

    <!-- ace styles -->
    <link rel="stylesheet" href="${ctxRoot}/static/framework/bootstrap/assets/css/chosen.css"/>
    <link rel="stylesheet" href="${ctxRoot}/static/framework/bootstrap/assets/css/ace.css">

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
    <!-- chosen-->
    <script src="${ctxRoot}/static/framework/bootstrap/assets/js/chosen.jquery.js"></script>
    <style>
        body {
            margin-bottom: 80px;
        }
    </style>
    <style>
        .chosen-container {
            margin-right: 15px;
        }
    </style>
    <script type="text/javascript">
        $(function() {
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
                        var data = {tagId: 1, areaId: 1};
                        X.post(url, data, treeClickCallback);
                    }
            );
            //var treeObj = $.fn.zTree.getZTreeObj("tagTree");
            //treeObj.setting.async.otherParam = {areaId: 1};
            $(".chosen-select").chosen();

            $("#btnSave").click(function(){
                var url = 'save_tag_description.do';
                var data = {tagId: $('#current-tag-id').val(),description: $('#tag-description').val()};
                X.post(url, data, '');

            });
        });
    </script>
</head>
<body>
<div class="page-content">
    <div class="page-header">
        <h1>
            标签体系展现
        </h1>
    </div><!-- /.page-header -->

    <div class="row">
        <div class="col-xs-12">
            <!-- PAGE CONTENT BEGINS -->
            <div class="widget-box">
                <div class="widget-body">
                    <div class="widget-main">
                        <div>
                            城市：
                            <select class="width-15 chosen-select" id="form-field-select-1" style="width:200px"
                                    data-placeholder="Choose a Country...">
                                <c:forEach var="item" items="${areaList}">
                                    <option value="${item.id}">${item.name}</option>
                                </c:forEach>
                            </select>
                            <button class="btn btn-sm btn-info"
                                    style="width: 80px;float: right" id="btnSearch">查 询
                            </button>
                        </div>
                    </div>
                </div>
            </div>
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




    <div class="col-sm-12">
        <h4 class="header green">标签描述</h4>

        <div class="widget-box">
            <div class="widget-header widget-header-small header-color-blue">
                <h4 class="lighter smaller" style="float: left">当前标签：<span id="current-tag-name"></span></h4>
                <button class="btn btn-sm btn-primary" id="btnSave" style="float: right;width: 60px;margin-right: 10px;">
                    <i class="icon-save bigger-125"></i>
                    保  存
                </button>
            </div>
            <input type="hidden" id="current-tag-id" value="">
            <div class="widget-body">
                <div class="widget-main no-padding">
                    <div class="md-editor" id="1441964727113"><textarea id="tag-description" class="span12 md-input" name="content" data-provide="markdown" rows="10" style="resize: none;">请选择标签</textarea></div>
                </div>
            </div>
        </div>
    </div>



</div>


</body>
</html>