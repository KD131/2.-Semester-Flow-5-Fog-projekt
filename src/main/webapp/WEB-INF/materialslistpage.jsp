<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:genericpage>
    <jsp:attribute name="header">
         Materialeliste
    </jsp:attribute>
    <jsp:attribute name="footer">
    </jsp:attribute>
    <jsp:body>
        <h1>Alle materialer</h1>

        <c:if test="${requestScope.error != null}">
            <p class="error">${requestScope.error}</p>
        </c:if>

        <p style="color: red">Editing a material will replace ALL of its functionalities with the new one.</p>
        <p style="color: red">Deleting a material will delete ALL of its functionalities, not just the one selected.</p>

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
                        <th>
                            <form action="${pageContext.request.contextPath}/fc/managematerial" method="post">
                                <button type="submit" class="btn btn-success" name="action" value="create">Tilføj</button>
                            </form>
                        </th>
                    </tr>
                    </thead>
                    <c:forEach var="material" items="${applicationScope.materialsList}">
                        <tr>
                            <td>${material.materialID}</td>
                            <td>${material.name}</td>
                            <td>${material.unit}</td>
                            <td>${material.buyPricePerUnitString}</td>
                            <td>${material.pricePerUnitString}</td>
                            <td>${material.length}</td>
                            <td>${material.width}</td>
                            <td>${material.height}</td>
                            <td>${material.functionality}</td>
                            <td>
                                <form action="${pageContext.request.contextPath}/fc/managematerial" method="post">
                                    <input type="hidden" name="materialID" value="${material.materialID}">
                                    <button type="submit" class="btn btn-warning" name="action" value="edit">Rediger</button>
                                    <button type="submit" class="btn btn-danger" name="action" value="delete">Slet</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </c:when>
            <c:otherwise>
                <p>Ingen materialer i databasen.</p>
            </c:otherwise>
        </c:choose>

    </jsp:body>
</t:genericpage>
