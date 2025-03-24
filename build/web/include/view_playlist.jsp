<%-- 
    Document   : view_playlist
    Created on : Mar 20, 2025, 1:51:27 AM
    Author     : pc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <link rel="stylesheet" href="./assets/css/homepage.css" />
        <link
        rel="stylesheet"
        href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.2/css/all.min.css"
        integrity="sha512-HK5fgLBL+xu6dm/Ii3z4xhlSUyZgTT9tuc/hSrtw6uzJOvgRr2a9jyxxT1ely+B+xFAmJKVSTbpM/CuL7qxO8w=="
        crossorigin="anonymous"
      />
      <link
        rel="stylesheet"
        href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.1/css/all.min.css"
      />
      <link rel="preconnect" href="https://fonts.googleapis.com" />
      <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
        <title>JSP Page</title>
    </head>
    <body>
        <div class="row">
            <c:forEach var="playlistItem" items="${playlistList}">
                <div class="col-md-4 mb-4">
                    <div class="playlist-item shadow-sm">
                        <a href="PlaylistServlet?playlist_id=${playlistItem.playlistId}">
                            <img src="${playlistItem.playlistImg != null && !playlistItem.playlistImg.isEmpty() ? playlistItem.playlistImg : './playlist_img/anhst1.jpg'}" class="card-img-top playlist-img" alt="...">
                        </a>
                        <div class="play-button">
                            <i class="fa fa-play"></i>
                        </div>
                        <h5 class="title-overplay">${playlistItem.playlistName}</h5>
                    </div>
                    <div class="mb-4 ">
                        <div class="card-body playlist_content">
                            <h5 class="card-title">${playlistItem.playlistName}</h5>
                            <p><strong>Số bài hát:</strong>${playlistItem.songNum}</p>
                        </div>
                    </div>
                </div>

            </c:forEach>
        </div>
        
    </body>
</html>
