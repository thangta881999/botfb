<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>Admin</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>

</head>
<body>
<div class="container page-search">
    <div class="main-content">


        <div class="products-grid container">
            <header>
                <h3 class="head text-center">Thông tin tài khoản</h3>
            </header>

            <div class="clearfix"></div>
            <div class="row" id="user-data" name="user-data">
                <div
                        class=" col-md-3">
                </div>
                <div
                        class=" col-md-6 col-sm-6 simpleCart_shelfItem text-center ">
                    <form id="form-user">
                        <input type="text"
                               id="id" name="id"
                               class="form-control hidden"
                               value="${user.id}">
                        <label for="fullName">Full Name</label> <br> <input type="text" readonly="true"
                                                                            id="fullName" name="fullName"
                                                                            class="form-control"
                                                                            value="${user.fullName}"
                    ><br>
                        <label for="userName">User Name </label><br> <input type="text" readonly="true"
                                                                            id="userName" name="userName"
                                                                            class="form-control"
                                                                            value="${user.userName}"
                    ><br>
                        <div class="password ">
                        <label for="password">Password </label><br> <input type="password" readonly="true"
                                                                           id="password" name="password"
                                                                           class="form-control"
                                                                           value="${user.password}"
                    ><br>
                        </div>
                        <div>
                        <button id="btn-changePassword" class="btn btn-action hidden ">Change Password
                        </button>
                        </div>
                        <div class="password1 hidden">
                            <label for="password1">New Password</label> <br> <input type="password1"
                                                                                    id="password1" name="password1"
                                                                                    class="form-control "
                                                                                    placeholder="Nhập lại mật khẩu mới"
                        ><br>
                        </div>
                        <div class="password2 hidden">
                            <label for="password2">Re-input New Password</label> <br> <input type="password"
                                                                                             id="password2"
                                                                                             name="password2"
                                                                                             class="form-control "
                                                                                             placeholder="Nhập mật khẩu mới"
                        ><br>
                        </div>

                        <label for="phone">Phone: </label><br>
                        <input name="phone" id="phone" class="form-control" readonly="true"
                               value="${user.phone}"> <br>
                        <br>
                    </form>
                    <div class="method">

                        <button id="btn-capnhat" class="btn btn-action ">Cập
                            nhật
                        </button>
                        <button id="btn-ok" class="btn btn-action hidden ">Ok
                        </button>
                        <button id="btn-thoat" class="btn btn-action hidden ">
                            Thoát
                        </button>
                    </div>
                </div>
            </div>
        </div>


    </div>


    <div class="clearfix"></div>
</div>

<div class="clearfix"></div>
</div>
<script src='<c:url value="/resources/JS/jquery-3.4.1.min.js" />'></script>
<script src='<c:url value="/resources/JS/web/user.js" />'></script>

</body>
</html>