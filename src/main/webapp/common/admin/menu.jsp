<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE HTML>
<html>
<head>
<title>Admin-TPShop</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

</head>
<body>
	<div>
	
		<!--//content-inner-->
		<!--/sidebar-menu-->
		<div class="sidebar-menu">
			<header class="logo1">
				<a href="#" class="sidebar-icon"> <span class="fa fa-bars"></span>
				</a>
			</header>
			<div style="border-top: 1px ridge rgba(255, 255, 255, 0.15)"></div>
			<div class="menu">
				<ul id="menu">
					<li><a href='<c:url value="/admin" />'><i class="fa fa-tachometer"></i>
							<span>Dashboard</span>
						<div class="clearfix"></div></a></li>


				
			
					<li id="menu-academico"><a href="#"><i
							class="fa fa-list-ul" aria-hidden="true"></i><span>Quản lý Sản phẩm</span> <span class="fa fa-angle-right" style="float: right"></span>
						<div class="clearfix"></div></a>
						<ul id="menu-academico-sub">
							<li id="menu-academico-avaliacoes">
							<a href='<c:url value="/admin/product" />'>
						<div class="clearfix fa fa-shopping-cart nav_icon"></div>Sản phẩm</a>
							</li>
							<li id="menu-academico-avaliacoes">
							<a href='<c:url value="/admin/categories" />'>
						<div class="clearfix fa fa-list nav_icon"></div>Danh mục</a>
							</li>
						</ul></li>
						<li><a href='<c:url value="/admin/bill" />'><i class="fa fa-money"></i>
							<span>Đơn hàng</span>
						<div class="clearfix"></div></a></li>
						<li id="menu-academico"><a href="#"><i
							class="fa fa-cog" aria-hidden="true"></i><span>Cài đặt</span> <span class="fa fa-angle-right" style="float: right"></span>
						<div class="clearfix"></div></a>
						<ul id="menu-academico-sub">
							<li id="menu-academico-avaliacoes">
							<a href='<c:url value="/admin/crawler" />'>
						<div class="clearfix fas fa-spider"></div> Crawler</a>
							</li>
							
						</ul></li>
						
				
				</ul>
			</div>
		</div>
		<div class="clearfix"></div>
	</div>
	

	
</body>
</html>