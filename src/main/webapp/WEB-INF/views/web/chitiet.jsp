<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Trang chủ</title>
</head>
<body>
<div class="container page-detail">
    <div class="row" style="margin-top: 15px">
        <div class="col-sm-2 col-md-2">

            <div class="menu-listy">
                <h2>our Products</h2>

                <ul class="categories-list">
                    <c:forEach var="parent" items="${danhmucDTO}">
                        <div class="categories">
                            <ul>
                                <h4 class="category-parent" style="margin-left: 10%">${parent.getTendanhmuc()}</h4>
                                <c:if test="${not empty parent.getChildrentsDanhMuc() }">
                                    <c:forEach var="childrent"
                                               items="${parent.getChildrentsDanhMuc()}">
                                        <li><a
                                                href='<c:url value="/sanpham/${childrent.getMadanhmuc()}/${childrent.getTendanhmuc()}" />'>${childrent.getTendanhmuc()}</a>
                                        </li>

                                    </c:forEach>
                                </c:if>
                            </ul>
                        </div>
                    </c:forEach>
                </ul>


            </div>
        </div>

        <div class="col-sm-8 col-md-8">
            <div class="row">
                <div class="col-sm-4 col-md-4 ">
                    <img style="max-width: 100%"
                         src='<c:url value="/resources/images/sanpham/${chiTietSanPham.getHinhsanpham()}" />'>
                </div>

                <div class="col-sm-8 col-md-8">
                    <h3 id="tensp" data-masp="${chiTietSanPham.getMasanpham()}">${chiTietSanPham.getTensanpham()}</h3>
                    <h4 id="giatien" data-giatien="${chiTietSanPham.getGiatien()}"
                        style="color: red">${chiTietSanPham.getGiatien()}VND</h4>

                    <table class="table">
                        <thead>
                        <tr>
                            <td><h5>Màu</h5></td>
                            <td><h5>Size</h5></td>
                            <td><h5>Số lượng</h5></td>
                        </tr>
                        </thead>

                        <tbody>
                        <c:forEach var="listchitietsanpham"
                                   items="${chiTietSanPham.getChiTietSanPham()}">
                            <c:if test="${listchitietsanpham.getSoluong() > 0}">

                                <tr>
                                    <td class="mau"
                                        data-mamau="${listchitietsanpham.getMauSanPham().getMamau()}">${listchitietsanpham.getMauSanPham().getTenmau()}</td>
                                    <td class="size"
                                        data-masize="${listchitietsanpham.getSizeSanPham().getMasize()}">${listchitietsanpham.getSizeSanPham().getSize()}</td>
                                    <td class="soluong">${listchitietsanpham.getSoluong()}</td>
                                    <td>
                                        <button
                                                data-machitiet="${listchitietsanpham.getMachitietsanpham()}"
                                                class="btn btn-success btnGioHang">+ Thêm vào giỏ
                                            hàng
                                        </button>
                                    </td>
                                </tr>

                            </c:if>

                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>

        <div class="col-sm-2 col-md-2">
            <span>${chiTietSanPham.getMota()}</span>
        </div>
    </div>
    <div class="container">
        <div class="component_rating row" style="margin-bottom:20px">
            <h3>Đánh giá</h3>
            <div
                    class="content"
                    style="display:flex; align-items:center;border-radius:5px; border:1px solid #dedede"
            >
                <div class="list-rating" style="width:80%; padding:20px;text-align:center">
                    <span style="font-size:15px;font-style:italic">${chiTietSanPham.getTensanpham()}</span>
                    <div class="item" style="; position:relative">
            <span
                    class="fa fa-star"
                    style="font-size:400%;color:#ff9705;margin:0 auto; text-align:center"
            >
              <span style="margin:auto; text-align:center">${averageRating}</span>
            </span>
                    </div>


                </div>

            </div>
        </div>
        <form id="form-review">
            <div style="display:flex;margin-top:15px; font-size:15px">
                <p style="margin-bottom:0">Chọn đánh giá của bạn</p>
                <span class="list_star" style="margin:0 15px">

						<section class='rating-widget'>

  <!-- Rating Stars Box -->
  <div class='rating-stars text-center'>
    <ul id='stars'>
      <li class='star' title='Poor' data-value='1'>
        <i class='fa fa-star fa-fw'></i>
      </li>
      <li class='star' title='Fair' data-value='2'>
        <i class='fa fa-star fa-fw'></i>
      </li>
      <li class='star' title='Good' data-value='3'>
        <i class='fa fa-star fa-fw'></i>
      </li>
      <li class='star' title='Excellent' data-value='4'>
        <i class='fa fa-star fa-fw'></i>
      </li>
      <li class='star' title='WOW!!!' data-value='5'>
        <i class='fa fa-star fa-fw'></i>
      </li>
    </ul>
  </div>

  <div class='success-box'>
    <div class='clearfix'></div>
	  <i class="icon-next"></i>
    <div class='text-message'></div>
    <div class='clearfix'></div>
  </div>



</section>

        </span>
                <span class="list_text">Tốt</span>
                <div style="margin-left:10px">
                    <textarea style="width:300px" name="comment" placeholder="Gửi đánh giá"></textarea>
                </div>
                <button
                        style="background:rgb(40, 138, 214); width:40px; height:30px; color:white; margin-left:10px"
                        class="btn btn-review"
                >Gửi
                </button>
            </div>
        </form>
        			<div class="list-rating" style="border-radius:5px; border:1px solid #dedede">
        				<div class="rating_item" style="margin:10px 0">
        					<ul style="list-style:none" class="list-review">
                                <c:forEach var="review" items="${reviews}">
        						<li  style="margin-bottom: 5px;">
        							<div>
                                        <c:if test="${review.user.fullName != null}">
                      <span
        					  style="color:#333;font-weight:bold;text-transform:capitalize"
        			  >${review.user.fullName}</span>
                                        </c:if>
                                        <c:if test="${review.user.fullName == null}">
                      <span
                              style="color:#333;font-weight:bold;text-transform:capitalize"
                      >Ẩn danh</span>
                                        </c:if>
        								<a style="color:#2ba832">
        									<i class="fa fa-check-circle"></i>
        								</a>
        							</div>
        							<p style="display:flex; margin-bottom:0">
                      <span class="post_rating">
        							<ul style="list-style:none; display:flex">
                                    <c:forEach begin="1" end="${review.rating}">
        								<li >
        									<i class="fa fa-star" style="color: #ff9705;"></i>
        								</li>
                                    </c:forEach>
        							</ul>
        							</span>
        							<span class="ra_content" style="margin-left:5px">${review.comment}</span>
        							</p>
        							<div>${review.created}</div>
        						</li>
                                    </c:forEach>
        					</ul>
        				</div>
        			</div>
    </div>
</div>
<script src='<c:url value="/resources/JS/jquery-3.4.1.min.js" />'></script>
<script src='<c:url value="/resources/JS/web/chitiet.js" />'></script>

</body>

</html>
