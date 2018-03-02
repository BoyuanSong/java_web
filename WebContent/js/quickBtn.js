$(document).ready(function() {
	
$('#quickLoginBtn').click(function(){
	$('#empid').val('00000');
	$('#emppw').val('00000');
}); // in login.jsp

$('#quickEmpAddBtn').click(function(){
	$('#empid').val('jack5566');
	$('#empname').val('廖敬霆');
	$('#empemail').val('lst448@hotmail.com');
}); // in empAdd.jsp

$('#quickEmailChangeBtn').click(function(){
	$('#empemail').val('CLHS79@hotmail.com');
}); // in empChangeEmail.jsp

$('#quickPwChangeBtn').click(function(){
	$('#typedOldEmppw').val('00000');
	$('#newEmppw1').val('11111');
	$('#newEmppw2').val('11111');
}); // in empChangePw.jsp



});