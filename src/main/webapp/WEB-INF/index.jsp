<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:genericpage>

    <jsp:attribute name="header">
         Home
    </jsp:attribute>

    <jsp:attribute name="footer">
        <c:set var="addHomeLink" value="${false}" scope="request"/>
    </jsp:attribute>

    <jsp:body>

        <div>
            <h2>Carport Projekt</h2>

            <div style="margin-top: 3em;margin-bottom: 3em;">
                Forsiden til gruppe C5's Carport Projekt
            </div>

            <c:if test="${sessionScope.role == 'employee' }">
                <p style="font-size: larger">Her er hvad du kan, siden du er logget ind som Administrator</p>
                 <p><a href="fc/employeepage">Administrator Side</a>
             </c:if>

             <c:if test="${sessionScope.role == 'customer' }">
                <p style="font-size: larger">Her er hvad du kan, siden du er logget ind som Kunde</p>
            <form action="${pageContext.request.contextPath}/fc/submitorder" method="post">
                <label for="carportWidth">Carport bredde:</label>
                <select name="carportWidth" id="carportWidth">
                    <c:forEach var="var" begin="240" end="750" step="30">
                        <option value="${var}">${var} cm</option>
                    </c:forEach>
                </select>

                <label for="carportLength">Carport længde:</label>
                <select name="carportLength" id="carportLength">
                    <c:forEach var="var" begin="240" end="780" step="30">
                        <option value="${var}">${var} cm</option>
                    </c:forEach>
                </select>

                <p>Vil du have skur til?</p>
                <input type="radio" id="yes" name="shed" value="yes">
                <label for="yes">Ja</label>
                <input type="radio" id="no" name="shed" value ="no" checked="checked">
                <label for="no">Nej</label>

                <br/>

                <label for="shedWidth">Skur bredde:</label>
                <select name="shedWidth" id="shedWidth">
                    <option value="noShed">ingen skur tak</option>
                    <c:forEach var="var" begin="210" end="720" step="30">
                        <option value="${var}">${var} cm</option>
                    </c:forEach>
                </select>

                <label for="shedLength">Skur længde:</label>
                <select name="shedLength" id="shedLength">
                    <option value="noShed">ingen skur tak</option>
                    <c:forEach var="var" begin="150" end="690" step="30">
                        <option value="${var}">${var} cm</option>
                    </c:forEach>
                </select>


                <input type="submit" value="Send ordre">
            </form>
            <c:if test="${requestScope.error != null}">
                <p style="color:red">${requestScope.error}</p>
            </c:if>
                <p><a href="fc/customerpage">Kunde Side</a>
            </c:if>

        </div>

    </jsp:body>
</t:genericpage>