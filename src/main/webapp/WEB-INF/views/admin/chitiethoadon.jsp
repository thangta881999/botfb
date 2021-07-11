<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<html>
<head>
<title>Admin</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

</head>
<body>
	<div class="page-container" style="background: white">
		<!--/content-inner-->
		<div class="left-content" style="background: white">
			<div class="row">
				<div class="row">
					<h3 style="text-align: center">Chi tiết hóa đơn</h3>

					<div class="col-md-12 col-xs-12 col-sm-12">



						<table class="table table-bordered" id="table-detailsOfbill">
							<thead>
								<tr>
									<!-- <th>
									<div class="checkbox">
										<label><input id="checkall" type="checkbox" value=""></label>
									</div>
								</th> -->
									<th>Mã hóa đơn</th>
									<th>Mã chi tiết sản phẩm</th>
									<th>Số lượng</th>
									<th>Giá tiền(VND)</th>
									<!-- <th id="delete-order" class="btn  btn-delete">
						<i class="fa fa-trash-o bigger-110 pink icon"></i>
						
					 </th> -->

								</tr>
							</thead>

							<tbody>
								<c:forEach var="value" items="${details}">
									<tr>
										<%-- <td class="mahoadon" data-id=${value.getSoluong() }>
										<div class="checkbox">
											<label><input class="checkboxhoadon" type="checkbox"
												value="${value.getChiTietHoaDonId().getMahoadon()}"></label>
										</div>
									</td> --%>
										<td class="mahoadon">${value.getChiTietHoaDonId().getMahoadon()}</td>
										<td class="machitietsanpham">${value.getChiTietHoaDonId().getMachitietsanpham()}</td>
										<td class="soluong">${value.getSoluong()}</td>
										<td class="giatien">${value.getGiatien() }</td>
										<%-- 	<td class="btn btn-action xemchitiet"
										data-id="${ value.getSoluong()}">
										<i class="fa fa-eye icon" aria-hidden="true">
										
										</i>
										</td> --%>
									</tr>
								</c:forEach>
							</tbody>
						</table>

					</div>
				</div>
			</div>
		</div>
	</div>
	<script src='<c:url value="/resources/JS/jquery-3.4.1.min.js" />'></script>
	<script src='<c:url value="/resources/JS/admin/chitiethoadon.js" />'></script>
</body>
</html>