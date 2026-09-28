<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>

<head>

    <title>Profile</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
          rel="stylesheet">

</head>



<body class="bg-light">



<nav class="navbar navbar-dark bg-dark">

    <div class="container">


        <a class="navbar-brand"
           href="${pageContext.request.contextPath}/home">

            Poly Video

        </a>

        <div class="d-flex">

            <c:choose>

                <c:when test="${not empty sessionScope.user}">

                    <span class="text-white me-3 mt-1">
                        Xin chào:
                        ${sessionScope.user.fullname}
                    </span>


                    <a class="btn btn-outline-light me-2"
                       href="${pageContext.request.contextPath}/home">
                        Home
                    </a>
                    <a class="btn btn-success me-2"
                       href="${pageContext.request.contextPath}/favorites">
                        Favorites
                    </a>

                    <a class="btn btn-danger"
                       href="${pageContext.request.contextPath}/logout">
                        Logout
                    </a>

                </c:when>


                <c:otherwise>

                    <a class="btn btn-primary"
                       href="${pageContext.request.contextPath}/login">
                        Login
                    </a>

                </c:otherwise>

            </c:choose>

            <c:if test="${sessionScope.user.admin}">

                <a class="btn btn-warning ms-2"
                   href="${pageContext.request.contextPath}/admin/users">
                    Admin Panel
                </a>

            </c:if>

            <c:if test="${sessionScope.user.admin}">
                <a href="${pageContext.request.contextPath}/admin/videos"
                   class="btn btn-warning ms-2">
                    Quản lý Video
                </a>
            </c:if>
        </div>
    </div>

</nav>





<div class="container mt-5">



    <div class="row justify-content-center">



        <div class="col-md-6">



            <div class="card shadow">



                <div class="card-body">



                    <h3 class="text-center mb-4">

                        Thông tin cá nhân

                    </h3>




                    <div class="mb-3">

                        <label class="fw-bold">
                            Username
                        </label>

                        <p>
                            ${user.id}
                        </p>

                    </div>





                    <div class="mb-3">

                        <label class="fw-bold">
                            Fullname
                        </label>

                        <p>
                            ${user.fullname}
                        </p>

                    </div>





                    <div class="mb-3">

                        <label class="fw-bold">
                            Email
                        </label>

                        <p>
                            ${user.email}
                        </p>

                    </div>






                    <a class="btn btn-warning"
                       href="${pageContext.request.contextPath}/change-password">

                        Đổi mật khẩu

                    </a>




                </div>


            </div>


        </div>


    </div>


</div>



</body>

</html>