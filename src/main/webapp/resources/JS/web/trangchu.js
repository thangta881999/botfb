$(document).ready(
	function () {
//		số sản phẩm muốn hiển thị
		var showPerpage=40;
		
			$(".product").css({
				"height": $(".product").width()+180
			})
//		
		$(window).scroll(function(){
			$(".product").css({
				"height": $(".product").width()+180
			})
		});
		$(window).resize(function(){
			$(".product").css({
				"height": $(".product").width()+180
			})
		});
//		
	function loadProducts(page){
		var sotrang=page;
		var offset = (sotrang - 1) *showPerpage;
		var limit=showPerpage;
		
		var url="";
		var data={};
		data["offset"]=offset;
		 data["limit"]=limit;
		
		if ($('#sortBy').val()==null)
			{
			 url="/api/products";
			 
			}
		else
			{
			url="/api/order/products";
			data["orderby"]="giatien";
			data["order"]= ($('#sortBy').val());
			}

		$.ajax({
			url: url,
			type: "GET",
			data: data,
				
			success: function (value) {
			
				var phantuchon = $("#listproducts");
				phantuchon.empty();
				var html="";

				for(i=0;i<value.length;i++)
					{
				
					html=html+"<div class=\"col-md-4 col-sm-6 product simpleCart_shelfItem text-center\">" + 
				"				<a href=\"chitiet/"+ value[i].masanpham+ "\""+">" + 
				 
				"					<img src=\"/resources/images/sanpham/"+value[i].hinhsanpham+"\" /><br /> </a>" 
				
				+"<div class=\"mask\">" 
				+"<a href=\"chitiet/" + value[i].masanpham+ "\""+">QuickView</a>"  +
				"</div>"
				
				+"<a  class=\"product_name\"  href=\"chitiet/" + value[i].masanpham+ "\""+">"+value[i].tensanpham+"</a>"
				+"<p>"
				+"<span class=\"item_price\">" +value[i].giatien+"VND</span>"
				+"</p>"
				
				
		
				
				html=html+"</div>";
					}
				phantuchon.append(html);
			}
		});
		
	};
			 window.pagObj = $('#pagination').twbsPagination({
		            totalPages: $(".pagination").attr('totalpage'),
		            visiblePages: 8,
	            onPageClick: function (event, page) {
	    		loadProducts(page);
	            }
	        });
//		
$("#sortBy").change(function () {
			
			var order= ($('#sortBy').val());
			var id= $("#danhmuc").attr("data-id");
			var showPerpage= ($('#showPerpage').val());
			$.ajax({
				url: "/api/order/products",
				type: "GET",
				data: {
					 orderby:  "giatien",
					 order:order,
					 offset:0,
					 limit: showPerpage
					
				},
				success: function (value) {
					$(".paging-item").removeClass("active");
					$(".pagination").find('li:first').addClass("active");
					var phantuchon = $("#listproducts");
					phantuchon.empty();
					var html="";

					for(i=0;i<value.length;i++)
						{
					
						html=html+"<div class=\"col-md-4 col-sm-6 product simpleCart_shelfItem text-center\">" + 
					"				<a href=\"chitiet/"+ value[i].masanpham+ "\""+">" + 
					 
					"					<img src=\"/resources/images/sanpham/"+value[i].hinhsanpham+"\" /><br /> </a>" 
					
					+"<div class=\"mask\">" 
					+"<a href=\"chitiet/" + value[i].masanpham+ "\""+">QuickView</a>"  +
					"</div>"
					
					+"<a  class=\"product_name\"  href=\"chitiet/" + value[i].masanpham+ "\""+">"+value[i].tensanpham+"</a>"
					+"<p>"
					+"<span class=\"item_price\">" +value[i].giatien+"VND</span>"
					+"</p>"
					
					
			
					
					html=html+"</div>";
						}
					phantuchon.append(html);
					
				}
			});
		});
//		
		$("#showPerpage").change(function () {
		
			showPerpage= ($('#showPerpage').val());
			var url="";
			var data={};
			data["offset"]=0;
			 data["limit"]=showPerpage;
				if ($('#sortBy').val()==null)
				{
				 url="/api/products";
				 
				}
			else
				{
				url="/api/order/products";
				data["orderby"]="giatien";
				data["order"]= ($('#sortBy').val());
				}
			
			$.ajax({
				url: url,
				type: "GET",
				data: data,
				success: function (value) {
				
					var phantuchon = $("#listproducts");
					phantuchon.empty();
					var html="";

					for(i=0;i<value.length;i++)
						{
					
						html=html+"<div class=\"col-md-4 col-sm-6 product simpleCart_shelfItem text-center\">" + 
					"				<a href=\"chitiet/"+ value[i].masanpham+ "\""+">" + 
					 
					"					<img src=\"/resources/images/sanpham/"+value[i].hinhsanpham+"\" /><br /> </a>" 
					
					+"<div class=\"mask\">" 
					+"<a href=\"chitiet/" + value[i].masanpham+ "\""+">QuickView</a>"  +
					"</div>"
					
					+"<a  class=\"product_name\"  href=\"chitiet/" + value[i].masanpham+ "\""+">"+value[i].tensanpham+"</a>"
					+"<p>"
					+"<span class=\"item_price\">" +value[i].giatien+"VND</span>"
					+"</p>"
					
					
			
					
					html=html+"</div>";
						}
					phantuchon.append(html);
				}
			});
			$.ajax({
				url: "/api/pages/products",
				type: "GET",
				data: {
					limit:showPerpage
				},
				success: function (value) {
				

					$(".pagination").twbsPagination('destroy');
					window.pagObj = $('#pagination').twbsPagination({
			            totalPages: value,
			            visiblePages: 8,
		            onPageClick: function (event, page) {
		    		loadProducts(page);
		            }
		        });
			
				}
			})
		})
	});