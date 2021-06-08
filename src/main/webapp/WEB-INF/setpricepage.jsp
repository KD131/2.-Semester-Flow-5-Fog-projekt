<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:genericpage>
    <jsp:attribute name="header">
         Sæt pris for ordre
    </jsp:attribute>
    <jsp:attribute name="footer">
    </jsp:attribute>
    <jsp:body>
        <div class="row">
            <div class="col-lg-4 mx-auto">
                <h1>Sæt pris for ordre</h1>
                <div class="row">
                    <div class="col">
                        <p class="col-form-label">Nuværende pris:</p>
                        <p class="col-form-label">Pris ud fra stykliste:</p>
                        <label class="col-form-label" for="total">Sæt pris:</label>
                    </div>

                    <div class="col">
                        <form id="newTotal" action="${pageContext.request.contextPath}/fc/confirmorder" method="post">
                            <p class="col-form-label">${requestScope.currTotal}</p>
                            <p class="col-form-label">${requestScope.BOMTotal}</p>
                            <input class="form-control" type="number" name="total" id="total" min="0" step="any" required
                                   value="${requestScope.BOMTotal}">
                            <input type="hidden" name="orderID" value="${requestScope.orderID}">
                        </form>
                    </div>
                </div>
                <button type="submit" form="newTotal" class="btn btn-primary mt-2 w-auto" name="action" value="confirm">
                    Bekræft ordre
                </button>
            </div>
        </div>
    </jsp:body>
</t:genericpage>
