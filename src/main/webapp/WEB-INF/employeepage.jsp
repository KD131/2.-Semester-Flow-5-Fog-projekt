<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:genericpage>
    <jsp:attribute name="header">
         Carport Admin Profil
    </jsp:attribute>
    <jsp:attribute name="footer">
    </jsp:attribute>
    <jsp:body>

        <h1>Hej ${sessionScope.email}!</h1>
        <p>Klik <a href="${pageContext.request.contextPath}/fc/showallorderspage">her</a> for at se en oversigt over bestillinger.</p>
        <a href="${pageContext.request.contextPath}/fc/materialslist">Liste of alle materialer.</a>

    </jsp:body>

</t:genericpage>
