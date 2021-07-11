<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Đăng nhập</title>
</head>
<body>
	<div id="body-flex-login">
		<div id="container-login">
			<div id="container-login-left">
				<div id="header-top-left" class="header-login">
					<span id="text-logo">WELLCOME</span><br>
					<span id="hint-text-logo">Hãy tạo nên phong cách của bạn với TPShop</span>
				</div>
				
				<div id="header-bottom-left">
					<p><img alt="icon_circle" src='<c:url value="/resources/images/icon_circle.png"/>' /><span>Luôn cập nhật xu hướng thời trang mới nhất</span></p>
					<p><img alt="icon_circle" src='<c:url value="/resources/images/icon_circle.png"/>' /><span>Giảm hơn 50% tất cả các mặt hàng dành cho khách VIP</span></p>
					<p><img alt="icon_circle" src='<c:url value="/resources/images/icon_circle.png"/>' /><span>Tận tình tư vấn để tạo nên phong cách của bạn</span></p>
				</div>
			</div>
			
			<div id="container-login-right">
				<div id="header-top-right" class="header-login">
					<span class="actived" id="dangnhap">Đăng nhập</span> / <span id="dangky">Đăng ký</span>
				</div>
				
				<div id="container-center-login-right">
					<div class="container-login-form" id="container-center-login-right">
						<c:if test="${param.incorrectAccount != null}">
					<div class="alert alert-danger">	
							Username or password incorrect
					</div>
				</c:if>
				<c:if test="${param.accessDenied != null}">
					<div class="alert alert-danger">	
							you Not authorize
					</div>
				</c:if>
						<form name='loginForm' action="<c:url value='j_spring_security_login' />" method='POST'>
							<input id="username" name="username" class="material-textinput input-icon-email" placeholder="username" type="text"/><br/><p></p>
							<input id="password" name="password" class="material-textinput input-icon-password" placeholder="Mật khẩu" type="password"/><br/><p></p>
							<input id="btndangnhap" class="material-primary-button" type="submit" name ="submit" value="ĐĂNG NHẬP"/><p></p>
								<span id="ketqua"></span>
	
   			 <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
								</form>
					</div>
					
					<div class="container-signup-form" id="container-center-login-right">
						<form action="" method="post">
							<input id="username" name="username" class="material-textinput input-icon-email" placeholder="Nhập vào email" type="text"/><br/><p></p>
							<input id="password" name="password" class="material-textinput input-icon-password" placeholder="Mật khẩu" type="password"/><br/><p></p>
							<input id="re_password" name="re_password" class="material-textinput input-icon-password" placeholder="Nhập lại mật khẩu" type="password"/><br/><p></p>
							<input id="btndangky" class="material-primary-button" type="submit" value="ĐĂNG KÝ"/><p></p>
						</form>
					</div>
					
					<span>${kiemtra}</span>
				</div>
				<div id="container-social-login">
			<%-- 		<img alt="icon_facebook" src='<c:url value="/resources/images/icon_facebook.png"/>' />
					<img alt="icon_gooogle" src='<c:url value="/resources/images/icon_google.png"/>' /> --%>
				</div> 
			</div>
		</div>
	</div>
	
</body>
</html>
