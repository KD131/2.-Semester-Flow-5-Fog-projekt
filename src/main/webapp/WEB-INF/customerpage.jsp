<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:genericpage>
    <jsp:attribute name="header">
         Carport Kunde Profil
    </jsp:attribute>
    <jsp:attribute name="footer">
    </jsp:attribute>

    <jsp:body>
        <h1 class="text-center">Hej ${sessionScope.email}!</h1><br/><br/>
        <p class="text-center">Hop til <a href="${pageContext.request.contextPath}">forsiden</a> for at bestille en carport!</p>
        <br/>
        <p class="text-center">Hop <a href="${pageContext.request.contextPath}/fc/showordersbyid">her hen</a> for at se dine bestillinger!</p>
    </jsp:body>

</t:genericpage>

