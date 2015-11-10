<%@tag pageEncoding="UTF-8" %>
<%@ attribute name="page" type="org.springframework.data.domain.Page" %>
<%@ attribute name="url" type="java.lang.String" %>
<%@ attribute name="method" type="java.lang.String" %>
<%@ attribute name="parameter" type="java.lang.String" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script language="javascript">
    function pageselectCallback(page) {
        var current = page.number + 1;
        var totalPages = page.totalPages;
        var paginationSize = page.size;
        var begin = Math.max(1, current - paginationSize / 2);
        var end = Math.min(begin + (paginationSize - 1), page.totalPages);
        var hasPreviousPage = page.firstPage;
        var hasNextPage = page.lastPage;

        var url = "${url}";
        var parameter = $("#${parameter}").val();
        var pagination = $(".pagination ul");
        $(".pagination ul li").remove();
        if (!hasPreviousPage) {
            pagination.append('<li><a href="' + url + '?id=${command.id}&sessionFlag=${sessionFlag}&page=1&sortType=${sortType}&type=${type}&${searchParams}&${parameter}=' + parameter + '" class="page" method="${method}">&lt;&lt;</a></li>');
            pagination.append('<li><a href="' + url + '?id=${command.id}&sessionFlag=${sessionFlag}&page=' + (current - 1) + '&sortType=${sortType}&type=${type}&${searchParams}&${parameter}=' + parameter + '" class="page" method="${method}">&lt;</a></li>');
        } else {
            pagination.append('<li class="disabled"><a href="javascript:void(0)">&lt;&lt;</a></li>');
            pagination.append('<li class="disabled"><a href="javascript:void(0)">&lt;</a></li>');
        }

        for (var i = begin; i <= end; i++) {
            if (i == current) {
                pagination.append('<li class="active"><a href="' + url + '?id=${command.id}&sessionFlag=${sessionFlag}&page=' + i + '&sortType=${sortType}&type=${type}&${searchParams}&${parameter}=' + parameter + '" class="page" method="${method}">' + i + '</a></li>')
            } else {
                pagination.append('<li><a href="' + url + '?id=${command.id}&sessionFlag=${sessionFlag}&page=' + i + '&sortType=${sortType}&type=${type}&${searchParams}&${parameter}=' + parameter + '" class="page" method="${method}">' + i + '</a></li>')
            }
        }

        if (!hasNextPage) {
            pagination.append('<li><a href="' + url + '?id=${command.id}&sessionFlag=${sessionFlag}&page=' + (current + 1) + '&sortType=${sortType}&type=${type}&${searchParams}&${parameter}=' + parameter + '" class="page" method="${method}">&gt;</a></li>')
            pagination.append('<li><a href="' + url + '?id=${command.id}&sessionFlag=${sessionFlag}&page=' + totalPages + '&sortType=${sortType}&type=${type}&${searchParams}&${parameter}=' + parameter + '" class="page" method="${method}">&gt;&gt;</a></li>')
        } else {
            pagination.append('<li class="disabled"><a href="javascript:void(0)">&gt;</a></li>');
            pagination.append('<li class="disabled"><a href="javascript:void(0)">&gt;&gt;</a></li>')
        }
    }

</script>

<c:if test="${not empty page}">
    <%
        int current = page.getNumber() + 1;
        int paginationSize = page.getSize();
        int begin = Math.max(1, current - paginationSize / 2);
        int end = Math.min(begin + (paginationSize - 1), page.getTotalPages());

        request.setAttribute("current", current);
        request.setAttribute("begin", begin);
        request.setAttribute("end", end);
    %>

    <div class="pagination">
        <ul>
            <% if (page.hasPreviousPage()) {%>
            <li>
                <a href="${url}?id=${command.id}&sessionFlag=${sessionFlag}&page=1&sortType=${sortType}&type=${type}&${searchParams}"
                   class="page" callback="${callback}">&lt;&lt;</a></li>
            <li>
                <a href="${url}?id=${command.id}&sessionFlag=${sessionFlag}&page=${current-1}&sortType=${sortType}&type=${type}&${searchParams}"
                   class="page" callback="${callback}">&lt;</a></li>
            <%} else {%>
            <li class="disabled"><a href="javascript:void(0)">&lt;&lt;</a></li>
            <li class="disabled"><a href="javascript:void(0)">&lt;</a></li>
            <%} %>

            <c:forEach var="i" begin="${begin}" end="${end}">
                <c:choose>
                    <c:when test="${i == current}">
                        <li class="active"><a
                                href="${url}?id=${command.id}&sessionFlag=${sessionFlag}&page=${i}&sortType=${sortType}&type=${type}&${searchParams}"
                                class="page" callback="${callback}">${i}</a></li>
                    </c:when>
                    <c:otherwise>
                        <li>
                            <a href="${url}?id=${command.id}&sessionFlag=${sessionFlag}&page=${i}&sortType=${sortType}&type=${type}&${searchParams}"
                               class="page" callback="${callback}">${i}</a></li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>

            <% if (page.hasNextPage()) {%>
            <li>
                <a href="${url}?id=${command.id}&sessionFlag=${sessionFlag}&page=${current+1}&sortType=${sortType}&type=${type}&${searchParams}"
                   class="page" callback="${callback}">&gt;</a></li>
            <li>
                <a href="${url}?id=${command.id}&sessionFlag=${sessionFlag}&page=${page.totalPages}&sortType=${sortType}&type=${type}&${searchParams}"
                   class="page" callback="${callback}">&gt;&gt;</a></li>
            <%} else {%>
            <li class="disabled"><a href="javascript:void(0)">&gt;</a></li>
            <li class="disabled"><a href="javascript:void(0)">&gt;&gt;</a></li>
            <%} %>

        </ul>
    </div>
</c:if>