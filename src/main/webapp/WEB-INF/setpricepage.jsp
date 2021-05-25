<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:genericpage>
    <jsp:attribute name="header">
         Sæt pris for ordre
    </jsp:attribute>
    <jsp:attribute name="footer">
    </jsp:attribute>
    <jsp:body>
        <h1>Sæt pris for ordre</h1>
        <p>Nuværende pris: ${requestScope.currTotal}</p>
        <p>Pris ud fra stykliste: ${requestScope.BOMTotal}</p>
        <form action="${pageContext.request.contextPath}/fc/confirmorder" method="post">
            <label for="total">Sæt pris:</label>
            <input type="number" name="total" id="total" min="0" step="any" value="${requestScope.BOMTotal}">
            <input type="hidden" name="orderID" value="${requestScope.orderID}">
            <button type="submit" class="btn btn-primary" name="action" value="confirm">Bekræft ordre</button>
        </form>
    </jsp:body>
</t:genericpage>
