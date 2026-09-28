<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>

    <title>Login - Poly Video</title>

    <meta charset="UTF-8">


    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
          rel="stylesheet">


</head>


<body class="bg-light">



<div class="container mt-5">


    <div class="row justify-content-center">


        <div class="col-md-5">



            <div class="card shadow">


                <div class="card-body p-4">



                    <h3 class="text-center mb-4">
                        Đăng nhập
                    </h3>



                    <!-- Hiển thị lỗi -->

                    <c:if test="${not empty message}">

                        <div class="alert alert-danger">

                                ${message}

                        </div>

                    </c:if>





                    <form action="${pageContext.request.contextPath}/login"
                          method="post">





                        <!-- EMAIL -->

                        <div class="mb-3">


                            <label class="form-label">

                                Email

                            </label>


                            <input type="email"
                                   name="email"
                                   value="${rememberEmail}"
                                   class="form-control"
                                   placeholder="Nhập email"
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





                        <!-- REMEMBER -->


                        <div class="form-check mb-3">


                            <input class="form-check-input"
                                   type="checkbox"
                                   name="remember"
                                   value="true"
                                   id="remember">


                            <label class="form-check-label"
                                   for="remember">

                                Remember me

                            </label>


                        </div>





                        <!-- BUTTON -->


                        <button type="submit"
                                class="btn btn-primary w-100">

                            Login

                        </button>




                    </form>





                    <div class="text-center mt-3">


                        <a href="${pageContext.request.contextPath}/register">

                            Đăng ký tài khoản

                        </a>


                        <br>


                        <a href="${pageContext.request.contextPath}/forgot-password">

                            Quên mật khẩu?

                        </a>


                    </div>




                </div>


            </div>



        </div>


    </div>


</div>




</body>

</html>