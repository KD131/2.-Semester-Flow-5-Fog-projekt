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
            <h2 class="text-center">Carport Projekt</h2>

            <div style="margin-top: 3em;margin-bottom: 3em;">
                <p class="text-center">Forside til Gruppe C5's Carport Projekt</p>
            </div>


            <c:if test="${sessionScope.role == 'employee' }">
                 <p class="text-center mt-5"><a href="${pageContext.request.contextPath}/fc/employeepage">Administrator Side</a>
             </c:if>


             <c:if test="${sessionScope.role == 'customer' }">
                <p class="text-center" style="font-size: large">Bestil en carport:</p>
            <form action="${pageContext.request.contextPath}/fc/submitorder" method="post">
                <div class="text-center mt-4">
                    <label for="carportWidth">Carport bredde:</label>
                </div>
                <div class="text-center">
                    <select name="carportWidth" id="carportWidth">
                        <option value="noShed">Vælg bredde</option>
                        <c:forEach var="var" begin="240" end="750" step="30">
                            <option value="${var}">${var} cm</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="text-center mt-2">
                    <label for="carportLength">Carport længde:</label>
                </div>
                <div class="text-center">
                    <select name="carportLength" id="carportLength">
                        <option value="noShed">Vælg længde</option>
                        <c:forEach var="var" begin="240" end="780" step="30">
                            <option value="${var}">${var} cm</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="text-center mt-4">
                    <p>Vil du have skur til?</p>
                    <div class="mt-0">
                        <input type="radio" id="yes" name="shed" value="yes">
                        <label for="yes">Ja</label>
                        <input type="radio" id="no" name="shed" value ="no" checked="checked">
                        <label for="no">Nej</label>
                    </div>
                </div>

                <div class="text-center mt-4">
                    <label for="shedWidth">Skur bredde:</label>
                </div>
                <div class="text-center">
                    <select name="shedWidth" id="shedWidth">
                        <option value="noShed">Ønsker ikke skur</option>
                        <c:forEach var="var" begin="210" end="720" step="30">
                            <option value="${var}">${var} cm</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="text-center mt-2">
                    <label for="shedLength">Skur længde:</label>
                </div>
                <div class="text-center">
                    <select name="shedLength" id="shedLength">
                        <option value="noShed">Ønsker ikke skur</option>
                        <c:forEach var="var" begin="150" end="690" step="30">
                            <option value="${var}">${var} cm</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="text-center mt-4">
                    <input type="submit" value="Send ordre">
                </div>
            </form>

            <c:if test="${requestScope.error != null}">
                <p style="color:red">${requestScope.error}</p>
            </c:if>
                <p class="text-center mt-5"><a href="${pageContext.request.contextPath}/fc/customerpage">Kunde Side</a>
            </c:if>

        </div>
    </jsp:body>
</t:genericpage>