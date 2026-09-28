<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>

<head>

    <meta charset="UTF-8">

    <title>Admin - Video Management</title>

    <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
            rel="stylesheet">

</head>

<body>

<div class="container mt-4">

    <h2>Video Management</h2>

    <hr>
    <!-- FORM -->
    <div class="card shadow-sm mb-4 container mt-4">
        <h2 class="mt-4">Update video form</h2>
        <form class="mb-4" method="post"

              action="${pageContext.request.contextPath}/admin/videos">

            <div class="mb-3">

                <label>ID</label>

                <input type="text"
                       name="id"
                       class="form-control"
                       value="${video.id}"
                       required>

            </div>


            <div class="mb-3">

                <label>Title</label>

                <input type="text"
                       name="title"
                       class="form-control"
                       value="${video.title}"
                       required>

            </div>


            <div class="mb-3">

                <label>Poster</label>

                <input type="text"
                       name="poster"
                       class="form-control"
                       value="${video.poster}">

            </div>


            <div class="mb-3">

                <label>Views</label>

                <input type="number"
                       name="views"
                       class="form-control"
                       value="${video.views != null ? video.views : 0}">

            </div>


            <div class="mb-3">

                <label>Description</label>

                <textarea name="description"
                          class="form-control"
                          rows="4">${video.description}</textarea>

            </div>


            <div class="form-check mb-3">

                <input type="checkbox"
                       name="active"
                       class="form-check-input"
                       id="active"
                ${video.active ? 'checked' : ''}>

                <label class="form-check-label"
                       for="active">

                    Active

                </label>

            </div>


            <button type="submit"
                    name="action"
                    value="update"
                    class="btn btn-primary">

                Update

            </button>


            <a href="${pageContext.request.contextPath}/admin/videos"
               class="btn btn-secondary">

                Reset

            </a>

        </form>
    </div>
    <div class="card shadow-sm mb-4">

        <div class="card-body">

            <h4 class="mb-3">
                ${empty video ? 'Thêm Video' : 'Cập nhật Video'}
            </h4>

            <form action="${pageContext.request.contextPath}/admin/videos"
                  method="post">

                <input type="hidden"
                       name="action"
                       value="${empty video ? 'create' : 'update'}">

                <div class="mb-3">

                    <label class="form-label">
                        ID
                    </label>

                    <input type="text"
                           name="id"
                           class="form-control"
                           value="${video.id}"
                    ${not empty video ? 'readonly' : ''}
                           required>

                </div>


                <div class="mb-3">

                    <label class="form-label">
                        Title
                    </label>

                    <input type="text"
                           name="title"
                           class="form-control"
                           value="${video.title}"
                           required>

                </div>


                <div class="mb-3">

                    <label class="form-label">
                        Poster
                    </label>

                    <input type="text"
                           name="poster"
                           class="form-control"
                           value="${video.poster}">

                </div>


                <div class="mb-3">

                    <label class="form-label">
                        Views
                    </label>

                    <input type="number"
                           name="views"
                           class="form-control"
                           value="${empty video.views ? 0 : video.views}"
                           min="0">

                </div>


                <div class="mb-3">

                    <label class="form-label">
                        Description
                    </label>

                    <textarea name="description"
                              class="form-control"
                              rows="4">${video.description}</textarea>

                </div>


                <div class="form-check mb-3">

                    <input type="checkbox"
                           name="active"
                           class="form-check-input"
                           value="true"
                    ${video.active ? 'checked' : ''}>

                    <label class="form-check-label">
                        Active
                    </label>

                </div>


                <button type="submit"
                        class="btn btn-primary">

                    ${empty video ? 'Create' : 'Update'}

                </button>


                <!-- VIDEO LIST -->
                <c:if test="${not empty video}">

                    <a href="${pageContext.request.contextPath}/admin/videos"
                       class="btn btn-secondary">

                        Cancel

                    </a>

                </c:if>


            </form>

        </div>


    </div>


    <h3>Video List</h3>


    <table class="table table-bordered">

        <thead>

        <tr>

            <th>ID</th>
            <th>Title</th>
            <th>Poster</th>
            <th>Views</th>
            <th>Active</th>
            <th>Action</th>

        </tr>

        </thead>


        <tbody>

        <c:forEach
                var="item"
                items="${videos}">

            <tr>

                <td>
                        ${item.id}
                </td>

                <td>
                        ${item.title}
                </td>

                <td>
                        ${item.poster}
                </td>

                <td>
                        ${item.views}
                </td>

                <td>

                    <c:choose>

                        <c:when test="${item.active}">
                            Active
                        </c:when>

                        <c:otherwise>
                            Inactive
                        </c:otherwise>

                    </c:choose>

                </td>

                <td>

                    <a href="${pageContext.request.contextPath}/admin/videos?id=${item.id}"
                       class="btn btn-warning btn-sm">

                        Edit

                    </a>


                    <form method="post"
                          action="${pageContext.request.contextPath}/admin/videos"
                          style="display:inline">

                        <input type="hidden"
                               name="id"
                               value="${item.id}">

                        <button type="submit"
                                name="action"
                                value="delete"
                                class="btn btn-danger btn-sm"
                                onclick="return confirm('Bạn có chắc chắn muốn xóa video này?')">

                            Delete

                        </button>

                    </form>

                </td>

            </tr>

        </c:forEach>

        </tbody>

    </table>

    <a style="margin-left: 88.5%" class="btn btn-secondary mb-3"
       href="${pageContext.request.contextPath}/home">

        ← Quay lại Home

    </a>

    <c:if test="${totalPages > 1}">

        <nav class="mt-3">

            <ul class="pagination">

                <!-- First -->

                <li class="page-item ${currentPage == 1 ? 'disabled' : ''}">

                    <a class="page-link"
                       href="${pageContext.request.contextPath}/admin/videos?page=1">

                        First

                    </a>

                </li>


                <!-- Previous -->

                <li class="page-item ${currentPage == 1 ? 'disabled' : ''}">

                    <a class="page-link"
                       href="${pageContext.request.contextPath}/admin/videos?page=${currentPage - 1}">

                        Previous

                    </a>

                </li>


                <!-- Page numbers -->

                <c:forEach
                        var="i"
                        begin="1"
                        end="${totalPages}">

                    <li class="page-item ${i == currentPage ? 'active' : ''}">

                        <a class="page-link"
                           href="${pageContext.request.contextPath}/admin/videos?page=${i}">

                                ${i}

                        </a>

                    </li>

                </c:forEach>


                <!-- Next -->

                <li class="page-item ${currentPage == totalPages ? 'disabled' : ''}">

                    <a class="page-link"
                       href="${pageContext.request.contextPath}/admin/videos?page=${currentPage + 1}">

                        Next

                    </a>

                </li>


                <!-- Last -->

                <li class="page-item ${currentPage == totalPages ? 'disabled' : ''}">

                    <a class="page-link"
                       href="${pageContext.request.contextPath}/admin/videos?page=${totalPages}">

                        Last

                    </a>

                </li>

            </ul>

        </nav>

    </c:if>

</div>

</body>

</html>