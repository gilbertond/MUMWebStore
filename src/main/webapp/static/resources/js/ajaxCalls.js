/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
var tagID = "";
var _data = "";
var arr = new Array();
var items = new Array();
var items2 = new Array();
var itemsStr = '';
var sampletxt = '';
var samplestate = false;
var orderid = 0;
var activebt = 0;
var xyz = 1;


function ajaxSubmitData(Url, respDiv, data, method) {
    $('#loader').removeClass('hide').html('<img style="padding-top:5%;width:2%" src="resources/images/gif-load.gif" />');
    $.ajax({
        type: method,
        data: data,
        cache: false,
        url: Url,
        timeout: 98000,
        success: function (resp) {
            $('#' + respDiv).html('').trigger('create');
            $('#' + respDiv).html(resp).trigger('create');
            $('#ajax_div').html('');
            $('#loader').addClass('hide');
        },
        error: function (jqXHR, error, errorThrown) {
                if (jqXHR.status && jqXHR.status === 400) {
                    $('#'+respDiv).html('<font class="alert alert-danger" style="margin-left: 25%;margin-top:30%;">Server returned an Error, contact admin</font>');
                } else if (jqXHR.status && jqXHR.status === 404){
                    $('#'+respDiv).html('<font class="alert alert-danger" style="margin-left: 25%;margin-top:30%;">Could not find page, contact admin</font>');
                }else if (error === "timeout") {
                    $('#'+respDiv).html('<font class="alert alert-danger" style="margin-left: 25%;margin-top:30%;">Request timed out, contact admin</font>');
                } else {
//                    alert("Something went wrong, contact system admin");
                }      
                $('#loader').addClass('hide');
        }
    });
}
function ajaxSubmitDataNoLoader(Url, respDiv, data, method) {
    //    alert(data);    
    $.ajax({
        type: method,
        data: data,
        cache: false,
        url: Url,
        success: function (resp) {
            //alert(resp);
            $('#' + respDiv).html('').trigger('create');
            $('#' + respDiv).html(resp).trigger('create');
        },
        error: function (jqXHR, error, errorThrown) {
            if (Url !== 'checkUserNotification.htm') {
                if (jqXHR.status && jqXHR.status == 400) {
                    alert('Server returned an Error, contact admin');
                } else if (error === "timeout") {
                    alert("Request timed out, contact admin");
                } else {
//                    alert("Something went wrong, contact system admin");
                }
            }
        }
    });
}