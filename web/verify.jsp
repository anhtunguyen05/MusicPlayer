<%-- 
    Document   : verfidy
    Created on : Mar 4, 2025, 10:28:56 PM
    Author     : pc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <link rel="stylesheet" href="./assets/css/validator.css" />
    </head>
    <body>

       
        <div class="container mt-5">
            <div class="row justify-content-center">
                <div class="col-md-4">
                    <h3 class="text-center">Xác thực tài khoản</h3>
                    
                    <form action="VerifyServlet" method="post" id="form-1">
                        <div class="form-group mb-3">
                            <label for="fullname" class="form-label"><h6>Nhập tên tài khoản:</h6></label>
                          <input
                            id="username"
                            name="username"
                            rules="required"
                            type="text"
                            placeholder="VD: Anhtu1210"
                            class="form-control"
                            value="${username}"
                          />
                          <span class="form-message"></span>
                        </div>
                        <div class="form-group mb-3">
                            <label for="password" class="form-label"><h6>OTP:</h6></label>
                          <input
                            id="code"
                            name="code"
                            rules="required|min:6"
                            type="text"
                            placeholder="Nhập mã OTP"
                            class="form-control"
                            
                          />
                          <span class="form-message"></span>
                        </div>
                         
                        <%
                            String error = request.getParameter("error");
                            if(error!=null){
                        %>
                        <h5 class="form-message text-danger">OTP is incorrect, please check your email!</h5>
                        <%
                            }
                        %>
                        <button type="submit" class="btn btn-primary w-100">Xác nhận</button>
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
