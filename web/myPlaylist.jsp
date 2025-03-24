<%-- 
    Document   : myPlaylist
    Created on : Mar 10, 2025, 11:34:06 PM
    Author     : pc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <title>JSP Page</title>
    </head>
    <body>
        <%@include file="/include/header.jsp"%>
        <div class="container mt-5">
            <%@include file="/include/nav_libary.jsp"%>
            <%@include file="/include/view_playlist.jsp"%>
        </div>
        <script src="./js/playlist.js?v=1.0"></script> 
    </body>
</html>
