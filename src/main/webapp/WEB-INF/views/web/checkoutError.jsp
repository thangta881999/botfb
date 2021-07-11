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
	   			
				<div class="new-product-top">
					<ul class="product-top-list">
						<li><a href='<c:url value="/"/>'>Home</a>&nbsp;<span>&gt;</span></li>
						<li><span class="act">Cart->Error Payment</span>&nbsp;</li>
					</ul>
					<p class="back"><a href='<c:url value="/giohang/"/>'>Back to Previous</a></p>
					<div class="clearfix"></div>
				</div>
				<div class="row">
				<div class="col-md-12" style="text-align: center"><h3> Thanh toán không thành công</h3></div>
				</div>
			
		
   </div>
   	<script src='<c:url value="/resources/JS/jquery-3.4.1.min.js" />'></script>
<script type="text/javascript">
$(".new-product-top").css({
	"margin-top": $(".banner-top").outerHeight()
})
$(window).resize(function(){
	$(".new-product-top").css({
		"margin-top": $(".banner-top").outerHeight()
	})
});
</script>
</body>
</html>