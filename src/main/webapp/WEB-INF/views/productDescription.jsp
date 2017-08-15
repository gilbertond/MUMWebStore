<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <meta charset="utf-8">
    <title>Product Descriptions</title>
    <link href="../css/productDescription.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="container">
    <p>${product.productName }</p>
    <label><img class="image" src="images/${product.image }"
                alt="#">
        <p>${product.description }</p>
        <p>Price: $${product.cost }</p>
        <p>Quantity: ${product.quantityAvailable }</p></label> <br/> <br/>
    <form action='<c:url value="/addToCart"/>' method="post">
        <input type="number" placeholder="Enter Quantity" name="qty"/>
        <input type="hidden" value="${product.productId}" name="productId">
        <button type="submit" value="submit">Add to cart</button>
    </form>
    <form action="<c:url value='/productPage' />" method="post">
        <div class="buttons">
            <button type="submit">Home</button>
        </div>
    </form>
</div>

</body>
</html>