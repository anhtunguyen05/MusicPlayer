/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAO.SongDAO;
import DAO.UserDAO;
import dbcontext.ConnectDB;
import jakarta.servlet.http.Part;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpSession;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Song;
import model.User;

/**
 *
 * @author pc
 */
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 50, // 50MB
        maxRequestSize = 1024 * 1024 * 100 // 100MB
)
public class SongServlet extends HttpServlet {

    private static final String UPLOAD_DIR = "music";
    private static final String UPLOAD_DIR_COVER = "song_img";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
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
            out.println("<title>Servlet UploadServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UploadServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("user_id");
        // Kiểm tra quyền VIP của người dùng
        UserDAO userDao = new UserDAO();
        User user = userDao.getUserById(userId.toString());

        if (!user.isVip()) {
            System.out.println("⚠ Người dùng không có quyền VIP, chuyển hướng đến payment.jsp!");
            response.sendRedirect("payment.jsp");
            return;
        }else{
            response.sendRedirect("addSong.jsp");
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String command = request.getParameter("command");
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("user_id");
         SongDAO songDao = new SongDAO();
        if (userId == null) {
            System.out.println("⚠ Không tìm thấy user_id trong session!");
            response.sendRedirect("login.jsp"); // Chuyển hướng nếu chưa đăng nhập
            return;
        }
        
        switch (command) {
            case "remove":
                String songId = request.getParameter("songId");
                songDao.deleteSong(songId);
                break;
            case "add":
                // Nhận dữ liệu từ form
                String name = request.getParameter("song_name");
                String artist = request.getParameter("artist");
                String genre = request.getParameter("genre");

                // Lấy file nhạc
                Part filePart = (Part) request.getPart("file");

                String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

                // Lấy ảnh bìa (nếu có)
                Part coverPart = (Part) request.getPart("cover");

                String coverFileName = coverPart != null ? Paths.get(coverPart.getSubmittedFileName()).getFileName().toString() : null;

                // Lưu file lên server
                String appPath = getServletContext().getRealPath("");
                String appPathOther = appPath;
                String savePath = appPath.replace("build\\web", "web");
                String uploadPath = savePath + UPLOAD_DIR;
                String uploadPathImg = savePath + UPLOAD_DIR_COVER;

                if (coverPart != null && coverPart.getSize() > 0) {  // Kiểm tra file có dữ liệu không
                    coverFileName = Paths.get(coverPart.getSubmittedFileName()).getFileName().toString();

                    File uploadImgDir = new File(uploadPathImg);
                    if (!uploadImgDir.exists()) {
                        uploadImgDir.mkdir();
                    }

                    coverPart.write(uploadPathImg + File.separator + coverFileName);
                    Files.copy(Paths.get(uploadPathImg + File.separator + coverFileName),
                            Paths.get(appPathOther + UPLOAD_DIR_COVER + File.separator + coverFileName),
                            StandardCopyOption.REPLACE_EXISTING);
                } else {
                    coverFileName = null; // Không có ảnh bìa
                }

                String song_img = coverFileName != null ? "./" + UPLOAD_DIR_COVER + "/" + coverFileName : "";
                String file_url = "./" + UPLOAD_DIR + "/" + fileName;
                Date date = new java.sql.Date(new Date().getTime());

                Song song = new Song(name, artist, genre,
                        song_img, file_url, userId, date);
                songDao.addSong(song);

                response.sendRedirect("mysong.jsp");
                break;
            case "search":
                String search = request.getParameter("search");
                request.setAttribute("search", search);
                request.getRequestDispatcher("responseSearch.jsp").forward(request, response);
                break;
            default:
                throw new AssertionError();
        }
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
