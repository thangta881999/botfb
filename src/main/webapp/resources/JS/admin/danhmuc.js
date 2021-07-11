$(document).ready(
function() {
	$("pre").remove();
	$("#tendanhmuc").keydown(function(event) {
		 $('input[name="tendanhmuc"]').next('span').remove();
		if (event.keyCode==8) //cho phép nhất backspace
			return true;
	
			if( $("#tendanhmuc").val().toString().length >= 20   )
			{
				 $('input[name="tendanhmuc"]').after('<span class="error" style="color:red;margin-left: 4%;">"Nhập tên danh mục từ 4-20 ký tự"</span>');
				return false;
			}
			if( $("#tendanhmuc").val().toString().length <3    )
			{
				 $('input[name="tendanhmuc"]').after('<span class="error" style="color:red;margin-left: 4%;">"Nhập tên sản phẩm từ 4-20 ký tự"</span>');
			}
	
		});
//	delete categories
	$("#checkall").change(function () {
		if (this.checked) {
			$("#table-danhmuc input").each(function () {
				$(this).attr("checked", true);
			})
		} else {
			$("#table-danhmuc input").each(function () {
				$(this).attr("checked", false);
			})
		}
	})

	$("#xoa-danhmuc").click(function () {
		swal({
			  title: "Xác nhận xóa",
			  text: "Bạn có chắc chắn muốn xóa hay không",
			  type: "warning",
			
			  showCancelButton: true,
			  confirmButtonClass: "btn-success",
			  cancelButtonClass: "btn-danger",
			  confirmButtonText: "Xác nhận",
			  cancelButtonText: "Hủy bỏ",
			}).then(function(isConfirm) {
			  if (isConfirm.value) {
				  $("#table-danhmuc input:checked").each(function () {
						var madanhmuc = $(this).val();
						
						var This = $(this);

						This.closest("tr").remove();
						$.ajax({
							url: "/admin/api/XoaDanhMuc",
							type: "GET",
							data: {
								madm: madanhmuc,
							},
							success: function (value) {
								This.closest("tr").remove();
								swal("Success!", "", "success");
								loadDanhMucs(1);
							}
						})
					})	
			  }
			});
					
	
	})
//	page
	function loadDanhMucs(page){
		var sotrang = page;
		var offset = (sotrang - 1) * 6;
		var limit =6;

		$.ajax({
			url: "/admin/api/categories",
			type: "GET",
			data: {
				offset: offset,
				limit:limit
			},
			success: function (value) {
				var tbobyvalue = $("#table-danhmuc").find("tbody");
				tbobyvalue.empty();
				
				var html="";
				for(i=0;i<value.length;i++)
					{
				html += "<tr>";
				html += "<td> \n" + 
						"	<div class=\"checkbox\">\n" + 
						"			<label><input class=\"checkboxvalue\" type=\"checkbox\" value=\" "+ value[i].madanhmuc +"\"></label>\n" + 
						"	</div>\n" + 
						"</td>";
				
				html += "<td class=\"madanhmuc\">" + value[i].madanhmuc + "</td>\n" + 
					"<td class=\"tendanhmuc\">" + value[i].tendanhmuc + "</td>\n" + 
				"<td class=\"parent_madanhmuc\">" + value[i].parent_madanhmuc + "</td>\n" + 
				 "<td class=\"btn btn-action capnhatdanhmuc\" style=\"color:white\"  data-id=\" "+ value[i].madanhmuc + " \">"
				 		+ "<i class=\"fa fa-pencil-square-o \" style=\"color:white\"></i> "
				 		+ "</td>";
			
				html += "</tr>";
					}
				tbobyvalue.append(html);
			}
		})
	};
	window.pagObj = $('#pagination').twbsPagination({
        totalPages: $(".pagination").attr('totalpage'),
        visiblePages: 8,
    onPageClick: function (event, page) {
	loadDanhMucs(page);
    }
});
//	thêm danh mục
	$("#btn-themdanhmuc").click(function (event) {
		event.preventDefault();
		var formData = $("#form-danhmuc").serializeArray();
		json = {};

		$.each(formData, function (i, field) {
			json[field.name] = field.value;
		});
	
		$('input').next('span').remove();
		$.ajax({
			url: "/admin/api/categories",
			type: "POST",
			 contentType: "application/json",

			   data: JSON.stringify(json),
			success: function (res) {
				 if (res.validated) {
	                    //take your successful action here; you may want to redirect to another page
						swal("Success!", "", "success");
						loadDanhMucs(1);
						
	                } else {
	                    $.each(res.errorMessages, function (key, value) {
	                        $('input[name=' + key + ']').after('<span class="error" style="color:red;margin-left: 4%;">' + value + '</span>');
	                    });
	                	swal("Error!", "Plese complete & check again input form", "error");
	                }
			},
			error: function() {
				swal("Error!", "Plese complete & check again input form", "error");
		      }


		});
	});
//	update
	$("#btn-thoat").click(function(event)
			{
				event.preventDefault();
				$("#btn-themdanhmuc").removeClass("hidden");
				$("#btn-capnhatdanhmuc").addClass("hidden");
				$("#btn-thoat").addClass("hidden");
			
				$("#tendanhmuc").val("");
				
			});
	$("body").on("click", ".capnhatdanhmuc", function () {
		$("#btn-themdanhmuc").addClass("hidden");
		$("#btn-capnhatdanhmuc").removeClass("hidden");
		$("#btn-thoat").removeClass("hidden");
		madm = $(this).attr("data-id");
		$.ajax({
			url: "/admin/api/LayDanhMucTheoMa",
			type: "GET",
			data: {
				madm: madm
			},
			success: function (value) {
				$("#tendanhmuc").val(value.tendanhmuc);
				$("#parent_madanhmuc").val(value.parent_madanhmuc );

				
			}


		});
	});
	$("#btn-capnhatdanhmuc").click(function (event) {
		event.preventDefault();
		var formData = $("#form-danhmuc").serializeArray();
		json = {};

		$.each(formData, function (i, field) {
			json[field.name] = field.value;
		});
		json["madanhmuc"]=madm;
	
		$('input').next('span').remove();
		$.ajax({
			url: "/admin/api/categories",
			type: "PUT",
			 contentType: "application/json",

			   data: JSON.stringify(json),
			success: function (res) {
				 if (res.validated) {
	                    //take your successful action here; you may want to redirect to another page
						swal("Success!", "", "success");
						loadDanhMucs(1);
	                } else {
	                    $.each(res.errorMessages, function (key, value) {
	                        $('input[name=' + key + ']').after('<span class="error" style="color:red;margin-left: 4%;">' + value + '</span>');
	                    });
	                	swal("Error!", "Plese complete & check again input form", "error");
	                }
			},
			error: function() {
				swal("Error!", "Plese complete & check again input form", "error");
		      }


		});
	});

}		
);
