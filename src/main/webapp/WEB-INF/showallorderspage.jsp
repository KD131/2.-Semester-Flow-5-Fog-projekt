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
                            <td>${order.carportLength}</td>
                            <td>${order.carportWidth}</td>
                            <td>${order.shedLength}</td>
                            <td>${order.shedWidth}</td>
                            <td><a href="#">IKKE LAVET</a></td>
                            <td>
                                <form action="${pageContext.request.contextPath}/fc/deleteorder" method="post">
                                    <input type="hidden" name="orderId" value="${order.orderId}">
                                    <input type="submit" class="btn btn-danger" value="Slet ordre">
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
