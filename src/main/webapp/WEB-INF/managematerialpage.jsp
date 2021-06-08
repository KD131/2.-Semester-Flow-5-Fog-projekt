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
        <div class="row text-start">
            <div class="col-lg-4 mx-auto">
                <h1 class="text-center">
                        <%-- Could be CHOOSE WHEN OTHERWISE but this is a more explicit test on both conditions --%>
                    <c:if test="${requestScope.action.equals('create')}">Tilføj nyt materiale</c:if>
                    <c:if test="${requestScope.action.equals('edit')}">Rediger materiale</c:if>
                </h1>

                <c:if test="${requestScope.error != null}">
                    <p class="error">${requestScope.error}</p>
                </c:if>
                <form action="${pageContext.request.contextPath}/fc/managematerial" method="post">
                    <div class="row form-group mt-2">
                        <div class="col">
                            <label for="name" class="col-form-label">Navn:</label>
                        </div>
                        <div class="col-8">
                            <input class="form-control" type="text" name="name" id="name"
                                   value="<c:if test="${requestScope.action.equals('edit')}">${requestScope.material.name}</c:if>">
                        </div>
                    </div>

                    <div class="row form-group mt-2">
                        <div class="col">
                            <label for="unit" class="col-form-label">Enhed:</label>
                        </div>
                        <div class="col">
                            <select class="form-control" name="unit" id="unit">
                                <c:forEach var="unit" items="${requestScope.units}">
                                    <option
                                        <%-- ignorecase not needed if database entries are converted to first upper-case letter but still a good check --%>
                                            <c:if test="${requestScope.action.equals('edit') and requestScope.material.unit.equalsIgnoreCase(unit)}">
                                                class="currentOption" selected
                                            </c:if>
                                            value="${unit}">${unit}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>

                        <%-- merges row and col divs with content to save space and time --%>
                    <div class="row form-group mt-2">
                        <label for="buyPricePerUnit" class="col col-form-label">Købspris per Enhed:</label>
                        <input class="col form-control" type="number" name="buyPricePerUnit" id="buyPricePerUnit"
                               min="0" step="any"
                               value="<c:if test="${requestScope.action.equals('edit')}">${requestScope.material.buyPricePerUnitString}</c:if>">
                    </div>

                    <div class="row form-group mt-2">
                        <label for="pricePerUnit" class="col col-form-label">Pris per Enhed:</label>
                        <input class="col form-control" type="number" name="pricePerUnit" id="pricePerUnit" min="0"
                               step="any"
                               value="<c:if test="${requestScope.action.equals('edit')}">${requestScope.material.pricePerUnitString}</c:if>">
                    </div>

                    <div class="row form-group mt-2">
                        <label for="length" class="col col-form-label">Længde:</label>
                        <input class="col form-control" type="number" name="length" id="length" min="0" step="1"
                               value="<c:if test="${requestScope.action.equals('edit')}">${requestScope.material.length}</c:if>">
                    </div>

                    <div class="row form-group mt-2">
                        <label for="width" class="col col-form-label">Bredde:</label>
                        <input class="col form-control" type="number" name="width" id="width" min="0" step="1"
                               value="<c:if test="${requestScope.action.equals('edit')}">${requestScope.material.width}</c:if>">
                    </div>

                    <div class="row form-group mt-2">
                        <label for="height" class="col col-form-label">Højde:</label>
                        <input class="col form-control" type="number" name="height" id="height" min="0" step="1"
                               value="<c:if test="${requestScope.action.equals('edit')}">${requestScope.material.height}</c:if>">
                    </div>

                    <!-- ADDING MORE THAN ONE FUNCTIONALITY MIGHT NOT BE POSSIBLE AT THE MOMENT -->
                    <div class="row form-group mt-2">
                        <label for="functionality" class="col col-form-label">Funktion:</label>
                        <select class="col form-control" name="functionality" id="functionality">
                            <c:forEach var="functionality" items="${requestScope.functionalities}">
                                <option
                                        <c:if test="${requestScope.action.equals('edit') and requestScope.material.functionality.equalsIgnoreCase(functionality)}">
                                            class="currentOption" selected
                                        </c:if>
                                        value="${functionality}">${functionality}
                                </option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="text-center mt-3">
                        <c:if test="${requestScope.action.equals('create')}">
                            <button type="submit" class="btn btn-primary" name="action" value="confirmcreation">
                                Tilføj
                            </button>
                        </c:if>
                        <c:if test="${requestScope.action.equals('edit')}">
                            <input type="hidden" name="materialID" value="${requestScope.material.materialID}">
                            <button type="submit" class="btn btn-warning" name="action" value="confirmedit">
                                Rediger
                            </button>
                        </c:if>
                    </div>
                </form>
            </div>
        </div>
    </jsp:body>
</t:genericpage>
