/**
 * 
 */
function checkPasswordMatch() {
	var password = $("#pwd").val();
	var confirmPassword = $("#confirmPassword").val();
	if (password != confirmPassword)
		$("#divCheckPasswordMatch").html("Passwords do not match!").addClass(
				"confirm-password");
	else
		$("#divCheckPasswordMatch").html("Passwords match.").addClass(
				"confirm-passwords");
}
$(function() {
	$("#confirmPassword").focusout(checkPasswordMatch);

	$("#emailId").change(function() {
		var th = $(this);
		$.post("/project_MUM-WebStore/emailCheck", {
			"email" : th.val()
		}).done(function(message) {
			message = $.parseJSON(message);
			$("#emailmsg").html(message.message);
		});
	});

});
