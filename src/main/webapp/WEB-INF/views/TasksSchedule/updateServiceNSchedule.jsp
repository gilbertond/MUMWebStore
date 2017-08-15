<%-- 
    Document   : addServiceNSchedule
    Author     : gilberto
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!--link rel="stylesheet" type="text/css" href="static/css/general.css" media="all">
        <link rel="stylesheet" type="text/css" href="static/css/opd.css" media="all"/-->
        <title>Medication</title>

        <c:url value="static/resources/bootstrap-3-3-7/css/bootstrap-theme.min.css" var="bootstrap-theme" />
        <c:url value="static/resources/bootstrap-3-3-7/css/bootstrap.min.css" var="bootstrap" />
        <link href="${bootstraptheme}" rel="stylesheet" />
        <link href="${bootstrap}" rel="stylesheet" />
<style type="text/css">
            <!--
            .InvestBtns {
                height:60px;
                width:60px;
            }


            .style2 {
                font-size: 18px;
                font-weight: bold;
                font-style: italic;
            }
            .AddOpenBtn
            {
                height:32px;
                width:35px;
            }

            -->
        </style>
        <style type="text/css">
            #resultsDiv{
                z-index: 106;
            }
            .web_dialog_overlay
            {
                position: fixed;
                top: 0;
                right: 0;
                bottom: 0;
                left: 0;
                margin-top: 20px;
                height: 100%;
                width: 100%;
                margin: 0;
                padding: 0;
                background: #fff;
                opacity: .60;
                filter: alpha(opacity=60);
                -moz-opacity: .6;
                z-index: 101;
                display: none;
            }
            .web_dialog
            {
                display: inline;
                position: fixed;
                min-width: 400px;
                min-height: 450px;
                top: 6%;
                left: 15%;
                margin-left: 0px;
                margin-top: 0px;
                background-color: #ffffff;
                border: 5px solid #cccccc;
                padding: 0px;
                z-index: 105;
                font-family: Verdana;
                font-size: 10pt;
                border-radius: 20px;
                //overflow: scroll;
            }
            .web_dialog_title
            {
                border-bottom: solid 2px #336699;
                background-color: #336699;
                padding: 4px;
                color: White;
                font-weight:bold;
            }
            .web_dialog_title a
            {
                color: White;
                text-decoration: none;
            }
            .align_right
            {
                text-align: right;
            }
            div.scrollClass {
                min-height: 10px;
                max-height: 520px;
                width: auto;
                overflow: scroll;
                padding: 10px;
            }
        </style>
        <c:url value="static/resources/js/cron-generate/cronGen.js" var="cronGen" />   
        <script src="${cronGen}"></script>
        <script type="text/javascript">

            $(document).ready(function ()
            {
                $(".scrollClass").height(520);
                $(".web_dialog").width(1000);
                $(".web_dialog").height(600);
                $(".modal-content").height(600);
                
                //popup(0,0);
                $(function () {
                    //generate();
                    $("#cron").cronGen();
                });

                ShowDialog(false);
                $("#btnShowSimple").click(function (e)
                {
                    //alert(99999);
                    ShowDialog(false);
                    e.preventDefault();
                });

                $("#btnShowModal").click(function (e)
                {
                    ShowDialog(true);
                    e.preventDefault();
                });

                $("#btnClose").click(function (e)
                {
                    HideDialog();
                    e.preventDefault();
                });

                $("#btnSubmit").click(function (e)
                {
                    var brand = $("#patientinfo input:input:checked").val();//$("#brands input:radio:checked").val();
                    $("#output").html("<b>You have decided to worn on Patient: </b>" + brand);//$("#output").html("<b>Your favorite mobile brand: </b>" + brand);
                    HideDialog();
                    e.preventDefault();
                });

                $(function () {
                    $('#dialog').scroll().resizable({minWidth: 850, maxWidth: 900, minHeight: 500, maxHeight: 580}).draggable({handle: '.drag-handle', scroll: true, containment: "#overlay"});
                });
            });

            function ShowDialog(modal)
            {
                //alert(123);
                $("#overlay").show();
                $("#dialog").fadeIn(300);

                if (modal)
                {
                    $("#overlay").unbind("click");
                }
                else
                {
                    $("#overlay").click(function (e)
                    {
                        if (confirm('Close Dialog?')) {
                            HideDialog();
                        }
                    });
                }
            }

            function HideDialog()
            {
                callAjaxRequest('manageScheduleTasks.htm', 'reloadDiv', 'GET', {ref: false});
                $("#overlay").hide();
                $("#dialog").fadeOut(300);
            }

        </script>
    </head>
    <body>
        <c:url value="static/resources/images/empty.gif" var="emptygif" />
        <c:url value="static/resources/images/close2.png" var="close2" />
        
        <div id="overlay" class="web_dialog_overlay"></div>
        <div align="center" id="dialog" class="web_dialog">
            <div style="opacity: 1;width: 100%;display: none" class="drag-handle">Scheduled Service Update
                <div id="closeIcon" class="drag-controls">
                    <img src="${emptygif}" title="Minimize">
                    <img id="btnClose" src="${close2}" title="Close" style="margin-right: 2px;" height="19px" width="19px">
                </div>
            </div>
            <div align="center" id="Mainx" style="">                
                <table style="display: none" width="100%">
                    <tr>
                        <td class="web_dialog_title">Scheduled Services Update</td>
                        <td class="web_dialog_title align_right">
                            <a href="#" id="btnClose">Close</a>
                        </td>
                    </tr>
                    <tr>
                        <td class="alert-info" colspan="2">
                            ${model.msg}
                        </td>
                    </tr>
                </table>                
                <div class="modal-content"> 
                    <form id="createServiceForm">
                        <input type="hidden" name="cronexpression" id="cron" />
                        <div style="height: 460px;width: 100%" align="center" class="modal-body scrollClass">                                    
                            <div align="left" style="width: 80%; height: 30px" class="form-group">    
                                <c:if test="${not empty model.msg}">
                                    <font class="alert-info">${model.msg}</font>
                                </c:if>
                                <table style="width: 100%">
                                    <tr>
                                        <td style="background-color: transparent;border-color: #ffffff;width: 100px">
                                            <label>Service<font color="red">*</font></label>
                                        </td>
                                        <td style="background-color: transparent;border-color: #ffffff">
                                            <input data-toggle="tooltip" title="Service Name" placeholder="Select from dropdown" onkeyup="isEmptyField('servicename');" style="width: 230px;;float: left;" class="form-control input-sm" value="${model.service[1]}" name="servicename" id="servicename" onKeyPress='return isAlphaNumericSpecial(event)' type="text"/>                                                                        
                                            <input value="${model.service[13]}" name="beanname" id="beanname" type="hidden"/>
                                            <input value="${model.service[14]}" name="autoid" id="autoid" type="hidden"/>
                                            <input value="${model.service[0]}" name="serviceid" id="serviceid" type="hidden"/>
                                            <select class="form-control input-sm" style="width: 220px;" name="service" id="service">
                                                <option value="0">--Select a Service below--</option>
                                                <c:forEach items="${model.services}" varStatus="cnt" var="service">
                                                    <option <c:if test="${service[0] == model.service[16]}">selected</c:if> onclick="$('#autoid').val('${service[0]}');$('#beanname').val('${service[5]}');$('#servicename').prop('readonly', false); $('#description').val('${service[4]}');$('#servicename').val('${service[1]}')" value="${service[1]}">${cnt.count}.&nbsp;${service[1]}</option>
                                                </c:forEach>
                                            </select>
                                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                                        </td>
                                    </tr>
                                </table>
                            </div>                             
                            <div align="left" style="width: 80%; height: 30px" class="form-group">
                                <table style="width: 30%">
                                    <tr>
                                        <td style="background-color: transparent;border-color: #ffffff;width: 150px">
                                            <label>Run Manually<font color="red"></font></label>
                                        </td>
                                        <td style="background-color: transparent;border-color: #ffffff;text-align: left">
                                            <input <c:if test="${model.service[6]}">checked="checked" value="1"</c:if> onclick="if ($(this).prop('checked')) {
                                                <c:if test="${not empty model.msg}">
                                                    if(!confirm('${model.msg}')){
                                                        return false;
                                                    }
                                                </c:if>
                                                $('#runonstartup').prop('checked', false);
                                                $('#contentDIV').fadeOut(800);
                                                $(this).val(1);
                                            } else {
                                                $('#runonstartup').prop('checked', true);
                                                $('#contentDIV').fadeIn(800);
                                                $(this).val(0);
                                            }" type="checkbox" name="startoncreation" id="startoncreation"/>
                                        </td>
                                        </tr>
                                    </table>
                                </div>
                                <div align="left" style="width: 80%; height: 30px" class="form-group">
                                    <table style="width: 30%">
                                        <tr>
                                            <td style="background-color: transparent;border-color: #ffffff;width: 150px">
                                                <label>Run Automatically<font color="red"></font></label>
                                            </td>
                                            <td style="background-color: transparent;border-color: #ffffff;text-align: left">
                                                    <input <c:if test="${model.service[7]}">checked="checked" value="1"</c:if> onclick="if ($(this).prop('checked')) {
                                                $('#startoncreation').prop('checked', false);
                                                $('#startoncreation').val(0);
                                                $('#contentDIV').fadeIn(800);
                                                $(this).val(1);
                                            } else {
                                                $('#startoncreation').prop('checked', true);
                                                $('#startoncreation').val(1);
                                                $('#contentDIV').fadeOut(800);
                                                $(this).val(0);
                                            }" type="checkbox" name="runonstartup" id="runonstartup"/>
                                        </td>
                                    </tr>
                                </table>
                            </div>
                            <div align="left" style="width: 80%; height: 50px" class="form-group">
                                <table style="width: 100%">
                                    <tr>
                                        <td style="background-color: transparent;border-color: #ffffff;width: 100px">
                                            <label>Description<font color="red">*</font></label>
                                        </td>
                                        <td style="background-color: transparent;border-color: #ffffff">
                                            <textarea onkeyup="isEmptyField('description');" placeholder="Enter Description" style="width: 600px; margin-right: 20%" class="form-control RequiredField" type="text" class="" id="description" name="description">${model.service[3]}</textarea>
                                        </td>
                                    </tr>
                                </table>
                            </div>
                            <div id="contentDIV" align="center" style="width: 100%; height: 150px;<c:if test="${model.service[8]}">display: none</c:if>" class="form-group">
                                <div style="float: left;">
                                    <fieldset style="min-width: 800px" class="FieldsetContainer">
                                        <legend class="H5">Runtime Settings</legend>
                                        <div id="CronGenMainDiv" align="center" style="width: 90%" class="containerx">
                                            <ul id="CronGenTabs" class="nav nav-tabs">
                                                <li class="active"><a id="MinutesTab" onclick="$('#selectedtab').val('1')" data-toggle="tab" href="#minutes">Minutes</a></li>
                                                <li><a id="HourlyTab" onclick="$('#selectedtab').val('2')" data-toggle="tab" href="#hourly">Hourly</a></li>
                                                <li><a id="DailyTab" onclick="$('#selectedtab').val('3')" data-toggle="tab" href="#daily">Daily</a></li>
                                                <li><a id="WeeklyTab" onclick="$('#selectedtab').val('4')" data-toggle="tab" href="#weekly">Weekly</a></li>
                                                <li><a id="MonthlyTab" onclick="$('#selectedtab').val('5')" data-toggle="tab" href="#monthly">Monthly</a></li>
                                                <li><a id="YearlyTab" onclick="$('#selectedtab').val('6')" data-toggle="tab" href="#yearly">Yearly</a></li>
                                            </ul>
                                            <div class="tab-content">
                                                <div style="height: 200px" id="minutes" class="tab-pane fade in active">
                                                    <h3 style="display: none">Minutes</h3>
                                                    <hr/>
                                                    <div align="left" style="width: 60%; height: 30px" class="form-group">   
                                                        <span style="float: left; width: 40px;">
                                                            Every 
                                                        </span>
                                                        <span style="float: right; width: 360px;">
                                                            <input size="3" maxlength="2" data-toggle="tooltip" title="Minutes" placeholder="" onkeyup="makeCron();
                                                                isEmptyField('MinutesInput');" style="width: 90px;float: left;" class="form-control input-sm" value="1" name="MinutesInput" id="MinutesInput" onKeyPress='return isNumberKey(event)' type="text"/>&nbsp;&nbsp;Minute(s)&nbsp; 
                                                            <span class="alert alert-info">Default - <strong>1Min</strong></span>                        
                                                        </span>
                                                    </div>
                                                </div>
                                                <div id="hourly" class="tab-pane fade">
                                                    <h3 style="display: none">Hourly</h3>
                                                    <hr/>
                                                    <div align="left" style="width: 42%; height: 30px" class="form-group">   
                                                        <span style="float: left; width: 70px;text-align: right">
                                                            <input checked="" value="1" type="radio" name="HourlyRadio" id="HourlyRadio"/>&nbsp;&nbsp;Every
                                                        </span>
                                                        <span style="float: right; width: 210px;">
                                                            &nbsp;&nbsp;<input size="3" maxlength="2" data-toggle="tooltip" title="Minutes" placeholder="" onkeyup="isEmptyField('HoursInput');" style="width: 90px;float: left;" class="form-control input-sm" value="1" name="HoursInput" id="HoursInput" onKeyPress='return isNumberKey(event)' type="text"/>&nbsp;&nbsp;Hour(s)&nbsp;                                                                                                                
                                                        </span>
                                                    </div>
                                                    <hr/>
                                                    <div align="left" style="width: 42%; height: 30px" class="form-group">   
                                                        <span style="float: left; width: 70px;text-align: right">
                                                            <input value="2" type="radio" name="HourlyRadio" id="HourlyRadio2"/>&nbsp;&nbsp;Every 
                                                        </span>
                                                        <span style="float: right; width: 140px;">
                                                            <select class="form-control input-sm" style="float: left; width: 70px;" id="AtHours" name="AtHours">
                                                                <option disabled value="0">00</option>
                                                                <option disabled value="1">01</option>
                                                                <option disabled value="2">02</option>
                                                                <option disabled value="3">03</option>
                                                                <option disabled value="4">04</option>
                                                                <option disabled value="5">05</option>
                                                                <option disabled value="6">06</option>
                                                                <option disabled value="7">07</option>
                                                                <option disabled value="8">08</option>
                                                                <option value="9">09</option>
                                                                <option value="10">10</option>
                                                                <option value="11">11</option>
                                                                <option selected="selected" value="12">12</option>
                                                                <option value="13">13</option>
                                                                <option value="14">14</option>
                                                                <option value="15">15</option>
                                                                <option value="16">16</option>
                                                                <option value="17">17</option>
                                                                <option value="18">18</option>
                                                                <option value="19">19</option>
                                                                <option value="20">20</option>
                                                                <option value="21">21</option>
                                                                <option value="22">22</option>
                                                                <option value="23">23</option>
                                                            </select>
                                                            <select class="form-control input-sm" style="float: right; width: 70px;" id="AtMinutes" name="AtMinutes">
                                                                <option selected="selected" value="0">00</option>
                                                                <option value="1">01</option>
                                                                <option value="2">02</option>
                                                                <option value="3">03</option>
                                                                <option value="4">04</option>
                                                                <option value="5">05</option>
                                                                <option value="6">06</option>
                                                                <option value="7">07</option>
                                                                <option value="8">08</option>
                                                                <option value="9">09</option>
                                                                <option value="10">10</option>
                                                                <option value="11">11</option>
                                                                <option value="12">12</option>
                                                                <option value="13">13</option>
                                                                <option value="14">14</option>
                                                                <option value="15">15</option>
                                                                <option value="16">16</option>
                                                                <option value="17">17</option>
                                                                <option value="18">18</option>
                                                                <option value="19">19</option>
                                                                <option value="20">20</option>
                                                                <option value="21">21</option>
                                                                <option value="22">22</option>
                                                                <option value="23">23</option>
                                                                <option value="24">24</option>
                                                                <option value="25">25</option>
                                                                <option value="26">26</option>
                                                                <option value="27">27</option>
                                                                <option value="28">28</option>
                                                                <option value="29">29</option>
                                                                <option value="30">30</option>
                                                                <option value="31">31</option>
                                                                <option value="32">32</option>
                                                                <option value="33">33</option>
                                                                <option value="34">34</option>
                                                                <option value="35">35</option>
                                                                <option value="36">36</option>
                                                                <option value="37">37</option>
                                                                <option value="38">38</option>
                                                                <option value="39">39</option>
                                                                <option value="40">40</option>
                                                                <option value="41">41</option>
                                                                <option value="42">42</option>
                                                                <option value="43">43</option>
                                                                <option value="44">44</option>
                                                                <option value="45">45</option>
                                                                <option value="46">46</option>
                                                                <option value="47">47</option>
                                                                <option value="48">48</option>
                                                                <option value="49">49</option>
                                                                <option value="50">50</option>
                                                                <option value="51">51</option>
                                                                <option value="52">52</option>
                                                                <option value="53">53</option>
                                                                <option value="54">54</option>
                                                                <option value="55">55</option>
                                                                <option value="56">56</option>
                                                                <option value="57">57</option>
                                                                <option value="58">58</option>
                                                                <option value="59">59</option>
                                                            </select> 
                                                        </span>
                                                    </div>
                                                </div>
                                                <div id="daily" class="tab-pane fade">
                                                    <h3 style="display: none">Daily</h3>
                                                    <hr/>
                                                    <div align="left" style="width: 60%; height: 30px" class="form-group">   
                                                        <span style="float: left; width: 70px">
                                                            <input checked="" value="1" type="radio" name="DailyRadio" id="DailyRadio1"/>&nbsp;&nbsp;Every
                                                        </span>
                                                        <span style="float: right; width: 210px;">
                                                            &nbsp;&nbsp;<input size="3" maxlength="2" data-toggle="tooltip" title="Day(s)" placeholder="" onkeyup="isEmptyField('DaysInput');" style="width: 90px;float: left;" class="form-control input-sm" value="1" name="DaysInput" id="DaysInput" onKeyPress='return isNumberKey(event)' type="text"/>&nbsp;&nbsp;Day(s)                                                             
                                                        </span>
                                                    </div>
                                                    <hr/>
                                                    <div align="left" style="width: 60%; height: 30px" class="form-group">   
                                                        <span style="float: left; width: 280px;">
                                                            <input value="2" type="radio" name="DailyRadio" id="DailyRadio2"/>&nbsp;&nbsp;Every Week Day
                                                        </span>
                                                    </div>
                                                    <hr/>
                                                    <div align="left" style="width: 60%; height: 30px" class="form-group">   
                                                        <span style="float: left; width: 200px;">
                                                            Start Time(24hr clock)
                                                        </span>
                                                        <span style="float: right; width: 140px;">
                                                            <select class="form-control input-sm" style="float: left; width: 70px;" name="DailyHours" id="DailyHours">
                                                                <option disabled value="0">00</option>
                                                                <option disabled value="1">01</option>
                                                                <option disabled value="2">02</option>
                                                                <option disabled value="3">03</option>
                                                                <option disabled value="4">04</option>
                                                                <option disabled value="5">05</option>
                                                                <option disabled value="6">06</option>
                                                                <option disabled value="7">07</option>
                                                                <option disabled value="8">08</option>
                                                                <option value="9">09</option>
                                                                <option value="10">10</option>
                                                                <option value="11">11</option>
                                                                <option selected="selected" value="12">12</option>
                                                                <option value="13">13</option>
                                                                <option value="14">14</option>
                                                                <option value="15">15</option>
                                                                <option value="16">16</option>
                                                                <option value="17">17</option>
                                                                <option value="18">18</option>
                                                                <option value="19">19</option>
                                                                <option value="20">20</option>
                                                                <option value="21">21</option>
                                                                <option value="22">22</option>
                                                                <option value="23">23</option>
                                                            </select>
                                                            <select class="form-control input-sm" style="float: right; width: 70px;" name="DailyMinutes" id="DailyMinutes">
                                                                <option selected="selected" value="0">00</option>
                                                                <option value="1">01</option>
                                                                <option value="2">02</option>
                                                                <option value="3">03</option>
                                                                <option value="4">04</option>
                                                                <option value="5">05</option>
                                                                <option value="6">06</option>
                                                                <option value="7">07</option>
                                                                <option value="8">08</option>
                                                                <option value="9">09</option>
                                                                <option value="10">10</option>
                                                                <option value="11">11</option>
                                                                <option value="12">12</option>
                                                                <option value="13">13</option>
                                                                <option value="14">14</option>
                                                                <option value="15">15</option>
                                                                <option value="16">16</option> 
                                                                <option value="17">17</option>
                                                                <option value="18">18</option>
                                                                <option value="19">19</option>
                                                                <option value="20">20</option>
                                                                <option value="21">21</option>
                                                                <option value="22">22</option>
                                                                <option value="23">23</option>
                                                                <option value="24">24</option>
                                                                <option value="25">25</option>
                                                                <option value="26">26</option>
                                                                <option value="27">27</option>
                                                                <option value="28">28</option>
                                                                <option value="29">29</option>
                                                                <option value="30">30</option>
                                                                <option value="31">31</option>
                                                                <option value="32">32</option>
                                                                <option value="33">33</option>
                                                                <option value="34">34</option>
                                                                <option value="35">35</option>
                                                                <option value="36">36</option>
                                                                <option value="37">37</option>
                                                                <option value="38">38</option>
                                                                <option value="39">39</option>
                                                                <option value="40">40</option>
                                                                <option value="41">41</option>
                                                                <option value="42">42</option>
                                                                <option value="43">43</option>
                                                                <option value="44">44</option>
                                                                <option value="45">45</option>
                                                                <option value="46">46</option>
                                                                <option value="47">47</option>
                                                                <option value="48">48</option>
                                                                <option value="49">49</option>
                                                                <option value="50">50</option>
                                                                <option value="51">51</option>
                                                                <option value="52">52</option>
                                                                <option value="53">53</option>
                                                                <option value="54">54</option>
                                                                <option value="55">55</option>
                                                                <option value="56">56</option>
                                                                <option value="57">57</option>
                                                                <option value="58">58</option>
                                                                <option value="59">59</option>
                                                            </select> 
                                                        </span>
                                                    </div>
                                                </div>
                                                <div id="weekly" class="tab-pane fade">
                                                    <h3 style="display: none">Weekly</h3>
                                                    <hr/>
                                                    <div align="left" style="width: 60%; height: 30px" class="form-group">   
                                                        <span style="float: left; width: 700px;">
                                                            <table style="width: 400px">
                                                                <tr>
                                                                    <td><input checked="" type="checkbox" name="Monday" value="MON"/>&nbsp;&nbsp;Monday</td>
                                                                    <td><input type="checkbox" name="Tuesday" value="TUE"/>&nbsp;&nbsp;Tuesday</td>
                                                                    <td><input type="checkbox" name="Wednesday" value="WED"/>&nbsp;&nbsp;Wednesday</td>
                                                                    <td><input type="checkbox" name="Thursday" value="THU"/>&nbsp;&nbsp;Thursday</td>
                                                                </tr>
                                                                <tr>
                                                                    <td><input type="checkbox" name="Friday" value="FRI"/>&nbsp;&nbsp;Friday</td>
                                                                    <td><input type="checkbox" name="Saturday" value="SAT"/>&nbsp;&nbsp;Saturday</td>
                                                                    <td><input type="checkbox" name="Sundau" value="SUN"/>&nbsp;&nbsp;Sunday</td>
                                                                </tr>
                                                            </table>
                                                        </span>
                                                    </div>
                                                    <hr/>
                                                    <div align="left" style="width: 60%; height: 30px" class="form-group">   
                                                        <span style="float: left; width: 200px;text-align: right">
                                                            Start Time(24hr clock) 
                                                        </span>
                                                        <span style="float: right; width: 140px;">
                                                            <select class="form-control input-sm" style="float: left; width: 70px;" name="WeeklyHours" id="WeeklyHours">
                                                                <option disabled value="1">01</option>
                                                                <option disabled value="2">02</option>
                                                                <option disabled value="3">03</option>
                                                                <option disabled value="4">04</option>
                                                                <option disabled value="5">05</option>
                                                                <option disabled value="6">06</option>
                                                                <option disabled value="7">07</option>
                                                                <option disabled value="8">08</option>
                                                                <option value="9">09</option>
                                                                <option value="10">10</option>
                                                                <option value="11">11</option>
                                                                <option selected="selected" value="12">12</option>
                                                                <option value="13">13</option>
                                                                <option value="14">14</option>
                                                                <option value="15">15</option>
                                                                <option value="16">16</option>
                                                                <option value="17">17</option>
                                                                <option value="18">18</option>
                                                                <option value="19">19</option>
                                                                <option value="20">20</option>
                                                                <option value="21">21</option>
                                                                <option value="22">22</option>
                                                                <option value="23">23</option>
                                                            </select>
                                                            <select class="form-control input-sm" style="float: right; width: 70px;" name="WeeklyMinutes" id="WeeklyMinutes">
                                                                <option selected="selected" value="0">00</option>
                                                                <option value="1">01</option>
                                                                <option value="2">02</option>
                                                                <option value="3">03</option>
                                                                <option value="4">04</option>
                                                                <option value="5">05</option>
                                                                <option value="6">06</option>
                                                                <option value="7">07</option>
                                                                <option value="8">08</option>
                                                                <option value="9">09</option>
                                                                <option value="10">10</option>
                                                                <option value="11">11</option>
                                                                <option value="12">12</option>
                                                                <option value="13">13</option>
                                                                <option value="14">14</option>
                                                                <option value="15">15</option>
                                                                <option value="16">16</option>
                                                                <option value="17">17</option>
                                                                <option value="18">18</option>
                                                                <option value="19">19</option>
                                                                <option value="20">20</option>
                                                                <option value="21">21</option>
                                                                <option value="22">22</option>
                                                                <option value="23">23</option>
                                                                <option value="24">24</option>
                                                                <option value="25">25</option>
                                                                <option value="26">26</option>
                                                                <option value="27">27</option>
                                                                <option value="28">28</option>
                                                                <option value="29">29</option>
                                                                <option value="30">30</option>
                                                                <option value="31">31</option>
                                                                <option value="32">32</option>
                                                                <option value="33">33</option>
                                                                <option value="34">34</option>
                                                                <option value="35">35</option>
                                                                <option value="36">36</option>
                                                                <option value="37">37</option>
                                                                <option value="38">38</option>
                                                                <option value="39">39</option>
                                                                <option value="40">40</option>
                                                                <option value="41">41</option>
                                                                <option value="42">42</option>
                                                                <option value="43">43</option>
                                                                <option value="44">44</option>
                                                                <option value="45">45</option>
                                                                <option value="46">46</option>
                                                                <option value="47">47</option>
                                                                <option value="48">48</option>
                                                                <option value="49">49</option>
                                                                <option value="50">50</option>
                                                                <option value="51">51</option>
                                                                <option value="52">52</option>
                                                                <option value="53">53</option>
                                                                <option value="54">54</option>
                                                                <option value="55">55</option>
                                                                <option value="56">56</option>
                                                                <option value="57">57</option>
                                                                <option value="58">58</option>
                                                                <option value="59">59</option>
                                                            </select> 
                                                        </span>
                                                    </div>
                                                </div>
                                                <div id="monthly" class="tab-pane fade">
                                                    <h3 style="display: none">Monthly</h3>
                                                    <hr/>
                                                    <div align="left" style="width: 700px; height: 30px;margin-left: 200px" class="form-group"> 
                                                        <span style="width: 600px;">
                                                            <table width="500px">
                                                                <tr>
                                                                    <td style="width: 4%">
                                                                        <input checked="" value="1" type="radio" name="MonthlyRadio" id="MonthlyRadio"/> 
                                                                    </td>
                                                                    <td style="width: 9%">
                                                                        Every 
                                                                    </td>
                                                                    <td style="width: 9%;">
                                                                        <input size="3" maxlength="2" data-toggle="tooltip" title="Day(s)" placeholder="" onkeyup="isEmptyField('DayOfMOnthInput');" style="width: 50px;float: left;" class="form-control input-sm" value="1" name="DayOfMOnthInput" id="DayOfMOnthInput" onKeyPress='return isNumberKey(event)' type="text"/>
                                                                    </td>
                                                                    <td style="width: 20%">
                                                                        &nbsp;Day of every
                                                                    </td>
                                                                    <td style="width: 10%">
                                                                        <input size="3" maxlength="2" data-toggle="tooltip" title="Month(s)" placeholder="" onkeyup="isEmptyField('MonthInput');" style="width: 50px;float: right;" class="form-control input-sm" value="1" name="MonthInput" id="MonthInput" onKeyPress='return isNumberKey(event)' type="text"/>
                                                                    </td>
                                                                    <td style="width: 5%">
                                                                        &nbsp;Month(s)
                                                                    </td>
                                                                    <td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td>
                                                                </tr>
                                                            </table>
                                                        </span>
                                                    </div>
                                                    <hr/>
                                                    <div align="left" style="width: 700px; height: 30px;margin-left: 200px" class="form-group">   
                                                        <span style="width: 600px;">
                                                            <table width="500px">
                                                                <tr>
                                                                    <td style="width: 4%">
                                                                        <input value="2" type="radio" name="MonthlyRadio" id="MonthlyRadio2"/>
                                                                    </td>
                                                                    <td>&nbsp;The&nbsp;</td>
                                                                    <td>
                                                                        <select class="form-control input-sm" style="width: 100px;" name="WeekDay" id="WeekDay">
                                                                            <option value="1">First</option>
                                                                            <option value="2">Second</option>
                                                                            <option value="3">Third</option>
                                                                            <option value="3">Fourth</option>
                                                                        </select>
                                                                    </td>
                                                                    <td>
                                                                        <select class="form-control input-sm" style="float: right; width: 120px;" name="DayInWeekOrder" id="DayInWeekOrder">
                                                                            <option value="2">Monday</option>
                                                                            <option value="3">Tuesday</option>
                                                                            <option value="4">Wednesday</option>
                                                                            <option value="5">Thursday</option>
                                                                            <option value="6">Friday</option>
                                                                            <option value="7">Saturday</option>
                                                                            <option value="1">Sunday</option>
                                                                        </select>
                                                                    </td>
                                                                    <td>
                                                                        of every
                                                                    </td>
                                                                    <td>
                                                                        <input size="3" maxlength="2" data-toggle="tooltip" title="Month(s)" placeholder="" onkeyup="isEmptyField('EveryMonthInput');" style="width: 60px;" class="form-control input-sm" value="1" name="EveryMonthInput" id="EveryMonthInput" onKeyPress='return isNumberKey(event)' type="text"/>
                                                                    </td>
                                                                    <td>
                                                                        &nbsp;&nbsp;Month(s)
                                                                    </td>
                                                                </tr>
                                                            </table>                          
                                                        </span>
                                                    </div>
                                                    <hr/>
                                                    <div align="left" style="width: 700px; height: 30px;margin-left: 200px" class="form-group">   
                                                        <span style="float: left; width: 200px;">
                                                            Start Time(24hr clock) 
                                                        </span>
                                                        <span style="width: 110px;">
                                                            <select class="form-control input-sm" style="float: left; width: 70px;" id="MonthlyHours" name="MonthlyHours">
                                                                <option disabled value="0">00</option>
                                                                <option disabled value="1">01</option>
                                                                <option disabled value="2">02</option>
                                                                <option disabled value="3">03</option>
                                                                <option disabled value="4">04</option>
                                                                <option disabled value="5">05</option>
                                                                <option disabled value="6">06</option>
                                                                <option disabled value="7">07</option>
                                                                <option disabled value="8">08</option>
                                                                <option value="9">09</option>
                                                                <option value="10">10</option>
                                                                <option value="11">11</option>
                                                                <option selected="selected" value="12">12</option>
                                                                <option value="13">13</option>
                                                                <option value="14">14</option>
                                                                <option value="15">15</option>
                                                                <option value="16">16</option>
                                                                <option value="17">17</option>
                                                                <option value="18">18</option>
                                                                <option value="19">19</option>
                                                                <option value="20">20</option>
                                                                <option value="21">21</option>
                                                                <option value="22">22</option>
                                                                <option value="23">23</option>
                                                            </select>
                                                            <select class="form-control input-sm" style="width: 70px;" id="MonthlyMinutes" name="MonthlyMinutes">
                                                                <option selected="selected" value="0">00</option>
                                                                <option value="1">01</option>
                                                                <option value="2">02</option>
                                                                <option value="3">03</option>
                                                                <option value="4">04</option>
                                                                <option value="5">05</option>
                                                                <option value="6">06</option>
                                                                <option value="7">07</option>
                                                                <option value="8">08</option>
                                                                <option value="9">09</option>
                                                                <option value="10">10</option>
                                                                <option value="11">11</option>
                                                                <option value="12">12</option>
                                                                <option value="13">13</option>
                                                                <option value="14">14</option>
                                                                <option value="15">15</option>
                                                                <option value="16">16</option>
                                                                <option value="17">17</option>
                                                                <option value="18">18</option>
                                                                <option value="19">19</option>
                                                                <option value="20">20</option>
                                                                <option value="21">21</option>
                                                                <option value="22">22</option>
                                                                <option value="23">23</option>
                                                                <option value="24">24</option>
                                                                <option value="25">25</option>
                                                                <option value="26">26</option>
                                                                <option value="27">27</option>
                                                                <option value="28">28</option>
                                                                <option value="29">29</option>
                                                                <option value="30">30</option>
                                                                <option value="31">31</option>
                                                                <option value="32">32</option>
                                                                <option value="33">33</option>
                                                                <option value="34">34</option>
                                                                <option value="35">35</option>
                                                                <option value="36">36</option>
                                                                <option value="37">37</option>
                                                                <option value="38">38</option>
                                                                <option value="39">39</option>
                                                                <option value="40">40</option>
                                                                <option value="41">41</option>
                                                                <option value="42">42</option>
                                                                <option value="43">43</option>
                                                                <option value="44">44</option>
                                                                <option value="45">45</option>
                                                                <option value="46">46</option>
                                                                <option value="47">47</option>
                                                                <option value="48">48</option>
                                                                <option value="49">49</option>
                                                                <option value="50">50</option>
                                                                <option value="51">51</option>
                                                                <option value="52">52</option>
                                                                <option value="53">53</option>
                                                                <option value="54">54</option>
                                                                <option value="55">55</option>
                                                                <option value="56">56</option>
                                                                <option value="57">57</option>
                                                                <option value="58">58</option>
                                                                <option value="59">59</option>
                                                            </select> 
                                                        </span>
                                                    </div>
                                                </div>
                                                <div id="yearly" class="tab-pane fade">
                                                    <h3 style="display: none">Yearly</h3>
                                                    <hr/>
                                                    <div align="left" style="width: 700px; height: 30px;margin-left: 200px" class="form-group"> 
                                                        <span style="width: 600px;">
                                                            <table width="500px">
                                                                <tr>
                                                                    <td style="width: 4%">
                                                                        <input checked="" value="1" type="radio" name="YearlyRadio" id="YearlyRadio"/> 
                                                                    </td>
                                                                    <td style="width: 9%">
                                                                        Every 
                                                                    </td>
                                                                    <td style="width: 10%;">
                                                                        <select class="form-control input-sm" style="width: 100px;" name="MonthsOfYear" id="MonthsOfYear">                            
                                                                            <option selected="selected" value="1">January</option>
                                                                            <option value="2">February</option>
                                                                            <option value="3">March</option>
                                                                            <option value="4">April</option>
                                                                            <option value="5">May</option>
                                                                            <option value="6">June</option>
                                                                            <option value="7">July</option>
                                                                            <option value="8">August</option>
                                                                            <option value="9">September</option>
                                                                            <option value="10">October</option>
                                                                            <option value="11">November</option>
                                                                            <option value="12">December</option>
                                                                        </select>
                                                                    </td>
                                                                    <td style="width: 10%">
                                                                        <input size="3" maxlength="2" data-toggle="tooltip" title="Month(s)" placeholder="" onkeyup="isEmptyField('YearInput');" style="width: 50px;float: right;" class="form-control input-sm" value="1" name="YearInput" id="YearInput" onKeyPress='return isNumberKey(event)' type="text"/>
                                                                    </td>
                                                                    <td style="width: 10%">&nbsp;</td>
                                                                    <td style="width: 5%">&nbsp;</td>
                                                                    <td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td>
                                                                </tr>
                                                            </table>
                                                        </span>
                                                    </div>
                                                    <hr/>
                                                    <div align="left" style="width: 700px; height: 30px;margin-left: 200px" class="form-group">   
                                                        <span style="width: 600px;">
                                                            <table width="500px">
                                                                <tr>
                                                                    <td style="width: 4%">
                                                                        <input value="2" type="radio" name="YearlyRadio" id="YearlyRadio2"/>
                                                                    </td>
                                                                    <td>&nbsp;The&nbsp;</td>
                                                                    <td align="center" style="width: 12%;text-align: center">
                                                                        <select class="form-control input-sm" style="width: 100px;" name="DayOrderInYear" id="DayOrderInYear">
                                                                            <option value="1">First</option>
                                                                            <option value="2">Second</option>
                                                                            <option value="3">Third</option>
                                                                            <option value="3">Fourth</option>
                                                                        </select>
                                                                    </td>
                                                                    <td align="center" style="width: 12%;text-align: center">
                                                                        <select class="form-control input-sm" style="float: right; width: 120px;" name="DayWeekForYear" id="DayWeekForYear">
                                                                            <option value="2">Monday</option>
                                                                            <option value="3">Tuesday</option>
                                                                            <option value="4">Wednesday</option>
                                                                            <option value="5">Thursday</option>
                                                                            <option value="6">Friday</option>
                                                                            <option value="7">Saturday</option>
                                                                            <option value="1">Sunday</option>
                                                                        </select>
                                                                    </td>
                                                                    <td style="width: 5%;text-align: center">
                                                                        of
                                                                    </td>
                                                                    <td>
                                                                        <select class="form-control input-sm" style="width: 100px;" name="MonthsOfYear2" id="MonthsOfYear2">                            
                                                                            <option selected="selected" value="1">January</option>
                                                                            <option value="2">February</option>
                                                                            <option value="3">March</option>
                                                                            <option value="4">April</option>
                                                                            <option value="5">May</option>
                                                                            <option value="6">June</option>
                                                                            <option value="7">July</option>
                                                                            <option value="8">August</option>
                                                                            <option value="9">September</option>
                                                                            <option value="10">October</option>
                                                                            <option value="11">November</option>
                                                                            <option value="12">December</option>
                                                                        </select>
                                                                    </td>
                                                                    <td>
                                                                        &nbsp;
                                                                    </td>
                                                                </tr>
                                                            </table>                          
                                                        </span>
                                                    </div>
                                                    <hr/>
                                                    <div align="left" style="width: 700px; height: 30px;margin-left: 200px" class="form-group">   
                                                        <span style="float: left; width: 200px;">
                                                            Start Time(24hr clock) 
                                                        </span>
                                                        <span style="width: 110px;">
                                                            <select class="form-control input-sm" style="float: left; width: 70px;" name="YearlyHours" id="YearlyHours">
                                                                <option disabled value="0">00</option>
                                                                <option disabled value="1">01</option>
                                                                <option disabled value="2">02</option>
                                                                <option disabled value="3">03</option>
                                                                <option disabled value="4">04</option>
                                                                <option disabled value="5">05</option>
                                                                <option disabled value="6">06</option>
                                                                <option disabled value="7">07</option>
                                                                <option disabled value="8">08</option>
                                                                <option value="9">09</option>
                                                                <option value="10">10</option>
                                                                <option value="11">11</option>
                                                                <option selected="selected" value="12">12</option>
                                                                <option value="13">13</option>
                                                                <option value="14">14</option>
                                                                <option value="15">15</option>
                                                                <option value="16">16</option>
                                                                <option value="17">17</option>
                                                                <option value="18">18</option>
                                                                <option value="19">19</option>
                                                                <option value="20">20</option>
                                                                <option value="21">21</option>
                                                                <option value="22">22</option>
                                                                <option value="23">23</option>
                                                            </select>
                                                            <select class="form-control input-sm" style="width: 70px;" name="YearlyMinutes" id="YearlyMinutes">
                                                                <option selected="selected" value="0">00</option>
                                                                <option value="1">01</option>
                                                                <option value="2">02</option>
                                                                <option value="3">03</option>
                                                                <option value="4">04</option>
                                                                <option value="5">05</option>
                                                                <option value="6">06</option>
                                                                <option value="7">07</option>
                                                                <option value="8">08</option>
                                                                <option value="9">09</option>
                                                                <option value="10">10</option>
                                                                <option value="11">11</option>
                                                                <option value="12">12</option>
                                                                <option value="13">13</option>
                                                                <option value="14">14</option>
                                                                <option value="15">15</option>
                                                                <option value="16">16</option>
                                                                <option value="17">17</option>
                                                                <option value="18">18</option>
                                                                <option value="19">19</option>
                                                                <option value="20">20</option>
                                                                <option value="21">21</option>
                                                                <option value="22">22</option>
                                                                <option value="23">23</option>
                                                                <option value="24">24</option>
                                                                <option value="25">25</option>
                                                                <option value="26">26</option>
                                                                <option value="27">27</option>
                                                                <option value="28">28</option>
                                                                <option value="29">29</option>
                                                                <option value="30">30</option>
                                                                <option value="31">31</option>
                                                                <option value="32">32</option>
                                                                <option value="33">33</option>
                                                                <option value="34">34</option>
                                                                <option value="35">35</option>
                                                                <option value="36">36</option>
                                                                <option value="37">37</option>
                                                                <option value="38">38</option>
                                                                <option value="39">39</option>
                                                                <option value="40">40</option>
                                                                <option value="41">41</option>
                                                                <option value="42">42</option>
                                                                <option value="43">43</option>
                                                                <option value="44">44</option>
                                                                <option value="45">45</option>
                                                                <option value="46">46</option>
                                                                <option value="47">47</option>
                                                                <option value="48">48</option>
                                                                <option value="49">49</option>
                                                                <option value="50">50</option>
                                                                <option value="51">51</option>
                                                                <option value="52">52</option>
                                                                <option value="53">53</option>
                                                                <option value="54">54</option>
                                                                <option value="55">55</option>
                                                                <option value="56">56</option>
                                                                <option value="57">57</option>
                                                                <option value="58">58</option>
                                                                <option value="59">59</option>
                                                            </select> 
                                                        </span>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </fieldset>
                                </div>
                            </div>
                        </div>
                        <div style="width: 30%" align="center" class="modal-footer"><!--btn btn-default btn-sm-->                       
                            <input value="1" type="hidden" name="selectedtab" id="selectedtab"/>
                            <div id="saveDiv"></div>
                            <button style="float: left" id="submitButton" type="button" class="btn btn-primary" 
                                    onclick="if($('#service').val()==='0'){alert('Select a service first');return true;};callAjaxRequest('updateScheduledService.htm', 'saveDiv', 'POST', $('#createServiceForm').serialize());">UPDATE</button>
                            <button type="button" class="btn bg-primary" onclick="HideDialog();">CANCEL</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
