<%@tag pageEncoding="UTF-8" %>
<%@ attribute name="page" type="org.springframework.data.domain.Page" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${not empty page && page.totalElements!=0}">
    <%
        int current = page.getNumber() + 1;
        int paginationSize = page.getSize();
        int begin = Math.max(1, current - paginationSize / 2);
        int end = Math.min(begin + (paginationSize - 1), page.getTotalPages());

        request.setAttribute("current", current);
        request.setAttribute("begin", begin);
        request.setAttribute("end", end);
    %>

    <div class="dataTables_paginate paging_simple_numbers" id="sample-table-2_paginate" style="margin-top: 3px;">
        <ul class="pagination">
            <% if (page.hasPreviousPage()) {%>
            <li class="paginate_button">
                <a href="?id=${command.id}&sessionFlag=${sessionFlag}&page=1&sortType=${sortType}&type=${type}&${searchParams}">
                    &lt;&lt;</a></li>
            <li class="paginate_button">
                <a href="?id=${command.id}&sessionFlag=${sessionFlag}&page=${current-1}&sortType=${sortType}&type=${type}&${searchParams}">
                    &lt;</a></li>
            <%} else {%>
            <li class=" paginate_button disabled"><a href="#">&lt;&lt;</a></li>
            <li class=" paginate_button disabled"><a href="#">&lt;</a></li>
            <%} %>

            <c:forEach var="i" begin="${begin}" end="${end}">
                <c:choose>
                    <c:when test="${i == current}">
                        <li class="paginate_button active"><a
                                href="?id=${command.id}&sessionFlag=${sessionFlag}&page=${i}&sortType=${sortType}&type=${type}&${searchParams}">${i}</a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="paginate_button ">
                            <a href="?id=${command.id}&sessionFlag=${sessionFlag}&page=${i}&sortType=${sortType}&type=${type}&${searchParams}">${i}</a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>

            <% if (page.hasNextPage()) {%>
            <li class="paginate_button">
                <a href="?id=${command.id}&sessionFlag=${sessionFlag}&page=${current+1}&sortType=${sortType}&type=${type}&${searchParams}">
                    &gt;</a></li>
            <li class="paginate_button">
                <a href="?id=${command.id}&sessionFlag=${sessionFlag}&page=${page.totalPages}&sortType=${sortType}&type=${type}&${searchParams}">
                    &gt;&gt;</a></li>
            <%} else {%>
            <li class="paginate_button disabled"><a href="#">&gt;</a></li>
            <li class="paginate_button disabled"><a href="#">&gt;&gt;</a></li>
            <%} %>

        </ul>
    </div>
</c:if>