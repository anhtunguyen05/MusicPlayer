<%-- 
    Document   : library
    Created on : Mar 17, 2025, 8:28:25 PM
    Author     : pc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
   <body data-fetch-page="${fetchPage}">
    <%@include file="/include/header.jsp"%>
    
    <div class="container mt-5">
        <%@include file="/include/nav_libary.jsp"%>
        <%@include file="/include/playlist.jsp"%> 
 
    </div>
    <%@include file="/include/dashboard.jsp"%>
        
    
      <%@include file="/include/updatePlaylist.jsp"%>
      <%@include file="/include/addPlaylist.jsp"%>
     
      <script src="./js/feature.js?v=1.0"></script> 
    
    <script src="./js/playlist_detail.js?v=1.0"></script>
       
</body>
</html>
