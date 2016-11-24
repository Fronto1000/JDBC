
import dao.UsersDao;
import factory.DaoFactory;
import factory.ServiceFactory;
import model.User;
import service.UserService;
import java.util.ArrayList;

public class Program {
    public static void main(String[] args){
        UserService userService = ServiceFactory.getInstance().getUserService();
        UsersDao usersDao = DaoFactory.getInstance().getUsersDao();
        //usersDao.delete(5);
        usersDao.update(new User(6,"Yura",26,"Moscow"));
        ArrayList<User> autos =  userService.getUsersByCity("Kazan");
        for (User car:autos) {
            System.out.println(car);
        }
        boolean isRegistered = userService.isRegistered("Alex");
        System.out.println(isRegistered);
    }
}