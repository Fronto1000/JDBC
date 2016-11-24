package service;

import dao.AutoDao;
import dao.UsersDao;
import model.Auto;
import model.User;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {

    private UsersDao usersDao;
    private AutoDao autoDao;


    public UserServiceImpl(UsersDao usersDao) {
        this.usersDao = usersDao;
    }

    public UserServiceImpl(UsersDao usersDao, AutoDao autoDao) {
        this.usersDao = usersDao;
        this.autoDao = autoDao;
    }

    public boolean isRegistered(String name) {
        List<User> users = usersDao.findAll();

        for (User currentUser : users) {
            if (currentUser.getName().equals(name)) {
                return true;
            }
        }

        return false;
    }

    public ArrayList<User> getUsersByCity(String cityName) {
        return usersDao.findAll();
    }

    public ArrayList<Auto> getAutoByCity(String city) {
        ArrayList<User> usersByCity = usersDao.findByCity(city);
        ArrayList<Auto> autos = new ArrayList<Auto>();
        for(User currentUser : usersByCity){
            Auto auto = autoDao.find(currentUser.getId());
            autos.add(auto);
        }
        return autos;
    }
}