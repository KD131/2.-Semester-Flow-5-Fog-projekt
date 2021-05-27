<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:genericpage>
    <jsp:attribute name="header">
         Ordrebekræftelse
    </jsp:attribute>
    <jsp:attribute name="footer">
    </jsp:attribute>

    <jsp:body>
        <h1 class="text-center">Ordre bestilt!</h1><br/><br/>
        <p class="text-center">Tillykke ${sessionScope.email}! Du har bestilt en ny carport.</p>
        <p>Din ordre venter bekræftelse af en medarbejder. Der vil der blive sat pris på ordren.</p>
        <br/>
        <p class="text-center">Hop <a href="${pageContext.request.contextPath}/fc/showcustomerorderspage">her hen</a> for at se dine bestillinger!</p>
    </jsp:body>

</t:genericpage>

