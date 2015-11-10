<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/include/taglib.jsp" %>
<div class="navbar-buttons navbar-header pull-right" role="navigation">
    <ul class="nav ace-nav">

        <%--<li class="purple">--%>
            <%--<a data-toggle="dropdown" class="dropdown-toggle" href="#">--%>
                <%--<i class="ace-icon fa fa-bell icon-animated-bell"></i>--%>
                <%--<span class="badge badge-important">8</span>--%>
            <%--</a>--%>

            <%--<ul class="dropdown-menu-right dropdown-navbar navbar-pink dropdown-menu dropdown-caret dropdown-close">--%>
                <%--<li class="dropdown-header">--%>
                    <%--<i class="ace-icon fa fa-exclamation-triangle"></i>--%>
                    <%--8 Notifications--%>
                <%--</li>--%>

                <%--<li class="dropdown-content">--%>
                    <%--<ul class="dropdown-menu dropdown-navbar navbar-pink">--%>
                        <%--<li>--%>
                            <%--<a href="#">--%>
                                <%--<div class="clearfix">--%>
													<%--<span class="pull-left">--%>
														<%--<i class="btn btn-xs no-hover btn-pink fa fa-comment"></i>--%>
														<%--New Comments--%>
													<%--</span>--%>
                                    <%--<span class="pull-right badge badge-info">+12</span>--%>
                                <%--</div>--%>
                            <%--</a>--%>
                        <%--</li>--%>

                        <%--<li>--%>
                            <%--<a href="#">--%>
                                <%--<i class="btn btn-xs btn-primary fa fa-user"></i>--%>
                                <%--Bob just signed up as an editor ...--%>
                            <%--</a>--%>
                        <%--</li>--%>

                        <%--<li>--%>
                            <%--<a href="#">--%>
                                <%--<div class="clearfix">--%>
													<%--<span class="pull-left">--%>
														<%--<i class="btn btn-xs no-hover btn-success fa fa-shopping-cart"></i>--%>
														<%--New Orders--%>
													<%--</span>--%>
                                    <%--<span class="pull-right badge badge-success">+8</span>--%>
                                <%--</div>--%>
                            <%--</a>--%>
                        <%--</li>--%>

                        <%--<li>--%>
                            <%--<a href="#">--%>
                                <%--<div class="clearfix">--%>
													<%--<span class="pull-left">--%>
														<%--<i class="btn btn-xs no-hover btn-info fa fa-twitter"></i>--%>
														<%--Followers--%>
													<%--</span>--%>
                                    <%--<span class="pull-right badge badge-info">+11</span>--%>
                                <%--</div>--%>
                            <%--</a>--%>
                        <%--</li>--%>
                    <%--</ul>--%>
                <%--</li>--%>

                <%--<li class="dropdown-footer">--%>
                    <%--<a href="#">--%>
                        <%--See all notifications--%>
                        <%--<i class="ace-icon fa fa-arrow-right"></i>--%>
                    <%--</a>--%>
                <%--</li>--%>
            <%--</ul>--%>
        <%--</li>--%>



        <!-- #section:basics/navbar.user_menu -->
        <li class="light-blue">
            <a data-toggle="dropdown" href="#" class="dropdown-toggle">
                <img class="nav-user-photo" src="${ctxRoot}/static/framework/bootstrap/assets/avatars/user.jpg"
                     alt="Jason's Photo"/>
								<span class="user-info">
									<small>欢迎,</small>
									<s:principal/>
								</span>

                <i class="ace-icon fa fa-caret-down"></i>
            </a>

            <ul class="user-menu dropdown-menu-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">

                <li>
                    <a href="${ctxRoot}/logout.do">
                        <i class="ace-icon fa fa-power-off"></i>
                        Logout
                    </a>
                </li>
            </ul>
        </li>
    </ul>
</div>
