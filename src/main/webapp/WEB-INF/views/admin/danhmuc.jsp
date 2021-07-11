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
				<h3 style="margin-left: 25%">Danh Mục</h3>

				<div class="col-md-7 col-sm-12">
					<!-- <button id="xoa-danhmuc" class="btn  btn-delete">
						<i class="fa fa-trash-o bigger-110 pink"></i>
						
					</button> -->


					<table class="table table-bordered" id="table-danhmuc">
						<thead>
							<tr>
								<th>
									<div class="checkbox">
										<label><input id="checkall" type="checkbox" value=""></label>
									</div>
								</th>
								<th>Mã Danh Mục</th>
								<th>Tên Danh Mục</th>
								<th>ID Danh Mục Gốc</th>
								<th id="xoa-danhmuc" class="btn  btn-delete">
						<i class="fa fa-trash bigger-110 pink icon"></i>
						
					 </th>
					
							</tr>
						</thead>

						<tbody>
							<c:forEach var="value" items="${danhmuc}">
								<tr>
									<td>
										<div class="checkbox">
											<label><input class="checkboxdanhmuc" type="checkbox"
												value="${value.getMadanhmuc()}"></label>
										</div>
									</td>
									<td class="madanhmuc">${value.getMadanhmuc()}</td>
									<td class="tendanhmuc">${value.getTendanhmuc()}</td>
									<td class="parent_madanhmuc">${value.getParent_madanhmuc()}</td>
							<td class="btn btn-action capnhatdanhmuc"
										data-id="${ value.getMadanhmuc()}"><i
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
				<form id="form-danhmuc">
					<div class="col-md-5 col-sm-12">
						<label for="tendanhmuc">Tên danh mục  <span class="required">*</span></label> <br> <input
							type="text" id="tendanhmuc" name="tendanhmuc"
							class="form-control" placeholder="Nhập tên danh mục"  ><br>
							<label for="parent_madanhmuc">Danh Mục Gốc<span class="required">*</span></label> <br>
							<select class="form-control" id="parent_madanhmuc"
								name="parent_madanhmuc">
											<option value="0">
									Đây là danh mục gốc</option>
								<c:forEach var="value" items="${danhmuc}">
								<c:if test ="${value.getParent_madanhmuc()==0}">
								<option value="${value.getMadanhmuc()}">
									${value.getTendanhmuc()}</option>
									</c:if>
							</c:forEach>
							</select>
							<br>
						
							
						
						
				</form>
			
				<div class="method">
					<button id="btn-themdanhmuc" class="btn btn-action">Thêm danh mục</button>
					<button id="btn-capnhatdanhmuc" class="btn btn-action hidden">Cập
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
	<script src='<c:url value="/resources/JS/admin/danhmuc.js" />'></script>

</body>
</html>