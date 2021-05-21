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
        <h1>Stykliste til ordre #</h1>
        <table class="table-striped table-hover">
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
            <c:forEach var="material" items="${requestScope.BOM}">
                <tr>
                    <td>${material.material.name}</td>
                </tr>
            </c:forEach>

        </table>



    </jsp:body>
</t:genericpage>
