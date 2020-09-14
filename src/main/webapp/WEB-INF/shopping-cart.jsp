
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Shopping Cart</title>
</head>
<body>
    <% if (session.getAttribute("CURRENT_SHOPPING_CART") != null) {%>
    Total count = ${CURRENT_SHOPPING_CART.totalCount}<br>
    Products:<br> ${CURRENT_SHOPPING_CART.view}
    <% } else {%>
    Shopping cart is null
    <% } %>
    <c:choose>
        <c:when test="${CURRENT_SHOPPING_CART != null}">
            Total count = ${CURRENT_SHOPPING_CART.totalCount}<br>
            Products: <br>
            <c:forEach var="it" items="${CURRENT_SHOPPING_CART.items}">
                ${it.productId } -&gt;${it.count}<br>
            </c:forEach>
        </c:when>
    </c:choose>
</body>
</html>
