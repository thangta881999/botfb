<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<div class="footer">
		<div class="container">
			<div class="footer_top">
				<div class="span_of_4">
					<div class="col-md-3 span1_of_4">
						<h4>Shop</h4>
						<ul class="f_nav">
							<c:forEach var="listdanhmuc" items="${danhmuc}">
								<li><a
									href='<c:url value="/sanpham/${listdanhmuc.getMadanhmuc()}/${listdanhmuc.getTendanhmuc()}" />'>${listdanhmuc.getTendanhmuc()}</a></li>
								<li role="separator" class="divider"></li>
							</c:forEach>
						
						</ul>
					</div>
					<div class="col-md-3 span1_of_4">
						<h4>help</h4>
						<ul class="f_nav">
							<li><a href='<c:url value="/contact" />'>Contact to us</a></li>
							
						</ul>
					</div>
					<div class="col-md-3 span1_of_4">
						<h4>account</h4>
						<ul class="f_nav">
							<li><a href='<c:url value="/dangnhap"/>'><span
									class="glyphicon glyphicon-user"> </span>Đăng nhập</a></a></li>
									<li><a href='<c:url value="/dangnhap"/>'><span
									class="glyphicon glyphicon-user"> </span>Tạo tài khoản</a></a></li>
						</ul>
					</div>
					<div class="col-md-3 span1_of_4">
						<h4>popular</h4>
						<ul class="f_nav">
							<c:forEach var="listdanhmuc" items="${danhmuc}">
								<li><a
									href='<c:url value="/sanpham/${listdanhmuc.getMadanhmuc()}/${listdanhmuc.getTendanhmuc()}" />'>${listdanhmuc.getTendanhmuc()}</a></li>
								<li role="separator" class="divider"></li>
							</c:forEach>
						</ul>
					</div>
					<div class="clearfix"></div>
				</div>
			</div>
			<div class="cards text-center">
				<img
					src='<c:url value="/resources/images/cards.jpg" />'
				alt="" />
			</div>
			<div class="copyright text-center">
				<p>
					© Tấn Phát- Anh Thắng Eshop. All Rights Reserved | Design by <a
						href="https://www.facebook.com/imfat5 ">TP </a>
				</p>
			</div>
		</div>
	</div>
	<script type="text/javascript" src='<c:url value="/resources/web/js/bootstrap-3.1.1.min.js" />'></script>
	<script src='<c:url value="/resources/web/js/jquery.min.js" />'></script>
	<!-- for bootstrap working -->

<!-- //for bootstrap working -->
<!-- cart -->
<script src='<c:url value="/resources/web/js/simpleCart.min.js" />'></script>
<!-- cart -->
<script src='<c:url value="/resources/web/js/responsiveslides.min.js" />'></script>
					<script>
						// You can also use "$(window).load(function() {"
						$(function() {
							// Slideshow 4
							$("#form-search").keyup(function(e){
		    if(e.keyCode == 13)
		    {
		    	window.location.href = "/search?keyword="+$("#form-search").val()+"";
		    }
		});
							$("#slider4")
									.responsiveSlides(
											{
												auto : true,
												pager : true,
												nav : false,
												speed : 500,
												namespace : "callbacks",
												before : function() {
													$('.events')
															.append(
																	"<li>before event fired.</li>");
												},
												after : function() {
													$('.events')
															.append(
																	"<li>after event fired.</li>");
												}
											});

						});
					</script>
<script type="text/javascript">
				$(window).load(function() {
					

				});
			</script>
		<script src='<c:url value="/resources/JS/wow.min.js" />'></script>
<script>
	new WOW().init();
</script>
			<script type="text/javascript" src='<c:url value="/resources/web/js/jquery.flexisel.js" />'></script>
					<script type="text/javascript" src='<c:url value="/resources/JS/web/menu.js" />'></script>
						<script type="text/javascript" src='<c:url value="/resources/JS/jquery.twbsPagination.js" />'></script>