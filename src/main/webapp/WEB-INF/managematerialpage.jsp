<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:genericpage>
    <jsp:attribute name="header">
            <c:if test="${requestScope.action.equals('create')}">Tilføj nyt materiale</c:if>
            <c:if test="${requestScope.action.equals('edit')}">Rediger materiale</c:if>
    </jsp:attribute>
    <jsp:attribute name="footer">
    </jsp:attribute>
    <jsp:body>
        <h1>
            <!-- Could be CHOOSE WHEN OTHERWISE but this is a more explicit test on both conditions -->
            <c:if test="${requestScope.action.equals('create')}">Tilføj nyt materiale</c:if>
            <c:if test="${requestScope.action.equals('edit')}">Rediger materiale</c:if>
        </h1>

        <c:if test="${requestScope.error != null}">
            <p class="error">${requestScope.error}</p>
        </c:if>

        <form action="${pageContext.request.contextPath}/fc/managematerial" method="post">
            <div class="form-group">
                <label for="name" class="form-label">Navn:</label>
                <input type="text" name="name" id="name"
                       value="<c:if test="${requestScope.action.equals('edit')}">${requestScope.material.name}</c:if>">
            </div>

            <div class="form-group">
                <label for="unit" class="form-label">Enhed:</label>
                <select name="unit" id="unit">
                    <c:forEach var="unit" items="${requestScope.units}">
                        <option
                                <c:if test="${requestScope.action.equals('edit') and requestScope.material.unit.equalsIgnoreCase(unit)}">
                                    style="font-weight: bold" selected
                                </c:if>
                                value="${unit}">${unit}
                        </option>
                    </c:forEach>
                </select>
            </div>

            <div class="form-group">
                <label for="buyPricePerUnit" class="form-label">Købspris per Enhed:</label>
                <input type="number" name="buyPricePerUnit" id="buyPricePerUnit" min="0" step="any"
                       value="<c:if test="${requestScope.action.equals('edit')}">${requestScope.material.buyPricePerUnit}</c:if>">
            </div>

            <div class="form-group">
                <label for="pricePerUnit" class="form-label">Pris per Enhed:</label>
                <input type="number" name="pricePerUnit" id="pricePerUnit" min="0" step="any"
                       value="<c:if test="${requestScope.action.equals('edit')}">${requestScope.material.pricePerUnit}</c:if>">
            </div>

            <div class="form-group">
                <label for="length" class="form-label">Længde:</label>
                <input type="number" name="length" id="length" min="0" step="1"
                       value="<c:if test="${requestScope.action.equals('edit')}">${requestScope.material.length}</c:if>">
            </div>

            <div class="form-group">
                <label for="width" class="form-label">Bredde:</label>
                <input type="number" name="width" id="width" min="0" step="1"
                       value="<c:if test="${requestScope.action.equals('edit')}">${requestScope.material.width}</c:if>">
            </div>

            <div class="form-group">
                <label for="height" class="form-label">Højde:</label>
                <input type="number" name="height" id="height" min="0" step="1"
                       value="<c:if test="${requestScope.action.equals('edit')}">${requestScope.material.height}</c:if>">
            </div>

            <!-- ADDING MORE THAN ONE FUNCTIONALITY MIGHT NOT BE POSSIBLE AT THE MOMENT -->
            <div class="form-group">
                <label for="functionality" class="form-label">Funktion:</label>
                <select name="functionality" id="functionality">
                    <c:forEach var="functionality" items="${requestScope.functionalities}">
                    <option
                            <c:if test="${requestScope.action.equals('edit') and requestScope.material.functionality.equalsIgnoreCase(functionality)}">
                                style="font-weight: bold" selected
                            </c:if>
                            value="${functionality}">${functionality}
                    </option>
                </c:forEach>
                </select>
            </div>

            <c:if test="${requestScope.action.equals('edit')}">
                <input type="hidden" name="materialID" value="${requestScope.material.materialID}">
            </c:if>

            <c:if test="${requestScope.action.equals('create')}">
                <button type="submit" class="btn btn-primary" name="action" value="confirmcreation">Tilføj</button>
            </c:if>
            <c:if test="${requestScope.action.equals('edit')}">
                <button type="submit" class="btn btn-warning" name="action" value="confirmedit">Rediger</button>
            </c:if>
        </form>
    </jsp:body>
</t:genericpage>
