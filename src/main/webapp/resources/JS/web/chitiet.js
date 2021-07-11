$(document).ready(
		function (){
//rating
			$("body").on("click", ".btn-review", function (event) {
				event.preventDefault();
				var userId=$('#userId').attr('userId');
				if(userId)
				{

					var json={};
					var rating = parseInt($('#stars li.selected').last().data('value'), 10);
					var formData = $("#form-review").serializeArray();
					var sanPham={};
					sanPham["masanpham"]=$('#tensp').attr('data-masp');
					json["sanPham"]=sanPham
					json["rating"]=rating;
					var user={};
					user["id"]= userId;
					json["user"]=user;
					$.each(formData, function (i, field) {
						json[field.name] = field.value;
					});
					json[user]=user;

					if (!rating||json["comment"]=="")
					{
						swal("Warning!", "Plese rating and comment to review", "warning");
					}
					else {
						$.ajax({
							url: "/api/bill/checkbilluserdeliverd?userId="+userId+"&masanpham="+sanPham.masanpham,
							type: "GET",
							contentType: "application/json",
							success: function (res) {
								if (res==="true") {
									$.ajax({
										url: "/api/reviews",
										type: "POST",
										contentType: "application/json",

										data: JSON.stringify(json),
										success: function (res) {
											if (res.validated) {
												//take your successful action here; you may want to redirect to another page
												swal("Success!", "", "success");
												window.location.reload();
											} else {
												$.each(res.errorMessages, function (key, value) {
													// $('input[name=' + key + ']').after('<span class="error" style="color:red;margin-left: 4%;">' + value + '</span>');
												});
												swal("Error!", "Plese complete & check again input form", "error");
											}
										},
										error: function() {
											swal("Error!", "Plese complete & check again input form", "error");
										}


									});
								} else {
									swal("Warning!", "Plese review after you receive this product", "warning");
								}
							},
							error: function() {
								swal("Error!", "Plese complete & check again input form", "error");
							}


						});

					}

				}
				else
				{
					swal("Warning!", "Plese logging to review", "warning");
				}



			});
			/* 1. Visualizing things on Hover - See next part for action on click */
			$('#stars li').on('mouseover', function(){
				var onStar = parseInt($(this).data('value'), 10); // The star currently mouse on

				// Now highlight all the stars that's not after the current hovered star
				$(this).parent().children('li.star').each(function(e){
					if (e < onStar) {
						$(this).addClass('hover');
					}
					else {
						$(this).removeClass('hover');
					}
				});

			}).on('mouseout', function(){
				$(this).parent().children('li.star').each(function(e){
					$(this).removeClass('hover');
				});
			});


			/* 2. Action to perform on click */
			$('#stars li').on('click', function(){
				var onStar = parseInt($(this).data('value'), 10); // The star currently selected
				var stars = $(this).parent().children('li.star');

				for (i = 0; i < stars.length; i++) {
					$(stars[i]).removeClass('selected');
				}

				for (i = 0; i < onStar; i++) {
					$(stars[i]).addClass('selected');
				}

				// JUST RESPONSE (Not needed)
				var ratingValue = parseInt($('#stars li.selected').last().data('value'), 10);
				var msg = "";
				if (ratingValue > 1) {
					msg = "Thanks! You rated this " + ratingValue + " stars.";
				}
				else {
					msg = "We will improve ourselves. You rated this " + ratingValue + " stars.";
				}
				responseMessage(msg);

			});



//			margin-page
			$(".page-detail").css({
				"margin-top": $(".banner-top").outerHeight()
			})
			$(window).resize(function(){
				$(".page-detail").css({
					"margin-top": $(".banner-top").outerHeight()
				})
			});
			$(".btnGioHang").click(
					function () {
						var mamau = $(this).closest("tr").find(".mau").attr(
							"data-mamau");
						var tenmau = $(this).closest("tr").find(".mau").text();

						var masize = $(this).closest("tr").find(".size").attr(
							"data-masize");
						var tensize = $(this).closest("tr").find(".size")
							.text();

						var soluong = $(this).closest("tr").find(".soluong")
							.text();

						var tensp = $("#tensp").text();
						var masp = $("#tensp").attr("data-masp");

						var giatien = $("#giatien").attr("data-giatien");

						var machitiet = $(this).attr("data-machitiet");
						var json={};
						json["masp"]=masp;
						json["masize"]=masize;
						json["mamau"]=mamau;
						json["tensp"]=tensp;
						json["giatien"]=giatien;
						json["tenmau"]=tenmau;
						json["tensize"]=tensize;
						json["soluong"]=1;
						json["machitiet"]=machitiet;
						$.ajax({
							url: "/api/carts",
							type: "POST",
							 contentType: 'application/json',
					            data: JSON.stringify(json),

							success: function (value) {
								$("#giohang").find("div").addClass(
									"circle-giohang")
								$("#giohang").find("div").html(
									"<span style='color:white'>" + value + "</span>");

							}
						})
					});
		}
		);
function responseMessage(msg) {
	$('.success-box').fadeIn(200);
	$('.success-box div.text-message').html("<span>" + msg + "</span>");
}