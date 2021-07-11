<%@page import="com.TP.service.DanhMucService"%>
<%@page import="com.TP.entity.DanhMucSanPham"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.TP.util.SecurityUtils"%>
<!-- header-section-starts -->

<!-- header-section-ends -->
<div class="banner-top"
	style="left: 0; max-width: 100%; overflow: visible; position: fixed !important; top: 0; width: 100%; z-index: 1000;">
	<div class="container">
		<nav class="navbar navbar-default" role="navigation">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#bs-example-navbar-collapse-1">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<div class="logo">
					<h1>
						<a href='<c:url value="/"/>'><span>TP</span> -Shop</a>
					</h1>
				</div>

			</div>
			<!--/.navbar-header-->
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
                    <security:authorize access="isAuthenticated()">
					<li><a href='<c:url value="/recommend?userId=${SecurityUtils.getPrincipal().getUserId()}" />'>PRODUCTS FOR U</a></li>
                    </security:authorize>
					<li>
						<div class="flexbox">
							<div class="search">

								<div>
									<input id="form-search" type="text" placeholder="Search . . ."
										required>
								</div>
							</div>
						</div>

					</li>
					<li><a href='<c:url value="/"/>'>Home</a></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown">Men <b class="caret"></b></a>

						<ul class="dropdown-menu multi-column columns-3 ">
							<div class="row">
								<c:forEach var="parent" items="${danhmucDTO}">
									<div class="col-sm-4 categories">
										<ul class="multi-column-dropdown">
											<h6 class="category-parent">${parent.getTendanhmuc()}</h6>
											<c:if test ="${not empty parent.getChildrentsDanhMuc() }">
											<c:forEach var="childrent" items="${parent.getChildrentsDanhMuc()}">
												<li ><a
													href='<c:url value="/sanpham/${childrent.getMadanhmuc()}/${childrent.getTendanhmuc()}" />'>${childrent.getTendanhmuc()}</a></li>

											</c:forEach>
											</c:if>
										</ul>
									</div>
								</c:forEach>


							</div>



							<div class="clearfix"></div>
							<!-- </div> -->
						</ul></li>


					<li><a href='<c:url value="/contact" />'>CONTACT</a></li>
					<security:authorize access="isAnonymous()">
						<li><a href='<c:url value="/dangnhap"/>'><span
								class="glyphicon glyphicon-user"> </span>Đăng nhập</a></li>
					</security:authorize>
					<security:authorize access="isAuthenticated()">
						<li class="dropdown"><a href="#"
							class="dropdown-toggle circle-avatar" data-toggle="dropdown"
							role="button" aria-haspopup="true" aria-expanded="false"> <span
								class="glyphicon glyphicon-user"> </span>
							<span id="userId" userId=<%=SecurityUtils.getPrincipal().getUserId()%>>
								<%=SecurityUtils.getPrincipal().getFullName()%>
							</span>



							<span
								class="caret"></span></a>
							<ul class="dropdown-menu"
								style="background: #816263; margin-left: 50%; min-width: auto;">
								<li><a class="nav-link"
									   href="<c:url value="/user/${SecurityUtils.getPrincipal().getUserId()}"/>">Thông tin</a></li>
								<li><a class="nav-link"
									   href="<c:url value="/user/bill?userId=${SecurityUtils.getPrincipal().getUserId()}"/>">Đơn hàng</a></li>
								<li><a class="nav-link"
									href="<c:url value='/dangnhap/thoat'/>">Thoát</a></li>
							</ul></li>
					</security:authorize>
					<li>
						<div id="giohang">
							<a href='<c:url value="/giohang/"/>'> <img
								src='<c:url value="/resources/images/icons_shopping_cart.png" />'>
								<c:if test="${soluongsanphamgiohang > 0}">
									<div class="circle-giohang">
										<span value="${soluongsanphamgiohang}" style="color: white">${soluongsanphamgiohang}</span>
									</div>
								</c:if> <c:if
									test="${soluongsanphamgiohang <= 0 || soluongsanphamgiohang == null}">
									<div class="circle-giohang">
										<span value="${soluongsanphamgiohang}" style="color: white">${soluongsanphamgiohang}</span>
									</div>
								</c:if>

							</a>


							<div class="clearfix"></div>
						</div>
					</li>

				</ul>

			</div>
			<!--/.navbar-collapse-->
		</nav>
		<!--/.navbar-->
	</div>
</div>

<style>
<!--
.dropdown-menu>li>a:hover, .dropdown-menu>li>a:focus {
	color: #ffffffbf;
	background-color: #816263;
}


-->
</style>

