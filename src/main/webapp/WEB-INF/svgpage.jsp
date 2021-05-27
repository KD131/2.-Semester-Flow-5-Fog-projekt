<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:genericpage>

    <jsp:attribute name="header">
         Vis SVG tegning
    </jsp:attribute>

    <jsp:attribute name="footer">
        <c:set var="addHomeLink" value="${false}" scope="request"/>
    </jsp:attribute>

    <jsp:body>

            <h2>Tegning</h2>
<%--            ${sessionScope.order.orderID}--%>
                ${requestScope.sidesvgdrawing}
                ${requestScope.topsvgdrawing}



    </jsp:body>
</t:genericpage>