<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/include/taglib.jsp" %>
<%--<div id='background' style="display:block;" class='background'></div>--%>
<%--<div id='progressBar' style="display:block;" class='progressBar'>数据加载中，请稍等...</div>--%>
<div class="navhead">
    <div class="img">
        <img src="${ctxRoot}/static/images/logo.png" class="logo"/>
    </div>
    <shiro:user>
        <div class="navcontaint">
            <ul>
                <shiro:hasRole name="admin">
                    <c:forEach var="menu" items="${menuList}">
                        <li>
                            <a href="${ctxRoot}${menu.url}" class="<c:if test="${menu.isActive}">current</c:if>">
                                    <span class="${menu.cssClass}">
                                            ${menu.title}
                                    </span>
                            </a>
                        </li>
                    </c:forEach>
                </shiro:hasRole>
                <li>
                    <a href="${ctxRoot}/logout.do">
                        <span>退出</span>
                    </a>
                </li>
            </ul>
        </div>
    </shiro:user>
</div>