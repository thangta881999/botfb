$(document).ready(function(){
	$(window).scroll(function(){
	// 	$("#form-user").css({
	// 		"height": $(".product").width()+180
	// 	})
	// });
	// $(window).resize(function(){
	// 	$("#form-user").css({
	// 		"height": $(".product").width()+180
	// 	})
	});
	$(".main-content").css({
		"margin-top": $(".banner-top").outerHeight()
	})
	$(window).resize(function(){
		$(".main-content").css({
			"margin-top": $(".banner-top").outerHeight()
		})
	});
	$("#btn-capnhat").click(function() {
		$("#userName").attr("readOnly",false);
		$("#fullName").attr("readOnly",false);
		$("#phone").attr("readOnly",false);
		$("#btn-ok").removeClass("hidden");
		$("#btn-thoat").removeClass("hidden");
		$("#btn-capnhat").addClass("hidden");
		$("#btn-changePassword").removeClass("hidden");
	});
	$("#btn-changePassword").click(function() {
		event.preventDefault();
		$(".password").addClass("hidden");
		$(".password1").removeClass("hidden");
		$(".password2").removeClass("hidden");
		$("#btn-changePassword").addClass( "hidden");
	});


	$("#btn-thoat").click(function() {
		location.reload();
	});
	$("#btn-ok").click(function() {
		if ($("#password1").val()===$("#password2").val() && $("#password1").val() != "" && $("#password1").val().length >6
		|| ( $("#password1").val()===$("#password2").val() && $("#password2").val()==="" && $("#password1").hasClass("hidden")) ) {
			var formData = $("#form-user").serializeArray();
			var json={};
			$.each(formData, function (i, field) {
				json[field.name] = field.value;
			});
			if(json.password1.length > 6)
			{
				json["password"]=json.password1;
			}
			$.ajax({
				url: "/api/user",
				type: "POST",
				contentType: "application/json",

				data: JSON.stringify(json),
				success: function (res) {
					swal("Success!", "", "success");
					location.reload();

				},
				error: function() {
					swal("Error!", "Plese complete & check again input form", "error");
				}
			});
		}
		else
		{
			swal("Error!", "Plese complete & check again input form", "error");
		}
	});


});

