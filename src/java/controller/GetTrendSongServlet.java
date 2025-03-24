/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dbcontext.ConnectDB;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author pc
 */
public class GetTrendSongServlet extends HttpServlet {

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
            out.println("<title>Servlet GetTrendSongServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet GetTrendSongServlet at " + request.getContextPath() + "</h1>");
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
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        JSONObject errorResponse = new JSONObject();

        JSONArray songList = new JSONArray();

// Lấy user_id từ session (nếu có)
        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("user_id");

        try (Connection conn = ConnectDB.getInstance().openConnection()) {
            // Nếu có user_id thì thêm truy vấn kiểm tra tym
            String query = """
        SELECT TOP 10 s.song_id, s.song_name, s.artist, s.file_url, s.song_img, s.view_count 
        """ + (userId != null ? ", CASE WHEN l.user_id IS NOT NULL THEN 1 ELSE 0 END AS liked " : "") + """
        FROM Songs s
        """ + (userId != null ? "LEFT JOIN Likes l ON s.song_id = l.song_id AND l.user_id = ? " : "") + """
        ORDER BY s.view_count DESC;
    """;

            PreparedStatement ps = conn.prepareStatement(query);

            // Nếu có user_id, set giá trị vào truy vấn
            if (userId != null) {
                ps.setInt(1, Integer.parseInt(userId));
            }

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                JSONObject song = new JSONObject();
                song.put("song_id", rs.getInt("song_id"));
                song.put("song_name", rs.getString("song_name"));
                song.put("artist", rs.getString("artist"));
                song.put("file_url", rs.getString("file_url"));
                song.put("song_img", rs.getString("song_img") != null ? rs.getString("song_img")
                        : "https://i.ytimg.com/vi/jTLhQf5KJSc/maxresdefault.jpg");

                // Nếu có user_id, thêm thông tin tym
                if (userId != null) {
                    song.put("liked", rs.getInt("liked") == 1);
                }

                songList.add(song);
            }

            response.getWriter().write(songList.toJSONString());

        } catch (Exception e) {
            e.printStackTrace();
            errorResponse.put("error", "Internal server error");
            response.getWriter().write(errorResponse.toJSONString());
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
        processRequest(request, response);
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
