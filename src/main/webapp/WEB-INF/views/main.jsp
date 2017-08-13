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
        <sec:authorize access="hasRole('ROLE_USER')">
            <div id="container">
                <div class="row">
                    <div style="width: 99%" class="panel panel-primary">
                        <div class="panel-heading">
                            <div class="row">
                                <div style="width: 60%" class="col-md-6 text-left text-uppercase h4">
                                    MUM Web Store<span id="serviceMsg" class="alert alert-info h6" style="display: none;width: 200px;opacity: .7;"></span>
                                </div>                            
                                <div style="width: 40%" class="col-md-6 text-right">                                
                                    <c:if test="${pageContext.request.userPrincipal.name != null}">
                                        <c:url var="logoutUrl" value="/logout"/>                                                                        
                                        <form action="${logoutUrl}" method="post">                                        
                                            <strong>
                                                <span class="glyphicon glyphicon-user"></span>&nbsp;
                                                ${pageContext.request.userPrincipal.name}<%--<c:out value="${pageContext.request.remoteUser}"></c:out>--%>
                                            </strong> | <input class="btn btn-primary btn-sm" type="submit" value="Sign Out"/>
                                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                        </form>
                                    </c:if>
                                </div>
                            </div>
                        </div>   
                    </div>                    
                </div>
            </div>
        </sec:authorize>
        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <!-- Include all compiled plugins (below), or include individual files as needed -->
        <script src="js/bootstrap.min.js"></script>
    </body>
</html>