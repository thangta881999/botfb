<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Trang chu</title>
</head>
<body>
	<div class="container">
		<div class="products-page">
			<div class="menu">
				<div class="menu-listy">
					<h2>our Products</h2>

					<ul class="categories-list">
						<c:forEach var="parent" items="${danhmucDTO}">
							<div class="categories">
								<ul>
									<h4 class="category-parent" style="margin-left: 10%">${parent.getTendanhmuc()}</h4>
									<c:if test="${not empty parent.getChildrentsDanhMuc() }">
										<c:forEach var="childrent"
											items="${parent.getChildrentsDanhMuc()}">
											<li><a
												href='<c:url value="/sanpham/${childrent.getMadanhmuc()}/${childrent.getTendanhmuc()}" />'>${childrent.getTendanhmuc()}</a></li>

										</c:forEach>
									</c:if>
								</ul>
							</div>
						</c:forEach>
					</ul>

				</div>



			</div>
			<div class="new-product">
				<div class="new-product-top">
					<ul class="product-top-list">
						<li><a href='<c:url value="/"/>'>Home</a>&nbsp;<span>&gt;</span></li>
						<li><span class="act">Best Sales</span>&nbsp;</li>
					</ul>
					<p class="back">
						<a href='<c:url value="/"/>'>Back to Previous</a>
					</p>
					<div class="clearfix"></div>
				</div>
				<div class="mens-toolbar">
					<div class="sort">
						<div class="sort-by">
							<label>Sort Price</label> <select id="sortBy">
								<option selected disabled hidden name="">Order...</option>
								<option value="DESC">DESC</option>
								<option value="ASC">ASC</option>

							</select> <a href=""><img
								src='<c:url value="/resources/images/arrow2.gif" />' alt=""
								class="v-middle"></a>
						</div>
					</div>
		

					<div class="clearfix"></div>
				</div>
				<div id="cbp-vm" class="cbp-vm-switcher cbp-vm-view-grid">
					<div class="cbp-vm-options">
						<a href="#" class="cbp-vm-icon cbp-vm-grid cbp-vm-selected"
							data-view="cbp-vm-view-grid" title="grid">Grid View</a> <a
							href="#" class="cbp-vm-icon cbp-vm-list"
							data-view="cbp-vm-view-list" title="list">List View</a>
					</div>
					<div class="pages">
						<div class="limiter visible-desktop">
							<label>Show</label> <select id="showPerpage">
								<option value="40" selected="selected">40</option>
								<option value="20">20</option>
								<option value="12">12</option>
							</select> per page
						</div>
					</div>
					<div class="clearfix"></div>
					<h3 id="danhmuc" data-id="${madanhmuc}">${tendanhmuc}</h3>
					<ul id="listsanpham">

						<c:forEach var="sanpham" items="${listSanPham}">
							<li><a class="cbp-vm-image"
								href='<c:url value="/chitiet/${sanpham.getMasanpham()}" />'>
							</a>
								<div class="simpleCart_shelfItem">
									<a class="cbp-vm-image"
										href='<c:url value="/chitiet/${sanpham.getMasanpham()}" />'>
										<div class="view view-first">
											<div class="inner_content clearfix">
												<div class="product_image">
													<img
														src='<c:url value="/resources/images/sanpham/${sanpham.getHinhsanpham()}" />'
														class="img-responsive" alt="">
													<div class="mask">
														<div class="info">Quick View</div>
													</div>
													<div class="product_container">
														<div class="cart-left">
															<p class="title">${sanpham.getTensanpham()}</p>
														</div>
														<div class="pricey">
															<span class="gia">${sanpham.getGiatien()} VND</span>
														</div>
														<div class="clearfix"></div>
													</div>
												</div>
											</div>
										</div>
									</a>
									<div class="cbp-vm-details">${sanpham.getMota()}</div></li>
						</c:forEach>
					</ul>

				</div>

				<nav aria-label="Page navigation" class="page-container">
					<ul class="pagination" id="pagination" totalpage="${tongsoPages}">
					</ul>
				</nav>
			</div>
			<div class="clearfix"></div>
		</div>
		<div class="clearfix"></div>

	</div>

	<script src='<c:url value="/resources/web/js/cbpViewModeSwitch.js" />'></script>
	<script src='<c:url value="/resources/web/js/classie.js" />'></script>
	<script src='<c:url value="/resources/JS/jquery-3.4.1.min.js" />'></script>
	<script src='<c:url value="/resources/JS/web/sanpham.js" />'></script>
</body>
</html>