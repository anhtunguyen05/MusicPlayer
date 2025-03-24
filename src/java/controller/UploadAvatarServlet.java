/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import DAO.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.nio.file.Paths;

/**
 *
 * @author pc
 */
public class UploadAvatarServlet extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UploadAvatarServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UploadAvatarServlet at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String userID = (String) request.getSession().getAttribute("user_id");
        if (userID == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        UserDAO userDao = new UserDAO();

        // Kiểm tra xem có nút "delete" được nhấn không
        String action = request.getParameter("action");
        if ("delete".equals(action)) {
            // Lấy đường dẫn avatar hiện tại
            String avatarUrl = userDao.getUserById(userID).getAvatarUrl();
            if (avatarUrl != null && !avatarUrl.equals("song_img/avatar.jpg")) {
                String filePath = getServletContext().getRealPath("") + File.separator + avatarUrl;
                File file = new File(filePath);
                if (file.exists()) {
                    file.delete(); // Xóa ảnh từ thư mục
                }
            }

            // Cập nhật lại avatar về ảnh mặc định
            userDao.updateAvatar(userID, "song_img/avatar.jpg");

            response.sendRedirect("profile.jsp");
            return;
        }

        // Xử lý upload ảnh mới
        Part filePart = request.getPart("avatar");
        if (filePart != null && filePart.getSize() > 0) {
            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            String uploadPath = getServletContext().getRealPath("") + File.separator + "uploads";

            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String filePath = uploadPath + File.separator + fileName;
            filePart.write(filePath);

            // Cập nhật avatar trong database
            String avatarUrl = "uploads/" + fileName;
            userDao.updateAvatar(userID, avatarUrl);
        }

        response.sendRedirect("profile.jsp");
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
