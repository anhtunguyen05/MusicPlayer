<%-- 
    Document   : homepage
    Created on : Feb 16, 2025, 10:53:36 PM
    Author     : pc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <title>JSP Page</title>
    </head>
    <body>
        <%@include file="/include/header.jsp"%>
        <div class="container mt-4">
            <h2 class="text-center text-primary">Danh sách Playlist</h2>
            <div class="row">
                <c:forEach var="playlistItem" items="${playlistList}">
                <div class="col-md-4">
                    <div class="card mb-4 shadow-sm">
                        <div class="card-body">
                            <h5 class="card-title">${playlistItem.playlistName}</h5>
                            <p><strong>Số bài hát:</strong>${playlistItem.songNum}</p>
                            <a href="PlaylistServlet?playlist_id=${playlistItem.playlistId}" class="btn btn-primary">Xem chi tiết</a>
                        </div>
                    </div>
                </div>
               </c:forEach>
            </div>
        </div>
    </body>
</html>
