$(document).ready(
function() {
	$("pre").remove();
	$("#checkall").change(function () {
		if (this.checked) {
			$("#table-order input").each(function () {
				$(this).attr("checked", true);
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
							url: "/admin/api/bill",
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
								url: "/admin/api/bill",
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
	$(document).on('click', '.order_delivering', function(){
		var This= $(this);
	 var tdTinhtrang = This.parent("td").parent("tr").children("td.tinhtrang");
		swal({
			  title: "Xác nhận đơn hàng",
			  text: "Xác nhận đơn hàng đã giao",
			  type: "warning",
			
			  showCancelButton: true,
			  confirmButtonClass: "btn-success",
			  cancelButtonClass: "btn-danger",
			  confirmButtonText: "Xác nhận",
			  cancelButtonText: "Hủy bỏ",
			}).then(function(isConfirm) {
			  if (isConfirm.value) {
				 var mahoadon= This.closest("tr").find(".mahoadon").attr("data-id");
				 var ghichu= This.closest("tr").find(".ghichu")[0].textContent;
				  if (This.closest("tr").find(".order_checkout").length >0)
				  {
					  var thanhtoan = false;
				  }
				  else
				  {
					  var thanhtoan = true;
				  }
				 var tinhtrang="INP";
				 if (This.attr("value")==="INP")
				 {
				 	tinhtrang ="DEL";
				 }
				  if (This.attr("value")==="DEL")
				  {
					  tinhtrang ="FIN";
					  thanhtoan = true;
				  }
				  if (This.attr("value")==="FIN")
				  {
					  tinhtrang ="FIN";
					  thanhtoan = true;
				  }



				 var json ={};
				 json["mahoadon"]=mahoadon;
				 json["ghichu"]=ghichu;
				 json["tinhtrang"]=tinhtrang;
				 json["thanhtoan"]=thanhtoan;
				
				 $.ajax({
						url: "/admin/api/bill",
						type: "PUT",
						 contentType: "application/json",
						   data: JSON.stringify(json),
						success: function (res) {
							 if (res=="1") {
				                    //take your successful action here; you may want to redirect to another page
									swal("Success!", "", "success");
									 tdTinhtrang.empty();
								 if (tinhtrang === "FIN")
								 {
									 tdTinhtrang.append('<div class="order_succsess" value="FIN">Đã nhận</div>');
								 }
								 if (tinhtrang === "DEL")
								 {
									 tdTinhtrang.append('<div class="order_delivering" value="DEL">Đang giao hàng</div>');

								 }
								 if (tinhtrang === "INP")
								 {
									 tdTinhtrang.append('<div class="order_delivering" value="INP">Đang xử lý</div>');
									 tdThanhtoan.empty();
									 tdThanhtoan.append('<div class="order_succsess" value="1">Hoàn thành</div>');
								 }

									 This.remove();
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
	
//	page
	function loadHoaDons(page){
		var sotrang = page;
		var offset = (sotrang - 1) * 6;
		var limit =6;

		$.ajax({
			url: "/admin/api/bill",
			type: "GET",
			data: {
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
							var tinhtrang="<div class=\"order_succsess\" value=\"DEL\">Đã nhận</div>"
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
					if (value[i].thanhtoan)
					{
						var thanhtoan="	<div class=\"order_succsess\" value=\"1\">Hoàn thành</div>";
					}
				else
					{
					var thanhtoan="<div class=\"order_checkout\" value=\"0\">Chưa thanh toán</div>"
					}
				html += "<tr>";
				html += "<td class=\"mahoadon\" data-id=\"" +value[i].mahoadon+"\"> \n" + 
						"	<div class=\"checkbox\">\n" + 
						"			<label><input class=\"checkboxvalue\" type=\"checkbox\" value=\" "+ value[i].mahoadon +"\"></label>\n" + 
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
	$(document).on('click', '.order_checkout', function(){
		var This= $(this);
		var tdThanhtoan= This.parent("td").parent("tr").children("td.thanhtoan");
		var tdTinhtrang = This.parent("td").parent("tr").children("td.tinhtrang");
		swal({
			title: "Xác nhận đơn hàng",
			text: "Xác nhận đơn hàng đã giao",
			type: "warning",

			showCancelButton: true,
			confirmButtonClass: "btn-success",
			cancelButtonClass: "btn-danger",
			confirmButtonText: "Xác nhận",
			cancelButtonText: "Hủy bỏ",
		}).then(function(isConfirm) {
			if (isConfirm.value) {
				var mahoadon= This.closest("tr").find(".mahoadon").attr("data-id");
				var ghichu= This.closest("tr").find(".ghichu")[0].textContent;
				var tinhtrang =This.closest("tr").find(".order_delivering").attr("value");
				var thanhtoan=true;
				var json ={};
				json["mahoadon"]=mahoadon;
				json["ghichu"]=ghichu;
				json["tinhtrang"]=tinhtrang;
				json["thanhtoan"]=thanhtoan;

				$.ajax({
					url: "/admin/api/bill",
					type: "PUT",
					contentType: "application/json",
					data: JSON.stringify(json),
					success: function (res) {
						if (res=="1") {
							//take your successful action here; you may want to redirect to another page
							swal("Success!", "", "success");
							tdThanhtoan.empty();
							tdThanhtoan.append('<div class="order_succsess" value="1">Hoàn thành</div>');
							This.remove();
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
	
}		
);
