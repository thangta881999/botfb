$(document).ready(
	function () {
//		số sản phẩm muốn hiển thị
		var showPerpage=40;
//		cắt chuỗi detail
		
		var s=$(".cbp-vm-details").text().slice(0,120)+"....";
		$(".cbp-vm-details").text(s) ;
		
//		margin-page
		$(".products-page").css({
			"margin-top": $(".banner-top").outerHeight()
		})
		$(window).resize(function(){
			$(".products-page").css({
				"margin-top": $(".banner-top").outerHeight()
			})
		});
//		sort
		$("#sortBy").change(function () {
			
			var order= ($('#sortBy').val());
			var id= $("#danhmuc").attr("data-id");
			var showPerpage= ($('#showPerpage').val());
			$.ajax({
				url: "/api/categories/"+id+"/order/products",
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
					var phantuchon = $("#listsanpham");
					phantuchon.empty();
					var html="";
				
					for(i=0;i<value.length;i++)
						{
						value[i].mota=value[i].mota.slice(0,120);
						html=html+"<li>";
						
					html+="	<a class=\"cbp-vm-image\"   href=\"/chitiet/" + value[i].masanpham+ "\""+">"
						+ "</a><div class=\"simpleCart_shelfItem\"><a class=\"cbp-vm-image\"  href=\"/chitiet/" + value[i].masanpham+ "\""+">"
					+" <div class=\"view view-first\">"
			   		 +" <div class=\"inner_content clearfix\">"
					+"	<div class=\"product_image\">"
					+"		<img src=\"/resources/images/sanpham/"+value[i].hinhsanpham+"\"  class=\"img-responsive\" alt=\"\">"
					+"		<div class=\"mask\">"
	                 +"      		<div class=\"info\">Quick View</div>"
			          +"        </div>"
					+"		<div class=\"product_container\">"
					+"		   <div class=\"cart-left\">"
					+"			 <p class=\"title\">"+ value[i].tensanpham+ "</p>"
					+"		   </div>"
					+"		   <div class=\"pricey\"><span"
					+"			class=\"gia\">"+value[i].giatien+" VND</span></div>";
						html= html+"<div class=\"clearfix\">";
					     html+"</div>";
	                     html=html+"</div>";
	                     html=html+"</div>";
	                 	html=html+"</div>";
                 	html=html+"</div>";
               		html=html+"</a>";
					html=html+"<div class=\"cbp-vm-details\">" + value[i].mota+"..."
					html=html+"</div>";
					html=html+"</li>";
						}
					phantuchon.append(html);
					
				}
			});
		});
//		
		$("#showPerpage").change(function () {
	
			showPerpage= ($('#showPerpage').val());
			var id= $("#danhmuc").attr("data-id");
			var url="";
			var data={};
			data["offset"]=0;
			 data["limit"]=showPerpage;
				if ($('#sortBy').val()==null)
				{
				 url="/api/categories/"+id+"/products";
				 
				}
			else
				{
				url="/api/categories/"+id+"/order/products";
				data["orderby"]="giatien";
				data["order"]= ($('#sortBy').val());
				}
			
			$.ajax({
				url: url,
				type: "GET",
				data: data,
				success: function (value) {
				
					var phantuchon = $("#listsanpham");
					phantuchon.empty();
					var html="";
				
					for(i=0;i<value.length;i++)
						{
						value[i].mota=value[i].mota.slice(0,120);
						html=html+"<li>";
						
					html+="	<a class=\"cbp-vm-image\"   href=\"/chitiet/" + value[i].masanpham+ "\""+">"
						+ "</a><div class=\"simpleCart_shelfItem\"><a class=\"cbp-vm-image\"  href=\"/chitiet/" + value[i].masanpham+ "\""+">"
					+" <div class=\"view view-first\">"
			   		 +" <div class=\"inner_content clearfix\">"
					+"	<div class=\"product_image\">"
					+"		<img src=\"/resources/images/sanpham/"+value[i].hinhsanpham+"\"  class=\"img-responsive\" alt=\"\">"
					+"		<div class=\"mask\">"
	                 +"      		<div class=\"info\">Quick View</div>"
			          +"        </div>"
					+"		<div class=\"product_container\">"
					+"		   <div class=\"cart-left\">"
					+"			 <p class=\"title\">"+ value[i].tensanpham+ "</p>"
					+"		   </div>"
					+"		   <div class=\"pricey\"><span"
					+"			class=\"gia\">"+value[i].giatien+" VND</span></div>";
						html= html+"<div class=\"clearfix\">";
					     html+"</div>";
	                     html=html+"</div>";
	                     html=html+"</div>";
	                 	html=html+"</div>";
                 	html=html+"</div>";
               		html=html+"</a>";
					html=html+"<div class=\"cbp-vm-details\">" + value[i].mota+"..."
					html=html+"</div>";
					html=html+"</li>";
						}
					phantuchon.append(html);
				}
			});
			
			$.ajax({
				url: "/api/pages/categories/"+id+"/products",
				type: "GET",
				data: {
					id:id,
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
//		
			function loadProducts(page){
			var sotrang =page;
			var offset = (sotrang - 1) * showPerpage;
			var limit=showPerpage;
			var id= $("#danhmuc").attr("data-id");
			var url="";
			var data={};
			data["offset"]=offset;
			 data["limit"]=limit;
			
			if ($('#sortBy').val()==null)
				{
				 url="/api/categories/"+id+"/products";
				 
				}
			else
				{
				url="/api/categories/"+id+"/order/products";
				data["orderby"]="giatien";
				data["order"]= ($('#sortBy').val());
				}
			
			$.ajax({
				url: url,
				type: "GET",
				data: data,
				success: function (value) {
				
					var phantuchon = $("#listsanpham");
					phantuchon.empty();
					var html="";
				
					for(i=0;i<value.length;i++)
						{
						value[i].mota=value[i].mota.slice(0,120);
						html=html+"<li>";
						
					html+="	<a class=\"cbp-vm-image\"   href=\"/chitiet/" + value[i].masanpham+ "\""+">"
						+ "</a><div class=\"simpleCart_shelfItem\"><a class=\"cbp-vm-image\"  href=\"/chitiet/" + value[i].masanpham+ "\""+">"
					+" <div class=\"view view-first\">"
			   		 +" <div class=\"inner_content clearfix\">"
					+"	<div class=\"product_image\">"
					+"		<img src=\"/resources/images/sanpham/"+value[i].hinhsanpham+"\"  class=\"img-responsive\" alt=\"\">"
					+"		<div class=\"mask\">"
	                 +"      		<div class=\"info\">Quick View</div>"
			          +"        </div>"
					+"		<div class=\"product_container\">"
					+"		   <div class=\"cart-left\">"
					+"			 <p class=\"title\">"+ value[i].tensanpham+ "</p>"
					+"		   </div>"
					+"		   <div class=\"pricey\"><span"
					+"			class=\"gia\">"+value[i].giatien+" VND</span></div>";
						html= html+"<div class=\"clearfix\">";
					     html+"</div>";
	                     html=html+"</div>";
	                     html=html+"</div>";
	                 	html=html+"</div>";
                 	html=html+"</div>";
               		html=html+"</a>";
					html=html+"<div class=\"cbp-vm-details\">" + value[i].mota+"..."
					html=html+"</div>";
					html=html+"</li>";
						}
					phantuchon.append(html);
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
	});