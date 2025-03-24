<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Admin Panel</title>

        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <!-- Custom CSS -->
        <style>
            body {
                background-color: #f8f9fa;
            }
            .container {
                margin-top: 30px;
            }
            .table th, .table td {
                text-align: center;
            }
            .vip-badge {
                padding: 5px 10px;
                border-radius: 15px;
                font-weight: bold;
            }
            .vip-yes {
                background-color: #28a745;
                color: white;
            }
            .vip-no {
                background-color: #dc3545;
                color: white;
            }
        </style>
    </head>
    <body>

        <%@include file="/include/header.jsp"%>

        <div class="container">
            <h2 class="text-center mb-4">List Of Users</h2>

            <div class="table-responsive">
                <table class="table table-bordered table-striped table-hover">
                    <thead class="table-dark">
                        <tr>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Email</th>
                            <th>VIP Status</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:choose>
                            <c:when test="${not empty userList}">
                                <c:forEach var="u" items="${userList}">
                                    <tr>
                                        <td>${u.userId}</td>
                                        <td>${u.username}</td>
                                        <td>${u.email}</td>
                                        <td>
                                            <span class="vip-badge ${u.vip ? 'vip-yes' : 'vip-no'}">
                                                ${u.vip ? 'VIP' : 'Regular'}
                                            </span>
                                        </td>
                                        <td>

                                            <a href="javascript:void(0)" onclick="confirmDelete('${u.userId}')" class="btn btn-danger">
                                                Delete
                                            </a>



                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <tr>
                                    <td colspan="5" class="text-center">No Users Available</td>
                                </tr>
                            </c:otherwise>
                        </c:choose>
                    </tbody>
                </table>
            </div>
        </div>
        <script>
            function confirmDelete(userId) {
                let confirmAction = confirm("Bạn có chắc chắn muốn xóa người dùng này không?");
                if (confirmAction) {
                    window.location.href = "RemoveUserServlet?id=" + userId;
                }
            }
        </script>


    </body>
</html>
