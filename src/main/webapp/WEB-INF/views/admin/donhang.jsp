<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin</title>
</head>
<body>
	<div class="page-container ">
		<!--/content-inner-->
		<div class="left-content">
			<div class="row">
				<h3 style="text-align: center">Đơn hàng</h3>

				<div class="col-md-12 col-xs-12 col-sm-12">



					<table class="table table-bordered" id="table-order">
						<thead>
							<tr>
								<th>
									<div class="checkbox">
										<label><input id="checkall" type="checkbox" value=""></label>
									</div>
								</th>
								<th>Tên khách hàng</th>
								<th>SDT</th>
								<th>Địa chỉ</th>
								<th>Hình thức giao hàng</th>
								<th>Tình trạng</th>
								<th>Thanh toán</th>
								<th>Ghi chú</th>
								<th>Ngày lập</th>
								<th>Ngày sửa</th>
								<th>Tổng tiền</th>
								<th id="delete-order" class="btn  btn-delete"><i
									class="fa fa-trash bigger-110 pink icon"></i></th>
							</tr>
						</thead>

						<tbody>
							<c:forEach var="value" items="${bills}">
								<tr>
									<td class="mahoadon" data-id=${value.getMahoadon() }>
										<div class="checkbox">
											<label><input class="checkboxhoadon" type="checkbox"
												value="${value.getMahoadon()}"></label>
										</div>
									</td>
									<td class="tenkhachhang">${value.getTenkhachhang()}</td>
									<td class="sodt">${value.getSodt()}</td>
									<td class="diachigiaohang">${value.getDiachigiaohang()}</td>
									<td class="hinhthucgiaohang">${value.getHinhthucgiaohang() }</td>
									<td class="tinhtrang"><c:choose>
										<c:when test="${value.getTinhtrang()=='FIN'}">
											<div class="order_succsess" value="FIN">Đã nhận</div>

										</c:when>
										<c:when test="${value.getTinhtrang()=='DEL'}">
											<div class="order_delivering" value="DEL">Đang giao hàng</div>

										</c:when>
										<c:otherwise>
											<div class="order_delivering" value="INP">Đang xử lý</div>
										</c:otherwise>


									</c:choose></td>
									<td class="thanhtoan"><c:choose>
											<c:when test="${value.getThanhtoan()}">
												<div class="order_succsess" value="1">Hoàn thành</div>

											</c:when>
											<c:otherwise>
												<div class="order_checkout" value="0">Chưa thanh
													toán</div>
											</c:otherwise>


										</c:choose></td>
									<td class="ghichu">${value.getGhichu() }</td>
									<td class="createdDate">${value.getCreatedDate()}</td>
									<td class="updatedDate">${value.getUpdatedDate()}</td>
									<td class="tongtien">${value.getTongtien()}</td>
									<td class="btn btn-action capnhathoadon"
										data-id="${ value.getMahoadon()}"><i
										class="fa fa-pencil-square-o icon" aria-hidden="true"></i></td>
									<td class="btn btn-action xemchitiet"><a
										href='<c:url value="bill/${ value.getMahoadon()}/details" />'>
											<i class="fa fa-eye icon" aria-hidden="true"> </i>
									</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<nav aria-label="Page navigation">
						<ul class="pagination" id="pagination" totalpage="${tongsoPages}">
						</ul>
					</nav>
				</div>
			</div>
		</div>
	</div>
	<script src='<c:url value="/resources/JS/jquery-3.4.1.min.js" />'></script>
	<script src='<c:url value="/resources/JS/admin/donhang.js" />'></script>
</body>
</html>