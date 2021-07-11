<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div class="footer">
    <div class="footer-inner " style="margin-left:50%">
        <div class="footer-content">
						<span class="bigger-120">
							<span class="blue bolder">Đại học CNTT</span>
						</span>

            &nbsp; &nbsp;
            <span class="action-buttons">
							<a href="#">
								<i class="ace-icon fa fa-twitter-square light-blue bigger-150"></i>
							</a>

							<a href="#">
								<i class="ace-icon fa fa-facebook-square text-primary bigger-150"></i>
							</a>

							<a href="#">
								<i class="ace-icon fa fa-rss-square orange bigger-150"></i>
							</a>
						</span>
        </div>
    </div>
</div>
<script>
		var toggle = true;
		
		$(".sidebar-icon").click(
				function() {
					if (toggle) {
						$(".page-container").addClass("sidebar-collapsed")
								.removeClass("sidebar-collapsed-back");
						$("#menu span").css({
							"position" : "absolute"
						});
					} else {
						$(".page-container").removeClass("sidebar-collapsed")
								.addClass("sidebar-collapsed-back");
						setTimeout(function() {
							$("#menu span").css({
								"position" : "relative"
							});
						}, 400);
					}

					toggle = !toggle;
				});
	</script>
<!--js -->
	<script src='<c:url value="/resources/JS/jquery.nicescroll.js" />'></script>
	<script src='<c:url value="/resources/JS/scripts.js" />'></script>
	<!-- Bootstrap Core JavaScript -->
	<script src='<c:url value="/resources/bootstrap/js/bootstrap.min.js" />'></script>
	<!-- /Bootstrap Core JavaScript -->
							<script type="text/javascript" src='<c:url value="/resources/JS/jquery.twbsPagination.js" />'></script>
