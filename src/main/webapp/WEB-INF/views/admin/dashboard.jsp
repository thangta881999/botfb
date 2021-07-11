<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.Iterator"%>
<!DOCTYPE HTML>
<html>
<head>
<title>Admin</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

</head>
<body>
	<!--/content-inner-->
	<div class="left-content">
		<div class="mother-grid-inner">


			<!--four-grids here-->
			<div class="four-grids">
				<div class="col-md-3 four-grid">
					<div class="four-agileits">
						<div class="icon">
							<i class="fa fa-spinner fa-spin fa-3x fa-fw" aria-hidden="true"></i>
						</div>
						<div class="four-text">
							<h3>Đơn hàng đang xử lý</h3>
							<h4>${billprocessing}</h4>

						</div>

					</div>
				</div>
				<div class="col-md-3 four-grid">
					<div class="four-agileinfo">
						<div class="icon">
							<i class="fa fa-check fa-3x " aria-hidden="true"></i>
						</div>
						<div class="four-text">
							<h3>Đơn hàng thành công</h3>
							<h4>${billsuccess}</h4>

						</div>

					</div>
				</div>
				<div class="col-md-3 four-grid">
					<div class="four-wthree">
						<div class="icon">
							<i class="fa fa-first-order fa-3x" aria-hidden="true"></i>
						</div>
						<div class="four-text">
							<h3>Tổng đơn hàng</h3>
							<h4>${totalBill}</h4>

						</div>

					</div>
				</div>
				<div class="col-md-3 four-grid">
					<div class="four-w3ls">
						<div class="icon">
							<i class="fa fa-money fa-3x" aria-hidden="true"></i>
						</div>
						<div class="four-text">
							<h3>Doanh thu(VND)</h3>
							<h4 id="doanhthu">${revenue}</h4>

						</div>

					</div>
				</div>

				<div class="clearfix"></div>
			</div>
			<div>
				<canvas id="myChart" style="max-width: 500px;margin:auto"></canvas>
				
				<hr style="width:50%"/>
				<canvas id="lineChart" style="max-width: 500px;margin:auto"></canvas>
			</div>



			<!--inner block start here-->
			<div class="inner-block"></div>
			<!--inner block end here-->
			<!--copy rights start here-->
			<div class="copyrights">
				<p>
					© 2020 Pooled. All Rights Reserved | Design by <a
						href="http://w3layouts.com/" target="_blank">W3layouts</a>
				</p>
			</div>
			<!--COPY rights end here-->
		</div>
	</div>

	<div class="clearfix"></div>

	<script src='<c:url value="/resources/JS/Chart.js" />'></script>
	<script src='<c:url value="/resources/JS/Chart.min.js" />'></script>
	<script src='<c:url value="/resources/JS/Chart.bundle.min.js" />'></script>
	<script src='<c:url value="/resources/JS/Chart.bundle.js" />'></script>
	<script src='<c:url value="/resources/JS/admin/dashboard.js" />'></script>
	<script type="text/javascript">
		/*Số sản phẩm bán ra mỗi loại */

		var category = ${productSellBycat.keySet()}
		var soluong = ${productSellBycat.values()}

		var ctx = document.getElementById("myChart").getContext('2d');
		var myChart = new Chart(ctx, {
			type : 'bar',
			data : {
				labels : category,
				datasets : [ {
					label : 'Số sản phẩm bán ra mỗi loại:',
					data : soluong,
					backgroundColor : [ 'rgba(255, 99, 132, 0.2)',
							'rgba(54, 162, 235, 0.2)',
							'rgba(255, 206, 86, 0.2)',
							'rgba(75, 192, 192, 0.2)',
							'rgba(153, 102, 255, 0.2)',
							'rgba(255, 159, 64, 0.2)' ],
					borderColor : [ 'rgba(255,99,132,1)',
							'rgba(54, 162, 235, 1)', 'rgba(255, 206, 86, 1)',
							'rgba(75, 192, 192, 1)', 'rgba(153, 102, 255, 1)',
							'rgba(255, 159, 64, 1)' ],
					borderWidth : 1
				} ]
			},
			options : {
				scales : {
					yAxes : [ {
						ticks : {
							beginAtZero : true
						},
						scaleLabel: {
				            display: true,
				            labelString: 'Số lượng'
				          }
					} ],
					xAxes : [ {
						ticks : {
							beginAtZero : true
						},
						scaleLabel: {
				            display: true,
				            labelString: 'Tên sản phẩm'
				          }
					} ]
				},
				 title: {
				      display: true,
				      text: 'Biểu đồ thể hiện số sản phẩm bán ra mỗi loại'
				    }
			}
		});
		/* Doanh thu hàng tháng */

		//line
		var months = ${monthlyrevenue.keySet()};
		var Revenue = ${monthlyrevenue.values()}
		//line
		var ctxL = document.getElementById("lineChart").getContext('2d');
		var myLineChart = new Chart(ctxL, {
			type : 'line',
			
			data : {
				labels : months,
				datasets : [ {
					label : "Doanh thu",
					data : Revenue,
				  borderColor: "#8e5ea2",
					borderWidth : 2,
					fill:false
					
				}
			
				]
			},
			options : {
				 responsive : true,
								 title: {
				      display: true,
				      text: 'Biểu đồ thể hiện doanh thu hàng tháng (VND) {tháng,doanhthu}'
				    },
				    scales : {
						yAxes : [ {
							ticks : {
								beginAtZero : true
							},
							scaleLabel: {
					            display: true,
					            labelString: 'Doanh thu'
					          }
						} ],
						xAxes : [ {
							ticks : {
								beginAtZero : true
							},
							scaleLabel: {
					            display: true,
					            labelString: 'Tháng'
					          }
						} ]
					},
				
			}
		});
	</script>

</body>
</html>