<%--
  Created by IntelliJ IDEA.
  User: Niroj
  Date: 8/13/2017
  Time: 2:05 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link rel="stylesheet" type="text/css" href="../css/product.css">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>Products</title>

    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
          crossorigin="anonymous">

    <!-- Optional theme -->
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
          integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp"
          crossorigin="anonymous">
</head>
<body>
<div id="container">
    <p id="paragraph">Welcome to MUM Web Store</p>
</div>
<nav class="navbar navbar-inverse bg-primary">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <a class="navbar-brand" href="#">Home</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse"
             id="bs-example-navbar-collapse-1">
            <form class="navbar-form navbar-left">
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="Search">
                </div>
                <button type="submit" class="btn btn-info">Search</button>
            </form>

            <ul class="nav navbar-nav navbar-center">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Category
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                        <c:forEach var="catagory" items="${catagories}">
                            <li><a href="<c:url value='/index/?categoryId=${catagory.categoryId}'/>">${catagory.categoryName}</a></li>
                        </c:forEach>

                    </ul>
                </li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li>
                    
                    <c:if test="${pageContext.request.userPrincipal.name != null}">
                            <c:url var="logoutUrl" value="/logout"/>                                                                        
                            <form action="/logout" method="post">                                        
                                <strong>
                                    <span class="glyphicon glyphicon-user"></span>&nbsp;
                                    ${pageContext.request.userPrincipal.name}<%--<c:out value="${pageContext.request.remoteUser}"></c:out>--%>
                                </strong> | <input class="btn btn-primary btn-sm" type="submit" value="Sign Out"/>
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            </form>
                        </c:if>
                </li>
            </ul>
            <%-- ${loggeduser} --%>
            <%--<ul class="nav navbar-nav navbar-right">--%>
                <%--<c:choose>--%>
                    <%--<c:when test="${not empty loggeduser }">--%>
                        <%--<li><a href="javascript:;">${loggeduser}</a></li>--%>
                        <%--<li><a href='<c:url value="/Logout"/>'>Logout</a></li>--%>
                        <%--<li><a href='<c:url value="/cart"/>'>Cart</a></li>--%>
                        <%--<li><a href='<c:url value="/Profile"/>'>View Profile</a></li>--%>

                    <%--</c:when>--%>
                    <%--<c:otherwise>--%>
                        <%--<li><a href='<c:url value="/Login"/>'>Sign In</a></li>--%>
                    <%--</c:otherwise>--%>
                <%--</c:choose>--%>

            <%--</ul>--%>
        </div>
        <!-- /.navbar-collapse -->
    </div>
    <!-- /.container-fluid --> </nav>

<!-- Wrapper for slides -->
<div class="carousel inner">
    <div class="item active">
        <c:forEach items="${products}" var="product">
            <label><a class="productdescription" id="${product.productId }"
                href='<c:url value="/productDescriptionForm?id=${product.productId}"/>'>
                     <img
                    class="image" src='/static/images/${product.image}'
                    alt="${product.productName }"><br/> ${product.productName}
                <br/> $ ${product.cost}</a></label>
        </c:forEach>
    </div>
</div>
<%--<input type="hidden" id="form-submit" value="<c:url value="/AddCart"/>">--%>

<%--<div class="modal fade" id="modalId">--%>
    <%--<div class="modal-dialog" role="document">--%>
        <%--<div class="modal-content">--%>
            <%--<div class="modal-header">--%>
                <%--<h5 class="modal-title">Modal title</h5>--%>
                <%--<button type="button" class="close" data-dismiss="modal"--%>
                        <%--aria-label="Close">--%>
                    <%--<span aria-hidden="true">&times;</span>--%>
                <%--</button>--%>
            <%--</div>--%>
            <%--<div class="modal-body">--%>
                <%--<p>Modal body text goes here.</p>--%>
            <%--</div>--%>
            <%--<div class="modal-footer">--%>
                <%--<button type="button" class="btn btn-primary">Submit</button>--%>
                <%--<button type="button" class="btn btn-secondary"--%>
                        <%--data-dismiss="modal">Close--%>
                <%--</button>--%>
            <%--</div>--%>
        <%--</div>--%>
    <%--</div>--%>
<%--</div>--%>


<!-- Left and right controls -->
<!--<a class="left carousel-control" href="#" data-slide="prev">
        <span class="glyphicon glyphicon-chevron-left"></span> <span
        class="sr-only">Previous</span>
    </a> <a class="right carousel-control" href="#"
        data-slide="next"> <span
        class="glyphicon glyphicon-chevron-right"></span> <span
        class="sr-only">Next</span>
    </a> -->

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script
        src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<!-- Latest compiled and minified JavaScript -->
<script
        src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
        integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
        crossorigin="anonymous"></script>

<script src="static/js/productdescription.js" rel="text/javascript"></script>
</body>
</html>