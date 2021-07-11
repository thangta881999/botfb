$(document).ready(
function() {
	$("pre").remove();
	$(".main-content").css({
		"margin-top": $(".banner-top").outerHeight(),
		"padding": "20px"
	})
	$(window).resize(function(){
		$(".main-content").css({
			"margin-top": $(".banner-top").outerHeight(),
			"padding": "20px"

		})
	});
	$("#checkall").change(function () {
		if (this.checked) {
			$("#table-order input").each(function () {
				if(!$(this).attr("disabled"))
				{
					$(this).attr("checked", true);
				}

			})
		} else {
			$("#table-order input").each(function () {
				$(this).attr("checked", false);
			})
		}
	})
//
//	delete
	
	$(document).on('click', '.capnhathoadon', function(){
		var This= $(this);
		swal({
			  title: "Cập nhật ghi chú",
			  input:"text",
			   inputValue:  This.closest("tr").find(".ghichu")[0].textContent,
			  type: "info",
			
			  showCancelButton: true,
			  confirmButtonClass: "btn-success",
			  cancelButtonClass: "btn-danger",
			  confirmButtonText: "Xác nhận",
			  cancelButtonText: "Hủy bỏ",
			}).then(function(isConfirm) {
			  if (isConfirm.value) {
				  json={};
				  json["mahoadon"]=This.closest("tr").find(".mahoadon").attr("data-id");
				  json["ghichu"]=isConfirm.value;
					 $.ajax({
							url: "/user/api/bill",
							type: "PUT",
							 contentType: "application/json",
							   data: JSON.stringify(json),
							success: function (res) {
								 if (res>0) {
					                    //take your successful action here; you may want to redirect to another page
										swal("Success!", "", "success");
								This.closest("tr").find(".ghichu")[0].innerText=isConfirm.value;
					                } else {
					                   
					                	swal("Error!", "Update Failed", "error");
					                }
							},
							error: function() {
								swal("Error!", "Server bảo trì", "error");
						      }


						});
			  }
			});
		
	});
	$("#delete-order").click(function () {
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
					  $("#table-order input:checked").each(function () {
							var mahoadon = $(this).val();
							
							var This = $(this);

							$.ajax({
								url: "/user/api/bill",
								  contentType: 'application/json',
								type: "DELETE",
								/*data: {
									mahoadon: mahoadon,
								},*/
								   data: JSON.stringify(mahoadon),
								success: function (value) {
									This.closest("tr").remove();
									swal("Success!", "", "success");
								}
							})
						})	
				  }
				});
						
		
		})
//	end delte

	
//	page
	function loadHoaDons(page){
		var sotrang = page;
		var offset = (sotrang - 1) * 6;
		var limit =6;
		var userId= $(location).attr("href").substring($(location).attr("href").substring().length-1,$(location).attr("href").substring().length);

		$.ajax({
			url: "/user/api/bill",
			type: "GET",
			data: {
				userId: userId,
				offset: offset,
				limit:limit
			},
			success: function (value) {
				var tbobyvalue = $("#table-order").find("tbody");
				tbobyvalue.empty();
				
			
				var html="";
				for(i=0;i<value.length;i++)
					{
					var createdDate =new Date(value[i].createdDate).toLocaleString();
					var updatedDate =new Date(value[i].updatedDate).toLocaleString();
					if (value[i].tinhtrang === "FIN")
					{
						var tinhtrang="	<div class=\"order_succsess\" value=\"FIN\">Đã nhận</div>";
					}
					if (value[i].tinhtrang === "DEL")
					{
						var tinhtrang="<div class=\"order_delivering\" value=\"DEL\">Đang giao hàng</div>"
					}
					if (value[i].tinhtrang === "INP")
					{
					var tinhtrang="<div class=\"order_delivering\" value=\"INP\">Đang xử lý</div>"
					}
					
					var updatedDate =new Date(value[i].updatedDate).toLocaleString();
					if (value[i].thanhtoan || value[i].tinhtrang === "DEL" || value[i].tinhtrang === "FIN")
					{
						var thanhtoan="	<div class=\"order_succsess\" value=\"1\">Hoàn thành</div>";
						var checkbox= 	"<label><input class=\"checkboxvalue\" type=\"checkbox\" disabled value=\" "+ value[i].mahoadon +"\"></label>\n"
					}
				else
					{
					var thanhtoan="<div class=\"order_delivering\" value=\"0\">Chưa thanh toán</div>"
						var checkbox = 	"<label><input class=\"checkboxvalue\" type=\"checkbox\" value=\" "+ value[i].mahoadon +"\"></label>\n"
					}
				html += "<tr>";
				html += "<td class=\"mahoadon\" data-id=\"" +value[i].mahoadon+"\"> \n" + 
						"	<div class=\"checkbox\">\n" + 
					checkbox +
						"	</div>\n" + 
						"</td>";
				
				html += "<td class=\"tenkhachang\">" + value[i].tenkhachhang + "</td>\n" + 
				"<td class=\"sodt\">" + value[i].sodt + "</td>\n"+
				"<td class=\"diachigiaohang\">" + value[i].diachigiaohang + "</td>\n"+
				"<td class=\"hinhthucgiaohang\">" + value[i].hinhthucgiaohang + "</td>\n"+
				"<td class=\"tinhtrang\">"
				
				+
				tinhtrang
				+
				"</td>\n"+
				"<td class=\"thanhtoan\">"
				+
				thanhtoan
				+
				"</td>\n";
		
				
				html += "<td class=\"ghichu\">" + value[i].ghichu + "</td>\n"+
				
				"<td class=\"createdDate\">"+createdDate  +"</td>"+
				"<td class=\"updatedDate\">"+ updatedDate  +"</td>"+
				"<td class=\"tongtien\">" + value[i].tongtien + "</td>\n"+
				 "<td class=\"btn btn-action capnhathoadon\"   data-id=\" "+ value[i].mahoadon + " \">"
				 		+ "<i class=\"fa fa-pencil-square-o icon \" ></i> "
				 		+ "</td>" +
				 	"	<td class=\"btn btn-action xemchitiet\"	> "
						+"	<a href=\"bill/"+value[i].mahoadon +"/details\"> "
						+"	<i class=\"fa fa-eye icon\" aria-hidden=\"true\"> </i>"
						+"	</a> "
						+"	</td> " 
				 		;
			
				html += "</tr>";
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
	loadHoaDons(page);
    }
	});
//
	
}		
);
