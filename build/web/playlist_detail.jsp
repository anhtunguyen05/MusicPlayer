<%-- 
    Document   : songsplaylist
    Created on : Feb 28, 2025, 3:15:12 AM
    Author     : pc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Chi tiết Playlist</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="./assets/css/playlist_detail.css" />
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
        <link
            href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap"
            rel="stylesheet"
            />
    </head>
    <body data-fetch-page="GetPlaylistServlet">
        <%@include file="/include/header.jsp"%>
        
        <div class="container mt-5">
            <%
            String puserId = (String) request.getAttribute("p.user_id");

            %>
            <div class="playlist-header">
                <h2><%= request.getAttribute("playlistName") %></h2>
                <%
                    if (userId.equals(puserId)){

                %>

                <div class="playlist-actions">
                    <button class="edit-btn" onclick="openEditForm()">Chỉnh sửa</button>
                    <button class="delete-btn" onclick="deletePlaylist(<%= request.getParameter("playlist_id") %>)">Xóa</button>
                </div>
                <%
                   }

                %>

                <div class="playlist-header__img">
                    <img  src="<%= request.getAttribute("playlist_img") %>" alt="alt"/>
                    <%
                    if (userId.equals(puserId)){

                %>
                <form action="PlaylistServlet" method="post" enctype="multipart/form-data">
                    <input style="display: none;" type="number" class="form-control" name="playlist_id" value="<%= request.getParameter("playlist_id") %>">
                    <label for="cover" class="form-label">Chọn ảnh bìa</label>
                    <input type="file" class="form-control" id="cover" name="playlist_cover" accept="image/*">
                    <button type="submit" class="form-control" name="action" value="update">
                        Chọn ảnh mới
                    </button>
                </form>
                    <%
                   }

                %>
                </div>
            </div>

            <h4>Danh sách bài hát</h4>
            <%@include file="/include/playlist.jsp"%> 


        </div>
        <%@include file="/include/dashboard.jsp"%>

        <%
           if (session.getAttribute("user_id") != null){
                
            
        %>
        <%@include file="/include/updatePlaylist.jsp"%>
        <%@include file="/include/addPlaylist.jsp"%>
        <script src="./js/feature.js?v=1.0"></script> 
        <%
            }
        
        %>
        <script src="./js/playlist_detail.js?v=1.0"></script>

    </body>
</html>
