<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:genericpage>
    <jsp:attribute name="header">
         All orders
    </jsp:attribute>
    <jsp:attribute name="footer">
    </jsp:attribute>
    <jsp:body>
        <h1>All customer orders</h1>
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
                        <th>Stykliste</th>
                        <th>Slet ordre</th>
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
                                <select form="update${status.count}" name="carportLength" id="carportLength">
                                    <c:forEach var="var" begin="240" end="780" step="30">
                                        <option
                                                <c:if test="${var == order.carportLength}">
                                                    style="font-weight: bold" selected
                                                </c:if>
                                                value="${var}">${var} cm
                                        </option>
                                    </c:forEach>
                                </select></td>
                            <td>
                                <select form="update${status.count}" name="carportWidth" id="carportWidth">
                                    <c:forEach var="var" begin="240" end="750" step="30">
                                        <option
                                                <c:if test="${var == order.carportWidth}">
                                                    style="font-weight: bold" selected
                                                </c:if>
                                                value="${var}">${var} cm
                                        </option>
                                    </c:forEach>
                                </select></td>


                            <td>
                                <select form="update${status.count}" name="shedLength" id="shedLength">
                                    <option
                                            <c:if test="${0 == order.shedLength}">
                                                style="font-weight: bold" selected
                                            </c:if>
                                            value="0">0 cm
                                    </option>
                                    <c:forEach var="var" begin="150" end="690" step="30">
                                    <option
                                            <c:if test="${var == order.shedLength}">
                                                style="font-weight: bold" selected
                                            </c:if>
                                            value="${var}">${var} cm
                                    </option>
                                    </c:forEach>
                                </select></td>

                            <td>
                                <select form="update${status.count}" name="shedWidth" id="shedWidth">
                                    <option
                                            <c:if test="${0 == order.shedWidth}">
                                                style="font-weight: bold" selected
                                            </c:if>
                                            value="0">0 cm
                                    </option>
                                    <c:forEach var="var" begin="150" end="720" step="30">
                                    <option
                                            <c:if test="${var == order.shedWidth}">
                                                style="font-weight: bold" selected
                                            </c:if>
                                            value="${var}">${var} cm
                                    </option>
                                    </c:forEach>
                                </select></td>
                            <td><a href="#">IKKE LAVET</a></td>
                            <td>
                                <form action="${pageContext.request.contextPath}/fc/deleteorder" method="post">
                                    <input type="hidden" name="orderID" value="${order.orderId}">
                                    <input type="submit" class="btn btn-danger" value="Slet ordre">
                                </form>
                                <form action="${pageContext.request.contextPath}/fc/confirmorder" method="post">
                                    <input type="hidden" name="orderID" value="${order.orderId}">
                                    <input type="submit" class="btn btn-danger" value="Confirm ordre">
                                </form>
                                <form action="${pageContext.request.contextPath}/fc/unconfirmorder" method="post">
                                    <input type="hidden" name="orderID" value="${order.orderId}">
                                    <input type="submit" class="btn btn-danger" value="Unconfirm ordre">
                                </form>

                                <form id="update${status.count}" method="post">
                                    <input type="hidden" name="orderID" value="${order.orderId}">
                                    <input type="submit" formaction="${pageContext.request.contextPath}/fc/updatedimensions" class="btn btn-danger" value="Update dimensions">
                                    <input type="submit" formaction="${pageContext.request.contextPath}/fc/showsvg" class="btn btn-danger" value="Show svg drawing">
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </c:when>
            <c:otherwise>
                <p>No orders in database.</p>
            </c:otherwise>
        </c:choose>

    </jsp:body>
</t:genericpage>
