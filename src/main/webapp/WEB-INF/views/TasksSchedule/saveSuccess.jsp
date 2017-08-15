<%-- 
    Document   : saveSuccess
    Author     : gilberto
--%>
<%@include file="../include.jsp" %>
<div style="margin-top: 10%" class="modal" id="alertModal" role="dialog">
    <div style="z-index: 105; width: 1000px; height: 350px" class="modal-dialog modal-lg">
        <div class="modal-content">
            <div style="text-align: center" class="modal-header">
                <h4 class="modal-title">
                    MESSAGE
                </h4>
            </div>
            <div class="modal-body">
                <div style="text-align: center" class="<c:if test="${model.saved}">alert alert-info</c:if><c:if test="${model.saved==false}">alert alert-danger</c:if> fade in">
                    <strong><span class="glyphicon glyphicon-alert"></span></strong>
                    ${model.message}
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" onchange="callAjaxRequest('manageScheduleTasks.htm', 'rightcolumn', 'GET', {ref: false});" class="btn btn-primary" onclick="chekstatus();$('#alertModal').toggleClass('show');HideDialog();" data-target="#alertModal">CLOSE</button>
            </div>
        </div>
    </div>
</div>
<script>
    $('#alertModal').toggleClass('show');//.modal('show');
</script>