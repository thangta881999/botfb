<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<title>Admin-TPshop</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords"
	content="Pooled Responsive web template, Bootstrap Web Templates, Flat Web Templates, Android Compatible web template, 
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyEricsson, Motorola web design" />
<script type="application/x-javascript">
	 addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } 
</script>
<!-- Bootstrap Core CSS -->
<link rel="stylesheet"
	href='<c:url value="/resources/bootstrap/css/bootstrap.min.css" />' />
<!-- Custom CSS -->
<link rel="stylesheet" href='<c:url value="/resources/css/style.css" />' />

<link rel="stylesheet"
	href='<c:url value="/resources/css/font-awesome.css" />' />
<!-- jQuery -->
<script src='<c:url value="/resources/JS/jquery-3.4.1.min.js" />'></script>
<!-- //jQuery -->
<link
	href='//fonts.googleapis.com/css?family=Roboto:700,500,300,100italic,100,400'
	rel='stylesheet' type='text/css' />
<link href='//fonts.googleapis.com/css?family=Montserrat:400,700'
	rel='stylesheet' type='text/css'>
<!-- lined-icons -->
<link rel="stylesheet"
	href='<c:url value="/resources/css/icon-font.min.css" />' />
<!-- //lined-icons -->
<link rel="stylesheet"
	href='<c:url value="/resources/css/Chart.css" />' />
	<link rel="stylesheet"
	href='<c:url value="/resources/css/Chart.min.css" />' />
<!-- chart css -->

<!-- sweetalert -->
<link rel="stylesheet"
	href='<c:url value="/resources/sweetalert/sweetalert2.min.css" />' />

	<script src='<c:url value="/resources/sweetalert/sweetalert2.min.js" />'></script>
	<script src='https://kit.fontawesome.com/a076d05399.js'></script>
</head >
<body style="overflow-y:auto" class="page-container">
<!-- header -->
    <%@ include file="/common/admin/header.jsp" %>
    <!-- header -->
    <%@include file="/common/admin/menu.jsp" %>
	<dec:body/>
	
		<!-- footer -->
    	<%@ include file="/common/admin/footer.jsp" %>
    	<!-- footer -->
</body>
</html>