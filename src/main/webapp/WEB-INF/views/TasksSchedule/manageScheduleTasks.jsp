<%-- 
    Document   : manageScheduleTasks
    Author     : gilberto
--%>

<%@include file="../include.jsp" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Jobs</title>         
        <script>
            var myVar;    
            function chekstatus(){
                myVar = setInterval(function(){
                    //$('#serviceMsg').fadeIn(2000);
                    callAjaxRequest('manageScheduleTasks.htm', 'schedulerTaskList', 'GET', {ref: true});
                }, 8000);
            }

            function stopFunction(){
                clearInterval(myVar); // stop the timer
                
            }

            $(document).ready(function(){
                chekstatus();
                
                $('.glyphicon-trash, .deleteService, .glyphicon, .glyphicon-edit .editService').click(function (){
                    stopFunction();
                });
            });
            <c:if test="${model.redir}">
                alert(90);
                window.location = "/";
            </c:if>
        </script>
    </head>
    <body>
        <div style="display: none" align="center" width="100%">
            <div class="H2Div">
                <h3 class="H2">Manage Schedule Services
            </div>
        </div>
        <div style="min-height: 400px;width: 100%;float: right" class="form-group">
            <div style="width: 100%" valign="top">
                <div id="divAddService"></div>
                <button onclick="stopFunction();callAjaxRequest('addServiceNSchedule.htm', 'divAddService', 'GET', null);" class="btn btn-info" style="height: 40px;width: 100px;float: left;margin-left: 100px">Add Service</button>                                
            </div>
            <div style="margin-top: 20px;" id="myModal" class="modal scrollClass" role="dialog">
                <div style="width: 700px" class="modal-dialog">
                    <div class="modal-content">
                        <div style="width: 100%" class="modal-header">
                            <div style="opacity: 1;width: 100%;text-align: center;font-weight: bold;background-color: #5bc0de;border-radius: 5px;height: 60px" class="drag-handleInactive">
                                <h5 class="modal-title"><strong>Service: <span id="serviceTitle"></span></strong></h5>
                            </div>                                                                                                                                    
                        </div>
                        <div align="center" class="modal-body">
                            <div align="left" style="width: 95%; height: 20px" class="form-group">
                                <div style="width: 95%;background-color: white">
                                    <table>
                                        <tr>
                                            <td valign="top" style="background-color: transparent;border-color: #ffffff;width: 120px"><label valign="top" style="float: left">Description</label></td>
                                            <td valign="top" style="background-color: transparent;border-color: #ffffff"><span id="serviceDescription"></span></td>
                                        </tr>
                                    </table>
                                </div>
                            </div>
                            <div align="left" style="width: 95%; height: 20px" class="form-group">
                                <div style="width: 95%;background-color: white">
                                    <table>
                                        <tr>
                                            <td valign="top" style="background-color: transparent;border-color: #ffffff;width: 120px">
                                                <label style="float: left" valign="top">Schedule</label>
                                            </td>
                                            <td  valign="top"style="background-color: transparent;border-color: #ffffff">
                                               <span id="serviceSchedule"></span>
                                            </td>
                                        </tr>
                                    </table>
                                </div>
                            </div>    
                            <div align="left" style="width: 95%; height: 50px" class="form-group">
                                <div style="width: 95%;background-color: white">
                                    <table>
                                        <tr>
                                            <td valign="top" style="background-color: transparent;border-color: #ffffff;width: 120px">
                                                <label style="float: left" valign="top">Last Execution</label>
                                            </td>
                                            <td valign="top" style="background-color: transparent;border-color: #ffffff">
                                                <span>
                                                From <strong><span id="serviceStart"></span></strong>
                                                to 
                                                <span id="serviceEnd"></span>
                                                <span id="servicePeriod"></span>
                                                </span>
                                            </td>
                                        </tr>
                                    </table>
                                </div>
                            </div>        
                            <div align="left" style="width: 95%; height: 20px" class="form-group">
                                <div style="width: 95%;background-color: white">
                                    <table>
                                        <tr>
                                            <td valign="top" style="background-color: transparent;border-color: #ffffff;width: 120px">
                                                <label valign="top" style="float: left">Date Created</label>
                                            </td>
                                            <td valign="top" style="background-color: transparent;border-color: #ffffff">
                                                <span id="serviceDateCreated"></span>  
                                            </td>
                                        </tr>
                                    </table>
                                </div>
                            </div>
                        </div>
                        <div class="modal-footer"><!--btn btn-default btn-sm-->
                            <button type="button" class="btn btn-primary btn-sm" onclick="chekstatus();$('#myModal').toggleClass('show');" data-target="#myModal">CLOSE</button>
                        </div>
                    </div>
                </div>
            </div>
            <fieldset style="width: 100%;min-height: 100px" class="FieldsetContainer">
                <legend class="H5">Available Services</legend>                
                <form id="schedulerListForm" method="post">
                    <div id="schedulerTaskList" align="center">
                        <c:choose>
                            <c:when test="${empty model.services}">
                                <span class="alert alert-info">No Services Registered Yet</span>
                            </c:when>
                            <c:otherwise>     
                                <div style="max-height: 200px;" class="table-responsive">
                                    <table width="100%" cellpadding="0" cellspacing="0" border="0" id="table" class="table">
                                        <thead>
                                            <tr>
                                                <th>Runtime</th>
                                        <th>Status</th>
                                        <th>Service</th>
                                        <th style="display: none">Date Created</th>
                                        <th style="display: none">Creator</th>
                                        <th width="200px">Description</th>
                                        <th>Schedule</th>
                                        <th style="display: none">Last Execution</th>
                                        <th style="display: none">Runtime</th>
                                        <th>Last Execution</th>
                                        <th>Details</th>
                                        <th>Update/Delete</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach items="${model.services}" varStatus="status" var="service">
                                                <tr style="">
                                                    <td style="width: 20%"> 
                                                        <div id="saveDiv${status.count}"></div>
                                                        <c:if test="${service[6]}">                                                    
                                                            <span style="height: 30px" class="alert alert-info">   
                                                                <input <c:if test="${service[2]==true && service[14]!=true}">disabled="disabled" checked=""</c:if> onclick="if ($(this).prop('checked')) {
                                                                if (confirm('Start this service?')) {
                                                                    stopFunction();callAjaxRequest('runManualService.htm', 'saveDiv${status.count}', {id: ${service[0]}, bean: '${service[13]}', 'GET', name: '${service[1]}'});
                                                                } else {
                                                                    return true;
                                                                }
                                                            }" name="serviceid" value="1" type="checkbox"/> 
                                                                Manual&nbsp;&nbsp;
                                                            </span>
                                                        </c:if>
                                                        <c:if test="${service[7]}">
                                                            <span style="height: 30px" class="alert alert-info">  
                                                                <input checked="checked" disabled="disabled" onclick="if ($(this).prop('checked')) {
                                                                    if (confirm('Start this service?')) {
                                                                        callAjaxRequest('runManualService.htm', 'saveDiv${status.count}', 'GET', {id: ${service[0]}, bean: '${service[13]}', name: '${service[1]}'});
                                                                    } else {
                                                                        return true;
                                                                    }
                                                                }" name="serviceid" value="1" type="checkbox"/>
                                                                Auto
                                                            </span>
                                                        </c:if>
                                                    </td>
                                                    <td style="width: 5%; text-align: left"> 
                                                        <c:if test="${service[2]==null && service[14]!=true}">
                                                            <font color="red"><strong>PENDING</strong></font>
                                                        </c:if>
                                                        <c:if test="${service[2]==false && service[14]!=true}">
                                                            <font color="blue"><strong>COMPLETED</strong></font>
                                                            <div id="fountainG_END">
                                                                <div id="fountainG_END_1" class="fountainG_END"></div>
                                                                <div id="fountainG_END_2" class="fountainG_END"></div>
                                                                <div id="fountainG_END_3" class="fountainG_END"></div>
                                                                <div id="fountainG_END_4" class="fountainG_END"></div>
                                                                <div id="fountainG_END_5" class="fountainG_END"></div>
                                                            </div>
                                                        </c:if>
                                                        <c:if test="${service[2]==true && service[14]!=true}">
                                                            <font color="green"><strong>SENDING</strong></font>
                                                            <div id="fountainG">
                                                                <div id="fountainG_1" class="fountainG"></div>
                                                                <div id="fountainG_2" class="fountainG"></div>
                                                                <div id="fountainG_3" class="fountainG"></div>
                                                                <div id="fountainG_4" class="fountainG"></div>
                                                                <div id="fountainG_5" class="fountainG"></div>
                                                            </div>
                                                        </c:if>
                                                        <c:if test="${service[14]==true && service[2]==true}">
                                                            <font color="red"><strong>ABORTED</strong></font>
                                                            <div id="fountainG_END">
                                                                <div id="fountainG_END_1" class="fountainG_END"></div>
                                                                <div id="fountainG_END_2" class="fountainG_END"></div>
                                                                <div id="fountainG_END_3" class="fountainG_END"></div>
                                                                <div id="fountainG_END_4" class="fountainG_END"></div>
                                                                <div id="fountainG_END_5" class="fountainG_END"></div>
                                                            </div>
                                                        </c:if>    
                                                    </td>
                                                    <td style="width: 25%">
                                                        <font color="blue">
                                                        <strong>${service[1]}</strong>
                                                        </font>
                                                    </td>
                                                    <td style="display: none">
                                                        <fmt:formatDate value="${service[5]}" pattern="dd/MM/yyyy hh:mm a"/>
                                                    </td>
                                                    <td style="width: 25%">
                                                        ${service[3]}
                                                    </td>
                                                    <td style="width: 15%">
                                                        ${service[12]}
                                                    </td>
                                                    <td style="display: none">
                                                        From <strong><fmt:formatDate value="${service[9]}" pattern="dd/MM/yyyy hh:mm a"/></strong>
                                                        to 
                                                        <strong><fmt:formatDate value="${service[10]}" pattern="dd/MM/yyyy  hh:mm a"/></strong>
                                                    </td>
                                                    <td style="display: none">
                                                        <c:if test="${service[7]==true}">Automatic</c:if>
                                                        <c:if test="${service[6]==true}">Manual</c:if>
                                                    </td>
                                                    <td style="width: 10%" align="center">
                                                        <c:if test="${service[11]==null}">
                                                            <span class="alert alert-danger">NEVER</span>
                                                        </c:if>
                                                        <c:if test="${service[11]==false}">
                                                            <c:choose>
                                                                <c:when test="${service[11]==false && service[2]==false && service[14]!=true}">
                                                                    <span class="alert alert-success">SUCCESS</span>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <span class="alert alert-danger">FAILED</span>
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </c:if>
                                                        <c:if test="${service[11]==true}">
                                                            <span class="alert alert-success">SUCCESS</span>
                                                        </c:if>
                                                    </td>
                                                    <td style="width: 6%" align="left">
                                                    <c:if test="${service[2]==false}">      
                                                        <c:set value="${service[10].time - service[9].time}" var="totalMicroDiff"/>
                                                        <c:set var="hours" value="${(totalMicroDiff div 1000) lt 1?0:fn:substringBefore(totalMicroDiff/1000 div (60*60), '.')}"/>
                                                        <c:set var="minutes" value="${fn:substringBefore(totalMicroDiff/1000 div 60, '.')}"/> 
                                                        <c:set var="seconds" value="${fn:substringBefore(totalMicroDiff/1000, '.')}"/> 
                                                        <c:set var="microsecondsx" value="${totalMicroDiff - (secondsDis*1000*60)}"/>

                                                        <fmt:formatNumber var="hrs" minIntegerDigits="2" value="${ hours }"/>
                                                        <fmt:formatNumber var="minutesDis" minIntegerDigits="2" value="${ minutes - (hours*60) }"/>
                                                        <fmt:formatNumber var="secondsDis" minIntegerDigits="2" value="${ seconds - (minutes*60) }"/>                                                                                                                                                       
                                                        <fmt:formatNumber var="microsecondsDis" minIntegerDigits="3" value="${ totalMicroDiff - (seconds*1000) }"/>                                                                            
                                                    </c:if>
                                                        <a onclick="stopFunction();
                                                        $('#myModal').toggleClass('show');
                                                        $('#serviceDateCreated').html('<fmt:formatDate value="${service[5]}" pattern="dd,MMM yyyy  h:mm a"/>');
                                                        $('#serviceTitle').html('${service[1]}'); 
                                                        $('#serviceDescription').html('${service[3]}');
                                                        $('#serviceSchedule').html('${service[12]}');
                                                        $('#serviceStart').html('<fmt:formatDate value="${service[9]}" pattern="dd, MMM yyyy h:mm:ss a"/>');
                                                        <c:if test="${empty service[9]}">$('#serviceStart').html('<font color=red><strong>NEVER RUN</strong></font>');</c:if>
                                                        <c:if test="${empty service[10]}">$('#serviceEnd').html('<font color=red><strong>NEVER RUN</strong></font>');</c:if>
                                                        <c:if test="${service[2]==true}">$('#servicePeriod').html('');$('#serviceEnd').html('<font color=green><strong>SENDING</strong></font>');</c:if>
                                                        <c:if test="${service[2]==false}">
                                                            $('#serviceEnd').html('');$('#servicePeriod').html('<strong><fmt:formatDate value="${service[10]}" pattern="dd,MMM yyyy  h:mm:ss a"/><p>${hours} Hour(s), ${minutesDis} Minute(s), ${secondsDis} Second(s) and ${microsecondsDis} Microsecond(s)</p></strong>');
                                                        </c:if>" data-target="#myModal" class="glyphicon glyphicon-folder-open" href="#"></a>                                                                                                                
                                                    </td>
                                                    <td style="width: 10%;text-align: center">
                                                        <a href="#" id="editService" onclick="
                                                            <c:if test="${service[2]==true && service[14]!=true}">
                                                                alert('Can\'t Update a Running Service');return false;
                                                            </c:if> 
                                                            stopFunction();callAjaxRequest('manageService.htm', 'divAddService', 'GET', {task: 'edit', id: ${service[0]}});" 
                                                        style="color: blueviolet;font-size: 1.2em;" class="glyphicon glyphicon-edit editService">
                                                    </a>
                                                        &nbsp;
                                                        <a href="#" id="deleteService" onclick="<c:if test="${service[2]==true}">alert('Can\'t Delete a Running Service');return false;</c:if> if(confirm('Delete Service: ${service[1]}')){stopFunction();callAjaxRequest('manageService.htm', 'divAddService', {task: 'delete', 'GET', id: ${service[0]}});}" style="color: blueviolet;font-size: 1.2em;" class="glyphicon glyphicon-trash deleteService">
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
                </form>
            </fieldset>
        </div>
    </body>
</html>