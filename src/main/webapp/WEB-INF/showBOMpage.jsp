<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:genericpage>
    <jsp:attribute name="header">
         Stykliste
    </jsp:attribute>
    <jsp:attribute name="footer">
    </jsp:attribute>
    <jsp:body>
        <c:choose>
            <c:when test="${requestScope.BOM != null}">
                <h1>Stykliste til ordre #${requestScope.orderID}</h1>
                <table class="table table-striped table-hover">
                    <thead>
                    <tr>
                        <th>Navn</th>
                        <th>Længde</th>
                        <th>Antal</th>
                        <th>Enhed</th>
                        <th>Beskrivelse</th>
                    </tr>
                    </thead>
                    <c:forEach var="material" items="${requestScope.BOM}">
                        <tr>
                            <td>${material.material.name}</td>
                            <td>${material.material.length}</td>
                            <td>${material.quantity}</td>
                            <td>${material.material.unit}</td>
                            <td>${material.description}</td>
                        </tr>
                    </c:forEach>
                </table>
            </c:when>
            <c:otherwise>
                <h1>Ordre kan ikke vises!</h1>
                <p>Ordren eksisterer ikke, eller du har ikke rettigheder til at se den.</p>
            </c:otherwise>
        </c:choose>


    </jsp:body>
</t:genericpage>
