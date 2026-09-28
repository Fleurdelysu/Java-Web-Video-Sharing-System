<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<%@taglib prefix="fmt" uri="jakarta.tags.fmt"%>

<!DOCTYPE html>
<html>
<head>
    <title>My Favorites</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
          rel="stylesheet">

</head>

<body>


<!-- NAVBAR -->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">

    <div class="container">

        <a class="navbar-brand" href="${pageContext.request.contextPath}/home">
            Poly Video
        </a>


        <div class="d-flex">

            <span class="text-white me-3 mt-1">
                Xin chào: ${sessionScope.user.fullname}
            </span>

            <a class="btn btn-outline-light me-2"
               href="${pageContext.request.contextPath}/home">
                Home
            </a>

            <a class="btn btn-info me-2"
               href="${pageContext.request.contextPath}/profile">
                Profile
            </a>

            <a class="btn btn-danger"
               href="${pageContext.request.contextPath}/logout">
                Logout
            </a>

            <c:if test="${sessionScope.user.admin}">

                <a class="btn btn-warning ms-2"
                   href="${pageContext.request.contextPath}/admin/users">
                    Admin Panel
                </a>

            </c:if>

        </div>

    </div>

</nav>



<div class="container mt-4">

    <h2 class="mb-4">
        My Favorites
    </h2>


    <c:choose>

        <c:when test="${not empty favorites}">

            <div class="row">

                <c:forEach var="favorite" items="${favorites}">

                    <div class="col-md-4 mb-4">

                        <div class="card shadow h-100">

                            <a href="${pageContext.request.contextPath}/video-detail?id=${favorite.video.id}">

                                <c:choose>

                                    <c:when test="${not empty favorite.video.poster}">

                                        <img src="${pageContext.request.contextPath}/images/${favorite.video.poster}"
                                             class="card-img-top"
                                             alt="${favorite.video.title}"
                                             style="height:220px;object-fit:cover">

                                    </c:when>

                                    <c:otherwise>

                                        <img src="https://via.placeholder.com/400x220"
                                             class="card-img-top"
                                             alt="${favorite.video.title}">

                                    </c:otherwise>

                                </c:choose>

                            </a>


                            <div class="card-body d-flex flex-column">

                                <h5 class="card-title">

                                    <a href="${pageContext.request.contextPath}/video-detail?id=${favorite.video.id}"
                                       class="text-decoration-none text-dark">
                                        ${favorite.video.title}
                                    </a>

                                </h5>


                                <p class="card-text text-muted">
                                    Lượt xem: ${favorite.video.views}
                                </p>


                                <p class="card-text">

                                    <small class="text-muted">

                                        Đã thích:
                                        <fmt:formatDate value="${favorite.likeDate}"
                                                        pattern="dd/MM/yyyy HH:mm"/>

                                    </small>

                                </p>


                                <div class="mt-auto">

                                    <a href="${pageContext.request.contextPath}/favorite?id=${favorite.video.id}&from=favorites"
                                       class="btn btn-outline-danger btn-sm">
                                        Unlike
                                    </a>


                                    <form action="${pageContext.request.contextPath}/share"
                                          method="post"
                                          class="mt-2">

                                        <input type="hidden" name="videoId" value="${favorite.video.id}">
                                        <input type="hidden" name="from" value="favorites">

                                        <div class="input-group input-group-sm">

                                            <input type="email"
                                                   name="email"
                                                   class="form-control"
                                                   placeholder="Email người nhận"
                                                   required>

                                            <button type="submit"
                                                    class="btn btn-primary">
                                                Share
                                            </button>

                                        </div>

                                    </form>

                                </div>

                            </div>

                        </div>

                    </div>

                </c:forEach>

            </div>

        </c:when>


        <c:otherwise>

            <div class="alert alert-info">
                Bạn chưa thích video nào.
                <a href="${pageContext.request.contextPath}/home">
                    Khám phá video ngay
                </a>
            </div>

        </c:otherwise>

    </c:choose>

</div>


</body>
</html>
