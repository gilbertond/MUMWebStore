<%-- 
    Document   : menu2
    Created on : Aug 12, 2017, 10:36:01 PM
    Author     : gilberto
--%>
<link rel="stylesheet"
      href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
      integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
      crossorigin="anonymous">

<link rel="stylesheet"
      href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
      integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp"
      crossorigin="anonymous">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
<style>
    body{margin-top:50px;}
    .glyphicon { margin-right:10px; }
    .panel-body { padding:0px; }
    .panel-body table tr td { padding-left: 15px }
    .panel-body .table {margin-bottom: 0px; }
</style>
<script src="static/js/ourAjax.js" type="text/javascript"></script>
<div class="container">
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
                            <form action="/logout" method="post">                                        
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
        <div class="col-sm-3 col-md-3">
            <div class="panel-group" id="accordion">
                <sec:authorize access="hasRole('ROLE_ADMINISTRATOR')">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h4 class="panel-title">
                                <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne"><span class="glyphicon glyphicon-folder-close">
                                    </span>Content</a>
                            </h4>
                        </div>
                        <div id="collapseOne" class="panel-collapse collapse in">
                            <div class="panel-body">
                                <table class="table">
                                    <tr>
                                        <td>
                                            <span class="glyphicon glyphicon-pencil text-primary"></span>
                                            <a href="#">Articles</a>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <span class="glyphicon glyphicon-flash text-success"></span>
                                            <a href="#">News</a>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <span class="glyphicon glyphicon-file text-info"></span>
                                            <a href="#">Newsletters</a>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <span class="glyphicon glyphicon-comment text-success"></span>
                                            <a href="#">Comments</a>
                                            <span class="badge">42</span>
                                        </td>
                                    </tr>
                                </table>
                            </div>
                        </div>
                    </div>
                </sec:authorize>
                <sec:authorize access="hasRole('ROLE_ADMINISTRATOR')">
                    <div style="display: none" class="panel panel-default">
                        <div class="panel-heading">
                            <h4 class="panel-title">
                                <a data-toggle="collapse" data-parent="#accordion" href="#collapseTwo"><span class="glyphicon glyphicon-th">
                                    </span>Modules</a>
                            </h4>
                        </div>
                        <div id="collapseTwo" class="panel-collapse collapse">
                            <div class="panel-body">
                                <table class="table">
                                    <tr>
                                        <td>
                                            <a href="#">Shipments</a>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <a href="#">Tex</a>
                                        </td>
                                    </tr>
                                </table>
                            </div>
                        </div>
                    </div>
                </sec:authorize>
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" data-parent="#accordion" href="#collapseThree"><span class="glyphicon glyphicon-user">
                                </span>Account</a>
                        </h4>
                    </div>
                    <div id="collapseThree" class="panel-collapse collapse">
                        <div class="panel-body">
                            <table class="table">
                                <tr>
                                    <td>
                                        <a href="#">Change Password</a>
                                    </td>
                                </tr>
                                <tr style="display: none">
                                    <td>
                                        <a href="#">Notifications</a> 
                                        <span class="label label-info">5</span>
                                    </td>
                                </tr>
                                <sec:authorize access="hasRole('ROLE_ADMINISTRATOR') and hasRole('ROLE_USER') and hasRole('ROLE_ROOT')">
                                    <tr>
                                        <td>
                                            <a onclick="callAjaxRequest('manageUsers', 'contectDiv', 'GET', {})" href="#">User management</a>
                                        </td>
                                    </tr>
                                </sec:authorize>
                                <tr>
                                    <td>
                                        <span class="glyphicon glyphicon-trash text-danger"></span>
                                        <a href="#" class="text-danger">
                                            Delete Account
                                        </a>
                                    </td>
                                </tr>
                            </table>
                        </div>
                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" data-parent="#accordion" href="#collapseFour"><span class="glyphicon glyphicon-file">
                                </span>Reports</a>
                        </h4>
                    </div>
                    <div id="collapseFour" class="panel-collapse collapse">
                        <div class="panel-body">
                            <table class="table">
                                <sec:authorize access="hasRole('ROLE_ADMINISTRATOR') and hasRole('ROLE_USER') and hasRole('ROLE_ROOT')">
                                    <tr>
                                        <td>
                                            <span class="glyphicon glyphicon-usd"></span><a href="#">Sales</a>
                                        </td>
                                    </tr>
                                </sec:authorize>
                                <sec:authorize access="hasRole('ROLE_ADMINISTRATOR') and hasRole('ROLE_USER') and hasRole('ROLE_ROOT')">
                                    <tr>
                                        <td>
                                            <span class="glyphicon glyphicon-user"></span><a href="#">Customers</a>
                                        </td>
                                    </tr>
                                </sec:authorize>
                                <sec:authorize access="hasRole('ROLE_ADMINISTRATOR') and hasRole('ROLE_USER') and hasRole('ROLE_ROOT')">
                                <tr>
                                    <td>
                                        <span class="glyphicon glyphicon-tasks"></span><a href="#">Products</a>
                                    </td>
                                </tr>
                                </sec:authorize>
                                <sec:authorize access="hasRole('ROLE_CLIENT') and hasRole('ROLE_USER') and hasRole('ROLE_ROOT')">
                                    <tr>
                                        <td>
                                            <span class="glyphicon glyphicon-shopping-cart"></span><a href="#">Shopping Cart</a>
                                        </td>
                                    </tr>
                                </sec:authorize>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-sm-9 col-md-9">
            <div class="well">
                <h1>Control Panel</h1>
                Administrator Dashboard
            </div>
            <div id="contectDiv" class="row">
                
            </div>
        </div>
    </div>
</div>