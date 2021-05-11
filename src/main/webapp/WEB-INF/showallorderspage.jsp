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
                        <th>Carport Bredde</th>
                        <th>Skur længde</th>
                        <th>Skur bredde</th>
                        <th>Stykliste</th>
                        <th>Slet ordre</th>
                    </tr>
                    </thead>
                    <c:forEach var="order" items="${requestScope.orderListings}">
                        <tr>
                            <td>${order.orderId}</td>
                            <td>${order.email}</td>
                            <td>${order.total}</td>
                            <td>${order.profitMargin}</td>
                            <td>${order.status}</td>
                            <td>${order.date}</td>
                            <td><select name="carportLength" id="carportLength">
                                <option value="${order.carportLength}"> ${order.carportLength} cm</option>
                                <c:forEach var="var" begin="240" end="780" step="30">
                                    <option value="${var}">${var} cm</option>
                                </c:forEach>
                            </select></td>
                            <td><select name="carportWidth" id="carportWidth">
                                <option value="${order.carportWidth}"> ${order.carportWidth} cm </option>
                                <c:forEach var="var" begin="240" end="750" step="30">
                                    <option value="${var}">${var} cm</option>
                                </c:forEach>
                            </select></td>


                            <td><select name="shedLength" id="shedLength">
                                <option value="${order.shedLength}"> ${order.shedLength} cm</option>
                                <c:forEach var="var" begin="150" end="690" step="30">
                                <option value="${var}">${var} cm </option>
                                </c:forEach>
                            </td>

                            <td><select name="shedWidth" id="shedWidth">
                                <option value="${order.shedWidth}"> ${order.shedWidth} cm </option>
                                <c:forEach var="var" begin="150" end="720" step ="30">
                                    <option value="${var}">${var} cm</option>
                                </c:forEach>
                            </td>
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
                                <form action="${pageContext.request.contextPath}/fc/updatedimensions" method="post">
                                    <input type="hidden" name="orderID" value="${order.orderId}">
                                    <input type="submit" class="btn btn-danger" value="Update dimensions">
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
