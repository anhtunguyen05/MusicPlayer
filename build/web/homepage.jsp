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
        <%@include file="/include/header.jsp"%>
        <div class="container mt-4">
            <div class="row">
                <div class="col-md-8">
                    <h2 class="text-center text-primary">Danh sách Playlist</h2>
                    <%@include file="/include/view_playlist.jsp"%>
                </div>
                <div class="col-md-4" data-fetch-page="GetTrendSongServlet">
                   <h4>Hot Trend</h4> 
                   <%@include file="/include/playlist.jsp"%> 
                </div>
            </div>
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
         <script src="./js/playlist.js?v=1.0"></script> 
    </body>
</html>
