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
    <script src="${ctxRoot}/static/framework/bootstrap/assets/js/bootstrap.js"></script>
    <%--<script src="${ctxRoot}/static/js/tree.js"></script>--%>
    <script src="${ctxRoot}/static/js/capacityTree.js"></script>
    <script src="${ctxRoot}/static/js/capacity.js"></script>
    <!-- chosen-->
    <script src="${ctxRoot}/static/framework/bootstrap/assets/js/chosen.jquery.js"></script>
    <style>
        body {
            margin-bottom: 80px;
        }

        .chosen-container {
            margin-right: 15px;
        }
    </style>
    <script type="text/javascript">
        $(function () {
            $(".chosen-select").chosen();
            $("#btnSearch").click(function () {
                var city =  $("#form-field-select-1").find("option:selected").val();
                var resultType =  $("#form-field-select-4").find("option:selected").val();
                var filter = $("#txtId").text();
                var url = 'query.do';
                var data = {city: city,filter:filter,resultType:resultType};
                X.post(url, data, searchCallback);
            });
            $("#parent").on('click','#form_field_select_3_chosen .active-result',function () {
                onChange();
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
    </div>
    <!-- /.page-header -->
    <div class="row">
        <div class="col-xs-12">
            <!-- PAGE CONTENT BEGINS -->
            <div class="widget-box">
                <div class="widget-body">
                    <div class="widget-main">
                        <div id="parent">
                            查询依据：
                            <select class="width-15 chosen-select" id="form-field-select-2" style="width:150px"
                                    data-placeholder="查询依据">
                                <option value="1">关键字</option>
                                <option value="2">URL标签</option>
                                <option value="3">APP标签</option>
                            </select>
                            城市：
                            <select class="width-15 chosen-select" id="form-field-select-1" style="width:150px"
                                    data-placeholder="Choose a Country...">
                                <c:forEach var="item" items="${areaList}">
                                    <c:if test="${item.id!='1'}">
                                        <option value="${item.pinyin}">${item.name}</option>
                                    </c:if>
                                </c:forEach>
                            </select>
                            查询关系：
                            <select class="width-15 chosen-select" id="form-field-select-3" style="width:150px"
                                    data-placeholder="查询依据">
                                <option value=""></option>
                                <option value="$">与</option>
                                <option value="|">或</option>
                                <option value="!">非</option>
                            </select>
                            <select class="width-15 chosen-select" id="form-field-select-4" style="width:150px"
                                    data-placeholder="输出条件">
                                <option selected value="0">手机号</option>
                                <option value="1">MEID号</option>
                                </select>
                            <button class="btn btn-sm btn-info" style="width: 80px;float: right" id="btnSearch">查 询
                            </button>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-4">
                    <div class="widget-box">
                        <div class="widget-header header-color-blue2">
                            <h4 class="lighter smaller">标签体系</h4>
                        </div>
                        <div class="widget-body">
                            <div class="widget-main padding-8">
                                <div class="mainzuo" style="overflow:auto">
                                    <!--<h5>查询条件</h5>-->
                                    <ul id="tagTree" class="ztree" style="height:453px;"></ul>
                                </div>
                                <!--
                                <div id="tree1" class="tree tree-selectable"><div class="tree-folder" style="display:none;">				<div class="tree-folder-header">					<i class="icon-plus"></i>					<div class="tree-folder-name"></div>				</div>				<div class="tree-folder-content"></div>				<div class="tree-loader" style="display: none;"></div>			</div>			<div class="tree-item" style="display:none;">				<i class="icon-remove"></i>				<div class="tree-item-name"></div>			</div><div class="tree-folder" style="display: block;">				<div class="tree-folder-header">					<i class="icon-plus"></i>					<div class="tree-folder-name">For Sale</div>				</div>				<div class="tree-folder-content" style="display: none;"><div class="tree-item" style="display: block;">				<i class="icon-remove"></i>				<div class="tree-item-name">Appliances</div>			</div><div class="tree-item" style="display: block;">				<i class="icon-remove"></i>				<div class="tree-item-name">Arts &amp; Crafts</div>			</div><div class="tree-item" style="display: block;">				<i class="icon-remove"></i>				<div class="tree-item-name">Clothing</div>			</div><div class="tree-item" style="display: block;">				<i class="icon-remove"></i>				<div class="tree-item-name">Computers</div>			</div><div class="tree-item" style="display: block;">				<i class="icon-remove"></i>				<div class="tree-item-name">Jewelry</div>			</div><div class="tree-item" style="display: block;">				<i class="icon-remove"></i>				<div class="tree-item-name">Office &amp; Business</div>			</div><div class="tree-item" style="display: block;">				<i class="icon-remove"></i>				<div class="tree-item-name">Sports &amp; Fitness</div>			</div></div>				<div class="tree-loader" style="display: none;"><div class="tree-loading"><i class="icon-refresh icon-spin blue"></i></div></div>			</div><div class="tree-folder" style="display: block;">				<div class="tree-folder-header">					<i class="icon-minus"></i>					<div class="tree-folder-name">Vehicles</div>				</div>				<div class="tree-folder-content" style="display: block;"><div class="tree-folder" style="display: block;">				<div class="tree-folder-header">					<i class="icon-plus"></i>					<div class="tree-folder-name">Cars</div>				</div>				<div class="tree-folder-content"></div>				<div class="tree-loader" style="display: none;"><div class="tree-loading"><i class="icon-refresh icon-spin blue"></i></div></div>			</div><div class="tree-item" style="display: block;">				<i class="icon-remove"></i>				<div class="tree-item-name">Motorcycles</div>			</div><div class="tree-item" style="display: block;">				<i class="icon-remove"></i>				<div class="tree-item-name">Boats</div>			</div></div>				<div class="tree-loader" style="display: none;"><div class="tree-loading"><i class="icon-refresh icon-spin blue"></i></div></div>			</div><div class="tree-folder" style="display: block;">				<div class="tree-folder-header">					<i class="icon-plus"></i>					<div class="tree-folder-name">Rentals</div>				</div>				<div class="tree-folder-content"></div>				<div class="tree-loader" style="display: none;"><div class="tree-loading"><i class="icon-refresh icon-spin blue"></i></div></div>			</div><div class="tree-folder" style="display: block;">				<div class="tree-folder-header">					<i class="icon-plus"></i>					<div class="tree-folder-name">Real Estate</div>				</div>				<div class="tree-folder-content"></div>				<div class="tree-loader" style="display: none;"><div class="tree-loading"><i class="icon-refresh icon-spin blue"></i></div></div>			</div><div class="tree-folder" style="display: block;">				<div class="tree-folder-header">					<i class="icon-plus"></i>					<div class="tree-folder-name">Pets</div>				</div>				<div class="tree-folder-content"></div>				<div class="tree-loader" style="display: none;"><div class="tree-loading"><i class="icon-refresh icon-spin blue"></i></div></div>			</div><div class="tree-item" style="display: block;">				<i class="icon-remove"></i>				<div class="tree-item-name">Tickets</div>			</div><div class="tree-item" style="display: block;">				<i class="icon-remove"></i>				<div class="tree-item-name">Services</div>			</div><div class="tree-item" style="display: block;">				<i class="icon-remove"></i>				<div class="tree-item-name">Personals</div>			</div></div>
                                -->
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-sm-8">
                    <div class="widget-box">
                        <div class="widget-header header-color-blue2">
                            <h4 class="lighter smaller" style="float: left">查询条件</h4>
                        </div>
                        <div class="widget-body">
                            <div class="widget-main padding-8">
                                <div id="main" style="height:150px;">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="widget-box" style="margin-top: 20px">
                        <div class="widget-header header-color-blue2">
                            <h4 class="lighter smaller" style="float: left">查询结果：<span id="current-tag-name"></span>
                            </h4>
                        </div>
                        <input type="hidden" id="current-tag-id" value="">

                        <div class="widget-body">
                            <div class="widget-main padding-8">
                                <div id="result" style="height:225px;overflow: auto">
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>