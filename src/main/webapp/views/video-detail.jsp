<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>

<head>

    <title>${video.title}</title>

    <meta charset="UTF-8">


    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
          rel="stylesheet">


</head>


<body>


<!-- NAVBAR -->

<nav class="navbar navbar-dark bg-dark">

    <div class="container">


        <a class="navbar-brand"
           href="${pageContext.request.contextPath}/home">

            Poly Video

        </a>


        <div>


            <c:if test="${not empty sessionScope.user}">

                <span class="text-white me-3 ">

                        ${sessionScope.user.fullname}

                </span>


                <a class="btn btn-danger"
                   href="${pageContext.request.contextPath}/logout">

                    Logout

                </a>


            </c:if>


        </div>


    </div>


</nav>


<div class="container mt-4">


    <div class="row">


        <!-- VIDEO INFO -->

        <div class="col-md-8">


            <div class="card shadow">


                <c:choose>


                    <c:when test="${not empty video.poster}">


                        <img src="${pageContext.request.contextPath}/images/${video.poster}"
                             class="card-img-top"
                             style="height:400px;object-fit:cover">


                    </c:when>


                    <c:otherwise>


                        <img src="https://via.placeholder.com/800x400"
                             class="card-img-top">


                    </c:otherwise>


                </c:choose>


                <div class="card-body">


                    <h2>

                        ${video.title}

                    </h2>


                    <p>

                        <strong>
                            Lượt xem:
                        </strong>

                        ${video.views}

                    </p>


                    <p>

                        ${video.description}

                    </p>


                    <hr>


                    <!-- ACTION -->

                    <div>


                        <a href="${pageContext.request.contextPath}/favorite?id=${video.id}"
                           class="btn btn-danger">

                            Favorite

                        </a>


                        <form action="${pageContext.request.contextPath}/share"
                              method="post"
                              class="mt-3">

                            <input type="hidden"
                                   name="videoId"
                                   value="${video.id}">

                            <input type="hidden"
                                   name="from"
                                   value="detail">

                            <div class="mb-2">

                                <label class="form-label">
                                    Email người nhận
                                </label>

                                <input type="text"
                                       name="email"
                                       class="form-control"
                                       placeholder="email1@gmail.com, email2@gmail.com"
                                       required>

                                <small class="text-muted">
                                    Nhập nhiều email, ngăn cách bằng dấu phẩy (,)
                                    hoặc dấu chấm phẩy (;)
                                </small>

                            </div>

                            <button type="submit"
                                    class="btn btn-primary">

                                Share

                            </button>

                        </form>


                    </div>


                </div>


            </div>


        </div>


        <!-- RELATED VIDEOS -->


        <div class="col-md-4">


            <h4>

                Video liên quan

            </h4>


            <c:forEach var="item"
                       items="${relatedVideos}">


                <div class="card mb-3">


                    <div class="row g-0">


                        <div class="col-5">


                            <img src="${pageContext.request.contextPath}/images/${item.poster}"
                                 class="img-fluid rounded-start"
                                 style="height:90px;width:100%;object-fit:cover">


                        </div>


                        <div class="col-7">


                            <div class="card-body p-2">


                                <a href="${pageContext.request.contextPath}/video-detail?id=${item.id}"
                                   class="text-decoration-none">


                                        ${item.title}


                                </a>


                                <br>


                                <small>

                                        ${item.views} views

                                </small>


                            </div>


                        </div>


                    </div>


                </div>


            </c:forEach>


        </div>


    </div>


</div>


</body>

</html>