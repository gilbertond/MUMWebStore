<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Hatake
  Date: 8/14/2017
  Time: 10:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Shipping Address</title>
    </head>
    <body>
        <form action="<c:url value='/checkout' />" method="post">
            <div class="panel panel-default">
                <div class="panel-body">
                    <fieldset class="form-group">
                        <legend>Enter your Shipping Address</legend>
                        <div class="form-group">
                            <label>Street</label>
                            <input type="text" name="street" class="form-control" placeholder="street" required>
                        </div>
                        <div class="form-group">
                            <label>City</label>
                            <input type="text" name="city" class="form-control" placeholder="city" required/>
                        </div>
                        <div class="form-group">
                            <label>State</label>
                            <input type="text" name="stateUS" class="form-control" placeholder="state" required/>
                        </div>
                        <div class="form-group">
                            <label>Zip</label> <input type="text" name="zipCode" class="form-control" placeholder="zip" required/>
                        </div>
                        <div class="form-group">
                            <label>Country</label>
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                            <input type="text" name="country" class="form-control" placeholder="country" required/>
                        </div>
                    </fieldset>
                </div>
            </div>
            <div class="buttons">
                <button type="submit">CheckOut</button>
            </div>
        </form>
    </body>
</html>
