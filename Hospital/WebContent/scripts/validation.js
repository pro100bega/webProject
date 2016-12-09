$(function(){
	
	$.validator.setDefaults({
		errorClass: "help-block",
		highlight: function(element){
			$(element)
			.closest(".form-signin")
			.addClass("has-error");
		},
		unhighlight: function(element){
			$(element).closest(".form-signin").removeClass("has-error");
		}
	});
	
	$('.form-signin').validate({
		rules:{
			username:{
				required : true
			},
			password:{
				required : true
			},
			passwordConfirm: {
				required : true,
				equalTo: "#password"
			}
		}
	});
	
});