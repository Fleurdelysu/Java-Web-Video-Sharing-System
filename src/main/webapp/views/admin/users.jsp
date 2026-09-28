<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html>

<head>

    <title>Admin Users</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
          rel="stylesheet">

</head>


<body>


<div class="container mt-4">


    <h2>
        Quản lý User
    </h2>

    <a href="${pageContext.request.contextPath}/admin/users?action=add"
       class="btn btn-primary mb-3">
        + Add User
    </a>

    <a class="btn btn-secondary mb-3"
       href="${pageContext.request.contextPath}/home">

        ← Quay lại Home

    </a>


    <table class="table table-bordered">


        <thead>

        <tr>

            <th>ID</th>
            <th>Fullname</th>
            <th>Email</th>
            <th>Admin</th>
            <th>Action</th>

        </tr>

        </thead>

        <tbody>

        <c:forEach items="${users}" var="u">


            <tr>

                <td>${u.id}</td>

                <td>${u.fullname}</td>

                <td>${u.email}</td>

                <td>

                    <c:choose>

                        <c:when test="${u.admin}">
                            Admin
                        </c:when>

                        <c:otherwise>
                            User
                        </c:otherwise>

                    </c:choose>

                </td>


                <td>

                    <a class="btn btn-warning btn-sm"
                       href="${pageContext.request.contextPath}/admin/users?action=edit&id=${u.id}">

                        Edit

                    </a>

                    <a class="btn btn-danger btn-sm"
                       href="${pageContext.request.contextPath}/admin/users?action=delete&id=${u.id}"
                       onclick="return confirm('Bạn có chắc chắn muốn xóa user này?')">

                        Delete

                    </a>

                </td>


            </tr>


        </c:forEach>


        </tbody>

    </table>

    <c:if test="${totalPages > 1}">

        <nav class="mt-3">

            <ul class="pagination">

                <!-- First -->

                <li class="page-item ${currentPage == 1 ? 'disabled' : ''}">

                    <a class="page-link"
                       href="${pageContext.request.contextPath}/admin/users?page=1">

                        First

                    </a>

                </li>


                <!-- Previous -->

                <li class="page-item ${currentPage == 1 ? 'disabled' : ''}">

                    <a class="page-link"
                       href="${pageContext.request.contextPath}/admin/users?page=${currentPage - 1}">

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
                           href="${pageContext.request.contextPath}/admin/users?page=${i}">

                                ${i}

                        </a>

                    </li>

                </c:forEach>


                <!-- Next -->

                <li class="page-item ${currentPage == totalPages ? 'disabled' : ''}">

                    <a class="page-link"
                       href="${pageContext.request.contextPath}/admin/users?page=${currentPage + 1}">

                        Next

                    </a>

                </li>


                <!-- Last -->

                <li class="page-item ${currentPage == totalPages ? 'disabled' : ''}">

                    <a class="page-link"
                       href="${pageContext.request.contextPath}/admin/users?page=${totalPages}">

                        Last

                    </a>

                </li>

            </ul>

        </nav>

    </c:if>


</div>


</body>

</html>