<%-- 
    Document   : 403
    Created on : Aug 12, 2017, 5:37:34 PM
    Author     : 985939
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Denied</title>
    </head>
    <body>   
        <div class="alert-danger">
            <h3>Un authorised access</h3> 
        </div>
        <form action="/logout" method="post">
            <input type="submit" class="button red big" value="Login to access" /> 
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
        </form>   
    </body>
</html>