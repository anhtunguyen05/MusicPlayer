<%-- 
    Document   : responseSearch
    Created on : Mar 17, 2025, 1:44:32 AM
    Author     : pc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
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
    <body data-fetch-page="GetSearchServlet?search=${search}">
        <%@include file="/include/header.jsp"%>
    
    <div class="container mt-5">
        
       
            
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
