<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>

    <title>Register - Poly Video</title>

    <meta charset="UTF-8">


    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
          rel="stylesheet">

</head>


<body class="bg-light">



<div class="container mt-5">


    <div class="row justify-content-center">


        <div class="col-md-6">



            <div class="card shadow">


                <div class="card-body p-4">



                    <h3 class="text-center mb-4">
                        Đăng ký tài khoản
                    </h3>




                    <!-- Hiển thị thông báo -->

                    <c:if test="${not empty message}">

                        <div class="alert alert-danger">

                                ${message}

                        </div>

                    </c:if>





                    <form action="${pageContext.request.contextPath}/register"
                          method="post">





                        <!-- ID -->

                        <div class="mb-3">


                            <label class="form-label">

                                Username

                            </label>


                            <input type="text"
                                   name="id"
                                   class="form-control"
                                   placeholder="Nhập username"
                                   required>


                        </div>





                        <!-- FULLNAME -->


                        <div class="mb-3">


                            <label class="form-label">

                                Fullname

                            </label>


                            <input type="text"
                                   name="fullname"
                                   class="form-control"
                                   placeholder="Nhập họ tên"
                                   required>


                        </div>





                        <!-- EMAIL -->


                        <div class="mb-3">


                            <label class="form-label">

                                Email

                            </label>


                            <input type="email"
                                   name="email"
                                   class="form-control"
                                   placeholder="example@gmail.com"
                                   required>


                        </div>





                        <!-- PASSWORD -->


                        <div class="mb-3">


                            <label class="form-label">

                                Password

                            </label>


                            <input type="password"
                                   name="password"
                                   class="form-control"
                                   placeholder="Nhập mật khẩu"
                                   required>


                        </div>





                        <!-- CONFIRM PASSWORD -->


                        <div class="mb-3">


                            <label class="form-label">

                                Confirm Password

                            </label>


                            <input type="password"
                                   name="confirmPassword"
                                   class="form-control"
                                   placeholder="Nhập lại mật khẩu"
                                   required>


                        </div>






                        <button type="submit"
                                class="btn btn-success w-100">

                            Register

                        </button>




                    </form>






                    <div class="text-center mt-3">


                        <span>
                            Đã có tài khoản?
                        </span>


                        <a href="${pageContext.request.contextPath}/login">

                            Đăng nhập

                        </a>


                    </div>





                </div>


            </div>



        </div>


    </div>


</div>




</body>

</html>