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
                        <th>Total</th>
                        <th>Status</th>
                        <th>Dato</th>
                        <th>Carport længde</th>
                        <th>Carport bredde</th>
                        <th>Skur længde</th>
                        <th>Skur bredde</th>
                        <th>pris</th>
                        <th>tegning</th>
                        <th>Stykliste</th>

                    </tr>
                    </thead>
                    <c:forEach var="order" items="${requestScope.orderListings}">
                        <tr>
                            <td>${order.orderId}</td>
                            <td>${order.email}</td>
                            <td>${order.total}</td>
                            <td>${order.status}</td>
                            <td>${order.date}</td>
                            <td>${order.carportLength}</td>
                            <td>${order.carportWidth}</td>
                            <td>${order.shedLength}</td>
                            <td>${order.shedWidth}</td>
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
                            <!-- vises kun hvis ordrestatus er bestilt eller højere-->
                            <td>
                                <c:set var="permitSVG" value="${{'Bestilt', 'Godkendt', 'Betalt', 'Afsluttet'}}"/>
                                <c:if test="${permitSVG.contains(order.status)}">
                                    <a href="${pageContext.request.contextPath}/fc/showSVGcustomer">Vis Tegning</a>
                                </c:if>
                            </td>
                            <!-- vises kun hvis ordrestatus er betalt eller højere-->
                            <td>
                                <c:set var="permitBOM" value="${{'Betalt', 'Afsluttet'}}"/>
                                <c:if test="${permitBOM.contains(order.status)}">
                                    <a href="${pageContext.request.contextPath}/fc/showBOMcustomer">Vis Stykliste</a>
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
