<%--
  Created by IntelliJ IDEA.
  User: whr
  Date: 2015/9/11
  Time: 21:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/include/taglib.jsp" %>
<html>
<head>
  <meta charset="utf-8"/>
  <title>text</title>
  <meta name="description" content="Common form elements and layouts"/>
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <!--[if IE 7]>
  <link rel="stylesheet" href="${ctxRoot}/static/chosen/font-awesome-ie7.min.css"/>
  <![endif]-->
  <link rel="stylesheet" href="${ctxRoot}/static/framework/bootstrap/assets/css/chosen.css"/>
  <link rel="stylesheet" href="${ctxRoot}/static/framework/bootstrap/assets/css/ace.css"/>
  <!--[if lte IE 8]>
  <link rel="stylesheet" href="${ctxRoot}/static/framework/bootstrap/assets/css/ace-ie.css"/>
  <![endif]-->
  <script src="${ctxRoot}/static/framework/bootstrap/assets/js/chosen.jquery.js"></script>
  <script src="${ctxRoot}/static/framework/echarts/echarts.js"></script>
  <script src="${ctxRoot}/static/js/dist.js"></script>
  <style>
    .chosen-container {
      margin-right: 15px;
    }

    .widget-box {
      border: 0px;
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
                'echarts/chart/map'  // 按需加载所需图表，如需动态类型切换功能，别忘了同时加载相应图表
              ],
              function (ec) {
                var url = 'show_map_chart.do'
                var data = {tagId: 1};
                X.post(url, data, mapCallback);
              }
      );

      $("#btnSearch").click(function(){
        var tagId = '';
        if($("#form-field-select-3").val() != '无'){
          tagId = $("#form-field-select-3").val();
        }else if($("#form-field-select-2").val() != '无') {
          tagId = $("#form-field-select-2").val();
        }else if($("#form-field-select-1").val() != '无') {
          tagId = $("#form-field-select-1").val();
        }
        if(tagId == '') {
          alert("请选择一个标签！")
        } else {
          var url = 'show_map_chart.do'
          var data = {tagId: 1};
          X.post(url, data, mapCallback);
        }
      });
    });
  </script>
</head>
<body>
<div class="main-container" id="main-container">
  <script type="text/javascript">
    try {
      ace.settings.check('main-container', 'fixed')
    } catch (e) {
    }
  </script>
  <div class="main-container-inner">
    <div class="main-content">
      <div class="page-content">
        <div class="page-header">
          <h1>
            标签分布统计页面
          </h1>
        </div>
        <!-- /.page-header -->
        <div class="row">
          <div class="col-xs-12">

            <%--<form class="form-horizontal" role="form">--%>
            <div class="row">
              <div class="col-xs-10 col-xs-offset-1 ">
                <div class="widget-box">
                  <div class="widget-body">
                    <div class="widget-main">
                      <div>
                        一级标签：
                        <select class="width-15 chosen-select" id="form-field-select-1"
                                data-placeholder="Choose a Country..." onchange="onFirstChange()">
                          <option value="无">请选择</option>
                          <c:forEach var="item" items="${tagFirstList}">
                            <option value="${item.id}">${item.name}</option>
                          </c:forEach>
                        </select>
                        二级标签：
                        <select class="width-15 chosen-select" id="form-field-select-2"
                                data-placeholder="Choose a Country..." onchange="onSecondChange()">
                          <option value="无">请选择</option>
                        </select>
                        三级标签：
                        <select class="width-15 chosen-select" id="form-field-select-3"
                                data-placeholder="Choose a Country...">
                          <%--<option value="">&nbsp;</option>--%>
                          <option value="无">请选择</option>
                        </select>
                        <button class="btn btn-sm btn-info"
                                style="width: 80px;float: right" id="btnSearch">查 询
                        </button>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
              <div class="col-xs-10 col-xs-offset-1">
                <div id="map"
                     style="width: 100%;height: 500px;border: 1px solid #d0d0d0;margin: 0px auto"></div>
              </div>

            </div>
            <%--</form>--%>
          </div>
        </div>

      </div>
    </div>
  </div>
</div>
</body>
</html>

</body>
</html>
