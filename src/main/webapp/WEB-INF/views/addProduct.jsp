<%--
  Created by IntelliJ IDEA.
  User: Niroj
  Date: 8/12/2017
  Time: 12:17 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Add Product</title>
    <link rel="stylesheet" href="../css/addProduct.css">
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
          crossorigin="anonymous">

    <!-- Optional theme -->
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
          integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp"
          crossorigin="anonymous">
    <!-- Latest compiled and minified JavaScript -->
    <script
            src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
            integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
            crossorigin="anonymous"></script>
</head>
<body>
<%--style="width: 55%; margin: auto; border: 3px solid rgb(0, 191, 255); padding: 20px"--%>
<div id="container">
    <form action="/addProduct" method="post" enctype="multipart/form-data">
        <div class="form-group required">
            <label class="control-label col-md-4 " style="margin: auto" > Add Product </label>
        </div>
        <div class="form-group required">
            <label class="control-label col-md-4  requiredField">Category
            </label>
            <div class="controls col-md-8 ">
                <select name="catagoryId">
                    <c:forEach var="catagory" items="${catagories}">
                        <option value="${catagory.categoryId}">${catagory.categoryName}</option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <div class="form-group required">
            <label class="control-label col-md-4  requiredField"> Name </label>
            <div class="controls col-md-8 ">
                <input class="input-md  textinput textInput form-control"
<<<<<<< HEAD
                       name="productName" placeholder="Name" type="text"/>
=======
                       name="productName" placeholder="Name" type="text" required />
>>>>>>> niroj
            </div>
        </div>
        <div class="form-group required">
            <label class="control-label col-md-4  requiredField"> Cost
            </label>
            <div class="controls col-md-8 ">
                <input class="input-md  textinput textInput form-control"
                       name="cost" placeholder="Cost" type="text" required />
            </div>
        </div>
        <div class="form-group required">
            <label class="control-label col-md-4  requiredField">Quantity
            </label>
            <div class="controls col-md-8 ">
                <input class="input-md  textinput textInput form-control"
<<<<<<< HEAD
                       name="quantityAvailable" placeholder="Quantity" type="text"/>
=======
                       name="quantityAvailable" placeholder="Quantity" type="text" required />
>>>>>>> niroj
            </div>
        </div>
        <div class="form-group required">
            <label class="control-label col-md-4  requiredField">
                Product Description </label>
            <div class="controls col-md-8 ">
					<textarea class="input-md  textinput textInput form-control"
<<<<<<< HEAD
                              name="description"></textarea>
=======
                              name="description" required></textarea>
>>>>>>> niroj
            </div>
        </div>
        <div class="container">
            <div class="col-md-6">
                <div class="form-group">
                    <label>Upload Image</label>
                    <div class="form-group">
                        <input type="file" id="imgInp" name="imageName"
<<<<<<< HEAD
                               class="form-control btn btn-default">
=======
                               class="form-control btn btn-default" required>
>>>>>>> niroj
                    </div>
                </div>
            </div>
        </div>

        <br/>
        <div class="buttons">
            <button type="submit" class="btn btn-primary">Add Product</button>
        </div>

    </form>
    <form action="<c:url value='/productPage' />" method="post">
        <div class="buttons">
            <button type="submit" class="btn btn-primary">Home</button>
        </div>
    </form>

</div>

<script
        src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

<script type="text/javascript" src="static/js/fileupload.js"></script>

</body>
</html>