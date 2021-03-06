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
        <h1>Alle ordre for: ${sessionScope.user.email}</h1>
        <c:choose>
            <c:when test="${not empty requestScope.orderListings}">
                <table class="table table-striped table-hover">
                    <thead>
                    <tr>
                        <th>Ordre ID</th>
                        <th>Email</th>
                        <th>Status</th>
                        <th>Pris</th>
                        <th>Dato</th>
                        <th>Carport længde</th>
                        <th>Carport bredde</th>
                        <th>Skur længde</th>
                        <th>Skur bredde</th>
                        <th>Tegning</th>
                        <th>Stykliste</th>

                    </tr>
                    </thead>
                    <c:forEach var="order" items="${requestScope.orderListings}">
                        <tr>
                            <td>${order.orderId}</td>
                            <td>${order.email}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${order.status.equals('Godkendt')}">
                                        <form action="${pageContext.request.contextPath}/fc/payorder" method="post">
                                            <input type="hidden" name="orderID" value="${order.orderId}">
                                            <input type="submit" class="btn btn-success" value="Betal ordre">
                                        </form>
                                    </c:when>
                                    <c:otherwise>
                                        ${order.status}
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <!-- vises kun hvis ordrestatus er godkendt eller højere-->
                            <td>
                                <c:set var="permitTotal" value="${{'Godkendt', 'Betalt', 'Afsluttet'}}"/>
                                <c:choose>
                                    <c:when test="${permitTotal.contains(order.status)}">
                                        ${order.total}
                                    </c:when>
                                    <c:otherwise>
                                        Venter behandling
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>${order.date}</td>
                            <td>${order.carportLength}</td>
                            <td>${order.carportWidth}</td>
                            <td>${order.shedLength}</td>
                            <td>${order.shedWidth}</td>
                            <!-- vises kun hvis ordrestatus er bestilt eller højere-->
                            <td>
                                <c:set var="permitSVG" value="${{'Bestilt', 'Godkendt', 'Betalt', 'Afsluttet'}}"/>
                                <c:if test="${permitSVG.contains(order.status)}">
                                    <form action="${pageContext.request.contextPath}/fc/showsvg" method="post">
                                        <input type="hidden" name="orderID" value="${order.orderId}">
                                        <input type="hidden" name="carportWidth" value="${order.carportWidth}">
                                        <input type="hidden" name="carportLength" value="${order.carportLength}">
                                        <input type="hidden" name="shedWidth" value="${order.shedWidth}">
                                        <input type="hidden" name="shedLength" value="${order.shedLength}">
                                        <input type="submit" class="btn btn-primary" value="Vis tegning">
                                    </form>
                                </c:if>
                            </td>
                            <!-- vises kun hvis ordrestatus er betalt eller højere-->
                            <td>
                                <c:set var="permitBOM" value="${{'Betalt', 'Afsluttet'}}"/>
                                <c:if test="${permitBOM.contains(order.status)}">
                                    <form action="${pageContext.request.contextPath}/fc/showbomcustomer" method="post">
                                        <input type="hidden" name="orderID" value="${order.orderId}">
                                        <input type="submit" class="btn btn-primary" value="Vis stykliste">
                                    </form>
                                </c:if>
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
