$(document).ready(
function()
{
	$("pre").remove();
	$(".main-content").css({
		"margin-top": $(".banner-top").outerHeight(),
		"padding": "30px"
	})
	$(window).resize(function(){
		$(".main-content").css({
			"margin-top": $(".banner-top").outerHeight(),
			"padding": "30px"

		})
	});
});
