<%-- 
    Document   : login
    Created on : Feb 15, 2025, 12:09:48 AM
    Author     : pc
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <link rel="stylesheet" href="./assets/css/validator.css" />
        <title>Sign in</title>
    </head>
   <body class="bg-light">
        <div class="container mt-5">
            <div class="row justify-content-center">
                <div class="col-md-4">
                    <h3 class="text-center">Đăng nhập</h3>
                    <c:set var="cookie" value="${pageContext.request.cookies}"/>
                    <form action="LoginServlet" method="post" id="form-1">
                        <div class="form-group mb-3">
                            <label for="fullname" class="form-label"><h6>Nhập tên tài khoản:</h6></label>
                          <input
                            id="username"
                            name="username"
                            rules="required"
                            type="text"
                            placeholder="VD: Anhtu1210"
                            class="form-control"
                            value="${cookie.cuser.value}"
                          />
                          <span class="form-message"></span>
                        </div>
                        <div class="form-group mb-3">
                            <label for="password" class="form-label"><h6>Mật khẩu</h6></label>
                          <input
                            id="password"
                            name="password"
                            rules="required|min:6"
                            type="password"
                            placeholder="Nhập mật khẩu"
                            class="form-control"
                            value="${cookie.cpass.value}"
                          />
                          <span class="form-message"></span>
                        </div>
                         <div class="form-group mb-3">
                          <input
                            id="remember"
                            name="remember"
                            type="checkbox"
                            ${(cookie.crem!=null?'checked':'')}
                          />Remember me
                        </div>
                        <%
                            String error = request.getParameter("error");
                            if(error=="not_verify"){
                        %>
                        <h5 class="form-message text-danger">Email is not verify, please check your email!</h5>
                        <%
                            }else{
                        %>
                         <h5 class="form-message text-danger">Username or password incorrect, please try again!</h5>
                        <%
                            }
                        %>
                        <button type="submit" class="btn btn-primary w-100">Đăng nhập</button>
                    </form>
                    <p class="text-center mt-3">Chưa có tài khoản? <a href="register.jsp">Đăng ký</a></p>
                </div>
            </div>
        </div>
       <script src="./js/validator.js"></script>
       <script>
      var form = new Validator("#form-1");
       </script>
    </body>
    
</html>
