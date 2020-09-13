
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
</body>
</html>
