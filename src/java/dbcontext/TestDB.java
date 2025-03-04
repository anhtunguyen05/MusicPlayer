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
//         studentDAO.addStudent(new Student("Tran", "Ly", "tran@y.c"));
        // studentDAO.updateStudent(new Student(9,"Tran", "JJJJ", "tran@y.c"));
        // Student s = studentDAO.getStudent("9");
        // System.out.println("" + s);
        //studentDAO.deleteStudent("1");
        List<User> l = userDAO.getUsersByName("j");
        System.out.println(l);
        
                        
//        userDAO.addUser(new User( "Ha Dong", "hadong@gmail.com", "hadongngu", "hehe.img", true, "khongbiet", true));
//        List<Song> s = songDAO.getSongs();
//        songDAO.deleteSong("1");
//        userDAO.deleteUser("6");
//        System.out.println(l);
    }
}
