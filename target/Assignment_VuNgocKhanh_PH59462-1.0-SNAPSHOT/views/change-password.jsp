<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>

<head>

    <title>Change Password</title>


    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
          rel="stylesheet">


</head>



<body class="bg-light">



<div class="container mt-5">


    <div class="row justify-content-center">


        <div class="col-md-5">



            <div class="card shadow">


                <div class="card-body">



                    <h3 class="text-center mb-4">

                        Đổi mật khẩu

                    </h3>




                    <c:if test="${not empty message}">

                        <div class="alert alert-danger">

                                ${message}

                        </div>

                    </c:if>






                    <form action="${pageContext.request.contextPath}/change-password"
                          method="post">





                        <div class="mb-3">

                            <label class="form-label">

                                Mật khẩu cũ

                            </label>


                            <input type="password"
                                   name="oldPassword"
                                   class="form-control"
                                   required>


                        </div>





                        <div class="mb-3">

                            <label class="form-label">

                                Mật khẩu mới

                            </label>


                            <input type="password"
                                   name="newPassword"
                                   class="form-control"
                                   required>


                        </div>





                        <div class="mb-3">

                            <label class="form-label">

                                Xác nhận mật khẩu mới

                            </label>


                            <input type="password"
                                   name="confirmPassword"
                                   class="form-control"
                                   required>


                        </div>





                        <button class="btn btn-warning w-100">

                            Đổi mật khẩu

                        </button>



                    </form>




                </div>


            </div>


        </div>


    </div>


</div>



</body>

</html>