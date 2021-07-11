<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Trang chủ</title>
</head>
<body>
	<div class="contact-page">
	<div class="container">
	<div class="dreamcrub">
			   	 <ul class="breadcrumbs">
                    <li class="home">
                       <a href='<c:url value="/" />' title="Go to Home Page">Home</a>&nbsp;
                       <span>&gt;</span>
                    </li>
                    <li class="women">
                       Contact
                    </li>
                </ul>
                <ul class="previous">
                	<li><a href='<c:url value="/" />'>Back to Previous Page</a></li>
                </ul>
                <div class="clearfix"></div>
			   </div>
		<div class="contact-info">
			<h2>FIND US HERE</h2>
		</div>
		<div class="contact-map">
			<iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d6981.498087948508!2d106.79595728075077!3d10.869840099908997!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x317527587e9ad5bf%3A0xafa66f9c8be3c91!2zVHLGsOG7nW5nIMSQ4bqhaSBo4buNYyBDw7RuZyBuZ2jhu4cgVGjDtG5nIHRpbiDEkEhRRyBUUC5IQ00!5e0!3m2!1svi!2s!4v1593512151803!5m2!1svi!2s" width="600" height="450" frameborder="0" style="border:0;" allowfullscreen="" aria-hidden="false" tabindex="0"></iframe>
		</div>
		</div>
		</div>
		<script src='<c:url value="/resources/JS/jquery-3.4.1.min.js" />'></script>
		<script type="text/javascript">
		$(".contact-page").css({
			"margin-top": $(".banner-top").outerHeight()
		})
		$(window).resize(function(){
			$(".contact-page").css({
				"margin-top": $(".banner-top").outerHeight()
			})
		});
		</script>
</body>
</html>