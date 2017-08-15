<%--
  Created by IntelliJ IDEA.
  User: Hatake
  Date: 8/12/2017
  Time: 4:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cart Information</title>
</head>
<body>
<table border="1">
    <thead>
    <tr>
        <th>ProductName</th>
        <th>Quantity</th>
        <th>Unit Price</th>
        <th>Total</th>
        <th>Action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="items" items="${sessionScope.cart}">
        <tr>
            <td>${items.product.productName}</td>
            <td>${items.quantity}</td>
            <td>${items.product.cost}</td>
            <td>${items.product.cost*items.quantity}</td>
            <td>
                <form action="<c:url value='/delete' />" method="post">
                    <div class="buttons">
                        <input type="hidden" name="id" value="${items.product.productId}"/>
                        <input type="hidden" name="qty" value="${items.quantity}"/>

                        <button type="submit">Delete</button>
                    </div>
                </form>
            </td>

        </tr>
    </c:forEach>
    </tbody>
</table>

</table>
<a href="<c:url value='/getAddress'/>">CheckOut product</a>
<a href="<c:url value='/index'/>">Continue Shopping</a>
</body>
</html>
