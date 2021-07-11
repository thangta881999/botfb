$(document).ready(
function() {
	$(".categories ul li").hide();  
	//Ẩn và hiện các items con khi click vào items cha
	$('.categories').hover(function (e) {
	      //e.preventDefault();
	      $(this).children('ul').children('.category-parent').toggleClass('active').siblings('li').slideToggle(200);
	      $(this).siblings().children('category-parent').removeClass('active');
	      $(this).siblings().children('li').slideUp(200);
    })
    



}		
);
