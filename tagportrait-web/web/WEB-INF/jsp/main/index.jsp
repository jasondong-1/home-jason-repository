<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/include/taglib.jsp" %>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  <title>homepage</title>
</head>
<body>
<div class="homezhuti">
  <div><h1>欢迎访问精准营销用户标签画像系统!</h1></div>
  <div class="yu">
    <div class="mzuo">
      <form>
        <fieldset class="field">
          <legend class="lege">执行Hive语句</legend>
          获取执行计划信息,执行Hive语句返回查询结果,结果文件可下载到本地
        </fieldset>
        <fieldset class="field">
          <legend class="lege">查看数据库表结构</legend>
          查看Hive数据库和表结构详细信息
        </fieldset>
        <fieldset class="field">
          <legend class="lege">查看查询历史</legend>
          查看用户提交过的查询历史和下载结果文件
        </fieldset>
      </form>
    </div>
    <div class="myou">
      <form>
        <fieldset class="field">
          <legend class="lege">最近十条查询记录</legend>
          <div class="tab">
            <table cellpadding="0" cellspacing="0">
              <c:forEach var="hql" items="${hqlList}">
                <tr>
                  <td class="ownerh" style="line-height: 35px">${hql}</td>
                </tr>
              </c:forEach>
            </table>
          </div>
        </fieldset>
      </form>
    </div>
  </div>
</div>
</body>
</html>