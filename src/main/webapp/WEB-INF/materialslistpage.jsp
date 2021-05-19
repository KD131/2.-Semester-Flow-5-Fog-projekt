<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:genericpage>
    <jsp:attribute name="header">
         Materialslist
    </jsp:attribute>
    <jsp:attribute name="footer">
    </jsp:attribute>
    <jsp:body>
        <h1>All materials</h1>
        <c:choose>
            <c:when test="${not empty applicationScope.materialsList}">
                <table class="table table-striped table-hover">
                    <thead>
                    <tr>
                        <th>Materiale ID</th>
                        <th>Navn</th>
                        <th>Enhed</th>
                        <th>Købspris per Enhed</th>
                        <th>Pris per Enhed</th>
                        <th>Længde</th>
                        <th>Bredde</th>
                        <th>Højde</th>
                        <th>Funktion</th>
                        <th>Rediger</th>
                    </tr>
                    </thead>
                    <c:forEach var="material" items="${applicationScope.materialsList}">
                        <tr>
                            <td>${material.materialID}</td>
                            <td>${material.name}</td>
                            <td>${material.unit}</td>
                            <td>${material.buyPricePerUnit}</td>
                            <td>${material.pricePerUnit}</td>
                            <td>${material.length}</td>
                            <td>${material.width}</td>
                            <td>${material.height}</td>
                            <td>${material.functionality}</td>
                            <td><a href="#">IKKE LAVET</a></td>
                        </tr>
                    </c:forEach>
                </table>
            </c:when>
            <c:otherwise>
                <p>No materials in database.</p>
            </c:otherwise>
        </c:choose>

    </jsp:body>
</t:genericpage>
