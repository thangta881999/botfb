$(document).ready(

	function()
	{
		$(document).on('click', '.changeState', function(){
	
				var action=$(this).attr("data-action");
				$.ajax({
					url: "/admin/crawler/changestate",
					type: "GET",
					data: {
						action:action
					},
					success: function (value) {
						if(value=="PLAY" || value=="REFRESH")
							{
							$(".stop-playCrawler").attr("data-action","pause");
							$(".stop-playCrawler").addClass("fa-pause");
							$(".stop-playCrawler").removeClass("fa-play")
								$(".state").empty();
								$(".state").append("Đang tiến hành");
								$(".icon").empty();
								$(".icon").append("<i class=\"fa fa-spinner fa-spin fa-3x fa-fw\" aria-hidden=\"true\"></i>"
								);
							}
						if(value=="PAUSE")
								{
							$(".stop-playCrawler").attr("data-action","play");
								$(".stop-playCrawler").addClass("fa-play");
								$(".stop-playCrawler").removeClass("fa-pause");
								$(".state").empty();
								$(".state").append("Tạm dừng");
								$(".icon").empty();
								}
						if(value=="STOP")
						{
					$(".stop-playCrawler").attr("data-action","play");
						$(".stop-playCrawler").addClass("fa-play");
						$(".stop-playCrawler").removeClass("fa-pause");
						$(".state").empty();
						$(".state").append("&nbsp;"+"&nbsp;"+ "&nbsp;"+  "STOP");
						$(".icon").empty();
						}
					}
				});
			})
			
			
	
})
