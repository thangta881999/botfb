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
				<h3 style="margin-left: 25%">Sản phẩm</h3>

				<div class="col-md-7 col-sm-12">



					<table class="table" id="table-sanpham">
						<thead>
							<tr>
								<th>
									<div class="checkbox">
										<label><input id="checkall" type="checkbox" value=""></label>
									</div>
								</th>
								<th>Tên sản phẩm</th>
								<th>Hình ảnh</th>
								<th>Giá tiền (VND)</th>
								<th>Dành cho</th>
								<th id="xoa-sanpham" class="btn  btn-delete"><i
									class="fa fa-trash bigger-110 pink icon"></i></th>

							</tr>
						</thead>

						<tbody>
							<c:forEach var="value" items="${listSanPhams}">
								<tr>
									<td>
										<div class="checkbox">
											<label><input class="checkboxsanpham" type="checkbox"
												value="${value.getMasanpham()}"></label>
										</div>
									</td>
									<td class="tensp">${value.getTensanpham()}</td>
									<td class="hinhsanhpham"><img
										style="width: 50px; height: 50px"
										src='<c:url value="/resources/images/sanpham/${value.getHinhsanpham()}" />'>
									</td>
									<td class="giatien">${value.getGiatien()}</td>
									<td class="danhcho">${value.getDanhcho()}</td>
									<td class="btn btn-action capnhatsanpham"
										data-id="${ value.getMasanpham()}"><i
										class="fa fa-pencil-square-o icon"></i></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
						<nav aria-label="Page navigation">
							<ul class="pagination" id="pagination" totalpage="${tongsoPages}">
							</ul>
						</nav>

				</div>
				<form id="form-sanpham">
					<div class="col-md-5 col-sm-12">
						<label for="tensanpham">Tên sản phẩm <span
							class="required">*</span></label> <br> <input type="text"
							id="tensanpham" name="tensanpham" class="form-control"
							placeholder="Nhập tên sản phẩm"><br> <label
							for="mota">Mô tả</label>

						<!-- <textarea rows="5" type="text" id="mota" name="mota"
							class="form-control" placeholder="Nhập vào mô tả"></textarea>
						<br>  -->
						<textarea rows="5" id="mota" name="mota" class="form-control"
							placeholder="Nhập vào mô tả"></textarea>
						<br>

						<script src="https://cdn.ckeditor.com/4.13.0/standard/ckeditor.js"></script>

						<label for="giatien">Giá tiền(VND) <span class="required">*</span></label><br>
						<input name="giatien" id="giatien" class="form-control"
							placeholder="Nhập vào giá tiền"> <br> <label
							for="danhcho">Dành cho</label> <br> <input type="radio"
							name="danhcho" id="rd-nam" value="Nam" checked="checked">Nam
						<input type="radio" name="danhcho" id="rd-nu" value="Nữ">Nữ
						<br> <label for="danhMucSanPham">Danh mục</label> <select
							name="danhMucSanPham" class="form-control" id="danhMucSanPham">
							<c:forEach var="valuedanhmuc" items="${danhmuc}">
							<c:if test ="${valuedanhmuc.getParent_madanhmuc()>0}">
								<option value="${valuedanhmuc.getMadanhmuc()}">${valuedanhmuc.getTendanhmuc()	}
								</option>
								</c:if>
							</c:forEach>
						</select> <br> <label for="hinhsanpham">Hình ảnh <span
							class="required">*</span></label> <br> <input type="file"
							name="hinhsanpham" id="hinhsanpham" class="form-control"
							placeholder="Chọn hình ảnh"><br>
				</form>
				<div class="chitietsanpham" id="chitietsanpham">
					<button class=" btn btn-action btn-chitiet fa fa-plus"></button>
					<button
						class=" btn btn-action btn-delete fa fa-trash bigger-110 pink icon"></button>

					<br> <br> <select class="form-control" id="mau"
						name="mau">
						<c:forEach var="valmau" items="${mauSanPhams}">
							<option value="${valmau.getMamau()}">
								${valmau.getTenmau()}</option>
						</c:forEach>
					</select> <br> <select class="form-control" id="size" name="size">
						<c:forEach var="valsize" items="${sizeSanPhams}">
							<option value="${valsize.getMasize()}">
								${valsize.getSize()}</option>
						</c:forEach>
					</select> <br> <label for="soluong">So luong</label> <input min="1"
						type="number" id="soluong" name="soluong" class="form-control"
						value="1" placeholder="Nhap so luong"> <br>
				</div>
				<div id="container-chitietsanpham">
					<div class="chitietsanpham">
						<button class=" btn btn-action btn-chitiet fa fa-plus"></button>


							<br> <br> <select class="form-control" id="mau"
								name="mau">
							<c:forEach var="valmau" items="${mauSanPhams}">
								<option value="${valmau.getMamau()}">
									${valmau.getTenmau()}</option>
							</c:forEach>
						</select> <br> <select class="form-control" id="size" name="size">
							<c:forEach var="valsize" items="${sizeSanPhams}">
								<option value="${valsize.getMasize()}">
									${valsize.getSize()}</option>
							</c:forEach>
						</select> <br> <label for="soluong">So luong</label> <input min="1"
							type="number" id="soluong" name="soluong" class="form-control"
							value="1" placeholder="Nhap so luong"> <br>


					</div>
				</div>
				<div class="method">
					<button id="btn-themsanpham" class="btn btn-action">Thêm
						sản phẩm</button>
					<button id="btn-capnhatsanpham" class="btn btn-action hidden">Cập
						nhật</button>
					<button id="btn-thoat" class="btn btn-action hidden">Thoát</button>
				</div>
			</div>

		</div>
	</div>
	<!--//content-inner-->

	<!--/sidebar-menu-->

	<div class="clearfix"></div>
	</div>
	<script src='<c:url value="/resources/JS/admin/sanpham.js" />'></script>

</body>
</html>