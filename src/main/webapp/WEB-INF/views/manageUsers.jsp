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
        <div class="container" id="usersDiv" align="center">
            <div class="row" valign="top">
                ${message}
                <button onclick="callAjaxRequest('/addNewUser', 'usersDiv', 'GET', null);" class="btn btn-info" style="height: 40px;width: 100px;float: left;margin-left: 100px">Add new user</button>                                
            </div>
            <div align="left" class="row" style="width: 100%; margin-right: 40%">                
                <c:choose>
                    <c:when test="${empty users}">
                        <br/>
                        <br/>
                        <div style="width: 80%" class="table-responsive">
                            <span class="alert alert-info">No users registered yet</span>
                        </div>
                    </c:when>
                    <c:otherwise>     
                        <div style="max-height: 200px;width: 80%" class="table-responsive">
                            <table style="width: 100%;" cellpadding="0" cellspacing="0" border="0" id="table" class="table">
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
                                                <a href="#" id="editUser" onclick="alert('under implemetation...'); return;callAjaxRequest('updateUser', 'usersDiv', 'GET', {id: ${user.userId}});" 
                                                   style="color: blueviolet;font-size: 1.2em;" class="glyphicon glyphicon-edit">
                                                </a>
                                                &nbsp;
                                                <a href="#" id="deleteUser" onclick="alert('under implemetation...'); return; if (confirm('Delete User ${user.firstName} ${user.lastName}')) {
                                                            callAjaxRequest('deleteUser', 'usersDiv', 'GET', {id: ${user.userId}});
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
        </div>
    </body>
</html>
