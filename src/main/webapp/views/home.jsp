<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
    <title>Home</title>

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

            <c:choose>

                <c:when test="${not empty sessionScope.user}">

                    <span class="text-white me-3 mt-1">
                        Xin chào:
                        ${sessionScope.user.fullname}
                    </span>

                    <a class="btn btn-info me-2"
                       href="${pageContext.request.contextPath}/profile">
                        Profile
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


<!-- CONTENT -->

<div class="container mt-4">

    <h2 class="mb-4">
        Danh sách video
    </h2>


    <div class="row">

        <c:forEach var="video" items="${videos}">

            <div class="col-md-4 mb-4">

                <div class="card shadow h-100">

                    <a href="${pageContext.request.contextPath}/video-detail?id=${video.id}">
                        <c:choose>

                            <c:when test="${not empty video.poster}">

                                <img src="${pageContext.request.contextPath}/images/${video.poster}"
                                     class="card-img-top"
                                     alt="${video.title}"
                                     style="height:220px;object-fit:cover">

                            </c:when>

                            <c:otherwise>

                                <img src="https://via.placeholder.com/400x220"
                                     class="card-img-top"
                                     alt="${video.title}">

                            </c:otherwise>

                        </c:choose>
                    </a>


                    <div class="card-body d-flex flex-column">

                        <h5 class="card-title">
                                ${video.title}
                        </h5>

                        <p class="card-text text-muted">
                            Lượt xem: ${video.views}
                        </p>


                        <div class="mt-auto">

                            <c:choose>

                                <c:when test="${not empty sessionScope.user}">

                                    <c:choose>

                                        <c:when test="${favoriteVideoIds.contains(video.id)}">

                                            <a href="${pageContext.request.contextPath}/favorite?id=${video.id}&from=home&page=${currentPage}"
                                               class="btn btn-outline-danger btn-sm">
                                                Unlike
                                            </a>

                                        </c:when>

                                        <c:otherwise>

                                            <a href="${pageContext.request.contextPath}/favorite?id=${video.id}&from=home&page=${currentPage}"
                                               class="btn btn-danger btn-sm">
                                                Like
                                            </a>

                                        </c:otherwise>

                                    </c:choose>


                                    <form action="${pageContext.request.contextPath}/share"
                                          method="post"
                                          class="mt-2">

                                        <input type="hidden" name="videoId" value="${video.id}">
                                        <input type="hidden" name="from" value="home">
                                        <input type="hidden" name="page" value="${currentPage}">

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

                                </c:when>


                                <c:otherwise>

                                    <a href="${pageContext.request.contextPath}/login"
                                       class="btn btn-danger btn-sm">
                                        Like
                                    </a>

                                    <a href="${pageContext.request.contextPath}/login"
                                       class="btn btn-primary btn-sm">
                                        Share
                                    </a>

                                </c:otherwise>

                            </c:choose>

                        </div>

                    </div>

                </div>

            </div>

        </c:forEach>

    </div>


    <c:if test="${empty videos}">

        <div class="alert alert-info">
            Không có video nào.
        </div>

    </c:if>


    <!-- PAGINATION -->

    <nav class="mt-4" aria-label="Phân trang video">

        <ul class="pagination justify-content-center">

            <li class="page-item ${currentPage <= 1 ? 'disabled' : ''}">

                <a class="page-link"
                   href="${pageContext.request.contextPath}/home?page=1">
                    First
                </a>

            </li>


            <li class="page-item ${currentPage <= 1 ? 'disabled' : ''}">

                <a class="page-link"
                   href="${pageContext.request.contextPath}/home?page=${currentPage - 1}">
                    Previous
                </a>

            </li>


            <li class="page-item disabled">

                <span class="page-link">
                    Trang ${currentPage} / ${totalPages}
                </span>

            </li>


            <li class="page-item ${currentPage >= totalPages ? 'disabled' : ''}">

                <a class="page-link"
                   href="${pageContext.request.contextPath}/home?page=${currentPage + 1}">
                    Next
                </a>

            </li>


            <li class="page-item ${currentPage >= totalPages ? 'disabled' : ''}">

                <a class="page-link"
                   href="${pageContext.request.contextPath}/home?page=${totalPages}">
                    Last
                </a>

            </li>

        </ul>

    </nav>

</div>


</body>
</html>
