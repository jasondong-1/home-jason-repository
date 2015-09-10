<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<ul class="nav nav-list">
    <li <c:if test="${pageContext.request.requestURI eq '/coral/main/index.do'}">class="active"</c:if>>
        <a href="${ctxRoot}/main/index.do">
            <i class="menu-icon fa fa-home"></i>
            <span class="menu-text">首页</span>
        </a>
        <b class="arrow"></b>
    </li>

    <c:forEach var="item" items="${menuList}">
        <c:if test="${empty item.subMenu}">
            <li <c:if test="${item.isActive == true}">class="active"</c:if>>
                <a href="${ctxRoot}${item.url}">
                    <i class="${item.cssClass}"></i>
                    <span class="menu-text">${item.title}</span>
                </a>
            </li>
        </c:if>
        <!--二级菜单-->
        <c:if test="${not empty item.subMenu}">
            <li id="parent_menu_${item.id}" class="">
                <a href="#" class="dropdown-toggle">
                    <i class="${item.cssClass}"></i>
                    <span class="menu-text">${item.title}</span>
                    <b class="arrow icon-angle-down"></b>
                </a>
                <ul class="submenu">
                    <c:forEach var="submenu" items="${item.subMenu}">
                        <li class="<c:if test="${submenu.isActive}">active</c:if>">
                            <a href="${ctxRoot}${submenu.url}">
                                <i class="icon-double-angle-right"></i>
                                    ${submenu.title}
                            </a>
                        </li>
                    </c:forEach>
                </ul>
            </li>
        </c:if>

    </c:forEach>
</ul>
</li>
</ul><!-- /.nav-list -->
<script>
    $(".submenu li.active").parent("ul").parent("li").addClass("open").addClass("active");
</script>
