package service;

import dao.AutoDao;
import dao.UsersDao;
import model.Auto;
import model.User;

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

    public List<User> getUsersByCity(String cityName) {
        return null;
    }

    public List<Auto> getAutoByCity(List<User> users) {
        return null;
    }
}