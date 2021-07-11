$(document).ready(
	function () {
		var masp = 0;
//		fix tạm thời lỗi tự phát sinh <pre> cuối page
		$("pre").remove();
//		
		var editor='';
		editor=CKEDITOR.replace( 'mota' );
		
		$("#giatien").keydown(function(event) {
			 $('input[name="giatien"]').next('span').remove();
			if (event.keyCode==8 || event.keyCode>=37 && event.keyCode<=40) //cho phép nhất backspace
				return true;
		
			if(event.keyCode<48|| event.keyCode>57) // chỉ cho phép nhập số
				return false

		
				if( $("#giatien").val().toString().length >= 14 )
				{
					 $('input[name="giatien"]').after('<span class="error" style="color:red;margin-left: 4%;">"Số tiền dưới 100 tỷ"</span>');
					return false;
				}
		
			});
//		#container-chitietsanpham >.chitietsp > #soluong ( input[soluong] chỉ cho phép nhập số )
		$("#container-chitietsanpham").keydown(function(event) {
			console.log($("#soluong").val())
			if (event.keyCode==8 || event.keyCode>=37 && event.keyCode<=40) //cho phép nhất backspace
				return true;
		
			if(event.keyCode<48|| event.keyCode>57) // chỉ cho phép nhập số
				return false

		
			});
		$("#tensanpham").keydown(function(event) {
			 $('input[name="tensanpham"]').next('span').remove();
			if (event.keyCode==8) //cho phép nhất backspace
				return true;
		
				if( $("#tensanpham").val().toString().length >= 255   )
				{
					 $('input[name="tensanpham"]').after('<span class="error" style="color:red;margin-left: 4%;">"Nhập tên sản phẩm từ 10-255 ký tự"</span>');
					return false;
				}
				if( $("#tensanpham").val().toString().length <10    )
				{
					 $('input[name="tensanpham"]').after('<span class="error" style="color:red;margin-left: 4%;">"Nhập tên sản phẩm từ 10-255 ký tự"</span>');
				}
		
			});
		
		$("#giatien").keyup(function(event) {
		
			$("#giatien").val($("#giatien").val().replace(/\./g,''));
			$("#giatien").val($("#giatien").val().toString().replace(/\B(?=(\d{3})+(?!\d))/g, "."));
			$("#giatien").val($("#giatien").val().toString().replace(/^0+/, ''));
		
			
		
			});
		$("#giatien").change(function()
				{
			if($("#giatien").val()<0)
			{
				$("#giatien").val("0");
			}
			
				});
	

//pagination.js
		function loadProducts(page){
			var sotrang = page;
			var offset = (sotrang - 1) * 5;
			var limit =5;

			$.ajax({
				url: "/admin/api/products",
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
		};
		window.pagObj = $('#pagination').twbsPagination({
            totalPages: $(".pagination").attr('totalpage'),
            visiblePages: 8,
        onPageClick: function (event, page) {
		loadProducts(page);
        }
    });



//admin.js
		$("#checkall").change(function () {
			if (this.checked) {
				$("#table-sanpham input").each(function () {
					$(this).attr("checked", true);
				})
			} else {
				$("#table-sanpham input").each(function () {
					$(this).attr("checked", false);
				})
			}
		})

		$("#xoa-sanpham").click(function () {
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
				  if (isConfirm) {
					  $("#table-sanpham  input:checked").each(function () {
							var masanpham = $(this).val();
							
							var This = $(this);

							$.ajax({
								url: "/admin/api/XoaSanPham",
								type: "GET",
								data: {
									masp: masanpham,
								},
								success: function (value) {
									This.closest("tr").remove();
									swal("Success!", "", "success");
								}
							})
						})	
				  }
				});
						
		
		})
		var files = [];
		var tenhinh = "";
		$("#hinhsanpham").change(function (event) {
			files = event.target.files;
			forms = new FormData();
			forms.append("file", files[0]);
			tenhinh = files[0].name;


			$.ajax({
				url: "/admin/api/UploadFile",
				type: "POST",
				data: forms,
				contentType: false,
				processData: false,
				enctype: "multipart/form-data",
				success: function (value) {

				}
			});
		});
		$("body").on("click", ".btn-chitiet", function (event) {
			event.preventDefault();
			/*$(this).remove();*/
			var chitietclone = $("#chitietsanpham").clone().removeAttr("id");
			$("#container-chitietsanpham").append(chitietclone);
		});
		
		$("body").on("click", ".btn-delete", function (event) {
			event.preventDefault();
			/*$(this).remove();*/
			$(this).parent().eq(0).remove(); 
		});
		function KiemTraChiTietSanPham(mamau,masize,arrayChiTiet)
		{
			for (i = 0; i < arrayChiTiet.length; i++) 
				{
					if(arrayChiTiet[i].mauSanPham.mamau==mamau && arrayChiTiet[i].sizeSanPham.masize==masize )
						{
							return i;
						}
				}
			return -1;
		};
		$("#btn-themsanpham").click(function (event) {
			event.preventDefault();
			var formData = $("#form-sanpham").serializeArray();
			console.log(formData);
			json = {};
			arrayChiTiet = [];

			$.each(formData, function (i, field) {
				json[field.name] = field.value;
			});
			json["giatien"]=$("#giatien").val().toString().replace(/\B(?=(\d{3})+(?!\d))/g, ".");
			objectDanhMuc = {};
			objectDanhMuc["madanhmuc"]=$("#danhMucSanPham").val();
			json["danhMucSanPham"]=objectDanhMuc;
			
			json["mota"]=CKEDITOR.instances['mota'].getData();
			$("#container-chitietsanpham > .chitietsanpham").each(function () {
				mausp = $(this).find('select[name="mau"]').val();
				sizesp = $(this).find('select[name="size"]').val();
				soluongSp = $(this).find('input[name="soluong"]').val();
			
				
				var checkChiTietsp=KiemTraChiTietSanPham(mausp,sizesp,arrayChiTiet);
				if (checkChiTietsp>-1 )
				{
					var soluong= parseInt(arrayChiTiet[checkChiTietsp].soluong) +parseInt( soluongSp);
					arrayChiTiet[checkChiTietsp].soluong = soluong;
				}
				else
					{
					objectChitiet = {};
					objectmauSanPham={};
					objectmauSanPham["mamau"]=mausp;
					objectsizeSanPham={};
					objectsizeSanPham["masize"]=sizesp;
					objectChitiet["mauSanPham"] = objectmauSanPham;
					objectChitiet["sizeSanPham"] = objectsizeSanPham;
					
					objectChitiet["soluong"] = soluongSp;
					arrayChiTiet.push(objectChitiet);
					}

			});

			json["chiTietSanPham"] = arrayChiTiet;
			json["hinhsanpham"] = tenhinh;
			console.log(JSON.stringify(json));
			$('input').next('span').remove();
			$.ajax({
				url: "/admin/api/products",
				type: "POST",
				 contentType: "application/json",

				   data: JSON.stringify(json),
				success: function (res) {
					 if (res.validated) {
		                    //take your successful action here; you may want to redirect to another page
							swal("Success!", "", "success");
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

		$("body").on("click", ".capnhatsanpham", function () {
			$("#btn-themsanpham").addClass("hidden");
			$("#btn-capnhatsanpham").removeClass("hidden");
			$("#btn-thoat").removeClass("hidden");
			masp = $(this).attr("data-id");
			$.ajax({
				url: "/admin/api/laysanphamtheoma",
				type: "POST",
				data: {
					id: masp
				},
				success: function (value) {
					$("#tensanpham").val(value.tensanpham);
					$("#giatien").val(value.giatien);

					editor= CKEDITOR.instances['mota'].setData(value.mota);
					tenhinh=value.hinhsanpham;
					if (value.danhcho === "Nam") {
						$("#rd-nam").prop("checked", true);
					}
					else {
						$("#rd-nu").prop("checked", true);

					}
					$("#danhMucSanPham").val(value.danhMucSanPham.madanhmuc);
					$("#container-chitietsanpham").html("");
					if (value.chiTietSanPham.length==0 )
						{
						$("#container-chitietsanpham").append('<button class=" btn btn-action btn-chitiet">Thêm chi tiết</button><br></br>');
						}
					for (i = 0; i < value.chiTietSanPham.length; i++) {
						var chitietclone = $("#chitietsanpham").clone().removeAttr("id");
						if (i < value.chiTietSanPham.length - 1) {
							chitietclone.find(".btn-chitiet").remove();
						}
						
						chitietclone.find("#mau").val(value.chiTietSanPham[i].mauSanPham.mamau);
						chitietclone.find("#size").val(value.chiTietSanPham[i].sizeSanPham.masize);
						chitietclone.find("#soluong").val(value.chiTietSanPham[i].soluong);
						$("#container-chitietsanpham").append(chitietclone);
					}

				}


			});
		});
		$("#btn-thoat").click(function(event)
		{
			event.preventDefault();
			$("#btn-themsanpham").removeClass("hidden");
			$("#btn-capnhatsanpham").addClass("hidden");
			$("#btn-thoat").addClass("hidden");
		
			editor=CKEDITOR.instances['mota'].setData("");
			$("#tensanpham").val("");
			$("#giatien").val("");
			$("#mota").val("");
				$("#rd-nam").prop("checked", true);
				$("#danhMucSanPham").val(1);
					$("#container-chitietsanpham").html("");
						var chitietclone = $("#chitietsanpham").clone().removeAttr("id");
					
						$("#container-chitietsanpham").append(chitietclone);
		});
	
		$("#btn-capnhatsanpham").click(function (event) {
			event.preventDefault();
			var formData = $("#form-sanpham").serializeArray();
			json = {};
			arrayChiTiet = [];

			$.each(formData, function (i, field) {
				json[field.name] = field.value;
			});
			objectDanhMuc = {};
			objectDanhMuc["madanhmuc"]=$("#danhMucSanPham").val();
			json["danhMucSanPham"]=objectDanhMuc;
			json["giatien"]=$("#giatien").val().toString().replace(/\B(?=(\d{3})+(?!\d))/g, ".");
			
			json["mota"]=CKEDITOR.instances['mota'].getData();
			$("#container-chitietsanpham > .chitietsanpham").each(function () {
				mausp = $(this).find('select[name="mau"]').val();
				sizesp = $(this).find('select[name="size"]').val();
				soluongSp = $(this).find('input[name="soluong"]').val();
				var checkChiTietsp=KiemTraChiTietSanPham(mausp,sizesp,arrayChiTiet);
				if (checkChiTietsp>-1 )
				{
					var soluong= parseInt(arrayChiTiet[checkChiTietsp].soluong) +parseInt( soluongSp);
					arrayChiTiet[checkChiTietsp].soluong = soluong;
				}
				else
					{
					objectChitiet={};
					objectmauSanPham={};
					objectmauSanPham["mamau"]=mausp;
					objectsizeSanPham={};
					objectsizeSanPham["masize"]=sizesp;
					objectChitiet["mauSanPham"] = objectmauSanPham;
					objectChitiet["sizeSanPham"] = objectsizeSanPham;
					
					objectChitiet["soluong"] = soluongSp;
					arrayChiTiet.push(objectChitiet);
					}
			

			});
			json["masanpham"]=masp;
			json["chiTietSanPham"] = arrayChiTiet;
			json["hinhsanpham"] = tenhinh;
			$('input').next('span').remove();
		
			json["mota"]=CKEDITOR.instances['mota'].getData();
		

			$.ajax({
				url: "/admin/api/capnhatsanpham",
				type: "POST",
				 contentType: "application/json",
				   data: JSON.stringify(json),
				success: function (res) {
					 if (res.validated) {
		                    //take your successful action here; you may want to redirect to another page
							swal("Success!", "", "success");
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
	});