$(document).ready(
	function () {
		var masp = 0;
//		fix tạm thời lỗi tự phát sinh <pre> cuối page
		$("pre").remove();
//		


		$("#dangnhap").click(function () {
			$(this).addClass("actived");
			$("#dangky").removeClass("actived");
			$(".container-login-form").show();
			$(".container-signup-form").hide();
		});

		$("#dangky").click(function () {
			$(this).addClass("actived");
			$("#dangnhap").removeClass("actived");
			$(".container-login-form").hide();
			$(".container-signup-form").show();
		});
		
//pagination.js
		$("body").on("click", ".paging-item", function () {
			$(".paging-item").removeClass("active");
			$(this).addClass("active");
			var sotrang = $(this).text();
			var offset = (sotrang - 1) * 12;
			var limit =12;

			$.ajax({
				url: "/api/LaySanPhamLimit",
				type: "GET",
				data: {
					offset: offset,
					limit:limit
				},
				success: function (value) {
					var tbobysanpham = $("#table-sanpham").find("tbody");
					tbobysanpham.empty();
					tbobysanpham.append(value);
				}
			})
		});



	});