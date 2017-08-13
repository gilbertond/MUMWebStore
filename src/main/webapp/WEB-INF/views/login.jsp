<%--
  Created by IntelliJ IDEA.
  User: Niroj
  Date: 8/12/2017
  Time: 12:18 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>MUM Store Login</title>
        <link rel="stylesheet" href="../css/login-style.css">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet"
              href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
              integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
              crossorigin="anonymous">
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
        <div id="container">
            <h1>MUM WEB STORE</h1>
            <sec:authorize access="hasRole('ROLE_ANONYMOUS')">
                <div class="login-section">
                    <h2>Sign in</h2>
                    <form id="loginForm" method="post" action="/login">
                        <div class="form-group">
                            <label>Email address</label> 
                            <input type="email" name="uName" class="form-control" id="exampleInputEmail1" placeholder="root@emal.com">
                        </div>
                        <div class="form-group">
                            <label>Password</label> 
                            <input type="password" name="pWord" class="form-control" placeholder="Test123" pattern="((?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,})">
                        </div>
                        <div style="height: 50px;margin-top: 10px" class="form-check">
                            <label class="form-check-label"> 
                                ${message}
                                <c:if test="${param.error ne null}">
                                    <div class="alert-danger">Account not found</div>
                                </c:if>
                                <c:if test="${param.logout ne null}">
                                    <div class="alert-normal">Currently logged out.</div>
                                </c:if> 
                                <!--input type="checkbox" name="remember" class="form-check-input"> Remember Me-->
                            </label>
                        </div>
                        <button type="submit" name="login-btn" class="btn btn-primary">Sign in</button>
                        <a href="/signup" name="signup-btn" class="btn btn-info">Sign up</a>                         
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                    </form>
                    
                </div>
            </sec:authorize>
            <sec:authorize access="hasRole('ROLE_USER')">
                <script>
                    window.location.href = "/user";
                </script> 
            </sec:authorize>
        </div>
    </body>
</html>