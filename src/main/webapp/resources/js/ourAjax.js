/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function callAjaxRequest(Url, returnDiv, method, data) {
    alert(90);
    $.ajax({
        url: Url,
        type: method,
        data: data,
        success: function (data) {
            $('#' + returnDiv).html(data).trigger(this);
        }
    });
}

