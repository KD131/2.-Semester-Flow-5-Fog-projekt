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
<<<<<<< HEAD
        <h1>Hello ${sessionScope.email} </h1>
        You are now logged in as a EMPLOYEE of our wonderful site.



=======
        <h1>Hej ${sessionScope.email}!</h1>
        <p>Klik <a href="${pageContext.request.contextPath}/fc/showallorders">her</a> for at se en oversigt over bestillinger.</p>
>>>>>>> 44d360fdd2dbf87042c89302f4ffb92b2caa1bc5
    </jsp:body>

</t:genericpage>
