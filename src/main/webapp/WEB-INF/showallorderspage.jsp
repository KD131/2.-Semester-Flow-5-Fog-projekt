<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:genericpage>
    <jsp:attribute name="header">
         Alle ordrer
    </jsp:attribute>
    <jsp:attribute name="footer">
    </jsp:attribute>
    <jsp:body>
        <h1>Alle ordrer</h1>

        <c:if test="${requestScope.error != null}">
            <p class="error">${requestScope.error}</p>
        </c:if>

        <c:choose>
            <c:when test="${not empty requestScope.orderListings}">
                <table class="table table-striped table-hover">
                    <thead>
                    <tr>
                        <th>Ordre ID</th>
                        <th>Email</th>
                        <th>Total</th>
                        <th>Profit margen</th>
                        <th>Status</th>
                        <th>Dato</th>
                        <th>Carport længde</th>
                        <th>Carport bredde</th>
                        <th>Skur længde</th>
                        <th>Skur bredde</th>
                        <th></th>
                        <th></th>
                    </tr>
                    </thead>
                    <c:forEach var="order" items="${requestScope.orderListings}" varStatus="status">
                        <tr>
                            <td>${order.orderId}</td>
                            <td>${order.email}</td>
                            <td>${order.total}</td>
                            <td>${order.profitMargin}</td>
                            <td>${order.status}</td>
                            <td>${order.date}</td>
                            <td>
                                <select class="form-select w-auto"  form="update${status.count}" name="carportLength" id="carportLength">
                                    <c:forEach var="var" begin="240" end="780" step="30">
                                        <option
                                                <c:if test="${var == order.carportLength}">
                                                    class="currentOption" selected
                                                </c:if>
                                                value="${var}">${var} cm
                                        </option>
                                    </c:forEach>
                                </select></td>
                            <td>
                                <select class="form-select w-auto"  form="update${status.count}" name="carportWidth" id="carportWidth">
                                    <c:forEach var="var" begin="240" end="750" step="30">
                                        <option
                                                <c:if test="${var == order.carportWidth}">
                                                    class="currentOption" selected
                                                </c:if>
                                                value="${var}">${var} cm
                                        </option>
                                    </c:forEach>
                                </select></td>


                            <td>
                                <select class="form-select w-auto"  form="update${status.count}" name="shedLength" id="shedLength">
                                    <option
                                            <c:if test="${0 == order.shedLength}">
                                                class="currentOption" selected
                                            </c:if>
                                            value="0">0 cm
                                    </option>
                                    <c:forEach var="var" begin="150" end="690" step="30">
                                    <option
                                            <c:if test="${var == order.shedLength}">
                                                class="currentOption" selected
                                            </c:if>
                                            value="${var}">${var} cm
                                    </option>
                                    </c:forEach>
                                </select></td>

                            <td>
                                <select class="form-select w-auto" form="update${status.count}" name="shedWidth" id="shedWidth">
                                    <option
                                            <c:if test="${0 == order.shedWidth}">
                                                class="currentOption" selected
                                            </c:if>
                                            value="0">0 cm
                                    </option>
                                    <c:forEach var="var" begin="150" end="720" step="30">
                                    <option
                                            <c:if test="${var == order.shedWidth}">
                                                class="currentOption" selected
                                            </c:if>
                                            value="${var}">${var} cm
                                    </option>
                                    </c:forEach>
                                </select>
                            </td>

                            <td>
                                <form class="mb-1" id="update${status.count}" method="post">
                                    <input type="hidden" name="orderID" value="${order.orderId}">
                                    <input type="submit" formaction="${pageContext.request.contextPath}/fc/updatedimensions" class="mb-1 btn btn-primary" value="Opdater ordre">
                                    <input type="submit" formaction="${pageContext.request.contextPath}/fc/showsvg" class="btn btn-primary" value="Vis tegning">
                                </form>

                                <form class="mb-1" action="${pageContext.request.contextPath}/fc/showbom" method="post">
                                    <input type="hidden" name="orderID" value="${order.orderId}">
<%--                                    <input type="hidden" name="carportWidth" value="${order.carportWidth}">--%>
<%--                                    <input type="hidden" name="carportLength" value="${order.carportLength}">--%>
<%--                                    <input type="hidden" name="shedWidth" value="${order.shedWidth}">--%>
<%--                                    <input type="hidden" name="shedLength" value="${order.shedLength}">--%>
                                    <input type="submit" class="btn btn-primary" value="Vis stykliste">
                                </form>
                            </td>

                            <td>
                                <form class="mb-1" action="${pageContext.request.contextPath}/fc/confirmorder" method="post">
                                    <input type="hidden" name="orderID" value="${order.orderId}">
                                    <input type="hidden" name="total" value="${order.total}">
                                    <button type="submit" class="btn btn-success" name="action" value="setprice">Bekræft ordre</button>
                                </form>
                                <form class="mb-1" action="${pageContext.request.contextPath}/fc/unconfirmorder" method="post">
                                    <input type="hidden" name="orderID" value="${order.orderId}">
                                    <input type="submit" class="btn btn-warning" value="Af-bekræft ordre">
                                </form>
                                <form class="mb-1" action="${pageContext.request.contextPath}/fc/deleteorder" method="post">
                                    <input type="hidden" name="orderID" value="${order.orderId}">
                                    <input type="submit" class="btn btn-danger" value="Slet ordre">
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </c:when>
            <c:otherwise>
                <p>Ingen ordrer i databasen.</p>
            </c:otherwise>
        </c:choose>

    </jsp:body>
</t:genericpage>
