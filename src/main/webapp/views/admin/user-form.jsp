<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

<head>

    <meta charset="UTF-8">

    <title>User Form</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
          rel="stylesheet">

</head>

<body>

<div class="container mt-4">

    <h2>
        <c:choose>

            <c:when test="${mode == 'add'}">
                Thêm User
            </c:when>

            <c:otherwise>
                Cập nhật User
            </c:otherwise>

        </c:choose>
    </h2>


    <form method="post"
          action="${pageContext.request.contextPath}/admin/users">


        <!-- ID -->

        <div class="mb-3">

            <label class="form-label">
                ID
            </label>

            <input class="form-control"
                   name="id"
                   value="${user.id}"
                   <c:if test="${mode == 'edit'}">readonly</c:if>
                   required>

        </div>


        <!-- PASSWORD -->

        <div class="mb-3">

            <label class="form-label">
                Password
            </label>

            <input type="password"
                   class="form-control"
                   name="password"
                   value="${user.password}"
                   required>

        </div>


        <!-- FULLNAME -->

        <div class="mb-3">

            <label class="form-label">
                Fullname
            </label>

            <input class="form-control"
                   name="fullname"
                   value="${user.fullname}"
                   required>

        </div>


        <!-- EMAIL -->

        <div class="mb-3">

            <label class="form-label">
                Email
            </label>

            <input type="email"
                   class="form-control"
                   name="email"
                   value="${user.email}"
                   required>

        </div>


        <!-- ADMIN -->

        <div class="form-check mb-3">

            <input class="form-check-input"
                   type="checkbox"
                   name="admin"
                   value="true"
            <c:if test="${mode == 'edit' && user.admin}">
                   checked
            </c:if>>

            <label class="form-check-label">
                Admin
            </label>

        </div>


        <!-- BUTTON -->

        <button type="submit"
                class="btn btn-primary">

            <c:choose>

                <c:when test="${mode == 'add'}">
                    Create
                </c:when>

                <c:otherwise>
                    Update
                </c:otherwise>

            </c:choose>

        </button>


        <a href="${pageContext.request.contextPath}/admin/users"
           class="btn btn-secondary">

            Cancel

        </a>


    </form>

</div>

</body>

</html>