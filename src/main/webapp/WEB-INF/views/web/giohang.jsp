<%@page import="java.util.List"%>
<%@ page import="com.TP.util.SecurityUtils" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Trang chu</title>
</head>
<body>
	<div class="container page-cart" style="margin-top:70px">
		<div class="row">
			<div class="col-md-8 col-sm-12">
				<h3>Danh sách sản phẩm trong giỏ hàng</h3>
				<table class="table">
					<thead>
						<tr>
							<td><h5>Tên sản phẩm</h5></td>
							<td><h5>Màu</h5></td>
							<td><h5>Size</h5></td>
							<td><h5>Số lượng</h5></td>
							<td><h5>Giá tiền</h5></td>
						</tr>
					</thead>

					<tbody>
						<c:forEach var="giohang" items="${giohangs}">
							<tr data-machitiet="${giohang.getMachitiet()}">
								<td class="tensp" data-masp="${giohang.getMasp()}">${giohang.getTensp()}</td>
								<td class="mau" data-mamau="${giohang.getMamau()}">${giohang.getTenmau()}</td>
								<td class="size" data-masize="${giohang.getMasize()}">${giohang.getTensize()}</td>
								<td class="soluong" ><input class="soluong-giohang" min="1" type="number" value="${giohang.getSoluong()}"></td>
								<td class="giatien" data-giatien="${giohang.getGiatien()}">${giohang.getGiatien()}</td>
								<td class="xoa-giohang btn btn-danger"  style="margin-left: 15px;">Xoá</td>
							</tr>
						
						</c:forEach>
					</tbody>
				</table>
				
				<h4>Tổng tiền: <span id="tongtienBill" style="color: red;"></span> VND</h4>
			</div>

			<div class="col-md-4 col-sm-12">
				<h3>Thông tin người nhận / mua</h3>
				<form  id="form-order">
					<div class="form-group">
						<label for="tennguoimua">Tên người mua/nhận <span class="required">*</span></label>
						<security:authorize access="isAuthenticated()">
							<input class="form-control" readonly="readonly" id="tenkhachhang" name="tenkhachhang" value="<%=SecurityUtils.getPrincipal().getFullName()%>">

						</input> <br>
						</security:authorize>
						<security:authorize access="isAnonymous()">
							<input class="form-control" id="tenkhachhang" name="tenkhachhang" >

						</security:authorize>

						<label for="dienthoailienlac">Điện thoại liên lạc <span class="required">*</span></label>
						<security:authorize access="isAuthenticated()">
							<input class="form-control" id="sodt" name="sodt" value="<%=SecurityUtils.getPrincipal().getPhone()%>"></input><br>
						</security:authorize>
						<security:authorize access="isAnonymous()">
						<input class="form-control" id="sodt" name="sodt" ></input><br>
						</security:authorize>

						<div class="radio">
							<label class="active"><input checked="" type="radio" name="hinhthucgiaohang" value="Giao hàng tận nơi">Giao hàng tận nơi</input></label>
						</div>

						<div class="radio">
							<label> <input type="radio" name="hinhthucgiaohang" value="Nhận hàng tại cửa hàng">Nhận hàng tại cửa hàng</input></label>
						</div><br>
						<label for="diachigiaohang">Địa chỉ nhận hàng<span class="required">*</span></label> 
						 <select 
							name="province" class="form-control" id="province">
							 <optgroup label="--Tỉnh/Thành phố--">
							  <option selected disabled hidden name="">Tỉnh/Thành phố</option>
						</select> <br>
						<select
							name="district" class="form-control" id="district">
							  <optgroup label="--Quận/Huyện--">
							  <option selected disabled hidden name="">Quận/Huyện</option>
						</select> <br>
						<select
							name="wards" class="form-control" id="wards">
							  <optgroup label="--Phường/Xã--">
							   <option selected disabled hidden name="">Phường/Xã</option>
						</select> <br>
							<input class="form-control" id="street" name="street" required="required" placeholder="Số nhà,tên đường"></input>  <br>
							<input class="form-control" id="diachigiaohang" name="diachigiaohang" type="hidden"></input>  <br>
						<label for="ghichu">Ghi chú</label>
						<textarea class="form-control" rows="5" id="ghichu" name="ghichu"></textarea> <br>
						<h4>Tổng tiền: <textarea   class="form-control" id="tongtien" name="tongtien" rows="1" cols="1"  style="color: red;" readonly="readonly"></textarea></h4>
						
							<div class="radio">
							<label class="active"><input checked="" type="radio" name="hinhthucthanhtoan" value="taicho">Thanh toán khi nhận hàng</input></label>
						</div>

						<div class="radio">
							<label> <input type="radio" name="hinhthucthanhtoan" value="MomoPay">Thanh toán bằng momo</input>
							<img style="height:32px;width:32px"
					src='<c:url value="/resources/images/momopay.jpg" />'
				alt="" />
							 </label>
						</div><br>
						<input class="btn btn-primary btn-order" value="Đặt hàng"></input>
					</div>
				</form>
			</div>
		</div>
	</div>
		<script src='<c:url value="/resources/JS/jquery-3.4.1.min.js" />'></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/forge/0.8.2/forge.all.min.js"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
					<script src='<c:url value="/resources/JS/web/giohang.js" />'></script>
					
</body>
</html>