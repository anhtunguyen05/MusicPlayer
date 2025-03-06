package dbcontext;

import java.util.List;
import model.User;
import model.History;
import model.Song;
import model.Like;
import model.Playlist;
import DAO.SongDAO;
import DAO.HistoryDAO;
import DAO.LikeDAO;
import DAO.PlaylistDAO;

        
import DAO.UserDAO;
import java.util.Date;

/**
 *
 * @author Ly Quynh Tran
 */
public class TestDB {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        UserDAO userDAO = new UserDAO();
        SongDAO songDAO = new SongDAO();
        List<User> l = userDAO.getUsersByName("j");
        System.out.println(l);
        
        songDAO.addSong(new Song("hehe", "haha", "hh",
                 "df", "dsf", 1, new java.sql.Date(new Date().getTime())));
 //       userDAO.addUser(new User( "Ha Dong", "hadong@gmail.com", "hadongngu", "hehe.img", true, "khongbiet", true));
//        List<Song> s = songDAO.getSongs();
//        songDAO.deleteSong("1");
//        userDAO.deleteUser("6");
//        System.out.println(l);
    }
}
