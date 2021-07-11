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
		<div class="page-container" style="background: white">
		<!--/content-inner-->
		<div class="left-content" style="background: white">
			<div class="row">
				<h3 style="margin-left: 25%">Crawler Data</h3>

				<div class="col-md-12 col-sm-12">
					<table class="table table-bordered" id="table-crawler">
						<thead>
							<tr>
								
								<th>Website</th>
								<th>Crawler</th>
								<th >Action</th>
					
							</tr>
						</thead>

						<tbody>
								<tr>
									<td class="tenwebsite">https://yame.vn/</td>
									<td>
									<select id="crawlerBy">
								<!-- <option selected disabled hidden name="">Crawler By...</option> -->
								<option value="all">ALL</option>
								<option value="category">Category</option>

							</select>
									 </td>
							<td >
								<div style="margin-left: 20px" class="four-text fa fa-pause fa-2x stop-playCrawler changeState"
								data-action="pause"
								>
								<span style="color: white;">--------</span>   
						
						</div>
							<div class="four-text fa fa-refresh fa-2x refreshCrawler changeState" data-action="refresh">
							<span style="color: white;">--------</span>    
							
						</div>
							<div class="four-text fa fa-stop fa-2x stopCrawler changeState" data-action="stop">
						</div>
						<div class="state">${state }</div>
						<div class="icon" style="margin-left: 5px">
							<i class="fa fa-spinner fa-spin fa-3x fa-fw" aria-hidden="true"></i>
							
						</div> 
							</td>
								</tr>
						</tbody>
					</table>

		
				</div>
			
			</div>

		</div>
	</div>


	<div class="clearfix"></div>

<script src='<c:url value="/resources/JS/admin/crawler.js" />'></script>
</body>
</html>