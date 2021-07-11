$(document).ready(
		function() {
			//api tỉnh huyện xã
			$(".page-cart").css({
				"margin-top": $(".banner-top").outerHeight()
			})
			$(window).resize(function(){
				$(".page-cart").css({
					"margin-top": $(".banner-top").outerHeight()
				})
			});
			
			$(".soluong-giohang").keydown(function(event) {
				if (event.keyCode==8 || event.keyCode>=37 && event.keyCode<=40) //cho phép nhất backspace
					return true;
			
				if(event.keyCode<48|| event.keyCode>57) // chỉ cho phép nhập số
					return false

			
					
			
				});
			$.ajax({
				type: 'GET',
				url: 'https://dc.tintoc.net/app/api-customer/public/provinces?page=0&&size=64',
				contentType : 'application/json',
				
				success: data => {
					for(i=0;i<data.length;i++)
						{
						 $('#province').append($('<option>',
							     {
							        value: data[i].id,
							        name:data[i].name,
							        text : data[i].name
							    }));
						}
					
								
								
				},
				error: () => {
					console.log('Error');
				}
			})
			 $('select[name=province]').change(function(){
				 $('select[name=district]').empty().append('<optgroup label="--Quận/Huyện--">');
				 $('select[name=wards]').empty().append('<optgroup label="--Phường/Xã--">');
				 $.ajax({
						type: 'GET',
						url: 'https://dc.tintoc.net/app/api-customer/public/districts',
						data:{
							page:0,
							'provinceId.equals': $('select[name=province').val(),
						},
							
						contentType : 'application/json',
						
						success: data => {
							for(i=0;i<data.length;i++)
								{
								 $('#district').append($('<option>',
									     {
									        value: data[i].id,
									        name:data[i].name,
									        text : data[i].name
									    }));
								}
							$.ajax({
								type: 'GET',
								url: 'https://dc.tintoc.net/app/api-customer/public/wards',
								data:{
									page:0,
									'districtId.equals': data[0].id,
								},
									
								contentType : 'application/json',
								
								success: data => {
									for(i=0;i<data.length;i++)
										{
										 $('#wards').append($('<option>',
											     {
											        value: data[i].id,
											        name:data[i].name,
											        text : data[i].name
											    }));
										}
								},
								error: () => {
									console.log('Error');
								}
						 });
							
						},
						error: () => {
							console.log('Error');
						}
				 });
				 
				 
			 }
			 );
			 $('select[name=district]').change(function(){
				 $('select[name=wards').empty().append('<optgroup label="--Phường/Xã--">');
				 $.ajax({
						type: 'GET',
						url: 'https://dc.tintoc.net/app/api-customer/public/wards',
						data:{
							page:0,
							'districtId.equals': $('select[name=district').val(),
						},
							
						contentType : 'application/json',
						
						success: data => {
							for(i=0;i<data.length;i++)
								{
								 $('#wards').append($('<option>',
									     {
									        value: data[i].id,
									        name:data[i].name,
									        text : data[i].name
									    }));
								}
						},
						error: () => {
							console.log('Error');
						}
				 });
				 
				 
			 }
			 );
			 
			 // end api tỉnh huyện xã
         
			GanTongTienGioHang();

			function GanTongTienGioHang(isEventChange) {
				var tongtiensp = 0;
				$("#tongtien").html("");
				$(".giatien").each(
						function() {
							var giatien = $(this).closest("tr")
									.find(".giatien").attr("data-giatien");
							var soluong = $(this).closest("tr").find(
									".soluong-giohang").val();
							var tongtien = parseInt(giatien) * soluong;
							var format = parseFloat(tongtien).toFixed(3)
									.replace(/(\d)(?=(\d{3})+\.)/g, "$1.")
									.toString();
							tongtiensp = tongtiensp + tongtien;

							if (!isEventChange) {
								$(this).html(format);
							}

							var formatTongtien = tongtiensp.toFixed(3).replace(
									/(\d)(?=(\d{3})+\.)/g, "$1.").toString();
							$("#tongtienBill").html(formatTongtien + "")
							$("#tongtien").html(formatTongtien + "")
						})
			}
			;

			$(".soluong-giohang").change(
					function() {
						var soluong = $(this).val();
						var giatien = $(this).closest("tr").find(".giatien")
								.attr("data-giatien");

						var mamau = $(this).closest("tr").find(".mau").attr(
								"data-mamau");
						var masize = $(this).closest("tr").find(".size").attr(
								"data-masize");
						var masp = $(this).closest("tr").find(".tensp").attr(
								"data-masp");

						var tongtien = soluong * parseInt(giatien);
						var format = tongtien.toFixed(3).replace(
								/(\d)(?=(\d{3})+\.)/g, "$1.").toString();

						$(this).closest("tr").find(".giatien").html(format);

						GanTongTienGioHang(true);
						var json = {};
						json["masp"] = masp;
						json["soluong"] = soluong;
						json["mamau"] = mamau;
						json["masize"] = masize;
						$.ajax({
							url : "/api/carts",
							type : "PUT",
							contentType : 'application/json',
							data : JSON.stringify(json),
							dataType : 'json',

							success : function(value) {
							}
						})
					})

			$(".xoa-giohang").click(
					function() {
						 var self = $(this);
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
								 
									var mamau = self.closest("tr").find(".mau").attr(
											"data-mamau");
									var masize = self.closest("tr").find(".size").attr(
											"data-masize");
									var masp = self.closest("tr").find(".tensp").attr(
											"data-masp");
									var mactsp=self.parent("tr").attr("data-machitiet");
									var json = {};
									json["masp"] = masp;
									json["mamau"] = mamau;
									json["masize"] = masize;
									$.ajax({
										url : "/api/carts",
										type : "DELETE",
										contentType : 'application/json',
										data : JSON.stringify(json),
										success : function(value) {
											$('tr[data-machitiet='+mactsp+']').next('span').remove();
											self.closest("tr").remove();
											

											GanTongTienGioHang(true);
											swal("Success!", "", "success");
										},
										error : function() {
											swal("Error!", "Please Delete Again", "error");
										}
									})
							  }
							});
						
					}
					);
			
			$("#tenkhachhang").keydown(function(event) {
				 $('input[name="tenkhachhang"]').next('span').remove();
				if (event.keyCode==8 || event.keyCode>=37 && event.keyCode<=40) //cho phép nhất backspace
					return true;
			
				
			
					if( $("#tenkhachhang").val().toString().length >= 30 )
					{
						 $('input[name="tenkhachhang"]').after('<span class="error" style="color:red;margin-left: 4%;">"Nhập tên khách hàng từ 3-30 ký tự"</span>');
						return false;
					}
			
				});
			$("body").on("click", ".btn-order", function (event) {
				event.preventDefault();
				
				$('input').next('span').remove();
				$('tr').next('span').remove();
				if ($("#province").find(":selected").attr("name") && $("#street").val()  )
						{
					var address = $("#street").val()+"-"
					+$("#wards").find(":selected").attr("name")+"-"
					+$("#district").find(":selected").attr("name")+"-"
					+ $("#province").find(":selected").attr("name");
						$("#diachigiaohang").val(address);
						}
			
				$.ajax({
					url: "/api/order",
					type: "POST",
					  data : $('form[id=form-order]').serialize(),
					success: function (res) {
						 if (res.validated) {
			                    //take your successful action here; you may want to redirect to another page
								swal("Đặt hàng thành công!", "", "success");
//								thanh toán momo
								if($('input[name=hinhthucthanhtoan]:checked').val()=="MomoPay")
									{
								
									//parameters send to MoMo get get payUrl
									var partnerCode = "MOMOHVSK20200717"
									var accessKey = "M0f7ckZfoECcx7Id"
									
									var returnUrl = "http://localhost:8080/giohang/checkout"
									var notifyurl = "https://momo.vn/"
									var amount = $("#tongtien").val().replace(/\./g,'');
									var orderId = ''+res.id+'';
									var orderInfo = "Payment for bill: "+orderId;
									var requestId = ''+ new Date().getTime() +'';
									var requestType = "captureMoMoWallet"
									var extraData = "merchantName=TP-Shop" //pass empty value if your merchant does not have stores else merchantName=[storeName]; merchantId=[storeId] to identify a transaction map with a physical store

									//before sign HMAC SHA256 with format
									//partnerCode=$partnerCode&accessKey=$accessKey&requestId=$requestId&amount=$amount&orderId=$oderId&orderInfo=$orderInfo&returnUrl=$returnUrl&notifyUrl=$notifyUrl&extraData=$extraData
									
									var rawSignature = "partnerCode="+partnerCode+"&accessKey="+accessKey+"&requestId="+requestId+"&amount="+amount+"&orderId="+orderId+"&orderInfo="+orderInfo+"&returnUrl="+returnUrl+"&notifyUrl="+notifyurl+"&extraData="+extraData;
									//puts raw signature
									var  secretkey="StZ3X0UEA6AbFgnlwIw0vJh1ah3L0kXg";
									var hmac = forge.hmac.create();  
						            hmac.start('sha256', secretkey);
						            hmac.update(rawSignature);  
						            var signature = hmac.digest().toHex();  
									console.log(signature);
									var body = JSON.stringify({
									    partnerCode : partnerCode,
									    accessKey : accessKey,
									    requestId : requestId,
									    amount : amount,
									    orderId : orderId,
									    orderInfo : orderInfo,
									    returnUrl : returnUrl,
									    notifyUrl : notifyurl,
									    extraData : extraData,
									    requestType : requestType,
									    signature : signature,
									});
									var endpoint = "https://test-payment.momo.vn/gw_payment/transactionProcessor";
									 axios.post(endpoint, body)
									.then(function (response) {
									    if(response.data.errorCode!="0")
									    	{
									    	var mes=response.data.localMessage;
									    	if (response.data.details!=null)
									    		{
									    		mes +"\n"+
								    			response.data.details[0].description;
									    		}
									    	swal(mes, "", "warning");
									    	}
									    else
									    	
									    	{
									    	window.location.replace(response.data.payUrl);
									    	}
									    
									})
									.catch(function (error) {
									    console.log(error.message); 
									});
//									 end thanh toán momo
									}
			                } else {
			                    
			                	swal("Error!", "Vui lòng đặt mua hàng và điền đẩy đủ thông tin theo mẫu ", "error");
			                	if (res.errorMessages!=null)
			                		{
			                	$.each(res.errorMessages, function (key, value) {
			                        $('input[name=' + key + ']').after('<span class="error" style="color:red;margin-left: 4%;">' + value + '</span>');
			                        $('tr[data-machitiet=' + key + ']').after('<span class="error" style="color:red;margin-left: 4%;">' + value + '</span>');
			                	
			                	});
			                		}
			                }

						
					},
					error: function() {
						swal("Error!", "Serve đang được bảo trì", "error");
				      },


				});
			});
		});