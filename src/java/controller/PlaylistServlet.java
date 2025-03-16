/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAO.PlaylistDAO;
import DAO.Playlist_SongDAO;
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
import java.util.Date;
import model.Playlist;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author pc
 */
public class PlaylistServlet extends HttpServlet {

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
            out.println("<title>Servlet PlaylistServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PlaylistServlet at " + request.getContextPath() + "</h1>");
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
       String playlistId = request.getParameter("playlist_id");
       PlaylistDAO p = new PlaylistDAO();
       Playlist pl = p.getPlaylistById(playlistId);
       request.setAttribute("playlistName", pl.getPlaylistName());
       request.setAttribute("playlist_id", pl.getPlaylistId());
       request.setAttribute("p.user_id", pl.getUserId());
       request.getRequestDispatcher("playlist_detail.jsp").forward(request, response);
       //response.sendRedirect("playlist_detail.jsp?playlist_id=" + playlistId);
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
        String action = request.getParameter("action");

        PlaylistDAO p = new PlaylistDAO();
        Playlist_SongDAO playSong = new Playlist_SongDAO();
        switch (action) {
            case "add":
                int songId = Integer.parseInt(request.getParameter("songId"));
                int playlistId = Integer.parseInt(request.getParameter("playlistId"));

                playSong.addSongToPlaylist(playlistId, songId);
                break;
            case "create":
                Integer userId = (Integer) request.getSession().getAttribute("user_id");

                String playlistName = request.getParameter("playlistName");
                Date date = new java.sql.Date(new Date().getTime());

                p.addPlaylist(new Playlist(playlistName, null, userId, date));
                break;
            case "remove":
                int songIds = Integer.parseInt(request.getParameter("songId"));
                int playlistIds = Integer.parseInt(request.getParameter("playlistId"));
                playSong.removeSongFromPlaylist(playlistIds, songIds);
                response.sendRedirect("playlist_detail.jsp?playlist_id=" + playlistIds);
                break;
            case "delete":
                p.deletePlaylist(request.getParameter("playlist_id"));
                response.sendRedirect("HomepageServlet");
                break;
            case "update":
                String name = request.getParameter("new_name");
                Playlist pl = p.getPlaylistById(request.getParameter("playlist_id"));
                pl.setPlaylistName(name);
                p.updatePlaylist(pl);
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
