<%-- 
    Document   : manageUsers
    Created on : Aug 13, 2017, 4:08:05 PM
    Author     : gilberto
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Users</title>
    </head>
    <body>
        <div id="usersDiv" align="center">
            <c:choose>
                <c:when test="${empty users}">
                    <span class="alert alert-info">No users registered yet</span>
                </c:when>
                <c:otherwise>     
                    <div style="max-height: 200px;" class="table-responsive">
                        <table width="100%" cellpadding="0" cellspacing="0" border="0" id="table" class="table">
                            <thead>
                                <tr>
                                    <th>#</th>
                                    <th>Firtname</th>
                                    <th>Lastname</th>
                                    <th>Email</th>
                                    <th>Status</th>
                                    <th>Role</th>
                                    <th>Update/Delete</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${users}" varStatus="status" var="user">
                                    <tr style="">  
                                        <td style="width: 5%">
                                            ${status.count}
                                        </td>
                                        <td style="width: 10%">
                                            ${user.firstName}
                                        </td>
                                        <td style="width: 10%">
                                            ${user.lastName}
                                        </td>
                                        <td style="width: 20%">
                                            ${user.email}
                                        </td>
                                        <td style="width: 5%">
                                            ${user.active? 'ACTIVE':'DISABLED'}
                                        </td>      
                                        <td style="width: 20%">
                                            ${user.roles}
                                        </td>
                                <td style="width: 20%;text-align: center">
                                    <a href="#" id="editUser" onclick="callAjaxRequest('/updateUser', 'divAddService', {task: 'edit', id: ${user.userId}}, 'GET');" 
                                       style="color: blueviolet;font-size: 1.2em;" class="glyphicon glyphicon-edit">
                                    </a>
                                    &nbsp;
                                    <a href="#" id="deleteUser" onclick=" if (confirm('Delete User ${user.firstName} ${user.lastName}')){
                                        callAjaxRequest('/deleteUser', 'divAddService', {task: 'delete', id: ${user.userId}}, 'GET'); 
                                        }" style="color: blueviolet;font-size: 1.2em;" class="glyphicon glyphicon-trash">
                                        </a>
                                    </td>
                                    </tr>
                            </c:forEach>                                                    
                            </tbody>
                        </table>    
                    </div>                                
                </c:otherwise>
            </c:choose>                                        
        </div>
    </body>
</html>
