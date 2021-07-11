$(document).ready(function(){
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
	 var keyword=($(".keyword").attr("keyword"));
	 
	 var sizedataAllsearch=0;
	 $showPerpage= 20;
	 var flagData=$showPerpage;
	 $.ajax(
	            {
	                type        : 'GET',
	                url         : '/api/search',
	            	data: {
						 keyword:  keyword,
						 offset: 0,
						limit : 100,
						 
						
					},
	                success     : function (value)
	                {
	                	sizedataAllsearch= value.length;
	                }
	            });
		
//		scroll data
		// Biến dùng kiểm tra nếu đang gửi ajax thì ko thực hiện gửi thêm
		var is_busy = false;
		     
		// Biến lưu trữ trang hiện tại
		var page = 1;
		 
		// Biến lưu trữ rạng thái phân trang 
		var stopped = false;
		$("#load_more").click(function() {
			$element = $('.page-search');
			  $loadding = $('#loadding');
			 
			  $button = $(this);
	            // Nếu đang gửi ajax thì ngưng
	            if (is_busy == true){
	                return false;
	            }
	             
	          
	             
	            $button.html('LOADDING ...');
	             
	          
	             
	           
	            $offset=page* $showPerpage;
	            
	            // Gửi Ajax
	            $.ajax(
	            {
	                type        : 'GET',
	                url         : '/api/search',
	            	data: {
						 keyword:  keyword,
						 offset: $offset,
						limit : $showPerpage,
						 
						
					},
	                success     : function (value)
	                {
	                	flagData=flagData +parseInt(value.length);
	                	if (flagData>=sizedataAllsearch)
	                		{
	                		 $button.remove();
	                		}
	                
	                    var phantuchon = $("#listproducts");
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
	    	            page++;
	                }
	            })
	            .always(function()
	            {
	                // Sau khi thực hiện xong ajax thì ẩn hidden và cho trạng thái gửi ajax = false
	            	 $button.html('LOAD MORE');
	                 is_busy = false;
	            });
		});
		
//		margin page
		$(".main-content").css({
			"margin-top": $(".banner-top").outerHeight()
		})
		$(window).resize(function(){
			$(".main-content").css({
				"margin-top": $(".banner-top").outerHeight()
			})
		});

});

